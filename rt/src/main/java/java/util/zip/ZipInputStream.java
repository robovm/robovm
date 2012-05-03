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

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.nio.ByteOrder;
import java.nio.charset.ModifiedUtf8;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.Arrays;
import libcore.io.Memory;
import libcore.io.Streams;

/**
 * This class provides an implementation of {@code FilterInputStream} that
 * decompresses data from an {@code InputStream} containing a ZIP archive.
 *
 * <p>A ZIP archive is a collection of (possibly) compressed files.
 * When reading from a {@code ZipInputStream}, you retrieve the
 * entry's metadata with {@code getNextEntry} before you can read the userdata.
 *
 * <p>Although {@code InflaterInputStream} can only read compressed ZIP archive
 * entries, this class can read non-compressed entries as well.
 *
 * <p>Use {@code ZipFile} if you can access the archive as a file directly,
 * especially if you want random access to entries, rather than needing to
 * iterate over all entries.
 *
 * <h3>Example</h3>
 * <p>Using {@code ZipInputStream} is a little more complicated than {@link GZIPInputStream}
 * because ZIP archives are containers that can contain multiple files. This code pulls all the
 * files out of a ZIP archive, similar to the {@code unzip(1)} utility.
 * <pre>
 * InputStream is = ...
 * ZipInputStream zis = new ZipInputStream(new BufferedInputStream(is));
 * try {
 *     ZipEntry ze;
 *     while ((ze = zis.getNextEntry()) != null) {
 *         ByteArrayOutputStream baos = new ByteArrayOutputStream();
 *         byte[] buffer = new byte[1024];
 *         int count;
 *         while ((count = zis.read(buffer)) != -1) {
 *             baos.write(buffer, 0, count);
 *         }
 *         String filename = ze.getName();
 *         byte[] bytes = baos.toByteArray();
 *         // do something with 'filename' and 'bytes'...
 *     }
 * } finally {
 *     zis.close();
 * }
 * </pre>
 *
 * @see ZipEntry
 * @see ZipFile
 */
public class ZipInputStream extends InflaterInputStream implements ZipConstants {
    private static final int ZIPLocalHeaderVersionNeeded = 20;

    private boolean entriesEnd = false;

    private boolean hasDD = false;

    private int entryIn = 0;

    private int inRead, lastRead = 0;

    private ZipEntry currentEntry;

    private final byte[] hdrBuf = new byte[LOCHDR - LOCVER];

    private final CRC32 crc = new CRC32();

    private byte[] nameBuf = new byte[256];

    private char[] charBuf = new char[256];

    /**
     * Constructs a new {@code ZipInputStream} from the specified input stream.
     *
     * @param stream
     *            the input stream to representing a ZIP archive.
     */
    public ZipInputStream(InputStream stream) {
        super(new PushbackInputStream(stream, BUF_SIZE), new Inflater(true));
        if (stream == null) {
            throw new NullPointerException();
        }
    }

    /**
     * Closes this {@code ZipInputStream}.
     *
     * @throws IOException
     *             if an {@code IOException} occurs.
     */
    @Override
    public void close() throws IOException {
        if (!closed) {
            closeEntry(); // Close the current entry
            super.close();
        }
    }

