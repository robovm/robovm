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

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Arrays;
import java.util.Vector;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;

/**
 * Base class for ClientHandshakeImpl and ServerHandshakeImpl classes.
 * @see <a href="http://www.ietf.org/rfc/rfc2246.txt">TLS 1.0 spec., 7.4.
 * Handshake protocol</a>
 *
 */
public abstract class HandshakeProtocol {

    /**
     * Handshake status NEED_UNWRAP - HandshakeProtocol needs to receive data
     */
    public static final int NEED_UNWRAP = 1;

    /**
     * Handshake status NOT_HANDSHAKING - is not currently handshaking
     */
    public static final int NOT_HANDSHAKING = 2;

    /**
     * Handshake status FINISHED - HandshakeProtocol has just finished
     */
    public static final int FINISHED = 3;

    /**
     * Handshake status NEED_TASK - HandshakeProtocol needs the results of delegated task
     */
    public static final int NEED_TASK = 4;

    /**
     * Current handshake status
     */
    protected int status = NOT_HANDSHAKING;

    /**
     * IO stream for income/outcome handshake data
     */
    protected HandshakeIODataStream io_stream = new HandshakeIODataStream();

    /**
     * SSL Record Protocol implementation.
     */
    protected SSLRecordProtocol recordProtocol;

    /**
     * SSLParametersImpl suplied by SSLSocket or SSLEngine
     */
    protected SSLParametersImpl parameters;

    /**
     * Delegated tasks for this handshake implementation
     */
    protected Vector<DelegatedTask> delegatedTasks = new Vector<DelegatedTask>();

    /**
     * Indicates non-blocking handshake
     */
    protected boolean nonBlocking;

    /**
     * Pending session
     */
    protected SSLSessionImpl session;

    /**
     * Sent and received handshake messages
     */
    protected ClientHello clientHello;
    protected ServerHello serverHello;
    protected CertificateMessage serverCert;
    protected ServerKeyExchange serverKeyExchange;
    protected CertificateRequest certificateRequest;
    protected ServerHelloDone serverHelloDone;
    protected CertificateMessage clientCert;
    protected ClientKeyExchange clientKeyExchange;
    protected CertificateVerify certificateVerify;
    protected Finished clientFinished;
    protected Finished serverFinished;

    /**
     * Indicates that change cipher spec message has been received
     */
    protected boolean changeCipherSpecReceived = false;

    /**
     * Indicates previous session resuming
     */
    protected boolean isResuming = false;

    /**
     *  Premaster secret
     */
    protected byte[] preMasterSecret;

    /**
     * Exception occured in delegated task
     */
    protected Exception delegatedTaskErr;

    // reference verify_data used to verify finished message
    private byte[] verify_data = new byte[12];

    // Encoding of "master secret" string: "master secret".getBytes()
    private byte[] master_secret_bytes =
            {109, 97, 115, 116, 101, 114, 32, 115, 101, 99, 114, 101, 116 };

    // indicates whether protocol needs to send change cipher spec message
    private boolean needSendCCSpec = false;

    // indicates whether protocol needs to send change cipher spec message
    protected boolean needSendHelloRequest = false;

    /**
     * SSLEngine owning this HandshakeProtocol
     */
    public SSLEngineImpl engineOwner;

    /**
     * SSLSocket owning this HandshakeProtocol
     */
    public SSLSocketImpl socketOwner;

    /**
     * Creates HandshakeProtocol instance
     * @param owner
     */
    protected HandshakeProtocol(Object owner) {
        if (owner instanceof SSLEngineImpl) {
            engineOwner = (SSLEngineImpl) owner;
            nonBlocking = true;
            this.parameters = engineOwner.sslParameters;
        }
        else if (owner instanceof SSLSocketImpl) {
            socketOwner = (SSLSocketImpl) owner;
            nonBlocking = false;
            this.parameters = socketOwner.sslParameters;
        }
    }

    /**
     * Sets SSL Record Protocol
     * @param recordProtocol
     */
    public void setRecordProtocol(SSLRecordProtocol recordProtocol) {
        this.recordProtocol = recordProtocol;
    }

    /**
     * Start session negotiation
     * @param session
     */
    public abstract void start();

    /**
     * Stops the current session renegotiation process.
     * Such functionality is needed when it is session renegotiation
     * process and no_renegotiation alert message is received
     * from another peer.
     * @param session
     */
    protected void stop() {
        clearMessages();
        status = NOT_HANDSHAKING;
    }

