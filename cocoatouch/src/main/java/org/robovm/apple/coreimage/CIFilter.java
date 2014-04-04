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

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("CoreImage") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CIFilter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class CIFilterPtr extends Ptr<CIFilter, CIFilterPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CIFilter.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CIFilter(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "outputImage")
    public native CIImage getOutputImage();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="kCIAttributeFilterName", optional=true)
    public static native NSString AttributeFilterName();
    @GlobalValue(symbol="kCIAttributeFilterDisplayName", optional=true)
    public static native NSString AttributeFilterDisplayName();
    @GlobalValue(symbol="kCIAttributeDescription", optional=true)
    public static native NSString AttributeDescription();
    @GlobalValue(symbol="kCIAttributeReferenceDocumentation", optional=true)
    public static native NSString AttributeReferenceDocumentation();
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
    @GlobalValue(symbol="kCIAttributeTypeInteger", optional=true)
    public static native NSString AttributeTypeInteger();
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
    @GlobalValue(symbol="kCIAttributeTypeGradient", optional=true)
    public static native NSString AttributeTypeGradient();
    @GlobalValue(symbol="kCIAttributeTypeImage", optional=true)
    public static native NSString AttributeTypeImage();
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
    @GlobalValue(symbol="kCICategoryFilterGenerator", optional=true)
    public static native NSString CategoryFilterGenerator();
    @GlobalValue(symbol="kCIOutputImageKey", optional=true)
    public static native NSString ParameterOutputImage();
    @GlobalValue(symbol="kCIInputBackgroundImageKey", optional=true)
    public static native NSString ParameterInputBackgroundImage();
    @GlobalValue(symbol="kCIInputImageKey", optional=true)
    public static native NSString ParameterInputImage();
    @GlobalValue(symbol="kCIInputTimeKey", optional=true)
    public static native NSString ParameterInputTime();
    @GlobalValue(symbol="kCIInputTransformKey", optional=true)
    public static native NSString ParameterInputTransform();
    @GlobalValue(symbol="kCIInputScaleKey", optional=true)
    public static native NSString ParameterInputScale();
    @GlobalValue(symbol="kCIInputAspectRatioKey", optional=true)
    public static native NSString ParameterInputAspectRatio();
    @GlobalValue(symbol="kCIInputCenterKey", optional=true)
    public static native NSString ParameterInputCenter();
    @GlobalValue(symbol="kCIInputRadiusKey", optional=true)
    public static native NSString ParameterInputRadius();
    @GlobalValue(symbol="kCIInputAngleKey", optional=true)
    public static native NSString ParameterInputAngle();
    @GlobalValue(symbol="kCIInputRefractionKey", optional=true)
    public static native NSString ParameterInputRefraction();
    @GlobalValue(symbol="kCIInputWidthKey", optional=true)
    public static native NSString ParameterInputWidth();
    @GlobalValue(symbol="kCIInputSharpnessKey", optional=true)
    public static native NSString ParameterInputSharpness();
    @GlobalValue(symbol="kCIInputIntensityKey", optional=true)
    public static native NSString ParameterInputIntensity();
    @GlobalValue(symbol="kCIInputEVKey", optional=true)
    public static native NSString ParameterInputEV();
    @GlobalValue(symbol="kCIInputSaturationKey", optional=true)
    public static native NSString ParameterInputSaturation();
    @GlobalValue(symbol="kCIInputColorKey", optional=true)
    public static native NSString ParameterInputColor();
    @GlobalValue(symbol="kCIInputBrightnessKey", optional=true)
    public static native NSString ParameterInputBrightness();
    @GlobalValue(symbol="kCIInputContrastKey", optional=true)
    public static native NSString ParameterInputContrast();
    @GlobalValue(symbol="kCIInputGradientImageKey", optional=true)
    public static native NSString ParameterInputGradientImage();
    @GlobalValue(symbol="kCIInputMaskImageKey", optional=true)
    public static native NSString ParameterInputMaskImage();
    @GlobalValue(symbol="kCIInputShadingImageKey", optional=true)
    public static native NSString ParameterInputShadingImage();
    @GlobalValue(symbol="kCIInputTargetImageKey", optional=true)
    public static native NSString ParameterInputTargetImage();
    @GlobalValue(symbol="kCIInputExtentKey", optional=true)
    public static native NSString ParameterInputExtent();
    @GlobalValue(symbol="kCIInputVersionKey", optional=true)
    public static native NSString ParameterInputVersion();
    
    @Method(selector = "name")
    public native String getName();
    @Method(selector = "inputKeys")
    public native NSArray<?> getInputKeys();
    @Method(selector = "outputKeys")
    public native NSArray<?> getOutputKeys();
    @Method(selector = "setDefaults")
    public native void setDefaults();
    @Method(selector = "attributes")
    public native NSDictionary<?, ?> getAttributes();
    @Method(selector = "filterWithName:")
    public static native CIFilter getFilter(String name);
    @Method(selector = "filterNamesInCategory:")
    public static native NSArray<NSString> getFilterNames(String category);
    @Method(selector = "filterNamesInCategories:")
    public static native NSArray<NSString> getFilterNames(NSArray<NSString> categories);
    @Method(selector = "serializedXMPFromFilters:inputImageExtent:")
    public static native NSData serializeToXMP(NSArray<CIFilter> filters, @ByVal CGRect extent);
    @Method(selector = "filterArrayFromSerializedXMP:inputImageExtent:error:")
    public static native NSArray<CIFilter> deserializeFromXMP(NSData xmpData, @ByVal CGRect extent, NSError.NSErrorPtr outError);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
