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
@Marshaler(/*<name>*/CAEmitterMode/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CAEmitterMode/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CAEmitterMode/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CAEmitterMode toObject(Class<CAEmitterMode> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CAEmitterMode.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CAEmitterMode o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<CAEmitterMode> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CAEmitterMode> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CAEmitterMode.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CAEmitterMode> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (CAEmitterMode o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CAEmitterMode Points = new CAEmitterMode("Points");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CAEmitterMode Outline = new CAEmitterMode("Outline");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CAEmitterMode Surface = new CAEmitterMode("Surface");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CAEmitterMode Volume = new CAEmitterMode("Volume");
    /*</constants>*/
    
    private static /*<name>*/CAEmitterMode/*</name>*/[] values = new /*<name>*/CAEmitterMode/*</name>*/[] {/*<value_list>*/Points, Outline, Surface, Volume/*</value_list>*/};
    
    /*<name>*/CAEmitterMode/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CAEmitterMode/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/CAEmitterMode/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CAEmitterMode/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("QuartzCore") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCAEmitterLayerPoints", optional=true)
        public static native NSString Points();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCAEmitterLayerOutline", optional=true)
        public static native NSString Outline();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCAEmitterLayerSurface", optional=true)
        public static native NSString Surface();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCAEmitterLayerVolume", optional=true)
        public static native NSString Volume();
        /*</values>*/
    }
}
