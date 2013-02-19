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

package javax.crypto;

import java.security.GeneralSecurityException;

/**
 * This is the base class for {@code ExemptionMechanismException}.
 */
public class ExemptionMechanismException extends GeneralSecurityException {

    /**
     * @serial
     */
    private static final long serialVersionUID = 1572699429277957109L;

    /**
     * Creates a new {@code ExemptionMechanismException} with the specified
     * message.
     *
     * @param msg
     *            the exception message.
     */
    public ExemptionMechanismException(String msg) {
        super(msg);
    }

    /**
     * Creates a new {@code ExemptionMechanismException} with no message.
     */
    public ExemptionMechanismException() {
    }
}