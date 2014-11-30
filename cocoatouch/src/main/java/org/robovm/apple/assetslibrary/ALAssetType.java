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
package org.robovm.apple.assetslibrary;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.imageio.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(ALAssetType.Marshaler.class)
/*<annotations>*/@Library("AssetsLibrary")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ALAssetType/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static ALAssetType toObject(Class<ALAssetType> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return ALAssetType.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(ALAssetType o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(ALAssetType.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final ALAssetType Photo = new ALAssetType("PhotoValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final ALAssetType Video = new ALAssetType("VideoValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final ALAssetType Unknown = new ALAssetType("UnknownValue");
    
    private static ALAssetType[] values = new ALAssetType[] {Photo, Video, Unknown};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private ALAssetType(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static ALAssetType valueOf(NSString value) {
        for (ALAssetType v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/ALAssetType/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="ALAssetTypePhoto", optional=true)
    protected static native NSString PhotoValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="ALAssetTypeVideo", optional=true)
    protected static native NSString VideoValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="ALAssetTypeUnknown", optional=true)
    protected static native NSString UnknownValue();
    /*</methods>*/
}
