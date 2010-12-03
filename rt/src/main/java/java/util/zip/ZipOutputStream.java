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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;

import org.apache.harmony.archive.internal.nls.Messages;

/**
 * This class provides an implementation of {@code FilterOutputStream} that
 * compresses data entries into a <i>ZIP-archive</i> output stream.
 * <p>
 * {@code ZipOutputStream} is used to write {@code ZipEntries} to the underlying
 * stream. Output from {@code ZipOutputStream} conforms to the {@code ZipFile}
 * file format.
 * <p>
 * While {@code DeflaterOutputStream} can write a compressed <i>ZIP-archive</i>
 * entry, this extension can write uncompressed entries as well. In this case
 * special rules apply, for this purpose refer to the <a
 * href="http://www.pkware.com/documents/casestudies/APPNOTE.TXT">file format
 * specification</a>.
 *
 * @see ZipEntry
 * @see ZipFile
 */
public class ZipOutputStream extends DeflaterOutputStream implements
        ZipConstants {

    /**
     * Indicates deflated entries.
     */
    public static final int DEFLATED = 8;

    /**
     * Indicates uncompressed entries.
     */
    public static final int STORED = 0;

    static final int ZIPDataDescriptorFlag = 8;

    static final int ZIPLocalHeaderVersionNeeded = 20;

    private String comment;

    private final Vector<String> entries = new Vector<String>();

    private int compressMethod = DEFLATED;

    private int compressLevel = Deflater.DEFAULT_COMPRESSION;

    private ByteArrayOutputStream cDir = new ByteArrayOutputStream();

    private ZipEntry currentEntry;

    private final CRC32 crc = new CRC32();

    private int offset = 0, curOffset = 0, nameLength;

    private byte[] nameBytes;

    /**
     * Constructs a new {@code ZipOutputStream} with the specified output
     * stream.
     *
     * @param p1
     *            the {@code OutputStream} to write the data to.
     */
    public ZipOutputStream(OutputStream p1) {
        super(p1, new Deflater(Deflater.DEFAULT_COMPRESSION, true));
    }

    /**
     * Closes the current {@code ZipEntry}, if any, and the underlying output
     * stream. If the stream is already closed this method does nothing.
     *
     * @throws IOException
     *             If an error occurs closing the stream.
     */
    @Override
    public void close() throws IOException {
        if (out != null) {
            finish();
            out.close();
            out = null;
        }
    }

    /**
     * Closes the current {@code ZipEntry}. Any entry terminal data is written
     * to the underlying stream.
     *
     * @throws IOException
     *             If an error occurs closing the entry.
     */
    public void closeEntry() throws IOException {
        if (cDir == null) {
            throw new IOException(Messages.getString("archive.1E")); //$NON-NLS-1$
        }
        if (currentEntry == null) {
            return;
        }
        if (currentEntry.getMethod() == DEFLATED) {
            super.finish();
        }

        // Verify values for STORED types
        if (currentEntry.getMethod() == STORED) {
            if (crc.getValue() != currentEntry.crc) {
                throw new ZipException(Messages.getString("archive.20")); //$NON-NLS-1$
            }
            if (currentEntry.size != crc.tbytes) {
                throw new ZipException(Messages.getString("archive.21")); //$NON-NLS-1$
            }
        }
        curOffset = LOCHDR;

        // Write the DataDescriptor
        if (currentEntry.getMethod() != STORED) {
            curOffset += EXTHDR;
            writeLong(out, EXTSIG);
            writeLong(out, currentEntry.crc = crc.getValue());
            writeLong(out, currentEntry.compressedSize = def.getTotalOut());
            writeLong(out, currentEntry.size = def.getTotalIn());
        }
        // Update the CentralDirectory
        writeLong(cDir, CENSIG);
        writeShort(cDir, ZIPLocalHeaderVersionNeeded); // Version created
        writeShort(cDir, ZIPLocalHeaderVersionNeeded); // Version to extract
        writeShort(cDir, currentEntry.getMethod() == STORED ? 0
                : ZIPDataDescriptorFlag);
        writeShort(cDir, currentEntry.getMethod());
        writeShort(cDir, currentEntry.time);
        writeShort(cDir, currentEntry.modDate);
        writeLong(cDir, crc.getValue());
        if (currentEntry.getMethod() == DEFLATED) {
            curOffset += writeLong(cDir, def.getTotalOut());
            writeLong(cDir, def.getTotalIn());
        } else {
            curOffset += writeLong(cDir, crc.tbytes);
            writeLong(cDir, crc.tbytes);
        }
        curOffset += writeShort(cDir, nameLength);
        if (currentEntry.extra != null) {
            curOffset += writeShort(cDir, currentEntry.extra.length);
        } else {
            writeShort(cDir, 0);
        }
        String c;
        if ((c = currentEntry.getComment()) != null) {
            writeShort(cDir, c.length());
        } else {
            writeShort(cDir, 0);
        }
        writeShort(cDir, 0); // Disk Start
        writeShort(cDir, 0); // Internal File Attributes
        writeLong(cDir, 0); // External File Attributes
        writeLong(cDir, offset);
        cDir.write(nameBytes);
        nameBytes = null;
        if (currentEntry.extra != null) {
            cDir.write(currentEntry.extra);
        }
        offset += curOffset;
        if (c != null) {
            cDir.write(c.getBytes());
        }
        currentEntry = null;
        crc.reset();
        def.reset();
        done = false;
    }

    /**
     * Indicates that all entries have been written to the stream. Any terminal
     * information is written to the underlying stream.
     *
     * @throws IOException
     *             if an error occurs while terminating the stream.
     */
    @Override
    public void finish() throws IOException {
        if (out == null) {
            throw new IOException(Messages.getString("archive.1E")); //$NON-NLS-1$
        }
        if (cDir == null) {
            return;
        }
        if (entries.size() == 0) {
            throw new ZipException(Messages.getString("archive.28")); //$NON-NLS-1$;
        }
        if (currentEntry != null) {
            closeEntry();
        }
        int cdirSize = cDir.size();
        // Write Central Dir End
        writeLong(cDir, ENDSIG);
        writeShort(cDir, 0); // Disk Number
        writeShort(cDir, 0); // Start Disk
        writeShort(cDir, entries.size()); // Number of entries
        writeShort(cDir, entries.size()); // Number of entries
        writeLong(cDir, cdirSize); // Size of central dir
        writeLong(cDir, offset); // Offset of central dir
        if (comment != null) {
            writeShort(cDir, comment.length());
            cDir.write(comment.getBytes());
        } else {
            writeShort(cDir, 0);
        }
        // Write the central dir
        out.write(cDir.toByteArray());
        cDir = null;

    }

    /**
     * Writes entry information to the underlying stream. Data associated with
     * the entry can then be written using {@code write()}. After data is
     * written {@code closeEntry()} must be called to complete the writing of
     * the entry to the underlying stream.
     *
     * @param ze
     *            the {@code ZipEntry} to store.
     * @throws IOException
     *             If an error occurs storing the entry.
     * @see #write
     */
    public void putNextEntry(ZipEntry ze) throws java.io.IOException {
        if (currentEntry != null) {
            closeEntry();
        }
        if (ze.getMethod() == STORED
                || (compressMethod == STORED && ze.getMethod() == -1)) {
            if (ze.crc == -1) {
                /* [MSG "archive.20", "Crc mismatch"] */
                throw new ZipException(Messages.getString("archive.20")); //$NON-NLS-1$
            }
            if (ze.size == -1 && ze.compressedSize == -1) {
                /* [MSG "archive.21", "Size mismatch"] */
                throw new ZipException(Messages.getString("archive.21")); //$NON-NLS-1$
            }
            if (ze.size != ze.compressedSize && ze.compressedSize != -1
                    && ze.size != -1) {
                /* [MSG "archive.21", "Size mismatch"] */
                throw new ZipException(Messages.getString("archive.21")); //$NON-NLS-1$
            }
        }
        /* [MSG "archive.1E", "Stream is closed"] */
        if (cDir == null) {
            throw new IOException(Messages.getString("archive.1E")); //$NON-NLS-1$
        }
        if (entries.contains(ze.name)) {
            /* [MSG "archive.29", "Entry already exists: {0}"] */
            throw new ZipException(Messages.getString("archive.29", ze.name)); //$NON-NLS-1$
        }
        nameLength = utf8Count(ze.name);
        if (nameLength > 0xffff) {
            /* [MSG "archive.2A", "Name too long: {0}"] */
            throw new IllegalArgumentException(Messages.getString(
                    "archive.2A", ze.name)); //$NON-NLS-1$
        }

        def.setLevel(compressLevel);
        currentEntry = ze;
        entries.add(currentEntry.name);
        if (currentEntry.getMethod() == -1) {
            currentEntry.setMethod(compressMethod);
        }
        writeLong(out, LOCSIG); // Entry header
        writeShort(out, ZIPLocalHeaderVersionNeeded); // Extraction version
        writeShort(out, currentEntry.getMethod() == STORED ? 0
                : ZIPDataDescriptorFlag);
        writeShort(out, currentEntry.getMethod());
        if (currentEntry.getTime() == -1) {
            currentEntry.setTime(System.currentTimeMillis());
        }
        writeShort(out, currentEntry.time);
        writeShort(out, currentEntry.modDate);

        if (currentEntry.getMethod() == STORED) {
            if (currentEntry.size == -1) {
                currentEntry.size = currentEntry.compressedSize;
            } else if (currentEntry.compressedSize == -1) {
                currentEntry.compressedSize = currentEntry.size;
            }
            writeLong(out, currentEntry.crc);
            writeLong(out, currentEntry.size);
            writeLong(out, currentEntry.size);
        } else {
            writeLong(out, 0);
            writeLong(out, 0);
            writeLong(out, 0);
        }
        writeShort(out, nameLength);
        if (currentEntry.extra != null) {
            writeShort(out, currentEntry.extra.length);
        } else {
            writeShort(out, 0);
        }
        nameBytes = toUTF8Bytes(currentEntry.name, nameLength);
        out.write(nameBytes);
        if (currentEntry.extra != null) {
            out.write(currentEntry.extra);
        }
    }

    /**
     * Sets the {@code ZipFile} comment associated with the file being written.
     *
     * @param comment
     *            the comment associated with the file.
     */
    public void setComment(String comment) {
        if (comment.length() > 0xFFFF) {
            throw new IllegalArgumentException(Messages.getString("archive.2B")); //$NON-NLS-1$
        }
        this.comment = comment;
    }

    /**
     * Sets the compression level to be used for writing entry data. This level
     * may be set on a per entry basis. The level must have a value between -1
     * and 8 according to the {@code Deflater} compression level bounds.
     *
     * @param level
     *            the compression level (ranging from -1 to 8).
     * @see Deflater
     */
    public void setLevel(int level) {
        if (level < Deflater.DEFAULT_COMPRESSION
                || level > Deflater.BEST_COMPRESSION) {
            throw new IllegalArgumentException();
        }
        compressLevel = level;
    }

    /**
     * Sets the compression method to be used when compressing entry data.
     * method must be one of {@code STORED} (for no compression) or {@code
     * DEFLATED}.
     *
     * @param method
     *            the compression method to use.
     */
    public void setMethod(int method) {
        if (method != STORED && method != DEFLATED) {
            throw new IllegalArgumentException();
        }
        compressMethod = method;

    }

    private long writeLong(OutputStream os, long i) throws java.io.IOException {
        // Write out the long value as an unsigned int
        os.write((int) (i & 0xFF));
        os.write((int) (i >> 8) & 0xFF);
        os.write((int) (i >> 16) & 0xFF);
        os.write((int) (i >> 24) & 0xFF);
        return i;
    }

    private int writeShort(OutputStream os, int i) throws java.io.IOException {
        os.write(i & 0xFF);
        os.write((i >> 8) & 0xFF);
        return i;

    }

    /**
     * Writes data for the current entry to the underlying stream.
     *
     * @exception IOException
     *                If an error occurs writing to the stream
     */
    @Override
    public void write(byte[] buffer, int off, int nbytes)
            throws java.io.IOException {
        // avoid int overflow, check null buf
        if ((off < 0 || (nbytes < 0) || off > buffer.length)
                || (buffer.length - off < nbytes)) {
            throw new IndexOutOfBoundsException();
        }

        if (currentEntry == null) {
            /* [MSG "archive.2C", "No active entry"] */
            throw new ZipException(Messages.getString("archive.2C")); //$NON-NLS-1$
        }

        if (currentEntry.getMethod() == STORED) {
            out.write(buffer, off, nbytes);
        } else {
            super.write(buffer, off, nbytes);
        }
        crc.update(buffer, off, nbytes);
    }

    static int utf8Count(String value) {
        int total = 0;
        for (int i = value.length(); --i >= 0;) {
            char ch = value.charAt(i);
            if (ch < 0x80) {
                total++;
            } else if (ch < 0x800) {
                total += 2;
            } else {
                total += 3;
            }
        }
        return total;
    }

    static byte[] toUTF8Bytes(String value, int length) {
        byte[] result = new byte[length];
        int pos = result.length;
        for (int i = value.length(); --i >= 0;) {
            char ch = value.charAt(i);
            if (ch < 0x80) {
                result[--pos] = (byte) ch;
            } else if (ch < 0x800) {
                result[--pos] = (byte) (0x80 | (ch & 0x3f));
                result[--pos] = (byte) (0xc0 | (ch >> 6));
            } else {
                result[--pos] = (byte) (0x80 | (ch & 0x3f));
                result[--pos] = (byte) (0x80 | ((ch >> 6) & 0x3f));
                result[--pos] = (byte) (0xe0 | (ch >> 12));
            }
        }
        return result;
    }
}
