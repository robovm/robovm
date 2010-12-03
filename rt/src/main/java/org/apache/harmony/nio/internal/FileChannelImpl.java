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

import java.io.Closeable;
import java.io.FileDescriptor;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.NonWritableChannelException;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

import org.apache.harmony.luni.platform.IFileSystem;
import org.apache.harmony.luni.platform.Platform;
import org.apache.harmony.luni.platform.PlatformAddress;
import org.apache.harmony.luni.platform.PlatformAddressFactory;
import org.apache.harmony.nio.internal.nls.Messages;

/*
 * The file channel impl class is the bridge between the logical channels
 * described by the NIO channel framework, and the file system implementation
 * provided by the port layer.
 * 
 * This class is non-API, but implements the API of the FileChannel interface.
 */
public abstract class FileChannelImpl extends FileChannel {

    // Reference to the portable file system code.
    private static final IFileSystem fileSystem = Platform.getFileSystem();

    private static final int ALLOC_GRANULARITY;

    static {
        try {
            ALLOC_GRANULARITY = fileSystem.getAllocGranularity();
        } catch (IOException e) {
            throw new Error(e);
        }

    }

    // Handle to the open file
    private final long handle;

    // The object that will track all outstanding locks on this channel.
    private final LockManager lockManager = new LockManager();

    private static class RepositioningLock {}
    private final Object repositioningLock = new RepositioningLock();

    private final Object stream;

    /*
     * Create a new file channel implementation class that wraps the given file
     * handle and operates in the specified mode.
     * 
     */
    public FileChannelImpl(Object stream, long handle) {
        super();
        this.handle = handle;
        this.stream = stream;
    }

