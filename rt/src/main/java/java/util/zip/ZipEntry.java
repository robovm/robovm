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
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.apache.harmony.archive.internal.nls.Messages;

/**
 * An instance of {@code ZipEntry} represents an entry within a <i>ZIP-archive</i>.
 * An entry has attributes such as name (= path) or the size of its data. While
 * an entry identifies data stored in an archive, it does not hold the data
 * itself. For example when reading a <i>ZIP-file</i> you will first retrieve
 * all its entries in a collection and then read the data for a specific entry
 * through an input stream.
 *
 * @see ZipFile
 * @see ZipOutputStream
 */
public class ZipEntry implements ZipConstants, Cloneable {
    String name, comment;

    long compressedSize = -1, crc = -1, size = -1;

    int compressionMethod = -1, time = -1, modDate = -1;

    byte[] extra;

    int nameLen = -1;
    long mLocalHeaderRelOffset = -1;

    /**
     * Zip entry state: Deflated.
     */
    public static final int DEFLATED = 8;

    /**
     * Zip entry state: Stored.
     */
    public static final int STORED = 0;

    /**
     * Constructs a new {@code ZipEntry} with the specified name.
     *
     * @param name
     *            the name of the ZIP entry.
     * @throws IllegalArgumentException
     *             if the name length is outside the range (> 0xFFFF).
     */
    public ZipEntry(String name) {
        if (name == null) {
            throw new NullPointerException();
        }
        if (name.length() > 0xFFFF) {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }

    /**
     * Gets the comment for this {@code ZipEntry}.
     *
     * @return the comment for this {@code ZipEntry}, or {@code null} if there
     *         is no comment. If we're reading an archive with
     *         {@code ZipInputStream} the comment is not available.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Gets the compressed size of this {@code ZipEntry}.
     *
     * @return the compressed size, or -1 if the compressed size has not been
     *         set.
     */
    public long getCompressedSize() {
        return compressedSize;
    }

    /**
     * Gets the checksum for this {@code ZipEntry}.
     *
     * @return the checksum, or -1 if the checksum has not been set.
     */
    public long getCrc() {
        return crc;
    }

    /**
     * Gets the extra information for this {@code ZipEntry}.
     *
     * @return a byte array containing the extra information, or {@code null} if
     *         there is none.
     */
    public byte[] getExtra() {
        return extra;
    }

    /**
     * Gets the compression method for this {@code ZipEntry}.
     *
     * @return the compression method, either {@code DEFLATED}, {@code STORED}
     *         or -1 if the compression method has not been set.
     */
    public int getMethod() {
        return compressionMethod;
    }

    /**
     * Gets the name of this {@code ZipEntry}.
     *
     * @return the entry name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the uncompressed size of this {@code ZipEntry}.
     *
     * @return the uncompressed size, or {@code -1} if the size has not been
     *         set.
     */
    public long getSize() {
        return size;
    }

    /**
     * Gets the last modification time of this {@code ZipEntry}.
     *
     * @return the last modification time as the number of milliseconds since
     *         Jan. 1, 1970.
     */
    public long getTime() {
        if (time != -1) {
            GregorianCalendar cal = new GregorianCalendar();
            cal.set(Calendar.MILLISECOND, 0);
            cal.set(1980 + ((modDate >> 9) & 0x7f), ((modDate >> 5) & 0xf) - 1,
                    modDate & 0x1f, (time >> 11) & 0x1f, (time >> 5) & 0x3f,
                    (time & 0x1f) << 1);
            return cal.getTime().getTime();
        }
        return -1;
    }

    /**
     * Determine whether or not this {@code ZipEntry} is a directory.
     *
     * @return {@code true} when this {@code ZipEntry} is a directory, {@code
     *         false} otherwise.
     */
    public boolean isDirectory() {
        return name.charAt(name.length() - 1) == '/';
    }

    /**
     * Sets the comment for this {@code ZipEntry}.
     *
     * @param string
     *            the comment for this entry.
     */
    public void setComment(String string) {
        if (string == null || string.length() <= 0xFFFF) {
            comment = string;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Sets the compressed size for this {@code ZipEntry}.
     *
     * @param value
     *            the compressed size (in bytes).
     */
    public void setCompressedSize(long value) {
        compressedSize = value;
    }

    /**
     * Sets the checksum for this {@code ZipEntry}.
     *
     * @param value
     *            the checksum for this entry.
     * @throws IllegalArgumentException
     *             if {@code value} is < 0 or > 0xFFFFFFFFL.
     */
    public void setCrc(long value) {
        if (value >= 0 && value <= 0xFFFFFFFFL) {
            crc = value;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Sets the extra information for this {@code ZipEntry}.
     *
     * @param data
     *            a byte array containing the extra information.
     * @throws IllegalArgumentException
     *             when the length of data is greater than 0xFFFF bytes.
     */
    public void setExtra(byte[] data) {
        if (data == null || data.length <= 0xFFFF) {
            extra = data;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Sets the compression method for this {@code ZipEntry}.
     *
     * @param value
     *            the compression method, either {@code DEFLATED} or {@code
     *            STORED}.
     * @throws IllegalArgumentException
     *             when value is not {@code DEFLATED} or {@code STORED}.
     */
    public void setMethod(int value) {
        if (value != STORED && value != DEFLATED) {
            throw new IllegalArgumentException();
        }
        compressionMethod = value;
    }

    /**
     * Sets the uncompressed size of this {@code ZipEntry}.
     *
     * @param value
     *            the uncompressed size for this entry.
     * @throws IllegalArgumentException
     *             if {@code value} < 0 or {@code value} > 0xFFFFFFFFL.
     */
    public void setSize(long value) {
        if (value >= 0 && value <= 0xFFFFFFFFL) {
            size = value;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Sets the modification time of this {@code ZipEntry}.
     *
     * @param value
     *            the modification time as the number of milliseconds since Jan.
     *            1, 1970.
     */
    public void setTime(long value) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date(value));
        int year = cal.get(Calendar.YEAR);
        if (year < 1980) {
            modDate = 0x21;
            time = 0;
        } else {
            modDate = cal.get(Calendar.DATE);
            modDate = (cal.get(Calendar.MONTH) + 1 << 5) | modDate;
            modDate = ((cal.get(Calendar.YEAR) - 1980) << 9) | modDate;
            time = cal.get(Calendar.SECOND) >> 1;
            time = (cal.get(Calendar.MINUTE) << 5) | time;
            time = (cal.get(Calendar.HOUR_OF_DAY) << 11) | time;
        }
    }

    /**
     * Returns the string representation of this {@code ZipEntry}.
     *
     * @return the string representation of this {@code ZipEntry}.
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Constructs a new {@code ZipEntry} using the values obtained from {@code
     * ze}.
     *
     * @param ze
     *            the {@code ZipEntry} from which to obtain values.
     */
    public ZipEntry(ZipEntry ze) {
        name = ze.name;
        comment = ze.comment;
        time = ze.time;
        size = ze.size;
        compressedSize = ze.compressedSize;
        crc = ze.crc;
        compressionMethod = ze.compressionMethod;
        modDate = ze.modDate;
        extra = ze.extra;
        nameLen = ze.nameLen;
        mLocalHeaderRelOffset = ze.mLocalHeaderRelOffset;
    }

    /**
     * Returns a shallow copy of this entry.
     *
     * @return a copy of this entry.
     */
    @Override
    public Object clone() {
        return new ZipEntry(this);
    }

    /**
     * Returns the hash code for this {@code ZipEntry}.
     *
     * @return the hash code of the entry.
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /*
     * Internal constructor.  Creates a new ZipEntry by reading the
     * Central Directory Entry from "in", which must be positioned at
     * the CDE signature.
     *
     * On exit, "in" will be positioned at the start of the next entry.
     */
    ZipEntry(LittleEndianReader ler, InputStream in) throws IOException {

        /*
         * We're seeing performance issues when we call readShortLE and
         * readIntLE, so we're going to read the entire header at once
         * and then parse the results out without using any function calls.
         * Uglier, but should be much faster.
         *
         * Note that some lines look a bit different, because the corresponding
         * fields or locals are long and so we need to do & 0xffffffffl to avoid
         * problems induced by sign extension.
         */

        byte[] hdrBuf = ler.hdrBuf;
        myReadFully(in, hdrBuf);

        long sig = (hdrBuf[0] & 0xff) | ((hdrBuf[1] & 0xff) << 8) |
            ((hdrBuf[2] & 0xff) << 16) | ((hdrBuf[3] << 24) & 0xffffffffL);
        if (sig != CENSIG) {
             throw new ZipException(Messages.getString("archive.3A"));
        }

        compressionMethod = (hdrBuf[10] & 0xff) | ((hdrBuf[11] & 0xff) << 8);
        time = (hdrBuf[12] & 0xff) | ((hdrBuf[13] & 0xff) << 8);
        modDate = (hdrBuf[14] & 0xff) | ((hdrBuf[15] & 0xff) << 8);
        crc = (hdrBuf[16] & 0xff) | ((hdrBuf[17] & 0xff) << 8)
                | ((hdrBuf[18] & 0xff) << 16)
                | ((hdrBuf[19] << 24) & 0xffffffffL);
        compressedSize = (hdrBuf[20] & 0xff) | ((hdrBuf[21] & 0xff) << 8)
                | ((hdrBuf[22] & 0xff) << 16)
                | ((hdrBuf[23] << 24) & 0xffffffffL);
        size = (hdrBuf[24] & 0xff) | ((hdrBuf[25] & 0xff) << 8)
                | ((hdrBuf[26] & 0xff) << 16)
                | ((hdrBuf[27] << 24) & 0xffffffffL);
        nameLen = (hdrBuf[28] & 0xff) | ((hdrBuf[29] & 0xff) << 8);
        int extraLen = (hdrBuf[30] & 0xff) | ((hdrBuf[31] & 0xff) << 8);
        int commentLen = (hdrBuf[32] & 0xff) | ((hdrBuf[33] & 0xff) << 8);
        mLocalHeaderRelOffset = (hdrBuf[42] & 0xff) | ((hdrBuf[43] & 0xff) << 8)
                | ((hdrBuf[44] & 0xff) << 16)
                | ((hdrBuf[45] << 24) & 0xffffffffL);

        byte[] nameBytes = new byte[nameLen];
        myReadFully(in, nameBytes);

        byte[] commentBytes = null;
        if (commentLen > 0) {
            commentBytes = new byte[commentLen];
            myReadFully(in, commentBytes);
        }

        if (extraLen > 0) {
            extra = new byte[extraLen];
            myReadFully(in, extra);
        }

        try {
            /*
             * The actual character set is "IBM Code Page 437".  As of
             * Sep 2006, the Zip spec (APPNOTE.TXT) supports UTF-8.  When
             * bit 11 of the GP flags field is set, the file name and
             * comment fields are UTF-8.
             *
             * TODO: add correct UTF-8 support.
             */
            name = new String(nameBytes, "ISO-8859-1");
            if (commentBytes != null) {
                comment = new String(commentBytes, "ISO-8859-1");
            } else {
                comment = null;
            }
        } catch (UnsupportedEncodingException uee) {
            throw new InternalError(uee.getMessage());
        }
    }

    private void myReadFully(InputStream in, byte[] b) throws IOException {
        int len = b.length;
        int off = 0;

        while (len > 0) {
            int count = in.read(b, off, len);
            if (count <= 0) {
                throw new EOFException();
            }
            off += count;
            len -= count;
        }
    }

    /*
     * Read a four-byte int in little-endian order.
     */
    static long readIntLE(RandomAccessFile raf) throws IOException {
        int b0 = raf.read();
        int b1 = raf.read();
        int b2 = raf.read();
        int b3 = raf.read();

        if (b3 < 0) {
            throw new EOFException(Messages.getString("archive.3B"));
        }
        return b0 | (b1 << 8) | (b2 << 16) | (b3 << 24); // ATTENTION: DOES SIGN EXTENSION: IS THIS WANTED?
    }

    static class LittleEndianReader {
        private byte[] b = new byte[4];
        byte[] hdrBuf = new byte[CENHDR];

        /*
         * Read a two-byte short in little-endian order.
         */
        int readShortLE(InputStream in) throws IOException {
            if (in.read(b, 0, 2) == 2) {
                return (b[0] & 0XFF) | ((b[1] & 0XFF) << 8);
            } else {
                throw new EOFException(Messages.getString("archive.3C"));
            }
        }

        /*
         * Read a four-byte int in little-endian order.
         */
        long readIntLE(InputStream in) throws IOException {
            if (in.read(b, 0, 4) == 4) {
                return (   ((b[0] & 0XFF))
                         | ((b[1] & 0XFF) << 8)
                         | ((b[2] & 0XFF) << 16)
                         | ((b[3] & 0XFF) << 24))
                       & 0XFFFFFFFFL; // Here for sure NO sign extension is wanted.
            } else {
                throw new EOFException(Messages.getString("archive.3D"));
            }
        }
    }
}
