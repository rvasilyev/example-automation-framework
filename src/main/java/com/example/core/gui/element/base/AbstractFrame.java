package com.example.core.gui.element.base;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import com.example.core.enumeration.element.Locators;
import com.example.core.enumeration.element.TypeNames;
import com.example.core.exception.export.ElementNotPresentException;
import com.example.core.gui.iface.export.base.Frame;
import org.openqa.selenium.NoSuchFrameException;


public abstract class AbstractFrame extends ComplexWebElement implements Frame {

    protected AbstractFrame(Locators locator, TypeNames typeName) {
        super(Selenide.$x(locator.value()), typeName);
    }

    @Override
    protected SelenideElement getSelf() {
        try {
            SelenideElement selfRef = super.getSelf();
            Selenide.switchTo().defaultContent();
            if (selfRef.getTagName().equals("frame")) {
                Selenide.switchTo().frame(selfRef);
                return Selenide.$x(Locators.ROOT.value());
            } else {
                return selfRef;
            }
        } catch (ElementNotFound | NoSuchFrameException e){
           throw new ElementNotPresentException(this, e);
        }
    }

    @Override
    public boolean isPresent() {
        Selenide.switchTo().defaultContent();
        return super.isPresent();
    }
}
