package com.example.ekanban;

/**
 * Created by mdzahidraza on 03/06/17.
 */
public enum Action {
    CLICK("CLICK"),
    SUBMIT("SUBMIT"),
    SENDKEY("SENDKEY"),
    SELECT("SELECT"),
    WAIT("WAIT"),
    SEARCH("SEARCH"),
    ASSERT("ASSERT");

    private final String value;

    Action(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Action parse(String value) {
        Action action = null;
        for (Action item : Action.values()) {
            if (item.getValue().equals(value)) {
                action = item;
                break;
            }
        }
        return action;
    }
}
