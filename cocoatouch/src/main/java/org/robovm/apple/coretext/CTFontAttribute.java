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
package org.robovm.apple.coretext;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(CTFontAttribute.Marshaler.class)
/*<annotations>*/@Library("CoreText")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTFontAttribute/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static CTFontAttribute toObject(Class<CTFontAttribute> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CTFontAttribute.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CTFontAttribute o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CTFontAttribute.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute URL = new CTFontAttribute("URLValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute Name = new CTFontAttribute("NameValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute DisplayName = new CTFontAttribute("DisplayNameValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute FamilyName = new CTFontAttribute("FamilyNameValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute StyleName = new CTFontAttribute("StyleNameValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute Traits = new CTFontAttribute("TraitsValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute Variation = new CTFontAttribute("VariationValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute Size = new CTFontAttribute("SizeValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute Matrix = new CTFontAttribute("MatrixValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute CascadeList = new CTFontAttribute("CascadeListValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute CharacterSet = new CTFontAttribute("CharacterSetValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute Languages = new CTFontAttribute("LanguagesValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute BaselineAdjust = new CTFontAttribute("BaselineAdjustValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute MacintoshEncodings = new CTFontAttribute("MacintoshEncodingsValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute Features = new CTFontAttribute("FeaturesValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute FeatureSettings = new CTFontAttribute("FeatureSettingsValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute FixedAdvance = new CTFontAttribute("FixedAdvanceValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute Orientation = new CTFontAttribute("OrientationValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute Format = new CTFontAttribute("FormatValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute RegistrationScope = new CTFontAttribute("RegistrationScopeValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute Priority = new CTFontAttribute("PriorityValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute Enabled = new CTFontAttribute("EnabledValue");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final CTFontAttribute Downloadable = new CTFontAttribute("DownloadableValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CTFontAttribute Downloaded = new CTFontAttribute("DownloadedValue");
    private static CTFontAttribute[] values = new CTFontAttribute[] {URL, Name, DisplayName, FamilyName, StyleName, Traits, Variation, Size, Matrix, CascadeList,
        CharacterSet, Languages, BaselineAdjust, MacintoshEncodings, Features, FeatureSettings, FixedAdvance, Orientation, Format, RegistrationScope, Priority,
        Enabled, Downloadable, Downloaded};
    private final LazyGlobalValue<CFString> lazyGlobalValue;
    
    private CTFontAttribute(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFString value() {
        return lazyGlobalValue.value();
    }
    
    public static CTFontAttribute valueOf(CFString value) {
        for (CTFontAttribute v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CTFontAttribute/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontURLAttribute", optional=true)
    protected static native CFString URLValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontNameAttribute", optional=true)
    protected static native CFString NameValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontDisplayNameAttribute", optional=true)
    protected static native CFString DisplayNameValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontFamilyNameAttribute", optional=true)
    protected static native CFString FamilyNameValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontStyleNameAttribute", optional=true)
    protected static native CFString StyleNameValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontTraitsAttribute", optional=true)
    protected static native CFString TraitsValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontVariationAttribute", optional=true)
    protected static native CFString VariationValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontSizeAttribute", optional=true)
    protected static native CFString SizeValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontMatrixAttribute", optional=true)
    protected static native CFString MatrixValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontCascadeListAttribute", optional=true)
    protected static native CFString CascadeListValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontCharacterSetAttribute", optional=true)
    protected static native CFString CharacterSetValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontLanguagesAttribute", optional=true)
    protected static native CFString LanguagesValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontBaselineAdjustAttribute", optional=true)
    protected static native CFString BaselineAdjustValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontMacintoshEncodingsAttribute", optional=true)
    protected static native CFString MacintoshEncodingsValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontFeaturesAttribute", optional=true)
    protected static native CFString FeaturesValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontFeatureSettingsAttribute", optional=true)
    protected static native CFString FeatureSettingsValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontFixedAdvanceAttribute", optional=true)
    protected static native CFString FixedAdvanceValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontOrientationAttribute", optional=true)
    protected static native CFString OrientationValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontFormatAttribute", optional=true)
    protected static native CFString FormatValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontRegistrationScopeAttribute", optional=true)
    protected static native CFString RegistrationScopeValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontPriorityAttribute", optional=true)
    protected static native CFString PriorityValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontEnabledAttribute", optional=true)
    protected static native CFString EnabledValue();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCTFontDownloadableAttribute", optional=true)
    protected static native CFString DownloadableValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCTFontDownloadedAttribute", optional=true)
    protected static native CFString DownloadedValue();
    /*</methods>*/
}
