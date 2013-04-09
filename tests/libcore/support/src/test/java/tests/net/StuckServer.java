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
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * A test ServerSocket that you can't connect to --- connects will time out.
 */
public final class StuckServer {
    private ServerSocket serverSocket;
    private ArrayList<Socket> clients = new ArrayList<Socket>();

    public StuckServer() throws IOException {
        // Set a backlog and use it up so that we can expect the
        // connection to time out. According to Steven's
        // 4.5 "listen function", Linux adds 3 to the specified
        // backlog, so we need to connect 4 times before it will hang.
        serverSocket = new ServerSocket(0, 1);
        for (int i = 0; i < 4; i++) {
            clients.add(new Socket(serverSocket.getInetAddress(), serverSocket.getLocalPort()));
        }
    }

    public void unblockAfterMs(final int ms) {
        Thread t = new Thread(new Runnable() {
            @Override public void run() {
                try {
                    Thread.sleep(ms);
                    for (Socket client : clients) {
                        client.close();
                    }
                    clients.clear();
                    clients.add(serverSocket.accept());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        t.start();
    }

    public InetSocketAddress getLocalSocketAddress() {
        return (InetSocketAddress) serverSocket.getLocalSocketAddress();
    }

    public int getLocalPort() {
        return serverSocket.getLocalPort();
    }

    public void close() throws IOException {
        serverSocket.close();
        for (Socket client : clients) {
            client.close();
        }
    }
}
