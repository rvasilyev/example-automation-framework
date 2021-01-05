package com.example.core.enumeration.system;

//TODO: Wrap all Selenide properties
public enum SystemProps {

    SEPARATE_SCREENSHOTS_ENABLED("separate.screenshots.enabled", "false"),
    BROWSER("selenide.browser", "chrome"),
    BROWSER_VERSION("selenide.browserVersion", ""),
    BROWSER_SIZE("selenide.browserSize", "1366x768"),
    BROWSER_START_MAXIMIZED("selenide.startMaximized", "false"),
    BROWSER_HEADLESS("selenide.headless", "true"),
    APP_BASE_URL("selenide.baseUrl", "http://localhost:8080/example"),
    REPORTS_FOLDER("selenide.reportsFolder", "target/reports/" + BROWSER.defaultValue),
    DOWNLOADS_FOLDER("selenide.downloadsFolder", "target/downloads/" + BROWSER.defaultValue),
    WDM_ENABLED("selenide.driverManagerEnabled", "false"),
    WAITS_TIMEOUT("selenide.timeout", "4000"),
    WAITS_POLLING_INTERVAL("selenide.pollingInterval", "50"),
    AUTO_SCREENSHOTS_ON_FAIL("selenide.screenshots", "false"),
    SAVE_PAGE_SOURCE("selenide.savePageSource", "false"),
    CLICK_VIA_JS("selenide.clickViaJs", "true"),
    ALLURE_RESULTS_DIRECTORY("allure.results.directory", "target/allure-results"),
    ALLURE_LINK_ISSUE_PATTERN("allure.link.issue.pattern", "https://jira.devops.telekom.de/browse/{}"),
    ALLURE_LINK_TMS_PATTERN("allure.link.tms.pattern", "https://jira.devops.telekom.de/browse/{}");

    private final String key;
    private final String defaultValue;

    SystemProps(String key, String defaultValue){
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public String key() {
        return key;
    }

    public String defaultValue(){
        return  defaultValue;
    }
}
