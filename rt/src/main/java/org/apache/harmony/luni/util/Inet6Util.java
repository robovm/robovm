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

package org.apache.harmony.luni.util;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Utility functions for IPV6 operations.
 */
public class Inet6Util {

    /**
     * Creates an byte[] based on an ipAddressString. No error handling is
     * performed here.
     */
    public static byte[] createByteArrayFromIPAddressString(
            String ipAddressString) {

        if (isValidIPV4Address(ipAddressString)) {
            StringTokenizer tokenizer = new StringTokenizer(ipAddressString, ".");
            String token = "";
            int tempInt = 0;
            byte[] byteAddress = new byte[4];
            for (int i = 0; i < 4; i++) {
                token = tokenizer.nextToken();
                tempInt = Integer.parseInt(token);
                byteAddress[i] = (byte) tempInt;
            }

            return byteAddress;
        }

        if (ipAddressString.charAt(0) == '[') {
            ipAddressString = ipAddressString.substring(1, ipAddressString.length() - 1);
        }

        StringTokenizer tokenizer = new StringTokenizer(ipAddressString, ":.", true);
        ArrayList<String> hexStrings = new ArrayList<String>();
        ArrayList<String> decStrings = new ArrayList<String>();
        String token = "";
        String prevToken = "";
        // If a double colon exists, we need to insert 0s.
        int doubleColonIndex = -1;

        /*
         * Go through the tokens, including the separators ':' and '.' When we
         * hit a : or . the previous token will be added to either the hex list
         * or decimal list. In the case where we hit a :: we will save the index
         * of the hexStrings so we can add zeros in to fill out the string
         */
        while (tokenizer.hasMoreTokens()) {
            prevToken = token;
            token = tokenizer.nextToken();

            if (token.equals(":")) {
                if (prevToken.equals(":")) {
                    doubleColonIndex = hexStrings.size();
                } else if (!prevToken.equals("")) {
                    hexStrings.add(prevToken);
                }
            } else if (token.equals(".")) {
                decStrings.add(prevToken);
            }
        }

        if (prevToken.equals(":")) {
            if (token.equals(":")) {
                doubleColonIndex = hexStrings.size();
            } else {
                hexStrings.add(token);
            }
        } else if (prevToken.equals(".")) {
            decStrings.add(token);
        }

        // figure out how many hexStrings we should have
        // also check if it is a IPv4 address
        int hexStringsLength = 8;

        // If we have an IPv4 address tagged on at the end, subtract
        // 4 bytes, or 2 hex words from the total
        if (decStrings.size() > 0) {
            hexStringsLength -= 2;
        }

        // if we hit a double Colon add the appropriate hex strings
        if (doubleColonIndex != -1) {
            int numberToInsert = hexStringsLength - hexStrings.size();
            for (int i = 0; i < numberToInsert; i++) {
                hexStrings.add(doubleColonIndex, "0");
            }
        }

        byte ipByteArray[] = new byte[16];

        // Finally convert these strings to bytes...
        for (int i = 0; i < hexStrings.size(); i++) {
            convertToBytes(hexStrings.get(i), ipByteArray, i * 2);
        }

        // Now if there are any decimal values, we know where they go...
        for (int i = 0; i < decStrings.size(); i++) {
            ipByteArray[i + 12] = (byte) (Integer.parseInt(decStrings.get(i)) & 255);
        }

        // now check to see if this guy is actually and IPv4 address
        // an ipV4 address is ::FFFF:d.d.d.d
        boolean ipV4 = true;
        for (int i = 0; i < 10; i++) {
            if (ipByteArray[i] != 0) {
                ipV4 = false;
                break;
            }
        }

        if (ipByteArray[10] != -1 || ipByteArray[11] != -1) {
            ipV4 = false;
        }

        if (ipV4) {
            byte ipv4ByteArray[] = new byte[4];
            for (int i = 0; i < 4; i++) {
                ipv4ByteArray[i] = ipByteArray[i + 12];
            }
            return ipv4ByteArray;
        }

        return ipByteArray;

    }

    static String hexCharacters = "0123456789ABCDEF";

