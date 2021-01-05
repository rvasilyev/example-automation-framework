package com.example.core.enumeration.element.name;

public enum Radiobuttons {

    RADIOBUTTON1("Radiobutton 1"),
    RADIOBUTTON2("Radiobutton 1"),
    RADIOBUTTON3("Radiobutton 1");

    private final String value;

    Radiobuttons(String value){
        this.value = value;
    }

    public String value(){
        return value;
    }
}
