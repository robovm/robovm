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
package org.robovm.apple.imageio;

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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(CGImagePropertyExif.Marshaler.class)
/*<annotations>*/@Library("ImageIO")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImagePropertyExif/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @MarshalsPointer
        public static CGImagePropertyExif toObject(Class<CGImagePropertyExif> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CGImagePropertyExif.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CGImagePropertyExif o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CGImagePropertyExif.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif ExposureTime = new CGImagePropertyExif("ExposureTimeKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif FNumber = new CGImagePropertyExif("FNumberKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif ExposureProgram = new CGImagePropertyExif("ExposureProgramKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif SpectralSensitivity = new CGImagePropertyExif("SpectralSensitivityKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif ISOSpeedRatings = new CGImagePropertyExif("ISOSpeedRatingsKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif OECF = new CGImagePropertyExif("OECFKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif Version = new CGImagePropertyExif("VersionKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif DateTimeOriginal = new CGImagePropertyExif("DateTimeOriginalKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif DateTimeDigitized = new CGImagePropertyExif("DateTimeDigitizedKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif ComponentsConfiguration = new CGImagePropertyExif("ComponentsConfigurationKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif CompressedBitsPerPixel = new CGImagePropertyExif("CompressedBitsPerPixelKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif ShutterSpeedValue = new CGImagePropertyExif("ShutterSpeedValueKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif ApertureValue = new CGImagePropertyExif("ApertureValueKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif BrightnessValue = new CGImagePropertyExif("BrightnessValueKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif ExposureBiasValue = new CGImagePropertyExif("ExposureBiasValueKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif MaxApertureValue = new CGImagePropertyExif("MaxApertureValueKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif SubjectDistance = new CGImagePropertyExif("SubjectDistanceKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif MeteringMode = new CGImagePropertyExif("MeteringModeKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif LightSource = new CGImagePropertyExif("LightSourceKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif Flash = new CGImagePropertyExif("FlashKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif FocalLength = new CGImagePropertyExif("FocalLengthKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif SubjectArea = new CGImagePropertyExif("SubjectAreaKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif MakerNote = new CGImagePropertyExif("MakerNoteKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif UserComment = new CGImagePropertyExif("UserCommentKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif SubsecTime = new CGImagePropertyExif("SubsecTimeKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif SubsecTimeOrginal = new CGImagePropertyExif("SubsecTimeOrginalKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif SubsecTimeDigitized = new CGImagePropertyExif("SubsecTimeDigitizedKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif FlashPixVersion = new CGImagePropertyExif("FlashPixVersionKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif ColorSpace = new CGImagePropertyExif("ColorSpaceKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif PixelXDimension = new CGImagePropertyExif("PixelXDimensionKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif PixelYDimension = new CGImagePropertyExif("PixelYDimensionKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif RelatedSoundFile = new CGImagePropertyExif("RelatedSoundFileKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif FlashEnergy = new CGImagePropertyExif("FlashEnergyKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif SpatialFrequencyResponse = new CGImagePropertyExif("SpatialFrequencyResponseKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif FocalPlaneXResolution = new CGImagePropertyExif("FocalPlaneXResolutionKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif FocalPlaneYResolution = new CGImagePropertyExif("FocalPlaneYResolutionKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif FocalPlaneResolutionUnit = new CGImagePropertyExif("FocalPlaneResolutionUnitKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif SubjectLocation = new CGImagePropertyExif("SubjectLocationKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif ExposureIndex = new CGImagePropertyExif("ExposureIndexKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif SensingMethod = new CGImagePropertyExif("SensingMethodKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif FileSource = new CGImagePropertyExif("FileSourceKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif SceneType = new CGImagePropertyExif("SceneTypeKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif CFAPattern = new CGImagePropertyExif("CFAPatternKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif CustomRendered = new CGImagePropertyExif("CustomRenderedKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif ExposureMode = new CGImagePropertyExif("ExposureModeKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif WhiteBalance = new CGImagePropertyExif("WhiteBalanceKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif DigitalZoomRatio = new CGImagePropertyExif("DigitalZoomRatioKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif FocalLenIn35mmFilm = new CGImagePropertyExif("FocalLenIn35mmFilmKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif SceneCaptureType = new CGImagePropertyExif("SceneCaptureTypeKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif GainControl = new CGImagePropertyExif("GainControlKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif Contrast = new CGImagePropertyExif("ContrastKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif Saturation = new CGImagePropertyExif("SaturationKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif Sharpness = new CGImagePropertyExif("SharpnessKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif DeviceSettingDescription = new CGImagePropertyExif("DeviceSettingDescriptionKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif SubjectDistRange = new CGImagePropertyExif("SubjectDistRangeKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif ImageUniqueID = new CGImagePropertyExif("ImageUniqueIDKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif Gamma = new CGImagePropertyExif("GammaKey");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CGImagePropertyExif LensSerialNumber = new CGImagePropertyExif("LensSerialNumberKey");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CGImagePropertyExif CameraOwnerName = new CGImagePropertyExif("CameraOwnerNameKey");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CGImagePropertyExif BodySerialNumber = new CGImagePropertyExif("BodySerialNumberKey");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CGImagePropertyExif LensSpecification = new CGImagePropertyExif("LensSpecificationKey");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CGImagePropertyExif LensMake = new CGImagePropertyExif("LensMakeKey");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CGImagePropertyExif LensModel = new CGImagePropertyExif("LensModelKey");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImagePropertyExif SensitivityType = new CGImagePropertyExif("SensitivityTypeKey");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImagePropertyExif StandardOutputSensitivity = new CGImagePropertyExif("StandardOutputSensitivityKey");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImagePropertyExif RecommendedExposureIndex = new CGImagePropertyExif("RecommendedExposureIndexKey");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImagePropertyExif ISOSpeed = new CGImagePropertyExif("ISOSpeedKey");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImagePropertyExif ISOSpeedLatitudeyyy = new CGImagePropertyExif("ISOSpeedLatitudeyyyKey");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImagePropertyExif ISOSpeedLatitudezzz = new CGImagePropertyExif("ISOSpeedLatitudezzzKey");

    
    private static CGImagePropertyExif[] values = new CGImagePropertyExif[] {ExposureTime, FNumber, ExposureProgram, SpectralSensitivity, 
        ISOSpeedRatings, OECF, Version, DateTimeOriginal, DateTimeDigitized, ComponentsConfiguration, CompressedBitsPerPixel, ShutterSpeedValue, 
        ApertureValue, BrightnessValue, ExposureBiasValue, MaxApertureValue, SubjectDistance, MeteringMode, LightSource, Flash, FocalLength, 
        SubjectArea, MakerNote, UserComment, SubsecTime, SubsecTimeOrginal, SubsecTimeDigitized, FlashPixVersion, ColorSpace, PixelXDimension, 
        PixelYDimension, RelatedSoundFile, FlashEnergy, SpatialFrequencyResponse, FocalPlaneXResolution, FocalPlaneYResolution, FocalPlaneResolutionUnit, 
        SubjectLocation, ExposureIndex, SensingMethod, FileSource, SceneType, CFAPattern, CustomRendered, ExposureMode, WhiteBalance, DigitalZoomRatio, 
        FocalLenIn35mmFilm, SceneCaptureType, GainControl, Contrast, Saturation, Sharpness, DeviceSettingDescription, SubjectDistRange, ImageUniqueID, 
        Gamma, LensSerialNumber, CameraOwnerName, BodySerialNumber, LensSpecification, LensMake, LensModel, SensitivityType, StandardOutputSensitivity, 
        RecommendedExposureIndex, ISOSpeed, ISOSpeedLatitudeyyy, ISOSpeedLatitudezzz};
    private final LazyGlobalValue<CFString> lazyGlobalValue;
    
    private CGImagePropertyExif(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFString value() {
        return lazyGlobalValue.value();
    }
    
    public static CGImagePropertyExif valueOf(CFString value) {
        for (CGImagePropertyExif v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CGImagePropertyExif/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifExposureTime", optional=true)
    protected static native CFString ExposureTimeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifFNumber", optional=true)
    protected static native CFString FNumberKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifExposureProgram", optional=true)
    protected static native CFString ExposureProgramKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifSpectralSensitivity", optional=true)
    protected static native CFString SpectralSensitivityKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifISOSpeedRatings", optional=true)
    protected static native CFString ISOSpeedRatingsKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifOECF", optional=true)
    protected static native CFString OECFKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifSensitivityType", optional=true)
    protected static native CFString SensitivityTypeKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifStandardOutputSensitivity", optional=true)
    protected static native CFString StandardOutputSensitivityKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifRecommendedExposureIndex", optional=true)
    protected static native CFString RecommendedExposureIndexKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifISOSpeed", optional=true)
    protected static native CFString ISOSpeedKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifISOSpeedLatitudeyyy", optional=true)
    protected static native CFString ISOSpeedLatitudeyyyKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifISOSpeedLatitudezzz", optional=true)
    protected static native CFString ISOSpeedLatitudezzzKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifVersion", optional=true)
    protected static native CFString VersionKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifDateTimeOriginal", optional=true)
    protected static native CFString DateTimeOriginalKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifDateTimeDigitized", optional=true)
    protected static native CFString DateTimeDigitizedKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifComponentsConfiguration", optional=true)
    protected static native CFString ComponentsConfigurationKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifCompressedBitsPerPixel", optional=true)
    protected static native CFString CompressedBitsPerPixelKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifShutterSpeedValue", optional=true)
    protected static native CFString ShutterSpeedValueKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifApertureValue", optional=true)
    protected static native CFString ApertureValueKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifBrightnessValue", optional=true)
    protected static native CFString BrightnessValueKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifExposureBiasValue", optional=true)
    protected static native CFString ExposureBiasValueKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifMaxApertureValue", optional=true)
    protected static native CFString MaxApertureValueKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifSubjectDistance", optional=true)
    protected static native CFString SubjectDistanceKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifMeteringMode", optional=true)
    protected static native CFString MeteringModeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifLightSource", optional=true)
    protected static native CFString LightSourceKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifFlash", optional=true)
    protected static native CFString FlashKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifFocalLength", optional=true)
    protected static native CFString FocalLengthKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifSubjectArea", optional=true)
    protected static native CFString SubjectAreaKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifMakerNote", optional=true)
    protected static native CFString MakerNoteKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifUserComment", optional=true)
    protected static native CFString UserCommentKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifSubsecTime", optional=true)
    protected static native CFString SubsecTimeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifSubsecTimeOrginal", optional=true)
    protected static native CFString SubsecTimeOrginalKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifSubsecTimeDigitized", optional=true)
    protected static native CFString SubsecTimeDigitizedKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifFlashPixVersion", optional=true)
    protected static native CFString FlashPixVersionKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifColorSpace", optional=true)
    protected static native CFString ColorSpaceKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifPixelXDimension", optional=true)
    protected static native CFString PixelXDimensionKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifPixelYDimension", optional=true)
    protected static native CFString PixelYDimensionKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifRelatedSoundFile", optional=true)
    protected static native CFString RelatedSoundFileKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifFlashEnergy", optional=true)
    protected static native CFString FlashEnergyKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifSpatialFrequencyResponse", optional=true)
    protected static native CFString SpatialFrequencyResponseKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifFocalPlaneXResolution", optional=true)
    protected static native CFString FocalPlaneXResolutionKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifFocalPlaneYResolution", optional=true)
    protected static native CFString FocalPlaneYResolutionKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifFocalPlaneResolutionUnit", optional=true)
    protected static native CFString FocalPlaneResolutionUnitKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifSubjectLocation", optional=true)
    protected static native CFString SubjectLocationKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifExposureIndex", optional=true)
    protected static native CFString ExposureIndexKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifSensingMethod", optional=true)
    protected static native CFString SensingMethodKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifFileSource", optional=true)
    protected static native CFString FileSourceKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifSceneType", optional=true)
    protected static native CFString SceneTypeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifCFAPattern", optional=true)
    protected static native CFString CFAPatternKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifCustomRendered", optional=true)
    protected static native CFString CustomRenderedKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifExposureMode", optional=true)
    protected static native CFString ExposureModeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifWhiteBalance", optional=true)
    protected static native CFString WhiteBalanceKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifDigitalZoomRatio", optional=true)
    protected static native CFString DigitalZoomRatioKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifFocalLenIn35mmFilm", optional=true)
    protected static native CFString FocalLenIn35mmFilmKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifSceneCaptureType", optional=true)
    protected static native CFString SceneCaptureTypeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifGainControl", optional=true)
    protected static native CFString GainControlKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifContrast", optional=true)
    protected static native CFString ContrastKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifSaturation", optional=true)
    protected static native CFString SaturationKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifSharpness", optional=true)
    protected static native CFString SharpnessKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifDeviceSettingDescription", optional=true)
    protected static native CFString DeviceSettingDescriptionKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifSubjectDistRange", optional=true)
    protected static native CFString SubjectDistRangeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifImageUniqueID", optional=true)
    protected static native CFString ImageUniqueIDKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifCameraOwnerName", optional=true)
    protected static native CFString CameraOwnerNameKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifBodySerialNumber", optional=true)
    protected static native CFString BodySerialNumberKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifLensSpecification", optional=true)
    protected static native CFString LensSpecificationKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifLensMake", optional=true)
    protected static native CFString LensMakeKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifLensModel", optional=true)
    protected static native CFString LensModelKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifLensSerialNumber", optional=true)
    protected static native CFString LensSerialNumberKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifGamma", optional=true)
    protected static native CFString GammaKey();
    /*</methods>*/
}
