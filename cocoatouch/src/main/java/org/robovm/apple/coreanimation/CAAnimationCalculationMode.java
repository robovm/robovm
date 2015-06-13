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
@Marshaler(/*<name>*/CAAnimationCalculationMode/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CAAnimationCalculationMode/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CAAnimationCalculationMode/*</name>*/.class); }

    /*<marshalers>*/
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
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<CAAnimationCalculationMode> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CAAnimationCalculationMode> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CAAnimationCalculationMode.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CAAnimationCalculationMode> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (CAAnimationCalculationMode o : l) {
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
    public static final CAAnimationCalculationMode Linear = new CAAnimationCalculationMode("Linear");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CAAnimationCalculationMode Discrete = new CAAnimationCalculationMode("Discrete");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CAAnimationCalculationMode Paced = new CAAnimationCalculationMode("Paced");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CAAnimationCalculationMode Cubic = new CAAnimationCalculationMode("Cubic");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CAAnimationCalculationMode CubicPaced = new CAAnimationCalculationMode("CubicPaced");
    /*</constants>*/
    
    private static /*<name>*/CAAnimationCalculationMode/*</name>*/[] values = new /*<name>*/CAAnimationCalculationMode/*</name>*/[] {/*<value_list>*/Linear, Discrete, Paced, Cubic, CubicPaced/*</value_list>*/};
    
    /*<name>*/CAAnimationCalculationMode/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CAAnimationCalculationMode/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/CAAnimationCalculationMode/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CAAnimationCalculationMode/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("QuartzCore") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCAAnimationLinear", optional=true)
        public static native NSString Linear();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCAAnimationDiscrete", optional=true)
        public static native NSString Discrete();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCAAnimationPaced", optional=true)
        public static native NSString Paced();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCAAnimationCubic", optional=true)
        public static native NSString Cubic();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCAAnimationCubicPaced", optional=true)
        public static native NSString CubicPaced();
        /*</values>*/
    }
}
