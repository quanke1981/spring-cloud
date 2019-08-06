package com.example.myproject.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.myproject.model.BaseEntity;
import com.example.myproject.model.Book;
import com.example.myproject.service.BaseService;

@SuppressWarnings("rawtypes")
public abstract class BaseController<T extends BaseEntity, V extends BaseService> {
    
    @Autowired
    protected V service;
    
    @SuppressWarnings("unchecked")
	@RequestMapping(method=RequestMethod.GET)
    @ResponseBody
    public List<T> get() {
        return service.getAll();
    }
    
    
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public T getUserById(@PathVariable int id) {
    	Optional<T> entity = service.findOne(id);
    	
    	if(entity.isPresent()) {
    		return entity.get();
    	}
    	return null;
    }
    
    @SuppressWarnings("unchecked")
    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<T> post(@RequestBody T entity) {
        return ResponseEntity.ok((T)service.save(entity));
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public boolean delete(@PathVariable int id) {
        if(service.exists(id)) {
            service.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }
    
    
}
