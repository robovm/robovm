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

import java.security.cert.CertPath;
import java.security.cert.CertPathValidatorException;
import java.security.cert.Certificate;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;


/**
 * Tests for <code>CertPathValidatorException</code> class constructors and
 * methods.
 *
 */
public class CertPathValidatorExceptionTest extends TestCase {

    private static String[] msgs = {
            "",
            "Check new message",
            "Check new message Check new message Check new message Check new message Check new message" };

    private static Throwable tCause = new Throwable("Throwable for exception");

    /**
     * Test for <code>CertPathValidatorException()</code> constructor
     * Assertion: constructs CertPathValidatorException with no detail message
     */
    public void testCertPathValidatorException01() {
        CertPathValidatorException tE = new CertPathValidatorException();
        assertNull("getMessage() must return null.", tE.getMessage());
        assertNull("getCause() must return null", tE.getCause());
    }

    /**
     * Test for <code>CertPathValidatorException(String)</code> constructor
     * Assertion: constructs CertPathValidatorException with detail message msg.
     * Parameter <code>msg</code> is not null.
     */
    public void testCertPathValidatorException02() {
        CertPathValidatorException tE;
        for (int i = 0; i < msgs.length; i++) {
            tE = new CertPathValidatorException(msgs[i]);
            assertEquals("getMessage() must return: ".concat(msgs[i]), tE
                    .getMessage(), msgs[i]);
            assertNull("getCause() must return null", tE.getCause());
        }
    }

    /**
     * Test for <code>CertPathValidatorException(String)</code> constructor
     * Assertion: constructs CertPathValidatorException when <code>msg</code>
     * is null
     */
    public void testCertPathValidatorException03() {
        String msg = null;
        CertPathValidatorException tE = new CertPathValidatorException(msg);
        assertNull("getMessage() must return null.", tE.getMessage());
        assertNull("getCause() must return null", tE.getCause());
    }

    /**
     * Test for <code>CertPathValidatorException(Throwable)</code> constructor
     * Assertion: constructs CertPathValidatorException when <code>cause</code>
     * is null
     */
    public void testCertPathValidatorException04() {
        Throwable cause = null;
        CertPathValidatorException tE = new CertPathValidatorException(cause);
        assertNull("getMessage() must return null.", tE.getMessage());
        assertNull("getCause() must return null", tE.getCause());
    }

    /**
     * Test for <code>CertPathValidatorException(Throwable)</code> constructor
     * Assertion: constructs CertPathValidatorException when <code>cause</code>
     * is not null
     */
    public void testCertPathValidatorException05() {
        CertPathValidatorException tE = new CertPathValidatorException(tCause);
        if (tE.getMessage() != null) {
            String toS = tCause.toString();
            String getM = tE.getMessage();
            assertTrue("getMessage() should contain ".concat(toS), (getM
                    .indexOf(toS) != -1));
        }
        assertNotNull("getCause() must not return null", tE.getCause());
        assertEquals("getCause() must return ".concat(tCause.toString()), tE
                .getCause(), tCause);
    }

    /**
     * Test for <code>CertPathValidatorException(String, Throwable)</code>
     * constructor Assertion: constructs CertPathValidatorException when
     * <code>cause</code> is null <code>msg</code> is null
     */
    public void testCertPathValidatorException06() {
        CertPathValidatorException tE = new CertPathValidatorException(null,
                null);
        assertNull("getMessage() must return null", tE.getMessage());
        assertNull("getCause() must return null", tE.getCause());
    }

    /**
     * Test for <code>CertPathValidatorException(String, Throwable)</code>
     * constructor Assertion: constructs CertPathValidatorException when
     * <code>cause</code> is null <code>msg</code> is not null
     */
    public void testCertPathValidatorException07() {
        CertPathValidatorException tE;
        for (int i = 0; i < msgs.length; i++) {
            tE = new CertPathValidatorException(msgs[i], null);
            assertEquals("getMessage() must return: ".concat(msgs[i]), tE
                    .getMessage(), msgs[i]);
            assertNull("getCause() must return null", tE.getCause());
        }
    }

