/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
@Marshaler(CGImagePropertyCanon.Marshaler.class)
/*<annotations>*/@Library("ImageIO")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImagePropertyCanon/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static CGImagePropertyCanon toObject(Class<CGImagePropertyCanon> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CGImagePropertyCanon.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CGImagePropertyCanon o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CGImagePropertyCanon.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyCanon OwnerName = new CGImagePropertyCanon("OwnerNameKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyCanon CameraSerialNumber = new CGImagePropertyCanon("CameraSerialNumberKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyCanon ImageSerialNumber = new CGImagePropertyCanon("ImageSerialNumberKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyCanon FlashExposureComp = new CGImagePropertyCanon("FlashExposureCompKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyCanon ContinuousDrive = new CGImagePropertyCanon("ContinuousDriveKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyCanon LensModel = new CGImagePropertyCanon("LensModelKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyCanon Firmware = new CGImagePropertyCanon("FirmwareKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyCanon AspectRatioInfo = new CGImagePropertyCanon("AspectRatioInfoKey");
    
    private static CGImagePropertyCanon[] values = new CGImagePropertyCanon[] {OwnerName, CameraSerialNumber, ImageSerialNumber, 
        FlashExposureComp, ContinuousDrive, LensModel, Firmware, AspectRatioInfo};
    private final LazyGlobalValue<CFString> lazyGlobalValue;
    
    private CGImagePropertyCanon(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFString value() {
        return lazyGlobalValue.value();
    }
    
    public static CGImagePropertyCanon valueOf(CFString value) {
        for (CGImagePropertyCanon v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CGImagePropertyCanon/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyMakerCanonOwnerName", optional=true)
    protected static native CFString OwnerNameKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyMakerCanonCameraSerialNumber", optional=true)
    protected static native CFString CameraSerialNumberKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyMakerCanonImageSerialNumber", optional=true)
    protected static native CFString ImageSerialNumberKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyMakerCanonFlashExposureComp", optional=true)
    protected static native CFString FlashExposureCompKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyMakerCanonContinuousDrive", optional=true)
    protected static native CFString ContinuousDriveKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyMakerCanonLensModel", optional=true)
    protected static native CFString LensModelKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyMakerCanonFirmware", optional=true)
    protected static native CFString FirmwareKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyMakerCanonAspectRatioInfo", optional=true)
    protected static native CFString AspectRatioInfoKey();
    /*</methods>*/
}
