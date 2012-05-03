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

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.nio.charset.Charsets;
import static libcore.io.OsConstants.*;

public final class IoUtils {
    private IoUtils() {
    }

    /**
     * Calls close(2) on 'fd'. Also resets the internal int to -1. Does nothing if 'fd' is null
     * or invalid.
     */
    public static void close(FileDescriptor fd) throws IOException {
        try {
            if (fd != null && fd.valid()) {
                Libcore.os.close(fd);
            }
        } catch (ErrnoException errnoException) {
            throw errnoException.rethrowAsIOException();
        }
    }

    /**
     * Closes 'closeable', ignoring any checked exceptions. Does nothing if 'closeable' is null.
     */
    public static void closeQuietly(AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException rethrown) {
                throw rethrown;
            } catch (Exception ignored) {
            }
        }
    }

    /**
     * Closes 'fd', ignoring any exceptions. Does nothing if 'fd' is null or invalid.
     */
    public static void closeQuietly(FileDescriptor fd) {
        try {
            IoUtils.close(fd);
        } catch (IOException ignored) {
        }
    }

    /**
     * Closes 'socket', ignoring any exceptions. Does nothing if 'socket' is null.
     */
    public static void closeQuietly(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (Exception ignored) {
            }
        }
    }

    /**
     * Sets 'fd' to be blocking or non-blocking, according to the state of 'blocking'.
     */
    public static void setBlocking(FileDescriptor fd, boolean blocking) throws IOException {
        try {
            int flags = Libcore.os.fcntlVoid(fd, F_GETFL);
            if (!blocking) {
                flags |= O_NONBLOCK;
            } else {
                flags &= ~O_NONBLOCK;
            }
            Libcore.os.fcntlLong(fd, F_SETFL, flags);
        } catch (ErrnoException errnoException) {
            throw errnoException.rethrowAsIOException();
        }
    }

    /**
     * Returns the contents of 'path' as a byte array.
     */
    public static byte[] readFileAsByteArray(String path) throws IOException {
        return readFileAsBytes(path).toByteArray();
    }

    /**
     * Returns the contents of 'path' as a string. The contents are assumed to be UTF-8.
     */
    public static String readFileAsString(String path) throws IOException {
        return readFileAsBytes(path).toString(Charsets.UTF_8);
    }

    private static UnsafeByteSequence readFileAsBytes(String path) throws IOException {
        RandomAccessFile f = null;
        try {
            f = new RandomAccessFile(path, "r");
            UnsafeByteSequence bytes = new UnsafeByteSequence((int) f.length());
            byte[] buffer = new byte[8192];
            while (true) {
                int byteCount = f.read(buffer);
                if (byteCount == -1) {
                    return bytes;
                }
                bytes.write(buffer, 0, byteCount);
            }
        } finally {
            IoUtils.closeQuietly(f);
        }
    }

    /**
     * Recursively delete everything in {@code dir}.
     */
    // TODO: this should specify paths as Strings rather than as Files
    public static void deleteContents(File dir) throws IOException {
        File[] files = dir.listFiles();
        if (files == null) {
            throw new IllegalArgumentException("not a directory: " + dir);
        }
        for (File file : files) {
            if (file.isDirectory()) {
                deleteContents(file);
            }
            if (!file.delete()) {
                throw new IOException("failed to delete file: " + file);
            }
        }
    }
}
