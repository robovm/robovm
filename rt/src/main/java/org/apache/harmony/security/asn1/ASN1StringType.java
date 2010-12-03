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
import java.io.UnsupportedEncodingException;


/**
 * This class is the super class for all string ASN.1 types
 * 
 * @see http://asn1.elibel.tm.fr/en/standards/index.htm
 */

public abstract class ASN1StringType extends ASN1Type {

    // TODO: what about defining them as separate classes?  
    // TODO: check decoded/encoded characters
    public static final ASN1StringType BMPSTRING = new ASN1StringType(
            TAG_BMPSTRING) {
    };

    public static final ASN1StringType IA5STRING = new ASN1StringType(
            TAG_IA5STRING) {
    };

    public static final ASN1StringType GENERALSTRING = new ASN1StringType(
            TAG_GENERALSTRING) {
    };

    public static final ASN1StringType PRINTABLESTRING = new ASN1StringType(
            TAG_PRINTABLESTRING) {
    };

    public static final ASN1StringType TELETEXSTRING = new ASN1StringType(
            TAG_TELETEXSTRING) {
    };

    public static final ASN1StringType UNIVERSALSTRING = new ASN1StringType(
            TAG_UNIVERSALSTRING) {
    };

    public static final ASN1StringType UTF8STRING = new ASN1StringType(
            TAG_UTF8STRING) {

        public Object getDecodedObject(BerInputStream in) throws IOException {
            return new String(in.buffer, in.contentOffset, in.length, "UTF-8"); //$NON-NLS-1$
        }

        public void setEncodingContent(BerOutputStream out) {

            try {
                byte[] bytes = ((String) out.content).getBytes("UTF-8"); //$NON-NLS-1$
                out.content = bytes;
                out.length = bytes.length;
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    };

    public ASN1StringType(int tagNumber) {
        super(tagNumber);
    }

    //
    //
    // Decode
    //
    //

    /**
     * Tests provided identifier.
     * 
     * @param identifier -
     *            identifier to be verified
     * @return - true if identifier correspond to primitive or constructed
     *         identifier of this ASN.1 string type, otherwise false
     */
    public final boolean checkTag(int identifier) {
        return this.id == identifier || this.constrId == identifier;
    }

    public Object decode(BerInputStream in) throws IOException {

        in.readString(this);
        
        if (in.isVerify) {
            return null;
        }
        return getDecodedObject(in);
    }

    /**
     * Extracts String object from BER input stream.
     *
     * @param in - BER input stream
     * @return java.land.String object
     */
    public Object getDecodedObject(BerInputStream in) throws IOException {
        /* To ensure we get the correct encoding on non-ASCII platforms, specify
           that we wish to convert from ASCII to the default platform encoding */
        return new String(in.buffer, in.contentOffset, in.length, "ISO-8859-1");
    }

    //
    //
    // Encode
    //
    //
    
    public void encodeASN(BerOutputStream out) {
        out.encodeTag(id);
        encodeContent(out);
    }

    public void encodeContent(BerOutputStream out) {
        out.encodeString();
    }

    public void setEncodingContent(BerOutputStream out) {
        try {
            byte[] bytes = ((String) out.content).getBytes("UTF-8"); //$NON-NLS-1$
            out.content = bytes;
            out.length = bytes.length;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}



