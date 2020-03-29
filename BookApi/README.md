# Book Test API
This application is a Spring Boot app which exposes a single `Book` API as well as OpenAPI 3.0 endpoints which expose the schema and an interactive UI for testing the API. It supports basic CRUD operations using an H2 in memory database so no items will persist between startups. I tried keeping this API very simple so we could start work on the testing engine. We can create another API later on if we want to test more functionality.

## Building the Application
You'll need Maven or an IDE with Maven Integration to build this application. Once you get Maven installed, use this command from the `/BookApi/` directory:
```
mvn clean install
```
This should build the application and will place the packaged jar into the `/BookApi/target/` folder.

## Running the Application
The application can be ran by using Java from the command line:
```
java -jar BookApi-0.0.1-SNAPSHOT.jar
```
## Useful Endpoints
#### OpenAPI 3.0 Schema
This OpenAPI schema for this API is automatically generated based on the code by a package called springdoc. The OpenAPI 3.0 Schema for the Book API can be found at`http://localhost:8080/v3/api-docs`. This is the endpoint which our testing program will poll to design it's test cases.

#### Swagger UI
This UI is good for testing calls to the API `http://localhost:8080/swagger-ui/index.html`

#### Spring Boot Actuator
This API has a collection of endpoints for monitoring the application `http://localhost:8080/actuator/`
