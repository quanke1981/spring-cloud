package com.example.myproject.common;

public enum FilterComparisonOperator {

    IS_NOT_BLANK("isNotBlank"),
    EQUALS("equals"),
    NOT_EQUAL("notEqual"),
    LESS_THAN("lessThan"),
    LESS_THAN_OR_EQUAL("lessThanOrEqual"),
    GREATER_THAN("greaterThan"),
    GREATER_THAN_OR_EQUAL("greaterThanOrEqual"),
    CONTAINS("contains"),
    NOT_CONTAINS("notContains"),
    STARTS_WITH("startsWith"),
    ENDS_WITH("endsWith"),
    BETWEEN("between"),
    IN("in"),
    NOT_IN("notIn");

    private String name;

    FilterComparisonOperator(String name) { this.name = name; }

    public String getName() { return name; }

}