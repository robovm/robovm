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

package tests.security.cert;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectStreamException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import junit.framework.TestCase;
import org.apache.harmony.security.tests.support.cert.MyCertificate;
import org.apache.harmony.security.tests.support.cert.MyFailingCertificate;
import org.apache.harmony.security.tests.support.cert.TestUtils;
import org.apache.harmony.testframework.serialization.SerializationTest;

/**
 * Tests for <code>Certificate</code> fields and methods
 *
 */
public class CertificateTest extends TestCase {
    /**
     * Meaningless cert encoding just for testing purposes
     */
    private static final byte[] testEncoding = new byte[] { (byte) 1, (byte) 2,
            (byte) 3, (byte) 4, (byte) 5 };

    /**
     * Test for <code>Certificate(String type)</code> method<br>
     */
    public final void testCertificate() throws Exception {
        Certificate c1 = new MyCertificate("TEST_TYPE", testEncoding);
        assertTrue(Arrays.equals(testEncoding, c1.getEncoded()));
        assertEquals("TEST", c1.getPublicKey().getAlgorithm());
        assertTrue(Arrays.equals(new byte[] { (byte) 1, (byte) 2, (byte) 3 },
                                 c1.getPublicKey().getEncoded()));
        assertEquals("TEST_FORMAT", c1.getPublicKey().getFormat());
        assertEquals("TEST_TYPE", c1.getType());
    }

    /**
     * Test for <code>hashCode()</code> method<br>
     * Assertion: returns hash of the <code>Certificate</code> instance
     * @throws CertificateEncodingException
     */
    public final void testHashCode() throws CertificateEncodingException {
        Certificate c1 = new MyCertificate("TEST_TYPE", testEncoding);
        Certificate c2 = new MyCertificate("TEST_TYPE", testEncoding);

        assertTrue(c1.hashCode() == c2.hashCode());

        assertFalse(c1.hashCode() == new MyCertificate("TEST_TYPE", cert
                .getEncoded()).hashCode());
        assertFalse(c1.hashCode() == cert.hashCode());
    }

    /**
     * Test for <code>hashCode()</code> method<br>
     * Assertion: hash code of equal objects should be the same
     */
    public final void testHashCodeEqualsObject() {
        Certificate c1 = new MyCertificate("TEST_TYPE", testEncoding);
        Certificate c2 = new MyCertificate("TEST_TYPE", testEncoding);

        assertTrue((c1.hashCode() == c2.hashCode()) && c1.equals(c2));
        assertFalse(cert.equals(c1));
    }


    /**
     * Test for <code>getType()</code> method<br>
     * Assertion: returns this certificate type
     */
    public final void testGetType() {
        Certificate c1 = new MyCertificate("TEST_TYPE", testEncoding);
        assertEquals("TEST_TYPE", c1.getType());
    }

    /**
     * Test #1 for <code>equals(Object)</code> method<br>
     * Assertion: object equals to itself
     */
    public final void testEqualsObject01() {
        Certificate c1 = new MyCertificate("TEST_TYPE", testEncoding);
        assertTrue(c1.equals(c1));
    }

    /**
     * Test for <code>equals(Object)</code> method<br>
     * Assertion: object equals to other <code>Certificate</code>
     * instance with the same state
     */
    public final void testEqualsObject02() {
        Certificate c1 = new MyCertificate("TEST_TYPE", testEncoding);
        Certificate c2 = new MyCertificate("TEST_TYPE", testEncoding);
        assertTrue(c1.equals(c2) && c2.equals(c1));
    }

    /**
     * Test for <code>equals(Object)</code> method<br>
     * Assertion: object not equals to <code>null</code>
     */
    public final void testEqualsObject03() {
        Certificate c1 = new MyCertificate("TEST_TYPE", testEncoding);
        assertFalse(c1.equals(null));
    }

    /**
     * Test for <code>equals(Object)</code> method<br>
     * Assertion: object not equals to other which is not
     * instance of <code>Certificate</code>
     */
    public final void testEqualsObject04() {
        Certificate c1 = new MyCertificate("TEST_TYPE", testEncoding);
        assertFalse(c1.equals("TEST_TYPE"));
    }

