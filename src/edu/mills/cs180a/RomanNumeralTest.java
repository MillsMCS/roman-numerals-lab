package edu.mills.cs180a;


import static edu.mills.cs180a.RomanNumeral.convertFromIntToString;
import static edu.mills.cs180a.RomanNumeral.convertFromStringToInt;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


class RomanNumeralTest {

	@ParameterizedTest
	@ValueSource(strings = {" ", "U", "$"})
	void testConvertFromStringToIntThrowsExceptionForInvalidEntry(String s) {
		assertThrows(IllegalArgumentException.class,
				() -> convertFromStringToInt(s));
	}

	@ParameterizedTest
	@CsvSource({"L,50","XCIV,94","D,500","X,10", "IV,4", "CD,400", "v,5", "xl,40", "IM,1001", "CCCXCVIII,398", "MMMMMMMMM,9000","MMMMMMMMMCMXCIX,9999"})
	void convertFromStringToIntShouldGenerateTheExpectedIntValue(String input, int expected) {
		int actualValue = convertFromStringToInt(input);
		assertEquals(expected, actualValue);
	}

	@ParameterizedTest
	@CsvSource({"50,L","94,XCIV","100,C", "9,IX", "40,XL", "1,I", "324,CCCXXIV", "9000,MMMMMMMMM", "9999,MMMMMMMMMCMXCIX","500,D"})
	void convertFromIntToStringShouldGenerateTheExpectedStringValue(int input, String expected) {
		String actualValue = convertFromIntToString(input);
		assertEquals(expected, actualValue);
	}


	//String to int tests
	@ParameterizedTest
	@CsvSource (value = { "X,10", "C,100", "IV,4", "CMXCIX, 999", "II,2", "MMII, 2002", "CDXXII, 422", "MMMMMMMMCMXCIX, 8999"})
	void testConvertFromStringToInt(String input, int expected) {
		int actualValue = convertFromStringToInt(input);
		assertEquals(expected, actualValue);
	}		

	@Test
	void testInvalidStringInput() {
		assertThrows(IllegalArgumentException.class,
				() -> convertFromStringToInt(" "));
		assertThrows(IllegalArgumentException.class,
				() -> convertFromStringToInt("one"));
	}

	//letters other than Roman numerals
	@Test
	void testInvalidString() {
		assertThrows(IllegalArgumentException.class,
				() -> convertFromStringToInt("J"));
	}

	//Int to string tests
	@ParameterizedTest
	@CsvSource (value = { "9, IX", "540, DXL", "999, CMXCIX", "53, LIII", "5, V", "424, CDXXIV", "113, CXIII", "2000, MM" })
	void testConvertFromIntToString(int input, String expected) {
		String actualValue = convertFromIntToString(input);
		assertEquals(expected, actualValue);
	}

	@ParameterizedTest
	@ValueSource(ints = {0, -1, 10000})
	void testConvertFromIntToStringThrowsExceptionForOutOfRange(int n) {
		assertThrows(IllegalArgumentException.class,
				() -> convertFromIntToString(n));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"DCCCC", "IIII", "VIIII", "XXXX"})
	void testThrowsExceptionForNonStandardForm(String s) {
		assertThrows(IllegalArgumentException.class,
				() -> convertFromStringToInt(s));
	}
	
	@ValueSource(ints = {-1, 0, 10000})
	void testOutOfBoundsNumbers(int num) {
		assertThrows(IllegalArgumentException.class,
				() -> convertFromIntToString(num));
	}

}
