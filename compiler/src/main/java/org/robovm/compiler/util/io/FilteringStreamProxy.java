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
 * 
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
        setName(getClass().getSimpleName());
    }

    protected abstract boolean findPattern(byte[] b, int length, OutputStream out) throws IOException;

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
                if (buffer.length <= pos) {
                    byte[] newBuffer = new byte[buffer.length * 2];
                    System.arraycopy(buffer, 0, newBuffer, 0, buffer.length);
                    buffer = newBuffer;
                }
                buffer[pos++] = (byte) b;
                if (findPattern(buffer, pos, out)) {
                    break;
                }
            }
            // Now just shuffle data from in to out
            while (!eof && !isInterrupted() && !shouldStop()) {
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
