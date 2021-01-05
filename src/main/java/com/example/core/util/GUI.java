package com.example.core.util;

import com.example.core.gui.element.complex.frame.ContentFrameImpl;
import com.example.core.gui.element.complex.frame.MenuFrameImpl;
import com.example.core.gui.element.complex.widget.AlertWidgetImpl;
import com.example.core.gui.iface.complex.frame.ContentFrame;
import com.example.core.gui.iface.complex.frame.MenuFrame;
import com.example.core.gui.iface.complex.widget.Alert;

/**
 * This utility class is the entry point for accessing the GUI elements. Cannot be instantiated.
 */
public class GUI {

    //these constants contain proxy objects of main widgets for calling in methods to avoid unnecessary creation of many instances
    private static final Alert alert = new AlertWidgetImpl();
    private static final ContentFrame content = new ContentFrameImpl();
    private static final MenuFrame mainMenu = new MenuFrameImpl();

    private GUI() {
        //prevents creating an instance of utility class
    }

    /**
     * Accesses the alert (modal dialog) on GUI.
     *
     * @return a {@code Alert} proxy instance representing alert (modal dialog) on GUI
     */
    public static Alert alert() {
        return alert;
    }

    /**
     * Accesses the main content area on GUI.
     *
     * @return a {@code ContentFrame} proxy instance representing main content area on GUI
     */
    public static ContentFrame content() {
        return content;
    }

    /**
     * Accesses the main menu on GUI.
     *
     * @return a {@code MenuFrame} proxy instance representing main menu on GUI
     */
    public static MenuFrame mainMenu() {
        return mainMenu;
    }
}
