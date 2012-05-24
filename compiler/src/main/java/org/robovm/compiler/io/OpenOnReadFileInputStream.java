/**
 * 
 */
package org.robovm.compiler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * {@link InputStream} which writes to a {@link FileInputStream} but
 * defers opening the {@link FileInputStream} until the first write.
 */
public class OpenOnReadFileInputStream extends InputStream {
    private final File file;
    private InputStream in;
    
    public OpenOnReadFileInputStream(File file) {
        this.file = file;
    }
    
    private void ensureOpen() throws IOException {
        if (in == null) {
            in = new FileInputStream(file);
        }
    }
    
    public void close() throws IOException {
        ensureOpen();
        in.close();
    }

    public int available() throws IOException {
        ensureOpen();
        return in.available();
    }

    public int read() throws IOException {
        ensureOpen();
        return in.read();
    }

    public int read(byte[] b, int off, int len) throws IOException {
        ensureOpen();
        return in.read(b, off, len);
    }

    public int read(byte[] b) throws IOException {
        ensureOpen();
        return in.read(b);
    }

    public long skip(long n) throws IOException {
        ensureOpen();
        return in.skip(n);
    }
}
