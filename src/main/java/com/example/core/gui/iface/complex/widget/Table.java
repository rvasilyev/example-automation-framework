package com.example.core.gui.iface.complex.widget;

import com.example.core.enumeration.element.name.Columns;
import com.example.core.enumeration.element.Sortings;
import com.example.core.enumeration.element.TableRowCount;
import com.example.core.gui.iface.base.Widget;

/**
 * Represents a table on GUI. Table is accessed using its order number on page from top to bottom starting from 1.
 */
public interface Table extends Widget {

    /**
     * Gets the order number of this table on page from top to bottom starting from 1.
     *
     * @return the order number of this table on page from top to bottom starting from 1
     */
    int getTableNumber();

    /**
     * Gets the specified row using its order number within this table from top to bottom starting from 1.
     *
     * @param rowNumber row order number within this table from top to bottom starting from 1
     * @return a {@code Row} instance representing row with given order number
     */
    Row row(int rowNumber);

    /**
     * Gets the specified row within this table containing given value on intersection with given column.
     *
     * @param columnName name of the column of this table, whose cells will be scanned
     * @param value the value to be found in the cell on intersection of given column and searching row
     * @return a {@code Row} instance representing row containing given value on intersection with given column
     * @see Columns
     */
    Row rowWithColumnValue(Columns columnName, String value);

    /**
     * Sorts given column of this table in given order.
     *
     * @param columnName name of the column to be sorted
     * @param sorting the type of sorting
     * @see Sortings
     */
    void sortColumn(Columns columnName, Sortings sorting);

    /**
     * Gets amount of rows in this table.
     *
     * @return amount of rows in this table
     */
    int getRowAmount();

    /**
     * Selects how many rows will be shown in this table on GUI in appropriate field.
     *
     * @param tableRowAmount amount of rows to be shown in this table on GUI
     */
    void selectRowAmount(TableRowCount tableRowAmount);

    /**
     * Checks if this table contains filled rows or not. Headers are excluded.
     *
     * @return {@code true} if this table has no rows or they are blank, otherwise {@code false}
     */
    boolean isEmpty();
}
