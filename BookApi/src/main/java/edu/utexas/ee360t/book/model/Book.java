package edu.utexas.ee360t.book.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This entity class represents Books in the Database.
 */
@Entity
@Getter @Setter @NoArgsConstructor
public class Book extends BaseEntity {
	
	private static final long serialVersionUID = -5271498244709430815L;

	@Id @Null(groups = New.class) @NotNull(groups = Existing.class)
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Long id;
	   
	   @NotNull
	   @Size(min = 0, max = 200)
	   private String title;
	   
	   @NotNull
	   @Size(min = 0, max = 200)
	   private String author;
}