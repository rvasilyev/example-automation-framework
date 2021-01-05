package com.example.core.gui.element.simple;

import com.example.core.enumeration.element.Locators;
import com.example.core.enumeration.element.TypeNames;
import com.example.core.gui.element.base.SimpleGUIElementImpl;
import com.example.core.gui.iface.base.ComplexGUIElement;
import com.example.core.gui.iface.simple.Link;

public class LinkImpl extends SimpleGUIElementImpl implements Link {

    public LinkImpl(String linkName, ComplexGUIElement container) {
        super(Locators.LINK, TypeNames.LINK, linkName, container);
    }
}
