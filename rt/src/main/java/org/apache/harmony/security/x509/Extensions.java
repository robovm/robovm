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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.security.auth.x500.X500Principal;

import org.apache.harmony.security.asn1.ASN1SequenceOf;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.BerInputStream;

/**
 * The class encapsulates the ASN.1 DER encoding/decoding work 
 * with the Extensions part of X.509 certificate
 * (as specified in RFC 3280 -
 *  Internet X.509 Public Key Infrastructure.
 *  Certificate and Certificate Revocation List (CRL) Profile.
 *  http://www.ietf.org/rfc/rfc3280.txt):
 *
 * <pre>
 *  Extensions  ::=  SEQUENCE SIZE (1..MAX) OF Extension
 * </pre>
 */

public class Extensions {

    // Supported critical extensions oids:
    private static List SUPPORTED_CRITICAL = Arrays.asList(
            new String[] {"2.5.29.15", "2.5.29.19", "2.5.29.32", "2.5.29.17",  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
                "2.5.29.30", "2.5.29.36", "2.5.29.37", "2.5.29.54"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    
    // the values of extensions of the structure
    private List<Extension> extensions;
    private Set critical;
    private Set noncritical;
    // the flag showing is there any unsupported critical extension
    // in the list of extensions or not.
    private boolean hasUnsupported;
    // map containing the oid of extensions as a keys and
    // Extension objects as values
    private HashMap oidMap;
    // the ASN.1 encoded form of Extensions
    private byte[] encoding;
    
    /**
     * Constructs an object representing the value of Extensions.
     */
    public Extensions() {}
    
    /**
     * TODO
     * @param   extensions: List
     */
    public Extensions(List extensions) {
        this.extensions = extensions;
    }
    
    /**
     * Returns the values of extensions.
     * @return  extensions
     */
    public List getExtensions() {
        return extensions;
    }
    
    public int size() {
        return (extensions == null) 
                        ? 0
                        : extensions.size();
    }
    
    /**
     * Returns the list of critical extensions.
     * @return  extensions
     */
    public Set getCriticalExtensions() {
        if (critical == null) {
            makeOidsLists();
        }
        return critical;
    }

    /**
     * Returns the list of critical extensions.
     * @return  extensions
     */
    public Set getNonCriticalExtensions() {
        if (noncritical == null) {
            makeOidsLists();
        }
        return noncritical;
    }

    public boolean hasUnsupportedCritical() {
        if (critical == null) {
            makeOidsLists();
        }
        return hasUnsupported;
    }

    //
    // Makes the separated lists with oids of critical 
    // and non-critical extensions
    //
    private void makeOidsLists() {
        if (extensions == null) {
            return;
        }
        int size = extensions.size();
        critical = new HashSet(size);
        noncritical = new HashSet(size);
        for (int i=0; i<size; i++) {
            Extension extn = (Extension) extensions.get(i);
            String oid = extn.getExtnID();
            if (extn.getCritical()) {
                if (!SUPPORTED_CRITICAL.contains(oid)) {
                    hasUnsupported = true;
                }
                critical.add(oid);
            } else {
                noncritical.add(oid);
            }
        }
    }
    
    /**
     * Returns the values of extensions.
     * @param oid - the OID of needed extension.
     * @return  extensions
     */
    public Extension getExtensionByOID(String oid) {
        if (extensions == null) {
            return null;
        }
        if (oidMap == null) {
            oidMap = new HashMap();
            Iterator it = extensions.iterator();
            while (it.hasNext()) {
                Extension extn = (Extension) it.next();
                oidMap.put(extn.getExtnID(), extn);
            }
        }
        return (Extension) oidMap.get(oid);
    }


    /**
     * Returns the value of Key Usage extension (OID == 2.5.29.15).
     * The ASN.1 definition of Key Usage Extension is:
     *
     * <pre> 
     * id-ce-keyUsage OBJECT IDENTIFIER ::=  { id-ce 15 }
     *
     * KeyUsage ::= BIT STRING {
     *     digitalSignature        (0),
     *     nonRepudiation          (1),
     *     keyEncipherment         (2),
     *     dataEncipherment        (3),
     *     keyAgreement            (4),
     *     keyCertSign             (5),
     *     cRLSign                 (6),
     *     encipherOnly            (7),
     *     decipherOnly            (8) 
     * }
     * </pre> 
     * (as specified in RFC 3280)
     *
     * @return the value of Key Usage Extension if it is in the list,
     * and null if there is no such extension or its value can not be decoded
     * otherwise. Note, that the length of returned array can be greater
     * than 9.
     */
    public boolean[] valueOfKeyUsage() {
        Extension extn = getExtensionByOID("2.5.29.15"); //$NON-NLS-1$
        KeyUsage kUsage = null;
        if ((extn == null) || ((kUsage = extn.getKeyUsageValue()) == null)) {
            return null;
        }
        return kUsage.getKeyUsage();
    }
   
    /**
     * Returns the value of Extended Key Usage extension (OID == 2.5.29.37).
     * The ASN.1 definition of Extended Key Usage Extension is:
     *
     * <pre> 
     *  id-ce-extKeyUsage OBJECT IDENTIFIER ::= { id-ce 37 }
     *  
     *  ExtKeyUsageSyntax ::= SEQUENCE SIZE (1..MAX) OF KeyPurposeId
     *  
     *  KeyPurposeId ::= OBJECT IDENTIFIER
     * </pre>
     * (as specified in RFC 3280)
     *
     * @return the list with string representations of KeyPurposeId's OIDs
     * and null
     * @throws IOException if extension was incorrectly encoded.
     */
    public List valueOfExtendedKeyUsage() throws IOException {
        Extension extn = getExtensionByOID("2.5.29.37"); //$NON-NLS-1$
        if (extn == null) {
            return null;
        }
        return ((ExtendedKeyUsage) 
                extn.getDecodedExtensionValue()).getExtendedKeyUsage();
    }
    
    /**
     * Returns the value of Basic Constraints Extension (OID = 2.5.29.19).
     * The ASN.1 definition of Basic Constraints Extension is:
     *
     * <pre> 
     *   id-ce-basicConstraints OBJECT IDENTIFIER ::=  { id-ce 19 }
     *
     *   BasicConstraints ::= SEQUENCE {
     *        cA                      BOOLEAN DEFAULT FALSE,
     *        pathLenConstraint       INTEGER (0..MAX) OPTIONAL 
     *   }
     * </pre>
     * (as specified in RFC 3280)
     *
     * @return the value of pathLenConstraint field if extension presents,
     * and Integer.MAX_VALUE if does not.
     */
    public int valueOfBasicConstrains() {
        Extension extn = getExtensionByOID("2.5.29.19"); //$NON-NLS-1$
        BasicConstraints bc = null;
        if ((extn == null) 
                || ((bc = extn.getBasicConstraintsValue()) == null)) {
            return Integer.MAX_VALUE;
        }
        return bc.getPathLenConstraint();
    }
    
    /**
     * Returns the value of Subject Alternative Name (OID = 2.5.29.17).
     * The ASN.1 definition for Subject Alternative Name is:
     *
     * <pre> 
     *  id-ce-subjectAltName OBJECT IDENTIFIER ::=  { id-ce 17 }
     *  
     *  SubjectAltName ::= GeneralNames
     * </pre>
     * (as specified in RFC 3280)
     *
     * @return Returns the collection of pairs: 
     * (Integer (tag), Object (name value)) if extension presents, and
     * null if does not.
     */
    public List valueOfSubjectAlternativeName() throws IOException {
        Extension extn = getExtensionByOID("2.5.29.17"); //$NON-NLS-1$
        if (extn == null) {
            return null;
        }
        return ((GeneralNames) GeneralNames.ASN1.decode(extn.getExtnValue()))
                .getPairsList();
    }
    
    /**
     * Returns the value of Issuer Alternative Name Extension (OID = 2.5.29.18).
     * The ASN.1 definition for Issuer Alternative Name is:
     *
     * <pre> 
     *   id-ce-issuerAltName OBJECT IDENTIFIER ::=  { id-ce 18 }
     *  
     *   IssuerAltName ::= GeneralNames
     * </pre>
     * (as specified in RFC 3280)
     *
     * @return Returns the collection of pairs: 
     * (Integer (tag), Object (name value)) if extension presents, and
     * null if does not.
     */
    public List valueOfIssuerAlternativeName() throws IOException {
        Extension extn = getExtensionByOID("2.5.29.18"); //$NON-NLS-1$
        if (extn == null) {
            return null;
        }
        return ((GeneralNames) 
                GeneralNames.ASN1.decode(extn.getExtnValue())).getPairsList();
    }
   
    /**
     * Returns the value of Certificate Issuer Extension (OID = 2.5.29.29). 
     * It is a CRL entry extension and contains the GeneralNames describing
     * the issuer of revoked certificate. Its ASN.1 notation is as follows:
     * <pre>
     *   id-ce-certificateIssuer   OBJECT IDENTIFIER ::= { id-ce 29 }
     *
     *   certificateIssuer ::=     GeneralNames
     * </pre>
     * (as specified in RFC 3280)
     *
     * @return the value of Certificate Issuer Extension
     */
    public X500Principal valueOfCertificateIssuerExtension() 
                                                        throws IOException {
        Extension extn = getExtensionByOID("2.5.29.29"); //$NON-NLS-1$
        if (extn == null) {
            return null;
        }
        return ((CertificateIssuer) 
                extn.getDecodedExtensionValue()).getIssuer();
    }
    
    /**
     * TODO
     * @param   extn:  Extension
     * @return
     */
    public void addExtension(Extension extn) {
        encoding = null;
        if (extensions == null) {
            extensions = new ArrayList();
        }
        extensions.add(extn);
        if (oidMap != null) {
            oidMap.put(extn.getExtnID(), extn);
        }
        if (critical != null) {
            String oid = extn.getExtnID();
            if (extn.getCritical()) {
                if (!SUPPORTED_CRITICAL.contains(oid)) {
                    hasUnsupported = true;
                }
                critical.add(oid);
            } else {
                noncritical.add(oid);
            }
        }
    }

    /**
     * Returns ASN.1 encoded form of this X.509 Extensions value.
     * @return a byte array containing ASN.1 encode form.
     */
    public byte[] getEncoded() {
        if (encoding == null) {
            encoding = ASN1.encode(this);
        }
        return encoding;
    }
    
    public boolean equals(Object exts) {
        if (!(exts instanceof Extensions)) {
            return false;
        }
        Extensions extns = (Extensions) exts;
        return ((extensions == null) || (extensions.size() == 0) 
                    ? ((extns.extensions == null) 
                            || (extns.extensions.size() == 0))
                    : ((extns.extensions == null) 
                            || (extns.extensions.size() == 0))
                        ? false
                        : (extensions.containsAll(extns.extensions)
                            && (extensions.size() == extns.extensions.size()))
                );
    }
    
    public int hashCode() {
    	int hashcode = 0;
    	if (extensions != null) {
    		hashcode = extensions.hashCode();
    	}
    	return hashcode;
    }
    
    /**
     * Places the string representation into the StringBuffer object.
     */
    public void dumpValue(StringBuffer buffer, String prefix) {
        if (extensions == null) {
            return;
        }
        int num = 1;
        for (Extension extension: extensions) {
            buffer.append('\n').append(prefix)
                .append('[').append(num++).append("]: "); //$NON-NLS-1$
            extension.dumpValue(buffer, prefix);
        }
    }

    /**
     * Custom X.509 Extensions decoder.
     */
    public static final ASN1Type ASN1 = new ASN1SequenceOf(Extension.ASN1) {

        public Object getDecodedObject(BerInputStream in) {
            return new Extensions((List)in.content);
        }

        public Collection getValues(Object object) {
            Extensions exts = (Extensions) object;
            return (exts.extensions == null) ? new ArrayList() : exts.extensions;
        }
    };
}

