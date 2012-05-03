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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import javax.security.auth.x500.X500Principal;
import org.apache.harmony.security.asn1.ASN1Integer;
import org.apache.harmony.security.asn1.ASN1OctetString;
import org.apache.harmony.security.x501.Name;

/**
 * A CRL selector ({@code CRLSelector} for selecting {@code
 * X509CRL}s that match the specified criteria.
 * <p>
 * When constructed, all criteria are set to default values that will match any
 * {@code X509CRL}.
 */
public class X509CRLSelector implements CRLSelector {

    // issuerNames criterion:
    // contains X.500 distinguished names in CANONICAL format
    private ArrayList<String> issuerNames;
    // contains X500Principal objects corresponding to the names
    // from issuerNames collection (above)
    private ArrayList<X500Principal> issuerPrincipals;
    // minCRLNumber criterion
    private BigInteger minCRL;
    // maxCRLNumber criterion
    private BigInteger maxCRL;
    // dateAndTime criterion
    private long dateAndTime = -1;
    // the certificate being checked
    private X509Certificate certificateChecking;

    /**
     * Creates a new {@code X509CertSelector}.
     */
    public X509CRLSelector() { }

    /**
     * Sets the criterion for the issuer distinguished names.
     * <p>
     * The CRL issuer must match at least one of the specified distinguished
     * names.
     *
     * @param issuers
     *            the list of issuer distinguished names to match, or {@code
     *            null} if any issuer distinguished name will do.
     */
    public void setIssuers(Collection<X500Principal> issuers) {
        if (issuers == null) {
            issuerNames = null;
            issuerPrincipals = null;
            return;
        }
        issuerNames = new ArrayList<String>(issuers.size());
        issuerPrincipals = new ArrayList<X500Principal>(issuers);
        for (X500Principal issuer: issuers) {
            issuerNames.add(issuer.getName(X500Principal.CANONICAL));
        }
    }

    /**
     * <b>Do not use:</b> use {@link #setIssuers(Collection)} or one of
     * {@link #addIssuerName} instead. Sets the criterion for the issuer
     * distinguished names.
     * <p>
     * The CRL issuer must match at least one of the specified distinguished
     * names.
     * <p>
     * The specified parameter {@code names} is a collection with an entry for
     * each name to be included in the criterion. The name is specified as a
     * {@code String} or a byte array specifying the name (in RFC 2253 or ASN.1
     * DER encoded form)
     *
     * @param names
     *            the list of issuer distinguished names to match, or {@code
     *            null} if any issuer distinguished name will do.
     * @throws IOException
     *             if parsing fails.
     */
    public void setIssuerNames(Collection<?> names) throws IOException {
        if (names == null) {
            issuerNames = null;
            issuerPrincipals = null;
            return;
        }
        if (names.size() == 0) {
            return;
        }
        issuerNames = new ArrayList<String>(names.size());
        for (Object name: names) {
            if (name instanceof String) {
                issuerNames.add(
                        new Name((String) name).getName(
                            X500Principal.CANONICAL));
            } else if (name instanceof byte[]) {
                issuerNames.add(
                        new Name((byte[]) name).getName(
                            X500Principal.CANONICAL));
            } else {
                throw new IOException("name neither a String nor a byte[]");
            }
        }
    }

    /**
     * Adds an issuer to the criterion for the issuer distinguished names.
     * <p>
     * The CRL issuer must match at least one of the specified distinguished
     * names.
     *
     * @param issuer
     *            the issuer to add to the criterion
     */
    public void addIssuer(X500Principal issuer) {
        if (issuer == null) {
            throw new NullPointerException("issuer == null");
        }
        if (issuerNames == null) {
            issuerNames = new ArrayList<String>();
        }
        String name = issuer.getName(X500Principal.CANONICAL);
        if (!issuerNames.contains(name)) {
            issuerNames.add(name);
        }
        if (issuerPrincipals == null) {
            issuerPrincipals = new ArrayList<X500Principal>(issuerNames.size());
        }
        // extend the list of issuer Principals
        int size = issuerNames.size() - 1;
        for (int i=issuerPrincipals.size(); i<size; i++) {
            issuerPrincipals.add(new X500Principal(issuerNames.get(i)));
        }
        issuerPrincipals.add(issuer);
    }

