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

package javax.net.ssl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * The extension of {@code Socket} providing secure protocols like SSL (Secure
 * Socket Layer") or TLS (Transport Layer Security).
 */
public abstract class SSLSocket extends Socket {

    /**
     * Only to be used by subclasses.
     * <p>
     * Creates a TCP socket.
     */
    protected SSLSocket() {
        super();
    }

    /**
     * Only to be used by subclasses.
     * <p>
     * Creates a TCP socket connection to the specified host at the specified
     * port.
     *
     * @param host
     *            the host name to connect to.
     * @param port
     *            the port number to connect to.
     * @throws IOException
     *             if creating the socket fails.
     * @throws UnknownHostException
     *             if the specified host is not known.
     */
    protected SSLSocket(String host, int port) throws IOException, UnknownHostException {
        super(host, port);
    }

    /**
     * Only to be used by subclasses.
     * <p>
     * Creates a TCP socket connection to the specified address at the specified
     * port.
     *
     * @param address
     *            the address to connect to.
     * @param port
     *            the port number to connect to.
     * @throws IOException
     *             if creating the socket fails.
     */
    protected SSLSocket(InetAddress address, int port) throws IOException {
        super(address, port);
    }

    /**
     * Only to be used by subclasses.
     * <p>
     * Creates a TCP socket connection to the specified host at the specified
     * port with the client side bound to the specified address and port.
     *
     * @param host
     *            the host name to connect to.
     * @param port
     *            the port number to connect to.
     * @param clientAddress
     *            the client address to bind to
     * @param clientPort
     *            the client port number to bind to.
     * @throws IOException
     *             if creating the socket fails.
     * @throws UnknownHostException
     *             if the specified host is not known.
     */
    protected SSLSocket(String host, int port, InetAddress clientAddress, int clientPort)
            throws IOException, UnknownHostException {
        super(host, port, clientAddress, clientPort);
    }

    /**
     * Only to be used by subclasses.
     * <p>
     * Creates a TCP socket connection to the specified address at the specified
     * port with the client side bound to the specified address and port.
     *
     * @param address
     *            the address to connect to.
     * @param port
     *            the port number to connect to.
     * @param clientAddress
     *            the client address to bind to.
     * @param clientPort
     *            the client port number to bind to.
     * @throws IOException
     *             if creating the socket fails.
     */
    protected SSLSocket(InetAddress address, int port, InetAddress clientAddress, int clientPort)
            throws IOException {
        super(address, port, clientAddress, clientPort);
    }

    /**
     * Returns the names of the supported cipher suites.
     *
     * @return the names of the supported cipher suites.
     */
    public abstract String[] getSupportedCipherSuites();

    /**
     * Returns the names of the enabled cipher suites.
     *
     * @return the names of the enabled cipher suites.
     */
    public abstract String[] getEnabledCipherSuites();

    /**
     * Sets the names of the cipher suites to be enabled.
     * Only cipher suites returned by {@link #getSupportedCipherSuites()} are
     * allowed.
     *
     * @param suites
     *            the names of the to be enabled cipher suites.
     * @throws IllegalArgumentException
     *             if one of the cipher suite names is not supported.
     */
    public abstract void setEnabledCipherSuites(String[] suites);

    /**
     * Returns the names of the supported protocols.
     *
     * @return the names of the supported protocols.
     */
    public abstract String[] getSupportedProtocols();

    /**
     * Returns the names of the enabled protocols.
     *
     * @return the names of the enabled protocols.
     */
    public abstract String[] getEnabledProtocols();

    /**
     * Sets the names of the protocols to be enabled. Only
     * protocols returned by {@link #getSupportedProtocols()} are allowed.
     *
     * @param protocols
     *            the names of the to be enabled protocols.
     * @throws IllegalArgumentException
     *             if one of the protocols is not supported.
     */
    public abstract void setEnabledProtocols(String[] protocols);

    /**
     * Returns the {@code SSLSession} for this connection. If necessary, a
     * handshake will be initiated, in which case this method will block until the handshake
     * has been established. If the handshake fails, an invalid session object
     * will be returned.
     *
     * @return the session object.
     */
    public abstract SSLSession getSession();

    /**
     * Registers the specified listener to receive notification on completion of a
     * handshake on this connection.
     *
     * @param listener
     *            the listener to register.
     * @throws IllegalArgumentException
     *             if {@code listener} is {@code null}.
     */
    public abstract void addHandshakeCompletedListener(HandshakeCompletedListener listener);

    /**
     * Removes the specified handshake completion listener.
     *
     * @param listener
     *            the listener to remove.
     * @throws IllegalArgumentException
     *             if the specified listener is not registered or {@code null}.
     */
    public abstract void removeHandshakeCompletedListener(HandshakeCompletedListener listener);

    /**
     * Starts a new SSL handshake on this connection.
     *
     * @throws IOException
     *             if an error occurs.
     */
    public abstract void startHandshake() throws IOException;

    /**
     * Sets whether this connection should act in client mode when handshaking.
     *
     * @param mode
     *            {@code true} if this connection should act in client mode,
     *            {@code false} if not.
     */
    public abstract void setUseClientMode(boolean mode);

    /**
     * Returns whether this connection will act in client mode when handshaking.
     *
     * @return {@code true} if this connections will act in client mode when
     *         handshaking, {@code false} if not.
     */
    public abstract boolean getUseClientMode();

    /**
     * Sets whether this connection should require client authentication. This
     * is only useful for sockets in server mode. The client authentication is
     * one of the following:
     * <ul>
     * <li>authentication required</li>
     * <li>authentication requested</li>
     * <li>no authentication needed</li>
     * </ul>
     * This method overrides the setting of {@link #setWantClientAuth(boolean)}.
     *
     * @param need
     *            {@code true} if client authentication is required,
     *            {@code false} if no authentication is needed.
     */
    public abstract void setNeedClientAuth(boolean need);

    /**
     * Returns whether this connection requires client authentication.
     * This is only useful for sockets in server mode.
     *
     * @return {@code true} if client authentication is required, {@code false}
     *         if no client authentication is needed.
     */
    public abstract boolean getNeedClientAuth();

    /**
     * Sets whether this connections should request client authentication. This
     * is only useful for sockets in server mode. The client authentication is
     * one of:
     * <ul>
     * <li>authentication required</li>
     * <li>authentication requested</li>
     * <li>no authentication needed</li>
     * </ul>
     * This method overrides the setting of {@link #setNeedClientAuth(boolean)}.
     *
     * @param want
     *            {@code true} if client authentication should be requested,
     *            {@code false} if not authentication is needed.
     */
    public abstract void setWantClientAuth(boolean want);

    /**
     * Returns whether this connections will request client authentication.
     *
     * @return {@code true} is client authentication will be requested,
     *         {@code false} if no client authentication is needed.
     */
    public abstract boolean getWantClientAuth();

    /**
     * Sets whether new SSL sessions may be created by this socket or if
     * existing sessions must be reused.
     *
     * @param flag
     *            {@code true} if new sessions may be created, otherwise
     *            {@code false}.
     */
    public abstract void setEnableSessionCreation(boolean flag);

    /**
     * Returns whether new SSL sessions may be created by this socket or if
     * existing sessions must be reused.
     *
     * @return {@code true} if new sessions may be created, otherwise
     *         {@code false}.
     */
    public abstract boolean getEnableSessionCreation();

    public abstract void setSSLParameters(SSLParameters inputSSLParameters);
    
    public abstract SSLParameters getSSLParameters();
}
