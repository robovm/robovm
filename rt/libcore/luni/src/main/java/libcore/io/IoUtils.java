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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import static libcore.io.OsConstants.*;

public final class IoUtils {
    private static final Random TEMPORARY_DIRECTORY_PRNG = new Random();

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
    public static byte[] readFileAsByteArray(String absolutePath) throws IOException {
        return new FileReader(absolutePath).readFully().toByteArray();
    }

    /**
     * Returns the contents of 'path' as a string. The contents are assumed to be UTF-8.
     */
    public static String readFileAsString(String absolutePath) throws IOException {
        return new FileReader(absolutePath).readFully().toString(StandardCharsets.UTF_8);
    }

    /**
     * Do not use. Use createTemporaryDirectory instead.
     *
     * Used by frameworks/base unit tests to clean up a temporary directory.
     * Deliberately ignores errors, on the assumption that test cleanup is only
     * supposed to be best-effort.
     *
     * @deprecated Use {@link #createTemporaryDirectory} instead.
     */
    public static void deleteContents(File dir) throws IOException {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteContents(file);
                }
                file.delete();
            }
        }
    }

    /**
     * Creates a unique new temporary directory under "java.io.tmpdir".
     */
    public static File createTemporaryDirectory(String prefix) {
        while (true) {
            String candidateName = prefix + TEMPORARY_DIRECTORY_PRNG.nextInt();
            File result = new File(System.getProperty("java.io.tmpdir"), candidateName);
            if (result.mkdir()) {
                return result;
            }
        }
    }

    /**
     * Do not use. This is for System.loadLibrary use only.
     *
     * Checks whether {@code path} can be opened read-only. Similar to File.exists, but doesn't
     * require read permission on the parent, so it'll work in more cases, and allow you to
     * remove read permission from more directories. Everyone else should just open(2) and then
     * use the fd, but the loadLibrary API is broken by its need to ask ClassLoaders where to
     * find a .so rather than just calling dlopen(3).
     */
    public static boolean canOpenReadOnly(String path) {
        try {
            // Use open(2) rather than stat(2) so we require fewer permissions. http://b/6485312.
            FileDescriptor fd = Libcore.os.open(path, O_RDONLY, 0);
            Libcore.os.close(fd);
            return true;
        } catch (ErrnoException errnoException) {
            return false;
        }
    }

    public static void throwInterruptedIoException() throws InterruptedIOException {
        // This is typically thrown in response to an
        // InterruptedException which does not leave the thread in an
        // interrupted state, so explicitly interrupt here.
        Thread.currentThread().interrupt();
        // TODO: set InterruptedIOException.bytesTransferred
        throw new InterruptedIOException();
    }

    /**
     * A convenience class for reading the contents of a file into a {@code String}
     * or a {@code byte[]}. This class attempts to minimize the number of allocations
     * and copies required to read this data.
     *
     * For the case where we know the "true" length of a file (most ordinary files)
     * we allocate exactly one byte[] and copy data into that. Calls to
     * {@link #toByteArray} will then return the internal array and <b>not</b> a copy.
     *
     * <b>Note that an absolute path must be supplied. Expect your reads to fail
     * if one isn't.</b>
     */
    private static class FileReader {
        private FileDescriptor fd;
        private boolean unknownLength;

        private byte[] bytes;
        private int count;

        public FileReader(String absolutePath) throws IOException {
            // We use IoBridge.open because callers might differentiate
            // between a FileNotFoundException and a general IOException.
            //
            // NOTE: This costs us an additional call to fstat(2) to test whether
            // "absolutePath" is a directory or not. We can eliminate it
            // at the cost of copying some code from IoBridge.open.
            try {
                fd = IoBridge.open(absolutePath, O_RDONLY);
            } catch (FileNotFoundException fnfe) {
                throw fnfe;
            }

            int capacity;
            try {
                final StructStat stat = Libcore.os.fstat(fd);
                // Like RAF & other APIs, we assume that the file size fits
                // into a 32 bit integer.
                capacity = (int) stat.st_size;
                if (capacity == 0) {
                    unknownLength = true;
                    capacity = 8192;
                }
            } catch (ErrnoException exception) {
                closeQuietly(fd);
                throw exception.rethrowAsIOException();
            }

            bytes = new byte[capacity];
        }

        public FileReader readFully() throws IOException {
            int read;
            int capacity = bytes.length;
            try {
                while ((read = Libcore.os.read(fd, bytes, count, capacity - count)) != 0) {
                    count += read;
                    if (count == capacity) {
                        if (unknownLength) {
                            // If we don't know the length of this file, we need to continue
                            // reading until we reach EOF. Double the capacity in preparation.
                            final int newCapacity = capacity * 2;
                            byte[] newBytes = new byte[newCapacity];
                            System.arraycopy(bytes, 0, newBytes, 0, capacity);
                            bytes = newBytes;
                            capacity = newCapacity;
                        } else {
                            // We know the length of this file and we've read the right number
                            // of bytes from it, return.
                            break;
                        }
                    }
                }

                return this;
            } catch (ErrnoException e) {
                throw e.rethrowAsIOException();
            } finally {
                closeQuietly(fd);
            }
        }

        @FindBugsSuppressWarnings("EI_EXPOSE_REP")
        public byte[] toByteArray() {
            if (count == bytes.length) {
                return bytes;
            }
            byte[] result = new byte[count];
            System.arraycopy(bytes, 0, result, 0, count);
            return result;
        }

        public String toString(Charset cs) {
            return new String(bytes, 0, count, cs);
        }
    }
}
