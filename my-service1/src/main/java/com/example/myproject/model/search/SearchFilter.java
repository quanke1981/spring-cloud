package com.example.myproject.model.search;

import java.util.List;

public class SearchFilter extends Filter {

    private List<SubFilter> subFilters;

    public List<SubFilter> getSubFilters() {
        return subFilters;
    }

    public void setSubFilterList(List<SubFilter> subFilters) {
        this.subFilters = subFilters;
    }
}
