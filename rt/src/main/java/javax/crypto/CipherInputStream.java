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

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import libcore.io.Streams;

/**
 * This class wraps an {@code InputStream} and a cipher so that {@code read()}
 * methods return data that are read from the underlying {@code InputStream} and
 * processed by the cipher.
 * <p>
 * The cipher must be initialized for the requested operation before being used
 * by a {@code CipherInputStream}. For example, if a cipher initialized for
 * decryption is used with a {@code CipherInputStream}, the {@code
 * CipherInputStream} tries to read the data an decrypt them before returning.
 */
public class CipherInputStream extends FilterInputStream {

    private static final int I_BUFFER_SIZE = 20;

    private final Cipher cipher;
    private final byte[] inputBuffer = new byte[I_BUFFER_SIZE];
    private int index; // index of the bytes to return from outputBuffer
    private byte[] outputBuffer;
    private boolean finished;

    /**
     * Creates a new {@code CipherInputStream} instance for an {@code
     * InputStream} and a cipher.
     *
     * <p><strong>Warning:</strong> passing a null source creates an invalid
     * {@code CipherInputStream}. All read operations on such a stream will
     * fail.
     *
     * @param is
     *            the input stream to read data from.
     * @param c
     *            the cipher to process the data with.
     */
    public CipherInputStream(InputStream is, Cipher c) {
        super(is);
        this.cipher = c;
    }

    /**
     * Creates a new {@code CipherInputStream} instance for an {@code
     * InputStream} without a cipher.
     * <p>
     * A {@code NullCipher} is created and used to process the data.
     *
     * @param is
     *            the input stream to read data from.
     */
    protected CipherInputStream(InputStream is) {
        this(is, new NullCipher());
    }

    /**
     * Reads the next byte from this cipher input stream.
     *
     * @return the next byte, or {@code -1} if the end of the stream is reached.
     * @throws IOException
     *             if an error occurs.
     */
    @Override
    public int read() throws IOException {
        if (finished) {
            return ((outputBuffer == null) || (index == outputBuffer.length))
                            ? -1
                            : outputBuffer[index++] & 0xFF;
        }
        if ((outputBuffer != null) && (index < outputBuffer.length)) {
            return outputBuffer[index++] & 0xFF;
        }
        index = 0;
        outputBuffer = null;
        int byteCount;
        while (outputBuffer == null) {
            if ((byteCount = in.read(inputBuffer)) == -1) {
                try {
                    outputBuffer = cipher.doFinal();
                } catch (Exception e) {
                    throw new IOException(e.getMessage());
                }
                finished = true;
                break;
            }
            outputBuffer = cipher.update(inputBuffer, 0, byteCount);
        }
        return read();
    }

    /**
     * Reads the next {@code len} bytes from this input stream into buffer
     * {@code buf} starting at offset {@code off}.
     * <p>
     * if {@code b} is {@code null}, the next {@code len} bytes are read and
     * discarded.
     *
     * @return the number of bytes filled into buffer {@code b}, or {@code -1}
     *         of the of the stream is reached.
     * @throws IOException
     *             if an error occurs.
     * @throws NullPointerException
     *             if the underlying input stream is {@code null}.
     */
    @Override
    public int read(byte[] buf, int off, int len) throws IOException {
        if (in == null) {
            throw new NullPointerException("Underlying input stream is null");
        }

        int i;
        for (i = 0; i < len; ++i) {
            int b = read();
            if (b == -1) {
                return (i == 0) ? -1 : i;
            }
            if (buf != null) {
                buf[off+i] = (byte) b;
            }
        }
        return i;
    }

    @Override
    public long skip(long byteCount) throws IOException {
        return Streams.skipByReading(this, byteCount);
    }

    @Override
    public int available() throws IOException {
        return 0;
    }

    /**
     * Closes this {@code CipherInputStream}, also closes the underlying input
     * stream and call {@code doFinal} on the cipher object.
     *
     * @throws IOException
     *             if an error occurs.
     */
    @Override
    public void close() throws IOException {
        in.close();
        try {
            cipher.doFinal();
        } catch (GeneralSecurityException ignore) {
            //do like RI does
        }

    }

    /**
     * Returns whether this input stream supports {@code mark} and
     * {@code reset}, which it does not.
     *
     * @return false, since this input stream does not support {@code mark} and
     *         {@code reset}.
     */
    @Override
    public boolean markSupported() {
        return false;
    }
}
