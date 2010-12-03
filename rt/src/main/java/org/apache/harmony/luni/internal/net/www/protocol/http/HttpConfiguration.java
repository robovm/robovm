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

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URI;

import org.apache.harmony.luni.internal.nls.Messages;

/**
 * An <code>HttpConfiguration</code> contains all the details needed to create an http connection
 * and to compare whether or not two connections are the same.  An HttpConfiguration
 * will either consist of a <code>Proxy<code> or a port number (<code>int</code>)
 * and host name (<code>String</code>) or all three,  depending on whether or not a 
 * <code>Proxy</code> is used and the type of <code>Proxy</code> it is.
 * 
 * <code>HttpConfiguration</code> is used as a key by <code>HttpConnectionManager</code>
 * to retrieve <code>HttpConnection</code>s from its connection pool.
 */
public class HttpConfiguration {

    private Proxy proxy;
    private int hostPort;
    private String hostName;
    private URI uri;

    public HttpConfiguration(URI uri) {
        this.uri = uri;
        this.hostName = uri.getHost();
        this.hostPort = uri.getPort();
        if(hostPort == -1) {
            if(uri.getScheme().equals("https")) { //$NON-NLS-1$
                hostPort = 443;
            } else {
                hostPort = 80;
            }
        }
    }

    public HttpConfiguration(URI uri, Proxy proxy) {
        this.uri = uri;
        this.proxy = proxy;
        if (proxy.type() == Proxy.Type.HTTP) {
            SocketAddress proxyAddr = proxy.address();
            if (!(proxyAddr instanceof InetSocketAddress)) {
               throw new IllegalArgumentException(Messages.getString(
                   "luni.49", proxyAddr.getClass())); //$NON-NLS-1$
            }
            InetSocketAddress iProxyAddr = (InetSocketAddress) proxyAddr;
            this.hostName = iProxyAddr.getHostName();
            this.hostPort = iProxyAddr.getPort();
        } else {
            // using SOCKS proxy
            this.hostName = uri.getHost();
            this.hostPort = uri.getPort();
            if(hostPort == -1) {
                if(uri.getScheme().equals("https")) { //$NON-NLS-1$
                    hostPort = 443;
                } else {
                    hostPort = 80;
                }
            }
        }
        this.uri = uri;
        SocketAddress proxyAddr = proxy.address();
        if (!(proxyAddr instanceof InetSocketAddress)) {
           throw new IllegalArgumentException(Messages.getString(
               "luni.49", proxyAddr.getClass())); //$NON-NLS-1$
        }
        InetSocketAddress iProxyAddr = (InetSocketAddress) proxyAddr;
        this.hostName = iProxyAddr.getHostName();
        this.hostPort = iProxyAddr.getPort();
    }

    /**
     * Returns true if this configuration uses a Proxy
     */
    public boolean usesProxy() {
        return proxy != null;
    }

    /**
     * Returns the Proxy for this configuration, or null if a proxy
     * is not used
     */
    public Proxy getProxy() {
        return proxy;
    }

    /**
     * Returns the host name for this configuration, or null if an http Proxy is used
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * Returns the port for this configuration, or 0 if an http Proxy is used
     */
    public int getHostPort() {
        return hostPort;
    }

    @Override
    public boolean equals(Object arg0) {
        if(!(arg0 instanceof HttpConfiguration)) {
            return false;
        } else {
            HttpConfiguration config = (HttpConfiguration)arg0;
            if(config.proxy != null && proxy != null) {
                return config.proxy.equals(proxy) && uri.equals(config.uri);
            }
            return uri.equals(config.uri);
        }
    }

    @Override
    public int hashCode() {
        return uri.hashCode();
    }

}