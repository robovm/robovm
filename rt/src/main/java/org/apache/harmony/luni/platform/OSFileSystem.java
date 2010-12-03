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

package org.apache.harmony.luni.platform;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * This is the portable implementation of the file system interface.
 * 
 */
class OSFileSystem implements IFileSystem {

    static {
        oneTimeInitializationImpl();
    }

    private static final OSFileSystem singleton = new OSFileSystem();

    public static OSFileSystem getOSFileSystem() {
        return singleton;
    }

    private OSFileSystem() {
        super();
    }

    private native static void oneTimeInitializationImpl();

    private final void validateLockArgs(int type, long start, long length) {
        if ((type != IFileSystem.SHARED_LOCK_TYPE)
                && (type != IFileSystem.EXCLUSIVE_LOCK_TYPE)) {
            throw new IllegalArgumentException("Illegal lock type requested."); //$NON-NLS-1$
        }

        // Start position
        if (start < 0) {
            throw new IllegalArgumentException(
                    "Lock start position must be non-negative"); //$NON-NLS-1$
        }

        // Length of lock stretch
        if (length < 0) {
            throw new IllegalArgumentException(
                    "Lock length must be non-negative"); //$NON-NLS-1$
        }
    }

    private native int lockImpl(long fileDescriptor, long start, long length,
            int type, boolean wait);

    /**
     * Returns the granularity for virtual memory allocation.
     * Note that this value for Windows differs from the one for the
     * page size (64K and 4K respectively).
     */
    public native int getAllocGranularity() throws IOException;

    public boolean lock(long fileDescriptor, long start, long length, int type,
            boolean waitFlag) throws IOException {
        // Validate arguments
        validateLockArgs(type, start, length);
        int result = lockImpl(fileDescriptor, start, length, type, waitFlag);
        return result != -1;
    }

    private native int unlockImpl(long fileDescriptor, long start, long length);

    public void unlock(long fileDescriptor, long start, long length)
            throws IOException {
        // Validate arguments
        validateLockArgs(IFileSystem.SHARED_LOCK_TYPE, start, length);
        int result = unlockImpl(fileDescriptor, start, length);
        if (result == -1) {
            throw new IOException();
        }
    }

    private native int fflushImpl(long fd, boolean metadata);

    public void fflush(long fileDescriptor, boolean metadata)
            throws IOException {
        int result = fflushImpl(fileDescriptor, metadata);
        if (result == -1) {
            throw new IOException();
        }
    }

    /*
     * File position seeking.
     */

    private native long seekImpl(long fd, long offset, int whence);

    public long seek(long fileDescriptor, long offset, int whence)
            throws IOException {
        long pos = seekImpl(fileDescriptor, offset, whence);
        if (pos == -1) {
            throw new IOException();
        }
        return pos;
    }

    /*
     * Direct read/write APIs work on addresses.
     */
    private native long readDirectImpl(long fileDescriptor, long address,
            int offset, int length);

    public long readDirect(long fileDescriptor, long address, int offset,
            int length) throws IOException {
        long bytesRead = readDirectImpl(fileDescriptor, address, offset, length);
        if (bytesRead < -1) {
            throw new IOException();
        }
        return bytesRead;
    }

    private native long writeDirectImpl(long fileDescriptor, long address,
            int offset, int length);

    public long writeDirect(long fileDescriptor, long address, int offset,
            int length) throws IOException {
        long bytesWritten = writeDirectImpl(fileDescriptor, address, offset,
                length);
        if (bytesWritten < 0) {
            throw new IOException();
        }
        return bytesWritten;
    }

    /*
     * Indirect read/writes work on byte[]'s
     */
    private native long readImpl(long fileDescriptor, byte[] bytes, int offset,
            int length);

