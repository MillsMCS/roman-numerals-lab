package edu.mills.cs180a;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class RomanNumeralTest {

	/**
	 * 
	 */
	
	
	//Does the test convert Strings to ints?
	@ParameterizedTest
	@CsvSource({"II,2.0","III,4", "VI,5.0", "XII,12"})
	void ParamString2Int(String expected, int actual)
	{
		int input = RomanNumeral.convertFromStringToInt(expected);
		assertEquals(input,actual);
	}
	
	
	//Does the test convert ints to Strings?
	@ParameterizedTest
	@CsvSource({"4,four","10.0,V", "18, XVIII", "7.0,VII" })
	void ParamInt2String(int expected, String actual)
	{
		String input = RomanNumeral.convertFromIntToString(expected);
		//assertThrows(IllegalArgumentException.class);
		assertEquals(input,actual);
	}	
	
	//0 or 1 million
	
	

}
