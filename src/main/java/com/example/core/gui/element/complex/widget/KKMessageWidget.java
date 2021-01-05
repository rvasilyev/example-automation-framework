package com.example.core.gui.element.complex.widget;

import com.example.core.enumeration.element.Locators;
import com.example.core.enumeration.element.TypeNames;
import com.example.core.enumeration.element.name.Buttons;
import com.example.core.exception.export.ElementNotPresentException;
import com.example.core.gui.element.base.AbstractWidget;
import com.example.core.gui.element.simple.KKButton;
import com.example.core.gui.iface.export.base.ComplexGUIElement;
import com.example.core.gui.iface.export.complex.widget.Message;

public class KKMessageWidget extends AbstractWidget implements Message {

    public KKMessageWidget(ComplexGUIElement container) {
        super(Locators.MESSAGE, TypeNames.MESSAGE, "",container);
    }

    @Override
    public String getMessageText() {

        KKButton detailsButton = new KKButton(Buttons.BUTTON1.value(), this);
        if (detailsButton.isPresent()) {
            detailsButton.click();
        }

        String message = getSelf().text();
        if (message.isBlank()) {
            throw new ElementNotPresentException("No message found to obtain its text");
        } else {
            return message;
        }
    }
}
