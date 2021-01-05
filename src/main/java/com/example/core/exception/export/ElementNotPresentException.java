package com.example.core.exception.export;

import com.example.core.gui.iface.export.base.GUIElement;

public class ElementNotPresentException extends RuntimeException{

    private static final String MESSAGE_PREFIX = "Element is not present: ";

    public ElementNotPresentException(Throwable inner) {
        super(inner);
    }

    public ElementNotPresentException(String message, Throwable inner) {
        super(message, inner);
    }

    public ElementNotPresentException(String message) {
        super(message);
    }

    public ElementNotPresentException(GUIElement element, Throwable inner) {
        super(MESSAGE_PREFIX + element.toString(), inner);
    }

    public ElementNotPresentException(GUIElement element) {
        super(MESSAGE_PREFIX + element.toString());
    }
}
