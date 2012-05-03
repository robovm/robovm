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
import java.security.AccessController;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PrivilegedExceptionAction;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.interfaces.DHKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.DHPublicKeySpec;
import javax.net.ssl.X509ExtendedKeyManager;
import javax.net.ssl.X509KeyManager;
import javax.security.auth.x500.X500Principal;

/**
 * Client side handshake protocol implementation.
 * Handshake protocol operates on top of the Record Protocol.
 * It is responsible for session negotiating.
 *
 * The implementation processes inbound server handshake messages,
 * creates and sends respond messages. Outbound messages are supplied
 * to Record Protocol. Detected errors are reported to the Alert protocol.
 *
 * @see <a href="http://www.ietf.org/rfc/rfc2246.txt">TLS 1.0 spec., 7. The
 * TLS Handshake Protocol</a>
 *
 */
public class ClientHandshakeImpl extends HandshakeProtocol {

    /**
     * Creates Client Handshake Implementation
     *
     * @param owner
     */
    ClientHandshakeImpl(Object owner) {
        super(owner);
    }

    /**
     * Starts handshake
     *
     */
    @Override
    public void start() {
        if (session == null) { // initial handshake
            session = findSessionToResume();
        } else { // start session renegotiation
            if (clientHello != null && this.status != FINISHED) {
                // current negotiation has not completed
                return; // ignore
            }
            if (!session.isValid()) {
                session = null;
            }
        }
        if (session != null) {
            isResuming = true;
        } else if (parameters.getEnableSessionCreation()){
            isResuming = false;
            session = new SSLSessionImpl(parameters.getSecureRandom());
            if (engineOwner != null) {
                session.setPeer(engineOwner.getPeerHost(), engineOwner.getPeerPort());
            } else {
                session.setPeer(socketOwner.getInetAddress().getHostName(), socketOwner.getPort());
            }
            session.protocol = ProtocolVersion.getLatestVersion(parameters.getEnabledProtocols());
            recordProtocol.setVersion(session.protocol.version);
        } else {
            fatalAlert(AlertProtocol.HANDSHAKE_FAILURE, "SSL Session may not be created ");
        }
        startSession();
    }

    /**
     * Starts renegotiation on a new session
     *
     */
    private void renegotiateNewSession() {
        if (parameters.getEnableSessionCreation()){
            isResuming = false;
            session = new SSLSessionImpl(parameters.getSecureRandom());
            if (engineOwner != null) {
                session.setPeer(engineOwner.getPeerHost(), engineOwner.getPeerPort());
            } else {
                session.setPeer(socketOwner.getInetAddress().getHostName(), socketOwner.getPort());
            }
            session.protocol = ProtocolVersion.getLatestVersion(parameters.getEnabledProtocols());
            recordProtocol.setVersion(session.protocol.version);
            startSession();
        } else {
            status = NOT_HANDSHAKING;
            sendWarningAlert(AlertProtocol.NO_RENEGOTIATION);
        }
    }

    /*
     * Starts/resumes session
     */
    private void startSession() {
        CipherSuite[] cipher_suites;
        if (isResuming) {
            cipher_suites = new CipherSuite[] { session.cipherSuite };
        } else {
            cipher_suites = parameters.getEnabledCipherSuitesMember();
        }
        clientHello = new ClientHello(parameters.getSecureRandom(),
                session.protocol.version, session.id, cipher_suites);
        session.clientRandom = clientHello.random;
        send(clientHello);
        status = NEED_UNWRAP;
    }

