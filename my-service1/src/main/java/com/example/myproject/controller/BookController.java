package com.example.myproject.controller;

import java.util.List;
import java.util.Set;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myproject.model.Book;
import com.example.myproject.model.Author;
import com.example.myproject.service.BookService;

@RestController
@RequestMapping("api/books")
public class BookController extends BaseController<Book, BookService> {
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/{id}", method=RequestMethod.POST)
    @ResponseBody
    public Book post(@PathVariable int id, @RequestBody Set<Author> authors) {
		Book book = service.getOne(id);
		book.setAuthors(authors);
        return service.save(book);
    }
	
    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public Book post(@RequestBody Book entity) {
		entity.setAuthors(null);
        return service.save(entity);
    }
	
}
