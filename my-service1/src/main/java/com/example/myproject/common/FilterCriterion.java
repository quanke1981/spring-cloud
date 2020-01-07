package com.example.myproject.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

public class FilterCriterion {

    private FilterComparisonOperator comparisonOperator;

    private Object value;

    private Object valueTo;

    private FilterValueType filterValueType;

    public FilterCriterion() {}

    public FilterCriterion(Object value, Object valueTo, FilterValueType filterValueType,
                           FilterComparisonOperator filterComparisonOperator) {
        this.value = value;
        this.valueTo = valueTo;
        this.filterValueType = filterValueType;
        this.comparisonOperator = filterComparisonOperator;
    }

    public FilterComparisonOperator getComparisonOperator() {
        return comparisonOperator;
    }

    public void setComparisonOperator(FilterComparisonOperator comparisonOperator) {
        this.comparisonOperator = comparisonOperator;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValueTo() {
        return valueTo;
    }

    public void setValueTo(Object valueTo) {
        this.valueTo = valueTo;
    }

    public FilterValueType getFilterValueType() {
        return filterValueType;
    }

    public void setFilterValueType(FilterValueType filterValueType) {
        this.filterValueType = filterValueType;
    }

    @JsonIgnore
    public boolean isValid() {

        if (null == value && !comparisonOperator.equals(FilterComparisonOperator.IS_NOT_BLANK)) {
            return false;
        }

        if (comparisonOperator.equals(FilterComparisonOperator.BETWEEN) && null == valueTo) {
            return false;
        }

        switch (filterValueType) {
            case NUMBER:
            case DATE:
                if (comparisonOperator.equals(FilterComparisonOperator.STARTS_WITH) ||
                        comparisonOperator.equals(FilterComparisonOperator.ENDS_WITH) ||
                        comparisonOperator.equals(FilterComparisonOperator.CONTAINS) ||
                        comparisonOperator.equals(FilterComparisonOperator.NOT_CONTAINS)) {
                    return false;
                }
                break;
            case STRING:
                if (comparisonOperator.equals(FilterComparisonOperator.BETWEEN) ||
                        comparisonOperator.equals(FilterComparisonOperator.LESS_THAN) ||
                        comparisonOperator.equals(FilterComparisonOperator.GREATER_THAN)) {
                    return false;
                }
                break;
            case BOOLEAN:
                if (!comparisonOperator.equals(FilterComparisonOperator.EQUALS) &&
                        !comparisonOperator.equals(FilterComparisonOperator.NOT_EQUAL)) {
                    return false;
                }
                break;
            case LIST:
                // if comparison operator is "IN" or "NOT IN", the provided value should be a list.
                if (!comparisonOperator.equals(FilterComparisonOperator.IN) && !comparisonOperator.equals(FilterComparisonOperator.NOT_IN)
                        || CollectionUtils.isEmpty((Collection) value)) {
                    return false;
                }
                break;
        }
        return true;
    }
}
