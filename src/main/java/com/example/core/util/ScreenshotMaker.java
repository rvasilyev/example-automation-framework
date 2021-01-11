package com.example.core.util;

import com.codeborne.selenide.Screenshots;
import com.example.core.enumeration.system.SystemProps;
import com.example.core.exception.TestFrameworkException;
import io.qameta.allure.Allure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * A utility class, which helps to create and store screenshots in report and as separate files. Cannot be instantiated.
 */
public class ScreenshotMaker {

    private static final Logger LOG = LoggerFactory.getLogger(ScreenshotMaker.class);

    /**
     * The full path of folder where screenshots are stored. Unique for each thread. Works if
     * {@code separate.screenshots.enabled} system property is {@code true}
     */
    private static final ThreadLocal<Path> CONTEXT = new ThreadLocal<>();

    /**
     * Counter for screenshots stored in the context folder. Is used to order screenshots by adding order numbers
     * to their names. Unique for each thread. Works if {@code separate.screenshots.enabled} system property is {@code true}
     */
    private static final ThreadLocal<AtomicInteger> COUNTER = new ThreadLocal<>();

    /**
     * The {@code boolean} constant storing {@code separate.screenshots.enabled} system property value for many calls
     * in class methods.
     */
    private static final boolean SEPARATE_SCREENSHOTS_ENABLED = System.getProperty(SystemProps.SEPARATE_SCREENSHOTS_ENABLED.key(),
            SystemProps.SEPARATE_SCREENSHOTS_ENABLED.defaultValue()).equals("true");

    private ScreenshotMaker(){
        //prevents creating an instance of utility class
    }

    /**
     * Creates the screenshot context, namely a folder based on given root path, where screenshots
     * will be stored if {@code separate.screenshots.enabled} system property is {@code true}. Otherwise does nothing.
     * <p>There is a separate context for each thread in case of many parallel threads since context is stored in a
     * {@code ThreadLocal} variable.
     *
     * @param contextRootPath a {@code Path} representing base folder, where screenshot context will be created
     */
    public static void startContext(Path contextRootPath){
        if(SEPARATE_SCREENSHOTS_ENABLED){
            Path screenshotsFolder = contextRootPath.resolve("screenshots");
            CONTEXT.set(screenshotsFolder);
            COUNTER.set(new AtomicInteger());
            try {
                Files.createDirectories(screenshotsFolder);
                try(Stream<Path> stream = Files.walk(screenshotsFolder)) {
                    stream.filter(Files::isRegularFile)
                            .forEach(path -> {
                                try{
                                    Files.deleteIfExists(path);
                                }catch(IOException e){
                                    LOG.error(e.getMessage());
                                }
                            });
                }
            }catch(IOException e){
                LOG.error(e.getMessage());
            }
            LOG.info("Screenshot context started for '{}'.", contextRootPath);
        } else {
            LOG.info("Separate screenshots disabled. Screenshot context doesn't need to be started.");
        }
    }

    /**
     * Stops the screenshot context, namely cleans all metadata.
     */
    public static void stopContext(){
        if(SEPARATE_SCREENSHOTS_ENABLED) {
            CONTEXT.remove();
            COUNTER.remove();
            LOG.info("Screenshot context stopped.");
        } else {
            LOG.info("Screenshot context not started, nothing to stop.");
        }
    }

    /**
     * Takes a screenshot and stores it in report. If {@code separate.screenshots.enabled} system property is
     * {@code true}, creates also a separate screenshot file within screenshot context folder. The given screenshot name
     * will be transformed into following valid form:
     * <ul>
     *     <li>case will be changed into lower form
     *     <li>non-ASCII symbols will be transliterated
     *     <li>invalid meta-symbols and spaces will be replaced with '-' or '_'
     *     <li>some invalid symbols like asterisk or quotes will be totally skipped
     *     <li>order number will be append at the beginning of the given name
     * </ul>
     *
     * @param name the screenshot name
     */
    public static void screenshot(String name) {
        String editedName = editScreenshotName(name);
        File screenshotFile = Screenshots.takeScreenShotAsFile();

        if(screenshotFile != null) {
            try (FileInputStream fis = new FileInputStream(screenshotFile)) {
                Allure.addAttachment(editedName, fis);

                if(SEPARATE_SCREENSHOTS_ENABLED){
                    if(CONTEXT.get() == null){
                        throw new TestFrameworkException("Can't save screenshot file: no context specified for current thread.");
                    }
                    String screenshotName = String.format("%03d", COUNTER.get().getAndIncrement()) + "_" + editedName + ".png";
                    byte[] bytes = Files.readAllBytes(screenshotFile.toPath());
                    Path screenshot = CONTEXT.get().resolve(screenshotName);
                    Files.write(screenshot, bytes);
                    LOG.info("Screenshot saved: {}", screenshot);
                }
            } catch (Exception e) {
                LOG.error(e.getMessage());
            }
        }else{
            LOG.error("Failed to save screenshot '{}': screenshot file is null.", editedName);
        }
    }

    /**
     * Formats given {@code String}, namely screenshot name, as follows:
     * <ul>
     *     <li>case will be changed into lower form
     *     <li>non-ASCII symbols will be transliterated
     *     <li>invalid meta-symbols and spaces will be replaced with '-' or '_'
     *     <li>some invalid symbols like asterisk or quotes will be totally skipped
     *</ul>
     *
     * @param name screenshot name to be formatted
     * @return formatted screenshot name or default name if {@code NullPointerException} occurred
     */
    private static String editScreenshotName(String name) {
        try {
            String editedName = name.toLowerCase();
            editedName = editedName
                    .replace(" ", "-")
                    .replace("--", "-")
                    .replace("/", "_")
                    .replace(":", "-")
                    .replace(",", "-")
                    .replace("-->", "")
                    .replace("->", "")
                    .replace("'", "")
                    .replace("\"", "")
                    .replace("*", "");

            return editedName;
        }catch(NullPointerException e){
            return "screenshot-name-was-null";
        }
    }
}
