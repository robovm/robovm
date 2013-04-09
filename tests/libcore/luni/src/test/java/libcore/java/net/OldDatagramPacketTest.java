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
import tests.support.Support_PortManager;

public class OldDatagramPacketTest extends junit.framework.TestCase {

    DatagramPacket dp;

    volatile boolean started = false;

    public void test_getPort() throws IOException {
        dp = new DatagramPacket("Hello".getBytes(), 5, InetAddress.getLocalHost(), 1000);
        assertEquals("Incorrect port returned", 1000, dp.getPort());

        InetAddress localhost = InetAddress.getByName("localhost");

        int[] ports = Support_PortManager.getNextPortsForUDP(2);
        final int port = ports[0];
        final Object lock = new Object();

        Thread thread = new Thread(new Runnable() {
            public void run() {
                DatagramSocket socket = null;
                try {
                    socket = new DatagramSocket(port);
                    synchronized (lock) {
                        started = true;
                        lock.notifyAll();
                    }
                    socket.setSoTimeout(3000);
                    DatagramPacket packet = new DatagramPacket(new byte[256],
                            256);
                    socket.receive(packet);
                    socket.send(packet);
                    socket.close();
                } catch (IOException e) {
                    System.out.println("thread exception: " + e);
                    if (socket != null)
                        socket.close();
                }
            }
        });
        thread.start();

        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(ports[1]);
            socket.setSoTimeout(3000);
            DatagramPacket packet = new DatagramPacket(new byte[] { 1, 2, 3, 4,
                    5, 6 }, 6, localhost, port);
            synchronized (lock) {
                try {
                    if (!started)
                        lock.wait();
                } catch (InterruptedException e) {
                    fail(e.toString());
                }
            }
            socket.send(packet);
            socket.receive(packet);
            socket.close();
            assertTrue("datagram received wrong port: " + packet.getPort(),
                    packet.getPort() == port);
        } finally {
            if (socket != null) {
                socket.close();
            }
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
        dp = new DatagramPacket("Hello".getBytes(), 5);
        try {
            dp.setData(null, 2, 3);
            fail("NullPointerException was not thrown.");
        } catch(NullPointerException expected) {
        }
    }

    public void test_setData$B() {
        dp = new DatagramPacket("Hello".getBytes(), 5);
        try {
            dp.setData(null);
            fail("NullPointerException was not thrown.");
        } catch(NullPointerException expected) {
        }
    }
}
