/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/conn/routing/HttpRoute.java $
 * $Revision: 653041 $
 * $Date: 2008-05-03 03:39:28 -0700 (Sat, 03 May 2008) $
 *
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.http.conn.routing;

import java.net.InetAddress;

import org.apache.http.HttpHost;

/**
 * The route for a request.
 * Instances of this class are unmodifiable and therefore suitable
 * for use as lookup keys.
 * 
 * @author <a href="mailto:rolandw at apache.org">Roland Weber</a>
 *
 *
 * <!-- empty lines to avoid svn diff problems -->
 * @version $Revision: 653041 $
 *
 * @since 4.0
 */
public final class HttpRoute implements RouteInfo, Cloneable {

    /** The target host to connect to. */
    private final HttpHost targetHost;

    /**
     * The local address to connect from.
     * <code>null</code> indicates that the default should be used.
     */
    private final InetAddress localAddress;

    /** The proxy servers, if any. */
    private final HttpHost[] proxyChain;

    /** Whether the the route is tunnelled through the proxy. */
    private final TunnelType tunnelled;

    /** Whether the route is layered. */
    private final LayerType layered;

    /** Whether the route is (supposed to be) secure. */
    private final boolean secure;


    /**
     * Internal, fully-specified constructor.
     * This constructor does <i>not</i> clone the proxy chain array,
     * nor test it for <code>null</code> elements. This conversion and
     * check is the responsibility of the public constructors.
     * The order of arguments here is different from the similar public
     * constructor, as required by Java.
     *
     * @param local     the local address to route from, or
     *                  <code>null</code> for the default
     * @param target    the host to which to route
     * @param proxies   the proxy chain to use, or
     *                  <code>null</code> for a direct route
     * @param secure    <code>true</code> if the route is (to be) secure,
     *                  <code>false</code> otherwise
     * @param tunnelled the tunnel type of this route, or
     *                  <code>null</code> for PLAIN
     * @param layered   the layering type of this route, or
     *                  <code>null</code> for PLAIN
     */
    private HttpRoute(InetAddress local,
                      HttpHost target, HttpHost[] proxies,
                      boolean secure,
                      TunnelType tunnelled, LayerType layered) {
        if (target == null) {
            throw new IllegalArgumentException
                ("Target host may not be null.");
        }
        if ((tunnelled == TunnelType.TUNNELLED) && (proxies == null)) {
            throw new IllegalArgumentException
                ("Proxy required if tunnelled.");
        }

        // tunnelled is already checked above, that is in line with the default
        if (tunnelled == null)
            tunnelled = TunnelType.PLAIN;
        if (layered == null)
            layered = LayerType.PLAIN;

        this.targetHost   = target;
        this.localAddress = local;
        this.proxyChain   = proxies;
        this.secure       = secure;
        this.tunnelled    = tunnelled;
        this.layered      = layered;
    }


    /**
     * Creates a new route with all attributes specified explicitly.
     *
     * @param target    the host to which to route
     * @param local     the local address to route from, or
     *                  <code>null</code> for the default
     * @param proxies   the proxy chain to use, or
     *                  <code>null</code> for a direct route
     * @param secure    <code>true</code> if the route is (to be) secure,
     *                  <code>false</code> otherwise
     * @param tunnelled the tunnel type of this route
     * @param layered   the layering type of this route
     */
    public HttpRoute(HttpHost target, InetAddress local, HttpHost[] proxies,
                     boolean secure, TunnelType tunnelled, LayerType layered) {
        this(local, target, toChain(proxies), secure, tunnelled, layered);
    }


