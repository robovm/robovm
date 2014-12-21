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
package org.robovm.apple.coreanimation;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.metal.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(CAEmitterRenderMode.Marshaler.class)
/*<annotations>*/@Library("QuartzCore")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CAEmitterRenderMode/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static CAEmitterRenderMode toObject(Class<CAEmitterRenderMode> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CAEmitterRenderMode.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CAEmitterRenderMode o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CAEmitterRenderMode.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CAEmitterRenderMode Unordered = new CAEmitterRenderMode("UnorderedValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CAEmitterRenderMode OldestFirst = new CAEmitterRenderMode("OldestFirstValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CAEmitterRenderMode OldestLast = new CAEmitterRenderMode("OldestLastValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CAEmitterRenderMode BackToFront = new CAEmitterRenderMode("BackToFrontValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CAEmitterRenderMode Additive = new CAEmitterRenderMode("AdditiveValue");
    
    private static CAEmitterRenderMode[] values = new CAEmitterRenderMode[] {Unordered, OldestFirst, OldestLast, 
        BackToFront, Additive};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private CAEmitterRenderMode(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static CAEmitterRenderMode valueOf(NSString value) {
        for (CAEmitterRenderMode v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CAEmitterRenderMode/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerUnordered", optional=true)
    protected static native NSString UnorderedValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerOldestFirst", optional=true)
    protected static native NSString OldestFirstValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerOldestLast", optional=true)
    protected static native NSString OldestLastValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerBackToFront", optional=true)
    protected static native NSString BackToFrontValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerAdditive", optional=true)
    protected static native NSString AdditiveValue();
    /*</methods>*/
}
