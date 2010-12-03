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

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * An ouput stream filter to decompress data compressed in the format of
 * Deflater.
 */
public class InflaterOutputStream extends FilterOutputStream {

    /**
     * The inflater used by InflaterOutputStream to decompress data.
     */
    protected final Inflater inf;

    /**
     * The internal output buffer.
     */
    protected final byte[] buf;

    private boolean closed = false;

    private static final int DEFAULT_BUFFER_SIZE = 1024;

    /**
     * Constructs an InflaterOutputStream with the default Inflater and internal
     * output buffer size.
     * 
     * @param out
     *            the output stream that InflaterOutputStream will write
     *            compressed data into.
     */
    public InflaterOutputStream(OutputStream out) {
        this(out, new Inflater());
    }

    /**
     * Constructs an InflaterOutputStream with the specifed Inflater and the
     * default internal output buffer size.
     * 
     * @param out
     *            the output stream that InflaterOutputStream will write
     *            compressed data into.
     * @param infl
     *            the Inflater used by the InflaterOutputStream to decompress
     *            data.
     */
    public InflaterOutputStream(OutputStream out, Inflater infl) {
        this(out, infl, DEFAULT_BUFFER_SIZE);
    }

    /**
     * Constructs an InflaterOutputStream with the specifed Inflater and
     * internal output buffer size.
     * 
     * @param out
     *            the output stream that InflaterOutputStream will write
     *            compressed data into.
     * @param infl
     *            the Inflater used by the InflaterOutputStream to decompress
     *            data.
     * @param bufLen the size of the internal output buffer.
     */
    public InflaterOutputStream(OutputStream out, Inflater infl, int bufLen) {
        super(out);
        if (null == out || null == infl) {
            throw new NullPointerException();
        }
        if (bufLen <= 0) {
            throw new IllegalArgumentException();
        }
        inf = infl;
        buf = new byte[bufLen];
    }

    /**
     * Writes remaining data into the output stream and closes the underling
     * output stream data.
     */
    @Override
    public void close() throws IOException {
        if (!closed) {
            finish();
            inf.end();
            out.close();
            closed = true;
        }
    }

    /**
     * Flushes the output stream.
     */
    @Override
    public void flush() throws IOException {
        finish();
        out.flush();
    }

    /**
     * Finishes writing current uncompressed data into the InflaterOutputStream
     * but not closes it.
     * 
     * @throws IOException
     *             if the stream has been closed or some I/O error occurs.
     */
    public void finish() throws IOException {
        checkClosed();
        write();
    }

    /**
     * Writes a bit to the uncompressing output stream.
     * 
     * @param b
     *            the bit to write to the uncompressing output stream.
     * @throws IOException
     *             if the stream has been closed or some I/O error occurs.
     * @throws ZipException
     *             if a zip exception occurs.
     */
    @Override
    public void write(int b) throws IOException, ZipException {
        write(new byte[] { (byte) b }, 0, 1);
    }

    /**
     * Writes a bit to the uncompressing output stream.
     * 
     * @param b
     *            the byte array to write to the uncompressing output stream.
     * @param off
     *            the offset in the byte array where the data is first to be
     *            uncompressed.
     * @param len
     *            the number of the bytes to be uncompressed.
     * @throws IOException
     *             if the stream has been closed or some I/O error occurs.
     * @throws ZipException
     *             if a zip exception occurs.
     * @throws NullPointerException
     *             if the byte array is null.
     * @throws IndexOutOfBoundsException
     *             if the off less than zero or len less than zero or off + len
     *             is greater than the byte array length.
     */
    @Override
    public void write(byte[] b, int off, int len) throws IOException,
            ZipException {
        checkClosed();
        if (null == b) {
            throw new NullPointerException();
        }
        if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        }

        inf.setInput(b, off, len);
        write();
    }

    private void write() throws IOException, ZipException {
        int inflated = 0;
        try {
            while ((inflated = inf.inflate(buf)) > 0) {
                out.write(buf, 0, inflated);
            }
        } catch (DataFormatException e) {
            throw new ZipException();
        }
    }

    private void checkClosed() throws IOException {
        if (closed) {
            throw new IOException();
        }
    }
}
