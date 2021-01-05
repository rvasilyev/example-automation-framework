package com.example.core.gui.element.simple;

import com.codeborne.selenide.ex.ElementNotFound;
import com.example.core.enumeration.element.Locators;
import com.example.core.enumeration.element.TypeNames;
import com.example.core.exception.export.ElementNotPresentException;
import com.example.core.gui.element.base.SimpleWebElement;
import com.example.core.gui.iface.export.simple.Checkbox;
import com.example.core.gui.iface.export.base.ComplexGUIElement;

public class KKCheckbox extends SimpleWebElement implements Checkbox {

    public KKCheckbox(String checkboxName, ComplexGUIElement container) {
        super(Locators.CHECKBOX, TypeNames.CHECKBOX, checkboxName, container);
    }

    @Override
    public boolean isEnabled(){
        try {
            if (getSelf().getTagName().equals("img")) {
                return false;
            } else {
                return super.isEnabled();
            }
        }catch (ElementNotFound e){
            throw new ElementNotPresentException(this, e);
        }
    }

    @Override
    public boolean isSelected(){
        try {
            return getSelf().isSelected();
        } catch (ElementNotFound e) {
            throw new ElementNotPresentException(this, e);
        }
    }
}
