package edu.utexas.ee360t.test.utility;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpMethod;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
/**
 * Utility class for retrieving and parsing OpenAPI Specifications.
 */
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.oas.models.OpenAPI;
/**
 * Utility class for retrieving and parsing OpenAPI Specifications.
 */
public class OpenApiUtility {

	/**
	 * This method accepts a domain and returns an object representing the API 
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException
	 * @return
	 */
	public static OpenAPI retrieveApiSpecifications(String domain) throws JsonParseException, JsonMappingException, IOException {
		OpenAPI api;
		try {
			api = new OpenAPIV3Parser().read(domain + "/v3/api-docs");
			
			URI uri = new URI(domain + "/actuator/restart");
            given()
            	.contentType(io.restassured.http.ContentType.JSON)
			.when()
        		.request(HttpMethod.POST.toString(), uri)
        	.then()
        		.statusCode(200);
            
            try {
            
            Thread.sleep(2000);
            
            } catch (InterruptedException e) {
    			e.printStackTrace();
    		}

			return api;
		} catch(NullPointerException e) {
			throw new RuntimeException("Specified API Does not exist. Is it running?", e);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new RuntimeException("An Exception Occured while Restarting Test App", e);
		} 

	}
}
