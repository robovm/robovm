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

/**
 * The interface specification to determine whether a {@code
 * Certificate} meets some criteria.
 * <p>
 * The implementations of this interface are typically used to define the
 * criteria for selecting {@code Certificate}s from a {@code CertStore}.
 *
 * @see CertStore
 * @see Certificate
 */
public interface CertSelector extends Cloneable {

    /**
     * Clones this {@code CertSelector} instance.
     *
     * @return the cloned instance.
     */
    public Object clone();

    /**
     * Checks whether the defined criteria of this instance match the specified
     * certificate.
     *
     * @param cert
     *            the certificate to be evaluated.
     * @return {@code true} if the certificate matches the criteria, {@code
     *         false} otherwise.
     */
    public boolean match(Certificate cert);
}
