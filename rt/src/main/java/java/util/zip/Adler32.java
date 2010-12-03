/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java.util.zip;

/**
 * The Adler-32 class is used to compute the {@code Adler32} checksum from a set
 * of data. Compared to the CRC-32 algorithm it trades reliabilty for speed.
 * Refer to RFC 1950 for the specification.
 *
 * @see CRC32
 */
public class Adler32 implements java.util.zip.Checksum {

    private long adler = 1;

    /**
     * Returns the {@code Adler32} checksum for all input received.
     *
     * @return The checksum for this instance.
     */
    public long getValue() {
        return adler;
    }

    /**
     * Reset this instance to its initial checksum.
     */
    public void reset() {
        adler = 1;
    }

    /**
     * Update this {@code Adler32} checksum with the single byte provided as
     * argument.
     *
     * @param i
     *            the byte to update checksum with.
     */
    public void update(int i) {
        adler = updateByteImpl(i, adler);
    }

    /**
     * Update this {@code Adler32} checksum using the contents of {@code buf}.
     *
     * @param buf
     *            bytes to update checksum with.
     */
    public void update(byte[] buf) {
        update(buf, 0, buf.length);
    }

    /**
     * Update this {@code Adler32} checksum with the contents of {@code buf},
     * starting from the offset provided and reading n bytes of data.
     *
     * @param buf
     *            buffer to obtain data from.
     * @param off
     *            offset in {@code buf} to start reading from.
     * @param nbytes
     *            number of bytes from {@code buf} to use.
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code offset > buf.length} or {@code nbytes} is negative
     *             or {@code offset + nbytes > buf.length}.
     */
    public void update(byte[] buf, int off, int nbytes) {
        // avoid int overflow, check null buf
        if (off <= buf.length && nbytes >= 0 && off >= 0
                && buf.length - off >= nbytes) {
            adler = updateImpl(buf, off, nbytes, adler);
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private native long updateImpl(byte[] buf, int off, int nbytes, long adler1);

    private native long updateByteImpl(int val, long adler1);
}
