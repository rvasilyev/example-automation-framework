package com.example.core.gui.iface;

import com.example.core.enumeration.element.name.*;
import com.example.core.gui.element.complex.widget.FieldSetWidgetImpl;
import com.example.core.gui.element.complex.widget.MessageWidgetImpl;
import com.example.core.gui.element.complex.widget.TabWidgetImpl;
import com.example.core.gui.element.simple.BreadcrumbItemImpl;
import com.example.core.gui.element.simple.CheckboxImpl;
import com.example.core.gui.element.simple.IconImpl;
import com.example.core.gui.iface.base.Widget;
import com.example.core.gui.iface.complex.widget.FieldSet;
import com.example.core.gui.iface.complex.widget.Message;
import com.example.core.gui.iface.complex.widget.Tab;
import com.example.core.gui.iface.simple.BreadcrumbItem;
import com.example.core.gui.iface.simple.Checkbox;
import com.example.core.gui.iface.simple.Icon;

/**
 * Determines a GUI element, which can contain other additional more specific elements and gives access to them.
 */
public interface ExtendedConfigArea extends ConfigArea {

    /**
     * Refers to a unspecified service message on GUI.
     *
     * @return a {@code Message} instance representing unspecified service message on GUI
     * @see Message
     */
    default Message message(){
        return new MessageWidgetImpl(this);
    }

    /**
     * Refers to a field set on GUI using its name and including ancestor element. Useful to specify searching in case
     * of many similar elements on GUI or to make calls in code clearer.
     *
     * @param name the name of field set on GUI
     * @param container including ancestor element, which contains this field set
     * @return a {@code FieldSet} instance representing field set element with given name on GUI
     * @see FieldSet
     */
    default FieldSet fieldSet(FieldSets name, Widget container) {
        return new FieldSetWidgetImpl(name.value(), container);
    }

    /**
     * Refers to a field set on GUI using its name.
     *
     * @param name the name of field set on GUI
     * @return a {@code FieldSet} instance representing field set element with given name on GUI
     * @see FieldSet
     */
    default FieldSet fieldSet(FieldSets name) {
        return new FieldSetWidgetImpl(name.value(), this);
    }

    /**
     * Refers to a breadcrumb item on GUI using its name.
     *
     * @param name the name of breadcrumb item on GUI
     * @return a {@code BreadcrumbItem} instance representing breadcrumb item element with given name on GUI
     * @see BreadcrumbItem
     */
    default BreadcrumbItem breadcrumbItem(BreadcrumbItems name) {
        return new BreadcrumbItemImpl(name.value(), this);
    }

    /**
     * Refers to a checkbox on GUI using its name and including ancestor element. Useful to specify searching
     * in case of many similar elements on GUI or to make calls in code clearer.
     *
     * @param name the name of checkbox on GUI
     * @param container including ancestor element, which contains this checkbox
     * @return a {@code Checkbox} instance representing checkbox element with given name on GUI
     * @see Checkbox
     */
    default Checkbox checkbox(Checkboxes name, Widget container) {
        return new CheckboxImpl(name.value(), container);
    }

    /**
     * Refers to a checkbox on GUI using its name.
     *
     * @param name the name of checkbox on GUI
     * @return a {@code Checkbox} instance representing checkbox element with given name on GUI
     * @see Checkbox
     */
    default Checkbox checkbox(Checkboxes name) {
        return new CheckboxImpl(name.value(), this);
    }

    /**
     * Refers to an icon on GUI using its name and including ancestor element. Useful to specify searching
     * in case of many similar elements on GUI or to make calls in code clearer.
     *
     * @param name the name of icon on GUI
     * @param container including ancestor element, which contains this icon
     * @return a {@code Icon} instance representing icon element with given name on GUI
     * @see Icon
     */
    default Icon icon(Icons name, Widget container) {
        return new IconImpl(name.value(), container);
    }

    /**
     * Refers to an icon on GUI using its name.
     *
     * @param name the name of icon on GUI
     * @return a {@code Icon} instance representing icon element with given name on GUI
     * @see Icon
     */
    default Icon icon(Icons name) {
        return new IconImpl(name.value(), this);
    }

    /**
     * Refers to a tab on GUI using its name and including ancestor element. Useful to specify searching
     * in case of many similar elements on GUI or to make calls in code clearer.
     *
     * @param name the name of tab on GUI
     * @param container including ancestor element, which contains this tab
     * @return a {@code Tab} instance representing tab element with given name on GUI
     * @see Tab
     */
    default Tab tab(Tabs name, Widget container) {
        return new TabWidgetImpl(name.value(), container);
    }

    /**
     * Refers to a tab on GUI using its name.
     *
     * @param name the name of tab on GUI
     * @return a {@code Tab} instance representing tab element with given name on GUI
     * @see Tab
     */
    default Tab tab(Tabs name) {
        return new TabWidgetImpl(name.value(), this);
    }
}
