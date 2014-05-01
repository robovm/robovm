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

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSError/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSErrorPtr extends Ptr<NSError, NSErrorPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSError.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSError() {}
    protected NSError(SkipInit skipInit) { super(skipInit); }
    public NSError(String domain, @MachineSizedSInt long code, NSDictionary<?, ?> dict) { super((SkipInit) null); initObject(initWithDomain$code$userInfo$(domain, code, dict)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="NSCocoaErrorDomain", optional=true)
    public static native String ErrorDomainCocoa();
    @GlobalValue(symbol="NSPOSIXErrorDomain", optional=true)
    public static native String ErrorDomainPOSIX();
    @GlobalValue(symbol="NSOSStatusErrorDomain", optional=true)
    public static native String ErrorDomainOSStatus();
    @GlobalValue(symbol="NSMachErrorDomain", optional=true)
    public static native String ErrorDomainMach();
    @GlobalValue(symbol="NSUnderlyingErrorKey", optional=true)
    public static native NSString KeyUnderlyingError();
    @GlobalValue(symbol="NSLocalizedDescriptionKey", optional=true)
    public static native NSString KeyLocalizedDescription();
    @GlobalValue(symbol="NSLocalizedFailureReasonErrorKey", optional=true)
    public static native NSString KeyLocalizedFailureReasonError();
    @GlobalValue(symbol="NSLocalizedRecoverySuggestionErrorKey", optional=true)
    public static native NSString KeyLocalizedRecoverySuggestionError();
    @GlobalValue(symbol="NSLocalizedRecoveryOptionsErrorKey", optional=true)
    public static native NSString KeyLocalizedRecoveryOptionsError();
    @GlobalValue(symbol="NSRecoveryAttempterErrorKey", optional=true)
    public static native NSString KeyRecoveryAttempterError();
    @GlobalValue(symbol="NSHelpAnchorErrorKey", optional=true)
    public static native NSString KeyHelpAnchorError();
    @GlobalValue(symbol="NSStringEncodingErrorKey", optional=true)
    public static native NSString KeyStringEncodingError();
    @GlobalValue(symbol="NSURLErrorKey", optional=true)
    public static native NSString KeyURLError();
    @GlobalValue(symbol="NSFilePathErrorKey", optional=true)
    public static native NSString KeyFilePathError();
    @GlobalValue(symbol="NSURLErrorFailingURLErrorKey", optional=true)
    public static native NSString KeyURLErrorFailingURLError();
    @GlobalValue(symbol="NSURLErrorFailingURLStringErrorKey", optional=true)
    public static native NSString KeyURLErrorFailingURLStringError();
    @GlobalValue(symbol="NSErrorFailingURLStringKey", optional=true)
    public static native NSString KeyErrorFailingURLString();
    @GlobalValue(symbol="NSURLErrorFailingURLPeerTrustErrorKey", optional=true)
    public static native NSString KeyURLErrorFailingURLPeerTrustError();
    
    @Method(selector = "initWithDomain:code:userInfo:")
    protected native @Pointer long initWithDomain$code$userInfo$(String domain, @MachineSizedSInt long code, NSDictionary<?, ?> dict);
    @Method(selector = "domain")
    public native String domain();
    @Method(selector = "code")
    public native @MachineSizedSInt long code();
    @Method(selector = "userInfo")
    public native NSDictionary<?, ?> userInfo();
    @Method(selector = "localizedDescription")
    public native String localizedDescription();
    @Method(selector = "localizedFailureReason")
    public native String localizedFailureReason();
    @Method(selector = "localizedRecoverySuggestion")
    public native String localizedRecoverySuggestion();
    @Method(selector = "localizedRecoveryOptions")
    public native NSArray<?> localizedRecoveryOptions();
    @Method(selector = "recoveryAttempter")
    public native NSObject recoveryAttempter();
    @Method(selector = "helpAnchor")
    public native String helpAnchor();
    @Method(selector = "errorWithDomain:code:userInfo:")
    public static native NSObject errorWithDomain$code$userInfo$(String domain, @MachineSizedSInt long code, NSDictionary<?, ?> dict);
    /*</methods>*/
}
