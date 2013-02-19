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
import java.io.PrintStream;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Locale;
import javax.net.ssl.SSLHandshakeException;

/**
 * This class provides Input/Output data functionality
 * for handshake layer. It provides read and write operations
 * and accumulates all sent/received handshake's data.
 * This class can be presented as a combination of 2 data pipes.
 * The first data pipe is a pipe of income data: append method
 * places the data at the beginning of the pipe, and read methods
 * consume the data from the pipe. The second pipe is an outcoming
 * data pipe: write operations plases the data into the pipe,
 * and getData methods consume the data.
 * It is important to note that work with pipe cound not be
 * started if there is unconsumed data in another pipe. It is
 * reasoned by the following: handshake protocol performs read
 * and write operations consecuently. I.e. it first reads all
 * income data and only than produces the responce and places it
 * into the stream.
 * The read operations of the stream presented by the methods
 * of SSLInputStream which in its turn is an extension of InputStream.
 * So this stream can be used as an InputStream parameter for
 * certificate generation.
 * Also input stream functionality supports marks. The marks
 * help to reset the position of the stream in case of incompleate
 * handshake records. Note that in case of exhausting
 * of income data the EndOfBufferException is thown which implies
 * the following:
 *  1. the stream contains scrappy handshake record,
 *  2. the read position should be reseted to marked,
 *  3. and more income data is expected.
 * The throwing of the exception (instead of returning of -1 value
 * or incompleate filling of destination buffer)
 * helps to speed up the process of scrappy data recognition and
 * processing.
 * For more information about TLS handshake process see
 * TLS v 1 specification at http://www.ietf.org/rfc/rfc2246.txt.
 */
