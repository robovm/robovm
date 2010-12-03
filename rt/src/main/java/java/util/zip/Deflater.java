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


/**
 * This class compresses data using the <i>DEFLATE</i> algorithm (see <a
 * href="http://www.gzip.org/algorithm.txt">specification</a>).
 * <p>
 * Basically this class is part of the API to the stream based ZLIB compression
 * library and is used as such by {@code DeflaterOutputStream} and its
 * descendants.
 * <p>
 * The typical usage of a {@code Deflater} instance outside this package
 * consists of a specific call to one of its constructors before being passed to
 * an instance of {@code DeflaterOutputStream}.
 *
 * @see DeflaterOutputStream
 * @see Inflater
 */
public class Deflater {

    /**
     * Upper bound for the compression level range.
     */
    public static final int BEST_COMPRESSION = 9;

    /**
     * Lower bound for compression level range.
     */
    public static final int BEST_SPEED = 1;

    /**
     * Usage of the default compression level.
     */
    public static final int DEFAULT_COMPRESSION = -1;

    /**
     * Default value for compression strategy.
     */
    public static final int DEFAULT_STRATEGY = 0;

    /**
     * Default value for compression method.
     */
    public static final int DEFLATED = 8;

    /**
     * Possible value for compression strategy.
     */
    public static final int FILTERED = 1;

    /**
     * Possible value for compression strategy.
     */
    public static final int HUFFMAN_ONLY = 2;

    /**
     * Possible value for compression level.
     */
    public static final int NO_COMPRESSION = 0;

    /**
     * Use buffering for best compression.
     */
    static final int Z_NO_FLUSH = 0;

    /**
     * Flush buffers so recipients can immediately decode the data sent thus
     * far. This mode may degrade compression.
     */
    static final int Z_SYNC_FLUSH = 2;

    /**
     * Flush buffers because there is no further data.
     */
    static final int Z_FINISH = 4;

    // Fill in the JNI id caches
    private static native void oneTimeInitialization();

    // A stub buffer used when deflate() called while inputBuffer has not been
    // set.
    private static final byte[] STUB_INPUT_BUFFER = new byte[0];

    static {
        oneTimeInitialization();
    }

    private int flushParm = Z_NO_FLUSH;

    private boolean finished;

    private int compressLevel = DEFAULT_COMPRESSION;

    private int strategy = DEFAULT_STRATEGY;

    private long streamHandle = -1;

    private byte[] inputBuffer;

    private int inRead;

    private int inLength;

    /**
     * Constructs a new {@code Deflater} instance with default compression
     * level. The strategy can be specified with {@link #setStrategy}, only. A
     * header is added to the output by default; use constructor {@code
     * Deflater(level, boolean)} if you need to omit the header.
     */
    public Deflater() {
        this(DEFAULT_COMPRESSION, false);
    }

    /**
     * Constructs a new {@code Deflater} instance with a specific compression
     * level. The strategy can be specified with {@code setStrategy}, only. A
     * header is added to the output by default; use
     * {@code Deflater(level, boolean)} if you need to omit the header.
     *
     * @param level
     *            the compression level in the range between 0 and 9.
     */
    public Deflater(int level) {
        this(level, false);
    }

    /**
     * Constructs a new {@code Deflater} instance with a specific compression
     * level. If noHeader is passed as true no ZLib header is added to the
     * output. In a ZIP archive every entry (compressed file) comes with such a
     * header. The strategy can be specified with the setStrategy method, only.
     *
     * @param level
     *            the compression level in the range between 0 and 9.
     * @param noHeader
     *            {@code true} indicates that no ZLIB header should be written.
     */
    public Deflater(int level, boolean noHeader) {
        super();
        if (level < DEFAULT_COMPRESSION || level > BEST_COMPRESSION) {
            throw new IllegalArgumentException();
        }
        compressLevel = level;
        streamHandle = createStream(compressLevel, strategy, noHeader);
    }

    /**
     * Deflates the data (previously passed to {@code setInput}) into the
     * supplied buffer.
     *
     * @param buf
     *            buffer to write compressed data to.
     * @return number of bytes of compressed data written to {@code buf}.
     * @see #deflate(byte[], int, int)
     */
    public int deflate(byte[] buf) {
        return deflate(buf, 0, buf.length);
    }

    /**
     * Deflates data (previously passed to {@code setInput}) into a specific
     * region within the supplied buffer.
     *
     * @param buf
     *            the buffer to write compressed data to.
     * @param off
     *            the offset within {@code buf} at which to start writing to.
     * @param nbytes
     *            maximum number of bytes of compressed data to be written.
     * @return the number of bytes of compressed data written to {@code buf}.
     */
    public int deflate(byte[] buf, int off, int nbytes) {
        return deflate(buf, off, nbytes, flushParm);
    }

