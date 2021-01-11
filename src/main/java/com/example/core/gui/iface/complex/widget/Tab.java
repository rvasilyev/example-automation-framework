package com.example.core.gui.iface.complex.widget;

import com.example.core.gui.iface.ClickableElement;
import com.example.core.gui.iface.RequirableElement;
import com.example.core.gui.iface.SelectableElement;
import com.example.core.gui.iface.base.Widget;

/**
 * Represents the tab on GUI. Tab is not only header in tab list, its content also belongs to it since tab is a complex
 * element.
 */
public interface Tab extends Widget, RequirableElement, ClickableElement, SelectableElement {
}
