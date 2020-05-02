# ut-software-testing-project
Spring 2020 Software Testing Group Project

## Overview
Dynamically generates a large number of JUnit tests to test an API. Those tests are dynamically generated based off of the `/v3/api-docs/` endpoint on the API under test. The final product should be able to be pointed at any API that offers an OpenAPI schema at this endpoint to put it under test.

## Book API
A basic API for CRUD operations on a simple Book database table. This API is located within the `ut-software-testing-project\BookApi\` folder. It exposes OpenAPI 3.0 specifications through it's `/v3/api-docs/` endpoint.

## Math API
A basic API for simple math operations. This API is located within the `ut-software-testing-project\MathApi\` folder. It exposes OpenAPI 3.0 specifications through it's `/v3/api-docs/` endpoint.

### Testing Framework
- JUnit 5
- Lombok
- Rest Assured

We are using JUnit 5 so that we can create a small number of dynamic tests with a large number of paramters to those tests. RestAssured integrates well with JUnit and allows for easy validation of Responses to HTTP Requests. Lombok is used to ease development, automatically creating Constructors, Getters, Setters, etc.

The package needs a base URL for the application, and then calls the `/v3/api-docs/` end-point to gather information about the APIs which that application exposes. From there, a large number of tests are created. For example, if the following endpoint was the only one exposed on an API, the following tests could be created:
```
"paths": {
  "/api/book": {
    "get": {
      "tags": [
        "book-controller"
      ],
    "operationId": "getBook",
    "parameters": [
      {
        "name": "id",
        "in": "query",
        "required": true,
        "schema": {
          "type": "integer",
          "format": "int64"
        }
      }
    ],
  "responses": {
    "200": {
      "description": "default response",
      "content": {
        "*/*": {
          "schema": {
          "$ref": "#/components/schemas/Book"
          }
        }
      }
    }
  }
}
```
1. GET  /api/book?id=1       Expect 200 if we know id 1 exists
2. GET  /api/book?id=hello   Expect 400 because id is an int
3. GET  /api/book?id=9999    Expect 204 if we know id 9999 doesn't exist
4. POST /api/book            Expect 405 because POST isn't a valid method
5. PUT  /api/book            Expect 405 because PUT isn't a valid method
6. GET  /api/bookz           Expect 404 because this isn't a valid route
7. etc.
