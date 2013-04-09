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

package org.apache.harmony.security.tests.java.security;

import java.security.KeyStore;

import javax.crypto.SecretKey;

import junit.framework.TestCase;

/**
 * Tests for <code>KeyStore.SecretKeyEntry</code> class constructor and methods
 *
 */
public class KSSecretKeyEntryTest extends TestCase {

    /**
     * Test for <code>SecretKeyEntry(SecretKey secretKey)</code> constructor
     * Assertion: throws NullPointerException when secretKey is null
     */
    public void testSecretKeyEntry() {
        SecretKey sk = null;
        try {
            new KeyStore.SecretKeyEntry(sk);
            fail("NullPointerException must be thrown when secretKey is null");
        } catch(NullPointerException e) {
            //expected
        }

        sk = new tmpSecretKey();
        try {
            KeyStore.SecretKeyEntry ske = new KeyStore.SecretKeyEntry(sk);
            assertNotNull(ske);
            assertTrue(ske instanceof KeyStore.SecretKeyEntry);
        } catch(Exception e) {
            fail("Unexpected exception was thrown when secretKey is not null");
        }
    }

    /**
     * Test for <code>getSecretKey()</code> method
     * Assertion: returns SecretKey from the given entry
     */
    public void testGetSecretKey() {
        SecretKey sk = new tmpSecretKey();
        KeyStore.SecretKeyEntry ske = new KeyStore.SecretKeyEntry(sk);
        assertEquals("Incorrect SecretKey", sk, ske.getSecretKey());
    }

    /**
     * Test for <code>toString()</code> method
     * Assertion: returns non null string
     */
    public void testToString() {
        SecretKey sk = new tmpSecretKey();
        KeyStore.SecretKeyEntry ske = new KeyStore.SecretKeyEntry(sk);
        assertNotNull("toString() returns null string", ske.toString());
    }
}

class tmpSecretKey implements SecretKey {
    public String getAlgorithm() {
        return "My algorithm";
    }
    public String getFormat() {
        return "My Format";
    }
    public byte[] getEncoded() {
        return new byte[1];
    }
}
