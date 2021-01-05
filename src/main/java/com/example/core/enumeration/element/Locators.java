package com.example.core.enumeration.element;

public enum Locators {

    BREADCRUMB_ITEM(".//div[@id='nav']/ul/li/a[contains(text(),'%s')] | " +
            ".//div[@id='nav']/ul/li/span[contains(text(),'%s')]"),
    BUTTON(".//input[@type='submit' and contains(@value,'%s')] | " +
            ".//input[@type='button' and contains(@value,'%s')]"),
    CHECKBOX(".//p[starts-with(text(),'%s')]/preceding-sibling::input[@type='checkbox'] | " +
            ".//p[starts-with(text(),'%s')]/following-sibling::input[@type='checkbox'] | " +
            ".//label[starts-with(text(),'%s')]/parent::p/following-sibling::p[@class='tkonf_checkbox']/input[@type='checkbox'] | " +
            ".//label[starts-with(text(),'%s')]/parent::p/following-sibling::p[@class='tkonf_checkbox']/img | " +
            ".//span[starts-with(text(),'%s')]/parent::div/preceding-sibling::input[@type='checkbox']"),
    DROPBOX(".//div[starts-with(text(), '%s')]/following-sibling::div//select | " +
            ".//p[starts-with(text(), '%s')]/parent::div//select | " +
            ".//label[starts-with(text(), '%s')]/parent::p/following-sibling::div/select | " +
            ".//select[contains(@title,'%s')] | " +
            ".//select[contains(@alt,'%s')]"),
    DROPBOX_OPTION(".//option"),
    ICON(".//input[@type='image' and contains(@alt,'%s')] | " +
            ".//img[contains(@alt,'%s')]/parent::a"),
    MENU_ITEM(".//tbody/tr/td/div/a[@class='menuLink' and contains(@title,'%s')] | " +
            ".//div[@class='mainMenu']/a[contains(@class,'mainMenuLink') and contains(@title,'%s')]"),
    RADIOBUTTON(".//input[@type='radio' and contains(@alt,'%s')]"),
    TEXT_FIELD(".//label[starts-with(text(),'%s')]/parent::p/following-sibling::span/input[@type='text'] | " +
            ".//label[starts-with(text(),'%s')]/parent::p/following-sibling::span//span | " +
            ".//label[starts-with(text(),'%s')]/parent::p/following-sibling::div/span | " +
            ".//label[starts-with(text(),'%s')]/parent::p/following-sibling::input[@type='text'] | " +
            ".//label[starts-with(text(),'%s')]/parent::div/following-sibling::input[@type='text'] | " +
            ".//label[starts-with(text(),'%s')]/parent::div/following::input[@type='text'] | " +
            ".//label[starts-with(text(),'%s')]/parent::div/following-sibling::div/span | " +
            ".//div[starts-with(text(),'%s')]/parent::div/following-sibling::span | " +
            ".//div[contains(@title,'%s')]/span | " +
            ".//input[@type='text' and contains(@title,'%s')] | " +
            ".//div[contains(@id,'%s')]/textarea | " +
            ".//div[contains(@id,'%s') and count(./*)=0] | " +
            ".//div[contains(@class,'%s') or contains(@alt,'%s')]/p"),
    FIELD_SET(".//legend[starts-with(text(),'%s')]/parent::fieldset"),
    TAB_HEADER(".//ul[@class='tablist']/li/a[starts-with(text(),'%s')]"),
    TAB_CONTENT(".//a[starts-with(text(),'%s')]/parent::li[contains(@class,'tab_active')]/parent::ul[@class='tablist']/following-sibling::div[@class='tab_content']"),
    TABLE(".//descendant::table[%s]/parent::div"),
    TABLE_ROW(".//tbody/tr[%s]"),
    TABLE_COLUMN(".//td"),
    TABLE_HEADER(".//thead//p[starts-with(text(),'%s')]/ancestor::th | " +
            ".//thead//abbr[starts-with(text(),'%s')]/ancestor::th"),
    ALERT(".//div[@class='modal-content'] | .//scale-modal[@opened='true']"),
    LINK(".//a[starts-with(text(),'%s')]"),
    FRAME_MAIN_MENU(".//frame[@title='Navigation'] | " +
            ".//frame[@title='menu']"),
    FRAME_CONTENT(".//frame[@title='Content'] | " +
            ".//frame[@title='main_page']"),
    MESSAGE(".//p[@class='advice' or @class='message' or @class='error'] | " +
            ".//table[@class='error'] | " +
            ".//div[@id='details']"),
    ALERT_CLOSING_SYMBOL(".//button[@class='close'] | .//scale-icon[@title='Close']"),
    SIMPLE_ERROR_MESSAGE(".//*[@class='simple_error' or @class='errorlist' or contains(@class,'error-message')]"),
    SPINNER(".//div[contains(@class,'click-shield-cont')]"),
    FILE_UPLOAD_FIELD(".//input[@type='file']"),
    ROOT(".//html");

    private final String value;

    Locators(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public String forName(String elementName) {
        return value().replace("%s", elementName);
    }
}
