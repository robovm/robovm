/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package libcore.javax.security.auth.x500;

import java.io.ByteArrayInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import javax.security.auth.x500.X500Principal;
import junit.framework.TestCase;
import libcore.util.SerializationTester;


public class X500PrincipalTest extends TestCase {

    public void testSerialization() {
        String expected = "aced0005737200266a617661782e73656375726974792e617574682e7"
                + "83530302e583530305072696e636970616cf90dff3c88b877c703000078707572"
                + "00025b42acf317f8060854e002000078700000006a30683117301506035504031"
                + "30e7777772e676f6f676c652e636f6d31133011060355040a130a476f6f676c65"
                + "20496e63311630140603550407130d4d6f756e7461696e2056696577311330110"
                + "603550408130a43616c69666f726e6961310b300906035504061302555378";
        X500Principal actual = new X500Principal("C=US, "
                                                 + "ST=California, "
                                                 + "L=Mountain View, "
                                                 + "O=Google Inc, "
                                                 + "CN=www.google.com");
        new SerializationTester<X500Principal>(actual, expected).test();
    }

    /**
     * ASN1-encoded trusted certificate #946059622 for
     * Entrust.net. This certificate uses the T61String (aka
     * TeletexString or TELETEXSTRING) encoding for one
     * organizationalUnitNames:
     *
     * "www.entrust.net/CPS_2048 incorp. by ref. (limits liab.)"
     */
    private static final byte[] T61STRING_CERT = new byte[] { 48, -126, 4, 92, 48, -126, 3, 68, -96, 3, 2, 1, 2, 2, 4, 56, 99, -71, 102, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 5, 5, 0, 48, -127, -76, 49, 20, 48, 18, 6, 3, 85, 4, 10, 19, 11, 69, 110, 116, 114, 117, 115, 116, 46, 110, 101, 116, 49, 64, 48, 62, 6, 3, 85, 4, 11, 20, 55, 119, 119, 119, 46, 101, 110, 116, 114, 117, 115, 116, 46, 110, 101, 116, 47, 67, 80, 83, 95, 50, 48, 52, 56, 32, 105, 110, 99, 111, 114, 112, 46, 32, 98, 121, 32, 114, 101, 102, 46, 32, 40, 108, 105, 109, 105, 116, 115, 32, 108, 105, 97, 98, 46, 41, 49, 37, 48, 35, 6, 3, 85, 4, 11, 19, 28, 40, 99, 41, 32, 49, 57, 57, 57, 32, 69, 110, 116, 114, 117, 115, 116, 46, 110, 101, 116, 32, 76, 105, 109, 105, 116, 101, 100, 49, 51, 48, 49, 6, 3, 85, 4, 3, 19, 42, 69, 110, 116, 114, 117, 115, 116, 46, 110, 101, 116, 32, 67, 101, 114, 116, 105, 102, 105, 99, 97, 116, 105, 111, 110, 32, 65, 117, 116, 104, 111, 114, 105, 116, 121, 32, 40, 50, 48, 52, 56, 41, 48, 30, 23, 13, 57, 57, 49, 50, 50, 52, 49, 55, 53, 48, 53, 49, 90, 23, 13, 49, 57, 49, 50, 50, 52, 49, 56, 50, 48, 53, 49, 90, 48, -127, -76, 49, 20, 48, 18, 6, 3, 85, 4, 10, 19, 11, 69, 110, 116, 114, 117, 115, 116, 46, 110, 101, 116, 49, 64, 48, 62, 6, 3, 85, 4, 11, 20, 55, 119, 119, 119, 46, 101, 110, 116, 114, 117, 115, 116, 46, 110, 101, 116, 47, 67, 80, 83, 95, 50, 48, 52, 56, 32, 105, 110, 99, 111, 114, 112, 46, 32, 98, 121, 32, 114, 101, 102, 46, 32, 40, 108, 105, 109, 105, 116, 115, 32, 108, 105, 97, 98, 46, 41, 49, 37, 48, 35, 6, 3, 85, 4, 11, 19, 28, 40, 99, 41, 32, 49, 57, 57, 57, 32, 69, 110, 116, 114, 117, 115, 116, 46, 110, 101, 116, 32, 76, 105, 109, 105, 116, 101, 100, 49, 51, 48, 49, 6, 3, 85, 4, 3, 19, 42, 69, 110, 116, 114, 117, 115, 116, 46, 110, 101, 116, 32, 67, 101, 114, 116, 105, 102, 105, 99, 97, 116, 105, 111, 110, 32, 65, 117, 116, 104, 111, 114, 105, 116, 121, 32, 40, 50, 48, 52, 56, 41, 48, -126, 1, 34, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 1, 5, 0, 3, -126, 1, 15, 0, 48, -126, 1, 10, 2, -126, 1, 1, 0, -83, 77, 75, -87, 18, -122, -78, -22, -93, 32, 7, 21, 22, 100, 42, 43, 75, -47, -65, 11, 74, 77, -114, -19, -128, 118, -91, 103, -73, 120, 64, -64, 115, 66, -56, 104, -64, -37, 83, 43, -35, 94, -72, 118, -104, 53, -109, -117, 26, -99, 124, 19, 58, 14, 31, 91, -73, 30, -49, -27, 36, 20, 30, -79, -127, -87, -115, 125, -72, -52, 107, 75, 3, -15, 2, 12, -36, -85, -91, 64, 36, 0, 127, 116, -108, -95, -99, 8, 41, -77, -120, 11, -11, -121, 119, -99, 85, -51, -28, -61, 126, -41, 106, 100, -85, -123, 20, -122, -107, 91, -105, 50, 80, 111, 61, -56, -70, 102, 12, -29, -4, -67, -72, 73, -63, 118, -119, 73, 25, -3, -64, -88, -67, -119, -93, 103, 47, -58, -97, -68, 113, 25, 96, -72, 45, -23, 44, -55, -112, 118, 102, 123, -108, -30, -81, 120, -42, 101, 83, 93, 60, -42, -100, -78, -49, 41, 3, -7, 47, -92, 80, -78, -44, 72, -50, 5, 50, 85, -118, -3, -78, 100, 76, 14, -28, -104, 7, 117, -37, 127, -33, -71, 8, 85, 96, -123, 48, 41, -7, 123, 72, -92, 105, -122, -29, 53, 63, 30, -122, 93, 122, 122, 21, -67, -17, 0, -114, 21, 34, 84, 23, 0, -112, 38, -109, -68, 14, 73, 104, -111, -65, -8, 71, -45, -99, -107, 66, -63, 14, 77, -33, 111, 38, -49, -61, 24, 33, 98, 102, 67, 112, -42, -43, -64, 7, -31, 2, 3, 1, 0, 1, -93, 116, 48, 114, 48, 17, 6, 9, 96, -122, 72, 1, -122, -8, 66, 1, 1, 4, 4, 3, 2, 0, 7, 48, 31, 6, 3, 85, 29, 35, 4, 24, 48, 22, -128, 20, 85, -28, -127, -47, 17, -128, -66, -40, -119, -71, 8, -93, 49, -7, -95, 36, 9, 22, -71, 112, 48, 29, 6, 3, 85, 29, 14, 4, 22, 4, 20, 85, -28, -127, -47, 17, -128, -66, -40, -119, -71, 8, -93, 49, -7, -95, 36, 9, 22, -71, 112, 48, 29, 6, 9, 42, -122, 72, -122, -10, 125, 7, 65, 0, 4, 16, 48, 14, 27, 8, 86, 53, 46, 48, 58, 52, 46, 48, 3, 2, 4, -112, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 5, 5, 0, 3, -126, 1, 1, 0, 89, 71, -84, 33, -124, -118, 23, -55, -100, -119, 83, 30, -70, -128, -123, 26, -58, 60, 78, 62, -79, -100, -74, 124, -58, -110, 93, 24, 100, 2, -29, -45, 6, 8, 17, 97, 124, 99, -29, 43, -99, 49, 3, 112, 118, -46, -93, 40, -96, -12, -69, -102, 99, 115, -19, 109, -27, 42, -37, -19, 20, -87, 43, -58, 54, 17, -48, 43, -21, 7, -117, -91, -38, -98, 92, 25, -99, 86, 18, -11, 84, 41, -56, 5, -19, -78, 18, 42, -115, -12, 3, 27, -1, -25, -110, 16, -121, -80, 58, -75, -61, -99, 5, 55, 18, -93, -57, -12, 21, -71, -43, -92, 57, 22, -101, 83, 58, 35, -111, -15, -88, -126, -94, 106, -120, 104, -63, 121, 2, 34, -68, -86, -90, -42, -82, -33, -80, 20, 95, -72, -121, -48, -35, 124, 127, 123, -1, -81, 28, -49, -26, -37, 7, -83, 94, -37, -123, -99, -48, 43, 13, 51, -37, 4, -47, -26, 73, 64, 19, 43, 118, -5, 62, -23, -100, -119, 15, 21, -50, 24, -80, -123, 120, 33, 79, 107, 79, 14, -6, 54, 103, -51, 7, -14, -1, 8, -48, -30, -34, -39, -65, 42, -81, -72, -121, -122, 33, 60, 4, -54, -73, -108, 104, 127, -49, 60, -23, -104, -41, 56, -1, -20, -64, -39, 80, -16, 46, 75, 88, -82, 70, 111, -48, 46, -61, 96, -38, 114, 85, 114, -67, 76, 69, -98, 97, -70, -65, -124, -127, -110, 3, -47, -46, 105, 124, -59 };