    // the following tests just call methods
    // that are abstract in <code>Certificate</code>
    // (So they just like signature tests)

    /**
     * This test just calls <code>getEncoded()</code> method<br>
     * @throws CertificateException
     */
    public final void testGetEncoded() throws CertificateException {
        Certificate c1 = new MyCertificate("TEST_TYPE", testEncoding);
        assertNotNull(c1.getEncoded());
        assertTrue(Arrays.equals(testEncoding,c1.getEncoded()));

        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        byte[] expectedEncoding = cert.getEncoded();
        Certificate actual = cf.generateCertificate(new ByteArrayInputStream(expectedEncoding));
        byte[] actualEncoding = actual.getEncoded();
        assertTrue(Arrays.equals(expectedEncoding, actualEncoding));

        assertFalse(expectedEncoding[4] == 200);
        expectedEncoding[4] = (byte) 200;
        try {
            cf.generateCertificate(new ByteArrayInputStream(expectedEncoding));
            fail();
        } catch (CertificateException expected) {
        }
    }

    /**
     * This test just calls <code>verify(PublicKey)</code> method<br>
     *
     * @throws InvalidKeyException
     * @throws CertificateException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws SignatureException
     */
    public final void testVerifyPublicKey()
        throws InvalidKeyException,
               CertificateException,
               NoSuchAlgorithmException,
               NoSuchProviderException,
               SignatureException {
        Certificate c1 = new MyCertificate("TEST_TYPE", testEncoding);
        c1.verify(null);
    }

    /**
     * This test just calls <code>verify(PublicKey,String)</code> method<br>
     *
     * @throws InvalidKeyException
     * @throws CertificateException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws SignatureException
     */
    public final void testVerifyPublicKeyString()
        throws InvalidKeyException,
               CertificateException,
               NoSuchAlgorithmException,
               NoSuchProviderException,
               SignatureException {
        Certificate c1 = new MyCertificate("TEST_TYPE", testEncoding);
        c1.verify(null, null);
    }

    /**
     * This test just calls <code>toString()</code> method<br>
     */
    public final void testToString() {
        Certificate c1 = new MyCertificate("TEST_TYPE", testEncoding);
        c1.toString();
    }

    /**
     * This test just calls <code>testGetPublicKey()</code> method<br>
     */
    public final void testGetPublicKey() {
        Certificate c1 = new MyCertificate("TEST_TYPE", testEncoding);
        c1.getPublicKey();
    }

    /**
     * This test just calls <code>writeReplace()</code> method<br>
     */
    public final void testWriteReplace() throws Exception {
        MyCertificate c1 = new MyCertificate("TEST_TYPE", testEncoding);
        Object obj = c1.writeReplace();
        assertTrue(obj.toString().contains("java.security.cert.Certificate$CertificateRep"));
    }

public class MyModifiablePublicKey implements PublicKey {

        private PublicKey key;
        private boolean modifiedAlgo;
        private String algo;
        private String format;
        private boolean modifiedFormat;
        private boolean modifiedEncoding;
        private byte[] encoding;

        public MyModifiablePublicKey(PublicKey k) {
            super();
            this.key = k;
        }

        public String getAlgorithm() {
            if (modifiedAlgo) {
                return algo;
            } else {
                return key.getAlgorithm();
            }
        }

        public String getFormat() {
            if (modifiedFormat) {
                return this.format;
            } else {
                return key.getFormat();
            }

        }

        public byte[] getEncoded() {
            if (modifiedEncoding) {
                return this.encoding;
            } else {
                return key.getEncoded();
            }

        }

        public long getSerVerUID() {
            return key.serialVersionUID;
        }

        public void setAlgorithm(String myAlgo) {
            modifiedAlgo = true;
            this.algo = myAlgo;
        }

        public void setFormat(String myFormat) {
            modifiedFormat = true;
            format = myFormat;
        }