    /**
     * <b>Do not use:</b>, use {@link #addIssuer(X500Principal)} or
     * {@link #addIssuerName(byte[])} instead. It can fail to match some CRLs
     * because of a loss of encoding information in a RFC 2253 string.
     * <p>
     * Adds an issuer to the criterion for the issuer distinguished names. The
     * CRK issuer must match at least one of the specified distinguished names.
     *
     * @param iss_name
     *            the RFC 2253 encoded name.
     * @throws IOException
     *             if parsing fails.
     */
    public void addIssuerName(String iss_name) throws IOException {
        if (issuerNames == null) {
            issuerNames = new ArrayList<String>();
        }

        if (iss_name == null) {
            iss_name = "";
        }

        String name = new Name(iss_name).getName(X500Principal.CANONICAL);
        if (!issuerNames.contains(name)) {
            issuerNames.add(name);
        }
    }

    /**
     * Adds an issuer to the criterion for the issuer distinguished names.
     * <p>
     * The CRL issuer must match at least one of the specified distinguished
     * names.
     *
     * @param iss_name
     *            the issuer to add to the criterion in ASN.1 DER encoded form.
     * @throws IOException
     *             if parsing fails.
     */
    public void addIssuerName(byte[] iss_name) throws IOException {
        if (iss_name == null) {
            throw new NullPointerException("iss_name == null");
        }
        if (issuerNames == null) {
            issuerNames = new ArrayList<String>();
        }
        String name = new Name(iss_name).getName(X500Principal.CANONICAL);
        if (!issuerNames.contains(name)) {
            issuerNames.add(name);
        }
    }

    /**
     * Sets the criterion for the minimum CRL number.
     * <p>
     * The CRL must have a number extension with a value greater than or equal
     * to the specified parameter.
     *
     * @param minCRL
     *            the minimum CRL number or null to not check the minimum CRL
     *            number
     */
    public void setMinCRLNumber(BigInteger minCRL) {
        this.minCRL = minCRL;
    }

    /**
     * Sets the criterion for the maximum CRL number.
     * <p>
     * The CRL must have a number extension with a value less than or equal to
     * the specified parameter.
     *
     * @param maxCRL
     *            the maximum CRL number or null to not check the maximum CRL
     *            number.
     */
    public void setMaxCRLNumber(BigInteger maxCRL) {
        this.maxCRL = maxCRL;
    }

    /**
     * Sets the criterion for the CRL update period.
     * <p>
     * The CRL's {@code thisUpdate} value must be equal or before the specified
     * date and the {@code nextUpdate} value must be after the specified date.
     *
     * @param dateAndTime
     *            the date to search for valid CRL's or {@code null} to not
     *            check the date.
     */
    public void setDateAndTime(Date dateAndTime) {
        if (dateAndTime == null) {
            this.dateAndTime = -1;
            return;
        }
        this.dateAndTime = dateAndTime.getTime();
    }

    /**
     * Sets a certificate hint to find CRLs. It's not a criterion but may help
     * finding relevant CRLs.
     *
     * @param cert
     *            the certificate hint or {@code null}.
     */
    public void setCertificateChecking(X509Certificate cert) {
        this.certificateChecking = cert;
    }

    /**
     * Returns the criterion for the issuer distinguished names.
     * <p>
     * The CRL issuer must match at least one of the distinguished names.
     *
     * @return the unmodifiable list of issuer distinguished names to match, or
     *         {@code null} if any issuer distinguished name will do.
     */
    public Collection<X500Principal> getIssuers() {
        if (issuerNames == null) {
            return null;
        }
        if (issuerPrincipals == null) {
            issuerPrincipals = new ArrayList<X500Principal>(issuerNames.size());
        }
        int size = issuerNames.size();
        // extend the list of issuer Principals
        for (int i=issuerPrincipals.size(); i<size; i++) {
            issuerPrincipals.add(new X500Principal(issuerNames.get(i)));
        }
        return Collections.unmodifiableCollection(issuerPrincipals);
    }

    /**
     * Returns the criterion for the issuer distinguished names.
     * <p>
     * The CRL issuer must match at least one of the distinguished names.
     *
     * @return a copy of the list of issuer distinguished names to
     *         match, or {@code null} if any issuer distinguished name
     *         will do. The elements may be strings or ASN.1 DER
     *         encoded byte arrays.
     */
    public Collection<Object> getIssuerNames() {
        if (issuerNames == null) {
            return null;
        }
        return (Collection<Object>) issuerNames.clone();
    }

