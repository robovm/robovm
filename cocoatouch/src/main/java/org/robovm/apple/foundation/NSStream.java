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
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSocketSecurityLevelKey", optional=true)
    public static native NSString KeySocketSecurityLevel();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSocketSecurityLevelNone", optional=true)
    public static native NSString SocketSecurityLevelNone();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSocketSecurityLevelSSLv2", optional=true)
    public static native NSString SocketSecurityLevelSSLv2();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSocketSecurityLevelSSLv3", optional=true)
    public static native NSString SocketSecurityLevelSSLv3();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSocketSecurityLevelTLSv1", optional=true)
    public static native NSString SocketSecurityLevelTLSv1();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSocketSecurityLevelNegotiatedSSL", optional=true)
    public static native NSString SocketSecurityLevelNegotiatedSSL();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSOCKSProxyConfigurationKey", optional=true)
    public static native NSString KeySOCKSProxyConfiguration();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSOCKSProxyHostKey", optional=true)
    public static native NSString KeySOCKSProxyHost();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSOCKSProxyPortKey", optional=true)
    public static native NSString KeySOCKSProxyPort();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSOCKSProxyVersionKey", optional=true)
    public static native NSString KeySOCKSProxyVersion();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSOCKSProxyUserKey", optional=true)
    public static native NSString KeySOCKSProxyUser();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSOCKSProxyPasswordKey", optional=true)
    public static native NSString KeySOCKSProxyPassword();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSOCKSProxyVersion4", optional=true)
    public static native NSString SOCKSProxyVersion4();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSOCKSProxyVersion5", optional=true)
    public static native NSString SOCKSProxyVersion5();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamDataWrittenToMemoryStreamKey", optional=true)
    public static native NSString KeyDataWrittenToMemoryStream();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamFileCurrentOffsetKey", optional=true)
    public static native NSString KeyFileCurrentOffset();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSocketSSLErrorDomain", optional=true)
    public static native String ErrorDomainSocketSSL();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSOCKSErrorDomain", optional=true)
    public static native String ErrorDomainSOCKS();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSStreamNetworkServiceType", optional=true)
    public static native NSString KeyNetworkServiceType();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSStreamNetworkServiceTypeVoIP", optional=true)
    public static native NSString NetworkServiceTypeVoIP();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSStreamNetworkServiceTypeVideo", optional=true)
    public static native NSString NetworkServiceTypeVideo();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSStreamNetworkServiceTypeBackground", optional=true)
    public static native NSString NetworkServiceTypeBackground();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSStreamNetworkServiceTypeVoice", optional=true)
    public static native NSString NetworkServiceTypeVoice();
    
    @Method(selector = "open")
    public native void openStream();
    @Method(selector = "close")
    public native void closeStream();
    @Method(selector = "delegate")
    public native NSStreamDelegate delegate();
    @Method(selector = "setDelegate:")
    public native void setDelegate(NSStreamDelegate delegate);
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
