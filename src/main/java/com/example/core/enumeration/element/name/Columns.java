package com.example.core.enumeration.element.name;

public enum Columns {

    COLUMN1("Column 1"),
    COLUMN2("Column 2"),
    COLUMN3("Column 3");

    private final String value;

    Columns(String value){
        this.value = value;
    }

    public String value(){
        return value;
    }
}
