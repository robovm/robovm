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
@Marshaler(CAEmitterShape.Marshaler.class)
/*<annotations>*/@Library("QuartzCore")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CAEmitterShape/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static CAEmitterShape toObject(Class<CAEmitterShape> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CAEmitterShape.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CAEmitterShape o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CAEmitterShape.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CAEmitterShape Point = new CAEmitterShape("PointValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CAEmitterShape Line = new CAEmitterShape("LineValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CAEmitterShape Rectangle = new CAEmitterShape("RectangleValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CAEmitterShape Cuboid = new CAEmitterShape("CuboidValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CAEmitterShape Circle = new CAEmitterShape("CircleValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CAEmitterShape Sphere = new CAEmitterShape("SphereValue");
    
    private static CAEmitterShape[] values = new CAEmitterShape[] {Point, Line, Rectangle, Cuboid, Circle, Sphere};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private CAEmitterShape(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static CAEmitterShape valueOf(NSString value) {
        for (CAEmitterShape v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CAEmitterShape/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerPoint", optional=true)
    protected static native NSString PointValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerLine", optional=true)
    protected static native NSString LineValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerRectangle", optional=true)
    protected static native NSString RectangleValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerCuboid", optional=true)
    protected static native NSString CuboidValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerCircle", optional=true)
    protected static native NSString CircleValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerSphere", optional=true)
    protected static native NSString SphereValue();
    /*</methods>*/
}
