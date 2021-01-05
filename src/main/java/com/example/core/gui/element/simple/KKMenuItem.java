package com.example.core.gui.element.simple;

import com.example.core.enumeration.element.Locators;
import com.example.core.enumeration.element.TypeNames;
import com.example.core.gui.element.base.SimpleWebElement;
import com.example.core.gui.iface.export.base.ComplexGUIElement;
import com.example.core.gui.iface.export.simple.MenuItem;

public class KKMenuItem extends SimpleWebElement implements MenuItem {

    public KKMenuItem(String menuItemName, ComplexGUIElement container) {
        super(Locators.MENU_ITEM, TypeNames.MENU_ITEM, menuItemName, container);
    }

}
