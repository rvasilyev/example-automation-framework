package com.example.core.gui.iface.export.complex.frame;

import com.example.core.enumeration.element.name.MenuItems;
import com.example.core.gui.iface.export.simple.MenuItem;
import com.example.core.gui.iface.export.base.Frame;

public interface MenuFrame extends Frame {

    MenuItem menuItem(MenuItems menuItemName);
}
