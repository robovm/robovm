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
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSession;

/**
 * Implementation of SSLEngine.
 * @see javax.net.ssl.SSLEngine class documentation for more information.
 */
public class SSLEngineImpl extends SSLEngine {

    // indicates if peer mode was set
    private boolean peer_mode_was_set = false;
    // indicates if handshake has been started
    private boolean handshake_started = false;
    // indicates if inbound operations finished
    private boolean isInboundDone = false;
    // indicates if outbound operations finished
    private boolean isOutboundDone = false;
    // indicates if close_notify alert had been sent to another peer
    private boolean close_notify_was_sent = false;
    // indicates if close_notify alert had been received from another peer
    private boolean close_notify_was_received = false;
    // indicates if engine was closed (it means that
    // all the works on it are done, except (probably) some finalizing work)
    private boolean engine_was_closed = false;
    // indicates if engine was shutted down (it means that
    // all cleaning work had been done and the engine is not operable)
    private boolean engine_was_shutteddown = false;

    // record protocol to be used
    protected SSLRecordProtocol recordProtocol;
    // input stream for record protocol
    private SSLBufferedInput recProtIS;
    // handshake protocol to be used
    private HandshakeProtocol handshakeProtocol;
    // alert protocol to be used
    private AlertProtocol alertProtocol;
    // place where application data will be stored
    private SSLEngineAppData appData;
    // outcoming application data stream
    private SSLEngineDataStream dataStream = new SSLEngineDataStream();
    // active session object
    private SSLSessionImpl session;

    // peer configuration parameters
    protected SSLParametersImpl sslParameters;

    // in case of emergency situations when data could not be
    // placed in destination buffers it will be stored in this
    // fields
    private byte[] remaining_wrapped_data = null;
    private byte[] remaining_hsh_data = null;

    // logger
    private Logger.Stream logger = Logger.getStream("engine");

    protected SSLEngineImpl(SSLParametersImpl sslParameters) {
        this.sslParameters = sslParameters;
    }

    protected SSLEngineImpl(String host, int port, SSLParametersImpl sslParameters) {
        super(host, port);
        this.sslParameters = sslParameters;
    }

    /**
     * Starts the handshake.
     * @throws  SSLException
     * @see javax.net.ssl.SSLEngine#beginHandshake() method documentation
     * for more information
     */
    @Override
    public void beginHandshake() throws SSLException {
        if (engine_was_closed) {
            throw new SSLException("Engine has already been closed.");
        }
        if (!peer_mode_was_set) {
            throw new IllegalStateException("Client/Server mode was not set");
        }
        if (!handshake_started) {
            handshake_started = true;
            if (getUseClientMode()) {
                handshakeProtocol = new ClientHandshakeImpl(this);
            } else {
                handshakeProtocol = new ServerHandshakeImpl(this);
            }
            appData = new SSLEngineAppData();
            alertProtocol = new AlertProtocol();
            recProtIS = new SSLBufferedInput();
            recordProtocol = new SSLRecordProtocol(handshakeProtocol,
                    alertProtocol, recProtIS, appData);
        }
        handshakeProtocol.start();
    }

    /**
     * Closes inbound operations of this engine
     * @throws  SSLException
     * @see javax.net.ssl.SSLEngine#closeInbound() method documentation
     * for more information
     */
    @Override
    public void closeInbound() throws SSLException {
        if (logger != null) {
            logger.println("closeInbound() "+isInboundDone);
        }
        if (isInboundDone) {
            return;
        }
        isInboundDone = true;
        engine_was_closed = true;
        if (handshake_started) {
            if (!close_notify_was_received) {
                if (session != null) {
                    session.invalidate();
                }
                alertProtocol.alert(AlertProtocol.FATAL,
                        AlertProtocol.INTERNAL_ERROR);
                throw new SSLException("Inbound is closed before close_notify "
                        + "alert has been received.");
            }
        } else {
            // engine is closing before initial handshake has been made
            shutdown();
        }
    }

    /**
     * Closes outbound operations of this engine
     * @see javax.net.ssl.SSLEngine#closeOutbound() method documentation
     * for more information
     */
    @Override
    public void closeOutbound() {
        if (logger != null) {
            logger.println("closeOutbound() "+isOutboundDone);
        }
        if (isOutboundDone) {
            return;
        }
        isOutboundDone = true;
        if (handshake_started) {
            // initial handshake had been started
            alertProtocol.alert(AlertProtocol.WARNING,
                    AlertProtocol.CLOSE_NOTIFY);
            close_notify_was_sent = true;
        } else {
            // engine is closing before initial handshake has been made
            shutdown();
        }
        engine_was_closed = true;
    }