    /*
     * Helper method to throw an exception if the channel is already closed.
     * Note that we don't bother to synchronize on this test since the file may
     * be closed by operations beyond our control anyways.
     */
    protected final void openCheck() throws ClosedChannelException {
        if (!isOpen()) {
            throw new ClosedChannelException();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.nio.channels.spi.AbstractInterruptibleChannel#implCloseChannel()
     */
    protected void implCloseChannel() throws IOException {
        if (stream instanceof Closeable) {
            ((Closeable) stream).close();
        }
    }

    protected FileLock basicLock(long position, long size, boolean shared,
            boolean wait) throws IOException {
        if ((position < 0) || (size < 0)) {
            // nio.0A=Lock position and size must be non-negative.
            throw new IllegalArgumentException(Messages.getString("nio.0A")); //$NON-NLS-1$
        }
        int lockType = shared ? IFileSystem.SHARED_LOCK_TYPE
                : IFileSystem.EXCLUSIVE_LOCK_TYPE;
        FileLock pendingLock = new FileLockImpl(this, position, size, shared);
        lockManager.addLock(pendingLock);

        if (fileSystem.lock(handle, position, size, lockType, wait)) {
            return pendingLock;
        }

        // Lock acquisition failed
        lockManager.removeLock(pendingLock);
        return null;
    }

    /*
     * Acquire a lock on the receiver, blocks if the lock cannot be obtained
     * immediately.
     * 
     * @see java.nio.channels.FileChannel#lock(long, long, boolean)
     */
    public final FileLock lock(long position, long size, boolean shared)
            throws IOException {
        openCheck();
        FileLock resultLock = null;
        {
            boolean completed = false;
            try {
                begin();
                resultLock = basicLock(position, size, shared, true);
                completed = true;
            } finally {
                end(completed);
            }
        }
        return resultLock;
    }

    /*
     * Attempts to acquire the given lock, but does not block. If the lock
     * cannot be acquired the method returns null.
     * 
     * @see java.nio.channels.FileChannel#tryLock(long, long, boolean)
     */
    public final FileLock tryLock(long position, long size, boolean shared)
            throws IOException {
        openCheck();
        return basicLock(position, size, shared, false);
    }

    /*
     * Non-API method to release a given lock on a file channel. Assumes that
     * the lock will mark itself invalid after successful unlocking.
     */
    void release(FileLock lock) throws IOException {
        openCheck();
        fileSystem.unlock(handle, lock.position(), lock.size());
        lockManager.removeLock(lock);
    }

    /*
     * Flush the contents of the file to disk, and the metadata if asked.
     */
    public void force(boolean metadata) throws IOException {
        openCheck();
        // Forcing data-only on a read-only file is a no-op.
        if (metadata) {
            fileSystem.fflush(handle, metadata);
        }
    }

    public abstract MappedByteBuffer map(MapMode mode, long position, long size)
            throws IOException;

    protected final MappedByteBuffer mapImpl(int mapMode, long position,
            long size) throws IOException {
        if (position + size > size()) {
            fileSystem.truncate(handle, position + size);
        }
        long alignment = position - position % ALLOC_GRANULARITY;
        int offset = (int) (position - alignment);
        PlatformAddress address = PlatformAddressFactory.allocMap(handle,
                alignment, size + offset, mapMode);
        MappedByteBuffer buffer = null;
        try {
            buffer = MappedByteBufferFactory.getBuffer(address, mapMode, size,
                    offset);
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
        return buffer;
    }

    /*
     * Answers the current file position.
     */
    public long position() throws IOException {
        openCheck();
        return fileSystem.seek(handle, 0L, IFileSystem.SEEK_CUR);
    }

    /*
     * Sets the file pointer.
     */
    public FileChannel position(long newPosition) throws IOException {
        openCheck();
        if (newPosition < 0) {
            // nio.0B=New position must be non-negative.
            throw new IllegalArgumentException(Messages.getString("nio.0B")); //$NON-NLS-1$
        }

        synchronized (repositioningLock) {
            fileSystem.seek(handle, newPosition, IFileSystem.SEEK_SET);
        }
        return this;
    }

    public int read(ByteBuffer buffer, long position) throws IOException {
        if (null == buffer) {
            throw new NullPointerException();
        }
        if (position < 0) {
            throw new IllegalArgumentException();
        }
        openCheck();
        if (!buffer.hasRemaining()) {
            return 0;
        }
        synchronized (repositioningLock) {
            int bytesRead = 0;
            long preReadPosition = position();
            position(position);
            try {
                bytesRead = read(buffer);
            } finally {
                position(preReadPosition);
            }
            return bytesRead;
        }
    }

    public int read(ByteBuffer buffer) throws IOException {
        openCheck();
        if (!buffer.hasRemaining()) {
            return 0;
        }
        boolean completed = false;
        int bytesRead = 0;
        synchronized (repositioningLock) {
            if (buffer.isDirect()) {
                DirectBuffer directBuffer = (DirectBuffer) buffer;
                long address = directBuffer.getEffectiveAddress().toLong();
                try {
                    begin();
                    /*
                     * if (bytesRead <= EOF) dealt by read completed = false;
                     */
                    bytesRead = (int) fileSystem.readDirect(handle, address,
                            buffer.position(), buffer.remaining());
                    completed = true;
                } finally {
                    end(completed && bytesRead >= 0);
                }
            } else {
                try {
                    begin();
                    /*
                     * if (bytesRead <= EOF) dealt by read completed = false;
                     */
                    bytesRead = (int) fileSystem.read(handle, buffer.array(),
                            buffer.arrayOffset() + buffer.position(), buffer
                                    .remaining());
                    completed = true;
                } finally {
                    end(completed && bytesRead >= 0);
                }
            }
            if (bytesRead > 0) {
                buffer.position(buffer.position() + bytesRead);
            }
        }
        return bytesRead;
    }

    public long read(ByteBuffer[] buffers, int offset, int length)
            throws IOException {
        int count = 0;
        if (offset < 0 || length < 0 || offset + length > buffers.length) {
            throw new IndexOutOfBoundsException();
        }
        openCheck();
        if (calculateTotalRemaining(buffers, offset, length) == 0) {
            return 0;
        }
        ByteBuffer[] directBuffers = new ByteBuffer[length];
        long[] handles = new long[length];
        int[] offsets = new int[length];
        int[] lengths = new int[length];
        for (int i = 0; i < length; i++) {
            ByteBuffer buffer = buffers[i + offset];
            if (!buffer.isDirect()) {
                buffer = ByteBuffer.allocateDirect(buffer.remaining());
                directBuffers[i] = buffer;
                offsets[i] = 0;
            } else {
                offsets[i] = buffer.position();
            }
            handles[i] = ((DirectBuffer) buffer).getEffectiveAddress().toLong();
            lengths[i] = buffer.remaining();
        }
        long bytesRead = 0;
        {
            boolean completed = false;
            try {
                begin();
                synchronized (repositioningLock) {
                    bytesRead = fileSystem.readv(handle, handles, offsets,
                            lengths, length);

                }
                completed = true;
                /*
                 * if (bytesRead < EOF) //dealt by readv? completed = false;
                 */
            } finally {
                end(completed);
            }
        }
        int end = offset + length;
        long bytesRemaining = bytesRead;
        for (int i = offset; i < end && bytesRemaining > 0; i++) {
            if (buffers[i].isDirect()) {
                if (lengths[i] < bytesRemaining) {
                    int pos = buffers[i].limit();
                    buffers[i].position(pos);
                    bytesRemaining -= lengths[i];
                } else {
                    int pos = (int) bytesRemaining;
                    buffers[i].position(pos);
                    break;
                }
            } else {
                ByteBuffer buf = directBuffers[i - offset];
                if (bytesRemaining < buf.remaining()) {
                    // this is the last step.
                    int pos = buf.position();
                    buffers[i].put(buf);
                    buffers[i].position(pos + (int) bytesRemaining);
                    bytesRemaining = 0;
                } else {
                    bytesRemaining -= buf.remaining();
                    buffers[i].put(buf);
                }
            }
        }
        return bytesRead;
    }

    /*
     * Answers the current file size, as an integer number of bytes.
     */
    public long size() throws IOException {
        openCheck();
        return fileSystem.size(handle);
    }

    public long transferFrom(ReadableByteChannel src, long position, long count)
            throws IOException {
        openCheck();
        if (!src.isOpen()) {
            throw new ClosedChannelException();
        }
        if (position < 0 || count < 0) {
            throw new IllegalArgumentException();
        }
        if (position > size()) {
            return 0;
        }

        ByteBuffer buffer = null;

        try {
            if (src instanceof FileChannel) {
                FileChannel fileSrc = (FileChannel) src;
                long size = fileSrc.size();
                long filePosition = fileSrc.position();
                count = Math.min(count, size - filePosition);
                buffer = fileSrc.map(MapMode.READ_ONLY, filePosition, count);
                fileSrc.position(filePosition + count);
            } else {
                buffer = ByteBuffer.allocateDirect((int) count);
                src.read(buffer);
                buffer.flip();
            }
            return write(buffer, position);
        } finally {
            // unmap the buffer
            if (buffer != null) {
                // all children of FileChannelImpl currently returns
                // an instance of DirectBuffer from map() method
                ((DirectBuffer) buffer).free();
            }
        }
    }

    public long transferTo(long position, long count, WritableByteChannel target)
            throws IOException {
        openCheck();
        if (!target.isOpen()) {
            throw new ClosedChannelException();
        }
        if (target instanceof ReadOnlyFileChannel) {
            throw new NonWritableChannelException();
        }
        if (position < 0 || count < 0) {
            throw new IllegalArgumentException();
        }

        if (count == 0 || position >= size()) {
            return 0;
        }
        ByteBuffer buffer = null;
        count = Math.min(count, size() - position);
        if (target instanceof SocketChannelImpl) {
            // only socket can be transfered by system call
            return kernelTransfer(handle, ((SocketChannelImpl) target).getFD(),
                    position, count);
        }

        try {
            buffer = map(MapMode.READ_ONLY, position, count);
            return target.write(buffer);
        } finally {
            // unmap the buffer
            if (buffer != null) {
                // all children of FileChannelImpl currently returns
                // an instance of DirectBuffer from map() method
                ((DirectBuffer) buffer).free();
            }
        }
    }

    private long kernelTransfer(long l, FileDescriptor fd, long position,
            long count) throws IOException {
        boolean completed = false;
        try {
            begin();
            long ret = fileSystem.transfer(l, fd, position, count);
            completed = true;
            return ret;
        } finally {
            end(completed);
        }
    }

    public FileChannel truncate(long size) throws IOException {
        openCheck();
        if (size < 0) {
            throw new IllegalArgumentException();
        }
        if (size < size()) {
            synchronized (repositioningLock) {
                long position = position();
                fileSystem.truncate(handle, size);
                /*
                 * FIXME: currently the port library always modifies the
                 * position to given size. not sure it is a bug or intended
                 * behaviour, so I always reset the position to proper value as
                 * Java Spec.
                 */
                position(position > size ? size : position);
            }
        }
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.nio.channels.WritableByteChannel#write(java.nio.ByteBuffer)
     */

    public int write(ByteBuffer buffer, long position) throws IOException {
        if (null == buffer) {
            throw new NullPointerException();
        }
        if (position < 0) {
            throw new IllegalArgumentException();
        }
        openCheck();
        if (!buffer.hasRemaining()) {
            return 0;
        }
        int bytesWritten = 0;
        synchronized (repositioningLock) {
            long preWritePosition = position();
            position(position);
            try {
                bytesWritten = writeImpl(buffer);
            } finally {
                position(preWritePosition);
            }
        }
        return bytesWritten;
    }

    public int write(ByteBuffer buffer) throws IOException {
        openCheck();
        return writeImpl(buffer);
    }

    private int writeImpl(ByteBuffer buffer) throws IOException {
        int bytesWritten;
        boolean completed = false;
        synchronized (repositioningLock) {
            if (buffer.isDirect()) {
                DirectBuffer directBuffer = (DirectBuffer) buffer;
                long address = directBuffer.getEffectiveAddress().toLong();
                try {
                    begin();
                    bytesWritten = (int) fileSystem.writeDirect(handle,
                            address, buffer.position(), buffer.remaining());
                    completed = true;
                } finally {
                    end(completed);
                }
            } else {
                try {
                    begin();
                    bytesWritten = (int) fileSystem.write(handle, buffer
                            .array(), buffer.arrayOffset() + buffer.position(),
                            buffer.remaining());
                    completed = true;
                } finally {
                    end(completed);
                }
            }
            if (bytesWritten > 0) {
                buffer.position(buffer.position() + bytesWritten);
            }
        }
        return bytesWritten;
    }

    public long write(ByteBuffer[] sources, int offset, int length)
            throws IOException {
        if (offset < 0 || length < 0 || (offset + length) > sources.length) {
            throw new IndexOutOfBoundsException();
        }
        openCheck();
        if (calculateTotalRemaining(sources, offset, length) == 0) {
            return 0;
        }
        Object[] src = new Object[length];
        int[] offsets = new int[length];
        int[] lengths = new int[length];
        for (int i = 0; i < length; i++) {
            ByteBuffer buffer = sources[i + offset];
            if (!buffer.isDirect()) {
                if (buffer.hasArray()) {
                    src[i] = buffer.array();
                    offsets[i] = buffer.position();
                } else {
                    ByteBuffer directBuffer = ByteBuffer.allocateDirect(buffer
                            .remaining());
                    int oldPosition = buffer.position();
                    directBuffer.put(buffer);
                    buffer.position(oldPosition);
                    directBuffer.flip();
                    src[i] = directBuffer;
                    offsets[i] = 0;
                }
            } else {
                src[i] = buffer;
                offsets[i] = buffer.position();
            }
            lengths[i] = buffer.remaining();
        }

        long bytesWritten = 0;
        boolean completed = false;
        synchronized (repositioningLock) {
            try {
                begin();
                bytesWritten = fileSystem.writev(handle, src, offsets,
                        lengths, length);
                completed = true;
            } finally {
                end(completed);
            }
        }

        long bytesRemaining = bytesWritten;
        for (int i = offset; i < length + offset; i++) {
            if (bytesRemaining > sources[i].remaining()) {
                int pos = sources[i].limit();
                bytesRemaining -= sources[i].remaining();
                sources[i].position(pos);
            } else {
                int pos = sources[i].position() + (int) bytesRemaining;
                sources[i].position(pos);
                break;
            }
        }
        return bytesWritten;
    }

    public long getHandle() {
        return handle;
    }

    private int calculateTotalRemaining(ByteBuffer[] buffers, int offset,
            int length) {
        int count = 0;
        for (int i = offset; i < offset + length; i++) {
            count += buffers[i].remaining();
        }
        return count;
    }
}
