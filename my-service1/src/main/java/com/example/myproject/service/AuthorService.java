package com.example.myproject.service;

import com.example.myproject.common.FilterCriteria;
import com.example.myproject.model.Author;
import com.example.myproject.model.Author_;
import com.example.myproject.model.Book;
import com.example.myproject.model.Book_;
import com.example.myproject.repository.AuthorRepository;
import com.example.myproject.repository.SubqueryGenerator;
import com.example.myproject.repository.specification.SpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.util.List;

@Service
public class AuthorService extends BaseService<Author> {

    @Autowired
    protected AuthorRepository repository;

    protected JpaRepository<Author, Integer> getRepository() {
        return this.repository;
    }

    public List<Author> getAuthorsByQuery (List<FilterCriteria> criteria) {
        SubqueryGenerator subqueryGenerator = createSubqueryGenerator();

        Specification<Author> spec = new SpecificationBuilder<Author>(subqueryGenerator).build(criteria);
        return repository.findAll(spec);
    }

    private SubqueryGenerator createSubqueryGenerator () {
        return (Field, filter, root, cq, cb) -> {
            Subquery<Integer> subquery = cq.subquery(Integer.class);
            Root<Book> subRoot = subquery.from(Book.class);
            return subquery.select(cb.literal(1))
                    .where(cb.and(
                            cb.equal(subRoot.join(Book_.authors).get(Author_.ID), root.get(Author_.ID)),
                            cb.equal(subRoot.get(Book_.name), filter.getValue().toString()))
                    );
        };
    }
}
