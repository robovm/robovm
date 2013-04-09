/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

/**
 * @author Alexander Y. Kleymenov
 * @version $Revision$
 */

package org.apache.harmony.crypto.tests.javax.crypto;

import junit.framework.TestCase;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchProviderException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NullCipher;
import javax.crypto.SealedObject;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 */
public class SealedObjectTest extends TestCase {
    class Mock_SealedObject extends SealedObject {
        public Mock_SealedObject(Serializable object, Cipher c)
                throws IOException, IllegalBlockSizeException {
            super(object, c);
        }

        public byte[] get_encodedParams() {
            return super.encodedParams;
        }

    }

    /**
     * readObject(ObjectInputStream s) method testing. Tests if the
     * serialization/deserialization works correctly: object is serialized,
     * deserialized, the content od deserialized object equals to the content of
     * initial object.
     */
    public void testReadObject() throws Exception {
        String secret = "secret string";
        SealedObject so = new SealedObject(secret, new NullCipher());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(so);

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(
                bos.toByteArray()));

        SealedObject so_des = (SealedObject) ois.readObject();
        assertEquals("The secret content of deserialized object "
                + "should be equal to the secret content of initial object",
                secret, so_des.getObject(new NullCipher()));
        assertEquals("The value returned by getAlgorithm() method of "
                + "deserialized object should be equal to the value returned "
                + "by getAlgorithm() method of initial object", so
                .getAlgorithm(), so_des.getAlgorithm());
    }

    /**
     * SealedObject(Serializable object, Cipher c) method testing. Tests if the
     * NullPointerException is thrown in the case of null cipher.
     */
    public void testSealedObject1() throws Exception {
        String secret = "secret string";
        try {
            new SealedObject(secret, null);
            fail("NullPointerException should be thrown in the case "
                    + "of null cipher.");
        } catch (NullPointerException e) {
        }

        KeyGenerator kg = KeyGenerator.getInstance("DES");
        Key key = kg.generateKey();

        IvParameterSpec ips = new IvParameterSpec(new byte[] {
                1, 2, 3, 4, 5, 6, 7, 8});

        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, ips);

        SealedObject so = new SealedObject(secret, cipher);

        cipher = Cipher.getInstance("DES/CBC/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, key, ips);

        try {
            new SealedObject(secret, cipher);
            fail("IllegalBlockSizeException expected");
        } catch (IllegalBlockSizeException e) {
            //expected
        }
    }

    /**
     * SealedObject(SealedObject so) method testing. Tests if the
     * NullPointerException is thrown in the case of null SealedObject.
     */
    public void testSealedObject2() throws Exception {
        try {
            new SealedObject(null) {};
            fail("NullPointerException should be thrown in the case "
                    + "of null SealedObject.");
        } catch (NullPointerException e) {
        }

        String secret = "secret string";
        Cipher cipher = new NullCipher();
        SealedObject so1 = new SealedObject(secret, cipher);
        SealedObject so2 = new SealedObject(so1) {};

        assertEquals("The secret content of the object should equals "
                + "to the secret content of initial object.", secret, so2
                .getObject(cipher));
        assertEquals("The algorithm which was used to seal the object "
                + "should be the same as the algorithm used to seal the "
                + "initial object", so1.getAlgorithm(), so2.getAlgorithm());
    }

    /**
     * getAlgorithm() method testing. Tests if the returned value equals to the
     * corresponding value of Cipher object.
     */
    public void testGetAlgorithm() throws Exception {
        String secret = "secret string";
        String algorithm = "DES";
        KeyGenerator kg = KeyGenerator.getInstance(algorithm);
        Key key = kg.generateKey();

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        SealedObject so = new SealedObject(secret, cipher);

        assertEquals("The algorithm name should be the same as used "
                + "in cipher.", algorithm, so.getAlgorithm());
    }

    /**
     * getObject(Key key) method testing. Tests if the object sealed with
     * encryption algorithm and specified parameters can be retrieved by
     * specifying the cryptographic key.
     */
    public void testGetObject1() throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance("DES");
        Key key = kg.generateKey();

        IvParameterSpec ips = new IvParameterSpec(new byte[] {
                1, 2, 3, 4, 5, 6, 7, 8});

        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, ips);

        String secret = "secret string";
        Mock_SealedObject so = new Mock_SealedObject(secret, cipher);

        assertEquals("The returned object does not equals to the "
                + "original object.", secret, so.getObject(key));

        assertTrue("The encodedParams field of SealedObject object "
                + "should contain the encoded algorithm parameters.", Arrays
                .equals(so.get_encodedParams(), cipher.getParameters()
                        .getEncoded()));
        try {
            so.getObject((Key)null);
            fail("InvalidKeyException expected");
        } catch (InvalidKeyException e) {
            //expected
        } catch (NullPointerException e) {
            //also ok
        }
    }

    /**
     * getObject(Cipher c) method testing. Tests if the proper exception is
     * thrown in the case of incorrect input parameters and if the object sealed
     * with encryption algorithm and specified parameters can be retrieved by
     * specifying the initialized Cipher object.
     */
    public void testGetObject2() throws Exception {
        try {
            new SealedObject("secret string", new NullCipher())
                    .getObject((Cipher) null);
            fail("NullPointerException should be thrown in the case of "
                    + "null cipher.");
        } catch (NullPointerException e) {
        }

        KeyGenerator kg = KeyGenerator.getInstance("DES");
        Key key = kg.generateKey();

        IvParameterSpec ips = new IvParameterSpec(new byte[] {
                1, 2, 3, 4, 5, 6, 7, 8});

        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, ips);

        String secret = "secret string";
        SealedObject so = new SealedObject(secret, cipher);

        cipher.init(Cipher.DECRYPT_MODE, key, ips);
        assertEquals("The returned object does not equals to the "
                + "original object.", secret, so.getObject(cipher));

        try {
            so.getObject((Cipher)null);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            //expected
        }
    }

    /**
     * getObject(Key key, String provider) method testing. Tests if the proper
     * exception is thrown in the case of incorrect input parameters and if the
     * object sealed with encryption algorithm can be retrieved by specifying
     * the cryptographic key and provider name.
     */
    public void testGetObject3() throws Exception {
        try {
            new SealedObject("secret string", new NullCipher()).getObject(
                    new SecretKeySpec(new byte[] {0, 0, 0}, "algorithm"), null);
            fail("IllegalArgumentException should be thrown in the case of "
                    + "null provider.");
        } catch (IllegalArgumentException e) {
        }

        try {
            new SealedObject("secret string", new NullCipher()).getObject(
                    new SecretKeySpec(new byte[] {0, 0, 0}, "algorithm"), "");
            fail("IllegalArgumentException should be thrown in the case of "
                    + "empty provider.");
        } catch (IllegalArgumentException e) {
        }

        KeyGenerator kg = KeyGenerator.getInstance("DES");
        Key key = kg.generateKey();

        Cipher cipher = Cipher.getInstance("DES");
        String provider = cipher.getProvider().getName();
        cipher.init(Cipher.ENCRYPT_MODE, key);

        String secret = "secret string";
        SealedObject so = new SealedObject(secret, cipher);

        cipher.init(Cipher.DECRYPT_MODE, key);
        assertEquals("The returned object does not equals to the "
                + "original object.", secret, so.getObject(key, provider));

        kg = KeyGenerator.getInstance("DESede");
        key = kg.generateKey();

        try {
            so.getObject(key, provider);
            fail("InvalidKeyException expected");
        } catch (InvalidKeyException e) {
            //expected
        }

        try {
            so.getObject(key, "Wrong provider name");
            fail("NoSuchProviderException expected");
        } catch (NoSuchProviderException e) {
            //expected
        }
    }

}
