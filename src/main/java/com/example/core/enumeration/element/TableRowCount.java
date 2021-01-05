package com.example.core.enumeration.element;

import com.example.core.enumeration.DropboxOptionsEnum;

public enum TableRowCount implements DropboxOptionsEnum {

    ROW_COUNT_10(10),
    ROW_COUNT_20(20),
    ROW_COUNT_50(50);

    private final int value;

    TableRowCount(int value){
        this.value = value;
    }

    public int intValue() {
        return value;
    }

    @Override
    public String value(){
        return String.valueOf(value);
    }
}
