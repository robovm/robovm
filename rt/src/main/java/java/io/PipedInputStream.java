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

import org.apache.harmony.luni.internal.nls.Messages;

/**
 * Receives information from a communications pipe. When two threads want to
 * pass data back and forth, one creates a piped output stream and the other one
 * creates a piped input stream.
 * 
 * @see PipedOutputStream
 */
public class PipedInputStream extends InputStream {

    private Thread lastReader, lastWriter;

    private boolean isClosed = false;

    /**
     * The circular buffer through which data is passed.
     */
    protected byte buffer[];

    /**
     * The index in {@code buffer} where the next byte will be written.
     */
    protected int in = -1;

    /**
     * The index in {@code buffer} where the next byte will be read.
     */
    protected int out = 0;

    /**
     * The size of the default pipe in bytes.
     */
    protected static final int PIPE_SIZE = 1024;

    /**
     * Indicates if this pipe is connected.
     */
    boolean isConnected = false;

    /**
     * Constructs a new unconnected {@code PipedInputStream}. The resulting
     * stream must be connected to a {@link PipedOutputStream} before data may
     * be read from it.
     */
    public PipedInputStream() {
        /* empty */
    }

    /**
     * Constructs a new {@code PipedInputStream} connected to the
     * {@link PipedOutputStream} {@code out}. Any data written to the output
     * stream can be read from the this input stream.
     * 
     * @param out
     *            the piped output stream to connect to.
     * @throws IOException
     *             if this stream or {@code out} are already connected.
     */
    public PipedInputStream(PipedOutputStream out) throws IOException {
        connect(out);
    }
    
    /**
     * Constructs a new PipedInputStream connected to the PipedOutputStream
     * <code>out</code> and uses the specified buffer size. Any data written
     * to the output stream can be read from this input stream.
     * 
     * @param out
     *            the PipedOutputStream to connect to.
     * @param pipeSize
     *            the size of the buffer.
     * @throws IOException
     *             if an I/O error occurs.
     * @throws IllegalArgumentException
     *             if pipeSize is less than or equal to zero.
     * @since 1.6
     */
    public PipedInputStream(PipedOutputStream out, int pipeSize)
            throws IOException {
        this(pipeSize);
        connect(out);
    }

    /**
     * Constructs a new unconnected PipedInputStream and uses the specified
     * buffer size. The resulting Stream must be connected to a
     * PipedOutputStream before data may be read from it.
     * 
     * @param pipeSize
     *            the size of the buffer.
     * @throws IllegalArgumentException
     *             if pipeSize is less than or equal to zero.
     * @since 1.6
     */
    public PipedInputStream(int pipeSize) {
        if (pipeSize <= 0) {
            throw new IllegalArgumentException();
        }
        buffer = new byte[pipeSize];
    }

    /**
     * Returns the number of bytes that are available before this stream will
     * block. This implementation returns the number of bytes written to this
     * pipe that have not been read yet.
     * 
     * @return the number of bytes available before blocking.
     * @throws IOException
     *             if an error occurs in this stream.
     */
    @Override
    public synchronized int available() throws IOException {
        if (buffer == null || in == -1) {
            return 0;
        }
        return in <= out ? buffer.length - out + in : in - out;
    }

    /**
     * Closes this stream. This implementation releases the buffer used for the
     * pipe and notifies all threads waiting to read or write.
     * 
     * @throws IOException
     *             if an error occurs while closing this stream.
     */
    @Override
    public void close() throws IOException {
        synchronized (this) {
            /* No exception thrown if already closed */
            if (buffer != null) {
                /* Release buffer to indicate closed. */
                buffer = null;
            }
        }
    }

    /**
     * Connects this {@code PipedInputStream} to a {@link PipedOutputStream}.
     * Any data written to the output stream becomes readable in this input
     * stream.
     * 
     * @param src
     *            the source output stream.
     * @throws IOException
     *             if either stream is already connected.
     */
    public void connect(PipedOutputStream src) throws IOException {
        src.connect(this);
    }

