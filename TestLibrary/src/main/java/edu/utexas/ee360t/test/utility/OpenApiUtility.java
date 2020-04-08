package edu.utexas.ee360t.test.utility;

import java.io.IOException;
import java.net.URL;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import edu.utexas.ee360t.test.dto.Api;

/**
 * Utility class for retrieving and parsing OpenAPI Specifications.
 */
public class OpenApiUtility {
	
	/**
	 * This method accepts a domain and returns an object representing the API 
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public static Api retrieveApiSpecifications(URL domain) throws JsonParseException, JsonMappingException, IOException {
		//TODO: Parse OpenAPI Specification into Api
		Api api = new Api();
		return api;
	}
}
