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
@Marshaler(NSStreamProperty.Marshaler.class)
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSStreamProperty/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static NSStreamProperty toObject(Class<NSStreamProperty> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSStreamProperty.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(NSStreamProperty o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(NSStreamProperty.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final NSStreamProperty SocketSecurityLevel = new NSStreamProperty("SocketSecurityLevelValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final NSStreamProperty SOCKSProxyConfiguration = new NSStreamProperty("SOCKSProxyConfigurationValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final NSStreamProperty DataWrittenToMemoryStream = new NSStreamProperty("DataWrittenToMemoryStreamValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final NSStreamProperty FileCurrentOffset = new NSStreamProperty("FileCurrentOffsetValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSStreamProperty NetworkServiceType = new NSStreamProperty("NetworkServiceTypeValue");
    
    private static NSStreamProperty[] values = new NSStreamProperty[] {SocketSecurityLevel, SOCKSProxyConfiguration, DataWrittenToMemoryStream, FileCurrentOffset, 
        NetworkServiceType};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private NSStreamProperty(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static NSStreamProperty valueOf(NSString value) {
        for (NSStreamProperty v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSStreamProperty/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSocketSecurityLevelKey", optional=true)
    protected static native NSString SocketSecurityLevelValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSOCKSProxyConfigurationKey", optional=true)
    protected static native NSString SOCKSProxyConfigurationValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamDataWrittenToMemoryStreamKey", optional=true)
    protected static native NSString DataWrittenToMemoryStreamValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamFileCurrentOffsetKey", optional=true)
    protected static native NSString FileCurrentOffsetValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSStreamNetworkServiceType", optional=true)
    protected static native NSString NetworkServiceTypeValue();
    /*</methods>*/
}
