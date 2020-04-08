# Test Library

## Preparing your Environment

This library is easiest to use inside your IDE. To get it to work, you'll need to
run the `lombok.jar` file and specify your IDE's install location so that lombok can
generate the Getters and Setters for annotated classes.

### IntelliJ Lombok Installation
I'm not familiar with IntelliJ, but it looks like there's a plugin for enabling lombok: 
`https://plugins.jetbrains.com/plugin/6317-lombok`

## Running the Tests
The dynamic test suite is ran when you run this method of the `TestGenerator` class as JUnit tests:
```
@TestFactory
Stream<DynamicTest> dynamicTests() {
...
```

## Notes on Lombok
The lombok annotations `@Getter`, `@Setter`, and `@NoArgsConstructor`generate Getters, Setters, and Constructors
with no elements for the annotated class. When added to a class `@Getters` and `@Setters` all non-static private fields
have getters and setters generated in accordance with Java convention. They can be added to parameters
to create a Getter or Setter for each field as well.

This lombok annotated class:
```
@Getter @Setter @NoArgsConstructor
public class Book {
	private String author;
	private String title;
}
```
Is equivalent to this plain java class:
```
public class Book {
	private String author;
	private String title;
	
	public Book() {}
	
	public String getAuthor() {
		return this.author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
}
```
There is also an `@AllArgsConstructor` that generates a constructor with a parameter for each
private non-static fields.

The `@Builder` annotation creates a Builder for the class it is annotating. An example of this being used
in the `TestGenerator` class, but I don't plan on using this one very frequently.