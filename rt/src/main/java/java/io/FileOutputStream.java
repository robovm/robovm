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

package java.io;

import java.nio.channels.FileChannel;

import org.apache.harmony.luni.platform.IFileSystem;
import org.apache.harmony.luni.platform.Platform;
import org.apache.harmony.luni.internal.nls.Messages;
import org.apache.harmony.nio.FileChannelFactory;

/**
 * A specialized {@link OutputStream} that writes to a file in the file system.
 * All write requests made by calling methods in this class are directly
 * forwarded to the equivalent function of the underlying operating system.
 * Since this may induce some performance penalty, in particular if many small
 * write requests are made, a FileOutputStream is often wrapped by a
 * BufferedOutputStream.
 * 
 * @see BufferedOutputStream
 * @see FileInputStream
 */
public class FileOutputStream extends OutputStream implements Closeable {

    /**
     * The FileDescriptor representing this FileOutputStream.
     */
    FileDescriptor fd;

    boolean innerFD;

    // The unique file channel associated with this FileInputStream (lazily
    // initialized).
    private FileChannel channel;

    private IFileSystem fileSystem = Platform.getFileSystem();

    /**
     * Constructs a new FileOutputStream on the File {@code file}. If the file
     * exists, it is overwritten.
     * 
     * @param file
     *            the file to which this stream writes.
     * @throws FileNotFoundException
     *             if {@code file} cannot be opened for writing.
     * @throws SecurityException
     *             if a {@code SecurityManager} is installed and it denies the
     *             write request.
     * @see java.lang.SecurityManager#checkWrite(FileDescriptor)
     */
    public FileOutputStream(File file) throws FileNotFoundException {
        this(file, false);
    }

