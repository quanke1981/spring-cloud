package com.example.myproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import com.vladmihalcea.hibernate.type.json.JsonNodeStringType;

@Entity
@Table(name="book")
@TypeDef(name = "jsonb-node", typeClass = JsonNodeStringType.class)
public class Book extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -6460903024062661162L;

	public Book() {
    }
	
	public Book(Book book) {
        this.id = book.getId();
        this.name = book.getName();
    }

	@Column(name="book_name")
	private String name;

	public JsonNode getProperties() {
		return properties;
	}

	public void setProperties(JsonNode properties) {
		this.properties = properties;
	}

	@Type(type = "jsonb-node")
	@Column(name="book_properties", length = Integer.MAX_VALUE, columnDefinition = "JSON")
	private JsonNode properties;

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name="book_author",joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
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


	
	@Override
    public String toString() {
        return "Book {" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
	
}
