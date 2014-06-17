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

/**
 * {@link Signer} represents an identity (individual or corporation) that owns a
 * private key and the corresponding public key.
 *
 * @deprecated Use the {@link java.security.cert java.security.cert} package
 * and {@link java.security.Principal} instead.
 */
@Deprecated
public abstract class Signer extends Identity {

    private static final long serialVersionUID = -1763464102261361480L;

    private PrivateKey privateKey;

    /**
     * Constructs a new instance of {@code Signer}.
     */
    protected Signer() {
    }

    /**
     * Constructs a new instance of {@code Signer} with the given name.
     *
     * @param name
     *            the name of the signer.
     */
    public Signer(String name) {
        super(name);
    }

    /**
     * Constructs a new instance of {@code Signer} with the given name in the
     * given scope.
     *
     * @param name
     *            the name of the signer.
     * @param scope
     *            the scope of the signer.
     * @throws KeyManagementException
     *             if a signer with the specified name already exists in the
     *             provided scope.
     */
    public Signer(String name, IdentityScope scope) throws KeyManagementException {
        super(name, scope);
    }

    /**
     * Returns the private key of this {@code Signer}.
     */
    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    /**
     * Associates the specified key pair with this {@code Signer}.
     *
     * @param pair
     *            the key pair to associate with this {@code Signer}.
     * @throws InvalidParameterException
     *             if the key pair is invalid.
     * @throws KeyException
     *             if any other key related problem occurs.
     */
    public final void setKeyPair(KeyPair pair) throws InvalidParameterException, KeyException {
        if (pair == null) {
            throw new NullPointerException("pair == null");
        }

        if (pair.getPrivate() == null || pair.getPublic() == null) {
            throw new InvalidParameterException();
        }
        setPublicKey(pair.getPublic());
        this.privateKey = pair.getPrivate();
    }

    /**
     * Returns a string containing a concise, human-readable description of this
     * {@code Signer} including its name and its scope if present.
     *
     * @return a printable representation for this {@code Signer}.
     */
    @Override
    public String toString() {
        String s = "[Signer]" + getName();
        if (getScope() != null) {
            s = s + '[' + getScope().toString() + ']';
        }
        return s;
    }
}
