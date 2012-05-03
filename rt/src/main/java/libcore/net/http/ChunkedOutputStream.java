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

package libcore.net.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * An HTTP body with alternating chunk sizes and chunk bodies. Chunks are
 * buffered until {@code maxChunkLength} bytes are ready, at which point the
 * chunk is written and the buffer is cleared.
 */
final class ChunkedOutputStream extends AbstractHttpOutputStream {
    private static final byte[] CRLF = { '\r', '\n' };
    private static final byte[] HEX_DIGITS = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };
    private static final byte[] FINAL_CHUNK = new byte[] { '0', '\r', '\n', '\r', '\n' };

    /** Scratch space for up to 8 hex digits, and then a constant CRLF */
    private final byte[] hex = { 0, 0, 0, 0, 0, 0, 0, 0, '\r', '\n' };

    private final OutputStream socketOut;
    private final int maxChunkLength;
    private final ByteArrayOutputStream bufferedChunk;


    public ChunkedOutputStream(OutputStream socketOut, int maxChunkLength) {
        this.socketOut = socketOut;
        this.maxChunkLength = Math.max(1, dataLength(maxChunkLength));
        this.bufferedChunk = new ByteArrayOutputStream(maxChunkLength);
    }

    /**
     * Returns the amount of data that can be transmitted in a chunk whose total
     * length (data+headers) is {@code dataPlusHeaderLength}. This is presumably
     * useful to match sizes with wire-protocol packets.
     */
    private int dataLength(int dataPlusHeaderLength) {
        int headerLength = 4; // "\r\n" after the size plus another "\r\n" after the data
        for (int i = dataPlusHeaderLength - headerLength; i > 0; i >>= 4) {
            headerLength++;
        }
        return dataPlusHeaderLength - headerLength;
    }

    @Override public synchronized void write(byte[] buffer, int offset, int count)
            throws IOException {
        checkNotClosed();
        Arrays.checkOffsetAndCount(buffer.length, offset, count);

        while (count > 0) {
            int numBytesWritten;

            if (bufferedChunk.size() > 0 || count < maxChunkLength) {
                // fill the buffered chunk and then maybe write that to the stream
                numBytesWritten = Math.min(count, maxChunkLength - bufferedChunk.size());
                // TODO: skip unnecessary copies from buffer->bufferedChunk?
                bufferedChunk.write(buffer, offset, numBytesWritten);
                if (bufferedChunk.size() == maxChunkLength) {
                    writeBufferedChunkToSocket();
                }

            } else {
                // write a single chunk of size maxChunkLength to the stream
                numBytesWritten = maxChunkLength;
                writeHex(numBytesWritten);
                socketOut.write(buffer, offset, numBytesWritten);
                socketOut.write(CRLF);
            }

            offset += numBytesWritten;
            count -= numBytesWritten;
        }
    }

    /**
     * Equivalent to, but cheaper than writing Integer.toHexString().getBytes()
     * followed by CRLF.
     */
    private void writeHex(int i) throws IOException {
        int cursor = 8;
        do {
            hex[--cursor] = HEX_DIGITS[i & 0xf];
        } while ((i >>>= 4) != 0);
        socketOut.write(hex, cursor, hex.length - cursor);
    }

    @Override public synchronized void flush() throws IOException {
        if (closed) {
            return; // don't throw; this stream might have been closed on the caller's behalf
        }
        writeBufferedChunkToSocket();
        socketOut.flush();
    }

    @Override public synchronized void close() throws IOException {
        if (closed) {
            return;
        }
        closed = true;
        writeBufferedChunkToSocket();
        socketOut.write(FINAL_CHUNK);
    }

    private void writeBufferedChunkToSocket() throws IOException {
        int size = bufferedChunk.size();
        if (size <= 0) {
            return;
        }

        writeHex(size);
        bufferedChunk.writeTo(socketOut);
        bufferedChunk.reset();
        socketOut.write(CRLF);
    }
}
