package com.example.core.gui.element.complex.widget;

import com.example.core.enumeration.element.Locators;
import com.example.core.enumeration.element.TypeNames;
import com.example.core.gui.element.base.WidgetImpl;
import com.example.core.gui.iface.complex.widget.Alert;
import com.example.core.util.ScreenshotMaker;
import io.qameta.allure.Step;

public class AlertWidgetImpl extends WidgetImpl implements Alert {

    public AlertWidgetImpl() {
        super(Locators.ALERT, TypeNames.ALERT);
    }

    @Override
    @Step("Close modal window")
    public void close() {
        getSelf().$x(Locators.ALERT_CLOSING_SYMBOL.value()).click();
        waitForUnlockedGUI();
        ScreenshotMaker.screenshot("modal-window-closed");
    }
}
