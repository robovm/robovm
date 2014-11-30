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
@Marshaler(ALAssetProperty.Marshaler.class)
/*<annotations>*/@Library("AssetsLibrary")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ALAssetProperty/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @MarshalsPointer
        public static ALAssetProperty toObject(Class<ALAssetProperty> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return ALAssetProperty.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(ALAssetProperty o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(ALAssetProperty.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final ALAssetProperty Type = new ALAssetProperty("TypeValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final ALAssetProperty Location = new ALAssetProperty("LocationValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final ALAssetProperty Duration = new ALAssetProperty("DurationValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final ALAssetProperty Orientation = new ALAssetProperty("OrientationValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final ALAssetProperty Date = new ALAssetProperty("DateValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final ALAssetProperty Representations = new ALAssetProperty("RepresentationsValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final ALAssetProperty URLs = new ALAssetProperty("URLsValue");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final ALAssetProperty AssetURL = new ALAssetProperty("AssetURLValue");
    
    private static ALAssetProperty[] values = new ALAssetProperty[] {Type, Location, Duration, Orientation, Date, 
        Representations, URLs, AssetURL};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private ALAssetProperty(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static ALAssetProperty valueOf(NSString value) {
        for (ALAssetProperty v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/ALAssetProperty/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="ALAssetPropertyType", optional=true)
    protected static native NSString TypeValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="ALAssetPropertyLocation", optional=true)
    protected static native NSString LocationValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="ALAssetPropertyDuration", optional=true)
    protected static native NSString DurationValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="ALAssetPropertyOrientation", optional=true)
    protected static native NSString OrientationValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="ALAssetPropertyDate", optional=true)
    protected static native NSString DateValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="ALAssetPropertyRepresentations", optional=true)
    protected static native NSString RepresentationsValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="ALAssetPropertyURLs", optional=true)
    protected static native NSString URLsValue();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="ALAssetPropertyAssetURL", optional=true)
    protected static native NSString AssetURLValue();
    /*</methods>*/
}
