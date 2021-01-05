package com.example;

import com.example.core.enumeration.element.name.Buttons;
import com.example.core.enumeration.element.name.TextFields;
import com.example.core.extension.TestConfigExtension;
import com.example.core.gui.iface.simple.Button;
import com.example.core.util.ExtendedAssertions;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.example.core.util.GUI.content;

@ExtendWith(TestConfigExtension.class)
@DisplayName("Example test suite")
class ExampleTest {

    @Test
    @Tags({
            @Tag("Example"),
            @Tag("Fast"),
            @Tag("UI")
    })
    @Epic("Example")
    @Feature("UI")
    @Story("Code demontration")
    @Owner("R. Vasilyev")
    @DisplayName("Login/logout emulation")
    @Description("Very long detailed test description.")
    void testExample1() {
        login("user1");
        logout();
    }

    @Test
    @Tags({
            @Tag("Example"),
            @Tag("Fast"),
            @Tag("UI")
    })
    @Epic("Example")
    @Feature("UI")
    @Story("Code demontration")
    @Owner("R. Vasilyev")
    @DisplayName("GUI objects call demonstration")
    @Description("Very long detailed test description.")
    void testExample2() {

        login("user2");






        logout();
    }

    @Step("Login as '{username}'")
    private void login(String username){
        content().textField(TextFields.TEXT_FIELD1).setValue(username);
        content().textField(TextFields.TEXT_FIELD1).setValue("password");
        Button buttonLogin = content().button(Buttons.BUTTON1);
        buttonLogin.click();
        ExtendedAssertions.assertIsNotPresent(buttonLogin);
    }

    @Step("Logout")
    private void logout(){
        Button buttonLogout = content().button(Buttons.BUTTON2);
        buttonLogout.click();
        ExtendedAssertions.assertIsNotPresent(buttonLogout);
    }
}
