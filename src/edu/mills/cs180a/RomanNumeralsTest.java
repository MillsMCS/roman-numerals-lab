package edu.mills.cs180a;

import static org.junit.jupiter.api.Assertions.*;
import static edu.mills.cs180a.RomanNumeral.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class RomanNumeralTest {

	@ParameterizedTest
	@CsvSource({"IV, 4", "XVI, 16", "XXX, 30", "ii, 2", "DCCLXXXIX, 789", "mmmmmmmmmcmxcix, 9999", "cdxliv, 444"})
	void testConvertFromStringToIntWorksForValidString(String input, String expected) {
		assertEquals(convertFromStringToInt(input), Integer.parseInt(expected));
	}
	
	@ParameterizedTest
	@ValueSource(strings={"hi", "bleh", "123"})
	void testConvertFromStringToIntRejectsInvalidString(String input) {
		assertThrows(IllegalArgumentException.class, () -> convertFromStringToInt(input));
	}
	
	@ParameterizedTest
	@ValueSource(strings= {"-V", "MMMMMMMMMMXI"})
	void testConvertFromStringToIntRejectsOutOfBoundsNumeral(String input) {
		assertThrows(IllegalArgumentException.class, () -> new RomanNumeral(input));
	}

	@ParameterizedTest
	@CsvSource({"1, I", "16, XVI", "100, C", "550, DL"})
	void testConvertFromIntToStringWorksForValidSimpleInt(String input, String expected) {
		assertEquals(expected, convertFromIntToString(Integer.parseInt(input)));
	}	
	
	@ParameterizedTest
	@ValueSource(ints={-5, 10000, 0})
	void testConvertFromIntToStringRejectsOutOfBoundsInt(int input) {
		assertThrows(IllegalArgumentException.class, () -> new RomanNumeral(input));
	}

	@ParameterizedTest
	@CsvSource({"9999, MMMMMMMMMCMXCIX", "1, I"})
	void testConvertFromIntToStringWorksForEdgeCases(String input, String expected) {
		assertEquals(expected, convertFromIntToString(Integer.parseInt(input)));
	}
	
	@ParameterizedTest
	@CsvSource({"4, IV", "29, XXIX", "93, XCIII", "440, CDXL"})
	void testConvertFromIntToStringWorksForValidComplexInt(String input, String expected) {
		assertEquals(expected, convertFromIntToString(Integer.parseInt(input)));
	}	

}