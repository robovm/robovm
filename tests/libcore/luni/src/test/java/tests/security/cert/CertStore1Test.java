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

import org.apache.harmony.security.tests.support.SpiEngUtils;
import org.apache.harmony.security.tests.support.cert.MyCertStoreParameters;
import org.apache.harmony.security.tests.support.cert.MyCertStoreSpi;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;
import java.security.cert.CertStore;
import java.security.cert.CertStoreException;
import java.security.cert.CertStoreParameters;
import java.security.cert.CertStoreSpi;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.LDAPCertStoreParameters;
import java.util.Collection;

/**
 * Tests for <code>CertStore</code> class constructors and
 * methods.
 *
 */
public class CertStore1Test extends TestCase {

    public static final String srvCertStore = "CertStore";

    private static final String defaultType = "LDAP";
    public static final String [] validValues =  {
            "LDAP", "ldap", "Ldap", "lDAP", "lDaP" };
    public static  String [] validValuesC = null;

    private static String [] invalidValues = SpiEngUtils.invalidValues;

    private static boolean LDAPSupport = false;
    private static final String CollectionType = "Collection";
    private static boolean CollectionSupport = false;

    private static Provider defaultProvider;
    private static String defaultProviderName;
    private static Provider defaultProviderCol;
    private static String defaultProviderColName;

    private static String NotSupportMsg = "";

    static {
        defaultProvider = SpiEngUtils.isSupport(defaultType,
                srvCertStore);
        LDAPSupport = (defaultProvider != null);
        defaultProviderName = (LDAPSupport ? defaultProvider.getName() : null);
        NotSupportMsg = "LDAP and Collection algorithm are not supported";

        defaultProviderCol = SpiEngUtils.isSupport(CollectionType,
                srvCertStore);
        CollectionSupport = (defaultProviderCol != null);
        defaultProviderColName = (CollectionSupport ? defaultProviderCol.getName() : null);
        if (CollectionSupport) {
            validValuesC = new String[3];
            validValuesC[0] = CollectionType;
            validValuesC[1] = CollectionType.toUpperCase();
            validValuesC[2] = CollectionType.toLowerCase();
        }
    }

    private Provider dProv = null;
    private String dName = null;
    private String dType = null;
    private CertStoreParameters dParams = null;
    private String[] dValid;

