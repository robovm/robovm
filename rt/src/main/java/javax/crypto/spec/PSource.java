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

import libcore.util.EmptyArray;

/**
 * The source of the label <code>L</code> as specified in <a
 * href="http://www.ietf.org/rfc/rfc3447.txt"> PKCS #1</a>.
 */
public class PSource {

    private String pSrcName;

    private PSource() {}

    /**
     * Creates a new <code>PSource</code> instance with the specified source
     * algorithm identifier.
     *
     * @param pSrcName
     *            the source algorithm identifier.
     * @throws NullPointerException
     *             if pSrcName is null.
     */
    protected PSource(String pSrcName) {
        if (pSrcName == null) {
            throw new NullPointerException();
        }
        this.pSrcName = pSrcName;
    }

    /**
     * Returns the source algorithm identifier.
     *
     * @return the source algorithm identifier.
     */
    public String getAlgorithm() {
        return pSrcName;
    }

    /**
     * The explicit specification of the parameter <code>P</code> used in the
     * source algorithm.
     */
    public static final class PSpecified extends PSource {

        private final byte[] p;

        /**
         * The instance of <code>PSpecified</code> with the default value
         * <code>byte[0]</code> for <code>P</code>
         */
        public static final PSpecified DEFAULT = new PSpecified();

        private PSpecified() {
            super("PSpecified");
            p = EmptyArray.BYTE;
        }

        /**
         * Creates a new instance of <code>PSpecified</code> with the specified
         * parameter <code>P</code>.
         *
         * @param p
         *            the parameter <code>P</code>.
         * @throws NullPointerException
         *             if <code>p</code> is null.
         */
        public PSpecified(byte[] p) {
            super("PSpecified");
            if (p == null) {
                throw new NullPointerException();
            }
            //TODO: It is unknown which name should be used!
            //super("");
            this.p = new byte[p.length];
            System.arraycopy(p, 0, this.p, 0, p.length);
        }

        /**
         * Returns a copy of the value of the parameter <code>P</code>.
         *
         * @return a copy of the value of the parameter <code>P</code>
         */
        public byte[] getValue() {
            byte[] result = new byte[p.length];
            System.arraycopy(p, 0, result, 0, p.length);
            return result;
        }
    }
}
