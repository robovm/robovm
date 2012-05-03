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

package java.security.cert;

import java.io.IOException;
import org.apache.harmony.security.asn1.ObjectIdentifier;
import org.apache.harmony.security.utils.Array;


/**
 * This class implements a policy qualifier as defined by the ASN.1
 * {@code PolicyQualifierInfo} structure.
 */
public class PolicyQualifierInfo {
    // This PolicyQualifierInfo DER encoding
    private final byte[] encoded;
    // This PolicyQualifierInfo policy qualifier id -
    // OID represented as String containing non-negative integers
    // separated by periods
    private final String policyQualifierId;
    // DER encoding of the policy qualifier - part of encoded
    private final byte[] policyQualifier;

    /**
     * Creates a new {@code PolicyQualifierInfo} from the specified encoded
     * form.
     *
     * @param encoded
     *            the DER encoded policy qualifier.
     * @throws IOException
     *             the policy qualifier cannot be decoded.
     */
    public PolicyQualifierInfo(byte[] encoded) throws IOException {
        if (encoded == null) {
            throw new NullPointerException("encoded == null");
        }
        if (encoded.length == 0) {
            throw new IOException("encoded.length == 0");
        }
        this.encoded = new byte[encoded.length];
        System.arraycopy(encoded, 0, this.encoded, 0, this.encoded.length);

        // DER Decoding:
        Object[] decoded = (Object[]) org.apache.harmony.security.x509.PolicyQualifierInfo.ASN1
                .decode(this.encoded);
        policyQualifierId = ObjectIdentifier.toString((int[]) decoded[0]);
        policyQualifier = (byte[]) decoded[1];
    }

    /**
     * Returns a ASN.1 DER encoded copy of policy qualifier info.
     *
     * @return a ASN.1 DER encoded copy of policy qualifier info.
     */
    public final byte[] getEncoded() {
        byte[] ret = new byte[encoded.length];
        System.arraycopy(encoded, 0, ret, 0, encoded.length);
        return ret;
    }

    /**
     * Returns the identifier (an OID) of this policy qualifier info.
     *
     * @return the identifier of this policy qualifier info.
     */
    public final String getPolicyQualifierId() {
        return policyQualifierId;
    }

    /**
     * Returns a ASN.1 DER encoded copy of the qualifier of this policy
     * qualifier info.
     *
     * @return a ASN.1 DER encoded copy of the qualifier of this policy
     *         qualifier info.
     */
    public final byte[] getPolicyQualifier() {
        if (policyQualifier == null) {
            return null;
        }
        byte[] ret = new byte[policyQualifier.length];
        System.arraycopy(policyQualifier, 0, ret, 0, policyQualifier.length);
        return ret;
    }

    /**
     * Returns a string representation of this {@code PolicyQualifierInfo}
     * instance.
     *
     * @return a string representation of this {@code PolicyQualifierInfo}
     *         instance.
     */
    public String toString() {
        StringBuilder sb =
            new StringBuilder("PolicyQualifierInfo: [\npolicyQualifierId: ");
        sb.append(policyQualifierId);
        sb.append("\npolicyQualifier: \n");
        sb.append(Array.toString(policyQualifier, " "));
        sb.append("]");
        return sb.toString();
    }
}
