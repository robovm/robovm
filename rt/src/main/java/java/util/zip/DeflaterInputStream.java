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

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * An inputstream filter to compress data by the compressing format of Deflate.
 */
public class DeflaterInputStream extends FilterInputStream {

    /**
     * The Deflater used by this DeflaterInputStream
     */
    protected final Deflater def;

    /**
     * The internal input data buffer used by this DeflaterInputStream.
     */
    protected final byte[] buf;

    private static final int defaultsize = 1024;

    private static final int EOF = -1;

    private boolean closed = false;

    private boolean available = true;

    /**
     * Constructs a DeflaterInputStream with the default Deflater and internal
     * input buffer length.
     * 
     * @param in
     *            the InputStream that the DeflaterInputStream reads data from.
     */
    public DeflaterInputStream(InputStream in) {
        this(in, new Deflater(), defaultsize);
    }

    /**
     * Constructs a DeflaterInputStream with a specified Deflater and the
     * default internal input buffer length.
     * 
     * @param in
     *            the InputStream that the DeflaterInputStream reads data from.
     * @param defl
     *            an specifed Deflater used to compress data.
     */
    public DeflaterInputStream(InputStream in, Deflater defl) {
        this(in, defl, defaultsize);
    }

    /**
     * Constructs a DeflaterInputStream with a specified Deflater and input
     * buffer length.
     * 
     * @param in
     *            the InputStream that the DeflaterInputStream reads data from.
     * @param defl
     *            a specifed Deflater used to compress data.
     * @param bufLen
     *            the buffer length of the internal input data buffer.
     */
    public DeflaterInputStream(InputStream in, Deflater defl, int bufLen) {
        super(in);
        if (null == in || null == defl) {
            throw new NullPointerException();
        }
        if (bufLen <= 0) {
            throw new IllegalArgumentException();
        }
        def = defl;
        buf = new byte[bufLen];
    }

    /**
     * Closes the underlying input stream and discards any remaining uncompressed
     * data.
     */
    @Override
    public void close() throws IOException {
        closed = true;
        def.end();
        in.close();
    }

    /**
     * Reads a byte from the compressed input stream.
     * 
     * @return the byte or -1 if the end of the compressed input stream has been
     *         reached.
     */
    @Override
    public int read() throws IOException {
        byte[] result = new byte[1];

        // EOF
        if (read(result, 0, 1) == EOF) {
            return EOF;
        }

        int r = result[0];
        if (r < 0) {
            r += 256;
        }
        return r;
    }

    /**
     * Reads compressed data into a byte buffer.
     * 
     * @param b
     *            the byte buffer that compressed data will be read into.
     * @param off
     *            the offset in the byte buffer where compressed data will start
     *            to be read into.
     * @param len
     *            the length of the compressed data that is expected to read.
     * @return the number of bytes read or -1 if the end of the compressed input
     *         stream has been reached.
     */
    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        checkClosed();
        if (null == b) {
            throw new NullPointerException();
        }
        if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        }

        if (!available) {
            return EOF;
        }

        int count = 0;

        while (count < len && !def.finished()) {
            if (def.needsInput()) {
                // read data from input stream
                int readed = in.read(buf);
                if (EOF == readed) {
                    // gets to the end of the input stream
                    def.finish();
                } else {
                    def.setInput(buf, 0, readed);
                }
            }
            // gets compressed data from def
            int readed = def.deflate(buf, 0, Math.min(buf.length, len - count));
            if (EOF == readed) {
                break;
            }
            System.arraycopy(buf, 0, b, off + count, readed);
            count += readed;
        }
        if (0 == count) {
            count = EOF;
            available = false;
        }
        return count;
    }

    /**
     * Skips n bytes from the DeflateInputStream.
     * 
     * @param n
     *            the bytes to skipped. If n is greater than Integer.MAX_VALUE,
     *            the DeflateInputStream tries to skip Integer.MAX_VALUE bytes.
     * @return the number of bytes actually skipped.
     */
    @Override
    public long skip(long n) throws IOException {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        checkClosed();

        int length = (int) (n > Integer.MAX_VALUE ? Integer.MAX_VALUE : n);
        byte[] buffer = new byte[defaultsize > length ? length : defaultsize];
        int skipped = 0;
        int count = 0;
        while (skipped < length
                && (count = read(buffer, 0,
                        (length - skipped > buffer.length) ? buffer.length
                                : length - skipped)) >= 0) {
            skipped += count;
        }
        return skipped;
    }

    /**
     * Returns 1 to denote there is data to read while 0 if no data is
     * available. The returned value does not denote how many bytes that can be
     * read.
     * 
     * @return 1 to denote there is data to read while 0 if no data is
     *         available.
     */
    @Override
    public int available() throws IOException {
        checkClosed();
        if (available) {
            return 1;
        }
        return 0;
    }

    /**
     * Denotes whether this inputstream support mark()/reset() operation. Always
     * returns false since the two operaions are not supported in
     * DeflaterInputStream.
     * 
     * @return always returns false.
     */
    @Override
    public boolean markSupported() {
        return false;
    }

    /**
     * Not supported in DeflaterInputStream and just does nothing.
     * 
     * @param limit
     *            maximum number of bytes that can be read before the mark
     *            becomes invalid.
     */
    @Override
    public void mark(int limit) {
        // do nothing
    }

    /**
     * Not supported in DeflaterInputStream and just throws IOException.
     */
    @Override
    public void reset() throws IOException {
        throw new IOException();
    }

    private void checkClosed() throws IOException {
        if (closed) {
            throw new IOException();
        }
    }
}