    /**
     * Test for <code>CertPathValidatorException(String, Throwable)</code>
     * constructor Assertion: constructs CertPathValidatorException when
     * <code>cause</code> is not null <code>msg</code> is null
     */
    public void testCertPathValidatorException08() {
        CertPathValidatorException tE = new CertPathValidatorException(null,
                tCause);
        if (tE.getMessage() != null) {
            String toS = tCause.toString();
            String getM = tE.getMessage();
            assertTrue("getMessage() must should ".concat(toS), (getM
                    .indexOf(toS) != -1));
        }
        assertNotNull("getCause() must not return null", tE.getCause());
        assertEquals("getCause() must return ".concat(tCause.toString()), tE
                .getCause(), tCause);
    }

    /**
     * Test for <code>CertPathValidatorException(String, Throwable)</code>
     * constructor Assertion: constructs CertPathValidatorException when
     * <code>cause</code> is not null <code>msg</code> is not null
     */
    public void testCertPathValidatorException09() {
        CertPathValidatorException tE;
        for (int i = 0; i < msgs.length; i++) {
            tE = new CertPathValidatorException(msgs[i], tCause);
            String getM = tE.getMessage();
            String toS = tCause.toString();
            if (msgs[i].length() > 0) {
                assertTrue("getMessage() must contain ".concat(msgs[i]), getM
                        .indexOf(msgs[i]) != -1);
                if (!getM.equals(msgs[i])) {
                    assertTrue("getMessage() should contain ".concat(toS), getM
                            .indexOf(toS) != -1);
                }
            }
            assertNotNull("getCause() must not return null", tE.getCause());
            assertEquals("getCause() must return ".concat(tCause.toString()),
                    tE.getCause(), tCause);
        }
    }

    /**
     * Test for
     * <code>CertPathValidatorException(String, Throwable, CertPath, int)</code>
     * constructor Assertion: constructs CertPathValidatorException when
     * <code>cause</code> is null <code>msg</code> is null
     * <code>certPath</code> is null <code>index</code> is -1
     */
    public void testCertPathValidatorException10() {
        CertPathValidatorException tE = new CertPathValidatorException(null,
                null, null, -1);
        assertNull("getMessage() must return null", tE.getMessage());
        assertNull("getCause() must return null", tE.getCause());
        assertNull("getCertPath() must return null", tE.getCertPath());
        assertEquals("getIndex() must be -1", tE.getIndex(), -1);
    }

    /**
     * Test for
     * <code>CertPathValidatorException(String, Throwable, CertPath, int)</code>
     * constructor Assertion: constructs CertPathValidatorException when
     * <code>cause</code> is null <code>msg</code> is null
     * <code>certPath</code> is null <code>index</code> not -1 throws:
     * IllegalArgumentException
     */
    public void testCertPathValidatorException11() {
        int[] indx = { 0, 1, 100, Integer.MAX_VALUE, Integer.MIN_VALUE };
        for (int j = 0; j < indx.length; j++) {
            for (int i = 0; i < msgs.length; i++) {
                try {
                    new CertPathValidatorException(msgs[i], tCause, null, indx[j]);
                    fail("Error. IllegalArgumentException was not thrown as expected. "
                            + " msg: "
                            + msgs[i]
                            + ", certPath is null and index is " + indx[j]);
                } catch (IllegalArgumentException e) {
                }
            }
        }
    }

