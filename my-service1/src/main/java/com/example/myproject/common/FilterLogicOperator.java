package com.example.myproject.common;

public enum FilterLogicOperator {

    NULL(""),
    OR("OR"),
    AND("AND");

    private String name;

    FilterLogicOperator(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
