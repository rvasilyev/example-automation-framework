package com.example.core.gui.iface.export;

/**
 * Determines a GUI element which can be clicked. Actually, all GUI elements can be technically clicked, but
 * this interface refers to element there is reason to click to affect its or other elements state. For example,
 * there is no reason to click a table or a frame, so those are not clickable in this sense, but button or checkbox are.
 */
public interface ClickableElement {

    /**
     * Clicks this element.
     */
    void click();
}
