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
@Marshaler(CGImagePropertyExifAux.Marshaler.class)
/*<annotations>*/@Library("ImageIO")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImagePropertyExifAux/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static CGImagePropertyExifAux toObject(Class<CGImagePropertyExifAux> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CGImagePropertyExifAux.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CGImagePropertyExifAux o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CGImagePropertyExifAux.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExifAux LensInfo = new CGImagePropertyExifAux("LensInfoKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExifAux LensModel = new CGImagePropertyExifAux("LensModelKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExifAux SerialNumber = new CGImagePropertyExifAux("SerialNumberKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExifAux LensID = new CGImagePropertyExifAux("LensIDKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExifAux LensSerialNumber = new CGImagePropertyExifAux("LensSerialNumberKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExifAux ImageNumber = new CGImagePropertyExifAux("ImageNumberKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExifAux FlashCompensation = new CGImagePropertyExifAux("FlashCompensationKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExifAux OwnerName = new CGImagePropertyExifAux("OwnerNameKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExifAux Firmware = new CGImagePropertyExifAux("FirmwareKey");
    
    private static CGImagePropertyExifAux[] values = new CGImagePropertyExifAux[] {LensInfo, LensModel, SerialNumber, 
        LensID, LensSerialNumber, ImageNumber, FlashCompensation, OwnerName, Firmware};
    private final LazyGlobalValue<CFString> lazyGlobalValue;
    
    private CGImagePropertyExifAux(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFString value() {
        return lazyGlobalValue.value();
    }
    
    public static CGImagePropertyExifAux valueOf(CFString value) {
        for (CGImagePropertyExifAux v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CGImagePropertyExifAux/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifAuxLensInfo", optional=true)
    protected static native CFString LensInfoKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifAuxLensModel", optional=true)
    protected static native CFString LensModelKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifAuxSerialNumber", optional=true)
    protected static native CFString SerialNumberKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifAuxLensID", optional=true)
    protected static native CFString LensIDKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifAuxLensSerialNumber", optional=true)
    protected static native CFString LensSerialNumberKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifAuxImageNumber", optional=true)
    protected static native CFString ImageNumberKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifAuxFlashCompensation", optional=true)
    protected static native CFString FlashCompensationKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifAuxOwnerName", optional=true)
    protected static native CFString OwnerNameKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifAuxFirmware", optional=true)
    protected static native CFString FirmwareKey();
    /*</methods>*/
}
