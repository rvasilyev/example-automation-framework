package com.example.core.gui.element.base;

import com.codeborne.selenide.Selenide;
import com.example.core.enumeration.element.Locators;
import com.example.core.enumeration.element.TypeNames;
import com.example.core.gui.iface.export.base.ComplexGUIElement;
import com.example.core.gui.iface.export.base.Widget;

public abstract class AbstractWidget extends ComplexWebElement implements Widget {

    protected AbstractWidget(Locators locator, TypeNames typeName) {
        super(Selenide.$x(locator.value()), typeName);
    }

    protected AbstractWidget(Locators locator, TypeNames typeName, String name, ComplexGUIElement container){
        super(locator, typeName, name, container);
    }
}
