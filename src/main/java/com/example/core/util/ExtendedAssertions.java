package com.example.core.util;

import com.example.core.gui.iface.export.RequirableElement;
import com.example.core.gui.iface.export.base.GUIElement;
import com.example.core.gui.iface.export.base.SimpleGUIElement;
import io.qameta.allure.Step;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Assertions;

public class ExtendedAssertions extends Assertions {

    private ExtendedAssertions(){
        //prevents creating an instance of utility class
    }

    @Step("Check condition '{messageOnError}'")
    public static <T> void assertThat(Object checkingValue, Matcher<? super T> matcher, String messageOnError) {
        if (matcher.matches(checkingValue)) {
            ScreenshotMaker.screenshot("successfully-checked-" + messageOnError);
        } else {
            ScreenshotMaker.screenshot("check-failed-" + messageOnError);
            fail("Condition not met: " + messageOnError + "\nExpected: " + matcher + "\nActual:  " + checkingValue);
        }
    }

    public static void assertIsPresent(GUIElement element){
        assertThat(element.isPresent(), CoreMatchers.is(true), element + " must be present");
    }

    public static void assertIsNotPresent(GUIElement element){
        assertThat(element.isPresent(), CoreMatchers.is(false), element + " must not be present");
    }

    public static void assertIsRequired(RequirableElement element){
        assertThat(element.isRequired(), CoreMatchers.is(true), element + " must be required");
    }

    public static void assertIsEnabled(SimpleGUIElement element){
        assertThat(element.isEnabled(), CoreMatchers.is(true), element + " must be enabled");
    }

    public static void assertIsDisabled(SimpleGUIElement element){
        assertThat(element.isEnabled(), CoreMatchers.is(false), element + " must be disabled");
    }

    public static void assertIsEqualTo(Object object1, Object object2){
        assertThat(object1, CoreMatchers.equalTo(object2), object1 + " must be equal to " + object2);
    }

    public static void assertIsNotEqualTo(Object object1, Object object2){
        assertThat(object1, CoreMatchers.not(CoreMatchers.equalTo(object2)), object1 + " must be not equal to " + object2);
    }
}