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
package java.net;

import java.io.IOException;
import java.util.List;

/**
 * Selects an applicable proxy server when connecting to a resource specified by
 * a URL. Proxy selectors are concrete subclasses of {@code ProxySelector} and
 * can be set as default by calling the {@code setDefault()} method. If a
 * connection can't be established, the caller should notify the proxy selector
 * by invoking the {@code connectFailed()} method.
 */
public abstract class ProxySelector {

    private static ProxySelector defaultSelector = new ProxySelectorImpl();

    /*
     * "getProxySelector" permission. getDefault method requires this
     * permission.
     */
    private final static NetPermission getProxySelectorPermission = new NetPermission(
            "getProxySelector"); //$NON-NLS-1$

    /*
     * "setProxySelector" permission. setDefault method requires this
     * permission.
     */
    private final static NetPermission setProxySelectorPermission = new NetPermission(
            "setProxySelector"); //$NON-NLS-1$

    /**
     * Creates a new {@code ProxySelector} instance.
     */
    public ProxySelector() {
        super();
    }

    /**
     * Gets the default {@code ProxySelector} of the system.
     * 
     * @return the currently set default {@code ProxySelector}.
     * @throws SecurityException
     *             if a security manager is installed but it doesn't have the
     *             NetPermission("getProxySelector").
     */
    public static ProxySelector getDefault() {
        SecurityManager sm = System.getSecurityManager();
        if (null != sm) {
            sm.checkPermission(getProxySelectorPermission);
        }
        return defaultSelector;
    }

    /**
     * Sets the default {@code ProxySelector} of the system. Removes the system
     * default {@code ProxySelector} if the parameter {@code selector} is set to
     * {@code null}.
     * 
     * @param selector
     *            the {@code ProxySelector} instance to set as default or
     *            {@code null} to remove the current default {@code
     *            ProxySelector}.
     * @throws SecurityException
     *             if a security manager is installed but it doesn't have the
     *             NetPermission("setProxySelector").
     */
    public static void setDefault(ProxySelector selector) {
        SecurityManager sm = System.getSecurityManager();
        if (null != sm) {
            sm.checkPermission(setProxySelectorPermission);
        }
        defaultSelector = selector;
    }

    /**
     * Gets all applicable proxies based on the accessing protocol of {@code
     * uri}. The format of URI is defined as below:
     * <p>
     * <li>http URI stands for http connection.</li>
     * <li>https URI stands for https connection.</li>
     * <li>ftp URI stands for ftp connection.</li>
     * <li>socket:://ip:port URI stands for tcp client sockets connection.</li>
     *
     * @param uri
     *            the target URI object.
     * @return a list containing all applicable proxies. If no proxy is
     *         available, the list contains only the {@code Proxy.NO_PROXY}
     *         element.
     * @throws IllegalArgumentException
     *             if {@code uri} is {@code null}.
     */
    public abstract List<Proxy> select(URI uri);

    /**
     * Notifies the {@code ProxySelector} that a connection to the proxy server
     * could not be established. A concrete implementation should upon this
     * notification maintain the list of available proxies, since an updated
     * version should be provided by {@code select()}.
     * 
     * @param uri
     *            the URI to which the connection could not be established.
     * @param sa
     *            the address of the proxy.
     * @param ioe
     *            the exception which was thrown during connection
     *            establishment.
     * @throws IllegalArgumentException
     *             if any argument is {@code null}.
     * @see #select(URI)
     */
    public abstract void connectFailed(URI uri, SocketAddress sa,
            IOException ioe);
}
