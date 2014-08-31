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
/*<annotations>*/@Library("CoreText") @Marshaler(NSString.AsStringMarshaler.class)/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CoreText/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CoreText.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontFeatureTypeIdentifierKey", optional=true)
    public static native String CTFontFeatureTypeIdentifierKey();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontFeatureTypeNameKey", optional=true)
    public static native String CTFontFeatureTypeNameKey();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontFeatureTypeExclusiveKey", optional=true)
    public static native String CTFontFeatureTypeExclusiveKey();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontFeatureTypeSelectorsKey", optional=true)
    public static native String CTFontFeatureTypeSelectorsKey();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontFeatureSelectorIdentifierKey", optional=true)
    public static native String CTFontFeatureSelectorIdentifierKey();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontFeatureSelectorNameKey", optional=true)
    public static native String CTFontFeatureSelectorNameKey();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontFeatureSelectorDefaultKey", optional=true)
    public static native String CTFontFeatureSelectorDefaultKey();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontFeatureSelectorSettingKey", optional=true)
    public static native String CTFontFeatureSelectorSettingKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCTBaselineClassRoman", optional=true)
    public static native String Value__kCTBaselineClassRoman();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCTBaselineClassIdeographicCentered", optional=true)
    public static native String Value__kCTBaselineClassIdeographicCentered();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCTBaselineClassIdeographicLow", optional=true)
    public static native String Value__kCTBaselineClassIdeographicLow();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCTBaselineClassIdeographicHigh", optional=true)
    public static native String Value__kCTBaselineClassIdeographicHigh();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCTBaselineClassHanging", optional=true)
    public static native String Value__kCTBaselineClassHanging();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCTBaselineClassMath", optional=true)
    public static native String Value__kCTBaselineClassMath();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCTBaselineReferenceFont", optional=true)
    public static native String Value__kCTBaselineReferenceFont();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCTBaselineOriginalFont", optional=true)
    public static native String Value__kCTBaselineOriginalFont();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontManagerErrorDomain", optional=true)
    public static native String Value__kCTFontManagerErrorDomain();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontManagerErrorFontURLsKey", optional=true)
    public static native String Value__kCTFontManagerErrorFontURLsKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCTFontManagerRegisteredFontsChangedNotification", optional=true)
    public static native String Value__kCTFontManagerRegisteredFontsChangedNotification();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontAttributeName", optional=true)
    public static native String Value__kCTFontAttributeName();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTForegroundColorFromContextAttributeName", optional=true)
    public static native String Value__kCTForegroundColorFromContextAttributeName();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTKernAttributeName", optional=true)
    public static native String Value__kCTKernAttributeName();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTLigatureAttributeName", optional=true)
    public static native String Value__kCTLigatureAttributeName();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTForegroundColorAttributeName", optional=true)
    public static native String Value__kCTForegroundColorAttributeName();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTParagraphStyleAttributeName", optional=true)
    public static native String Value__kCTParagraphStyleAttributeName();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTStrokeWidthAttributeName", optional=true)
    public static native String Value__kCTStrokeWidthAttributeName();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTStrokeColorAttributeName", optional=true)
    public static native String Value__kCTStrokeColorAttributeName();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTUnderlineStyleAttributeName", optional=true)
    public static native String Value__kCTUnderlineStyleAttributeName();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTSuperscriptAttributeName", optional=true)
    public static native String Value__kCTSuperscriptAttributeName();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTUnderlineColorAttributeName", optional=true)
    public static native String Value__kCTUnderlineColorAttributeName();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="kCTVerticalFormsAttributeName", optional=true)
    public static native String Value__kCTVerticalFormsAttributeName();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTGlyphInfoAttributeName", optional=true)
    public static native String Value__kCTGlyphInfoAttributeName();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTCharacterShapeAttributeName", optional=true)
    public static native String Value__kCTCharacterShapeAttributeName();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCTLanguageAttributeName", optional=true)
    public static native String Value__kCTLanguageAttributeName();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTRunDelegateAttributeName", optional=true)
    public static native String Value__kCTRunDelegateAttributeName();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCTBaselineClassAttributeName", optional=true)
    public static native String Value__kCTBaselineClassAttributeName();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCTBaselineInfoAttributeName", optional=true)
    public static native String Value__kCTBaselineInfoAttributeName();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCTBaselineReferenceInfoAttributeName", optional=true)
    public static native String Value__kCTBaselineReferenceInfoAttributeName();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCTWritingDirectionAttributeName", optional=true)
    public static native String Value__kCTWritingDirectionAttributeName();
    
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTGetCoreTextVersion", optional=true)
    public static native int function__CTGetCoreTextVersion();
    /*</methods>*/
}
