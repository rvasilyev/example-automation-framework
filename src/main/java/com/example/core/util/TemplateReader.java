package com.example.core.util;

import com.example.core.exception.TestFrameworkException;
import freemarker.cache.FileTemplateLoader;
import freemarker.core.HTMLOutputFormat;
import freemarker.core.OutputFormat;
import freemarker.core.RTFOutputFormat;
import freemarker.core.XMLOutputFormat;
import freemarker.template.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Objects;
import java.util.Properties;

/**
 * A utility class which helps to create concrete files from parametrized templates. Cannot be instantiated.
 */
public class TemplateReader {

    private static final Logger LOG = LoggerFactory.getLogger(TemplateReader.class);

    /**
     * Version of FreeMarker used by adjusting its configuration.
     */
    private static final Version FREEMARKER_VERSION = Configuration.VERSION_2_3_30;

    private TemplateReader() {
        //prevents creating an instance of utility class
    }

    /**
     * Configures behavior of FreeMarker for processing templates by creating and adjusting a {@code Configuration} instance.
     *
     * @param outputFormat output format for processed template
     * @param baseDir base directory for loading templates
     * @return a {@code Configuration} instance representing current FreeMarker configuration
     * @throws IOException, if an IO error occurs
     */
    private static Configuration createFreemarkerConfiguration(OutputFormat outputFormat, File baseDir) throws IOException
    {
        Configuration configuration = new Configuration(FREEMARKER_VERSION);
        FileTemplateLoader fileLoader = new FileTemplateLoader(baseDir);
        configuration.setTemplateLoader(fileLoader);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setLogTemplateExceptions(true);
        configuration.setObjectWrapper(new DefaultObjectWrapperBuilder(FREEMARKER_VERSION).build());
        configuration.setOutputFormat(outputFormat);

        return  configuration;
    }

    /**
     * Reads a template {@code File}, processes it with FreeMarker using given output format and parameters from given
     * {@code Properties}.
     *
     * @param templateFile a {@code File} instance representing template file containing parameters to be replaced
     * @param properties {@code Properties} containing values of variables to replace in template file
     * @param outputFormat output format for Freemarker configuration
     * @return a {@code String} representation of given template with replaced values
     * @throws TestFrameworkException if an invalid output format is given or an {@code IOException} or
     * {@code TemplateException} occurred
     */
    public static String process(File templateFile, Properties properties, OutputFormats outputFormat) {
        Objects.requireNonNull(templateFile);
        Objects.requireNonNull(properties);

        try(StringWriter writer = new StringWriter(1000)) {
            Objects.requireNonNull(templateFile, templateFile + " not found as resource");
            LOG.info("templateFile = '{}' exists= {}", templateFile.getCanonicalPath(), templateFile.exists());
            if(!templateFile.exists()){
                throw new TestFrameworkException("Template '" + templateFile + "' doesn't exist");
            }

            OutputFormat format;
            switch (outputFormat){
                case XML: format = XMLOutputFormat.INSTANCE;
                    break;
                case HTML: format = HTMLOutputFormat.INSTANCE;
                    break;
                case RTF: format = RTFOutputFormat.INSTANCE;
                    break;
                default: throw new TestFrameworkException("Invalid output format for FreeMarker: " + outputFormat);
            }
            //Using file path caused problems, so extract simple file name and use it instead
            String fileSeparator = System.getProperty("file.separator");
            String absolutePath = templateFile.getAbsolutePath();
            String templateName = absolutePath.substring(absolutePath.lastIndexOf(fileSeparator) + 1);
            String basePath = absolutePath.substring(0,absolutePath.lastIndexOf(fileSeparator));
            Template template = createFreemarkerConfiguration(format, new File(basePath)).getTemplate(templateName);

            template.process(properties, writer);

            String response = writer.toString();

            LOG.debug("response={}", response);

            return response;
        } catch (IOException | TemplateException e) {
            LOG.error("Failed to use template {}", templateFile);
            throw new TestFrameworkException("Failed to use template " + templateFile.toString(), e);
        }
    }

    /**
     * An enumeration containing constants representing possible output formats for Freemarker configuration.
     */
    public enum OutputFormats {
        XML, HTML, RTF
    }
}
