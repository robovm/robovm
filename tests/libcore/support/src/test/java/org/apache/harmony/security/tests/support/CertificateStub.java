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

package org.apache.harmony.security.tests.support;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Certificate;
import java.security.KeyException;
import java.security.Principal;
import java.security.PublicKey;

/**
 * Stub for interface Certificate, necessary for testing
 */
@SuppressWarnings("deprecation")
public class CertificateStub implements Certificate {

    String format;
    Principal guarantor;
    Principal principal;
    PublicKey key;

    public CertificateStub(String format, Principal guarantor, Principal principal, PublicKey key){
        this.format = format;
        this.guarantor = guarantor;
        this.principal = principal;
        this.key = key;
    }

    /**
     * Stub - does nothing
     * @see java.security.Certificate#decode(java.io.InputStream)
     */
    public void decode(InputStream stream) throws KeyException,
            IOException {


    }

    /**
     * Stub - does nothing
     * @see java.security.Certificate#encode(java.io.OutputStream)
     */
    public void encode(OutputStream stream) throws KeyException,
            IOException {


    }

    /**
     * @see java.security.Certificate#getFormat()
     */
    public String getFormat() {

        return format;
    }

    /**
     * @see java.security.Certificate#getGuarantor()
     */
    public Principal getGuarantor() {

        return guarantor;
    }

    /**
     * @see java.security.Certificate#getPrincipal()
     */
    public Principal getPrincipal() {
        return principal;
    }

    /**
     * @see java.security.Certificate#getPublicKey()
     */
    public PublicKey getPublicKey() {
        return key;
    }

    /**
     * Stub - returns <code>null</code>
     * @see java.security.Certificate#toString(boolean)
     */
    public String toString(boolean detailed) {
        return null;
    }

}
