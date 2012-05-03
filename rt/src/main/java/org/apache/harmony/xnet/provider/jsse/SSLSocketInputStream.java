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

package org.apache.harmony.xnet.provider.jsse;

import java.io.IOException;
import java.io.InputStream;
import javax.net.ssl.SSLException;

/**
 * This class provides input data stream functionality
 * for SSLSocket. It accumulates the application data
 * received by SSL protocol.
 */
public final class SSLSocketInputStream extends InputStream {

    // The size of the internal data buffer.
    // It should not be less than maximum data chunk enclosed
    // in one ssl packet.
    private static final int BUFFER_SIZE = SSLRecordProtocol.MAX_DATA_LENGTH;

    // Internal buffer accumulating the received application data
    private byte[] buffer = new byte[BUFFER_SIZE];

    // position of the next byte to read from the buffer
    private int pos;

    // position of the last byte to read + 1
    private int end;

    // the ssl socket owning the stream
    private final SSLSocketImpl owner;

    // the flag indicating that the end of the (owner's) input stream
    // has been reached
    private boolean end_reached = false;

    /**
     * Creates the application data input stream for specified socket.
     * @param   owner the socket which will provide this input stream
     * to client applications.
     */
    protected SSLSocketInputStream(SSLSocketImpl owner) {
        this.owner = owner;
    }

    // The helper delivering the application data from the record layer
    protected Adapter dataPoint = new Adapter();

    /**
     * Tells to the stream that the end of the income data has
     * been reached.
     */
    protected void setEnd() {
        end_reached = true;
    }

    // ------------------ InputStream implementation -------------------

    /**
     * Returns the number of bytes available for reading without blocking.
     * @return the number of available bytes.
     * @throws  IOException
     */
    @Override
    public int available() throws IOException {
        return end - pos;
    }

    /**
     * Closes the stream
     * @throws  IOException
     */
    @Override
    public void close() throws IOException {
        buffer = null;
    }

    /**
     * Reads one byte. If there is no data in the underlying buffer,
     * this operation can block until the data will be
     * available.
     * @return read value.
     * @throws  IOException
     */
    @Override
    public int read() throws IOException {
        if (buffer == null) {
            throw new IOException("Stream was closed.");
        }
        while (pos == end) {
            if (end_reached) {
                return -1;
            }
            // If there is no data in the buffer
            // - will block until the data will be provided by
            // record layer
            owner.needAppData();
        }
        return buffer[pos++] & 0xFF;
    }

    @Override public int read(byte[] b, int off, int len) throws IOException {
        int read_b;
        int i = 0;
        do {
            if ((read_b = read()) == -1) {
                return (i == 0) ? -1 : i;
            }
            b[off+i] = (byte) read_b;
            i++;
        } while ((available() != 0) && (i<len));
        return i;
    }

    // The helper class delivering the application data from the record layer
    // to this input stream.
    // It 'adapts' the InputStream interface to Appendable, which is used for
    // transmission of income data from the record protocol to its clients.
    private class Adapter implements org.apache.harmony.xnet.provider.jsse.Appendable {
        /**
         * Appends the data to the stream.
         * This method could be implemented in the outer class
         * itself, but it could be insecure.
         */
        public void append(byte[] src) {
            int length = src.length;
            if (BUFFER_SIZE - (end - pos) < length) {
                // If the size of the buffer is greater than or equals to
                // SSLRecordProtocol.MAX_DATA_LENGTH this situation will
                // happen iff:
                // 1. the length of received data fragment is greater
                // than allowed by the spec
                // 2. it is rehandshaking stage and we have got several
                // extra app data messages.
                // In any case it is better to throw alert exception.
                throw new AlertException(AlertProtocol.INTERNAL_ERROR,
                        new SSLException("Could not accept income app data."));
            }
            if (end + length > BUFFER_SIZE) {
                // move the content of the buffer to the beginning
                System.arraycopy(buffer, pos, buffer, 0, end-pos);
                end -= pos;
                pos = 0;
            }
            System.arraycopy(src, 0, buffer, end, length);
            end = end + length;
        }
    }
}
