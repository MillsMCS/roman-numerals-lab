package edu.mills.cs180a;

import java.util.Map;
import java.util.Map.Entry;

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
	
	
	/**
	 * Takes a Roman Numeral and converts it to an integer.
	 * @param s the Roman Numeral inputed to convert to an integer
	 * @return Integer conversion of s
	 */
	@VisibleForTesting
	protected static int convertFromStringToInt(String s) {
		s = s.toUpperCase();
		int ans = LETTERS_TO_VALUES.get(s.charAt(s.length()-1));
		
		for(int i = 0; i < MAX_VALUE; i++) {			
			return LETTERS_TO_VALUES.get(s.charAt(i));
		}
		return ans;
	}
	
	/**
	 * Takes a number and converts it to Roman Numeral
	 * @param n the number inputed to convert to Roman Numeral
	 * @return the Roman Numeral of n
	 */
	@VisibleForTesting
	protected static String convertFromIntToString(int n) {
		String ans = "";
		
		for(Entry<Character, Integer> entry : LETTERS_TO_VALUES.entrySet()) {
			if(entry.getValue() == n) {
				ans = entry.getKey().toString();
				return ans;
			}
		}
		
		return ans.toString();
	}
	
	
}
