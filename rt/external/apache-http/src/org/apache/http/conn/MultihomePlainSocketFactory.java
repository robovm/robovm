/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/conn/MultihomePlainSocketFactory.java $
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

package org.apache.http.conn;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;

import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/**
 * Socket factory that implements a simple multi-home fail-over on connect failure, 
 * provided the same hostname resolves to multiple {@link InetAddress}es. Please note
 * the {@link #connectSocket(Socket, String, int, InetAddress, int, HttpParams)}
 * method cannot be reliably interrupted by closing the socket returned by the
 * {@link #createSocket()} method.
 */
public final class MultihomePlainSocketFactory implements SocketFactory {

    /**
     * The factory singleton.
     */
    private static final
    MultihomePlainSocketFactory DEFAULT_FACTORY = new MultihomePlainSocketFactory();

    /**
     * Gets the singleton instance of this class.
     * @return the one and only plain socket factory
     */
    public static MultihomePlainSocketFactory getSocketFactory() {
        return DEFAULT_FACTORY;
    }

    /**
     * Restricted default constructor.
     */
    private MultihomePlainSocketFactory() {
        super();
    }


    // non-javadoc, see interface org.apache.http.conn.SocketFactory
    public Socket createSocket() {
        return new Socket();
    }

    /**
     * Attempts to connects the socket to any of the {@link InetAddress}es the 
     * given host name resolves to. If connection to all addresses fail, the  
     * last I/O exception is propagated to the caller.
     * 
     * @param sock socket to connect to any of the given addresses
     * @param host Host name to connect to
     * @param port the port to connect to
     * @param localAddress local address
     * @param localPort local port
     * @param params HTTP parameters 
     * 
     * @throws  IOException if an error occurs during the connection
     * @throws  SocketTimeoutException if timeout expires before connecting
     */
    public Socket connectSocket(Socket sock, String host, int port, 
                                InetAddress localAddress, int localPort,
                                HttpParams params)
        throws IOException {

        if (host == null) {
            throw new IllegalArgumentException("Target host may not be null.");
        }
        if (params == null) {
            throw new IllegalArgumentException("Parameters may not be null.");
        }

        if (sock == null)
            sock = createSocket();

        if ((localAddress != null) || (localPort > 0)) {

            // we need to bind explicitly
            if (localPort < 0)
                localPort = 0; // indicates "any"

            InetSocketAddress isa =
                new InetSocketAddress(localAddress, localPort);
            sock.bind(isa);
        }

        int timeout = HttpConnectionParams.getConnectionTimeout(params);

        InetAddress[] inetadrs = InetAddress.getAllByName(host);
        List<InetAddress> addresses = new ArrayList<InetAddress>(inetadrs.length);
        addresses.addAll(Arrays.asList(inetadrs));
        Collections.shuffle(addresses);

        IOException lastEx = null;
        for (InetAddress address: addresses) {
            try {
                sock.connect(new InetSocketAddress(address, port), timeout);
                break;
            } catch (SocketTimeoutException ex) {
                throw ex;
            } catch (IOException ex) {
                // create new socket
                sock = new Socket();
                // keep the last exception and retry
                lastEx = ex;
            }
        }
        if (lastEx != null) {
            throw lastEx;
        }
        return sock;
    } // connectSocket


    /**
     * Checks whether a socket connection is secure.
     * This factory creates plain socket connections
     * which are not considered secure.
     *
     * @param sock      the connected socket
     *
     * @return  <code>false</code>
     *
     * @throws IllegalArgumentException if the argument is invalid
     */
    public final boolean isSecure(Socket sock)
        throws IllegalArgumentException {

        if (sock == null) {
            throw new IllegalArgumentException("Socket may not be null.");
        }
        // This class check assumes that createSocket() calls the constructor
        // directly. If it was using javax.net.SocketFactory, we couldn't make
        // an assumption about the socket class here.
        if (sock.getClass() != Socket.class) {
            throw new IllegalArgumentException
                ("Socket not created by this factory.");
        }
        // This check is performed last since it calls a method implemented
        // by the argument object. getClass() is final in java.lang.Object.
        if (sock.isClosed()) {
            throw new IllegalArgumentException("Socket is closed.");
        }

        return false;

    } // isSecure


    /**
     * Compares this factory with an object.
     * There is only one instance of this class.
     *
     * @param obj       the object to compare with
     *
     * @return  iff the argument is this object
     */
    @Override
    public boolean equals(Object obj) {
        return (obj == this);
    }

    /**
     * Obtains a hash code for this object.
     * All instances of this class have the same hash code.
     * There is only one instance of this class.
     */
    @Override
    public int hashCode() {
        return PlainSocketFactory.class.hashCode();
    }

}
