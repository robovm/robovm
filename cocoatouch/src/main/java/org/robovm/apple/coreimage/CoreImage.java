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
import org.robovm.apple.opengles.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreImage") @Marshaler(NSString.AsStringMarshaler.class)/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CoreImage/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CoreImage.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCIFormatARGB8", optional=true)
    public static native int FormatARGB8();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCIFormatBGRA8", optional=true)
    public static native int FormatBGRA8();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCIFormatRGBA8", optional=true)
    public static native int FormatRGBA8();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCIFormatRGBAh", optional=true)
    public static native int FormatRGBAh();
    @GlobalValue(symbol="kCIImageColorSpace", optional=true)
    public static native NSString ImageColorSpace();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCIImageProperties", optional=true)
    public static native NSString ImageProperties();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCIImageAutoAdjustEnhance", optional=true)
    public static native NSString ImageAutoAdjustEnhance();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCIImageAutoAdjustRedEye", optional=true)
    public static native NSString ImageAutoAdjustRedEye();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCIImageAutoAdjustFeatures", optional=true)
    public static native NSString ImageAutoAdjustFeatures();
    @GlobalValue(symbol="kCIContextOutputColorSpace", optional=true)
    public static native NSString ContextOutputColorSpace();
    @GlobalValue(symbol="kCIContextWorkingColorSpace", optional=true)
    public static native NSString ContextWorkingColorSpace();
    @GlobalValue(symbol="kCIContextUseSoftwareRenderer", optional=true)
    public static native NSString ContextUseSoftwareRenderer();
    @GlobalValue(symbol="kCIAttributeFilterName", optional=true)
    public static native NSString AttributeFilterName();
    @GlobalValue(symbol="kCIAttributeFilterDisplayName", optional=true)
    public static native NSString AttributeFilterDisplayName();
    @GlobalValue(symbol="kCIAttributeFilterCategories", optional=true)
    public static native NSString AttributeFilterCategories();
    @GlobalValue(symbol="kCIAttributeClass", optional=true)
    public static native NSString AttributeClass();
    @GlobalValue(symbol="kCIAttributeType", optional=true)
    public static native NSString AttributeType();
    @GlobalValue(symbol="kCIAttributeMin", optional=true)
    public static native NSString AttributeMin();
    @GlobalValue(symbol="kCIAttributeMax", optional=true)
    public static native NSString AttributeMax();
    @GlobalValue(symbol="kCIAttributeSliderMin", optional=true)
    public static native NSString AttributeSliderMin();
    @GlobalValue(symbol="kCIAttributeSliderMax", optional=true)
    public static native NSString AttributeSliderMax();
    @GlobalValue(symbol="kCIAttributeDefault", optional=true)
    public static native NSString AttributeDefault();
    @GlobalValue(symbol="kCIAttributeIdentity", optional=true)
    public static native NSString AttributeIdentity();
    @GlobalValue(symbol="kCIAttributeName", optional=true)
    public static native NSString AttributeName();
    @GlobalValue(symbol="kCIAttributeDisplayName", optional=true)
    public static native NSString AttributeDisplayName();
    @GlobalValue(symbol="kCIAttributeTypeTime", optional=true)
    public static native NSString AttributeTypeTime();
    @GlobalValue(symbol="kCIAttributeTypeScalar", optional=true)
    public static native NSString AttributeTypeScalar();
    @GlobalValue(symbol="kCIAttributeTypeDistance", optional=true)
    public static native NSString AttributeTypeDistance();
    @GlobalValue(symbol="kCIAttributeTypeAngle", optional=true)
    public static native NSString AttributeTypeAngle();
    @GlobalValue(symbol="kCIAttributeTypeBoolean", optional=true)
    public static native NSString AttributeTypeBoolean();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCIAttributeTypeInteger", optional=true)
    public static native NSString AttributeTypeInteger();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCIAttributeTypeCount", optional=true)
    public static native NSString AttributeTypeCount();
    @GlobalValue(symbol="kCIAttributeTypePosition", optional=true)
    public static native NSString AttributeTypePosition();
    @GlobalValue(symbol="kCIAttributeTypeOffset", optional=true)
    public static native NSString AttributeTypeOffset();
    @GlobalValue(symbol="kCIAttributeTypePosition3", optional=true)
    public static native NSString AttributeTypePosition3();
    @GlobalValue(symbol="kCIAttributeTypeRectangle", optional=true)
    public static native NSString AttributeTypeRectangle();
    @GlobalValue(symbol="kCIAttributeTypeColor", optional=true)
    public static native NSString AttributeTypeColor();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCIAttributeTypeImage", optional=true)
    public static native NSString AttributeTypeImage();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCIAttributeTypeTransform", optional=true)
    public static native NSString AttributeTypeTransform();
    @GlobalValue(symbol="kCICategoryDistortionEffect", optional=true)
    public static native NSString CategoryDistortionEffect();
    @GlobalValue(symbol="kCICategoryGeometryAdjustment", optional=true)
    public static native NSString CategoryGeometryAdjustment();
    @GlobalValue(symbol="kCICategoryCompositeOperation", optional=true)
    public static native NSString CategoryCompositeOperation();
    @GlobalValue(symbol="kCICategoryHalftoneEffect", optional=true)
    public static native NSString CategoryHalftoneEffect();
    @GlobalValue(symbol="kCICategoryColorAdjustment", optional=true)
    public static native NSString CategoryColorAdjustment();
    @GlobalValue(symbol="kCICategoryColorEffect", optional=true)
    public static native NSString CategoryColorEffect();
    @GlobalValue(symbol="kCICategoryTransition", optional=true)
    public static native NSString CategoryTransition();
    @GlobalValue(symbol="kCICategoryTileEffect", optional=true)
    public static native NSString CategoryTileEffect();
    @GlobalValue(symbol="kCICategoryGenerator", optional=true)
    public static native NSString CategoryGenerator();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCICategoryReduction", optional=true)
    public static native NSString CategoryReduction();
    @GlobalValue(symbol="kCICategoryGradient", optional=true)
    public static native NSString CategoryGradient();
    @GlobalValue(symbol="kCICategoryStylize", optional=true)
    public static native NSString CategoryStylize();
    @GlobalValue(symbol="kCICategorySharpen", optional=true)
    public static native NSString CategorySharpen();
    @GlobalValue(symbol="kCICategoryBlur", optional=true)
    public static native NSString CategoryBlur();
    @GlobalValue(symbol="kCICategoryVideo", optional=true)
    public static native NSString CategoryVideo();
    @GlobalValue(symbol="kCICategoryStillImage", optional=true)
    public static native NSString CategoryStillImage();
    @GlobalValue(symbol="kCICategoryInterlaced", optional=true)
    public static native NSString CategoryInterlaced();
    @GlobalValue(symbol="kCICategoryNonSquarePixels", optional=true)
    public static native NSString CategoryNonSquarePixels();
    @GlobalValue(symbol="kCICategoryHighDynamicRange", optional=true)
    public static native NSString CategoryHighDynamicRange();
    @GlobalValue(symbol="kCICategoryBuiltIn", optional=true)
    public static native NSString CategoryBuiltIn();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCIOutputImageKey", optional=true)
    public static native NSString OutputImageKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCIInputBackgroundImageKey", optional=true)
    public static native NSString InputBackgroundImageKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCIInputImageKey", optional=true)
    public static native NSString InputImageKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCIInputTimeKey", optional=true)
    public static native NSString InputTimeKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCIInputTransformKey", optional=true)
    public static native NSString InputTransformKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCIInputScaleKey", optional=true)
    public static native NSString InputScaleKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCIInputAspectRatioKey", optional=true)
    public static native NSString InputAspectRatioKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCIInputCenterKey", optional=true)
    public static native NSString InputCenterKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCIInputRadiusKey", optional=true)
    public static native NSString InputRadiusKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCIInputAngleKey", optional=true)
    public static native NSString InputAngleKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCIInputWidthKey", optional=true)
    public static native NSString InputWidthKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCIInputSharpnessKey", optional=true)
    public static native NSString InputSharpnessKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCIInputIntensityKey", optional=true)
    public static native NSString InputIntensityKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCIInputEVKey", optional=true)
    public static native NSString InputEVKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCIInputSaturationKey", optional=true)
    public static native NSString InputSaturationKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCIInputColorKey", optional=true)
    public static native NSString InputColorKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCIInputBrightnessKey", optional=true)
    public static native NSString InputBrightnessKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCIInputContrastKey", optional=true)
    public static native NSString InputContrastKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCIInputMaskImageKey", optional=true)
    public static native NSString InputMaskImageKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCIInputTargetImageKey", optional=true)
    public static native NSString InputTargetImageKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCIInputExtentKey", optional=true)
    public static native NSString InputExtentKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCIInputVersionKey", optional=true)
    public static native NSString InputVersionKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="CIDetectorTypeFace", optional=true)
    public static native String DetectorTypeFace();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="CIDetectorAccuracy", optional=true)
    public static native NSString DetectorAccuracy();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="CIDetectorAccuracyLow", optional=true)
    public static native NSString DetectorAccuracyLow();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="CIDetectorAccuracyHigh", optional=true)
    public static native NSString DetectorAccuracyHigh();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="CIDetectorTracking", optional=true)
    public static native NSString DetectorTracking();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="CIDetectorMinFeatureSize", optional=true)
    public static native NSString DetectorMinFeatureSize();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="CIDetectorImageOrientation", optional=true)
    public static native NSString DetectorImageOrientation();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="CIDetectorEyeBlink", optional=true)
    public static native NSString DetectorEyeBlink();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="CIDetectorSmile", optional=true)
    public static native NSString DetectorSmile();
    @GlobalValue(symbol="CIFeatureTypeFace", optional=true)
    public static native String FeatureTypeFace();
    /*</methods>*/
}
