/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpcore/trunk/module-main/src/main/java/org/apache/http/impl/io/ContentLengthInputStream.java $
 * $Revision: 652091 $
 * $Date: 2008-04-29 13:41:07 -0700 (Tue, 29 Apr 2008) $
 *
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.http.impl.io;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.io.SessionInputBuffer;

/**
 * Stream that cuts off after a specified number of bytes.
 * Note that this class NEVER closes the underlying stream, even when close
 * gets called.  Instead, it will read until the "end" of its chunking on
 * close, which allows for the seamless execution of subsequent HTTP 1.1
 * requests, while not requiring the client to remember to read the entire
 * contents of the response.
 *
 * <p>Implementation note: Choices abound. One approach would pass
 * through the {@link InputStream#mark} and {@link InputStream#reset} calls to
 * the underlying stream.  That's tricky, though, because you then have to
 * start duplicating the work of keeping track of how much a reset rewinds.
 * Further, you have to watch out for the "readLimit", and since the semantics
 * for the readLimit leave room for differing implementations, you might get
 * into a lot of trouble.</p>
 *
 * <p>Alternatively, you could make this class extend
 * {@link java.io.BufferedInputStream}
 * and then use the protected members of that class to avoid duplicated effort.
 * That solution has the side effect of adding yet another possible layer of
 * buffering.</p>
 *
 * <p>Then, there is the simple choice, which this takes - simply don't
 * support {@link InputStream#mark} and {@link InputStream#reset}.  That choice
 * has the added benefit of keeping this class very simple.</p>
 *
 * @author Ortwin Glueck
 * @author Eric Johnson
 * @author <a href="mailto:mbowler@GargoyleSoftware.com">Mike Bowler</a>
 *
 * @since 4.0
 */
public class ContentLengthInputStream extends InputStream {
    
    private static final int BUFFER_SIZE = 2048;
    /**
     * The maximum number of bytes that can be read from the stream. Subsequent
     * read operations will return -1.
     */
    private long contentLength;

    /** The current position */
    private long pos = 0;

    /** True if the stream is closed. */
    private boolean closed = false;

    /**
     * Wrapped input stream that all calls are delegated to.
     */
    private SessionInputBuffer in = null;

    /**
     * Creates a new length limited stream
     *
     * @param in The session input buffer to wrap
     * @param contentLength The maximum number of bytes that can be read from
     * the stream. Subsequent read operations will return -1.
     */
    public ContentLengthInputStream(final SessionInputBuffer in, long contentLength) {
        super();
        if (in == null) {
            throw new IllegalArgumentException("Input stream may not be null");
        }
        if (contentLength < 0) {
            throw new IllegalArgumentException("Content length may not be negative");
        }
        this.in = in;
        this.contentLength = contentLength;
    }

    /**
     * <p>Reads until the end of the known length of content.</p>
     *
     * <p>Does not close the underlying socket input, but instead leaves it
     * primed to parse the next response.</p>
     * @throws IOException If an IO problem occurs.
     */
    public void close() throws IOException {
        if (!closed) {
            try {
                byte buffer[] = new byte[BUFFER_SIZE];
                while (read(buffer) >= 0) {
                }
            } finally {
                // close after above so that we don't throw an exception trying
                // to read after closed!
                closed = true;
            }
        }
    }


    /**
     * Read the next byte from the stream
     * @return The next byte or -1 if the end of stream has been reached.
     * @throws IOException If an IO problem occurs
     * @see java.io.InputStream#read()
     */
    public int read() throws IOException {
        if (closed) {
            throw new IOException("Attempted read from closed stream.");
        }

        if (pos >= contentLength) {
            return -1;
        }
        pos++;
        return this.in.read();
    }

    /**
     * Does standard {@link InputStream#read(byte[], int, int)} behavior, but
     * also notifies the watcher when the contents have been consumed.
     *
     * @param b     The byte array to fill.
     * @param off   Start filling at this position.
     * @param len   The number of bytes to attempt to read.
     * @return The number of bytes read, or -1 if the end of content has been
     *  reached.
     *
     * @throws java.io.IOException Should an error occur on the wrapped stream.
     */
    public int read (byte[] b, int off, int len) throws java.io.IOException {
        if (closed) {
            throw new IOException("Attempted read from closed stream.");
        }

        if (pos >= contentLength) {
            return -1;
        }

        if (pos + len > contentLength) {
            len = (int) (contentLength - pos);
        }
        int count = this.in.read(b, off, len);
        pos += count;
        return count;
    }


    /**
     * Read more bytes from the stream.
     * @param b The byte array to put the new data in.
     * @return The number of bytes read into the buffer.
     * @throws IOException If an IO problem occurs
     * @see java.io.InputStream#read(byte[])
     */
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    /**
     * Skips and discards a number of bytes from the input stream.
     * @param n The number of bytes to skip.
     * @return The actual number of bytes skipped. <= 0 if no bytes
     * are skipped.
     * @throws IOException If an error occurs while skipping bytes.
     * @see InputStream#skip(long)
     */
    public long skip(long n) throws IOException {
        if (n <= 0) {
            return 0;
        }
        byte[] buffer = new byte[BUFFER_SIZE];
        // make sure we don't skip more bytes than are 
        // still available
        long remaining = Math.min(n, this.contentLength - this.pos); 
        // skip and keep track of the bytes actually skipped
        long count = 0;
        while (remaining > 0) {
            int l = read(buffer, 0, (int)Math.min(BUFFER_SIZE, remaining));
            if (l == -1) {
                break;
            }
            count += l;
            remaining -= l;
        }
        this.pos += count;
        return count;
    }
}