    /**
     * Processes inbound handshake messages
     * @param bytes
     */
    @Override
    public void unwrap(byte[] bytes) {
        if (this.delegatedTaskErr != null) {
            Exception e = this.delegatedTaskErr;
            this.delegatedTaskErr = null;
            this.fatalAlert(AlertProtocol.HANDSHAKE_FAILURE, "Error in delegated task", e);
        }
        int handshakeType;
        io_stream.append(bytes);
        while (io_stream.available() > 0) {
            io_stream.mark();
            int length;
            try {
                handshakeType = io_stream.read();
                length = io_stream.readUint24();
                if (io_stream.available() < length) {
                    io_stream.reset();
                    return;
                }
                switch (handshakeType) {
                case 0: // HELLO_REQUEST
                    // we don't need to take this message into account
                    // during FINISH message verification, so remove it
                    io_stream.removeFromMarkedPosition();
                    if (clientHello != null
                            && (clientFinished == null || serverFinished == null)) {
                        //currently negotiating - ignore
                        break;
                    }
                    // renegotiate
                    if (session.isValid()) {
                        session = (SSLSessionImpl) session.clone();
                        isResuming = true;
                        startSession();
                    } else {
                        // if SSLSession is invalidated (e.g. timeout limit is
                        // exceeded) connection can't resume the session.
                        renegotiateNewSession();
                    }
                    break;
                case 2: // SERVER_HELLO
                    if (clientHello == null || serverHello != null) {
                        unexpectedMessage();
                        return;
                    }
                    serverHello = new ServerHello(io_stream, length);

                    //check protocol version
                    ProtocolVersion servProt = ProtocolVersion.getByVersion(serverHello.server_version);
                    String[] enabled = parameters.getEnabledProtocols();
                    find: {
                        for (int i = 0; i < enabled.length; i++) {
                            if (servProt.equals(ProtocolVersion.getByName(enabled[i]))) {
                                break find;
                            }
                        }
                        fatalAlert(AlertProtocol.HANDSHAKE_FAILURE,
                                   "Bad server hello protocol version");
                    }

                    // check compression method
                    if (serverHello.compression_method != 0) {
                        fatalAlert(AlertProtocol.HANDSHAKE_FAILURE,
                                   "Bad server hello compression method");
                    }

                    //check cipher_suite
                    CipherSuite[] enabledSuites = parameters.getEnabledCipherSuitesMember();
                    find: {
                        for (int i = 0; i < enabledSuites.length; i++) {
                            if (serverHello.cipher_suite.equals(enabledSuites[i])) {
                                break find;
                            }
                        }
                        fatalAlert(AlertProtocol.HANDSHAKE_FAILURE,
                                   "Bad server hello cipher suite");
                    }

                    if (isResuming) {
                        if (serverHello.session_id.length == 0) {
                            // server is not willing to establish the new connection
                            // using specified session
                            isResuming = false;
                        } else if (!Arrays.equals(serverHello.session_id, clientHello.session_id)) {
                            isResuming = false;
                        } else if (!session.protocol.equals(servProt)) {
                            fatalAlert(AlertProtocol.HANDSHAKE_FAILURE,
                                       "Bad server hello protocol version");
                        } else if (!session.cipherSuite.equals(serverHello.cipher_suite)) {
                            fatalAlert(AlertProtocol.HANDSHAKE_FAILURE,
                                       "Bad server hello cipher suite");
                        }
                        if (serverHello.server_version[1] == 1) {
                            computerReferenceVerifyDataTLS("server finished");
                        } else {
                            computerReferenceVerifyDataSSLv3(SSLv3Constants.server);
                        }
                    }
                    session.protocol = servProt;
                    recordProtocol.setVersion(session.protocol.version);
                    session.cipherSuite = serverHello.cipher_suite;
                    session.id = serverHello.session_id.clone();
                    session.serverRandom = serverHello.random;
                    break;
                case 11: // CERTIFICATE
                    if (serverHello == null || serverKeyExchange != null
                            || serverCert != null || isResuming) {
                        unexpectedMessage();
                        return;
                    }
                    serverCert = new CertificateMessage(io_stream, length);
                    break;
                case 12: // SERVER_KEY_EXCHANGE
                    if (serverHello == null || serverKeyExchange != null
                            || isResuming) {
                        unexpectedMessage();
                        return;
                    }
                    serverKeyExchange = new ServerKeyExchange(io_stream,
                            length, session.cipherSuite.keyExchange);
                    break;
                case 13: // CERTIFICATE_REQUEST
                    if (serverCert == null || certificateRequest != null
                            || session.cipherSuite.isAnonymous() || isResuming) {
                        unexpectedMessage();
                        return;
                    }
                    certificateRequest = new CertificateRequest(io_stream, length);
                    break;
                case 14: // SERVER_HELLO_DONE
                    if (serverHello == null || serverHelloDone != null || isResuming) {
                        unexpectedMessage();
                        return;
                    }
                    serverHelloDone = new ServerHelloDone(io_stream, length);
                    if (this.nonBlocking) {
                        delegatedTasks.add(new DelegatedTask(new Runnable() {
                            public void run() {
                                processServerHelloDone();
                            }
                        }, this));
                        return;
                    }
                    processServerHelloDone();
                    break;
                case 20: // FINISHED
                    if (!changeCipherSpecReceived) {
                        unexpectedMessage();
                        return;
                    }
                    serverFinished = new Finished(io_stream, length);
                    verifyFinished(serverFinished.getData());
                    session.lastAccessedTime = System.currentTimeMillis();
                    session.context = parameters.getClientSessionContext();
                    parameters.getClientSessionContext().putSession(session);
                    if (isResuming) {
                        sendChangeCipherSpec();
                    } else {
                        session.lastAccessedTime = System.currentTimeMillis();
                        status = FINISHED;
                    }
                    // XXX there is no cleanup work
                    break;
                default:
                    unexpectedMessage();
                    return;
                }
            } catch (IOException e) {
                // io stream dosn't contain complete handshake message
                io_stream.reset();
                return;
            }
        }

    }

