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
import java.math.BigInteger;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import libcore.util.EmptyArray;
import org.apache.harmony.security.asn1.ASN1OctetString;
import org.apache.harmony.security.utils.Array;
import org.apache.harmony.security.x509.AlgorithmIdentifier;
import org.apache.harmony.security.x509.CertificatePolicies;
import org.apache.harmony.security.x509.GeneralName;
import org.apache.harmony.security.x509.GeneralNames;
import org.apache.harmony.security.x509.NameConstraints;
import org.apache.harmony.security.x509.PolicyInformation;
import org.apache.harmony.security.x509.PrivateKeyUsagePeriod;
import org.apache.harmony.security.x509.SubjectPublicKeyInfo;



/**
 * A certificate selector ({@code CertSelector} for selecting {@code
 * X509Certificate}s that match the specified criteria.
 */
public class X509CertSelector implements CertSelector {

    // match criteria
    private X509Certificate certificateEquals;
    private BigInteger serialNumber;
    private X500Principal issuer;
    private X500Principal subject;
    private byte[] subjectKeyIdentifier;
    private byte[] authorityKeyIdentifier;
    private Date certificateValid;
    private String subjectPublicKeyAlgID;
    private Date privateKeyValid;
    private byte[] subjectPublicKey;
    private boolean[] keyUsage;
    private Set<String> extendedKeyUsage;
    private boolean matchAllNames = true;
    private int pathLen = -1;
    private List<GeneralName>[] subjectAltNames;
    private NameConstraints nameConstraints;
    private Set<String> policies;
    private ArrayList<GeneralName> pathToNames;

    // needed to avoid needless encoding/decoding work
    private PublicKey subjectPublicKeyImpl;
    private String issuerName;
    private byte[] issuerBytes;

    /**
     * Creates a new {@code X509CertSelector}.
     */
    public X509CertSelector() {}

    /**
     * Sets the certificate that a matching certificate must be equal to.
     *
     * @param certificate
     *            the certificate to match, or null to not check this criteria.
     */
    public void setCertificate(X509Certificate certificate) {
        certificateEquals = certificate;
    }

    /**
     * Returns the certificate that a matching certificate must be equal to.
     *
     * @return the certificate to match, or null if this criteria is not
     *         checked.
     */
    public X509Certificate getCertificate() {
        return certificateEquals;
    }

