/*
 * Copyright (C) 2014 Trillian Mobile AB
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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.imageio.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(CIFilterAttributeType.Marshaler.class)
/*<annotations>*/@Library("CoreImage")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CIFilterAttributeType/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
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

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CIFilterAttributeType.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final CIFilterAttributeType Time = new CIFilterAttributeType("TimeValue");
    public static final CIFilterAttributeType Scalar = new CIFilterAttributeType("ScalarValue");
    public static final CIFilterAttributeType Distance = new CIFilterAttributeType("DistanceValue");
    public static final CIFilterAttributeType Angle = new CIFilterAttributeType("AngleValue");
    public static final CIFilterAttributeType Boolean = new CIFilterAttributeType("BooleanValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CIFilterAttributeType Integer = new CIFilterAttributeType("IntegerValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CIFilterAttributeType Count = new CIFilterAttributeType("CountValue");
    public static final CIFilterAttributeType Position = new CIFilterAttributeType("PositionValue");
    public static final CIFilterAttributeType Offset = new CIFilterAttributeType("OffsetValue");
    public static final CIFilterAttributeType Position3 = new CIFilterAttributeType("Position3Value");
    public static final CIFilterAttributeType Rectangle = new CIFilterAttributeType("RectangleValue");
    public static final CIFilterAttributeType Color = new CIFilterAttributeType("ColorValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CIFilterAttributeType Image = new CIFilterAttributeType("ImageValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CIFilterAttributeType Transform = new CIFilterAttributeType("TransformValue");
    
    private static CIFilterAttributeType[] values = new CIFilterAttributeType[] {Time, Scalar, Distance, Angle, Boolean, 
        Integer, Count, Position, Offset, Position3, Rectangle, Color, Image, Transform};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private CIFilterAttributeType(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static CIFilterAttributeType valueOf(NSString value) {
        for (CIFilterAttributeType v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CIFilterAttributeType/*</name>*/.class.getName());
    }
    /*<methods>*/
    @GlobalValue(symbol="kCIAttributeTypeTime", optional=true)
    protected static native NSString TimeValue();
    @GlobalValue(symbol="kCIAttributeTypeScalar", optional=true)
    protected static native NSString ScalarValue();
    @GlobalValue(symbol="kCIAttributeTypeDistance", optional=true)
    protected static native NSString DistanceValue();
    @GlobalValue(symbol="kCIAttributeTypeAngle", optional=true)
    protected static native NSString AngleValue();
    @GlobalValue(symbol="kCIAttributeTypeBoolean", optional=true)
    protected static native NSString BooleanValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCIAttributeTypeInteger", optional=true)
    protected static native NSString IntegerValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCIAttributeTypeCount", optional=true)
    protected static native NSString CountValue();
    @GlobalValue(symbol="kCIAttributeTypePosition", optional=true)
    protected static native NSString PositionValue();
    @GlobalValue(symbol="kCIAttributeTypeOffset", optional=true)
    protected static native NSString OffsetValue();
    @GlobalValue(symbol="kCIAttributeTypePosition3", optional=true)
    protected static native NSString Position3Value();
    @GlobalValue(symbol="kCIAttributeTypeRectangle", optional=true)
    protected static native NSString RectangleValue();
    @GlobalValue(symbol="kCIAttributeTypeColor", optional=true)
    protected static native NSString ColorValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCIAttributeTypeImage", optional=true)
    protected static native NSString ImageValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCIAttributeTypeTransform", optional=true)
    protected static native NSString TransformValue();
    /*</methods>*/
}
