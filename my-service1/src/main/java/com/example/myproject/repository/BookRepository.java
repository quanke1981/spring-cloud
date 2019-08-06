package com.example.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myproject.model.Book;
import com.example.myproject.model.User;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

}