    /**
     * @param flushParam one of {@link #Z_NO_FLUSH}, {@link #Z_FINISH} or
     *            {@link #Z_SYNC_FLUSH}.
     */
    synchronized int deflate(byte[] buf, int off, int nbytes, int flushParam) {
        if (streamHandle == -1) {
            throw new IllegalStateException();
        }
        // avoid int overflow, check null buf
        if (off > buf.length || nbytes < 0 || off < 0 || buf.length - off < nbytes) {
            throw new ArrayIndexOutOfBoundsException();
        }
        // put a stub buffer, no effect.
        if (inputBuffer == null) {
            setInput(STUB_INPUT_BUFFER);
        }
        return deflateImpl(buf, off, nbytes, streamHandle, flushParam);
    }

    private synchronized native int deflateImpl(byte[] buf, int off,
            int nbytes, long handle, int flushParm);

    private synchronized native void endImpl(long handle);

    /**
     * Frees all resources held onto by this deflating algorithm. Any unused
     * input or output is discarded. While this method is used by {@code
     * finalize()}, it can be called explicitly in order to free native
     * resources before the next GC cycle. After {@code end()} was called other
     * methods will typically throw an {@code IllegalStateException}.
     */
    public synchronized void end() {
        if (streamHandle != -1) {
            endImpl(streamHandle);
            inputBuffer = null;
            streamHandle = -1;
        }
    }

    @Override
    protected void finalize() {
        end();
    }

    /**
     * Indicates to the {@code Deflater} that all uncompressed input has been provided
     * to it.
     *
     * @see #finished
     */
    public synchronized void finish() {
        flushParm = Z_FINISH;
    }

    /**
     * Returns whether or not all provided data has been successfully
     * compressed.
     *
     * @return true if all data has been compressed, false otherwise.
     */
    public synchronized boolean finished() {
        return finished;
    }

    /**
     * Returns the Adler32 checksum of uncompressed data currently read. If a
     * preset dictionary is used getAdler() will return the Adler32 checksum of
     * the dictionary used.
     *
     * @return the Adler32 checksum of uncompressed data or preset dictionary if
     *         used.
     * @see #setDictionary(byte[])
     * @see #setDictionary(byte[], int, int)
     */
    public synchronized int getAdler() {
        if (streamHandle == -1) {
            throw new IllegalStateException();
        }

        return getAdlerImpl(streamHandle);
    }

    private synchronized native int getAdlerImpl(long handle);

    /**
     * Returns the total number of bytes of input consumed by the {@code Deflater}.
     *
     * @return number of bytes of input read.
     */
    public synchronized int getTotalIn() {
        if (streamHandle == -1) {
            throw new IllegalStateException();
        }

        return (int) getTotalInImpl(streamHandle);
    }

    private synchronized native long getTotalInImpl(long handle);

    /**
     * Returns the total number of compressed bytes output by this {@code Deflater}.
     *
     * @return number of compressed bytes output.
     */
    public synchronized int getTotalOut() {
        if (streamHandle == -1) {
            throw new IllegalStateException();
        }

        return (int) getTotalOutImpl(streamHandle);
    }

    private synchronized native long getTotalOutImpl(long handle);

    /**
     * Counterpart to setInput(). Indicates whether or not all bytes of
     * uncompressed input have been consumed by the {@code Deflater}. If needsInput()
     * returns true setInput() must be called before deflation can continue. If
     * all bytes of uncompressed data have been provided to the {@code Deflater}
     * finish() must be called to ensure the compressed data is output.
     *
     * @return {@code true} if input is required for deflation to continue,
     *         {@code false} otherwise.
     * @see #finished()
     * @see #setInput(byte[])
     * @see #setInput(byte[], int, int)
     */
    public synchronized boolean needsInput() {
        if (inputBuffer == null) {
            return true;
        }
        return inRead == inLength;
    }

    /**
     * Resets the {@code Deflater} to accept new input without affecting any
     * previously made settings for the compression strategy or level. This
     * operation <i>must</i> be called after {@code finished()} returns
     * {@code true} if the {@code Deflater} is to be reused.
     *
     * @see #finished
     */
    public synchronized void reset() {
        if (streamHandle == -1) {
            throw new NullPointerException();
        }

        flushParm = Z_NO_FLUSH;
        finished = false;
        resetImpl(streamHandle);
        inputBuffer = null;
    }

    private synchronized native void resetImpl(long handle);

