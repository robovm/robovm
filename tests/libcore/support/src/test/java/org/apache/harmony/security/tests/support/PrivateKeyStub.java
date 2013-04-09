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

import java.security.PrivateKey;

/**
 * Stub for interface PrivateKey tests
 */

public class PrivateKeyStub implements PrivateKey {

    private static final long serialVersionUID = 111111111L;

    String algorithm = null;
    String format = null;
    byte [] encoded = null;


    /**
     * Constructor
     *
     * @param algorithm
     * @param format
     * @param encoded
     */
    public PrivateKeyStub(String algorithm, String format, byte[] encoded) {
        this.algorithm = algorithm;
        this.format = format;
        this.encoded = encoded;
    }

    /**
     * Returns algorithm
     * @see java.security.Key#getAlgorithm()
     */
    public String getAlgorithm() {
        return algorithm;
    }

    /**
     * Returns format
     * @see java.security.Key#getFormat()
     */
    public String getFormat() {
        return format;
    }

    /**
     * Returns encoded form
     * @see java.security.Key#getEncoded()
     */
    public byte[] getEncoded() {
        return encoded;
    }

}
