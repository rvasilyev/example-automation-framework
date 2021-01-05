package com.example.core.enumeration.element.name;

public enum Tabs {

    TAB1("Tab 1"),
    TAB2("Tab 2"),
    TAB3("Tab 3");

    private final String value;

    Tabs(String value){
        this.value = value;
    }

    public String value(){
        return value;
    }
}
