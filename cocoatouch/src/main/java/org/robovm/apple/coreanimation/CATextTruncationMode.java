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
@Marshaler(/*<name>*/CATextTruncationMode/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CATextTruncationMode/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CATextTruncationMode/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CATextTruncationMode toObject(Class<CATextTruncationMode> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CATextTruncationMode.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CATextTruncationMode o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<CATextTruncationMode> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CATextTruncationMode> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CATextTruncationMode.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CATextTruncationMode> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (CATextTruncationMode o : l) {
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
    public static final CATextTruncationMode None = new CATextTruncationMode("None");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CATextTruncationMode Start = new CATextTruncationMode("Start");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CATextTruncationMode End = new CATextTruncationMode("End");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CATextTruncationMode Middle = new CATextTruncationMode("Middle");
    /*</constants>*/
    
    private static /*<name>*/CATextTruncationMode/*</name>*/[] values = new /*<name>*/CATextTruncationMode/*</name>*/[] {/*<value_list>*/None, Start, End, Middle/*</value_list>*/};
    
    /*<name>*/CATextTruncationMode/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CATextTruncationMode/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/CATextTruncationMode/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CATextTruncationMode/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("QuartzCore") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCATruncationNone", optional=true)
        public static native NSString None();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCATruncationStart", optional=true)
        public static native NSString Start();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCATruncationEnd", optional=true)
        public static native NSString End();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCATruncationMiddle", optional=true)
        public static native NSString Middle();
        /*</values>*/
    }
}
