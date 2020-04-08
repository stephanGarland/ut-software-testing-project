package edu.utexas.ee360t.book.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import edu.utexas.ee360t.book.model.Book;
import edu.utexas.ee360t.book.repository.BookRepo;

/**
 * This class implements the logic for creating, retrieving updating, and removing
 * objects from the database.
 */
@Service
public class BookService {
	
	private BookRepo repo;
	
	public BookService(BookRepo repo) {
		this.repo = repo;
	}
	
	public Book getBook(long id) {
		Optional<Book> book = repo.findById(id);
		if(book.isPresent()) {
			return book.get();
		} else {
			throw new EntityNotFoundException(Long.toString(id));
		} 
	}
	
	public Book createBook(Book book) {
		return repo.save(book);
	}
	
	public Book updateBook(Book book) {
		if(repo.existsById(book.getId())){
			return repo.save(book);
		} else {
			throw new EntityNotFoundException(Long.toString(book.getId()));
		}
	}
	
	public void deleteBook(long id) {
		repo.deleteById(id);
	}
}