    /**
     * Constructs a new FileOutputStream on the File {@code file}. The
     * parameter {@code append} determines whether or not the file is opened and
     * appended to or just opened and overwritten.
     * 
     * @param file
     *            the file to which this stream writes.
     * @param append
     *            indicates whether or not to append to an existing file.
     * @throws FileNotFoundException
     *             if the {@code file} cannot be opened for writing.
     * @throws SecurityException
     *             if a {@code SecurityManager} is installed and it denies the
     *             write request.
     * @see java.lang.SecurityManager#checkWrite(FileDescriptor)
     * @see java.lang.SecurityManager#checkWrite(String)
     */
    public FileOutputStream(File file, boolean append)
            throws FileNotFoundException {
        super();
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkWrite(file.getPath());
        }
        fd = new FileDescriptor();
        fd.descriptor = fileSystem.open(file.properPath(true),
                append ? IFileSystem.O_APPEND : IFileSystem.O_WRONLY);
        innerFD = true;
        channel = FileChannelFactory.getFileChannel(this, fd.descriptor,
                append ? IFileSystem.O_APPEND : IFileSystem.O_WRONLY);
    }

    /**
     * Constructs a new FileOutputStream on the FileDescriptor {@code fd}. The
     * file must already be open, therefore no {@code FileNotFoundException}
     * will be thrown.
     * 
     * @param fd
     *            the FileDescriptor to which this stream writes.
     * @throws NullPointerException
     *             if {@code fd} is {@code null}.
     * @throws SecurityException
     *             if a {@code SecurityManager} is installed and it denies the
     *             write request.
     * @see java.lang.SecurityManager#checkWrite(FileDescriptor)
     */
    public FileOutputStream(FileDescriptor fd) {
        super();
        if (fd == null) {
            throw new NullPointerException(Messages.getString("luni.B6")); //$NON-NLS-1$
        }
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkWrite(fd);
        }
        this.fd = fd;
        innerFD = false;
        channel = FileChannelFactory.getFileChannel(this, fd.descriptor,
                IFileSystem.O_WRONLY);
    }

    /**
     * Constructs a new FileOutputStream on the file named {@code filename}. If
     * the file exists, it is overwritten. The {@code filename} may be absolute
     * or relative to the system property {@code "user.dir"}.
     * 
     * @param filename
     *            the name of the file to which this stream writes.
     * @throws FileNotFoundException
     *             if the file cannot be opened for writing.
     * @throws SecurityException
     *             if a {@code SecurityManager} is installed and it denies the
     *             write request.
     */
    public FileOutputStream(String filename) throws FileNotFoundException {
        this(filename, false);
    }

    /**
     * Constructs a new FileOutputStream on the file named {@code filename}.
     * The parameter {@code append} determines whether or not the file is opened
     * and appended to or just opened and overwritten. The {@code filename} may
     * be absolute or relative to the system property {@code "user.dir"}.
     * 
     * @param filename
     *            the name of the file to which this stream writes.
     * @param append
     *            indicates whether or not to append to an existing file.
     * @throws FileNotFoundException
     *             if the file cannot be opened for writing.
     * @throws SecurityException
     *             if a {@code SecurityManager} is installed and it denies the
     *             write request.
     */
    public FileOutputStream(String filename, boolean append)
            throws FileNotFoundException {
        this(new File(filename), append);
    }

    /**
     * Closes this stream. This implementation closes the underlying operating
     * system resources allocated to represent this stream.
     * 
     * @throws IOException
     *             if an error occurs attempting to close this stream.
     */
    @Override
    public void close() throws IOException {
        if (fd == null) {
            // if fd is null, then the underlying file is not opened, so nothing
            // to close
            return;
        }

        if (channel != null) {
            synchronized (channel) {
                if (channel.isOpen() && fd.descriptor >= 0) {
                    channel.close();
                }
            }
        }

        synchronized (this) {
            if (fd.descriptor >= 0 && innerFD) {
                fileSystem.close(fd.descriptor);
                fd.descriptor = -1;
            }
        }
    }

    /**
     * Frees any resources allocated for this stream before it is garbage
     * collected. This method is called from the Java Virtual Machine.
     * 
     * @throws IOException
     *             if an error occurs attempting to finalize this stream.
     */
    @Override
    protected void finalize() throws IOException {
        close();
    }

    /**
     * Returns the FileChannel equivalent to this output stream.
     * <p>
     * The file channel is write-only and has an initial position within the
     * file that is the same as the current position of this stream within the
     * file. All changes made to the underlying file descriptor state via the
     * channel are visible by the output stream and vice versa.
     *
     * @return the file channel representation for this stream.
     */
    public FileChannel getChannel() {
        return channel;
    }

    /**
     * Returns a FileDescriptor which represents the lowest level representation
     * of an operating system stream resource.
     * 
     * @return a FileDescriptor representing this stream.
     * @throws IOException
     *             if an error occurs attempting to get the FileDescriptor of
     *             this stream.
     */
    public final FileDescriptor getFD() throws IOException {
        return fd;
    }

    /**
     * Writes the entire contents of the byte array {@code buffer} to this
     * stream.
     * 
     * @param buffer
     *            the buffer to be written to the file.
     * @throws IOException
     *             if this stream is closed or an error occurs attempting to
     *             write to this stream.
     */
    @Override
    public void write(byte[] buffer) throws IOException {
        write(buffer, 0, buffer.length);
    }

    /**
     * Writes {@code count} bytes from the byte array {@code buffer} starting at
     * {@code offset} to this stream.
     * 
     * @param buffer
     *            the buffer to write to this stream.
     * @param offset
     *            the index of the first byte in {@code buffer} to write.
     * @param count
     *            the number of bytes from {@code buffer} to write.
     * @throws IndexOutOfBoundsException
     *             if {@code count < 0} or {@code offset < 0}, or if
     *             {@code count + offset} is greater than the length of
     *             {@code buffer}.
     * @throws IOException
     *             if this stream is closed or an error occurs attempting to
     *             write to this stream.
     * @throws NullPointerException
     *             if {@code buffer} is {@code null}.
     */
    @Override
    public void write(byte[] buffer, int offset, int count) throws IOException {
        if (buffer == null) {
            throw new NullPointerException();
        }
        if (count < 0 || offset < 0 || offset > buffer.length
                || count > buffer.length - offset) {
            throw new IndexOutOfBoundsException();
        }

        if (count == 0) {
            return;
        }

        openCheck();
        fileSystem.write(fd.descriptor, buffer, offset, count);
    }

    /**
     * Writes the specified byte {@code oneByte} to this stream. Only the low
     * order byte of the integer {@code oneByte} is written.
     * 
     * @param oneByte
     *            the byte to be written.
     * @throws IOException
     *             if this stream is closed an error occurs attempting to write
     *             to this stream.
     */
    @Override
    public void write(int oneByte) throws IOException {
        openCheck();
        byte[] byteArray = new byte[1];
        byteArray[0] = (byte) oneByte;
        fileSystem.write(fd.descriptor, byteArray, 0, 1);
    }

    private synchronized void openCheck() throws IOException {
        if (fd.descriptor < 0) {
            throw new IOException();
        }
    }
}
