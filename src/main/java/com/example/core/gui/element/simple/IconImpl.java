package com.example.core.gui.element.simple;

import com.example.core.enumeration.element.Locators;
import com.example.core.enumeration.element.TypeNames;
import com.example.core.gui.element.base.SimpleGUIElementImpl;
import com.example.core.gui.iface.base.ComplexGUIElement;
import com.example.core.gui.iface.simple.Icon;

public class IconImpl extends SimpleGUIElementImpl implements Icon {

    public IconImpl(String iconName, ComplexGUIElement container) {
        super(Locators.ICON, TypeNames.ICON, iconName, container);
    }
}