    /**
     * Processes SSLv2 Hello message.
     * SSLv2 client hello message message is an unexpected message
     * for client side of handshake protocol.
     * @ see TLS 1.0 spec., E.1. Version 2 client hello
     * @param bytes
     */
    @Override
    public void unwrapSSLv2(byte[] bytes) {
        unexpectedMessage();
    }

    /**
     * Creates and sends Finished message
     */
    @Override
    protected void makeFinished() {
        byte[] verify_data;
        if (serverHello.server_version[1] == 1) {
            verify_data = new byte[12];
            computerVerifyDataTLS("client finished", verify_data);
        } else {
            verify_data = new byte[36];
            computerVerifyDataSSLv3(SSLv3Constants.client, verify_data);
        }
        clientFinished = new Finished(verify_data);
        send(clientFinished);
        if (isResuming) {
            session.lastAccessedTime = System.currentTimeMillis();
            status = FINISHED;
        } else {
            if (serverHello.server_version[1] == 1) {
                computerReferenceVerifyDataTLS("server finished");
            } else {
                computerReferenceVerifyDataSSLv3(SSLv3Constants.server);
            }
            status = NEED_UNWRAP;
        }
    }

    /**
     * Processes ServerHelloDone: makes verification of the server messages; sends
     * client messages, computers masterSecret, sends ChangeCipherSpec
     */
    void processServerHelloDone() {
        PrivateKey clientKey = null;

        if (serverCert != null) {
            if (session.cipherSuite.isAnonymous()) {
                unexpectedMessage();
                return;
            }
            verifyServerCert();
        } else {
            if (!session.cipherSuite.isAnonymous()) {
                unexpectedMessage();
                return;
            }
        }

        // Client certificate
        if (certificateRequest != null) {
            X509Certificate[] certs = null;
            // obtain certificates from key manager
            String alias = null;
            String[] certTypes = certificateRequest.getTypesAsString();
            X500Principal[] issuers = certificateRequest.certificate_authorities;
            X509KeyManager km = parameters.getKeyManager();
            if (km instanceof X509ExtendedKeyManager) {
                X509ExtendedKeyManager ekm = (X509ExtendedKeyManager)km;
                if (this.socketOwner != null) {
                    alias = ekm.chooseClientAlias(certTypes, issuers, this.socketOwner);
                } else {
                    alias = ekm.chooseEngineClientAlias(certTypes, issuers, this.engineOwner);
                }
                if (alias != null) {
                    certs = ekm.getCertificateChain(alias);
                }
            } else {
                alias = km.chooseClientAlias(certTypes, issuers, this.socketOwner);
                if (alias != null) {
                    certs = km.getCertificateChain(alias);
                }
            }

            session.localCertificates = certs;
            clientCert = new CertificateMessage(certs);
            clientKey = km.getPrivateKey(alias);
            send(clientCert);
        }
        // Client key exchange
        if (session.cipherSuite.keyExchange == CipherSuite.KEY_EXCHANGE_RSA
                || session.cipherSuite.keyExchange == CipherSuite.KEY_EXCHANGE_RSA_EXPORT) {
            // RSA encrypted premaster secret message
            Cipher c;
            try {
                c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                if (serverKeyExchange != null) {
                    c.init(Cipher.ENCRYPT_MODE, serverKeyExchange
                            .getRSAPublicKey());
                } else {
                    c.init(Cipher.ENCRYPT_MODE, serverCert.certs[0]);
                }
            } catch (Exception e) {
                fatalAlert(AlertProtocol.INTERNAL_ERROR,
                        "Unexpected exception", e);
                return;
            }
            preMasterSecret = new byte[48];
            parameters.getSecureRandom().nextBytes(preMasterSecret);
            System.arraycopy(clientHello.client_version, 0, preMasterSecret, 0, 2);
            try {
                clientKeyExchange = new ClientKeyExchange(c
                        .doFinal(preMasterSecret),
                        serverHello.server_version[1] == 1);
            } catch (Exception e) {
                fatalAlert(AlertProtocol.INTERNAL_ERROR,
                        "Unexpected exception", e);
                return;
            }
        } else {
            try {
                KeyFactory kf = KeyFactory.getInstance("DH");
                KeyAgreement agreement = KeyAgreement.getInstance("DH");
                KeyPairGenerator kpg = KeyPairGenerator.getInstance("DH");
                PublicKey serverPublic;
                DHParameterSpec spec;
                if (serverKeyExchange != null) {
                    serverPublic = kf.generatePublic(new DHPublicKeySpec(
                            serverKeyExchange.par3, serverKeyExchange.par1,
                            serverKeyExchange.par2));
                    spec = new DHParameterSpec(serverKeyExchange.par1,
                            serverKeyExchange.par2);
                } else {
                    serverPublic = serverCert.certs[0].getPublicKey();
                    spec = ((DHPublicKey) serverPublic).getParams();
                }
                kpg.initialize(spec);

                KeyPair kp = kpg.generateKeyPair();
                Key key = kp.getPublic();
                if (clientCert != null
                        && serverCert != null
                        && (session.cipherSuite.keyExchange == CipherSuite.KEY_EXCHANGE_DHE_RSA
                                || session.cipherSuite.keyExchange == CipherSuite.KEY_EXCHANGE_DHE_DSS)) {
                    PublicKey client_pk = clientCert.certs[0].getPublicKey();
                    PublicKey server_pk = serverCert.certs[0].getPublicKey();
                    if (client_pk instanceof DHKey
                            && server_pk instanceof DHKey) {
                        if (((DHKey) client_pk).getParams().getG().equals(
                                ((DHKey) server_pk).getParams().getG())
                                && ((DHKey) client_pk).getParams().getP()
                                    .equals(((DHKey) server_pk).getParams().getG())) {
                            // client cert message DH public key parameters
                            // matched those specified by the
                            //   server in its certificate,
                            clientKeyExchange = new ClientKeyExchange(); // empty
                        }
                    }
                } else {
                    clientKeyExchange = new ClientKeyExchange(
                            ((DHPublicKey) key).getY());
                }
                key = kp.getPrivate();
                agreement.init(key);
                agreement.doPhase(serverPublic, true);
                preMasterSecret = agreement.generateSecret();
            } catch (Exception e) {
                fatalAlert(AlertProtocol.INTERNAL_ERROR,
                        "Unexpected exception", e);
                return;
            }
        }
        if (clientKeyExchange != null) {
            send(clientKeyExchange);
        }

        computerMasterSecret();

        // send certificate verify for all certificates except those containing
        // fixed DH parameters
        if (clientCert != null && !clientKeyExchange.isEmpty()) {
            // Certificate verify
            String authType = clientKey.getAlgorithm();
            DigitalSignature ds = new DigitalSignature(authType);
            ds.init(clientKey);

            if ("RSA".equals(authType)) {
                ds.setMD5(io_stream.getDigestMD5());
                ds.setSHA(io_stream.getDigestSHA());
            } else if ("DSA".equals(authType)) {
                ds.setSHA(io_stream.getDigestSHA());
            // The Signature should be empty in case of anonymous signature algorithm:
            // } else if ("DH".equals(authType)) {
            }
            certificateVerify = new CertificateVerify(ds.sign());
            send(certificateVerify);
        }

        sendChangeCipherSpec();
    }

