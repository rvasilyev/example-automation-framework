package com.example.core.gui.iface;

import com.codeborne.selenide.Selenide;
import com.example.core.enumeration.StorageEnum;
import com.example.core.enumeration.element.Locators;
import com.example.core.enumeration.element.name.Buttons;
import com.example.core.enumeration.element.name.Dropboxes;
import com.example.core.enumeration.element.name.Radiobuttons;
import com.example.core.enumeration.element.name.TextFields;
import com.example.core.gui.element.complex.widget.TableWidgetImpl;
import com.example.core.gui.element.simple.*;
import com.example.core.gui.iface.base.ComplexGUIElement;
import com.example.core.gui.iface.base.Widget;
import com.example.core.gui.iface.complex.widget.Table;
import com.example.core.gui.iface.simple.*;

/**
 * Determines a GUI element, which can contain some other basic/common elements and gives access to them.
 */
public interface ConfigArea extends ComplexGUIElement {

    /**
     * Refers to a table on GUI using its order number and including ancestor element. Useful to specify searching
     * in case of many similar elements on GUI or to make calls in code clearer.
     *
     * @param number the order number of table on GUI from top to bottom starting from 1
     * @param container including ancestor element, which contains this table
     * @return a {@code Table} instance representing table element with given order number on GUI
     * @see Table
     */
    default Table table(int number, Widget container) {
        return new TableWidgetImpl(number, container);
    }

    /**
     * Refers to a table on GUI using its order number.
     *
     * @param number the order number of table on GUI from top to bottom starting from 1
     * @return a {@code Table} instance representing table element with given order number on GUI
     * @see Table
     */
    default Table table(int number) {
        return new TableWidgetImpl(number, this);
    }

    /**
     * Refers to a button on GUI using its name and including ancestor element. Useful to specify searching
     * in case of many similar elements on GUI or to make calls in code clearer.
     *
     * @param name the name of button on GUI
     * @param container including ancestor element, which contains this button
     * @return a {@code Button} instance representing button element with given name on GUI
     * @see Button
     */
    default Button button(Buttons name, Widget container) {
        return new ButtonImpl(name.value(), container);
    }

    /**
     * Refers to a button on GUI using its name.
     *
     * @param name the name of button on GUI
     * @return a {@code Button} instance representing button element with given name on GUI
     * @see Button
     */
    default Button button(Buttons name) {
        return new ButtonImpl(name.value(), this);
    }

    /**
     * Refers to a dropbox on GUI using its name and including ancestor element. Useful to specify searching
     * in case of many similar elements on GUI or to make calls in code clearer.
     *
     * @param name the name of dropbox on GUI
     * @param container including ancestor element, which contains this dropbox
     * @return a {@code Dropbox} instance representing dropbox element with given name on GUI
     * @see Dropbox
     */
    default Dropbox dropbox(Dropboxes name, Widget container) {
        return new DropboxImpl(name.value(), container);
    }

    /**
     * Refers to a dropbox on GUI using its name.
     *
     * @param name the name of dropbox on GUI
     * @return a {@code Dropbox} instance representing dropbox element with given name on GUI
     * @see Dropbox
     */
    default Dropbox dropbox(Dropboxes name) {
        return new DropboxImpl(name.value(), this);
    }

    /**
     * Refers to a radiobutton on GUI using its name and including ancestor element. Useful to specify searching
     * in case of many similar elements on GUI or to make calls in code clearer.
     *
     * @param name the name of radiobutton on GUI
     * @param container including ancestor element, which contains this radiobutton
     * @return a {@code Radiobutton} instance representing radiobutton element with given name on GUI
     * @see Radiobutton
     */
    default Radiobutton radiobutton(Radiobuttons name, Widget container) {
        return new RadiobuttonImpl(name.value(), container);
    }

    /**
     * Refers to a radiobutton on GUI using its name.
     *
     * @param name the name of radiobutton on GUI
     * @return a {@code Radiobutton} instance representing radiobutton element with given name on GUI
     * @see Radiobutton
     */
    default Radiobutton radiobutton(Radiobuttons name) {
        return new RadiobuttonImpl(name.value(), this);
    }

    /**
     * Refers to a text input field or text area on GUI using its name and including ancestor element. Useful to specify searching
     * in case of many similar elements on GUI or to make calls in code clearer.
     *
     * @param name the name of text field on GUI
     * @param container including ancestor element, which contains this text field
     * @return a {@code TextField} instance representing text field or text area element with given name on GUI
     * @see TextField
     */
    default TextField textField(TextFields name, Widget container) {
        return new TextFieldImpl(name.value(), container);
    }

    /**
     * Refers to a text input field or text area on GUI using its name.
     *
     * @param name the name of text field on GUI
     * @return a {@code TextField} instance representing text field or text area element with given name on GUI
     * @see TextField
     */
    default TextField textField(TextFields name) {
        return new TextFieldImpl(name.value(), this);
    }

    /**
     * Refers to a link on GUI using its name or text and including ancestor element. Useful to specify searching
     * in case of many similar elements on GUI or to make calls in code clearer.
     *
     * @param nameOrText the name or text of link on GUI
     * @param container including ancestor element, which contains this link
     * @return a {@code Link} instance representing link element with given name or text on GUI
     * @see Link
     * @see StorageEnum
     */
    default Link link(StorageEnum nameOrText, Widget container) {
        return new LinkImpl(nameOrText.value(), container);
    }

    /**
     * Refers to a link on GUI using its name or text.
     *
     * @param nameOrText the name or text of link on GUI
     * @return a {@code Link} instance representing link element with given name or text on GUI
     * @see Link
     * @see StorageEnum
     */
    default Link link(StorageEnum nameOrText) {
        return new LinkImpl(nameOrText.value(), this);
    }

    /**
     * Checks if element contains simple error messages except errors representing by {@code Message} objects.
     *
     * @return {@code true} if element contains simple error messages, otherwise {@code false}
     * @see com.example.core.gui.iface.complex.widget.Message
     */
    default boolean hasErrors() {
        return !Selenide.$$x(Locators.SIMPLE_ERROR_MESSAGE.value()).isEmpty();
    }
}
