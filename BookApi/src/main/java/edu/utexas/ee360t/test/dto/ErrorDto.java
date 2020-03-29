package edu.utexas.ee360t.test.dto;

import org.springframework.http.HttpStatus;

import edu.utexas.ee360t.book.Base;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This DTO is used to return Error messages.
 */
@AllArgsConstructor @Getter
public class ErrorDto extends Base {

	private static final long serialVersionUID = -1846603868951898094L;
	private HttpStatus code;
	private String message;

}