    /**
     * Closes the current ZIP entry and positions to read the next entry.
     *
     * @throws IOException
     *             if an {@code IOException} occurs.
     */
    public void closeEntry() throws IOException {
        checkClosed();
        if (currentEntry == null) {
            return;
        }
        if (currentEntry instanceof java.util.jar.JarEntry) {
            Attributes temp = ((JarEntry) currentEntry).getAttributes();
            if (temp != null && temp.containsKey("hidden")) {
                return;
            }
        }

        /*
         * The following code is careful to leave the ZipInputStream in a
         * consistent state, even when close() results in an exception. It does
         * so by:
         *  - pushing bytes back into the source stream
         *  - reading a data descriptor footer from the source stream
         *  - resetting fields that manage the entry being closed
         */

        // Ensure all entry bytes are read
        Exception failure = null;
        try {
            Streams.skipAll(this);
        } catch (Exception e) {
            failure = e;
        }

        int inB, out;
        if (currentEntry.compressionMethod == ZipEntry.DEFLATED) {
            inB = inf.getTotalIn();
            out = inf.getTotalOut();
        } else {
            inB = inRead;
            out = inRead;
        }
        int diff = entryIn - inB;
        // Pushback any required bytes
        if (diff != 0) {
            ((PushbackInputStream) in).unread(buf, len - diff, diff);
        }

        try {
            readAndVerifyDataDescriptor(inB, out);
        } catch (Exception e) {
            if (failure == null) { // otherwise we're already going to throw
                failure = e;
            }
        }

        inf.reset();
        lastRead = inRead = entryIn = len = 0;
        crc.reset();
        currentEntry = null;

        if (failure != null) {
            if (failure instanceof IOException) {
                throw (IOException) failure;
            } else if (failure instanceof RuntimeException) {
                throw (RuntimeException) failure;
            }
            AssertionError error = new AssertionError();
            error.initCause(failure);
            throw error;
        }
    }

    private void readAndVerifyDataDescriptor(int inB, int out) throws IOException {
        if (hasDD) {
            Streams.readFully(in, hdrBuf, 0, EXTHDR);
            int sig = Memory.peekInt(hdrBuf, 0, ByteOrder.LITTLE_ENDIAN);
            if (sig != (int) EXTSIG) {
                throw new ZipException(String.format("unknown format (EXTSIG=%x)", sig));
            }
            currentEntry.crc = ((long) Memory.peekInt(hdrBuf, EXTCRC, ByteOrder.LITTLE_ENDIAN)) & 0xffffffffL;
            currentEntry.compressedSize = ((long) Memory.peekInt(hdrBuf, EXTSIZ, ByteOrder.LITTLE_ENDIAN)) & 0xffffffffL;
            currentEntry.size = ((long) Memory.peekInt(hdrBuf, EXTLEN, ByteOrder.LITTLE_ENDIAN)) & 0xffffffffL;
        }
        if (currentEntry.crc != crc.getValue()) {
            throw new ZipException("CRC mismatch");
        }
        if (currentEntry.compressedSize != inB || currentEntry.size != out) {
            throw new ZipException("Size mismatch");
        }
    }

