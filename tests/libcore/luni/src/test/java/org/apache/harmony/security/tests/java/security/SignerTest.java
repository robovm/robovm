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
* @author Aleksei Y. Semenov
* @version $Revision$
*/

package org.apache.harmony.security.tests.java.security;

import java.security.IdentityScope;
import java.security.InvalidParameterException;
import java.security.KeyManagementException;
import java.security.KeyPair;
import java.security.Permission;
import java.security.Permissions;
import java.security.SecurityPermission;
import java.security.Signer;

import org.apache.harmony.security.tests.support.PrivateKeyStub;
import org.apache.harmony.security.tests.support.PublicKeyStub;
import org.apache.harmony.security.tests.support.SignerStub;


import junit.framework.TestCase;

/**
 * tests for class Signer
 *
 */
@SuppressWarnings("deprecation")
public class SignerTest extends TestCase {

    public static class MySecurityManager extends SecurityManager {
        public Permissions denied = new Permissions();
        public void checkPermission(Permission permission){
            if (denied!=null && denied.implies(permission)) throw new SecurityException();
        }
    }

    /**
     * java.security.Signer#toString()
     */
    public void test_toString() throws Exception {
        Signer s1 = new SignerStub("testToString1");
        assertEquals("[Signer]testToString1", s1.toString());

        Signer s2 = new SignerStub("testToString2", IdentityScope.getSystemScope());
        s2.toString();

        KeyPair kp = new KeyPair(new PublicKeyStub("public", "SignerTest.testToString", null),
                new PrivateKeyStub("private", "SignerTest.testToString", null));
        s1.setKeyPair(kp);
        s1.toString();

        s2.setKeyPair(kp);
        s2.toString();
    }

    /**
     * verify Signer() creates instance
     */
    public void testSigner() {
        Signer s = new SignerStub();
        assertNotNull(s);
        //assertNull(s.getName(), s.getName());
        assertNull(s.getPrivateKey());
    }

    /**
     * verify Signer(String) creates instance
     */
    public void testSignerString() throws Exception {
        Signer s = new SignerStub("sss3");
        assertNotNull(s);
        assertEquals("sss3", s.getName());
        assertNull(s.getPrivateKey());

        Signer s2 = new SignerStub(null);
        assertNull(s2.getName());

    }

    /**
     * verify  Signer(String, IdentityScope) creates instance
     */
    public void testSignerStringIdentityScope() throws Exception {
        Signer s = new SignerStub("sss4", IdentityScope.getSystemScope());
        assertNotNull(s);
        assertEquals("sss4", s.getName());
        assertSame(IdentityScope.getSystemScope(), s.getScope());
        assertNull(s.getPrivateKey());

        try {
            Signer s2 = new SignerStub("sss4", IdentityScope.getSystemScope());
            fail("expected KeyManagementException not thrown");
        } catch (KeyManagementException e)
        {
            // ok
        }

        Signer s2 = new SignerStub(null);
        assertNull(s2.getName());


    }

    /**
     * verify Signer.getPrivateKey() returns null or private key
     */
    public void testGetPrivateKey() throws Exception {
        byte [] privateKeyData = { 1, 2, 3, 4, 5};
        PrivateKeyStub privateKey = new PrivateKeyStub("private", "fff", privateKeyData);
        PublicKeyStub publicKey = new PublicKeyStub("public", "fff", null);
        KeyPair kp = new KeyPair(publicKey, privateKey);

        Signer s = new SignerStub("sss5");

        assertNull(s.getPrivateKey());

        s.setKeyPair(kp);
        assertSame(privateKey, s.getPrivateKey());
    }

    /**
     * java.security.Signer#setKeyPair(java.security.KeyPair)
     */
    public void test_setKeyPairLjava_security_KeyPair() throws Exception {

        // Regression for HARMONY-2408
        // test: NullPointerException if pair is null
        try {
            new SignerStub("name").setKeyPair(null);
            fail("No expected NullPointerException");
        } catch (NullPointerException e) {
        }

        try {
            KeyPair kp = new KeyPair(null, null);
            SignerStub s = new SignerStub("name");
            s.setKeyPair(kp);
        } catch (InvalidParameterException e) {
            // ok
        }


    }

}