    private boolean initParams() {
        if (!LDAPSupport && !CollectionSupport) {
            fail(NotSupportMsg);
            return false;
        }
        dParams = (CollectionSupport ? (CertStoreParameters)new CollectionCertStoreParameters() :
            (CertStoreParameters)new LDAPCertStoreParameters());
        dType = (CollectionSupport ? CollectionType : defaultType );
        dProv = (CollectionSupport ? defaultProviderCol : defaultProvider );
        dName = (CollectionSupport ? defaultProviderColName : defaultProviderName );
        dValid = (CollectionSupport ? validValuesC : validValues );
        return true;
    }
    private CertStore [] createCS() {
        if (!LDAPSupport && !CollectionSupport) {
            fail(NotSupportMsg);
            return null;
        }
        try {
            CertStore [] ss = new CertStore[3];
            ss[0] = CertStore.getInstance(dType, dParams);
            ss[1] = CertStore.getInstance(dType, dParams, dProv);
            ss[2] = CertStore.getInstance(dType, dParams, dName);
            return ss;
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * Test for <code>getDefaultType()</code> method
     * Assertion: returns security property "certstore.type" or "LDAP"
     */
    public void testCertStore01() {
        if (!LDAPSupport) {
            return;
        }
        String dt = CertStore.getDefaultType();
        String sn = Security.getProperty("certstore.type");
        String def = "Proba.cert.store.type";
        if (sn == null) {
            sn = defaultType;
        }
        assertNotNull("Default type have not be null", dt);
        assertEquals("Incorrect default type", dt, sn);

        Security.setProperty("certstore.type", def);
        dt = CertStore.getDefaultType();
        assertEquals("Incorrect default type", dt, def);
        Security.setProperty("certstore.type", sn);
        assertEquals("Incorrect default type", Security.getProperty("certstore.type"), sn );
    }
    /**
     * Test for
     * <code>CertStore</code> constructor
     * Assertion: returns CertStore object
     */
    public void testCertStore02() throws InvalidAlgorithmParameterException, CertStoreException {
        if (!initParams()) {
            return;
        }
        MyCertStoreParameters pp = new MyCertStoreParameters();
        CertStoreSpi spi = new MyCertStoreSpi(pp);
        CertStore certS = new myCertStore(spi, dProv, dType, pp);
        assertEquals("Incorrect algorithm", certS.getType(), dType);
        assertEquals("Incorrect provider", certS.getProvider(), dProv);
        assertTrue("Incorrect parameters", certS.getCertStoreParameters()
                instanceof MyCertStoreParameters);
        try {
            certS.getCertificates(null);
            fail("CertStoreException must be thrown");
        } catch (CertStoreException e) {
        }
        certS = new myCertStore(null, null, null, null);
        assertNull("Incorrect algorithm", certS.getType());
        assertNull("Incorrect provider", certS.getProvider());
        assertNull("Incorrect parameters", certS.getCertStoreParameters());
        try {
            certS.getCertificates(null);
            fail("NullPointerException must be thrown");
        } catch (NullPointerException e) {
        }
    }

    /**
     * Test for <code>getInstance(String type, CertStoreParameters params)</code> method
     * Assertion:
     * throws NullPointerException when type is null
     * throws NoSuchAlgorithmException when type is incorrect;
     */
    public void testCertStore03() throws InvalidAlgorithmParameterException {
        if (!initParams()) {
            return;
        }
        try {
            CertStore.getInstance(null, dParams);
            fail("NullPointerException or NoSuchAlgorithmException must be thrown when type is null");
        } catch (NullPointerException e) {
        } catch (NoSuchAlgorithmException e) {
        }
        for (int i = 0; i < invalidValues.length; i++ ) {
            try {
                CertStore.getInstance(invalidValues[i], dParams);
                fail("NoSuchAlgorithmException must be thrown");
            } catch (NoSuchAlgorithmException e) {
            }
        }
    }

    /**
     * Test for <code>getInstance(String type, CertStoreParameters params)</code> method
     * Assertion: return CertStore object
     */
    public void testCertStore05()
            throws InvalidAlgorithmParameterException, NoSuchAlgorithmException {
        if (!initParams()) {
            return;
        }
        CertStore certS;
        for (int i = 0; i < dValid.length; i++) {
            certS = CertStore.getInstance(dValid[i], dParams);
            assertEquals("Incorrect type", certS.getType(), dValid[i]);
            certS.getCertStoreParameters();
        }
    }
    /**
     * Test for method
     * <code>getInstance(String type, CertStoreParameters params, String provider)</code>
     * Assertion: throws IllegalArgumentException when provider is null or empty
     *
     */
    public void testCertStore06()
            throws InvalidAlgorithmParameterException, NoSuchAlgorithmException,
                NoSuchProviderException {
        if (!initParams()) {
            return;
        }
        String provider = null;
        for (int i = 0; i < dValid.length; i++) {
            try {
                CertStore.getInstance(dValid[i], dParams, provider);
                fail("IllegalArgumentException must be thrown");
            } catch (IllegalArgumentException e) {
            }
            try {
                CertStore.getInstance(dValid[i], dParams, "");
                fail("IllegalArgumentException must be thrown");
            } catch (IllegalArgumentException e) {
            }
        }
    }
    /**
     * Test for method
     * <code>getInstance(String type, CertStoreParameters params, String provider)</code>
     * Assertion: throws NoSuchProviderException when provider has invalid value
     */
    public void testCertStore07()
            throws InvalidAlgorithmParameterException, NoSuchAlgorithmException {
        if (!initParams()) {
            return;
        }
        for (int i = 0; i < dValid.length; i++) {
            for (int j = 1; j < invalidValues.length; j++ ) {
                try {
                    CertStore.getInstance(dValid[i], dParams, invalidValues[j]);
                    fail("NoSuchProviderException must be thrown");
                } catch (NoSuchProviderException e) {
                }
            }
        }
    }
    /**
     * Test for method
     * <code>getInstance(String type, CertStoreParameters params, String provider)</code>
     * Assertion:
     * throws NullPointerException when type is null
     * throws NoSuchAlgorithmException when type is incorrect;
     */
    public void testCertStore08() throws InvalidAlgorithmParameterException,
            NoSuchProviderException {
        if (!initParams()) {
            return;
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                CertStore.getInstance(invalidValues[i], dParams, dName);
                fail("NoSuchAlgorithmException must be thrown");
            } catch (NoSuchAlgorithmException e){
            }
        }
        try {
            CertStore.getInstance(null, dParams, dName);
            fail("NullPointerException or NoSuchAlgorithmException must be thrown when type is null");
        } catch (NullPointerException e) {
        } catch (NoSuchAlgorithmException e) {
        }
    }

    /**
     * Test for method
     * <code>getInstance(String type, CertStoreParameters params, String provider)</code>
     * Assertion: return CertStore object
     */
    public void testCertStore10()
            throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
        if (!initParams()) {
            return;
        }
        CertStore certS;
        for (int i = 0; i < dValid.length; i++) {
            certS = CertStore.getInstance(dValid[i], dParams, dName);
            assertEquals("Incorrect type", certS.getType(), dValid[i]);
            certS.getCertStoreParameters();
        }
    }

