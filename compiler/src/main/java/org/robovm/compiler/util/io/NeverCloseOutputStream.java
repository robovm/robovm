/*
 * Copyright (C) 2014 RoboVM AB
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
import java.io.OutputStream;

/**
 * {@link OutputStream} which wraps another {@link OutputStream} and forwards
 * all calls to it except {@link OutputStream#close()}.
 */
public class NeverCloseOutputStream extends OutputStream {
    private final OutputStream out;

    public NeverCloseOutputStream(OutputStream out) {
        this.out = out;
    }

    public void write(int b) throws IOException {
        out.write(b);
    }

    public int hashCode() {
        return out.hashCode();
    }

    public void write(byte[] b) throws IOException {
        out.write(b);
    }

    public void write(byte[] b, int off, int len) throws IOException {
        out.write(b, off, len);
    }

    public boolean equals(Object obj) {
        return out.equals(obj);
    }

    public void flush() throws IOException {
        out.flush();
    }

    public void close() throws IOException {
    }

    public String toString() {
        return out.toString();
    }

}
