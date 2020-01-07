package com.example.myproject.model.search;

import java.util.List;

public class Query {

    private boolean distinct;

    private String orderBy;

    private List<SearchFilter> filters;

    public boolean isDistinct() {
        return distinct;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public List<SearchFilter> getFilters() {
        return filters;
    }

    public void setSearchFilterList(List<SearchFilter> filters) {
        this.filters = filters;
    }
}
