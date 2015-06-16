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
/*<annotations>*/@Library("SceneKit") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/SCNShaderModifierEntryPoint/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNShaderModifierEntryPoint/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/SCNShaderModifierEntryPoint/*</name>*/.class); }

    /*<marshalers>*/
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
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<SCNShaderModifierEntryPoint> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<SCNShaderModifierEntryPoint> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(SCNShaderModifierEntryPoint.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<SCNShaderModifierEntryPoint> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (SCNShaderModifierEntryPoint o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/
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

    /*<constants>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final SCNShaderModifierEntryPoint Geometry = new SCNShaderModifierEntryPoint("Geometry");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final SCNShaderModifierEntryPoint Surface = new SCNShaderModifierEntryPoint("Surface");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final SCNShaderModifierEntryPoint LightingModel = new SCNShaderModifierEntryPoint("LightingModel");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final SCNShaderModifierEntryPoint Fragment = new SCNShaderModifierEntryPoint("Fragment");
    /*</constants>*/
    
    private static /*<name>*/SCNShaderModifierEntryPoint/*</name>*/[] values = new /*<name>*/SCNShaderModifierEntryPoint/*</name>*/[] {/*<value_list>*/Geometry, Surface, LightingModel, Fragment/*</value_list>*/};
    
    /*<name>*/SCNShaderModifierEntryPoint/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/SCNShaderModifierEntryPoint/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/SCNShaderModifierEntryPoint/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/SCNShaderModifierEntryPoint/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("SceneKit") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="SCNShaderModifierEntryPointGeometry", optional=true)
        public static native NSString Geometry();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="SCNShaderModifierEntryPointSurface", optional=true)
        public static native NSString Surface();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="SCNShaderModifierEntryPointLightingModel", optional=true)
        public static native NSString LightingModel();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="SCNShaderModifierEntryPointFragment", optional=true)
        public static native NSString Fragment();
        /*</values>*/
    }
}
