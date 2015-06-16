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
@Marshaler(/*<name>*/CAScrollMode/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CAScrollMode/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CAScrollMode/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CAScrollMode toObject(Class<CAScrollMode> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CAScrollMode.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CAScrollMode o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<CAScrollMode> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CAScrollMode> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CAScrollMode.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CAScrollMode> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (CAScrollMode o : l) {
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
    public static final CAScrollMode None = new CAScrollMode("None");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CAScrollMode Vertically = new CAScrollMode("Vertically");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CAScrollMode Horizontally = new CAScrollMode("Horizontally");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CAScrollMode Both = new CAScrollMode("Both");
    /*</constants>*/
    
    private static /*<name>*/CAScrollMode/*</name>*/[] values = new /*<name>*/CAScrollMode/*</name>*/[] {/*<value_list>*/None, Vertically, Horizontally, Both/*</value_list>*/};
    
    /*<name>*/CAScrollMode/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CAScrollMode/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/CAScrollMode/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CAScrollMode/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("QuartzCore") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCAScrollNone", optional=true)
        public static native NSString None();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCAScrollVertically", optional=true)
        public static native NSString Vertically();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCAScrollHorizontally", optional=true)
        public static native NSString Horizontally();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCAScrollBoth", optional=true)
        public static native NSString Both();
        /*</values>*/
    }
}
