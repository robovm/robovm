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

import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;

/**
 * The algorithm parameter specification for the Diffie-Hellman algorithm.
 */
public class DHParameterSpec implements AlgorithmParameterSpec {

    private final BigInteger p;
    private final BigInteger g;
    private final int l;

    /**
     * Creates a new <code>DHParameterSpec</code> instance with the specified
     * <i>prime modulus</i> and <i>base generator</i>.
     *
     * @param p
     *            the prime modulus.
     * @param g
     *            the base generator.
     */
    public DHParameterSpec(BigInteger p, BigInteger g) {
        this.p = p;
        this.g = g;
        this.l = 0;
    }

    /**
     * Creates a new <code>DHParameterSpec</code> instance with the specified
     * <i>prime modulus</i>, <i>base generator</i> and size (in bits) of the
     * <i>random exponent</i>.
     *
     * @param p
     *            the prime modulus.
     * @param g
     *            the base generator.
     * @param l
     *            the size of the random exponent (in bits).
     */
    public DHParameterSpec(BigInteger p, BigInteger g, int l) {
        this.p = p;
        this.g = g;
        this.l = l;
    }

    /**
     * Returns the <i>prime modulus</i> of this parameter specification.
     *
     * @return the prime modulus.
     */
    public BigInteger getP() {
        return p;
    }

    /**
     * Returns the <i>base generator</i> of this parameter specification.
     *
     * @return the base generator.
     */
    public BigInteger getG() {
        return g;
    }

    /**
     * Returns the size (in bits) of the <i>random exponent</i>.
     *
     * @return the size (in bits) of the random exponent.
     */
    public int getL() {
        return l;
    }
}

