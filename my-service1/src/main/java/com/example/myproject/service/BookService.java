package com.example.myproject.service;

import org.springframework.stereotype.Service;
import com.example.myproject.model.Book;

@Service
public class BookService extends BaseService<Book>{
	
	public Book save(Book entity) {
        return repository.save(entity);
    }
	
	public Book updateAuhorList(Book entity) {
		return repository.save(entity);
	}
	
}
