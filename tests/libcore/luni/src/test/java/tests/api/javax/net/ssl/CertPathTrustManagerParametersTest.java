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

package tests.api.javax.net.ssl;

import java.security.cert.CertPathParameters;
import javax.net.ssl.CertPathTrustManagerParameters;

import junit.framework.TestCase;

/**
 * Tests for <code>CertPathTrustManagerParameters</code> class constructors
 * and methods.
 *
 */
public class CertPathTrustManagerParametersTest extends TestCase {

    /**
     * javax.net.ssl.CertPathTrustManagerParameters#
     *     CertPathTrustManagerParameters(java.security.cert.CertPathParameters)
     * Case 1: Try to construct object.
     * Case 2: Check NullPointerException.
     */
    public void test_ConstructorLjava_security_cert_CertPathParameters() {
        // case 1: Try to construct object.
        try {
            CertPathParameters parameters = new MyCertPathParameters();
            CertPathTrustManagerParameters p =
                new CertPathTrustManagerParameters(parameters);
            assertNotSame("Parameters were cloned incorrectly",
                    parameters, p.getParameters());
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString());
        }

        // case 2: Check NullPointerException.
        try {
            new CertPathTrustManagerParameters(null);
            fail("Expected CertPathTrustManagerParameters was not thrown");
        } catch (NullPointerException npe) {
            // expected
        }
    }

    /**
     * javax.net.ssl.CertPathTrustManagerParameters#getParameters()
     */
    public void test_getParameters() {
        CertPathParameters parameters = new MyCertPathParameters();
        CertPathTrustManagerParameters p = new CertPathTrustManagerParameters(
                parameters);
        if (!(p.getParameters() instanceof MyCertPathParameters)) {
            fail("incorrect parameters");
        }
        assertNotSame("Parameters were cloned incorrectly",
                parameters, p.getParameters());
    }
}

class MyCertPathParameters implements CertPathParameters {
    public Object clone() {
        return new MyCertPathParameters();
    }
}
