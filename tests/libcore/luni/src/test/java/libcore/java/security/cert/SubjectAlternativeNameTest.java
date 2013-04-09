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

package libcore.java.security.cert;

import com.android.org.bouncycastle.asn1.x509.GeneralName;
import java.io.ByteArrayInputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.List;
import junit.framework.TestCase;
import libcore.java.security.TestKeyStore;

public final class SubjectAlternativeNameTest extends TestCase {

    /**
     * The spec doesn't cover this, but we require that IP addresses are
     * formatted consistently with InetAddress.getHostAddress().
     */
    public void testFormatIpv4Address() throws Exception {
        assertEquals("127.0.0.1", formatIpAddress(new byte[]{127, 0, 0, 1}));
    }

    public void testFormatIpv4MappedAddress() throws Exception {
        byte[] mappedAddress = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, 127, 0, 0, 1 };
        assertEquals("127.0.0.1", formatIpAddress(mappedAddress));
    }

    public void testFormatIpv6Address() throws Exception {
        byte[] ipAddress = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
        String decoded = formatIpAddress(ipAddress);
        assertTrue(decoded, decoded.equals("::1") || decoded.equals("0:0:0:0:0:0:0:1"));
    }

    private String formatIpAddress(byte[] ipAddress) throws Exception {
        Certificate root = new TestKeyStore.Builder()
                .addSubjectAltNameIpAddress(ipAddress)
                .build()
                .getRootCertificate("RSA");
        X509Certificate javaCertificate = bouncycastleToJava(root);
        Collection<List<?>> subjectAlternativeNames = javaCertificate.getSubjectAlternativeNames();
        assertEquals(1, subjectAlternativeNames.size());
        List<?> subjectAlternativeName = subjectAlternativeNames.iterator().next();
        assertEquals(2, subjectAlternativeName.size());
        assertEquals(GeneralName.iPAddress, subjectAlternativeName.get(0));
        return (String) subjectAlternativeName.get(1);
    }

    private X509Certificate bouncycastleToJava(Certificate certificate) throws Exception {
        byte[] encoded = certificate.getEncoded();
        CertificateFactory factory = CertificateFactory.getInstance("X.509");
        return (X509Certificate) factory.generateCertificate(new ByteArrayInputStream(encoded));
    }
}
