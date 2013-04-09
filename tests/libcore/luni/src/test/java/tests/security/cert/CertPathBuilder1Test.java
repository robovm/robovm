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
* @author Vera Y. Petrashkova
* @version $Revision$
*/

package tests.security.cert;

import dalvik.annotation.KnownFailure;

import org.apache.harmony.security.tests.support.SpiEngUtils;
import org.apache.harmony.security.tests.support.cert.MyCertPathBuilderSpi;
import org.apache.harmony.security.tests.support.cert.TestUtils;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;
import java.security.cert.CertPath;
import java.security.cert.CertPathBuilder;
import java.security.cert.CertPathBuilderException;
import java.security.cert.CertPathBuilderResult;
import java.security.cert.CertPathBuilderSpi;
import java.security.cert.CertPathParameters;
import java.security.cert.CertificateException;

import junit.framework.TestCase;

/**
 * Tests for <code>CertPathBuilder</code> class constructors and
 * methods.
 *
 */
public class CertPathBuilder1Test extends TestCase {

    public static final String srvCertPathBuilder = "CertPathBuilder";

    public static final String defaultType = "PKIX";
    public static final String [] validValues = {
            "PKIX", "pkix", "PkiX", "pKiX" };

    private static String [] invalidValues = SpiEngUtils.invalidValues;

    private static boolean PKIXSupport = false;

    private static Provider defaultProvider;
    private static String defaultProviderName;

    private static String NotSupportMsg = "";

    public static final String DEFAULT_TYPE_PROPERTY = "certpathbuilder.type";

    static {
        defaultProvider = SpiEngUtils.isSupport(defaultType,
                srvCertPathBuilder);
        PKIXSupport = (defaultProvider != null);
        defaultProviderName = (PKIXSupport ? defaultProvider.getName() : null);
        NotSupportMsg = defaultType.concat(" is not supported");
    }
    private static CertPathBuilder[] createCPBs() {
        if (!PKIXSupport) {
            fail(NotSupportMsg);
            return null;
        }
        try {
            CertPathBuilder[] certPBs = new CertPathBuilder[3];
            certPBs[0] = CertPathBuilder.getInstance(defaultType);
            certPBs[1] = CertPathBuilder.getInstance(defaultType,
                    defaultProviderName);
            certPBs[2] = CertPathBuilder.getInstance(defaultType,
                    defaultProvider);
            return certPBs;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * java.security.cert.CertPathBuilder#getDefaultType()
     */
    public void test_getDefaultType() throws Exception {

        // Regression for HARMONY-2785

        // test: default value
        assertNull(Security.getProperty(DEFAULT_TYPE_PROPERTY));
        assertEquals("PKIX", CertPathBuilder.getDefaultType());
    }

    /**
     * Test for <code>getInstance(String algorithm)</code> method
     * Assertion:
     * throws NullPointerException when algorithm is null
     * throws NoSuchAlgorithmException when algorithm  is not correct
     * or it is not available
     */
    public void testCertPathBuilder02() throws NoSuchAlgorithmException {
        try {
            CertPathBuilder.getInstance(null);
            fail("No expected NullPointerException");
        } catch (NullPointerException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                CertPathBuilder.getInstance(invalidValues[i]);
                fail("NoSuchAlgorithmException must be thrown");
            } catch (NoSuchAlgorithmException e) {
            }
        }
    }

    /**
     * Test for <code>getInstance(String algorithm)</code> method
     * Assertion: returns CertPathBuilder object
     */
    public void testCertPathBuilder03() throws NoSuchAlgorithmException  {
        if (!PKIXSupport) {
            fail(NotSupportMsg);
            return;
        }
        for (int i = 0; i < validValues.length; i++) {
            CertPathBuilder cpb = CertPathBuilder.getInstance(validValues[i]);
            assertEquals("Incorrect algorithm", cpb.getAlgorithm(), validValues[i]);
        }
    }
    /**
     * Test for <code>getInstance(String algorithm, String provider)</code> method
     * Assertion: throws IllegalArgumentException when provider is null or empty
     *
     * FIXME: verify what exception will be thrown if provider is empty
     */
    public void testCertPathBuilder04()
            throws NoSuchAlgorithmException, NoSuchProviderException  {
        if (!PKIXSupport) {
            fail(NotSupportMsg);
            return;
        }
        String provider = null;
        for (int i = 0; i < validValues.length; i++) {
            try {
                CertPathBuilder.getInstance(validValues[i], provider);
                fail("IllegalArgumentException must be thrown thrown");
            } catch (IllegalArgumentException e) {
            }
            try {
                CertPathBuilder.getInstance(validValues[i], "");
                fail("IllegalArgumentException must be thrown thrown");
            } catch (IllegalArgumentException e) {
            }
        }
    }

    /**
     * Test for <code>getInstance(String algorithm, String provider)</code> method
     * Assertion:
     * throws NoSuchProviderException when provider has invalid value
     */
    public void testCertPathBuilder05()
            throws NoSuchAlgorithmException  {
        if (!PKIXSupport) {
            fail(NotSupportMsg);
            return;
        }
        for (int i = 0; i < validValues.length; i++ ) {
            for (int j = 1; j < invalidValues.length; j++) {
                try {
                    CertPathBuilder.getInstance(validValues[i], invalidValues[j]);
                    fail("NoSuchProviderException must be hrown");
                } catch (NoSuchProviderException e1) {
                }
            }
        }
    }
    /**
     * Test for <code>getInstance(String algorithm, String provider)</code> method
     * Assertion:
     * throws NullPointerException when algorithm is null
     * throws NoSuchAlgorithmException when algorithm  is not correct
     */
    public void testCertPathBuilder06()
            throws NoSuchAlgorithmException, NoSuchProviderException  {
        if (!PKIXSupport) {
            fail(NotSupportMsg);
            return;
        }
        try {
            CertPathBuilder.getInstance(null, defaultProviderName);
            fail("No expected NullPointerException");
        } catch (NullPointerException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                CertPathBuilder.getInstance(invalidValues[i], defaultProviderName);
                fail("NoSuchAlgorithmException must be thrown");
            } catch (NoSuchAlgorithmException e1) {
            }
        }
    }

