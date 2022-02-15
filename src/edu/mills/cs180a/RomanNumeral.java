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

	//reverse key value pair
	protected static final Map<Integer, Character> VALUES_TO_LETTERS = 
			LETTERS_TO_VALUES.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));


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

		int result = 0;
		int num = 0;

		s = s.toUpperCase();

		if (!s.matches("[ivxlcdmIVXLCDM]+")) {
			throw new IllegalArgumentException("String must be roman numeral");  
		}

		if(s.contains("IIII")||s.contains("VIIII")||s.contains("XXXX")||s.contains("LXXXX")||s.contains("CCCC")||s.contains("DCCCC")) {
			throw new IllegalArgumentException("Cannot be non-standard forms");  
		}

		//standard form exceptions
		if(s.contains("IV")){
			num=4;
			result = result + num;
			s= s.replaceAll(("IV"), "");
		}
		if(s.contains("IX")){
			num=9;
			result = result + num;
			s= s.replaceAll(("IX"), "");
		}
		if(s.contains("XL")){
			num=40;
			result = result + num;
			s= s.replaceAll(("XL"), "");
		}
		if(s.contains("XC")){
			num=90;
			result = result + num;
			s= s.replaceAll(("XC"), "");
		}
		if(s.contains("CD")){
			num=400;
			result = result + num;
			s= s.replaceAll(("CD"), "");
		}
		if(s.contains("CM")){
			num=900;
			result = result + num;
			s= s.replaceAll(("CM"), "");
		}

		//map through LETTERS_TO_VALUES to add
		for (int i=0; i<s.length(); i++) {
			num = LETTERS_TO_VALUES.get(s.charAt(i));
			result = result + num;
		}

		return result;
	}

	@VisibleForTesting
	protected static String convertFromIntToString(int n) {
		if(n>=MIN_VALUE && n<=MAX_VALUE) {

			StringBuilder result = new StringBuilder();

			//arrays for values and symbols that have association
			int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
			String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

			//loop starting from largest value, compare if given num is subtract-able from value, subtract, repeat
			int i = 0;
			while (n > 0) {
				while ( n >= values[i]) {
					n = n - values[i];
					result.append(symbols[i]);
				}
				i++;
			}
			return result.toString();	
		}

		else {
			throw new IllegalArgumentException("Out of bound");
		}

	}
}
