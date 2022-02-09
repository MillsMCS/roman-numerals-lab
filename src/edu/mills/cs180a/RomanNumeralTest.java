package edu.mills.cs180a;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


class RomanNumeralTest {
	
	
    @ParameterizedTest
	@CsvSource( {"I, 1", "V , 5", "X , 10", "L , 50", "C , 100", "D, 500", "M , 1000"} )
	void testEqualityConvertFromStringToInt(String input, int expected) {
    	int actual = RomanNumeral.convertFromStringToInt(input);
		assertEquals(expected, actual);
	}
    
    //throws an illegal exception if 0 is entered as input
	
	@ParameterizedTest
	@ValueSource(ints = {0, -1, -2, -4}) 
	void testIllegalArgumentForConvertIntToString(int n) {
		assertThrows(IllegalArgumentException.class, () -> RomanNumeral.convertFromIntToString(n));	
	}
	
	
	@Test
	void testConvertFromIntToStringIsNotNull() {
		assertNotNull(RomanNumeral.convertFromIntToString(0));
	}
	
	
	@ParameterizedTest
	@CsvSource({"1 , I", "5 , V", " 10 , X", "50 , L", " 100 , C", "500 , D", "1000 , M"})
	void testEqualityConvertFromIntToString(int input, String expected) {
		String actual = RomanNumeral.convertFromIntToString(input);
		assertEquals(expected, actual);
	}

}
