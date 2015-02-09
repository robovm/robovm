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
@Marshaler(CGImagePropertyNikon.Marshaler.class)
/*<annotations>*/@Library("ImageIO")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImagePropertyNikon/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @MarshalsPointer
        public static CGImagePropertyNikon toObject(Class<CGImagePropertyNikon> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CGImagePropertyNikon.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CGImagePropertyNikon o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CGImagePropertyNikon.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyNikon ISOSetting = new CGImagePropertyNikon("ISOSettingKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyNikon ColorMode = new CGImagePropertyNikon("ColorModeKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyNikon Quality = new CGImagePropertyNikon("QualityKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyNikon WhiteBalanceMode = new CGImagePropertyNikon("WhiteBalanceModeKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyNikon SharpenMode = new CGImagePropertyNikon("SharpenModeKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyNikon FocusMode = new CGImagePropertyNikon("FocusModeKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyNikon FlashSetting = new CGImagePropertyNikon("FlashSettingKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyNikon ISOSelection = new CGImagePropertyNikon("ISOSelectionKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyNikon FlashExposureComp = new CGImagePropertyNikon("FlashExposureCompKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyNikon ImageAdjustment = new CGImagePropertyNikon("ImageAdjustmentKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyNikon LensAdapter = new CGImagePropertyNikon("LensAdapterKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyNikon LensType = new CGImagePropertyNikon("LensTypeKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyNikon LensInfo = new CGImagePropertyNikon("LensInfoKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyNikon FocusDistance = new CGImagePropertyNikon("FocusDistanceKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyNikon DigitalZoom = new CGImagePropertyNikon("DigitalZoomKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyNikon ShootingMode = new CGImagePropertyNikon("ShootingModeKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyNikon CameraSerialNumber = new CGImagePropertyNikon("CameraSerialNumberKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyNikon ShutterCount = new CGImagePropertyNikon("ShutterCountKey");
    
    private static CGImagePropertyNikon[] values = new CGImagePropertyNikon[] {ISOSetting, ColorMode, Quality, 
        WhiteBalanceMode, SharpenMode, FocusMode, FlashSetting, ISOSelection, FlashExposureComp, ImageAdjustment, 
        LensAdapter, LensType, LensInfo, FocusDistance, DigitalZoom, ShootingMode, CameraSerialNumber, ShutterCount};
    private final LazyGlobalValue<CFString> lazyGlobalValue;
    
    private CGImagePropertyNikon(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFString value() {
        return lazyGlobalValue.value();
    }
    
    public static CGImagePropertyNikon valueOf(CFString value) {
        for (CGImagePropertyNikon v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CGImagePropertyNikon/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyMakerNikonISOSetting", optional=true)
    protected static native CFString ISOSettingKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyMakerNikonColorMode", optional=true)
    protected static native CFString ColorModeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyMakerNikonQuality", optional=true)
    protected static native CFString QualityKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyMakerNikonWhiteBalanceMode", optional=true)
    protected static native CFString WhiteBalanceModeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyMakerNikonSharpenMode", optional=true)
    protected static native CFString SharpenModeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyMakerNikonFocusMode", optional=true)
    protected static native CFString FocusModeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyMakerNikonFlashSetting", optional=true)
    protected static native CFString FlashSettingKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyMakerNikonISOSelection", optional=true)
    protected static native CFString ISOSelectionKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyMakerNikonFlashExposureComp", optional=true)
    protected static native CFString FlashExposureCompKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyMakerNikonImageAdjustment", optional=true)
    protected static native CFString ImageAdjustmentKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyMakerNikonLensAdapter", optional=true)
    protected static native CFString LensAdapterKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyMakerNikonLensType", optional=true)
    protected static native CFString LensTypeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyMakerNikonLensInfo", optional=true)
    protected static native CFString LensInfoKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyMakerNikonFocusDistance", optional=true)
    protected static native CFString FocusDistanceKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyMakerNikonDigitalZoom", optional=true)
    protected static native CFString DigitalZoomKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyMakerNikonShootingMode", optional=true)
    protected static native CFString ShootingModeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyMakerNikonCameraSerialNumber", optional=true)
    protected static native CFString CameraSerialNumberKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyMakerNikonShutterCount", optional=true)
    protected static native CFString ShutterCountKey();
    /*</methods>*/
}
