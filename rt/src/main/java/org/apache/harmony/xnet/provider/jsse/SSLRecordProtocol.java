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
import javax.net.ssl.SSLProtocolException;

/**
 * This class performs functionality dedicated to SSL record layer.
 * It unpacks and routes income data to the appropriate
 * client protocol (handshake, alert, application data protocols)
 * and packages outcome data into SSL/TLS records.
 * Initially created object has null connection state and does not
 * perform any cryptography computations over the income/outcome data.
 * After handshake protocol agreed upon security parameters they are placed
 * into SSLSessionImpl object and available for record protocol as
 * pending session. The order of setting up of the pending session
 * as an active session differs for client and server modes.
 * So for client mode the parameters are provided by handshake protocol
 * during retrieving of change_cipher_spec message to be sent (by calling of
 * getChangeCipherSpecMesage method).
 * For server side mode record protocol retrieves the parameters from
 * handshake protocol after receiving of client's change_cipher_spec message.
 * After the pending session has been set up as a current session,
 * new connection state object is created and used for encryption/decryption
 * of the messages.
 * Among with base functionality this class provides the information about
 * constrains on the data length, and information about correspondence
 * of plain and encrypted data lengths.
 * For more information on TLS v1 see http://www.ietf.org/rfc/rfc2246.txt,
 * on SSL v3 see http://wp.netscape.com/eng/ssl3,
 * on SSL v2 see http://wp.netscape.com/eng/security/SSL_2.html.
 */
public class SSLRecordProtocol {

    /**
     * Maximum length of allowed plain data fragment
     * as specified by TLS specification.
     */
    protected static final int MAX_DATA_LENGTH = 16384; // 2^14
    /**
     * Maximum length of allowed compressed data fragment
     * as specified by TLS specification.
     */
    protected static final int MAX_COMPRESSED_DATA_LENGTH
                                    = MAX_DATA_LENGTH + 1024;
    /**
     * Maximum length of allowed ciphered data fragment
     * as specified by TLS specification.
     */
    protected static final int MAX_CIPHERED_DATA_LENGTH
                                    = MAX_COMPRESSED_DATA_LENGTH + 1024;
    /**
     * Maximum length of ssl record. It is counted as:
     * type(1) + version(2) + length(2) + MAX_CIPHERED_DATA_LENGTH
     */
    protected static final int MAX_SSL_PACKET_SIZE
                                    = MAX_CIPHERED_DATA_LENGTH + 5;
    // the SSL session used for connection
    private SSLSessionImpl session;
    // protocol version of the connection
    private byte[] version;
    // input stream of record protocol
    private SSLInputStream in;
    // handshake protocol object to which handshaking data will be transmitted
    private HandshakeProtocol handshakeProtocol;
    // alert protocol to indicate alerts occurred/received
    private AlertProtocol alertProtocol;
    // application data object to which application data will be transmitted
    private org.apache.harmony.xnet.provider.jsse.Appendable appData;
    // connection state holding object
    private ConnectionState
        activeReadState, activeWriteState, pendingConnectionState;

    // logger
    private Logger.Stream logger = Logger.getStream("record");

    // flag indicating if session object has been changed after
    // handshake phase (to distinguish session pending state)
    private boolean sessionWasChanged = false;

    // change cipher spec message content
    private static final byte[] change_cipher_spec_byte = new byte[] {1};

    /**
     * Creates an instance of record protocol and tunes
     * up the client protocols to use ut.
     * @param   handshakeProtocol:  HandshakeProtocol
     * @param   alertProtocol:  AlertProtocol
     * @param   in: SSLInputStream
     * @param   appData:    Appendable
     */
    protected SSLRecordProtocol(HandshakeProtocol handshakeProtocol,
            AlertProtocol alertProtocol,
            SSLInputStream in,
            Appendable appData) {
        this.handshakeProtocol = handshakeProtocol;
        this.handshakeProtocol.setRecordProtocol(this);
        this.alertProtocol = alertProtocol;
        this.alertProtocol.setRecordProtocol(this);
        this.in = in;
        this.appData = appData;
    }

