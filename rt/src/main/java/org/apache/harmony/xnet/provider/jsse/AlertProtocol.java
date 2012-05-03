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

/**
 * This class encapsulates the functionality of Alert Protocol.
 * Constant values are taken according to the TLS v1 specification
 * (http://www.ietf.org/rfc/rfc2246.txt), p 7.2.
 */
public class AlertProtocol {

    // ------------------------ AlertLevel codes --------------------------
    /**
     * Defines the severity of alert as warning
     */
    protected static final byte WARNING = 1;
    /**
     * Defines the severity of alert as fatal
     */
    protected static final byte FATAL = 2;

    // --------------------- AlertDescription codes -----------------------
    /**
     * Defines the description code of the close_notify alert
     */
    protected static final byte CLOSE_NOTIFY = 0;
    /**
     * Defines the description code of the unexpected_message alert
     */
    protected static final byte UNEXPECTED_MESSAGE = 10;
    /**
     * Defines the description code of the bad_record_mac alert
     */
    protected static final byte BAD_RECORD_MAC = 20;
    /**
     * Defines the description code of the decryption_failed alert
     */
    protected static final byte DECRYPTION_FAILED = 21;
    /**
     * Defines the description code of the record_overflow alert
     */
    protected static final byte RECORD_OVERFLOW = 22;
    /**
     * Defines the description code of the decompression_failure alert
     */
    protected static final byte DECOMPRESSION_FAILURE = 30;
    /**
     * Defines the description code of the handshake_failure alert
     */
    protected static final byte HANDSHAKE_FAILURE = 40;
    /**
     * Defines the description code of the bad_certificate alert
     */
    protected static final byte BAD_CERTIFICATE = 42;
    /**
     * Defines the description code of the unsupported_certificate alert
     */
    protected static final byte UNSUPPORTED_CERTIFICATE = 43;
    /**
     * Defines the description code of the certificate_revoked alert
     */
    protected static final byte CERTIFICATE_REVOKED = 44;
    /**
     * Defines the description code of the certificate_expired alert
     */
    protected static final byte CERTIFICATE_EXPIRED = 45;
    /**
     * Defines the description code of the certificate_unknown alert
     */
    protected static final byte CERTIFICATE_UNKNOWN = 46;
    /**
     * Defines the description code of the illegal_parameter alert
     */
    protected static final byte ILLEGAL_PARAMETER = 47;
    /**
     * Defines the description code of the unknown_ca alert
     */
    protected static final byte UNKNOWN_CA = 48;
    /**
     * Defines the description code of the access_denied alert
     */
    protected static final byte ACCESS_DENIED = 49;
    /**
     * Defines the description code of the decode_error alert
     */
    protected static final byte DECODE_ERROR = 50;
    /**
     * Defines the description code of the decrypt_error alert
     */
    protected static final byte DECRYPT_ERROR = 51;
    /**
     * Defines the description code of the export_restriction alert
     */
    protected static final byte EXPORT_RESTRICTION = 60;
    /**
     * Defines the description code of the protocol_version alert
     */
    protected static final byte PROTOCOL_VERSION = 70;
    /**
     * Defines the description code of the insufficient_security alert
     */
    protected static final byte INSUFFICIENT_SECURITY = 71;
    /**
     * Defines the description code of the internal_error alert
     */
    protected static final byte INTERNAL_ERROR = 80;
    /**
     * Defines the description code of the user_canceled alert
     */
    protected static final byte USER_CANCELED = 90;
    /**
     * Defines the description code of the no_renegotiation alert
     */
    protected static final byte NO_RENEGOTIATION = 100;
    // holds level and description codes
    private final byte[] alert = new byte[2];
    // record protocol to be used to wrap the alerts
    private SSLRecordProtocol recordProtocol;

    private Logger.Stream logger = Logger.getStream("alert");

    /**
     * Creates the instance of AlertProtocol.
     * Note that class is not ready to work without providing of
     * record protocol
     * @see #setRecordProtocol
     */
    protected AlertProtocol() {}

