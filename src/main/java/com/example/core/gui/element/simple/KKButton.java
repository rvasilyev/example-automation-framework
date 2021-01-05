package com.example.core.gui.element.simple;

import com.example.core.enumeration.element.Locators;
import com.example.core.enumeration.element.TypeNames;
import com.example.core.gui.element.base.SimpleWebElement;
import com.example.core.gui.iface.export.simple.Button;
import com.example.core.gui.iface.export.base.ComplexGUIElement;

public class KKButton extends SimpleWebElement implements Button {

    public KKButton(String buttonName, ComplexGUIElement container) {
        super(Locators.BUTTON, TypeNames.BUTTON, buttonName, container);
    }
}
