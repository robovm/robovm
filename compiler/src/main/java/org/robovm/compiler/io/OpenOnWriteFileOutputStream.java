/*
 * Copyright (C) 2012 RoboVM
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