    /**
     * Sets up the record protocol to be used by this allert protocol.
     */
    protected void setRecordProtocol(SSLRecordProtocol recordProtocol) {
        this.recordProtocol = recordProtocol;
    }

    /**
     * Reports an alert to be sent/received by transport.
     * This method is usually called during processing
     * of the income TSL record: if it contains alert message from another
     * peer, or if warning alert occured during the processing of the
     * message and this warning should be sent to another peer.
     * @param   level:  alert level code
     * @param   description: alert description code
     * @return
     */
    protected void alert(byte level, byte description) {
        if (logger != null) {
            logger.println("Alert.alert: "+level+" "+description);
        }
        this.alert[0] = level;
        this.alert[1] = description;
    }

    /**
     * Returns the description code of alert or -100 if there
     * is no alert.
     */
    protected byte getDescriptionCode() {
        return (alert[0] != 0) ? alert[1] : -100;
    }

    /**
     * Resets the protocol to be in "no alert" state.
     * This method shoud be called after processing of the reported alert.
     */
    protected void setProcessed() {
        // free the info about alert
        if (logger != null) {
            logger.println("Alert.setProcessed");
        }
        this.alert[0] = 0;
    }

    /**
     * Checks if any alert has occured.
     */
    protected boolean hasAlert() {
        return (alert[0] != 0);
    }

    /**
     * Checks if occured alert is fatal alert.
     */
    protected boolean isFatalAlert() {
        return (alert[0] == 2);
    }

    /**
     * Returns the string representation of occured alert.
     * If no alert has occured null is returned.
     */
    protected String getAlertDescription() {
        switch (alert[1]) {
        case CLOSE_NOTIFY:
            return "close_notify";
        case UNEXPECTED_MESSAGE:
            return "unexpected_message";
        case BAD_RECORD_MAC:
            return "bad_record_mac";
        case DECRYPTION_FAILED:
            return "decryption_failed";
        case RECORD_OVERFLOW:
            return "record_overflow";
        case DECOMPRESSION_FAILURE:
            return "decompression_failure";
        case HANDSHAKE_FAILURE:
            return "handshake_failure";
        case BAD_CERTIFICATE:
            return "bad_certificate";
        case UNSUPPORTED_CERTIFICATE:
            return "unsupported_certificate";
        case CERTIFICATE_REVOKED:
            return "certificate_revoked";
        case CERTIFICATE_EXPIRED:
            return "certificate_expired";
        case CERTIFICATE_UNKNOWN:
            return "certificate_unknown";
        case ILLEGAL_PARAMETER:
            return "illegal_parameter";
        case UNKNOWN_CA:
            return "unknown_ca";
        case ACCESS_DENIED:
            return "access_denied";
        case DECODE_ERROR:
            return "decode_error";
        case DECRYPT_ERROR:
            return "decrypt_error";
        case EXPORT_RESTRICTION:
            return "export_restriction";
        case PROTOCOL_VERSION:
            return "protocol_version";
        case INSUFFICIENT_SECURITY:
            return "insufficient_security";
        case INTERNAL_ERROR:
            return "internal_error";
        case USER_CANCELED:
            return "user_canceled";
        case NO_RENEGOTIATION:
            return "no_renegotiation";
        }
        return null;
    }

    /**
     * Returns the record with reported alert message.
     * The returned array of bytes is ready to be sent to another peer.
     * Note, that this method does not automatically set the state of alert
     * protocol in "no alert" state, so after wrapping the method setProcessed
     * should be called.
     */
    protected byte[] wrap() {
        byte[] res = recordProtocol.wrap(ContentType.ALERT, alert, 0, 2);
        return res;
    }

    /**
     * Shutdown the protocol. It will be impossible to use the instance
     * after the calling of this method.
     */
    protected void shutdown() {
        alert[0] = 0;
        alert[1] = 0;
        recordProtocol = null;
    }
}

