package com.example.myproject.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.myproject.model.Author;
import com.example.myproject.model.Book;

@Service
public class BookService extends BaseService<Book>{
	
	public Book save(Book entity) {
        return repository.save(entity);
    }
	
	public Book updateAuhorList(int id, Set<Author> authors) {
		Book book = repository.getOne(id);
		
//		if(book!=null) {
//			Iterator<Author> iterator = book.getAuthors().iterator();
//			if(book.getAuthors()!=null) {
//				while(iterator.hasNext()) {
//					Author curAuthor = iterator.next();
//					boolean isDelete = true;
//					for(Author author: authors) {
//						//update existing author
//						if(author.getId()>0 ) {
//							if(curAuthor.getId() == author.getId()) {
//								curAuthor.setFirstName(author.getFirstName());
//								curAuthor.setLastName(author.getLastName());
//								isDelete = false;
//								break;
//							}
//						}
//					}
//					
//					if (isDelete) {
//						iterator.remove();
//					}
//				}
//				
//				authors.stream().filter(author -> author.getId()<=0).forEach(author-> {
//					book.getAuthors().add(author);
//				});
//			}
//			else {
//				book.setAuthors(authors);
//			}
//			
//			return repository.save(book);
//		}
		
		return null;
	}
	
	
	
}
