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

package javax.net.ssl;

import java.net.InetAddress;
import java.security.cert.Certificate;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * A HostnameVerifier that works the same way as Curl and Firefox.
 *
 * <p>The hostname must match either the first CN, or any of the subject-alts.
 * A wildcard can occur in the CN, and in any of the subject-alts.
 *
 * @author Julius Davies
 */
class DefaultHostnameVerifier implements HostnameVerifier {

    /**
     * This contains a list of 2nd-level domains that aren't allowed to
     * have wildcards when combined with country-codes.
     * For example: [*.co.uk].
     *
     * <p>The [*.co.uk] problem is an interesting one.  Should we just hope
     * that CA's would never foolishly allow such a certificate to happen?
     * Looks like we're the only implementation guarding against this.
     * Firefox, Curl, Sun Java 1.4, 5, 6 don't bother with this check.
     */
    private static final String[] BAD_COUNTRY_2LDS =
          { "ac", "co", "com", "ed", "edu", "go", "gouv", "gov", "info",
            "lg", "ne", "net", "or", "org" };

    static {
        // Just in case developer forgot to manually sort the array.  :-)
        Arrays.sort(BAD_COUNTRY_2LDS);
    }

    public final boolean verify(String host, SSLSession session) {
        Certificate[] certs;
        try {
            certs = session.getPeerCertificates();
        } catch (SSLException e) {
            return false;
        }

        X509Certificate x509 = (X509Certificate) certs[0];

        // We can be case-insensitive when comparing the host we used to
        // establish the socket to the hostname in the certificate.
        String hostName = host.trim().toLowerCase(Locale.ENGLISH);

        // Verify the first CN provided. Other CNs are ignored. Firefox, wget,
        // curl, and Sun Java work this way.
        String firstCn = getFirstCn(x509);
        if (matches(hostName, firstCn)) {
            return true;
        }

        for (String cn : getDNSSubjectAlts(x509)) {
            if (matches(hostName, cn)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns true if {@code hostname} matches {@code cn}.
     *
     * @param hostName a trimmed, lowercase hostname to verify
     * @param cn a certificate CN or DNS subject alt. Either a literal name or
     *     a wildcard of the form "*.google.com".
     */
    private boolean matches(String hostName, String cn) {
        if (cn == null) {
            return false;
        }

        // Don't trim the CN, though!
        cn = cn.toLowerCase(Locale.ENGLISH);

        if (cn.startsWith("*.")) {
            // When a wildcard matches, also check that the wildcard is legit
            //   - Wildcards must contain at least two dots: "*.google.com"
            //   - Wildcards must be for private domains. No "*.co.uk" etc.
            //   - Wildcards must not match IP addresses: "*.8.8"
            int matchLength = cn.length() - 1;
            return hostName.regionMatches(hostName.length() - matchLength, cn, 1, matchLength)
                    && cn.indexOf('.', 2) != -1
                    && acceptableCountryWildcard(cn)
                    && !InetAddress.isNumeric(hostName);
        } else {
            return hostName.equals(cn);
        }
    }

    private boolean acceptableCountryWildcard(String cn) {
        int cnLen = cn.length();
        if (cnLen >= 7 && cnLen <= 9) {
            // Look for the '.' in the 3rd-last position:
            if (cn.charAt(cnLen - 3) == '.') {
                // Trim off the [*.] and the [.XX].
                String s = cn.substring(2, cnLen - 3);
                // And test against the sorted array of bad 2lds:
                int x = Arrays.binarySearch(BAD_COUNTRY_2LDS, s);
                return x < 0;
            }
        }
        return true;
    }

    private String getFirstCn(X509Certificate cert) {
        /*
         * Sebastian Hauer's original StrictSSLProtocolSocketFactory used
         * getName() and had the following comment:
         *
         *      Parses a X.500 distinguished name for the value of the
         *     "Common Name" field.  This is done a bit sloppy right
         *     now and should probably be done a bit more according to
         *     <code>RFC 2253</code>.
         *
         * I've noticed that toString() seems to do a better job than
         * getName() on these X500Principal objects, so I'm hoping that
         * addresses Sebastian's concern.
         *
         * For example, getName() gives me this:
         * 1.2.840.113549.1.9.1=#16166a756c6975736461766965734063756362632e636f6d
         *
         * whereas toString() gives me this:
         * EMAILADDRESS=juliusdavies@cucbc.com
         *
         * Looks like toString() even works with non-ascii domain names!
         * I tested it with "&#x82b1;&#x5b50;.co.jp" and it worked fine.
         */
        String subjectPrincipal = cert.getSubjectX500Principal().toString();
        for (String token : subjectPrincipal.split(",")) {
            int x = token.indexOf("CN=");
            if (x >= 0) {
                return token.substring(x + 3);
            }
        }
        return null;
    }

    /**
     * Returns all SubjectAlt DNS names from an X509Certificate.
     *
     * <p>Note: Java doesn't appear able to extract international characters
     * from the SubjectAlts.  It can only extract international characters
     * from the CN field.
     *
     * <p>(Or maybe the version of OpenSSL I'm using to test isn't storing the
     * international characters correctly in the SubjectAlts?).
     */
    private List<String> getDNSSubjectAlts(X509Certificate cert) {
        Collection<List<?>> subjectAlternativeNames;
        try {
            subjectAlternativeNames = cert.getSubjectAlternativeNames();
        } catch (CertificateParsingException cpe) {
            System.logI("Error parsing certificate", cpe);
            return Collections.emptyList();
        }

        if (subjectAlternativeNames == null) {
            return Collections.emptyList();
        }

        List<String> subjectAltList = new ArrayList<String>();
        for (List<?> pair : subjectAlternativeNames) {
            int type = (Integer) pair.get(0);
            // If type is 2, then we've got a dNSName
            if (type == 2) {
                subjectAltList.add((String) pair.get(1));
            }
        }
        return subjectAltList;
    }
}
