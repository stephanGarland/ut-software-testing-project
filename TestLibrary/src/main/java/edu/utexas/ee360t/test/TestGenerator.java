package edu.utexas.ee360t.test;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import edu.utexas.ee360t.test.dto.TestDefinition;
import edu.utexas.ee360t.test.dto.TestDefinition.ExpectedResponse;
import edu.utexas.ee360t.test.dto.TestDefinition.Request;

import static io.restassured.RestAssured.given;

public class TestGenerator {
	
	@TestFactory
	Stream<DynamicTest> dynamicTests() {
		List<TestDefinition> tests = retrieveTestDefinitions();

	    Stream<DynamicTest> testStream 
	      = tests.stream()
	      .map(test -> DynamicTest.dynamicTest(
	        test.toString(), 
	        () -> {
	        	given()
	        		.headers(test.getRequest().getHeaders())
	        		.params(test.getRequest().getParams())
	        		.body(test.getRequest().getBody())
	            .when()
	            	.request(test.getRequest().getMethod().toString(), new URI("http://localhost:8080" + test.getRequest().getUrl()))           	
	            .then()
	            	.headers(test.getResponse().getHeaders())
	            	.statusCode(test.getResponse().getStatus().value());
	        		
	        	//TODO: Add Response Body checks
	        	
	        }));
	         
	    return testStream;
	}
	
	private List<TestDefinition> retrieveTestDefinitions(){
		//This list is populated with sample Test Definitions
		//Eventually we will want to dynamically generate TestDefinitons based on Api definitions
		List<TestDefinition> tests = new ArrayList<>();
		
		//Request request = Request.builder().url("/api/book").method(HttpMethod.GET).build(); 
		
		Request request = new Request("/api/book", HttpMethod.GET);
		ExpectedResponse response = new ExpectedResponse(HttpStatus.BAD_REQUEST, new HttpHeaders(), new HashMap<String, Object>());
		tests.add(TestDefinition.builder().request(request).response(response).build());
		
		Request request2 = new Request("/api/book", HttpMethod.GET);
		request2.addParam("id", 11);
		ExpectedResponse response2 = new ExpectedResponse(HttpStatus.NO_CONTENT, new HttpHeaders(), new HashMap<String, Object>());
		tests.add(TestDefinition.builder().request(request2).response(response2).build());

		Request request3 = new Request("/api/author", HttpMethod.GET);
		ExpectedResponse response3 = new ExpectedResponse(HttpStatus.NOT_FOUND, new HttpHeaders(), new HashMap<String, Object>());
		tests.add(TestDefinition.builder().request(request3).response(response3).build());

		Request request4 = new Request("/api/book", HttpMethod.PATCH);
		ExpectedResponse response4 = new ExpectedResponse(HttpStatus.METHOD_NOT_ALLOWED, new HttpHeaders(), new HashMap<String, Object>());
		tests.add(TestDefinition.builder().request(request4).response(response4).build());		
		
		Request request5 = new Request("/api/book", HttpMethod.POST);
		Map<String,Object> body = new HashMap<>();
		body.put("id", 1);
		body.put("title", "Book");
		body.put("author", "Allen J");
		request5.setBody(body);
		request5.addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
		ExpectedResponse response5 = new ExpectedResponse(HttpStatus.BAD_REQUEST, new HttpHeaders(), new HashMap<String, Object>());
		tests.add(TestDefinition.builder().request(request5).response(response5).build());

		Request request6 = new Request("/api/book", HttpMethod.POST);
		Map<String,Object> body2 = new HashMap<>();
		body2.put("title", "Book");
		body2.put("author", "Allen J");
		request6.setBody(body2);
		request6.addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
		ExpectedResponse response6 = new ExpectedResponse(HttpStatus.OK, new HttpHeaders(), new HashMap<String, Object>());
		tests.add(TestDefinition.builder().request(request6).response(response6).build());		
		
		return tests;
	}

}
