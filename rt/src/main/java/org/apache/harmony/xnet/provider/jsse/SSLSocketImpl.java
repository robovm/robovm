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

package org.apache.harmony.xnet.provider.jsse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

/**
 * SSLSocket implementation.
 * @see javax.net.ssl.SSLSocket class documentation for more information.
 */
public class SSLSocketImpl extends SSLSocket {

    // indicates if handshake has been started
    private boolean handshake_started = false;

    // record protocol to be used
    protected SSLRecordProtocol recordProtocol;
    // handshake protocol to be used
    private HandshakeProtocol handshakeProtocol;
    // alert protocol to be used
    private AlertProtocol alertProtocol;
    // application data input stream, this stream is presented by
    // ssl socket as an input stream. Additionally this object is a
    // place where application data will be stored by record protocol
    private SSLSocketInputStream appDataIS;
    // outgoing application data stream
    private SSLSocketOutputStream appDataOS;
    // active session object
    private SSLSessionImpl session;

    private boolean socket_was_closed = false;

    // the sslParameters object encapsulates all the info
    // about supported and enabled cipher suites and protocols,
    // as well as the information about client/server mode of
    // ssl socket, whether it require/want client authentication or not,
    // and controls whether new SSL sessions may be established by this
    // socket or not.
    protected SSLParametersImpl sslParameters;
    // super's streams to be wrapped:
    protected InputStream input;
    protected OutputStream output;
    // handshake complete listeners
    private ArrayList<HandshakeCompletedListener> listeners;
    // logger
    private Logger.Stream logger = Logger.getStream("socket");

    // ----------------- Constructors and initializers --------------------

    /**
     * Constructor
     * @param   sslParameters:  SSLParametersImpl
     * @see javax.net.ssl.SSLSocket#SSLSocket() method documentation
     * for more information.
     */
    protected SSLSocketImpl(SSLParametersImpl sslParameters) {
        this.sslParameters = sslParameters;
        // init should be called after creation!
    }

    /**
     * Constructor
     * @param   host:   String
     * @param   port:   int
     * @param   sslParameters:  SSLParametersImpl
     * @throws  IOException
     * @throws  UnknownHostException
     * @see javax.net.ssl.SSLSocket#SSLSocket(String,int)
     * method documentation for more information.
     */
    protected SSLSocketImpl(String host, int port, SSLParametersImpl sslParameters)
            throws IOException, UnknownHostException {
        super(host, port);
        this.sslParameters = sslParameters;
        init();
    }

    /**
     * Constructor
     * @param   host:   String
     * @param   port:   int
     * @param   localHost:  InetAddress
     * @param   localPort:  int
     * @param   sslParameters:  SSLParametersImpl
     * @throws  IOException
     * @throws  UnknownHostException
     * @see javax.net.ssl.SSLSocket#SSLSocket(String,int,InetAddress,int)
     * method documentation for more information.
     */
    protected SSLSocketImpl(String host, int port,
            InetAddress localHost, int localPort,
            SSLParametersImpl sslParameters) throws IOException,
            UnknownHostException {
        super(host, port, localHost, localPort);
        this.sslParameters = sslParameters;
        init();
    }

    /**
     * Constructor
     * @param   host:   InetAddress
     * @param   port:   int
     * @param   sslParameters:  SSLParametersImpl
     * @return
     * @throws  IOException
     * @see javax.net.ssl.SSLSocket#SSLSocket(InetAddress,int)
     * method documentation for more information.
     */
    protected SSLSocketImpl(InetAddress host, int port,
            SSLParametersImpl sslParameters) throws IOException {
        super(host, port);
        this.sslParameters = sslParameters;
        init();
    }

    /**
     * Constructor
     * @param   address:    InetAddress
     * @param   port:   int
     * @param   localAddress:   InetAddress
     * @param   localPort:  int
     * @param   sslParameters:  SSLParametersImpl
     * @return
     * @throws  IOException
     * @see javax.net.ssl.SSLSocket#SSLSocket(InetAddress,int,InetAddress,int)
     * method documentation for more information.
     */
    protected SSLSocketImpl(InetAddress address, int port,
            InetAddress localAddress, int localPort,
            SSLParametersImpl sslParameters) throws IOException {
        super(address, port, localAddress, localPort);
        this.sslParameters = sslParameters;
        init();
    }

