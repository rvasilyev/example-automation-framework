package com.example.core.gui.iface.complex.widget;

import com.example.core.gui.iface.base.Widget;

/**
 * Represents a field set on GUI, which groups some elements (not only fields) and separates them graphically from other
 * elements.
 */
public interface FieldSet extends Widget {

    /**
     * Expands this field set.
     */
    void expand();

    /**
     * Collapses this field set.
     */
    void collapse();
}
