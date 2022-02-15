package edu.mills.cs180a;

import java.util.Map;

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
		int returnInt = 0;
		for (int i = 0; i < s.length(); i++) {
			switch(s.charAt(i)) {
			case 'M':
			case 'm':
				returnInt += 1000;
				break;
			case 'D':
			case 'd':
				returnInt += 500;
				break;
			case 'C':
			case 'c':
				// check for subtractive notation
				if (i < s.length() - 1) {
					if(s.charAt(i + 1) == 'D' || s.charAt(i + 1) == 'd') {
						returnInt += 400;
						i++;
						break;
					}
					if(s.charAt(i + 1) == 'M' || s.charAt(i + 1) == 'm') {
						returnInt += 900;
						i++;
						break;
					}
				}
				returnInt += 100;
				break;
			case 'L':
			case 'l':
				returnInt += 50;
				break;
			case 'X':
			case 'x':
				// check for subtractive notation
				if (i < s.length() - 1) {
					if(s.charAt(i + 1) == 'L' || s.charAt(i + 1) == 'l') {
						returnInt += 40;
						i++;
						break;
					}
					if(s.charAt(i + 1) == 'C' || s.charAt(i + 1) == 'c') {
						returnInt += 90;
						i++;
						break;
					}
				}
				returnInt += 10;
				break;
			case 'V':
			case 'v':
				returnInt += 5;
				break;
			case 'I':
			case 'i':
				// check for subtractive notation
				if (i < s.length() - 1) {
					if(s.charAt(i + 1) == 'V' || s.charAt(i + 1) == 'v') {
						returnInt += 4;
						i++;
						break;
					}
					if(s.charAt(i + 1) == 'X' || s.charAt(i + 1) == 'x') {
						returnInt += 9;
						i++;
						break;
					}
				}
				returnInt ++;
				break;
			default:
				throw new IllegalArgumentException();
			}
		}
		
		return returnInt;
	}
	
	@VisibleForTesting
	protected static String convertFromIntToString (int n) {
		String numeral = "";
		int remainder = n;
		while (remainder >= 1) {
			if (remainder >= 1000) {
				numeral = numeral + convertFromIntToStringHelper(remainder, 1000);
				remainder = remainder % 1000;
			}
			if (remainder >= 900) {
				numeral = numeral + convertFromIntToStringHelper(remainder, 100);
				remainder = remainder % 100;
			}
			if (remainder >= 500) {
				numeral = numeral + convertFromIntToStringHelper(remainder, 500);
				remainder = remainder % 500;
			}
			if (remainder >= 100) {
				numeral = numeral + convertFromIntToStringHelper(remainder, 100);
				remainder = remainder % 100;
				}
			if (remainder >= 90) {
				numeral = numeral + convertFromIntToStringHelper(remainder, 10);
				remainder = remainder % 10;
			}
			if (remainder >= 50) {
				numeral = numeral + convertFromIntToStringHelper(remainder, 50);
				remainder = remainder % 50;
			}
			if (remainder >= 10) {
				numeral = numeral + convertFromIntToStringHelper(remainder, 10);
				remainder = remainder % 10;
			}
			if (remainder >= 9) {
				numeral = numeral + convertFromIntToStringHelper(remainder, 1);
				remainder = remainder % 1;
			}
			if (remainder >= 5) {
				numeral = numeral + convertFromIntToStringHelper(remainder, 5);
				remainder = remainder % 5;
			}
			if (remainder >= 1) {
				numeral = numeral + convertFromIntToStringHelper(remainder, 1);
				remainder = remainder % 1;
			}
		}
		return numeral;
	}
	
	// add chars for the given divisor amount
	private static String convertFromIntToStringHelper (int num, int digitPlace) {
		int charNum = num / digitPlace;	// # of chars for any one letter
		String returnString = "";
		for (int i = 0; i < charNum; i++) {
			switch (digitPlace) {
			case 1000:
				returnString = returnString + "M";
				break;
			case 500:
				returnString = returnString + "D";
				break;
			case 100:
				// check for subtractive case
				if (charNum == 4) {
					returnString = returnString + "CD";
					return returnString;
				}
				if (charNum == 9) {
					returnString = returnString + "CM";
					return returnString;
				}
				returnString = returnString + "C";
				break;
			case 50:
				returnString = returnString + "L";
				break;
			case 10:
				// check for subtractive case
				if (charNum == 4) {
					returnString = returnString + "XL";
					return returnString;
				}
				if (charNum == 9) {
					returnString = returnString + "XC";
					return returnString;
				}
				returnString = returnString + "X";
				break;
			case 5:
				returnString = returnString + "V";
				break;
			case 1:
				// check for subtractive case
				if (charNum == 4) {
					returnString = returnString + "IV";
					return returnString;
				}
				if (charNum == 9) {
					returnString = returnString + "IX";
					return returnString;
				}
				returnString = returnString + "I";
				break;
			}
		}
		
		return returnString;
	}
}
