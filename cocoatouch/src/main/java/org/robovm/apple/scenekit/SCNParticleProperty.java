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
@Marshaler(/*<name>*/SCNParticleProperty/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNParticleProperty/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/SCNParticleProperty/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static SCNParticleProperty toObject(Class<SCNParticleProperty> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return SCNParticleProperty.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(SCNParticleProperty o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<SCNParticleProperty> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<SCNParticleProperty> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(SCNParticleProperty.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<SCNParticleProperty> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (SCNParticleProperty o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/
    public static class AsPropertyControllerMapMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static Map<SCNParticleProperty, SCNParticlePropertyController> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSDictionary<NSString, SCNParticlePropertyController> o = (NSDictionary<NSString, SCNParticlePropertyController>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            Map<SCNParticleProperty, SCNParticlePropertyController> map = new HashMap<>();
            for (Map.Entry<NSString, SCNParticlePropertyController> e : o.entrySet()) {
                map.put(SCNParticleProperty.valueOf(e.getKey()), e.getValue());
            }
            
            return map;
        }
        @MarshalsPointer
        public static long toNative(Map<SCNParticleProperty, SCNParticlePropertyController> o, long flags) {
            if (o == null) {
                return 0L;
            }
            NSDictionary<NSString, SCNParticlePropertyController> dict = new NSMutableDictionary<>();
            for (Map.Entry<SCNParticleProperty, SCNParticlePropertyController> e : o.entrySet()) {
                dict.put(e.getKey().value(), e.getValue());
            }
            return NSObject.Marshaler.toNative(dict, flags);
        }
    }

    /*<constants>*/
    public static final SCNParticleProperty Position = new SCNParticleProperty("Position");
    public static final SCNParticleProperty Angle = new SCNParticleProperty("Angle");
    public static final SCNParticleProperty RotationAxis = new SCNParticleProperty("RotationAxis");
    public static final SCNParticleProperty Velocity = new SCNParticleProperty("Velocity");
    public static final SCNParticleProperty AngularVelocity = new SCNParticleProperty("AngularVelocity");
    public static final SCNParticleProperty Life = new SCNParticleProperty("Life");
    public static final SCNParticleProperty Color = new SCNParticleProperty("Color");
    public static final SCNParticleProperty Opacity = new SCNParticleProperty("Opacity");
    public static final SCNParticleProperty Size = new SCNParticleProperty("Size");
    public static final SCNParticleProperty Frame = new SCNParticleProperty("Frame");
    public static final SCNParticleProperty FrameRate = new SCNParticleProperty("FrameRate");
    public static final SCNParticleProperty Bounce = new SCNParticleProperty("Bounce");
    public static final SCNParticleProperty Charge = new SCNParticleProperty("Charge");
    public static final SCNParticleProperty Friction = new SCNParticleProperty("Friction");
    public static final SCNParticleProperty ContactPoint = new SCNParticleProperty("ContactPoint");
    public static final SCNParticleProperty ContactNormal = new SCNParticleProperty("ContactNormal");
    /*</constants>*/
    
    private static /*<name>*/SCNParticleProperty/*</name>*/[] values = new /*<name>*/SCNParticleProperty/*</name>*/[] {/*<value_list>*/Position, Angle, RotationAxis, Velocity, AngularVelocity, Life, Color, Opacity, Size, Frame, FrameRate, Bounce, Charge, Friction, ContactPoint, ContactNormal/*</value_list>*/};
    
    /*<name>*/SCNParticleProperty/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/SCNParticleProperty/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/SCNParticleProperty/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/SCNParticleProperty/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("SceneKit") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        @GlobalValue(symbol="SCNParticlePropertyPosition", optional=true)
        public static native NSString Position();
        @GlobalValue(symbol="SCNParticlePropertyAngle", optional=true)
        public static native NSString Angle();
        @GlobalValue(symbol="SCNParticlePropertyRotationAxis", optional=true)
        public static native NSString RotationAxis();
        @GlobalValue(symbol="SCNParticlePropertyVelocity", optional=true)
        public static native NSString Velocity();
        @GlobalValue(symbol="SCNParticlePropertyAngularVelocity", optional=true)
        public static native NSString AngularVelocity();
        @GlobalValue(symbol="SCNParticlePropertyLife", optional=true)
        public static native NSString Life();
        @GlobalValue(symbol="SCNParticlePropertyColor", optional=true)
        public static native NSString Color();
        @GlobalValue(symbol="SCNParticlePropertyOpacity", optional=true)
        public static native NSString Opacity();
        @GlobalValue(symbol="SCNParticlePropertySize", optional=true)
        public static native NSString Size();
        @GlobalValue(symbol="SCNParticlePropertyFrame", optional=true)
        public static native NSString Frame();
        @GlobalValue(symbol="SCNParticlePropertyFrameRate", optional=true)
        public static native NSString FrameRate();
        @GlobalValue(symbol="SCNParticlePropertyBounce", optional=true)
        public static native NSString Bounce();
        @GlobalValue(symbol="SCNParticlePropertyCharge", optional=true)
        public static native NSString Charge();
        @GlobalValue(symbol="SCNParticlePropertyFriction", optional=true)
        public static native NSString Friction();
        @GlobalValue(symbol="SCNParticlePropertyContactPoint", optional=true)
        public static native NSString ContactPoint();
        @GlobalValue(symbol="SCNParticlePropertyContactNormal", optional=true)
        public static native NSString ContactNormal();
        /*</values>*/
    }
}
