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

package java.security.cert;

import java.util.Collection;
import java.util.Collections;

/**
 * The parameters to initialize a <i>Collection</i> type {@code CertStore} instance.
 * <p>
 * It is used to specify the {@code Collection} where the {@code CertStore} will
 * retrieve the certificates and CRLs from.
 */
public class CollectionCertStoreParameters implements CertStoreParameters {
    // Default empty and immutable collection.
    // Used if <code>CollectionCertStoreParameters</code>instance
    // created by the no arg constructor
    private static final Collection<?> defaultCollection = Collections.EMPTY_SET;
    // A <code>Collection</code> of <code>Certificate</code>s
    // and <code>CRL</code>s
    private final Collection<?> collection;

    /**
     * Creates a new {@code CollectionCertStoreParameters} without a collection.
     * <p>
     * The default collection is an empty and unmodifiable {@code Collection}.
     */
    public CollectionCertStoreParameters() {
        this.collection = defaultCollection;
    }

    /**
     * Creates a new {@code CollectionCertStoreParameters} with the specified
     * collection.
     * <p>
     * The specified collection is not copied and therefore may be modified at
     * any time.
     *
     * @param collection
     *            the collection where the {@code Certificate}s and {@code CRL}s
     *            will be retrieved from.
     * @throws NullPointerException
     *             if {@code collection is null}.
     */
    public CollectionCertStoreParameters(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("collection == null");
        }
        this.collection = collection;
    }

    /**
     * Clones this {@code CollectionCertStoreParameters} instance, but not the
     * underlying collection.
     *
     * @return the cloned instance.
     */
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * Returns the collection where the {@code Certificate}s and {@code CRL}s
     * are retrieved from.
     *
     * @return the collection where the {@code Certificate}s and {@code CRL}s
     *         will be retrieved from.
     */
    public Collection<?> getCollection() {
        return collection;
    }

    /**
     * Returns the string representation of this instance.
     *
     * @return the string representation of this instance.
     */
    public String toString() {
        StringBuilder sb =
            new StringBuilder("CollectionCertStoreParameters: [\ncollection: ");
        sb.append(getCollection().toString());
        sb.append("\n]");
        return sb.toString();
    }
}
