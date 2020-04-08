package edu.utexas.ee360t.book.repository;

import org.springframework.stereotype.Repository;

import edu.utexas.ee360t.book.model.Book;

import org.springframework.data.repository.CrudRepository;

/**
 * This class creates the repository methods for direct CRUD access to the database.
 * In this case, Spring Data JPA is automatically generating the database logic.
 * @author Allen James
 */
@Repository
public interface BookRepo extends CrudRepository<Book, Long> {
	//Spring is generating these CRUD functions since this extends CrudRepository & has annotation
}