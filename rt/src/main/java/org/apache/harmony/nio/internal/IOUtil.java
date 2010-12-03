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

package org.apache.harmony.nio.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;

import org.apache.harmony.nio.Util;
import org.apache.harmony.nio.internal.nls.Messages;

/**
 * Static methods for I/O util. Used by io package and nio package.
 */
public final class IOUtil {

    private static final int DEFAULT_BUFFER_SIZE = 8192;

    /*
     * Not designed to be instantiated.
     */
    private IOUtil() {
        super();
    }

    /*
     * Read method for InputStreamReader and Channels.
     */
    public static int readInputStreamReader(InputStream in, ByteBuffer bytes,
            CharBuffer chars, CharsetDecoder decoder, Object lock)
            throws IOException {
        synchronized (lock) {
            if (in != null) {
                if (chars.limit() == chars.position()) {
                    fillBuf(in, bytes, chars, decoder);
                }
                if (chars.limit() == 0) {
                    return -1;
                }
                return chars.get();
            }
            // nio.06=InputStreamReader is closed.
            throw new IOException(Messages.getString("nio.06")); //$NON-NLS-1$
        }
    }

    /*
     * Read method for InputStreamReader and Channels.
     */
    public static int readInputStreamReader(char[] buf, int offset, int length,
            InputStream in, ByteBuffer bytes, CharBuffer chars,
            CharsetDecoder decoder, Object lock) throws IOException {
        synchronized (lock) {
            if (in != null) {
                if (length == 0) {
                    return 0;
                }
                Util.assertArrayIndex(buf, offset, length);
                // read at least once
                if (chars.limit() == chars.position()) {
                    fillBuf(in, bytes, chars, decoder);
                }
                int position = chars.position();
                int availableChars = chars.limit() - position;
                // read at least once for one byte
                int needChars = length;
                while (availableChars < needChars) {
                    System.arraycopy(chars.array(), position, buf, offset,
                            availableChars);
                    chars.position(position + availableChars);
                    needChars -= availableChars;
                    offset += availableChars;
                    if (in.available() <= 0) {
                        return needChars == length ? -1 : length - needChars;
                    }
                    fillBuf(in, bytes, chars, decoder);
                    position = chars.position();
                    availableChars = chars.limit();
                    if (availableChars == 0) {
                        return needChars == length ? -1 : length - needChars;
                    }
                }
                System.arraycopy(chars.array(), position, buf, offset,
                        needChars);
                chars.position(chars.position() + needChars);
                return length;
            }
            // nio.06=InputStreamReader is closed.
            throw new IOException(Messages.getString("nio.06")); //$NON-NLS-1$
        }
    }

    /*
     * Refill the buffer from wrapped InputStream.
     */
    private static void fillBuf(InputStream in, ByteBuffer bytes,
            CharBuffer chars, CharsetDecoder decoder) throws IOException {
        chars.clear();
        int read = 0;
        try {
            read = in.read(bytes.array());
        } catch (IOException e) {
            chars.limit(0);
            throw e;
        }
        if (read == -1) {
            chars.limit(0);
            return;
        }
        bytes.limit(read);
        boolean endOfInput = read < DEFAULT_BUFFER_SIZE;
        CoderResult result = decoder.decode(bytes, chars, endOfInput);
        if (result.isError()) {
            throw new IOException(result.toString());
        }
        bytes.clear();
        chars.flip();
    }

    /*
     * Write method for OutputStreamWriter and Channels.
     */
    public static void writeOutputStreamWriter(String str, int offset,
            int count, OutputStream out, ByteBuffer bytes,
            CharsetEncoder encoder, Object lock) throws IOException {
        Util.assertArrayIndex(str.length(), offset, count);
        CharBuffer chars = CharBuffer.wrap(str, offset, count + offset);
        convert(lock, encoder, bytes, chars, out);
    }

    /*
     * Write method for OutputStreamWriter and Channels.
     */
    public static void writeOutputStreamWriter(int oneChar, OutputStream out,
            ByteBuffer bytes, CharsetEncoder encoder, Object lock)
            throws IOException {
        synchronized (lock) {
            if (encoder == null) {
                // nio.07=Writer is closed.
                throw new IOException(Messages.getString("nio.07")); //$NON-NLS-1$
            }
            CharBuffer chars = CharBuffer.wrap(new char[] { (char) oneChar });
            convert(lock, encoder, bytes, chars, out);
        }
    }

    /*
     * Write method for OutputStreamWriter and Channels.
     */
    public static void writeOutputStreamWriter(char[] buf, int offset,
            int count, OutputStream out, ByteBuffer bytes,
            CharsetEncoder encoder, Object lock) throws IOException {
        Util.assertArrayIndex(buf, offset, count);
        CharBuffer chars = CharBuffer.wrap(buf, offset, count);
        convert(lock, encoder, bytes, chars, out);
    }

    /*
     * Flush method for OutputStreamWriter and Channels.
     */
    public static void flushOutputStreamWriter(OutputStream out,
            ByteBuffer bytes, CharsetEncoder encoder, Object lock)
            throws IOException {
        synchronized (lock) {
            if (encoder == null) {
                // nio.07=Writer is closed.
                throw new IOException(Messages.getString("nio.07")); //$NON-NLS-1$
            }
            int position;
            if ((position = bytes.position()) > 0) {
                bytes.flip();
                out.write(bytes.array(), 0, position);
                bytes.clear();
            }
            out.flush();
        }
    }

    /*
     * Convert function used in write.
     */
    private static void convert(Object lock, CharsetEncoder encoder,
            ByteBuffer bytes, CharBuffer chars, OutputStream out)
            throws IOException {
        synchronized (lock) {
            if (encoder == null) {
                // nio.07=Writer is closed.
                throw new IOException(Messages.getString("nio.07")); //$NON-NLS-1$
            }
            CoderResult result = encoder.encode(chars, bytes, true);
            while (true) {
                if (result.isError()) {
                    throw new IOException(result.toString());
                } else if (result.isOverflow()) {
                    // flush the output buffer
                    flushOutputStreamWriter(out, bytes, encoder, lock);
                    result = encoder.encode(chars, bytes, true);
                    continue;
                }
                break;
            }
        }
    }
}
