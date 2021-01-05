package com.example.core.enumeration.element.name;

public enum MenuItems {

    MENU_ITEM1("Menu item 1"),
    MENU_ITEM2("Menu item 2"),
    MENU_ITEM3("Menu item 3");

    private final String value;

    MenuItems(String value){
        this.value = value;
    }

    public String value(){
        return value;
    }
}
