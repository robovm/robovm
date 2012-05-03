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

package javax.crypto.spec;

import java.security.spec.AlgorithmParameterSpec;

/**
 * The algorithm parameter specification for generating Diffie-Hellman
 * parameters used in Diffie-Hellman key agreement.
 */
public class DHGenParameterSpec implements AlgorithmParameterSpec {

    private final int primeSize;
    private final int exponentSize;

    /**
     * Creates a new <code>DHGenParameterSpec</code> instance with the specified
     * parameters.
     *
     * @param primeSize
     *            the size of the <i>prime modulus</i> in bits.
     * @param exponentSize
     *            the size of the <i>random exponent</i> in bits.
     */
    public DHGenParameterSpec(int primeSize, int exponentSize) {
        this.primeSize = primeSize;
        this.exponentSize = exponentSize;
    }

    /**
     * Returns the size of the <i>prime modulus</i> in bits.
     *
     * @return the size of the prime modulus in bits.
     */
    public int getPrimeSize() {
        return primeSize;
    }

    /**
     * Returns the size of the <i>random exponent</i> in bits.
     *
     * @return the size of the random exponent in bits.
     */
    public int getExponentSize() {
        return exponentSize;
    }
}

