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

package java.security;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * A {@code SignedObject} instance acts as a container for another object. The
 * {@code SignedObject} contains the target in serialized form along with a
 * digital signature of the serialized data.
 */
public final class SignedObject implements Serializable {

    private static final long serialVersionUID = 720502720485447167L;

    private byte[] content;

    private byte[] signature;

    private String thealgorithm;

    private void readObject(ObjectInputStream s) throws IOException,
            ClassNotFoundException {

        s.defaultReadObject();
        byte[] tmp = new byte[content.length];
        System.arraycopy(content, 0, tmp, 0, content.length);
        content = tmp;
        tmp = new byte[signature.length];
        System.arraycopy(signature, 0, tmp, 0, signature.length);
        signature = tmp;
    }

    /**
     * Constructs a new instance of {@code SignedObject} with the target object,
     * the private key and the engine to compute the signature. The given
     * {@code object} is signed with the specified key and engine.
     *
     * @param object
     *            the object to bes signed.
     * @param signingKey
     *            the private key, used to sign the {@code object}.
     * @param signingEngine
     *            the engine that performs the signature generation.
     * @throws IOException
     *             if a serialization error occurs.
     * @throws InvalidKeyException
     *             if the private key is not valid.
     * @throws SignatureException
     *             if signature generation failed.
     */
    public SignedObject(Serializable object, PrivateKey signingKey,
            Signature signingEngine) throws IOException, InvalidKeyException,
            SignatureException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        try {
            // Serialize
            oos.writeObject(object);
            oos.flush();
        } finally {
            oos.close();
        }
        content = baos.toByteArray();
        signingEngine.initSign(signingKey);
        thealgorithm = signingEngine.getAlgorithm();
        signingEngine.update(content);
        signature = signingEngine.sign();
    }

    /**
     * Returns the encapsulated object. Each time this method is invoked, the
     * encapsulated object is deserialized before it is returned.
     *
     * @return the encapsulated object.
     * @throws IOException
     *             if deserialization failed.
     * @throws ClassNotFoundException
     *             if the class of the encapsulated object can not be found.
     */
    public Object getObject() throws IOException, ClassNotFoundException {
        // deserialize our object
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(
                content));
        try {
            return ois.readObject();
        } finally {
            ois.close();
        }
    }

    /**
     * Returns the signature data of the encapsulated serialized object.
     *
     * @return the signature data of the encapsulated serialized object.
     */
    public byte[] getSignature() {
        byte[] sig = new byte[signature.length];
        System.arraycopy(signature, 0, sig, 0, signature.length);
        return sig;
    }

    /**
     * Returns the name of the algorithm of this {@code SignedObject}.
     *
     * @return the name of the algorithm of this {@code SignedObject}.
     */
    public String getAlgorithm() {
        return thealgorithm;
    }

    /**
     * Indicates whether the contained signature for the encapsulated object is
     * valid.
     *
     * @param verificationKey
     *            the public key to verify the signature.
     * @param verificationEngine
     *            the signature engine.
     * @return {@code true} if the contained signature for the encapsulated
     *         object is valid, {@code false} otherwise.
     * @throws InvalidKeyException
     *             if the public key is invalid.
     * @throws SignatureException
     *             if signature verification failed.
     */
    public boolean verify(PublicKey verificationKey,
            Signature verificationEngine) throws InvalidKeyException,
            SignatureException {

        verificationEngine.initVerify(verificationKey);
        verificationEngine.update(content);
        return verificationEngine.verify(signature);
    }

}
