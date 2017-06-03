package com.example.ekanban;

/**
 * Created by mdzahidraza on 03/06/17.
 */
public enum Condition {

    PRESENCE("PRESENCE"),
    ABSENCE("ABSENCE"),
    MATCH_URL("MATCH_URL");

    private final String value;

    Condition(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Condition parse(String value) {
        Condition condition = null;
        for (Condition item : Condition.values()) {
            if (item.getValue().equals(value)) {
                condition = item;
                break;
            }
        }
        return condition;
    }
}
