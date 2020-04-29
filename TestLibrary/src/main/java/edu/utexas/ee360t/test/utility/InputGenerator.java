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

import org.apache.commons.lang3.RandomUtils;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.Schema;

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
			case "boolean":
				return RandomUtils.nextInt(0, 2) != 0;
			case "integer":
				return generateValidIntegers(schema).iterator().next();
			case "number":
				return generateValidDouble(schema).iterator().next();
			case "string":
				if(Objects.isNull(schema.getFormat())) {
					return generateValidString(schema).iterator().next();
				} else {
					switch(schema.getFormat()) {
						case "byte":
						default:
							return generateValidBytes(schema).iterator().next();						
					}
				}
				
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
	 * @return	LongStream of valid byte values
	 */
	public static LongStream generateValidBytes(Schema<?> schema) {
		return new Random().longs(minimumInteger(schema), maximumInteger(schema));
	}
	
	/**
	 * This method accepts a Schema object and outputs a LongStream of random
	 * valid values for the parameter.
	 * @param schema	The OpenAPI Schema for the parameter
	 * @return	LongStream of valid long values
	 */
	public static LongStream generateValidIntegers(Schema<?> schema) {
		return new Random().longs(minimumInteger(schema), maximumInteger(schema));
	}
	
	/**
	 * This method accepts a Schema object and outputs a LongStream of random
	 * invalid values for the parameter.
	 * @param schema	The OpenAPI Schema for the parameter
	 * @return	LongStream of invalid long values
	 */
	public static LongStream generateInvalidIntegers(Schema<?> schema) throws AllValuesAreValidException {
		Long min = minimumInteger(schema);
		Long max = maximumInteger(schema);

		switch(schema.getFormat()) {
		case "int32":
			if(min == Integer.MIN_VALUE) {
				if(max == Integer.MAX_VALUE) {
					throw new AllValuesAreValidException();
				} else {
					return new Random().longs(max, Integer.MAX_VALUE);
				}
			}
		case "int64":
		default:
			if(min == Long.MIN_VALUE) {
				if(max == Long.MAX_VALUE) {
					throw new AllValuesAreValidException();
				} else {
					return new Random().longs(max, Long.MAX_VALUE);
				}
				
			}
		}
		
		LongStream tooLow = new Random().longs(Long.MIN_VALUE, minimumInteger(schema));
		LongStream tooHigh = new Random().longs(maximumInteger(schema), Long.MAX_VALUE);
		
		return LongStream.concat(tooLow, tooHigh).unordered();
	}
	
	public static long generateId() {
		return new Random().longs(1, 9).iterator().next();
	}
	
	/**
	 * This method accepts a Schema object and outputs a DoubleStream of random
	 * valid values for the parameter.
	 * @param schema	The OpenAPI Schema for the parameter
	 * @return	DoubleStream of valid double values
	 */
	public static DoubleStream generateValidDouble(Schema<?> schema) {
		return new Random().doubles(minimumInteger(schema), maximumInteger(schema));
	}
	
	/**
	 * This method accepts a Schema object and outputs a DoubleStream of random
	 * invalid values for the parameter.
	 * @param schema	The OpenAPI Schema for the parameter
	 * @return	DoubleStream of invalid double values
	 */
	public static DoubleStream generateInvalidDouble(Schema<?> schema) {
		Double min = minimumDouble(schema);
		Double max = maximumDouble(schema);
		
		DoubleStream tooLow = null;
		DoubleStream tooHigh = null;
		switch(schema.getFormat()) {
		case "float":
			if(min == Float.MIN_VALUE) {
				tooLow = new Random().doubles(Float.NaN, Float.NaN);
			}
			if(max == Float.MAX_VALUE) {
				tooHigh = new Random().doubles(Float.NaN, Float.NaN);
			}
			break;
		case "double":
		default:
			if(min == Double.MIN_VALUE) {
				tooLow = new Random().doubles(Double.NaN, Double.NaN);
			}
			if(max == Double.MAX_VALUE) {
				tooHigh = new Random().doubles(Double.NaN, Double.NaN);
			}
		}
		
		if(Objects.isNull(tooLow)) {
			tooLow = new Random().doubles(Double.MIN_VALUE, min);
		}
		
		if(Objects.isNull(tooHigh)) {
			tooHigh = new Random().doubles(max, Double.MAX_VALUE);
		}
		
		return DoubleStream.concat(tooLow, tooHigh).unordered();
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
		
		int min, max;
		if(Objects.isNull(schema.getMinLength())){
			min = 0;
		} else {
			min = schema.getMinLength();
		}
		
		if(Objects.isNull(schema.getMaxLength())){
			max = Short.MAX_VALUE;
		} else {
			max = schema.getMaxLength();
		}
		
		IntStream intStream = new Random().ints(min, max);
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
		return null; //sb.build();
	}
	
	/**
	 * This method accepts a Schema object and outputs a Stream of random
	 * invalid values for the parameter.
	 * @param schema	The OpenAPI Schema for the parameter
	 * @return	Stream of valid String values
	 */
	public static String generateValidString() {
		final String alphanumerics = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		SecureRandom rand = new SecureRandom();
		
		String stream = "";
		for (int i = 0; i <= new Random().nextInt(300); i++) {
			String s = Character.toString(alphanumerics.charAt(rand.nextInt(alphanumerics.length())));
			stream = stream + s;		
		}
		return stream;
	}

	private static long minimumInteger(Schema<?> schema) {
		BigDecimal min = schema.getMinimum();
		if(Objects.isNull(min)) {
			if(Objects.nonNull(schema.getFormat())) {
				switch(schema.getFormat()) {
				case "byte":
					return Byte.MIN_VALUE;
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
	
	private static double minimumDouble(Schema<?> schema) {
		BigDecimal min = schema.getMinimum();
		if(Objects.isNull(min)) {
			if(Objects.nonNull(schema.getFormat())) {
				switch(schema.getFormat()) {
				case "float":
					return Float.MIN_VALUE;
				case "double":
				default:
					return Double.MIN_VALUE;
				}
			} else {
				throw new IllegalArgumentException();
			}

		} else {
			return min.longValue();
		}
	}
	
	public static long maximumInteger(Schema<?> schema) {
		BigDecimal max = schema.getMaximum();
		if(Objects.isNull(max)) {
			switch(schema.getFormat()) {
			case "byte":
				return Byte.MAX_VALUE;
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
	
	public static double maximumDouble(Schema<?> schema) {
		BigDecimal max = schema.getMaximum();
		if(Objects.isNull(max)) {
			switch(schema.getFormat()) {
			case "float":
				return Float.MAX_VALUE;
			case "double":
			default:
				return Double.MAX_VALUE;
			}
		} else {
			return max.longValue();
		}
	}

}
