module taf.core {
    requires com.codeborne.selenide;
    requires freemarker;
    requires org.slf4j;
    requires org.apache.logging.log4j;
    requires java.xml;
    requires java.xml.soap;
    requires io.qameta.allure.commons;
    requires selenium.api;
    requires selenium.support;
    requires poi;
    requires poi.ooxml;
    requires selenium.chrome.driver;
    requires selenium.firefox.driver;
    requires org.junit.jupiter.api;
    requires hamcrest.core;

    exports com.example.core.enumeration;
    exports com.example.core.enumeration.element;
    exports com.example.core.enumeration.element.name;
    exports com.example.core.enumeration.system;

    exports com.example.core.exception.export;

    exports com.example.core.gui.iface.export;
    exports com.example.core.gui.iface.export.base;
    exports com.example.core.gui.iface.export.complex.frame;
    exports com.example.core.gui.iface.export.complex.widget;
    exports com.example.core.gui.iface.export.simple;

    exports com.example.core.soap;
    exports com.example.core.util;
    exports com.example.core.gui.element.complex.frame;
    exports com.example.core.exception;
}