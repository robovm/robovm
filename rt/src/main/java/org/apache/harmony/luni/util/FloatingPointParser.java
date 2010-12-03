/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.harmony.luni.util;


/**
 * Used to parse a string and return either a single or double precision
 * floating point number.
 */
public final class FloatingPointParser {

	private static final class StringExponentPair {
		String s;

		int e;

		boolean negative;

		StringExponentPair(String s, int e, boolean negative) {
			this.s = s;
			this.e = e;
			this.negative = negative;
		}
	}

	/**
	 * Takes a String and an integer exponent. The String should hold a positive
	 * integer value (or zero). The exponent will be used to calculate the
	 * floating point number by taking the positive integer the String
	 * represents and multiplying by 10 raised to the power of the
	 * exponent. Returns the closest double value to the real number
	 * 
	 * @param s
	 *            the String that will be parsed to a floating point
	 * @param e
	 *            an int represent the 10 to part
	 * @return the double closest to the real number
	 * 
	 * @exception NumberFormatException
	 *                if the String doesn't represent a positive integer value
	 */
	private static native double parseDblImpl(String s, int e);

	/**
	 * Takes a String and an integer exponent. The String should hold a positive
	 * integer value (or zero). The exponent will be used to calculate the
	 * floating point number by taking the positive integer the String
	 * represents and multiplying by 10 raised to the power of the
	 * exponent. Returns the closest float value to the real number
	 * 
	 * @param s
	 *            the String that will be parsed to a floating point
	 * @param e
	 *            an int represent the 10 to part
	 * @return the float closest to the real number
	 * 
	 * @exception NumberFormatException
	 *                if the String doesn't represent a positive integer value
	 */
	private static native float parseFltImpl(String s, int e);

	/**
	 * Takes a String and does some initial parsing. Should return a
	 * StringExponentPair containing a String with no leading or trailing white
	 * space and trailing zeroes eliminated. The exponent of the
	 * StringExponentPair will be used to calculate the floating point number by
	 * taking the positive integer the String represents and multiplying by 10
	 * raised to the power of the exponent.
	 * 
	 * @param s
	 *            the String that will be parsed to a floating point
	 * @param length
	 *            the length of s
	 * @return a StringExponentPair with necessary values
	 * 
	 * @exception NumberFormatException
	 *                if the String doesn't pass basic tests
	 */
	private static StringExponentPair initialParse(String s, int length) {
		boolean negative = false;
		char c;
		int start, end, decimal;
		int e = 0;

		start = 0;
		if (length == 0)
			throw new NumberFormatException(s);

		c = s.charAt(length - 1);
		if (c == 'D' || c == 'd' || c == 'F' || c == 'f') {
			length--;
			if (length == 0)
				throw new NumberFormatException(s);
		}

		end = Math.max(s.indexOf('E'), s.indexOf('e'));
		if (end > -1) {
			if (end + 1 == length)
				throw new NumberFormatException(s);

                        int exponent_offset = end + 1;
                        if (s.charAt(exponent_offset) == '+') {
                                if (s.charAt(exponent_offset + 1) == '-') {
                                        throw new NumberFormatException(s);
                                }
                                exponent_offset++; // skip the plus sign
                        }
			try {
				e = Integer.parseInt(s.substring(exponent_offset,
                                                                 length));
                        } catch (NumberFormatException ex) {
                                // ex contains the exponent substring
                                // only so throw a new exception with
                                // the correct string
				throw new NumberFormatException(s);
                        }                            
                                    
		} else {
			end = length;
		}
		if (length == 0)
			throw new NumberFormatException(s);

		c = s.charAt(start);
		if (c == '-') {
			++start;
			--length;
			negative = true;
		} else if (c == '+') {
			++start;
			--length;
		}
		if (length == 0)
			throw new NumberFormatException(s);

		decimal = s.indexOf('.');
		if (decimal > -1) {
			e -= end - decimal - 1;
			s = s.substring(start, decimal) + s.substring(decimal + 1, end);
		} else {
			s = s.substring(start, end);
		}

		if ((length = s.length()) == 0)
			throw new NumberFormatException();

		end = length;
		while (end > 1 && s.charAt(end - 1) == '0')
			--end;

		start = 0;
		while (start < end - 1 && s.charAt(start) == '0')
			start++;

		if (end != length || start != 0) {
			e += length - end;
			s = s.substring(start, end);
		}

        // Trim the length of very small numbers, natives can only handle down
        // to E-309
        final int APPROX_MIN_MAGNITUDE = -359;
        final int MAX_DIGITS = 52;
        length = s.length();
        if (length > MAX_DIGITS && e < APPROX_MIN_MAGNITUDE) {
            int d = Math.min(APPROX_MIN_MAGNITUDE - e, length - 1);
            s = s.substring(0, length - d);
            e += d;
        }

		return new StringExponentPair(s, e, negative);
	}

