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
    public static final int Constant__kCVTimeIsIndefinite = 1;
    public static final int Constant__kCVTimeStampVideoHostTimeValid = 3;
    public static final int Constant__kCVTimeStampIsInterlaced = 196608;
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
    public static native String CVBufferPropagatedAttachmentsKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVBufferNonPropagatedAttachmentsKey", optional=true)
    public static native String CVBufferNonPropagatedAttachmentsKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVBufferMovieTimeKey", optional=true)
    public static native String CVBufferMovieTimeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVBufferTimeValueKey", optional=true)
    public static native String CVBufferTimeValueKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVBufferTimeScaleKey", optional=true)
    public static native String CVBufferTimeScaleKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferCGColorSpaceKey", optional=true)
    public static native String CVImageBufferCGColorSpaceKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferCleanApertureKey", optional=true)
    public static native String CVImageBufferCleanApertureKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferCleanApertureWidthKey", optional=true)
    public static native String CVImageBufferCleanApertureWidthKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferCleanApertureHeightKey", optional=true)
    public static native String CVImageBufferCleanApertureHeightKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferCleanApertureHorizontalOffsetKey", optional=true)
    public static native String CVImageBufferCleanApertureHorizontalOffsetKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferCleanApertureVerticalOffsetKey", optional=true)
    public static native String CVImageBufferCleanApertureVerticalOffsetKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferPreferredCleanApertureKey", optional=true)
    public static native String CVImageBufferPreferredCleanApertureKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferFieldCountKey", optional=true)
    public static native String CVImageBufferFieldCountKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferFieldDetailKey", optional=true)
    public static native String CVImageBufferFieldDetailKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferFieldDetailTemporalTopFirst", optional=true)
    public static native String CVImageBufferFieldDetailTemporalTopFirst();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferFieldDetailTemporalBottomFirst", optional=true)
    public static native String CVImageBufferFieldDetailTemporalBottomFirst();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferFieldDetailSpatialFirstLineEarly", optional=true)
    public static native String CVImageBufferFieldDetailSpatialFirstLineEarly();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferFieldDetailSpatialFirstLineLate", optional=true)
    public static native String CVImageBufferFieldDetailSpatialFirstLineLate();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferPixelAspectRatioKey", optional=true)
    public static native String CVImageBufferPixelAspectRatioKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferPixelAspectRatioHorizontalSpacingKey", optional=true)
    public static native String CVImageBufferPixelAspectRatioHorizontalSpacingKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferPixelAspectRatioVerticalSpacingKey", optional=true)
    public static native String CVImageBufferPixelAspectRatioVerticalSpacingKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferDisplayDimensionsKey", optional=true)
    public static native String CVImageBufferDisplayDimensionsKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferDisplayWidthKey", optional=true)
    public static native String CVImageBufferDisplayWidthKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferDisplayHeightKey", optional=true)
    public static native String CVImageBufferDisplayHeightKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferGammaLevelKey", optional=true)
    public static native String CVImageBufferGammaLevelKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferICCProfileKey", optional=true)
    public static native String CVImageBufferICCProfileKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferYCbCrMatrixKey", optional=true)
    public static native String CVImageBufferYCbCrMatrixKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferYCbCrMatrix_ITU_R_709_2", optional=true)
    public static native String CVImageBufferYCbCrMatrixITU_R_709_2();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferYCbCrMatrix_ITU_R_601_4", optional=true)
    public static native String CVImageBufferYCbCrMatrixITU_R_601_4();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferYCbCrMatrix_SMPTE_240M_1995", optional=true)
    public static native String CVImageBufferYCbCrMatrixSMPTE_240M_1995();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferColorPrimariesKey", optional=true)
    public static native String CVImageBufferColorPrimariesKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferColorPrimaries_ITU_R_709_2", optional=true)
    public static native String CVImageBufferColorPrimariesITU_R_709_2();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferColorPrimaries_EBU_3213", optional=true)
    public static native String CVImageBufferColorPrimariesEBU_3213();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferColorPrimaries_SMPTE_C", optional=true)
    public static native String CVImageBufferColorPrimariesSMPTE_C();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferColorPrimaries_P22", optional=true)
    public static native String CVImageBufferColorPrimariesP22();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferTransferFunctionKey", optional=true)
    public static native String CVImageBufferTransferFunctionKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferTransferFunction_ITU_R_709_2", optional=true)
    public static native String CVImageBufferTransferFunctionITU_R_709_2();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferTransferFunction_SMPTE_240M_1995", optional=true)
    public static native String CVImageBufferTransferFunctionSMPTE_240M_1995();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferTransferFunction_UseGamma", optional=true)
    public static native String CVImageBufferTransferFunctionUseGamma();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferChromaLocationTopFieldKey", optional=true)
    public static native String CVImageBufferChromaLocationTopFieldKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferChromaLocationBottomFieldKey", optional=true)
    public static native String CVImageBufferChromaLocationBottomFieldKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferChromaLocation_Left", optional=true)
    public static native String CVImageBufferChromaLocationLeft();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferChromaLocation_Center", optional=true)
    public static native String CVImageBufferChromaLocationCenter();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferChromaLocation_TopLeft", optional=true)
    public static native String CVImageBufferChromaLocationTopLeft();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferChromaLocation_Top", optional=true)
    public static native String CVImageBufferChromaLocationTop();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferChromaLocation_BottomLeft", optional=true)
    public static native String CVImageBufferChromaLocationBottomLeft();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferChromaLocation_Bottom", optional=true)
    public static native String CVImageBufferChromaLocationBottom();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferChromaLocation_DV420", optional=true)
    public static native String CVImageBufferChromaLocationDV420();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferChromaSubsamplingKey", optional=true)
    public static native String CVImageBufferChromaSubsamplingKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferChromaSubsampling_420", optional=true)
    public static native String CVImageBufferChromaSubsampling_420();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferChromaSubsampling_422", optional=true)
    public static native String CVImageBufferChromaSubsampling_422();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVImageBufferChromaSubsampling_411", optional=true)
    public static native String CVImageBufferChromaSubsampling_411();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferPixelFormatTypeKey", optional=true)
    public static native String CVPixelBufferPixelFormatTypeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferMemoryAllocatorKey", optional=true)
    public static native String CVPixelBufferMemoryAllocatorKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferWidthKey", optional=true)
    public static native String CVPixelBufferWidthKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferHeightKey", optional=true)
    public static native String CVPixelBufferHeightKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferExtendedPixelsLeftKey", optional=true)
    public static native String CVPixelBufferExtendedPixelsLeftKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferExtendedPixelsTopKey", optional=true)
    public static native String CVPixelBufferExtendedPixelsTopKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferExtendedPixelsRightKey", optional=true)
    public static native String CVPixelBufferExtendedPixelsRightKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferExtendedPixelsBottomKey", optional=true)
    public static native String CVPixelBufferExtendedPixelsBottomKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferBytesPerRowAlignmentKey", optional=true)
    public static native String CVPixelBufferBytesPerRowAlignmentKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferCGBitmapContextCompatibilityKey", optional=true)
    public static native String CVPixelBufferCGBitmapContextCompatibilityKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferCGImageCompatibilityKey", optional=true)
    public static native String CVPixelBufferCGImageCompatibilityKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferOpenGLCompatibilityKey", optional=true)
    public static native String CVPixelBufferOpenGLCompatibilityKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferPlaneAlignmentKey", optional=true)
    public static native String CVPixelBufferPlaneAlignmentKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferIOSurfacePropertiesKey", optional=true)
    public static native String CVPixelBufferIOSurfacePropertiesKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferOpenGLESCompatibilityKey", optional=true)
    public static native String CVPixelBufferOpenGLESCompatibilityKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferPoolMinimumBufferCountKey", optional=true)
    public static native String CVPixelBufferPoolMinimumBufferCountKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferPoolMaximumBufferAgeKey", optional=true)
    public static native String CVPixelBufferPoolMaximumBufferAgeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferPoolAllocationThresholdKey", optional=true)
    public static native String CVPixelBufferPoolAllocationThresholdKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferPoolFreeBufferNotification", optional=true)
    public static native String CVPixelBufferPoolFreeBufferNotification();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCVOpenGLESTextureCacheMaximumTextureAgeKey", optional=true)
    public static native String CVOpenGLESTextureCacheMaximumTextureAgeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatName", optional=true)
    public static native String CVPixelFormatName();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatConstant", optional=true)
    public static native String CVPixelFormatConstant();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatCodecType", optional=true)
    public static native String CVPixelFormatCodecType();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatFourCC", optional=true)
    public static native String CVPixelFormatFourCC();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatContainsAlpha", optional=true)
    public static native String CVPixelFormatContainsAlpha();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatPlanes", optional=true)
    public static native String CVPixelFormatPlanes();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatBlockWidth", optional=true)
    public static native String CVPixelFormatBlockWidth();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatBlockHeight", optional=true)
    public static native String CVPixelFormatBlockHeight();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatBitsPerBlock", optional=true)
    public static native String CVPixelFormatBitsPerBlock();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatBlockHorizontalAlignment", optional=true)
    public static native String CVPixelFormatBlockHorizontalAlignment();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatBlockVerticalAlignment", optional=true)
    public static native String CVPixelFormatBlockVerticalAlignment();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatBlackBlock", optional=true)
    public static native String CVPixelFormatBlackBlock();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatHorizontalSubsampling", optional=true)
    public static native String CVPixelFormatHorizontalSubsampling();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatVerticalSubsampling", optional=true)
    public static native String CVPixelFormatVerticalSubsampling();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatOpenGLFormat", optional=true)
    public static native String CVPixelFormatOpenGLFormat();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatOpenGLType", optional=true)
    public static native String CVPixelFormatOpenGLType();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatOpenGLInternalFormat", optional=true)
    public static native String CVPixelFormatOpenGLInternalFormat();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatCGBitmapInfo", optional=true)
    public static native String CVPixelFormatCGBitmapInfo();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatQDCompatibility", optional=true)
    public static native String CVPixelFormatQDCompatibility();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatCGBitmapContextCompatibility", optional=true)
    public static native String CVPixelFormatCGBitmapContextCompatibility();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatCGImageCompatibility", optional=true)
    public static native String CVPixelFormatCGImageCompatibility();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatOpenGLCompatibility", optional=true)
    public static native String CVPixelFormatOpenGLCompatibility();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatOpenGLESCompatibility", optional=true)
    public static native String CVPixelFormatOpenGLESCompatibility();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelFormatFillExtendedPixelsCallback", optional=true)
    public static native String CVPixelFormatFillExtendedPixelsCallback();
    /*</methods>*/
}
