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
import java.math.BigInteger;
import java.security.AccessController;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PrivilegedExceptionAction;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.DHPublicKeySpec;
import javax.net.ssl.X509ExtendedKeyManager;
import javax.net.ssl.X509KeyManager;
import javax.net.ssl.X509TrustManager;

/**
 * Server side handshake protocol implementation.
 * Handshake protocol operates on top of the Record Protocol.
 * It responsible for negotiating a session.
 *
 * The implementation processes inbound client handshake messages,
 * creates and sends respond messages. Outbound messages are supplied
 * to Record Protocol. Detected errors are reported to the Alert protocol.
 *
 * @see <a href="http://www.ietf.org/rfc/rfc2246.txt">TLS 1.0 spec., 7.4.
 * Handshake protocol.</a>
 *
 */
public class ServerHandshakeImpl extends HandshakeProtocol {

    // private key used in key exchange
    private PrivateKey privKey;

    /**
     * Creates Server Handshake Implementation
     *
     * @param owner
     */
    public ServerHandshakeImpl(Object owner) {
        super(owner);
        status = NEED_UNWRAP;
    }

    /**
     * Start session negotiation
     */
    @Override
    public void start() {
        if (session == null) { // initial handshake
            status = NEED_UNWRAP;
            return; // wait client hello
        }
        if (clientHello != null && this.status != FINISHED) {
            // current negotiation has not completed
            return; // ignore
        }

        // renegotiation
        sendHelloRequest();
        status = NEED_UNWRAP;
    }

