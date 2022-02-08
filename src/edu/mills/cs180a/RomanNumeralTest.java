package edu.mills.cs180a;


import static edu.mills.cs180a.RomanNumeral.convertFromStringToInt;
import static edu.mills.cs180a.RomanNumeral.convertFromIntToString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;

//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


class RomanNumeralTest{

	//tests ConvertFromStringToInt 
	@ParameterizedTest
	@CsvSource({"XI,11", "L,50", "X,10", "MD,1500", "MX,1010", "MMMMMMMMM,9000","MMMMMMMMMCMXCIX,9999", "MMMMMMMMMM,10000"}) // five Strings
	void testIfStringRomanNumeralsAreValidInts(String inputString, int expectedValue) {
		assertEquals(expectedValue, convertFromStringToInt(inputString));
	}

	//Checks if the was a space in String input for convertFromStringToInt
	@ParameterizedTest
	@ValueSource (strings = {" "," ii","mm","x", "   "})
	void testBlanks(String invalidInput) {
		assertThrows(IllegalArgumentException.class, () -> convertFromStringToInt(invalidInput));
	}


	//Checks if the was a space in input for convertFromStringToInt
	@ParameterizedTest
	@ValueSource (strings = {"hello", "x", "i","d", "l","x"})
	void testGetRomanNumeralsThrowsException(String input) {
		assertThrows(IllegalArgumentException.class, () -> convertFromStringToInt(input));
	}


	//tests ConvertFromIntToString 
	@ParameterizedTest
	@CsvSource({"11,XI", "50,L", "10,X", "1500,MD", "1010,MX"}) // five Strings
	void testIfNumConvertsToRoman(int inputNumber, String expectedValue) {
		assertEquals(expectedValue, convertFromIntToString(inputNumber));
	}

	//Checks if there was a bigger int to change to roman because the max is 9999
	@ParameterizedTest
	@ValueSource (ints = {10000}) 
	void testBlankSpaces(int invalidNums) {
		assertThrows(IllegalArgumentException.class, () -> convertFromIntToString(invalidNums));
	}

	//Checks if if there was a bigger int to change to roman because the max is 9999

	@ParameterizedTest
	@ValueSource (ints = {100000, 0,}) 
	void testGetRomanNumeralsCorrespondingStringThrowsException(int Num) {
		assertThrows(IllegalArgumentException.class, () -> convertFromIntToString(Num));
	}



}
