package com.example.core.gui.element.complex.frame;

import com.example.core.enumeration.element.Locators;
import com.example.core.enumeration.element.TypeNames;
import com.example.core.gui.element.base.AbstractFrame;
import com.example.core.gui.iface.export.complex.frame.ContentFrame;

public class KKContentFrame extends AbstractFrame implements ContentFrame {

    public KKContentFrame() {
        super(Locators.FRAME_CONTENT, TypeNames.CONTENT_FRAME);
    }
}
