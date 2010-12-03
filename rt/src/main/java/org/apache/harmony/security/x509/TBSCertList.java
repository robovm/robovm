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
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.security.auth.x500.X500Principal;

import org.apache.harmony.security.asn1.ASN1Explicit;
import org.apache.harmony.security.asn1.ASN1Integer;
import org.apache.harmony.security.asn1.ASN1Sequence;
import org.apache.harmony.security.asn1.ASN1SequenceOf;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.BerInputStream;
import org.apache.harmony.security.x501.Name;


/**
 * The class encapsulates the ASN.1 DER encoding/decoding work 
 * with TBSCertList structure which is the part of X.509 CRL
 * (as specified in RFC 3280 -
 *  Internet X.509 Public Key Infrastructure.
 *  Certificate and Certificate Revocation List (CRL) Profile.
 *  http://www.ietf.org/rfc/rfc3280.txt):
 *
 * <pre>
 *   TBSCertList  ::=  SEQUENCE  {
 *        version                 Version OPTIONAL,
 *                                     -- if present, MUST be v2
 *        signature               AlgorithmIdentifier,
 *        issuer                  Name,
 *        thisUpdate              Time,
 *        nextUpdate              Time OPTIONAL,
 *        revokedCertificates     SEQUENCE OF SEQUENCE  {
 *             userCertificate         CertificateSerialNumber,
 *             revocationDate          Time,
 *             crlEntryExtensions      Extensions OPTIONAL
 *                                           -- if present, MUST be v2
 *                                  }  OPTIONAL,
 *        crlExtensions           [0]  EXPLICIT Extensions OPTIONAL
 *                                           -- if present, MUST be v2
 *   }
 * </pre>
 */
public class TBSCertList {

    // the value of version field of the structure
    private final int version; 
    // the value of signature field of the structure
    private final AlgorithmIdentifier signature; 
    // the value of issuer field of the structure
    private final Name issuer;
    // the value of thisUpdate of the structure
    private final Date thisUpdate;
    // the value of nextUpdate of the structure
    private final Date nextUpdate;
    // the value of revokedCertificates of the structure
    private final List revokedCertificates;
    // the value of crlExtensions field of the structure
    private final Extensions crlExtensions;
    // the ASN.1 encoded form of TBSCertList
    private byte[] encoding;

    public static class RevokedCertificate {
        private final BigInteger userCertificate;
        private final Date revocationDate;
        private final Extensions crlEntryExtensions;
        
        private boolean issuerRetrieved;
        private X500Principal issuer;
        private byte[] encoding;

        public RevokedCertificate(BigInteger userCertificate,
                Date revocationDate, Extensions crlEntryExtensions) {
            this.userCertificate = userCertificate;
            this.revocationDate = revocationDate;
            this.crlEntryExtensions = crlEntryExtensions;
        }

        public Extensions getCrlEntryExtensions() {
            return crlEntryExtensions;
        }

        public BigInteger getUserCertificate() {
            return userCertificate;
        }

        public Date getRevocationDate() {
            return revocationDate;
        }

        /**
         * Returns the value of Certificate Issuer Extension, if it is
         * presented.
         */
        public X500Principal getIssuer() {
            if (crlEntryExtensions == null) {
                return null;
            }
            if (!issuerRetrieved) {
                try {
                    issuer =  
                        crlEntryExtensions.valueOfCertificateIssuerExtension();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                issuerRetrieved = true;
            }
            return issuer;
        }
        
        public byte[] getEncoded() {
            if (encoding == null) {
                encoding = ASN1.encode(this);
            }
            return encoding;
        }
        
        public boolean equals(Object rc) {
            if (!(rc instanceof RevokedCertificate)) {
                return false;
            }
            RevokedCertificate rcert = (RevokedCertificate) rc;
            return userCertificate.equals(rcert.userCertificate)
                && ((revocationDate.getTime() / 1000) 
                        == (rcert.revocationDate.getTime() / 1000))
                && ((crlEntryExtensions == null)
                    ? rcert.crlEntryExtensions == null
                    : crlEntryExtensions.equals(rcert.crlEntryExtensions));
        }
        
        public int hashCode() {
        	return userCertificate.hashCode() * 37 + (int)revocationDate.getTime() / 1000
        	+ (crlEntryExtensions == null ? 0 : crlEntryExtensions.hashCode());
        }

        /**
         * Places the string representation of extension value
         * into the StringBuffer object.
         */
        public void dumpValue(StringBuffer buffer, String prefix) {
            buffer.append(prefix).append("Certificate Serial Number: ") //$NON-NLS-1$
                .append(userCertificate).append('\n');
            buffer.append(prefix).append("Revocation Date: ") //$NON-NLS-1$
                .append(revocationDate);
            if (crlEntryExtensions != null) {
                buffer.append('\n').append(prefix)
                    .append("CRL Entry Extensions: ["); //$NON-NLS-1$
                crlEntryExtensions.dumpValue(buffer, prefix + "  "); //$NON-NLS-1$
                buffer.append(prefix).append(']');
            }
        }
        
        public static final ASN1Sequence ASN1 = new ASN1Sequence(
                new ASN1Type[] {ASN1Integer.getInstance(), Time.ASN1,
                Extensions.ASN1}) {
            {
                setOptional(2);
            }

            protected Object getDecodedObject(BerInputStream in) {
                Object[] values = (Object[]) in.content;

                return new RevokedCertificate(
                            new BigInteger((byte[]) values[0]),
                            (Date) values[1],
                            (Extensions) values[2]
                        );
            }

            protected void getValues(Object object, Object[] values) {
                RevokedCertificate rcert = (RevokedCertificate) object;

                values[0] = rcert.userCertificate.toByteArray();
                values[1] = rcert.revocationDate;
                values[2] = rcert.crlEntryExtensions;
            }
        };
    }

