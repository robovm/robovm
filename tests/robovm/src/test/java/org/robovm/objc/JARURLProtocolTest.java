/*
 * Copyright (C) 2015 RoboVM AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.robovm.objc;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.robovm.apple.foundation.NSString;
import org.robovm.apple.foundation.NSStringEncoding;
import org.robovm.apple.foundation.NSURL;
import org.robovm.rt.bro.Bro;

/**
 * Tests our native <code>NSURLProtocol</code> which handles <code>jar:</code>
 * URLs. This is registered on app startup by the native vm libs.
 */
public class JARURLProtocolTest {

    @Before
    public void setUp() {
        Assume.assumeTrue(Bro.IS_DARWIN);
    }

    @Test
    public void testReadSimpleEntry() throws Exception {
        URL jarFile = createJar("resource.txt", "Hello world!!!");
        URL url = rel(jarFile, "resource.txt", true);
        NSURL nsUrl = new NSURL(url);
        String expected = toString(url);
        String actual = NSString.readURL(nsUrl, NSStringEncoding.UTF8);

        assertEquals(expected, actual);
    }

    @Test
    public void testReadEntryWithSpacesAndPlus() throws Exception {
        // NOTE: jar: entries that contain spaces in JAR URLs are not URL
        // encoded. Such URLs are illegal and will make NSURL fail. We need to
        // initialize the NSURL with a URL that has been properly encoded which
        // is why we pass encodePath=true to the NSURL but false to toString().
        URL jarFile = createJar("resource with spaces and+.txt", "Hello world!!!");
        NSURL nsUrl = new NSURL(rel(jarFile, "resource with spaces and+.txt", true));
        String expected = toString(rel(jarFile, "resource with spaces and+.txt", false));
        String actual = NSString.readURL(nsUrl, NSStringEncoding.UTF8);

        assertEquals(expected, actual);
    }

    @Test
    public void testReadLargeEntry() throws Exception {
        // 8 MB of very compressable data
        URL jarFile = createJar("resource.txt", new RepeatingCharSequence("01234567", 1024 * 1024));
        URL url = rel(jarFile, "resource.txt", true);
        NSURL nsUrl = new NSURL(url);
        String expected = toString(url);
        String actual = NSString.readURL(nsUrl, NSStringEncoding.UTF8);

        assertEquals(expected, actual);
    }

    private static URL createJar(CharSequence... entries) throws IOException {
        File jarFile = File.createTempFile(JARURLProtocolTest.class.getSimpleName() + " and spaces", ".jar");
        int i = 0;
        try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(jarFile))) {
            CharSequence name = entries[i++];
            CharSequence contents = entries[i++];
            ZipEntry newEntry = new ZipEntry(name.toString());
            out.putNextEntry(newEntry);

            for (int j = 0; j < contents.length(); j++) {
                out.write((byte) (contents.charAt(j) & 0x7f));
            }

            out.closeEntry();
        }
        return new URL("jar:" + jarFile.toURI() + "!/");
    }

    private static URL rel(URL url, String path, boolean encodePath) throws Exception {
        if (encodePath) {
            path = URLEncoder.encode(path, "utf8");
            path = path.replace("+", "%20");
        }
        return new URL(url.getProtocol(), url.getHost(), url.getFile()
                + path);
    }

    private static String toString(URL url) throws IOException {
        StringWriter output = new StringWriter();
        try (Reader input = new InputStreamReader(url.openStream(), "utf8")) {
            int n = 0;
            char[] buffer = new char[8192];
            while ((n = input.read(buffer)) != -1) {
                output.write(buffer, 0, n);
            }
        }
        return output.toString();
    }

    static class RepeatingCharSequence implements CharSequence {
        final String s;
        final int multiple;

        public RepeatingCharSequence(String s, int multiple) {
            this.s = s;
            this.multiple = multiple;
        }

        @Override
        public int length() {
            return s.length() * multiple;
        }

        @Override
        public char charAt(int index) {
            return s.charAt(index % s.length());
        }

        @Override
        public CharSequence subSequence(int start, int end) {
            return null;
        }
    }
}
