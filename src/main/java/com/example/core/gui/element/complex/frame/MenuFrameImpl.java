package com.example.core.gui.element.complex.frame;

import com.example.core.enumeration.element.Locators;
import com.example.core.enumeration.element.name.MenuItems;
import com.example.core.enumeration.element.TypeNames;
import com.example.core.gui.element.base.FrameImpl;
import com.example.core.gui.element.simple.MenuItemImpl;
import com.example.core.gui.iface.complex.frame.MenuFrame;
import com.example.core.gui.iface.simple.MenuItem;

public class MenuFrameImpl extends FrameImpl implements MenuFrame {

    public MenuFrameImpl(){
        this(TypeNames.MENU_FRAME);
    }

    protected MenuFrameImpl(TypeNames typeName){
        super(Locators.FRAME_MAIN_MENU, typeName);
    }

    @Override
    public MenuItem menuItem(MenuItems menuItemName) {

        return new MenuItemImpl(menuItemName.value(), this);
    }

}
