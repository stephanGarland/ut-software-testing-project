package edu.utexas.ee360t.test.utility;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import io.swagger.v3.oas.models.media.Schema;

public class InputGenerator {
	
	/**
	 * This method accepts a Schema object and outputs an object representing a valid
	 * input for that parameter.
	 * @param schema	The OpenAPI Schema for the parameter
	 * @return	Object containing a valid input for the parameter
	 */
	public static Object generateValidInput(Schema<?> schema) {
		switch(schema.getType()) {
		case "integer":
		default:
			return generateValidIntegers(schema).iterator().next();
		case "double":
			return generateValidDouble(schema).iterator().next();
		}
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
	 * @return	LongStream of valid long values
	 */
	public static LongStream generateInvalidIntegers(Schema<?> schema) {
		//TODO: Create the logic to generate invalid Integers
		return null;
	}
	
	/**
	 * This method accepts a Schema object and outputs a DoubleStream of random
	 * valid values for the parameter.
	 * @param schema	The OpenAPI Schema for the parameter
	 * @return	DoubleStream of valid double values
	 */
	public static DoubleStream generateValidDouble(Schema<?> schema) {
		//TODO: Create the logic to generate valid Doubles
		return null;
	}
	
	/**
	 * This method accepts a Schema object and outputs a DoubleStream of random
	 * invalid values for the parameter.
	 * @param schema	The OpenAPI Schema for the parameter
	 * @return	DoubleStream of invalid double values
	 */
	public static DoubleStream generateInvalidDouble(Schema<?> schema) {
		//TODO: Create the logic to generate invalid Doubles
		return null;
	}
	
	/**
	 * This method accepts a Schema object and outputs a DoubleStream of random
	 * valid values for the parameter.
	 * @param schema	The OpenAPI Schema for the parameter
	 * @return	Stream of valid String values
	 */
	public static Stream<String> generateValidString(Schema<?> schema) {
		//TODO: Create the logic to generate valid Strings
		return null;
	}

	private static long minimum(Schema<?> schema) {
		BigDecimal min = schema.getMinimum();
		if(Objects.isNull(min)) {
			switch(schema.getFormat()) {
			case "int32":
				return Integer.MIN_VALUE;
			case "int64":
			default:
				return Long.MIN_VALUE;
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