    /**
     * Constructs the instance of TBSCertList without optional fields.
     * Take a note, that regarding to the rfc 3280 (p. 49):
     * "When CRLs are issued, the CRLs MUST be version 2 CRLs, include the date
     * by which the next CRL will be issued in the nextUpdate field (section
     * 5.1.2.5), include the CRL number extension (section 5.2.3), and include
     * the authority key identifier extension (section 5.2.1). Conforming
     * applications that support CRLs are REQUIRED to process both version 1 and
     * version 2 complete CRLs that provide revocation information for all
     * certificates issued by one CA. Conforming applications are NOT REQUIRED
     * to support processing of delta CRLs, indirect CRLs, or CRLs with a scope
     * other than all certificates issued by one CA."
     * @param   signature:  AlgorithmIdentifier
     * @param   issuer: Name
     * @param   thisUpdate: Time
     */
    public TBSCertList(AlgorithmIdentifier signature, 
            Name issuer, Date thisUpdate) {
        this.version = 1; 
        this.signature = signature; 
        this.issuer = issuer;
        this.thisUpdate = thisUpdate;
        this.nextUpdate = null;
        this.revokedCertificates = null;
        this.crlExtensions = null;
    }

    /**
     * Constructs the instance of TBSCertList with all optional fields
     * @param   version: version of the CRL. Should be 1 or 2. 
     * Note that if the version of CRL is 1, then nextUpdate,
     * crlExtensions fields of CRL and crlEntryExtensions field
     * of CRL entry must not be presented in CRL.
     * FIXME: do check for it.
     * @param   signature:  AlgorithmIdentifier
     * @param   issuer: Name
     * @param   thisUpdate: Time
     * @param   nextUpdate: Time
     * @param   revokedCertificates:    List
     * @param   crlExtensions:  Extensions
     */
    public TBSCertList(int version, AlgorithmIdentifier signature, 
            Name issuer, Date thisUpdate, Date nextUpdate, 
            List revokedCertificates, Extensions crlExtensions) {
        this.version = version; 
        this.signature = signature; 
        this.issuer = issuer;
        this.thisUpdate = thisUpdate;
        this.nextUpdate = nextUpdate;
        this.revokedCertificates = revokedCertificates;
        this.crlExtensions = crlExtensions;
    }

    // Constructs the object with associated ASN.1 encoding
    private TBSCertList(int version, AlgorithmIdentifier signature, 
            Name issuer, Date thisUpdate, Date nextUpdate, 
            List revokedCertificates, Extensions crlExtensions,
            byte[] encoding) {
        this.version = version; 
        this.signature = signature; 
        this.issuer = issuer;
        this.thisUpdate = thisUpdate;
        this.nextUpdate = nextUpdate;
        this.revokedCertificates = revokedCertificates;
        this.crlExtensions = crlExtensions;
        this.encoding = encoding;
    }

    /**
     * Returns the value of version field of the structure.
     * @return  version
     */
    public int getVersion() {
        return version;
    }

    /**
     * Returns the value of signature field of the structure.
     * @return  signature
     */
    public AlgorithmIdentifier getSignature() {
        return signature;
    }
    
    /**
     * Returns the value of issuer field of the structure.
     * @return  issuer
     */
    public Name getIssuer() {
        return issuer;
    }
    
    /**
     * Returns the value of thisUpdate field of the structure.
     * @return thisUpdate
     */
    public Date getThisUpdate() {
        return thisUpdate;
    }
    
    /**
     * Returns the value of nextUpdate field of the structure.
     * @return nextUpdate
     */
    public Date getNextUpdate() {
        return nextUpdate;
    }
    
    /**
     * Returns the value of revokedCertificates field of the structure.
     * @return revokedCertificates
     */
    public List getRevokedCertificates() {
        return revokedCertificates;
    }

    /**
     * Returns the value of crlExtensions field of the structure.
     * @return  extensions
     */
    public Extensions getCrlExtensions() {
        return crlExtensions;
    }

