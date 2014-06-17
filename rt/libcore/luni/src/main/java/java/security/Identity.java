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

import java.io.Serializable;
import java.util.Arrays;
import java.util.Vector;
import libcore.util.Objects;

/**
 * {@code Identity} represents an identity like a person or a company.
 *
 * @deprecated Use {@link Principal}, {@link KeyStore} and the {@code java.security.cert} package
 * instead.
 */
@Deprecated
public abstract class Identity implements Principal, Serializable {
    private static final long serialVersionUID = 3609922007826600659L;

    private String name;

    private PublicKey publicKey;

    private String info = "no additional info";

    private IdentityScope scope;

    private Vector<Certificate> certificates;

    /**
     * Constructs a new instance of {@code Identity}.
     */
    protected Identity() {
    }

    /**
     * Creates a new instance of {@code Identity} with the specified name.
     *
     * @param name
     *            the name of this {@code Identity}.
     */
    public Identity(String name) {
        this.name = name;
    }

    /**
     * Creates a new instance of {@code Identity} with the specified name and
     * the scope of this {@code Identity}.
     *
     * @param name
     *            the name of this {@code Identity}.
     * @param scope
     *            the {@code IdentityScope} of this {@code Identity}.
     * @throws KeyManagementException
     *             if an {@code Identity} with the same name is already present
     *             in the specified scope.
     */
    public Identity(String name, IdentityScope scope)
            throws KeyManagementException {
        this(name);
        if (scope != null) {
            scope.addIdentity(this);
            this.scope = scope;
        }
    }

    /**
     * Adds a {@code Certificate} to this {@code Identity}.
     *
     * @param certificate
     *            the {@code Certificate} to be added to this {@code Identity}.
     * @throws KeyManagementException
     *             if the certificate is not valid.
     */
    public void addCertificate(Certificate certificate) throws KeyManagementException {
        PublicKey certPK = certificate.getPublicKey();
        if (publicKey != null) {
            if (!checkKeysEqual(publicKey, certPK)) {
                throw new KeyManagementException("Cert's public key does not match Identity's public key");
            }
        } else {
            publicKey = certPK;
        }
        if (certificates == null) {
            certificates = new Vector<Certificate>();
        }
        certificates.add(certificate);
    }




    private static boolean checkKeysEqual(PublicKey pk1, PublicKey pk2) {
        // first, they should have the same format
        // second, their encoded form must be the same

        // assert(pk1 != null);
        // assert(pk2 != null);

        String format1 = pk1.getFormat();
        String format2;
        if ((pk2 == null)
                || (((format2 = pk2.getFormat()) != null) ^ (format1 != null))
                || ((format1 != null) && !format1.equals(format2))) {
            return false;
        }

        return Arrays.equals(pk1.getEncoded(), pk2.getEncoded());
    }




    /**
     * Removes the specified {@code Certificate} from this {@code Identity}.
     *
     * @param certificate
     *            the {@code Certificate} to be removed.
     * @throws KeyManagementException
     *             if the certificate is not found.
     */
    public void removeCertificate(Certificate certificate) throws KeyManagementException {
        if (certificates != null) {
            if (!certificates.contains(certificate)) {
                throw new KeyManagementException("Certificate not found");
            }
            certificates.removeElement(certificate);
        }
    }




    /**
     * Returns the certificates for this {@code Identity}. External
     * modifications of the returned array has no impact on this {@code
     * Identity}.
     *
     * @return the {@code Certificates} for this {@code Identity}
     */
    public Certificate[] certificates() {
        if (certificates == null) {
            return new Certificate[0];
        }
        Certificate[] ret = new Certificate[certificates.size()];
        certificates.copyInto(ret);
        return ret;
    }