    /**
     * Proceses inbound handshake messages
     * @param bytes
     */
    @Override
    public void unwrap(byte[] bytes) {

        io_stream.append(bytes);
        while (io_stream.available() > 0) {
            int handshakeType;
            int length;
            io_stream.mark();
            try {
                handshakeType = io_stream.read();
                length = io_stream.readUint24();
                if (io_stream.available() < length) {
                    io_stream.reset();
                    return;
                }

                switch (handshakeType) {
                case 1: // CLIENT_HELLO
                    if (clientHello != null && this.status != FINISHED) {
                            // Client hello has been received during handshake
                            unexpectedMessage();
                            return;
                    }
                    // if protocol planed to send Hello Request message
                    // - cancel this demand.
                    needSendHelloRequest = false;
                    clientHello = new ClientHello(io_stream, length);
                    if (nonBlocking) {
                        delegatedTasks.add(new DelegatedTask(new Runnable() {
                            public void run() {
                                processClientHello();
                            }
                        }, this));
                        return;
                    }
                    processClientHello();
                    break;

                case 11: //    CLIENT CERTIFICATE
                    if (isResuming || certificateRequest == null
                            || serverHelloDone == null || clientCert != null) {
                        unexpectedMessage();
                        return;
                    }
                    clientCert = new CertificateMessage(io_stream, length);
                    if (clientCert.certs.length == 0) {
                        if (parameters.getNeedClientAuth()) {
                            fatalAlert(AlertProtocol.HANDSHAKE_FAILURE,
                                       "HANDSHAKE FAILURE: no client certificate received");
                        }
                    } else {
                        String authType = clientCert.getAuthType();
                        try {
                            parameters.getTrustManager().checkClientTrusted(
                                    clientCert.certs, authType);
                        } catch (CertificateException e) {
                            fatalAlert(AlertProtocol.BAD_CERTIFICATE,
                                       "Untrusted Client Certificate ", e);
                        }
                        session.peerCertificates = clientCert.certs;
                    }
                    break;

                case 15: // CERTIFICATE_VERIFY
                    if (isResuming
                            || clientKeyExchange == null
                            || clientCert == null
                            || clientKeyExchange.isEmpty() //client certificate
                                                           // contains fixed DH
                                                           // parameters
                            || certificateVerify != null
                            || changeCipherSpecReceived) {
                        unexpectedMessage();
                        return;
                    }
                    certificateVerify = new CertificateVerify(io_stream, length);

                    String authType = clientCert.getAuthType();
                    DigitalSignature ds = new DigitalSignature(authType);
                    ds.init(clientCert.certs[0]);
                    byte[] md5_hash = null;
                    byte[] sha_hash = null;

                    if ("RSA".equals(authType)) {
                        md5_hash = io_stream.getDigestMD5withoutLast();
                        sha_hash = io_stream.getDigestSHAwithoutLast();
                    } else if ("DSA".equals(authType)) {
                        sha_hash = io_stream.getDigestSHAwithoutLast();
                    // The Signature should be empty in case of anonymous signature algorithm:
                    // } else if ("DH".equals(authType)) {
                    }
                    ds.setMD5(md5_hash);
                    ds.setSHA(sha_hash);
                    if (!ds.verifySignature(certificateVerify.signedHash)) {
                        fatalAlert(AlertProtocol.DECRYPT_ERROR,
                                   "DECRYPT ERROR: CERTIFICATE_VERIFY incorrect signature");
                    }
                    break;
                case 16: // CLIENT_KEY_EXCHANGE
                    if (isResuming
                            || serverHelloDone == null
                            || clientKeyExchange != null
                            || (clientCert == null && parameters.getNeedClientAuth())) {
                        unexpectedMessage();
                        return;
                    }
                    if (session.cipherSuite.keyExchange == CipherSuite.KEY_EXCHANGE_RSA
                            || session.cipherSuite.keyExchange == CipherSuite.KEY_EXCHANGE_RSA_EXPORT) {
                        clientKeyExchange = new ClientKeyExchange(io_stream,
                                length, serverHello.server_version[1] == 1,
                                true);
                        Cipher c = null;
                        try {
                            c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                            c.init(Cipher.DECRYPT_MODE, privKey);
                            preMasterSecret = c.doFinal(clientKeyExchange.exchange_keys);
                            // check preMasterSecret:
                            if (preMasterSecret.length != 48
                                    || preMasterSecret[0] != clientHello.client_version[0]
                                    || preMasterSecret[1] != clientHello.client_version[1]) {
                                // incorrect preMasterSecret
                                // prevent an attack (see TLS 1.0 spec., 7.4.7.1.)
                                preMasterSecret = new byte[48];
                                parameters.getSecureRandom().nextBytes(
                                        preMasterSecret);
                            }
                        } catch (Exception e) {
                            fatalAlert(AlertProtocol.INTERNAL_ERROR,
                                       "INTERNAL ERROR", e);
                        }
                    } else { // diffie hellman key exchange
                        clientKeyExchange = new ClientKeyExchange(io_stream,
                                length, serverHello.server_version[1] == 1,
                                false);
                        if (clientKeyExchange.isEmpty()) {
                            // TODO check that client cert. DH params
                            // matched server cert. DH params

                            // client cert. contains fixed DH parameters
                            preMasterSecret = ((DHPublicKey) clientCert.certs[0].getPublicKey()).getY().toByteArray();
                        } else {
                            try {
                                KeyFactory kf = KeyFactory.getInstance("DH");
                                KeyAgreement agreement = KeyAgreement.getInstance("DH");
                                PublicKey clientPublic = kf.generatePublic(new DHPublicKeySpec(
                                                new BigInteger(
                                                        1,
                                                        clientKeyExchange.exchange_keys),
                                                serverKeyExchange.par1,
                                                serverKeyExchange.par2));
                                agreement.init(privKey);
                                agreement.doPhase(clientPublic, true);
                                preMasterSecret = agreement.generateSecret();
                            } catch (Exception e) {
                                fatalAlert(AlertProtocol.INTERNAL_ERROR,
                                           "INTERNAL ERROR", e);
                                return;
                            }
                        }
                    }

                    computerMasterSecret();
                    break;

                case 20: // FINISHED
                    if (!isResuming && !changeCipherSpecReceived) {
                        unexpectedMessage();
                        return;
                    }

                    clientFinished = new Finished(io_stream, length);
                    verifyFinished(clientFinished.getData());
                    session.context = parameters.getServerSessionContext();
                    parameters.getServerSessionContext().putSession(session);
                    if (!isResuming) {
                        sendChangeCipherSpec();
                    } else {
                        session.lastAccessedTime = System.currentTimeMillis();
                        status = FINISHED;
                    }
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
     * Processes SSLv2 Hello message
     * @ see TLS 1.0 spec., E.1. Version 2 client hello
     * @param bytes
     */
    @Override
    public void unwrapSSLv2(byte[] bytes) {
        io_stream.append(bytes);
        io_stream.mark();
        try {
            clientHello = new ClientHello(io_stream);
        } catch (IOException e) {
            io_stream.reset();
            return;
        }
        if (nonBlocking) {
            delegatedTasks.add(new DelegatedTask(new Runnable() {
                public void run() {
                    processClientHello();
                }
            }, this));
            return;
        }
        processClientHello();
    }

    /**
     *
     * Processes Client Hello message.
     * Server responds to client hello message with server hello
     * and (if necessary) server certificate, server key exchange,
     * certificate request, and server hello done messages.
     */
    void processClientHello() {
        CipherSuite cipher_suite;

        // check that clientHello contains CompressionMethod.null
        checkCompression: {
            for (int i = 0; i < clientHello.compression_methods.length; i++) {
                if (clientHello.compression_methods[i] == 0) {
                    break checkCompression;
                }
            }
            fatalAlert(AlertProtocol.HANDSHAKE_FAILURE,
                       "HANDSHAKE FAILURE. Incorrect client hello message");
        }

        if (!ProtocolVersion.isSupported(clientHello.client_version)) {
            fatalAlert(AlertProtocol.PROTOCOL_VERSION,
                       "PROTOCOL VERSION. Unsupported client version "
                       + clientHello.client_version[0]
                       + clientHello.client_version[1]);
        }

        isResuming = false;
        FIND: if (clientHello.session_id.length != 0) {
            // client wishes to reuse session

            SSLSessionImpl sessionToResume;
            boolean reuseCurrent = false;

            // reuse current session
            if (session != null
                    && Arrays.equals(session.id, clientHello.session_id)) {
                if (session.isValid()) {
                    isResuming = true;
                    break FIND;
                }
                reuseCurrent = true;
            }

            // find session in cash
            sessionToResume = findSessionToResume(clientHello.session_id);
            if (sessionToResume == null || !sessionToResume.isValid()) {
                if (!parameters.getEnableSessionCreation()) {
                    if (reuseCurrent) {
                        // we can continue current session
                        sendWarningAlert(AlertProtocol.NO_RENEGOTIATION);
                        status = NOT_HANDSHAKING;
                        clearMessages();
                        return;
                    }
                    // throw AlertException
                    fatalAlert(AlertProtocol.HANDSHAKE_FAILURE, "SSL Session may not be created");
                }
                session = null;
            } else {
                session = (SSLSessionImpl)sessionToResume.clone();
                isResuming = true;
            }
        }

        if (isResuming) {
            cipher_suite = session.cipherSuite;
            // clientHello.cipher_suites must include at least cipher_suite from the session
            checkCipherSuite: {
                for (int i = 0; i < clientHello.cipher_suites.length; i++) {
                    if (cipher_suite.equals(clientHello.cipher_suites[i])) {
                        break checkCipherSuite;
                    }
                }
                fatalAlert(AlertProtocol.HANDSHAKE_FAILURE,
                           "HANDSHAKE FAILURE. Incorrect client hello message");
            }
        } else {
            cipher_suite = selectSuite(clientHello.cipher_suites);
            if (cipher_suite == null) {
                fatalAlert(AlertProtocol.HANDSHAKE_FAILURE, "HANDSHAKE FAILURE. NO COMMON SUITE");
            }
            if (!parameters.getEnableSessionCreation()) {
                fatalAlert(AlertProtocol.HANDSHAKE_FAILURE,
                           "SSL Session may not be created");
            }
            session = new SSLSessionImpl(cipher_suite, parameters.getSecureRandom());
            if (engineOwner != null) {
                session.setPeer(engineOwner.getPeerHost(), engineOwner.getPeerPort());
            } else {
                session.setPeer(socketOwner.getInetAddress().getHostName(), socketOwner.getPort());
            }
        }

        recordProtocol.setVersion(clientHello.client_version);
        session.protocol = ProtocolVersion.getByVersion(clientHello.client_version);
        session.clientRandom = clientHello.random;

        // create server hello message
        serverHello = new ServerHello(parameters.getSecureRandom(),
                clientHello.client_version,
                session.getId(), cipher_suite, (byte) 0); //CompressionMethod.null
        session.serverRandom = serverHello.random;
        send(serverHello);
        if (isResuming) {
            sendChangeCipherSpec();
            return;
        }

        //    create and send server certificate message if needed
        if (!cipher_suite.isAnonymous()) { // need to send server certificate
            X509Certificate[] certs = null;
            String certType = cipher_suite.getServerKeyType();
            if (certType == null) {
                fatalAlert(AlertProtocol.HANDSHAKE_FAILURE, "NO CERT TYPE FOR " + cipher_suite.getName());
            }
            // obtain certificates from key manager
            String alias = null;
            X509KeyManager km = parameters.getKeyManager();
            if (km instanceof X509ExtendedKeyManager) {
                X509ExtendedKeyManager ekm = (X509ExtendedKeyManager)km;
                if (this.socketOwner != null) {
                    alias = ekm.chooseServerAlias(certType, null,
                            this.socketOwner);
                } else {
                    alias = ekm.chooseEngineServerAlias(certType, null,
                            this.engineOwner);
                }
                if (alias != null) {
                    certs = ekm.getCertificateChain(alias);
                }
            } else {
                alias = km.chooseServerAlias(certType, null, this.socketOwner);
                if (alias != null) {
                    certs = km.getCertificateChain(alias);
                }
            }

            if (certs == null) {
                fatalAlert(AlertProtocol.HANDSHAKE_FAILURE, "NO SERVER CERTIFICATE FOUND");
                return;
            }
            session.localCertificates = certs;
            serverCert = new CertificateMessage(certs);
            privKey = km.getPrivateKey(alias);
            send(serverCert);
        }

        // create and send server key exchange message if needed
        RSAPublicKey rsakey = null;
        DHPublicKeySpec dhkeySpec = null;
        byte[] hash = null;
        BigInteger p = null;
        BigInteger g = null;

        KeyPairGenerator kpg = null;

        try {
            if (cipher_suite.keyExchange == CipherSuite.KEY_EXCHANGE_RSA_EXPORT) {
                PublicKey pk = serverCert.certs[0].getPublicKey();
                if (getRSAKeyLength(pk) > 512) {
                    // key is longer than 512 bits
                    kpg = KeyPairGenerator.getInstance("RSA");
                    kpg.initialize(512);
                }
            } else if (cipher_suite.keyExchange == CipherSuite.KEY_EXCHANGE_DHE_DSS
                    || cipher_suite.keyExchange == CipherSuite.KEY_EXCHANGE_DHE_DSS_EXPORT
                    || cipher_suite.keyExchange == CipherSuite.KEY_EXCHANGE_DHE_RSA
                    || cipher_suite.keyExchange == CipherSuite.KEY_EXCHANGE_DHE_RSA_EXPORT
                    || cipher_suite.keyExchange == CipherSuite.KEY_EXCHANGE_DH_anon
                    || cipher_suite.keyExchange == CipherSuite.KEY_EXCHANGE_DH_anon_EXPORT) {
                kpg = KeyPairGenerator.getInstance("DH");
                p = new BigInteger(1, DHParameters.getPrime());
                g = new BigInteger("2");
                DHParameterSpec spec = new DHParameterSpec(p, g);
                kpg.initialize(spec);
            }
        } catch (Exception e) {
            fatalAlert(AlertProtocol.INTERNAL_ERROR, "INTERNAL ERROR", e);
        }

        if (kpg != null) {
            // need to send server key exchange message
            DigitalSignature ds = new DigitalSignature(cipher_suite.authType);
            KeyPair kp = null;
            try {
                kp = kpg.genKeyPair();
                if (cipher_suite.keyExchange == CipherSuite.KEY_EXCHANGE_RSA_EXPORT) {
                    rsakey = (RSAPublicKey) kp.getPublic();
                } else {
                    DHPublicKey dhkey = (DHPublicKey) kp.getPublic();
                    KeyFactory kf = KeyFactory.getInstance("DH");
                    dhkeySpec = kf.getKeySpec(dhkey, DHPublicKeySpec.class);
                }
                if (!cipher_suite.isAnonymous()) { // calculate signed_params

                    // init by private key which correspond to
                    // server certificate
                    ds.init(privKey);

                    // use emphemeral key for key exchange
                    privKey = kp.getPrivate();
                    ds.update(clientHello.getRandom());
                    ds.update(serverHello.getRandom());

                    byte[] tmp;
                    byte[] tmpLength = new byte[2];
//FIXME 1_byte==0x00
                    if (cipher_suite.keyExchange == CipherSuite.KEY_EXCHANGE_RSA_EXPORT) {
                        tmp = ServerKeyExchange.toUnsignedByteArray(rsakey.getModulus());
                        tmpLength[0] = (byte) ((tmp.length & 0xFF00) >>> 8);
                        tmpLength[1] = (byte) (tmp.length & 0xFF);
                        ds.update(tmpLength);
                        ds.update(tmp);
                        tmp = ServerKeyExchange.toUnsignedByteArray(rsakey.getPublicExponent());
                        tmpLength[0] = (byte) ((tmp.length & 0xFF00) >>> 8);
                        tmpLength[1] = (byte) (tmp.length & 0xFF);
                        ds.update(tmpLength);
                        ds.update(tmp);
                    } else {
                        tmp = ServerKeyExchange.toUnsignedByteArray(dhkeySpec.getP());
                        tmpLength[0] = (byte) ((tmp.length & 0xFF00) >>> 8);
                        tmpLength[1] = (byte) (tmp.length & 0xFF);
                        ds.update(tmpLength);
                        ds.update(tmp);
                        tmp = ServerKeyExchange.toUnsignedByteArray(dhkeySpec.getG());
                        tmpLength[0] = (byte) ((tmp.length & 0xFF00) >>> 8);
                        tmpLength[1] = (byte) (tmp.length & 0xFF);
                        ds.update(tmpLength);
                        ds.update(tmp);
                        tmp = ServerKeyExchange.toUnsignedByteArray(dhkeySpec.getY());
                        tmpLength[0] = (byte) ((tmp.length & 0xFF00) >>> 8);
                        tmpLength[1] = (byte) (tmp.length & 0xFF);
                        ds.update(tmpLength);
                        ds.update(tmp);
                    }
                    hash = ds.sign();
                } else {
                    privKey = kp.getPrivate(); // use emphemeral key for key exchange
                }
            } catch (Exception e) {
                fatalAlert(AlertProtocol.INTERNAL_ERROR, "INTERNAL ERROR", e);
            }

            if (cipher_suite.keyExchange == CipherSuite.KEY_EXCHANGE_RSA_EXPORT) {
                serverKeyExchange = new ServerKeyExchange(rsakey.getModulus(),
                        rsakey.getPublicExponent(), null, hash);
            } else {
                serverKeyExchange = new ServerKeyExchange(p,
                        g, dhkeySpec.getY(), hash);
            }
            send(serverKeyExchange);
        }

        // CERTIFICATE_REQUEST
        certRequest: if (parameters.getWantClientAuth()
                || parameters.getNeedClientAuth()) {
            X509Certificate[] accepted;
            try {
                X509TrustManager tm = parameters.getTrustManager();
                accepted = tm.getAcceptedIssuers();
            } catch (ClassCastException e) {
                // don't send certificateRequest
                break certRequest;
            }
            byte[] requestedClientCertTypes = { CipherSuite.TLS_CT_RSA_SIGN,
                                                CipherSuite.TLS_CT_DSS_SIGN };
            certificateRequest = new CertificateRequest(
                    requestedClientCertTypes, accepted);
            send(certificateRequest);
        }

        // SERVER_HELLO_DONE
        serverHelloDone = new ServerHelloDone();
        send(serverHelloDone);
        status = NEED_UNWRAP;
    }

    /**
     * Creates and sends finished message
     */
    @Override
    protected void makeFinished() {
        byte[] verify_data;
        boolean isTLS = (serverHello.server_version[1] == 1); // TLS 1.0 protocol
        if (isTLS) {
            verify_data = new byte[12];
            computerVerifyDataTLS("server finished", verify_data);
        } else { // SSL 3.0 protocol (http://wp.netscape.com/eng/ssl3)
            verify_data = new byte[36];
            computerVerifyDataSSLv3(SSLv3Constants.server, verify_data);
        }
        serverFinished = new Finished(verify_data);
        send(serverFinished);
        if (isResuming) {
            if (isTLS) {
                computerReferenceVerifyDataTLS("client finished");
            } else {
                computerReferenceVerifyDataSSLv3(SSLv3Constants.client);
            }
            status = NEED_UNWRAP;
        } else {
            session.lastAccessedTime = System.currentTimeMillis();
            status = FINISHED;
        }
    }

    // find sesssion in the session hash
    private SSLSessionImpl findSessionToResume(byte[] session_id) {
        return (SSLSessionImpl)parameters.getServerSessionContext().getSession(session_id);
    }

    // find appropriate cipher_suite in the client suites
    private CipherSuite selectSuite(CipherSuite[] clientSuites) {
        for (CipherSuite clientSuite : clientSuites) {
            if (!clientSuite.supported) {
                continue;
            }
            for (CipherSuite enabledCipherSuite : parameters.getEnabledCipherSuitesMember()) {
                if (clientSuite.equals(enabledCipherSuite)) {
                    return clientSuite;
                }
            }
        }
        return null;
    }

    /**
     * Processes inbound ChangeCipherSpec message
     */
    @Override
    public void receiveChangeCipherSpec() {
        if (isResuming) {
            if (serverFinished == null) {
                unexpectedMessage();
            } else {
                changeCipherSpecReceived = true;
            }
        } else {
            if ((parameters.getNeedClientAuth() && clientCert == null)
                    || clientKeyExchange == null
                    || (clientCert != null
                            && !clientKeyExchange.isEmpty()
                            && certificateVerify == null)) {
                unexpectedMessage();
            } else {
                changeCipherSpecReceived = true;
            }
            if (serverHello.server_version[1] == 1) {
                computerReferenceVerifyDataTLS("client finished");
            } else {
                computerReferenceVerifyDataSSLv3(SSLv3Constants.client);
            }
        }
    }

}
