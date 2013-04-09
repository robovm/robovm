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
 * @author Vladimir N. Molotkov
 * @version $Revision$
 */

package libcore.java.security.cert;

import dalvik.annotation.BrokenTest;
import java.io.ByteArrayInputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.util.Calendar;
import java.util.Collections;
import java.util.Set;
import junit.framework.TestCase;
import org.apache.harmony.security.tests.support.cert.TestUtils;
import tests.targets.security.KeyStoreTestPKCS12;

/**
 * Tests for <code>PKIXParameters</code> fields and methods
 */
public class OldPKIXParametersTest extends TestCase {

    public final void testClone() throws InvalidAlgorithmParameterException {
        Set<TrustAnchor> taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName()
                    + ": not performed (could not create test TrustAnchor set)");
        }

        PKIXParameters cpp = new PKIXParameters(taSet);
        PKIXParameters cppc = (PKIXParameters) cpp.clone();

        assertEquals(cpp.getPolicyQualifiersRejected(), cppc
                .getPolicyQualifiersRejected());
        assertEquals(cpp.getCertPathCheckers(), cppc.getCertPathCheckers());
        assertEquals(cpp.getCertStores(), cppc.getCertStores());
        assertEquals(cpp.getDate(), cppc.getDate());
        assertEquals(cpp.getInitialPolicies(), cppc.getInitialPolicies());
        assertEquals(cpp.getSigProvider(), cppc.getSigProvider());
        assertEquals(cpp.getTargetCertConstraints(), cppc
                .getTargetCertConstraints());
        assertEquals(cpp.getTrustAnchors(), cppc.getTrustAnchors());

        assertEquals(cpp.isAnyPolicyInhibited(), cppc.isAnyPolicyInhibited());
        assertEquals(cpp.isExplicitPolicyRequired(), cppc
                .isExplicitPolicyRequired());
        assertEquals(cpp.isPolicyMappingInhibited(), cppc
                .isPolicyMappingInhibited());
        assertEquals(cpp.isRevocationEnabled(), cppc.isRevocationEnabled());

        cpp.setDate(Calendar.getInstance().getTime());
        cpp.setPolicyQualifiersRejected(!cppc.getPolicyQualifiersRejected());
        assertFalse(cpp.getDate().equals(cppc.getDate()));
        assertFalse(cpp.getPolicyQualifiersRejected() == cppc
                .getPolicyQualifiersRejected());

        cppc.setExplicitPolicyRequired(!cpp.isExplicitPolicyRequired());
        cppc.setRevocationEnabled(!cpp.isRevocationEnabled());

        assertFalse(cpp.isExplicitPolicyRequired() == cppc
                .isExplicitPolicyRequired());
        assertFalse(cpp.isRevocationEnabled() == cppc.isRevocationEnabled());

        PKIXParameters cpp1 = null;
        try {
            cpp1.clone();
        } catch (NullPointerException e) {
            // expected
        }
    }

    /**
     * Test for <code>isPolicyMappingInhibited()</code> method<br>
     * Assertion: returns true if policy mapping is inhibited, false otherwise
     * Assertion: by default, policy mapping is not inhibited (the flag is
     * false)
     *
     * @throws InvalidAlgorithmParameterException
     */
    public final void testIsPolicyMappingInhibited() throws Exception {
        Set<TrustAnchor> taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": not performed (could not create test TrustAnchor set)");
        }

        PKIXParameters p = new PKIXParameters(taSet);
        assertFalse(p.isPolicyMappingInhibited());

        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        TestUtils.initCertPathSSCertChain();
        Set<TrustAnchor> taSet2 = Collections.singleton(new TrustAnchor(
               TestUtils.rootCertificateSS, null));
        p = new PKIXParameters(taSet2);

        assertFalse(p.isPolicyMappingInhibited());
        p.setPolicyMappingInhibited(true);
        assertTrue(p.isRevocationEnabled());
    }

    /**
     * Test for <code>isPolicyMappingInhibited()</code> method<br>
     * Assertion: returns the current value of the RevocationEnabled flag
     * Assertion: when a <code>PKIXParameters</code> object is created, this
     * flag is set to true
     *
     * @throws InvalidAlgorithmParameterException
     */
    public final void testIsRevocationEnabled() throws Exception {
        Set<TrustAnchor> taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName()
                    + ": not performed (could not create test TrustAnchor set)");
        }

        PKIXParameters p = new PKIXParameters(taSet);
        assertTrue(p.isRevocationEnabled());

        CertificateFactory cf = CertificateFactory.getInstance("X.509");
       TestUtils.initCertPathSSCertChain();
       Set<TrustAnchor> taSet2 = Collections.singleton(new TrustAnchor(
              TestUtils.rootCertificateSS, null));
       p = new PKIXParameters(taSet2);

       assertTrue(p.isRevocationEnabled());
       p.setRevocationEnabled(false);
       assertFalse(p.isRevocationEnabled());
    }

    /**
     * Test for <code>toString</code> method<br>
     */
    public final void testToString() throws Exception {
        Set<TrustAnchor> taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName()
                    + ": not performed (could not create test TrustAnchor set)");
        }

        PKIXParameters p = new PKIXParameters(taSet);
        assertNotNull(p.toString());

        PKIXParameters p1 = null;
        try {
            p1.toString();
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            // expected
        }
    }

    /**
     * Test #4 for <code>PKIXParameters(KeyStore)</code> constructor<br>
     *
     * @throws InvalidAlgorithmParameterException
     * @throws KeyStoreException
     */
    @BrokenTest("Fails in CTS environment, but passes in CoreTestRunner")
    public final void testPKIXParametersKeyStore04() throws Exception {


        KeyStore store = KeyStore.getInstance("PKCS12");
        KeyStoreTestPKCS12 k = new KeyStoreTestPKCS12();
        ByteArrayInputStream stream = new ByteArrayInputStream(k.keyStoreData);

        try {
            PKIXParameters p = new PKIXParameters(store);
        } catch (KeyStoreException e) {
            // ok
        }

        store = KeyStore.getInstance("PKCS12");
        store.load(stream, new String(KeyStoreTestPKCS12.keyStorePassword)
                .toCharArray());
        stream.close();

        try {
              PKIXParameters p = new PKIXParameters(store);
        } catch (InvalidAlgorithmParameterException e) {
            // ok
        }


        KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        keystore.load(null,null);
        keystore.setCertificateEntry("test", TestUtils.rootCertificateSS);


        PKIXParameters p = new PKIXParameters(keystore);
    }
}
