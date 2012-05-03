/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.harmony.security.x509.tsp;

import java.math.BigInteger;
import java.util.List;
import org.apache.harmony.security.asn1.ASN1BitString;
import org.apache.harmony.security.asn1.ASN1Integer;
import org.apache.harmony.security.asn1.ASN1Sequence;
import org.apache.harmony.security.asn1.ASN1SequenceOf;
import org.apache.harmony.security.asn1.ASN1StringType;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.BerInputStream;
import org.apache.harmony.security.asn1.BitString;


/**
 * As defined in Time-Stamp Protocol (TSP)
 * (http://www.ietf.org/rfc/rfc3161.txt)
 *
 * PKIStatusInfo ::= SEQUENCE {
 *    status PKIStatus,
 *    statusString PKIFreeText OPTIONAL,
 *    failInfo PKIFailureInfo OPTIONAL
 * }
 *
 */
public class PKIStatusInfo {

    private final PKIStatus status;

    private final List statusString;

    private final PKIFailureInfo failInfo;

    public PKIStatusInfo(PKIStatus pKIStatus, List statusString,
            PKIFailureInfo failInfo) {
        this.status = pKIStatus;
        this.statusString = statusString;
        this.failInfo = failInfo;
    }

    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("-- PKIStatusInfo:");
        res.append("\nPKIStatus : ");
        res.append(status);
        res.append("\nstatusString:  ");
        res.append(statusString);
        res.append("\nfailInfo:  ");
        res.append(failInfo);
        res.append("\n-- PKIStatusInfo End\n");
        return res.toString();
    }

    /**
     * @return Returns the failInfo.
     */
    public PKIFailureInfo getFailInfo() {
        return failInfo;
    }

    /**
     * @return Returns the pKIStatus.
     */
    public PKIStatus getStatus() {
        return status;
    }

    /**
     * @return Returns the statusString.
     */
    public List getStatusString() {
        return statusString;
    }

    public static final ASN1Sequence ASN1 = new ASN1Sequence(new ASN1Type[] {
        ASN1Integer.getInstance(),                      // status
        new ASN1SequenceOf(ASN1StringType.UTF8STRING),  // statusString
        ASN1BitString.getInstance() }) {                // failInfo
        {
            setOptional(1);
            setOptional(2);
        }

        protected void getValues(Object object, Object[] values) {
            PKIStatusInfo psi = (PKIStatusInfo) object;
            values[0] = BigInteger.valueOf(psi.status.getStatus())
                    .toByteArray();
            values[1] = psi.statusString;
            if (psi.failInfo != null) {
                // set the needed bit in the bit string
                boolean[] failInfoBoolArray = new boolean[PKIFailureInfo
                        .getMaxValue()];
                failInfoBoolArray[psi.failInfo.getValue()] = true;
                values[2] = new BitString(failInfoBoolArray);
            } else {
                values[2] = null;
            }
        }

        protected Object getDecodedObject(BerInputStream in) {
            Object[] values = (Object[]) in.content;

            int failInfoValue = -1;
            if (values[2] != null) {
                boolean[] failInfoBoolArray = ((BitString) values[2]).toBooleanArray();
                for (int i = 0; i < failInfoBoolArray.length; i++) {
                    if (failInfoBoolArray[i]) {
                        failInfoValue = i;
                        break;
                    }
                }
            }
            return new PKIStatusInfo(
                    PKIStatus.getInstance(ASN1Integer.toIntValue(values[0])),
                    (List)values[1],
                    PKIFailureInfo.getInstance(failInfoValue));
        }
    };
}

