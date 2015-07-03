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
@Marshaler(/*<name>*/CAMediaTimingFunctionName/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CAMediaTimingFunctionName/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CAMediaTimingFunctionName/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CAMediaTimingFunctionName toObject(Class<CAMediaTimingFunctionName> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CAMediaTimingFunctionName.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CAMediaTimingFunctionName o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<CAMediaTimingFunctionName> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CAMediaTimingFunctionName> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CAMediaTimingFunctionName.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CAMediaTimingFunctionName> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (CAMediaTimingFunctionName o : l) {
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
    public static final CAMediaTimingFunctionName Linear = new CAMediaTimingFunctionName("Linear");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CAMediaTimingFunctionName EaseIn = new CAMediaTimingFunctionName("EaseIn");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CAMediaTimingFunctionName EaseOut = new CAMediaTimingFunctionName("EaseOut");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CAMediaTimingFunctionName EaseInEaseOut = new CAMediaTimingFunctionName("EaseInEaseOut");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final CAMediaTimingFunctionName Default = new CAMediaTimingFunctionName("Default");
    /*</constants>*/
    
    private static /*<name>*/CAMediaTimingFunctionName/*</name>*/[] values = new /*<name>*/CAMediaTimingFunctionName/*</name>*/[] {/*<value_list>*/Linear, EaseIn, EaseOut, EaseInEaseOut, Default/*</value_list>*/};
    
    /*<name>*/CAMediaTimingFunctionName/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CAMediaTimingFunctionName/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/CAMediaTimingFunctionName/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CAMediaTimingFunctionName/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("QuartzCore") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCAMediaTimingFunctionLinear", optional=true)
        public static native NSString Linear();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCAMediaTimingFunctionEaseIn", optional=true)
        public static native NSString EaseIn();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCAMediaTimingFunctionEaseOut", optional=true)
        public static native NSString EaseOut();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCAMediaTimingFunctionEaseInEaseOut", optional=true)
        public static native NSString EaseInEaseOut();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kCAMediaTimingFunctionDefault", optional=true)
        public static native NSString Default();
        /*</values>*/
    }
}
