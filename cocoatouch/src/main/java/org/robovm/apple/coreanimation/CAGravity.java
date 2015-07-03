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
/*<annotations>*/@Library("QuartzCore") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/CAGravity/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CAGravity/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CAGravity/*</name>*/.class); }

    /*<marshalers>*/
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
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<CAGravity> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CAGravity> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CAGravity.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CAGravity> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (CAGravity o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CAGravity Center = new CAGravity("Center");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CAGravity Top = new CAGravity("Top");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CAGravity Bottom = new CAGravity("Bottom");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CAGravity Left = new CAGravity("Left");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CAGravity Right = new CAGravity("Right");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CAGravity TopLeft = new CAGravity("TopLeft");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CAGravity TopRight = new CAGravity("TopRight");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CAGravity BottomLeft = new CAGravity("BottomLeft");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CAGravity BottomRight = new CAGravity("BottomRight");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CAGravity Resize = new CAGravity("Resize");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CAGravity ResizeAspect = new CAGravity("ResizeAspect");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CAGravity ResizeAspectFill = new CAGravity("ResizeAspectFill");
    /*</constants>*/
    
    private static /*<name>*/CAGravity/*</name>*/[] values = new /*<name>*/CAGravity/*</name>*/[] {/*<value_list>*/Center, Top, Bottom, Left, Right, TopLeft, TopRight, BottomLeft, BottomRight, Resize, ResizeAspect, ResizeAspectFill/*</value_list>*/};
    
    /*<name>*/CAGravity/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CAGravity/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/CAGravity/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CAGravity/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("QuartzCore") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCAGravityCenter", optional=true)
        public static native NSString Center();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCAGravityTop", optional=true)
        public static native NSString Top();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCAGravityBottom", optional=true)
        public static native NSString Bottom();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCAGravityLeft", optional=true)
        public static native NSString Left();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCAGravityRight", optional=true)
        public static native NSString Right();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCAGravityTopLeft", optional=true)
        public static native NSString TopLeft();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCAGravityTopRight", optional=true)
        public static native NSString TopRight();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCAGravityBottomLeft", optional=true)
        public static native NSString BottomLeft();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCAGravityBottomRight", optional=true)
        public static native NSString BottomRight();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCAGravityResize", optional=true)
        public static native NSString Resize();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCAGravityResizeAspect", optional=true)
        public static native NSString ResizeAspect();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCAGravityResizeAspectFill", optional=true)
        public static native NSString ResizeAspectFill();
        /*</values>*/
    }
}
