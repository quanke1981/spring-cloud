package com.example.myproject.model.search;

public abstract class Filter {

    private String field;

    private String filterConnector;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getFilterConnector() {
        return filterConnector;
    }

    public void setFilterConnector(String filterConnector) {
        this.filterConnector = filterConnector;
    }
}
