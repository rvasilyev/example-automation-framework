package com.example.core.gui.element.base;

import com.codeborne.selenide.SelenideElement;
import com.example.core.enumeration.element.Locators;
import com.example.core.enumeration.element.TypeNames;
import com.example.core.gui.iface.base.ComplexGUIElement;

public abstract class ComplexGUIElementImpl extends GUIElementImpl implements ComplexGUIElement {

    protected ComplexGUIElementImpl(SelenideElement self, TypeNames typeName){
        super(self, typeName);
    }

    protected ComplexGUIElementImpl(Locators locator, TypeNames typeName, String name, ComplexGUIElement container){
        super(locator, typeName, name, container);
    }
}
