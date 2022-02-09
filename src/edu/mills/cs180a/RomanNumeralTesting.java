package edu.mills.cs180a;

import static edu.mills.cs180a.RomanNumeral.convertFromIntToString;
import static edu.mills.cs180a.RomanNumeral.convertFromStringToInt;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RomanNumeralTesting {

	@Test
	void stringToInt() {
		assertEquals(5, convertFromStringToInt("V"));
		assertEquals(10, convertFromStringToInt("X"));
		assertEquals(50, convertFromStringToInt("L"));
	}
		
	@ParameterizedTest
	@CsvSource({"x,10", "v,5", "L,50"})
	void ParamStringToInt(String input, int expected) {
		String actual = input.toUpperCase();
		assertEquals(expected, convertFromStringToInt(actual));
	}
	
	@Test
	void intToString() {
		assertEquals("V", convertFromIntToString(5));
		assertEquals("X", convertFromIntToString(10));
		assertEquals("L", convertFromIntToString(50));
	}
	
	@ParameterizedTest
	@CsvSource({"1,I", "5,V", "10,X"})
	void ParamIntToString(int input, String expected) {
		assertEquals(expected, convertFromIntToString(input));
	}


}
