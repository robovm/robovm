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

import junit.framework.TestCase;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;
import java.security.cert.CertPathParameters;
import java.security.cert.CertPathValidator;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertPathValidatorSpi;
import java.security.cert.CertificateException;

import org.apache.harmony.security.tests.support.cert.MyCertPath;
import org.apache.harmony.security.tests.support.cert.MyCertPathValidatorSpi;
import org.apache.harmony.security.tests.support.SpiEngUtils;

/**
 * Tests for <code>CertPathValidator</code> class constructors and
 * methods.
 *
 */
public class CertPathValidator1Test extends TestCase {

    public static final String srvCertPathValidator = "CertPathValidator";

    private static final String defaultType = "PKIX";
    public static final String [] validValues = {
            "PKIX", "pkix", "PkiX", "pKiX" };

    private static String [] invalidValues = SpiEngUtils.invalidValues;

    private static boolean PKIXSupport = false;

    private static Provider defaultProvider;
    private static String defaultProviderName;

    private static String NotSupportMsg = "";

    static {
        defaultProvider = SpiEngUtils.isSupport(defaultType,
                srvCertPathValidator);
        PKIXSupport = (defaultProvider != null);
        defaultProviderName = (PKIXSupport ? defaultProvider.getName() : null);
        NotSupportMsg = defaultType.concat(" is not supported");
    }



