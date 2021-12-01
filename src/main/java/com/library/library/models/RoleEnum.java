package com.library.library.models;

public enum RoleEnum {
    ROLE_BLOCKED("ROLE_BLOCKED"),
    ROLE_READER("ROLE_READER"),
    ROLE_LIBRARIAN("ROLE_LIBRARIAN"),
    ROLE_ADMIN("ROLE_ADMIN");

    String value;

    RoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
