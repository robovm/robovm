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
* @author Stepan M. Mishura
*/

package org.apache.harmony.security.asn1;

import java.util.Arrays;

import org.apache.harmony.security.internal.nls.Messages;

/**
 * Instance of this class represents ObjectIdentifier (OID).
 * 
 * According to X.690:
 * OID is represented as a sequence of subidentifier.
 * Each subidentifier is represented as non negative integer value.
 * There are at least 2 subidentifiers in the sequence.
 * 
 * Valid values for first subidentifier are 0, 1 and 2.
 * If the first subidentifier has 0 or 1 value the second
 * subidentifier must be less then 40.
 * 
 * @see http://asn1.elibel.tm.fr/en/standards/index.htm
 */

public final class ObjectIdentifier {

    // OID as array of integers
    private final int[] oid;

    // OID as string
    private String soid;

    /**
     * Creates ObjectIdentifier(OID) from array of integers.
     * 
     * @param oid - array of integers
     * @throws IllegalArgumentException - if oid is invalid or null
     */
    public ObjectIdentifier(int[] oid) {
        validate(oid);
        this.oid = oid;
    }

    /**
     * Creates ObjectIdentifier(OID) from string representation.
     * 
     * @param strOid - oid string
     * @throws IllegalArgumentException - if oid string is invalid or null
     */
    public ObjectIdentifier(String strOid) {
        this.oid = toIntArray(strOid);
        this.soid = strOid;
    }

    /**
     * Returns array of integers.
     * 
     * @return array of integers
     */
    public int[] getOid() {
        return oid;
    }

    /**
     * Compares object with OID for equality.
     * 
     * @return true if object is ObjectIdentifier and it has the same
     *         representation as array of integers, otherwise false
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        return Arrays.equals(oid, ((ObjectIdentifier) o).oid);
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        if (soid == null) {
            soid = toString(oid);
        }
        return soid;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        // FIXME change me to Arrays.hashCode(int[])
        int intHash = 0;
        for (int i = 0; i < oid.length && i < 4; i++) {
            intHash += oid[i] << (8 * i); //TODO what about to find better one?
        }
        return intHash & 0x7FFFFFFF; // only positive
    }

    /**
     * Validates ObjectIdentifier (OID).
     * 
     * @param oid - oid as array of integers
     * @throws IllegalArgumentException - if oid is invalid or null
     */
    public static void validate(int[] oid) {

        if (oid == null) {
            throw new IllegalArgumentException(Messages.getString("security.98")); //$NON-NLS-1$
        }

        if (oid.length < 2) {
            throw new IllegalArgumentException(
                    Messages.getString("security.99")); //$NON-NLS-1$
        }

        if (oid[0] > 2) {
            throw new IllegalArgumentException(
                    Messages.getString("security.9A")); //$NON-NLS-1$
        } else if (oid[0] != 2 && oid[1] > 39) {
            throw new IllegalArgumentException(
                    Messages.getString("security.9B")); //$NON-NLS-1$
        }

        for (int i = 0; i < oid.length; i++) {
            if (oid[i] < 0) {
                throw new IllegalArgumentException(
                        Messages.getString("security.9C")); //$NON-NLS-1$
            }
        }
    }

