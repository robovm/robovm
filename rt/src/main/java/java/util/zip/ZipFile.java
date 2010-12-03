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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Iterator;
import org.apache.harmony.archive.internal.nls.Messages;

/**
 * This class provides random read access to a <i>ZIP-archive</i> file.
 * <p>
 * While {@code ZipInputStream} provides stream based read access to a
 * <i>ZIP-archive</i>, this class implements more efficient (file based) access
 * and makes use of the <i>central directory</i> within a <i>ZIP-archive</i>.
 * <p>
 * Use {@code ZipOutputStream} if you want to create an archive.
 * <p>
 * A temporary ZIP file can be marked for automatic deletion upon closing it.
 *
 * @see ZipEntry
 * @see ZipOutputStream
 */
public class ZipFile implements ZipConstants {

    /**
     * Open ZIP file for read.
     */
    public static final int OPEN_READ = 1;

    /**
     * Delete ZIP file when closed.
     */
    public static final int OPEN_DELETE = 4;

    private final String fileName;

    private File fileToDeleteOnClose;

    private RandomAccessFile mRaf;

    private final ZipEntry.LittleEndianReader ler = new ZipEntry.LittleEndianReader();

    private final LinkedHashMap<String, ZipEntry> mEntries
            = new LinkedHashMap<String, ZipEntry>();

    /**
     * Constructs a new {@code ZipFile} with the specified file.
     *
     * @param file
     *            the file to read from.
     * @throws ZipException
     *             if a ZIP error occurs.
     * @throws IOException
     *             if an {@code IOException} occurs.
     */
    public ZipFile(File file) throws ZipException, IOException {
        this(file, OPEN_READ);
    }

    /**
     * Opens a file as <i>ZIP-archive</i>. "mode" must be {@code OPEN_READ} or
     * {@code OPEN_DELETE} . The latter sets the "delete on exit" flag through a
     * file.
     *
     * @param file
     *            the ZIP file to read.
     * @param mode
     *            the mode of the file open operation.
     * @throws IOException
     *             if an {@code IOException} occurs.
     */
    public ZipFile(File file, int mode) throws IOException {
        fileName = file.getPath();
        if (mode != OPEN_READ && mode != (OPEN_READ | OPEN_DELETE)) {
            throw new IllegalArgumentException();
        }

        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkRead(fileName);
        }
        if ((mode & OPEN_DELETE) != 0) {
            if (security != null) {
                security.checkDelete(fileName);
            }
            fileToDeleteOnClose = file; // file.deleteOnExit();
        } else {
            fileToDeleteOnClose = null;
        }

        mRaf = new RandomAccessFile(fileName, "r");

