package com.example.core.enumeration.element;

public enum Sortings {
    ASCENDING("ascending"),
    DESCENDING("descending");

    private final String value;

    Sortings(String value){
        this.value = value;
    }

    public String value(){
        return value;
    }
}
