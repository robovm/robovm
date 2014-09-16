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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 7.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSURLSessionConfiguration/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSURLSessionConfigurationPtr extends Ptr<NSURLSessionConfiguration, NSURLSessionConfigurationPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSURLSessionConfiguration.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSURLSessionConfiguration() {}
    protected NSURLSessionConfiguration(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "identifier")
    public native String getIdentifier();
    @Property(selector = "requestCachePolicy")
    public native NSURLRequestCachePolicy getRequestCachePolicy();
    @Property(selector = "setRequestCachePolicy:")
    public native void setRequestCachePolicy(NSURLRequestCachePolicy v);
    @Property(selector = "timeoutIntervalForRequest")
    public native double getTimeoutIntervalForRequest();
    @Property(selector = "setTimeoutIntervalForRequest:")
    public native void setTimeoutIntervalForRequest(double v);
    @Property(selector = "timeoutIntervalForResource")
    public native double getTimeoutIntervalForResource();
    @Property(selector = "setTimeoutIntervalForResource:")
    public native void setTimeoutIntervalForResource(double v);
    @Property(selector = "networkServiceType")
    public native NSURLRequestNetworkServiceType getNetworkServiceType();
    @Property(selector = "setNetworkServiceType:")
    public native void setNetworkServiceType(NSURLRequestNetworkServiceType v);
    @Property(selector = "allowsCellularAccess")
    public native boolean isAllowsCellularAccess();
    @Property(selector = "setAllowsCellularAccess:")
    public native void setAllowsCellularAccess(boolean v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "isDiscretionary")
    public native boolean isDiscretionary();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setDiscretionary:")
    public native void setDiscretionary(boolean v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "sessionSendsLaunchEvents")
    public native boolean isSessionSendsLaunchEvents();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setSessionSendsLaunchEvents:")
    public native void setSessionSendsLaunchEvents(boolean v);
    @Property(selector = "connectionProxyDictionary")
    public native NSDictionary<?, ?> getConnectionProxyDictionary();
    @Property(selector = "setConnectionProxyDictionary:")
    public native void setConnectionProxyDictionary(NSDictionary<?, ?> v);
    @Property(selector = "TLSMinimumSupportedProtocol")
    public native SSLProtocol getTLSMinimumSupportedProtocol();
    @Property(selector = "setTLSMinimumSupportedProtocol:")
    public native void setTLSMinimumSupportedProtocol(SSLProtocol v);
    @Property(selector = "TLSMaximumSupportedProtocol")
    public native SSLProtocol getTLSMaximumSupportedProtocol();
    @Property(selector = "setTLSMaximumSupportedProtocol:")
    public native void setTLSMaximumSupportedProtocol(SSLProtocol v);
    @Property(selector = "HTTPShouldUsePipelining")
    public native boolean isHTTPShouldUsePipelining();
    @Property(selector = "setHTTPShouldUsePipelining:")
    public native void setHTTPShouldUsePipelining(boolean v);
    @Property(selector = "HTTPShouldSetCookies")
    public native boolean isHTTPShouldSetCookies();
    @Property(selector = "setHTTPShouldSetCookies:")
    public native void setHTTPShouldSetCookies(boolean v);
    @Property(selector = "HTTPCookieAcceptPolicy")
    public native NSHTTPCookieAcceptPolicy getHTTPCookieAcceptPolicy();
    @Property(selector = "setHTTPCookieAcceptPolicy:")
    public native void setHTTPCookieAcceptPolicy(NSHTTPCookieAcceptPolicy v);
    @Property(selector = "HTTPAdditionalHeaders")
    public native NSDictionary<?, ?> getHTTPAdditionalHeaders();
    @Property(selector = "setHTTPAdditionalHeaders:")
    public native void setHTTPAdditionalHeaders(NSDictionary<?, ?> v);
    @Property(selector = "HTTPMaximumConnectionsPerHost")
    public native @MachineSizedSInt long getHTTPMaximumConnectionsPerHost();
    @Property(selector = "setHTTPMaximumConnectionsPerHost:")
    public native void setHTTPMaximumConnectionsPerHost(@MachineSizedSInt long v);
    @Property(selector = "HTTPCookieStorage")
    public native NSHTTPCookieStorage getHTTPCookieStorage();
    @Property(selector = "setHTTPCookieStorage:")
    public native void setHTTPCookieStorage(NSHTTPCookieStorage v);
    @Property(selector = "URLCredentialStorage")
    public native NSURLCredentialStorage getURLCredentialStorage();
    @Property(selector = "setURLCredentialStorage:")
    public native void setURLCredentialStorage(NSURLCredentialStorage v);
    @Property(selector = "URLCache")
    public native NSURLCache getURLCache();
    @Property(selector = "setURLCache:")
    public native void setURLCache(NSURLCache v);
    @Property(selector = "protocolClasses")
    public native NSArray<?> getProtocolClasses();
    @Property(selector = "setProtocolClasses:")
    public native void setProtocolClasses(NSArray<?> v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "defaultSessionConfiguration")
    public static native NSURLSessionConfiguration defaultSessionConfiguration();
    @Method(selector = "ephemeralSessionConfiguration")
    public static native NSURLSessionConfiguration ephemeralSessionConfiguration();
    @Method(selector = "backgroundSessionConfiguration:")
    public static native NSURLSessionConfiguration backgroundSessionConfiguration$(String identifier);
    /*</methods>*/
}
