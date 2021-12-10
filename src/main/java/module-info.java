module de.gruppeo.mibquizzz {
    requires java.prefs;
    requires java.persistence;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.data.jpa;
    requires spring.jdbc;
    requires spring.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires org.hibernate.orm.core;

    opens de.gruppeo.wise2122_java_client to javafx.fxml;
    exports de.gruppeo.wise2122_java_client;
    opens de.gruppeo.wise2122_java_client.controllers to javafx.fxml;
    exports de.gruppeo.wise2122_java_client.controllers;

    opens de.gruppeo.wise2122_java_server to spring.web,spring.boot,spring.boot.autoconfigure,spring.jdbc,spring.context;
    exports de.gruppeo.wise2122_java_server;
    opens de.gruppeo.wise2122_java_server.controller to spring.web,spring.boot,spring.boot.autoconfigure,spring.jdbc,spring.context;
    exports de.gruppeo.wise2122_java_server.controller;

}