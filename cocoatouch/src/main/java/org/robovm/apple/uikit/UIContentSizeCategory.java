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
/*<annotations>*/@Library("UIKit")/*</annotations>*/
/**
 * @since Available in iOS 7.0 and later.
 */
@Marshaler(UIContentSizeCategory.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIContentSizeCategory/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static UIContentSizeCategory toObject(Class<UIContentSizeCategory> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return UIContentSizeCategory.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(UIContentSizeCategory o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(UIContentSizeCategory.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final UIContentSizeCategory XS = new UIContentSizeCategory("ExtraSmallValue");
    public static final UIContentSizeCategory S = new UIContentSizeCategory("SmallValue");
    public static final UIContentSizeCategory M = new UIContentSizeCategory("MediumValue");
    public static final UIContentSizeCategory L = new UIContentSizeCategory("LargeValue");
    public static final UIContentSizeCategory XL = new UIContentSizeCategory("ExtraLargeValue");
    public static final UIContentSizeCategory XXL = new UIContentSizeCategory("ExtraExtraLargeValue");
    public static final UIContentSizeCategory XXXL = new UIContentSizeCategory("ExtraExtraExtraLargeValue");
    public static final UIContentSizeCategory AccessibilityM = new UIContentSizeCategory("AccessibilityMediumValue");
    public static final UIContentSizeCategory AccessibilityL = new UIContentSizeCategory("AccessibilityLargeValue");
    public static final UIContentSizeCategory AccessibilityXL = new UIContentSizeCategory("AccessibilityExtraLargeValue");
    public static final UIContentSizeCategory AccessibilityXXL = new UIContentSizeCategory("AccessibilityExtraExtraLargeValue");
    public static final UIContentSizeCategory AccessibilityXXXL = new UIContentSizeCategory("AccessibilityExtraExtraExtraLargeValue");
    private static UIContentSizeCategory[] values = new UIContentSizeCategory[] {XS, S, M, L, XL, XXL, XXXL, AccessibilityM, AccessibilityL, AccessibilityXL, AccessibilityXXL, AccessibilityXXXL};
    
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private UIContentSizeCategory(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static UIContentSizeCategory valueOf(NSString value) {
        for (UIContentSizeCategory v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/UIContentSizeCategory/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIContentSizeCategoryExtraSmall", optional=true)
    protected static native NSString ExtraSmallValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIContentSizeCategorySmall", optional=true)
    protected static native NSString SmallValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIContentSizeCategoryMedium", optional=true)
    protected static native NSString MediumValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIContentSizeCategoryLarge", optional=true)
    protected static native NSString LargeValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIContentSizeCategoryExtraLarge", optional=true)
    protected static native NSString ExtraLargeValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIContentSizeCategoryExtraExtraLarge", optional=true)
    protected static native NSString ExtraExtraLargeValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIContentSizeCategoryExtraExtraExtraLarge", optional=true)
    protected static native NSString ExtraExtraExtraLargeValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIContentSizeCategoryAccessibilityMedium", optional=true)
    protected static native NSString AccessibilityMediumValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIContentSizeCategoryAccessibilityLarge", optional=true)
    protected static native NSString AccessibilityLargeValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIContentSizeCategoryAccessibilityExtraLarge", optional=true)
    protected static native NSString AccessibilityExtraLargeValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIContentSizeCategoryAccessibilityExtraExtraLarge", optional=true)
    protected static native NSString AccessibilityExtraExtraLargeValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIContentSizeCategoryAccessibilityExtraExtraExtraLarge", optional=true)
    protected static native NSString AccessibilityExtraExtraExtraLargeValue();
    /*</methods>*/
}
