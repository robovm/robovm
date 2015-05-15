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
@Marshaler(ALAssetsGroupProperty.Marshaler.class)
/*<annotations>*/@Library("AssetsLibrary")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ALAssetsGroupProperty/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static ALAssetsGroupProperty toObject(Class<ALAssetsGroupProperty> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return ALAssetsGroupProperty.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(ALAssetsGroupProperty o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(ALAssetsGroupProperty.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final ALAssetsGroupProperty Name = new ALAssetsGroupProperty("NameValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final ALAssetsGroupProperty Type = new ALAssetsGroupProperty("TypeValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final ALAssetsGroupProperty PersistentID = new ALAssetsGroupProperty("PersistentIDValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final ALAssetsGroupProperty URL = new ALAssetsGroupProperty("URLValue");
    
    private static ALAssetsGroupProperty[] values = new ALAssetsGroupProperty[] {Name, Type, PersistentID, URL};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private ALAssetsGroupProperty(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static ALAssetsGroupProperty valueOf(NSString value) {
        for (ALAssetsGroupProperty v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/ALAssetsGroupProperty/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="ALAssetsGroupPropertyName", optional=true)
    protected static native NSString NameValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="ALAssetsGroupPropertyType", optional=true)
    protected static native NSString TypeValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="ALAssetsGroupPropertyPersistentID", optional=true)
    protected static native NSString PersistentIDValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="ALAssetsGroupPropertyURL", optional=true)
    protected static native NSString URLValue();
    /*</methods>*/
}
