package com.example.core.gui.element.simple;

import com.example.core.enumeration.element.Locators;
import com.example.core.enumeration.element.TypeNames;
import com.example.core.gui.element.base.SimpleWebElement;
import com.example.core.gui.iface.export.base.ComplexGUIElement;
import com.example.core.gui.iface.export.simple.Icon;

public class KKIcon extends SimpleWebElement implements Icon {

    public KKIcon(String iconName, ComplexGUIElement container) {
        super(Locators.ICON, TypeNames.ICON, iconName, container);
    }
}
