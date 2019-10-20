module FESta.Java {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.base;
    requires java.logging;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires com.jfoenix;
    requires java.sql;
    requires java.naming;
    requires org.kordamp.iconli.core;
    requires org.kordamp.ikonli.javafx;
    // add icon pack modules
    requires org.kordamp.ikonli.fontawesome;

    opens view;
    opens controller;
    opens model;
}