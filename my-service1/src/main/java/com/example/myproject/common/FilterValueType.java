package com.example.myproject.common;

import java.time.ZonedDateTime;

public enum FilterValueType {

    STRING("string"),
    NUMBER("number"),
    BOOLEAN("boolean"),
    LIST("list"),

    DATE("date");

    private String name;

    FilterValueType(String name) { this.name = name; }

    // For json column
    public static Class getClass(FilterValueType filterValueType) {
        switch (filterValueType) {
            case NUMBER:
                return Long.class;
            case STRING:
                return String.class;
            case BOOLEAN:
                return Boolean.class;
            case DATE:
                return ZonedDateTime.class;
            default:
                return null;
        }
    }

    public String getName() {
        return name;
    }
}
