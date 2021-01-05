package com.example.core.gui.element.complex.widget;

import com.example.core.enumeration.element.Locators;
import com.example.core.enumeration.element.TypeNames;
import com.example.core.enumeration.element.name.Buttons;
import com.example.core.exception.export.ElementNotPresentException;
import com.example.core.gui.element.base.WidgetImpl;
import com.example.core.gui.element.simple.ButtonImpl;
import com.example.core.gui.iface.base.ComplexGUIElement;
import com.example.core.gui.iface.complex.widget.Message;

public class MessageWidgetImpl extends WidgetImpl implements Message {

    public MessageWidgetImpl(ComplexGUIElement container) {
        super(Locators.MESSAGE, TypeNames.MESSAGE, "",container);
    }

    @Override
    public String getMessageText() {

        ButtonImpl detailsButton = new ButtonImpl(Buttons.BUTTON1.value(), this);
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
