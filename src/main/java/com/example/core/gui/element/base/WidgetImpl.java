package com.example.core.gui.element.base;

import com.codeborne.selenide.Selenide;
import com.example.core.enumeration.element.Locators;
import com.example.core.enumeration.element.TypeNames;
import com.example.core.gui.iface.base.ComplexGUIElement;
import com.example.core.gui.iface.base.Widget;

public abstract class WidgetImpl extends ComplexGUIElementImpl implements Widget {

    protected WidgetImpl(Locators locator, TypeNames typeName) {
        super(Selenide.$x(locator.value()), typeName);
    }

    protected WidgetImpl(Locators locator, TypeNames typeName, String name, ComplexGUIElement container){
        super(locator, typeName, name, container);
    }
}
