package com.example.core.gui.element.complex.widget;

import com.example.core.enumeration.element.Locators;
import com.example.core.enumeration.element.TypeNames;
import com.example.core.gui.element.base.AbstractWidget;
import com.example.core.gui.iface.export.complex.widget.Alert;
import com.example.core.util.ScreenshotMaker;
import io.qameta.allure.Step;

public class KKAlertWidget extends AbstractWidget implements Alert {

    public KKAlertWidget() {
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
