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

package java.security;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * {@code DigestOutputStream} is a {@code FilterOutputStream} which maintains an
 * associated message digest.
 */
public class DigestOutputStream extends FilterOutputStream {

    /**
     * The message digest for this stream.
     */
    protected MessageDigest digest;

    // Indicates whether digest functionality is on or off
    private boolean isOn = true;

    /**
     * Constructs a new instance of this {@code DigestOutputStream}, using the
     * given {@code stream} and the {@code digest}.
     *
     * @param stream
     *            the output stream.
     * @param digest
     *            the message digest.
     */
    public DigestOutputStream(OutputStream stream, MessageDigest digest) {
        super(stream);
        this.digest = digest;
    }

    /**
     * Returns the message digest for this stream.
     *
     * @return the message digest for this stream.
     */
    public MessageDigest getMessageDigest() {
        return digest;
    }

    /**
     * Sets the message digest which this stream will use.
     *
     * @param digest
     *            the message digest which this stream will use.
     */
    public void setMessageDigest(MessageDigest digest) {
        this.digest = digest;
    }

    /**
     * Writes the specified {@code int} to the stream. Updates the digest if
     * this function is {@link #on(boolean)}.
     *
     * @param b
     *            the byte to be written.
     * @throws IOException
     *             if writing to the stream causes a {@code IOException}
     */
    @Override
    public void write(int b) throws IOException {
        // update digest only if digest functionality is on
        if (isOn) {
            digest.update((byte)b);
        }
        // write the byte
        out.write(b);
    }

    /**
     * Writes {@code len} bytes into the stream, starting from the specified
     * offset. Updates the digest if this function is {@link #on(boolean)}.
     *
     * @param b
     *            the buffer to write to.
     * @param off
     *            the index of the first byte in {@code b} to write.
     * @param len
     *            the number of bytes in {@code b} to write.
     * @throws IOException
     *             if writing to the stream causes an {@code IOException}.
     */
    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        // update digest only if digest functionality is on
        if (isOn) {
            digest.update(b, off, len);
        }
        // write len bytes
        out.write(b, off, len);
    }

    /**
     * Enables or disables the digest function (default is on).
     *
     * @param on
     *            {@code true} if the digest should be computed, {@code false}
     *            otherwise.
     * @see MessageDigest
     */
    public void on(boolean on) {
        isOn = on;
    }

    /**
     * Returns a string containing a concise, human-readable description of this
     * {@code DigestOutputStream} including the digest.
     *
     * @return a printable representation for this {@code DigestOutputStream}.
     */
    @Override
    public String toString() {
        return super.toString() + ", " + digest.toString() +
            (isOn ? ", is on" : ", is off");
    }
}
