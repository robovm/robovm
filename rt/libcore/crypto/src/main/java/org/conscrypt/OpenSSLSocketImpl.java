/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.conscrypt;

import dalvik.system.BlockGuard;
import dalvik.system.CloseGuard;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;
import static libcore.io.OsConstants.*;
import libcore.io.ErrnoException;
import libcore.io.Libcore;
import libcore.io.Streams;
import libcore.io.StructTimeval;

/**
 * Implementation of the class OpenSSLSocketImpl based on OpenSSL.
 * <p>
 * Extensions to SSLSocket include:
 * <ul>
 * <li>handshake timeout
 * <li>session tickets
 * <li>Server Name Indication
 * </ul>
 */
public class OpenSSLSocketImpl
        extends javax.net.ssl.SSLSocket
        implements NativeCrypto.SSLHandshakeCallbacks {

    private long sslNativePointer;
    private InputStream is;
    private OutputStream os;
    private final Object handshakeLock = new Object();
    private final Object readLock = new Object();
    private final Object writeLock = new Object();
    private SSLParametersImpl sslParameters;
    private byte[] npnProtocols;
    private byte[] alpnProtocols;
    private String[] enabledProtocols;
    private String[] enabledCipherSuites;
    private boolean useSessionTickets;
    private String hostname;
    /** Whether the TLS Channel ID extension is enabled. This field is server-side only. */
    private boolean channelIdEnabled;
    /** Private key for the TLS Channel ID extension. This field is client-side only. */
    private OpenSSLKey channelIdPrivateKey;
    private OpenSSLSessionImpl sslSession;
    private final Socket socket;
    private boolean autoClose;
    private boolean handshakeStarted = false;
    private final CloseGuard guard = CloseGuard.get();

    /**
     * Not set to true until the update from native that tells us the
     * full handshake is complete, since SSL_do_handshake can return
     * before the handshake is completely done due to
     * handshake_cutthrough support.
     */
    private boolean handshakeCompleted = false;

    private ArrayList<HandshakeCompletedListener> listeners;

    /**
     * Local cache of timeout to avoid getsockopt on every read and
     * write for non-wrapped sockets. Note that
     * OpenSSLSocketImplWrapper overrides setSoTimeout and
     * getSoTimeout to delegate to the wrapped socket.
     */
    private int readTimeoutMilliseconds = 0;
    private int writeTimeoutMilliseconds = 0;

    private int handshakeTimeoutMilliseconds = -1;  // -1 = same as timeout; 0 = infinite
    private String wrappedHost;
    private int wrappedPort;

    protected OpenSSLSocketImpl(SSLParametersImpl sslParameters) throws IOException {
        this.socket = this;
        init(sslParameters);
    }

    protected OpenSSLSocketImpl(SSLParametersImpl sslParameters,
                                String[] enabledProtocols,
                                String[] enabledCipherSuites) throws IOException {
        this.socket = this;
        init(sslParameters, enabledProtocols, enabledCipherSuites);
    }

    protected OpenSSLSocketImpl(String host, int port, SSLParametersImpl sslParameters)
            throws IOException {
        super(host, port);
        this.socket = this;
        init(sslParameters);
    }

    protected OpenSSLSocketImpl(InetAddress address, int port, SSLParametersImpl sslParameters)
            throws IOException {
        super(address, port);
        this.socket = this;
        init(sslParameters);
    }


    protected OpenSSLSocketImpl(String host, int port,
                                InetAddress clientAddress, int clientPort,
                                SSLParametersImpl sslParameters) throws IOException {
        super(host, port, clientAddress, clientPort);
        this.socket = this;
        init(sslParameters);
    }

    protected OpenSSLSocketImpl(InetAddress address, int port,
                                InetAddress clientAddress, int clientPort,
                                SSLParametersImpl sslParameters) throws IOException {
        super(address, port, clientAddress, clientPort);
        this.socket = this;
        init(sslParameters);
    }

    /**
     * Create an SSL socket that wraps another socket. Invoked by
     * OpenSSLSocketImplWrapper constructor.
     */
    protected OpenSSLSocketImpl(Socket socket, String host, int port,
            boolean autoClose, SSLParametersImpl sslParameters) throws IOException {
        this.socket = socket;
        this.wrappedHost = host;
        this.wrappedPort = port;
        this.autoClose = autoClose;
        init(sslParameters);

        // this.timeout is not set intentionally.
        // OpenSSLSocketImplWrapper.getSoTimeout will delegate timeout
        // to wrapped socket
    }

    /**
     * Initialize the SSL socket and set the certificates for the
     * future handshaking.
     */
    private void init(SSLParametersImpl sslParameters) throws IOException {
        init(sslParameters,
             NativeCrypto.getDefaultProtocols(),
             NativeCrypto.getDefaultCipherSuites());
    }

    /**
     * Initialize the SSL socket and set the certificates for the
     * future handshaking.
     */
    private void init(SSLParametersImpl sslParameters,
                      String[] enabledProtocols,
                      String[] enabledCipherSuites) throws IOException {
        this.sslParameters = sslParameters;
        this.enabledProtocols = enabledProtocols;
        this.enabledCipherSuites = enabledCipherSuites;
    }

    /**
     * Gets the suitable session reference from the session cache container.
     */
    private OpenSSLSessionImpl getCachedClientSession(ClientSessionContext sessionContext) {
        String hostName = getPeerHostName();
        int port = getPeerPort();
        if (hostName == null) {
            return null;
        }
        OpenSSLSessionImpl session = (OpenSSLSessionImpl) sessionContext.getSession(hostName, port);
        if (session == null) {
            return null;
        }

        String protocol = session.getProtocol();
        boolean protocolFound = false;
        for (String enabledProtocol : enabledProtocols) {
            if (protocol.equals(enabledProtocol)) {
                protocolFound = true;
                break;
            }
        }
        if (!protocolFound) {
            return null;
        }

        String cipherSuite = session.getCipherSuite();
        boolean cipherSuiteFound = false;
        for (String enabledCipherSuite : enabledCipherSuites) {
            if (cipherSuite.equals(enabledCipherSuite)) {
                cipherSuiteFound = true;
                break;
            }
        }
        if (!cipherSuiteFound) {
            return null;
        }

        return session;
    }

    private void checkOpen() throws SocketException {
        if (isClosed()) {
            throw new SocketException("Socket is closed");
        }
    }

    /**
     * Starts a TLS/SSL handshake on this connection using some native methods
     * from the OpenSSL library. It can negotiate new encryption keys, change
     * cipher suites, or initiate a new session. The certificate chain is
     * verified if the correspondent property in java.Security is set. All
     * listeners are notified at the end of the TLS/SSL handshake.
     */
    @Override public synchronized void startHandshake() throws IOException {
        synchronized (handshakeLock) {
            checkOpen();
            if (!handshakeStarted) {
                handshakeStarted = true;
            } else {
                return;
            }
        }

        // note that this modifies the global seed, not something specific to the connection
        final int seedLengthInBytes = NativeCrypto.RAND_SEED_LENGTH_IN_BYTES;
        final SecureRandom secureRandom = sslParameters.getSecureRandomMember();
        if (secureRandom == null) {
            NativeCrypto.RAND_load_file("/dev/urandom", seedLengthInBytes);
        } else {
            NativeCrypto.RAND_seed(secureRandom.generateSeed(seedLengthInBytes));
        }

        final boolean client = sslParameters.getUseClientMode();

        final long sslCtxNativePointer = (client) ?
            sslParameters.getClientSessionContext().sslCtxNativePointer :
            sslParameters.getServerSessionContext().sslCtxNativePointer;

        this.sslNativePointer = 0;
        boolean exception = true;
        try {
            sslNativePointer = NativeCrypto.SSL_new(sslCtxNativePointer);
            guard.open("close");

            if (npnProtocols != null) {
                NativeCrypto.SSL_CTX_enable_npn(sslCtxNativePointer);
            }

            if (client && alpnProtocols != null) {
                NativeCrypto.SSL_CTX_set_alpn_protos(sslCtxNativePointer, alpnProtocols);
            }

            // setup server certificates and private keys.
            // clients will receive a call back to request certificates.
            if (!client) {
                Set<String> keyTypes = new HashSet<String>();
                for (String enabledCipherSuite : enabledCipherSuites) {
                    if (enabledCipherSuite.equals(NativeCrypto.TLS_EMPTY_RENEGOTIATION_INFO_SCSV)) {
                        continue;
                    }
                    String keyType = CipherSuite.getByName(enabledCipherSuite).getServerKeyType();
                    if (keyType != null) {
                        keyTypes.add(keyType);
                    }
                }
                for (String keyType : keyTypes) {
                    try {
                        setCertificate(sslParameters.getKeyManager().chooseServerAlias(keyType,
                                                                                       null,
                                                                                       this));
                    } catch (CertificateEncodingException e) {
                        throw new IOException(e);
                    }
                }
            }

            NativeCrypto.setEnabledProtocols(sslNativePointer, enabledProtocols);
            NativeCrypto.setEnabledCipherSuites(sslNativePointer, enabledCipherSuites);
            if (useSessionTickets) {
                NativeCrypto.SSL_clear_options(sslNativePointer, NativeCrypto.SSL_OP_NO_TICKET);
            }
            if (hostname != null) {
                NativeCrypto.SSL_set_tlsext_host_name(sslNativePointer, hostname);
            }

            boolean enableSessionCreation = sslParameters.getEnableSessionCreation();
            if (!enableSessionCreation) {
                NativeCrypto.SSL_set_session_creation_enabled(sslNativePointer,
                                                              enableSessionCreation);
            }

            AbstractSessionContext sessionContext;
            OpenSSLSessionImpl sessionToReuse;
            if (client) {
                // look for client session to reuse
                ClientSessionContext clientSessionContext = sslParameters.getClientSessionContext();
                sessionContext = clientSessionContext;
                sessionToReuse = getCachedClientSession(clientSessionContext);
                if (sessionToReuse != null) {
                    NativeCrypto.SSL_set_session(sslNativePointer,
                                                 sessionToReuse.sslSessionNativePointer);
                }
            } else {
                sessionContext = sslParameters.getServerSessionContext();
                sessionToReuse = null;
            }

            // setup peer certificate verification
            if (client) {
                // TODO support for anonymous cipher would require us to
                // conditionally use SSL_VERIFY_NONE
            } else {
                // needing client auth takes priority...
                boolean certRequested;
                if (sslParameters.getNeedClientAuth()) {
                    NativeCrypto.SSL_set_verify(sslNativePointer,
                                                NativeCrypto.SSL_VERIFY_PEER
                                                | NativeCrypto.SSL_VERIFY_FAIL_IF_NO_PEER_CERT);
                    certRequested = true;
                // ... over just wanting it...
                } else if (sslParameters.getWantClientAuth()) {
                    NativeCrypto.SSL_set_verify(sslNativePointer,
                                                NativeCrypto.SSL_VERIFY_PEER);
                    certRequested = true;
                // ... and it defaults properly so don't call SSL_set_verify in the common case.
                } else {
                    certRequested = false;
                }

                if (certRequested) {
                    X509TrustManager trustManager = sslParameters.getTrustManager();
                    X509Certificate[] issuers = trustManager.getAcceptedIssuers();
                    if (issuers != null && issuers.length != 0) {
                        byte[][] issuersBytes;
                        try {
                            issuersBytes = encodeIssuerX509Principals(issuers);
                        } catch (CertificateEncodingException e) {
                            throw new IOException("Problem encoding principals", e);
                        }
                        NativeCrypto.SSL_set_client_CA_list(sslNativePointer, issuersBytes);
                    }
                }
            }

            // Temporarily use a different timeout for the handshake process
            int savedReadTimeoutMilliseconds = getSoTimeout();
            int savedWriteTimeoutMilliseconds = getSoWriteTimeout();
            if (handshakeTimeoutMilliseconds >= 0) {
                setSoTimeout(handshakeTimeoutMilliseconds);
                setSoWriteTimeout(handshakeTimeoutMilliseconds);
            }

            // TLS Channel ID
            if (channelIdEnabled) {
                if (client) {
                    // Client-side TLS Channel ID
                    if (channelIdPrivateKey == null) {
                        throw new SSLHandshakeException("Invalid TLS channel ID key specified");
                    }
                    NativeCrypto.SSL_set1_tls_channel_id(sslNativePointer,
                            channelIdPrivateKey.getPkeyContext());
                } else {
                    // Server-side TLS Channel ID
                    NativeCrypto.SSL_enable_tls_channel_id(sslNativePointer);
                }
            }

            int sslSessionNativePointer;
            try {
                sslSessionNativePointer = NativeCrypto.SSL_do_handshake(sslNativePointer,
                        socket.getFileDescriptor$(), this, getSoTimeout(), client, npnProtocols,
                        client ? null : alpnProtocols);
            } catch (CertificateException e) {
                SSLHandshakeException wrapper = new SSLHandshakeException(e.getMessage());
                wrapper.initCause(e);
                throw wrapper;
            }
            byte[] sessionId = NativeCrypto.SSL_SESSION_session_id(sslSessionNativePointer);
            if (sessionToReuse != null && Arrays.equals(sessionToReuse.getId(), sessionId)) {
                this.sslSession = sessionToReuse;
                sslSession.lastAccessedTime = System.currentTimeMillis();
                NativeCrypto.SSL_SESSION_free(sslSessionNativePointer);
            } else {
                if (!enableSessionCreation) {
                    // Should have been prevented by NativeCrypto.SSL_set_session_creation_enabled
                    throw new IllegalStateException("SSL Session may not be created");
                }
                X509Certificate[] localCertificates
                        = createCertChain(NativeCrypto.SSL_get_certificate(sslNativePointer));
                X509Certificate[] peerCertificates
                        = createCertChain(NativeCrypto.SSL_get_peer_cert_chain(sslNativePointer));
                this.sslSession = new OpenSSLSessionImpl(sslSessionNativePointer, localCertificates,
                        peerCertificates, getPeerHostName(), getPeerPort(), sessionContext);
                // if not, putSession later in handshakeCompleted() callback
                if (handshakeCompleted) {
                    sessionContext.putSession(sslSession);
                }
            }

            // Restore the original timeout now that the handshake is complete
            if (handshakeTimeoutMilliseconds >= 0) {
                setSoTimeout(savedReadTimeoutMilliseconds);
                setSoWriteTimeout(savedWriteTimeoutMilliseconds);
            }

            // if not, notifyHandshakeCompletedListeners later in handshakeCompleted() callback
            if (handshakeCompleted) {
                notifyHandshakeCompletedListeners();
            }

            exception = false;
        } catch (SSLProtocolException e) {
            throw new SSLHandshakeException(e);
        } finally {
            // on exceptional exit, treat the socket as closed
            if (exception) {
                close();
            }
        }
    }

    private static byte[][] encodeIssuerX509Principals(X509Certificate[] certificates)
            throws CertificateEncodingException {
        byte[][] principalBytes = new byte[certificates.length][];
        for (int i = 0; i < certificates.length; i++) {
            principalBytes[i] = certificates[i].getIssuerX500Principal().getEncoded();
        }
        return principalBytes;
    }

    String getPeerHostName() {
        if (wrappedHost != null) {
            return wrappedHost;
        }
        InetAddress inetAddress = super.getInetAddress();
        if (inetAddress != null) {
            return inetAddress.getHostName();
        }
        return null;
    }

    int getPeerPort() {
        return wrappedHost == null ? super.getPort() : wrappedPort;
    }

    /**
     * Return a possibly null array of X509Certificates given the
     * possibly null array of DER encoded bytes.
     */
    private static X509Certificate[] createCertChain(byte[][] certificatesBytes) throws IOException {
        if (certificatesBytes == null) {
            return null;
        }
        X509Certificate[] certificates = new X509Certificate[certificatesBytes.length];
        for (int i = 0; i < certificatesBytes.length; i++) {
            certificates[i] = OpenSSLX509Certificate.fromX509Der(certificatesBytes[i]);
        }
        return certificates;
    }

    private void setCertificate(String alias) throws CertificateEncodingException, SSLException {
        if (alias == null) {
            return;
        }
        PrivateKey privateKey = sslParameters.getKeyManager().getPrivateKey(alias);
        if (privateKey == null) {
            return;
        }
        X509Certificate[] certificates = sslParameters.getKeyManager().getCertificateChain(alias);
        if (certificates == null) {
            return;
        }

        // Note that OpenSSL says to use SSL_use_certificate before SSL_use_PrivateKey.

        byte[][] certificateBytes = NativeCrypto.encodeCertificates(certificates);
        NativeCrypto.SSL_use_certificate(sslNativePointer, certificateBytes);

        try {
            final OpenSSLKey key = OpenSSLKey.fromPrivateKey(privateKey);
            NativeCrypto.SSL_use_PrivateKey(sslNativePointer, key.getPkeyContext());
        } catch (InvalidKeyException e) {
            throw new SSLException(e);
        }

        // checks the last installed private key and certificate,
        // so need to do this once per loop iteration
        NativeCrypto.SSL_check_private_key(sslNativePointer);
    }

    @SuppressWarnings("unused") // used by NativeCrypto.SSLHandshakeCallbacks / client_cert_cb
    public void clientCertificateRequested(byte[] keyTypeBytes, byte[][] asn1DerEncodedPrincipals)
            throws CertificateEncodingException, SSLException {

        String[] keyTypes = new String[keyTypeBytes.length];
        for (int i = 0; i < keyTypeBytes.length; i++) {
            keyTypes[i] = CipherSuite.getClientKeyType(keyTypeBytes[i]);
        }

        X500Principal[] issuers;
        if (asn1DerEncodedPrincipals == null) {
            issuers = null;
        } else {
            issuers = new X500Principal[asn1DerEncodedPrincipals.length];
            for (int i = 0; i < asn1DerEncodedPrincipals.length; i++) {
                issuers[i] = new X500Principal(asn1DerEncodedPrincipals[i]);
            }
        }
        setCertificate(sslParameters.getKeyManager().chooseClientAlias(keyTypes, issuers, this));
    }

    @SuppressWarnings("unused") // used by NativeCrypto.SSLHandshakeCallbacks / info_callback
    public void handshakeCompleted() {
        handshakeCompleted = true;

        // If sslSession is null, the handshake was completed during
        // the call to NativeCrypto.SSL_do_handshake and not during a
        // later read operation. That means we do not need to fix up
        // the SSLSession and session cache or notify
        // HandshakeCompletedListeners, it will be done in
        // startHandshake.
        if (sslSession == null) {
            return;
        }

        // reset session id from the native pointer and update the
        // appropriate cache.
        sslSession.resetId();
        AbstractSessionContext sessionContext =
            (sslParameters.getUseClientMode())
            ? sslParameters.getClientSessionContext()
                : sslParameters.getServerSessionContext();
        sessionContext.putSession(sslSession);

        // let listeners know we are finally done
        notifyHandshakeCompletedListeners();
    }

    private void notifyHandshakeCompletedListeners() {
        if (listeners != null && !listeners.isEmpty()) {
            // notify the listeners
            HandshakeCompletedEvent event =
                new HandshakeCompletedEvent(this, sslSession);
            for (HandshakeCompletedListener listener : listeners) {
                try {
                    listener.handshakeCompleted(event);
                } catch (RuntimeException e) {
                    // The RI runs the handlers in a separate thread,
                    // which we do not. But we try to preserve their
                    // behavior of logging a problem and not killing
                    // the handshaking thread just because a listener
                    // has a problem.
                    Thread thread = Thread.currentThread();
                    thread.getUncaughtExceptionHandler().uncaughtException(thread, e);
                }
            }
        }
    }

    @SuppressWarnings("unused") // used by NativeCrypto.SSLHandshakeCallbacks
    @Override public void verifyCertificateChain(byte[][] bytes, String authMethod)
            throws CertificateException {
        try {
            if (bytes == null || bytes.length == 0) {
                throw new SSLException("Peer sent no certificate");
            }
            X509Certificate[] peerCertificateChain = new X509Certificate[bytes.length];
            for (int i = 0; i < bytes.length; i++) {
                peerCertificateChain[i] = OpenSSLX509Certificate.fromX509Der(bytes[i]);
            }
            boolean client = sslParameters.getUseClientMode();
            if (client) {
                X509TrustManager x509tm = sslParameters.getTrustManager();
                if (x509tm instanceof TrustManagerImpl) {
                    TrustManagerImpl tm = (TrustManagerImpl) x509tm;
                    tm.checkServerTrusted(peerCertificateChain, authMethod, wrappedHost);
                } else {
                    x509tm.checkServerTrusted(peerCertificateChain, authMethod);
                }
            } else {
                String authType = peerCertificateChain[0].getPublicKey().getAlgorithm();
                sslParameters.getTrustManager().checkClientTrusted(peerCertificateChain,
                                                                   authType);
            }

        } catch (CertificateException e) {
            throw e;
        } catch (Exception e) {
            throw new CertificateException(e);
        }
    }

    @Override public InputStream getInputStream() throws IOException {
        checkOpen();
        synchronized (this) {
            if (is == null) {
                is = new SSLInputStream();
            }

            return is;
        }
    }

    @Override public OutputStream getOutputStream() throws IOException {
        checkOpen();
        synchronized (this) {
            if (os == null) {
                os = new SSLOutputStream();
            }

            return os;
        }
    }

    /**
     * This inner class provides input data stream functionality
     * for the OpenSSL native implementation. It is used to
     * read data received via SSL protocol.
     */
    private class SSLInputStream extends InputStream {
        SSLInputStream() throws IOException {
            /*
             * Note: When startHandshake() throws an exception, no
             * SSLInputStream object will be created.
             */
            OpenSSLSocketImpl.this.startHandshake();
        }

        /**
         * Reads one byte. If there is no data in the underlying buffer,
         * this operation can block until the data will be
         * available.
         * @return read value.
         * @throws <code>IOException</code>
         */
        @Override
        public int read() throws IOException {
            return Streams.readSingleByte(this);
        }

        /**
         * Method acts as described in spec for superclass.
         * @see java.io.InputStream#read(byte[],int,int)
         */
        @Override
        public int read(byte[] buf, int offset, int byteCount) throws IOException {
            BlockGuard.getThreadPolicy().onNetwork();
            synchronized (readLock) {
                checkOpen();
                Arrays.checkOffsetAndCount(buf.length, offset, byteCount);
                if (byteCount == 0) {
                    return 0;
                }
                return NativeCrypto.SSL_read(sslNativePointer, socket.getFileDescriptor$(),
                        OpenSSLSocketImpl.this, buf, offset, byteCount, getSoTimeout());
            }
        }
    }

    /**
     * This inner class provides output data stream functionality
     * for the OpenSSL native implementation. It is used to
     * write data according to the encryption parameters given in SSL context.
     */
    private class SSLOutputStream extends OutputStream {
        SSLOutputStream() throws IOException {
            /*
             * Note: When startHandshake() throws an exception, no
             * SSLOutputStream object will be created.
             */
            OpenSSLSocketImpl.this.startHandshake();
        }

        /**
         * Method acts as described in spec for superclass.
         * @see java.io.OutputStream#write(int)
         */
        @Override
        public void write(int oneByte) throws IOException {
            Streams.writeSingleByte(this, oneByte);
        }

        /**
         * Method acts as described in spec for superclass.
         * @see java.io.OutputStream#write(byte[],int,int)
         */
        @Override
        public void write(byte[] buf, int offset, int byteCount) throws IOException {
            BlockGuard.getThreadPolicy().onNetwork();
            synchronized (writeLock) {
                checkOpen();
                Arrays.checkOffsetAndCount(buf.length, offset, byteCount);
                if (byteCount == 0) {
                    return;
                }
                NativeCrypto.SSL_write(sslNativePointer, socket.getFileDescriptor$(),
                        OpenSSLSocketImpl.this, buf, offset, byteCount, writeTimeoutMilliseconds);
            }
        }
    }


    @Override public SSLSession getSession() {
        if (sslSession == null) {
            try {
                startHandshake();
            } catch (IOException e) {
                // return an invalid session with
                // invalid cipher suite of "SSL_NULL_WITH_NULL_NULL"
                return SSLSessionImpl.getNullSession();
            }
        }
        return sslSession;
    }

    @Override public void addHandshakeCompletedListener(
            HandshakeCompletedListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Provided listener is null");
        }
        if (listeners == null) {
            listeners = new ArrayList<HandshakeCompletedListener>();
        }
        listeners.add(listener);
    }

    @Override public void removeHandshakeCompletedListener(
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

    @Override public boolean getEnableSessionCreation() {
        return sslParameters.getEnableSessionCreation();
    }

    @Override public void setEnableSessionCreation(boolean flag) {
        sslParameters.setEnableSessionCreation(flag);
    }

    @Override public String[] getSupportedCipherSuites() {
        return NativeCrypto.getSupportedCipherSuites();
    }

    @Override public String[] getEnabledCipherSuites() {
        return enabledCipherSuites.clone();
    }

    @Override public void setEnabledCipherSuites(String[] suites) {
        enabledCipherSuites = NativeCrypto.checkEnabledCipherSuites(suites);
    }

    @Override public String[] getSupportedProtocols() {
        return NativeCrypto.getSupportedProtocols();
    }

    @Override public String[] getEnabledProtocols() {
        return enabledProtocols.clone();
    }

    @Override public void setEnabledProtocols(String[] protocols) {
        enabledProtocols = NativeCrypto.checkEnabledProtocols(protocols);
    }

    /**
     * This method enables session ticket support.
     *
     * @param useSessionTickets True to enable session tickets
     */
    public void setUseSessionTickets(boolean useSessionTickets) {
        this.useSessionTickets = useSessionTickets;
    }

    /**
     * This method enables Server Name Indication
     *
     * @param hostname the desired SNI hostname, or null to disable
     */
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    /**
     * Enables/disables TLS Channel ID for this server socket.
     *
     * <p>This method needs to be invoked before the handshake starts.
     *
     * @throws IllegalStateException if this is a client socket or if the handshake has already
     *         started.

     */
    public void setChannelIdEnabled(boolean enabled) {
        if (getUseClientMode()) {
            throw new IllegalStateException("Client mode");
        }
        if (handshakeStarted) {
            throw new IllegalStateException(
                    "Could not enable/disable Channel ID after the initial handshake has"
                    + " begun.");
        }
        this.channelIdEnabled = enabled;
    }

    /**
     * Gets the TLS Channel ID for this server socket. Channel ID is only available once the
     * handshake completes.
     *
     * @return channel ID or {@code null} if not available.
     *
     * @throws IllegalStateException if this is a client socket or if the handshake has not yet
     *         completed.
     * @throws SSLException if channel ID is available but could not be obtained.
     */
    public byte[] getChannelId() throws SSLException {
        if (getUseClientMode()) {
            throw new IllegalStateException("Client mode");
        }
        if (!handshakeCompleted) {
            throw new IllegalStateException(
                    "Channel ID is only available after handshake completes");
        }
        return NativeCrypto.SSL_get_tls_channel_id(sslNativePointer);
    }

    /**
     * Sets the {@link PrivateKey} to be used for TLS Channel ID by this client socket.
     *
     * <p>This method needs to be invoked before the handshake starts.
     *
     * @param privateKey private key (enables TLS Channel ID) or {@code null} for no key (disables
     *        TLS Channel ID). The private key must be an Elliptic Curve (EC) key based on the NIST
     *        P-256 curve (aka SECG secp256r1 or ANSI X9.62 prime256v1).
     *
     * @throws IllegalStateException if this is a server socket or if the handshake has already
     *         started.
     */
    public void setChannelIdPrivateKey(PrivateKey privateKey) {
        if (!getUseClientMode()) {
            throw new IllegalStateException("Server mode");
        }
        if (handshakeStarted) {
            throw new IllegalStateException(
                    "Could not change Channel ID private key after the initial handshake has"
                    + " begun.");
        }
        if (privateKey == null) {
            this.channelIdEnabled = false;
            this.channelIdPrivateKey = null;
        } else {
            this.channelIdEnabled = true;
            try {
                this.channelIdPrivateKey = OpenSSLKey.fromPrivateKey(privateKey);
            } catch (InvalidKeyException e) {
                // Will have error in startHandshake
            }
        }
    }

    @Override public boolean getUseClientMode() {
        return sslParameters.getUseClientMode();
    }

    @Override public void setUseClientMode(boolean mode) {
        if (handshakeStarted) {
            throw new IllegalArgumentException(
                    "Could not change the mode after the initial handshake has begun.");
        }
        sslParameters.setUseClientMode(mode);
    }

    @Override public boolean getWantClientAuth() {
        return sslParameters.getWantClientAuth();
    }

    @Override public boolean getNeedClientAuth() {
        return sslParameters.getNeedClientAuth();
    }

    @Override public void setNeedClientAuth(boolean need) {
        sslParameters.setNeedClientAuth(need);
    }

    @Override public void setWantClientAuth(boolean want) {
        sslParameters.setWantClientAuth(want);
    }

    @Override public void sendUrgentData(int data) throws IOException {
        throw new SocketException("Method sendUrgentData() is not supported.");
    }

    @Override public void setOOBInline(boolean on) throws SocketException {
        throw new SocketException("Methods sendUrgentData, setOOBInline are not supported.");
    }

    @Override public void setSoTimeout(int readTimeoutMilliseconds) throws SocketException {
        super.setSoTimeout(readTimeoutMilliseconds);
        this.readTimeoutMilliseconds = readTimeoutMilliseconds;
    }

    @Override public int getSoTimeout() throws SocketException {
        return readTimeoutMilliseconds;
    }

    /**
     * Note write timeouts are not part of the javax.net.ssl.SSLSocket API
     */
    public void setSoWriteTimeout(int writeTimeoutMilliseconds) throws SocketException {
        this.writeTimeoutMilliseconds = writeTimeoutMilliseconds;

        StructTimeval tv = StructTimeval.fromMillis(writeTimeoutMilliseconds);
        try {
            Libcore.os.setsockoptTimeval(getFileDescriptor$(), SOL_SOCKET, SO_SNDTIMEO, tv);
        } catch (ErrnoException errnoException) {
            throw errnoException.rethrowAsSocketException();
        }
    }

    /**
     * Note write timeouts are not part of the javax.net.ssl.SSLSocket API
     */
    public int getSoWriteTimeout() throws SocketException {
        return writeTimeoutMilliseconds;
    }

    /**
     * Set the handshake timeout on this socket.  This timeout is specified in
     * milliseconds and will be used only during the handshake process.
     */
    public void setHandshakeTimeout(int handshakeTimeoutMilliseconds) throws SocketException {
        this.handshakeTimeoutMilliseconds = handshakeTimeoutMilliseconds;
    }

    @Override public void close() throws IOException {
        // TODO: Close SSL sockets using a background thread so they close gracefully.

        synchronized (handshakeLock) {
            if (!handshakeStarted) {
                // prevent further attempts to start handshake
                handshakeStarted = true;

                synchronized (this) {
                    free();

                    if (socket != this) {
                        if (autoClose && !socket.isClosed()) socket.close();
                    } else {
                        if (!super.isClosed()) super.close();
                    }
                }

                return;
            }
        }

        synchronized (this) {

            // Interrupt any outstanding reads or writes before taking the writeLock and readLock
            NativeCrypto.SSL_interrupt(sslNativePointer);

            synchronized (writeLock) {
                synchronized (readLock) {
                    // Shut down the SSL connection, per se.
                    try {
                        if (handshakeStarted) {
                            BlockGuard.getThreadPolicy().onNetwork();
                            NativeCrypto.SSL_shutdown(sslNativePointer, socket.getFileDescriptor$(),
                                    this);
                        }
                    } catch (IOException ignored) {
                        /*
                         * Note that although close() can throw
                         * IOException, the RI does not throw if there
                         * is problem sending a "close notify" which
                         * can happen if the underlying socket is closed.
                         */
                    } finally {
                        /*
                         * Even if the above call failed, it is still safe to free
                         * the native structs, and we need to do so lest we leak
                         * memory.
                         */
                        free();

                        if (socket != this) {
                            if (autoClose && !socket.isClosed()) {
                                socket.close();
                            }
                        } else {
                            if (!super.isClosed()) {
                                super.close();
                            }
                        }
                    }
                }
            }
        }
    }

    private void free() {
        if (sslNativePointer == 0) {
            return;
        }
        NativeCrypto.SSL_free(sslNativePointer);
        sslNativePointer = 0;
        guard.close();
    }

    @Override protected void finalize() throws Throwable {
        try {
            /*
             * Just worry about our own state. Notably we do not try and
             * close anything. The SocketImpl, either our own
             * PlainSocketImpl, or the Socket we are wrapping, will do
             * that. This might mean we do not properly SSL_shutdown, but
             * if you want to do that, properly close the socket yourself.
             *
             * The reason why we don't try to SSL_shutdown, is that there
             * can be a race between finalizers where the PlainSocketImpl
             * finalizer runs first and closes the socket. However, in the
             * meanwhile, the underlying file descriptor could be reused
             * for another purpose. If we call SSL_shutdown, the
             * underlying socket BIOs still have the old file descriptor
             * and will write the close notify to some unsuspecting
             * reader.
             */
            if (guard != null) {
                guard.warnIfOpen();
            }
            free();
        } finally {
            super.finalize();
        }
    }

    @Override
    public FileDescriptor getFileDescriptor$() {
        if (socket == this) {
            return super.getFileDescriptor$();
        } else {
            return socket.getFileDescriptor$();
        }
    }

    /**
     * Returns the protocol agreed upon by client and server, or null if no
     * protocol was agreed upon.
     */
    public byte[] getNpnSelectedProtocol() {
        return NativeCrypto.SSL_get_npn_negotiated_protocol(sslNativePointer);
    }

    /**
     * Returns the protocol agreed upon by client and server, or {@code null} if
     * no protocol was agreed upon.
     */
    public byte[] getAlpnSelectedProtocol() {
        return NativeCrypto.SSL_get0_alpn_selected(sslNativePointer);
    }

    /**
     * Sets the list of protocols this peer is interested in. If null no
     * protocols will be used.
     *
     * @param npnProtocols a non-empty array of protocol names. From
     *     SSL_select_next_proto, "vector of 8-bit, length prefixed byte
     *     strings. The length byte itself is not included in the length. A byte
     *     string of length 0 is invalid. No byte string may be truncated.".
     */
    public void setNpnProtocols(byte[] npnProtocols) {
        if (npnProtocols != null && npnProtocols.length == 0) {
            throw new IllegalArgumentException("npnProtocols.length == 0");
        }
        this.npnProtocols = npnProtocols;
    }

    /**
     * Sets the list of protocols this peer is interested in. If the list is
     * {@code null}, no protocols will be used.
     *
     * @param alpnProtocols a non-empty array of protocol names. From
     *            SSL_select_next_proto, "vector of 8-bit, length prefixed byte
     *            strings. The length byte itself is not included in the length.
     *            A byte string of length 0 is invalid. No byte string may be
     *            truncated.".
     */
    public void setAlpnProtocols(byte[] alpnProtocols) {
        if (alpnProtocols != null && alpnProtocols.length == 0) {
            throw new IllegalArgumentException("alpnProtocols.length == 0");
        }
        this.alpnProtocols = alpnProtocols;
    }
}
