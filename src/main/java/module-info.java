module de.gruppeo.mibquizzz {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.prefs;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.jdbc;
    requires spring.context;
    requires spring.web;

    opens de.gruppeo.wise2122_java_client to javafx.fxml;
    exports de.gruppeo.wise2122_java_client;
    opens de.gruppeo.wise2122_java_client.controllers to javafx.fxml;
    exports de.gruppeo.wise2122_java_client.controllers;
}