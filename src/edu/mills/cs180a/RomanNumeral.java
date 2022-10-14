package edu.mills.cs180a;
import java.util.*;

/**
 * Immutable Roman Numeral, representing a value between {@link #MIN_VALUE} and
 * {@link #MAX_VALUE}, inclusive.
 * 
 * @author Ellen Spertus
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
	
	protected static final String [] roman = {"I", "V", "X", "L","C", "D", "M"};
	protected static final int [] numerals = {1, 5, 10, 50, 100, 500, 1000 };
	
			
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
		
		char [] romanNums = s.toCharArray();

		int numericValue = 0;
		
		for(char c: romanNums)
		{ numericValue += LETTERS_TO_VALUES.get(c); }
		
		return numericValue;
	}
	
	
	@VisibleForTesting
	protected static String convertFromIntToString(int num) {
		
		if(num > MAX_VALUE || num < MIN_VALUE)
		{
			throw new IllegalArgumentException("Number out of bounds");
		}

		StringBuilder str = new StringBuilder();
		
		for (int i=roman.length-1; i >= 0; i--){
			while(num >= numerals[i]) 
			{
				str.append(roman[i]);
				num -= numerals[i];
			}
				
		}
		return str.toString();
		
	}
}
