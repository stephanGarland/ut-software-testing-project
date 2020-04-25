package edu.utexas.ee360t.test.utility;

import java.io.IOException;
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
		try {
			OpenAPI api = new OpenAPIV3Parser().read(domain + "/v3/api-docs");
			return api;
		} catch(NullPointerException e) {
			throw new RuntimeException("Specified API Does not exist. Is it running?");
		}

	}
}
