package com.example.core.enumeration.element.name;

public enum Buttons {

    BUTTON1("Button 1"),
    BUTTON2("Button 2"),
    BUTTON3("Button 3");

    private final String value;

    Buttons(String value){
        this.value = value;
    }

    public String value(){
        return value;
    }
}
