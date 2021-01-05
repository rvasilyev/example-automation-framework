package com.example.core.gui.element.simple;

import com.example.core.enumeration.element.Locators;
import com.example.core.enumeration.element.TypeNames;
import com.example.core.gui.element.base.SimpleGUIElementImpl;
import com.example.core.gui.iface.simple.Button;
import com.example.core.gui.iface.base.ComplexGUIElement;

public class ButtonImpl extends SimpleGUIElementImpl implements Button {

    public ButtonImpl(String buttonName, ComplexGUIElement container) {
        super(Locators.BUTTON, TypeNames.BUTTON, buttonName, container);
    }
}