    public long read(long fileDescriptor, byte[] bytes, int offset, int length)
            throws IOException {
        if (bytes == null) {
            throw new NullPointerException();
        }
        long bytesRead = readImpl(fileDescriptor, bytes, offset, length);
        if (bytesRead < -1) {
                        /*
                         * TODO: bytesRead is never less than -1 so this code
                         * does nothing?
                         * The native code throws an exception in only one case
                         * so perhaps this should be 'bytesRead < 0' to handle
                         * any other cases.  But the other cases have been
                         * ignored until now so fixing this could break things
                         */
            throw new IOException();
        }
        return bytesRead;
    }

    private native long writeImpl(long fileDescriptor, byte[] bytes,
            int offset, int length);

    public long write(long fileDescriptor, byte[] bytes, int offset, int length)
            throws IOException {
        long bytesWritten = writeImpl(fileDescriptor, bytes, offset, length);
        if (bytesWritten < 0) {
            throw new IOException();
        }
        return bytesWritten;
    }

    /*
     * Scatter/gather calls.
     */
    public long readv(long fileDescriptor, long[] addresses, int[] offsets,
            int[] lengths, int size) throws IOException {
        long bytesRead = readvImpl(fileDescriptor, addresses, offsets, lengths,
                size);
        if (bytesRead < -1) {
            throw new IOException();
        }
        return bytesRead;
    }

    private native long readvImpl(long fileDescriptor, long[] addresses,
            int[] offsets, int[] lengths, int size);

    public native long writev(long fileDescriptor, Object[] buffers,
            int[] offsets, int[] lengths, int size) throws IOException;

    private native int closeImpl(long fileDescriptor);

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.harmony.luni.platform.IFileSystem#close(long)
     */
    public void close(long fileDescriptor) throws IOException {
        int rc = closeImpl(fileDescriptor);
        if (rc == -1) {
            throw new IOException();
        }
    }

    public void truncate(long fileDescriptor, long size) throws IOException {
        int rc = truncateImpl(fileDescriptor, size);
        if (rc < 0) {
            throw new IOException();
        }
    }

    private native int truncateImpl(long fileDescriptor, long size);

    public long open(byte[] fileName, int mode) throws FileNotFoundException {
        if (fileName == null) {
            throw new NullPointerException();
        }
        long handler = openImpl(fileName, mode);
        if (handler < 0) {
                    try {
                        throw new FileNotFoundException(new String(fileName, "UTF-8"));
                    } catch (java.io.UnsupportedEncodingException e) {
                        // UTF-8 should always be supported, so throw an assertion
                        FileNotFoundException fnfe = new FileNotFoundException(new String(fileName));
                        e.initCause(fnfe);
                        throw new AssertionError(e);
                    }
        }
        return handler;
    }

    private native long openImpl(byte[] fileName, int mode);

    public long transfer(long fileHandler, FileDescriptor socketDescriptor,
            long offset, long count) throws IOException {
        long result = transferImpl(fileHandler, socketDescriptor, offset, count);
        if (result < 0)
            throw new IOException();
        return result;
    }

    private native long transferImpl(long fileHandler,
            FileDescriptor socketDescriptor, long offset, long count);

    public long ttyAvailable() throws IOException {
        long nChar = ttyAvailableImpl();
        if (nChar < 0) {
            throw new IOException();
        }
        return nChar;
    }

    private native long ttyAvailableImpl();

    public long available(long fileDescriptor) throws IOException {
        long nChar = availableImpl(fileDescriptor);
        if (nChar < 0) {
            throw new IOException();
        }
        return nChar;
    }

    private native long availableImpl(long fileDescriptor);

    public long size(long fileDescriptor) throws IOException {
        long nChar = sizeImpl(fileDescriptor);
        if (nChar < 0) {
            throw new IOException();
        }
        return nChar;
    }

    private native long sizeImpl(long fileDescriptor);

    public long ttyRead(byte[] bytes, int offset, int length) throws IOException {
        long nChar = ttyReadImpl(bytes, offset, length);
        if (nChar < 0) {
            throw new IOException();
        }
        return nChar;
    }

    private native long ttyReadImpl(byte[] bytes, int offset, int length);
}
