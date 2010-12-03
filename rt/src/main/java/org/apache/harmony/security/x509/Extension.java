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
import java.util.Arrays;

import org.apache.harmony.security.asn1.ASN1Boolean;
import org.apache.harmony.security.asn1.ASN1OctetString;
import org.apache.harmony.security.asn1.ASN1Oid;
import org.apache.harmony.security.asn1.ASN1Sequence;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.BerInputStream;
import org.apache.harmony.security.asn1.ObjectIdentifier;
import org.apache.harmony.security.utils.Array;

/**
 * The class encapsulates the ASN.1 DER encoding/decoding work 
 * with the Extension part of X.509 certificate
 * (as specified in RFC 3280 -
 *  Internet X.509 Public Key Infrastructure.
 *  Certificate and Certificate Revocation List (CRL) Profile.
 *  http://www.ietf.org/rfc/rfc3280.txt):
 *
 * <pre>
 *  Extension  ::=  SEQUENCE  {
 *       extnID      OBJECT IDENTIFIER,
 *       critical    BOOLEAN DEFAULT FALSE,
 *       extnValue   OCTET STRING  
 *  }
 * </pre>
 */

public class Extension {
    // critical constants
    public static final boolean CRITICAL = true;
    public static final boolean NON_CRITICAL = false;
    
    // constants: the extension OIDs
    // certificate extensions:
    static final int[] SUBJ_DIRECTORY_ATTRS = {2, 5, 29, 9};
    static final int[] SUBJ_KEY_ID = {2, 5, 29, 14};
    static final int[] KEY_USAGE = {2, 5, 29, 15};
    static final int[] PRIVATE_KEY_USAGE_PERIOD = {2, 5, 29, 16};
    static final int[] SUBJECT_ALT_NAME = {2, 5, 29, 17};
    static final int[] ISSUER_ALTERNATIVE_NAME = {2, 5, 29, 18};
    static final int[] BASIC_CONSTRAINTS = {2, 5, 29, 19};
    static final int[] NAME_CONSTRAINTS = {2, 5, 29, 30};
    static final int[] CRL_DISTR_POINTS = {2, 5, 29, 31};
    static final int[] CERTIFICATE_POLICIES = {2, 5, 29, 32};
    static final int[] POLICY_MAPPINGS = {2, 5, 29, 33};
    static final int[] AUTH_KEY_ID = {2, 5, 29, 35};
    static final int[] POLICY_CONSTRAINTS = {2, 5, 29, 36};
    static final int[] EXTENDED_KEY_USAGE = {2, 5, 29, 37};
    static final int[] FRESHEST_CRL = {2, 5, 29, 46};
    static final int[] INHIBIT_ANY_POLICY = {2, 5, 29, 54};
    static final int[] AUTHORITY_INFO_ACCESS =
                                            {1, 3, 6, 1, 5, 5, 7, 1, 1};
    static final int[] SUBJECT_INFO_ACCESS =
                                            {1, 3, 6, 1, 5, 5, 7, 1, 11};
    // crl extensions:
    static final int[] ISSUING_DISTR_POINT = {2, 5, 29, 28};
    // crl entry extensions:
    static final int[] CRL_NUMBER = {2, 5, 29, 20};
    static final int[] CERTIFICATE_ISSUER = {2, 5, 29, 29};
    static final int[] INVALIDITY_DATE = {2, 5, 29, 24};
    static final int[] REASON_CODE = {2, 5, 29, 21};
    static final int[] ISSUING_DISTR_POINTS = {2, 5, 29, 28};
    
    // the value of extnID field of the structure
    private final int[] extnID;
    private String extnID_str;
    // the value of critical field of the structure
    private final boolean critical;
    // the value of extnValue field of the structure
    private final byte[] extnValue;
    // the ASN.1 encoded form of Extension
    private byte[] encoding;
    // the raw (not decoded) value of extnValue field of the structure
    private byte[] rawExtnValue;
    // the decoded extension value
    protected ExtensionValue extnValueObject;
    // tells whether extension value has been decoded or not
    private boolean valueDecoded = false;

    /**
     * TODO
     * @param   extnID: String
     * @param   critical:   boolean
     * @param   extnValue:  byte[]
     */
    public Extension(String extnID, boolean critical, 
            ExtensionValue extnValueObject) {
        this.extnID_str = extnID;
        this.extnID = ObjectIdentifier.toIntArray(extnID);
        this.critical = critical;
        this.extnValueObject = extnValueObject;
        this.valueDecoded = true;
        this.extnValue = extnValueObject.getEncoded();
    }
        
