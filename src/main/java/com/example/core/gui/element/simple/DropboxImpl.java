package com.example.core.gui.element.simple;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import com.example.core.enumeration.DropboxOptionsEnum;
import com.example.core.enumeration.element.Locators;
import com.example.core.enumeration.element.TypeNames;
import com.example.core.exception.export.ElementNotPresentException;
import com.example.core.gui.element.base.SimpleGUIElementImpl;
import com.example.core.gui.iface.base.ComplexGUIElement;
import com.example.core.gui.iface.simple.Dropbox;
import com.example.core.util.ScreenshotMaker;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class DropboxImpl extends SimpleGUIElementImpl implements Dropbox {

    private static final Logger LOG = LoggerFactory.getLogger(DropboxImpl.class);

    public DropboxImpl(String dropboxName, ComplexGUIElement container) {
        super(Locators.DROPBOX, TypeNames.DROPBOX, dropboxName, container);
    }

    @Override
    public boolean hasValue(String fullValueText) {
        try {
            return getSelf().$$x(Locators.DROPBOX_OPTION.value()).stream()
                    .anyMatch(element -> element.getText().equals(fullValueText));
        } catch (ElementNotFound e){
            throw new ElementNotPresentException(this, e);
        }
    }

    @Override
    public boolean hasValidOptionSelected() {
        String selectedValue = getSelectedValue();
        return selectedValue != null && isOptionValid(selectedValue);
    }

    @Override
    @Step("Select value '{enumConstant}' in {this}")
    public void selectValue(DropboxOptionsEnum enumConstant) {
        try {
            String valueText = enumConstant.value();
            if(!hasValue(valueText)){
                throw new ElementNotPresentException("No option '" + valueText + "' is present in " + this);
            }
            getSelf().selectOption(valueText);
            waitForUnlockedGUI();
            LOG.info("In dropbox '{}' value '{}' selected.", this, valueText);
            ScreenshotMaker.screenshot("in-dropbox-" + getName().toLowerCase() + "-value-with-text-" + valueText + "-selected");
        } catch (ElementNotFound e){
            throw new ElementNotPresentException(this, e);
        }
    }

    @Override
    @Step("Select first valid value in {this}")
    public void selectFirstValidValue() {
        try {
            String valueText = getValidSelectableValues()
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new ElementNotPresentException("No valid value is present in " + this));
            getSelf().selectOption(valueText);
            waitForUnlockedGUI();
            LOG.info("In dropbox '{}' first valid value '{}' selected.", this, valueText);
            ScreenshotMaker.screenshot("in-dropbox-" + getName().toLowerCase() + "-first-valid-value-selected");
        } catch (ElementNotFound e){
            throw new ElementNotPresentException(this, e);
        }
    }

    @Override
    public List<String> getValidSelectableValues() {
        try {
            ElementsCollection dropboxOptions = getSelf().$$x(Locators.DROPBOX_OPTION.value());
            return dropboxOptions.stream()
                    .map(SelenideElement::getText)
                    .filter(this::isOptionValid)
                    .collect(Collectors.toList());
        } catch (ElementNotFound e){
            throw new ElementNotPresentException(this, e);
        }
    }

    @Override
    public String getSelectedValue() {
        try {
            return getSelf().getSelectedText();
        } catch (ElementNotFound e){
            throw new ElementNotPresentException(this, e);
        }
    }

    private boolean isOptionValid(String optionText) {
        return !optionText.isEmpty() && !"---".equals(optionText) &&
                !optionText.startsWith("[") && !optionText.endsWith("]");
    }
}
