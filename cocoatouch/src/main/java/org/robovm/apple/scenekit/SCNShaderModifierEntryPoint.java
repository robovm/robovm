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
package org.robovm.apple.scenekit;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.glkit.*;
import org.robovm.apple.spritekit.*;
import org.robovm.apple.opengles.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(SCNShaderModifierEntryPoint.Marshaler.class)
/*<annotations>*/@Library("SceneKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNShaderModifierEntryPoint/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static SCNShaderModifierEntryPoint toObject(Class<SCNShaderModifierEntryPoint> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return SCNShaderModifierEntryPoint.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(SCNShaderModifierEntryPoint o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    public static class AsStringMapMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static Map<SCNShaderModifierEntryPoint, String> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSDictionary<NSString, NSString> o = (NSDictionary<NSString, NSString>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            Map<SCNShaderModifierEntryPoint, String> map = new HashMap<>();
            for (Map.Entry<NSString, NSString> e : o.entrySet()) {
                map.put(SCNShaderModifierEntryPoint.valueOf(e.getKey()), e.getValue().toString());
            }
            
            return map;
        }
        @MarshalsPointer
        public static long toNative(Map<SCNShaderModifierEntryPoint, String> o, long flags) {
            if (o == null) {
                return 0L;
            }
            NSDictionary<NSString, NSString> dict = new NSMutableDictionary<>();
            for (Map.Entry<SCNShaderModifierEntryPoint, String> e : o.entrySet()) {
                dict.put(e.getKey().value(), new NSString(e.getValue()));
            }
            return NSObject.Marshaler.toNative(dict, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(SCNShaderModifierEntryPoint.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final SCNShaderModifierEntryPoint Geometry = new SCNShaderModifierEntryPoint("GeometryValue");
    public static final SCNShaderModifierEntryPoint Surface = new SCNShaderModifierEntryPoint("SurfaceValue");
    public static final SCNShaderModifierEntryPoint LightingModel = new SCNShaderModifierEntryPoint("LightingModelValue");
    public static final SCNShaderModifierEntryPoint Fragment = new SCNShaderModifierEntryPoint("FragmentValue");
    
    private static SCNShaderModifierEntryPoint[] values = new SCNShaderModifierEntryPoint[] {Geometry, Surface, 
        LightingModel, Fragment};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private SCNShaderModifierEntryPoint(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static SCNShaderModifierEntryPoint valueOf(NSString value) {
        for (SCNShaderModifierEntryPoint v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/SCNShaderModifierEntryPoint/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="SCNShaderModifierEntryPointGeometry", optional=true)
    protected static native NSString GeometryValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="SCNShaderModifierEntryPointSurface", optional=true)
    protected static native NSString SurfaceValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="SCNShaderModifierEntryPointLightingModel", optional=true)
    protected static native NSString LightingModelValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="SCNShaderModifierEntryPointFragment", optional=true)
    protected static native NSString FragmentValue();
    /*</methods>*/
}
