package com.example.core.extension;

import com.example.core.enumeration.element.name.Buttons;
import com.example.core.enumeration.element.name.MenuItems;
import com.example.core.enumeration.system.SystemProps;
import com.example.core.exception.TestFrameworkException;
import com.example.core.exception.export.ElementNotPresentException;
import com.example.core.gui.iface.export.complex.widget.Message;
import com.example.core.gui.iface.export.simple.MenuItem;
import com.example.core.util.ScreenshotMaker;
import com.example.core.util.TestUtil;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.logging.log4j.ThreadContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

import static com.example.core.util.GUI.*;

public class TestConfigExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback, TestExecutionExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(TestConfigExtension.class);
    private static final File USER_SOURCE = new File("src/main/resources/UserList.xlsx");
    private static final Path REPORTS_ROOT_FOLDER = Path.of(System.getProperty(SystemProps.REPORTS_FOLDER.key(), SystemProps.REPORTS_FOLDER.defaultValue()));
    private static final String LOG_CONTEXT_MAP_KEY = "LogContext";
    private static final String LOG_FILE_MAP_KEY = "LogFile";
    private static final String TEST_NAME_MAP_KEY = "TestName";
    private static final String LOG_FILE_SUFFIX = "Log.html";

    @Override
    public void beforeTestExecution(ExtensionContext context) {
        context.getTestMethod().ifPresentOrElse(testMethod -> {
                    String testClassFullName = testMethod.getDeclaringClass().getName();
                    String pathSubstring = testClassFullName.substring(testClassFullName.indexOf("test"));
                    String[] pathComponents = pathSubstring.split("\\.");

                    String rawPath = String.join(File.separator, pathComponents);
                    String testMethodName = testMethod.getName();
                    Path contextRootPath = REPORTS_ROOT_FOLDER.resolve(rawPath).resolve(testMethodName);

                    ThreadContext.put(LOG_CONTEXT_MAP_KEY, contextRootPath.resolve("logs").toString());
                    ThreadContext.put(LOG_FILE_MAP_KEY, testMethodName + LOG_FILE_SUFFIX);
                    ThreadContext.put(TEST_NAME_MAP_KEY, testMethodName);

                    ScreenshotMaker.startContext(contextRootPath);
                },
                () -> LOG.error("Test method not found. Report context is not started."));
        TestUtil.openApplication();
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        ScreenshotMaker.stopContext();
        TestUtil.closeApplication();

        String logFileName = ThreadContext.get(LOG_FILE_MAP_KEY);
        String logFilePath = ThreadContext.get(LOG_CONTEXT_MAP_KEY) + File.separator + logFileName;
        try(FileInputStream fis = new FileInputStream(logFilePath)){
            Allure.addAttachment(logFileName, "text/html", fis, ".html");
        } catch (FileNotFoundException e){
            LOG.error("Cannot attach log to Allure report: log file '{}' not found.", logFilePath);
        } catch (IOException e){
            LOG.error("Cannot attach log to Allure report: {}", e.getMessage());
        }
        ThreadContext.clearMap();
    }

    @Override
    @Step("Testfehler ggf. bearbeiten und Test beenden")
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        String errorText = null;

        try {
            if (content().isPresent()) {
                Message errorMessage = content().message();
                if(errorMessage.isPresent()){
                    errorText = errorMessage.getMessageText();
                }
            }

            if(mainMenu().isPresent()) {
                if(alert().isPresent()){
                    alert().close();
                }
                MenuItem itemAbmelden = mainMenu().menuItem(MenuItems.MENU_ITEM1);
                MenuItem itemSchliessen = mainMenu().menuItem(MenuItems.MENU_ITEM2);
                if (itemAbmelden.isPresent()) {
                    itemAbmelden.click();
                    if(alert().isPresent()) {
                        alert().button(Buttons.BUTTON1).click();
                    }
                } else if (itemSchliessen.isPresent()) {
                    itemSchliessen.click();
                }
            }

            if(errorText == null && throwable instanceof ElementNotPresentException){
                errorText = throwable.getMessage();
            }
        } catch (TestFrameworkException e) {
            throw e;
        }  catch (AssertionError | Exception e) {
            LOG.error("Skipping errors during logout while handling test error:\n{}", e.getMessage());
        }

        if (errorText != null) {
            LOG.error(errorText, throwable);
            Assertions.fail(errorText, throwable);
        } else {
            LOG.error("Error occurred.", throwable);
            throw throwable;
        }
    }
}
