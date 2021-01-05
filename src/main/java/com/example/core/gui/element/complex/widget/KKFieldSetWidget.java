package com.example.core.gui.element.complex.widget;

import com.example.core.enumeration.element.Locators;
import com.example.core.enumeration.element.TypeNames;
import com.example.core.enumeration.element.name.Buttons;
import com.example.core.gui.element.base.AbstractWidget;
import com.example.core.gui.element.simple.KKButton;
import com.example.core.gui.iface.export.base.ComplexGUIElement;
import com.example.core.gui.iface.export.complex.widget.FieldSet;

public class KKFieldSetWidget extends AbstractWidget implements FieldSet {

    public KKFieldSetWidget(String fieldSetName, ComplexGUIElement container) {
        this(Locators.FIELD_SET, fieldSetName, container);
    }

    protected KKFieldSetWidget(Locators locator, String fieldSetName, ComplexGUIElement container){
        super(locator, TypeNames.FIELD_SET, fieldSetName, container);
    }

    @Override
    public void expand() {
        new KKButton(Buttons.BUTTON1.value(), this).click();
    }

    @Override
    public void collapse() {
        new KKButton(Buttons.BUTTON2.value(), this).click();
    }
}
