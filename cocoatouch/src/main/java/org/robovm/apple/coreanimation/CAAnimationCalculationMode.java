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
@Marshaler(CAAnimationCalculationMode.Marshaler.class)
/*<annotations>*/@Library("QuartzCore")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CAAnimationCalculationMode/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @MarshalsPointer
        public static CAAnimationCalculationMode toObject(Class<CAAnimationCalculationMode> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CAAnimationCalculationMode.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CAAnimationCalculationMode o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CAAnimationCalculationMode.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CAAnimationCalculationMode Linear = new CAAnimationCalculationMode("LinearValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CAAnimationCalculationMode Discrete = new CAAnimationCalculationMode("DiscreteValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CAAnimationCalculationMode Paced = new CAAnimationCalculationMode("PacedValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CAAnimationCalculationMode Cubic = new CAAnimationCalculationMode("CubicValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CAAnimationCalculationMode CubicPaced = new CAAnimationCalculationMode("CubicPacedValue");
    
    private static CAAnimationCalculationMode[] values = new CAAnimationCalculationMode[] {Linear, Discrete, Paced, Cubic, CubicPaced};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private CAAnimationCalculationMode(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static CAAnimationCalculationMode valueOf(NSString value) {
        for (CAAnimationCalculationMode v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CAAnimationCalculationMode/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAAnimationLinear", optional=true)
    protected static native NSString LinearValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAAnimationDiscrete", optional=true)
    protected static native NSString DiscreteValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAAnimationPaced", optional=true)
    protected static native NSString PacedValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCAAnimationCubic", optional=true)
    protected static native NSString CubicValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCAAnimationCubicPaced", optional=true)
    protected static native NSString CubicPacedValue();
    /*</methods>*/
}
