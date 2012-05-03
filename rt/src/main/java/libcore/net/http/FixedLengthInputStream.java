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

/**
 * An HTTP body with a fixed length specified in advance.
 */
final class FixedLengthInputStream extends AbstractHttpInputStream {
    private int bytesRemaining;

    public FixedLengthInputStream(InputStream is, CacheRequest cacheRequest,
            HttpEngine httpEngine, int length) throws IOException {
        super(is, httpEngine, cacheRequest);
        bytesRemaining = length;
        if (bytesRemaining == 0) {
            endOfInput(true);
        }
    }

    @Override public int read(byte[] buffer, int offset, int count) throws IOException {
        Arrays.checkOffsetAndCount(buffer.length, offset, count);
        checkNotClosed();
        if (bytesRemaining == 0) {
            return -1;
        }
        int read = in.read(buffer, offset, Math.min(count, bytesRemaining));
        if (read == -1) {
            unexpectedEndOfInput(); // the server didn't supply the promised content length
            throw new IOException("unexpected end of stream");
        }
        bytesRemaining -= read;
        cacheWrite(buffer, offset, read);
        if (bytesRemaining == 0) {
            endOfInput(true);
        }
        return read;
    }

    @Override public int available() throws IOException {
        checkNotClosed();
        return bytesRemaining == 0 ? 0 : Math.min(in.available(), bytesRemaining);
    }

    @Override public void close() throws IOException {
        if (closed) {
            return;
        }
        closed = true;
        if (bytesRemaining != 0) {
            unexpectedEndOfInput();
        }
    }
}
