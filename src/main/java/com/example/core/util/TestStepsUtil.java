package com.example.core.util;

import com.example.core.exception.TestFrameworkException;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.time.Duration;
import java.util.function.BooleanSupplier;

/**
 * A utility class containing {@code static} help methods assumed to be used in tests as steps creating entries in test
 * report. Cannot be instantiated.
 */
public class TestStepsUtil {

    private static final Logger LOG = LoggerFactory.getLogger(TestStepsUtil.class);

    private TestStepsUtil() {
        //prevents creating an instance of utility class
    }

    /**
     * Fills the file upload field on GUI creating a step in report.
     *
     * @param filePath path to uploading file
     */
    @Step("Select file '{filePath}'")
    public static void selectSourceFile(Path filePath) {
        LOG.info("Selecting file '{}' for upload...", filePath);
        TestUtil.chooseFileForUpload(filePath);
        ScreenshotMaker.screenshot("file-" + filePath.getFileName() + "-selected");
        LOG.info("File '{}' selected for upload.", filePath);
    }

    /**
     * Waits for given amount of time with polling using given interval and performing given action. Action can be simply
     * condition without any additional statements if needed. If given action or condition returns {@code true},
     * stops waiting skipping rest of timeout.
     *
     * @param timeout a {@code Duration} instance representing how long must the method wait for given action or condition
     * @param repeatingInterval a {@code Duration} instance representing how often must the method check the result of
     *                          given action or condition
     * @param action a set of instructions or simply condition, which must be performed with returning {@code true} by success
     * @throws TestFrameworkException if timeout is less, than repeating interval or an {@code InterruptedException} occurred
     */
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
