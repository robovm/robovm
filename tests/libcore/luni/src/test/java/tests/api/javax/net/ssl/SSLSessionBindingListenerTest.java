/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package tests.api.javax.net.ssl;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionBindingEvent;
import javax.net.ssl.SSLSessionBindingListener;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import java.io.IOException;
import java.net.UnknownHostException;

import junit.framework.TestCase;

/**
 * Tests for SSLSessionBindingListener class
 *
 */
public class SSLSessionBindingListenerTest extends TestCase {

    public class mySSLSessionBindingListener implements SSLSessionBindingListener {

        public boolean boundDone = false;
        public boolean unboundDone = false;

        mySSLSessionBindingListener() {
        }

        public void valueBound(SSLSessionBindingEvent event) {
            if (event != null) boundDone = true;
        }
        public void valueUnbound(SSLSessionBindingEvent event) {
            if (event != null) unboundDone = true;
        }
    }

    /**
     * @throws IOException
     * @throws UnknownHostException
     * @throws InterruptedException
     * javax.net.ssl.SSLSessionBindingListener#valueBound(SSLSessionBindingEvent event)
     */
    public void test_valueBound() throws UnknownHostException, IOException,
            InterruptedException {
        SSLSocket sock = (SSLSocket) SSLSocketFactory.getDefault()
                .createSocket();
        SSLSession ss = sock.getSession();
        mySSLSessionBindingListener sbl = new mySSLSessionBindingListener();
        ss.putValue("test", sbl);
        assertTrue("valueBound was not called.", sbl.boundDone);
    }

    /**
     * @throws IOException
     * @throws UnknownHostException
     * javax.net.ssl.SSLSessionBindingListener#valueUnbound(SSLSessionBindingEvent event)
     */
    public void test_valueUnbound() throws UnknownHostException, IOException {
        SSLSocket sock = (SSLSocket) SSLSocketFactory.getDefault()
                .createSocket();
        SSLSession ss = sock.getSession();
        mySSLSessionBindingListener sbl = new mySSLSessionBindingListener();
        ss.putValue("test", sbl);
        ss.removeValue("test");
        assertTrue("valueUnbound was not called.", sbl.unboundDone);
    }
}
