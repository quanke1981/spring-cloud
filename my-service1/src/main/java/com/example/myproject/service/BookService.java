package com.example.myproject.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.myproject.common.FilterCriteria;
import com.example.myproject.model.search.Query;
import com.example.myproject.model.search.SearchFilter;
import com.example.myproject.repository.SubqueryGenerator;
import com.example.myproject.repository.specification.CommonSpecification;
import com.example.myproject.repository.specification.SpecificationBuilder;
import net.bytebuddy.description.type.TypeDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.myproject.model.*;
import com.example.myproject.repository.BookRepository;

import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

@Transactional
@Service
public class BookService extends BaseService<Book> {

	@Autowired
	protected BookRepository repository;

	protected JpaRepository<Book, Integer> getRepository() {
		return this.repository;
	}

	public Book updateAuhorList(int id, Set<Author> authors) {
		Optional<Book> opBook = repository.findById(id);

		if (opBook.isPresent()) {
			Book book = opBook.get();
			Set<Author> extAuhtors = book.getAuthors();
			extAuhtors.clear();
			extAuhtors.addAll(authors);
			return repository.save(book);
		}

		return null;
	}



//	public List<Book> getBookByQuery(Query query) {
//		Specification<Book> spec = new CommonSpecification<Book>(query);
//		List<Book> books = repository.findAll(spec);
//		books.forEach(book->book.getAuthors().forEach(Author::getFirstName));
//		return books;
//	}

	public List<Book> getBookByQuery(List<FilterCriteria> criteria) {
		Specification<Book> spec = new SpecificationBuilder<Book>().build(criteria);
		return repository.findAll(spec);
	}

	@Override
	public List<Book> getAll() {
		List<Book> books = getRepository().findAll();
		books.forEach(book -> {
			book.getAuthors().size();
		});
		return books;
	}
	
}