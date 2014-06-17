/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/impl/conn/DefaultClientConnectionOperator.java $
 * $Revision: 652193 $
 * $Date: 2008-04-29 17:10:36 -0700 (Tue, 29 Apr 2008) $
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

package org.apache.http.impl.conn;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.InetAddress;

import java.net.SocketException;
import org.apache.http.HttpHost;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HttpContext;

import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.scheme.LayeredSocketFactory;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;


/**
 * Default implementation of a
 * {@link ClientConnectionOperator ClientConnectionOperator}.
 * It uses a {@link SchemeRegistry SchemeRegistry} to look up
 * {@link SocketFactory SocketFactory} objects.
 *
 * @author <a href="mailto:rolandw at apache.org">Roland Weber</a>
 *
 *
 * <!-- empty lines to avoid svn diff problems -->
 * @version   $Revision: 652193 $ $Date: 2008-04-29 17:10:36 -0700 (Tue, 29 Apr 2008) $
 *
 * @since 4.0
 */
public class DefaultClientConnectionOperator
    implements ClientConnectionOperator {

    private static final PlainSocketFactory staticPlainSocketFactory = new PlainSocketFactory();

    /** The scheme registry for looking up socket factories. */
    protected SchemeRegistry schemeRegistry;


    /**
     * Creates a new client connection operator for the given scheme registry.
     *
     * @param schemes   the scheme registry
     */
    public DefaultClientConnectionOperator(SchemeRegistry schemes) {
        if (schemes == null) {
            throw new IllegalArgumentException
                ("Scheme registry must not be null.");
        }
        schemeRegistry = schemes;
    }


    // non-javadoc, see interface ClientConnectionOperator
    public OperatedClientConnection createConnection() {
        return new DefaultClientConnection();
    }


    // non-javadoc, see interface ClientConnectionOperator
    public void openConnection(OperatedClientConnection conn,
                               HttpHost target,
                               InetAddress local,
                               HttpContext context,
                               HttpParams params)
        throws IOException {

        if (conn == null) {
            throw new IllegalArgumentException
                ("Connection must not be null.");
        }
        if (target == null) {
            throw new IllegalArgumentException
                ("Target host must not be null.");
        }
        // local address may be null
        //@@@ is context allowed to be null?
        if (params == null) {
            throw new IllegalArgumentException
                ("Parameters must not be null.");
        }
        if (conn.isOpen()) {
            throw new IllegalArgumentException
                ("Connection must not be open.");
        }

        final Scheme schm = schemeRegistry.getScheme(target.getSchemeName());
        final SocketFactory sf = schm.getSocketFactory();
        final SocketFactory plain_sf;
        final LayeredSocketFactory layered_sf;
        if (sf instanceof LayeredSocketFactory) {
            plain_sf = staticPlainSocketFactory;
            layered_sf = (LayeredSocketFactory)sf;
        } else {
            plain_sf = sf;
            layered_sf = null;
        }
        InetAddress[] addresses = InetAddress.getAllByName(target.getHostName());

        for (int i = 0; i < addresses.length; ++i) {
            Socket sock = plain_sf.createSocket();
            conn.opening(sock, target);

            try {
                Socket connsock = plain_sf.connectSocket(sock,
                    addresses[i].getHostAddress(),
                    schm.resolvePort(target.getPort()),
                    local, 0, params);
                if (sock != connsock) {
                    sock = connsock;
                    conn.opening(sock, target);
                }
                /*
                 * prepareSocket is called on the just connected
                 * socket before the creation of the layered socket to
                 * ensure that desired socket options such as
                 * TCP_NODELAY, SO_RCVTIMEO, SO_LINGER will be set
                 * before any I/O is performed on the socket. This
                 * happens in the common case as
                 * SSLSocketFactory.createSocket performs hostname
                 * verification which requires that SSL handshaking be
                 * performed.
                 */
                prepareSocket(sock, context, params);
                if (layered_sf != null) {
                    Socket layeredsock = layered_sf.createSocket(sock,
                        target.getHostName(),
                        schm.resolvePort(target.getPort()),
                        true);
                    if (layeredsock != sock) {
                        conn.opening(layeredsock, target);
                    }
                    conn.openCompleted(sf.isSecure(layeredsock), params);
                } else {
                    conn.openCompleted(sf.isSecure(sock), params);
                }
                break;
            // BEGIN android-changed
            //       catch SocketException to cover any kind of connect failure
            } catch (SocketException ex) {
                if (i == addresses.length - 1) {
                    ConnectException cause = ex instanceof ConnectException
                            ? (ConnectException) ex : new ConnectException(ex.getMessage(), ex);
                    throw new HttpHostConnectException(target, cause);
                }
            // END android-changed
            } catch (ConnectTimeoutException ex) {
                if (i == addresses.length - 1) {
                    throw ex;
                }
            }
        }
    } // openConnection


    // non-javadoc, see interface ClientConnectionOperator
    public void updateSecureConnection(OperatedClientConnection conn,
                                       HttpHost target,
                                       HttpContext context,
                                       HttpParams params)
        throws IOException {


        if (conn == null) {
            throw new IllegalArgumentException
                ("Connection must not be null.");
        }
        if (target == null) {
            throw new IllegalArgumentException
                ("Target host must not be null.");
        }
        //@@@ is context allowed to be null?
        if (params == null) {
            throw new IllegalArgumentException
                ("Parameters must not be null.");
        }
        if (!conn.isOpen()) {
            throw new IllegalArgumentException
                ("Connection must be open.");
        }

        final Scheme schm = schemeRegistry.getScheme(target.getSchemeName());
        if (!(schm.getSocketFactory() instanceof LayeredSocketFactory)) {
            throw new IllegalArgumentException
                ("Target scheme (" + schm.getName() +
                 ") must have layered socket factory.");
        }

        final LayeredSocketFactory lsf = (LayeredSocketFactory) schm.getSocketFactory();
        final Socket sock;
        try {
            sock = lsf.createSocket
                (conn.getSocket(), target.getHostName(), schm.resolvePort(target.getPort()), true);
        } catch (ConnectException ex) {
            throw new HttpHostConnectException(target, ex);
        }
        prepareSocket(sock, context, params);
        conn.update(sock, target, lsf.isSecure(sock), params);
        //@@@ error handling: close the layered socket in case of exception?

    } // updateSecureConnection


    /**
     * Performs standard initializations on a newly created socket.
     *
     * @param sock      the socket to prepare
     * @param context   the context for the connection
     * @param params    the parameters from which to prepare the socket
     *
     * @throws IOException      in case of an IO problem
     */
    protected void prepareSocket(Socket sock, HttpContext context,
                                 HttpParams params)
        throws IOException {

        // context currently not used, but derived classes may need it
        //@@@ is context allowed to be null?

        sock.setTcpNoDelay(HttpConnectionParams.getTcpNoDelay(params));
        sock.setSoTimeout(HttpConnectionParams.getSoTimeout(params));

        int linger = HttpConnectionParams.getLinger(params);
        if (linger >= 0) {
            sock.setSoLinger(linger > 0, linger);
        }

    } // prepareSocket


} // class DefaultClientConnectionOperator
