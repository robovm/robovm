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

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;

/**
 * Tests {@link FilteringStreamProxy}.
 */
public class FilteringStreamProxyTest {

    @Test
    public void testFindPattern() throws Exception {
        class TestProxy extends FilteringStreamProxy {
            int foobar = -1;
            public TestProxy(InputStream in, OutputStream out) {
                super(in, out);
            }
            protected boolean findPattern(byte[] b, int length, OutputStream out) throws IOException {
                String s = new String(b, 0, length, "utf-8");
                if (!s.endsWith("\n")) {
                    // Only consider complete lines
                    return false;
                }
                int idx = s.lastIndexOf("foobar=");
                if (idx == -1) {
                    return false;
                }
                foobar = Integer.parseInt(s.substring(idx + "foobar=".length()).trim());
                out.write(b, 0, idx);
                return true;
            }
        }
        
        ByteArrayInputStream in = new ByteArrayInputStream("line 1\nline 2\nfoobar=12345\nline 4\nline 5\n".getBytes("utf-8"));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        TestProxy proxy = new TestProxy(in, out);
        proxy.start();
        proxy.join();
        assertEquals(12345, proxy.foobar);
        assertEquals("line 1\nline 2\nline 4\nline 5\n", new String(out.toByteArray(), "utf-8"));
    }

    @Test
    public void testBufferEnlargement() throws Exception {
    }

}
