/*
 * Copyright (C) 2011 The Android Open Source Project
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

import dalvik.system.BlockGuard;
import dalvik.system.SocketTagger;
import java.io.FileDescriptor;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import static libcore.io.OsConstants.*;

/**
 * Informs BlockGuard of any activity it should be aware of.
 */
public class BlockGuardOs extends ForwardingOs {
    public BlockGuardOs(Os os) {
        super(os);
    }

    private FileDescriptor tagSocket(FileDescriptor fd) throws ErrnoException {
        try {
            SocketTagger.get().tag(fd);
            return fd;
        } catch (SocketException e) {
            throw new ErrnoException("socket", EINVAL, e);
        }
    }

    private void untagSocket(FileDescriptor fd) throws ErrnoException {
        try {
            SocketTagger.get().untag(fd);
        } catch (SocketException e) {
            throw new ErrnoException("socket", EINVAL, e);
        }
    }

    @Override public FileDescriptor accept(FileDescriptor fd, InetSocketAddress peerAddress) throws ErrnoException {
        BlockGuard.getThreadPolicy().onNetwork();
        return tagSocket(os.accept(fd, peerAddress));
    }

    @Override public void close(FileDescriptor fd) throws ErrnoException {
        try {
            if (S_ISSOCK(Libcore.os.fstat(fd).st_mode)) {
                if (isLingerSocket(fd)) {
                    // If the fd is a socket with SO_LINGER set, we might block indefinitely.
                    // We allow non-linger sockets so that apps can close their network
                    // connections in methods like onDestroy which will run on the UI thread.
                    BlockGuard.getThreadPolicy().onNetwork();
                }
                untagSocket(fd);
            }
        } catch (ErrnoException ignored) {
            // We're called via Socket.close (which doesn't ask for us to be called), so we
            // must not throw here, because Socket.close must not throw if asked to close an
            // already-closed socket. Also, the passed-in FileDescriptor isn't necessarily
            // a socket at all.
        }
        os.close(fd);
    }

    private static boolean isLingerSocket(FileDescriptor fd) throws ErrnoException {
        StructLinger linger = Libcore.os.getsockoptLinger(fd, SOL_SOCKET, SO_LINGER);
        return linger.isOn() && linger.l_linger > 0;
    }

    @Override public void connect(FileDescriptor fd, InetAddress address, int port) throws ErrnoException {
        BlockGuard.getThreadPolicy().onNetwork();
        os.connect(fd, address, port);
    }

    // TODO: Untag newFd when needed for dup2(FileDescriptor oldFd, int newFd)

    @Override public void fdatasync(FileDescriptor fd) throws ErrnoException {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        os.fdatasync(fd);
    }

    @Override public void fsync(FileDescriptor fd) throws ErrnoException {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        os.fsync(fd);
    }

    @Override public void ftruncate(FileDescriptor fd, long length) throws ErrnoException {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        os.ftruncate(fd, length);
    }

    @Override public FileDescriptor open(String path, int flags, int mode) throws ErrnoException {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        if ((mode & O_ACCMODE) != O_RDONLY) {
            BlockGuard.getThreadPolicy().onWriteToDisk();
        }
        return os.open(path, flags, mode);
    }

    @Override public int poll(StructPollfd[] fds, int timeoutMs) throws ErrnoException {
        // Greater than 0 is a timeout in milliseconds and -1 means "block forever",
        // but 0 means "poll and return immediately", which shouldn't be subject to BlockGuard.
        if (timeoutMs != 0) {
            BlockGuard.getThreadPolicy().onNetwork();
        }
        return os.poll(fds, timeoutMs);
    }

    @Override public int pread(FileDescriptor fd, ByteBuffer buffer, long offset) throws ErrnoException {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        return os.pread(fd, buffer, offset);
    }

    @Override public int pread(FileDescriptor fd, byte[] bytes, int byteOffset, int byteCount, long offset) throws ErrnoException {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        return os.pread(fd, bytes, byteOffset, byteCount, offset);
    }

    @Override public int pwrite(FileDescriptor fd, ByteBuffer buffer, long offset) throws ErrnoException {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        return os.pwrite(fd, buffer, offset);
    }

    @Override public int pwrite(FileDescriptor fd, byte[] bytes, int byteOffset, int byteCount, long offset) throws ErrnoException {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        return os.pwrite(fd, bytes, byteOffset, byteCount, offset);
    }

    @Override public int read(FileDescriptor fd, ByteBuffer buffer) throws ErrnoException {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        return os.read(fd, buffer);
    }

    @Override public int read(FileDescriptor fd, byte[] bytes, int byteOffset, int byteCount) throws ErrnoException {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        return os.read(fd, bytes, byteOffset, byteCount);
    }

    @Override public int readv(FileDescriptor fd, Object[] buffers, int[] offsets, int[] byteCounts) throws ErrnoException {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        return os.readv(fd, buffers, offsets, byteCounts);
    }

    @Override public int recvfrom(FileDescriptor fd, ByteBuffer buffer, int flags, InetSocketAddress srcAddress) throws ErrnoException {
        BlockGuard.getThreadPolicy().onNetwork();
        return os.recvfrom(fd, buffer, flags, srcAddress);
    }

    @Override public int recvfrom(FileDescriptor fd, byte[] bytes, int byteOffset, int byteCount, int flags, InetSocketAddress srcAddress) throws ErrnoException {
        BlockGuard.getThreadPolicy().onNetwork();
        return os.recvfrom(fd, bytes, byteOffset, byteCount, flags, srcAddress);
    }

    @Override public int sendto(FileDescriptor fd, ByteBuffer buffer, int flags, InetAddress inetAddress, int port) throws ErrnoException {
        BlockGuard.getThreadPolicy().onNetwork();
        return os.sendto(fd, buffer, flags, inetAddress, port);
    }

    @Override public int sendto(FileDescriptor fd, byte[] bytes, int byteOffset, int byteCount, int flags, InetAddress inetAddress, int port) throws ErrnoException {
        // We permit datagrams without hostname lookups.
        if (inetAddress != null) {
            BlockGuard.getThreadPolicy().onNetwork();
        }
        return os.sendto(fd, bytes, byteOffset, byteCount, flags, inetAddress, port);
    }

    @Override public FileDescriptor socket(int domain, int type, int protocol) throws ErrnoException {
        return tagSocket(os.socket(domain, type, protocol));
    }

    @Override public int write(FileDescriptor fd, ByteBuffer buffer) throws ErrnoException {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        return os.write(fd, buffer);
    }

    @Override public int write(FileDescriptor fd, byte[] bytes, int byteOffset, int byteCount) throws ErrnoException {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        return os.write(fd, bytes, byteOffset, byteCount);
    }

    @Override public int writev(FileDescriptor fd, Object[] buffers, int[] offsets, int[] byteCounts) throws ErrnoException {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        return os.writev(fd, buffers, offsets, byteCounts);
    }
}