    /**
     * TODO
     * @param   extnID: String
     * @param   critical:   boolean
     * @param   extnValue:  byte[]
     */
    public Extension(String extnID, boolean critical, byte[] extnValue) {
        this.extnID_str = extnID;
        this.extnID = ObjectIdentifier.toIntArray(extnID);
        this.critical = critical;
        this.extnValue = extnValue;
    }
        
    /**
     * TODO
     * @param   extnID: int[]
     * @param   critical:   boolean
     * @param   extnValue:  byte[]
     */
    public Extension(int[] extnID, boolean critical, byte[] extnValue) {
        this.extnID = extnID;
        this.critical = critical;
        this.extnValue = extnValue;
    }
        
    /**
     * TODO
     * @param   extnID: String
     * @param   extnValue:  byte[]
     */
    public Extension(String extnID, byte[] extnValue) {
        this(extnID, NON_CRITICAL, extnValue);
    }

    /**
     * TODO
     * @param   extnID: int[]
     * @param   extnValue:  byte[]
     */
    public Extension(int[] extnID, byte[] extnValue) {
        this(extnID, NON_CRITICAL, extnValue);
    }

    // 
    // TODO
    // @param   extnID: int[]
    // @param   critical:   boolean
    // @param   extnValue:  byte[]
    // @param   encoding:   byte[]
    // 
    private Extension(int[] extnID, boolean critical, byte[] extnValue,
            byte[] rawExtnValue, byte[] encoding, 
            ExtensionValue decodedExtValue) {
        this(extnID, critical, extnValue);
        this.rawExtnValue = rawExtnValue;
        this.encoding = encoding;
        this.extnValueObject = decodedExtValue;
        this.valueDecoded = (decodedExtValue != null);
    }
    
    /**
     * Returns the value of extnID field of the structure.
     * @return  extnID
     */
    public String getExtnID() {
        if (extnID_str == null) {
            extnID_str = ObjectIdentifier.toString(extnID);
        }
        return extnID_str;
    }
    
    /**
     * Returns the value of critical field of the structure.
     * @return  critical
     */
    public boolean getCritical() {
        return critical;
    }

    /**
     * Returns the value of extnValue field of the structure.
     * @return  extnValue
     */
    public byte[] getExtnValue() {
        return extnValue;
    }

    /**
     * Returns the raw (undecoded octet string) value of extnValue 
     * field of the structure.
     * @return  rawExtnValue
     */
    public byte[] getRawExtnValue() {
        if (rawExtnValue == null) {
            rawExtnValue = ASN1OctetString.getInstance().encode(extnValue);
        }
        return rawExtnValue;
    }

    /**
     * Returns ASN.1 encoded form of this X.509 Extension value.
     * @return a byte array containing ASN.1 encode form.
     */
    public byte[] getEncoded() {
        if (encoding == null) {
            encoding = Extension.ASN1.encode(this);
        }
        return encoding;
    }

    public boolean equals(Object ext) {
        if (!(ext instanceof Extension)) {
            return false;
        }
        Extension extn = (Extension) ext;
        return Arrays.equals(extnID, extn.extnID) 
            && (critical == extn.critical)
            && Arrays.equals(extnValue, extn.extnValue);
    }
    
    public int hashCode() {
    	return (extnID.hashCode() * 37 + (critical ? 1 : 0)) * 37 + extnValue.hashCode();
    }

    public ExtensionValue getDecodedExtensionValue() throws IOException {
        if (!valueDecoded) {
            decodeExtensionValue();
        }
        return extnValueObject;
    }

    public KeyUsage getKeyUsageValue() {
        if (!valueDecoded) {
            try {
                decodeExtensionValue();
            } catch (IOException e) { }
        }
        if (extnValueObject instanceof KeyUsage) {
            return (KeyUsage) extnValueObject;
        } else {
            return null;
        }
    }

    public BasicConstraints getBasicConstraintsValue() {
        if (!valueDecoded) {
            try {
                decodeExtensionValue();
            } catch (IOException e) { }
        }
        if (extnValueObject instanceof BasicConstraints) {
            return (BasicConstraints) extnValueObject;
        } else {
            return null;
        }
    }