    /**
     * Creates a new route with at most one proxy.
     *
     * @param target    the host to which to route
     * @param local     the local address to route from, or
     *                  <code>null</code> for the default
     * @param proxy     the proxy to use, or
     *                  <code>null</code> for a direct route
     * @param secure    <code>true</code> if the route is (to be) secure,
     *                  <code>false</code> otherwise
     * @param tunnelled <code>true</code> if the route is (to be) tunnelled
     *                  via the proxy,
     *                  <code>false</code> otherwise
     * @param layered   <code>true</code> if the route includes a
     *                  layered protocol,
     *                  <code>false</code> otherwise
     */
    public HttpRoute(HttpHost target, InetAddress local, HttpHost proxy,
                     boolean secure, TunnelType tunnelled, LayerType layered) {
        this(local, target, toChain(proxy), secure, tunnelled, layered);
    }


    /**
     * Creates a new direct route.
     * That is a route without a proxy.
     *
     * @param target    the host to which to route
     * @param local     the local address to route from, or
     *                  <code>null</code> for the default
     * @param secure    <code>true</code> if the route is (to be) secure,
     *                  <code>false</code> otherwise
     */
    public HttpRoute(HttpHost target, InetAddress local, boolean secure) {
        this(local, target, null, secure, TunnelType.PLAIN, LayerType.PLAIN);
    }


    /**
     * Creates a new direct insecure route.
     *
     * @param target    the host to which to route
     */
    public HttpRoute(HttpHost target) {
        this(null, target, null, false, TunnelType.PLAIN, LayerType.PLAIN);
    }


    /**
     * Creates a new route through a proxy.
     * When using this constructor, the <code>proxy</code> MUST be given.
     * For convenience, it is assumed that a secure connection will be
     * layered over a tunnel through the proxy.
     *
     * @param target    the host to which to route
     * @param local     the local address to route from, or
     *                  <code>null</code> for the default
     * @param proxy     the proxy to use
     * @param secure    <code>true</code> if the route is (to be) secure,
     *                  <code>false</code> otherwise
     */
    public HttpRoute(HttpHost target, InetAddress local, HttpHost proxy,
                     boolean secure) {
        this(local, target, toChain(proxy), secure,
             secure ? TunnelType.TUNNELLED : TunnelType.PLAIN,
             secure ? LayerType.LAYERED    : LayerType.PLAIN);
        if (proxy == null) {
            throw new IllegalArgumentException
                ("Proxy host may not be null.");
        }
    }


    /**
     * Helper to convert a proxy to a proxy chain.
     *
     * @param proxy     the only proxy in the chain, or <code>null</code>
     *
     * @return  a proxy chain array, or <code>null</code>
     */
    private static HttpHost[] toChain(HttpHost proxy) {
        if (proxy == null)
            return null;

        return new HttpHost[]{ proxy };
    }


    /**
     * Helper to duplicate and check a proxy chain.
     * An empty proxy chain is converted to <code>null</code>.
     *
     * @param proxies   the proxy chain to duplicate, or <code>null</code>
     *
     * @return  a new proxy chain array, or <code>null</code>
     */
    private static HttpHost[] toChain(HttpHost[] proxies) {
        if ((proxies == null) || (proxies.length < 1))
            return null;

        for (HttpHost proxy : proxies) {
            if (proxy == null)
                throw new IllegalArgumentException
                        ("Proxy chain may not contain null elements.");
        }

        // copy the proxy chain, the traditional way
        HttpHost[] result = new HttpHost[proxies.length];
        System.arraycopy(proxies, 0, result, 0, proxies.length);

        return result;
    }



    // non-JavaDoc, see interface RouteInfo
    public final HttpHost getTargetHost() {
        return this.targetHost;
    }


    // non-JavaDoc, see interface RouteInfo
    public final InetAddress getLocalAddress() {
        return this.localAddress;
    }


    // non-JavaDoc, see interface RouteInfo
    public final int getHopCount() {
        return (proxyChain == null) ? 1 : (proxyChain.length+1);
    }


