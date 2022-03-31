package edu.mills.cs180a;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;

class RomanNumeralTests {
	
	
	//Does the test convert Strings to ints?
	@ParameterizedTest
	@CsvSource({"II,2","III,3", "VI,6", "XII,12"})
	void string2IntConvert(String s, int expected)
	{
		int actual = RomanNumeral.convertFromStringToInt(s);
		
		
		assertEquals(expected, actual);
	}
	
	
	//Does the test convert ints to Strings?
	@ParameterizedTest
	@CsvSource({"4,IIII","10,X", "18, XVIII", "7,VII" })
	void int2StringConvert(int n, String expected)
	{
		String actual = RomanNumeral.convertFromIntToString(n);
		//assertThrows(IllegalArgumentException.class);
		assertEquals(expected,actual);
	}	
	
	@ParameterizedTest
	@CsvSource({"-4","0","99999"})
	void testExceptionsThrown(int i) {
		assertThrows(IllegalArgumentException.class,() -> RomanNumeral.convertFromIntToString(i));
	}
}