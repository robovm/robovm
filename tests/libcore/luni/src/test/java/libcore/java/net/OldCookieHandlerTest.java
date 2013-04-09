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
import java.net.CookieHandler;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import junit.framework.TestCase;
import tests.support.Support_Configuration;

public class OldCookieHandlerTest extends TestCase {

    URI getURI, putURI;
    String link = "http://" + Support_Configuration.SpecialInetTestAddress + "/";
    boolean isGetCalled = false;
    boolean isPutCalled = false;
    boolean completedSuccessfully = false;

    public void test_CookieHandler() {
        assertNull(CookieHandler.getDefault());
    }

    public void test_get_put() {
        MockCookieHandler mch = new MockCookieHandler();
        CookieHandler defaultHandler = CookieHandler.getDefault();
        CookieHandler.setDefault(mch);

        class TestThread extends Thread {
            public void run() {
                try {
                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();
                    conn.getContent();
                    url = new URL(link);
                    conn = url.openConnection();
                    conn.getContent();
                    completedSuccessfully = true;
                } catch (Exception e) {
                    e.printStackTrace();
               }
            }
        }
        try {
            TestThread thread = new TestThread();

            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                fail("InterruptedException was thrown.");
            }

            assertTrue(isGetCalled);
            assertTrue(isPutCalled);
            assertTrue(completedSuccessfully);
        } finally {
            CookieHandler.setDefault(defaultHandler);
        }
    }

    class MockCookieHandler extends CookieHandler {

        public Map get(URI uri, Map requestHeaders) throws IOException {
            getURI = uri;
            isGetCalled = true;
            return requestHeaders;
        }

        public void put(URI uri, Map responseHeaders) throws IOException {
            putURI = uri;
            isPutCalled = true;
        }
    }
}
