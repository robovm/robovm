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
@Marshaler(/*<name>*/CATextAlignmentMode/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CATextAlignmentMode/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CATextAlignmentMode/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CATextAlignmentMode toObject(Class<CATextAlignmentMode> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CATextAlignmentMode.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CATextAlignmentMode o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<CATextAlignmentMode> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CATextAlignmentMode> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CATextAlignmentMode.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CATextAlignmentMode> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (CATextAlignmentMode o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CATextAlignmentMode Natural = new CATextAlignmentMode("Natural");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CATextAlignmentMode Left = new CATextAlignmentMode("Left");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CATextAlignmentMode Right = new CATextAlignmentMode("Right");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CATextAlignmentMode Center = new CATextAlignmentMode("Center");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CATextAlignmentMode Justified = new CATextAlignmentMode("Justified");
    /*</constants>*/
    
    private static /*<name>*/CATextAlignmentMode/*</name>*/[] values = new /*<name>*/CATextAlignmentMode/*</name>*/[] {/*<value_list>*/Natural, Left, Right, Center, Justified/*</value_list>*/};
    
    /*<name>*/CATextAlignmentMode/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CATextAlignmentMode/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/CATextAlignmentMode/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CATextAlignmentMode/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("QuartzCore") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCAAlignmentNatural", optional=true)
        public static native NSString Natural();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCAAlignmentLeft", optional=true)
        public static native NSString Left();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCAAlignmentRight", optional=true)
        public static native NSString Right();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCAAlignmentCenter", optional=true)
        public static native NSString Center();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCAAlignmentJustified", optional=true)
        public static native NSString Justified();
        /*</values>*/
    }
}
