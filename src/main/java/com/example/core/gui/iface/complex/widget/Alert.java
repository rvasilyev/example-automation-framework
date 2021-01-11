package com.example.core.gui.iface.complex.widget;

import com.example.core.gui.iface.ConfigArea;
import com.example.core.gui.iface.base.Widget;

/**
 * Represents a modal window on GUI except the native browser alert. It's assumed, that alert contains some other configuration
 * GUI elements like buttons, text fields, tables and so on, so is an alternative to frame.
 */
public interface Alert extends Widget, ConfigArea {

    /**
     * Closes this alert without saving configuration.
     */
    void close();
}
