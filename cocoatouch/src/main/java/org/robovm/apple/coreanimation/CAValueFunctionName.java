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
package org.robovm.apple.coreanimation;

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
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.metal.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(CAValueFunctionName.Marshaler.class)
/*<annotations>*/@Library("QuartzCore")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CAValueFunctionName/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

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
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CAValueFunctionName.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final CAValueFunctionName RotateX = new CAValueFunctionName("RotateXValue");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final CAValueFunctionName RotateY = new CAValueFunctionName("RotateYValue");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final CAValueFunctionName RotateZ = new CAValueFunctionName("RotateZValue");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final CAValueFunctionName Scale = new CAValueFunctionName("ScaleValue");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final CAValueFunctionName ScaleX = new CAValueFunctionName("ScaleXValue");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final CAValueFunctionName ScaleY = new CAValueFunctionName("ScaleYValue");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final CAValueFunctionName ScaleZ = new CAValueFunctionName("ScaleZValue");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final CAValueFunctionName Translate = new CAValueFunctionName("TranslateValue");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final CAValueFunctionName TranslateX = new CAValueFunctionName("TranslateXValue");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final CAValueFunctionName TranslateY = new CAValueFunctionName("TranslateYValue");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final CAValueFunctionName TranslateZ = new CAValueFunctionName("TranslateZValue");
    
    private static CAValueFunctionName[] values = new CAValueFunctionName[] {RotateX, RotateY, RotateZ, 
        Scale, ScaleX, ScaleY, ScaleZ, Translate, TranslateX, TranslateY, TranslateZ};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private CAValueFunctionName(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static CAValueFunctionName valueOf(NSString value) {
        for (CAValueFunctionName v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CAValueFunctionName/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAValueFunctionRotateX", optional=true)
    protected static native NSString RotateXValue();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAValueFunctionRotateY", optional=true)
    protected static native NSString RotateYValue();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAValueFunctionRotateZ", optional=true)
    protected static native NSString RotateZValue();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAValueFunctionScale", optional=true)
    protected static native NSString ScaleValue();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAValueFunctionScaleX", optional=true)
    protected static native NSString ScaleXValue();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAValueFunctionScaleY", optional=true)
    protected static native NSString ScaleYValue();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAValueFunctionScaleZ", optional=true)
    protected static native NSString ScaleZValue();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAValueFunctionTranslate", optional=true)
    protected static native NSString TranslateValue();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAValueFunctionTranslateX", optional=true)
    protected static native NSString TranslateXValue();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAValueFunctionTranslateY", optional=true)
    protected static native NSString TranslateYValue();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAValueFunctionTranslateZ", optional=true)
    protected static native NSString TranslateZValue();
    /*</methods>*/
}
