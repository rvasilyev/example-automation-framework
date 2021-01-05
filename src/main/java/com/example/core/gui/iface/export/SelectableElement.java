package com.example.core.gui.iface.export;

/**
 * Determines a GUI element which can be activated via its selection.
 */
public interface SelectableElement {

    /**
     * Checks if element is currently selected or not.
     *
     * @return {@code true} if element is currently selected, otherwise {@code false}
     */
    boolean isSelected();
}
