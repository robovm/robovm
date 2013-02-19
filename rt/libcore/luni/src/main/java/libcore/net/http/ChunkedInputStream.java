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

import java.io.IOException;
import java.io.InputStream;
import java.net.CacheRequest;
import java.util.Arrays;
import libcore.io.Streams;

/**
 * An HTTP body with alternating chunk sizes and chunk bodies.
 */
final class ChunkedInputStream extends AbstractHttpInputStream {
    private static final int MIN_LAST_CHUNK_LENGTH = "\r\n0\r\n\r\n".length();
    private static final int NO_CHUNK_YET = -1;
    private int bytesRemainingInChunk = NO_CHUNK_YET;
    private boolean hasMoreChunks = true;

    ChunkedInputStream(InputStream is, CacheRequest cacheRequest,
            HttpEngine httpEngine) throws IOException {
        super(is, httpEngine, cacheRequest);
    }

    @Override public int read(byte[] buffer, int offset, int count) throws IOException {
        Arrays.checkOffsetAndCount(buffer.length, offset, count);
        checkNotClosed();

        if (!hasMoreChunks) {
            return -1;
        }
        if (bytesRemainingInChunk == 0 || bytesRemainingInChunk == NO_CHUNK_YET) {
            readChunkSize();
            if (!hasMoreChunks) {
                return -1;
            }
        }
        int read = in.read(buffer, offset, Math.min(count, bytesRemainingInChunk));
        if (read == -1) {
            unexpectedEndOfInput(); // the server didn't supply the promised chunk length
            throw new IOException("unexpected end of stream");
        }
        bytesRemainingInChunk -= read;
        cacheWrite(buffer, offset, read);

        /*
         * If we're at the end of a chunk and the next chunk size is readable,
         * read it! Reading the last chunk causes the underlying connection to
         * be recycled and we want to do that as early as possible. Otherwise
         * self-delimiting streams like gzip will never be recycled.
         * http://code.google.com/p/android/issues/detail?id=7059
         */
        if (bytesRemainingInChunk == 0 && in.available() >= MIN_LAST_CHUNK_LENGTH) {
            readChunkSize();
        }

        return read;
    }

    private void readChunkSize() throws IOException {
        // read the suffix of the previous chunk
        if (bytesRemainingInChunk != NO_CHUNK_YET) {
            Streams.readAsciiLine(in);
        }
        String chunkSizeString = Streams.readAsciiLine(in);
        int index = chunkSizeString.indexOf(";");
        if (index != -1) {
            chunkSizeString = chunkSizeString.substring(0, index);
        }
        try {
            bytesRemainingInChunk = Integer.parseInt(chunkSizeString.trim(), 16);
        } catch (NumberFormatException e) {
            throw new IOException("Expected a hex chunk size, but was " + chunkSizeString);
        }
        if (bytesRemainingInChunk == 0) {
            hasMoreChunks = false;
            httpEngine.readTrailers();
            endOfInput(true);
        }
    }

    @Override public int available() throws IOException {
        checkNotClosed();
        if (!hasMoreChunks || bytesRemainingInChunk == NO_CHUNK_YET) {
            return 0;
        }
        return Math.min(in.available(), bytesRemainingInChunk);
    }

    @Override public void close() throws IOException {
        if (closed) {
            return;
        }

        closed = true;
        if (hasMoreChunks) {
            unexpectedEndOfInput();
        }
    }
}
