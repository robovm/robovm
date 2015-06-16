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
/*<annotations>*/@Library("ImageIO") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/CGImagePropertyExif/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImagePropertyExif/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CGImagePropertyExif/*</name>*/.class); }

    /*<marshalers>*/
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
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CGImagePropertyExif> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CGImagePropertyExif> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CGImagePropertyExif.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CGImagePropertyExif> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CGImagePropertyExif o : l) {
                array.add(o.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif ExposureTime = new CGImagePropertyExif("ExposureTime");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif FNumber = new CGImagePropertyExif("FNumber");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif ExposureProgram = new CGImagePropertyExif("ExposureProgram");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif SpectralSensitivity = new CGImagePropertyExif("SpectralSensitivity");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif ISOSpeedRatings = new CGImagePropertyExif("ISOSpeedRatings");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif OECF = new CGImagePropertyExif("OECF");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImagePropertyExif SensitivityType = new CGImagePropertyExif("SensitivityType");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImagePropertyExif StandardOutputSensitivity = new CGImagePropertyExif("StandardOutputSensitivity");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImagePropertyExif RecommendedExposureIndex = new CGImagePropertyExif("RecommendedExposureIndex");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImagePropertyExif ISOSpeed = new CGImagePropertyExif("ISOSpeed");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImagePropertyExif ISOSpeedLatitudeyyy = new CGImagePropertyExif("ISOSpeedLatitudeyyy");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImagePropertyExif ISOSpeedLatitudezzz = new CGImagePropertyExif("ISOSpeedLatitudezzz");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif Version = new CGImagePropertyExif("Version");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif DateTimeOriginal = new CGImagePropertyExif("DateTimeOriginal");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif DateTimeDigitized = new CGImagePropertyExif("DateTimeDigitized");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif ComponentsConfiguration = new CGImagePropertyExif("ComponentsConfiguration");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif CompressedBitsPerPixel = new CGImagePropertyExif("CompressedBitsPerPixel");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif ShutterSpeedValue = new CGImagePropertyExif("ShutterSpeedValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif ApertureValue = new CGImagePropertyExif("ApertureValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif BrightnessValue = new CGImagePropertyExif("BrightnessValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif ExposureBiasValue = new CGImagePropertyExif("ExposureBiasValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif MaxApertureValue = new CGImagePropertyExif("MaxApertureValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif SubjectDistance = new CGImagePropertyExif("SubjectDistance");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif MeteringMode = new CGImagePropertyExif("MeteringMode");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif LightSource = new CGImagePropertyExif("LightSource");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif Flash = new CGImagePropertyExif("Flash");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif FocalLength = new CGImagePropertyExif("FocalLength");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif SubjectArea = new CGImagePropertyExif("SubjectArea");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif MakerNote = new CGImagePropertyExif("MakerNote");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif UserComment = new CGImagePropertyExif("UserComment");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif SubsecTime = new CGImagePropertyExif("SubsecTime");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif SubsecTimeOrginal = new CGImagePropertyExif("SubsecTimeOrginal");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif SubsecTimeDigitized = new CGImagePropertyExif("SubsecTimeDigitized");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif FlashPixVersion = new CGImagePropertyExif("FlashPixVersion");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif ColorSpace = new CGImagePropertyExif("ColorSpace");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif PixelXDimension = new CGImagePropertyExif("PixelXDimension");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif PixelYDimension = new CGImagePropertyExif("PixelYDimension");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif RelatedSoundFile = new CGImagePropertyExif("RelatedSoundFile");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif FlashEnergy = new CGImagePropertyExif("FlashEnergy");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif SpatialFrequencyResponse = new CGImagePropertyExif("SpatialFrequencyResponse");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif FocalPlaneXResolution = new CGImagePropertyExif("FocalPlaneXResolution");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif FocalPlaneYResolution = new CGImagePropertyExif("FocalPlaneYResolution");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif FocalPlaneResolutionUnit = new CGImagePropertyExif("FocalPlaneResolutionUnit");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif SubjectLocation = new CGImagePropertyExif("SubjectLocation");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif ExposureIndex = new CGImagePropertyExif("ExposureIndex");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif SensingMethod = new CGImagePropertyExif("SensingMethod");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif FileSource = new CGImagePropertyExif("FileSource");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif SceneType = new CGImagePropertyExif("SceneType");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif CFAPattern = new CGImagePropertyExif("CFAPattern");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif CustomRendered = new CGImagePropertyExif("CustomRendered");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif ExposureMode = new CGImagePropertyExif("ExposureMode");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif WhiteBalance = new CGImagePropertyExif("WhiteBalance");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif DigitalZoomRatio = new CGImagePropertyExif("DigitalZoomRatio");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif FocalLenIn35mmFilm = new CGImagePropertyExif("FocalLenIn35mmFilm");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif SceneCaptureType = new CGImagePropertyExif("SceneCaptureType");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif GainControl = new CGImagePropertyExif("GainControl");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif Contrast = new CGImagePropertyExif("Contrast");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif Saturation = new CGImagePropertyExif("Saturation");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif Sharpness = new CGImagePropertyExif("Sharpness");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif DeviceSettingDescription = new CGImagePropertyExif("DeviceSettingDescription");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif SubjectDistRange = new CGImagePropertyExif("SubjectDistRange");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif ImageUniqueID = new CGImagePropertyExif("ImageUniqueID");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CGImagePropertyExif CameraOwnerName = new CGImagePropertyExif("CameraOwnerName");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CGImagePropertyExif BodySerialNumber = new CGImagePropertyExif("BodySerialNumber");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CGImagePropertyExif LensSpecification = new CGImagePropertyExif("LensSpecification");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CGImagePropertyExif LensMake = new CGImagePropertyExif("LensMake");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CGImagePropertyExif LensModel = new CGImagePropertyExif("LensModel");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CGImagePropertyExif LensSerialNumber = new CGImagePropertyExif("LensSerialNumber");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExif Gamma = new CGImagePropertyExif("Gamma");
    /*</constants>*/
    
    private static /*<name>*/CGImagePropertyExif/*</name>*/[] values = new /*<name>*/CGImagePropertyExif/*</name>*/[] {/*<value_list>*/ExposureTime, FNumber, ExposureProgram, SpectralSensitivity, ISOSpeedRatings, OECF, SensitivityType, StandardOutputSensitivity, RecommendedExposureIndex, ISOSpeed, ISOSpeedLatitudeyyy, ISOSpeedLatitudezzz, Version, DateTimeOriginal, DateTimeDigitized, ComponentsConfiguration, CompressedBitsPerPixel, ShutterSpeedValue, ApertureValue, BrightnessValue, ExposureBiasValue, MaxApertureValue, SubjectDistance, MeteringMode, LightSource, Flash, FocalLength, SubjectArea, MakerNote, UserComment, SubsecTime, SubsecTimeOrginal, SubsecTimeDigitized, FlashPixVersion, ColorSpace, PixelXDimension, PixelYDimension, RelatedSoundFile, FlashEnergy, SpatialFrequencyResponse, FocalPlaneXResolution, FocalPlaneYResolution, FocalPlaneResolutionUnit, SubjectLocation, ExposureIndex, SensingMethod, FileSource, SceneType, CFAPattern, CustomRendered, ExposureMode, WhiteBalance, DigitalZoomRatio, FocalLenIn35mmFilm, SceneCaptureType, GainControl, Contrast, Saturation, Sharpness, DeviceSettingDescription, SubjectDistRange, ImageUniqueID, CameraOwnerName, BodySerialNumber, LensSpecification, LensMake, LensModel, LensSerialNumber, Gamma/*</value_list>*/};
    
    /*<name>*/CGImagePropertyExif/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CGImagePropertyExif/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CGImagePropertyExif/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CGImagePropertyExif/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("ImageIO") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifExposureTime", optional=true)
        public static native CFString ExposureTime();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifFNumber", optional=true)
        public static native CFString FNumber();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifExposureProgram", optional=true)
        public static native CFString ExposureProgram();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifSpectralSensitivity", optional=true)
        public static native CFString SpectralSensitivity();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifISOSpeedRatings", optional=true)
        public static native CFString ISOSpeedRatings();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifOECF", optional=true)
        public static native CFString OECF();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifSensitivityType", optional=true)
        public static native CFString SensitivityType();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifStandardOutputSensitivity", optional=true)
        public static native CFString StandardOutputSensitivity();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifRecommendedExposureIndex", optional=true)
        public static native CFString RecommendedExposureIndex();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifISOSpeed", optional=true)
        public static native CFString ISOSpeed();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifISOSpeedLatitudeyyy", optional=true)
        public static native CFString ISOSpeedLatitudeyyy();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifISOSpeedLatitudezzz", optional=true)
        public static native CFString ISOSpeedLatitudezzz();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifVersion", optional=true)
        public static native CFString Version();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifDateTimeOriginal", optional=true)
        public static native CFString DateTimeOriginal();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifDateTimeDigitized", optional=true)
        public static native CFString DateTimeDigitized();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifComponentsConfiguration", optional=true)
        public static native CFString ComponentsConfiguration();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifCompressedBitsPerPixel", optional=true)
        public static native CFString CompressedBitsPerPixel();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifShutterSpeedValue", optional=true)
        public static native CFString ShutterSpeedValue();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifApertureValue", optional=true)
        public static native CFString ApertureValue();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifBrightnessValue", optional=true)
        public static native CFString BrightnessValue();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifExposureBiasValue", optional=true)
        public static native CFString ExposureBiasValue();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifMaxApertureValue", optional=true)
        public static native CFString MaxApertureValue();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifSubjectDistance", optional=true)
        public static native CFString SubjectDistance();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifMeteringMode", optional=true)
        public static native CFString MeteringMode();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifLightSource", optional=true)
        public static native CFString LightSource();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifFlash", optional=true)
        public static native CFString Flash();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifFocalLength", optional=true)
        public static native CFString FocalLength();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifSubjectArea", optional=true)
        public static native CFString SubjectArea();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifMakerNote", optional=true)
        public static native CFString MakerNote();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifUserComment", optional=true)
        public static native CFString UserComment();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifSubsecTime", optional=true)
        public static native CFString SubsecTime();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifSubsecTimeOrginal", optional=true)
        public static native CFString SubsecTimeOrginal();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifSubsecTimeDigitized", optional=true)
        public static native CFString SubsecTimeDigitized();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifFlashPixVersion", optional=true)
        public static native CFString FlashPixVersion();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifColorSpace", optional=true)
        public static native CFString ColorSpace();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifPixelXDimension", optional=true)
        public static native CFString PixelXDimension();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifPixelYDimension", optional=true)
        public static native CFString PixelYDimension();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifRelatedSoundFile", optional=true)
        public static native CFString RelatedSoundFile();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifFlashEnergy", optional=true)
        public static native CFString FlashEnergy();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifSpatialFrequencyResponse", optional=true)
        public static native CFString SpatialFrequencyResponse();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifFocalPlaneXResolution", optional=true)
        public static native CFString FocalPlaneXResolution();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifFocalPlaneYResolution", optional=true)
        public static native CFString FocalPlaneYResolution();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifFocalPlaneResolutionUnit", optional=true)
        public static native CFString FocalPlaneResolutionUnit();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifSubjectLocation", optional=true)
        public static native CFString SubjectLocation();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifExposureIndex", optional=true)
        public static native CFString ExposureIndex();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifSensingMethod", optional=true)
        public static native CFString SensingMethod();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifFileSource", optional=true)
        public static native CFString FileSource();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifSceneType", optional=true)
        public static native CFString SceneType();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifCFAPattern", optional=true)
        public static native CFString CFAPattern();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifCustomRendered", optional=true)
        public static native CFString CustomRendered();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifExposureMode", optional=true)
        public static native CFString ExposureMode();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifWhiteBalance", optional=true)
        public static native CFString WhiteBalance();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifDigitalZoomRatio", optional=true)
        public static native CFString DigitalZoomRatio();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifFocalLenIn35mmFilm", optional=true)
        public static native CFString FocalLenIn35mmFilm();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifSceneCaptureType", optional=true)
        public static native CFString SceneCaptureType();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifGainControl", optional=true)
        public static native CFString GainControl();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifContrast", optional=true)
        public static native CFString Contrast();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifSaturation", optional=true)
        public static native CFString Saturation();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifSharpness", optional=true)
        public static native CFString Sharpness();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifDeviceSettingDescription", optional=true)
        public static native CFString DeviceSettingDescription();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifSubjectDistRange", optional=true)
        public static native CFString SubjectDistRange();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifImageUniqueID", optional=true)
        public static native CFString ImageUniqueID();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifCameraOwnerName", optional=true)
        public static native CFString CameraOwnerName();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifBodySerialNumber", optional=true)
        public static native CFString BodySerialNumber();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifLensSpecification", optional=true)
        public static native CFString LensSpecification();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifLensMake", optional=true)
        public static native CFString LensMake();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifLensModel", optional=true)
        public static native CFString LensModel();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifLensSerialNumber", optional=true)
        public static native CFString LensSerialNumber();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifGamma", optional=true)
        public static native CFString Gamma();
        /*</values>*/
    }
}
