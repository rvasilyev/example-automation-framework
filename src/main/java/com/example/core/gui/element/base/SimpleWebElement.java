package com.example.core.gui.element.base;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import com.example.core.enumeration.element.Locators;
import com.example.core.enumeration.element.TypeNames;
import com.example.core.exception.export.ElementNotPresentException;
import com.example.core.gui.iface.export.base.ComplexGUIElement;
import com.example.core.gui.iface.export.base.SimpleGUIElement;
import com.example.core.util.ScreenshotMaker;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.stream.Stream;

public abstract class SimpleWebElement extends AbstractWebElement implements SimpleGUIElement {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleWebElement.class);

    protected SimpleWebElement(Locators locator, TypeNames typeName, String name, ComplexGUIElement container){
        super(locator, typeName, name, container);
    }

    @Override
    public boolean isEnabled() {
        try {
            return getSelf().isEnabled();
        } catch (ElementNotFound e){
            throw new ElementNotPresentException(this, e);
        }
    }

    @Override
    @Step("Click {this}")
    public void click() {
        try {
            getSelf().click();
            waitForUnlockedGUI();
            LOG.info("{} clicked.", this);
            ScreenshotMaker.screenshot(toString() + "-clicked");
        } catch (ElementNotFound e){
            throw new ElementNotPresentException(this, e);
        }
    }

    @Override
    public boolean isRequired(){
        try {
            SelenideElement selfRef = getSelf();

            String classAttr = Optional.ofNullable(selfRef.getAttribute("class")).orElse("null");
            String parentClassAttr = Optional.ofNullable(selfRef.parent().getAttribute("class")).orElse("null");
            boolean classAttrRequired = classAttr.contains("error") || classAttr.contains("required");
            boolean parentClassAttrRequired = parentClassAttr.contains("error")
                    || parentClassAttr.contains("required")
                    || parentClassAttr.contains("mandatory");

            if (classAttrRequired || parentClassAttrRequired) {
                return true;
            } else {
                String attribute = selfRef.getAttribute("title");
                if (attribute != null) {
                    Stream<String> attributeParts = Stream.of("requires value", "illegal value");
                    return attributeParts.anyMatch(attribute::contains);
                } else {
                    return false;
                }
            }
        } catch (ElementNotFound e){
             throw new ElementNotPresentException(this, e);
        }
    }
}
