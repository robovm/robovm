/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java.util.zip;

import org.apache.harmony.archive.internal.nls.Messages;

/**
 * This class uncompresses data that was compressed using the <i>DEFLATE</i>
 * algorithm (see <a href="http://www.gzip.org/algorithm.txt">specification</a>).
 * <p>
 * Basically this class is part of the API to the stream based ZLIB compression
 * library and is used as such by {@code InflaterInputStream} and its
 * descendants.
 * <p>
 * The typical usage of a {@code Inflater} outside this package consists of a
 * specific call to one of its constructors before being passed to an instance
 * of {@code InflaterInputStream}.
 *
 * @see InflaterInputStream
 * @see Deflater
 */
public class Inflater {

    static {
        oneTimeInitialization();
    }

    // Fill in the JNI id caches
    private static native void oneTimeInitialization();

    private boolean finished; // Set by the inflateImpl native

    int inLength;

    int inRead;

    private boolean needsDictionary; // Set by the inflateImpl native

    private long streamHandle = -1;

    /**
     * This constructor creates an inflater that expects a header from the input
     * stream. Use {@code Inflater(boolean)} if the input comes without a ZLIB
     * header.
     */
    public Inflater() {
        this(false);
    }

    /**
     * This constructor allows to create an inflater that expects no header from
     * the input stream.
     *
     * @param noHeader
     *            {@code true} indicates that no ZLIB header comes with the
     *            input.
     */
    public Inflater(boolean noHeader) {
        streamHandle = createStream(noHeader);
    }

    private native long createStream(boolean noHeader1);

    /**
     * Release any resources associated with this {@code Inflater}. Any unused
     * input/output is discarded. This is also called by the finalize method.
     */
    public synchronized void end() {
        if (streamHandle != -1) {
            endImpl(streamHandle);
            inRead = 0;
            inLength = 0;
            streamHandle = -1;
        }
    }

    private native synchronized void endImpl(long handle);

    @Override
    protected void finalize() {
        end();
    }

    /**
     * Indicates if the {@code Inflater} has inflated the entire deflated
     * stream. If deflated bytes remain and {@code needsInput()} returns {@code
     * true} this method will return {@code false}. This method should be
     * called after all deflated input is supplied to the {@code Inflater}.
     *
     * @return {@code true} if all input has been inflated, {@code false}
     *         otherwise.
     */
    public synchronized boolean finished() {
        return finished;
    }

    /**
     * Returns the <i>Adler32</i> checksum of either all bytes inflated, or the
     * checksum of the preset dictionary if one has been supplied.
     *
     * @return The <i>Adler32</i> checksum associated with this
     *         {@code Inflater}.
     */
    public synchronized int getAdler() {
        if (streamHandle == -1) {
            throw new IllegalStateException();
        }
        return getAdlerImpl(streamHandle);
    }

    private native synchronized int getAdlerImpl(long handle);

    /**
     * Returns the total number of bytes read by the {@code Inflater}. This
     * method performs the same as {@code getTotalIn()} except that it returns a
     * {@code long} value instead of an integer.
     *
     * @return the total number of bytes read.
     */
    public synchronized long getBytesRead() {
        // Throw NPE here
        if (streamHandle == -1) {
            throw new NullPointerException();
        }
        return getTotalInImpl(streamHandle);
    }

    /**
     * Returns a the total number of bytes read by the {@code Inflater}. This
     * method performs the same as {@code getTotalOut} except it returns a
     * {@code long} value instead of an integer.
     *
     * @return the total bytes written to the output buffer.
     */
    public synchronized long getBytesWritten() {
        // Throw NPE here
        if (streamHandle == -1) {
            throw new NullPointerException();
        }
        return getTotalOutImpl(streamHandle);
    }

    /**
     * Returns the number of bytes of current input remaining to be read by the
     * inflater.
     *
     * @return the number of bytes of unread input.
     */
    public synchronized int getRemaining() {
        return inLength - inRead;
    }

    /**
     * Returns total number of bytes of input read by the {@code Inflater}. The
     * result value is limited by {@code Integer.MAX_VALUE}.
     *
     * @return the total number of bytes read.
     */
    public synchronized int getTotalIn() {
        if (streamHandle == -1) {
            throw new IllegalStateException();
        }
        long totalIn = getTotalInImpl(streamHandle);
        return (totalIn <= Integer.MAX_VALUE ? (int) totalIn
                : Integer.MAX_VALUE);
    }

    private synchronized native long getTotalInImpl(long handle);

    /**
     * Returns total number of bytes written to the output buffer by the {@code
     * Inflater}. The result value is limited by {@code Integer.MAX_VALUE}.
     *
     * @return the total bytes of output data written.
     */
    public synchronized int getTotalOut() {
        if (streamHandle == -1) {
            throw new IllegalStateException();
        }
        long totalOut = getTotalOutImpl(streamHandle);
        return (totalOut <= Integer.MAX_VALUE ? (int) totalOut
                : Integer.MAX_VALUE);
    }

    private native synchronized long getTotalOutImpl(long handle);

    /**
     * Inflates bytes from current input and stores them in {@code buf}.
     *
     * @param buf
     *            the buffer where decompressed data bytes are written.
     * @return the number of bytes inflated.
     * @throws DataFormatException
     *             if the underlying stream is corrupted or was not compressed
     *             using a {@code Deflater}.
     */
    public int inflate(byte[] buf) throws DataFormatException {
        return inflate(buf, 0, buf.length);
    }