    /**
     * Returns ASN.1 encoded form of this X.509 TBSCertList value.
     * @return a byte array containing ASN.1 encode form.
     */
    public byte[] getEncoded() {
        if (encoding == null) {
            encoding = ASN1.encode(this);
        }
        return encoding;
    }
    
    public boolean equals(Object tbs) {
        if (!(tbs instanceof TBSCertList)) {
            return false;
        }
        TBSCertList tbscert = (TBSCertList) tbs;
        return (version == tbscert.version)
            && (signature.equals(tbscert.signature))
            // FIXME use Name.equals when it will be implemented
            && (Arrays.equals(issuer.getEncoded(), tbscert.issuer.getEncoded()))
            && ((thisUpdate.getTime() / 1000) 
                    == (tbscert.thisUpdate.getTime() / 1000))
            && ((nextUpdate == null) 
                    ? tbscert.nextUpdate == null
                    : ((nextUpdate.getTime() / 1000) 
                        == (tbscert.nextUpdate.getTime() / 1000)))
            && ((((revokedCertificates == null) 
                            || (tbscert.revokedCertificates == null))
                    && (revokedCertificates == tbscert.revokedCertificates))
                || (revokedCertificates.containsAll(tbscert.revokedCertificates)
                    && (revokedCertificates.size() 
                        == tbscert.revokedCertificates.size())))
            && ((crlExtensions == null)
                    ? tbscert.crlExtensions == null
                    : crlExtensions.equals(tbscert.crlExtensions));
    }
    
    public int hashCode() {
    	return ((version * 37 + signature.hashCode()) * 37
    		+ issuer.getEncoded().hashCode()) * 37
    		+ (int)thisUpdate.getTime() / 1000;
    }

    /**
     * Places the string representation of extension value
     * into the StringBuffer object.
     */
    public void dumpValue(StringBuffer buffer) {
        buffer.append("X.509 CRL v").append(version); //$NON-NLS-1$
        buffer.append("\nSignature Algorithm: ["); //$NON-NLS-1$
        signature.dumpValue(buffer);
        buffer.append(']');
        buffer.append("\nIssuer: ").append(issuer.getName(X500Principal.RFC2253)); //$NON-NLS-1$
        buffer.append("\n\nThis Update: ").append(thisUpdate); //$NON-NLS-1$
        buffer.append("\nNext Update: ").append(nextUpdate).append('\n'); //$NON-NLS-1$
        if (revokedCertificates != null) {
            buffer.append("\nRevoked Certificates: ") //$NON-NLS-1$
                .append(revokedCertificates.size()).append(" ["); //$NON-NLS-1$
            int number = 1;
            for (Iterator it = revokedCertificates.iterator();it.hasNext();) {
                buffer.append("\n  [").append(number++).append(']'); //$NON-NLS-1$
                ((RevokedCertificate) it.next()).dumpValue(buffer, "  "); //$NON-NLS-1$
                buffer.append('\n');
            }
            buffer.append("]\n"); //$NON-NLS-1$
        }
        if (crlExtensions != null) {
            buffer.append("\nCRL Extensions: ") //$NON-NLS-1$
                .append(crlExtensions.size()).append(" ["); //$NON-NLS-1$
            crlExtensions.dumpValue(buffer, "  "); //$NON-NLS-1$
            buffer.append("]\n"); //$NON-NLS-1$
        }
    }

    /**
     * X.509 TBSCertList encoder/decoder.
     */
    public static final ASN1Sequence ASN1 = new ASN1Sequence(new ASN1Type[] {
            ASN1Integer.getInstance(), // version
            AlgorithmIdentifier.ASN1,  // signature
            Name.ASN1, // issuer
            Time.ASN1, // thisUpdate
            Time.ASN1, // nextUpdate
            new ASN1SequenceOf(RevokedCertificate.ASN1), // revokedCertificates
            new ASN1Explicit(0, Extensions.ASN1) // crlExtensions
                }) {
        {
            setOptional(0);
            setOptional(4);
            setOptional(5);
            setOptional(6);
        }

        protected Object getDecodedObject(BerInputStream in) 
                        throws IOException {
            Object[] values = (Object[]) in.content;
            return new TBSCertList(
                        (values[0] == null) 
                            ? 1
                            : ASN1Integer.toIntValue(values[0])+1,
                        (AlgorithmIdentifier) values[1],
                        (Name) values[2], 
                        (Date) values[3], 
                        (Date) values[4], 
                        (List) values[5],
                        (Extensions) values[6],
                        in.getEncoded()
                    );
        }

        protected void getValues(Object object, Object[] values) {
            TBSCertList tbs = (TBSCertList) object;
            values[0] = (tbs.version > 1)
                ? ASN1Integer.fromIntValue(tbs.version - 1) : null;
            values[1] = tbs.signature;
            values[2] = tbs.issuer; 
            values[3] = tbs.thisUpdate;
            values[4] = tbs.nextUpdate;
            values[5] = tbs.revokedCertificates;
            values[6] = tbs.crlExtensions;
        }
    };
}

