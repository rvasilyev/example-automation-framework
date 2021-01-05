package com.example.core.gui.iface.simple;

import com.example.core.enumeration.DropboxOptionsEnum;
import com.example.core.gui.iface.base.SimpleGUIElement;

import java.util.List;

/**
 * Represents a dropbox on GUI. It's assumed, that dropbox is an element looking like a dropbox regardless its actual
 * technical implementation.
 */
public interface Dropbox extends SimpleGUIElement {

    /**
     * Checks if this dropbox contains an option with specified value. Comparing uses String.equals(), not String.contains().
     *
     * @param fullValueText value to be found inside option list of this dropbox
     * @return {@code true } if this dropbox contains an option with value exactly matching given value text,
     * otherwise {@code false}
     */
    boolean hasValue(String fullValueText);

    /**
     * Checks if selected option has a proper readable logical value from the option list of this dropbox, not only
     * dashes, spaces, metacharacters and so on.
     *
     * @return {@code true} if selected option has a proper readable logical value from the option list of this dropbox,
     * otherwise {@code false}
     */
    boolean hasValidOptionSelected();

    /**
     * Selects the option with specified value from the option list of this dropbox.
     *
     * @param enumConstant value to be selected in form of named constant
     * @see DropboxOptionsEnum
     */
    void selectValue(DropboxOptionsEnum enumConstant);

    /**
     * Selects first option with valid value from the option list of this dropbox. Valid value is a proper readable
     * logical value from the option list of this dropbox containing not only dashes, spaces, metacharacters and so on.
     */
    void selectFirstValidValue();

    /**
     * Obtains the list of all valid values from option list of this dropbox. Valid value is a proper readable
     * logical value from the option list of this dropbox containing not only dashes, spaces, metacharacters and so on.
     *
     * @return a {@code List<String>} instance containing all valid values from option list of this dropbox
     */
    List<String> getValidSelectableValues();

    /**
     * Gets the value of currently selected option of this dropbox.
     *
     * @return a {@code String} representing the value of currently selected option of this dropbox
     */
    String getSelectedValue();
}
