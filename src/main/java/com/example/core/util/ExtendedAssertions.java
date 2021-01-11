package com.example.core.util;

import com.example.core.gui.iface.RequirableElement;
import com.example.core.gui.iface.base.GUIElement;
import com.example.core.gui.iface.base.SimpleGUIElement;
import io.qameta.allure.Step;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Assertions;

/**
 * A utility class containing {@code static} specific assertion methods to be used in tests creating step entries
 * in test report. Cannot be instantiated.
 */
public class ExtendedAssertions {

    private ExtendedAssertions(){
        //prevents creating an instance of utility class
    }

    /**
     * Checks if given object matches given matcher. If not, assertion error with given message will be thrown.
     * This message is also the step name in test report since this assertion creates the separate step entry.
     *
     * @param checkingValue an {@code Object} to check
     * @param matcher a {@code Matcher} used to check given object
     * @param messageOnError message to be shown on error if assertion fails, also used as step name in test report
     * @see Matcher
     * @see CoreMatchers
     */
    @Step("Check condition '{messageOnError}'")
    public static <T> void assertThat(Object checkingValue, Matcher<? super T> matcher, String messageOnError) {
        if (matcher.matches(checkingValue)) {
            ScreenshotMaker.screenshot("successfully-checked-" + messageOnError);
        } else {
            ScreenshotMaker.screenshot("check-failed-" + messageOnError);
            Assertions.fail("Condition not met: " + messageOnError + "\nExpected: " + matcher + "\nActual:  " + checkingValue);
        }
    }

    /**
     * Checks if given element is present on GUI. Creates the separate step entry in test report.
     *
     * @param element element to be checked
     */
    public static void assertIsPresent(GUIElement element){
        assertThat(element.isPresent(), CoreMatchers.is(true), element + " must be present");
    }

    /**
     * Checks if given element is not present on GUI. Creates the separate step entry in test report.
     *
     * @param element element to be checked
     */
    public static void assertIsNotPresent(GUIElement element){
        assertThat(element.isPresent(), CoreMatchers.is(false), element + " must not be present");
    }

    /**
     * Checks if given element is marked as required on GUI. Creates the separate step entry in test report.
     *
     * @param element element to be checked
     */
    public static void assertIsRequired(RequirableElement element){
        assertThat(element.isRequired(), CoreMatchers.is(true), element + " must be required");
    }

    /**
     * Checks if given element is enabled on GUI. Creates the separate step entry in test report.
     *
     * @param element element to be checked
     */
    public static void assertIsEnabled(SimpleGUIElement element){
        assertThat(element.isEnabled(), CoreMatchers.is(true), element + " must be enabled");
    }

    /**
     * Checks if given element is disabled on GUI. Creates the separate step entry in test report.
     *
     * @param element element to be checked
     */
    public static void assertIsDisabled(SimpleGUIElement element){
        assertThat(element.isEnabled(), CoreMatchers.is(false), element + " must be disabled");
    }

    /**
     * Checks if two objects are equal. Order doesn't matter. Creates the separate step entry in test report.
     *
     * @param object1 the first object
     * @param object2 the second object
     */
    public static void assertIsEqualTo(Object object1, Object object2){
        assertThat(object1, CoreMatchers.equalTo(object2), object1 + " must be equal to " + object2);
    }

    /**
     * Checks if two objects are not equal. Order doesn't matter. Creates the separate step entry in test report.
     *
     * @param object1 the first object
     * @param object2 the second object
     */
    public static void assertIsNotEqualTo(Object object1, Object object2){
        assertThat(object1, CoreMatchers.not(CoreMatchers.equalTo(object2)), object1 + " must be not equal to " + object2);
    }
}