    public static String createIPAddrStringFromByteArray(byte ipByteArray[]) {
        if (ipByteArray.length == 4) {
            return addressToString(bytesToInt(ipByteArray, 0));
        }

        if (ipByteArray.length == 16) {
            if (isIPv4MappedAddress(ipByteArray)) {
                byte ipv4ByteArray[] = new byte[4];
                for (int i = 0; i < 4; i++) {
                    ipv4ByteArray[i] = ipByteArray[i + 12];
                }
                return addressToString(bytesToInt(ipv4ByteArray, 0));
            }
            StringBuilder buffer = new StringBuilder();
            boolean isFirst = true;
            for (int i = 0; i < ipByteArray.length; i++) {
                if ((i & 1) == 0) {
                    isFirst = true;
                }
                int j = (ipByteArray[i] & 0xf0) >>> 4;
                if (j != 0 || !isFirst) {
                    buffer.append(hexCharacters.charAt(j));
                    isFirst = false;
                }
                j = ipByteArray[i] & 0x0f;
                if (j != 0 || !isFirst) {
                    buffer.append(hexCharacters.charAt(j));
                    isFirst = false;
                }
                if ((i & 1) != 0 && (i + 1) < ipByteArray.length) {
                    if (isFirst) {
                        buffer.append('0');
                    }
                    buffer.append(':');
                }
            }
            return buffer.toString();
        }
        return null;
    }

    /** Converts a 4 character hex word into a 2 byte word equivalent */
    public static void convertToBytes(String hexWord, byte ipByteArray[],
            int byteIndex) {

        int hexWordLength = hexWord.length();
        int hexWordIndex = 0;
        ipByteArray[byteIndex] = 0;
        ipByteArray[byteIndex + 1] = 0;
        int charValue;

        // high order 4 bits of first byte
        if (hexWordLength > 3) {
            charValue = getIntValue(hexWord.charAt(hexWordIndex++));
            ipByteArray[byteIndex] = (byte) (ipByteArray[byteIndex] | (charValue << 4));
        }

        // low order 4 bits of the first byte
        if (hexWordLength > 2) {
            charValue = getIntValue(hexWord.charAt(hexWordIndex++));
            ipByteArray[byteIndex] = (byte) (ipByteArray[byteIndex] | charValue);
        }

        // high order 4 bits of second byte
        if (hexWordLength > 1) {
            charValue = getIntValue(hexWord.charAt(hexWordIndex++));
            ipByteArray[byteIndex + 1] = (byte) (ipByteArray[byteIndex + 1] | (charValue << 4));
        }

        // low order 4 bits of the first byte
        charValue = getIntValue(hexWord.charAt(hexWordIndex));
        ipByteArray[byteIndex + 1] = (byte) (ipByteArray[byteIndex + 1] | charValue & 15);
    }

    static int getIntValue(char c) {

        switch (c) {
        case '0':
            return 0;
        case '1':
            return 1;
        case '2':
            return 2;
        case '3':
            return 3;
        case '4':
            return 4;
        case '5':
            return 5;
        case '6':
            return 6;
        case '7':
            return 7;
        case '8':
            return 8;
        case '9':
            return 9;
        }

        c = Character.toLowerCase(c);
        switch (c) {
        case 'a':
            return 10;
        case 'b':
            return 11;
        case 'c':
            return 12;
        case 'd':
            return 13;
        case 'e':
            return 14;
        case 'f':
            return 15;
        }
        return 0;
    }

    private static boolean isIPv4MappedAddress(byte ipAddress[]) {

        // Check if the address matches ::FFFF:d.d.d.d
        // The first 10 bytes are 0. The next to are -1 (FF).
        // The last 4 bytes are varied.
        for (int i = 0; i < 10; i++) {
            if (ipAddress[i] != 0) {
                return false;
            }
        }

        if (ipAddress[10] != -1 || ipAddress[11] != -1) {
            return false;
        }

        return true;

    }

    /**
     * Takes the byte array and creates an integer out of four bytes starting at
     * start as the high-order byte. This method makes no checks on the validity
     * of the parameters.
     */
    public static int bytesToInt(byte bytes[], int start) {
        // First mask the byte with 255, as when a negative
        // signed byte converts to an integer, it has bits
        // on in the first 3 bytes, we are only concerned
        // about the right-most 8 bits.
        // Then shift the rightmost byte to align with its
        // position in the integer.
        int value = ((bytes[start + 3] & 255))
                | ((bytes[start + 2] & 255) << 8)
                | ((bytes[start + 1] & 255) << 16)
                | ((bytes[start] & 255) << 24);
        return value;
    }

    public static String addressToString(int value) {
        return ((value >> 24) & 0xff) + "." + ((value >> 16) & 0xff) + "."
                + ((value >> 8) & 0xff) + "." + (value & 0xff);
    }

    public static boolean isIP6AddressInFullForm(String ipAddress) {
        if (isValidIP6Address(ipAddress)) {
            int doubleColonIndex = ipAddress.indexOf("::");
            if (doubleColonIndex >= 0) {
                // Simplified form which contains ::
                return false;
            }
            return true;
        }
        return false;
    }

