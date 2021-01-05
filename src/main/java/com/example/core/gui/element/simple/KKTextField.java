package com.example.core.gui.element.simple;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import com.example.core.enumeration.element.Locators;
import com.example.core.enumeration.element.TypeNames;
import com.example.core.exception.TestFrameworkException;
import com.example.core.exception.export.ElementNotPresentException;
import com.example.core.gui.element.base.SimpleWebElement;
import com.example.core.gui.iface.export.base.ComplexGUIElement;
import com.example.core.gui.iface.export.simple.TextField;
import com.example.core.util.ScreenshotMaker;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class KKTextField extends SimpleWebElement implements TextField {

    private static final Logger LOG = LoggerFactory.getLogger(KKTextField.class);

    public KKTextField(String textFieldName, ComplexGUIElement container) {
        super(Locators.TEXT_FIELD, TypeNames.TEXT_FIELD, textFieldName, container);
    }

    @Override
    public boolean isEnabled() {
        try {
            boolean hasDisablingTag = List.of("span", "div", "p").contains(getSelf().getTagName());
            if (hasDisablingTag || getSelf().is(Condition.readonly)) {
                return false;
            } else {
                return super.isEnabled();
            }
        } catch (ElementNotFound e){
            throw new ElementNotPresentException(this, e);
        }
    }

    @Override
    public boolean isBlank(){
        return getValue().isBlank();
    }

    @Override
    public String getValue() {
        try {
            SelenideElement selfRef = getSelf();
            if (selfRef.has(Condition.attribute("type", "password"))) {
                throw new TestFrameworkException("Unable to get value from password field");
            } else if (selfRef.has(Condition.attribute("value"))) {
                return selfRef.getValue();
            } else {
                return selfRef.text();
            }
        } catch (ElementNotFound e){
            throw new ElementNotPresentException(this, e);
        }
    }

    @Override
    @Step("Set value '{text}' in {this}")
    public void setValue(String value) {
        try {
            SelenideElement selfRef = getSelf();
            selfRef.clear();
            selfRef.sendKeys(value);
            boolean isNotLoginField = !selfRef.has(Condition.attribute("id", "username"))
                    && !selfRef.has(Condition.attribute("id", "userId"))
                    && !selfRef.has(Condition.attribute("name", "userid"))
                    && !selfRef.has(Condition.attribute("type", "password"));
            if (isNotLoginField) {
                selfRef.pressEnter();
                waitForUnlockedGUI();
            }
            LOG.info("In text field '{}' value '{}' set.", this, value);
            ScreenshotMaker.screenshot("in-text-field-" + getName().toLowerCase() + "-value-" + value + "-set");
        } catch (ElementNotFound e){
            throw new ElementNotPresentException(this, e);
        }
    }
}
