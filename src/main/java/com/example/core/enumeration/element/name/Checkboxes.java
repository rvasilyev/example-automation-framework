package com.example.core.enumeration.element.name;

public enum Checkboxes {

    CHECKBOX1("Checkbox 1"),
    CHECKBOX2("Checkbox 2"),
    CHECKBOX3("Checkbox 3");

    private final String value;

    Checkboxes(String value){
        this.value = value;
    }

    public String value(){
        return value;
    }
}