    /**
     * Returns the criterion for the minimum CRL number.
     * <p>
     * The CRL must have a number extension with a value greater than or equal
     * to the returned value.
     *
     * @return the minimum CRL number or {@code null} if the minimum CRL number
     *         is not to be checked.
     */
    public BigInteger getMinCRL() {
        return minCRL;
    }

    /**
     * Returns the criterion for the maximum CRL number.
     * <p>
     * The CRL must have a number extension with a value less than or equal to
     * the returned value.
     *
     * @return the maximum CRL number or null if the maximum CRL number is not
     *         checked.
     */
    public BigInteger getMaxCRL() {
        return maxCRL;
    }

    /**
     * Returns the criterion for the CRL update period.
     * <p>
     * The CRL's {@code thisUpdate} value must be equal or before the returned
     * date and the {@code nextUpdate} value must be after the returned date.
     *
     * @return the date to search for valid CRL's or {@code null} if the date is
     *         not checked.
     */
    public Date getDateAndTime() {
        if (dateAndTime == -1) {
            return null;
        }
        return new Date(dateAndTime);
    }

    /**
     * Returns the certificate hint to find CRLs. It's not a criterion but may
     * help finding relevant CRLs.
     *
     * @return the certificate hint or {@code null} if none set.
     */
    public X509Certificate getCertificateChecking() {
        return certificateChecking;
    }

    /**
     * Returns a string representation of this {@code X509CRLSelector} instance.
     *
     * @return a string representation of this {@code X509CRLSelector} instance.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("X509CRLSelector:\n[");
        if (issuerNames != null) {
            result.append("\n  IssuerNames:\n  [");
            int size = issuerNames.size();
            for (int i=0; i<size; i++) {
                result.append("\n    "
                    + issuerNames.get(i));
            }
            result.append("\n  ]");
        }
        if (minCRL != null) {
            result.append("\n  minCRL: " + minCRL);
        }
        if (maxCRL != null) {
            result.append("\n  maxCRL: " + maxCRL);
        }
        if (dateAndTime != -1) {
            result.append("\n  dateAndTime: " + (new Date(dateAndTime)));
        }
        if (certificateChecking != null) {
            result.append("\n  certificateChecking: " + certificateChecking);
        }
        result.append("\n]");
        return result.toString();
    }

    /**
     * Returns whether the specified CRL matches all the criteria collected in
     * this instance.
     *
     * @param crl
     *            the CRL to check.
     * @return {@code true} if the CRL matches all the criteria, otherwise
     *         {@code false}.
     */
    public boolean match(CRL crl) {
        if (!(crl instanceof X509CRL)) {
            return false;
        }
        X509CRL crlist = (X509CRL) crl;
        if ((issuerNames != null) &&
                // the search speed depends on the class of issuerNames
                !(issuerNames.contains(
                        crlist.getIssuerX500Principal().getName(
                            X500Principal.CANONICAL)))) {
            return false;
        }
        if ((minCRL != null) || (maxCRL != null)) {
            try {
                // As specified in rfc 3280 (http://www.ietf.org/rfc/rfc3280.txt)
                // CRL Number Extension's OID is 2.5.29.20 .
                byte[] bytes = crlist.getExtensionValue("2.5.29.20");
                bytes = (byte[]) ASN1OctetString.getInstance().decode(bytes);
                BigInteger crlNumber = new BigInteger((byte[])
                        ASN1Integer.getInstance().decode(bytes));
                if ((minCRL != null) && (crlNumber.compareTo(minCRL) < 0)) {
                    return false;
                }
                if ((maxCRL != null) && (crlNumber.compareTo(maxCRL) > 0)) {
                    return false;
                }
            } catch (IOException e) {
                return false;
            }
        }
        if (dateAndTime != -1) {
            Date thisUp = crlist.getThisUpdate();
            Date nextUp = crlist.getNextUpdate();
            if ((thisUp == null) || (nextUp == null)) {
                return false;
            }
            if ((dateAndTime < thisUp.getTime())
                                || (dateAndTime > nextUp.getTime())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Clones this {@code X509CRL} instance.
     *
     * @return the cloned instance.
     */
    public Object clone() {
        X509CRLSelector result;

        try {
            result = (X509CRLSelector) super.clone();
            if (issuerNames != null) {
                result.issuerNames = new ArrayList<String>(issuerNames);
            }
        } catch (CloneNotSupportedException e) {
            result = null;
        }
        return result;
    }
}
