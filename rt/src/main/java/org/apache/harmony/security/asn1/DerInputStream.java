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
import java.io.InputStream;

import org.apache.harmony.security.internal.nls.Messages;

/**
 * Decodes ASN.1 types encoded with DER (X.690)
 * 
 * @see http://asn1.elibel.tm.fr/en/standards/index.htm
 */

public final class DerInputStream extends BerInputStream {

    public DerInputStream(byte[] encoded) throws IOException {
        super(encoded, 0, encoded.length);
    }

    public DerInputStream(byte[] encoded, int offset, int encodingLen)
            throws IOException {
        super(encoded, offset, encodingLen);
    }

    public DerInputStream(InputStream in) throws IOException {
        super(in);
    }

    /**
     * @see org.apache.harmony.security.asn1.BerInputStream#next()
     */
    public final int next() throws IOException {

        int tag = super.next();

        if (length == INDEFINIT_LENGTH) {
            throw new ASN1Exception(Messages.getString("security.105")); //$NON-NLS-1$
        }

        // FIXME add check: length encoding uses minimum number of octets

        return tag;
    }

    // mask for verifying unused bits for ASN.1 bitstring
    private static final byte[] UNUSED_BITS_MASK = new byte[] { 0x01, 0x03,
            0x07, 0x0F, 0x1F, 0x3F, 0x7F };


    /**
     * @see org.apache.harmony.security.asn1.BerInputStream#readBitString()
     */
    public void readBitString() throws IOException {

        if (tag == ASN1Constants.TAG_C_BITSTRING) {
            throw new ASN1Exception(Messages.getString("security.106", tagOffset)); //$NON-NLS-1$
        }

        super.readBitString();

        //check: unused bits values - MUST be 0
        if (length > 1
                && buffer[contentOffset] != 0
                && (buffer[offset - 1] & UNUSED_BITS_MASK[buffer[contentOffset] - 1]) != 0) {
            throw new ASN1Exception(Messages.getString("security.107", contentOffset)); //$NON-NLS-1$
        }
    }

    /**
     * @see org.apache.harmony.security.asn1.BerInputStream#readBoolean()
     */
    public void readBoolean() throws IOException {

        super.readBoolean();

        // check encoded content
        if (buffer[contentOffset] != 0 && buffer[contentOffset] != (byte) 0xFF) {
            throw new ASN1Exception(Messages.getString("security.108", contentOffset)); //$NON-NLS-1$
        }
    }

    /**
     * @see org.apache.harmony.security.asn1.BerInputStream#readOctetString()
     */
    public void readOctetString() throws IOException {

        if (tag == ASN1Constants.TAG_C_OCTETSTRING) {
            throw new ASN1Exception(
                    Messages.getString("security.109", tagOffset)); //$NON-NLS-1$
        }
        super.readOctetString();
    }

    /**
     * @see org.apache.harmony.security.asn1.BerInputStream#readSequence(org.apache.harmony.security.asn1.ASN1Sequence)
     */
    public void readSequence(ASN1Sequence sequence) throws IOException {
        //
        // According to ASN.1 DER spec. sequence MUST not include
        // any encoding which value is equal to its default value
        //
        // Verification of this assertion is not implemented
        //
        super.readSequence(sequence);
    }

    /**
     * @see org.apache.harmony.security.asn1.BerInputStream#readSetOf(org.apache.harmony.security.asn1.ASN1SetOf)
     */
    public void readSetOf(ASN1SetOf setOf) throws IOException {
        //
        // According to ASN.1 DER spec. set of MUST appear in
        // ascending order (short component are padded for comparison)
        //
        // Verification of this assertion is not implemented
        //
        super.readSetOf(setOf);
    }

    /**
     * @see org.apache.harmony.security.asn1.BerInputStream#readString()
     */
    public void readString(ASN1StringType type) throws IOException {

        if (tag == type.constrId) {
            throw new ASN1Exception(Messages.getString("security.10A", tagOffset)); //$NON-NLS-1$
        }
        super.readString(type);
    }

    /**
     * @see org.apache.harmony.security.asn1.BerInputStream#readUTCTime()
     */
    public void readUTCTime() throws IOException {

        if (tag == ASN1Constants.TAG_C_UTCTIME) {
            // It is a string type and it can be encoded as primitive or constructed.
            throw new ASN1Exception(Messages.getString("security.10B", tagOffset)); //$NON-NLS-1$
        }

        // check format: DER uses YYMMDDHHMMSS'Z' only
        if (length != ASN1UTCTime.UTC_HMS) {
            throw new ASN1Exception(Messages.getString("security.10C", tagOffset)); //$NON-NLS-1$
        }

        super.readUTCTime();
    }

    /**
     * @see org.apache.harmony.security.asn1.BerInputStream#readGeneralizedTime()
     */
    public void readGeneralizedTime() throws IOException {

        if (tag == ASN1Constants.TAG_C_GENERALIZEDTIME) {
            // It is a string type and it can be encoded as primitive or constructed.
            throw new ASN1Exception(Messages.getString("security.10D", tagOffset)); //$NON-NLS-1$
        }

        super.readGeneralizedTime();

        // FIXME makes sense only if we support all GeneralizedTime formats 
        // late check syntax: the last char MUST be Z
        //if (buffer[offset - 1] != 'Z') {
        //    throw new ASN1Exception(
        //            "ASN.1 GeneralizedTime wrongly encoded at ["
        //                    + contentOffset + ']');
        //}

        // the fractional-seconds elements, if present MUST
        // omit all trailing zeros
        // FIXME implement me
        //        if () {
        //            throw new IOException(
        //                    "DER ASN.1 GeneralizedTime wrongly encoded at ["
        //                            + contentOffset
        //                            + "]. Trailing zeros MUST be omitted");
        //        }
    }
}
