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
 * Places information on a communications pipe. When two threads want to pass
 * data back and forth, one creates a piped writer and the other creates a piped
 * reader.
 * 
 * @see PipedReader
 */
public class PipedWriter extends Writer {
    /**
     * The destination PipedReader
     */
    private PipedReader dest;

    private boolean closed;

    /**
     * Constructs a new unconnected {@code PipedWriter}. The resulting writer
     * must be connected to a {@code PipedReader} before data may be written to
     * it.
     *
     * @see PipedReader
     */
    public PipedWriter() {
        super();
    }

    /**
     * Constructs a new {@code PipedWriter} connected to the {@link PipedReader}
     * {@code dest}. Any data written to this writer can be read from {@code
     * dest}.
     * 
     * @param dest
     *            the {@code PipedReader} to connect to.
     * @throws IOException
     *             if {@code dest} is already connected.
     */
    public PipedWriter(PipedReader dest) throws IOException {
        super(dest);
        connect(dest);
    }

    /**
     * Closes this writer. If a {@link PipedReader} is connected to this writer,
     * it is closed as well and the pipe is disconnected. Any data buffered in
     * the reader can still be read.
     * 
     * @throws IOException
     *             if an error occurs while closing this writer.
     */
    @Override
    public void close() throws IOException {
        synchronized (lock) {
            /* Is the pipe connected? */
            if (dest != null) {
                dest.done();
                dest = null;
            }
            closed = true;
        }
    }

    /**
     * Connects this {@code PipedWriter} to a {@link PipedReader}. Any data
     * written to this writer becomes readable in the reader.
     * 
     * @param stream
     *            the reader to connect to.
     * @throws IOException
     *             if this writer is closed or already connected, or if {@code
     *             stream} is already connected.
     */
    public void connect(PipedReader stream) throws IOException {
        synchronized (lock) {
            if (this.dest != null) {
                throw new IOException(Messages.getString("luni.5F")); //$NON-NLS-1$
            }
            if (closed) {
                throw new IOException(Messages.getString("luni.CF")); //$NON-NLS-1$
            }
            stream.establishConnection();
            this.dest = stream;
        }
    }

    /**
     * Notifies the readers of this {@code PipedReader} that characters can be read. This
     * method does nothing if this Writer is not connected.
     * 
     * @throws IOException
     *             if an I/O error occurs while flushing this writer.
     */
    @Override
    public void flush() throws IOException {
        synchronized(lock) {
            if (closed) {
                throw new IOException(Messages.getString("luni.CF")); //$NON-NLS-1$
            }
        }
    	
        if (dest != null) {
            dest.flush();
        }
    }

    /**
     * Writes {@code count} characters from the character array {@code buffer}
     * starting at offset {@code index} to this writer. The written data can
     * then be read from the connected {@link PipedReader} instance.
     * <p>
     * Separate threads should be used to write to a {@code PipedWriter} and to
     * read from the connected {@code PipedReader}. If the same thread is used,
     * a deadlock may occur.
     *
     * @param buffer
     *            the buffer to write.
     * @param offset
     *            the index of the first character in {@code buffer} to write.
     * @param count
     *            the number of characters from {@code buffer} to write to this
     *            writer.
     * @throws IndexOutOfBoundsException
     *             if {@code offset < 0} or {@code count < 0}, or if {@code
     *             offset + count} is bigger than the length of {@code buffer}.
     * @throws InterruptedIOException
     *             if the pipe is full and the current thread is interrupted
     *             waiting for space to write data. This case is not currently
     *             handled correctly.
     * @throws IOException
     *             if this writer is closed or not connected, if the target
     *             reader is closed or if the thread reading from the target
     *             reader is no longer alive. This case is currently not handled
     *             correctly.
     * @throws NullPointerException
     *             if {@code buffer} is {@code null}.
     */
    @Override
    public void write(char buffer[], int offset, int count) throws IOException {
        synchronized (lock) {
            if (closed) {
                throw new IOException(Messages.getString("luni.CF")); //$NON-NLS-1$
            }
            if (dest == null) {
                throw new IOException(Messages.getString("luni.D1")); //$NON-NLS-1$
            }
            if (buffer == null) {
                throw new NullPointerException(Messages.getString("luni.11")); //$NON-NLS-1$
            }

            // avoid int overflow
            if (offset < 0 || offset > buffer.length || count < 0
                    || count > buffer.length - offset) {
                throw new IndexOutOfBoundsException();
            }
            dest.receive(buffer, offset, count);
        }
    }

    /**
     * Writes a single character {@code c} to this writer. This character can
     * then be read from the connected {@link PipedReader} instance.
     * <p>
     * Separate threads should be used to write to a {@code PipedWriter} and to
     * read from the connected {@code PipedReader}. If the same thread is used,
     * a deadlock may occur.
     * 
     * @param c
     *            the character to write.
     * @throws InterruptedIOException
     *             if the pipe is full and the current thread is interrupted
     *             waiting for space to write data. This case is not currently
     *             handled correctly.
     * @throws IOException
     *             if this writer is closed or not connected, if the target
     *             reader is closed or if the thread reading from the target
     *             reader is no longer alive. This case is currently not handled
     *             correctly.
     */
    @Override
    public void write(int c) throws IOException {
        synchronized (lock) {
            if (closed) {
                throw new IOException(Messages.getString("luni.CF")); //$NON-NLS-1$
            }
            if (dest == null) {
                throw new IOException(Messages.getString("luni.D1")); //$NON-NLS-1$
            }
            dest.receive((char) c);
        }
    }
}
