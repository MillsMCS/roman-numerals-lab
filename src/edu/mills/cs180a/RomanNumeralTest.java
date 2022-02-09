package edu.mills.cs180a;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static edu.mills.cs180a.RomanNumeral.convertFromIntToString;
import static edu.mills.cs180a.RomanNumeral.convertFromStringToInt;

class RomanNumeralTest {

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
	@ValueSource(ints = {-1, 0, 10000})
	void testOutOfBoundsNumbers(int num) {
		assertThrows(IllegalArgumentException.class,
				() -> convertFromIntToString(num));
	}


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


}//RomanNumeralTest

