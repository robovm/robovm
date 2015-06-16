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
@Marshaler(/*<name>*/SCNRenderingTransform/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNRenderingTransform/*</name>*/ 
    extends /*<extends>*/SCNProgramSemantic/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/SCNRenderingTransform/*</name>*/.class); }

    /*<marshalers>*/
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
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<SCNRenderingTransform> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<SCNRenderingTransform> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(SCNRenderingTransform.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<SCNRenderingTransform> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (SCNRenderingTransform o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/
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

    /*<constants>*/
    public static final SCNRenderingTransform Model = new SCNRenderingTransform("Model");
    public static final SCNRenderingTransform View = new SCNRenderingTransform("View");
    public static final SCNRenderingTransform Projection = new SCNRenderingTransform("Projection");
    public static final SCNRenderingTransform Normal = new SCNRenderingTransform("Normal");
    public static final SCNRenderingTransform ModelView = new SCNRenderingTransform("ModelView");
    public static final SCNRenderingTransform ModelViewProjection = new SCNRenderingTransform("ModelViewProjection");
    /*</constants>*/
    
    private static /*<name>*/SCNRenderingTransform/*</name>*/[] values = new /*<name>*/SCNRenderingTransform/*</name>*/[] {/*<value_list>*/Model, View, Projection, Normal, ModelView, ModelViewProjection/*</value_list>*/};
    
    /*<name>*/SCNRenderingTransform/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/SCNRenderingTransform/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/SCNRenderingTransform/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/SCNRenderingTransform/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("SceneKit") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        @GlobalValue(symbol="SCNModelTransform", optional=true)
        public static native NSString Model();
        @GlobalValue(symbol="SCNViewTransform", optional=true)
        public static native NSString View();
        @GlobalValue(symbol="SCNProjectionTransform", optional=true)
        public static native NSString Projection();
        @GlobalValue(symbol="SCNNormalTransform", optional=true)
        public static native NSString Normal();
        @GlobalValue(symbol="SCNModelViewTransform", optional=true)
        public static native NSString ModelView();
        @GlobalValue(symbol="SCNModelViewProjectionTransform", optional=true)
        public static native NSString ModelViewProjection();
        /*</values>*/
    }
}