    /**
     * Confirm DRLCertFactory uses a non-hex format for T61String encoding: http://b/2102191
     */
    public void testGetName() throws Exception {
        CertificateFactory certFactBC = CertificateFactory.getInstance("X.509", "BC");
        CertificateFactory certFactDRL = CertificateFactory.getInstance("X.509", "DRLCertFactory");

        X509Certificate certBC = (X509Certificate)
                certFactBC.generateCertificate(new ByteArrayInputStream(T61STRING_CERT));
        X509Certificate certDRL = (X509Certificate)
                certFactDRL.generateCertificate(new ByteArrayInputStream(T61STRING_CERT));

        assertEquals(certBC, certDRL);

        assertEquals(certBC.getSubjectX500Principal(), certBC.getSubjectX500Principal());
        assertEquals(certDRL.getIssuerX500Principal(), certDRL.getIssuerX500Principal());

        assertEquals(certBC.getSubjectX500Principal(), certDRL.getSubjectX500Principal());
        assertEquals(certBC.getIssuerX500Principal(), certDRL.getIssuerX500Principal());

        String[] formats = {
            X500Principal.CANONICAL,
            X500Principal.RFC1779,
            X500Principal.RFC2253
        };
        for (String format : formats) {
            assertEquals(certBC.getSubjectX500Principal().getName(format),
                         certDRL.getSubjectX500Principal().getName(format));
            assertEquals(certBC.getIssuerX500Principal().getName(format),
                         certDRL.getIssuerX500Principal().getName(format));
        }
        String expected = ""
                + "cn=entrust.net certification authority (2048),"
                + "ou=(c) 1999 entrust.net limited,"
                + "ou=www.entrust.net/cps_2048 incorp. by ref. (limits liab.),"
                + "o=entrust.net";
        assertEquals(expected,
                     certBC.getSubjectX500Principal().getName(X500Principal.CANONICAL));

    }

    // http://code.google.com/p/android/issues/detail?id=21531
    // http://b/5580664
    public void testIA5StringEncodings() {
        testIA5StringEncoding("emailAddress=root@android.com",
                              new byte[] { 48, 33, 49, 31, 48, 29, 6, 9, 42, -122, 72, -122, -9, 13, 1, 9, 1, 22, 16, 114, 111, 111, 116, 64, 97, 110, 100, 114, 111, 105, 100, 46, 99, 111, 109 });
        testIA5StringEncoding("dc=com",
                              new byte[] { 48, 21, 49, 19, 48, 17, 6, 10, 9, -110, 38, -119, -109, -14, 44, 100, 1, 25, 22, 3, 99, 111, 109 });
    }

    private void testIA5StringEncoding(String name, byte[] expectedEncoded) {
        X500Principal original = new X500Principal(name);

        byte[] actualEncoded = original.getEncoded();
        assertEquals(Arrays.toString(expectedEncoded), Arrays.toString(actualEncoded));

        X500Principal decoded = new X500Principal(actualEncoded);
        assertEquals(original, decoded);
    }
}
