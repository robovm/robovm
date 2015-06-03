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
@Marshaler(CAShapeFillRule.Marshaler.class)
/*<annotations>*/@Library("QuartzCore")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CAShapeFillRule/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static CAShapeFillRule toObject(Class<CAShapeFillRule> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CAShapeFillRule.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CAShapeFillRule o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CAShapeFillRule.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final CAShapeFillRule NonZero = new CAShapeFillRule("NonZeroValue");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final CAShapeFillRule EvenOdd = new CAShapeFillRule("EvenOddValue");
    
    private static CAShapeFillRule[] values = new CAShapeFillRule[] {NonZero, EvenOdd};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private CAShapeFillRule(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static CAShapeFillRule valueOf(NSString value) {
        for (CAShapeFillRule v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CAShapeFillRule/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAFillRuleNonZero", optional=true)
    protected static native NSString NonZeroValue();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAFillRuleEvenOdd", optional=true)
    protected static native NSString EvenOddValue();
    /*</methods>*/
}
