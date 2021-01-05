package com.example.core.gui.iface.export;

/**
 * Determines a GUI element which can be indicated as required (mandatory to be filled/selected and so on).
 */
public interface RequirableElement {

    /**
     * Checks if element is currently marked as required or not.
     *
     * @return {@code true} if element is currently marked as required, otherwise {@code false}
     */
    boolean isRequired();
}