	/*
	 * Assumes the string is trimmed.
	 */
	private static double parseDblName(String namedDouble, int length) {
		// Valid strings are only +Nan, NaN, -Nan, +Infinity, Infinity,
		// -Infinity.
		if ((length != 3) && (length != 4) && (length != 8) && (length != 9)) {
			throw new NumberFormatException();
		}

		boolean negative = false;
		int cmpstart = 0;
		switch (namedDouble.charAt(0)) {
		case '-':
			negative = true; // fall through
		case '+':
			cmpstart = 1;
		default:
		}

		if (namedDouble.regionMatches(false, cmpstart, "Infinity", 0, 8)) {
			return negative ? Double.NEGATIVE_INFINITY
					: Float.POSITIVE_INFINITY;
		}

		if (namedDouble.regionMatches(false, cmpstart, "NaN", 0, 3)) {
			return Double.NaN;
		}

		throw new NumberFormatException();
	}

	/*
	 * Assumes the string is trimmed.
	 */
	private static float parseFltName(String namedFloat, int length) {
		// Valid strings are only +Nan, NaN, -Nan, +Infinity, Infinity,
		// -Infinity.
		if ((length != 3) && (length != 4) && (length != 8) && (length != 9)) {
			throw new NumberFormatException();
		}

		boolean negative = false;
		int cmpstart = 0;
		switch (namedFloat.charAt(0)) {
		case '-':
			negative = true; // fall through
		case '+':
			cmpstart = 1;
		default:
		}

		if (namedFloat.regionMatches(false, cmpstart, "Infinity", 0, 8)) {
			return negative ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY;
		}

		if (namedFloat.regionMatches(false, cmpstart, "NaN", 0, 3)) {
			return Float.NaN;
		}

		throw new NumberFormatException();
	}

	/**
	 * Returns the closest double value to the real number in the string.
	 * 
	 * @param s
	 *            the String that will be parsed to a floating point
	 * @return the double closest to the real number
	 * 
	 * @exception NumberFormatException
	 *                if the String doesn't represent a double
	 */
	public static double parseDouble(String s) {
		s = s.trim();
		int length = s.length();

		if (length == 0) {
			throw new NumberFormatException(s);
		}

		// See if this could be a named double
		char last = s.charAt(length - 1);
		if ((last == 'y') || (last == 'N')) {
			return parseDblName(s, length);
		}
        
        // See if it could be a hexadecimal representation
        if (s.toLowerCase().indexOf("0x") != -1) { //$NON-NLS-1$
            return HexStringParser.parseDouble(s);
        }
        
		StringExponentPair info = initialParse(s, length);

		double result = parseDblImpl(info.s, info.e);
		if (info.negative)
			result = -result;

		return result;
	}

	/**
	 * Returns the closest float value to the real number in the string.
	 * 
	 * @param s
	 *            the String that will be parsed to a floating point
	 * @return the float closest to the real number
	 * 
	 * @exception NumberFormatException
	 *                if the String doesn't represent a float
	 */
	public static float parseFloat(String s) {
		s = s.trim();
		int length = s.length();

		if (length == 0) {
			throw new NumberFormatException(s);
		}

		// See if this could be a named float
		char last = s.charAt(length - 1);
		if ((last == 'y') || (last == 'N')) {
			return parseFltName(s, length);
		}
        
        // See if it could be a hexadecimal representation
        if (s.toLowerCase().indexOf("0x") != -1) { //$NON-NLS-1$
            return HexStringParser.parseFloat(s);
        }
        
		StringExponentPair info = initialParse(s, length);

		float result = parseFltImpl(info.s, info.e);
		if (info.negative)
			result = -result;

		return result;
	}
}
