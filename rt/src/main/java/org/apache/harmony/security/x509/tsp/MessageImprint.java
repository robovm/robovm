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

import org.apache.harmony.security.asn1.ASN1OctetString;
import org.apache.harmony.security.asn1.ASN1Sequence;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.BerInputStream;
import org.apache.harmony.security.x509.AlgorithmIdentifier;

/**
 * As defined in Time-Stamp Protocol (TSP)
 * (http://www.ietf.org/rfc/rfc3161.txt)
 * 
 * MessageImprint ::= SEQUENCE  {
 *      hashAlgorithm                AlgorithmIdentifier,
 *      hashedMessage                OCTET STRING  
 * }
 * 
 */
public class MessageImprint {
    private final AlgorithmIdentifier algId;
    private final byte [] hashedMessage;
    
    public MessageImprint(AlgorithmIdentifier algId, byte [] hashedMessage){
        this.algId = algId;
        this.hashedMessage = hashedMessage;
    }
    
    public static final ASN1Sequence ASN1 = new ASN1Sequence(new ASN1Type[] {
        AlgorithmIdentifier.ASN1,
        ASN1OctetString.getInstance()}) {
        
        protected Object getDecodedObject(BerInputStream in) {
            Object[] values = (Object[]) in.content;
            return new MessageImprint(
                    (AlgorithmIdentifier)values[0],
                    (byte[])values[1]);
        }
        
        protected void getValues(Object object, Object[] values) {
            MessageImprint mi = (MessageImprint) object;
            values[0] = mi.algId;
            values[1] = mi.hashedMessage;
        }
    };
}

