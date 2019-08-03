package com.example.myproject.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="author")
public class Author extends BaseEntity {
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="first_name")
	private String firstName;
	
	
	@ManyToMany(fetch=FetchType.LAZY, mappedBy="authors")
	private Set<Book> books;

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	
	
	
}
