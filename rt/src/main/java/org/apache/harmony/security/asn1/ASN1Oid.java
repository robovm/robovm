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
* @author Vladimir N. Molotkov, Stepan M. Mishura
*/

package org.apache.harmony.security.asn1;

import java.io.IOException;


/**
 * This class represents ASN.1 Object Identifier type.
 * 
 * @see http://asn1.elibel.tm.fr/en/standards/index.htm
 */

public class ASN1Oid extends ASN1Primitive {

    // default implementation
    private static final ASN1Oid ASN1 = new ASN1Oid();

    /**
     * Constructs ASN.1 Object Identifier type
     * 
     * The constructor is provided for inheritance purposes
     * when there is a need to create a custom ASN.1 Object Identifier type.
     * To get a default implementation it is recommended to use
     * getInstance() method.
     */
    public ASN1Oid() {
        super(TAG_OID);
    }

    /**
     * Returns ASN.1 Object Identifier type default implementation
     * 
     * The default implementation works with encoding
     * that is represented as array of integers.
     *
     * @return ASN.1 Object Identifier type default implementation
     */
    public static ASN1Oid getInstance() {
        return ASN1;
    }

    //
    //
    // Decode
    //
    //

    public Object decode(BerInputStream in) throws IOException {
        in.readOID();

        if (in.isVerify) {
            return null;
        }
        return getDecodedObject(in);
    }

    /**
     * Extracts array of integers from BER input stream.
     *
     * @param in - BER input stream
     * @return array of integers
     */
    public Object getDecodedObject(BerInputStream in) throws IOException {
        // Allocate and decode
        int oidElement = in.oidElement;
        int[] oid = new int[oidElement];
        for (int id = 1, i = 0; id < oid.length; id++, i++) {
            int octet = in.buffer[in.contentOffset + i];
            oidElement = octet & 0x7F;
            while ((octet & 0x80) != 0) {
                i++;
                octet = in.buffer[in.contentOffset + i];
                oidElement = oidElement << 7 | (octet & 0x7f);
            }
            oid[id] = oidElement;
        }
        // Special handling for the first packed OID element
        if (oid[1] > 79) {
            oid[0] = 2;
            oid[1] = oid[1] - 80;
        } else {
            oid[0] = oid[1] / 40;
            oid[1] = oid[1] % 40;
        }
        return oid;
    }

    //
    //
    // Encode
    //
    //

    public void encodeContent(BerOutputStream out) {
        out.encodeOID();
    }

    public void setEncodingContent(BerOutputStream out) {
        int[] oid = (int[]) out.content;

        int length = 0;

        // first subidentifier
        int elem = oid[0] * 40 + oid[1];
        if (elem == 0) {
            length = 1;
        } else {
            for (; elem > 0; elem = elem >> 7) {
                length++;
            }
        }

        // the rest of subidentifiers
        for (int i = 2; i < oid.length; i++) {
            if (oid[i] == 0) {
                length++;
                continue;
            }
            for (elem = oid[i]; elem > 0; elem = elem >> 7) {
                length++;
            }
        }
        out.length = length;
    }

    //
    //
    // OID encoder/decoder for mapping to string 
    //
    //

    private final static ASN1Oid STRING_OID = new ASN1Oid() {

        public Object getDecodedObject(BerInputStream in) throws IOException {

            StringBuilder buf = new StringBuilder();

            int element;

            //Special handling for the first packed OID element
            int octet = in.buffer[in.contentOffset];
            element = octet & 0x7F;

            int index = 0;
            while ((octet & 0x80) != 0) {
                index++;
                octet = in.buffer[in.contentOffset + index];
                element = element << 7 | (octet & 0x7F);
            }

            if (element > 79) {
                buf.append('2');
                buf.append('.');
                buf.append(element - 80);
            } else {
                buf.append(element / 40);
                buf.append('.');
                buf.append(element % 40);
            }

            // the rest of subidentifiers
            for (int j = 2; j < in.oidElement; j++) {
                buf.append('.');

                index++;
                octet = in.buffer[in.contentOffset + index];
                element = octet & 0x7F;

                while ((octet & 0x80) != 0) {
                    index++;
                    octet = in.buffer[in.contentOffset + index];
                    element = element << 7 | (octet & 0x7f);
                }

                buf.append(element);
            }

            return buf.toString();
        }

        public void setEncodingContent(BerOutputStream out) {

            //FIXME this is a stub for a while
            out.content = ObjectIdentifier.toIntArray((String) out.content);

            super.setEncodingContent(out);
        }
    };

    /**
     * Returns ASN.1 Object Identifier type implementation
     * 
     * This implementation works with encoding
     * that is mapped to java.lang.String object.
     *
     * @return ASN.1 Object Identifier type implementation
     */
    public static ASN1Oid getInstanceForString() {
        return STRING_OID;
    }
}
