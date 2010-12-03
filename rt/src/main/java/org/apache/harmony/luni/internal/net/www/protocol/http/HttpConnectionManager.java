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
package org.apache.harmony.luni.internal.net.www.protocol.http;

import java.io.IOException;
import java.net.Proxy;
import java.net.URI;
import java.security.AccessController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.harmony.luni.util.PriviAction;

/**
 * <code>HttpConnectionManager</code> manages a pool of <code>HttpConnection</code>s
 * that are not currently in use and is used to get hold of persistent <code>HttpConnection</code>s.  
 * Clients should return an <code>HttpConnection</code> to the pool after use by calling 
 * <code>returnConnectionToPool</code>
 * 
 * Two system properties affect the behaviour of this class - <code>http.maxConnections</code>
 * and <code>http.keepAlive</code>.  <code>http.keepAlive</code> determines whether 
 * or not connections should be persisted and <code>http.maxConnections</code> 
 * determines the maximum number of connections to each individual host that 
 * should be kept in the pool.
 */
public class HttpConnectionManager {

    // The maximum number of connections to any location
    private static int maxConnections = 5;

    // Keeps connections alive if true
    private static boolean keepAlive = true;

    private static HttpConnectionManager defaultConnectionManager;
    private ConnectionPool pool = new ConnectionPool();

    /**
     * Returns the default connection manager
     */
    public static HttpConnectionManager getDefault() {
        if(defaultConnectionManager == null) {
            defaultConnectionManager = new HttpConnectionManager();
        }
        return defaultConnectionManager;
    }

    public HttpConnection getConnection(URI uri, int connectTimeout) throws IOException {
        checkSystemProperties();
        HttpConfiguration config = new HttpConfiguration(uri);
        return pool.getHttpConnection(config, connectTimeout);
    }

    public HttpConnection getConnection(URI uri, Proxy proxy, int connectTimeout) throws IOException {
        checkSystemProperties();
        HttpConfiguration config = new HttpConfiguration(uri, proxy);
        return pool.getHttpConnection(config, connectTimeout);
    }

    public void returnConnectionToPool(HttpConnection connection) {
        checkSystemProperties();
        pool.returnConnection(connection);
    }

    public int numFreeConnections() {
        return pool.numFreeConnections();
    }

    private void checkSystemProperties() {
        String httpMaxConnections =  AccessController.doPrivileged(new PriviAction<String>("http.maxConnections"));
        String httpKeepAlive = AccessController.doPrivileged(new PriviAction<String>("http.keepAlive"));
        if(httpMaxConnections != null) {
            maxConnections = Integer.parseInt(httpMaxConnections);
        }
        if(httpKeepAlive != null) {
            keepAlive = Boolean.parseBoolean(httpKeepAlive);
            if(!keepAlive) {
                pool.clear();
            }
        }
    }

    private static class ConnectionPool {

        private Map<HttpConfiguration, List<HttpConnection>> freeConnectionMap = new HashMap<HttpConfiguration, List<HttpConnection>>(); // Map of free Sockets

        public synchronized void clear() {
            for (Iterator<List<HttpConnection>> iter = freeConnectionMap.values().iterator(); iter.hasNext();) {
                List<HttpConnection> connections = iter.next();
                for (Iterator<HttpConnection> iterator = connections.iterator(); iterator.hasNext();) {
                    HttpConnection connection = iterator.next();
                    connection.closeSocketAndStreams();
                }
            }
            freeConnectionMap.clear();
        }

        public synchronized void returnConnection(HttpConnection connection) {
            if(!connection.getSocket().isClosed() && keepAlive) {
                HttpConfiguration config = connection.getHttpConfiguration();
                List<HttpConnection> connections = freeConnectionMap.get(config);
                if(connections == null) {
                    connections = new ArrayList<HttpConnection>();
                    freeConnectionMap.put(config, connections);
                }
                if(connections.size() < HttpConnectionManager.maxConnections) {
                    if(!connections.contains(connection)) {
                        connections.add(connection);
                    }
                } else {
                    connection.closeSocketAndStreams();
                }
            } else {
                // Make sure all streams are closed etc.
                connection.closeSocketAndStreams();
            }
        }

        public synchronized HttpConnection getHttpConnection(HttpConfiguration config, int connectTimeout) throws IOException {
            List<HttpConnection> connections = freeConnectionMap.get(config);
            if(keepAlive && connections == null) {
                connections = new ArrayList<HttpConnection>();
                freeConnectionMap.put(config, connections);
            }
            if(!keepAlive || connections.isEmpty()) {
                HttpConnection connection = new HttpConnection(config, connectTimeout);
                return connection;
            } else {
                HttpConnection connection = connections.get(0);
                connections.remove(0);
                if(!connection.isStale()) {
                    SecurityManager security = System.getSecurityManager();
                    if (security != null) {
                        security.checkConnect(connection.getSocket().getInetAddress().getHostName(), connection.getSocket().getPort());
                    }
                    return connection;                 
                } else {
                    return getHttpConnection(config, connectTimeout);
                }
            }
        }

        public int numFreeConnections() {
            int numFree = 0;
            for (Iterator<List<HttpConnection>> iter = freeConnectionMap.values().iterator(); iter.hasNext();) {
                List<HttpConnection> connections = iter.next();
                numFree += connections.size();
            }
            return numFree;
        }
    }

    public void reset() {
        pool.clear();
    }

}