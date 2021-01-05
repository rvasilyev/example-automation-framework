package com.example.core.enumeration.element.name;

public enum Icons {

    ICON1("Icon 1"),
    ICON2("Icon 2"),
    ICON3("Icon 3");

    private final String value;

    Icons(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
