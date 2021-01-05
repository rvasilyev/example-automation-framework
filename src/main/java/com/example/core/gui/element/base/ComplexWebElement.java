package com.example.core.gui.element.base;

import com.codeborne.selenide.SelenideElement;
import com.example.core.enumeration.element.Locators;
import com.example.core.enumeration.element.TypeNames;
import com.example.core.gui.iface.export.base.ComplexGUIElement;

public abstract class ComplexWebElement extends AbstractWebElement implements ComplexGUIElement {

    protected ComplexWebElement(SelenideElement self, TypeNames typeName){
        super(self, typeName);
    }

    protected ComplexWebElement(Locators locator, TypeNames typeName, String name, ComplexGUIElement container){
        super(locator, typeName, name, container);
    }
}
