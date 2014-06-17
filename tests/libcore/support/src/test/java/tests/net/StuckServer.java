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

package tests.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * A test ServerSocket that you can't connect to --- connects will time out.
 */
public final class StuckServer {
    private static final boolean DEBUG = false;

    private ServerSocket serverSocket;
    private InetSocketAddress address;
    private ArrayList<Socket> clients = new ArrayList<Socket>();

    public StuckServer(boolean useBacklog) throws IOException {
        // Set a backlog and use it up so that we can expect the
        // connection to time out. According to Stevens
        // 4.5 "listen function", Linux adds 3 to the specified
        // backlog, so we need to connect 4 times before it will hang.
        // The trouble with this is that it won't hang forever.
        // After 10s or so, the kernel allows a couple more connections.
        // This mode is ony useful if you actually want to continue eventually; we use it to
        // test non-blocking connects, for example, where you want to test every part of the code.
        if (useBacklog) {
            this.serverSocket = new ServerSocket(0, 1);
            this.address = (InetSocketAddress) serverSocket.getLocalSocketAddress();
            if (DEBUG) {
                System.err.println("StuckServer: " + serverSocket);
            }
            int clientCount = 4;
            if (System.getProperty("os.name").contains("iOS") || System.getProperty("os.name").contains("Mac")) {
                // RoboVM note: On Darwin the exact backlog is honored.
                clientCount = 1;
            }
            for (int i = 0; i < clientCount; ++i) {
                Socket client = new Socket(serverSocket.getInetAddress(), serverSocket.getLocalPort());
                clients.add(client);
                if (DEBUG) {
                    System.err.println("StuckServer client " + i + " - " + client);
                }
            }
        } else {
            // In general, though, you don't want to rely on listen(2) backlog. http://b/6971145.
            // RFC 5737 implies this network will be unreachable. (There are two other networks
            // to try if we have trouble with this one.)
            // We've had trouble with 10.* in the past (because test labs running CTS often use
            // net 10!) but hopefully this network will be better.
            InetAddress testNet1 = InetAddress.getByAddress(new byte[] { (byte) 192, 0, 2, 0 });
            this.address = new InetSocketAddress(testNet1, 80);
        }
    }

    public InetSocketAddress getLocalSocketAddress() {
        return address;
    }

    public int getLocalPort() {
        return address.getPort();
    }

    public void close() throws IOException {
        if (serverSocket != null) {
            serverSocket.close();
        }
        for (Socket client : clients) {
            client.close();
        }
    }
}