    /**
     * Test for
     * <code>CertPathValidatorException(String, Throwable, CertPath, int)</code>
     * constructor Assertion: constructs CertPathValidatorException when
     * <code>cause</code> not null <code>msg</code> not null
     * <code>certPath</code> is null <code>index</code> is -1
     */
    public void testCertPathValidatorException12() {
        CertPathValidatorException tE;

        for (int i = 0; i < msgs.length; i++) {
            try {
                tE = new CertPathValidatorException(msgs[i], tCause, null, -1);
                String getM = tE.getMessage();
                String toS = tCause.toString();
                if (msgs[i].length() > 0) {
                    assertTrue("getMessage() must contain ".concat(msgs[i]),
                            getM.indexOf(msgs[i]) != -1);
                    if (!getM.equals(msgs[i])) {
                        assertTrue("getMessage() should contain ".concat(toS),
                                getM.indexOf(toS) != -1);
                    }
                }
                assertNotNull("getCause() must not return null", tE.getCause());
                assertEquals("getCause() must return "
                        .concat(tCause.toString()), tE.getCause(), tCause);
                assertNull("getCertPath() must return null", tE.getCertPath());
                assertEquals("getIndex() must return -1", tE.getIndex(), -1);
            } catch (IndexOutOfBoundsException e) {
                fail("Unexpected exception: " + e.toString()
                        + " Parameters: msg: " + msgs[i]
                        + ", certPath is null and index is -1");
            }
        }
    }

    /**
     * Test for
     * <code>CertPathValidatorException(String, Throwable, CertPath, int)</code>
     * constructor Assertion: constructs CertPathValidatorException when
     * <code>cause</code> not null <code>msg</code> not null
     * <code>certPath</code> not null <code>index</code>< -1 || >=
     * certPath.getCertificates().size() throws: IndexOutOfBoundsException
     */
    public void testCertPathValidatorException13() {
        myCertPath mcp = new myCertPath("X.509", "");
        CertPath cp = mcp.get("X.509");
        int[] indx = { -2, -100, 0, 1, 100, Integer.MAX_VALUE,
                Integer.MIN_VALUE };
        for (int j = 0; j < indx.length; j++) {
            for (int i = 0; i < msgs.length; i++) {
                try {
                    new CertPathValidatorException(msgs[i], tCause, cp, indx[j]);
                    fail("IndexOutOfBoundsException was not thrown as expected. "
                            + " msg: "
                            + msgs[i]
                            + ", certPath is null and index is " + indx[j]);
                } catch (IndexOutOfBoundsException e) {
                }
            }
        }
    }

    /**
     * Test for
     * <code>CertPathValidatorException(String, Throwable, CertPath, int)</code>
     * constructor Assertion: constructs CertPathValidatorException when
     * <code>cause</code> not null <code>msg</code> not null
     * <code>certPath</code> not null <code>index</code><
     * certPath.getCertificates().size()
     */
    public void testCertPathValidatorException14() {
        CertPathValidatorException tE;
        myCertPath mcp = new myCertPath("X.509", "");
        CertPath cp = mcp.get("X.509");
        for (int i = 0; i < msgs.length; i++) {
            try {
                tE = new CertPathValidatorException(msgs[i], tCause, cp, -1);
                String getM = tE.getMessage();
                String toS = tCause.toString();
                if (msgs[i].length() > 0) {
                    assertTrue("getMessage() must contain ".concat(msgs[i]),
                            getM.indexOf(msgs[i]) != -1);
                    if (!getM.equals(msgs[i])) {
                        assertTrue("getMessage() should contain ".concat(toS),
                                getM.indexOf(toS) != -1);
                    }
                }
                assertNotNull("getCause() must not return null", tE.getCause());
                assertEquals("getCause() must return "
                        .concat(tCause.toString()), tE.getCause(), tCause);
                assertNotNull("getCertPath() must not return null", tE
                        .getCertPath());
                assertEquals(
                        "getCertPath() must return ".concat(cp.toString()), tE
                                .getCertPath(), cp);
                assertEquals("getIndex() must return -1", tE.getIndex(), -1);

            } catch (IndexOutOfBoundsException e) {
                fail("Unexpected IndexOutOfBoundsException was thrown. "
                        + e.toString());
            }
        }
    }

