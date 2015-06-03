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
package org.robovm.apple.coreanimation;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.metal.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(CAGravity.Marshaler.class)
/*<annotations>*/@Library("QuartzCore")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CAGravity/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static CAGravity toObject(Class<CAGravity> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CAGravity.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CAGravity o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CAGravity.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CAGravity Center = new CAGravity("CenterValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CAGravity Top = new CAGravity("TopValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CAGravity Bottom = new CAGravity("BottomValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CAGravity Left = new CAGravity("LeftValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CAGravity Right = new CAGravity("RightValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CAGravity TopLeft = new CAGravity("TopLeftValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CAGravity TopRight = new CAGravity("TopRightValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CAGravity BottomLeft = new CAGravity("BottomLeftValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CAGravity BottomRight = new CAGravity("BottomRightValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CAGravity Resize = new CAGravity("ResizeValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CAGravity ResizeAspect = new CAGravity("ResizeAspectValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CAGravity ResizeAspectFill = new CAGravity("ResizeAspectFillValue");
    
    private static CAGravity[] values = new CAGravity[] {Center, Top, Bottom, Left, Right, TopLeft, TopRight, 
        BottomLeft, BottomRight, Resize, ResizeAspect, ResizeAspectFill};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private CAGravity(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static CAGravity valueOf(NSString value) {
        for (CAGravity v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CAGravity/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAGravityCenter", optional=true)
    protected static native NSString CenterValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAGravityTop", optional=true)
    protected static native NSString TopValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAGravityBottom", optional=true)
    protected static native NSString BottomValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAGravityLeft", optional=true)
    protected static native NSString LeftValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAGravityRight", optional=true)
    protected static native NSString RightValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAGravityTopLeft", optional=true)
    protected static native NSString TopLeftValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAGravityTopRight", optional=true)
    protected static native NSString TopRightValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAGravityBottomLeft", optional=true)
    protected static native NSString BottomLeftValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAGravityBottomRight", optional=true)
    protected static native NSString BottomRightValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAGravityResize", optional=true)
    protected static native NSString ResizeValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAGravityResizeAspect", optional=true)
    protected static native NSString ResizeAspectValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAGravityResizeAspectFill", optional=true)
    protected static native NSString ResizeAspectFillValue();
    /*</methods>*/
}
