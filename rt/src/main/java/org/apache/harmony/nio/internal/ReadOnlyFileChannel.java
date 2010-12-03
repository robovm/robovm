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
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.NonWritableChannelException;
import java.nio.channels.ReadableByteChannel;

import org.apache.harmony.luni.platform.IMemorySystem;

public final class ReadOnlyFileChannel extends FileChannelImpl {
    public ReadOnlyFileChannel(Object stream, long handle) {
        super(stream, handle);
    }

    public final int write(ByteBuffer buffer, long position) throws IOException {
        if (null == buffer) {
            throw new NullPointerException();
        }
        if (position < 0) {
            throw new IllegalArgumentException();
        }
        throw new NonWritableChannelException();
    }

    public final int write(ByteBuffer buffer) throws IOException {
        openCheck();
        throw new NonWritableChannelException();
    }

    public final long write(ByteBuffer[] buffers, int offset, int length)
            throws IOException {
        if (offset < 0 || length < 0 || (offset + length) > buffers.length) {
            throw new IndexOutOfBoundsException();
        }
        openCheck();
        throw new NonWritableChannelException();
    }

    public final FileChannel truncate(long size) throws IOException {
        openCheck();
        if (size < 0) {
            throw new IllegalArgumentException();
        }
        throw new NonWritableChannelException();
    }

    public final long transferFrom(ReadableByteChannel src, long position,
            long count) throws IOException {
        openCheck();
        if (!src.isOpen()) {
            throw new ClosedChannelException();
        }
        throw new NonWritableChannelException();
    }

    public final MappedByteBuffer map(MapMode mode, long position, long size)
            throws IOException {
        openCheck();
        if (mode == null) {
            throw new NullPointerException();
        }
        if (position < 0 || size < 0 || size > Integer.MAX_VALUE) {
            throw new IllegalArgumentException();
        }
        if (mode != MapMode.READ_ONLY) {
            throw new NonWritableChannelException();
        }
        return super.mapImpl(IMemorySystem.MMAP_READ_ONLY, position, size);
    }

    public final void force(boolean metadata) throws IOException {
        openCheck();
        return;
    }

    protected final FileLock basicLock(long position, long size,
            boolean shared, boolean wait) throws IOException {
        if (!shared) {
            throw new NonWritableChannelException();
        }
        return super.basicLock(position, size, shared, true);
    }
}