    // non-JavaDoc, see interface RouteInfo
    public final HttpHost getHopTarget(int hop) {
        if (hop < 0)
            throw new IllegalArgumentException
                ("Hop index must not be negative: " + hop);
        final int hopcount = getHopCount();
        if (hop >= hopcount)
            throw new IllegalArgumentException
                ("Hop index " + hop +
                 " exceeds route length " + hopcount);

        HttpHost result = null;
        if (hop < hopcount-1)
            result = this.proxyChain[hop];
        else
            result = this.targetHost;

        return result;
    }


    // non-JavaDoc, see interface RouteInfo
    public final HttpHost getProxyHost() {
        return (this.proxyChain == null) ? null : this.proxyChain[0];
    }


    // non-JavaDoc, see interface RouteInfo
    public final TunnelType getTunnelType() {
        return this.tunnelled;
    }


    // non-JavaDoc, see interface RouteInfo
    public final boolean isTunnelled() {
        return (this.tunnelled == TunnelType.TUNNELLED);
    }


    // non-JavaDoc, see interface RouteInfo
    public final LayerType getLayerType() {
        return this.layered;
    }


    // non-JavaDoc, see interface RouteInfo
    public final boolean isLayered() {
        return (this.layered == LayerType.LAYERED);
    }


    // non-JavaDoc, see interface RouteInfo
    public final boolean isSecure() {
        return this.secure;
    }


    /**
     * Compares this route to another.
     *
     * @param o         the object to compare with
     *
     * @return  <code>true</code> if the argument is the same route,
     *          <code>false</code>
     */
    @Override
    public final boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof HttpRoute))
            return false;

        HttpRoute that = (HttpRoute) o;
        boolean equal = this.targetHost.equals(that.targetHost);
        equal &=
            ( this.localAddress == that.localAddress) ||
            ((this.localAddress != null) &&
              this.localAddress.equals(that.localAddress));
        equal &=
            ( this.proxyChain        == that.proxyChain) ||
            ((this.proxyChain        != null) &&
             (that.proxyChain        != null) &&
             (this.proxyChain.length == that.proxyChain.length));
        // comparison of actual proxies follows below
        equal &=
            (this.secure    == that.secure) &&
            (this.tunnelled == that.tunnelled) &&
            (this.layered   == that.layered);

        // chain length has been compared above, now check the proxies
        if (equal && (this.proxyChain != null)) {
            for (int i=0; equal && (i<this.proxyChain.length); i++)
                equal = this.proxyChain[i].equals(that.proxyChain[i]);
        }

        return equal;
    }


    /**
     * Generates a hash code for this route.
     *
     * @return  the hash code
     */
    @Override
    public final int hashCode() {

        int hc = this.targetHost.hashCode();

        if (this.localAddress != null)
            hc ^= localAddress.hashCode();
        if (this.proxyChain != null) {
            hc ^= proxyChain.length;
            for (HttpHost aProxyChain : proxyChain) hc ^= aProxyChain.hashCode();
        }

        if (this.secure)
            hc ^= 0x11111111;

        hc ^= this.tunnelled.hashCode();
        hc ^= this.layered.hashCode();

        return hc;
    }


    /**
     * Obtains a description of this route.
     *
     * @return  a human-readable representation of this route
     */
    @Override
    public final String toString() {
        StringBuilder cab = new StringBuilder(50 + getHopCount()*30);

        cab.append("HttpRoute[");
        if (this.localAddress != null) {
            cab.append(this.localAddress);
            cab.append("->");
        }
        cab.append('{');
        if (this.tunnelled == TunnelType.TUNNELLED)
            cab.append('t');
        if (this.layered == LayerType.LAYERED)
            cab.append('l');
        if (this.secure)
            cab.append('s');
        cab.append("}->");
        if (this.proxyChain != null) {
            for (HttpHost aProxyChain : this.proxyChain) {
                cab.append(aProxyChain);
                cab.append("->");
            }
        }
        cab.append(this.targetHost);
        cab.append(']');

        return cab.toString();
    }


    // default implementation of clone() is sufficient
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


} // class HttpRoute
