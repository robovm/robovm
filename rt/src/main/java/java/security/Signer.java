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
 * @deprecated Replaced by behavior in {@link java.security.cert
 *             java.security.cert} package and {@link java.security.Principal
 *             Principal}
 */
@Deprecated
public abstract class Signer extends Identity {

    private static final long serialVersionUID = -1763464102261361480L;

    private PrivateKey privateKey;

    /**
     * Constructs a new instance of {@code Signer}.
     */
    protected Signer() {
        super();
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
    public Signer(String name, IdentityScope scope)
            throws KeyManagementException {
        super(name, scope);
    }

    /**
     * Returns the private key of this {@code Signer}. If a {@code
     * SecurityManager} is installed, code calling this method needs the {@code
     * SecurityPermission} {@code "getSignerPrivateKey"} to be granted, otherwise
     * a {@code SecurityException} will be thrown.
     *
     * @return the private key of this {@code Signer}.
     * @throws SecurityException
     *             if a {@code SecurityManager} is installed and the caller does
     *             not have permission to invoke this method.
     */
    public PrivateKey getPrivateKey() {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkSecurityAccess("getSignerPrivateKey"); //$NON-NLS-1$
        }

        return privateKey;
    }

    /**
     * Associates the specified key pair with this {@code Signer}. If a {@code
     * SecurityManager} is installed, code calling this method needs the {@code
     * SecurityPermission} {@code getSignerPrivateKey} to be granted, otherwise
     * a {@code SecurityException} will be thrown.
     *
     * @param pair
     *            the key pair to associate with this {@code Signer}.
     * @throws InvalidParameterException
     *             if the key pair is invalid.
     * @throws KeyException
     *             if any other key related problem occurs.
     * @throws SecurityException
     *             if a {@code SecurityManager} is installed and the caller does
     *             not have permission to invoke this method.
     */
    public final void setKeyPair(KeyPair pair)
            throws InvalidParameterException, KeyException {
        
        if (pair == null) {
            throw new NullPointerException();
        }

        if ((pair.getPrivate() == null) || (pair.getPublic() == null)) {
            throw new InvalidParameterException();
        }
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkSecurityAccess("setSignerKeyPair"); //$NON-NLS-1$
        }
        final PublicKey pk = pair.getPublic();
        try {
            AccessController.doPrivileged(new PrivilegedExceptionAction<Void>() {
                public Void run() throws KeyManagementException {
                    setPublicKey(pk);
                    return null;
                }
            });
        } catch (PrivilegedActionException e) {
            throw new KeyException(e.getException());
        }
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
        String s = "[Signer]" + getName(); //$NON-NLS-1$
        if (getScope() != null) {
            s = s + '[' + getScope().toString() + ']';
        }
        return s;
    }
}
