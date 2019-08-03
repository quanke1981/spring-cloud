package com.example.myproject.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myproject.model.Book;
import com.example.myproject.service.BookService;

@RestController
@RequestMapping("api/books")
public class BookController extends BaseController<Book, BookService> {
	
	
	
}
