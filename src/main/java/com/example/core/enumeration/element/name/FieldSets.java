package com.example.core.enumeration.element.name;

public enum FieldSets {

    FIELD_SET1("Field set 1"),
    FIELD_SET2("Field set 2"),
    FIELD_SET3("Field set 3");

    private final String value;

    FieldSets(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
