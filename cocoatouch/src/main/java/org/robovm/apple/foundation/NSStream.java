/*
 * Copyright (C) 2014 Trillian AB
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

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSStream/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSStreamPtr extends Ptr<NSStream, NSStreamPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSStream.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSStream() {}
    protected NSStream(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="NSStreamSocketSecurityLevelKey")
    public static native NSString KeySocketSecurityLevel();
    @GlobalValue(symbol="NSStreamSocketSecurityLevelNone")
    public static native NSString SocketSecurityLevelNone();
    @GlobalValue(symbol="NSStreamSocketSecurityLevelSSLv2")
    public static native NSString SocketSecurityLevelSSLv2();
    @GlobalValue(symbol="NSStreamSocketSecurityLevelSSLv3")
    public static native NSString SocketSecurityLevelSSLv3();
    @GlobalValue(symbol="NSStreamSocketSecurityLevelTLSv1")
    public static native NSString SocketSecurityLevelTLSv1();
    @GlobalValue(symbol="NSStreamSocketSecurityLevelNegotiatedSSL")
    public static native NSString SocketSecurityLevelNegotiatedSSL();
    @GlobalValue(symbol="NSStreamSOCKSProxyConfigurationKey")
    public static native NSString KeySOCKSProxyConfiguration();
    @GlobalValue(symbol="NSStreamSOCKSProxyHostKey")
    public static native NSString KeySOCKSProxyHost();
    @GlobalValue(symbol="NSStreamSOCKSProxyPortKey")
    public static native NSString KeySOCKSProxyPort();
    @GlobalValue(symbol="NSStreamSOCKSProxyVersionKey")
    public static native NSString KeySOCKSProxyVersion();
    @GlobalValue(symbol="NSStreamSOCKSProxyUserKey")
    public static native NSString KeySOCKSProxyUser();
    @GlobalValue(symbol="NSStreamSOCKSProxyPasswordKey")
    public static native NSString KeySOCKSProxyPassword();
    @GlobalValue(symbol="NSStreamSOCKSProxyVersion4")
    public static native NSString SOCKSProxyVersion4();
    @GlobalValue(symbol="NSStreamSOCKSProxyVersion5")
    public static native NSString SOCKSProxyVersion5();
    @GlobalValue(symbol="NSStreamDataWrittenToMemoryStreamKey")
    public static native NSString KeyDataWrittenToMemoryStream();
    @GlobalValue(symbol="NSStreamFileCurrentOffsetKey")
    public static native NSString KeyFileCurrentOffset();
    @GlobalValue(symbol="NSStreamSocketSSLErrorDomain")
    public static native String ErrorDomainSocketSSL();
    @GlobalValue(symbol="NSStreamSOCKSErrorDomain")
    public static native String ErrorDomainSOCKS();
    @GlobalValue(symbol="NSStreamNetworkServiceType")
    public static native NSString KeyNetworkServiceType();
    @GlobalValue(symbol="NSStreamNetworkServiceTypeVoIP")
    public static native NSString NetworkServiceTypeVoIP();
    @GlobalValue(symbol="NSStreamNetworkServiceTypeVideo")
    public static native NSString NetworkServiceTypeVideo();
    @GlobalValue(symbol="NSStreamNetworkServiceTypeBackground")
    public static native NSString NetworkServiceTypeBackground();
    @GlobalValue(symbol="NSStreamNetworkServiceTypeVoice")
    public static native NSString NetworkServiceTypeVoice();
    
    @Method(selector = "open")
    public native void openStream();
    @Method(selector = "close")
    public native void closeStream();
    @Method(selector = "delegate")
    public native NSStreamDelegate delegate();
    @Method(selector = "setDelegate:")
    public native void setDelegate$(NSStreamDelegate delegate);
    @Method(selector = "propertyForKey:")
    public native NSObject propertyForKey$(String key);
    @Method(selector = "setProperty:forKey:")
    public native boolean setProperty$forKey$(NSObject property, String key);
    @Method(selector = "scheduleInRunLoop:forMode:")
    public native void scheduleInRunLoop$forMode$(NSRunLoop aRunLoop, String mode);
    @Method(selector = "removeFromRunLoop:forMode:")
    public native void removeFromRunLoop$forMode$(NSRunLoop aRunLoop, String mode);
    @Method(selector = "streamStatus")
    public native NSStreamStatus streamStatus();
    @Method(selector = "streamError")
    public native NSError streamError();
    /*</methods>*/
}
