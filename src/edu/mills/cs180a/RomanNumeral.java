package edu.mills.cs180a;

import java.util.Map;

/**
 * Immutable Roman Numeral, representing a value between {@link #MIN_VALUE} and
 * {@link #MAX_VALUE}, inclusive.
 * 
 */
public class RomanNumeral {
	/**
	 * The lowest number that can be represented.
	 */
	public static final int MIN_VALUE = 1;

	/**
	 * The highest number that can be represented.
	 */
	public static final int MAX_VALUE = 9999;

	@VisibleForTesting
	protected static final Map<Character, Integer> LETTERS_TO_VALUES = Map.of(
			'I', 1,
			'V', 5,
			'X', 10,
			'L', 50,
			'C', 100,
			'D', 500,
			'M', 1000
			);

	private final int value;
	private String text;

	/**
	 * Constructs a newly-allocated {@code RomanNumeral} object that represents
	 * the specified value. The argument must be in the range {@link #MIN_VALUE}
	 * to {@link #MAX_VALUE}, inclusive.
	 * 
	 * @param value the value to be represented
	 * @throws IllegalArgumentException if the argument is out of bounds
	 */
	public RomanNumeral(int value) {
		if (value < MIN_VALUE || value > MAX_VALUE) {
			throw new IllegalArgumentException(
					"Value out of bounds [" + MIN_VALUE + "..." + MAX_VALUE + "]: " + value);
		}
		this.value = value;
		text = convertFromIntToString(value);
	}

	/**
	 * Constructs a newly-allocated {@code RomanNumeral} object for the 
	 * specified text. The argument must be in the range {@link #MIN_VALUE}
	 * to {@link #MAX_VALUE}, inclusive.
	 * 
	 * @param text the Roman Numeral
	 * @throws IllegalArgumentException if the argument is out of bounds
	 */
	public RomanNumeral(String text) {
		this.text = text;
		value = convertFromStringToInt(text);
		if (value < MIN_VALUE || value > MAX_VALUE) {
			throw new IllegalArgumentException(
					"Value out of bounds [" + MIN_VALUE + "..." + MAX_VALUE + "]: " + value);
		}
	}

	/**
	 * Returns the numeric value of this {@code RomanNumeral}.
	 * 
	 * @return the numeric value of this {@code RomanNumeral}
	 */
	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return text;
	}

	@VisibleForTesting
	protected static int convertFromStringToInt(String s) {

		//Returns the char value at the specified index. An index ranges from 0 to length() - 1. 
		//The first char value of the sequence is at index 0, the next at index 1, and so on, as for array indexing.
		//s.length will be beneficial to when we avoid going over bounds 
		//we do not want an exception
		int len = s.length();
		String given = s.toString();

		System.out.println(" ");
		//System.out.println("Formula:  Last Symbol + The Second to Last Symbol + etc(thrid to last) + (4th to last symbol)");
		System.out.println("Given Symbols: " + given);

		//Examples for lastSymbolOfGivenStringS: 
		//Given: X Then: X is last symbol,
		//Given: XI Then: I is last symbol
		int lastSymbolOfStringS = LETTERS_TO_VALUES.get(s.charAt(len - 1));

		//Current value is going to be updated as the String S is converting each string symbol inside for loop
		//As of now, before the start of loop we start with the last letter of given String S. 		
		int currSymbolValue = lastSymbolOfStringS;
		System.out.println("Last Symbol Value: " + currSymbolValue + " ");		

		//Starts at length -2 because the first loop has not decremented until it adds last value and second to last value so that is why. 
		for (int i = len - 2; i >= 0; i--) {

			if (LETTERS_TO_VALUES.get(s.charAt(i)) >= LETTERS_TO_VALUES.get(s.charAt(i + 1)))
			{
				System.out.println("update position i:" + i);
				System.out.println(" ");
				System.out.println("Formula: " + currSymbolValue + " "+ LETTERS_TO_VALUES.get(s.charAt(i)));

				currSymbolValue += LETTERS_TO_VALUES.get(s.charAt(i));

				System.out.println("Updated Current Symbol Value: " + currSymbolValue);
				System.out.println(" ");
			}
			else
			{
				System.out.println("update position i:" + i);
				System.out.println(" ");
				System.out.println("Formula: " + currSymbolValue + " "+ LETTERS_TO_VALUES.get(s.charAt(i)));
				//Subtracting because the last value is bigger then the following symbol value
				currSymbolValue -= LETTERS_TO_VALUES.get(s.charAt(i));

				System.out.println("Updated Current Symbol Value: " + currSymbolValue);
				System.out.println(" ");
			}
		}
		System.out.println("Finished Update:       Given: " + s.toString() + "," + " Returned Symbol Value: " + currSymbolValue);
		return  currSymbolValue;

	}

	@VisibleForTesting
	protected static String convertFromIntToString(int n) {	
		return null;
	}
}
