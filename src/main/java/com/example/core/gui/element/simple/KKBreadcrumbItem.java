package com.example.core.gui.element.simple;

import com.codeborne.selenide.ex.ElementNotFound;
import com.example.core.enumeration.element.Locators;
import com.example.core.enumeration.element.TypeNames;
import com.example.core.exception.export.ElementNotPresentException;
import com.example.core.gui.element.base.SimpleWebElement;
import com.example.core.gui.iface.export.simple.BreadcrumbItem;
import com.example.core.gui.iface.export.base.ComplexGUIElement;

public class KKBreadcrumbItem extends SimpleWebElement implements BreadcrumbItem {

    public KKBreadcrumbItem(String breadcrumbItemName, ComplexGUIElement container) {
        super(Locators.BREADCRUMB_ITEM, TypeNames.BREADCRUMB_ITEM, breadcrumbItemName, container);
    }

    @Override
    public boolean isSelected(){
        try {
            return getSelf().getTagName().equals("span");
        } catch (ElementNotFound e){
            throw  new ElementNotPresentException(this);
        }
    }
}