    private void decodeExtensionValue() throws IOException {
        if (valueDecoded) {
            return;
        }
        valueDecoded = true;
        if (oidEquals(extnID, SUBJ_KEY_ID)) {
            extnValueObject = SubjectKeyIdentifier.decode(extnValue);
        } else if (oidEquals(extnID, KEY_USAGE)) {
            extnValueObject = new KeyUsage(extnValue);
        } else if (oidEquals(extnID, SUBJECT_ALT_NAME)) {
            extnValueObject = new AlternativeName(
                    AlternativeName.SUBJECT, extnValue);
        } else if (oidEquals(extnID, ISSUER_ALTERNATIVE_NAME)) {
            extnValueObject = new AlternativeName(
                    AlternativeName.SUBJECT, extnValue);
        } else if (oidEquals(extnID, BASIC_CONSTRAINTS)) {
            extnValueObject = new BasicConstraints(extnValue);
        } else if (oidEquals(extnID, NAME_CONSTRAINTS)) {
            extnValueObject = NameConstraints.decode(extnValue);
        } else if (oidEquals(extnID, CERTIFICATE_POLICIES)) {
            extnValueObject = CertificatePolicies.decode(extnValue);
        } else if (oidEquals(extnID, AUTH_KEY_ID)) {
            extnValueObject = AuthorityKeyIdentifier.decode(extnValue);
        } else if (oidEquals(extnID, POLICY_CONSTRAINTS)) {
            extnValueObject = new PolicyConstraints(extnValue);
        } else if (oidEquals(extnID, EXTENDED_KEY_USAGE)) {
            extnValueObject = new ExtendedKeyUsage(extnValue);
        } else if (oidEquals(extnID, INHIBIT_ANY_POLICY)) {
            extnValueObject = new InhibitAnyPolicy(extnValue);
        } else if (oidEquals(extnID, CERTIFICATE_ISSUER)) {
            extnValueObject = new CertificateIssuer(extnValue);
        } else if (oidEquals(extnID, CRL_DISTR_POINTS)) {
            extnValueObject = CRLDistributionPoints.decode(extnValue);
        } else if (oidEquals(extnID, CERTIFICATE_ISSUER)) {
            extnValueObject = new ReasonCode(extnValue);
        } else if (oidEquals(extnID, INVALIDITY_DATE)) {
            extnValueObject = new InvalidityDate(extnValue);
        } else if (oidEquals(extnID, REASON_CODE)) {
            extnValueObject = new ReasonCode(extnValue);
        } else if (oidEquals(extnID, CRL_NUMBER)) {
            extnValueObject = new CRLNumber(extnValue);
        } else if (oidEquals(extnID, ISSUING_DISTR_POINTS)) {
            extnValueObject = IssuingDistributionPoint.decode(extnValue);
        } else if (oidEquals(extnID, AUTHORITY_INFO_ACCESS)) {
            extnValueObject = InfoAccessSyntax.decode(extnValue);
        } else if (oidEquals(extnID, SUBJECT_INFO_ACCESS)) {
            extnValueObject = InfoAccessSyntax.decode(extnValue);
        }
    }

