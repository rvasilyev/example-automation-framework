package com.example.core.gui.element.complex.frame;

import com.example.core.enumeration.element.Locators;
import com.example.core.enumeration.element.TypeNames;
import com.example.core.gui.element.base.FrameImpl;
import com.example.core.gui.iface.complex.frame.ContentFrame;

public class ContentFrameImpl extends FrameImpl implements ContentFrame {

    public ContentFrameImpl() {
        super(Locators.FRAME_CONTENT, TypeNames.CONTENT_FRAME);
    }
}
