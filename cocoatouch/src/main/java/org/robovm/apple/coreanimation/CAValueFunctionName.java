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
@Marshaler(/*<name>*/CAValueFunctionName/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CAValueFunctionName/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CAValueFunctionName/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CAValueFunctionName toObject(Class<CAValueFunctionName> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CAValueFunctionName.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CAValueFunctionName o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<CAValueFunctionName> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CAValueFunctionName> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CAValueFunctionName.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CAValueFunctionName> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (CAValueFunctionName o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final CAValueFunctionName RotateX = new CAValueFunctionName("RotateX");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final CAValueFunctionName RotateY = new CAValueFunctionName("RotateY");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final CAValueFunctionName RotateZ = new CAValueFunctionName("RotateZ");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final CAValueFunctionName Scale = new CAValueFunctionName("Scale");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final CAValueFunctionName ScaleX = new CAValueFunctionName("ScaleX");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final CAValueFunctionName ScaleY = new CAValueFunctionName("ScaleY");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final CAValueFunctionName ScaleZ = new CAValueFunctionName("ScaleZ");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final CAValueFunctionName Translate = new CAValueFunctionName("Translate");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final CAValueFunctionName TranslateX = new CAValueFunctionName("TranslateX");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final CAValueFunctionName TranslateY = new CAValueFunctionName("TranslateY");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final CAValueFunctionName TranslateZ = new CAValueFunctionName("TranslateZ");
    /*</constants>*/
    
    private static /*<name>*/CAValueFunctionName/*</name>*/[] values = new /*<name>*/CAValueFunctionName/*</name>*/[] {/*<value_list>*/RotateX, RotateY, RotateZ, Scale, ScaleX, ScaleY, ScaleZ, Translate, TranslateX, TranslateY, TranslateZ/*</value_list>*/};
    
    /*<name>*/CAValueFunctionName/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CAValueFunctionName/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/CAValueFunctionName/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CAValueFunctionName/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("QuartzCore") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kCAValueFunctionRotateX", optional=true)
        public static native NSString RotateX();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kCAValueFunctionRotateY", optional=true)
        public static native NSString RotateY();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kCAValueFunctionRotateZ", optional=true)
        public static native NSString RotateZ();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kCAValueFunctionScale", optional=true)
        public static native NSString Scale();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kCAValueFunctionScaleX", optional=true)
        public static native NSString ScaleX();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kCAValueFunctionScaleY", optional=true)
        public static native NSString ScaleY();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kCAValueFunctionScaleZ", optional=true)
        public static native NSString ScaleZ();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kCAValueFunctionTranslate", optional=true)
        public static native NSString Translate();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kCAValueFunctionTranslateX", optional=true)
        public static native NSString TranslateX();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kCAValueFunctionTranslateY", optional=true)
        public static native NSString TranslateY();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kCAValueFunctionTranslateZ", optional=true)
        public static native NSString TranslateZ();
        /*</values>*/
    }
}
