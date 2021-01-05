package com.example.core.gui.element.complex.widget;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import com.example.core.enumeration.element.Locators;
import com.example.core.enumeration.element.TypeNames;
import com.example.core.exception.export.ElementNotPresentException;
import com.example.core.gui.element.base.AbstractWidget;
import com.example.core.gui.iface.export.base.ComplexGUIElement;
import com.example.core.gui.iface.export.complex.widget.Tab;
import com.example.core.util.ScreenshotMaker;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KKTabWidget extends AbstractWidget implements Tab {

    private static final Logger LOG = LoggerFactory.getLogger(KKTabWidget.class);

    public KKTabWidget(String tabName, ComplexGUIElement container) {
        super(Locators.TAB_CONTENT, TypeNames.TAB, tabName, container);
    }

    @Override
    public boolean isSelected(){
        return checkParentClassContains("tkonf_tab_active");
    }

    @Override
    public boolean isRequired() {
        return checkParentClassContains("error");
    }

    @Override
    public boolean isPresent() {
        SelenideElement tabHeader = getTabHeader();
        return tabHeader.exists() && tabHeader.isDisplayed();
    }

    @Override
    @Step("Click {this}")
    public void click() {
        try {
            getTabHeader().click();
            LOG.info("{} '{}' clicked.", getClass().getSimpleName(), getName());
            ScreenshotMaker.screenshot(toString() + "-clicked");
        } catch (ElementNotFound e){
            throw new ElementNotPresentException(this);
        }
    }

    private boolean checkParentClassContains(String value){
        try {
            String parentClassAttr = getTabHeader().parent().getAttribute("class");
            return (parentClassAttr != null) && parentClassAttr.contains(value);
        } catch (ElementNotFound e){
            throw new ElementNotPresentException(this);
        }
    }

    private SelenideElement getTabHeader(){
        return Selenide.$x(Locators.TAB_HEADER.forName(getName()));
    }
}
