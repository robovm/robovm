/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.foundation.CocoaUtility;
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SSLErrorCode/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*/
    public static final int Protocol = -9800;
    public static final int Negotiation = -9801;
    public static final int FatalAlert = -9802;
    public static final int WouldBlock = -9803;
    public static final int SessionNotFound = -9804;
    public static final int ClosedGraceful = -9805;
    public static final int ClosedAbort = -9806;
    public static final int XCertChainInvalid = -9807;
    public static final int BadCert = -9808;
    public static final int Crypto = -9809;
    public static final int Internal = -9810;
    public static final int ModuleAttach = -9811;
    public static final int UnknownRootCert = -9812;
    public static final int NoRootCert = -9813;
    public static final int CertExpired = -9814;
    public static final int CertNotYetValid = -9815;
    public static final int ClosedNoNotify = -9816;
    public static final int BufferOverflow = -9817;
    public static final int BadCipherSuite = -9818;
    public static final int PeerUnexpectedMsg = -9819;
    public static final int PeerBadRecordMac = -9820;
    public static final int PeerDecryptionFail = -9821;
    public static final int PeerRecordOverflow = -9822;
    public static final int PeerDecompressFail = -9823;
    public static final int PeerHandshakeFail = -9824;
    public static final int PeerBadCert = -9825;
    public static final int PeerUnsupportedCert = -9826;
    public static final int PeerCertRevoked = -9827;
    public static final int PeerCertExpired = -9828;
    public static final int PeerCertUnknown = -9829;
    public static final int IllegalParam = -9830;
    public static final int PeerUnknownCA = -9831;
    public static final int PeerAccessDenied = -9832;
    public static final int PeerDecodeError = -9833;
    public static final int PeerDecryptError = -9834;
    public static final int PeerExportRestriction = -9835;
    public static final int PeerProtocolVersion = -9836;
    public static final int PeerInsufficientSecurity = -9837;
    public static final int PeerInternalError = -9838;
    public static final int PeerUserCancelled = -9839;
    public static final int PeerNoRenegotiation = -9840;
    public static final int PeerAuthCompleted = -9841;
    public static final int ClientCertRequested = -9842;
    public static final int HostNameMismatch = -9843;
    public static final int ConnectionRefused = -9844;
    public static final int DecryptionFail = -9845;
    public static final int BadRecordMac = -9846;
    public static final int RecordOverflow = -9847;
    public static final int BadConfiguration = -9848;
    public static final int UnexpectedRecord = -9849;
    /*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*//*</methods>*/
}
