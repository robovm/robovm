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

package javax.crypto.interfaces;

import java.math.BigInteger;
import java.security.PublicKey;

/**
 * The interface for a public key in the Diffie-Hellman key exchange protocol.
 */
public interface DHPublicKey extends DHKey, PublicKey {

    /**
     * The serial version identifier.
     */
    public static final long serialVersionUID = -6628103563352519193L;

    /**
     * Returns this key's public value Y.
     * @return this key's public value Y.
     */
    public BigInteger getY();
}