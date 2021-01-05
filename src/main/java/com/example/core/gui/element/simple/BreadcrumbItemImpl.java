package com.example.core.gui.element.simple;

import com.codeborne.selenide.ex.ElementNotFound;
import com.example.core.enumeration.element.Locators;
import com.example.core.enumeration.element.TypeNames;
import com.example.core.exception.export.ElementNotPresentException;
import com.example.core.gui.element.base.SimpleGUIElementImpl;
import com.example.core.gui.iface.simple.BreadcrumbItem;
import com.example.core.gui.iface.base.ComplexGUIElement;

public class BreadcrumbItemImpl extends SimpleGUIElementImpl implements BreadcrumbItem {

    public BreadcrumbItemImpl(String breadcrumbItemName, ComplexGUIElement container) {
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
