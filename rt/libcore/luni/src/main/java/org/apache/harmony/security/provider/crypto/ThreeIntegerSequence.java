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


package org.apache.harmony.security.provider.crypto;

import org.apache.harmony.security.asn1.ASN1Integer;
import org.apache.harmony.security.asn1.ASN1Sequence;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.BerInputStream;


/**
 * The auxiliary class providing means to process ASN1Sequence of three Integers.
 * Such sequences are parts of ASN1 encoded formats for DSA private and public keys.
 */
class ThreeIntegerSequence {

    byte[] p, q, g;

    private byte[] encoding;

    ThreeIntegerSequence(byte[] p, byte[] q, byte[] g) {

        this.p = p;
        this.q = q;
        this.g = g;
        encoding = null;
    }

    public byte[] getEncoded() {
        if (encoding == null) {
            encoding = ASN1.encode(this);
        }
        return encoding;
    }

    public static final ASN1Sequence ASN1 = new ASN1Sequence(new ASN1Type[] {
            ASN1Integer.getInstance(), ASN1Integer.getInstance(),
            ASN1Integer.getInstance() }) {

        protected Object getDecodedObject(BerInputStream in) {

            Object[] values = (Object[]) in.content;

            return new ThreeIntegerSequence((byte[]) values[0],
                    (byte[]) values[1], (byte[]) values[2]);
        }

        protected void getValues(Object object, Object[] values) {

            ThreeIntegerSequence mySeq = (ThreeIntegerSequence) object;

            values[0] = mySeq.p;
            values[1] = mySeq.q;
            values[2] = mySeq.g;
        }
    };
}
