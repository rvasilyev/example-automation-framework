package com.example.core.enumeration.element.name;

import com.example.core.enumeration.StorageEnum;

public enum Links implements StorageEnum {

    LINK1("Link 1"),
    LINK2("Link 2"),
    LINK3("Link 3");

    private final String value;

    Links(String value){
        this.value = value;
    }

    @Override
    public String value(){
        return value;
    }
}