        public void setEncoding(byte[] myEncoded) {
            modifiedEncoding = true;
            encoding = myEncoded;
        }
    }

    private Certificate cert;

    private Provider wrongProvider;

    private Provider usefulProvider;

    public void setUp() throws Exception {
        super.setUp();
        TestUtils.initCertPathSSCertChain();
        cert = TestUtils.rootCertificateSS;
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        wrongProvider = cf.getProvider();
        usefulProvider = Signature.getInstance("SHA1WithRSA").getProvider();
    }

    /**
     * This test just calls <code>verify(PublicKey,String)</code> method<br>
     *
     * @throws InvalidKeyException
     * @throws CertificateException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws SignatureException
     */
    public final void testVerifyPublicKeyString2() throws InvalidKeyException,
            CertificateException, NoSuchAlgorithmException,
            NoSuchProviderException, SignatureException {

        // real test
        cert.verify(cert.getPublicKey(), usefulProvider.getName());

        // Exception tests

        try {
            cert.verify(cert.getPublicKey(), "UnknownProvider");
            fail();
        } catch (NoSuchProviderException expected) {
        }

        // This test has side effects affecting all other tests running later
        // on in the same vm instance. Maybe a better way would be to first add
        // a new provider, test if it works, then remove it and test if the
        // exception is thrown.
        //
        // Security.removeProvider(wrongProvider.getName());
        //
        // try {
        //     cert.verify(cert.getPublicKey(), wrongProvider.getName());
        //     fail();
        // } catch (NoSuchAlgorithmException expected) {
        // }
        //
        // Security.insertProviderAt(wrongProvider, oldPosition);

        /*
        PublicKey k = cert.getPublicKey();
        MyModifiablePublicKey tamperedKey = new MyModifiablePublicKey(k);
        tamperedKey.setAlgorithm("wrongAlgo");

        try {
            cert.verify(tamperedKey, provs[0].getName());
            fail();
        } catch (SignatureException expected) {
        }

        try {
            cert.verify(c1.getPublicKey(), provs[0].getName());
            fail();
        } catch (InvalidKeyException expected) {
        }
        */
    }

    /**
     * This test just calls <code>verify(PublicKey)</code> method<br>
     *
     * @throws InvalidKeyException
     * @throws CertificateException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws SignatureException
     * @throws IOException
     * @throws InvalidAlgorithmParameterException
     */
    public final void testVerifyPublicKey2()
            throws InvalidKeyException, CertificateException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException, InvalidAlgorithmParameterException, IOException {

        Certificate c1 = new MyCertificate("TEST_TYPE", testEncoding);
        c1.verify(null);

        cert.verify(cert.getPublicKey());

        PublicKey k = cert.getPublicKey();

        MyModifiablePublicKey changedEncoding = new MyModifiablePublicKey(k);
        changedEncoding.setEncoding(new byte[cert.getEncoded().length - 1]);

        try {
            cert.verify(c1.getPublicKey());
            fail();
        } catch (InvalidKeyException expected) {
        }

        try {
            cert.verify(changedEncoding);
            fail();
        } catch (Exception expected) {
        }
    }

    /**
     * This test just calls <code>writeReplace()</code> method<br>
     */
    public final void testWriteReplace2() {
        MyCertificate c1 = new MyFailingCertificate("TEST_TYPE", testEncoding);

        try {
            c1.writeReplace();
            fail();
        } catch (ObjectStreamException expected) {
        }
    }

    /**
     * serialization/deserialization compatibility.
     */
    public void testSerializationSelf() throws Exception {
        TestUtils.initCertPathSSCertChain();

        SerializationTest.verifySelf(TestUtils.rootCertificateSS);
    }

    /**
     * serialization/deserialization compatibility with RI.
     */
    public void testSerializationCompatibility() throws Exception {
        // create test file (once)
        // SerializationTest.createGoldenFile("/sdcard", this, TestUtils.rootCertificateSS);
        TestUtils.initCertPathSSCertChain();

        SerializationTest.verifyGolden(this, TestUtils.rootCertificateSS);
    }
}
