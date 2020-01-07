package com.example.myproject.repository.specification;

import com.example.myproject.common.FilterComparisonOperator;
import com.example.myproject.common.FilterCriteria;
import com.example.myproject.common.FilterCriterion;
import com.example.myproject.common.FilterLogicOperator;
import com.example.myproject.repository.SubqueryGenerator;
import com.example.myproject.model.*;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpecificationBuilder<T> {

    private static Logger logger = LoggerFactory.getLogger(SpecificationBuilder.class);

    private SubqueryGenerator subqueryGenerator;

    private Map<String, Join> joinMap = new HashMap<>();

    public SpecificationBuilder() {
    }

    public SpecificationBuilder(SubqueryGenerator subqueryGenerator) {
        this.subqueryGenerator = subqueryGenerator;
    }

    public Specification<T> build(List<FilterCriteria> filterCriteriaList) {
        return (root, cq, cb) -> cb.and(
                filterCriteriaList.stream()
                        .map(filter-> this.buildPredicate(filter, root, cq, cb))
                        .toArray(Predicate[]::new)
        );
    }

    private Predicate buildPredicate(FilterCriteria filterCriteria, Root<T> root, CriteriaQuery cq, CriteriaBuilder cb) {
        Predicate predicate = null;
        for (FilterCriterion filter : filterCriteria.getCriteria()) {
            if (predicate == null) {
                predicate = buildPredicate(filterCriteria.getField(), filterCriteria.isJsonType(), filter, root, cq, cb);
                continue;
            }

            if (filterCriteria.getLogicOperator().equals(FilterLogicOperator.OR)) {
                predicate = cb.or(predicate, buildPredicate(filterCriteria.getField(), filterCriteria.isJsonType(), filter, root, cq, cb));
            }
            else {
                predicate = cb.and(predicate, buildPredicate(filterCriteria.getField(), filterCriteria.isJsonType(), filter, root, cq, cb));
            }
        }

        return predicate;
    }


    protected Predicate buildPredicate(String field, Boolean isJsonField, FilterCriterion filter, Root<T> root,  CriteriaQuery cq, CriteriaBuilder cb) {
        Predicate predicate = null;
        FilterComparisonOperator comparisonOperator = filter.getComparisonOperator();

        Expression expression = this.getExpression(field, isJsonField, root, cb);
        switch (comparisonOperator) {
            case IS_NOT_BLANK:
                predicate = cb.and(cb.isNotNull(expression), cb.notEqual(cb.trim(expression), ""));
                break;
            case LESS_THAN:
                switch (filter.getFilterValueType()) {
                    case DATE:
                        predicate = cb.lessThan(expression, (ZonedDateTime) filter.getValue());
                        break;
                    case NUMBER:
                        predicate = cb.lessThan(expression, (Long) filter.getValue());
                        break;
                    default:
                        predicate = cb.lessThan(expression, filter.getValue().toString());
                }

                break;
            case LESS_THAN_OR_EQUAL:
                switch (filter.getFilterValueType()) {
                    case DATE:
                        predicate = cb.lessThanOrEqualTo(expression, (ZonedDateTime) filter.getValue());
                        break;
                    case NUMBER:
                        predicate = cb.lessThanOrEqualTo(expression, (Long) filter.getValue());
                        break;
                    default:
                        predicate = cb.lessThanOrEqualTo(expression, filter.getValue().toString());
                }

                break;
            case GREATER_THAN:
                switch (filter.getFilterValueType()) {
                    case DATE:
                        predicate = cb.greaterThan(expression, (ZonedDateTime) filter.getValue());
                        break;
                    case NUMBER:
                        predicate = cb.greaterThan(expression, (Long) filter.getValue());
                        break;
                    default:
                        predicate = cb.greaterThan(expression, filter.getValue().toString());
                }

                break;
            case GREATER_THAN_OR_EQUAL:
                switch (filter.getFilterValueType()) {
                    case DATE:
                        predicate = cb.greaterThanOrEqualTo(expression, (ZonedDateTime) filter.getValue());
                        break;
                    case NUMBER:
                        predicate = cb.greaterThanOrEqualTo(expression, (Long) filter.getValue());
                        break;
                    default:
                        predicate = cb.greaterThanOrEqualTo(expression, filter.getValue().toString());
                }
                break;
            case STARTS_WITH:
                predicate = cb.like(expression, filter.getValue().toString() + "%");
                break;
            case ENDS_WITH:
                predicate = cb.like(expression, "%" + filter.getValue().toString());
                break;
            case CONTAINS:
                predicate = cb.like(expression, "%" + filter.getValue().toString() + "%");
                break;
            case NOT_CONTAINS:
                predicate = cb.notLike(expression, "%" + filter.getValue().toString() + "%");
                break;
            case BETWEEN:
                switch (filter.getFilterValueType()) {
                    case DATE:
                        predicate = cb.and(
                                cb.greaterThan(expression, (ZonedDateTime) filter.getValue()),
                                cb.lessThan(expression, (ZonedDateTime) filter.getValueTo())
                        );
                        break;
                    case NUMBER:
                        predicate = cb.and(
                                cb.greaterThan(expression, (Long) filter.getValue()),
                                cb.lessThan(expression, (Long) filter.getValueTo())
                        );
                        break;
                    default:
                        predicate = cb.and(
                                cb.greaterThan(expression, filter.getValue().toString()),
                                cb.lessThan(expression, filter.getValueTo().toString())
                        );
                }
                break;
            case IN:
                predicate = expression.in(filter.getValue());
                break;
            case NOT_EQUAL:
                if (field.contains(".")) {
                    Subquery<?> subquery = cq.subquery(root.getJavaType());
                    Root<?> subRoot = subquery.from(root.getJavaType());

                    String tableName = field.split("\\.")[0];
                    String fieldName = field.split("\\.")[1];
                    Expression<?> subExpression = subquery.select(subRoot.get("id"))
                            .where(cb.equal(subRoot.join(tableName).get(fieldName), filter.getValue().toString()));
                    predicate = cb.not(root.get("id").in(subExpression));
                }
                else {
                    predicate = cb.notEqual(expression, filter.getValue());
                }
                break;
            case NOT_IN:
                if (field.contains(".")) {
                    Subquery<?> subquery = cq.subquery(root.getJavaType());
                    Root<?> subRoot = subquery.from(root.getJavaType());

                    String tableName = field.split("\\.")[0];
                    String fieldName = field.split("\\.")[1];
                    Expression<?> subExpression = subquery.select(subRoot.get("id"))
                            .where(cb.equal(subRoot.join(tableName).get(fieldName), filter.getValue().toString()));
                    predicate = cb.not(root.get("id").in(subExpression));
                }
                else {
                    predicate = cb.not(expression.in(filter.getValue()));
                }
                break;
            default:
                predicate = cb.equal(expression, filter.getValue());
        }
        return predicate;
    }

    private Expression getExpression(String field, Boolean isJsonField, Root<T> root, CriteriaBuilder cb) {
        return getExpression(field, isJsonField, root, cb, "");
    }

    private Expression getExpression(String field, Boolean isJsonField, From from, CriteriaBuilder cb, String joinedTables) {
        if (!field.contains(".")) {
            return from.get(field);
        }


//        if (field.split("\\.").length==2 && isJsonField) {
//            Path<T> path = from.get(field.split("\\.")[0]);
//            return cb.function("JSON_EXTRACT", String.class, path.as(JsonNode.class), cb.literal("$.\"" + field.split("\\.")[1] + "\""));
//        }
        if (field.split("\\.").length==2) {
            Path<T> path = from.get(field.split("\\.")[0]);
             if (JsonNode.class.equals(path.getJavaType()))
                return cb.function("JSON_EXTRACT", String.class, path.as(JsonNode.class), cb.literal("$.\"" + field.split("\\.")[1] + "\""));
        }

        String joinTable = field.substring(0, field.indexOf("."));
        field = field.substring(field.indexOf(".") + 1);
        joinedTables = joinedTables.isEmpty()? joinTable: joinedTables + "." + joinTable;
        if (!joinMap.containsKey(joinedTables)) {
            joinMap.put(joinedTables, from.join(joinTable));
        }

        return getExpression(field, isJsonField, joinMap.get(joinedTables), cb, joinedTables);
    }
}
