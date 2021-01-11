package com.example.core.gui.iface.complex.frame;

import com.example.core.enumeration.element.name.MenuItems;
import com.example.core.gui.iface.simple.MenuItem;
import com.example.core.gui.iface.base.Frame;

/**
 * Represents a menu, especially main menu, on GUI.
 */
public interface MenuFrame extends Frame {

    MenuItem menuItem(MenuItems menuItemName);
}