    /**
     * Test for method
     * <code>getInstance(String type, CertStoreParameters params, Provider provider)</code>
     * Assertion: throws IllegalArgumentException when provider is null
     */
    public void testCertStore11() throws InvalidAlgorithmParameterException,
            NoSuchAlgorithmException {
        if (!initParams()) {
            return;
        }
        Provider provider = null;
        for (int i = 0; i < dValid.length; i++) {
            try {
                CertStore.getInstance(dValid[i], dParams, provider);
                fail("IllegalArgumentException must be thrown");
            } catch (IllegalArgumentException e) {
            }
        }
    }
    /**
     * Test for <code>getInstance(String type, CertStoreParameters params, Provider provider)</code> method
     * Assertion:
     * throws NullPointerException when type is null
     * throws NoSuchAlgorithmException when type is incorrect;
     */
    public void testCertStore12() throws InvalidAlgorithmParameterException {
        if (!initParams()) {
            return;
        }
        try {
            CertStore.getInstance(null, dParams, dProv);
            fail("NullPointerException or NoSuchAlgorithmException must be thrown when type is null");
        } catch (NullPointerException e) {
        } catch (NoSuchAlgorithmException e) {
        }
        for (int i = 0; i < invalidValues.length; i++ ) {
            try {
                CertStore.getInstance(invalidValues[i], dParams, dProv);
                fail("NoSuchAlgorithmException must be thrown");
            } catch (NoSuchAlgorithmException e) {
            }
        }
    }

    /**
     * Test for method
     * <code>getInstance(String type, CertStoreParameters params, Provider provider)</code>
     * Assertion: return CertStore object
     */
    public void testCertStore14()
            throws InvalidAlgorithmParameterException, NoSuchAlgorithmException {
        if (!initParams()) {
            return;
        }
        CertStore certS;
        for (int i = 0; i < dValid.length; i++) {
            certS = CertStore.getInstance(dValid[i], dParams, dProv);
            assertEquals("Incorrect type", certS.getType(), dValid[i]);
            certS.getCertStoreParameters();
        }
    }
    /**
     * Test for methods
     * <code>getCertificates(CertSelector selector)</code>
     * <code>getCRLs(CRLSelector selector)</code>
     * Assertion: returns empty Collection when selector is null
     */
    public void testCertStore15() throws CertStoreException {
        if (!initParams()) {
            return;
        }
        CertStore [] certS = createCS();
        assertNotNull("CertStore object were not created", certS);
        Collection<?> coll;
        for (int i = 0; i < certS.length; i++) {
            coll = certS[i].getCertificates(null);
            assertTrue("Result collection not empty",coll.isEmpty());
            coll = certS[i].getCRLs(null);
            assertTrue("Result collection not empty",coll.isEmpty());
        }
    }

