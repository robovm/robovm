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
package org.robovm.apple.coreservices;

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
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CFNetwork") @Marshaler(NSString.AsStringMarshaler.class)/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CoreServices/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CoreServices.class); }/*</bind>*/
    /*<constants>*/
    public static final int Constant__kCFStreamErrorSOCKS5BadResponseAddr = 1;
    public static final int Constant__kCFStreamErrorSOCKS5BadState = 2;
    public static final int Constant__kCFStreamErrorSOCKSUnknownClientVersion = 3;
    public static final int Constant__kCFStreamErrorSOCKS4RequestFailed = 91;
    public static final int Constant__kCFStreamErrorSOCKS4IdentdFailed = 92;
    public static final int Constant__kCFStreamErrorSOCKS4IdConflict = 93;
    public static final int Constant__kCFStreamSocketSecurityNone = 0;
    public static final int Constant__kCFStreamSocketSecuritySSLv2 = 1;
    public static final int Constant__kCFStreamSocketSecuritySSLv3 = 2;
    public static final int Constant__kCFStreamSocketSecuritySSLv23 = 3;
    public static final int Constant__kCFStreamSocketSecurityTLSv1 = 4;
    public static final int Constant__kCFStreamErrorHTTPParseFailure = -1;
    public static final int Constant__kCFStreamErrorHTTPRedirectionLoop = -2;
    public static final int Constant__kCFStreamErrorHTTPBadURL = -3;
    public static final int Constant__kCFStreamErrorHTTPAuthenticationTypeUnsupported = -1000;
    public static final int Constant__kCFStreamErrorHTTPAuthenticationBadUserName = -1001;
    public static final int Constant__kCFStreamErrorHTTPAuthenticationBadPassword = -1002;
    /*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFErrorDomainCFNetwork", optional=true)
    public static native String Value__kCFErrorDomainCFNetwork();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFErrorDomainWinSock", optional=true)
    public static native String Value__kCFErrorDomainWinSock();
    /**
     * @since Available in iOS 2.2 and later.
     */
    @GlobalValue(symbol="kCFURLErrorFailingURLErrorKey", optional=true)
    public static native String Value__kCFURLErrorFailingURLErrorKey();
    /**
     * @since Available in iOS 2.2 and later.
     */
    @GlobalValue(symbol="kCFURLErrorFailingURLStringErrorKey", optional=true)
    public static native String Value__kCFURLErrorFailingURLStringErrorKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFGetAddrInfoFailureKey", optional=true)
    public static native String Value__kCFGetAddrInfoFailureKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFSOCKSStatusCodeKey", optional=true)
    public static native String Value__kCFSOCKSStatusCodeKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFSOCKSVersionKey", optional=true)
    public static native String Value__kCFSOCKSVersionKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFSOCKSNegotiationMethodKey", optional=true)
    public static native String Value__kCFSOCKSNegotiationMethodKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDNSServiceFailureKey", optional=true)
    public static native String Value__kCFDNSServiceFailureKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFFTPStatusCodeKey", optional=true)
    public static native String Value__kCFFTPStatusCodeKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamErrorDomainNetDB", optional=true)
    public static native int Value__kCFStreamErrorDomainNetDB();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamErrorDomainSystemConfiguration", optional=true)
    public static native int Value__kCFStreamErrorDomainSystemConfiguration();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamErrorDomainMach", optional=true)
    public static native int Value__kCFStreamErrorDomainMach();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamErrorDomainNetServices", optional=true)
    public static native int Value__kCFStreamErrorDomainNetServices();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFStreamPropertySSLContext", optional=true)
    public static native String Value__kCFStreamPropertySSLContext();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamPropertySSLPeerTrust", optional=true)
    public static native String Value__kCFStreamPropertySSLPeerTrust();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamSSLValidatesCertificateChain", optional=true)
    public static native String Value__kCFStreamSSLValidatesCertificateChain();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamPropertySSLSettings", optional=true)
    public static native String Value__kCFStreamPropertySSLSettings();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamSSLLevel", optional=true)
    public static native String Value__kCFStreamSSLLevel();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamSSLPeerName", optional=true)
    public static native String Value__kCFStreamSSLPeerName();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamSSLCertificates", optional=true)
    public static native String Value__kCFStreamSSLCertificates();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamSSLIsServer", optional=true)
    public static native String Value__kCFStreamSSLIsServer();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFStreamNetworkServiceType", optional=true)
    public static native String Value__kCFStreamNetworkServiceType();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFStreamNetworkServiceTypeVoIP", optional=true)
    public static native String Value__kCFStreamNetworkServiceTypeVoIP();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFStreamNetworkServiceTypeVideo", optional=true)
    public static native String Value__kCFStreamNetworkServiceTypeVideo();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFStreamNetworkServiceTypeBackground", optional=true)
    public static native String Value__kCFStreamNetworkServiceTypeBackground();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFStreamNetworkServiceTypeVoice", optional=true)
    public static native String Value__kCFStreamNetworkServiceTypeVoice();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFStreamPropertyNoCellular", optional=true)
    public static native String Value__kCFStreamPropertyNoCellular();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCFStreamPropertyConnectionIsCellular", optional=true)
    public static native String Value__kCFStreamPropertyConnectionIsCellular();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamErrorDomainWinSock", optional=true)
    public static native @MachineSizedSInt long Value__kCFStreamErrorDomainWinSock();
    @GlobalValue(symbol="kCFStreamErrorDomainSOCKS", optional=true)
    public static native int Value__kCFStreamErrorDomainSOCKS();
    @GlobalValue(symbol="kCFStreamPropertySOCKSProxy", optional=true)
    public static native String Value__kCFStreamPropertySOCKSProxy();
    @GlobalValue(symbol="kCFStreamPropertySOCKSProxyHost", optional=true)
    public static native String Value__kCFStreamPropertySOCKSProxyHost();
    @GlobalValue(symbol="kCFStreamPropertySOCKSProxyPort", optional=true)
    public static native String Value__kCFStreamPropertySOCKSProxyPort();
    @GlobalValue(symbol="kCFStreamPropertySOCKSVersion", optional=true)
    public static native String Value__kCFStreamPropertySOCKSVersion();
    @GlobalValue(symbol="kCFStreamSocketSOCKSVersion4", optional=true)
    public static native String Value__kCFStreamSocketSOCKSVersion4();
    @GlobalValue(symbol="kCFStreamSocketSOCKSVersion5", optional=true)
    public static native String Value__kCFStreamSocketSOCKSVersion5();
    @GlobalValue(symbol="kCFStreamPropertySOCKSUser", optional=true)
    public static native String Value__kCFStreamPropertySOCKSUser();
    @GlobalValue(symbol="kCFStreamPropertySOCKSPassword", optional=true)
    public static native String Value__kCFStreamPropertySOCKSPassword();
    @GlobalValue(symbol="kCFStreamErrorDomainSSL", optional=true)
    public static native int Value__kCFStreamErrorDomainSSL();
    @GlobalValue(symbol="kCFStreamPropertySocketSecurityLevel", optional=true)
    public static native String Value__kCFStreamPropertySocketSecurityLevel();
    @GlobalValue(symbol="kCFStreamSocketSecurityLevelNone", optional=true)
    public static native String Value__kCFStreamSocketSecurityLevelNone();
    @GlobalValue(symbol="kCFStreamSocketSecurityLevelSSLv2", optional=true)
    public static native String Value__kCFStreamSocketSecurityLevelSSLv2();
    @GlobalValue(symbol="kCFStreamSocketSecurityLevelSSLv3", optional=true)
    public static native String Value__kCFStreamSocketSecurityLevelSSLv3();
    @GlobalValue(symbol="kCFStreamSocketSecurityLevelTLSv1", optional=true)
    public static native String Value__kCFStreamSocketSecurityLevelTLSv1();
    @GlobalValue(symbol="kCFStreamSocketSecurityLevelNegotiatedSSL", optional=true)
    public static native String Value__kCFStreamSocketSecurityLevelNegotiatedSSL();
    @GlobalValue(symbol="kCFStreamPropertyShouldCloseNativeSocket", optional=true)
    public static native String Value__kCFStreamPropertyShouldCloseNativeSocket();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamPropertySocketRemoteHost", optional=true)
    public static native String Value__kCFStreamPropertySocketRemoteHost();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamPropertySocketRemoteNetService", optional=true)
    public static native String Value__kCFStreamPropertySocketRemoteNetService();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamPropertyProxyLocalBypass", optional=true)
    public static native String Value__kCFStreamPropertyProxyLocalBypass();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamErrorDomainFTP", optional=true)
    public static native int Value__kCFStreamErrorDomainFTP();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamPropertyFTPUserName", optional=true)
    public static native String Value__kCFStreamPropertyFTPUserName();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamPropertyFTPPassword", optional=true)
    public static native String Value__kCFStreamPropertyFTPPassword();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamPropertyFTPUsePassiveMode", optional=true)
    public static native String Value__kCFStreamPropertyFTPUsePassiveMode();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamPropertyFTPResourceSize", optional=true)
    public static native String Value__kCFStreamPropertyFTPResourceSize();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamPropertyFTPFetchResourceInfo", optional=true)
    public static native String Value__kCFStreamPropertyFTPFetchResourceInfo();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamPropertyFTPFileTransferOffset", optional=true)
    public static native String Value__kCFStreamPropertyFTPFileTransferOffset();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamPropertyFTPAttemptPersistentConnection", optional=true)
    public static native String Value__kCFStreamPropertyFTPAttemptPersistentConnection();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamPropertyFTPProxy", optional=true)
    public static native String Value__kCFStreamPropertyFTPProxy();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamPropertyFTPProxyHost", optional=true)
    public static native String Value__kCFStreamPropertyFTPProxyHost();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamPropertyFTPProxyPort", optional=true)
    public static native String Value__kCFStreamPropertyFTPProxyPort();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamPropertyFTPProxyUser", optional=true)
    public static native String Value__kCFStreamPropertyFTPProxyUser();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamPropertyFTPProxyPassword", optional=true)
    public static native String Value__kCFStreamPropertyFTPProxyPassword();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFFTPResourceMode", optional=true)
    public static native String Value__kCFFTPResourceMode();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFFTPResourceName", optional=true)
    public static native String Value__kCFFTPResourceName();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFFTPResourceOwner", optional=true)
    public static native String Value__kCFFTPResourceOwner();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFFTPResourceGroup", optional=true)
    public static native String Value__kCFFTPResourceGroup();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFFTPResourceLink", optional=true)
    public static native String Value__kCFFTPResourceLink();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFFTPResourceSize", optional=true)
    public static native String Value__kCFFTPResourceSize();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFFTPResourceType", optional=true)
    public static native String Value__kCFFTPResourceType();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFFTPResourceModDate", optional=true)
    public static native String Value__kCFFTPResourceModDate();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamErrorDomainHTTP", optional=true)
    public static native int Value__kCFStreamErrorDomainHTTP();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamPropertyHTTPResponseHeader", optional=true)
    public static native String Value__kCFStreamPropertyHTTPResponseHeader();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamPropertyHTTPFinalURL", optional=true)
    public static native String Value__kCFStreamPropertyHTTPFinalURL();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamPropertyHTTPFinalRequest", optional=true)
    public static native String Value__kCFStreamPropertyHTTPFinalRequest();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamPropertyHTTPProxy", optional=true)
    public static native String Value__kCFStreamPropertyHTTPProxy();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamPropertyHTTPProxyHost", optional=true)
    public static native String Value__kCFStreamPropertyHTTPProxyHost();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamPropertyHTTPProxyPort", optional=true)
    public static native String Value__kCFStreamPropertyHTTPProxyPort();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamPropertyHTTPSProxyHost", optional=true)
    public static native String Value__kCFStreamPropertyHTTPSProxyHost();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamPropertyHTTPSProxyPort", optional=true)
    public static native String Value__kCFStreamPropertyHTTPSProxyPort();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamPropertyHTTPShouldAutoredirect", optional=true)
    public static native String Value__kCFStreamPropertyHTTPShouldAutoredirect();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamPropertyHTTPAttemptPersistentConnection", optional=true)
    public static native String Value__kCFStreamPropertyHTTPAttemptPersistentConnection();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStreamPropertyHTTPRequestBytesWrittenCount", optional=true)
    public static native String Value__kCFStreamPropertyHTTPRequestBytesWrittenCount();
    /*</methods>*/
}
