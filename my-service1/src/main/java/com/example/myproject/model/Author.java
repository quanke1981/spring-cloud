package com.example.myproject.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "author")
public class Author extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -8592493562164475525L;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "first_name")
	private String firstName;

	public Author() {
	}

	public Author(Author author) {
		this.id = author.getId();
		this.firstName = author.getFirstName();
		this.lastName = author.getLastName();
	}

	@JsonIgnore
	@ManyToMany(mappedBy = "authors")
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
