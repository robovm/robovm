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

import org.apache.harmony.security.internal.nls.Messages;


/**
 * This class represents explicitly tagged ASN.1 type.
 * 
 * @see http://asn1.elibel.tm.fr/en/standards/index.htm
 */

public final class ASN1Explicit extends ASN1Constructured {

    /**
     * Tagged type
     */
    public final ASN1Type type;

    /**
     * Constructs explicitly tagged ASN.1 type
     * with context-specific tag class and specified tag number. 
     * 
     * @param tagNumber - ASN.1 tag number
     * @param type - ASN.1 type to be tagged
     * @throws IllegalArgumentException - if tagNumber is invalid
     */
    public ASN1Explicit(int tagNumber, ASN1Type type) {
        this(CLASS_CONTEXTSPECIFIC, tagNumber, type);
    }

    /**
     * Constructs explicitly tagged ASN.1 type.
     * 
     * @param tagClass - ASN.1 tag class.
     * @param tagNumber - ASN.1 tag number
     * @param type - ASN.1 type to be tagged
     * @throws IllegalArgumentException - if tagClass or tagNumber is invalid
     */
    public ASN1Explicit(int tagClass, int tagNumber, ASN1Type type) {
        super(tagClass, tagNumber);

        this.type = type;
    }

    //
    //
    // Decode
    //
    //

    public Object decode(BerInputStream in) throws IOException {
        if (constrId != in.tag) {
            throw new ASN1Exception(
                    Messages.getString("security.13F", //$NON-NLS-1$
                    new Object[] { in.tagOffset, Integer.toHexString(constrId),
                            Integer.toHexString(in.tag) }));
        }
        in.next();

        in.content = type.decode(in);

        if (in.isVerify) {
            return null;
        }
        return getDecodedObject(in);
    }

    //
    //
    // Encode
    //
    //

    public void encodeContent(BerOutputStream out) {
        out.encodeExplicit(this);
    }

    public void setEncodingContent(BerOutputStream out) {
        out.getExplicitLength(this);
    }

    public String toString() {
        //FIXME fix performance
        return super.toString() + " for type " + type; //$NON-NLS-1$
    }
}