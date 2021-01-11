package com.example.core.gui.iface.base;

import com.example.core.gui.iface.ClickableElement;
import com.example.core.gui.iface.RequirableElement;

/**
 * Represents a simple element on GUI. Simple are those elements, which can't contain other elements inside themself.
 */
public interface SimpleGUIElement extends GUIElement, ClickableElement, RequirableElement {

    /**
     * Checks if this element is enabled on GUI.
     *
     * @return {@code true} if this element is enabled on GUI, otherwise {@code false}
     */
    boolean isEnabled();
}
