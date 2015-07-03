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
package org.robovm.apple.uikit;

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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @StronglyLinked/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIFontWeight/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<Double>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/UIFontWeight/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static UIFontWeight toObject(Class<UIFontWeight> cls, long handle, long flags) {
            NSNumber o = (NSNumber) NSObject.Marshaler.toObject(NSNumber.class, handle, flags);
            if (o == null) {
                return null;
            }
            return UIFontWeight.valueOf(o.doubleValue());
        }
        @MarshalsPointer
        public static long toNative(UIFontWeight o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(NSNumber.valueOf(o.value()), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<UIFontWeight> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSNumber> o = (NSArray<NSNumber>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<UIFontWeight> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(UIFontWeight.valueOf(o.get(i).doubleValue()));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<UIFontWeight> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSNumber> array = new NSMutableArray<>();
            for (UIFontWeight o : l) {
                array.add(NSNumber.valueOf(o.value()));
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 8.2 and later.
     */
    public static final UIFontWeight UltraLight = new UIFontWeight("UltraLight");
    /**
     * @since Available in iOS 8.2 and later.
     */
    public static final UIFontWeight Thin = new UIFontWeight("Thin");
    /**
     * @since Available in iOS 8.2 and later.
     */
    public static final UIFontWeight Light = new UIFontWeight("Light");
    /**
     * @since Available in iOS 8.2 and later.
     */
    public static final UIFontWeight Regular = new UIFontWeight("Regular");
    /**
     * @since Available in iOS 8.2 and later.
     */
    public static final UIFontWeight Medium = new UIFontWeight("Medium");
    /**
     * @since Available in iOS 8.2 and later.
     */
    public static final UIFontWeight Semibold = new UIFontWeight("Semibold");
    /**
     * @since Available in iOS 8.2 and later.
     */
    public static final UIFontWeight Bold = new UIFontWeight("Bold");
    /**
     * @since Available in iOS 8.2 and later.
     */
    public static final UIFontWeight Heavy = new UIFontWeight("Heavy");
    /**
     * @since Available in iOS 8.2 and later.
     */
    public static final UIFontWeight Black = new UIFontWeight("Black");
    /*</constants>*/
    
    private static /*<name>*/UIFontWeight/*</name>*/[] values = new /*<name>*/UIFontWeight/*</name>*/[] {/*<value_list>*/UltraLight, Thin, Light, Regular, Medium, Semibold, Bold, Heavy, Black/*</value_list>*/};
    
    /*<name>*/UIFontWeight/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/UIFontWeight/*</name>*/ valueOf(/*<type>*/@MachineSizedFloat double/*</type>*/ value) {
        for (/*<name>*/UIFontWeight/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/UIFontWeight/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("UIKit") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 8.2 and later.
         */
        @GlobalValue(symbol="UIFontWeightUltraLight", optional=true)
        public static native @MachineSizedFloat double UltraLight();
        /**
         * @since Available in iOS 8.2 and later.
         */
        @GlobalValue(symbol="UIFontWeightThin", optional=true)
        public static native @MachineSizedFloat double Thin();
        /**
         * @since Available in iOS 8.2 and later.
         */
        @GlobalValue(symbol="UIFontWeightLight", optional=true)
        public static native @MachineSizedFloat double Light();
        /**
         * @since Available in iOS 8.2 and later.
         */
        @GlobalValue(symbol="UIFontWeightRegular", optional=true)
        public static native @MachineSizedFloat double Regular();
        /**
         * @since Available in iOS 8.2 and later.
         */
        @GlobalValue(symbol="UIFontWeightMedium", optional=true)
        public static native @MachineSizedFloat double Medium();
        /**
         * @since Available in iOS 8.2 and later.
         */
        @GlobalValue(symbol="UIFontWeightSemibold", optional=true)
        public static native @MachineSizedFloat double Semibold();
        /**
         * @since Available in iOS 8.2 and later.
         */
        @GlobalValue(symbol="UIFontWeightBold", optional=true)
        public static native @MachineSizedFloat double Bold();
        /**
         * @since Available in iOS 8.2 and later.
         */
        @GlobalValue(symbol="UIFontWeightHeavy", optional=true)
        public static native @MachineSizedFloat double Heavy();
        /**
         * @since Available in iOS 8.2 and later.
         */
        @GlobalValue(symbol="UIFontWeightBlack", optional=true)
        public static native @MachineSizedFloat double Black();
        /*</values>*/
    }
}
