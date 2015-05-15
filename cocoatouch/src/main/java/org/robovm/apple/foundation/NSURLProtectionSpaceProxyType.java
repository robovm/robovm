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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(NSURLProtectionSpaceProxyType.Marshaler.class)
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSURLProtectionSpaceProxyType/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static NSURLProtectionSpaceProxyType toObject(Class<NSURLProtectionSpaceProxyType> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSURLProtectionSpaceProxyType.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(NSURLProtectionSpaceProxyType o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(NSURLProtectionSpaceProxyType.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final NSURLProtectionSpaceProxyType HTTP = new NSURLProtectionSpaceProxyType("HTTPValue");
    public static final NSURLProtectionSpaceProxyType HTTPS = new NSURLProtectionSpaceProxyType("HTTPSValue");
    public static final NSURLProtectionSpaceProxyType FTP = new NSURLProtectionSpaceProxyType("FTPValue");
    public static final NSURLProtectionSpaceProxyType SOCKS = new NSURLProtectionSpaceProxyType("SOCKSValue");
    
    public static final NSURLProtectionSpaceProxyType[] values = new NSURLProtectionSpaceProxyType[] {HTTP, HTTPS, FTP, SOCKS};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private NSURLProtectionSpaceProxyType(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static NSURLProtectionSpaceProxyType valueOf(NSString value) {
        for (NSURLProtectionSpaceProxyType v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSURLProtectionSpaceProxyType/*</name>*/.class.getName());
    }
    /*<methods>*/
    @GlobalValue(symbol="NSURLProtectionSpaceHTTPProxy", optional=true)
    protected static native NSString HTTPValue();
    @GlobalValue(symbol="NSURLProtectionSpaceHTTPSProxy", optional=true)
    protected static native NSString HTTPSValue();
    @GlobalValue(symbol="NSURLProtectionSpaceFTPProxy", optional=true)
    protected static native NSString FTPValue();
    @GlobalValue(symbol="NSURLProtectionSpaceSOCKSProxy", optional=true)
    protected static native NSString SOCKSValue();
    /*</methods>*/
}
