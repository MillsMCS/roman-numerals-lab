package edu.mills.cs180a;

import static org.junit.jupiter.api.Assertions.*;
import static edu.mills.cs180a.RomanNumeral.convertFromIntToString;
import static edu.mills.cs180a.RomanNumeral.convertFromStringToInt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class RomanNumeralTests {
	
	// Testing for out of bounds input
	@ParameterizedTest
	@ValueSource(ints = {-100, 0, 5, 10000})
	void testIntInputIsOutOfBounds(int invalidInt) {
		assertThrows(IllegalArgumentException.class,
				() -> new RomanNumeral(invalidInt));
	}

	// Testing for convertFromStringToInt
	@ParameterizedTest
	@CsvSource({"I,1", "V,5", "X,10", "L,50", "C,100", "D,500", "M,1000"})
	void testStringToIntValidRomanSymbol(String romanNumeral, int expected) {
		int actual = convertFromStringToInt(romanNumeral);
		assertEquals(expected, actual);
	}
	
	@ParameterizedTest
	@CsvSource({"i,1", "v,5", "x,10", "l,50", "c,100", "d,500", "m,1000"})
	void testStringToIntValidLowercaseRomanSymbol(String romanNumeral, int expected) {
		int actual = convertFromStringToInt(romanNumeral);
		assertEquals(expected, actual);
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"@", "V!", "123", "ten", "vi"})
	void testStringToIntInvalidRomanNumeral(String invalidInput) {
		assertThrows(IllegalArgumentException.class,
				() -> convertFromStringToInt(invalidInput));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"IIII", "VIIII", "xxxx", "CMXCIX", "IM"})
	void testStringToIntNonStandardRomanNumeral(String invalidInput) {
		assertThrows(IllegalArgumentException.class,
				() -> convertFromStringToInt(invalidInput));
	}
	
	@ParameterizedTest
	@CsvSource({"II,2", "III,3", "XI,11", "lii,52", "DCXIII,613"})
	void testStringToIntAdditivePrinciple(String additiveNumeral, int expected) {
		int actual = convertFromStringToInt(additiveNumeral);
		assertEquals(expected, actual);
	}
	
	@ParameterizedTest
	@CsvSource({"iv, 4", "IV,4", "IX,9", "XL,40", "XC,90", "CD,400", "CM,900"})
	void testStringToIntSubtractivePrinciple(String subtractiveNumeral, int expected) {
		int actual = convertFromStringToInt(subtractiveNumeral);
		assertEquals(expected, actual);
	}
	
	// Testing for convertFromIntToString
	@ParameterizedTest
	@CsvSource({"1,I", "5,V", "10,X", "50,L", "100,C", "500,D", "1000,M"})
	void testIntToStringIsValidRomanSymbol(int n, String expected) {
		String actual = convertFromIntToString(n);
		assertEquals(expected, actual);
	}
	
	@ParameterizedTest
	@CsvSource({"4,IV", "9,IX", "40,XL", "90,XC", "400,CD", "900,CM"})
	void testIntToStringSubtractivePrinciple(int n, String expected) {
		String actual = convertFromIntToString(n);
		assertEquals(expected, actual);
	}
	
	@ParameterizedTest
	@CsvSource({"2,II", "3,III", "11,XI", "1507,MDVII"})
	void testIntToStringAdditivePrinciple(int n, String expected) {
		String actual = convertFromIntToString(n);
		assertEquals(expected, actual);
	}

}
