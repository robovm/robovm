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

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import java.util.Arrays;
import libcore.io.Memory;

/**
 * The {@code GZIPInputStream} class is used to read data stored in the GZIP
 * format, reading and decompressing GZIP data from the underlying stream into
 * its buffer.
 *
 * <h3>Example</h3>
 * <p>Using {@code GZIPInputStream} is easier than {@link ZipInputStream}
 * because GZIP is only for compression, and is not a container for multiple files.
 * This code decompresses the data from a GZIP stream, similar to the {@code gunzip(1)} utility.
 * <pre>
 * InputStream is = ...
 * GZIPInputStream zis = new GZIPInputStream(new BufferedInputStream(is));
 * try {
 *     // Reading from 'zis' gets you the uncompressed bytes...
 *     processStream(zis);
 * } finally {
 *     zis.close();
 * }
 * </pre>
 */
public class GZIPInputStream extends InflaterInputStream {
    private static final int FCOMMENT = 16;

    private static final int FEXTRA = 4;

    private static final int FHCRC = 2;

    private static final int FNAME = 8;

    /**
     * The magic header for the GZIP format.
     */
    public static final int GZIP_MAGIC = 0x8b1f;

    /**
     * The checksum algorithm used when handling uncompressed data.
     */
    protected CRC32 crc = new CRC32();

    /**
     * Indicates the end of the input stream.
     */
    protected boolean eos = false;

    /**
     * Construct a {@code GZIPInputStream} to read from GZIP data from the
     * underlying stream.
     *
     * @param is
     *            the {@code InputStream} to read data from.
     * @throws IOException
     *             if an {@code IOException} occurs.
     */
    public GZIPInputStream(InputStream is) throws IOException {
        this(is, BUF_SIZE);
    }

    /**
     * Construct a {@code GZIPInputStream} to read from GZIP data from the
     * underlying stream. Set the internal buffer size to {@code size}.
     *
     * @param is
     *            the {@code InputStream} to read data from.
     * @param size
     *            the internal read buffer size.
     * @throws IOException
     *             if an {@code IOException} occurs.
     */
    public GZIPInputStream(InputStream is, int size) throws IOException {
        super(is, new Inflater(true), size);
        byte[] header = new byte[10];
        readFully(header, 0, header.length);
        short magic = Memory.peekShort(header, 0, ByteOrder.LITTLE_ENDIAN);
        if (magic != (short) GZIP_MAGIC) {
            throw new IOException(String.format("unknown format (magic number %x)", magic));
        }
        int flags = header[3];
        boolean hcrc = (flags & FHCRC) != 0;
        if (hcrc) {
            crc.update(header, 0, header.length);
        }
        if ((flags & FEXTRA) != 0) {
            readFully(header, 0, 2);
            if (hcrc) {
                crc.update(header, 0, 2);
            }
            int length = Memory.peekShort(header, 0, ByteOrder.LITTLE_ENDIAN) & 0xffff;
            while (length > 0) {
                int max = length > buf.length ? buf.length : length;
                int result = in.read(buf, 0, max);
                if (result == -1) {
                    throw new EOFException();
                }
                if (hcrc) {
                    crc.update(buf, 0, result);
                }
                length -= result;
            }
        }
        if ((flags & FNAME) != 0) {
            readZeroTerminated(hcrc);
        }
        if ((flags & FCOMMENT) != 0) {
            readZeroTerminated(hcrc);
        }
        if (hcrc) {
            readFully(header, 0, 2);
            short crc16 = Memory.peekShort(header, 0, ByteOrder.LITTLE_ENDIAN);
            if ((short) crc.getValue() != crc16) {
                throw new IOException("CRC mismatch");
            }
            crc.reset();
        }
    }

    /**
     * Closes this stream and any underlying streams.
     */
    @Override
    public void close() throws IOException {
        eos = true;
        super.close();
    }

    /**
     * Reads and decompresses GZIP data from the underlying stream into the
     * given buffer.
     */
    @Override
    public int read(byte[] buffer, int offset, int byteCount) throws IOException {
        if (closed) {
            throw new IOException("Stream is closed");
        }
        if (eos) {
            return -1;
        }
        Arrays.checkOffsetAndCount(buffer.length, offset, byteCount);

        int bytesRead;
        try {
            bytesRead = super.read(buffer, offset, byteCount);
        } finally {
            eos = eof; // update eos after every read(), even when it throws
        }

        if (bytesRead != -1) {
            crc.update(buffer, offset, bytesRead);
        }

        if (eos) {
            verifyCrc();
        }

        return bytesRead;
    }

    private void verifyCrc() throws IOException {
        // Get non-compressed bytes read by fill
        int size = inf.getRemaining();
        final int trailerSize = 8; // crc (4 bytes) + total out (4 bytes)
        byte[] b = new byte[trailerSize];
        int copySize = (size > trailerSize) ? trailerSize : size;

        System.arraycopy(buf, len - size, b, 0, copySize);
        readFully(b, copySize, trailerSize - copySize);

        if (Memory.peekInt(b, 0, ByteOrder.LITTLE_ENDIAN) != (int) crc.getValue()) {
            throw new IOException("CRC mismatch");
        }
        if (Memory.peekInt(b, 4, ByteOrder.LITTLE_ENDIAN) != inf.getTotalOut()) {
            throw new IOException("Size mismatch");
        }
    }

    private void readFully(byte[] buffer, int offset, int length) throws IOException {
        int result;
        while (length > 0) {
            result = in.read(buffer, offset, length);
            if (result == -1) {
                throw new EOFException();
            }
            offset += result;
            length -= result;
        }
    }

    private void readZeroTerminated(boolean hcrc) throws IOException {
        int result;
        while ((result = in.read()) > 0) {
            if (hcrc) {
                crc.update(result);
            }
        }
        if (result == -1) {
            throw new EOFException();
        }
        // Add the zero
        if (hcrc) {
            crc.update(result);
        }
    }
}
