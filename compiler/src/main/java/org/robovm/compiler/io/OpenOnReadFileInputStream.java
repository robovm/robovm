/*
 * Copyright (C) 2012 Trillian AB
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
