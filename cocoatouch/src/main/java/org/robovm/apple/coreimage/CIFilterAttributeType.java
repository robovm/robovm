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
package org.robovm.apple.coreimage;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.imageio.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreImage") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/CIFilterAttributeType/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CIFilterAttributeType/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CIFilterAttributeType/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CIFilterAttributeType toObject(Class<CIFilterAttributeType> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CIFilterAttributeType.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CIFilterAttributeType o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<CIFilterAttributeType> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CIFilterAttributeType> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CIFilterAttributeType.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CIFilterAttributeType> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (CIFilterAttributeType o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    public static final CIFilterAttributeType Time = new CIFilterAttributeType("Time");
    public static final CIFilterAttributeType Scalar = new CIFilterAttributeType("Scalar");
    public static final CIFilterAttributeType Distance = new CIFilterAttributeType("Distance");
    public static final CIFilterAttributeType Angle = new CIFilterAttributeType("Angle");
    public static final CIFilterAttributeType Boolean = new CIFilterAttributeType("Boolean");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CIFilterAttributeType Integer = new CIFilterAttributeType("Integer");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CIFilterAttributeType Count = new CIFilterAttributeType("Count");
    public static final CIFilterAttributeType Position = new CIFilterAttributeType("Position");
    public static final CIFilterAttributeType Offset = new CIFilterAttributeType("Offset");
    public static final CIFilterAttributeType Position3 = new CIFilterAttributeType("Position3");
    public static final CIFilterAttributeType Rectangle = new CIFilterAttributeType("Rectangle");
    public static final CIFilterAttributeType Color = new CIFilterAttributeType("Color");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CIFilterAttributeType Image = new CIFilterAttributeType("Image");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CIFilterAttributeType Transform = new CIFilterAttributeType("Transform");
    /*</constants>*/
    
    private static /*<name>*/CIFilterAttributeType/*</name>*/[] values = new /*<name>*/CIFilterAttributeType/*</name>*/[] {/*<value_list>*/Time, Scalar, Distance, Angle, Boolean, Integer, Count, Position, Offset, Position3, Rectangle, Color, Image, Transform/*</value_list>*/};
    
    /*<name>*/CIFilterAttributeType/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CIFilterAttributeType/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/CIFilterAttributeType/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CIFilterAttributeType/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreImage") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        @GlobalValue(symbol="kCIAttributeTypeTime", optional=true)
        public static native NSString Time();
        @GlobalValue(symbol="kCIAttributeTypeScalar", optional=true)
        public static native NSString Scalar();
        @GlobalValue(symbol="kCIAttributeTypeDistance", optional=true)
        public static native NSString Distance();
        @GlobalValue(symbol="kCIAttributeTypeAngle", optional=true)
        public static native NSString Angle();
        @GlobalValue(symbol="kCIAttributeTypeBoolean", optional=true)
        public static native NSString Boolean();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCIAttributeTypeInteger", optional=true)
        public static native NSString Integer();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCIAttributeTypeCount", optional=true)
        public static native NSString Count();
        @GlobalValue(symbol="kCIAttributeTypePosition", optional=true)
        public static native NSString Position();
        @GlobalValue(symbol="kCIAttributeTypeOffset", optional=true)
        public static native NSString Offset();
        @GlobalValue(symbol="kCIAttributeTypePosition3", optional=true)
        public static native NSString Position3();
        @GlobalValue(symbol="kCIAttributeTypeRectangle", optional=true)
        public static native NSString Rectangle();
        @GlobalValue(symbol="kCIAttributeTypeColor", optional=true)
        public static native NSString Color();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCIAttributeTypeImage", optional=true)
        public static native NSString Image();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCIAttributeTypeTransform", optional=true)
        public static native NSString Transform();
        /*</values>*/
    }
}
