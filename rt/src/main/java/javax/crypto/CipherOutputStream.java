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

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import libcore.io.Streams;

/**
 * This class wraps an output stream and a cipher so that {@code write} methods
 * send the data through the cipher before writing them to the underlying output
 * stream.
 * <p>
 * The cipher must be initialized for the requested operation before being used
 * by a {@code CipherOutputStream}. For example, if a cipher initialized for
 * encryption is used with a {@code CipherOutputStream}, the {@code
 * CipherOutputStream} tries to encrypt the data writing it out.
 */
public class CipherOutputStream extends FilterOutputStream {

    private final Cipher cipher;

    /**
     * Creates a new {@code CipherOutputStream} instance for an {@code
     * OutputStream} and a {@code Cipher}.
     *
     * @param os
     *            the output stream to write data to.
     * @param c
     *            the cipher to process the data with.
     */
    public CipherOutputStream(OutputStream os, Cipher c) {
        super(os);
        cipher = c;
    }

    /**
     * Creates a new {@code CipherOutputStream} instance for an {@code
     * OutputStream} without a cipher.
     * <p>
     * A {@code NullCipher} is created to process the data.
     *
     * @param os
     *            the output stream to write the data to.
     */
    protected CipherOutputStream(OutputStream os) {
        this(os, new NullCipher());
    }

    /**
     * Writes the single byte to this cipher output stream.
     *
     * @param b
     *            the byte to write.
     * @throws IOException
     *             if an error occurs.
     */
    @Override public void write(int b) throws IOException {
        Streams.writeSingleByte(this, b);
    }

    /**
     * Writes the {@code len} bytes from buffer {@code b} starting at offset
     * {@code off} to this cipher output stream.
     *
     * @param b
     *            the buffer.
     * @param off
     *            the offset to start at.
     * @param len
     *            the number of bytes.
     * @throws IOException
     *             if an error occurs.
     */
    @Override public void write(byte[] b, int off, int len) throws IOException {
        if (len == 0) {
            return;
        }
        byte[] result = cipher.update(b, off, len);
        if (result != null) {
            out.write(result);
        }
    }

    /**
     * Flushes this cipher output stream.
     *
     * @throws IOException
     *             if an error occurs
     */
    @Override
    public void flush() throws IOException {
        out.flush();
    }

    /**
     * Close this cipher output stream.
     * <p>
     * On the underlying cipher {@code doFinal} will be invoked, and any
     * buffered bytes from the cipher are also written out, and the cipher is
     * reset to its initial state. The underlying output stream is also closed.
     *
     * @throws IOException
     *             if an error occurs.
     */
    @Override
    public void close() throws IOException {
        byte[] result;
        try {
            if (cipher != null) {
                result = cipher.doFinal();
                if (result != null) {
                    out.write(result);
                }
            }
            if (out != null) {
                out.flush();
            }
        } catch (BadPaddingException e) {
            throw new IOException(e.getMessage());
        } catch (IllegalBlockSizeException e) {
            throw new IOException(e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
