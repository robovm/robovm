/*
 * Copyright (C) 2014 Trillian Mobile AB
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/gpl-2.0.html>.
 */
package org.robovm.compiler.util.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

/**
 * Reads from one {@link InputStream} and writes to an {@link OutputStream}
 * looking for a pattern in the in data.
 */
public abstract class FilteringStreamProxy extends Thread {
    private final int bufferSize;
    protected final InputStream in;
    protected final OutputStream out;

    public FilteringStreamProxy(InputStream in, OutputStream out) {
        this(4096, in, out);
    }

    public FilteringStreamProxy(int bufferSize, InputStream in, OutputStream out) {
        this.bufferSize = bufferSize;
        this.in = in;
        this.out = out;
        setDaemon(true);
        setName(FilteringStreamProxy.class.getSimpleName() + "Thread[" 
                + getClass().getSimpleName() + "]");
    }

    /**
     * Searches for a pattern in the specified buffer. Returns {@code true} when
     * found. It's the responsibility of this method to write any buffered bytes
     * to the specified {@link OutputStream} once the pattern has been found.
     */
    protected abstract boolean findPattern(byte[] b, int length, OutputStream out) 
            throws IOException;

    private byte[] ensureBufferSize(byte[] buffer, int requiredLength) {
        if (buffer.length < requiredLength) {
            byte[] newBuffer = new byte[buffer.length * 2];
            System.arraycopy(buffer, 0, newBuffer, 0, buffer.length);
            buffer = newBuffer;
        }
        return buffer;
    }
    
    public void run() {
        try {
            boolean eof = false;
            // Read byte by byte until we find the pattern we're looking for.
            byte[] buffer = new byte[bufferSize];
            int pos = 0;
            while (!eof && !isInterrupted() && !shouldStop()) {
                int b = in.read();
                if (b == -1) {
                    eof = true;
                    break;
                }
                buffer = ensureBufferSize(buffer, pos + 1);
                buffer[pos++] = (byte) b;
                if (findPattern(buffer, pos, out)) {
                    break;
                }
            }
            // Pattern has been found (or eof reached).
            while (!eof && !isInterrupted() && !shouldStop()) {
                // Now just shuffle data from in to out
                int n = in.read(buffer);
                if (n == -1) {
                    eof = true;
                    break;
                }
                if (n > 0) {
                    out.write(buffer, 0, n);
                    out.flush();
                }
            }
        } catch (Throwable t) {
            handleException(t);
        } finally {
            closeStreams();
        }
    }

    protected void closeStreams() {
        IOUtils.closeQuietly(out);
        IOUtils.closeQuietly(in);
    }

    protected void handleException(Throwable t) {
        t.printStackTrace();
    }

    protected boolean shouldStop() {
        return false;
    }
}