    /**
     * Compares the specified {@code Identity} with this {@code Identity} for
     * equality and returns {@code true} if the specified object is equal,
     * {@code false} otherwise.
     * <p>
     * To be equal, two {@code Identity} objects need to have the same name and
     * the same public keys.
     *
     * @param identity
     *            the identity to check for equality.
     * @return {@code true} if the {@code Identity} objects are equal, {@code
     *         false} otherwise.
     */
    protected boolean identityEquals(Identity identity) {
        if (!name.equals(identity.name)) {
            return false;
        }

        if (publicKey == null) {
            return (identity.publicKey == null);
        }

        return checkKeysEqual(publicKey, identity.publicKey);
    }




    /**
     * Returns a string containing a concise, human-readable description of the
     * this {@code Identity}.
     *
     * @param detailed
     *            whether or not this method should return detailed information.
     * @return a printable representation for this {@code Permission}.
     */
    public String toString(boolean detailed) {
        String s = toString();
        if (detailed) {
            s += " " + info;
        }
        return s;
    }




    /**
     * Returns the {@code IdentityScope} of this {@code Identity}.
     *
     * @return the {@code IdentityScope} of this {@code Identity}.
     */
    public final IdentityScope getScope() {
        return scope;
    }




    /**
     * Sets the specified {@code PublicKey} to this {@code Identity}.
     *
     * @param key
     *            the {@code PublicKey} to be set.
     * @throws KeyManagementException
     *             if another {@code Identity} in the same scope as this {@code
     *             Identity} already has the same {@code PublicKey}.
     */
    public void setPublicKey(PublicKey key) throws KeyManagementException {
        // this check does not always work
        if ((scope != null) && (key != null)) {
            Identity i = scope.getIdentity(key);
            //System.out.println("###DEBUG## Identity: "+i);
            if ((i != null) && (i != this)) {
                throw new KeyManagementException("key already used in scope");
            }
        }
        this.publicKey = key;
        certificates = null;
    }




    /**
     * Returns the {@code PublicKey} associated with this {@code Identity}.
     *
     * @return the {@code PublicKey} associated with this {@code Identity}.
     */
    public PublicKey getPublicKey() {
        return publicKey;
    }




    /**
     * Sets an information string for this {@code Identity}.
     * @param info
     *            the information to be set.
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * Returns the information string of this {@code Identity}.
     *
     * @return the information string of this {@code Identity}.
     */
    public String getInfo() {
        return info;
    }

    /**
     * Compares the specified object with this {@code Identity} for equality and
     * returns {@code true} if the specified object is equal, {@code false}
     * otherwise. {@code Identity} objects are considered equal, if they have
     * the same name and are in the same scope.
     *
     * @param obj
     *            object to be compared for equality with this {@code
     *            Identity}.
     * @return {@code true} if the specified object is equal to this {@code
     *         Identity}, otherwise {@code false}.
     */
    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Identity)) {
            return false;
        }
        Identity i = (Identity) obj;
        if (Objects.equal(name, i.name) && (Objects.equal(scope, i.scope))) {
            return true;
        }
        return identityEquals(i);
    }

    /**
     * Returns the name of this {@code Identity}.
     *
     * @return the name of this {@code Identity}.
     */
    public final String getName() {
        return name;
    }

    /**
     * Returns the hash code value for this {@code Identity}. Returns the same
     * hash code for {@code Identity}s that are equal to each other as required
     * by the general contract of {@link Object#hashCode}.
     *
     * @return the hash code value for this {@code Identity}.
     * @see Object#equals(Object)
     * @see Identity#equals(Object)
     */
    @Override
    public int hashCode() {
        int hash = 0;
        if (name != null) {
            hash += name.hashCode();
        }
        if (scope != null) {
            hash += scope.hashCode();
        }
        return hash;
    }

    /**
     * Returns a string containing a concise, human-readable description of the
     * this {@code Identity} including its name and its scope.
     *
     * @return a printable representation for this {@code Identity}.
     */
    @Override
    public String toString() {
        String s = (this.name == null ? "" : this.name);
        if (scope != null) {
            s += " [" + scope.getName() + "]";
        }
        return s;
    }
}
