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

import tests.security.cert.myCertPathBuilder.MyProvider;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;
import java.security.cert.CertPathBuilder;
import java.security.cert.CertPathBuilderException;
import java.security.cert.CertPathBuilderResult;

import org.apache.harmony.security.tests.support.SpiEngUtils;
/**
 * Tests for CertPathBuilder class constructors and methods
 *
 */
public class CertPathBuilder2Test extends TestCase {
    private static final String defaultAlg = "CertPB";
    private static final String CertPathBuilderProviderClass = "org.apache.harmony.security.tests.support.cert.MyCertPathBuilderSpi";

    private static final String[] invalidValues = SpiEngUtils.invalidValues;

    private static final String[] validValues;

    static {
        validValues = new String[4];
        validValues[0] = defaultAlg;
        validValues[1] = defaultAlg.toLowerCase();
        validValues[2] = "CeRtPb";
        validValues[3] = "cERTpb";
    }

    Provider mProv;

    protected void setUp() throws Exception {
        super.setUp();
        mProv = (new SpiEngUtils()).new MyProvider("MyCertPathBuilderProvider",
                "Provider for testing", CertPathBuilder1Test.srvCertPathBuilder
                        + "." + defaultAlg,

                CertPathBuilderProviderClass);
        Security.insertProviderAt(mProv, 1);
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
        Security.removeProvider(mProv.getName());
    }

    private void checkResult(CertPathBuilder certBuild)
            throws InvalidAlgorithmParameterException,
            CertPathBuilderException {
        String dt = CertPathBuilder.getDefaultType();
        String propName = CertPathBuilder1Test.DEFAULT_TYPE_PROPERTY;
        String dtN;
        for (int i = 0; i <invalidValues.length; i++) {
            Security.setProperty(propName, invalidValues[i]);
            dtN = CertPathBuilder.getDefaultType();
            if (!dtN.equals(invalidValues[i]) && !dtN.equals(dt)) {
                fail("Incorrect default type: ".concat(dtN));
            }
        }
        Security.setProperty(propName, dt);
        assertEquals("Incorrect default type", CertPathBuilder.getDefaultType(),
                dt);
        try {
            certBuild.build(null);
            fail("CertPathBuilderException must be thrown");
        } catch (CertPathBuilderException e) {
        }
        CertPathBuilderResult cpbResult = certBuild.build(null);
        assertNull("Not null CertPathBuilderResult", cpbResult);
    }