    private static CertPathValidator[] createCPVs() {
        if (!PKIXSupport) {
            fail(NotSupportMsg);
            return null;
        }
        try {
            CertPathValidator[] certPVs = new CertPathValidator[3];
            certPVs[0] = CertPathValidator.getInstance(defaultType);
            certPVs[1] = CertPathValidator.getInstance(defaultType,
                    defaultProviderName);
            certPVs[2] = CertPathValidator.getInstance(defaultType,
                    defaultProvider);
            return certPVs;
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * Test for <code>getDefaultType()</code> method
     * Assertion: returns security property "certpathvalidator.type" or "PKIX"
     */
    public void testCertPathValidator01() {
        if (!PKIXSupport) {
            fail(NotSupportMsg);
            return;
        }
        String propName = "certpathvalidator.type";
        String defCPV = Security.getProperty(propName);

        String dt = CertPathValidator.getDefaultType();
        String resType = defCPV;
        if (resType == null) {
            resType = defaultType;
        }
        assertNotNull("Default type have not be null", dt);
        assertEquals("Incorrect default type", dt, resType);

        if (defCPV == null) {
            Security.setProperty(propName, defaultType);
            dt = CertPathValidator.getDefaultType();
            resType = Security.getProperty(propName);
            assertNotNull("Incorrect default type", resType);
            assertNotNull("Default type have not be null", dt);
            assertEquals("Incorrect default type", dt, resType);
        }
    }

    /**
     * Test for <code>getInstance(String algorithm)</code> method
     * Assertion:
     * throws NullPointerException when algorithm is null
     * throws NoSuchAlgorithmException when algorithm  is not available
     */
    public void testCertPathValidator02() {
        try {
            CertPathValidator.getInstance(null);
            fail("NullPointerException or NoSuchAlgorithmException must be thrown when algorithm is null");
        } catch (NullPointerException e) {
        } catch (NoSuchAlgorithmException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                CertPathValidator.getInstance(invalidValues[i]);
                fail("NoSuchAlgorithmException must be thrown");
            } catch (NoSuchAlgorithmException e) {
            }
        }
    }
    /**
     * Test for <code>getInstance(String algorithm)</code> method
     * Assertion: returns CertPathValidator object
     */
    public void testCertPathValidator03() throws NoSuchAlgorithmException  {
        if (!PKIXSupport) {
            fail(NotSupportMsg);
            return;
        }
        CertPathValidator certPV;
        for (int i = 0; i < validValues.length; i++) {
            certPV = CertPathValidator.getInstance(validValues[i]);
            assertEquals("Incorrect algorithm", certPV.getAlgorithm(), validValues[i]);
        }
    }
    /**
     * Test for <code>getInstance(String algorithm, String provider)</code> method
     * Assertion: throws IllegalArgumentException when provider is null or empty
     *
     * FIXME: verify what exception will be thrown if provider is empty
     */
    public void testCertPathValidator04()
            throws NoSuchAlgorithmException, NoSuchProviderException  {
        if (!PKIXSupport) {
            fail(NotSupportMsg);
            return;
        }
        String provider = null;
        for (int i = 0; i < validValues.length; i++) {
            try {
                CertPathValidator.getInstance(validValues[i], provider);
                fail("IllegalArgumentException must be thrown thrown");
            } catch (IllegalArgumentException e) {
            }
            try {
                CertPathValidator.getInstance(validValues[i], "");
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
    public void testCertPathValidator05() throws NoSuchAlgorithmException {
        if (!PKIXSupport) {
            fail(NotSupportMsg);
            return;
        }
        for (int t = 0; t < validValues.length; t++) {
            for (int i = 1; i < invalidValues.length; i++) {
                try {
                    CertPathValidator.getInstance(validValues[t],
                            invalidValues[i]);
                    fail("NoSuchProviderException must be thrown");
                } catch (NoSuchProviderException e1) {
                }
            }
        }
    }

    /**
     * Test for <code>getInstance(String algorithm, String provider)</code> method
     * Assertion:
     * throws NullPointerException when algorithm is null
     * throws NoSuchAlgorithmException when algorithm  is not available
     */
    public void testCertPathValidator06()
            throws NoSuchAlgorithmException, NoSuchProviderException  {
        if (!PKIXSupport) {
            fail(NotSupportMsg);
            return;
        }
        try {
            CertPathValidator.getInstance(null, defaultProviderName);
            fail("NullPointerException or NoSuchAlgorithmException must be thrown when algorithm is null");
        } catch (NullPointerException e) {
        } catch (NoSuchAlgorithmException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                CertPathValidator.getInstance(invalidValues[i], defaultProviderName);
                fail("NoSuchAlgorithmException must be thrown");
            } catch (NoSuchAlgorithmException e1) {
            }
        }
    }
    /**
     * Test for <code>getInstance(String algorithm, String provider)</code> method
     * Assertion: returns CertPathValidator object
     */
    public void testCertPathValidator07() throws NoSuchAlgorithmException,
            NoSuchProviderException {
        if (!PKIXSupport) {
            fail(NotSupportMsg);
            return;
        }
        CertPathValidator certPV;
        for (int i = 0; i < validValues.length; i++) {
            certPV = CertPathValidator.getInstance(validValues[i],
                    defaultProviderName);
            assertEquals("Incorrect algorithm", certPV.getAlgorithm(),
                    validValues[i]);
            assertEquals("Incorrect provider name", certPV.getProvider()
                    .getName(), defaultProviderName);
        }
    }

    /**
     * Test for <code>getInstance(String algorithm, Provider provider)</code> method
     * Assertion: throws IllegalArgumentException when provider is null
     */
    public void testCertPathValidator08()
            throws NoSuchAlgorithmException  {
        if (!PKIXSupport) {
            fail(NotSupportMsg);
            return;
        }
        Provider prov = null;
        for (int t = 0; t < validValues.length; t++ ) {
            try {
                CertPathValidator.getInstance(validValues[t], prov);
                fail("IllegalArgumentException must be thrown");
            } catch (IllegalArgumentException e1) {
            }
        }
    }

    /**
     * Test for <code>getInstance(String algorithm, String provider)</code> method
     * Assertion:
     * throws NullPointerException when algorithm is null
     * throws NoSuchAlgorithmException when algorithm  is not available
     */
    public void testCertPathValidator09()
            throws NoSuchAlgorithmException, NoSuchProviderException  {
        if (!PKIXSupport) {
            fail(NotSupportMsg);
            return;
        }
        try {
            CertPathValidator.getInstance(null, defaultProvider);
            fail("NullPointerException or NoSuchAlgorithmException must be thrown when algorithm is null");
        } catch (NullPointerException e) {
        } catch (NoSuchAlgorithmException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                CertPathValidator.getInstance(invalidValues[i], defaultProvider);
                fail("NoSuchAlgorithm must be thrown");
            } catch (NoSuchAlgorithmException e1) {
            }
        }
    }
    /**
     * Test for <code>getInstance(String algorithm, String provider)</code> method
     * Assertion: returns CertPathValidator object
     */
    public void testCertPathValidator10() throws NoSuchAlgorithmException,
            NoSuchProviderException {
        if (!PKIXSupport) {
            fail(NotSupportMsg);
            return;
        }
        CertPathValidator certPV;
        for (int i = 0; i < invalidValues.length; i++) {
            certPV = CertPathValidator.getInstance(validValues[i],
                    defaultProvider);
            assertEquals("Incorrect algorithm", certPV.getAlgorithm(),
                    validValues[i]);
            assertEquals("Incorrect provider name", certPV.getProvider(),
                    defaultProvider);
        }
    }

    /**
     * Test for <code>validate(CertPath certpath, CertPathParameters params)</code> method
     * Assertion: throws InvalidAlgorithmParameterException params is not
     * instance of PKIXParameters or null
     */
    public void testCertPathValidator11()
            throws NoSuchAlgorithmException, NoSuchProviderException, CertPathValidatorException {
        if (!PKIXSupport) {
            fail(NotSupportMsg);
            return;
        }
        CertPathValidator [] certPV = createCPVs();
        assertNotNull("CertPathValidator objects were not created", certPV);
        MyCertPath mCP = new MyCertPath(new byte[0]);
        invalidParams mPar = new invalidParams();
        for (int i = 0; i < certPV.length; i++) {
            try {
                certPV[i].validate(mCP, mPar);
                fail("InvalidAlgorithmParameterException must be thrown");
            } catch(InvalidAlgorithmParameterException e) {
            }
            try {
                certPV[i].validate(mCP, null);
                fail("InvalidAlgorithmParameterException must be thrown");
            } catch(InvalidAlgorithmParameterException e) {
            }
        }
    }

     /**
     * Test for
     * <code>CertPathValidator</code> constructor
     * Assertion: returns CertPathValidator object
     */
    public void testCertPathValidator12()
            throws CertificateException, NoSuchProviderException, NoSuchAlgorithmException,
            CertPathValidatorException, InvalidAlgorithmParameterException {
        if (!PKIXSupport) {
            fail(NotSupportMsg);
            return;
        }
        CertPathValidatorSpi spi = new MyCertPathValidatorSpi();
        CertPathValidator certPV = new myCertPathValidator(spi,
                    defaultProvider, defaultType);
        assertEquals("Incorrect algorithm", certPV.getAlgorithm(), defaultType);
        assertEquals("Incorrect provider", certPV.getProvider(), defaultProvider);
        certPV.validate(null, null);
        try {
            certPV.validate(null, null);
            fail("CertPathValidatorException must be thrown");
        } catch (CertPathValidatorException e) {
        }
        certPV = new myCertPathValidator(null, null, null);
        assertNull("Incorrect algorithm", certPV.getAlgorithm());
        assertNull("Incorrect provider", certPV.getProvider());
        try {
            certPV.validate(null, null);
            fail("NullPointerException must be thrown");
        } catch (NullPointerException e) {
        }
    }

    /**
     * Test for <code>getAlgorithm()</code> method
     */
    public void testCertPathValidator13() throws NoSuchAlgorithmException {
        if (!PKIXSupport) {
            fail(NotSupportMsg);
            return;
        }
        CertPathValidator certPV;
        for (int i = 0; i < validValues.length; i++) {
            certPV = CertPathValidator.getInstance(validValues[i]);
            assertEquals("Incorrect algorithm", certPV.getAlgorithm(),
                    validValues[i]);
            try {
                certPV = CertPathValidator.getInstance(validValues[i],
                        defaultProviderName);
                assertEquals("Incorrect algorithm", certPV.getAlgorithm(),
                        validValues[i]);
            } catch (NoSuchProviderException e) {
                fail("Unexpected NoSuchAlgorithmException " + e.getMessage());
            }

            certPV = CertPathValidator.getInstance(validValues[i],
                    defaultProvider);
            assertEquals("Incorrect algorithm", certPV.getAlgorithm(),
                    validValues[i]);
        }
    }

    /**
     * Test for <code>getProvider()</code> method
     */
    public void testCertPathValidator14() throws NoSuchAlgorithmException {
        if (!PKIXSupport) {
            fail(NotSupportMsg);
            return;
        }

        CertPathValidator certPV;

        for (int i = 0; i < validValues.length; i++) {
            try {
                certPV = CertPathValidator.getInstance(validValues[i],
                        defaultProviderName);
                assertEquals("Incorrect provider", certPV.getProvider(),
                        defaultProvider);
            } catch (NoSuchProviderException e) {
                fail("Unexpected NoSuchProviderException " + e.getMessage());
            }

            certPV = CertPathValidator.getInstance(validValues[i],
                    defaultProvider);
            assertEquals("Incorrect provider", certPV.getProvider(),
                    defaultProvider);
        }
    }
}
/**
 * Additional class to verify CertPathValidator constructor
 */
class myCertPathValidator extends CertPathValidator {

    public myCertPathValidator(CertPathValidatorSpi spi, Provider prov, String type) {
        super(spi, prov, type);
    }
}
/**
 * Additional class to verify validate method
 */
class invalidParams implements CertPathParameters {
    public Object clone() {
        return new invalidParams();
    }
}