    /**
     * Test for <code>getInstance(String algorithm, String provider)</code> method
     * Assertion: returns CertPathBuilder object
     */
    public void testCertPathBuilder07()
            throws NoSuchAlgorithmException, NoSuchProviderException  {
        if (!PKIXSupport) {
            fail(NotSupportMsg);
            return;
        }
        CertPathBuilder certPB;
        for (int i = 0; i < validValues.length; i++) {
            certPB = CertPathBuilder.getInstance(validValues[i], defaultProviderName);
            assertEquals("Incorrect algorithm", certPB.getAlgorithm(), validValues[i]);
            assertEquals("Incorrect provider name", certPB.getProvider().getName(), defaultProviderName);
        }
    }

    /**
     * Test for <code>getInstance(String algorithm, Provider provider)</code> method
     * Assertion: throws IllegalArgumentException when provider is null
     */
    public void testCertPathBuilder08()
            throws NoSuchAlgorithmException  {
        if (!PKIXSupport) {
            fail(NotSupportMsg);
            return;
        }
        Provider prov = null;
        for (int t = 0; t < validValues.length; t++ ) {
            try {
                CertPathBuilder.getInstance(validValues[t], prov);
                fail("IllegalArgumentException must be thrown");
            } catch (IllegalArgumentException e1) {
            }
        }
    }