    /**
     * Test for <code>getCertPath()</code>. Returns the certification path
     * that was being validated when the exception was thrown.
     */
    public void testCertPathValidatorException15() {
        CertPathValidatorException tE = new CertPathValidatorException();
        assertNull("getCertPath() must return null.", tE.getCertPath());

        for (int i = 0; i < msgs.length; i++) {
            tE = new CertPathValidatorException(msgs[i]);
            assertNull("getCertPath() must return null ", tE.getCertPath());
        }

        Throwable cause = null;
        tE = new CertPathValidatorException(cause);
        assertNull("getCertPath() must return null.", tE.getCertPath());

        tE = new CertPathValidatorException(tCause);
        assertNull("getCertPath() must return null.", tE.getCertPath());

        for (int i = 0; i < msgs.length; i++) {
            tE = new CertPathValidatorException(msgs[i], tCause);
            assertNull("getCertPath() must return null", tE.getCertPath());
        }

        tE = new CertPathValidatorException(null, null, null, -1);
        assertNull("getCertPath() must return null", tE.getCertPath());

        for (int i = 0; i < msgs.length; i++) {
            try {
                tE = new CertPathValidatorException(msgs[i], tCause, null, -1);
                assertNull("getCertPath() must return null", tE.getCertPath());
            } catch (IndexOutOfBoundsException e) {
                fail("Unexpected exception: " + e.getMessage());
            }
        }

        myCertPath mcp = new myCertPath("X.509", "");
        CertPath cp = mcp.get("X.509");
        for (int i = 0; i < msgs.length; i++) {
            try {
                tE = new CertPathValidatorException(msgs[i], tCause, cp, -1);
                assertNotNull("getCertPath() must not return null", tE
                        .getCertPath());
                assertEquals(
                        "getCertPath() must return ".concat(cp.toString()), tE
                                .getCertPath(), cp);
            } catch (IndexOutOfBoundsException e) {
                fail("Unexpected IndexOutOfBoundsException was thrown. "
                        + e.toString());
            }
        }
    }

    /**
     * Test for <code>getIndex()</code>. Returns the index of the certificate
     * in the certification path that caused the exception to be thrown. Note
     * that the list of certificates in a CertPath is zero based. If no index
     * has been set, -1 is returned.
     */
    public void testCertPathValidatorException16() {
        CertPathValidatorException tE = new CertPathValidatorException();
        assertEquals("getIndex() must be equals -1", -1, tE.getIndex());

        for (int i = 0; i < msgs.length; i++) {
            tE = new CertPathValidatorException(msgs[i]);
            assertEquals("getIndex() must be equals -1", -1, tE.getIndex());
        }

        Throwable cause = null;
        tE = new CertPathValidatorException(cause);
        assertEquals("getIndex() must be equals -1", -1, tE.getIndex());

        tE = new CertPathValidatorException(tCause);
        assertEquals("getIndex() must be equals -1", -1, tE.getIndex());

        for (int i = 0; i < msgs.length; i++) {
            tE = new CertPathValidatorException(msgs[i], tCause);
            assertEquals("getIndex() must be equals -1", -1, tE.getIndex());
        }

        tE = new CertPathValidatorException(null, null, null, -1);
        assertEquals("getIndex() must be equals -1", -1, tE.getIndex());

        myCertPath mcp = new myCertPath("X.509", "");
        CertPath cp = mcp.get("X.509");
        for (int i = 0; i < msgs.length; i++) {
            try {
                tE = new CertPathValidatorException(msgs[i], tCause, cp, -1);
                assertNotNull("getIndex() must not return null", tE
                        .getCertPath());
                assertEquals(
                        "getIndex() must return ".concat(cp.toString()), tE
                                .getCertPath(), cp);
            } catch (IndexOutOfBoundsException e) {
                fail("Unexpected IndexOutOfBoundsException was thrown. "
                        + e.getMessage());
            }
        }
    }

    class myCertPath extends CertPath {

        private static final long serialVersionUID = 5871603047244722511L;

        public List<Certificate> getCertificates() {
            return new Vector<Certificate>();
        }

        public byte[] getEncoded() {
            return new byte[0];
        }

        public byte[] getEncoded(String s) {
            return new byte[0];
        }

        public Iterator<String> getEncodings() {
            return (Iterator<String>) (new StringTokenizer("ss ss ss ss"));
        }

        protected myCertPath(String s) {
            super(s);
        }

        public CertPath get(String s) {
            return new myCertPath(s);
        }

        public myCertPath(String s, String s1) {
            super(s);
        }

    }
}