    /**
     * Test for <code>getInstance(String algorithm)</code> method
     * Assertions:
     * throws
     * throws NullPointerException when algorithm is null
     * throws NoSuchAlgorithmException when algorithm  is not correct
     * returns CertPathBuilder object
     */
    public void testGetInstance01() throws NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, CertPathBuilderException {
        try {
            CertPathBuilder.getInstance(null);
            fail("NullPointerException or NoSuchAlgorithmException must be thrown when algorithm is null");
        } catch (NullPointerException e) {
        } catch (NoSuchAlgorithmException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                CertPathBuilder.getInstance(invalidValues[i]);
                fail("NoSuchAlgorithmException must be thrown (type: ".concat(
                        invalidValues[i]).concat(")"));
            } catch (NoSuchAlgorithmException e) {
            }
        }
        CertPathBuilder cerPB;
        for (int i = 0; i < validValues.length; i++) {
            cerPB = CertPathBuilder.getInstance(validValues[i]);
            assertEquals("Incorrect type", cerPB.getAlgorithm(), validValues[i]);
            assertEquals("Incorrect provider", cerPB.getProvider(), mProv);
            checkResult(cerPB);
        }
    }

    /**
     * Test for <code>getInstance(String algorithm, String provider)</code> method
     * Assertions:
     * throws NullPointerException when algorithm is null
     * throws NoSuchAlgorithmException when algorithm  is not correct
     * throws IllegalArgumentException when provider is null or empty;
     * throws NoSuchProviderException when provider is available;
     * returns CertPathBuilder object
     */
    public void testGetInstance02() throws NoSuchAlgorithmException,
            NoSuchProviderException, IllegalArgumentException,
            InvalidAlgorithmParameterException, CertPathBuilderException {
        try {
            CertPathBuilder.getInstance(null, mProv.getName());
            fail("NullPointerException or NoSuchAlgorithmException must be thrown when algorithm is null");
        } catch (NullPointerException e) {
        } catch (NoSuchAlgorithmException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                CertPathBuilder.getInstance(invalidValues[i], mProv
                        .getName());
                fail("NoSuchAlgorithmException must be thrown (type: ".concat(
                        invalidValues[i]).concat(")"));
            } catch (NoSuchAlgorithmException e) {
            }
        }
        String prov = null;
        for (int i = 0; i < validValues.length; i++) {
            try {
                CertPathBuilder.getInstance(validValues[i], prov);
                fail("IllegalArgumentException must be thrown when provider is null (type: "
                        .concat(validValues[i]).concat(")"));
            } catch (IllegalArgumentException e) {
            }
            try {
                CertPathBuilder.getInstance(validValues[i], "");
                fail("IllegalArgumentException must be thrown when provider is empty (type: "
                        .concat(validValues[i]).concat(")"));
            } catch (IllegalArgumentException e) {
            }
        }
        for (int i = 0; i < validValues.length; i++) {
            for (int j = 1; j < invalidValues.length; j++) {
                try {
                    CertPathBuilder.getInstance(validValues[i],
                            invalidValues[j]);
                    fail("NoSuchProviderException must be thrown (type: "
                            .concat(validValues[i]).concat(" provider: ")
                            .concat(invalidValues[j]).concat(")"));
                } catch (NoSuchProviderException e) {
                }
            }
        }
        CertPathBuilder cerPB;
        for (int i = 0; i < validValues.length; i++) {
            cerPB = CertPathBuilder.getInstance(validValues[i], mProv
                    .getName());
            assertEquals("Incorrect type", cerPB.getAlgorithm(), validValues[i]);
            assertEquals("Incorrect provider", cerPB.getProvider().getName(),
                    mProv.getName());
            checkResult(cerPB);
        }
    }

    /**
     * Test for <code>getInstance(String algorithm, Provider provider)</code>
     * method
     * Assertions:
     * throws NullPointerException when algorithm is null
     * throws NoSuchAlgorithmException when algorithm  is not correct
     * returns CertPathBuilder object
     */
    public void testGetInstance03() throws NoSuchAlgorithmException,
            IllegalArgumentException,
            InvalidAlgorithmParameterException, CertPathBuilderException {
        try {
            CertPathBuilder.getInstance(null, mProv);
            fail("NullPointerException or NoSuchAlgorithmException must be thrown when algorithm is null");
        } catch (NullPointerException e) {
        } catch (NoSuchAlgorithmException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                CertPathBuilder.getInstance(invalidValues[i], mProv);
                fail("NoSuchAlgorithmException must be thrown (type: ".concat(
                        invalidValues[i]).concat(")"));
            } catch (NoSuchAlgorithmException e) {
            }
        }
        Provider prov = null;
        for (int i = 0; i < validValues.length; i++) {
            try {
                CertPathBuilder.getInstance(validValues[i], prov);
                fail("IllegalArgumentException must be thrown when provider is null (type: "
                        .concat(validValues[i]).concat(")"));
            } catch (IllegalArgumentException e) {
            }
        }
        CertPathBuilder cerPB;
        for (int i = 0; i < validValues.length; i++) {
            cerPB = CertPathBuilder.getInstance(validValues[i], mProv);
            assertEquals("Incorrect type", cerPB.getAlgorithm(), validValues[i]);
            assertEquals("Incorrect provider", cerPB.getProvider(), mProv);
            checkResult(cerPB);
        }
    }
}
