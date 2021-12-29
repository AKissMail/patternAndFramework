package de.gruppeo.wise2122_java_client.models;

public class MCategory {

    private int quizcategoryid;
    private String categoryname;

    public MCategory() {}

    public MCategory(int quizcategoryid, String categoryname) {
        this.quizcategoryid = quizcategoryid;
        this.categoryname = categoryname;
    }

    public String getCategoryname() {
        return categoryname;
    }
}