    /*
     * Verifies certificate path
     */
    private void verifyServerCert() {
        String authType = session.cipherSuite.getAuthType(serverKeyExchange != null);
        if (authType == null) {
            return;
        }
        try {
            parameters.getTrustManager().checkServerTrusted(serverCert.certs, authType);
        } catch (CertificateException e) {
            fatalAlert(AlertProtocol.BAD_CERTIFICATE, "Not trusted server certificate", e);
            return;
        }
        session.peerCertificates = serverCert.certs;
    }

    /**
     * Processes ChangeCipherSpec message
     */
    @Override
    public void receiveChangeCipherSpec() {
        if (isResuming) {
            if (serverHello == null) {
                unexpectedMessage();
            }
        } else if (clientFinished == null) {
            unexpectedMessage();
        }
        changeCipherSpecReceived = true;
    }

    // Find session to resume in client session context
    private SSLSessionImpl findSessionToResume() {
        String host = null;
        int port = -1;
        if (engineOwner != null) {
            host = engineOwner.getPeerHost();
            port = engineOwner.getPeerPort();
        } else {
            host = socketOwner.getInetAddress().getHostName();
            port = socketOwner.getPort();
        }
        if (host == null || port == -1) {
            return null; // starts new session
        }

        ClientSessionContext context = parameters.getClientSessionContext();
        SSLSessionImpl session
                = (SSLSessionImpl) context.getSession(host, port);
        if (session != null) {
            session = (SSLSessionImpl) session.clone();
        }
        return session;
    }

}
