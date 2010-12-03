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
import org.apache.harmony.luni.util.Util;

import org.apache.harmony.nio.FileChannelFactory;

/**
 * Allows reading from and writing to a file in a random-access manner. This is
 * different from the uni-directional sequential access that a
 * {@link FileInputStream} or {@link FileOutputStream} provides. If the file is
 * opened in read/write mode, write operations are available as well. The
 * position of the next read or write operation can be moved forwards and
 * backwards after every operation.
 */
public class RandomAccessFile implements DataInput, DataOutput, Closeable {
    /**
     * The FileDescriptor representing this RandomAccessFile.
     */
    private FileDescriptor fd;

    private boolean syncMetadata = false;

    // The unique file channel associated with this FileInputStream (lazily
    // initialized).
    private FileChannel channel;

    private IFileSystem fileSystem = Platform.getFileSystem();

    private boolean isReadOnly;

    private static class RepositionLock {
    }

    private Object repositionLock = new RepositionLock();

    /**
     * Constructs a new {@code RandomAccessFile} based on {@code file} and opens
     * it according to the access string in {@code mode}.
     * <p><a id="accessmode"/>
     * {@code mode} may have one of following values:
     * <table border="0">
     * <tr>
     * <td>{@code "r"}</td>
     * <td>The file is opened in read-only mode. An {@code IOException} is
     * thrown if any of the {@code write} methods is called.</td>
     * </tr>
     * <tr>
     * <td>{@code "rw"}</td>
     * <td>The file is opened for reading and writing. If the file does not
     * exist, it will be created.</td>
     * </tr>
     * <tr>
     * <td>{@code "rws"}</td>
     * <td>The file is opened for reading and writing. Every change of the
     * file's content or metadata must be written synchronously to the target
     * device.</td>
     * </tr>
     * <tr>
     * <td>{@code "rwd"}</td>
     * <td>The file is opened for reading and writing. Every change of the
     * file's content must be written synchronously to the target device.</td>
     * </tr>
     * </table>
     *
     * @param file
     *            the file to open.
     * @param mode
     *            the file access <a href="#accessmode">mode</a>, either {@code
     *            "r"}, {@code "rw"}, {@code "rws"} or {@code "rwd"}.
     * @throws FileNotFoundException
     *             if the file cannot be opened or created according to {@code
     *             mode}.
     * @throws IllegalArgumentException
     *             if {@code mode} is not {@code "r"}, {@code "rw"}, {@code
     *             "rws"} or {@code "rwd"}.
     * @throws SecurityException
     *             if a {@code SecurityManager} is installed and it denies
     *             access request according to {@code mode}.
     * @see java.lang.SecurityManager#checkRead(FileDescriptor)
     * @see java.lang.SecurityManager#checkWrite(FileDescriptor)
     */
    public RandomAccessFile(File file, String mode)
            throws FileNotFoundException {
        super();

        int options = 0;
        
        fd = new FileDescriptor();
       
        if (mode.equals("r")) { //$NON-NLS-1$
            isReadOnly = true;
            fd.readOnly = true;
            options = IFileSystem.O_RDONLY;
        } else if (mode.equals("rw") || mode.equals("rws") || mode.equals("rwd")) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            isReadOnly = false;
            options = IFileSystem.O_RDWR;

            if (mode.equals("rws")) { //$NON-NLS-1$
                // Sync file and metadata with every write
                syncMetadata = true;
            } else if (mode.equals("rwd")) { //$NON-NLS-1$
                // Sync file, but not necessarily metadata
                options = IFileSystem.O_RDWRSYNC;
            }
        } else {
            throw new IllegalArgumentException(Messages.getString("luni.A2")); //$NON-NLS-1$
        }

        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkRead(file.getPath());
            if (!isReadOnly) {
                security.checkWrite(file.getPath());
            }
        }
        
        fd.descriptor = fileSystem.open(file.properPath(true), options);
        channel = FileChannelFactory.getFileChannel(this, fd.descriptor,
                options);

        // if we are in "rws" mode, attempt to sync file+metadata
        if (syncMetadata) {
            try {
                fd.sync();
            } catch (IOException e) {
                // Ignored
            }
        }
    }

    /**
     * Constructs a new {@code RandomAccessFile} based on the file named {@code
     * fileName} and opens it according to the access string in {@code mode}.
     * The file path may be specified absolutely or relative to the system
     * property {@code "user.dir"}.
     * 
     * @param fileName
     *            the name of the file to open.
     * @param mode
     *            the file access <a href="#accessmode">mode</a>, either {@code
     *            "r"}, {@code "rw"}, {@code "rws"} or {@code "rwd"}.
     * @throws FileNotFoundException
     *             if the file cannot be opened or created according to {@code
     *             mode}.
     * @throws IllegalArgumentException
     *             if {@code mode} is not {@code "r"}, {@code "rw"}, {@code
     *             "rws"} or {@code "rwd"}.
     * @throws SecurityException
     *             if a {@code SecurityManager} is installed and it denies
     *             access request according to {@code mode}.
     * @see java.lang.SecurityManager#checkRead(FileDescriptor)
     * @see java.lang.SecurityManager#checkWrite(FileDescriptor)
     */
    public RandomAccessFile(String fileName, String mode)
            throws FileNotFoundException {
        this(new File(fileName), mode);
    }

    /**
     * Closes this file.
     * 
     * @throws IOException
     *             if an error occurs while closing this file.
     */
    public void close() throws IOException {
        synchronized (channel) {
            if (channel.isOpen()) {
                channel.close();
            }
        }
        synchronized (this) {
            if (fd != null && fd.descriptor >= 0) {
                fileSystem.close(fd.descriptor);
                fd.descriptor = -1;
            }
        }
    }

    /**
     * Gets this file's {@link FileChannel} object.
     * <p>
     * The file channel's {@link FileChannel#position() position} is the same
     * as this file's file pointer offset (see {@link #getFilePointer()}). Any
     * changes made to this file's file pointer offset are also visible in the
     * file channel's position and vice versa.
     * 
     * @return this file's file channel instance.
     */
    public final synchronized FileChannel getChannel() {
        return channel;
    }

    /**
     * Gets this file's {@link FileDescriptor}. This represents the operating
     * system resource for this random access file.
     * 
     * @return this file's file descriptor object.
     * @throws IOException
     *             if an error occurs while getting the file descriptor of this
     *             file.
     */
    public final FileDescriptor getFD() throws IOException {
        return fd;
    }

    /**
     * Gets the current position within this file. All reads and
     * writes take place at the current file pointer position.
     * 
     * @return the current offset in bytes from the beginning of the file.
     * 
     * @throws IOException
     *             if an error occurs while getting the file pointer of this
     *             file.
     */
    public long getFilePointer() throws IOException {
        openCheck();
        return fileSystem.seek(fd.descriptor, 0L, IFileSystem.SEEK_CUR);
    }

    /**
     * Checks to see if the file is currently open. Returns silently if it is,
     * and throws an exception if it is not.
     * 
     * @throws IOException
     *             the receiver is closed.
     */
    private synchronized void openCheck() throws IOException {
        if (fd.descriptor < 0) {
            throw new IOException();
        }
    }

    /**
     * Returns the length of this file in bytes.
     * 
     * @return the file's length in bytes.
     * @throws IOException
     *             if this file is closed or some other I/O error occurs.
     */
    public long length() throws IOException {
        openCheck();
        return fileSystem.size(fd.descriptor);
    }

    /**
     * Reads a single byte from the current position in this file and returns it
     * as an integer in the range from 0 to 255. Returns -1 if the end of the
     * file has been reached. Blocks until one byte has been read, the end of
     * the file is detected or an exception is thrown.
     * 
     * @return the byte read or -1 if the end of the file has been reached.
     * @throws IOException
     *             if this file is closed or another I/O error occurs.
     */
    public int read() throws IOException {
        openCheck();
        byte[] bytes = new byte[1];
        synchronized (repositionLock) {
            long readed = fileSystem.read(fd.descriptor, bytes, 0, 1);
            return readed == -1 ? -1 : bytes[0] & 0xff;
        }
    }

    /**
     * Reads bytes from the current position in this file and stores them in the
     * byte array {@code buffer}. The maximum number of bytes read corresponds
     * to the size of {@code buffer}. Blocks until at least one byte has been
     * read.
     * 
     * @param buffer
     *            the byte array in which to store the bytes read.
     * @return the number of bytes actually read or -1 if the end of the file
     *         has been reached.
     * @throws IOException
     *             if this file is closed or another I/O error occurs.
     */
    public int read(byte[] buffer) throws IOException {
        return read(buffer, 0, buffer.length);
    }

    /**
     * Reads at most {@code count} bytes from the current position in this file
     * and stores them in the byte array {@code buffer} starting at {@code
     * offset}. Blocks until {@code count} bytes have been read, the end of the
     * file is reached or an exception is thrown.
     * 
     * @param buffer
     *            the array in which to store the bytes read from this file.
     * @param offset
     *            the initial position in {@code buffer} to store the bytes read
     *            from this file.
     * @param count
     *            the maximum number of bytes to store in {@code buffer}.
     * @return the number of bytes actually read or -1 if the end of the stream
     *         has been reached.
     * @throws IndexOutOfBoundsException
     *             if {@code offset < 0} or {@code count < 0}, or if {@code
     *             offset + count} is greater than the size of {@code buffer}.
     * @throws IOException
     *             if this file is closed or another I/O error occurs.
     */
    public int read(byte[] buffer, int offset, int count) throws IOException {
        // have to have four comparisions to not miss integer overflow cases
        if (count > buffer.length - offset || count < 0 || offset < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (0 == count) {
            return 0;
        }
        openCheck();
        synchronized (repositionLock) {
            return (int) fileSystem.read(fd.descriptor, buffer, offset, count);
        }
    }

    /**
     * Reads a boolean from the current position in this file. Blocks until one
     * byte has been read, the end of the file is reached or an exception is
     * thrown.
     * 
     * @return the next boolean value from this file.
     * @throws EOFException
     *             if the end of this file is detected.
     * @throws IOException
     *             if this file is closed or another I/O error occurs.
     * @see #writeBoolean(boolean)
     */
    public final boolean readBoolean() throws IOException {
        int temp = this.read();
        if (temp < 0) {
            throw new EOFException();
        }
        return temp != 0;
    }

    /**
     * Reads an 8-bit byte from the current position in this file. Blocks until
     * one byte has been read, the end of the file is reached or an exception is
     * thrown.
     * 
     * @return the next signed 8-bit byte value from this file.
     * @throws EOFException
     *             if the end of this file is detected.
     * @throws IOException
     *             if this file is closed or another I/O error occurs.
     * @see #writeBoolean(boolean)
     */
    public final byte readByte() throws IOException {
        int temp = this.read();
        if (temp < 0) {
            throw new EOFException();
        }
        return (byte) temp;
    }

    /**
     * Reads a 16-bit character from the current position in this file. Blocks until
     * two bytes have been read, the end of the file is reached or an exception is
     * thrown.
     * 
     * @return the next char value from this file.
     * @throws EOFException
     *             if the end of this file is detected.
     * @throws IOException
     *             if this file is closed or another I/O error occurs.
     * @see #writeChar(int)
     */
    public final char readChar() throws IOException {
        byte[] buffer = new byte[2];
        if (read(buffer, 0, buffer.length) != buffer.length) {
            throw new EOFException();
        }
        return (char) (((buffer[0] & 0xff) << 8) + (buffer[1] & 0xff));
    }

    /**
     * Reads a 64-bit double from the current position in this file. Blocks
     * until eight bytes have been read, the end of the file is reached or an
     * exception is thrown.
     * 
     * @return the next double value from this file.
     * @throws EOFException
     *             if the end of this file is detected.
     * @throws IOException
     *             if this file is closed or another I/O error occurs.
     * @see #writeDouble(double)
     */
    public final double readDouble() throws IOException {
        return Double.longBitsToDouble(readLong());
    }

    /**
     * Reads a 32-bit float from the current position in this file. Blocks
     * until four bytes have been read, the end of the file is reached or an
     * exception is thrown.
     * 
     * @return the next float value from this file.
     * @throws EOFException
     *             if the end of this file is detected.
     * @throws IOException
     *             if this file is closed or another I/O error occurs.
     * @see #writeFloat(float)
     */
    public final float readFloat() throws IOException {
        return Float.intBitsToFloat(readInt());
    }

    /**
     * Reads bytes from this file into {@code buffer}. Blocks until {@code
     * buffer.length} number of bytes have been read, the end of the file is
     * reached or an exception is thrown.
     * 
     * @param buffer
     *            the buffer to read bytes into.
     * @throws EOFException
     *             if the end of this file is detected.
     * @throws IOException
     *             if this file is closed or another I/O error occurs.
     * @throws NullPointerException
     *             if {@code buffer} is {@code null}.
     */
    public final void readFully(byte[] buffer) throws IOException {
        readFully(buffer, 0, buffer.length);
    }

    /**
     * Read bytes from this file into {@code buffer} starting at offset {@code
     * offset}. This method blocks until {@code count} number of bytes have been
     * read.
     * 
     * @param buffer
     *            the buffer to read bytes into.
     * @param offset
     *            the initial position in {@code buffer} to store the bytes read
     *            from this file.
     * @param count
     *            the maximum number of bytes to store in {@code buffer}.
     * @throws EOFException
     *             if the end of this file is detected.
     * @throws IndexOutOfBoundsException
     *             if {@code offset < 0} or {@code count < 0}, or if {@code
     *             offset + count} is greater than the length of {@code buffer}.
     * @throws IOException
     *             if this file is closed or another I/O error occurs.
     * @throws NullPointerException
     *             if {@code buffer} is {@code null}.
     */
    public final void readFully(byte[] buffer, int offset, int count)
            throws IOException {
        if (buffer == null) {
            throw new NullPointerException(Messages.getString("luni.11")); //$NON-NLS-1$
        }
        // avoid int overflow
        if (offset < 0 || offset > buffer.length || count < 0
                || count > buffer.length - offset) {
            throw new IndexOutOfBoundsException();
        }
        while (count > 0) {
            int result = read(buffer, offset, count);
            if (result < 0) {
                throw new EOFException();
            }
            offset += result;
            count -= result;
        }
    }

    /**
     * Reads a 32-bit integer from the current position in this file. Blocks
     * until four bytes have been read, the end of the file is reached or an
     * exception is thrown.
     * 
     * @return the next int value from this file.
     * @throws EOFException
     *             if the end of this file is detected.
     * @throws IOException
     *             if this file is closed or another I/O error occurs.
     * @see #writeInt(int)
     */
    public final int readInt() throws IOException {
        byte[] buffer = new byte[4];
        if (read(buffer, 0, buffer.length) != buffer.length) {
            throw new EOFException();
        }
        return ((buffer[0] & 0xff) << 24) + ((buffer[1] & 0xff) << 16)
                + ((buffer[2] & 0xff) << 8) + (buffer[3] & 0xff);
    }

    /**
     * Reads a line of text form the current position in this file. A line is
     * represented by zero or more characters followed by {@code '\n'}, {@code
     * '\r'}, {@code "\r\n"} or the end of file marker. The string does not
     * include the line terminating sequence.
     * <p>
     * Blocks until a line terminating sequence has been read, the end of the
     * file is reached or an exception is thrown.
     *
     * @return the contents of the line or {@code null} if no characters have
     *         been read before the end of the file has been reached.
     * @throws IOException
     *             if this file is closed or another I/O error occurs.
     */
    public final String readLine() throws IOException {
        StringBuilder line = new StringBuilder(80); // Typical line length
        boolean foundTerminator = false;
        long unreadPosition = 0;
        while (true) {
            int nextByte = read();
            switch (nextByte) {
                case -1:
                    return line.length() != 0 ? line.toString() : null;
                case (byte) '\r':
                    if (foundTerminator) {
                        seek(unreadPosition);
                        return line.toString();
                    }
                    foundTerminator = true;
                    /* Have to be able to peek ahead one byte */
                    unreadPosition = getFilePointer();
                    break;
                case (byte) '\n':
                    return line.toString();
                default:
                    if (foundTerminator) {
                        seek(unreadPosition);
                        return line.toString();
                    }
                    line.append((char) nextByte);
            }
        }
    }

    /**
     * Reads a 64-bit long from the current position in this file. Blocks until
     * eight bytes have been read, the end of the file is reached or an
     * exception is thrown.
     * 
     * @return the next long value from this file.
     * @throws EOFException
     *             if the end of this file is detected.
     * @throws IOException
     *             if this file is closed or another I/O error occurs.
     * @see #writeLong(long)
     */
    public final long readLong() throws IOException {
        byte[] buffer = new byte[8];
        if (read(buffer, 0, buffer.length) != buffer.length) {
            throw new EOFException();
        }
        return ((long) (((buffer[0] & 0xff) << 24) + ((buffer[1] & 0xff) << 16)
                + ((buffer[2] & 0xff) << 8) + (buffer[3] & 0xff)) << 32)
                + ((long) (buffer[4] & 0xff) << 24)
                + ((buffer[5] & 0xff) << 16)
                + ((buffer[6] & 0xff) << 8)
                + (buffer[7] & 0xff);
    }

    /**
     * Reads a 16-bit short from the current position in this file. Blocks until
     * two bytes have been read, the end of the file is reached or an exception
     * is thrown.
     * 
     * @return the next short value from this file.
     * @throws EOFException
     *             if the end of this file is detected.
     * @throws IOException
     *             if this file is closed or another I/O error occurs.
     * @see #writeShort(int)
     */
    public final short readShort() throws IOException {
        byte[] buffer = new byte[2];
        if (read(buffer, 0, buffer.length) != buffer.length) {
            throw new EOFException();
        }
        return (short) (((buffer[0] & 0xff) << 8) + (buffer[1] & 0xff));
    }

    /**
     * Reads an unsigned 8-bit byte from the current position in this file and
     * returns it as an integer. Blocks until one byte has been read, the end of
     * the file is reached or an exception is thrown.
     * 
     * @return the next unsigned byte value from this file as an int.
     * @throws EOFException
     *             if the end of this file is detected.
     * @throws IOException
     *             if this file is closed or another I/O error occurs.
     * @see #writeByte(int)
     */
    public final int readUnsignedByte() throws IOException {
        int temp = this.read();
        if (temp < 0) {
            throw new EOFException();
        }
        return temp;
    }

    /**
     * Reads an unsigned 16-bit short from the current position in this file and
     * returns it as an integer. Blocks until two bytes have been read, the end of
     * the file is reached or an exception is thrown.
     * 
     * @return the next unsigned short value from this file as an int.
     * @throws EOFException
     *             if the end of this file is detected.
     * @throws IOException
     *             if this file is closed or another I/O error occurs.
     * @see #writeShort(int)
     */
    public final int readUnsignedShort() throws IOException {
        byte[] buffer = new byte[2];
        if (read(buffer, 0, buffer.length) != buffer.length) {
            throw new EOFException();
        }
        return ((buffer[0] & 0xff) << 8) + (buffer[1] & 0xff);
    }

    /**
     * Reads a string that is encoded in {@link DataInput modified UTF-8} from
     * this file. The number of bytes that must be read for the complete string
     * is determined by the first two bytes read from the file. Blocks until all
     * required bytes have been read, the end of the file is reached or an
     * exception is thrown.
     * 
     * @return the next string encoded in {@link DataInput modified UTF-8} from
     *         this file.
     * @throws EOFException
     *             if the end of this file is detected.
     * @throws IOException
     *             if this file is closed or another I/O error occurs.
     * @throws UTFDataFormatException
     *             if the bytes read cannot be decoded into a character string.
     * @see #writeUTF(String)
     */
    public final String readUTF() throws IOException {
        int utfSize = readUnsignedShort();
        if (utfSize == 0) {
            return ""; //$NON-NLS-1$
        }
        byte[] buf = new byte[utfSize];
        if (read(buf, 0, buf.length) != buf.length) {
            throw new EOFException();
        }
        return Util.convertFromUTF8(buf, 0, utfSize);
    }

    /**
     * Moves this file's file pointer to a new position, from where following
     * {@code read}, {@code write} or {@code skip} operations are done. The
     * position may be greater than the current length of the file, but the
     * file's length will only change if the moving of the pointer is followed
     * by a {@code write} operation.
     * 
     * @param pos
     *            the new file pointer position.
     * @throws IOException
     *             if this file is closed, {@code pos < 0} or another I/O error
     *             occurs.
     */
    public void seek(long pos) throws IOException {
        if (pos < 0) {
            // seek position is negative
            throw new IOException(Messages.getString("luni.BB")); //$NON-NLS-1$
        }
        openCheck();
        synchronized (repositionLock) {
            fileSystem.seek(fd.descriptor, pos, IFileSystem.SEEK_SET);
        }
    }

    /**
     * Sets the length of this file to {@code newLength}. If the current file is
     * smaller, it is expanded but the contents from the previous end of the
     * file to the new end are undefined. The file is truncated if its current
     * size is bigger than {@code newLength}. If the current file pointer
     * position is in the truncated part, it is set to the end of the file.
     * 
     * @param newLength
     *            the new file length in bytes.
     * @throws IllegalArgumentException
     *             if {@code newLength < 0}.
     * @throws IOException
     *             if this file is closed or another I/O error occurs.
     */
    public void setLength(long newLength) throws IOException {
        openCheck();
        if (newLength < 0) {
            throw new IllegalArgumentException();
        }
        synchronized (repositionLock) {
            long position = fileSystem.seek(fd.descriptor, 0,
                    IFileSystem.SEEK_CUR);
            fileSystem.truncate(fd.descriptor, newLength);
            seek(position > newLength ? newLength : position);
        }

        // if we are in "rws" mode, attempt to sync file+metadata
        if (syncMetadata) {
            fd.sync();
        }
    }

    /**
     * Skips over {@code count} bytes in this file. Less than {@code count}
     * bytes are skipped if the end of the file is reached or an exception is
     * thrown during the operation. Nothing is done if {@code count} is
     * negative.
     * 
     * @param count
     *            the number of bytes to skip.
     * @return the number of bytes actually skipped.
     * @throws IOException
     *             if this file is closed or another I/O error occurs.
     */
    public int skipBytes(int count) throws IOException {
        if (count > 0) {
            long currentPos = getFilePointer(), eof = length();
            int newCount = (int) ((currentPos + count > eof) ? eof - currentPos
                    : count);
            seek(currentPos + newCount);
            return newCount;
        }
        return 0;
    }

    /**
     * Writes the entire contents of the byte array {@code buffer} to this file,
     * starting at the current file pointer.
     * 
     * @param buffer
     *            the buffer to write.
     * @throws IOException
     *             if an I/O error occurs while writing to this file.
     * @see #read(byte[])
     * @see #read(byte[],int,int)
     * @see #readFully(byte[])
     * @see #readFully(byte[],int,int)
     */
    public void write(byte[] buffer) throws IOException {
        write(buffer, 0, buffer.length);
    }

    /**
     * Writes {@code count} bytes from the byte array {@code buffer} to this
     * file, starting at the current file pointer and using {@code offset} as
     * the first position within {@code buffer} to get bytes.
     * 
     * @param buffer
     *            the buffer to write to this file.
     * @param offset
     *            the index of the first byte in {@code buffer} to write.
     * @param count
     *            the number of bytes from {@code buffer} to write.
     * @throws IndexOutOfBoundsException
     *             if {@code count < 0}, {@code offset < 0} or {@code count +
     *             offset} is greater than the size of {@code buffer}.
     * @throws IOException
     *             if an I/O error occurs while writing to this file.
     * @see #read(byte[], int, int)
     * @see #readFully(byte[], int, int)
     */
    public void write(byte[] buffer, int offset, int count) throws IOException {
        if (count > buffer.length - offset || count < 0 || offset < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (count == 0) {
            return;
        }
        synchronized (repositionLock) {
            fileSystem.write(fd.descriptor, buffer, offset, count);
        }

        // if we are in "rws" mode, attempt to sync file+metadata
        if (syncMetadata) {
            fd.sync();
        }
    }

    /**
     * Writes a byte to this file, starting at the current file pointer. Only
     * the least significant byte of the integer {@code oneByte} is written.
     * 
     * @param oneByte
     *            the byte to write to this file.
     * @throws IOException
     *             if this file is closed or another I/O error occurs.
     * @see #read()
     */
    public void write(int oneByte) throws IOException {
        openCheck();
        byte[] bytes = new byte[1];
        bytes[0] = (byte) (oneByte & 0xff);
        synchronized (repositionLock) {
            fileSystem.write(fd.descriptor, bytes, 0, 1);
        }

        // if we are in "rws" mode, attempt to sync file+metadata
        if (syncMetadata) {
            fd.sync();
        }
    }

    /**
     * Writes a boolean to this file, starting at the current file pointer.
     * 
     * @param val
     *            the boolean to write to this file.
     * @throws IOException
     *             if this file is closed or another I/O error occurs.
     * @see #readBoolean()
     */
    public final void writeBoolean(boolean val) throws IOException {
        write(val ? 1 : 0);
    }

    /**
     * Writes an 8-bit byte to this file, starting at the current file pointer.
     * Only the least significant byte of the integer {@code val} is written.
     * 
     * @param val
     *            the byte to write to this file.
     * @throws IOException
     *             if this file is closed or another I/O error occurs.
     * @see #readByte()
     * @see #readUnsignedByte()
     */
    public final void writeByte(int val) throws IOException {
        write(val & 0xFF);
    }

    /**
     * Writes the low order 8-bit bytes from a string to this file, starting at
     * the current file pointer.
     * 
     * @param str
     *            the string containing the bytes to write to this file
     * @throws IOException
     *             if an I/O error occurs while writing to this file.
     * @see #read(byte[])
     * @see #read(byte[],int,int)
     * @see #readFully(byte[])
     * @see #readFully(byte[],int,int)
     */
    public final void writeBytes(String str) throws IOException {
        byte bytes[] = new byte[str.length()];
        for (int index = 0; index < str.length(); index++) {
            bytes[index] = (byte) (str.charAt(index) & 0xFF);
        }
        write(bytes);
    }

    /**
     * Writes a 16-bit character to this file, starting at the current file
     * pointer. Only the two least significant bytes of the integer {@code val}
     * are written, with the high byte first.
     * 
     * @param val
     *            the char to write to this file.
     * @throws IOException
     *             if an I/O error occurs while writing to this file.
     * @see #readChar()
     */
    public final void writeChar(int val) throws IOException {
        byte[] buffer = new byte[2];
        buffer[0] = (byte) (val >> 8);
        buffer[1] = (byte) val;
        write(buffer, 0, buffer.length);
    }

    /**
     * Writes the 16-bit characters from a string to this file, starting at the
     * current file pointer. Each character is written in the same way as with
     * {@link #writeChar(int)}, with its high byte first.
     * 
     * @param str
     *            the string to write to this file.
     * @throws IOException
     *             if an I/O error occurs while writing to this file.
     * @see #readChar()
     */
    public final void writeChars(String str) throws IOException {
        byte newBytes[] = new byte[str.length() * 2];
        for (int index = 0; index < str.length(); index++) {
            int newIndex = index == 0 ? index : index * 2;
            newBytes[newIndex] = (byte) ((str.charAt(index) >> 8) & 0xFF);
            newBytes[newIndex + 1] = (byte) (str.charAt(index) & 0xFF);
        }
        write(newBytes);
    }

    /**
     * Writes a 64-bit double to this file, starting at the current file
     * pointer. The eight bytes returned by
     * {@link Double#doubleToLongBits(double)} are written to this file.
     * 
     * @param val
     *            the double to write to this file.
     * @throws IOException
     *             if an I/O error occurs while writing to this file.
     * @see #readDouble()
     */
    public final void writeDouble(double val) throws IOException {
        writeLong(Double.doubleToLongBits(val));
    }

    /**
     * Writes a 32-bit float to this file, starting at the current file pointer.
     * The four bytes returned by {@link Float#floatToIntBits(float)} are
     * written to this file.
     * 
     * @param val
     *            the float to write to this file.
     * @throws IOException
     *             if an I/O error occurs while writing to this file.
     * @see #readFloat()
     */
    public final void writeFloat(float val) throws IOException {
        writeInt(Float.floatToIntBits(val));
    }

    /**
     * Writes a 32-bit integer to this file, starting at the current file
     * pointer. The four bytes of the integer are written with the highest byte
     * first.
     * 
     * @param val
     *            the int to write to this file.
     * @throws IOException
     *             if an I/O error occurs while writing to this file.
     * @see #readInt()
     */
    public final void writeInt(int val) throws IOException {
        byte[] buffer = new byte[4];
        buffer[0] = (byte) (val >> 24);
        buffer[1] = (byte) (val >> 16);
        buffer[2] = (byte) (val >> 8);
        buffer[3] = (byte) val;
        write(buffer, 0, buffer.length);
    }

    /**
     * Writes a 64-bit long to this file, starting at the current file
     * pointer. The eight bytes of the long are written with the highest byte
     * first.
     * 
     * @param val
     *            the long to write to this file.
     * @throws IOException
     *             if an I/O error occurs while writing to this file.
     * @see #readLong()
     */
    public final void writeLong(long val) throws IOException {
        byte[] buffer = new byte[8];
        int t = (int) (val >> 32);
        buffer[0] = (byte) (t >> 24);
        buffer[1] = (byte) (t >> 16);
        buffer[2] = (byte) (t >> 8);
        buffer[3] = (byte) t;
        buffer[4] = (byte) (val >> 24);
        buffer[5] = (byte) (val >> 16);
        buffer[6] = (byte) (val >> 8);
        buffer[7] = (byte) val;
        write(buffer, 0, buffer.length);
    }

    /**
     * Writes a 16-bit short to this file, starting at the current file
     * pointer. Only the two least significant bytes of the integer {@code val}
     * are written, with the high byte first.
     * 
     * @param val
     *            the short to write to this file.
     * @throws IOException
     *             if an I/O error occurs while writing to this file.
     * @see #readShort()
     * @see DataInput#readUnsignedShort()
     */
    public final void writeShort(int val) throws IOException {
        writeChar(val);
    }

    /**
     * Writes a string encoded with {@link DataInput modified UTF-8} to this
     * file, starting at the current file pointer.
     * 
     * @param str
     *            the string to write in {@link DataInput modified UTF-8}
     *            format.
     * @throws IOException
     *             if an I/O error occurs while writing to this file.
     * @throws UTFDataFormatException
     *             if the encoded string is longer than 65535 bytes.
     * @see #readUTF()
     */
    public final void writeUTF(String str) throws IOException {
        int utfCount = 0, length = str.length();
        for (int i = 0; i < length; i++) {
            int charValue = str.charAt(i);
            if (charValue > 0 && charValue <= 127) {
                utfCount++;
            } else if (charValue <= 2047) {
                utfCount += 2;
            } else {
                utfCount += 3;
            }
        }
        if (utfCount > 65535) {
            throw new UTFDataFormatException(Messages.getString("luni.AB")); //$NON-NLS-1$
        }
        byte utfBytes[] = new byte[utfCount + 2];
        int utfIndex = 2;
        for (int i = 0; i < length; i++) {
            int charValue = str.charAt(i);
            if (charValue > 0 && charValue <= 127) {
                utfBytes[utfIndex++] = (byte) charValue;
            } else if (charValue <= 2047) {
                utfBytes[utfIndex++] = (byte) (0xc0 | (0x1f & (charValue >> 6)));
                utfBytes[utfIndex++] = (byte) (0x80 | (0x3f & charValue));
            } else {
                utfBytes[utfIndex++] = (byte) (0xe0 | (0x0f & (charValue >> 12)));
                utfBytes[utfIndex++] = (byte) (0x80 | (0x3f & (charValue >> 6)));
                utfBytes[utfIndex++] = (byte) (0x80 | (0x3f & charValue));
            }
        }
        utfBytes[0] = (byte) (utfCount >> 8);
        utfBytes[1] = (byte) utfCount;
        write(utfBytes);
    }
}
