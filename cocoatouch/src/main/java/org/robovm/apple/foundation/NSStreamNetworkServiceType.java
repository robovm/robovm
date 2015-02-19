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
@Marshaler(NSStreamNetworkServiceType.Marshaler.class)
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSStreamNetworkServiceType/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static NSStreamNetworkServiceType toObject(Class<NSStreamNetworkServiceType> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSStreamNetworkServiceType.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(NSStreamNetworkServiceType o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(NSStreamNetworkServiceType.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSStreamNetworkServiceType VoIP = new NSStreamNetworkServiceType("VoIPValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSStreamNetworkServiceType Video = new NSStreamNetworkServiceType("VideoValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSStreamNetworkServiceType Background = new NSStreamNetworkServiceType("BackgroundValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSStreamNetworkServiceType Voice = new NSStreamNetworkServiceType("VoiceValue");
    
    private static NSStreamNetworkServiceType[] values = new NSStreamNetworkServiceType[] {VoIP, Video, Background, Voice};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private NSStreamNetworkServiceType(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static NSStreamNetworkServiceType valueOf(NSString value) {
        for (NSStreamNetworkServiceType v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSStreamNetworkServiceType/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSStreamNetworkServiceTypeVoIP", optional=true)
    protected static native NSString VoIPValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSStreamNetworkServiceTypeVideo", optional=true)
    protected static native NSString VideoValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSStreamNetworkServiceTypeBackground", optional=true)
    protected static native NSString BackgroundValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSStreamNetworkServiceTypeVoice", optional=true)
    protected static native NSString VoiceValue();
    /*</methods>*/
}
