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

package org.apache.harmony.crypto.tests.javax.crypto.interfaces;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.interfaces.DHKey;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.spec.DHParameterSpec;

import junit.framework.TestCase;

/**
 * Tests for <code>DHPrivateKey</code> class field
 *
 */
public class DHPrivateKeyTest extends TestCase {

    /**
     * Test for <code>serialVersionUID</code> field
     */
    public void testField() {
        checkDHPrivateKey key = new checkDHPrivateKey();
        assertEquals("Incorrect serialVersionUID",
                key.getSerVerUID(), //DHPrivateKey.serialVersionUID
                2211791113380396553L);
    }

    public void test_getParams() throws Exception {
        KeyPairGenerator kg = KeyPairGenerator.getInstance("DH");
        kg.initialize(192);
        KeyPair kp1 = kg.genKeyPair();
        KeyPair kp2 = kg.genKeyPair();
        DHPrivateKey pk1 = (DHPrivateKey) kp1.getPrivate();
        DHPrivateKey pk2 = (DHPrivateKey) kp2.getPrivate();

        assertTrue(pk1.getX().getClass().getCanonicalName().equals("java.math.BigInteger"));
        assertTrue(pk1.getParams().getClass().getCanonicalName().equals("javax.crypto.spec.DHParameterSpec"));
        assertFalse(pk1.equals(pk2));
        assertTrue(pk1.getX().equals(pk1.getX()));
    }

    public class checkDHPrivateKey implements DHPrivateKey {
        public String getAlgorithm() {
            return "SecretKey";
        }
        public String getFormat() {
            return "Format";
        }
        public byte[] getEncoded() {
            return new byte[0];
        }
        public long getSerVerUID() {
            return serialVersionUID;
        }
        public BigInteger getX() {
            return null;
        }
        public DHParameterSpec getParams() {
            return null;
        }
    }
}
