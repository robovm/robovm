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
import java.io.OutputStream;
import java.util.Arrays;

/**
 * An HTTP body with a fixed length known in advance.
 */
final class FixedLengthOutputStream extends AbstractHttpOutputStream {
    private final OutputStream socketOut;
    private int bytesRemaining;

    public FixedLengthOutputStream(OutputStream socketOut, int bytesRemaining) {
        this.socketOut = socketOut;
        this.bytesRemaining = bytesRemaining;
    }

    @Override public void write(byte[] buffer, int offset, int count) throws IOException {
        checkNotClosed();
        Arrays.checkOffsetAndCount(buffer.length, offset, count);
        if (count > bytesRemaining) {
            throw new IOException("expected " + bytesRemaining + " bytes but received " + count);
        }
        socketOut.write(buffer, offset, count);
        bytesRemaining -= count;
    }

    @Override public void flush() throws IOException {
        if (closed) {
            return; // don't throw; this stream might have been closed on the caller's behalf
        }
        socketOut.flush();
    }

    @Override public void close() throws IOException {
        if (closed) {
            return;
        }
        closed = true;
        if (bytesRemaining > 0) {
            throw new IOException("unexpected end of stream");
        }
    }
}