    /**
     * Returns handshake status
     * @return
     */
    public SSLEngineResult.HandshakeStatus getStatus() {
        if (io_stream.hasData() || needSendCCSpec ||
                needSendHelloRequest || delegatedTaskErr != null) {
            return SSLEngineResult.HandshakeStatus.NEED_WRAP;
        }
        if (!delegatedTasks.isEmpty()) {
            return SSLEngineResult.HandshakeStatus.NEED_TASK;
        }

        switch (status) {
        case HandshakeProtocol.NEED_UNWRAP:
            return SSLEngineResult.HandshakeStatus.NEED_UNWRAP;
        case HandshakeProtocol.FINISHED:
            status = NOT_HANDSHAKING;
            clearMessages();
            return SSLEngineResult.HandshakeStatus.FINISHED;
        default: // HandshakeProtocol.NOT_HANDSHAKING:
            return SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
        }
    }

    /**
     * Returns pending session
     * @return session
     */
    public SSLSessionImpl getSession() {
        return session;
    }

    protected void sendChangeCipherSpec() {
        needSendCCSpec = true;
    }

    protected void sendHelloRequest() {
        needSendHelloRequest = true;
    }

    /**
     * Proceses inbound ChangeCipherSpec message
     */
    abstract void receiveChangeCipherSpec();

    /**
     * Creates and sends finished message
     */
    abstract void makeFinished();

    /**
     * Proceses inbound handshake messages
     * @param bytes
     */
    public abstract void unwrap(byte[] bytes);

    /**
     * Processes SSLv2 Hello message
     * @param bytes
     */
    public abstract void unwrapSSLv2(byte[] bytes);

    /**
     * Proceses outbound handshake messages
     * @return
     */
    public byte[] wrap() {
        if (delegatedTaskErr != null) {
            // process error occured in delegated task
            Exception e = delegatedTaskErr;
            delegatedTaskErr = null;
            fatalAlert(AlertProtocol.HANDSHAKE_FAILURE,
                    "Error occured in delegated task:" + e.getMessage(), e);
        }
        if (io_stream.hasData()) {
            return recordProtocol.wrap(ContentType.HANDSHAKE, io_stream);
        } else if (needSendCCSpec) {
            makeFinished();
            needSendCCSpec = false;
            return recordProtocol.getChangeCipherSpecMesage(getSession());
        } else if (needSendHelloRequest) {
            needSendHelloRequest = false;
            return recordProtocol.wrap(ContentType.HANDSHAKE,
                    // hello request message
                    // (see TLS v 1 specification:
                    // http://www.ietf.org/rfc/rfc2246.txt)
                    new byte[] {0, 0, 0, 0}, 0, 4);
        } else {
            return null; // nothing to send;
        }
    }

    /**
     * Sends fatal alert, breaks execution
     *
     * @param description
     */
    protected void sendWarningAlert(byte description) {
        recordProtocol.alert(AlertProtocol.WARNING, description);
    }

    /**
     * Sends fatal alert, breaks execution
     *
     * @param description
     * @param reason
     */
    protected void fatalAlert(byte description, String reason) {
        throw new AlertException(description, new SSLHandshakeException(reason));
    }

    /**
     * Sends fatal alert, breaks execution
     *
     * @param description
     * @param reason
     * @param cause
     */
    protected void fatalAlert(byte description, String reason, Exception cause) {
        throw new AlertException(description, new SSLException(reason, cause));
    }

    /**
     * Sends fatal alert, breaks execution
     *
     * @param description
     * @param cause
     */
    protected void fatalAlert(byte description, SSLException cause) {
        throw new AlertException(description, cause);
    }

    /**
     * Computers reference TLS verify_data that is used to verify finished message
     * @see <a href="http://www.ietf.org/rfc/rfc2246.txt">TLS spec. 7.4.9. Finished</a>
     * @param label
     */
    protected void computerReferenceVerifyDataTLS(String label) {
        computerVerifyDataTLS(label, verify_data);
    }

    /**
     * Computer TLS verify_data
     * @see <a href="http://www.ietf.org/rfc/rfc2246.txt">TLS spec. 7.4.9. Finished</a>
     * @param label
     * @param buf
     */
    protected void computerVerifyDataTLS(String label, byte[] buf) {
        byte[] md5_digest = io_stream.getDigestMD5();
        byte[] sha_digest = io_stream.getDigestSHA();

        byte[] digest = new byte[md5_digest.length + sha_digest.length];
        System.arraycopy(md5_digest, 0, digest, 0, md5_digest.length);
        System.arraycopy(sha_digest, 0, digest, md5_digest.length,
                sha_digest.length);
        try {
            PRF.computePRF(buf, session.master_secret,
                    label.getBytes(), digest);
        } catch (GeneralSecurityException e) {
            fatalAlert(AlertProtocol.INTERNAL_ERROR, "PRF error", e);
        }
    }

