/*
 * Copyright (C) 2013-2015 RoboVM AB
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
package org.robovm.apple.security;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
public enum /*<name>*/SSLErrorCode/*</name>*/ implements ValuedEnum {
    /*<values>*/
    Protocol(-9800L),
    Negotiation(-9801L),
    FatalAlert(-9802L),
    WouldBlock(-9803L),
    SessionNotFound(-9804L),
    ClosedGraceful(-9805L),
    ClosedAbort(-9806L),
    XCertChainInvalid(-9807L),
    BadCert(-9808L),
    Crypto(-9809L),
    Internal(-9810L),
    ModuleAttach(-9811L),
    UnknownRootCert(-9812L),
    NoRootCert(-9813L),
    CertExpired(-9814L),
    CertNotYetValid(-9815L),
    ClosedNoNotify(-9816L),
    BufferOverflow(-9817L),
    BadCipherSuite(-9818L),
    PeerUnexpectedMsg(-9819L),
    PeerBadRecordMac(-9820L),
    PeerDecryptionFail(-9821L),
    PeerRecordOverflow(-9822L),
    PeerDecompressFail(-9823L),
    PeerHandshakeFail(-9824L),
    PeerBadCert(-9825L),
    PeerUnsupportedCert(-9826L),
    PeerCertRevoked(-9827L),
    PeerCertExpired(-9828L),
    PeerCertUnknown(-9829L),
    IllegalParam(-9830L),
    PeerUnknownCA(-9831L),
    PeerAccessDenied(-9832L),
    PeerDecodeError(-9833L),
    PeerDecryptError(-9834L),
    PeerExportRestriction(-9835L),
    PeerProtocolVersion(-9836L),
    PeerInsufficientSecurity(-9837L),
    PeerInternalError(-9838L),
    PeerUserCancelled(-9839L),
    PeerNoRenegotiation(-9840L),
    PeerAuthCompleted(-9841L),
    ClientCertRequested(-9842L),
    HostNameMismatch(-9843L),
    ConnectionRefused(-9844L),
    DecryptionFail(-9845L),
    BadRecordMac(-9846L),
    RecordOverflow(-9847L),
    BadConfiguration(-9848L),
    UnexpectedRecord(-9849L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/SSLErrorCode/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/SSLErrorCode/*</name>*/ valueOf(long n) {
        for (/*<name>*/SSLErrorCode/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/SSLErrorCode/*</name>*/.class.getName());
    }
}
