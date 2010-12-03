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

import java.io.IOException;


/**
 * This class represents ASN.1 Enumerated type.
 * 
 * @see http://asn1.elibel.tm.fr/en/standards/index.htm
 */

public class ASN1Enumerated extends ASN1Primitive {

    // default implementation
    private static final ASN1Enumerated ASN1 = new ASN1Enumerated();

    /**
     * Constructs ASN.1 Enumerated type
     * 
     * The constructor is provided for inheritance purposes
     * when there is a need to create a custom ASN.1 Enumerated type.
     * To get a default implementation it is recommended to use
     * getInstance() method.
     */
    public ASN1Enumerated() {
        super(TAG_ENUM);
    }

    /**
     * Returns ASN.1 Enumerated type default implementation
     * 
     * The default implementation works with encoding
     * that is represented as byte array.
     *
     * @return ASN.1 Enumerated type default implementation
     */
    public static ASN1Enumerated getInstance() {
        return ASN1;
    }

    //
    //
    // Decode
    //
    //

    public Object decode(BerInputStream in) throws IOException {
        in.readEnumerated();

        if (in.isVerify) {
            return null;
        }
        return getDecodedObject(in);
    }

    /**
     * Extracts array of bytes from BER input stream.
     *
     * @param in - BER input stream
     * @return array of bytes
     */
    public Object getDecodedObject(BerInputStream in) throws IOException {
        byte[] bytesEncoded = new byte[in.length];
        System.arraycopy(in.buffer, in.contentOffset, bytesEncoded, 0,
                in.length);
        return bytesEncoded;
    }

    //
    //
    // Encode
    //
    //

    public void encodeContent(BerOutputStream out) {
        out.encodeInteger();
    }

    public void setEncodingContent(BerOutputStream out) {
        out.length = ((byte[]) out.content).length;
    }
}