    /**
     * Returns handshake's delegated tasks to be run
     * @return the delegated task to be executed.
     * @see javax.net.ssl.SSLEngine#getDelegatedTask() method documentation
     * for more information
     */
    @Override
    public Runnable getDelegatedTask() {
        return handshakeProtocol.getTask();
    }

    /**
     * Returns names of supported cipher suites.
     * @return array of strings containing the names of supported cipher suites
     * @see javax.net.ssl.SSLEngine#getSupportedCipherSuites() method
     * documentation for more information
     */
    @Override
    public String[] getSupportedCipherSuites() {
        return CipherSuite.getSupportedCipherSuiteNames();
    }

    // --------------- SSLParameters based methods ---------------------

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLEngine#getEnabledCipherSuites() method
     * documentation for more information
     */
    @Override
    public String[] getEnabledCipherSuites() {
        return sslParameters.getEnabledCipherSuites();
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLEngine#setEnabledCipherSuites(String[]) method
     * documentation for more information
     */
    @Override
    public void setEnabledCipherSuites(String[] suites) {
        sslParameters.setEnabledCipherSuites(suites);
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLEngine#getSupportedProtocols() method
     * documentation for more information
     */
    @Override
    public String[] getSupportedProtocols() {
        return ProtocolVersion.supportedProtocols.clone();
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLEngine#getEnabledProtocols() method
     * documentation for more information
     */
    @Override
    public String[] getEnabledProtocols() {
        return sslParameters.getEnabledProtocols();
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLEngine#setEnabledProtocols(String[]) method
     * documentation for more information
     */
    @Override
    public void setEnabledProtocols(String[] protocols) {
        sslParameters.setEnabledProtocols(protocols);
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLEngine#setUseClientMode(boolean) method
     * documentation for more information
     */
    @Override
    public void setUseClientMode(boolean mode) {
        if (handshake_started) {
            throw new IllegalArgumentException(
            "Could not change the mode after the initial handshake has begun.");
        }
        sslParameters.setUseClientMode(mode);
        peer_mode_was_set = true;
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLEngine#getUseClientMode() method
     * documentation for more information
     */
    @Override
    public boolean getUseClientMode() {
        return sslParameters.getUseClientMode();
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLEngine#setNeedClientAuth(boolean) method
     * documentation for more information
     */
    @Override
    public void setNeedClientAuth(boolean need) {
        sslParameters.setNeedClientAuth(need);
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLEngine#getNeedClientAuth() method
     * documentation for more information
     */
    @Override
    public boolean getNeedClientAuth() {
        return sslParameters.getNeedClientAuth();
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLEngine#setWantClientAuth(boolean) method
     * documentation for more information
     */
    @Override
    public void setWantClientAuth(boolean want) {
        sslParameters.setWantClientAuth(want);
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLEngine#getWantClientAuth() method
     * documentation for more information
     */
    @Override
    public boolean getWantClientAuth() {
        return sslParameters.getWantClientAuth();
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLEngine#setEnableSessionCreation(boolean) method
     * documentation for more information
     */
    @Override
    public void setEnableSessionCreation(boolean flag) {
        sslParameters.setEnableSessionCreation(flag);
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLEngine#getEnableSessionCreation() method
     * documentation for more information
     */
    @Override
    public boolean getEnableSessionCreation() {
        return sslParameters.getEnableSessionCreation();
    }

    // -----------------------------------------------------------------

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLEngine#getHandshakeStatus() method
     * documentation for more information
     */
    @Override
    public SSLEngineResult.HandshakeStatus getHandshakeStatus() {
        if (!handshake_started || engine_was_shutteddown) {
            // initial handshake has not been started yet
            return SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
        }
        if (alertProtocol.hasAlert()) {
            // need to send an alert
            return SSLEngineResult.HandshakeStatus.NEED_WRAP;
        }
        if (close_notify_was_sent && !close_notify_was_received) {
            // waiting for "close_notify" response
            return SSLEngineResult.HandshakeStatus.NEED_UNWRAP;
        }
        return handshakeProtocol.getStatus();
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLEngine#getSession() method
     * documentation for more information
     */
    @Override
    public SSLSession getSession() {
        if (session != null) {
            return session;
        }
        return SSLSessionImpl.NULL_SESSION;
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLEngine#isInboundDone() method
     * documentation for more information
     */
    @Override
    public boolean isInboundDone() {
        return isInboundDone || engine_was_closed;
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLEngine#isOutboundDone() method
     * documentation for more information
     */
    @Override
    public boolean isOutboundDone() {
        return isOutboundDone;
    }

    /**
     * Decodes one complete SSL/TLS record provided in the source buffer.
     * If decoded record contained application data, this data will
     * be placed in the destination buffers.
     * For more information about TLS record fragmentation see
     * TLS v 1 specification (http://www.ietf.org/rfc/rfc2246.txt) p 6.2.
     * @param src source buffer containing SSL/TLS record.
     * @param dsts destination buffers to place received application data.
     * @see javax.net.ssl.SSLEngine#unwrap(ByteBuffer,ByteBuffer[],int,int)
     * method documentation for more information
     */
    @Override
    public SSLEngineResult unwrap(ByteBuffer src, ByteBuffer[] dsts,
                                int offset, int length) throws SSLException {
        if (engine_was_shutteddown) {
            return new SSLEngineResult(SSLEngineResult.Status.CLOSED,
                    SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING, 0, 0);
        }
        if ((src == null) || (dsts == null)) {
            throw new IllegalStateException(
                    "Some of the input parameters are null");
        }

        if (!handshake_started) {
            beginHandshake();
        }

        SSLEngineResult.HandshakeStatus handshakeStatus = getHandshakeStatus();
        // If is is initial handshake or connection closure stage,
        // check if this call was made in spite of handshake status
        if ((session == null || engine_was_closed) && (
                    handshakeStatus.equals(
                        SSLEngineResult.HandshakeStatus.NEED_WRAP) ||
                    handshakeStatus.equals(
                        SSLEngineResult.HandshakeStatus.NEED_TASK))) {
            return new SSLEngineResult(
                    getEngineStatus(), handshakeStatus, 0, 0);
        }

        if (src.remaining() < recordProtocol.getMinRecordSize()) {
            return new SSLEngineResult(
                    SSLEngineResult.Status.BUFFER_UNDERFLOW,
                    getHandshakeStatus(), 0, 0);
        }

        try {
            src.mark();
            // check the destination buffers and count their capacity
            int capacity = 0;
            for (int i=offset; i<offset+length; i++) {
                if (dsts[i] == null) {
                    throw new IllegalStateException(
                            "Some of the input parameters are null");
                }
                if (dsts[i].isReadOnly()) {
                    throw new ReadOnlyBufferException();
                }
                capacity += dsts[i].remaining();
            }
            if (capacity < recordProtocol.getDataSize(src.remaining())) {
                return new SSLEngineResult(
                        SSLEngineResult.Status.BUFFER_OVERFLOW,
                        getHandshakeStatus(), 0, 0);
            }
            recProtIS.setSourceBuffer(src);
            // unwrap the record contained in source buffer, pass it
            // to appropriate client protocol (alert, handshake, or app)
            // and retrieve the type of unwrapped data
            int type = recordProtocol.unwrap();
            // process the data and return the result
            switch (type) {
                case ContentType.HANDSHAKE:
                case ContentType.CHANGE_CIPHER_SPEC:
                    if (handshakeProtocol.getStatus().equals(
                            SSLEngineResult.HandshakeStatus.FINISHED)) {
                        session = recordProtocol.getSession();
                    }
                    break;
                case ContentType.APPLICATION_DATA:
                    break;
                case ContentType.ALERT:
                    if (alertProtocol.isFatalAlert()) {
                        alertProtocol.setProcessed();
                        if (session != null) {
                            session.invalidate();
                        }
                        String description = "Fatal alert received "
                            + alertProtocol.getAlertDescription();
                        shutdown();
                        throw new SSLException(description);
                    } else {
                        if (logger != null) {
                            logger.println("Warning allert has been received: "
                                + alertProtocol.getAlertDescription());
                        }
                        switch(alertProtocol.getDescriptionCode()) {
                            case AlertProtocol.CLOSE_NOTIFY:
                                alertProtocol.setProcessed();
                                close_notify_was_received = true;
                                if (!close_notify_was_sent) {
                                    closeOutbound();
                                    closeInbound();
                                } else {
                                    closeInbound();
                                    shutdown();
                                }
                                break;
                            case AlertProtocol.NO_RENEGOTIATION:
                                alertProtocol.setProcessed();
                                if (session == null) {
                                    // message received during the initial
                                    // handshake
                                    throw new AlertException(
                                        AlertProtocol.HANDSHAKE_FAILURE,
                                        new SSLHandshakeException(
                                            "Received no_renegotiation "
                                            + "during the initial handshake"));
                                } else {
                                    // just stop the handshake
                                    handshakeProtocol.stop();
                                }
                                break;
                            default:
                                alertProtocol.setProcessed();
                        }
                    }
                    break;
            }
            return new SSLEngineResult(getEngineStatus(), getHandshakeStatus(),
                    recProtIS.consumed(),
                    // place the app. data (if any) into the dest. buffers
                    // and get the number of produced bytes:
                    appData.placeTo(dsts, offset, length));
        } catch (BufferUnderflowException e) {
            // there was not enought data ource buffer to make complete packet
            src.reset();
            return new SSLEngineResult(SSLEngineResult.Status.BUFFER_UNDERFLOW,
                    getHandshakeStatus(), 0, 0);
        } catch (AlertException e) {
            // fatal alert occured
            alertProtocol.alert(AlertProtocol.FATAL, e.getDescriptionCode());
            engine_was_closed = true;
            src.reset();
            if (session != null) {
                session.invalidate();
            }
            // shutdown work will be made after the alert will be sent
            // to another peer (by wrap method)
            throw e.getReason();
        } catch (SSLException e) {
            throw e;
        } catch (IOException e) {
            alertProtocol.alert(AlertProtocol.FATAL,
                    AlertProtocol.INTERNAL_ERROR);
            engine_was_closed = true;
            // shutdown work will be made after the alert will be sent
            // to another peer (by wrap method)
            throw new SSLException(e.getMessage());
        }
    }

    /**
     * Encodes the application data into SSL/TLS record. If handshake status
     * of the engine differs from NOT_HANDSHAKING the operation can work
     * without consuming of the source data.
     * For more information about TLS record fragmentation see
     * TLS v 1 specification (http://www.ietf.org/rfc/rfc2246.txt) p 6.2.
     * @param srcs the source buffers with application data to be encoded
     * into SSL/TLS record.
     * @param offset the offset in the destination buffers array pointing to
     * the first buffer with the source data.
     * @param len specifies the maximum number of buffers to be procesed.
     * @param dst the destination buffer where encoded data will be placed.
     * @see javax.net.ssl.SSLEngine#wrap(ByteBuffer[],int,int,ByteBuffer) method
     * documentation for more information
     */
    @Override
    public SSLEngineResult wrap(ByteBuffer[] srcs, int offset,
                            int len, ByteBuffer dst) throws SSLException {
        if (engine_was_shutteddown) {
            return new SSLEngineResult(SSLEngineResult.Status.CLOSED,
                    SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING, 0, 0);
        }
        if ((srcs == null) || (dst == null)) {
            throw new IllegalStateException(
                    "Some of the input parameters are null");
        }
        if (dst.isReadOnly()) {
            throw new ReadOnlyBufferException();
        }

        if (!handshake_started) {
            beginHandshake();
        }

        SSLEngineResult.HandshakeStatus handshakeStatus = getHandshakeStatus();
        // If it is an initial handshake or connection closure stage,
        // check if this call was made in spite of handshake status
        if ((session == null || engine_was_closed) && (
                handshakeStatus.equals(
                        SSLEngineResult.HandshakeStatus.NEED_UNWRAP) ||
                handshakeStatus.equals(
                        SSLEngineResult.HandshakeStatus.NEED_TASK))) {
            return new SSLEngineResult(
                    getEngineStatus(), handshakeStatus, 0, 0);
        }

        int capacity = dst.remaining();
        int produced = 0;

        if (alertProtocol.hasAlert()) {
            // we have an alert to be sent
            if (capacity < recordProtocol.getRecordSize(2)) {
                return new SSLEngineResult(
                        SSLEngineResult.Status.BUFFER_OVERFLOW,
                        handshakeStatus, 0, 0);
            }
            byte[] alert_data = alertProtocol.wrap();
            // place the alert record into destination
            dst.put(alert_data);
            if (alertProtocol.isFatalAlert()) {
                alertProtocol.setProcessed();
                if (session != null) {
                    session.invalidate();
                }
                // fatal alert has been sent, so shut down the engine
                shutdown();
                return new SSLEngineResult(
                        SSLEngineResult.Status.CLOSED,
                        SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING,
                        0, alert_data.length);
            } else {
                alertProtocol.setProcessed();
                // check if the works on this engine have been done
                if (close_notify_was_sent && close_notify_was_received) {
                    shutdown();
                    return new SSLEngineResult(SSLEngineResult.Status.CLOSED,
                            SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING,
                            0, alert_data.length);
                }
                return new SSLEngineResult(
                        getEngineStatus(),
                        getHandshakeStatus(),
                        0, alert_data.length);
            }
        }

        if (capacity < recordProtocol.getMinRecordSize()) {
            if (logger != null) {
                logger.println("Capacity of the destination("
                        +capacity+") < MIN_PACKET_SIZE("
                        +recordProtocol.getMinRecordSize()+")");
            }
            return new SSLEngineResult(SSLEngineResult.Status.BUFFER_OVERFLOW,
                        handshakeStatus, 0, 0);
        }

        try {
            if (!handshakeStatus.equals(
                        SSLEngineResult.HandshakeStatus.NEED_WRAP)) {
                // so we wraps application data
                dataStream.setSourceBuffers(srcs, offset, len);
                if ((capacity < SSLRecordProtocol.MAX_SSL_PACKET_SIZE) &&
                    (capacity < recordProtocol.getRecordSize(
                                                 dataStream.available()))) {
                    if (logger != null) {
                        logger.println("The destination buffer("
                                +capacity+") can not take the resulting packet("
                                + recordProtocol.getRecordSize(
                                    dataStream.available())+")");
                    }
                    return new SSLEngineResult(
                            SSLEngineResult.Status.BUFFER_OVERFLOW,
                            handshakeStatus, 0, 0);
                }
                if (remaining_wrapped_data == null) {
                    remaining_wrapped_data =
                        recordProtocol.wrap(ContentType.APPLICATION_DATA,
                                dataStream);
                }
                if (capacity < remaining_wrapped_data.length) {
                    // It should newer happen because we checked the destination
                    // buffer size, but there is a possibility
                    // (if dest buffer was filled outside)
                    // so we just remember the data into remaining_wrapped_data
                    // and will enclose it during the the next call
                    return new SSLEngineResult(
                            SSLEngineResult.Status.BUFFER_OVERFLOW,
                            handshakeStatus, dataStream.consumed(), 0);
                } else {
                    dst.put(remaining_wrapped_data);
                    produced = remaining_wrapped_data.length;
                    remaining_wrapped_data = null;
                    return new SSLEngineResult(getEngineStatus(),
                            handshakeStatus, dataStream.consumed(), produced);
                }
            } else {
                if (remaining_hsh_data == null) {
                    remaining_hsh_data = handshakeProtocol.wrap();
                }
                if (capacity < remaining_hsh_data.length) {
                    // It should newer happen because we checked the destination
                    // buffer size, but there is a possibility
                    // (if dest buffer was filled outside)
                    // so we just remember the data into remaining_hsh_data
                    // and will enclose it during the the next call
                    return new SSLEngineResult(
                            SSLEngineResult.Status.BUFFER_OVERFLOW,
                            handshakeStatus, 0, 0);
                } else {
                    dst.put(remaining_hsh_data);
                    produced = remaining_hsh_data.length;
                    remaining_hsh_data = null;

                    handshakeStatus = handshakeProtocol.getStatus();
                    if (handshakeStatus.equals(
                            SSLEngineResult.HandshakeStatus.FINISHED)) {
                        session = recordProtocol.getSession();
                    }
                }
                return new SSLEngineResult(
                        getEngineStatus(), getHandshakeStatus(), 0, produced);
            }
        } catch (AlertException e) {
            // fatal alert occured
            alertProtocol.alert(AlertProtocol.FATAL, e.getDescriptionCode());
            engine_was_closed = true;
            if (session != null) {
                session.invalidate();
            }
            // shutdown work will be made after the alert will be sent
            // to another peer (by wrap method)
            throw e.getReason();
        }
    }

    // Shutdownes the engine and makes all cleanup work.
    private void shutdown() {
        engine_was_closed = true;
        engine_was_shutteddown = true;
        isOutboundDone = true;
        isInboundDone = true;
        if (handshake_started) {
            alertProtocol.shutdown();
            alertProtocol = null;
            handshakeProtocol.shutdown();
            handshakeProtocol = null;
            recordProtocol.shutdown();
            recordProtocol = null;
        }
    }


    private SSLEngineResult.Status getEngineStatus() {
        return (engine_was_closed)
            ? SSLEngineResult.Status.CLOSED
            : SSLEngineResult.Status.OK;
    }
}