    public static boolean isValidIP6Address(String ipAddress) {
        int length = ipAddress.length();
        boolean doubleColon = false;
        int numberOfColons = 0;
        int numberOfPeriods = 0;
        int numberOfPercent = 0;
        String word = "";
        char c = 0;
        char prevChar = 0;
        int offset = 0; // offset for [] IP addresses

        if (length < 2) {
            return false;
        }

        for (int i = 0; i < length; i++) {
            prevChar = c;
            c = ipAddress.charAt(i);
            switch (c) {

            // case for an open bracket [x:x:x:...x]
            case '[':
                if (i != 0) {
                    return false; // must be first character
                }
                if (ipAddress.charAt(length - 1) != ']') {
                    return false; // must have a close ]
                }
                offset = 1;
                if (length < 4) {
                    return false;
                }
                break;

            // case for a closed bracket at end of IP [x:x:x:...x]
            case ']':
                if (i != length - 1) {
                    return false; // must be last character
                }
                if (ipAddress.charAt(0) != '[') {
                    return false; // must have a open [
                }
                break;

            // case for the last 32-bits represented as IPv4 x:x:x:x:x:x:d.d.d.d
            case '.':
                numberOfPeriods++;
                if (numberOfPeriods > 3) {
                    return false;
                }
                if (!isValidIP4Word(word)) {
                    return false;
                }
                if (numberOfColons != 6 && !doubleColon) {
                    return false;
                }
                // a special case ::1:2:3:4:5:d.d.d.d allows 7 colons with an
                // IPv4 ending, otherwise 7 :'s is bad
                if (numberOfColons == 7 && ipAddress.charAt(0 + offset) != ':'
                        && ipAddress.charAt(1 + offset) != ':') {
                    return false;
                }
                word = "";
                break;

            case ':':
                numberOfColons++;
                if (numberOfColons > 7) {
                    return false;
                }
                if (numberOfPeriods > 0) {
                    return false;
                }
                if (prevChar == ':') {
                    if (doubleColon) {
                        return false;
                    }
                    doubleColon = true;
                }
                word = "";
                break;
            case '%':
                if (numberOfColons == 0) {
                    return false;
                }
                numberOfPercent++;

                // validate that the stuff after the % is valid
                if ((i + 1) >= length) {
                    // in this case the percent is there but no number is
                    // available
                    return false;
                }
                try {
                    Integer.parseInt(ipAddress.substring(i + 1));
                } catch (NumberFormatException e) {
                    // right now we just support an integer after the % so if
                    // this is not
                    // what is there then return
                    return false;
                }
                break;

            default:
                if (numberOfPercent == 0) {
                    if (word.length() > 3) {
                        return false;
                    }
                    if (!isValidHexChar(c)) {
                        return false;
                    }
                }
                word += c;
            }
        }

        // Check if we have an IPv4 ending
        if (numberOfPeriods > 0) {
            if (numberOfPeriods != 3 || !isValidIP4Word(word)) {
                return false;
            }
        } else {
            // If we're at then end and we haven't had 7 colons then there is a
            // problem unless we encountered a doubleColon
            if (numberOfColons != 7 && !doubleColon) {
                return false;
            }

            // If we have an empty word at the end, it means we ended in either
            // a : or a .
            // If we did not end in :: then this is invalid
            if (numberOfPercent == 0) {
                if (word == "" && ipAddress.charAt(length - 1 - offset) == ':'
                        && ipAddress.charAt(length - 2 - offset) != ':') {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean isValidIP4Word(String word) {
        char c;
        if (word.length() < 1 || word.length() > 3) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            c = word.charAt(i);
            if (!(c >= '0' && c <= '9')) {
                return false;
            }
        }
        if (Integer.parseInt(word) > 255) {
            return false;
        }
        return true;
    }

    static boolean isValidHexChar(char c) {

        return (c >= '0' && c <= '9') || (c >= 'A' && c <= 'F')
                || (c >= 'a' && c <= 'f');
    }

    /**
     * Takes a string and parses it to see if it is a valid IPV4 address.
     * 
     * @return true, if the string represents an IPV4 address in dotted
     *         notation, false otherwise
     */
    public static boolean isValidIPV4Address(String value) {
        // general test
        if (!value.matches("[\\p{Digit}\\.]*")) {
            return false;
        }

        String[] parts = value.split("\\.");
        int length = parts.length;
        if (length > 4) {
            return false;
        }

        // for one part
        if (parts.length == 1) {
            long longValue = Long.parseLong(parts[0]);
            return longValue >= 0 && longValue <= 0xFFFFFFFFL;
        }
        // test every parts
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].length() > 3 || Integer.parseInt(parts[i]) > 255) {
                return false;
            }
        }
        return true;
    }

}
