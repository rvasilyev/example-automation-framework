package com.example.core.gui.iface.export;

import com.example.core.enumeration.element.name.*;
import com.example.core.gui.element.complex.widget.KKFieldSetWidget;
import com.example.core.gui.element.complex.widget.KKMessageWidget;
import com.example.core.gui.element.complex.widget.KKTabWidget;
import com.example.core.gui.element.simple.KKBreadcrumbItem;
import com.example.core.gui.element.simple.KKCheckbox;
import com.example.core.gui.element.simple.KKIcon;
import com.example.core.gui.iface.export.base.Widget;
import com.example.core.gui.iface.export.complex.widget.FieldSet;
import com.example.core.gui.iface.export.complex.widget.Message;
import com.example.core.gui.iface.export.complex.widget.Tab;
import com.example.core.gui.iface.export.simple.BreadcrumbItem;
import com.example.core.gui.iface.export.simple.Checkbox;
import com.example.core.gui.iface.export.simple.Icon;

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
        return new KKMessageWidget(this);
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
        return new KKFieldSetWidget(name.value(), container);
    }

    /**
     * Refers to a field set on GUI using its name.
     *
     * @param name the name of field set on GUI
     * @return a {@code FieldSet} instance representing field set element with given name on GUI
     * @see FieldSet
     */
    default FieldSet fieldSet(FieldSets name) {
        return new KKFieldSetWidget(name.value(), this);
    }

    /**
     * Refers to a breadcrumb item on GUI using its name.
     *
     * @param name the name of breadcrumb item on GUI
     * @return a {@code BreadcrumbItem} instance representing breadcrumb item element with given name on GUI
     * @see BreadcrumbItem
     */
    default BreadcrumbItem breadcrumbItem(BreadcrumbItems name) {
        return new KKBreadcrumbItem(name.value(), this);
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
        return new KKCheckbox(name.value(), container);
    }

    /**
     * Refers to a checkbox on GUI using its name.
     *
     * @param name the name of checkbox on GUI
     * @return a {@code Checkbox} instance representing checkbox element with given name on GUI
     * @see Checkbox
     */
    default Checkbox checkbox(Checkboxes name) {
        return new KKCheckbox(name.value(), this);
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
        return new KKIcon(name.value(), container);
    }

    /**
     * Refers to an icon on GUI using its name.
     *
     * @param name the name of icon on GUI
     * @return a {@code Icon} instance representing icon element with given name on GUI
     * @see Icon
     */
    default Icon icon(Icons name) {
        return new KKIcon(name.value(), this);
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
        return new KKTabWidget(name.value(), container);
    }

    /**
     * Refers to a tab on GUI using its name.
     *
     * @param name the name of tab on GUI
     * @return a {@code Tab} instance representing tab element with given name on GUI
     * @see Tab
     */
    default Tab tab(Tabs name) {
        return new KKTabWidget(name.value(), this);
    }
}
