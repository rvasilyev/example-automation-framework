package com.example.core.enumeration.element.name;

public enum Dropboxes {

    DROPBOX1("Dropbox 1"),
    DROPBOX2("Dropbox 2"),
    DROPBOX3("Dropbox 3");

    private final String value;

    Dropboxes(String value){
        this.value = value;
    }

    public String value(){
        return value;
    }
}
