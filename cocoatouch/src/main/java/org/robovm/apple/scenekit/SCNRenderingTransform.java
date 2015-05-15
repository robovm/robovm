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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.glkit.*;
import org.robovm.apple.spritekit.*;
import org.robovm.apple.opengles.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(SCNRenderingTransform.Marshaler.class)
/*<annotations>*/@Library("SceneKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNRenderingTransform/*</name>*/ 
    extends /*<extends>*/SCNProgramSemantic/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static SCNRenderingTransform toObject(Class<SCNRenderingTransform> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return SCNRenderingTransform.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(SCNRenderingTransform o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    public static class AsTransform3DMapMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static Map<SCNRenderingTransform, CATransform3D> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSDictionary<NSString, NSValue> o = (NSDictionary<NSString, NSValue>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            Map<SCNRenderingTransform, CATransform3D> map = new HashMap<>();
            for (Map.Entry<NSString, NSValue> e : o.entrySet()) {
                map.put(SCNRenderingTransform.valueOf(e.getKey()), e.getValue().transform3DValue());
            }
            
            return map;
        }
        @MarshalsPointer
        public static long toNative(Map<SCNRenderingTransform, CATransform3D> o, long flags) {
            if (o == null) {
                return 0L;
            }
            NSDictionary<NSString, NSValue> dict = new NSMutableDictionary<>();
            for (Map.Entry<SCNRenderingTransform, CATransform3D> e : o.entrySet()) {
                dict.put(e.getKey().value(), NSValue.valueOf(e.getValue()));
            }
            return NSObject.Marshaler.toNative(dict, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(SCNRenderingTransform.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final SCNRenderingTransform Model = new SCNRenderingTransform("ModelValue");
    public static final SCNRenderingTransform View = new SCNRenderingTransform("ViewValue");
    public static final SCNRenderingTransform Projection = new SCNRenderingTransform("ProjectionValue");
    public static final SCNRenderingTransform Normal = new SCNRenderingTransform("NormalValue");
    public static final SCNRenderingTransform ModelView = new SCNRenderingTransform("ModelViewValue");
    public static final SCNRenderingTransform ModelViewProjection = new SCNRenderingTransform("ModelViewProjectionValue");
    
    private static SCNRenderingTransform[] values = new SCNRenderingTransform[] {Model, View, Projection, Normal, 
        ModelView, ModelViewProjection};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private SCNRenderingTransform(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static SCNRenderingTransform valueOf(NSString value) {
        for (SCNRenderingTransform v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        return null;
//        throw new IllegalArgumentException("No constant with value " + value + " found in " 
//            + /*<name>*/SCNRenderingTransform/*</name>*/.class.getName());
    }
    /*<methods>*/
    @GlobalValue(symbol="SCNModelTransform", optional=true)
    protected static native NSString ModelValue();
    @GlobalValue(symbol="SCNViewTransform", optional=true)
    protected static native NSString ViewValue();
    @GlobalValue(symbol="SCNProjectionTransform", optional=true)
    protected static native NSString ProjectionValue();
    @GlobalValue(symbol="SCNNormalTransform", optional=true)
    protected static native NSString NormalValue();
    @GlobalValue(symbol="SCNModelViewTransform", optional=true)
    protected static native NSString ModelViewValue();
    @GlobalValue(symbol="SCNModelViewProjectionTransform", optional=true)
    protected static native NSString ModelViewProjectionValue();
    /*</methods>*/
}
