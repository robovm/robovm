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

package org.apache.harmony.luni.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * The class contains static {@link java.io.InputStream} utilities.
 */
public class InputStreamHelper {

    /**
     * Provides access to a protected underlying buffer of
     * <code>ByteArrayInputStream</code>.
     */
    private static final Field BAIS_BUF;

    /**
     * Provides access to a protected position in the underlying buffer of
     * <code>ByteArrayInputStream</code>.
     */
    private static final Field BAIS_POS;

    static {
        final Field[] f = new Field[2];
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                try {
                    f[0] = ByteArrayInputStream.class.getDeclaredField("buf"); //$NON-NLS-1$
                    f[0].setAccessible(true);
                    f[1] = ByteArrayInputStream.class.getDeclaredField("pos"); //$NON-NLS-1$
                    f[1].setAccessible(true);
                } catch (NoSuchFieldException nsfe) {
                    throw new InternalError(nsfe.getLocalizedMessage());
                }
                return null;
            }
        });
        BAIS_BUF = f[0];
        BAIS_POS = f[1];
    }

    /**
     * The extension of <code>ByteArrayInputStream</code> which exposes an
     * underlying buffer.
     */
    static class ExposedByteArrayInputStream extends ByteArrayInputStream {

        /**
         * @see java.io.ByteArrayInputStream(byte[])
         */
        public ExposedByteArrayInputStream(byte buf[]) {
            super(buf);
        }

        /**
         * @see java.io.ByteArrayInputStream(byte[], int, int)
         */
        public ExposedByteArrayInputStream(byte buf[], int offset, int length) {
            super(buf, offset, length);
        }

        /**
         * Reads the whole stream and returns the stream snapshot.
         */
        public synchronized byte[] expose() {
            if (pos == 0 && count == buf.length) {
                skip(count);
                return buf;
            }

            final int available = available();
            final byte[] buffer = new byte[available];
            System.arraycopy(buf, pos, buffer, 0, available);
            skip(available);
            return buffer;
        }
    }

    /**
     * Reads all bytes from {@link java.io.ByteArrayInputStream} using its
     * underlying buffer directly.
     * 
     * @return an underlying buffer, if a current position is at the buffer
     *         beginning, and an end position is at the buffer end, or a copy of
     *         the underlying buffer part.
     */
    private static byte[] expose(ByteArrayInputStream bais) {
        byte[] buffer, buf;
        int pos;
        synchronized (bais) {
            int available = bais.available();
            try {
                buf = (byte[]) BAIS_BUF.get(bais);
                pos = BAIS_POS.getInt(bais);
            } catch (IllegalAccessException iae) {
                throw new InternalError(iae.getLocalizedMessage());
            }
            if (pos == 0 && available == buf.length) {
                buffer = buf;
            } else {
                buffer = new byte[available];
                System.arraycopy(buf, pos, buffer, 0, available);
            }
            bais.skip(available);
        }
        return buffer;
    }

    /**
     * The utility method for reading the whole input stream into a snapshot
     * buffer. To speed up the access it works with an underlying buffer for a
     * given {@link java.io.ByteArrayInputStream}.
     * 
     * @param is
     *            the stream to be read.
     * @return the snapshot wrapping the buffer where the bytes are read to.
     * @throws UnsupportedOperationException
     *             if the input stream data cannot be exposed
     */
    public static byte[] expose(InputStream is) throws IOException,
            UnsupportedOperationException {
        if (is instanceof ExposedByteArrayInputStream) {
            return ((ExposedByteArrayInputStream) is).expose();
        }

        if (is.getClass().equals(ByteArrayInputStream.class)) {
            return expose((ByteArrayInputStream) is);
        }

        // We don't know how to do this
        throw new UnsupportedOperationException();
    }

    /**
     * Reads all the bytes from the given input stream.
     * 
     * Calls read multiple times on the given input stream until it receives an
     * end of file marker. Returns the combined results as a byte array. Note
     * that this method may block if the underlying stream read blocks.
     * 
     * @param is
     *            the input stream to be read.
     * @return the content of the stream as a byte array.
     * @throws IOException
     *             if a read error occurs.
     */
    public static byte[] readFullyAndClose(InputStream is) throws IOException {

        try {
            // Initial read
            byte[] buffer = new byte[1024];
            int count = is.read(buffer);
            int nextByte = is.read();

            // Did we get it all in one read?
            if (nextByte == -1) {
                byte[] dest = new byte[count];
                System.arraycopy(buffer, 0, dest, 0, count);
                return dest;
            }

            // Requires additional reads
            ByteArrayOutputStream baos = new ByteArrayOutputStream(count * 2);
            baos.write(buffer, 0, count);
            baos.write(nextByte);
            while (true) {
                count = is.read(buffer);
                if (count == -1) {
                    return baos.toByteArray();
                }
                baos.write(buffer, 0, count);
            }
        } finally {
            is.close();
        }
    }
}
