package com.example.core.gui.iface.export.complex.widget;

import com.example.core.enumeration.element.name.Columns;
import com.example.core.enumeration.element.Sortings;
import com.example.core.enumeration.element.TableRowCount;
import com.example.core.gui.iface.export.base.Widget;

public interface Table extends Widget {
    int getTableNumber();

    Row row(int rowNumber);

    Row rowWithColumnValue(Columns columnName, String value);

    void sortColumn(Columns columnName, Sortings sortingArt);

    int getRowCount();

    void selectRowCount(TableRowCount tableRowCount);

    boolean isEmpty();
}
