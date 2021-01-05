package com.example.core.enumeration.element.name;

public enum TextFields {

    TEXT_FIELD1("Text field 1"),
    TEXT_FIELD2("Text field 2"),
    TEXT_FIELD3("Text field 3");

    private final String value;

    TextFields(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
