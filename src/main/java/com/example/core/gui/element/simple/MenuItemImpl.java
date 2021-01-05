package com.example.core.gui.element.simple;

import com.example.core.enumeration.element.Locators;
import com.example.core.enumeration.element.TypeNames;
import com.example.core.gui.element.base.SimpleGUIElementImpl;
import com.example.core.gui.iface.base.ComplexGUIElement;
import com.example.core.gui.iface.simple.MenuItem;

public class MenuItemImpl extends SimpleGUIElementImpl implements MenuItem {

    public MenuItemImpl(String menuItemName, ComplexGUIElement container) {
        super(Locators.MENU_ITEM, TypeNames.MENU_ITEM, menuItemName, container);
    }

}
