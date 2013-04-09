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

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import junit.framework.TestCase;

public class OldProxyTest extends TestCase {

    private SocketAddress address = new InetSocketAddress("127.0.0.1", 1234);

    public void test_address() {
        Proxy proxy = new Proxy(Proxy.Type.SOCKS, address);
        assertEquals(address, proxy.address());

        try {
            new Proxy(Proxy.Type.SOCKS, null);
            fail("IllegalArgumentException was thrown.");
        } catch(IllegalArgumentException iae) {
            //expected
        }
    }

    public void test_hashCode() {
        SocketAddress address1 = new InetSocketAddress("127.0.0.1", 1234);

        Proxy proxy1 = new Proxy(Proxy.Type.HTTP, address1);
        Proxy proxy2 = new Proxy(Proxy.Type.HTTP, address1);
        assertTrue(proxy1.hashCode() == proxy2.hashCode());

        new Proxy(Proxy.Type.SOCKS, address1);
        Proxy proxy4 = new Proxy(Proxy.Type.SOCKS, address1);
        assertTrue(proxy1.hashCode() == proxy2.hashCode());

        assertTrue(proxy1.hashCode() != proxy4.hashCode());

        SocketAddress address2 = new InetSocketAddress("127.0.0.1", 1235);

        Proxy proxy5 = new Proxy(Proxy.Type.SOCKS, address1);
        Proxy proxy6 = new Proxy(Proxy.Type.SOCKS, address2);
        assertTrue(proxy5.hashCode() != proxy6.hashCode());
    }

    public void test_type() {

        Proxy proxy = new Proxy(Proxy.Type.HTTP, address);
        assertEquals(Proxy.Type.HTTP, proxy.type());

        proxy = new Proxy(Proxy.Type.SOCKS, address);
        assertEquals(Proxy.Type.SOCKS, proxy.type());

        proxy = Proxy.NO_PROXY;
        assertEquals(Proxy.Type.DIRECT, proxy.type());
    }
}