    // FIXME: implement me
    //    /**
    //     * Validates ObjectIdentifier (OID).
    //     * 
    //     * @param oid - oid as string
    //     * @throws IllegalArgumentException - if oid string  is invalid or null
    //     */
    //    public static void validate(String oid) {
    //
    //        if (oid == null) {
    //            throw new NullPointerException();
    //        }
    //
    //        int length = oid.length();
    //        if (length < 3 || oid.charAt(1) != '.') {
    //            throw new IllegalArgumentException("Invalid oid string");
    //        }
    //
    //        int pos = 2;
    //        int subidentifier = 0;
    //        switch (oid.charAt(0)) {
    //        case '0':
    //        case '1':
    //            for (char c = oid.charAt(pos);;) {
    //                if (c < '0' || c > '9') {
    //                    throw new IllegalArgumentException("Invalid oid string");
    //                } else {
    //                    subidentifier = subidentifier * 10 + c - '0';
    //                }
    //
    //                pos++;
    //                if (pos == length) {
    //                    break;
    //                }
    //
    //                c = oid.charAt(pos);
    //                if (c == '.') {
    //                    pos++;
    //                    if (pos == length) {
    //                        throw new IllegalArgumentException("Invalid oid string");
    //                    }
    //                    break;
    //                }
    //            }
    //
    //            if (subidentifier > 39) {
    //                throw new IllegalArgumentException(
    //                        "If the first subidentifier has 0 or 1 value the second "
    //                                + "subidentifier value MUST be less then 40.");
    //            }
    //            break;
    //        case '2':
    //            break;
    //        default:
    //            throw new IllegalArgumentException(
    //                    "Valid values for first subidentifier are 0, 1 and 2");
    //        }
    //
    //        if (pos == length) {
    //            return;
    //        }
    //
    //        for (char c = oid.charAt(pos);;) {
    //            if (c < '0' || c > '9') {
    //                throw new IllegalArgumentException("Invalid oid string");
    //            }
    //
    //            pos++;
    //            if (pos == length) {
    //                return;
    //            }
    //
    //            c = oid.charAt(pos);
    //            if (c == '.') {
    //                pos++;
    //                if (pos == length) {
    //                    throw new IllegalArgumentException("Invalid oid string");
    //                }
    //            }
    //        }
    //    }

    /**
     * Returns string representation of OID.
     * 
     * Note: it is supposed that passed array of integers
     * contains valid OID value, so no checks are performed. 
     *
     * @param oid - oid as array of integers
     * @return oid string representation
     */
    public static String toString(int[] oid) {
        StringBuilder sb = new StringBuilder(3 * oid.length);

        for (int i = 0; i < oid.length - 1; ++i) {
            sb.append(oid[i]);
            sb.append('.');
        }
        sb.append(oid[oid.length - 1]);
        return sb.toString();
    }

    /**
     * Gets ObjectIdentifier (OID) from string representation.
     * 
     * String representation is defined by the following syntax:
     *     OID = subidentifier 1*("." subidentifier)
     *     subidentifier = 1*(digit)
     * 
     * @param oidString -  string representation of OID
     * @return - oid as array of integers
     * @throws IllegalArgumentException - if oid string is invalid or null
     */
    public static int[] toIntArray(String str) {

        if (str == null) {
            throw new IllegalArgumentException(
                    Messages.getString("security.9D")); //$NON-NLS-1$
        }

        int length = str.length();
        if (length == 0) {
            throw new IllegalArgumentException(Messages.getString("security.9E")); //$NON-NLS-1$
        }

        int count = 1; // number of subidentifiers
        boolean wasDot = true; // indicates whether char before was dot or not.
        char c; // current char
        for (int i = 0; i < length; i++) {
            c = str.charAt(i);
            if (c == '.') {
                if (wasDot) {
                    throw new IllegalArgumentException(Messages.getString("security.9E")); //$NON-NLS-1$
                }
                wasDot = true;
                count++;
            } else if (c >= '0' && c <= '9') {
                wasDot = false;
            } else {
                throw new IllegalArgumentException(Messages.getString("security.9E")); //$NON-NLS-1$
            }
        }

        if (wasDot) {
            // the last char is dot
            throw new IllegalArgumentException(Messages.getString("security.9E")); //$NON-NLS-1$
        }

        if (count < 2) {
            throw new IllegalArgumentException(
                    Messages.getString("security.99")); //$NON-NLS-1$
        }

        int[] oid = new int[count];
        for (int i = 0, j = 0; i < length; i++) {
            c = str.charAt(i);
            if (c == '.') {
                j++;
            } else {
                oid[j] = oid[j] * 10 + c - 48; // '0' = 48
            }
        }

        if (oid[0] > 2) {
            throw new IllegalArgumentException(
                    Messages.getString("security.9A")); //$NON-NLS-1$
        } else if (oid[0] != 2 && oid[1] > 39) {
            throw new IllegalArgumentException(
                    Messages.getString("security.9B")); //$NON-NLS-1$
        }

        return oid;
    }
}