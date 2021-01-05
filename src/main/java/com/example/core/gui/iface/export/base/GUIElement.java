package com.example.core.gui.iface.export.base;

import com.example.core.enumeration.element.TypeNames;

/**
 * Represents an abstract element on GUI.
 * <p>Since there is user behavior emulated, the concrete type of the element must match its appearance on GUI
 * regardless its technical implementation. For example, if element looks like a button but is actually a link in DOM,
 * it must be described as button object with appropriate operations and behaviors, not as link, because user actually
 * sees the button on GUI and not the link.
 */
public interface GUIElement {

    /**
     * Checks if this element exists in DOM and is actually visible on web page.
     *
     * @return {@code true} if this element exists in DOM and is actually visible on web page, otherwise {@code false}
     */
    boolean isPresent();

    /**
     * Gets the name of this element.
     *
     * @return the name of this element
     */
    String getName();

    /**
     * Gets the type hierarchy of this element consisting of type names from first parent to last child.
     * Allows to determine where this element is actually located on GUI in user-friendly form.
     * <p>Example: "Content -> Tab 'Product' -> Table 1 -> Row 2 -> Text field 'User Reference'"
     *
     * @return a {@code String} describing the type hierarchy of this element consisting of type names from
     *         first parent to last child
     * @see TypeNames
     */
    String getTypeHierarchy();
}
