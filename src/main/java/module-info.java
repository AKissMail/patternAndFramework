module de.gruppeo.wise2122_paf_gruppeo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens de.gruppeo.wise2122_java_client to javafx.fxml;
    exports de.gruppeo.wise2122_java_client;
    opens de.gruppeo.wise2122_java_client.controller to javafx.fxml;
    exports de.gruppeo.wise2122_java_client.controller;
}