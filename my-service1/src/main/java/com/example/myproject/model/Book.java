package com.example.myproject.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="book")
public class Book extends BaseEntity {
	
	public Book() {
    }
	
	public Book(Book book) {
        this.id = book.getId();
        this.name = book.getName();
    }

	@Column(name="book_name")
	private String name;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book_author", joinColumns = {
            @JoinColumn(name = "book_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "author_id", referencedColumnName = "id")})
	private Set<Author> authors;
	

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
