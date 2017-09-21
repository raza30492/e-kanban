package com.example.ekanban;

/**
 * Created by mdzahidraza on 04/06/17.
 */
public enum Occurence {

    SINGLE("SINGLE"),
    MULTIPLE("MULTIPLE");

    private final String value;

    Occurence(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Occurence parse(String value) {
        Occurence occurence = null;
        for (Occurence item : Occurence.values()) {
            if (item.getValue().equals(value)) {
                occurence = item;
                break;
            }
        }
        return occurence;
    }
}
