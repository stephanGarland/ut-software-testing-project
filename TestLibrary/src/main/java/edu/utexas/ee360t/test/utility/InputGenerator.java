package edu.utexas.ee360t.test.utility;

import java.math.BigDecimal;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.Schema;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InputGenerator {
	
	/**
	 * This method accepts a Schema object and outputs an object representing a valid
	 * input for that parameter.
	 * @param schema	The OpenAPI Schema for the parameter
	 * @return	Object containing a valid input for the parameter
	 */
	public static Object generateValidInput(Schema<?> schema) {
		if(Objects.nonNull(schema.getType())) {
			switch(schema.getType()) {
			case "integer":
				return generateValidIntegers(schema).iterator().next();
			case "double":
				return generateValidDouble(schema).iterator().next();
			case "string":
				return generateValidString(schema).iterator().next();
			case "object":
			default:
				throw new IllegalArgumentException(schema.getType());
			}
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public static Map<String,Object> generateValidObjectFromSchemaWithRef(OpenAPI api, Schema<?> schema){
		if(Objects.isNull(schema.get$ref())) {
			throw new IllegalArgumentException("Schema must contain $ref");
		}
		
		String ref = schema.get$ref();
		String key = ref.substring(ref.lastIndexOf("/") + 1, ref.length());
		Schema<?> component = api.getComponents().getSchemas().get(key);
		
		return generateValidObjectFromSchema(component);
	}
	
	public static Map<String,Object> generateValidObjectFromSchema(Schema<?> schema){
		Map<String,Object> example = new HashMap<>();
		schema.getProperties().forEach((propertyName, details)->{
			if("id".equals(propertyName)) {
				example.put(propertyName, InputGenerator.generateId());
			} else {
				example.put(propertyName, InputGenerator.generateValidInput(details));
			}
		});
		
		return example;
	}
	
	/**
	 * This method accepts a Schema object and outputs a LongStream of random
	 * valid values for the parameter.
	 * @param schema	The OpenAPI Schema for the parameter
	 * @return	LongStream of valid long values
	 */
	public static LongStream generateValidIntegers(Schema<?> schema) {
		return new Random().longs(minimum(schema), maximum(schema));
	}
	
	/**
	 * This method accepts a Schema object and outputs a LongStream of random
	 * invalid values for the parameter.
	 * @param schema	The OpenAPI Schema for the parameter
	 * @return	LongStream of invalid long values
	 */
	public static LongStream generateInvalidIntegers(Schema<?> schema) {
		LongStream tooLow = new Random().longs(Long.MIN_VALUE, minimum(schema));
		LongStream tooHigh = new Random().longs(maximum(schema), Long.MAX_VALUE);
		
		return LongStream.concat(tooLow, tooHigh).unordered();
	}
	
	public static long generateId() {
		return new Random().longs(1, 10).iterator().next();
	}
	
	/**
	 * This method accepts a Schema object and outputs a DoubleStream of random
	 * valid values for the parameter.
	 * @param schema	The OpenAPI Schema for the parameter
	 * @return	DoubleStream of valid double values
	 */
	public static DoubleStream generateValidDouble(Schema<?> schema) {
		return new Random().doubles(minimum(schema), maximum(schema));
	}
	
	/**
	 * This method accepts a Schema object and outputs a DoubleStream of random
	 * invalid values for the parameter.
	 * @param schema	The OpenAPI Schema for the parameter
	 * @return	DoubleStream of invalid double values
	 */
	public static DoubleStream generateInvalidDouble(Schema<?> schema) {
		DoubleStream tooLow = new Random().doubles(Double.MIN_VALUE, minimum(schema));
		DoubleStream tooHigh = new Random().doubles(maximum(schema), Double.MAX_VALUE);
		return DoubleStream.concat(tooLow, tooHigh);
	}
	
	/**
	 * This method accepts a Schema object and outputs a Stream of random
	 * valid values for the parameter.
	 * @param schema	The OpenAPI Schema for the parameter
	 * @return	Stream of valid String values
	 */
	public static Stream<String> generateValidString(Schema<?> schema) {
		final String alphanumerics = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		SecureRandom rand = new SecureRandom();
		Stream.Builder<String> sb = Stream.builder();
		
		IntStream intStream = new Random().ints(schema.getMinLength(), schema.getMaxLength());
		Iterator<Integer> ints = intStream.iterator();
		for(int j = 0; j <= 10; j++) {
			String stream = "";
			for (int i = 0; i <= (int) ints.next(); i++) {
			String s = Character.toString(alphanumerics.charAt(rand.nextInt(alphanumerics.length())));
			stream = stream + s;		
			}
			sb.add(stream);
		}	
		return sb.build();
	}
	
	/**
	 * This method accepts a Schema object and outputs a Stream of random
	 * invalid values for the parameter.
	 * @param schema	The OpenAPI Schema for the parameter
	 * @return	Stream of valid String values
	 */
	public static Stream<String> generateInvalidString(Schema<?> schema) {
		final String alphanumerics = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		SecureRandom rand = new SecureRandom();
		Stream.Builder<String> sb = Stream.builder();
		//TODO: Replace this logic with logic to generate strings that are too long or short
		IntStream intStream = new Random().ints(schema.getMinLength(), schema.getMaxLength());
		Iterator<Integer> ints = intStream.iterator();
		for(int j = 0; j <= 10; j++) {
			String stream = "";
			for (int i = 0; i <= (int) ints.next(); i++) {
			String s = Character.toString(alphanumerics.charAt(rand.nextInt(alphanumerics.length())));
			stream = stream + s;		
			}
			sb.add(stream);
		}	
		return sb.build();
	}

	private static long minimum(Schema<?> schema) {
		BigDecimal min = schema.getMinimum();
		if(Objects.isNull(min)) {
			if(Objects.nonNull(schema.getFormat())) {
				switch(schema.getFormat()) {
				case "int32":
					return Integer.MIN_VALUE;
				case "int64":
				default:
					return Long.MIN_VALUE;
				}
			} else {
				throw new IllegalArgumentException();
			}

		} else {
			return min.longValue();
		}
	}
	
	public static long maximum(Schema<?> schema) {
		BigDecimal max = schema.getMaximum();
		if(Objects.isNull(max)) {
			switch(schema.getFormat()) {
			case "int32":
				return Integer.MAX_VALUE;
			case "int64":
			default:
				return Long.MAX_VALUE;
			}
		} else {
			return max.longValue();
		}
	}

}
