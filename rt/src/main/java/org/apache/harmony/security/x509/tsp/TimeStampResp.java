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

import org.apache.harmony.security.asn1.ASN1Sequence;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.BerInputStream;
import org.apache.harmony.security.pkcs7.ContentInfo;

/**
 * As defined in Time-Stamp Protocol (TSP)
 * (http://www.ietf.org/rfc/rfc3161.txt)
 *
 * TimeStampResp ::= SEQUENCE {
 *    status PKIStatusInfo,
 *    timeStampToken TimeStampToken OPTIONAL
 * }
 *
 */
public class TimeStampResp {

    private final PKIStatusInfo status;

    private final ContentInfo timeStampToken;

    public TimeStampResp(PKIStatusInfo status, ContentInfo timeStampToken) {
        this.status = status;
        this.timeStampToken = timeStampToken;
    }

    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("-- TimeStampResp:");
        res.append("\nstatus:  ");
        res.append(status);
        res.append("\ntimeStampToken:  ");
        res.append(timeStampToken);
        res.append("\n-- TimeStampResp End\n");
        return res.toString();
    }

    /**
     * @return Returns the status.
     */
    public PKIStatusInfo getStatus() {
        return status;
    }

    /**
     * @return Returns the timeStampToken.
     */
    public ContentInfo getTimeStampToken() {
        return timeStampToken;
    }

    public static final ASN1Sequence ASN1 = new ASN1Sequence(new ASN1Type[] {
            PKIStatusInfo.ASN1,     // status
            ContentInfo.ASN1}) {    // timeStampToken

        {
            setOptional(1);
        }

        protected Object getDecodedObject(BerInputStream in) {
            Object[] values = (Object[]) in.content;
            return new TimeStampResp(
                    (PKIStatusInfo) values[0],
                    (ContentInfo) values[1]);
        }

        protected void getValues(Object object, Object[] values) {
            TimeStampResp resp = (TimeStampResp) object;

            values[0] = resp.status;
            values[1] = resp.timeStampToken;
        }
    };
}

