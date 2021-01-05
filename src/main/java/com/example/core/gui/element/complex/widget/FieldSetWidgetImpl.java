package com.example.core.gui.element.complex.widget;

import com.example.core.enumeration.element.Locators;
import com.example.core.enumeration.element.TypeNames;
import com.example.core.enumeration.element.name.Buttons;
import com.example.core.gui.element.base.WidgetImpl;
import com.example.core.gui.element.simple.ButtonImpl;
import com.example.core.gui.iface.base.ComplexGUIElement;
import com.example.core.gui.iface.complex.widget.FieldSet;

public class FieldSetWidgetImpl extends WidgetImpl implements FieldSet {

    public FieldSetWidgetImpl(String fieldSetName, ComplexGUIElement container) {
        this(Locators.FIELD_SET, fieldSetName, container);
    }

    protected FieldSetWidgetImpl(Locators locator, String fieldSetName, ComplexGUIElement container){
        super(locator, TypeNames.FIELD_SET, fieldSetName, container);
    }

    @Override
    public void expand() {
        new ButtonImpl(Buttons.BUTTON1.value(), this).click();
    }

    @Override
    public void collapse() {
        new ButtonImpl(Buttons.BUTTON2.value(), this).click();
    }
}
