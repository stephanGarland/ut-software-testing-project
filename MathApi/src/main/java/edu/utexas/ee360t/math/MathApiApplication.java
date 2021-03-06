package edu.utexas.ee360t.math;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.endpoint.RefreshEndpoint;
import org.springframework.context.annotation.Import;

@SpringBootApplication @Import(RefreshEndpoint.class)
public class MathApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MathApiApplication.class, args);
	}

}
