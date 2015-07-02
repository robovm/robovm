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
package org.robovm.apple.coretext;

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
import org.robovm.apple.coremedia.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreText") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/CTFontAttribute/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTFontAttribute/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CTFontAttribute/*</name>*/.class); }

    /*<marshalers>*/
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
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CTFontAttribute> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(CFArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CTFontAttribute> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CTFontAttribute.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CTFontAttribute> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CTFontAttribute o : l) {
                array.add(o.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute URL = new CTFontAttribute("URL");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute Name = new CTFontAttribute("Name");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute DisplayName = new CTFontAttribute("DisplayName");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute FamilyName = new CTFontAttribute("FamilyName");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute StyleName = new CTFontAttribute("StyleName");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute Traits = new CTFontAttribute("Traits");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute Variation = new CTFontAttribute("Variation");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute Size = new CTFontAttribute("Size");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute Matrix = new CTFontAttribute("Matrix");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute CascadeList = new CTFontAttribute("CascadeList");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute CharacterSet = new CTFontAttribute("CharacterSet");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute Languages = new CTFontAttribute("Languages");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute BaselineAdjust = new CTFontAttribute("BaselineAdjust");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute MacintoshEncodings = new CTFontAttribute("MacintoshEncodings");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute Features = new CTFontAttribute("Features");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute FeatureSettings = new CTFontAttribute("FeatureSettings");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute FixedAdvance = new CTFontAttribute("FixedAdvance");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute Orientation = new CTFontAttribute("Orientation");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute Format = new CTFontAttribute("Format");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute RegistrationScope = new CTFontAttribute("RegistrationScope");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute Priority = new CTFontAttribute("Priority");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontAttribute Enabled = new CTFontAttribute("Enabled");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final CTFontAttribute Downloadable = new CTFontAttribute("Downloadable");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CTFontAttribute Downloaded = new CTFontAttribute("Downloaded");
    /*</constants>*/
    
    private static /*<name>*/CTFontAttribute/*</name>*/[] values = new /*<name>*/CTFontAttribute/*</name>*/[] {/*<value_list>*/URL, Name, DisplayName, FamilyName, StyleName, Traits, Variation, Size, Matrix, CascadeList, CharacterSet, Languages, BaselineAdjust, MacintoshEncodings, Features, FeatureSettings, FixedAdvance, Orientation, Format, RegistrationScope, Priority, Enabled, Downloadable, Downloaded/*</value_list>*/};
    
    /*<name>*/CTFontAttribute/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CTFontAttribute/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CTFontAttribute/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CTFontAttribute/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreText") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTFontURLAttribute", optional=true)
        public static native CFString URL();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTFontNameAttribute", optional=true)
        public static native CFString Name();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTFontDisplayNameAttribute", optional=true)
        public static native CFString DisplayName();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTFontFamilyNameAttribute", optional=true)
        public static native CFString FamilyName();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTFontStyleNameAttribute", optional=true)
        public static native CFString StyleName();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTFontTraitsAttribute", optional=true)
        public static native CFString Traits();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTFontVariationAttribute", optional=true)
        public static native CFString Variation();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTFontSizeAttribute", optional=true)
        public static native CFString Size();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTFontMatrixAttribute", optional=true)
        public static native CFString Matrix();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTFontCascadeListAttribute", optional=true)
        public static native CFString CascadeList();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTFontCharacterSetAttribute", optional=true)
        public static native CFString CharacterSet();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTFontLanguagesAttribute", optional=true)
        public static native CFString Languages();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTFontBaselineAdjustAttribute", optional=true)
        public static native CFString BaselineAdjust();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTFontMacintoshEncodingsAttribute", optional=true)
        public static native CFString MacintoshEncodings();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTFontFeaturesAttribute", optional=true)
        public static native CFString Features();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTFontFeatureSettingsAttribute", optional=true)
        public static native CFString FeatureSettings();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTFontFixedAdvanceAttribute", optional=true)
        public static native CFString FixedAdvance();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTFontOrientationAttribute", optional=true)
        public static native CFString Orientation();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTFontFormatAttribute", optional=true)
        public static native CFString Format();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTFontRegistrationScopeAttribute", optional=true)
        public static native CFString RegistrationScope();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTFontPriorityAttribute", optional=true)
        public static native CFString Priority();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTFontEnabledAttribute", optional=true)
        public static native CFString Enabled();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="kCTFontDownloadableAttribute", optional=true)
        public static native CFString Downloadable();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCTFontDownloadedAttribute", optional=true)
        public static native CFString Downloaded();
        /*</values>*/
    }
}
