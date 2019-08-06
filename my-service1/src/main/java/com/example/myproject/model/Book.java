package com.example.myproject.model;

import javax.persistence.*;
import java.util.Set;


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
	
//	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
//	@JoinTable(name="user_role",joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
//	private Set<Author> authors;
	

//	public Set<Author> getAuthors() {
//		return authors;
//	}
//
//	public void setAuthors(Set<Author> authors) {
//		this.authors = authors;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
