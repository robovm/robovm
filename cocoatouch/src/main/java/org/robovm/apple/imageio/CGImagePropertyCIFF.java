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
package org.robovm.apple.imageio;

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
@Marshaler(CGImagePropertyCIFF.Marshaler.class)
/*<annotations>*/@Library("ImageIO")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImagePropertyCIFF/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static CGImagePropertyCIFF toObject(Class<CGImagePropertyCIFF> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CGImagePropertyCIFF.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CGImagePropertyCIFF o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CGImagePropertyCIFF.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyCIFF Description = new CGImagePropertyCIFF("DescriptionKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyCIFF Firmware = new CGImagePropertyCIFF("FirmwareKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyCIFF OwnerName = new CGImagePropertyCIFF("OwnerNameKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyCIFF ImageName = new CGImagePropertyCIFF("ImageNameKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyCIFF ImageFileName = new CGImagePropertyCIFF("ImageFileNameKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyCIFF ReleaseMethod = new CGImagePropertyCIFF("ReleaseMethodKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyCIFF ReleaseTiming = new CGImagePropertyCIFF("ReleaseTimingKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyCIFF RecordID = new CGImagePropertyCIFF("RecordIDKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyCIFF SelfTimingTime = new CGImagePropertyCIFF("SelfTimingTimeKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyCIFF CameraSerialNumber = new CGImagePropertyCIFF("CameraSerialNumberKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyCIFF ImageSerialNumber = new CGImagePropertyCIFF("ImageSerialNumberKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyCIFF ContinuousDrive = new CGImagePropertyCIFF("ContinuousDriveKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyCIFF FocusMode = new CGImagePropertyCIFF("FocusModeKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyCIFF MeteringMode = new CGImagePropertyCIFF("MeteringModeKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyCIFF ShootingMode = new CGImagePropertyCIFF("ShootingModeKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyCIFF LensModel = new CGImagePropertyCIFF("LensModelKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyCIFF LensMaxMM = new CGImagePropertyCIFF("LensMaxMMKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyCIFF LensMinMM = new CGImagePropertyCIFF("LensMinMMKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyCIFF WhiteBalanceIndex = new CGImagePropertyCIFF("WhiteBalanceIndexKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyCIFF FlashExposureComp = new CGImagePropertyCIFF("FlashExposureCompKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyCIFF MeasuredEV = new CGImagePropertyCIFF("MeasuredEVKey");
    
    private static CGImagePropertyCIFF[] values = new CGImagePropertyCIFF[] {Description, Firmware, OwnerName, ImageName, 
        ImageFileName, ReleaseMethod, ReleaseTiming, RecordID, SelfTimingTime, CameraSerialNumber, ImageSerialNumber, 
        ContinuousDrive, FocusMode, MeteringMode, ShootingMode, LensModel, LensMaxMM, LensMinMM, WhiteBalanceIndex, 
        FlashExposureComp, MeasuredEV};
    private final LazyGlobalValue<CFString> lazyGlobalValue;
    
    private CGImagePropertyCIFF(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFString value() {
        return lazyGlobalValue.value();
    }
    
    public static CGImagePropertyCIFF valueOf(CFString value) {
        for (CGImagePropertyCIFF v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CGImagePropertyCIFF/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyCIFFDescription", optional=true)
    protected static native CFString DescriptionKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyCIFFFirmware", optional=true)
    protected static native CFString FirmwareKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyCIFFOwnerName", optional=true)
    protected static native CFString OwnerNameKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyCIFFImageName", optional=true)
    protected static native CFString ImageNameKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyCIFFImageFileName", optional=true)
    protected static native CFString ImageFileNameKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyCIFFReleaseMethod", optional=true)
    protected static native CFString ReleaseMethodKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyCIFFReleaseTiming", optional=true)
    protected static native CFString ReleaseTimingKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyCIFFRecordID", optional=true)
    protected static native CFString RecordIDKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyCIFFSelfTimingTime", optional=true)
    protected static native CFString SelfTimingTimeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyCIFFCameraSerialNumber", optional=true)
    protected static native CFString CameraSerialNumberKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyCIFFImageSerialNumber", optional=true)
    protected static native CFString ImageSerialNumberKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyCIFFContinuousDrive", optional=true)
    protected static native CFString ContinuousDriveKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyCIFFFocusMode", optional=true)
    protected static native CFString FocusModeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyCIFFMeteringMode", optional=true)
    protected static native CFString MeteringModeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyCIFFShootingMode", optional=true)
    protected static native CFString ShootingModeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyCIFFLensModel", optional=true)
    protected static native CFString LensModelKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyCIFFLensMaxMM", optional=true)
    protected static native CFString LensMaxMMKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyCIFFLensMinMM", optional=true)
    protected static native CFString LensMinMMKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyCIFFWhiteBalanceIndex", optional=true)
    protected static native CFString WhiteBalanceIndexKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyCIFFFlashExposureComp", optional=true)
    protected static native CFString FlashExposureCompKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyCIFFMeasuredEV", optional=true)
    protected static native CFString MeasuredEVKey();
    /*</methods>*/
}
