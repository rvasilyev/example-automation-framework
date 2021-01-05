package com.example.core.gui.element.simple;

import com.codeborne.selenide.ex.ElementNotFound;
import com.example.core.enumeration.element.Locators;
import com.example.core.enumeration.element.TypeNames;
import com.example.core.exception.export.ElementNotPresentException;
import com.example.core.gui.element.base.SimpleGUIElementImpl;
import com.example.core.gui.iface.base.ComplexGUIElement;
import com.example.core.gui.iface.simple.Radiobutton;

public class RadiobuttonImpl extends SimpleGUIElementImpl implements Radiobutton {

    public RadiobuttonImpl(String radiobuttonName, ComplexGUIElement container){
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
