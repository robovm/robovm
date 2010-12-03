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
* @author Alexander Y. Kleymenov
*/

package org.apache.harmony.security.x509;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.harmony.security.asn1.ASN1SequenceOf;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.BerInputStream;


/**
 * The class encapsulates the ASN.1 DER encoding/decoding work 
 * with the GeneralNames structure which is a part of X.509 certificate
 * (as specified in RFC 3280 -
 *  Internet X.509 Public Key Infrastructure.
 *  Certificate and Certificate Revocation List (CRL) Profile.
 *  http://www.ietf.org/rfc/rfc3280.txt):
 *
 * 
 * <pre>
 *   GeneralNames ::= SEQUENCE SIZE (1..MAX) OF GeneralName
 * </pre>
 * 
 * @see org.apache.harmony.security.x509.NameConstraints
 * @see org.apache.harmony.security.x509.GeneralSubtree
 */
public class GeneralNames {

    // the values of GeneralName
    private List generalNames;
    // the ASN.1 encoded form of GeneralNames
    private byte[] encoding;
    
    /**
     * Constructs an object representing the value of GeneralNames.
     */
    public GeneralNames() {
        generalNames = new ArrayList();
    }
    
    /**
     * TODO
     * @param   generalNames:   List
     */
    public GeneralNames(List generalNames) {
        this.generalNames = generalNames;
    }
    
    // 
    // TODO
    // @param   generalNames:   List
    // @param   encoding:   byte[]
    // 
    private GeneralNames(List generalNames, byte[] encoding) {
        this.generalNames = generalNames;
        this.encoding = encoding;
    }

    /**
     * Returns the list of values.
     * @return  names
     */
    public List getNames() {
        if ((generalNames == null) || (generalNames.size() == 0)) {
            return null;
        }
        return new ArrayList(generalNames);
    }

    /**
     * Returns the collection of pairs: (Integer (tag), Object (name value))*
     * @return the collection of pairs: (Integer (tag), Object (name value))*
     */
    public List getPairsList() {
        ArrayList result = new ArrayList();
        if (generalNames == null) {
            return result;
        }
        Iterator it = generalNames.iterator();
        while (it.hasNext()) {
            result.add(((GeneralName) it.next()).getAsList());
        }
        return result;
    }

    /**
     * TODO
     * @param   name:   GeneralName
     * @return
     */
    public void addName(GeneralName name) {
        encoding = null;
        if (generalNames == null) {
            generalNames = new ArrayList();
        }
        generalNames.add(name);
    }

    /* *
     * TODO
     * @param   name:   GeneralName
     * @return
     * 
    public GeneralName getNameByTag(int tag) {
        encoding = null;
        if ((generalNames == null) || (generalNames.size() == 0)) {
            return null;
        }
        for (int i=0; i<generalNames.size(); i++) {
            if (((GeneralName) generalName.get(i)).getTag() == tag) {
            }
        }
        generalNames.add(name);
    }
     */

    /**
     * Returns ASN.1 encoded form of this X.509 GeneralNames value.
     * @return a byte array containing ASN.1 encode form.
     */
    public byte[] getEncoded() {
        if (encoding == null) {
            encoding = ASN1.encode(this);
        }
        return encoding;
    }

    /**
     * Places the string representation of extension value
     * into the StringBuffer object.
     */
    public void dumpValue(StringBuffer buffer, String prefix) {
        if (generalNames == null) {
            return;
        }
        for (Iterator it=generalNames.iterator(); it.hasNext();) {
            buffer.append(prefix);
            buffer.append(it.next());
            buffer.append('\n');
        }
    }

    /**
     * ASN.1 DER X.509 GeneralNames encoder/decoder class.
     */
    public static final ASN1Type ASN1 = new ASN1SequenceOf(GeneralName.ASN1) {

        public Object getDecodedObject(BerInputStream in) {
            return new GeneralNames((List)in.content, in.getEncoded());
        }

        public Collection getValues(Object object) {
            GeneralNames gns = (GeneralNames) object;
            return gns.generalNames;
        }
    };
}
