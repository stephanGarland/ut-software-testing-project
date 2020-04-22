package edu.utexas.ee360t.book.controller;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.utexas.ee360t.book.model.Book;
import edu.utexas.ee360t.book.service.BookService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

/**
 * This class creates the REST end-points for the Book API.
 */
@CrossOrigin(origins = "*")
@RestController 
@RequestMapping("/api/book")
public class BookController {
	
	private BookService service;
	
	@Autowired
	public BookController(BookService service) {
		System.out.println("Started BookController");
		this.service = service;
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> getBook(@Min(0) @Max(Long.MAX_VALUE) @RequestParam long id) {
		return ResponseEntity.ok(service.getBook(id));
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> createBook(@Validated(Book.New.class) @RequestBody @org.springframework.web.bind.annotation.RequestBody Book book){
		return ResponseEntity.ok(service.createBook(book));
	}
	
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> updateBook(@Validated(Book.Existing.class) @RequestBody @org.springframework.web.bind.annotation.RequestBody Book book){
		return ResponseEntity.ok(service.updateBook(book));
	}
	
	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> deleteBook(@Min(0) @Max(Long.MAX_VALUE) @RequestParam long id){
		service.deleteBook(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value="/test" ,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> getBookTest(@Min(0) @Max(Long.MAX_VALUE) @RequestParam long id, @RequestParam double d, @RequestParam float f) {
		return ResponseEntity.ok(service.getBook(id));
	}

}
