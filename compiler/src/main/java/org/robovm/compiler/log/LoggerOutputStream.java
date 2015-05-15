/*
 * Copyright (C) 2012 RoboVM AB
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
package org.robovm.compiler.log;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author niklas
 *
 */
public abstract class LoggerOutputStream extends OutputStream {
    protected final Logger logger;
    private byte[] buffer = new byte[1024];
    private int start = 0;
    private int end = 0;

    public LoggerOutputStream(Logger logger) {
        this.logger = logger;
    }
    
    protected abstract void log(byte[] message, int off, int length);
    
    @Override
    public void close() throws IOException {
        if (start < end) {
            log(buffer, start, end - start);
        }
        super.close();
    }
    
    @Override
    public void write(int b) throws IOException {
        if (b == '\r') {
            // Skip
            return;
        }
        if (b == '\n') {
            log(buffer, start, end - start);
            start = end = 0;
            return;
        }
        if (end == buffer.length) {
            if (start > 0) {
                // Compact
                System.arraycopy(buffer, start, buffer, 0, end - start);
                end -= start;
                start = 0;
            } else {
                // Need a bigger buffer
                byte[] newBuffer = new byte[buffer.length * 2];
                System.arraycopy(buffer, 0, newBuffer, 0, buffer.length);
                buffer = newBuffer;
            }
        }
        buffer[end++] = (byte) b;
    }
    
}