    /**
     * Reads the next entry from this {@code ZipInputStream} or {@code null} if
     * no more entries are present.
     *
     * @return the next {@code ZipEntry} contained in the input stream.
     * @throws IOException
     *             if an {@code IOException} occurs.
     * @see ZipEntry
     */
    public ZipEntry getNextEntry() throws IOException {
        closeEntry();
        if (entriesEnd) {
            return null;
        }

        Streams.readFully(in, hdrBuf, 0, 4);
        int hdr = Memory.peekInt(hdrBuf, 0, ByteOrder.LITTLE_ENDIAN);
        if (hdr == CENSIG) {
            entriesEnd = true;
            return null;
        }
        if (hdr != LOCSIG) {
            return null;
        }

        // Read the local header
        Streams.readFully(in, hdrBuf, 0, (LOCHDR - LOCVER));
        int version = Memory.peekShort(hdrBuf, 0, ByteOrder.LITTLE_ENDIAN) & 0xff;
        if (version > ZIPLocalHeaderVersionNeeded) {
            throw new ZipException("Cannot read local header version " + version);
        }
        short flags = Memory.peekShort(hdrBuf, LOCFLG - LOCVER, ByteOrder.LITTLE_ENDIAN);
        hasDD = ((flags & ZipFile.GPBF_DATA_DESCRIPTOR_FLAG) != 0);
        int ceTime = Memory.peekShort(hdrBuf, LOCTIM - LOCVER, ByteOrder.LITTLE_ENDIAN) & 0xffff;
        int ceModDate = Memory.peekShort(hdrBuf, LOCTIM - LOCVER + 2, ByteOrder.LITTLE_ENDIAN) & 0xffff;
        int ceCompressionMethod = Memory.peekShort(hdrBuf, LOCHOW - LOCVER, ByteOrder.LITTLE_ENDIAN) & 0xffff;
        long ceCrc = 0, ceCompressedSize = 0, ceSize = -1;
        if (!hasDD) {
            ceCrc = ((long) Memory.peekInt(hdrBuf, LOCCRC - LOCVER, ByteOrder.LITTLE_ENDIAN)) & 0xffffffffL;
            ceCompressedSize = ((long) Memory.peekInt(hdrBuf, LOCSIZ - LOCVER, ByteOrder.LITTLE_ENDIAN)) & 0xffffffffL;
            ceSize = ((long) Memory.peekInt(hdrBuf, LOCLEN - LOCVER, ByteOrder.LITTLE_ENDIAN)) & 0xffffffffL;
        }
        int nameLength = Memory.peekShort(hdrBuf, LOCNAM - LOCVER, ByteOrder.LITTLE_ENDIAN) & 0xffff;
        if (nameLength == 0) {
            throw new ZipException("Entry is not named");
        }
        int extraLength = Memory.peekShort(hdrBuf, LOCEXT - LOCVER, ByteOrder.LITTLE_ENDIAN) & 0xffff;

        if (nameLength > nameBuf.length) {
            nameBuf = new byte[nameLength];
            // The bytes are modified UTF-8, so the number of chars will always be less than or
            // equal to the number of bytes. It's fine if this buffer is too long.
            charBuf = new char[nameLength];
        }
        Streams.readFully(in, nameBuf, 0, nameLength);
        currentEntry = createZipEntry(ModifiedUtf8.decode(nameBuf, charBuf, 0, nameLength));
        currentEntry.time = ceTime;
        currentEntry.modDate = ceModDate;
        currentEntry.setMethod(ceCompressionMethod);
        if (ceSize != -1) {
            currentEntry.setCrc(ceCrc);
            currentEntry.setSize(ceSize);
            currentEntry.setCompressedSize(ceCompressedSize);
        }
        if (extraLength > 0) {
            byte[] extraData = new byte[extraLength];
            Streams.readFully(in, extraData, 0, extraLength);
            currentEntry.setExtra(extraData);
        }
        return currentEntry;
    }

    /**
     * Reads up to the specified number of uncompressed bytes into the buffer
     * starting at the offset.
     *
     * @return the number of bytes read
     */
    @Override
    public int read(byte[] buffer, int offset, int byteCount) throws IOException {
        checkClosed();
        Arrays.checkOffsetAndCount(buffer.length, offset, byteCount);

        if (inf.finished() || currentEntry == null) {
            return -1;
        }

        if (currentEntry.compressionMethod == ZipEntry.STORED) {
            int csize = (int) currentEntry.size;
            if (inRead >= csize) {
                return -1;
            }
            if (lastRead >= len) {
                lastRead = 0;
                if ((len = in.read(buf)) == -1) {
                    eof = true;
                    return -1;
                }
                entryIn += len;
            }
            int toRead = byteCount > (len - lastRead) ? len - lastRead : byteCount;
            if ((csize - inRead) < toRead) {
                toRead = csize - inRead;
            }
            System.arraycopy(buf, lastRead, buffer, offset, toRead);
            lastRead += toRead;
            inRead += toRead;
            crc.update(buffer, offset, toRead);
            return toRead;
        }
        if (inf.needsInput()) {
            fill();
            if (len > 0) {
                entryIn += len;
            }
        }
        int read;
        try {
            read = inf.inflate(buffer, offset, byteCount);
        } catch (DataFormatException e) {
            throw new ZipException(e.getMessage());
        }
        if (read == 0 && inf.finished()) {
            return -1;
        }
        crc.update(buffer, offset, read);
        return read;
    }

    @Override
    public int available() throws IOException {
        checkClosed();
        // The InflaterInputStream contract says we must only return 0 or 1.
        return (currentEntry == null || inRead < currentEntry.size) ? 1 : 0;
    }

    /**
     * creates a {@link ZipEntry } with the given name.
     *
     * @param name
     *            the name of the entry.
     * @return the created {@code ZipEntry}.
     */
    protected ZipEntry createZipEntry(String name) {
        return new ZipEntry(name);
    }

    private void checkClosed() throws IOException {
        if (closed) {
            throw new IOException("Stream is closed");
        }
    }
}
