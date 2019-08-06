package com.example.myproject.controller;

import java.util.Iterator;
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
	
	
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    @ResponseBody
    public Book updateBookName(@PathVariable int id, @RequestBody Set<Author> authors) {
		Book book = service.getOne(id);
//		book.setAuthors(null);
        return service.save(book);
    }
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/{id}/updateAuthorList", method=RequestMethod.POST)
    @ResponseBody
    public Book updateAuthorList(@PathVariable int id, @RequestBody Set<Author> authors) {
        return service.updateAuhorList(id, authors);
    }
	
	//add book
    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public Book post(@RequestBody Book entity) {
//    	entity.setId(0);
        return service.save(entity);
    }
	
}
