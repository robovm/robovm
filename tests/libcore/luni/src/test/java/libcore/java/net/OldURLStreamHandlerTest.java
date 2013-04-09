/* Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package libcore.java.net;

import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.UnknownHostException;
import junit.framework.TestCase;

public class OldURLStreamHandlerTest extends TestCase {

    MockURLStreamHandler handler = null;

    public void test_equalsLjava_net_URLLjava_net_URL() throws MalformedURLException {
        URL url1 = new URL("ftp://test_url/test?a=b&c=%D0+%D1");
        URL url2 = new URL("http://test_url/test?a=b&c=%D0+%D1");
        assertFalse(url1.equals(url2));

        new URL("http://test_url+/test?a=b&c=%D0+%D1");
        assertFalse(handler.equals(url1,url2));

        try {
            assertFalse(handler.equals(null, url1));
            fail("NullPointerException was not thrown.");
        } catch(NullPointerException npe) {
            //expected
        }
    }

    public void test_getDefaultPort() {
        assertEquals(-1, handler.getDefaultPort());
    }

    public void test_getHostAddress() throws MalformedURLException, UnknownHostException {
        URL url1 = new URL("ftp://test_url/test?a=b&c=%D0+%D1");
        assertNull(handler.getHostAddress(url1));

        URL url2 = new URL("http://test:pwd@fakehostname.fakedomain/test?a=b&c=%D0+%D1");
        assertNull(handler.getHostAddress(url2));

        URL url3 = new URL("http://localhost/test");
        assertEquals(InetAddress.getByName("localhost"), handler.getHostAddress(url3));
    }

    public void test_hashCodeLjava_net_URL() throws MalformedURLException {
        URL url1 = new URL("ftp://test_url/test?a=b&c=%D0+%D1");
        URL url2 = new URL("http://test_url/test?a=b&c=%D0+%D1");
        assertTrue(handler.hashCode(url1) != handler.hashCode(url2));

        new URL("http://test_url+/test?a=b&c=%D0+%D1");
        assertFalse(handler.equals(url1,url2));

        try {
            handler.hashCode(null);
            fail("NullPointerException was not thrown.");
        } catch(NullPointerException expected) {
        }
    }

    public void test_hostsEqualLjava_net_URLLjava_net_URL() throws Exception {
        URL url1 = new URL("ftp://localhost:21/*test");
        URL url2 = new URL("http://localhost/_test");
        assertTrue(handler.hostsEqual(url1, url2));

        URL url3 = new URL("http://foo/_test_goo");
        assertFalse(handler.hostsEqual(url1, url3));
    }

    public void test_openConnectionLjava_net_URL() throws IOException {
        // abstract method, it doesn't check anything
        assertNull(handler.openConnection(null));
    }

    public void test_openConnectionLjava_net_URLLjava_net_Proxy() {
        try {
            handler.openConnection(null, null);
            fail("UnsupportedOperationException was not thrown.");
        } catch(UnsupportedOperationException  uoe) {
            //expected
        } catch (IOException e) {
            fail("IOException was thrown.");
        }
    }

    public void test_parseURLLjava_net_URLLjava_lang_StringII()
                                                throws MalformedURLException {
        String str  = "http://test.org/foo?a=123&b=%D5D6D7&c=++&d=";
        URL url = new URL("http://test.org");

        try {
            handler.parseURL(url, str, 0, str.length());
            fail("SecurityException should be thrown.");
        } catch(SecurityException se) {
            //SecurityException is expected
        }
    }

    public void test_sameFile() throws Exception {
        URL url1  = new URL("http://test:pwd@localhost:80/foo/foo1.c");
        URL url2  = new URL("http://test:pwd@localhost:80/foo/foo1.c");
        URL url3  = new URL("http://test:pwd@localhost:80/foo/foo2.c");
        URL url4  = new URL("ftp://test:pwd@localhost:21/foo/foo2.c");
        URL url5  = new URL("ftp://test:pwd@localhost:21/foo/foo1/foo2.c");
        URL url6  = new URL("http://test/foo/foo1.c");

        assertTrue("Test case 1", handler.sameFile(url1, url2));
        assertFalse("Test case 2", handler.sameFile(url3, url2));
        assertFalse("Test case 3", handler.sameFile(url3, url4));
        assertFalse("Test case 4", handler.sameFile(url4, url5));
        assertFalse("Test case 5", handler.sameFile(url1, url6));
    }

    public void test_setURL1() throws MalformedURLException {
        URL url = new URL("http://test.org");

        try {
            handler.setURL(url, "http", "localhost", 80, "foo.c", "ref");
            fail("SecurityException should be thrown.");
        } catch(SecurityException expected) {
        }
    }

    public void test_setURL2() throws MalformedURLException {
        URL url = new URL("http://test.org");

        try {
            handler.setURL(url, "http", "localhost", 80, "authority",
                    "user", "foo.c", "query", "ref");
            fail("SecurityException should be thrown.");
        } catch(SecurityException expected) {
        }
    }

    public void test_toExternalForm() throws MalformedURLException {
        URL [] urls = { new URL("ftp://test_url/test?a=b&c=%D0+%D1"),
                        new URL("http://test_url/test?a=b&c=%D0+%D1"),
                        new URL("http://test:pwd@localhost:80/foo/foo1.c")};

        for(URL url : urls) {
            assertEquals("Test case for " + url.toString(),
                    url.toString(), handler.toExternalForm(url));
        }
    }

    public void test_Constructor() {
        MockURLStreamHandler msh = new MockURLStreamHandler();
        assertEquals(-1, msh.getDefaultPort());
    }

    public void setUp() {
        handler = new MockURLStreamHandler();
    }

    class MockURLStreamHandler extends URLStreamHandler {

        @Override protected URLConnection openConnection(URL arg0) throws IOException {
            return null;
        }

        @Override public boolean equals(URL a, URL b) {
            return super.equals(a, b);
        }

        @Override public int getDefaultPort() {
            return super.getDefaultPort();
        }

        @Override public InetAddress getHostAddress(URL u) {
            return super.getHostAddress(u);
        }

        @Override public int hashCode(URL u) {
            return super.hashCode(u);
        }

        @Override public boolean hostsEqual(URL a, URL b) {
            return super.hostsEqual(a, b);
        }

        @Override public URLConnection openConnection(URL u, Proxy p) throws IOException {
            return super.openConnection(u, p);
        }

        @Override public void parseURL(URL url, String spec, int start, int limit) {
            super.parseURL(url, spec, start, limit);
        }

        @Override public boolean sameFile(URL a, URL b) {
            return super.sameFile(a, b);
        }

        @Override public void setURL(URL u,
                String protocol,
                String host,
                int port,
                String file,
                String ref) {
            super.setURL(u, protocol, host, port, file, ref);
        }

        @Override public void setURL(URL u,
                String protocol,
                String host,
                int port,
                String authority,
                String userInfo,
                String path,
                String query,
                String ref) {
            super.setURL(u, protocol, host, port, authority,
                    userInfo, path, query, ref);
        }

        @Override public String toExternalForm(URL u) {
            return super.toExternalForm(u);
        }
    }
}
