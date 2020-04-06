package edu.utexas.ee360t.test.utility;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.net.URL;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import edu.utexas.ee360t.test.dto.Api;

public class OpenApiTest {
	
	@Test
	public void testOpenApi() throws JsonParseException, JsonMappingException, IOException {
		URL url = new URL("http://localhost:8080/");
		OpenAPI api = OpenApiUtility.retrieveApiSpecifications(url);
		
		assertNotNull(api);
	}

}
