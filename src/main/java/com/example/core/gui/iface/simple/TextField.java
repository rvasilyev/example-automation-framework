package com.example.core.gui.iface.simple;

import com.example.core.gui.iface.base.SimpleGUIElement;

/**
 * Represents a text field on GUI. It's assumed, that text field is an element looking like a text input field or text
 * area regardless its actual technical implementation.
 */
public interface TextField extends SimpleGUIElement {

    /**
     * Gets the current value of this text field.
     *
     * @return the current value of this text field
     */
    String getValue();

    /**
     * Sets the value of this text field matching given value.
     *
     * @param value a {@code String} to be set into this text field as its value
     */
    void setValue(String value);
}
