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

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * {@code DigestInputStream} is a {@code FilterInputStream} which maintains an
 * associated message digest.
 */
public class DigestInputStream extends FilterInputStream {

    /**
     * The message digest for this stream.
     */
    protected MessageDigest digest;

    // Indicates whether digest functionality is on or off
    private boolean isOn = true;

    /**
     * Constructs a new instance of this {@code DigestInputStream}, using the
     * given {@code stream} and the {@code digest}.
     *
     * <p><strong>Warning:</strong> passing a null source creates an invalid
     * {@code DigestInputStream}. All operations on such a stream will fail.
     *
     * @param stream
     *            the input stream.
     * @param digest
     *            the message digest.
     */
    public DigestInputStream(InputStream stream, MessageDigest digest) {
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
     * Reads the next byte and returns it as an {@code int}. Updates the digest
     * for the byte if this function is {@link #on(boolean)}.
     * <p>
     * This operation is blocking.
     *
     * @return the byte which was read or -1 at end of stream.
     * @throws IOException
     *             if reading the source stream causes an {@code IOException}.
     */
    @Override
    public int read() throws IOException {
        // read the next byte
        int byteRead = in.read();
        // update digest only if
        // - digest functionality is on
        // - eos has not been reached
        if (isOn && (byteRead != -1)) {
            digest.update((byte)byteRead);
        }
        // return byte read
        return byteRead;
    }

    /**
     * Reads up to {@code byteCount} bytes into {@code buffer}, starting at
     * {@code byteOffset}. Updates the digest if this function is
     * {@link #on(boolean)}.
     *
     * <p>This operation is blocking.
     *
     * <p>Returns the number of bytes actually read or -1 if the end of the
     * filtered stream has been reached while reading.
     *
     * @throws IOException
     *             if reading the source stream causes an {@code IOException}
     */
    @Override
    public int read(byte[] buffer, int byteOffset, int byteCount) throws IOException {
        int bytesRead = in.read(buffer, byteOffset, byteCount);
        // update digest only if
        // - digest functionality is on
        // - eos has not been reached
        if (isOn && (bytesRead != -1)) {
            digest.update(buffer, byteOffset, bytesRead);
        }
        // return number of bytes read
        return bytesRead;
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
     * {@code DigestInputStream} including the digest.
     *
     * @return a printable representation for this {@code DigestInputStream}.
     */
    @Override
    public String toString() {
        return super.toString() + ", " + digest.toString() +
            (isOn ? ", is on" : ", is off");
    }
}
