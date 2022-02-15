package edu.mills.cs180a;

import java.util.Map;
import java.util.stream.Collectors;

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
	
	@VisibleForTesting
	private static final  Map<String, Integer> SUB_FORM= Map.of(
			"IV", 4,
			"IX", 9,
			"XL", 40,
			"XC", 90,
			"CD", 400,
			"CM", 900
			
			);
			
	
	//reverse map keys and values
	//how to flip code from stackOverflow
	//https://stackoverflow.com/questions/38589875/swap-keys-with-values-and-vice-versa-of-hashmap
	@VisibleForTesting
	protected static final Map<Integer, Character> VALUES_TO_LETTERS = 
		LETTERS_TO_VALUES.entrySet().stream()
						.collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
			
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
		String rNum = s.toUpperCase();
		  if (LETTERS_TO_VALUES.containsKey(rNum.charAt(0)) == false) {
			  throw new IllegalArgumentException(
						"Illegal character entered:" + rNum.charAt(0));  
		  }
		// if num first is less than second, subtract
		int total = 0;
		for (int i = 0; i < rNum.length() ; i++) {			
			int n = LETTERS_TO_VALUES.get(rNum.charAt(i));
			if (rNum.length() == 1){ 
				total = total + n;
				}
			if (rNum.length()-1 > i) {
					int m = LETTERS_TO_VALUES.get(rNum.charAt(i + 1));
					String pair = rNum.substring(i, i+2);
						if (SUB_FORM.containsKey(pair)){
							int newTot = SUB_FORM.get(pair);
							total = total + newTot;
							}
						
						//case with two equal letters
						else if (n == m ){
							
							total = total + n + m;
							i++;
						}
						else if (rNum.charAt(i) == rNum.length()-1) {
							total = total + m;
						}
						
			
	}
		}
		return total;
	}
	
	@VisibleForTesting
	protected static String convertFromIntToString(int n) {
		//for testing out of bounds numbers
		if ( n > MAX_VALUE || n < MIN_VALUE) {
			throw new IllegalArgumentException(
					"Value out of bounds [" + MIN_VALUE + "..." + MAX_VALUE + "]: " + n);
		}
		else {
			 String output = "";			 
			 while (n >= 1000) {
				output = output +  VALUES_TO_LETTERS.get(1000);
				n = n-1000;
				}
			 while (n >= 900) {
				 n = n - 900;
				 output = output + "CM";
			 }
			 while (n >= 500) {
					output = output +  VALUES_TO_LETTERS.get(500);
					n = n-500;
				 }
			 while (n >= 400) {
				 n = n - 400;
				 output = output + "CD";
			 }
			 while (n >= 100) {
					output = output +  VALUES_TO_LETTERS.get(100);
					n = n-100;
				 }
			 while (n >= 90) {
				 n = n - 90;
				 output = output + "XC";
			 }
			 while (n >= 50) {
					output = output +  VALUES_TO_LETTERS.get(50);
					n = n-50;
			 }
			 while (n >= 40) {
					 n = n - 40;
					 output = output + "XL";
				 }
			 while (n>= 10) {
				 n = n - 10;
				 output = output + "X";
			 }
			 while (n >= 9) {
				 n = n - 9;
				 output = output + "IX";
			 }
			 while (n >= 5) {
					output = output +  VALUES_TO_LETTERS.get(5);
					n = n-5;
				 }
			 while (n >= 4) {
				 n = n - 4;
				 output = output + "IV";
			 }
			 while (n >= 1) {
				 n = n-1;
				 output = output +  VALUES_TO_LETTERS.get(1);
					
			 }
			 //System.out.println("output is " + output); 
			 return output;
	}
}
}