    /**
     * Sets the serial number that a certificate must match.
     *
     * @param serialNumber
     *            the serial number to match, or {@code null} to not check the
     *            serial number.
     */
    public void setSerialNumber(BigInteger serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * Returns the serial number that a certificate must match.
     *
     * @return the serial number to match, or {@code null} if the serial number
     *         is not to be checked.
     */
    public BigInteger getSerialNumber() {
        return serialNumber;
    }

    /**
     * Sets the issuer that a certificate must match.
     *
     * @param issuer
     *            the issuer to match, or {@code null} if the issuer is not to
     *            be checked.
     */
    public void setIssuer(X500Principal issuer) {
        this.issuer = issuer;
        this.issuerName = null;
        this.issuerBytes = null;
    }

    /**
     * Returns the issuer that a certificate must match.
     *
     * @return the issuer that a certificate must match, or {@code null} if the
     *         issuer is not to be checked.
     */
    public X500Principal getIssuer() {
        return issuer;
    }

    /**
     * <b>Do not use</b>, use {@link #getIssuer()} or
     * {@link #getIssuerAsBytes()} instead. Sets the issuer that a certificate
     * must match.
     *
     * @param issuerName
     *            the issuer in a RFC 2253 format string, or {@code null} to not
     *            check the issuer.
     * @throws IOException
     *             if parsing the issuer fails.
     */
    public void setIssuer(String issuerName) throws IOException {
        if (issuerName == null) {
            this.issuer = null;
            this.issuerName = null;
            this.issuerBytes = null;
            return;
        }
        try {
            this.issuer = new X500Principal(issuerName);
            this.issuerName = issuerName;
            this.issuerBytes = null;
        } catch (IllegalArgumentException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * <b>Do not use</b>, use {@link #getIssuer()} or
     * {@link #getIssuerAsBytes()} instead. Returns the issuer that a
     * certificate must match in a RFC 2253 format string.
     *
     * @return the issuer in a RFC 2253 format string, or {@code null} if the
     *         issuer is not to be checked.
     */
    public String getIssuerAsString() {
        if (issuer == null) {
            return null;
        }
        if (issuerName == null) {
            issuerName = issuer.getName();
        }
        return issuerName;
    }

    /**
     * Sets the issuer that a certificate must match.
     *
     * @param issuerDN
     *            the distinguished issuer name in ASN.1 DER encoded format, or
     *            {@code null} to not check the issuer.
     * @throws IOException
     *             if decoding the issuer fail.
     */
    public void setIssuer(byte[] issuerDN) throws IOException {
        if (issuerDN == null) {
            issuer = null;
            return;
        }
        try {
            issuer = new X500Principal(issuerDN);
            this.issuerName = null;
            this.issuerBytes = new byte[issuerDN.length];
            System.arraycopy(issuerDN, 0, this.issuerBytes, 0, issuerDN.length);
        } catch (IllegalArgumentException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * Returns the issuer that a certificate must match.
     *
     * @return the distinguished issuer name in ASN.1 DER encoded format, or
     *         {@code null} if the issuer is not to be checked.
     * @throws IOException
     *             if encoding the issuer fails.
     */
    public byte[] getIssuerAsBytes() throws IOException {
        if (issuer == null) {
            return null;
        }
        if (issuerBytes == null) {
            issuerBytes = issuer.getEncoded();
        }
        byte[] result = new byte[issuerBytes.length];
        System.arraycopy(issuerBytes, 0, result, 0, issuerBytes.length);
        return result;
    }

    /**
     * Set the subject that a certificate must match.
     *
     * @param subject
     *            the subject distinguished name or {@code null} to not check
     *            the subject.
     */
    public void setSubject(X500Principal subject) {
        this.subject = subject;
    }

    /**
     * Returns the subject that a certificate must match.
     *
     * @return the subject distinguished name, or null if the subject is not to
     *         be checked.
     */
    public X500Principal getSubject() {
        return subject;
    }

    /**
     * <b>Do not use</b>, use {@link #setSubject(byte[])} or
     * {@link #setSubject(X500Principal)} instead. Returns the subject that a
     * certificate must match.
     *
     * @param subjectDN
     *            the subject distinguished name in RFC 2253 format or {@code
     *            null} to not check the subject.
     * @throws IOException
     *             if decoding the subject fails.
     */
    public void setSubject(String subjectDN) throws IOException {
        if (subjectDN == null) {
            subject = null;
            return;
        }
        try {
            subject = new X500Principal(subjectDN);
        } catch (IllegalArgumentException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * <b>Do not use</b>, use {@link #getSubject()} or
     * {@link #getSubjectAsBytes()} instead. Returns the subject that a
     * certificate must match.
     *
     * @return the subject distinguished name in RFC 2253 format, or {@code
     *         null} if the subject is not to be checked.
     */
    public String getSubjectAsString() {
        if (subject == null) {
            return null;
        }
        return subject.getName();
    }

    /**
     * Sets the subject that a certificate must match.
     *
     * @param subjectDN
     *            the subject distinguished name in ASN.1 DER format, or {@code
     *            null} to not check the subject.
     * @throws IOException
     *             if decoding the subject fails.
     */
    public void setSubject(byte[] subjectDN) throws IOException {
        if (subjectDN == null) {
            subject = null;
            return;
        }
        try {
            subject = new X500Principal(subjectDN);
        } catch (IllegalArgumentException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * Returns the subject that a certificate must match.
     *
     * @return the subject distinguished name in ASN.1 DER format, or {@code
     *         null} if the subject is not to be checked.
     * @throws IOException
     *             if encoding the subject fails.
     */
    public byte[] getSubjectAsBytes() throws IOException {
        if (subject == null) {
            return null;
        }
        return subject.getEncoded();
    }

    /**
     * Sets the criterion for the {@literal SubjectKeyIdentifier} extension.
     * <p>
     * The {@code subjectKeyIdentifier} should be a single DER encoded value.
     *
     * @param subjectKeyIdentifier
     *            the subject key identifier or {@code null} to disable this
     *            check.
     */
    public void setSubjectKeyIdentifier(byte[] subjectKeyIdentifier) {
        if (subjectKeyIdentifier == null) {
            this.subjectKeyIdentifier = null;
            return;
        }
        this.subjectKeyIdentifier = new byte[subjectKeyIdentifier.length];
        System.arraycopy(subjectKeyIdentifier, 0, this.subjectKeyIdentifier, 0,
                         subjectKeyIdentifier.length);
    }

    /**
     * Returns the criterion for the {@literal SubjectKeyIdentifier} extension.
     *
     * @return the subject key identifier or {@code null} if it is not to be
     *         checked.
     */
    public byte[] getSubjectKeyIdentifier() {
        if (subjectKeyIdentifier == null) {
            return null;
        }
        byte[] res = new byte[subjectKeyIdentifier.length];
        System.arraycopy(subjectKeyIdentifier, 0, res, 0, res.length);
        return res;
    }

    /**
     * Sets the criterion for the {@literal AuthorityKeyIdentifier} extension.
     *
     * @param authorityKeyIdentifier
     *            the authority key identifier, or {@code null} to disable this
     *            check.
     */
    public void setAuthorityKeyIdentifier(byte[] authorityKeyIdentifier) {
        if (authorityKeyIdentifier == null) {
            this.authorityKeyIdentifier = null;
            return;
        }
        this.authorityKeyIdentifier = new byte[authorityKeyIdentifier.length];
        System.arraycopy(authorityKeyIdentifier, 0,
                         this.authorityKeyIdentifier, 0,
                         authorityKeyIdentifier.length);
    }

    /**
     * Returns the criterion for the {@literal AuthorityKeyIdentifier}
     * extension.
     *
     * @return the authority key identifier, or {@code null} if it is not to be
     *         checked.
     */
    public byte[] getAuthorityKeyIdentifier() {
        if (authorityKeyIdentifier == null) {
            return null;
        }
        byte[] res = new byte[authorityKeyIdentifier.length];
        System.arraycopy(authorityKeyIdentifier, 0, res, 0, res.length);
        return res;
    }

    /**
     * Sets the criterion for the validity date of the certificate.
     * <p>
     * The certificate must be valid at the specified date.
     * @param certificateValid
     *            the validity date or {@code null} to not check the date.
     */
    public void setCertificateValid(Date certificateValid) {
        this.certificateValid = (certificateValid == null)
                                ? null
                                : (Date) certificateValid.clone();
    }

    /**
     * Returns the criterion for the validity date of the certificate.
     *
     * @return the validity date or {@code null} if the date is not to be
     *         checked.
     */
    public Date getCertificateValid() {
        return (certificateValid == null)
                                ? null
                                : (Date) certificateValid.clone();
    }

    /**
     * Sets the criterion for the validity date of the private key.
     * <p>
     * The private key must be valid at the specified date.
     *
     * @param privateKeyValid
     *            the validity date or {@code null} to not check the date.
     */
    public void setPrivateKeyValid(Date privateKeyValid) {
        if (privateKeyValid == null) {
            this.privateKeyValid = null;
            return;
        }
        this.privateKeyValid = (Date) privateKeyValid.clone();
    }

    /**
     * Returns the criterion for the validity date of the private key.
     * <p>
     * The private key must be valid at the specified date.
     *
     * @return the validity date or {@code null} if the date is not to be
     *         checked.
     */
    public Date getPrivateKeyValid() {
        if (privateKeyValid != null) {
            return (Date) privateKeyValid.clone();
        }
        return null;
    }

    private void checkOID(String oid) throws IOException {
        int beg = 0;
        int end = oid.indexOf('.', beg);
        try {
            int comp = Integer.parseInt(oid.substring(beg, end));
            beg = end + 1;
            if ((comp < 0) || (comp > 2)) {
                throw new IOException("Bad OID: " + oid);
            }
            end = oid.indexOf('.', beg);
            comp = Integer.parseInt(oid.substring(beg, end));
            if ((comp < 0) || (comp > 39)) {
                throw new IOException("Bad OID: " + oid);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IOException("Bad OID: " + oid);
        } catch (NumberFormatException e) {
            throw new IOException("Bad OID: " + oid);
        }
    }

    /**
     * Sets the criterion for the subject public key signature algorithm.
     * <p>
     * The certificate must contain a subject public key with the algorithm
     * specified.
     *
     * @param oid
     *            the OID (object identifier) of the signature algorithm or
     *            {@code null} to not check the OID.
     * @throws IOException
     *             if the specified object identifier is invalid.
     */
    public void setSubjectPublicKeyAlgID(String oid) throws IOException {
        if (oid == null) {
            subjectPublicKeyAlgID = null;
            return;
        }
        checkOID(oid);
        subjectPublicKeyAlgID = oid;
    }

    /**
     * Returns the criterion for the subject public key signature algorithm.
     *
     * @return the OID (object identifier) or the signature algorithm or {@code
     *         null} if it's not to be checked.
     */
    public String getSubjectPublicKeyAlgID() {
        return subjectPublicKeyAlgID;
    }

    /**
     * Sets the criterion for the subject public key.
     *
     * @param key
     *            the subject public key or {@code null} to not check the key.
     */
    public void setSubjectPublicKey(PublicKey key) {
        subjectPublicKey = (key == null) ? null : key.getEncoded();
        subjectPublicKeyImpl = key;
    }

    /**
     * Sets the criterion for the subject public key.
     *
     * @param key
     *            the subject public key in ASN.1 DER encoded format or {@code null} to
     *            not check the key.
     * @throws IOException
     *             if decoding the the public key fails.
     */
    public void setSubjectPublicKey(byte[] key) throws IOException {
        if (key == null) {
            subjectPublicKey = null;
            subjectPublicKeyImpl = null;
            return;
        }
        subjectPublicKey = new byte[key.length];
        System.arraycopy(key, 0, subjectPublicKey, 0, key.length);
        subjectPublicKeyImpl =
            ((SubjectPublicKeyInfo) SubjectPublicKeyInfo.ASN1.decode(key))
            .getPublicKey();
    }

    /**
     * Returns the criterion for the subject public key.
     *
     * @return the subject public key or {@code null} if the key is not to be
     *         checked.
     */
    public PublicKey getSubjectPublicKey() {
        return subjectPublicKeyImpl;
    }

    /**
     * Sets the criterion for the {@literal KeyUsage} extension.
     *
     * @param keyUsage
     *            the boolean array in the format as returned by
     *            {@link X509Certificate#getKeyUsage()}, or {@code null} to not
     *            check the key usage.
     */
    public void setKeyUsage(boolean[] keyUsage) {
        if (keyUsage == null) {
            this.keyUsage = null;
            return;
        }
        this.keyUsage = new boolean[keyUsage.length];
        System.arraycopy(keyUsage, 0, this.keyUsage, 0, keyUsage.length);
    }

    /**
     * Returns the criterion for the {@literal KeyUsage} extension.
     *
     * @return the boolean array in the format as returned by
     *         {@link X509Certificate#getKeyUsage()}, or {@code null} if the key
     *         usage is not to be checked.
     */
    public boolean[] getKeyUsage() {
        if (keyUsage == null) {
            return null;
        }
        boolean[] result = new boolean[keyUsage.length];
        System.arraycopy(keyUsage, 0, result, 0, keyUsage.length);
        return result;
    }

    /**
     * Sets the criterion for the {@literal ExtendedKeyUsage} extension.
     *
     * @param keyUsage
     *            the set of key usage OIDs, or {@code null} to not check it.
     * @throws IOException
     *             if one of the OIDs is invalid.
     */
    public void setExtendedKeyUsage(Set<String> keyUsage)
                             throws IOException {
        extendedKeyUsage = null;
        if ((keyUsage == null) || (keyUsage.size() == 0)) {
            return;
        }
        HashSet<String> key_u = new HashSet<String>();
        for (String usage : keyUsage) {
            checkOID(usage);
            key_u.add(usage);
        }
        extendedKeyUsage = Collections.unmodifiableSet(key_u);
    }

    /**
     * Returns the criterion for the {@literal ExtendedKeyUsage} extension.
     *
     * @return the set of key usage OIDs, or {@code null} if it's not to be
     *         checked.
     */
    public Set<String> getExtendedKeyUsage() {
        return extendedKeyUsage;
    }

    /**
     * Sets the flag for the matching behavior for subject alternative names.
     * <p>
     * The flag indicates whether a certificate must contain all or at least one
     * of the subject alternative names specified by {@link
     * #setSubjectAlternativeNames} or {@link #addSubjectAlternativeName}.
     *
     * @param matchAllNames
     *            {@code true} if a certificate must contain all of the
     *            specified subject alternative names, otherwise {@code false}.
     */
    public void setMatchAllSubjectAltNames(boolean matchAllNames) {
        this.matchAllNames = matchAllNames;
    }

    /**
     * Returns the flag for the matching behavior for subject alternative names.
     * <p>
     * The flag indicates whether a certificate must contain all or at least one
     * of the subject alternative names specified by {@link
     * #setSubjectAlternativeNames} or {@link #addSubjectAlternativeName}.
     *
     * @return {@code true} if a certificate must contain all of the specified
     *         subject alternative names, otherwise {@code false}.
     */
    public boolean getMatchAllSubjectAltNames() {
        return matchAllNames;
    }

    /**
     * Sets the criterion for subject alternative names.
     * <p>
     * the certificate must contain all or at least one of the specified subject
     * alternative names. The behavior is specified by
     * {@link #getMatchAllSubjectAltNames}.
     * <p>
     * The specified parameter {@code names} is a collection with an entry for
     * each name to be included in the criterion. The name is specified as a
     * {@code List}, the first entry must be an {@code Integer} specifying the
     * name type (0-8), the second entry must be a {@code String} or a byte
     * array specifying the name (in string or ASN.1 DER encoded form)
     *
     * @param names
     *            the names collection or {@code null} to not perform this check.
     * @throws IOException
     *             if the decoding of a name fails.
     */
    public void setSubjectAlternativeNames(Collection<List<?>> names) throws IOException {
        subjectAltNames = null;
        if ((names == null) || (names.size() == 0)) {
            return;
        }
        for (List<?> name : names) {
            int tag = (Integer) name.get(0);
            Object value = name.get(1);
            if (value instanceof String) {
                addSubjectAlternativeName(tag, (String) value);
            } else if (value instanceof byte[]) {
                addSubjectAlternativeName(tag, (byte[]) value);
            } else {
                throw new IOException("name neither a String nor a byte[]");
            }
        }
    }

    /**
     * Adds a subject alternative name to the respective criterion.
     *
     * @param tag
     *            the type of the name
     * @param name
     *            the name in string format.
     * @throws IOException
     *             if parsing the name fails.
     */
    public void addSubjectAlternativeName(int tag, String name)
                                                       throws IOException {
        GeneralName alt_name = new GeneralName(tag, name);
        // create only if there was not any errors
        if (subjectAltNames == null) {
            subjectAltNames = new ArrayList[9];
        }
        if (subjectAltNames[tag] == null) {
            subjectAltNames[tag] = new ArrayList<GeneralName>();
        }
        subjectAltNames[tag].add(alt_name);
    }

    /**
     * Adds a subject alternative name to the respective criterion.
     *
     * @param tag
     *            the type of the name.
     * @param name
     *            the name in ASN.1 DER encoded form.
     * @throws IOException
     *             if the decoding of the name fails.
     */
    public void addSubjectAlternativeName(int tag, byte[] name)
                                            throws IOException {
        GeneralName alt_name = new GeneralName(tag, name);
        // create only if there was not any errors
        if (subjectAltNames == null) {
            subjectAltNames = new ArrayList[9];
        }
        if (subjectAltNames[tag] == null) {
            subjectAltNames[tag] = new ArrayList<GeneralName>();
        }
        subjectAltNames[tag].add(alt_name);
    }

    /**
     * Returns the criterion for subject alternative names.
     * <p>
     * the certificate must contain all or at least one of the specified subject
     * alternative names. The behavior is specified by
     * {@link #getMatchAllSubjectAltNames}.
     * <p>
     * The subject alternative names is a collection with an entry for each name
     * included in the criterion. The name is specified as a {@code List}, the
     * first entry is an {@code Integer} specifying the name type (0-8), the
     * second entry is byte array specifying the name in ASN.1 DER encoded form)
     *
     * @return the names collection or {@code null} if none specified.
     */
    public Collection<List<?>> getSubjectAlternativeNames() {
        if (subjectAltNames == null) {
            return null;
        }
        ArrayList<List<?>> result = new ArrayList<List<?>>();
        for (int tag=0; tag<9; tag++) {
            if (subjectAltNames[tag] != null) {
                for (int name=0; name<subjectAltNames[tag].size(); name++) {
                    List<Object> list = new ArrayList<Object>(2);
                    list.add(tag);
                    list.add(subjectAltNames[tag].get(name));
                    result.add(list);
                }
            }
        }
        return result;
    }

    /**
     * Sets the criterion for the name constraints.
     * <p>
     * The certificate must constraint subject and subject alternative names
     * that match the specified name constraints.
     * <p>
     * The name constraints in ASN.1:
     *
     * <pre>
     * NameConstraints ::= SEQUENCE {
     *        permittedSubtrees       [0]     GeneralSubtrees OPTIONAL,
     *        excludedSubtrees        [1]     GeneralSubtrees OPTIONAL }
     *
     * GeneralSubtrees ::= SEQUENCE SIZE (1..MAX) OF GeneralSubtree
     *
     * GeneralSubtree ::= SEQUENCE {
     *        base                    GeneralName,
     *        minimum         [0]     BaseDistance DEFAULT 0,
     *        maximum         [1]     BaseDistance OPTIONAL }
     *
     * BaseDistance ::= INTEGER (0..MAX)
     *
     * GeneralName ::= CHOICE {
     *        otherName                       [0]     OtherName,
     *        rfc822Name                      [1]     IA5String,
     *        dNSName                         [2]     IA5String,
     *        x400Address                     [3]     ORAddress,
     *        directoryName                   [4]     Name,
     *        ediPartyName                    [5]     EDIPartyName,
     *        uniformResourceIdentifier       [6]     IA5String,
     *        iPAddress                       [7]     OCTET STRING,
     *        registeredID                    [8]     OBJECT IDENTIFIER}
     *
     * </pre>
     *
     * @param bytes
     *            the name constraints in ASN.1 DER encoded format, or null to
     *            not check any constraints.
     * @throws IOException
     *             if decoding the name constraints fail.
     */
    public void setNameConstraints(byte[] bytes) throws IOException {
        this.nameConstraints = (bytes == null)
            ? null
            : (NameConstraints) NameConstraints.ASN1.decode(bytes);
    }

    /**
     * Returns the criterion for the name constraints.
     *
     * @return the name constraints or {@code null} if none specified.
     * @see #setNameConstraints
     */
    public byte[] getNameConstraints() {
        return (nameConstraints == null)
            ? null
            : nameConstraints.getEncoded();
    }

    /**
     * Sets the criterion for the basic constraints extension.
     * <p>
     * A value greater than or equal to zero indicates that a certificate must
     * include a basic constraints extension with a path length of a least that
     * value. A value of {@code -2} indicates that only end-entity certificates
     * are accepted. A value of {@code -1} indicates that no check is done.
     *
     * @param pathLen
     *            the value specifying the criterion.
     * @throws IllegalArgumentException
     *             if {@code pathLen} is less than {@code -2}.
     */
    public void setBasicConstraints(int pathLen) {
        if (pathLen < -2) {
            throw new IllegalArgumentException("pathLen < -2");
        }
        this.pathLen = pathLen;
    }

    /**
     * Returns the criterion for the basic constraints extension.
     * <p>
     * A value greater than or equal to zero indicates that a certificate must
     * include a basic constraints extension with a path length of a least that
     * value. A value of {@code -2} indicates that only end-entity certificates
     * are accepted. A value of {@code -1} indicates that no check is done.
     *
     * @return the value of the criterion.
     */
    public int getBasicConstraints() {
        return pathLen;
    }

    /**
     * Sets the criterion for the policy constraint.
     * <p>
     * The certificate must have at least one of the specified certificate
     * policy extensions. For an empty set the certificate must have at least
     * some policies in its policy extension.
     *
     * @param policies
     *            the certificate policy OIDs, an empty set, or {@code null} to
     *            not perform this check.
     * @throws IOException
     *             if parsing the specified OIDs fails.
     */
    public void setPolicy(Set<String> policies) throws IOException {
        if (policies == null) {
            this.policies = null;
            return;
        }
        HashSet<String> pols = new HashSet<String>(policies.size());
        for (String certPolicyId : policies) {
            checkOID(certPolicyId);
            pols.add(certPolicyId);
        }
        this.policies = Collections.unmodifiableSet(pols);
    }

    /**
     * Returns the criterion for the policy constraint.
     * <p>
     * The certificate must have at least one of the certificate policy
     * extensions. For an empty set the certificate must have at least some
     * policies in its policy extension.
     *
     * @return the certificate policy OIDs, an empty set, or {@code null} if not
     *         to be checked.
     */
    public Set<String> getPolicy() {
        return policies;
    }

    /**
     * Adds a {@literal "pathToName"} to the respective criterion.
     *
     * @param type
     *            the type of the name.
     * @param name
     *            the name in string format.
     * @throws IOException
     *             if parsing fails.
     * @see #setPathToNames
     */
    public void addPathToName(int type, String name) throws IOException {
        GeneralName path_name = new GeneralName(type, name);
        // create only if there was not any errors
        if (pathToNames == null) {
            pathToNames = new ArrayList<GeneralName>();
        }
        pathToNames.add(path_name);
    }

    /**
     * Sets the criterion for the pathToNames constraint.
     * <p>
     * This allows to specify the complete set of names, a certificate's name
     * constraints must permit.
     * <p>
     * The specified parameter {@code names} is a collection with an entry for
     * each name to be included in the criterion. The name is specified as a
     * {@code List}, the first entry must be an {@code Integer} specifying the
     * name type (0-8), the second entry must be a {@code String} or a byte
     * array specifying the name (in string or ASN.1 DER encoded form)
     *
     * @param names
     *            the names collection or {@code null} to not perform this
     *            check.
     * @throws IOException
     *             if decoding fails.
     */
    public void setPathToNames(Collection<List<?>> names) throws IOException {
        pathToNames = null;
        if ((names == null) || (names.size() == 0)) {
            return;
        }
        for (List<?> name : names) {
            int tag = (Integer) name.get(0);
            Object value = name.get(1);
            if (value instanceof String) {
                addPathToName(tag, (String) value);
            } else if (value instanceof byte[]) {
                addPathToName(tag, (byte[]) value);
            } else {
                throw new IOException("name neither a String nor a byte[]");
            }
        }
    }

    /**
     * Adds a {@literal "pathToName"} to the respective criterion.
     *
     * @param type
     *            the type of the name
     * @param name
     *            the name in ASN.1 DER encoded form.
     * @throws IOException
     *             if decoding fails.
     * @see #setPathToNames
     */
    public void addPathToName(int type, byte[] name) throws IOException {
        GeneralName path_name= new GeneralName(type, name);
        // create only if there was not any errors
        if (pathToNames == null) {
            pathToNames = new ArrayList<GeneralName>();
        }
        pathToNames.add(path_name);
    }

    /**
     * Returns the criterion for the pathToNames constraint.
     * <p>
     * The constraint is a collection with an entry for each name to be included
     * in the criterion. The name is specified as a {@code List}, the first
     * entry is an {@code Integer} specifying the name type (0-8), the second
     * entry is a byte array specifying the name in ASN.1 DER encoded form.
     *
     * @return the pathToNames constraint or {@code null} if none specified.
     */
    public Collection<List<?>> getPathToNames() {
        if (pathToNames == null) {
            return null;
        }
        Collection<List<?>> result = new ArrayList<List<?>>();
        for (GeneralName name : pathToNames) {
            result.add(name.getAsList());
        }
        return result;
    }

    /**
     * Returns a string representation of this {@code X509CertSelector}
     * instance.
     *
     * @return a string representation of this {@code X509CertSelector}
     *         instance.
     */
    public String toString() {
        // For convenient reading of the string representation
        // all of the fields named according to the rfc 3280
        // (http://www.ietf.org/rfc/rfc3280.txt).

        StringBuilder result = new StringBuilder();
        result.append("X509CertSelector: \n[");
        if (this.certificateEquals != null) {
            result.append("\n  certificateEquals: ").append(certificateEquals);
        }
        if (this.serialNumber != null) {
            result.append("\n  serialNumber: ").append(serialNumber);
        }
        if (this.issuer != null) {
            result.append("\n  issuer: ").append(issuer);
        }
        if (this.subject != null) {
            result.append("\n  subject: ").append(subject);
        }
        if (this.subjectKeyIdentifier != null) {
            result.append("\n  subjectKeyIdentifier: ")
                    .append(Array.getBytesAsString(subjectKeyIdentifier));
        }
        if (this.authorityKeyIdentifier != null) {
            result.append("\n  authorityKeyIdentifier: ")
                    .append(Array.getBytesAsString(authorityKeyIdentifier));
        }
        if (this.certificateValid != null) {
            result.append("\n  certificateValid: ").append(certificateValid);
        }
        if (this.subjectPublicKeyAlgID != null) {
            result.append("\n  subjectPublicKeyAlgID: ").append(subjectPublicKeyAlgID);
        }
        if (this.privateKeyValid != null) {
            result.append("\n  privateKeyValid: ").append(privateKeyValid);
        }
        if (this.subjectPublicKey != null) {
            result.append("\n  subjectPublicKey: ")
                    .append(Array.getBytesAsString(subjectPublicKey));
        }
        if (this.keyUsage != null) {
            result.append("\n  keyUsage: \n  [");
            String[] kuNames = new String[] {
                "digitalSignature", "nonRepudiation", "keyEncipherment",
                "dataEncipherment", "keyAgreement", "keyCertSign", "cRLSign",
                "encipherOnly", "decipherOnly"
            };
            for (int i=0; i<9; i++) {
                if (keyUsage[i]) {
                    result.append("\n    ").append(kuNames[i]);
                }
            }
            result.append("\n  ]");
        }
        if (this.extendedKeyUsage != null) {
            result.append("\n  extendedKeyUsage: ").append(extendedKeyUsage.toString());
        }
        result.append("\n  matchAllNames: ").append(matchAllNames);
        result.append("\n  pathLen: ").append(pathLen);
        if (this.subjectAltNames != null) {
            result.append("\n  subjectAltNames:  \n  [");
            for (int i=0; i<9; i++) {
                List<GeneralName> names = subjectAltNames[i];
                if (names != null) {
                    int size = names.size();
                    for (GeneralName generalName : names) {
                        result.append("\n    ").append(generalName.toString());
                    }
                }
            }
            result.append("\n  ]");
        }
        if (this.nameConstraints != null) {
        }
        if (this.policies != null) {
            result.append("\n  policies: ").append(policies.toString());
        }
        if (this.pathToNames != null) {
            result.append("\n  pathToNames:  \n  [");
            for (GeneralName generalName : pathToNames) {
                result.append("\n    ").append(generalName.toString());
            }
        }
        result.append("\n]");
        return result.toString();
    }

    private byte[] getExtensionValue(X509Certificate cert, String oid) {
        try {
            byte[] bytes = cert.getExtensionValue(oid);
            if (bytes == null) {
                return null;
            }
            return (byte[]) ASN1OctetString.getInstance().decode(bytes);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Returns whether the specified certificate matches all the criteria
     * collected in this instance.
     *
     * @param certificate
     *            the certificate to check.
     * @return {@code true} if the certificate matches all the criteria,
     *         otherwise {@code false}.
     */
    public boolean match(Certificate certificate) {
        if (! (certificate instanceof X509Certificate)) {
            return false;
        }

        X509Certificate cert = (X509Certificate) certificate;
        if ((certificateEquals != null) &&
            !certificateEquals.equals(cert)) {
            return false;
        }
        if ((serialNumber != null) &&
            !serialNumber.equals(cert.getSerialNumber())) {
            return false;
        }
        if ((issuer != null) &&
            !issuer.equals(cert.getIssuerX500Principal())) {
            return false;
        }
        if ((subject != null) &&
            !subject.equals(cert.getSubjectX500Principal())) {
            return false;
        }
        if ((subjectKeyIdentifier != null) &&
            !Arrays.equals(subjectKeyIdentifier,
            // Here and later all of the extension OIDs
            // are taken from rfc 3280 (http://www.ietf.org/rfc/rfc3280.txt)
                           getExtensionValue(cert, "2.5.29.14"))) {
            return false;
        }
        if ((authorityKeyIdentifier != null) &&
            !Arrays.equals(authorityKeyIdentifier,
                           getExtensionValue(cert, "2.5.29.35"))) {
            return false;
        }
        if (certificateValid != null) {
            try {
                cert.checkValidity(certificateValid);
            } catch(CertificateExpiredException e) {
                return false;
            } catch(CertificateNotYetValidException e) {
                return false;
            }
        }
        if (privateKeyValid != null) {
            try {
                byte[] bytes = getExtensionValue(cert, "2.5.29.16");
                if (bytes == null) {
                    return false;
                }
                PrivateKeyUsagePeriod pkup = (PrivateKeyUsagePeriod)
                                    PrivateKeyUsagePeriod.ASN1.decode(bytes);
                Date notBefore = pkup.getNotBefore();
                Date notAfter = pkup.getNotAfter();
                if ((notBefore == null) && (notAfter == null)) {
                    return false;
                }
                if ((notBefore != null)
                    && notBefore.compareTo(privateKeyValid) > 0) {
                    return false;
                }
                if ((notAfter != null)
                    && notAfter.compareTo(privateKeyValid) < 0) {
                    return false;
                }
            } catch (IOException e) {
                return false;
            }
        }
        if (subjectPublicKeyAlgID  != null) {
            try {
                byte[] encoding = cert.getPublicKey().getEncoded();
                AlgorithmIdentifier ai = ((SubjectPublicKeyInfo)
                        SubjectPublicKeyInfo.ASN1.decode(encoding))
                        .getAlgorithmIdentifier();
                if (!subjectPublicKeyAlgID.equals(ai.getAlgorithm())) {
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        if (subjectPublicKey != null) {
            if (!Arrays.equals(subjectPublicKey,
                               cert.getPublicKey().getEncoded())) {
                return false;
            }
        }
        if (keyUsage != null) {
            boolean[] ku = cert.getKeyUsage();
            if (ku != null) {
                int i = 0;
                int min_length = (ku.length < keyUsage.length) ? ku.length
                        : keyUsage.length;
                for (; i < min_length; i++) {
                    if (keyUsage[i] && !ku[i]) {
                        // the specified keyUsage allows,
                        // but certificate does not.
                        return false;
                    }
                }
                for (; i<keyUsage.length; i++) {
                    if (keyUsage[i]) {
                        return false;
                    }
                }
            }
        }
        if (extendedKeyUsage != null) {
            try {
                List keyUsage = cert.getExtendedKeyUsage();
                if (keyUsage != null) {
                    if (!keyUsage.containsAll(extendedKeyUsage)) {
                        return false;
                    }
                }
            } catch (CertificateParsingException e) {
                return false;
            }
        }
        if (pathLen != -1) {
            int p_len = cert.getBasicConstraints();
            if ((pathLen < 0) && (p_len >= 0)) {
                // need end-entity but got CA
                return false;
            }
            if ((pathLen > 0) && (pathLen > p_len)) {
                // allowed _pathLen is small
                return false;
            }
        }
        if (subjectAltNames != null) {
            PASSED:
            try {
                byte[] bytes = getExtensionValue(cert, "2.5.29.17");
                if (bytes == null) {
                    return false;
                }
                List<GeneralName> sans = ((GeneralNames) GeneralNames.ASN1.decode(bytes))
                            .getNames();
                if ((sans == null) || (sans.size() == 0)) {
                    return false;
                }
                boolean[][] map = new boolean[9][];
                // initialize the check map
                for (int i=0; i<9; i++) {
                    map[i] = (subjectAltNames[i] == null)
                            ? EmptyArray.BOOLEAN : new boolean[subjectAltNames[i].size()];
                }
                for (GeneralName name : sans) {
                    int tag = name.getTag();
                    for (int i = 0; i < map[tag].length; i++) {
                        if (subjectAltNames[tag].get(i).equals(name)) {
                            if (!matchAllNames) {
                                break PASSED;
                            }
                            map[tag][i] = true;
                        }
                    }
                }
                if (!matchAllNames) {
                    // there was not any match
                    return false;
                }
                // else check the map
                for (int tag=0; tag<9; tag++) {
                    for (int name=0; name<map[tag].length; name++) {
                        if (!map[tag][name]) {
                            return false;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        if (nameConstraints != null) {
            if (!nameConstraints.isAcceptable(cert)) {
                return false;
            }
        }
        if (policies != null) {
            byte[] bytes = getExtensionValue(cert, "2.5.29.32");
            if (bytes == null) {
                return false;
            }
            if (policies.size() == 0) {
                // if certificate has such extension than it has at least
                // one policy in it.
                return true;
            }
            PASSED:
            try {
                List<PolicyInformation> policyInformations
                        = ((CertificatePolicies) CertificatePolicies.ASN1.decode(bytes))
                        .getPolicyInformations();
                for (PolicyInformation policyInformation : policyInformations) {
                    if (policies.contains(policyInformation.getPolicyIdentifier())) {
                        break PASSED;
                    }
                }
                return false;
            } catch (IOException e) {
                // the extension is invalid
                return false;
            }
        }
        if (pathToNames != null) {
            byte[] bytes = getExtensionValue(cert, "2.5.29.30");
            if (bytes != null) {
                NameConstraints nameConstraints;
                try {
                    nameConstraints =
                        (NameConstraints) NameConstraints.ASN1.decode(bytes);
                } catch (IOException e) {
                    // the extension is invalid;
                    return false;
                }
                if (!nameConstraints.isAcceptable(pathToNames)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Clones this {@code X509CertSelector} instance.
     *
     * @return the cloned instance.
     */
    public Object clone() {
        X509CertSelector result;

        try {
            result = (X509CertSelector) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }

        if (this.subjectKeyIdentifier != null) {
            result.subjectKeyIdentifier =
                new byte[this.subjectKeyIdentifier.length];
            System.arraycopy(this.subjectKeyIdentifier, 0,
                    result.subjectKeyIdentifier, 0,
                    this.subjectKeyIdentifier.length);
        }
        if (this.authorityKeyIdentifier != null) {
            result.authorityKeyIdentifier =
                new byte[this.authorityKeyIdentifier.length];
            System.arraycopy(this.authorityKeyIdentifier, 0,
                    result.authorityKeyIdentifier, 0,
                    this.authorityKeyIdentifier.length);
        }
        if (this.subjectPublicKey != null) {
            result.subjectPublicKey = new byte[this.subjectPublicKey.length];
            System.arraycopy(this.subjectPublicKey, 0, result.subjectPublicKey,
                    0, this.subjectPublicKey.length);
        }
        if (this.keyUsage != null) {
            result.keyUsage = new boolean[this.keyUsage.length];
            System.arraycopy(this.keyUsage, 0, result.keyUsage, 0,
                    this.keyUsage.length);
        }
        result.extendedKeyUsage = (this.extendedKeyUsage == null)
            ? null
            : new HashSet<String>(this.extendedKeyUsage);
        if (this.subjectAltNames != null) {
            result.subjectAltNames = new ArrayList[9];
            for (int i=0; i<9; i++) {
                if (this.subjectAltNames[i] != null) {
                    result.subjectAltNames[i] =
                        new ArrayList<GeneralName>(this.subjectAltNames[i]);
                }
            }
        }
        result.policies = (this.policies == null) ? null : new HashSet<String>(this.policies);
        result.pathToNames = (this.pathToNames == null)
            ? null
            : new ArrayList<GeneralName>(this.pathToNames);
        return result;
    }
}
