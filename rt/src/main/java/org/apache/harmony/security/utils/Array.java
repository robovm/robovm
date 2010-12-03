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

/**
* @author Vladimir N. Molotkov
*/

package org.apache.harmony.security.utils;


/**
 * Utility class for arrays
 * 
 */
public class Array {

    // No instances of this class
    private Array() {
    }

    /**
     * Represents <code>array</code> as <code>String</code>
     * for printing. Array length can be up to 32767
     *
     * @param array to be represented as <code>String</code>
     * 
     * @return <code>String</code> representation of the <code>array</code>
     */
    public static String toString(byte[] array, String prefix) {
        // Prefixes to be added to the offset values
        // in <code>String toString(byte[], String)</code> method
        final String[] offsetPrefix = {
                "", //$NON-NLS-1$
                "000", //$NON-NLS-1$
                "00", //$NON-NLS-1$
                "0", //$NON-NLS-1$
                "" //$NON-NLS-1$
        };
        StringBuilder sb = new StringBuilder();
        StringBuilder charForm = new StringBuilder();
        int i=0;
        for (i=0; i<array.length; i++) {
            if (i%16 == 0) {
                sb.append(prefix);
                // put offset
                String offset = Integer.toHexString(i);
                sb.append(offsetPrefix[offset.length()]);
                sb.append(offset);
                // clear char form for new line
                charForm.delete(0, charForm.length());
            }
            // put delimiter
            sb.append(' ');
            // put current byte
            int currentByte = (0xff & array[i]);
            String hexTail = Integer.toHexString(currentByte);
            if (hexTail.length() == 1) {
                sb.append('0');
            }
            sb.append(hexTail);
            // form character representation part
            char currentChar = (char)(currentByte & 0xffff);
            // FIXME if needed (how to distinguish PRINTABLE chars?)
            charForm.append(
                    (Character.isISOControl(currentChar) ? '.' : currentChar));
            // Add additional delimiter for each 8 values
            if ((i+1)%8 == 0) {
                sb.append(' ');
            }
            // Add character representation for each line
            if ((i+1)%16 == 0) {
                sb.append(' ');
                sb.append(charForm.toString());
                sb.append('\n');
            }
        }
        // form last line
        if (i%16 != 0) {
            int ws2add = 16 - i%16;
            for (int j=0; j<ws2add; j++) {
                sb.append("   "); //$NON-NLS-1$
            }
            if (ws2add > 8) {
                sb.append(' ');
            }
            sb.append("  "); //$NON-NLS-1$
            sb.append(charForm.toString());
            sb.append('\n');
        }
        return sb.toString();
    }
}
