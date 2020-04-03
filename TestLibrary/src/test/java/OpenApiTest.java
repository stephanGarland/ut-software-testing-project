import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import edu.utexas.ee360t.test.dto.Api;
import edu.utexas.ee360t.test.utility.OpenApiUtility;

public class OpenApiTest {
	
	@Test
	public void testOpenApi() throws JsonParseException, JsonMappingException, IOException {
		URL url = new URL("http://localhost:8080/");
		
		Api api = OpenApiUtility.retrieveApiSpecifications(url);
		
		assertNotNull(api);
	}

}
