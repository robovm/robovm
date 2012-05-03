/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package libcore.io;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.nio.NioUtils;
import java.nio.channels.FileChannel;
import libcore.io.ErrnoException;
import libcore.io.Libcore;
import libcore.io.Memory;
import static libcore.io.OsConstants.*;

/**
 * A memory-mapped file. Use {@link #mmap} to map a file, {@link #close} to unmap a file,
 * and either {@link #bigEndianIterator} or {@link #littleEndianIterator} to get a seekable
 * {@link BufferIterator} over the mapped data.
 */
public final class MemoryMappedFile implements AutoCloseable {
    private long address;
    private final long size;

    /**
     * Use this if you've called {@code mmap} yourself.
     */
    public MemoryMappedFile(long address, long size) {
        this.address = address;
        this.size = size;
    }

    /**
     * Use this to mmap the whole file read-only.
     */
    public static MemoryMappedFile mmapRO(String path) throws ErrnoException {
        FileDescriptor fd = Libcore.os.open(path, O_RDONLY, 0);
        long size = Libcore.os.fstat(fd).st_size;
        long address = Libcore.os.mmap(0L, size, PROT_READ, MAP_SHARED, fd, 0);
        Libcore.os.close(fd);
        return new MemoryMappedFile(address, size);
    }

    /**
     * Unmaps this memory-mapped file using munmap(2). This is a no-op if close has already been
     * called. Note that this class does <i>not</i> use finalization; you must call {@code close}
     * yourself.
     *
     * Calling this method invalidates any iterators over this {@code MemoryMappedFile}. It is an
     * error to use such an iterator after calling {@code close}.
     */
    public synchronized void close() throws ErrnoException {
        if (address != 0) {
            Libcore.os.munmap(address, size);
            address = 0;
        }
    }

    /**
     * Returns a new iterator that treats the mapped data as big-endian.
     */
    public BufferIterator bigEndianIterator() {
        return new NioBufferIterator((int) address, (int) size, ByteOrder.nativeOrder() != ByteOrder.BIG_ENDIAN);
    }

    /**
     * Returns a new iterator that treats the mapped data as little-endian.
     */
    public BufferIterator littleEndianIterator() {
        return new NioBufferIterator((int) address, (int) size, ByteOrder.nativeOrder() != ByteOrder.LITTLE_ENDIAN);
    }

    /**
     * Returns the size in bytes of the memory-mapped region.
     */
    public long size() {
        return size;
    }
}
