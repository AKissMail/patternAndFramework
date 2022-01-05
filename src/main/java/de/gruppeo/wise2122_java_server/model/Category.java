package de.gruppeo.wise2122_java_server.model;

public enum Category {
    Medieninformatik("MIB"), Entwicklung("Prog"), Mediendesign("MD"), ITSicherheit("ITSEC"), Erdkunde("Geo");

    private final String code;

    Category(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
