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
@Marshaler(NSURLProtectionSpaceProtocol.Marshaler.class)
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSURLProtectionSpaceProtocol/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static NSURLProtectionSpaceProtocol toObject(Class<NSURLProtectionSpaceProtocol> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSURLProtectionSpaceProtocol.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(NSURLProtectionSpaceProtocol o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(NSURLProtectionSpaceProtocol.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final NSURLProtectionSpaceProtocol HTTP = new NSURLProtectionSpaceProtocol("HTTPValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final NSURLProtectionSpaceProtocol HTTPS = new NSURLProtectionSpaceProtocol("HTTPSValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final NSURLProtectionSpaceProtocol FTP = new NSURLProtectionSpaceProtocol("FTPValue");
    
    private static NSURLProtectionSpaceProtocol[] values = new NSURLProtectionSpaceProtocol[] {HTTP, HTTPS, FTP};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private NSURLProtectionSpaceProtocol(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static NSURLProtectionSpaceProtocol valueOf(NSString value) {
        for (NSURLProtectionSpaceProtocol v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSURLProtectionSpaceProtocol/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSURLProtectionSpaceHTTP", optional=true)
    protected static native NSString HTTPValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSURLProtectionSpaceHTTPS", optional=true)
    protected static native NSString HTTPSValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSURLProtectionSpaceFTP", optional=true)
    protected static native NSString FTPValue();
    /*</methods>*/
}
