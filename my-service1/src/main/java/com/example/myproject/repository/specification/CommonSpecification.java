package com.example.myproject.repository.specification;

import com.example.myproject.model.BaseEntity;
import com.example.myproject.model.search.Filter;
import com.example.myproject.model.search.Query;
import com.example.myproject.model.search.SearchFilter;
import com.example.myproject.model.search.SubFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Arrays;
import java.util.List;


public class CommonSpecification <T extends BaseEntity> implements Specification<T> {

    private Query query;

    public CommonSpecification(Query query) {
        this.query = query;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

        Predicate rootPredicate = null;
        for (SearchFilter filter : query.getFilters()) {
            Predicate predicate = null;
            String defaultField = filter.getField();
            for (SubFilter subFilter : filter.getSubFilters()) {

                if (subFilter.getField().isEmpty()) {
                    subFilter.setField(defaultField);
                }

                Predicate subPredicate = getPredicateBySubfilter(subFilter, root, cb);
                predicate = connectPredicate(subFilter, subPredicate, predicate, cb);
            }

            rootPredicate = connectPredicate(filter, predicate, rootPredicate, cb);
        }

        if (query.isDistinct()) {
            cq.distinct(true);
        }

//        String orderBy = query.getOrderBy();
//        if(!orderBy.isEmpty()) {
//            Arrays.asList(orderBy.split(",")).forEach(orderby -> {
//                String[] orderbys = orderby.split("\\|");
//                if (orderbys.length>1&& orderbys[1].equals("desc")) {
//                    cq.orderBy(cb.desc(root.get(orderbys[0])));
//                }
//                else {
//                    cq.orderBy(cb.asc(root.get(orderbys[0])));
//                }
//            });
//        }

        return rootPredicate;
    }

    private Predicate getPredicateBySubfilter(SubFilter filter, From from, CriteriaBuilder cb) {
        String field = filter.getField();
        if (field.contains(".")) {
            String[] fieldArray = field.split("\\.");
            Join join = from.join(fieldArray[0]);
            return getPredicateByComparisonOperator(filter, join.get(fieldArray[1]), cb);
        }

        return getPredicateByComparisonOperator(filter, from.get(field), cb);
    }

    private Predicate getPredicateByComparisonOperator(SubFilter filter, Path path, CriteriaBuilder cb) {

        Predicate predicate = null;

        switch (filter.getComparisonOperator().toUpperCase()) {
            case "EQUALS":
                predicate = cb.equal(path, filter.getValue());
                break;
            case "NOT_EQUALS":
                predicate = cb.not(cb.equal(path, filter.getValue()));
                break;
            case "CONTAINS":
                predicate = cb.like(path, "%" + filter.getValue().toString() + "%");
                break;
            case "NOT_IN":
                List<String> values = Arrays.asList(filter.getValue().toString().split("\\|"));
                predicate = cb.not(cb.in(path).value(values));
                break;

            default:
                predicate = cb.equal(path, filter.getValue());
        }

        return predicate;
    }

    private Predicate connectPredicate(Filter filter, Predicate subPredicate, Predicate parentPredicate, CriteriaBuilder cb) {
        Predicate predicate = null;

        if (filter.getFilterConnector().equals("AND")) {
            predicate = parentPredicate==null? subPredicate: cb.and(parentPredicate, subPredicate);
        }
        else if (filter.getFilterConnector().equals("OR")) {
            predicate = parentPredicate==null? subPredicate: cb.or(parentPredicate, subPredicate);
        }
        else {
            predicate = subPredicate;
        }

        return predicate;
    }
}
