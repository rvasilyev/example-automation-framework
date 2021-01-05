package com.example.core.enumeration.element.name;

public enum BreadcrumbItems {

    BREADCRUMB_ITEM1("Breadcrmb item 1"),
    BREADCRUMB_ITEM2("Breadcrmb item 2"),
    BREADCRUMB_ITEM3("Breadcrmb item 3");

    private final String value;

    BreadcrumbItems(String value){
        this.value = value;
    }

    public String value(){
        return value;
    }
}
