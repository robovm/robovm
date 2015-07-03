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
@Marshaler(/*<name>*/CATransitionSubType/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CATransitionSubType/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CATransitionSubType/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CATransitionSubType toObject(Class<CATransitionSubType> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CATransitionSubType.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CATransitionSubType o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<CATransitionSubType> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CATransitionSubType> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CATransitionSubType.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CATransitionSubType> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (CATransitionSubType o : l) {
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
    public static final CATransitionSubType FromRight = new CATransitionSubType("FromRight");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CATransitionSubType FromLeft = new CATransitionSubType("FromLeft");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CATransitionSubType FromTop = new CATransitionSubType("FromTop");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CATransitionSubType FromBottom = new CATransitionSubType("FromBottom");
    /*</constants>*/
    
    private static /*<name>*/CATransitionSubType/*</name>*/[] values = new /*<name>*/CATransitionSubType/*</name>*/[] {/*<value_list>*/FromRight, FromLeft, FromTop, FromBottom/*</value_list>*/};
    
    /*<name>*/CATransitionSubType/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CATransitionSubType/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/CATransitionSubType/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CATransitionSubType/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("QuartzCore") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCATransitionFromRight", optional=true)
        public static native NSString FromRight();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCATransitionFromLeft", optional=true)
        public static native NSString FromLeft();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCATransitionFromTop", optional=true)
        public static native NSString FromTop();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCATransitionFromBottom", optional=true)
        public static native NSString FromBottom();
        /*</values>*/
    }
}
