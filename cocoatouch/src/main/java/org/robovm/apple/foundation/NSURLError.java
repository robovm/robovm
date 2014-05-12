/*
 * Copyright (C) 2014 Trillian Mobile AB
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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
public enum /*<name>*/NSURLError/*</name>*/ implements ValuedEnum {
    /*<values>*/
    Unknown(-1L),
    Cancelled(-999L),
    BadURL(-1000L),
    TimedOut(-1001L),
    UnsupportedURL(-1002L),
    CannotFindHost(-1003L),
    CannotConnectToHost(-1004L),
    NetworkConnectionLost(-1005L),
    DNSLookupFailed(-1006L),
    HTTPTooManyRedirects(-1007L),
    ResourceUnavailable(-1008L),
    NotConnectedToInternet(-1009L),
    RedirectToNonExistentLocation(-1010L),
    BadServerResponse(-1011L),
    UserCancelledAuthentication(-1012L),
    UserAuthenticationRequired(-1013L),
    ZeroByteResource(-1014L),
    CannotDecodeRawData(-1015L),
    CannotDecodeContentData(-1016L),
    CannotParseResponse(-1017L),
    FileDoesNotExist(-1100L),
    FileIsDirectory(-1101L),
    NoPermissionsToReadFile(-1102L),
    /**
     * @since Available in iOS 2.0 and later.
     */
    DataLengthExceedsMaximum(-1103L),
    SecureConnectionFailed(-1200L),
    ServerCertificateHasBadDate(-1201L),
    ServerCertificateUntrusted(-1202L),
    ServerCertificateHasUnknownRoot(-1203L),
    ServerCertificateNotYetValid(-1204L),
    ClientCertificateRejected(-1205L),
    ClientCertificateRequired(-1206L),
    CannotLoadFromNetwork(-2000L),
    CannotCreateFile(-3000L),
    CannotOpenFile(-3001L),
    CannotCloseFile(-3002L),
    CannotWriteToFile(-3003L),
    CannotRemoveFile(-3004L),
    CannotMoveFile(-3005L),
    DownloadDecodingFailedMidStream(-3006L),
    DownloadDecodingFailedToComplete(-3007L),
    /**
     * @since Available in iOS 3.0 and later.
     */
    InternationalRoamingOff(-1018L),
    /**
     * @since Available in iOS 3.0 and later.
     */
    CallIsActive(-1019L),
    /**
     * @since Available in iOS 3.0 and later.
     */
    DataNotAllowed(-1020L),
    /**
     * @since Available in iOS 3.0 and later.
     */
    RequestBodyStreamExhausted(-1021L);
    /*</values>*/

    private final long n;

    private /*<name>*/NSURLError/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/NSURLError/*</name>*/ valueOf(long n) {
        for (/*<name>*/NSURLError/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/NSURLError/*</name>*/.class.getName());
    }
}
