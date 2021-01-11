package com.example.core.gui.iface.complex.widget;

import com.example.core.gui.iface.base.Widget;

/**
 * Represents a table row on GUI.
 */
public interface Row extends Widget {

    /**
     * Gets the order number of this row from top to bottom within its table starting from 1.
     *
     * @return the order number of this row from top to bottom within its table starting from 1
     */
    int getOrderNumber();

    /**
     * Gets the table widget containing this row.
     *
     * @return the table widget containing this row
     */
    Table getTable();
}