    /**
     * Returns the session obtained during the handshake negotiation.
     * If the handshake process was not completed, method returns null.
     * @return the session in effect.
     */
    protected SSLSessionImpl getSession() {
        return session;
    }

    /**
     * Returns the minimum possible length of the SSL record.
     * @return
     */
    protected int getMinRecordSize() {
        return (activeReadState == null)
            ? 6 // type + version + length + 1 byte of data
            : 5 + activeReadState.getMinFragmentSize();
    }

    /**
     * Returns the record length for the specified incoming data length.
     * If actual resulting record length is greater than
     * MAX_CIPHERED_DATA_LENGTH, MAX_CIPHERED_DATA_LENGTH is returned.
     */
    protected int getRecordSize(int data_size) {
        if (activeWriteState == null) {
            return 5+data_size; // type + version + length + data_size
        } else {
            int res = 5 + activeWriteState.getFragmentSize(data_size);
            return (res > MAX_CIPHERED_DATA_LENGTH)
                ? MAX_CIPHERED_DATA_LENGTH // so the source data should be
                                           // split into several packets
                : res;
        }
    }

    /**
     * Returns the upper bound of length of data containing in the record with
     * specified length.
     * If the provided record_size is greater or equal to
     * MAX_CIPHERED_DATA_LENGTH the returned value will be
     * MAX_DATA_LENGTH
     * counted as for data with
     * MAX_CIPHERED_DATA_LENGTH length.
     */
    protected int getDataSize(int record_size) {
        record_size -= 5; // - (type + version + length + data_size)
        if (record_size > MAX_CIPHERED_DATA_LENGTH) {
            // the data of such size consists of the several packets
            return MAX_DATA_LENGTH;
        }
        if (activeReadState == null) {
            return record_size;
        }
        return activeReadState.getContentSize(record_size);
    }

    /**
     * Depending on the Connection State (Session) encrypts and compress
     * the provided data, and packs it into TLSCiphertext structure.
     * @param   content_type: int
     * @return  ssl packet created over the current connection state
     */
    protected byte[] wrap(byte content_type, DataStream dataStream) {
        byte[] fragment = dataStream.getData(MAX_DATA_LENGTH);
        return wrap(content_type, fragment, 0, fragment.length);
    }

    /**
     * Depending on the Connection State (Session) encrypts and compress
     * the provided data, and packs it into TLSCiphertext structure.
     * @param   content_type: int
     * @param   fragment: byte[]
     * @return  ssl packet created over the current connection state
     */
    protected byte[] wrap(byte content_type,
                       byte[] fragment, int offset, int len) {
        if (logger != null) {
            logger.println("SSLRecordProtocol.wrap: TLSPlaintext.fragment["
                    +len+"]:");
            logger.print(fragment, offset, len);
        }
        if (len > MAX_DATA_LENGTH) {
            throw new AlertException(
                AlertProtocol.INTERNAL_ERROR,
                new SSLProtocolException(
                    "The provided chunk of data is too big: " + len
                    + " > MAX_DATA_LENGTH == "+MAX_DATA_LENGTH));
        }
        byte[] ciphered_fragment = fragment;
        if (activeWriteState != null) {
            ciphered_fragment =
                activeWriteState.encrypt(content_type, fragment, offset, len);
            if (ciphered_fragment.length > MAX_CIPHERED_DATA_LENGTH) {
                throw new AlertException(
                    AlertProtocol.INTERNAL_ERROR,
                    new SSLProtocolException(
                        "The ciphered data increased more than on 1024 bytes"));
            }
            if (logger != null) {
                logger.println("SSLRecordProtocol.wrap: TLSCiphertext.fragment["
                        +ciphered_fragment.length+"]:");
                logger.print(ciphered_fragment);
            }
        }
        return packetize(content_type, version, ciphered_fragment);
    }

