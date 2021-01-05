package com.example.core.util;

import com.example.core.exception.TestFrameworkException;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.time.Duration;
import java.util.function.BooleanSupplier;

public class TestStepsUtil {

    private static final Logger LOG = LoggerFactory.getLogger(TestStepsUtil.class);

    private TestStepsUtil() {
        //prevents creating an instance of utility class
    }

    @Step("Select file '{sourceFile}'")
    public static void selectSourceFile(File sourceFile) {
        LOG.info("Selecting file '{}' for upload...", sourceFile);
        TestUtil.chooseFileForUpload(sourceFile);
        ScreenshotMaker.screenshot("file-" + sourceFile.getName() + "-selected");
        LOG.info("File '{}' selected for upload.", sourceFile);
    }

    @Step("Check given condition during {timeout} every {repeatingInterval} performing specified action")
    public static void waitUntilPerformingAction(Duration timeout, Duration repeatingInterval, BooleanSupplier action) {
        if (timeout.compareTo(repeatingInterval) < 0) {
            throw new TestFrameworkException("Periodic action: timeout " + timeout + " is less than repeating interval " + repeatingInterval);
        }

        long intervalInMillis = repeatingInterval.toMillis();
        long end = System.currentTimeMillis() + timeout.toMillis();

        while (System.currentTimeMillis() < end) {
            if (action.getAsBoolean()) {
                return;
            }

            try {
                Thread.sleep(intervalInMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new TestFrameworkException(e);
            }
        }
    }
}
