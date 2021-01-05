package com.example.core.gui.iface.export.base;

import com.example.core.gui.iface.export.ClickableElement;
import com.example.core.gui.iface.export.RequirableElement;

public interface SimpleGUIElement extends GUIElement, ClickableElement, RequirableElement {

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    boolean isEnabled();
}
