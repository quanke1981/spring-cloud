package com.example.myproject.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.example.myproject.model.Author;
import com.example.myproject.model.Book;
import com.example.myproject.model.User;
import com.example.myproject.repository.BookRepository;

@Service
public class BookService extends BaseService<Book> {
	
	@Autowired
    protected BookRepository repository;
	
	public Book updateAuhorList(int id, Set<Author> authors) {
		Optional<Book> opBook = repository.findById(id);
		
		if(opBook.isPresent()) {
			Book book = opBook.get();
			Set<Author> extAuhtors = book.getAuthors();
			extAuhtors.clear();
			extAuhtors.addAll(authors);
			return repository.save(book);
		}
		
		return null;
	}
}