    /**
     * Reads a single byte from this stream and returns it as an integer in the
     * range from 0 to 255. Returns -1 if the end of this stream has been
     * reached. If there is no data in the pipe, this method blocks until data
     * is available, the end of the stream is detected or an exception is
     * thrown.
     * <p>
     * Separate threads should be used to read from a {@code PipedInputStream}
     * and to write to the connected {@link PipedOutputStream}. If the same
     * thread is used, a deadlock may occur.
     *
     * @return the byte read or -1 if the end of the source stream has been
     *         reached.
     * @throws IOException
     *             if this stream is closed or not connected to an output
     *             stream, or if the thread writing to the connected output
     *             stream is no longer alive.
     */
    @Override
    public synchronized int read() throws IOException {
        if (!isConnected) {
            // luni.CB=Not connected
            throw new IOException(Messages.getString("luni.CB")); //$NON-NLS-1$
        }
        if (buffer == null) {
            // luni.CC=InputStream is closed
            throw new IOException(Messages.getString("luni.CC")); //$NON-NLS-1$
        }

        if (lastWriter != null && !lastWriter.isAlive() && (in < 0)) {
            // luni.CD=Write end dead
            throw new IOException(Messages.getString("luni.CD")); //$NON-NLS-1$
        }
        /**
         * Set the last thread to be reading on this PipedInputStream. If
         * lastReader dies while someone is waiting to write an IOException of
         * "Pipe broken" will be thrown in receive()
         */
        lastReader = Thread.currentThread();
        try {
            int attempts = 3;
            while (in == -1) {
                // Are we at end of stream?
                if (isClosed) {
                    return -1;
                }
                if ((attempts-- <= 0) && lastWriter != null && !lastWriter.isAlive()) {
                    // luni.CE=Pipe broken
                    throw new IOException(Messages.getString("luni.CE")); //$NON-NLS-1$
                }
                // Notify callers of receive()
                notifyAll();
                wait(1000);
            }
        } catch (InterruptedException e) {
            throw new InterruptedIOException();
        }

        byte result = buffer[out++];
        if (out == buffer.length) {
            out = 0;
        }
        if (out == in) {
            // empty buffer
            in = -1;
            out = 0;
        }
        return result & 0xff;
    }