public class HandshakeIODataStream
        extends SSLInputStream implements org.apache.harmony.xnet.provider.jsse.Appendable, DataStream {

    // Objects are used to compute digests of data passed
    // during the handshake phase
    private static final MessageDigest md5;
    private static final MessageDigest sha;

    static {
        try {
            md5 = MessageDigest.getInstance("MD5");
            sha = MessageDigest.getInstance("SHA-1");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(
                    "Could not initialize the Digest Algorithms.");
        }
    }

    public HandshakeIODataStream() {}

    // buffer is used to keep the handshaking data;
    private int buff_size = 1024;
    private int inc_buff_size = 1024;
    private byte[] buffer = new byte[buff_size];


    // ---------------- Input related functionality -----------------

    // position of the next byte to read
    private int read_pos;
    private int marked_pos;
    // position of the last byte to read + 1
    private int read_pos_end;

    @Override
    public int available() {
        return read_pos_end - read_pos;
    }

    @Override
    public boolean markSupported() {
        return true;
    }

    @Override
    public void mark(int limit) {
        marked_pos = read_pos;
    }

    public void mark() {
        marked_pos = read_pos;
    }

    @Override
    public void reset() {
        read_pos = marked_pos;
    }

    /**
     * Removes the data from the marked position to
     * the current read position. The method is usefull when it is needed
     * to delete one message from the internal buffer.
     */
    protected void removeFromMarkedPosition() {
        System.arraycopy(buffer, read_pos,
                buffer, marked_pos, read_pos_end - read_pos);
        read_pos_end -= (read_pos - marked_pos);
        read_pos = marked_pos;
    }

    /**
     * read an opaque value;
     * @param   byte:   byte
     * @return
     */
    @Override
    public int read() throws IOException {
        if (read_pos == read_pos_end) {
            //return -1;
            throw new EndOfBufferException();
        }
        return buffer[read_pos++] & 0xFF;
    }

    /**
     * reads vector of opaque values
     * @param   new:    long
     * @return
     */
    @Override
    public byte[] read(int length) throws IOException {
        if (length > available()) {
            throw new EndOfBufferException();
        }
        byte[] res = new byte[length];
        System.arraycopy(buffer, read_pos, res, 0, length);
        read_pos = read_pos + length;
        return res;
    }

    @Override
    public int read(byte[] dst, int offset, int length) throws IOException {
        if (length > available()) {
            throw new EndOfBufferException();
        }
        System.arraycopy(buffer, read_pos, dst, offset, length);
        read_pos = read_pos + length;
        return length;
    }

    // ------------------- Extending of the input data ---------------------

    /**
     * Appends the income data to be read by handshake protocol.
     * The attempts to overflow the buffer by means of this methods
     * seem to be futile because of:
     * 1. The SSL protocol specifies the maximum size of the record
     * and record protocol does not pass huge messages.
     * (see TLS v1 specification http://www.ietf.org/rfc/rfc2246.txt ,
     * p 6.2)
     * 2. After each call of this method, handshake protocol should
     * start (and starts) the operations on received data and recognize
     * the fake data if such was provided (to check the size of certificate
     * for example).
     */
    public void append(byte[] src) {
        append(src, 0, src.length);
    }

    private void append(byte[] src, int from, int length) {
        if (read_pos == read_pos_end) {
            // start reading state after writing
            if (write_pos_beg != write_pos) {
                // error: outboud handshake data was not sent,
                // but inbound handshake data has been received.
                throw new AlertException(
                    AlertProtocol.UNEXPECTED_MESSAGE,
                    new SSLHandshakeException(
                        "Handshake message has been received before "
                        + "the last oubound message had been sent."));
            }
            if (read_pos < write_pos) {
                read_pos = write_pos;
                read_pos_end = read_pos;
            }
        }
        if (read_pos_end + length > buff_size) {
            enlargeBuffer(read_pos_end+length-buff_size);
        }
        System.arraycopy(src, from, buffer, read_pos_end, length);
        read_pos_end += length;
    }

    private void enlargeBuffer(int size) {
        buff_size = (size < inc_buff_size)
            ? buff_size + inc_buff_size
            : buff_size + size;
        byte[] new_buff = new byte[buff_size];
        System.arraycopy(buffer, 0, new_buff, 0, buffer.length);
        buffer = new_buff;
    }

    protected void clearBuffer() {
        read_pos = 0;
        marked_pos = 0;
        read_pos_end = 0;
        write_pos = 0;
        write_pos_beg = 0;
        Arrays.fill(buffer, (byte) 0);
    }

    // ------------------- Output related functionality --------------------

    // position in the buffer available for write
    private int write_pos;
    // position in the buffer where the last write session has begun
    private int write_pos_beg;

    // checks if the data can be written in the buffer
    private void check(int length) {
        // (write_pos == write_pos_beg) iff:
        // 1. there were not write operations yet
        // 2. all written data was demanded by getData methods
        if (write_pos == write_pos_beg) {
            // just started to write after the reading
            if (read_pos != read_pos_end) {
                // error: attempt to write outbound data into the stream before
                // all the inbound handshake data had been read
                throw new AlertException(
                        AlertProtocol.INTERNAL_ERROR,
                        new SSLHandshakeException("Data was not fully read: "
                        + read_pos + " " + read_pos_end));
            }
            // set up the write positions
            if (write_pos_beg < read_pos_end) {
                write_pos_beg = read_pos_end;
                write_pos = write_pos_beg;
            }
        }
        // if there is not enought free space in the buffer - enlarge it:
        if (write_pos + length >= buff_size) {
            enlargeBuffer(length);
        }
    }

    /**
     * Writes an opaque value
     * @param   byte:   byte
     */
    public void write(byte b) {
        check(1);
        buffer[write_pos++] = b;
    }

    /**
     * Writes Uint8 value
     * @param long: the value to be written (last byte)
     */
    public void writeUint8(long n) {
        check(1);
        buffer[write_pos++] = (byte) (n & 0x00ff);
    }

    /**
     * Writes Uint16 value
     * @param long: the value to be written (last 2 bytes)
     */
    public void writeUint16(long n) {
        check(2);
        buffer[write_pos++] = (byte) ((n & 0x00ff00) >> 8);
        buffer[write_pos++] = (byte) (n & 0x00ff);
    }

    /**
     * Writes Uint24 value
     * @param long: the value to be written (last 3 bytes)
     */
    public void writeUint24(long n) {
        check(3);
        buffer[write_pos++] = (byte) ((n & 0x00ff0000) >> 16);
        buffer[write_pos++] = (byte) ((n & 0x00ff00) >> 8);
        buffer[write_pos++] = (byte) (n & 0x00ff);
    }

    /**
     * Writes Uint32 value
     * @param long: the value to be written (last 4 bytes)
     */
    public void writeUint32(long n) {
        check(4);
        buffer[write_pos++] = (byte) ((n & 0x00ff000000) >> 24);
        buffer[write_pos++] = (byte) ((n & 0x00ff0000) >> 16);
        buffer[write_pos++] = (byte) ((n & 0x00ff00) >> 8);
        buffer[write_pos++] = (byte) (n & 0x00ff);
    }

    /**
     * Writes Uint64 value
     * @param long: the value to be written
     */
    public void writeUint64(long n) {
        check(8);
        buffer[write_pos++] = (byte) ((n & 0x00ff00000000000000L) >> 56);
        buffer[write_pos++] = (byte) ((n & 0x00ff000000000000L) >> 48);
        buffer[write_pos++] = (byte) ((n & 0x00ff0000000000L) >> 40);
        buffer[write_pos++] = (byte) ((n & 0x00ff00000000L) >> 32);
        buffer[write_pos++] = (byte) ((n & 0x00ff000000) >> 24);
        buffer[write_pos++] = (byte) ((n & 0x00ff0000) >> 16);
        buffer[write_pos++] = (byte) ((n & 0x00ff00) >> 8);
        buffer[write_pos++] = (byte) (n & 0x00ff);
    }

    /**
     * writes vector of opaque values
     * @param  vector the vector to be written
     */
    public void write(byte[] vector) {
        check(vector.length);
        System.arraycopy(vector, 0, buffer, write_pos, vector.length);
        write_pos += vector.length;
    }

    // ------------------- Retrieve the written bytes ----------------------

    public boolean hasData() {
        return (write_pos > write_pos_beg);
    }

    /**
     * returns the chunk of stored data with the length no more than specified.
     * @param   length: int
     * @return
     */
    public byte[] getData(int length) {
        byte[] res;
        if (write_pos - write_pos_beg < length) {
            res = new byte[write_pos - write_pos_beg];
            System.arraycopy(buffer, write_pos_beg,
                    res, 0, write_pos-write_pos_beg);
            write_pos_beg = write_pos;
        } else {
            res = new byte[length];
            System.arraycopy(buffer, write_pos_beg, res, 0, length);
            write_pos_beg += length;
        }
        return res;
    }

    // ---------------------- Message Digest Functionality ----------------

    /**
     * Returns the MD5 digest of the data passed throught the stream
     * @return MD5 digest
     */
    protected byte[] getDigestMD5() {
        synchronized (md5) {
            int len = (read_pos_end > write_pos)
                ? read_pos_end
                : write_pos;
            md5.update(buffer, 0, len);
            return md5.digest();
        }
    }

    /**
     * Returns the SHA-1 digest of the data passed throught the stream
     * @return SHA-1 digest
     */
    protected byte[] getDigestSHA() {
        synchronized (sha) {
            int len = (read_pos_end > write_pos)
                ? read_pos_end
                : write_pos;
            sha.update(buffer, 0, len);
            return sha.digest();
        }
    }

    /**
     * Returns the MD5 digest of the data passed throught the stream
     * except last message
     * @return MD5 digest
     */
    protected byte[] getDigestMD5withoutLast() {
        synchronized (md5) {
            md5.update(buffer, 0, marked_pos);
            return md5.digest();
        }
    }

    /**
     * Returns the SHA-1 digest of the data passed throught the stream
     * except last message
     * @return SHA-1 digest
     */
    protected byte[] getDigestSHAwithoutLast() {
        synchronized (sha) {
            sha.update(buffer, 0, marked_pos);
            return sha.digest();
        }
    }

    /**
     * Returns all the data passed throught the stream
     * @return all the data passed throught the stream at the moment
     */
    protected byte[] getMessages() {
        int len = (read_pos_end > write_pos) ? read_pos_end : write_pos;
        byte[] res = new byte[len];
        System.arraycopy(buffer, 0, res, 0, len);
        return res;
    }
}