    /**
     * Inflates up to n bytes from the current input and stores them in {@code
     * buf} starting at {@code off}.
     *
     * @param buf
     *            the buffer to write inflated bytes to.
     * @param off
     *            the offset in buffer where to start writing decompressed data.
     * @param nbytes
     *            the number of inflated bytes to write to {@code buf}.
     * @throws DataFormatException
     *             if the underlying stream is corrupted or was not compressed
     *             using a {@code Deflater}.
     * @return the number of bytes inflated.
     */
    public synchronized int inflate(byte[] buf, int off, int nbytes)
            throws DataFormatException {
        // avoid int overflow, check null buf
        if (off > buf.length || nbytes < 0 || off < 0
                || buf.length - off < nbytes) {
            throw new ArrayIndexOutOfBoundsException();
        }

        if (nbytes == 0) {
            return 0;
        }

        if (streamHandle == -1) {
            throw new IllegalStateException();
        }

        if (needsInput()) {
            return 0;
        }

        boolean neededDict = needsDictionary;
        needsDictionary = false;
        int result = inflateImpl(buf, off, nbytes, streamHandle);
        if (needsDictionary && neededDict) {
            throw new DataFormatException(
                    Messages.getString("archive.27")); //$NON-NLS-1$
        }

        return result;
    }

    private native synchronized int inflateImpl(byte[] buf, int off,
            int nbytes, long handle);

    /**
     * Indicates whether the input bytes were compressed with a preset
     * dictionary. This method should be called prior to {@code inflate()} to
     * determine whether a dictionary is required. If so {@code setDictionary()}
     * should be called with the appropriate dictionary prior to calling {@code
     * inflate()}.
     *
     * @return {@code true} if a preset dictionary is required for inflation.
     * @see #setDictionary(byte[])
     * @see #setDictionary(byte[], int, int)
     */
    public synchronized boolean needsDictionary() {
        return needsDictionary;
    }

    /**
     * Indicates that input has to be passed to the inflater.
     *
     * @return {@code true} if {@code setInput} has to be called before
     *         inflation can proceed.
     * @see #setInput(byte[])
     */
    public synchronized boolean needsInput() {
        return inRead == inLength;
    }

    /**
     * Resets the {@code Inflater}. Should be called prior to inflating a new
     * set of data.
     */
    public synchronized void reset() {
        if (streamHandle == -1) {
            throw new NullPointerException();
        }
        finished = false;
        needsDictionary = false;
        inLength = inRead = 0;
        resetImpl(streamHandle);
    }

    private native synchronized void resetImpl(long handle);

    /**
     * Sets the preset dictionary to be used for inflation to {@code buf}.
     * {@code needsDictionary()} can be called to determine whether the current
     * input was deflated using a preset dictionary.
     *
     * @param buf
     *            The buffer containing the dictionary bytes.
     * @see #needsDictionary
     */
    public synchronized void setDictionary(byte[] buf) {
        setDictionary(buf, 0, buf.length);
    }

    /**
     * Like {@code setDictionary(byte[])}, allowing to define a specific region
     * inside {@code buf} to be used as a dictionary.
     * <p>
     * The dictionary should be set if the {@link #inflate(byte[])} returned
     * zero bytes inflated and {@link #needsDictionary()} returns
     * <code>true</code>.
     *
     * @param buf
     *            the buffer containing the dictionary data bytes.
     * @param off
     *            the offset of the data.
     * @param nbytes
     *            the length of the data.
     * @see #needsDictionary
     */
    public synchronized void setDictionary(byte[] buf, int off, int nbytes) {
        if (streamHandle == -1) {
            throw new IllegalStateException();
        }
        // avoid int overflow, check null buf
        if (off <= buf.length && nbytes >= 0 && off >= 0
                && buf.length - off >= nbytes) {
            setDictionaryImpl(buf, off, nbytes, streamHandle);
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private native synchronized void setDictionaryImpl(byte[] buf, int off,
            int nbytes, long handle);

    /**
     * Sets the current input to be decompressed. This method should only be
     * called if {@code needsInput()} returns {@code true}.
     *
     * @param buf
     *            the input buffer.
     * @see #needsInput
     */
    public synchronized void setInput(byte[] buf) {
        setInput(buf, 0, buf.length);
    }

    /**
     * Sets the current input to the region of the input buffer starting at
     * {@code off} and ending at {@code nbytes - 1} where data is written after
     * decompression. This method should only be called if {@code needsInput()}
     * returns {@code true}.
     *
     * @param buf
     *            the input buffer.
     * @param off
     *            the offset to read from the input buffer.
     * @param nbytes
     *            the number of bytes to read.
     * @see #needsInput
     */
    public synchronized void setInput(byte[] buf, int off, int nbytes) {
        if (streamHandle == -1) {
            throw new IllegalStateException();
        }
        // avoid int overflow, check null buf
        if (off <= buf.length && nbytes >= 0 && off >= 0
                && buf.length - off >= nbytes) {
            inRead = 0;
            inLength = nbytes;
            setInputImpl(buf, off, nbytes, streamHandle);
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private native synchronized void setInputImpl(byte[] buf, int off,
            int nbytes, long handle);
}
