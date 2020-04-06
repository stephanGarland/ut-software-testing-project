package edu.utexas.ee360t.test.utility;

import java.io.IOException;
import java.net.URL;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
	public static OpenAPI retrieveApiSpecifications(URL domain) throws JsonParseException, JsonMappingException, IOException {
		OpenAPI api = new OpenAPIV3Parser().read(String.valueOf(domain));
		return api;
	}
}
