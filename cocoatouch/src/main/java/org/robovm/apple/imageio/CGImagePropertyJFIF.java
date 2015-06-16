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
@Marshaler(CGImagePropertyJFIF.Marshaler.class)
/*<annotations>*/@Library("ImageIO")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImagePropertyJFIF/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static CGImagePropertyJFIF toObject(Class<CGImagePropertyJFIF> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CGImagePropertyJFIF.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CGImagePropertyJFIF o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CGImagePropertyJFIF.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyJFIF Version = new CGImagePropertyJFIF("VersionKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyJFIF XDensity = new CGImagePropertyJFIF("XDensityKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyJFIF YDensity = new CGImagePropertyJFIF("YDensityKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyJFIF DensityUnit = new CGImagePropertyJFIF("DensityUnitKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyJFIF IsProgressive = new CGImagePropertyJFIF("IsProgressiveKey");
    
    private static CGImagePropertyJFIF[] values = new CGImagePropertyJFIF[] {Version, XDensity, YDensity, DensityUnit, IsProgressive};
    private final LazyGlobalValue<CFString> lazyGlobalValue;
    
    private CGImagePropertyJFIF(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFString value() {
        return lazyGlobalValue.value();
    }
    
    public static CGImagePropertyJFIF valueOf(CFString value) {
        for (CGImagePropertyJFIF v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CGImagePropertyJFIF/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyJFIFVersion", optional=true)
    protected static native CFString VersionKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyJFIFXDensity", optional=true)
    protected static native CFString XDensityKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyJFIFYDensity", optional=true)
    protected static native CFString YDensityKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyJFIFDensityUnit", optional=true)
    protected static native CFString DensityUnitKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyJFIFIsProgressive", optional=true)
    protected static native CFString IsProgressiveKey();
    /*</methods>*/
}
