package com.example.core.soap;


import com.example.core.exception.TestFrameworkException;
import com.example.core.util.TemplateReader;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.XMLConstants;
import javax.xml.soap.*;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * This class represents a SOAP service connection point in KonKal. Can be instantiated only from
 * its package. Contains predefined constants representing used points to avoid unnecessary creation of many objects
 * and many parallel requests to these points, which cause errors and bad responses.
 */
public class SoapEndPoint {

    private static final Logger LOG = LoggerFactory.getLogger(SoapEndPoint.class);

    /**
     * The full URL of this {@code SoapEndPoint} instance, where requests could be sent
     */
    private final URL url;

    /**
     * Constructs new {@code SoapEndPoint} with given {@code URL}
     *
     * @param url the full URL of constructing {@code SoapEndPoint} instance
     */
    public SoapEndPoint(URL url) {
        this.url = url;
    }

    /**
     * Processes given template using given properties via {@code TemplateReader} and sends its content as the request
     * to the {@code SoapEndPoint} this method is calling on. This method is thread-safe.
     *
     * @param properties {@code Properties} with values for given template file to be passed to {@code TemplateReader}
     * @param template a template file containing request body to be passed to {@code TemplateReader}
     * @return result of the SOAP request to the {@code SoapEndPoint} this method is calling on
     * @see TemplateReader
     */
    @Step("Send SOAP request using template '{template}'")
    public synchronized String sendRequest(Properties properties, File template) {
        LOG.info("Step 'send' started");
        LOG.info("SOAP call on '{}' with template '{}'", url, template);

        String postBody = TemplateReader.process(template, properties, TemplateReader.OutputFormats.XML);
        LOG.debug("postBody={}", postBody);

        SOAPConnection soapConnection;
        String result;
        try {
            // create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            soapConnection = soapConnectionFactory.createConnection();

            // send SOAP Message to SOAP server
            MessageFactory factory = MessageFactory.newInstance();
            SOAPMessage soapRequest = factory.createMessage(new MimeHeaders(), new ByteArrayInputStream(postBody.getBytes(StandardCharsets.UTF_8)));
            SOAPMessage soapResponse = soapConnection.call(soapRequest, url);
            soapConnection.close();

            // process the SOAP response
            result = transformResponse(soapResponse);
            LOG.info("Response SOAP message = {}", result);
        }catch (Exception e) {
            throw new TestFrameworkException(e);
        }

        Allure.addAttachment("SOAP_request", "text/xml", postBody, ".xml");
        LOG.info("Step 'send' done");

        return result;
    }

    /**
     * Gets the URL of this {@code SoapEndPoint} instance.
     *
     * @return the URL of this {@code SoapEndPoint} instance
     */
    public URL getUrl() {
        return url;
    }

    private String transformResponse(SOAPMessage soapResponse){
        try (StringWriter writer = new StringWriter()) {

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
            Transformer transformer = transformerFactory.newTransformer();
            Source sourceContent = soapResponse.getSOAPPart().getContent();
            StreamResult result = new StreamResult(writer);
            transformer.transform(sourceContent, result);

            return writer.toString();
        } catch (TransformerException | SOAPException | IOException e) {
            throw new TestFrameworkException("Failed to convert SOAPMessage to String", e);
        }
    }
}
