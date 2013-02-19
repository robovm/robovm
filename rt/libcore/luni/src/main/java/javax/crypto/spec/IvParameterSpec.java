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
* @author Alexander Y. Kleymenov
* @version $Revision$
*/

package javax.crypto.spec;

import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;

/**
 * The algorithm parameter specification for an <i>initialization vector</i>.
 */
public class IvParameterSpec implements AlgorithmParameterSpec {

    private final byte[] iv;

    /**
     * Creates a new <code>IvParameterSpec</code> instance with the bytes from
     * the specified buffer <i>iv</i> used as <i>initialization vector</i>.
     *
     * @param iv
     *            the buffer used as initialization vector.
     * @throws NullPointerException
     *             if the specified buffer is null.
     */
    public IvParameterSpec(byte[] iv) {
        if (iv == null) {
            throw new NullPointerException("iv == null");
        }
        this.iv = new byte[iv.length];
        System.arraycopy(iv, 0, this.iv, 0, iv.length);
    }

    /**
     * Creates a new <code>IvParameterSpec</code> instance with <code>byteCount</code>
     * bytes from the specified buffer <code>iv</code> starting at
     * <code>offset</code>.
     *
     * @throws IllegalArgumentException
     *             if the specified buffer is null or <code>offset</code> and
     *             <code>byteCount</code> do not specify a valid chunk in the
     *             specified buffer.
     * @throws ArrayIndexOutOfBoundsException
     *             if <code>offset</code> or <code>byteCount</code> are negative.
     */
    public IvParameterSpec(byte[] iv, int offset, int byteCount) {
        if ((iv == null) || (iv.length - offset < byteCount)) {
            throw new IllegalArgumentException();
        }
        Arrays.checkOffsetAndCount(iv.length, offset, byteCount);
        this.iv = new byte[byteCount];
        System.arraycopy(iv, offset, this.iv, 0, byteCount);
    }

    /**
     * Returns a copy of the <i>initialization vector</i> data.
     *
     * @return a copy of the initialization vector data.
     */
    public byte[] getIV() {
        byte[] res = new byte[iv.length];
        System.arraycopy(iv, 0, res, 0, iv.length);
        return res;
    }
}
