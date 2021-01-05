package com.example.core.gui.element.simple;

import com.codeborne.selenide.ex.ElementNotFound;
import com.example.core.enumeration.element.Locators;
import com.example.core.enumeration.element.TypeNames;
import com.example.core.exception.export.ElementNotPresentException;
import com.example.core.gui.element.base.SimpleWebElement;
import com.example.core.gui.iface.export.base.ComplexGUIElement;
import com.example.core.gui.iface.export.simple.Radiobutton;

public class KKRadiobutton extends SimpleWebElement implements Radiobutton {

    public KKRadiobutton(String radiobuttonName, ComplexGUIElement container){
        super(Locators.RADIOBUTTON, TypeNames.RADIONUTTON, radiobuttonName, container);
    }

    @Override
    public boolean isSelected() {
        try {
            return getSelf().isSelected();
        } catch (ElementNotFound e) {
            throw new ElementNotPresentException(this, e);
        }
    }
}
