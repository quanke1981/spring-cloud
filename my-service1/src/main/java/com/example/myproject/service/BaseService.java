package com.example.myproject.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import com.example.myproject.model.BaseEntity;

public abstract class BaseService<T extends BaseEntity> {
	
    protected abstract JpaRepository<T, Integer> getRepository(); 
    
    public List<T> getAll() {
        return getRepository().findAll();
    }
    
    public Optional<T> findOne(int id) {
    	return getRepository().findById(id);
    }
    
    
    public T getOne(int id) {
        return getRepository().getOne(id);
    }
    

    public long count() {
        return getRepository().count();
    }
    

    public T save(T entity) {
        //return repository.saveAndFlush(entity);
        return getRepository().save(entity);
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(T entity) {
    	getRepository().delete(entity);
    }
    

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteById(int id) {
    	getRepository().deleteById(id);
    }
    

    public boolean exists(int id) {
        return getRepository().existsById(id);
    }
}