    /**
     * Sets the dictionary to be used for compression by this {@code Deflater}.
     * setDictionary() can only be called if this {@code Deflater} supports the writing
     * of ZLIB headers. This is the default behaviour but can be overridden
     * using {@code Deflater(int, boolean)}.
     *
     * @param buf
     *            the buffer containing the dictionary data bytes.
     * @see Deflater#Deflater(int, boolean)
     */
    public void setDictionary(byte[] buf) {
        setDictionary(buf, 0, buf.length);
    }

    /**
     * Sets the dictionary to be used for compression by this {@code Deflater}.
     * setDictionary() can only be called if this {@code Deflater} supports the writing
     * of ZLIB headers. This is the default behaviour but can be overridden
     * using {@code Deflater(int, boolean)}.
     *
     * @param buf
     *            the buffer containing the dictionary data bytes.
     * @param off
     *            the offset of the data.
     * @param nbytes
     *            the length of the data.
     * @see Deflater#Deflater(int, boolean)
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

    private synchronized native void setDictionaryImpl(byte[] buf, int off,
            int nbytes, long handle);

    /**
     * Sets the input buffer the {@code Deflater} will use to extract uncompressed bytes
     * for later compression.
     *
     * @param buf
     *            the buffer.
     */
    public void setInput(byte[] buf) {
        setInput(buf, 0, buf.length);
    }

    /**
     * Sets the input buffer the {@code Deflater} will use to extract uncompressed bytes
     * for later compression. Input will be taken from the buffer region
     * starting at off and ending at nbytes - 1.
     *
     * @param buf
     *            the buffer containing the input data bytes.
     * @param off
     *            the offset of the data.
     * @param nbytes
     *            the length of the data.
     */
    public synchronized void setInput(byte[] buf, int off, int nbytes) {
        if (streamHandle == -1) {
            throw new IllegalStateException();
        }
        // avoid int overflow, check null buf
        if (off <= buf.length && nbytes >= 0 && off >= 0
                && buf.length - off >= nbytes) {
            inLength = nbytes;
            inRead = 0;
            if (inputBuffer == null) {
                setLevelsImpl(compressLevel, strategy, streamHandle);
            }
            inputBuffer = buf;
            setInputImpl(buf, off, nbytes, streamHandle);
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private synchronized native void setLevelsImpl(int level, int strategy,
            long handle);

    private synchronized native void setInputImpl(byte[] buf, int off,
            int nbytes, long handle);

    /**
     * Sets the compression level to be used when compressing data. The
     * compression level must be a value between 0 and 9. This value must be set
     * prior to calling setInput().
     *
     * @param level
     *            compression level to use
     * @exception IllegalArgumentException
     *                If the compression level is invalid.
     */
    public synchronized void setLevel(int level) {
        if (level < DEFAULT_COMPRESSION || level > BEST_COMPRESSION) {
            throw new IllegalArgumentException();
        }
        compressLevel = level;
    }

    /**
     * Sets the compression strategy to be used. The strategy must be one of
     * FILTERED, HUFFMAN_ONLY or DEFAULT_STRATEGY.This value must be set prior
     * to calling setInput().
     *
     * @param strategy
     *            compression strategy to use
     * @exception IllegalArgumentException
     *                If the strategy specified is not one of FILTERED,
     *                HUFFMAN_ONLY or DEFAULT_STRATEGY.
     */
    public synchronized void setStrategy(int strategy) {
        if (strategy < DEFAULT_STRATEGY || strategy > HUFFMAN_ONLY) {
            throw new IllegalArgumentException();
        }
        this.strategy = strategy;
    }

    /**
     * Returns a long int of total number of bytes read by the {@code Deflater}. This
     * method performs the same as {@code getTotalIn} except it returns a long value
     * instead of an integer
     *
     * @see #getTotalIn()
     * @return total number of bytes read by {@code Deflater}.
     */
    public synchronized long getBytesRead() {
        // Throw NPE here
        if (streamHandle == -1) {
            throw new NullPointerException();
        }
        return getTotalInImpl(streamHandle);
    }

    /**
     * Returns a long int of total number of bytes of read by the {@code Deflater}. This
     * method performs the same as {@code getTotalOut} except it returns a long
     * value instead of an integer
     *
     * @see #getTotalOut()
     * @return bytes exactly write by {@code Deflater}
     */
    public synchronized long getBytesWritten() {
        // Throw NPE here
        if (streamHandle == -1) {
            throw new NullPointerException();
        }
        return getTotalOutImpl(streamHandle);
    }

    private native long createStream(int level, int strategy1, boolean noHeader1);
}
