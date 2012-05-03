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

package libcore.net.http;

import dalvik.system.SocketTagger;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A pool of HTTP connections. This class exposes its tuning parameters as
 * system properties:
 * <ul>
 *   <li>{@code http.keepAlive} true if HTTP connections should be pooled at
 *       all. Default is true.
 *   <li>{@code http.maxConnections} maximum number of connections to each URI.
 *       Default is 5.
 * </ul>
 *
 * <p>This class <i>doesn't</i> adjust its configuration as system properties
 * are changed. This assumes that the applications that set these parameters do
 * so before making HTTP connections, and that this class is initialized lazily.
 */
final class HttpConnectionPool {

    public static final HttpConnectionPool INSTANCE = new HttpConnectionPool();

    private final int maxConnections;
    private final HashMap<HttpConnection.Address, List<HttpConnection>> connectionPool
            = new HashMap<HttpConnection.Address, List<HttpConnection>>();

    private HttpConnectionPool() {
        String keepAlive = System.getProperty("http.keepAlive");
        if (keepAlive != null && !Boolean.parseBoolean(keepAlive)) {
            maxConnections = 0;
            return;
        }

        String maxConnectionsString = System.getProperty("http.maxConnections");
        this.maxConnections = maxConnectionsString != null
                ? Integer.parseInt(maxConnectionsString)
                : 5;
    }

    public HttpConnection get(HttpConnection.Address address, int connectTimeout)
            throws IOException {
        // First try to reuse an existing HTTP connection.
        synchronized (connectionPool) {
            List<HttpConnection> connections = connectionPool.get(address);
            if (connections != null) {
                while (!connections.isEmpty()) {
                    HttpConnection connection = connections.remove(connections.size() - 1);
                    if (!connection.isStale()) { // TODO: this op does I/O!
                        // Since Socket is recycled, re-tag before using
                        final Socket socket = connection.getSocket();
                        SocketTagger.get().tag(socket);
                        return connection;
                    }
                }
                connectionPool.remove(address);
            }
        }

        /*
         * We couldn't find a reusable connection, so we need to create a new
         * connection. We're careful not to do so while holding a lock!
         */
        return address.connect(connectTimeout);
    }

    public void recycle(HttpConnection connection) {
        final Socket socket = connection.getSocket();
        try {
            SocketTagger.get().untag(socket);
        } catch (SocketException e) {
            // When unable to remove tagging, skip recycling and close
            System.logW("Unable to untagSocket(): " + e);
            connection.closeSocketAndStreams();
            return;
        }

        if (maxConnections > 0 && connection.isEligibleForRecycling()) {
            HttpConnection.Address address = connection.getAddress();
            synchronized (connectionPool) {
                List<HttpConnection> connections = connectionPool.get(address);
                if (connections == null) {
                    connections = new ArrayList<HttpConnection>();
                    connectionPool.put(address, connections);
                }
                if (connections.size() < maxConnections) {
                    connections.add(connection);
                    return; // keep the connection open
                }
            }
        }

        // don't close streams while holding a lock!
        connection.closeSocketAndStreams();
    }
}
