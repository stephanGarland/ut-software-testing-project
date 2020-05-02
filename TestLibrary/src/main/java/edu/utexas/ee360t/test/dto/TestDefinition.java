package edu.utexas.ee360t.test.dto;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Builder @Getter
public class TestDefinition {
	
	private final Request request;
	private final ExpectedResponse response;
	
	@Getter @Setter
	@AllArgsConstructor
	public static class Request {
		private String url;
		private HttpMethod method;
		private HttpHeaders headers = new HttpHeaders();
		private Map<String,Object> params = new HashMap<>();
		private Map<String,Object> body = new HashMap<>();
		
		public Request(String url, HttpMethod method) {
			this.method = method;
			this.url = url;
		}
		
		public Request(String url, io.swagger.v3.oas.models.PathItem.HttpMethod method) {
			this.method = HttpMethod.valueOf(method.toString());
			this.url = url;
		}
		
		public void addParam(String key, Object value) {
			params.put(key, value);
		}
		
		public void addHeader(String key, String value) {
			headers.put(key, Arrays.asList(value));
		}
	}
	
	@Getter @Setter
	@AllArgsConstructor
	public static class ExpectedResponse {
		private HttpStatus status;
		private HttpHeaders headers = new HttpHeaders();
		private Map<String,Object> body = new HashMap<>();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(request.getMethod().toString()).append(" ")
		.append(request.getUrl());
		
		if(!request.getParams().isEmpty()) {
			sb.append("?");
		}
		Set<Entry<String,Object>> params = request.getParams().entrySet();
		
		params.forEach(param -> {
			sb.append(param.getKey()).append("=")
			.append(param.getValue())
			.append("&");
		});
		if(!request.getParams().isEmpty()) {
			sb.deleteCharAt(sb.length() -1);
		}
		
		sb.append(" ")
		.append(" ").append(response.getStatus().value());
		
		sb.append(" ").append("ct:").append(request.getHeaders().getContentType());
		
		return sb.toString();
	}
}