    /**
     * Test for <code>getInstance(String algorithm, String provider)</code> method
     * Assertion:
     * throws NullPointerException when algorithm is null
     * throws NoSuchAlgorithmException when algorithm  is not correct
     */
    public void testCertPathBuilder09()
            throws NoSuchAlgorithmException, NoSuchProviderException  {
        if (!PKIXSupport) {
            fail(NotSupportMsg);
            return;
        }
        try {
            CertPathBuilder.getInstance(null, defaultProvider);
            fail("No expected NullPointerException");
        } catch (NullPointerException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                CertPathBuilder.getInstance(invalidValues[i], defaultProvider);
                fail("NoSuchAlgorithm must be thrown");
            } catch (NoSuchAlgorithmException e1) {
            }
        }
    }
    /**
     * Test for <code>getInstance(String algorithm, String provider)</code> method
     * Assertion: returns CertPathBuilder object
     */
    public void testCertPathBuilder10()
            throws NoSuchAlgorithmException, NoSuchProviderException  {
        if (!PKIXSupport) {
            fail(NotSupportMsg);
            return;
        }
        CertPathBuilder certPB;
        for (int i = 0; i < invalidValues.length; i++) {
            certPB = CertPathBuilder.getInstance(validValues[i], defaultProvider);
            assertEquals("Incorrect algorithm", certPB.getAlgorithm(), validValues[i]);
            assertEquals("Incorrect provider name", certPB.getProvider(), defaultProvider);
        }
    }
    /**
     * Test for <code>build(CertPathParameters params)</code> method
     * Assertion: throws InvalidAlgorithmParameterException params is null
     */
    public void testCertPathBuilder11()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            CertPathBuilderException {
        if (!PKIXSupport) {
            fail(NotSupportMsg);
            return;
        }
        CertPathBuilder [] certPB = createCPBs();
        assertNotNull("CertPathBuilder objects were not created", certPB);
        for (int i = 0; i < certPB.length; i++ ){
            try {
                certPB[i].build(null);
                fail("InvalidAlgorithmParameterException must be thrown");
            } catch(InvalidAlgorithmParameterException e) {
            }
        }
    }

    // Test passed on RI
    @KnownFailure(value="expired certificate bug 2322662")
    public void testBuild() throws Exception {
        TestUtils.initCertPathSSCertChain();
        CertPathParameters params = TestUtils.getCertPathParameters();
        CertPathBuilder builder = TestUtils.getCertPathBuilder();

        try {
            CertPathBuilderResult result = builder.build(params);
            assertNotNull("builder result is null", result);
            CertPath certPath = result.getCertPath();
            assertNotNull("certpath of builder result is null", certPath);
        } catch (InvalidAlgorithmParameterException e) {
            fail("unexpected Exception: " + e);
        }

    }
    /**
     * Test for
     * <code>CertPathBuilder</code> constructor
     * Assertion: returns CertPathBuilder object
     */
    public void testCertPathBuilder12()
            throws CertificateException, NoSuchProviderException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            CertPathBuilderException {
        if (!PKIXSupport) {
            fail(NotSupportMsg);
            return;
        }
        CertPathBuilderSpi spi = new MyCertPathBuilderSpi();
        CertPathBuilder certPB = new myCertPathBuilder(spi,
                    defaultProvider, defaultType);
        assertEquals("Incorrect algorithm", certPB.getAlgorithm(), defaultType);
        assertEquals("Incorrect provider", certPB.getProvider(), defaultProvider);
        try {
            certPB.build(null);
            fail("CertPathBuilderException must be thrown ");
        } catch (CertPathBuilderException e) {
        }
        certPB = new myCertPathBuilder(null, null, null);
        assertNull("Incorrect algorithm", certPB.getAlgorithm());
        assertNull("Incorrect provider", certPB.getProvider());
        try {
            certPB.build(null);
            fail("NullPointerException must be thrown ");
        } catch (NullPointerException e) {
        }
    }

    /**
     * Test for <code>getAlgorithm()</code> method Assertion: returns
     * CertPathBuilder object
     */
    public void testCertPathBuilder13() throws NoSuchAlgorithmException {
        if (!PKIXSupport) {
            fail(NotSupportMsg);
            return;
        }

        for (int i = 0; i < validValues.length; i++) {
            CertPathBuilder cpb = CertPathBuilder.getInstance(validValues[i]);
            assertEquals("Incorrect algorithm", cpb.getAlgorithm(),
                    validValues[i]);
            try {
                cpb = CertPathBuilder.getInstance(validValues[i],
                        defaultProviderName);
                assertEquals("Incorrect algorithm", cpb.getAlgorithm(),
                        validValues[i]);
            } catch (NoSuchProviderException e) {
                fail("Unexpected NoSuchProviderException exeption "
                        + e.getMessage());
            }

            try {
                cpb = CertPathBuilder.getInstance(validValues[i],
                        defaultProviderName);
                assertEquals("Incorrect algorithm", cpb.getAlgorithm(),
                        validValues[i]);
            } catch (NoSuchProviderException e) {
                fail("Unexpected NoSuchProviderException " + e.getMessage());
            }
        }
    }

    /**
     * Test for <code>getProvider()</code> method Assertion: returns
     * CertPathBuilder object
     */
    public void testCertPathBuilder14() throws NoSuchAlgorithmException {
        if (!PKIXSupport) {
            fail(NotSupportMsg);
            return;
        }

        for (int i = 0; i < validValues.length; i++) {
            CertPathBuilder cpb2 = CertPathBuilder.getInstance(validValues[i],
                    defaultProvider);
            assertEquals("Incorrect provider", cpb2.getProvider(),
                    defaultProvider);

            try {
                CertPathBuilder cpb3 = CertPathBuilder.getInstance(
                        validValues[i], defaultProviderName);
                assertEquals("Incorrect provider", cpb3.getProvider(),
                        defaultProvider);
            } catch (NoSuchProviderException e) {
                fail("Unexpected NoSuchProviderException " + e.getMessage());
            }
        }

    }
}
/**
 * Additional class to verify CertPathBuilder constructor
 */
class myCertPathBuilder extends CertPathBuilder {

    private static Provider provider;

    public myCertPathBuilder(CertPathBuilderSpi spi, Provider prov, String type) {
        super(spi, prov, type);
    }

    public static CertPathBuilder getInstance(String algorithm)
            throws NoSuchAlgorithmException {
        myCertPathBuilder mcpb = new myCertPathBuilder(null, null, null);
        provider = mcpb.new MyProvider();
        return CertPathBuilder.getInstance(algorithm);
    }

    public Provider getMyProvider() {
        return provider;
    }

    public class MyProvider extends Provider {

        private static final long serialVersionUID = -6537447905658191184L;

        MyProvider() {
            super("MyProvider", 1.0, "Provider for testing");
        }

        MyProvider(String name, double version, String info) {
            super(name, version, info);
        }

        public void putService(Provider.Service s) {
            super.putService(s);
        }

        public void removeService(Provider.Service s) {
            super.removeService(s);
        }
    }

}
