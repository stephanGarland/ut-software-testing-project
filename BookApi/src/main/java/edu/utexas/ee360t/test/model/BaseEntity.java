package edu.utexas.ee360t.test.model;

import edu.utexas.ee360t.book.Base;

/**
 * This BaseEntity contains common values useful for entities.
 */
public class BaseEntity extends Base {
	
	private static final long serialVersionUID = 6508683964501915140L;
	
	/**
	 * The purpose of <code>New</code> and <code>Existing</code> interfaces are
	 * to track the two different types of entities for validation. Validation
	 * will vary based on the group specified.
	 */
	public interface New {}
	public interface Existing {}
}