    /**
     * Reads at most {@code count} bytes from this stream and stores them in the
     * byte array {@code bytes} starting at {@code offset}. Blocks until at
     * least one byte has been read, the end of the stream is detected or an
     * exception is thrown.
     * <p>
     * Separate threads should be used to read from a {@code PipedInputStream}
     * and to write to the connected {@link PipedOutputStream}. If the same
     * thread is used, a deadlock may occur.
     *
     * @param bytes
     *            the array in which to store the bytes read.
     * @param offset
     *            the initial position in {@code bytes} to store the bytes
     *            read from this stream.
     * @param count
     *            the maximum number of bytes to store in {@code bytes}.
     * @return the number of bytes actually read or -1 if the end of the stream
     *         has been reached.
     * @throws IndexOutOfBoundsException
     *             if {@code offset < 0} or {@code count < 0}, or if {@code
     *             offset + count} is greater than the size of {@code bytes}.
     * @throws InterruptedIOException
     *             if the thread reading from this stream is interrupted.
     * @throws IOException
     *             if this stream is closed or not connected to an output
     *             stream, or if the thread writing to the connected output
     *             stream is no longer alive.
     * @throws NullPointerException
     *             if {@code bytes} is {@code null}.
     */
    @Override
    public synchronized int read(byte[] bytes, int offset, int count)
            throws IOException {
        if (bytes == null) {
            throw new NullPointerException();
        }

        if (offset < 0 || offset > bytes.length || count < 0
                || count > bytes.length - offset) {
            throw new IndexOutOfBoundsException();
        }

        if (count == 0) {
            return 0;
        }

        if (!isConnected) {
            // luni.CB=Not connected
            throw new IOException(Messages.getString("luni.CB")); //$NON-NLS-1$
        }

        if (buffer == null) {
            // luni.CC=InputStream is closed
            throw new IOException(Messages.getString("luni.CC")); //$NON-NLS-1$
        }

        if (lastWriter != null && !lastWriter.isAlive() && (in < 0)) {
            // luni.CD=Write end dead
            throw new IOException(Messages.getString("luni.CD")); //$NON-NLS-1$
        }

        /**
         * Set the last thread to be reading on this PipedInputStream. If
         * lastReader dies while someone is waiting to write an IOException of
         * "Pipe broken" will be thrown in receive()
         */
        lastReader = Thread.currentThread();
        try {
            int attempts = 3;
            while (in == -1) {
                // Are we at end of stream?
                if (isClosed) {
                    return -1;
                }
                if ((attempts-- <= 0) && lastWriter != null && !lastWriter.isAlive()) {
                    // luni.CE=Pipe broken
                    throw new IOException(Messages.getString("luni.CE")); //$NON-NLS-1$
                }
                // Notify callers of receive()
                notifyAll();
                wait(1000);
            }
        } catch (InterruptedException e) {
            throw new InterruptedIOException();
        }

        int copyLength = 0;
        /* Copy bytes from out to end of buffer first */
        if (out >= in) {
            copyLength = count > (buffer.length - out) ? buffer.length - out
                    : count;
            System.arraycopy(buffer, out, bytes, offset, copyLength);
            out += copyLength;
            if (out == buffer.length) {
                out = 0;
            }
            if (out == in) {
                // empty buffer
                in = -1;
                out = 0;
            }
        }

        /*
         * Did the read fully succeed in the previous copy or is the buffer
         * empty?
         */
        if (copyLength == count || in == -1) {
            return copyLength;
        }

        int bytesCopied = copyLength;
        /* Copy bytes from 0 to the number of available bytes */
        copyLength = in - out > (count - bytesCopied) ? count - bytesCopied
                : in - out;
        System.arraycopy(buffer, out, bytes, offset + bytesCopied, copyLength);
        out += copyLength;
        if (out == in) {
            // empty buffer
            in = -1;
            out = 0;
        }
        return bytesCopied + copyLength;
    }

    /**
     * Receives a byte and stores it in this stream's {@code buffer}. This
     * method is called by {@link PipedOutputStream#write(int)}. The least
     * significant byte of the integer {@code oneByte} is stored at index
     * {@code in} in the {@code buffer}.
     * <p>
     * This method blocks as long as {@code buffer} is full.
     * 
     * @param oneByte
     *            the byte to store in this pipe.
     * @throws InterruptedIOException
     *             if the {@code buffer} is full and the thread that has called
     *             this method is interrupted.
     * @throws IOException
     *             if this stream is closed or the thread that has last read
     *             from this stream is no longer alive.
     */
    protected synchronized void receive(int oneByte) throws IOException {
        if (buffer == null || isClosed) {
            throw new IOException(Messages.getString("luni.CF")); //$NON-NLS-1$
        }
        if (lastReader != null && !lastReader.isAlive()) {
            throw new IOException(Messages.getString("luni.CE")); //$NON-NLS-1$
        }
        /**
         * Set the last thread to be writing on this PipedInputStream. If
         * lastWriter dies while someone is waiting to read an IOException of
         * "Pipe broken" will be thrown in read()
         */
        lastWriter = Thread.currentThread();
        try {
            while (buffer != null && out == in) {
                notifyAll();
                wait(1000);
                if (lastReader != null && !lastReader.isAlive()) {
                    throw new IOException(Messages.getString("luni.CE")); //$NON-NLS-1$
                }
            }
        } catch (InterruptedException e) {
            throw new InterruptedIOException();
        }
        if (buffer != null) {
            if (in == -1) {
                in = 0;
            }
            buffer[in++] = (byte) oneByte;
            if (in == buffer.length) {
                in = 0;
            }
            return;
        }
    }

    synchronized void done() {
        isClosed = true;
        notifyAll();
    }
}
