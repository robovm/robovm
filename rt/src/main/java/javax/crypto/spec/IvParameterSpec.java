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
*/

package javax.crypto.spec;

import java.security.spec.AlgorithmParameterSpec;

import org.apache.harmony.crypto.internal.nls.Messages;

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
            throw new NullPointerException(Messages.getString("crypto.38")); //$NON-NLS-1$
        }
        this.iv = new byte[iv.length];
        System.arraycopy(iv, 0, this.iv, 0, iv.length);
    }

    /**
     * Creates a new <code>IvParameterSpec</code> instance with <code>len</code>
     * bytes from the specified buffer <code>iv</code> starting at
     * <code>offset</code>.
     *
     * @param iv
     *            the buffer used as initialization vector.
     * @param offset
     *            the offset to start in the buffer.
     * @param len
     *            the length of the data.
     * @throws IllegalArgumentException
     *             if the specified buffer is null or <code>offset</code> and
     *             <code>len</code> do not specify a valid chunk in the
     *             specified buffer.
     * @throws ArrayIndexOutOfBoundsException
     *             if <code>offset</code> or <code>len</code> are negative.
     */
    public IvParameterSpec(byte[] iv, int offset, int len) {
        if ((iv == null) || (iv.length - offset < len)) {
            throw new IllegalArgumentException(
                    Messages.getString("crypto.39")); //$NON-NLS-1$
        }
        if (offset < 0 || len < 0) {
            throw new ArrayIndexOutOfBoundsException(Messages.getString("crypto.3A")); //$NON-NLS-1$
        }
        this.iv = new byte[len];
        System.arraycopy(iv, offset, this.iv, 0, len);
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

