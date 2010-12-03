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

package org.apache.harmony.security.x501;

import org.apache.harmony.security.asn1.ASN1OpenType;
import org.apache.harmony.security.asn1.ASN1Sequence;
import org.apache.harmony.security.asn1.ASN1SetOf;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.InformationObjectSet;


/**
 * X.501 Attributes
 * 
 * This is a draft class for Module InformationFramework (X.501).
 * 
 * @see http://www.itu.int/ITU-T/asn1/database/itu-t/x/x501/2001/InformationFramework.html 
 */

public class Attributes {

    
    /**
     * The class corresponds to following ASN.1 type:
     * 
     * Attribute ::= SEQUENCE {
     *     type  AttributeType,
     *     values SET SIZE (0..MAX) OF AttributeValue }
     * 
     * AttributeType ::= OBJECT IDENTIFIER
     * 
     * AttributeValue ::= ANY DEFINED BY AttributeType
     * 
     */
    public static ASN1Sequence getASN1(InformationObjectSet set) {
        ASN1OpenType.Id id = new ASN1OpenType.Id();
        ASN1OpenType any = new ASN1OpenType(id, set);

        return new ASN1Sequence(new ASN1Type[] { id, new ASN1SetOf(any) });
    }
}