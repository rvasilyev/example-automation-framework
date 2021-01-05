package com.example.core.gui.element.base;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.example.core.enumeration.element.Locators;
import com.example.core.enumeration.element.TypeNames;
import com.example.core.enumeration.system.SystemProps;
import com.example.core.exception.TestFrameworkException;
import com.example.core.exception.export.ElementNotPresentException;
import com.example.core.gui.iface.base.ComplexGUIElement;
import com.example.core.gui.iface.base.GUIElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.regex.Pattern;

public abstract class GUIElementImpl implements GUIElement {

    private static final Logger LOG = LoggerFactory.getLogger(GUIElementImpl.class);

    private final SelenideElement self;
    private final String typeHierarchy;
    private final String name;

    protected GUIElementImpl(Locators locator, TypeNames typeName, String name, ComplexGUIElement container) {
        this.self = findSelf(locator, name, container);
        this.typeHierarchy = container.toString() + " -> " + typeName.value();
        this.name = name;
    }

    protected GUIElementImpl(SelenideElement self, TypeNames typeName) {
        this.self = self;
        this.typeHierarchy = typeName.value();
        this.name = "";
    }

    @Override
    public boolean isPresent() {
        return self.exists() && self.isDisplayed();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getTypeHierarchy() {
        return typeHierarchy;
    }

    @Override
    public String toString(){
        String nameRef = getName().trim();

        if(!nameRef.isEmpty()){
            Pattern pattern = Pattern.compile("^([a-z])+", Pattern.CANON_EQ);
            if(nameRef.matches(pattern.toString())){
                String firstChar = String.valueOf(nameRef.charAt(0));
                nameRef = nameRef.replaceFirst(firstChar, firstChar.toUpperCase());
            }
            nameRef = " '" + nameRef + "'" ;
        }

        return getTypeHierarchy() + nameRef;
    }

    protected SelenideElement getSelf() {
        return self;
    }

    protected void waitForUnlockedGUI(){
        SelenideElement spinner = Selenide.$x(Locators.SPINNER.value());
        if(spinner.exists() && spinner.has(Condition.attributeMatching("style", ".+block.+"))) {
            try {
                long waitsTimeoutInMillis = Long.parseLong(System.getProperty(SystemProps.WAITS_TIMEOUT.key(), SystemProps.WAITS_TIMEOUT.defaultValue()));
                Selenide.Wait().withTimeout(Duration.ofMillis(waitsTimeoutInMillis)).until(ExpectedConditions.attributeContains(spinner, "style", "none"));
            } catch (TimeoutException e) {
                LOG.warn("GUI is still locked, could cause interaction problems or wrong report information.");
            }
        }
    }

    private SelenideElement findSelf(Locators locator, String name, ComplexGUIElement container) {
        ComplexGUIElementImpl complexWebElement;
        if(container instanceof ComplexGUIElementImpl){
            complexWebElement = (ComplexGUIElementImpl) container;
        }else{
            throw new TestFrameworkException("Error while handling container for element '" + name
                    + "'\n Expected: " + ComplexGUIElementImpl.class.getName() + " Actual: " + container.getClass().getName());
        }
        String formattedLocator = locator.forName(name);
        try {
            return complexWebElement.getSelf().$x(formattedLocator);
        } catch (ElementNotPresentException e) {
            throw e;
        } catch (Exception e) {
            throw new TestFrameworkException("Failed to create element '" + name + "'. May be locator is wrong? Locator must be a proper XPath locator.\n Message: " + e.getMessage()
                    + "\n" + formattedLocator);
        }
    }
}
