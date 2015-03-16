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
package org.robovm.apple.coreimage;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.imageio.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(CIDetectorType.Marshaler.class)
/*<annotations>*/@Library("CoreImage")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CIDetectorType/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @MarshalsPointer
        public static CIDetectorType toObject(Class<CIDetectorType> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CIDetectorType.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CIDetectorType o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CIDetectorType.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CIDetectorType Face = new CIDetectorType("FaceValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CIDetectorType Rectangle = new CIDetectorType("RectangleValue");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final CIDetectorType QRCode = new CIDetectorType("QRCodeValue");
    
    private static CIDetectorType[] values = new CIDetectorType[] {Face, QRCode, Rectangle};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private CIDetectorType(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static CIDetectorType valueOf(NSString value) {
        for (CIDetectorType v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CIDetectorType/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="CIDetectorTypeFace", optional=true)
    protected static native NSString FaceValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="CIDetectorTypeRectangle", optional=true)
    protected static native NSString RectangleValue();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="CIDetectorTypeQRCode", optional=true)
    protected static native NSString QRCodeValue();
    /*</methods>*/
}
