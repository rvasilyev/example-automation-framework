package com.example.core.util;

import com.codeborne.selenide.Configuration;
import com.example.core.enumeration.system.SystemProps;

/**
 * A utility class to configure global test parameters. Cannot be instantiated.
 */
public class GlobalConfig {

    private GlobalConfig(){
        //prevents creating an instance of utility class
    }

    /**
     * Configures Selenide properties with values from {@code SystemProps}. If no value exists for the property
     * then default value is used.
     */
    public static void load(){
        Configuration.browser = System.getProperty(SystemProps.BROWSER.key(), SystemProps.BROWSER.defaultValue());
        Configuration.baseUrl = System.getProperty(SystemProps.APP_BASE_URL.key(), SystemProps.APP_BASE_URL.defaultValue());
        Configuration.headless = Boolean.parseBoolean(System.getProperty(SystemProps.BROWSER_HEADLESS.key(), SystemProps.BROWSER_HEADLESS.defaultValue()));
        Configuration.reportsFolder = System.getProperty(SystemProps.REPORTS_FOLDER.key(), SystemProps.REPORTS_FOLDER.defaultValue());
        Configuration.downloadsFolder = System.getProperty(SystemProps.DOWNLOADS_FOLDER.key(), SystemProps.DOWNLOADS_FOLDER.defaultValue());
        Configuration.driverManagerEnabled = Boolean.parseBoolean(System.getProperty(SystemProps.WDM_ENABLED.key(), SystemProps.WDM_ENABLED.defaultValue()));
        Configuration.pollingInterval = Long.parseLong(System.getProperty(SystemProps.WAITS_POLLING_INTERVAL.key(), SystemProps.WAITS_POLLING_INTERVAL.defaultValue()));
        Configuration.savePageSource = Boolean.parseBoolean(System.getProperty(SystemProps.SAVE_PAGE_SOURCE.key(), SystemProps.SAVE_PAGE_SOURCE.defaultValue()));
        Configuration.screenshots = Boolean.parseBoolean(System.getProperty(SystemProps.AUTO_SCREENSHOTS_ON_FAIL.key(), SystemProps.AUTO_SCREENSHOTS_ON_FAIL.defaultValue()));
        Configuration.clickViaJs = Boolean.parseBoolean(System.getProperty(SystemProps.CLICK_VIA_JS.key(), SystemProps.CLICK_VIA_JS.defaultValue()));
    }
}
