package com.example.core.gui.element.complex.frame;

import com.example.core.enumeration.element.Locators;
import com.example.core.enumeration.element.name.MenuItems;
import com.example.core.enumeration.element.TypeNames;
import com.example.core.gui.element.base.AbstractFrame;
import com.example.core.gui.element.simple.KKMenuItem;
import com.example.core.gui.iface.export.complex.frame.MenuFrame;
import com.example.core.gui.iface.export.simple.MenuItem;

public class KKMenuFrame extends AbstractFrame implements MenuFrame {

    public KKMenuFrame(){
        this(TypeNames.MENU_FRAME);
    }

    protected KKMenuFrame(TypeNames typeName){
        super(Locators.FRAME_MAIN_MENU, typeName);
    }

    @Override
    public MenuItem menuItem(MenuItems menuItemName) {

        return new KKMenuItem(menuItemName.value(), this);
    }

}
