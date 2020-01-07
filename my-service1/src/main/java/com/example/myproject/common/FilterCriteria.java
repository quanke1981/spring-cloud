package com.example.myproject.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FilterCriteria {

    private String field;

    public boolean isJsonType() {
        return jsonType;
    }

    public void setJsonType(boolean jsonType) {
        this.jsonType = jsonType;
    }

    private boolean jsonType;

    private List<FilterCriterion> criteria = new ArrayList<>();

    private FilterLogicOperator logicOperator;

    public FilterCriteria() {}

    public FilterCriteria(String field, List<FilterCriterion> criteria, FilterLogicOperator logicOperator) {
        this.field = field;
        this.criteria = criteria;
        this.logicOperator = logicOperator;
    }

    public void addCriteria(FilterCriterion filterCriterion) {
        this.criteria.add(filterCriterion);
    }

    public List<FilterCriterion> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<FilterCriterion> criteria) {
        this.criteria = criteria;
    }

    public FilterLogicOperator getLogicOperator() {
        return logicOperator;
    }

    public void setLogicOperator(FilterLogicOperator logicOperator) {
        this.logicOperator = logicOperator;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @JsonIgnore
    public boolean isValid() {

        if (criteria == null || criteria.isEmpty() || logicOperator == null || StringUtils.isEmpty(field)) {
            return false;
        }

        if (logicOperator.equals(FilterLogicOperator.NULL) && criteria.size() > 1) {
            return false;
        }

        if (!logicOperator.equals(FilterLogicOperator.NULL) && criteria.size() == 1) {
            return false;
        }

        return !criteria.stream().anyMatch(filterCriterion -> !filterCriterion.isValid());
    }

}
