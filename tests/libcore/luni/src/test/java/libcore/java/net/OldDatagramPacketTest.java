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

package libcore.java.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class OldDatagramPacketTest extends junit.framework.TestCase {

    public void test_getPort() throws Exception {
        DatagramPacket dp = new DatagramPacket("Hello".getBytes(), 5, InetAddress.getLocalHost(), 1000);
        assertEquals("Incorrect port returned", 1000, dp.getPort());

        final DatagramSocket ss = new DatagramSocket();
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    DatagramPacket packet = new DatagramPacket(new byte[256], 256);
                    ss.setSoTimeout(3000);
                    ss.receive(packet);
                    ss.send(packet);
                } catch (IOException e) {
                    System.out.println("thread exception: " + e);
                }
            }
        });
        thread.start();

        DatagramSocket cs = new DatagramSocket();
        try {
            byte[] bytes = new byte[] { 1, 2, 3, 4, 5, 6 };
            DatagramPacket packet = new DatagramPacket(bytes, 6, InetAddress.getByName("localhost"), ss.getLocalPort());
            cs.send(packet);
            cs.setSoTimeout(3000);
            cs.receive(packet);
            cs.close();
            assertEquals(packet.getPort(), ss.getLocalPort());
        } finally {
            cs.close();
            ss.close();
        }
    }

    public void test_setLengthI() {
        try {
            new DatagramPacket("Hello".getBytes(), 6);
            fail("IllegalArgumentException was not thrown.");
        } catch(IllegalArgumentException expected) {
        }

        try {
            new DatagramPacket("Hello".getBytes(), -1);
            fail("IllegalArgumentException was not thrown.");
        } catch(IllegalArgumentException expected) {
        }
    }

    public void test_setData$BII() {
        DatagramPacket dp = new DatagramPacket("Hello".getBytes(), 5);
        try {
            dp.setData(null, 2, 3);
            fail("NullPointerException was not thrown.");
        } catch(NullPointerException expected) {
        }
    }

    public void test_setData$B() {
        DatagramPacket dp = new DatagramPacket("Hello".getBytes(), 5);
        try {
            dp.setData(null);
            fail("NullPointerException was not thrown.");
        } catch(NullPointerException expected) {
        }
    }
}
