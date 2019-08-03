package com.example.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myproject.model.BaseEntity;

public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Integer> {
    
}
