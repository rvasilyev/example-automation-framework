package com.example.core.enumeration.element;

public enum TypeNames {

    BREADCRUMB_ITEM("Breadcrumb item"),
    BUTTON("Button"),
    CHECKBOX("Checkbox"),
    DROPBOX("Dropbox"),
    ICON("Icon"),
    MENU_ITEM("Menu item"),
    RADIONUTTON("Radiobutton"),
    TAB("Tab"),
    TEXT_FIELD("Text field"),
    FIELD_SET("Field set"),
    TABLE("Table"),
    ROW("Row"),
    CONTENT_FRAME("Content frame"),
    MENU_FRAME("Main menu"),
    MESSAGE("Message"),
    ALERT("Modal window"),
    LINK("Link");

    private final String value;

    TypeNames(String value){
        this.value = value;
    }

    public String value(){
        return value;
    }
}
