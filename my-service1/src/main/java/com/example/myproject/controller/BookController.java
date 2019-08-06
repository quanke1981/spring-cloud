package com.example.myproject.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	
	@RequestMapping(method=RequestMethod.GET)
    @ResponseBody
    public List<Book> get() {
    	List<Book> results = service.getAll();
        return results;
    }
	
    @RequestMapping(value="/{id}", method=RequestMethod.POST)
    @ResponseBody
    public Book updateBookName(@PathVariable int id, @RequestBody Book entity) {
		
    	Optional<Book> book = service.findOne(id);
    	if(book.isPresent()) {
    		book.get().setName(entity.getName());
    		return service.save(book.get());
    	}
    	
    	return null;
    }
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/{id}/updateAuthorList", method=RequestMethod.POST)
    @ResponseBody
    public Book updateAuthorList(@PathVariable int id, @RequestBody Set<Author> authors) {
        return service.updateAuhorList(id, authors);
    }
	
}
