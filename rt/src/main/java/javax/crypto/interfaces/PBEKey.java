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

import javax.crypto.SecretKey;

/**
 * The interface to a <i>password-based-encryption</i>  key.
 */
public interface PBEKey extends SecretKey {

    /**
     * The serial version identifier.
     */
    public static final long serialVersionUID = -1430015993304333921L;

    /**
     * Returns the iteration count, 0 if not specified.
     *
     * @return the iteration count, 0 if not specified.
     */
    public int getIterationCount();

    /**
     * Returns a copy of the salt data or null if not specified.
     *
     * @return a copy of the salt data or null if not specified.
     */
    public byte[] getSalt();

    /**
     * Returns a copy to the password.
     *
     * @return a copy to the password.
     */
    public char[] getPassword();

}