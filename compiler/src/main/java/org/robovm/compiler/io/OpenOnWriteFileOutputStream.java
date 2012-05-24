/**
 * 
 */
package org.robovm.compiler.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * {@link OutputStream} which writes to a {@link FileOutputStream} but
 * defers opening the {@link FileOutputStream} until the first write.
 */
public class OpenOnWriteFileOutputStream extends OutputStream {
    private final File file;
    private OutputStream out;
    
    public OpenOnWriteFileOutputStream(File file) {
        this.file = file;
    }
    
    private void ensureOpen() throws IOException {
        if (out == null) {
            out = new FileOutputStream(file);
        }
    }
    
    public void close() throws IOException {
        ensureOpen();
        out.close();
    }

    public void flush() throws IOException {
        ensureOpen();
        out.flush();
    }

    public void write(int b) throws IOException {
        ensureOpen();
        out.write(b);
    }
    
    public void write(byte[] b) throws IOException {
        ensureOpen();
        out.write(b);
    }
    
    public void write(byte[] b, int off, int len) throws IOException {
        ensureOpen();
        out.write(b, off, len);
    }
}