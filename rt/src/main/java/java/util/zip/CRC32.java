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
 * The CRC32 class is used to compute a CRC32 checksum from data provided as
 * input value.
 */
public class CRC32 implements java.util.zip.Checksum {

    private long crc = 0L;

    long tbytes = 0L;

    /**
     * Returns the CRC32 checksum for all input received.
     *
     * @return The checksum for this instance.
     */
    public long getValue() {
        return crc;
    }

    /**
     * Resets the CRC32 checksum to it initial state.
     */
    public void reset() {
        tbytes = crc = 0;

    }

    /**
     * Updates this checksum with the byte value provided as integer.
     *
     * @param val
     *            represents the byte to update the checksum.
     */
    public void update(int val) {
        crc = updateByteImpl((byte) val, crc);
    }

    /**
     * Updates this checksum with the bytes contained in buffer {@code buf}.
     *
     * @param buf
     *            the buffer holding the data to update the checksum with.
     */
    public void update(byte[] buf) {
        update(buf, 0, buf.length);
    }

    /**
     * Updates this checksum with n bytes of data obtained from buffer {@code
     * buf}, starting at offset {@code off}.
     *
     * @param buf
     *            the buffer to update the checksum.
     * @param off
     *            the offset in {@code buf} to obtain data from.
     * @param nbytes
     *            the number of bytes to read from {@code buf}.
     */
    public void update(byte[] buf, int off, int nbytes) {
        // avoid int overflow, check null buf
        if (off <= buf.length && nbytes >= 0 && off >= 0
                && buf.length - off >= nbytes) {
            tbytes += nbytes;
            crc = updateImpl(buf, off, nbytes, crc);
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private native long updateImpl(byte[] buf, int off, int nbytes, long crc1);

    private native long updateByteImpl(byte val, long crc1);
}
