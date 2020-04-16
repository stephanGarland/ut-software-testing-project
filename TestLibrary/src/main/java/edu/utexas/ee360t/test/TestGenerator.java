package edu.utexas.ee360t.test;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

//import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import edu.utexas.ee360t.test.dto.TestDefinition;
import edu.utexas.ee360t.test.dto.TestDefinition.ExpectedResponse;
import edu.utexas.ee360t.test.dto.TestDefinition.Request;
import edu.utexas.ee360t.test.utility.InputGenerator;
import edu.utexas.ee360t.test.utility.OpenApiUtility;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.parameters.RequestBody;

import static io.restassured.RestAssured.given;

public class TestGenerator {
	
	@TestFactory
	Stream<DynamicTest> dynamicTests() {
		List<TestDefinition> tests;
		try {
			tests = retrieveTestDefinitions();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			tests = new ArrayList<>();
		}

	    Stream<DynamicTest> testStream 
	      = tests.parallelStream()
	      .map(test -> DynamicTest.dynamicTest(
	        test.toString(), 
	        () -> {
	        	if(test.getRequest().getBody().isEmpty()) {
		        	given()
	        		.headers(test.getRequest().getHeaders())
	        		.params(test.getRequest().getParams())
	        		.log().all()
	            .when()
	            	.request(test.getRequest().getMethod().toString(), new URI("http://localhost:8080" + test.getRequest().getUrl()))           	
	            .then()
	            	.headers(test.getResponse().getHeaders())
	            	.statusCode(test.getResponse().getStatus().value())
	            	.log().all();
	        	} else {
		        	given()
	        		.headers(test.getRequest().getHeaders())
	        		.params(test.getRequest().getParams())
	        		.body(test.getRequest().getBody())
	        		.log().all()
	            .when()
	            	.request(test.getRequest().getMethod().toString(), new URI("http://localhost:8080" + test.getRequest().getUrl()))           	
	            .then()
	            	.headers(test.getResponse().getHeaders())
	            	.statusCode(test.getResponse().getStatus().value())
	            	.log().all();
	        	}

	        		
	        	//TODO: Add Response Body checks
	        	
	        }));
	    	         
	    return testStream;
	}
	
	private List<TestDefinition> retrieveTestDefinitions() throws JsonParseException, JsonMappingException, IOException{
		//This list is populated with sample Test Definitions
		//Eventually we will want to dynamically generate TestDefinitons based on Api definitions
		List<TestDefinition> tests = new ArrayList<>();
		
		OpenAPI api = OpenApiUtility.retrieveApiSpecifications("http://localhost:8080");
		
		tests.addAll(verifyNotAcceptableResponses(api));	
		
		tests.addAll(verifyUnsupportedMediaTypeResponses(api));
		
		return tests;
	} 
	
	/**
	 * This method generates tests that check for proper 406 Not Acceptable responses from the defined API.
	 */
	private List<TestDefinition> verifyNotAcceptableResponses(OpenAPI api){
		Set<MediaType> typeList = getMediaType();
		
		List<TestDefinition> tests = new ArrayList<>();
		api.getPaths().forEach((path, mapping) ->{
			mapping.readOperationsMap().forEach((method, operation) ->{
				operation.getResponses().forEach((status, response) ->{
					
					response.getContent().forEach((content, mediaType) -> {
						typeList.remove(MediaType.valueOf(content));
					});
					
					typeList.forEach(media -> {
						Request req = new Request(path, HttpMethod.valueOf(method.toString()));
						ExpectedResponse res = new ExpectedResponse(HttpStatus.NOT_ACCEPTABLE, new HttpHeaders(), new HashMap<>());
						req.addHeader(HttpHeaders.ACCEPT, media.toString());
						req.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
						TestDefinition testDef = TestDefinition.builder().request(req).response(res).build();
						tests.add(testDef);
					});
				});
			});
		});
		return tests;
	}
	
	private List<TestDefinition> verifyUnsupportedMediaTypeResponses(OpenAPI api){
		Set<MediaType> typeList = getMediaType();
		
		
		List<TestDefinition> tests = new ArrayList<>();
		api.getPaths().forEach((path, mapping) ->{
			mapping.readOperationsMap().forEach((method, operation) ->{
				if(method.name().equals("GET")) return;
				
				RequestBody body = operation.getRequestBody();
				if(Objects.nonNull(body)) {
					body.getContent().forEach((contentType, schema) ->{
						typeList.remove(MediaType.valueOf(contentType));
					});
				} 
				operation.getResponses().forEach((status, response) ->{

					typeList.forEach(media -> {
 						Request req = new Request(path, HttpMethod.valueOf(method.toString()));
						ExpectedResponse res = new ExpectedResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE, new HttpHeaders(), new HashMap<>());
						req.addHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON.toString());
						req.addHeader(HttpHeaders.CONTENT_TYPE, media.toString());
						if(Objects.nonNull(operation.getParameters())) {
							operation.getParameters().forEach(parameter ->{
								req.addParam(parameter.getName(), (long)InputGenerator.generateValidInput(parameter.getSchema()));
							});
						}

						TestDefinition testDef = TestDefinition.builder().request(req).response(res).build();
						tests.add(testDef);
					});
				});
			});
		});
		return tests;
	}
	
	private Set<MediaType> getMediaType(){
		Set<MediaType> typeList = new HashSet<>();
		typeList.add(MediaType.APPLICATION_XML);
		typeList.add(MediaType.APPLICATION_JSON);
		typeList.add(MediaType.APPLICATION_PDF);
		typeList.add(MediaType.APPLICATION_CBOR);
		typeList.add(MediaType.APPLICATION_OCTET_STREAM);
		typeList.add(MediaType.APPLICATION_PROBLEM_JSON);
		typeList.add(MediaType.APPLICATION_XHTML_XML);
		typeList.add(MediaType.APPLICATION_ATOM_XML);
		typeList.add(MediaType.APPLICATION_FORM_URLENCODED);
		typeList.add(MediaType.APPLICATION_RSS_XML);
		typeList.add(MediaType.APPLICATION_PROBLEM_XML);
		typeList.add(MediaType.APPLICATION_STREAM_JSON);
		typeList.add(MediaType.APPLICATION_PROBLEM_JSON_UTF8);
		typeList.add(MediaType.TEXT_HTML);
		typeList.add(MediaType.TEXT_PLAIN);
		typeList.add(MediaType.TEXT_MARKDOWN);
		typeList.add(MediaType.TEXT_EVENT_STREAM);
		typeList.add(MediaType.TEXT_XML);
		typeList.add(MediaType.IMAGE_GIF);
		typeList.add(MediaType.IMAGE_JPEG);
		typeList.add(MediaType.IMAGE_PNG);
		
		return typeList;
	}

}
