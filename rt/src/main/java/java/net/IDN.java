/* Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java.net;

import com.ibm.icu.text.IDNA;
import com.ibm.icu.text.StringPrepParseException;

/**
 * Internationalized domain names uses all characters from Unicode set, while
 * traditional domain names uses only ASCII code. This class provides
 * transformation between Unicode representation and ASCII Compatible Encoding
 * (ACE) representation. Refer to RFC 3490 for detailed information.
 * 
 * There are two flags used to adjust the transformation.
 * 
 * 1. ALLOW_UNASSIGNED, if this flag is used, the domain name string to be
 * converted can contain unassigned code points in Unicode 3.2. If the flag is
 * not used, then no unassigned code points is not permitted.
 * 
 * 2. USE_STD3_ASCII_RULES, if this flag is used, ASCII strings must compile
 * with RFC 1122 and RFC 1123. It is an error if they don't compile.
 * 
 * These flags can be logically OR'ed together.
 * 
 * @since 1.6
 */
public final class IDN {

	/**
	 * When set, allows IDN to process unassigned unicode points.
	 */
	public static final int ALLOW_UNASSIGNED = 1;

	/**
	 * When set, ASCII strings are checked against RFC 1122 & RFC 1123.
	 */
	public static final int USE_STD3_ASCII_RULES = 2;
	
	private IDN() {
		// Do nothing
	}

	/**
	 * Transform a Unicode String to ASCII Compatible Encoding String according
	 * to the algorithm defined in RFC 3490.
	 * 
	 * Invoking this method is the same as invoking:
	 * 
	 * toASCII(input, 0);
	 * 
	 * @param input
	 *            the string to be transformed
	 * @return the transformed String
	 * @throws IllegalArgumentException -
	 *             if input is not compatible with RFC 3490 specification
	 */
	public static String toASCII(String input) {
		return toASCII(input, 0);
	}

	/**
	 * Transform a Unicode String to ASCII Compatible Encoding String according
	 * to the algorithm defined in RFC 3490.
	 * 
	 * If the tramsformation fails, an IllegalArgumentException will be thrown.
	 * Then the input string is also not a valid IDN.
	 * 
	 * The toASCII operation can handle both label and entire domain name. The
	 * entire domain name are always separated by dots. The dots are: \u002E
	 * (full stop), \u3002 (ideographic full stop), \uFF0E (fullwidth full
	 * stop), and \uFF61 (halfwidth ideographic full stop). If dots are also
	 * transformed, all of them will become \u002E (full stop).
	 * 
	 * @param input
	 *            the string to be transformed
	 * @param flag
	 *            0 or any logical OR of possible flags: ALLOW_UNASSIGNED,
	 *            USE_STD3_ASCII_RULES
	 * @return the transformed String
	 * @throws IllegalArgumentException
	 *             if the input string doesn't conform to RFC 3490 specification
	 */
	public static String toASCII(String input, int flag) {
		String result;
		int ICUFlag = convertFlags(flag);
		try {
			result = IDNA.convertIDNToASCII(input, ICUFlag).toString();
		} catch (StringPrepParseException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return result;
	}

	/**
	 * Translates a string from ASCII Compatible Encoding (ACE) to Unicode
	 * according to the algorithm defined in RFC 3490.
	 * 
	 * ToUnicode never fails. In case of any error, the input string is returned
	 * unmodified.
	 * 
	 * The toUnicode operation can handle both label and entire domain name. The
	 * entire domain name are always separated by dots. The dots are: \u002E
	 * (full stop), \u3002 (ideographic full stop), \uFF0E (fullwidth full
	 * stop), and \uFF61 (halfwidth ideographic full stop).
	 * 
	 * @param input
	 *            the string to be transformed
	 * @param flag
	 *            0 or any logical OR of possible flags: ALLOW_UNASSIGNED,
	 *            USE_STD3_ASCII_RULES
	 * @return the transformed String
	 */
	public static String toUnicode(String input, int flag) {
		String result;
		int ICUFlag = convertFlags(flag);
		try {
			result = IDNA.convertIDNToUnicode(input, ICUFlag).toString();
		} catch (StringPrepParseException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
        return convertDots(result);
	}

	private static String convertDots(String input) {
	    String result = input;
        if (result.indexOf("\u3002") != -1) {
            result = result.replace("\u3002", "\u002E");
        }

        if (result.indexOf("\uFF0E") != -1) {
            result = result.replace("\uFF0E", "\u002E");
        }

        if (result.indexOf("\uFF61") != -1) {
            result = result.replace("\uFF61", "\u002E");
        }

        return result;
    }

    private static int convertFlags(int flag) {
		int ICUFlag = ((flag & IDN.ALLOW_UNASSIGNED) == 0)? 0:IDNA.ALLOW_UNASSIGNED;
		ICUFlag |= ((flag & IDN.USE_STD3_ASCII_RULES) == 0)? 0:IDNA.USE_STD3_RULES;
		return ICUFlag;
	}

	/**
	 * Translates a string from ASCII Compatible Encoding (ACE) to Unicode
	 * according to the algorithm defined in RFC 3490.
	 * 
	 * Invoking this method is the same as invoking:
	 * 
	 * toUnicode(input, 0);
	 * 
	 * @param input
	 *            the string to be transformed
	 * @return the transformed String
	 */
	public static String toUnicode(String input) {
		return toUnicode(input, 0);
	}
}
