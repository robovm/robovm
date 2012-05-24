/**
 * 
 */
package org.robovm.compiler;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author niklas
 *
 */
abstract class LoggerOutputStream extends OutputStream {
    protected final Logger logger;
    private byte[] buffer = new byte[1024];
    private int start = 0;
    private int end = 0;

    LoggerOutputStream(Logger logger) {
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