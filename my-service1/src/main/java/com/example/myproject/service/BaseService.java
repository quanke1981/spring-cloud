package com.example.myproject.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.example.myproject.model.BaseEntity;

public abstract class BaseService<T extends BaseEntity> {
    
    @Autowired
    protected JpaRepository<T, Integer> repository;
    
    public List<T> getAll() {
        return repository.findAll();
    }
    
    public T getOne(int id) {
        return repository.getOne(id);
    }
    

    public long count() {
        return repository.count();
    }
    

    public T save(T entity) {
        //return repository.saveAndFlush(entity);
        return repository.save(entity);
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(T entity) {
        repository.delete(entity);
    }
    

    public void deleteById(int id) {
        repository.deleteById(id);
    }
    

    public boolean exists(int id) {
        return repository.existsById(id);
    }
}
