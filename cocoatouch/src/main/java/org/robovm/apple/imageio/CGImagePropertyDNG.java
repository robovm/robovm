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
@Marshaler(CGImagePropertyDNG.Marshaler.class)
/*<annotations>*/@Library("ImageIO")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImagePropertyDNG/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @MarshalsPointer
        public static CGImagePropertyDNG toObject(Class<CGImagePropertyDNG> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CGImagePropertyDNG.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CGImagePropertyDNG o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CGImagePropertyDNG.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyDNG Version = new CGImagePropertyDNG("VersionKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyDNG BackwardVersion = new CGImagePropertyDNG("BackwardVersionKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyDNG UniqueCameraModel = new CGImagePropertyDNG("UniqueCameraModelKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyDNG LocalizedCameraModel = new CGImagePropertyDNG("LocalizedCameraModelKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyDNG CameraSerialNumber = new CGImagePropertyDNG("CameraSerialNumberKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyDNG LensInfo = new CGImagePropertyDNG("LensInfoKey");
    
    private static CGImagePropertyDNG[] values = new CGImagePropertyDNG[] {Version, BackwardVersion, UniqueCameraModel, LocalizedCameraModel, 
        CameraSerialNumber, LensInfo};
    private final LazyGlobalValue<CFString> lazyGlobalValue;
    
    private CGImagePropertyDNG(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFString value() {
        return lazyGlobalValue.value();
    }
    
    public static CGImagePropertyDNG valueOf(CFString value) {
        for (CGImagePropertyDNG v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CGImagePropertyDNG/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyDNGVersion", optional=true)
    protected static native CFString VersionKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyDNGBackwardVersion", optional=true)
    protected static native CFString BackwardVersionKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyDNGUniqueCameraModel", optional=true)
    protected static native CFString UniqueCameraModelKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyDNGLocalizedCameraModel", optional=true)
    protected static native CFString LocalizedCameraModelKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyDNGCameraSerialNumber", optional=true)
    protected static native CFString CameraSerialNumberKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyDNGLensInfo", optional=true)
    protected static native CFString LensInfoKey();
    /*</methods>*/
}
