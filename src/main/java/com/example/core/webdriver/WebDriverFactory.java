package com.example.core.webdriver;

import com.example.core.enumeration.system.SystemProps;
import com.example.core.exception.TestFrameworkException;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * A factory creating {@code WebDriver} instances for all supported browsers. Cannot be instantiated.
 */
public class WebDriverFactory {

    private static final Logger LOG = LoggerFactory.getLogger(WebDriverFactory.class);

    private WebDriverFactory() {
        //prevents creating an instance of utility class
    }

    /**
     * Creates and returns a {@code WebDriver} instance based on {@code selenide.browser} system property.
     * The main settings are:
     * <ul>
     *     <li>headless mode according {@code selenide.headless} system property
     *     <li>downloads folder according {@code selenide.downloadsFolder} system property
     *     <li>window-size=1920x1080
     *     <li>acceptInsecureCerts=true
     *     <li>unhandledPromptBehavior=accept
     *     <li>unexpectedAlertBehaviour=accept
     *     <li>pageLoadStrategy=normal
     *     <li>proxy=null
     * </ul>
     * If {@code selenide.browser} contains an invalid value, a {@code TestFrameworkException} will be thrown.
     *
     * @return a {@code WebDriver} instance based on {@code selenide.browser} system property
     * @throws TestFrameworkException if {@code selenide.browser} contains an invalid value
     */
    public static WebDriver getWebDriver(){
        String browser = System.getProperty(SystemProps.BROWSER.key(), SystemProps.BROWSER.defaultValue());
        switch(browser){
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setHeadless(System.getProperty(SystemProps.BROWSER_HEADLESS.key(), SystemProps.BROWSER_HEADLESS.defaultValue()).equals("true"));
                chromeOptions.setAcceptInsecureCerts(true);
                chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
                chromeOptions.addArguments("--window-size=1920x1080"); // to see full page on screenshots in headless mode
                chromeOptions.addArguments("--disable-modal-animations");
                chromeOptions.addArguments("--stable-release-mode");
                chromeOptions.addArguments("--start-maximized"); // to overwrite '--window-size' option for window mode, for headless mode does nothing
                chromeOptions.setProxy(null);
                chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);

                Map<String, Object> prefs = new HashMap<>();

                prefs.put("download.directory_upgrade", true);
                prefs.put("profile.default_content_settings.automatic_downloads", true);
                prefs.put("profile.default_content_settings.popups", 0);
                prefs.put("download.default_directory", createDownloadDirectory().getAbsolutePath());
                chromeOptions.setExperimentalOption("prefs", prefs);

                return new ChromeDriver(chromeOptions);
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setHeadless(System.getProperty(SystemProps.BROWSER_HEADLESS.key(), SystemProps.BROWSER_HEADLESS.defaultValue()).equals("true"));
                firefoxOptions.setAcceptInsecureCerts(true);
                firefoxOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
                firefoxOptions.addArguments("--window-size=1920,1080"); // to see full page on screenshots
                firefoxOptions.setProxy(null);
                firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);

                FirefoxProfile firefoxProfile = new FirefoxProfile();
                firefoxProfile.setPreference("browser.download.folderList", 2);
                firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
                firefoxProfile.setPreference("browser.download.dir", createDownloadDirectory().getAbsolutePath());
                firefoxProfile.setPreference("browser.helperapps.neverAsk.saveToDisk","application/pdf,application/excel,text/xml");
                firefoxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
                firefoxProfile.setPreference("browser.download.manager.showAlertOnComplete", false);
                firefoxProfile.setPreference("browser.download.manager.closeWhenDone", false);
                firefoxProfile.setAcceptUntrustedCertificates(true);
                firefoxProfile.setAssumeUntrustedCertificateIssuer(false);

                firefoxOptions.setProfile(firefoxProfile);

                return new FirefoxDriver(firefoxOptions);
            default: throw new TestFrameworkException("No valid browser defined to create web driver.");
        }
    }

    /**
     * Creates and returns a {@code File} instance representing browser download directory based on
     * {@code selenide.downloadsFolder} system property.
     *
     * @return a {@code File} instance representing browser download directory
     */
    private static File createDownloadDirectory(){
        File downloadDirectory = new File(SystemProps.DOWNLOADS_FOLDER.defaultValue());
        if (!downloadDirectory.exists()) {
            boolean downloadDirCreated = downloadDirectory.mkdir();
            if (!downloadDirCreated) {
                LOG.warn("Warning: download directory was not created!");
            }
        }

        return downloadDirectory;
    }
}