    /**
     * Initialize the SSL socket.
     */
    protected void init() throws IOException {
        if (appDataIS != null) {
            // already initialized
            return;
        }
        initTransportLayer();
        appDataIS = new SSLSocketInputStream(this);
        appDataOS = new SSLSocketOutputStream(this);
    }

    /**
     * Initialize the transport data streams.
     */
    protected void initTransportLayer() throws IOException {
        input = super.getInputStream();
        output = super.getOutputStream();
    }

    /**
     * Closes the transport data streams.
     */
    protected void closeTransportLayer() throws IOException {
        super.close();
        if (input != null) {
            input.close();
            output.close();
        }
    }

    // --------------- SSLParameters based methods ---------------------

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLSocket#getSupportedCipherSuites()
     * method documentation for more information
     */
    @Override
    public String[] getSupportedCipherSuites() {
        return CipherSuite.getSupportedCipherSuiteNames();
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLSocket#getEnabledCipherSuites()
     * method documentation for more information
     */
    @Override
    public String[] getEnabledCipherSuites() {
        return sslParameters.getEnabledCipherSuites();
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLSocket#setEnabledCipherSuites(String[])
     * method documentation for more information
     */
    @Override
    public void setEnabledCipherSuites(String[] suites) {
        sslParameters.setEnabledCipherSuites(suites);
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLSocket#getSupportedProtocols()
     * method documentation for more information
     */
    @Override
    public String[] getSupportedProtocols() {
        return ProtocolVersion.supportedProtocols.clone();
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLSocket#getEnabledProtocols()
     * method documentation for more information
     */
    @Override
    public String[] getEnabledProtocols() {
        return sslParameters.getEnabledProtocols();
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLSocket#setEnabledProtocols(String[])
     * method documentation for more information
     */
    @Override
    public void setEnabledProtocols(String[] protocols) {
        sslParameters.setEnabledProtocols(protocols);
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLSocket#setUseClientMode(boolean)
     * method documentation for more information
     */
    @Override
    public void setUseClientMode(boolean mode) {
        if (handshake_started) {
            throw new IllegalArgumentException(
            "Could not change the mode after the initial handshake has begun.");
        }
        sslParameters.setUseClientMode(mode);
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLSocket#getUseClientMode()
     * method documentation for more information
     */
    @Override
    public boolean getUseClientMode() {
        return sslParameters.getUseClientMode();
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLSocket#setNeedClientAuth(boolean)
     * method documentation for more information
     */
    @Override
    public void setNeedClientAuth(boolean need) {
        sslParameters.setNeedClientAuth(need);
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLSocket#getNeedClientAuth()
     * method documentation for more information
     */
    @Override
    public boolean getNeedClientAuth() {
        return sslParameters.getNeedClientAuth();
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLSocket#setWantClientAuth(boolean)
     * method documentation for more information
     */
    @Override
    public void setWantClientAuth(boolean want) {
        sslParameters.setWantClientAuth(want);
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLSocket#getWantClientAuth()
     * method documentation for more information
     */
    @Override
    public boolean getWantClientAuth() {
        return sslParameters.getWantClientAuth();
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLSocket#setEnableSessionCreation(boolean)
     * method documentation for more information
     */
    @Override
    public void setEnableSessionCreation(boolean flag) {
        sslParameters.setEnableSessionCreation(flag);
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLSocket#getEnableSessionCreation()
     * method documentation for more information
     */
    @Override
    public boolean getEnableSessionCreation() {
        return sslParameters.getEnableSessionCreation();
    }

    // -----------------------------------------------------------------

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLSocket#getSession()
     * method documentation for more information
     */
    @Override
    public SSLSession getSession() {
        if (!handshake_started) {
            try {
                startHandshake();
            } catch (IOException e) {
                // return an invalid session with
                // invalid cipher suite of "SSL_NULL_WITH_NULL_NULL"
                return SSLSessionImpl.NULL_SESSION;
            }
        }
        return session;
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLSocket#addHandshakeCompletedListener(HandshakeCompletedListener)
     * method documentation for more information
     */
    @Override
    public void addHandshakeCompletedListener(
            HandshakeCompletedListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Provided listener is null");
        }
        if (listeners == null) {
            listeners = new ArrayList<HandshakeCompletedListener>();
        }
        listeners.add(listener);
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLSocket#removeHandshakeCompletedListener(HandshakeCompletedListener)
     * method documentation for more information
     */
    @Override
    public void removeHandshakeCompletedListener(
            HandshakeCompletedListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Provided listener is null");
        }
        if (listeners == null) {
            throw new IllegalArgumentException(
                    "Provided listener is not registered");
        }
        if (!listeners.remove(listener)) {
            throw new IllegalArgumentException(
                    "Provided listener is not registered");
        }
    }

    /**
     * Performs the handshake process over the SSL/TLS connection
     * as described in rfc 2246, TLS v1 specification
     * http://www.ietf.org/rfc/rfc2246.txt. If the initial handshake
     * has been already done, this method initiates rehandshake.
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLSocket#startHandshake()
     * method documentation for more information
     */
    @Override
    public void startHandshake() throws IOException {
        if (appDataIS == null) {
            throw new IOException("Socket is not connected.");
        }
        if (socket_was_closed) {
            throw new IOException("Socket has already been closed.");
        }

        if (!handshake_started) {
            handshake_started = true;
            if (sslParameters.getUseClientMode()) {
                if (logger != null) {
                    logger.println("SSLSocketImpl: CLIENT");
                }
                handshakeProtocol = new ClientHandshakeImpl(this);
            } else {
                if (logger != null) {
                    logger.println("SSLSocketImpl: SERVER");
                }
                handshakeProtocol = new ServerHandshakeImpl(this);
            }

            alertProtocol = new AlertProtocol();
            recordProtocol = new SSLRecordProtocol(handshakeProtocol,
                    alertProtocol, new SSLStreamedInput(input),
                    appDataIS.dataPoint);
        }

        if (logger != null) {
            logger.println("SSLSocketImpl.startHandshake");
        }

        handshakeProtocol.start();

        doHandshake();

        if (logger != null) {
            logger.println("SSLSocketImpl.startHandshake: END");
        }
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLSocket#getInputStream()
     * method documentation for more information
     */
    @Override
    public InputStream getInputStream() throws IOException {
        if (socket_was_closed) {
            throw new IOException("Socket has already been closed.");
        }
        return appDataIS;
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLSocket#getOutputStream()
     * method documentation for more information
     */
    @Override
    public OutputStream getOutputStream() throws IOException {
        if (socket_was_closed) {
            throw new IOException("Socket has already been closed.");
        }
        return appDataOS;
    }

    /**
     * This method works according to the specification of implemented class.
     * @see java.net.Socket#connect(SocketAddress)
     * method documentation for more information
     */
    @Override
    public void connect(SocketAddress endpoint) throws IOException {
        super.connect(endpoint);
        init();
    }

    /**
     * This method works according to the specification of implemented class.
     * @see java.net.Socket#connect(SocketAddress,int)
     * method documentation for more information
     */
    @Override
    public void connect(SocketAddress endpoint, int timeout)
            throws IOException {
        super.connect(endpoint, timeout);
        init();
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLSocket#close()
     * method documentation for more information
     */
    @Override
    public void close() throws IOException {
        if (logger != null) {
            logger.println("SSLSocket.close "+socket_was_closed);
        }
        if (!socket_was_closed) {
            if (handshake_started) {
                alertProtocol.alert(AlertProtocol.WARNING,
                        AlertProtocol.CLOSE_NOTIFY);
                try {
                    output.write(alertProtocol.wrap());
                } catch (IOException ex) { }
                alertProtocol.setProcessed();
            }
            shutdown();
            closeTransportLayer();
            socket_was_closed = true;
        }
    }

    /**
     * This method is not supported for SSLSocket implementation.
     */
    @Override
    public void sendUrgentData(int data) throws IOException {
        throw new SocketException(
                "Method sendUrgentData() is not supported.");
    }

    /**
     * This method is not supported for SSLSocket implementation.
     */
    @Override
    public void setOOBInline(boolean on) throws SocketException {
        throw new SocketException(
                "Methods sendUrgentData, setOOBInline are not supported.");
    }

    // -----------------------------------------------------------------

    private void shutdown() {
        if (handshake_started) {
            alertProtocol.shutdown();
            alertProtocol = null;
            handshakeProtocol.shutdown();
            handshakeProtocol = null;
            recordProtocol.shutdown();
            recordProtocol = null;
        }
        socket_was_closed = true;
    }

    /**
     * This method is called by SSLSocketInputStream class
     * when client application tries to read application data from
     * the stream, but there is no data in its underlying buffer.
     * @throws  IOException
     */
    protected void needAppData() throws IOException {
        if (!handshake_started) {
            startHandshake();
        }
        int type;
        if (logger != null) {
            logger.println("SSLSocket.needAppData..");
        }
        try {
            while(appDataIS.available() == 0) {
                // read and unwrap the record contained in the transport
                // input stream (SSLStreamedInput), pass it
                // to appropriate client protocol (alert, handshake, or app)
                // and retrieve the type of unwrapped data
                switch (type = recordProtocol.unwrap()) {
                    case ContentType.HANDSHAKE:
                        if (!handshakeProtocol.getStatus().equals(
                                SSLEngineResult.HandshakeStatus
                                .NOT_HANDSHAKING)) {
                            // handshake protocol got addressed to it message
                            // and did not ignore it, so it's a rehandshake
                            doHandshake();
                        }
                        break;
                    case ContentType.ALERT:
                        processAlert();
                        if (socket_was_closed) {
                            return;
                        }
                        break;
                    case ContentType.APPLICATION_DATA:
                        if (logger != null) {
                            logger.println(
                                    "SSLSocket.needAppData: got the data");
                        }
                        break;
                    default:
                        // will throw exception
                        reportFatalAlert(AlertProtocol.UNEXPECTED_MESSAGE,
                                new SSLException("Unexpected message of type "
                                    + type + " has been got"));
                }
                if (alertProtocol.hasAlert()) {
                    // warning alert occurred during wrap or unwrap
                    // (note: fatal alert causes AlertException
                    // to be thrown)
                    output.write(alertProtocol.wrap());
                    alertProtocol.setProcessed();
                }
                if (socket_was_closed) {
                    appDataIS.setEnd();
                    return;
                }
            }
        } catch (AlertException e) {
            // will throw exception
            reportFatalAlert(e.getDescriptionCode(), e.getReason());
        } catch (EndOfSourceException e) {
            // end of socket's input stream has been reached
            appDataIS.setEnd();
        }
        if (logger != null) {
            logger.println("SSLSocket.needAppData: app data len: "
                    + appDataIS.available());
        }
    }

    /**
     * This method is called by SSLSocketOutputStream when a client application
     * tries to send the data over ssl protocol.
     */
    protected void writeAppData(byte[] data, int offset, int len) throws IOException {
        if (!handshake_started) {
            startHandshake();
        }
        if (logger != null) {
            logger.println("SSLSocket.writeAppData: " +
                    len + " " + SSLRecordProtocol.MAX_DATA_LENGTH);
            //logger.println(new String(data, offset, len));
        }
        try {
            if (len < SSLRecordProtocol.MAX_DATA_LENGTH) {
                output.write(recordProtocol.wrap(ContentType.APPLICATION_DATA,
                            data, offset, len));
            } else {
                while (len >= SSLRecordProtocol.MAX_DATA_LENGTH) {
                    output.write(recordProtocol.wrap(
                                ContentType.APPLICATION_DATA, data, offset,
                                SSLRecordProtocol.MAX_DATA_LENGTH));
                    offset += SSLRecordProtocol.MAX_DATA_LENGTH;
                    len -= SSLRecordProtocol.MAX_DATA_LENGTH;
                }
                if (len > 0) {
                    output.write(
                        recordProtocol.wrap(ContentType.APPLICATION_DATA,
                                            data, offset, len));
                }
            }
        } catch (AlertException e) {
            // will throw exception
            reportFatalAlert(e.getDescriptionCode(), e.getReason());
        }
    }

    /*
     * Performs handshake process over this connection. The handshake
     * process is directed by the handshake status code provided by
     * handshake protocol. If this status is NEED_WRAP, method retrieves
     * handshake message from handshake protocol and sends it to another peer.
     * If this status is NEED_UNWRAP, method receives and processes handshake
     * message from another peer. Each of this stages (wrap/unwrap) change
     * the state of handshake protocol and this process is performed
     * until handshake status is FINISHED. After handshake process is finished
     * handshake completed event are sent to the registered listeners.
     * For more information about the handshake process see
     * TLS v1 specification (http://www.ietf.org/rfc/rfc2246.txt) p 7.3.
     */
    private void doHandshake() throws IOException {
        SSLEngineResult.HandshakeStatus status;
        int type;
        try {
            while (!(status = handshakeProtocol.getStatus()).equals(
                        SSLEngineResult.HandshakeStatus.FINISHED)) {
                if (logger != null) {
                    String s = (status.equals(
                                SSLEngineResult.HandshakeStatus.NEED_WRAP))
                        ? "NEED_WRAP"
                        : (status.equals(
                                SSLEngineResult.HandshakeStatus.NEED_UNWRAP))
                            ? "NEED_UNWRAP"
                            : "STATUS: OTHER!";
                    logger.println("SSLSocketImpl: HS status: "+s+" "+status);
                }
                if (status.equals(SSLEngineResult.HandshakeStatus.NEED_WRAP)) {
                    output.write(handshakeProtocol.wrap());
                } else if (status.equals(
                            SSLEngineResult.HandshakeStatus.NEED_UNWRAP)) {
                    // read and unwrap the record contained in the transport
                    // input stream (SSLStreamedInput), pass it
                    // to appropriate client protocol (alert, handshake, or app)
                    // and retrieve the type of unwrapped data
                    switch (type = recordProtocol.unwrap()) {
                        case ContentType.HANDSHAKE:
                        case ContentType.CHANGE_CIPHER_SPEC:
                            break;
                        case ContentType.APPLICATION_DATA:
                            // So it's rehandshake and
                            // if app data buffer will be overloaded
                            // it will throw alert exception.
                            // Probably we should count the number of
                            // not handshaking data and make additional
                            // constraints (do not expect buffer overflow).
                            break;
                        case ContentType.ALERT:
                            processAlert();
                            if (socket_was_closed) {
                                return;
                            }
                            break;
                        default:
                            // will throw exception
                            reportFatalAlert(AlertProtocol.UNEXPECTED_MESSAGE,
                                    new SSLException(
                                        "Unexpected message of type "
                                        + type + " has been got"));
                    }
                } else {
                    // will throw exception
                    reportFatalAlert(AlertProtocol.INTERNAL_ERROR,
                        new SSLException(
                            "Handshake passed unexpected status: "+status));
                }
                if (alertProtocol.hasAlert()) {
                    // warning alert occurred during wrap or unwrap
                    // (note: fatal alert causes AlertException
                    // to be thrown)
                    output.write(alertProtocol.wrap());
                    alertProtocol.setProcessed();
                }
            }
        } catch (EndOfSourceException e) {
            appDataIS.setEnd();
            throw new IOException("Connection was closed");
        } catch (AlertException e) {
            // will throw exception
            reportFatalAlert(e.getDescriptionCode(), e.getReason());
        }

        session = recordProtocol.getSession();
        if (listeners != null) {
            // notify the listeners
            HandshakeCompletedEvent event =
                new HandshakeCompletedEvent(this, session);
            int size = listeners.size();
            for (int i=0; i<size; i++) {
                listeners.get(i)
                    .handshakeCompleted(event);
            }
        }
    }

    /*
     * Process received alert message
     */
    private void processAlert() throws IOException {
        if (!alertProtocol.hasAlert()) {
            return;
        }
        if (alertProtocol.isFatalAlert()) {
            alertProtocol.setProcessed();
            String description = "Fatal alert received "
                + alertProtocol.getAlertDescription();
            shutdown();
            throw new SSLException(description);
        }

        if (logger != null) {
            logger.println("Warning alert received: "
                + alertProtocol.getAlertDescription());
        }
        switch(alertProtocol.getDescriptionCode()) {
            case AlertProtocol.CLOSE_NOTIFY:
                alertProtocol.setProcessed();
                appDataIS.setEnd();
                close();
                return;
            default:
                alertProtocol.setProcessed();
            // TODO: process other warning messages
        }
    }

    /*
     * Sends fatal alert message and throws exception
     */
    private void reportFatalAlert(byte description_code,
            SSLException reason) throws IOException {
        alertProtocol.alert(AlertProtocol.FATAL, description_code);
        try {
            // the output stream can be closed
            output.write(alertProtocol.wrap());
        } catch (IOException ex) { }
        alertProtocol.setProcessed();
        shutdown();
        throw reason;
    }
}
