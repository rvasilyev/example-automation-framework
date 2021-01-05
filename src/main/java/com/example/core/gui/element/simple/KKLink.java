package com.example.core.gui.element.simple;

import com.example.core.enumeration.element.Locators;
import com.example.core.enumeration.element.TypeNames;
import com.example.core.gui.element.base.SimpleWebElement;
import com.example.core.gui.iface.export.base.ComplexGUIElement;
import com.example.core.gui.iface.export.simple.Link;

public class KKLink extends SimpleWebElement implements Link {

    public KKLink(String linkName, ComplexGUIElement container) {
        super(Locators.LINK, TypeNames.LINK, linkName, container);
    }
}
