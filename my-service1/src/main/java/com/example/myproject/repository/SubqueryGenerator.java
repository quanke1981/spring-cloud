package com.example.myproject.repository;

import com.example.myproject.common.FilterCriterion;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Subquery;

public interface SubqueryGenerator {
    Subquery generate(String Field, FilterCriterion filter, From root, CriteriaQuery cq, CriteriaBuilder cb);
}