        readCentralDir();
    }

    /**
     * Opens a ZIP archived file.
     *
     * @param name
     *            the name of the ZIP file.
     * @throws IOException
     *             if an IOException occurs.
     */
    public ZipFile(String name) throws IOException {
        this(new File(name), OPEN_READ);
    }

    @Override
    protected void finalize() throws IOException {
        close();
    }

    /**
     * Closes this ZIP file. This method is idempotent.
     *
     * @throws IOException
     *             if an IOException occurs.
     */
    public void close() throws IOException {
        RandomAccessFile raf = mRaf;

        if (raf != null) { // Only close initialized instances
            synchronized(raf) {
                mRaf = null;
                raf.close();
            }
            if (fileToDeleteOnClose != null) {
                AccessController.doPrivileged(new PrivilegedAction<Object>() {
                    public Object run() {
                        new File(fileName).delete();
                        return null;
                    }
                });
                // fileToDeleteOnClose.delete();
                fileToDeleteOnClose = null;
            }
        }
    }

    private void checkNotClosed() {
        if (mRaf == null) {
            throw new IllegalStateException(Messages.getString("archive.36"));
        }
    }

    /**
     * Returns an enumeration of the entries. The entries are listed in the
     * order in which they appear in the ZIP archive.
     *
     * @return the enumeration of the entries.
     * @throws IllegalStateException if this ZIP file has been closed.
     */
    public Enumeration<? extends ZipEntry> entries() {
        checkNotClosed();
        final Iterator<ZipEntry> iterator = mEntries.values().iterator();

        return new Enumeration<ZipEntry>() {
            public boolean hasMoreElements() {
                checkNotClosed();
                return iterator.hasNext();
            }

            public ZipEntry nextElement() {
                checkNotClosed();
                return iterator.next();
            }
        };
    }

    /**
     * Gets the ZIP entry with the specified name from this {@code ZipFile}.
     *
     * @param entryName
     *            the name of the entry in the ZIP file.
     * @return a {@code ZipEntry} or {@code null} if the entry name does not
     *         exist in the ZIP file.
     * @throws IllegalStateException if this ZIP file has been closed.
     */
    public ZipEntry getEntry(String entryName) {
        checkNotClosed();
        if (entryName == null) {
            throw new NullPointerException();
        }

        ZipEntry ze = mEntries.get(entryName);
        if (ze == null) {
            ze = mEntries.get(entryName + "/");
        }
        return ze;
    }

    /**
     * Returns an input stream on the data of the specified {@code ZipEntry}.
     *
     * @param entry
     *            the ZipEntry.
     * @return an input stream of the data contained in the {@code ZipEntry}.
     * @throws IOException
     *             if an {@code IOException} occurs.
     * @throws IllegalStateException if this ZIP file has been closed.
     */
    public InputStream getInputStream(ZipEntry entry) throws IOException {
        /*
         * Make sure this ZipEntry is in this Zip file.  We run it through
         * the name lookup.
         */
        entry = getEntry(entry.getName());
        if (entry == null) {
            return null;
        }

        /*
         * Create a ZipInputStream at the right part of the file.
         */
        RandomAccessFile raf = mRaf;
        synchronized (raf) {
            // We don't know the entry data's start position. All we have is the
            // position of the entry's local header. At position 28 we find the
            // length of the extra data. In some cases this length differs from
            // the one coming in the central header.
            RAFStream rafstrm = new RAFStream(raf,
                    entry.mLocalHeaderRelOffset + 28);
            int localExtraLenOrWhatever = ler.readShortLE(rafstrm);
            // Skip the name and this "extra" data or whatever it is:
            rafstrm.skip(entry.nameLen + localExtraLenOrWhatever);
            rafstrm.mLength = rafstrm.mOffset + entry.compressedSize;
            if (entry.compressionMethod == ZipEntry.DEFLATED) {
                int bufSize = Math.max(1024, (int)Math.min(entry.getSize(), 65535L));
                return new ZipInflaterInputStream(rafstrm, new Inflater(true), bufSize, entry);
            } else {
                return rafstrm;
            }
        }
    }

    /**
     * Gets the file name of this {@code ZipFile}.
     *
     * @return the file name of this {@code ZipFile}.
     */
    public String getName() {
        return fileName;
    }

    /**
     * Returns the number of {@code ZipEntries} in this {@code ZipFile}.
     *
     * @return the number of entries in this file.
     * @throws IllegalStateException if this ZIP file has been closed.
     */
    public int size() {
        checkNotClosed();
        return mEntries.size();
    }

    /**
     * Find the central directory and read the contents.
     *
     * <p>The central directory can be followed by a variable-length comment
     * field, so we have to scan through it backwards.  The comment is at
     * most 64K, plus we have 18 bytes for the end-of-central-dir stuff
     * itself, plus apparently sometimes people throw random junk on the end
     * just for the fun of it.
     *
     * <p>This is all a little wobbly.  If the wrong value ends up in the EOCD
     * area, we're hosed. This appears to be the way that everybody handles
     * it though, so we're in good company if this fails.
     */
    private void readCentralDir() throws IOException {
        /*
         * Scan back, looking for the End Of Central Directory field.  If
         * the archive doesn't have a comment, we'll hit it on the first
         * try.
         *
         * No need to synchronize mRaf here -- we only do this when we
         * first open the Zip file.
         */
        long scanOffset = mRaf.length() - ENDHDR;
        if (scanOffset < 0) {
            throw new ZipException(Messages.getString("archive.37"));
        }

        long stopOffset = scanOffset - 65536;
        if (stopOffset < 0) {
            stopOffset = 0;
        }

        while (true) {
            mRaf.seek(scanOffset);
            if (ZipEntry.readIntLE(mRaf) == 101010256L) {
                break;
            }

            scanOffset--;
            if (scanOffset < stopOffset) {
                throw new ZipException(Messages.getString("archive.38"));
            }
        }

        /*
         * Found it, read the EOCD.
         *
         * For performance we want to use buffered I/O when reading the
         * file.  We wrap a buffered stream around the random-access file
         * object.  If we just read from the RandomAccessFile we'll be
         * doing a read() system call every time.
         */
        RAFStream rafs = new RAFStream(mRaf, mRaf.getFilePointer());
        BufferedInputStream bin = new BufferedInputStream(rafs, ENDHDR);

        int diskNumber = ler.readShortLE(bin);
        int diskWithCentralDir = ler.readShortLE(bin);
        int numEntries = ler.readShortLE(bin);
        int totalNumEntries = ler.readShortLE(bin);
        /*centralDirSize =*/ ler.readIntLE(bin);
        long centralDirOffset = ler.readIntLE(bin);
        /*commentLen =*/ ler.readShortLE(bin);

        if (numEntries != totalNumEntries ||
            diskNumber != 0 ||
            diskWithCentralDir != 0) {
            throw new ZipException(Messages.getString("archive.39"));
        }

        /*
         * Seek to the first CDE and read all entries.
         * However, when Z_SYNC_FLUSH is used the offset may not point directly
         * to the CDE so skip over until we find it. 
         * At most it will be 6 bytes away (one or two bytes for empty block, 4 bytes for
         * empty block signature).  
         */
        scanOffset = centralDirOffset;
        stopOffset = scanOffset + 6;
        
        while (true) {
            mRaf.seek(scanOffset);
            if (ZipEntry.readIntLE(mRaf) == CENSIG) {
                break;
            }

            scanOffset++;
            if (scanOffset > stopOffset) {
                throw new ZipException(Messages.getString("archive.3A"));
            }
        }
        
        // If CDE is found then go and read all the entries
        rafs = new RAFStream(mRaf, scanOffset);
        bin = new BufferedInputStream(rafs, 4096);
        for (int i = 0; i < numEntries; i++) {
            ZipEntry newEntry = new ZipEntry(ler, bin);
            mEntries.put(newEntry.getName(), newEntry);
        }
    }

    /**
     * Wrap a stream around a RandomAccessFile.  The RandomAccessFile is shared
     * among all streams returned by getInputStream(), so we have to synchronize
     * access to it.  (We can optimize this by adding buffering here to reduce
     * collisions.)
     *
     * <p>We could support mark/reset, but we don't currently need them.
     */
    static class RAFStream extends InputStream {

        RandomAccessFile mSharedRaf;
        long mOffset;
        long mLength;

        public RAFStream(RandomAccessFile raf, long pos) throws IOException {
            mSharedRaf = raf;
            mOffset = pos;
            mLength = raf.length();
        }

        @Override
        public int available() throws IOException {
            if (mLength > mOffset) {
                if (mLength - mOffset < Integer.MAX_VALUE) {
                    return (int)(mLength - mOffset);
                } else {
                    return Integer.MAX_VALUE;
                }
            } else {
                return 0;
            }
        }

        @Override
        public int read() throws IOException {
            byte[] singleByteBuf = new byte[1];
            if (read(singleByteBuf, 0, 1) == 1) {
                return singleByteBuf[0] & 0XFF;
            } else {
                return -1;
            }
        }

        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            synchronized (mSharedRaf) {
                mSharedRaf.seek(mOffset);
                if (len > mLength - mOffset) {
                    len = (int) (mLength - mOffset);
                }
                int count = mSharedRaf.read(b, off, len);
                if (count > 0) {
                    mOffset += count;
                    return count;
                } else {
                    return -1;
                }
            }
        }

        @Override
        public long skip(long n) throws IOException {
            if (n > mLength - mOffset) {
                n = mLength - mOffset;
            }
            mOffset += n;
            return n;
        }
    }
    
    static class ZipInflaterInputStream extends InflaterInputStream {

        ZipEntry entry;
        long bytesRead = 0;

        public ZipInflaterInputStream(InputStream is, Inflater inf, int bsize, ZipEntry entry) {
            super(is, inf, bsize);
            this.entry = entry;
        }

        @Override
        public int read(byte[] buffer, int off, int nbytes) throws IOException {
            int i = super.read(buffer, off, nbytes);
            if (i != -1) {
                bytesRead += i;
            }
            return i;
        }

        @Override
        public int available() throws IOException {
            return super.available() == 0 ? 0 : (int) (entry.getSize() - bytesRead);
        }
    }
}