    /**
     * Computer reference SSLv3 verify_data that is used to verify finished message
     * @see "SSLv3 spec. 7.6.9. Finished"
     * @param label
     */
    protected void computerReferenceVerifyDataSSLv3(byte[] sender) {
        verify_data = new byte[36];
        computerVerifyDataSSLv3(sender, verify_data);
    }

    /**
     * Computer SSLv3 verify_data
     * @see "SSLv3 spec. 7.6.9. Finished"
     * @param label
     * @param buf
     */
    protected void computerVerifyDataSSLv3(byte[] sender, byte[] buf) {
        MessageDigest md5;
        MessageDigest sha;
        try {
            md5 = MessageDigest.getInstance("MD5");
            sha = MessageDigest.getInstance("SHA-1");
        } catch (Exception e) {
            fatalAlert(AlertProtocol.INTERNAL_ERROR,
                       "Could not initialize the Digest Algorithms.",
                       e);
            return;
        }
        try {
            byte[] handshake_messages = io_stream.getMessages();
            md5.update(handshake_messages);
            md5.update(sender);
            md5.update(session.master_secret);
            byte[] b = md5.digest(SSLv3Constants.MD5pad1);
            md5.update(session.master_secret);
            md5.update(SSLv3Constants.MD5pad2);
            System.arraycopy(md5.digest(b), 0, buf, 0, 16);

            sha.update(handshake_messages);
            sha.update(sender);
            sha.update(session.master_secret);
            b = sha.digest(SSLv3Constants.SHApad1);
            sha.update(session.master_secret);
            sha.update(SSLv3Constants.SHApad2);
            System.arraycopy(sha.digest(b), 0, buf, 16, 20);
        } catch (Exception e) {
            fatalAlert(AlertProtocol.INTERNAL_ERROR, "INTERNAL ERROR", e);

        }
    }

    /**
     * Verifies finished data
     *
     * @param data
     * @param isServer
     */
    protected void verifyFinished(byte[] data) {
        if (!Arrays.equals(verify_data, data)) {
            fatalAlert(AlertProtocol.HANDSHAKE_FAILURE, "Incorrect FINISED");
        }
    }

    /**
     * Sends fatal alert "UNEXPECTED MESSAGE"
     *
     */
    protected void unexpectedMessage() {
        fatalAlert(AlertProtocol.UNEXPECTED_MESSAGE, "UNEXPECTED MESSAGE");
    }

    /**
     * Writes message to HandshakeIODataStream
     *
     * @param message
     */
    public void send(Message message) {
        io_stream.writeUint8(message.getType());
        io_stream.writeUint24(message.length());
        message.send(io_stream);
    }

    /**
     * Computers master secret
     *
     */
    public void computerMasterSecret() {
        byte[] seed = new byte[64];
        System.arraycopy(clientHello.getRandom(), 0, seed, 0, 32);
        System.arraycopy(serverHello.getRandom(), 0, seed, 32, 32);
        session.master_secret = new byte[48];
        if (serverHello.server_version[1] == 1) { // TLSv1
            try {
                PRF.computePRF(session.master_secret, preMasterSecret,
                        master_secret_bytes, seed);
            } catch (GeneralSecurityException e) {
                fatalAlert(AlertProtocol.INTERNAL_ERROR, "PRF error", e);
            }
        } else { // SSL3.0
            PRF.computePRF_SSLv3(session.master_secret, preMasterSecret, seed);
        }

        //delete preMasterSecret from memory
        Arrays.fill(preMasterSecret, (byte)0);
        preMasterSecret = null;
    }

    /**
     * Returns a delegated task.
     * @return Delegated task or null
     */
    public Runnable getTask() {
        if (delegatedTasks.isEmpty()) {
            return null;
        }
        return delegatedTasks.remove(0);
    }

    /**
     * Clears previously sent and received handshake messages
     */
    protected void clearMessages() {
        io_stream.clearBuffer();
        clientHello = null;
        serverHello = null;
        serverCert = null;
        serverKeyExchange = null;
        certificateRequest = null;
        serverHelloDone = null;
        clientCert = null;
        clientKeyExchange = null;
        certificateVerify = null;
        clientFinished = null;
        serverFinished = null;
    }

    /**
     * Returns RSA key length
     * @param pk
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    protected static int getRSAKeyLength(PublicKey pk)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        BigInteger mod;
        if (pk instanceof RSAKey) {
            mod = ((RSAKey) pk).getModulus();
        } else {
            KeyFactory kf = KeyFactory.getInstance("RSA");
            mod = kf.getKeySpec(pk, RSAPublicKeySpec.class)
                    .getModulus();
        }
        return mod.bitLength();
    }

    /**
     * Shuts down the protocol. It will be impossible to use the instance
     * after calling this method.
     */
    protected void shutdown() {
        clearMessages();
        session = null;
        preMasterSecret = null;
        delegatedTasks.clear();
    }
}
