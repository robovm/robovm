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
/**
 * @since Available in iOS 7.0 and later.
 */
/*<annotations>*/@Library("UIKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIFontDescriptorAttribute/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(UIFontDescriptorAttribute.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static UIFontDescriptorAttribute Family = new UIFontDescriptorAttribute("FamilyValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static UIFontDescriptorAttribute Name = new UIFontDescriptorAttribute("NameValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static UIFontDescriptorAttribute Face = new UIFontDescriptorAttribute("FaceValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static UIFontDescriptorAttribute Size = new UIFontDescriptorAttribute("SizeValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static UIFontDescriptorAttribute VisibleName = new UIFontDescriptorAttribute("VisibleNameValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static UIFontDescriptorAttribute Matrix = new UIFontDescriptorAttribute("MatrixValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static UIFontDescriptorAttribute CharacterSet = new UIFontDescriptorAttribute("CharacterSetValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static UIFontDescriptorAttribute CascadeList = new UIFontDescriptorAttribute("CascadeListValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static UIFontDescriptorAttribute Traits = new UIFontDescriptorAttribute("TraitsValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static UIFontDescriptorAttribute FixedAdvance = new UIFontDescriptorAttribute("FixedAdvanceValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static UIFontDescriptorAttribute FeatureSettings = new UIFontDescriptorAttribute("FeatureSettingsValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static UIFontDescriptorAttribute TextStyle = new UIFontDescriptorAttribute("TextStyleValue");
    private static UIFontDescriptorAttribute[] values = new UIFontDescriptorAttribute[] {Family, Name, Face, Size, VisibleName, Matrix, CharacterSet, CascadeList, Traits, FixedAdvance, FeatureSettings};
    
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private UIFontDescriptorAttribute(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static UIFontDescriptorAttribute valueOf(NSString value) {
        for (UIFontDescriptorAttribute v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/UIFontDescriptorAttribute/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontDescriptorFamilyAttribute", optional=true)
    protected static native NSString FamilyValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontDescriptorNameAttribute", optional=true)
    protected static native NSString NameValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontDescriptorFaceAttribute", optional=true)
    protected static native NSString FaceValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontDescriptorSizeAttribute", optional=true)
    protected static native NSString SizeValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontDescriptorVisibleNameAttribute", optional=true)
    protected static native NSString VisibleNameValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontDescriptorMatrixAttribute", optional=true)
    protected static native NSString MatrixValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontDescriptorCharacterSetAttribute", optional=true)
    protected static native NSString CharacterSetValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontDescriptorCascadeListAttribute", optional=true)
    protected static native NSString CascadeListValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontDescriptorTraitsAttribute", optional=true)
    protected static native NSString TraitsValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontDescriptorFixedAdvanceAttribute", optional=true)
    protected static native NSString FixedAdvanceValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontDescriptorFeatureSettingsAttribute", optional=true)
    protected static native NSString FeatureSettingsValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontDescriptorTextStyleAttribute", optional=true)
    protected static native NSString TextStyleValue();
    /*</methods>*/
}
