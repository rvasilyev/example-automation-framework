package com.example.core.gui.iface.complex.widget;

import com.example.core.gui.iface.base.Widget;

/**
 * Represents an information message on GUI.
 */
public interface Message extends Widget {

    /**
     * Gets the text of this message.
     *
     * @return the text of this message in form of {@code String}
     */
    String getMessageText();
}