    private byte[] packetize(byte type, byte[] version, byte[] fragment) {
        byte[] buff = new byte[5+fragment.length];
        buff[0] = type;
        if (version != null) {
            buff[1] = version[0];
            buff[2] = version[1];
        } else {
            buff[1] = 3;
            buff[2] = 1;
        }
        buff[3] = (byte) ((0x00FF00 & fragment.length) >> 8);
        buff[4] = (byte) (0x0000FF & fragment.length);
        System.arraycopy(fragment, 0, buff, 5, fragment.length);
        return buff;
    }

    /**
     * Set the ssl session to be used after sending the changeCipherSpec message
     * @param   session:    SSLSessionImpl
     */
    private void setSession(SSLSessionImpl session) {
        if (!sessionWasChanged) {
            // session was not changed for current handshake process
            if (logger != null) {
                logger.println("SSLRecordProtocol.setSession: Set pending session");
                logger.println("  cipher name: " + session.getCipherSuite());
            }
            this.session = session;
            // create new connection state
            pendingConnectionState = ((version == null) || (version[1] == 1))
                ? (ConnectionState) new ConnectionStateTLS(getSession())
                : (ConnectionState) new ConnectionStateSSLv3(getSession());
            sessionWasChanged = true;
        } else {
            // wait for rehandshaking's session
            sessionWasChanged = false;
        }
    }

    /**
     * Returns the change cipher spec message to be sent to another peer.
     * The pending connection state will be built on the base of provided
     * session object
     * The calling of this method triggers pending write connection state to
     * be active.
     * @return ssl record containing the "change cipher spec" message.
     */
    protected byte[] getChangeCipherSpecMesage(SSLSessionImpl session) {
        // make change_cipher_spec_message:
        byte[] change_cipher_spec_message;
        if (activeWriteState == null) {
            change_cipher_spec_message = new byte[] {
                    ContentType.CHANGE_CIPHER_SPEC, version[0],
                        version[1], 0, 1, 1
                };
        } else {
            change_cipher_spec_message =
                packetize(ContentType.CHANGE_CIPHER_SPEC, version,
                        activeWriteState.encrypt(ContentType.CHANGE_CIPHER_SPEC,
                            change_cipher_spec_byte, 0, 1));
        }
        setSession(session);
        activeWriteState = pendingConnectionState;
        if (logger != null) {
            logger.println("SSLRecordProtocol.getChangeCipherSpecMesage");
            logger.println("activeWriteState = pendingConnectionState");
            logger.print(change_cipher_spec_message);
        }
        return change_cipher_spec_message;
    }

