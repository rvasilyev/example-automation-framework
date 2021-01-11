package com.example.core.util;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.ex.ElementNotFound;
import com.example.core.enumeration.StorageEnum;
import com.example.core.enumeration.element.Locators;
import com.example.core.enumeration.system.SystemProps;
import com.example.core.exception.TestFrameworkException;
import com.example.core.exception.export.ElementNotPresentException;
import com.example.core.webdriver.WebDriverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

/**
 * A utility class containing {@code static} help methods assumed to be used in tests. Cannot be instantiated.
 */
public class TestUtil {

    private static final Logger LOG = LoggerFactory.getLogger(TestUtil.class);

    private TestUtil() {
        //prevents creating an instance of utility class
    }

    /**
     * Maps a custom {@code String} value to an enumeration constant. Uses {@code CONSTANT.name()} or {@code CONSTANT.value()}
     * if given enumeration implements {@code StorageEnum} interface to compare the values.
     * <p>Can return {@code null} in certain cases.
     *
     * @param clazz enumeration {@code Class} instance given reference {@code String} will be mapped to
     * @param referenceValue a {@code String}, which must be mapped to given enumeration {@code Class} instance
     * @return an enumeration constant, if any matches to given {@code String} found, or {@code null} if no matches
     * found or given {@code String} is {@code null} or empty.
     * @see StorageEnum
     */
    public static <T extends Enum<T>> T mapEnumConstant(Class<T> clazz, String referenceValue) {

        if (referenceValue == null || referenceValue.isEmpty()) {
            return null;
        }

        boolean isStorageEnum = false;
        if (List.of(clazz.getInterfaces()).contains(StorageEnum.class)) {
            isStorageEnum = true;
        }

        for (T enumValue : clazz.getEnumConstants()) {
            if (isStorageEnum) {
                StorageEnum storageEnumValue = (StorageEnum) enumValue;
                if (storageEnumValue.value().equals(referenceValue)) {
                    return enumValue;
                }
            } else {
                if (enumValue.name().equals(referenceValue)) {
                    return enumValue;
                }
            }
        }

        return null;
    }

    /**
     * Returns a {@code String} instance representing a date calculated from current system date plus given number of days.
     * Default time zone is used.
     *
     * @param daysOffset an {@code int} number of days to add to current date
     * @return a {@code String} representing calculated date
     */
    public static String currentDatePlusDays(int daysOffset) {
        return LocalDateTime.now().plusDays(daysOffset).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    /**
     * Navigates to URL from {@code selenide.baseUrl} system property.
     *
     * @throws TestFrameworkException if {@code MalformedURLException} occurred
     */
    public static void openApplication() {
        WebDriverRunner.setWebDriver(WebDriverFactory.getWebDriver());
        String baseUrl = System.getProperty(SystemProps.APP_BASE_URL.key(), SystemProps.APP_BASE_URL.defaultValue());
        try {
            navigateTo(new URL(baseUrl));
        } catch (MalformedURLException e){
            throw new TestFrameworkException(e);
        }
    }

    /**
     * Closes and destroys currently used {@code WebDriver} instance.
     */
    public static void closeApplication() {
        Selenide.closeWebDriver();
    }

    /**
     * Finds the file upload field on GUI and fills it with full given file path. The file path will be converted to
     * absolute form.
     *
     * @param filePath file path, which absolute form will be set into file upload field
     * @throws ElementNotPresentException if file upload field is not present on GUI
     */
    public static void chooseFileForUpload(Path filePath) {
        try {
            Selenide.$x(Locators.FILE_UPLOAD_FIELD.value()).sendKeys(filePath.toAbsolutePath().toString());
        } catch (ElementNotFound e){
            throw new ElementNotPresentException("File upload field is not present.", e);
        }
    }

    /**
     * Opens given {@code URL}.
     *
     * @param url an {@code URL} to be opened
     */
    public static void navigateTo(URL url) {
        LOG.info("Opening URL '{}'...", url);
        Selenide.open(url);
        LOG.info("Navigation finished for URL '{}'", url);
    }

    /**
     * Creates 'environment.properties' file for Allure report containing environment info. If file already exists,
     * it will be overridden. Herewith {@code allure.results.directory} system property is used.
     * <p>Despite using version information from GUI, this method does no actions on GUI for itself
     * to avoid hidden dependencies and misunderstanding when calling from outer code.
     * <p>If an exception occurs, the file will just not be created but work will not be stopped.
     */
    public static synchronized void manageAllureEnvironmentProps() {
        try {
            File environmentProps = Path.of(System.getProperty(SystemProps.ALLURE_RESULTS_DIRECTORY.key(),
                    SystemProps.ALLURE_RESULTS_DIRECTORY.defaultValue()), "environment.properties").toFile();
            if (environmentProps.createNewFile()) {
                LOG.info("File '{}' successfully created.", environmentProps);
            } else {
                LOG.warn("File '{}' already exists and will be overridden.", environmentProps);
            }

            try (FileWriter fw = new FileWriter(environmentProps, false)) {
                String unknownValue = "unknown";
                Properties properties = new Properties();
                properties.setProperty("browser", System.getProperty(SystemProps.BROWSER.key(), SystemProps.BROWSER.defaultValue()));
                properties.setProperty("test.environment.url", System.getProperty(SystemProps.APP_BASE_URL.key(), unknownValue));
                properties.setProperty("OS.version", System.getProperty("os.version", unknownValue));
                properties.setProperty("JDK.version", System.getProperty("java.version", unknownValue));
                properties.setProperty("selenide.version", System.getProperty("selenide.version", unknownValue));
                properties.setProperty("allure.version", System.getProperty("allure.version", unknownValue));
                properties.setProperty("junit.version", System.getProperty("junit.version", unknownValue));
                properties.store(fw, "");
            }

            LOG.info("Data successfully stored in '{}'.", environmentProps);
        } catch (Exception e) {
            LOG.error("Error while writing 'environment.properties' for Allure:\n", e);
        }
    }

    /**
     * Gets current test environment basic URL in form of {@code StringBuilder}, for example 'http://www.example.com:8080'
     *
     * @return current test environment basic URL in form of {@code StringBuilder}
     * @throws TestFrameworkException if {@code URISyntaxException} or {@code MalformedURLException} occurred
     */
    public static URL constructUrl(String pathWithoutHostAndPort) {
        try {
            URI uri = new URI(WebDriverRunner.url());

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(uri.getScheme()).append("://").append(uri.getHost());
            if (uri.getPort() >= 0) {
                stringBuilder.append(":").append(uri.getPort());
            }
            stringBuilder.append(pathWithoutHostAndPort);

            return new URL(stringBuilder.toString());
        } catch (URISyntaxException | MalformedURLException e) {
            throw new TestFrameworkException(e);
        }
    }
}