    /**
     * Test for <code>getType()</code> method
     */
    public void testCertStore16() throws NoSuchAlgorithmException,
            InvalidAlgorithmParameterException {
        if (!initParams()) {
            return;
        }

        CertStore certS;
        for (int i = 0; i < dValid.length; i++) {
            certS = CertStore.getInstance(dValid[i], dParams);
            assertEquals("Incorrect type", certS.getType(), dValid[i]);

            try {
                certS = CertStore.getInstance(dValid[i], dParams,
                        defaultProviderCol);
                assertEquals("Incorrect type", certS.getType(), dValid[i]);
            } catch (IllegalArgumentException e) {
                fail("Unexpected IllegalArgumentException " + e.getMessage());
            }

            try {
                certS = CertStore.getInstance(dValid[i], dParams,
                        defaultProviderColName);
                assertEquals("Incorrect type", certS.getType(), dValid[i]);
            } catch (NoSuchProviderException e) {
                fail("Unexpected IllegalArgumentException " + e.getMessage());
            }
        }
    }

    /**
     * Test for <code>getProvider()</code> method
     */
    public void testCertStore17() throws NoSuchAlgorithmException,
            InvalidAlgorithmParameterException {
        if (!initParams()) {
            return;
        }

        CertStore certS;
        for (int i = 0; i < dValid.length; i++) {
            try {
                certS = CertStore.getInstance(dValid[i], dParams,
                        defaultProviderCol);
                assertEquals("Incorrect provider", certS.getProvider(),
                        defaultProviderCol);
            } catch (IllegalArgumentException e) {
                fail("Unexpected IllegalArgumentException " + e.getMessage());
            }

            try {
                certS = CertStore.getInstance(dValid[i], dParams,
                        defaultProviderColName);
                assertEquals("Incorrect provider", certS.getProvider(),
                        defaultProviderCol);
            } catch (NoSuchProviderException e) {
                fail("Unexpected IllegalArgumentException " + e.getMessage());
            }
}
    }

    /**
     * Test for <code>getCertStoreParameters()</code> method
     */
    public void testCertStore18() throws NoSuchAlgorithmException,
            InvalidAlgorithmParameterException {
        if (!initParams()) {
            return;
        }

        CertStore certS;
        for (int i = 0; i < dValid.length; i++) {
            certS = CertStore.getInstance(dValid[i], dParams);
            assertEquals("Incorrect parameters",
                    ((CollectionCertStoreParameters) certS
                            .getCertStoreParameters()).getCollection(),
                    ((CollectionCertStoreParameters) dParams).getCollection());

            try {
                certS = CertStore.getInstance(dValid[i], dParams,
                        defaultProviderCol);
                assertEquals("Incorrect parameters",
                        ((CollectionCertStoreParameters) certS
                                .getCertStoreParameters()).getCollection(),
                        ((CollectionCertStoreParameters) dParams)
                                .getCollection());
            } catch (IllegalArgumentException e) {
                fail("Unexpected IllegalArgumentException " + e.getMessage());
            }

            try {
                certS = CertStore.getInstance(dValid[i], dParams,
                        defaultProviderColName);
                assertEquals("Incorrect parameters",
                        ((CollectionCertStoreParameters) certS
                                .getCertStoreParameters()).getCollection(),
                        ((CollectionCertStoreParameters) dParams)
                                .getCollection());
            } catch (NoSuchProviderException e) {
                fail("Unexpected IllegalArgumentException " + e.getMessage());
            }
        }
    }
}

/**
 * Additional class to verify CertStore constructor
 */
class myCertStore extends CertStore {
    public myCertStore(CertStoreSpi spi, Provider prov, String type, CertStoreParameters params) {
        super(spi, prov, type, params);
    }
}
