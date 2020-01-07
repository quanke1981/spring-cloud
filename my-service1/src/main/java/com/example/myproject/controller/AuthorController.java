package com.example.myproject.controller;

import com.example.myproject.common.FilterCriteria;
import com.example.myproject.model.Author;
import com.example.myproject.model.Book;
import com.example.myproject.model.search.Query;
import com.example.myproject.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/authors")
public class AuthorController extends BaseController<Author, AuthorService> {

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public List<Author> getBooksByQuery (@RequestBody List<FilterCriteria> criteriaList) {
        return service.getAuthorsByQuery(criteriaList);
    }
}