    /**
     * Retrieves the fragment field of TLSCiphertext, and than
     * depending on the established Connection State
     * decrypts and decompresses it. The following structure is expected
     * on the input at the moment of the call:
     *
     *  struct {
     *      ContentType type;
     *      ProtocolVersion version;
     *      uint16 length;
     *      select (CipherSpec.cipher_type) {
     *          case stream: GenericStreamCipher;
     *          case block: GenericBlockCipher;
     *      } fragment;
     *  } TLSCiphertext;
     *
     * (as specified by RFC 2246, TLS v1 Protocol specification)
     *
     * In addition this method can recognize SSLv2 hello message which
     * are often used to establish the SSL/TLS session.
     *
     * @throws IOException if some io errors have been occurred
     * @throws EndOfSourceException if underlying input stream
     *                              has ran out of data.
     * @throws EndOfBufferException if there was not enough data
     *                              to build complete ssl packet.
     * @return the type of unwrapped message.
     */
    protected int unwrap() throws IOException {
        if (logger != null) {
            logger.println("SSLRecordProtocol.unwrap: BEGIN [");
        }
        int type = in.readUint8();
        if ((type < ContentType.CHANGE_CIPHER_SPEC)
                || (type > ContentType.APPLICATION_DATA)) {
            if (logger != null) {
                logger.println("Non v3.1 message type:" + type);
            }
            if (type >= 0x80) {
                // it is probably SSL v2 client_hello message
                // (see SSL v2 spec at:
                // http://wp.netscape.com/eng/security/SSL_2.html)
                int length = (type & 0x7f) << 8 | in.read();
                byte[] fragment = in.read(length);
                handshakeProtocol.unwrapSSLv2(fragment);
                if (logger != null) {
                    logger.println(
                            "SSLRecordProtocol:unwrap ] END, SSLv2 type");
                }
                return ContentType.HANDSHAKE;
            }
            throw new AlertException(AlertProtocol.UNEXPECTED_MESSAGE,
                    new SSLProtocolException(
                        "Unexpected message type has been received: "+type));
        }
        if (logger != null) {
            logger.println("Got the message of type: " + type);
        }
        if (version != null) {
            if ((in.read() != version[0])
                    || (in.read() != version[1])) {
                throw new AlertException(AlertProtocol.UNEXPECTED_MESSAGE,
                        new SSLProtocolException(
                            "Unexpected message type has been received: " +
                            type));
            }
        } else {
            in.skip(2); // just skip the version number
        }
        int length = in.readUint16();
        if (logger != null) {
            logger.println("TLSCiphertext.fragment["+length+"]: ...");
        }
        if (length > MAX_CIPHERED_DATA_LENGTH) {
            throw new AlertException(AlertProtocol.RECORD_OVERFLOW,
                    new SSLProtocolException(
                        "Received message is too big."));
        }
        byte[] fragment = in.read(length);
        if (logger != null) {
            logger.print(fragment);
        }
        if (activeReadState != null) {
            fragment = activeReadState.decrypt((byte) type, fragment);
            if (logger != null) {
                logger.println("TLSPlaintext.fragment:");
                logger.print(fragment);
            }
        }
        if (fragment.length > MAX_DATA_LENGTH) {
            throw new AlertException(AlertProtocol.DECOMPRESSION_FAILURE,
                    new SSLProtocolException(
                        "Decompressed plain data is too big."));
        }
        switch (type) {
            case ContentType.CHANGE_CIPHER_SPEC:
                // notify handshake protocol:
                handshakeProtocol.receiveChangeCipherSpec();
                setSession(handshakeProtocol.getSession());
                // change cipher spec message has been received, so:
                if (logger != null) {
                    logger.println("activeReadState = pendingConnectionState");
                }
                activeReadState = pendingConnectionState;
                break;
            case ContentType.ALERT:
                alert(fragment[0], fragment[1]);
                break;
            case ContentType.HANDSHAKE:
                handshakeProtocol.unwrap(fragment);
                break;
            case ContentType.APPLICATION_DATA:
                if (logger != null) {
                    logger.println(
                            "TLSCiphertext.unwrap: APP DATA["+length+"]:");
                    logger.println(new String(fragment));
                }
                appData.append(fragment);
                break;
            default:
                throw new AlertException(AlertProtocol.UNEXPECTED_MESSAGE,
                        new SSLProtocolException(
                            "Unexpected message type has been received: " +
                            type));
        }
        if (logger != null) {
            logger.println("SSLRecordProtocol:unwrap ] END, type: " + type);
        }
        return type;
    }

    /**
     * Passes the alert information to the alert protocol.
     * @param   level:  byte
     * @param   description:    byte
     */
    protected void alert(byte level, byte description) {
        if (logger != null) {
            logger.println("SSLRecordProtocol.allert: "+level+" "+description);
        }
        alertProtocol.alert(level, description);
    }

    /**
     * Sets up the SSL version used in this connection.
     * This method is calling from the handshake protocol after
     * it becomes known witch protocol version will be used.
     * @param   ver:    byte[]
     * @return
     */
    protected void setVersion(byte[] ver) {
        this.version = ver;
    }

    /**
     * Shuts down the protocol. It will be impossible to use the instance
     * after the calling of this method.
     */
    protected void shutdown() {
        session = null;
        version = null;
        in = null;
        handshakeProtocol = null;
        alertProtocol = null;
        appData = null;
        if (pendingConnectionState != null) {
            pendingConnectionState.shutdown();
        }
        pendingConnectionState = null;
        if (activeReadState != null) {
            activeReadState.shutdown();
        }
        activeReadState = null;
        if (activeReadState != null) {
            activeReadState.shutdown();
        }
        activeWriteState = null;
    }
}
