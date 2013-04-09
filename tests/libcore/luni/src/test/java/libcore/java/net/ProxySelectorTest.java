/*
 * Copyright (C) 2010 The Android Open Source Project
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

package libcore.java.net;

import static java.net.InetSocketAddress.createUnresolved;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import junit.framework.TestCase;

public final class ProxySelectorTest extends TestCase {

    private ProxySelector proxySelector;

    private URI httpUri;
    private URI ftpUri;
    private URI httpsUri;
    private URI socketUri;
    private URI otherUri;

    protected void setUp() throws Exception {
        super.setUp();
        proxySelector = ProxySelector.getDefault();
        httpUri = new URI("http://android.com");
        ftpUri = new URI("ftp://android.com");
        httpsUri = new URI("https://android.com");
        socketUri = new URI("socket://android.com");
        otherUri = new URI("other://android.com");
    }

    @Override protected void tearDown() throws Exception {
        System.clearProperty("ftp.proxyHost");
        System.clearProperty("ftp.proxyPort");
        System.clearProperty("ftp.nonProxyHosts");
        System.clearProperty("http.proxyHost");
        System.clearProperty("http.proxyPort");
        System.clearProperty("http.nonProxyHosts");
        System.clearProperty("https.proxyHost");
        System.clearProperty("https.proxyPort");
        System.clearProperty("https.nonProxyHosts");
        System.clearProperty("other.proxyHost");
        System.clearProperty("other.proxyPort");
        System.clearProperty("socket.proxyHost");
        System.clearProperty("socket.proxyPort");
        System.clearProperty("proxyHost");
        System.clearProperty("proxyPort");
        // RoboVM note: socksProxyHost and socksProxyPort must be cleared too
        System.clearProperty("socksProxyHost");
        System.clearProperty("socksProxyPort");
    }

    public void testNoProxySystemProperty() throws URISyntaxException {
        assertEquals(Arrays.asList(Proxy.NO_PROXY), proxySelector.select(ftpUri));
        assertEquals(Arrays.asList(Proxy.NO_PROXY), proxySelector.select(httpUri));
        assertEquals(Arrays.asList(Proxy.NO_PROXY), proxySelector.select(httpsUri));
        assertEquals(Arrays.asList(Proxy.NO_PROXY), proxySelector.select(socketUri));
        assertEquals(Arrays.asList(Proxy.NO_PROXY), proxySelector.select(otherUri));
    }

    public void testProxyHostOnly() throws URISyntaxException {
        System.setProperty("ftp.proxyHost", "a");
        System.setProperty("http.proxyHost", "b");
        System.setProperty("https.proxyHost", "c");
        System.setProperty("other.proxyHost", "d");
        System.setProperty("socket.proxyHost", "d");
        assertEquals(Arrays.asList(new Proxy(Proxy.Type.HTTP, createUnresolved("a", 80))),
                proxySelector.select(ftpUri));
        assertEquals(Arrays.asList(new Proxy(Proxy.Type.HTTP, createUnresolved("b", 80))),
                proxySelector.select(httpUri));
        assertEquals(Arrays.asList(new Proxy(Proxy.Type.HTTP, createUnresolved("c", 443))),
                proxySelector.select(httpsUri));
        assertEquals(Arrays.asList(Proxy.NO_PROXY), proxySelector.select(otherUri));
        assertEquals(Arrays.asList(Proxy.NO_PROXY), proxySelector.select(socketUri));
    }

    public void testProxyHostPort() throws URISyntaxException {
        System.setProperty("ftp.proxyHost", "a");
        System.setProperty("ftp.proxyPort", "1001");
        System.setProperty("http.proxyHost", "b");
        System.setProperty("http.proxyPort", "1002");
        System.setProperty("https.proxyHost", "c");
        System.setProperty("https.proxyPort", "1003");
        System.setProperty("other.proxyHost", "d");
        System.setProperty("other.proxyPort", "1004");
        System.setProperty("socket.proxyHost", "e");
        System.setProperty("socket.proxyPort", "1005");
        assertEquals(Arrays.asList(new Proxy(Proxy.Type.HTTP, createUnresolved("a", 1001))),
                proxySelector.select(ftpUri));
        assertEquals(Arrays.asList(new Proxy(Proxy.Type.HTTP, createUnresolved("b", 1002))),
                proxySelector.select(httpUri));
        assertEquals(Arrays.asList(new Proxy(Proxy.Type.HTTP, createUnresolved("c", 1003))),
                proxySelector.select(httpsUri));
        assertEquals(Arrays.asList(Proxy.NO_PROXY), proxySelector.select(socketUri));
        assertEquals(Arrays.asList(Proxy.NO_PROXY), proxySelector.select(otherUri));
    }

    public void testProxyPortOnly() throws URISyntaxException {
        System.setProperty("ftp.proxyPort", "1001");
        System.setProperty("http.proxyPort", "1002");
        System.setProperty("https.proxyPort", "1003");
        System.setProperty("other.proxyPort", "1004");
        System.setProperty("socket.proxyPort", "1005");
        assertEquals(Arrays.asList(Proxy.NO_PROXY), proxySelector.select(ftpUri));
        assertEquals(Arrays.asList(Proxy.NO_PROXY), proxySelector.select(httpUri));
        assertEquals(Arrays.asList(Proxy.NO_PROXY), proxySelector.select(httpsUri));
        assertEquals(Arrays.asList(Proxy.NO_PROXY), proxySelector.select(socketUri));
        assertEquals(Arrays.asList(Proxy.NO_PROXY), proxySelector.select(otherUri));
    }

    public void testHttpsDoesNotUseHttpProperties() throws URISyntaxException {
        System.setProperty("http.proxyHost", "a");
        System.setProperty("http.proxyPort", "1001");
        assertEquals(Arrays.asList(Proxy.NO_PROXY), proxySelector.select(httpsUri));
    }

    public void testProxyHost() throws URISyntaxException {
        System.setProperty("proxyHost", "a");
        assertEquals(Arrays.asList(new Proxy(Proxy.Type.HTTP, createUnresolved("a", 80))),
                proxySelector.select(ftpUri));
        assertEquals(Arrays.asList(new Proxy(Proxy.Type.HTTP, createUnresolved("a", 80))),
                proxySelector.select(httpUri));
        assertEquals(Arrays.asList(new Proxy(Proxy.Type.HTTP, createUnresolved("a", 443))),
                proxySelector.select(httpsUri));
        assertEquals(Arrays.asList(Proxy.NO_PROXY), proxySelector.select(socketUri));
        assertEquals(Arrays.asList(Proxy.NO_PROXY), proxySelector.select(otherUri));
    }

    public void testHttpProxyHostPreferredOverProxyHost() throws URISyntaxException {
        System.setProperty("http.proxyHost", "a");
        System.setProperty("proxyHost", "b");
        assertEquals(Arrays.asList(new Proxy(Proxy.Type.HTTP, createUnresolved("a", 80))),
                proxySelector.select(httpUri));
    }

    public void testSocksProxyHost() throws URISyntaxException {
        System.setProperty("socksProxyHost", "a");
        assertEquals(Arrays.asList(new Proxy(Proxy.Type.SOCKS, createUnresolved("a", 1080))),
                proxySelector.select(ftpUri));
        assertEquals(Arrays.asList(new Proxy(Proxy.Type.SOCKS, createUnresolved("a", 1080))),
                proxySelector.select(httpUri));
        assertEquals(Arrays.asList(new Proxy(Proxy.Type.SOCKS, createUnresolved("a", 1080))),
                proxySelector.select(httpsUri));
        assertEquals(Arrays.asList(new Proxy(Proxy.Type.SOCKS, createUnresolved("a", 1080))),
                proxySelector.select(socketUri));
        assertEquals(Arrays.asList(Proxy.NO_PROXY), proxySelector.select(otherUri));
    }

    public void testSocksProxyHostAndPort() throws URISyntaxException {
        System.setProperty("socksProxyHost", "a");
        System.setProperty("socksProxyPort", "1001");
        assertEquals(Arrays.asList(new Proxy(Proxy.Type.SOCKS, createUnresolved("a", 1001))),
                proxySelector.select(ftpUri));
        assertEquals(Arrays.asList(new Proxy(Proxy.Type.SOCKS, createUnresolved("a", 1001))),
                proxySelector.select(httpUri));
        assertEquals(Arrays.asList(new Proxy(Proxy.Type.SOCKS, createUnresolved("a", 1001))),
                proxySelector.select(httpsUri));
        assertEquals(Arrays.asList(new Proxy(Proxy.Type.SOCKS, createUnresolved("a", 1001))),
                proxySelector.select(socketUri));
        assertEquals(Arrays.asList(Proxy.NO_PROXY), proxySelector.select(otherUri));
    }

    public void testNonProxyHostsFtp() throws URISyntaxException {
        System.setProperty("ftp.nonProxyHosts", "*.com");
        System.setProperty("ftp.proxyHost", "a");
        assertEquals(Arrays.asList(new Proxy(Proxy.Type.HTTP, createUnresolved("a", 80))),
                proxySelector.select(new URI("ftp://foo.net")));
        assertEquals(Arrays.asList(Proxy.NO_PROXY),
                proxySelector.select(new URI("ftp://foo.com")));
    }

    public void testNonProxyHostsHttp() throws URISyntaxException {
        System.setProperty("http.nonProxyHosts", "*.com");
        System.setProperty("http.proxyHost", "a");
        assertEquals(Arrays.asList(new Proxy(Proxy.Type.HTTP, createUnresolved("a", 80))),
                proxySelector.select(new URI("http://foo.net")));
        assertEquals(Arrays.asList(Proxy.NO_PROXY),
                proxySelector.select(new URI("http://foo.com")));
    }

    public void testNonProxyHostsHttps() throws URISyntaxException {
        System.setProperty("https.nonProxyHosts", "*.com");
        System.setProperty("https.proxyHost", "a");
        assertEquals(Arrays.asList(new Proxy(Proxy.Type.HTTP, createUnresolved("a", 443))),
                proxySelector.select(new URI("https://foo.net")));
        assertEquals(Arrays.asList(Proxy.NO_PROXY),
                proxySelector.select(new URI("https://foo.com")));
    }

    public void testSchemeCaseSensitive() throws URISyntaxException {
        System.setProperty("http.proxyHost", "a");
        assertEquals(Arrays.asList(new Proxy(Proxy.Type.HTTP, createUnresolved("a", 80))),
                proxySelector.select(new URI("HTTP://foo.net")));
    }
}
