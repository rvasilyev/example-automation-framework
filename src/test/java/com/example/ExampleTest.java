package com.example;

import com.example.core.enumeration.element.name.*;
import com.example.core.extension.TestConfigExtension;
import com.example.core.gui.iface.complex.widget.Tab;
import com.example.core.gui.iface.complex.widget.Table;
import com.example.core.gui.iface.simple.Button;
import com.example.core.gui.iface.simple.Dropbox;
import com.example.core.util.ExtendedAssertions;
import com.example.core.util.TestStepsUtil;
import io.qameta.allure.*;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.nio.file.Path;

import static com.example.core.util.GUI.*;

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
    @Story("Code demonstration")
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

        mainMenu().menuItem(MenuItems.MENU_ITEM1).click();
        content().textField(TextFields.TEXT_FIELD1).setValue("some value");
        content().button(Buttons.BUTTON1).click();

        Table table1 = content().table(1);
        ExtendedAssertions.assertThat(table1.isEmpty(), CoreMatchers.is(false), "Table 1 must not be empty.");
        content().icon(Icons.ICON1, table1.row(1)).click();

        Tab tab1 = content().tab(Tabs.TAB1);
        if(!tab1.isSelected()){
            tab1.click();
        }
        ExtendedAssertions.assertIsRequired(tab1);

        Dropbox dropbox1 = content().dropbox(Dropboxes.DROPBOX1);
        ExtendedAssertions.assertIsRequired(dropbox1);
        dropbox1.selectFirstValidValue();

        TestStepsUtil.selectSourceFile(Path.of("examples").resolve("example.xml"));
        content().button(Buttons.BUTTON2).click();
        if(alert().isPresent()){
            alert().close();
        }

        String messageSuccessfulUpload = content().message().getMessageText();
        ExtendedAssertions.assertIsEqualTo(messageSuccessfulUpload, "File successfully uploaded.");

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
