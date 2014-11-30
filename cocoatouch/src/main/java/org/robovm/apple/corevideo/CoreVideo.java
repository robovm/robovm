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
package org.robovm.apple.corevideo;

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
import org.robovm.apple.opengles.*;
import org.robovm.apple.coremedia.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreVideo") @Marshaler(NSString.AsStringMarshaler.class)/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CoreVideo/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CoreVideo.class); }/*</bind>*/
    /*<constants>*/
    public static final int CVTimeIsIndefinite = 1;
    public static final int CVTimeStampVideoHostTimeValid = 3;
    public static final int CVTimeStampIsInterlaced = 196608;
    /*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="kCVZeroTime", optional=true)
    public static native @ByVal CVTime CVTimeZeroTime();
    @GlobalValue(symbol="kCVIndefiniteTime", optional=true)
    public static native @ByVal CVTime CVTimeIndefiniteTime();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVBufferPropagatedAttachmentsKey", optional=true)
    public static native CFString CVBufferPropagatedAttachmentsKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVBufferNonPropagatedAttachmentsKey", optional=true)
    public static native CFString CVBufferNonPropagatedAttachmentsKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVBufferMovieTimeKey", optional=true)
    public static native CFString CVBufferMovieTimeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVBufferTimeValueKey", optional=true)
    public static native CFString CVBufferTimeValueKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVBufferTimeScaleKey", optional=true)
    public static native CFString CVBufferTimeScaleKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferCGColorSpaceKey", optional=true)
    public static native CFString CVImageBufferCGColorSpaceKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferCleanApertureKey", optional=true)
    public static native CFString CVImageBufferCleanApertureKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferCleanApertureWidthKey", optional=true)
    public static native CFString CVImageBufferCleanApertureWidthKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferCleanApertureHeightKey", optional=true)
    public static native CFString CVImageBufferCleanApertureHeightKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferCleanApertureHorizontalOffsetKey", optional=true)
    public static native CFString CVImageBufferCleanApertureHorizontalOffsetKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferCleanApertureVerticalOffsetKey", optional=true)
    public static native CFString CVImageBufferCleanApertureVerticalOffsetKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferPreferredCleanApertureKey", optional=true)
    public static native CFString CVImageBufferPreferredCleanApertureKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferFieldCountKey", optional=true)
    public static native CFString CVImageBufferFieldCountKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferFieldDetailKey", optional=true)
    public static native CFString CVImageBufferFieldDetailKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferFieldDetailTemporalTopFirst", optional=true)
    public static native CFString CVImageBufferFieldDetailTemporalTopFirst();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferFieldDetailTemporalBottomFirst", optional=true)
    public static native CFString CVImageBufferFieldDetailTemporalBottomFirst();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferFieldDetailSpatialFirstLineEarly", optional=true)
    public static native CFString CVImageBufferFieldDetailSpatialFirstLineEarly();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferFieldDetailSpatialFirstLineLate", optional=true)
    public static native CFString CVImageBufferFieldDetailSpatialFirstLineLate();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferPixelAspectRatioKey", optional=true)
    public static native CFString CVImageBufferPixelAspectRatioKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferPixelAspectRatioHorizontalSpacingKey", optional=true)
    public static native CFString CVImageBufferPixelAspectRatioHorizontalSpacingKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferPixelAspectRatioVerticalSpacingKey", optional=true)
    public static native CFString CVImageBufferPixelAspectRatioVerticalSpacingKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferDisplayDimensionsKey", optional=true)
    public static native CFString CVImageBufferDisplayDimensionsKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferDisplayWidthKey", optional=true)
    public static native CFString CVImageBufferDisplayWidthKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferDisplayHeightKey", optional=true)
    public static native CFString CVImageBufferDisplayHeightKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferGammaLevelKey", optional=true)
    public static native CFString CVImageBufferGammaLevelKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferICCProfileKey", optional=true)
    public static native CFString CVImageBufferICCProfileKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferYCbCrMatrixKey", optional=true)
    public static native CFString CVImageBufferYCbCrMatrixKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferYCbCrMatrix_ITU_R_709_2", optional=true)
    public static native CFString CVImageBufferYCbCrMatrixITU_R_709_2();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferYCbCrMatrix_ITU_R_601_4", optional=true)
    public static native CFString CVImageBufferYCbCrMatrixITU_R_601_4();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferYCbCrMatrix_SMPTE_240M_1995", optional=true)
    public static native CFString CVImageBufferYCbCrMatrixSMPTE_240M_1995();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferColorPrimariesKey", optional=true)
    public static native CFString CVImageBufferColorPrimariesKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferColorPrimaries_ITU_R_709_2", optional=true)
    public static native CFString CVImageBufferColorPrimariesITU_R_709_2();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferColorPrimaries_EBU_3213", optional=true)
    public static native CFString CVImageBufferColorPrimariesEBU_3213();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferColorPrimaries_SMPTE_C", optional=true)
    public static native CFString CVImageBufferColorPrimariesSMPTE_C();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferColorPrimaries_P22", optional=true)
    public static native CFString CVImageBufferColorPrimariesP22();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferTransferFunctionKey", optional=true)
    public static native CFString CVImageBufferTransferFunctionKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferTransferFunction_ITU_R_709_2", optional=true)
    public static native CFString CVImageBufferTransferFunctionITU_R_709_2();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferTransferFunction_SMPTE_240M_1995", optional=true)
    public static native CFString CVImageBufferTransferFunctionSMPTE_240M_1995();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferTransferFunction_UseGamma", optional=true)
    public static native CFString CVImageBufferTransferFunctionUseGamma();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferChromaLocationTopFieldKey", optional=true)
    public static native CFString CVImageBufferChromaLocationTopFieldKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferChromaLocationBottomFieldKey", optional=true)
    public static native CFString CVImageBufferChromaLocationBottomFieldKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferChromaLocation_Left", optional=true)
    public static native CFString CVImageBufferChromaLocationLeft();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferChromaLocation_Center", optional=true)
    public static native CFString CVImageBufferChromaLocationCenter();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferChromaLocation_TopLeft", optional=true)
    public static native CFString CVImageBufferChromaLocationTopLeft();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferChromaLocation_Top", optional=true)
    public static native CFString CVImageBufferChromaLocationTop();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferChromaLocation_BottomLeft", optional=true)
    public static native CFString CVImageBufferChromaLocationBottomLeft();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferChromaLocation_Bottom", optional=true)
    public static native CFString CVImageBufferChromaLocationBottom();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferChromaLocation_DV420", optional=true)
    public static native CFString CVImageBufferChromaLocationDV420();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferChromaSubsamplingKey", optional=true)
    public static native CFString CVImageBufferChromaSubsamplingKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferChromaSubsampling_420", optional=true)
    public static native CFString CVImageBufferChromaSubsampling_420();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferChromaSubsampling_422", optional=true)
    public static native CFString CVImageBufferChromaSubsampling_422();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferChromaSubsampling_411", optional=true)
    public static native CFString CVImageBufferChromaSubsampling_411();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferAlphaChannelIsOpaque", optional=true)
    public static native CFString Value__kCVImageBufferAlphaChannelIsOpaque();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferPixelFormatTypeKey", optional=true)
    public static native CFString CVPixelBufferPixelFormatTypeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferMemoryAllocatorKey", optional=true)
    public static native CFString CVPixelBufferMemoryAllocatorKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferWidthKey", optional=true)
    public static native CFString CVPixelBufferWidthKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferHeightKey", optional=true)
    public static native CFString CVPixelBufferHeightKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferExtendedPixelsLeftKey", optional=true)
    public static native CFString CVPixelBufferExtendedPixelsLeftKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferExtendedPixelsTopKey", optional=true)
    public static native CFString CVPixelBufferExtendedPixelsTopKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferExtendedPixelsRightKey", optional=true)
    public static native CFString CVPixelBufferExtendedPixelsRightKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferExtendedPixelsBottomKey", optional=true)
    public static native CFString CVPixelBufferExtendedPixelsBottomKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferBytesPerRowAlignmentKey", optional=true)
    public static native CFString CVPixelBufferBytesPerRowAlignmentKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferCGBitmapContextCompatibilityKey", optional=true)
    public static native CFString CVPixelBufferCGBitmapContextCompatibilityKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferCGImageCompatibilityKey", optional=true)
    public static native CFString CVPixelBufferCGImageCompatibilityKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferOpenGLCompatibilityKey", optional=true)
    public static native CFString CVPixelBufferOpenGLCompatibilityKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferPlaneAlignmentKey", optional=true)
    public static native CFString CVPixelBufferPlaneAlignmentKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferIOSurfacePropertiesKey", optional=true)
    public static native CFString CVPixelBufferIOSurfacePropertiesKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferOpenGLESCompatibilityKey", optional=true)
    public static native CFString CVPixelBufferOpenGLESCompatibilityKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferMetalCompatibilityKey", optional=true)
    public static native CFString CVPixelBufferMetalCompatibilityKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferPoolMinimumBufferCountKey", optional=true)
    public static native CFString CVPixelBufferPoolMinimumBufferCountKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferPoolMaximumBufferAgeKey", optional=true)
    public static native CFString CVPixelBufferPoolMaximumBufferAgeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferPoolAllocationThresholdKey", optional=true)
    public static native CFString CVPixelBufferPoolAllocationThresholdKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferPoolFreeBufferNotification", optional=true)
    public static native CFString CVPixelBufferPoolFreeBufferNotification();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCVOpenGLESTextureCacheMaximumTextureAgeKey", optional=true)
    public static native CFString CVOpenGLESTextureCacheMaximumTextureAgeKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCVMetalTextureCacheMaximumTextureAgeKey", optional=true)
    public static native CFString Value__kCVMetalTextureCacheMaximumTextureAgeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatName", optional=true)
    public static native CFString CVPixelFormatName();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatConstant", optional=true)
    public static native CFString CVPixelFormatConstant();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatCodecType", optional=true)
    public static native CFString CVPixelFormatCodecType();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatFourCC", optional=true)
    public static native CFString CVPixelFormatFourCC();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatContainsAlpha", optional=true)
    public static native CFString CVPixelFormatContainsAlpha();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatContainsYCbCr", optional=true)
    public static native CFString CVPixelFormatContainsYCbCr();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatContainsRGB", optional=true)
    public static native CFString CVPixelFormatContainsRGB();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatPlanes", optional=true)
    public static native CFString CVPixelFormatPlanes();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatBlockWidth", optional=true)
    public static native CFString CVPixelFormatBlockWidth();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatBlockHeight", optional=true)
    public static native CFString CVPixelFormatBlockHeight();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatBitsPerBlock", optional=true)
    public static native CFString CVPixelFormatBitsPerBlock();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatBlockHorizontalAlignment", optional=true)
    public static native CFString CVPixelFormatBlockHorizontalAlignment();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatBlockVerticalAlignment", optional=true)
    public static native CFString CVPixelFormatBlockVerticalAlignment();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatBlackBlock", optional=true)
    public static native CFString CVPixelFormatBlackBlock();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatHorizontalSubsampling", optional=true)
    public static native CFString CVPixelFormatHorizontalSubsampling();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatVerticalSubsampling", optional=true)
    public static native CFString CVPixelFormatVerticalSubsampling();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatOpenGLFormat", optional=true)
    public static native CFString CVPixelFormatOpenGLFormat();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatOpenGLType", optional=true)
    public static native CFString CVPixelFormatOpenGLType();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatOpenGLInternalFormat", optional=true)
    public static native CFString CVPixelFormatOpenGLInternalFormat();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatCGBitmapInfo", optional=true)
    public static native CFString CVPixelFormatCGBitmapInfo();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatQDCompatibility", optional=true)
    public static native CFString CVPixelFormatQDCompatibility();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatCGBitmapContextCompatibility", optional=true)
    public static native CFString CVPixelFormatCGBitmapContextCompatibility();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatCGImageCompatibility", optional=true)
    public static native CFString CVPixelFormatCGImageCompatibility();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatOpenGLCompatibility", optional=true)
    public static native CFString CVPixelFormatOpenGLCompatibility();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatOpenGLESCompatibility", optional=true)
    public static native CFString CVPixelFormatOpenGLESCompatibility();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatFillExtendedPixelsCallback", optional=true)
    public static native CFString CVPixelFormatFillExtendedPixelsCallback();
    /*</methods>*/
}
