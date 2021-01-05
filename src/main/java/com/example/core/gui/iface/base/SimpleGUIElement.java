package com.example.core.gui.iface.base;

import com.example.core.gui.iface.ClickableElement;
import com.example.core.gui.iface.RequirableElement;

public interface SimpleGUIElement extends GUIElement, ClickableElement, RequirableElement {

    boolean isEnabled();
}