    /**
     * Places the string representation into the StringBuffer object.
     */
    public void dumpValue(StringBuffer buffer, String prefix) {
        buffer.append("OID: ").append(getExtnID()) //$NON-NLS-1$
            .append(", Critical: ").append(critical).append('\n'); //$NON-NLS-1$
        if (!valueDecoded) {
            try {
                decodeExtensionValue();
            } catch (IOException e) { }
        }
        if (extnValueObject != null) {
            extnValueObject.dumpValue(buffer, prefix);
            return;
        }
        // else: dump unparsed hex representation
        buffer.append(prefix);
        if (oidEquals(extnID, SUBJ_DIRECTORY_ATTRS)) {
            buffer.append("Subject Directory Attributes Extension"); //$NON-NLS-1$
        } else if (oidEquals(extnID, SUBJ_KEY_ID)) {
            buffer.append("Subject Key Identifier Extension"); //$NON-NLS-1$
        } else if (oidEquals(extnID, KEY_USAGE)) {
            buffer.append("Key Usage Extension"); //$NON-NLS-1$
        } else if (oidEquals(extnID, PRIVATE_KEY_USAGE_PERIOD)) {
            buffer.append("Private Key Usage Period Extension"); //$NON-NLS-1$
        } else if (oidEquals(extnID, SUBJECT_ALT_NAME)) {
            buffer.append("Subject Alternative Name Extension"); //$NON-NLS-1$
        } else if (oidEquals(extnID, ISSUER_ALTERNATIVE_NAME)) {
            buffer.append("Issuer Alternative Name Extension"); //$NON-NLS-1$
        } else if (oidEquals(extnID, BASIC_CONSTRAINTS)) {
            buffer.append("Basic Constraints Extension"); //$NON-NLS-1$
        } else if (oidEquals(extnID, NAME_CONSTRAINTS)) {
            buffer.append("Name Constraints Extension"); //$NON-NLS-1$
        } else if (oidEquals(extnID, CRL_DISTR_POINTS)) {
            buffer.append("CRL Distribution Points Extension"); //$NON-NLS-1$
        } else if (oidEquals(extnID, CERTIFICATE_POLICIES)) {
            buffer.append("Certificate Policies Extension"); //$NON-NLS-1$
        } else if (oidEquals(extnID, POLICY_MAPPINGS)) {
            buffer.append("Policy Mappings Extension"); //$NON-NLS-1$
        } else if (oidEquals(extnID, AUTH_KEY_ID)) {
            buffer.append("Authority Key Identifier Extension"); //$NON-NLS-1$
        } else if (oidEquals(extnID, POLICY_CONSTRAINTS)) {
            buffer.append("Policy Constraints Extension"); //$NON-NLS-1$
        } else if (oidEquals(extnID, EXTENDED_KEY_USAGE)) {
            buffer.append("Extended Key Usage Extension"); //$NON-NLS-1$
        } else if (oidEquals(extnID, INHIBIT_ANY_POLICY)) {
            buffer.append("Inhibit Any-Policy Extension"); //$NON-NLS-1$
        } else if (oidEquals(extnID, AUTHORITY_INFO_ACCESS)) {
            buffer.append("Authority Information Access Extension"); //$NON-NLS-1$
        } else if (oidEquals(extnID, SUBJECT_INFO_ACCESS)) {
            buffer.append("Subject Information Access Extension"); //$NON-NLS-1$
        } else if (oidEquals(extnID, INVALIDITY_DATE)) {
            buffer.append("Invalidity Date Extension"); //$NON-NLS-1$
        } else if (oidEquals(extnID, CRL_NUMBER)) {
            buffer.append("CRL Number Extension"); //$NON-NLS-1$
        } else if (oidEquals(extnID, REASON_CODE)) {
            buffer.append("Reason Code Extension"); //$NON-NLS-1$
        } else {
            buffer.append("Unknown Extension"); //$NON-NLS-1$
        }
        buffer.append('\n').append(prefix)
            .append("Unparsed Extension Value:\n"); //$NON-NLS-1$
        buffer.append(Array.toString(extnValue, prefix));
    }


    // Compares two OIDs
    private static boolean oidEquals(int[] oid1, int[] oid2) {
        int length = oid1.length;
        if (length != oid2.length) {
            return false;
        }
        while (length > 0) {
            if (oid1[--length] != oid2[length]) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * X.509 Extension encoder/decoder.
     */
    public static final ASN1Sequence ASN1 = new ASN1Sequence(new ASN1Type[] {
            ASN1Oid.getInstance(), 
            ASN1Boolean.getInstance(),
            new ASN1OctetString() {
                public Object getDecodedObject(BerInputStream in) 
                                                throws IOException {
                    // first - decoded octet string,
                    // second - raw encoding of octet string
                    return new Object[] 
                        {super.getDecodedObject(in), in.getEncoded()};
                }
            }
        }) {
        {
            setDefault(Boolean.FALSE, 1);
        }

        protected Object getDecodedObject(BerInputStream in) throws IOException {
            Object[] values = (Object[]) in.content;

            int[] oid = (int[]) values[0];
            byte[] extnValue = (byte[]) ((Object[]) values[2])[0];
            byte[] rawExtnValue = (byte[]) ((Object[]) values[2])[1];
            
            ExtensionValue decodedExtValue = null;
            // decode Key Usage and Basic Constraints extension values
            if (oidEquals(oid, KEY_USAGE)) {
                decodedExtValue = new KeyUsage(extnValue);
            } else if (oidEquals(oid, BASIC_CONSTRAINTS)) {
                decodedExtValue = new BasicConstraints(extnValue);
            }

            return 
                new Extension((int[]) values[0],
                    ((Boolean) values[1]).booleanValue(),
                    extnValue, rawExtnValue, in.getEncoded(), decodedExtValue);
        }

        protected void getValues(Object object, Object[] values) {

            Extension ext = (Extension) object;

            values[0] = ext.extnID;
            values[1] = (ext.critical) ? Boolean.TRUE : Boolean.FALSE;
            values[2] = ext.extnValue;
        }
    };
}

