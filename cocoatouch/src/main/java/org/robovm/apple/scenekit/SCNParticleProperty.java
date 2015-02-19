/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
@Marshaler(SCNParticleProperty.Marshaler.class)
/*<annotations>*/@Library("SceneKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNParticleProperty/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

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
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<SCNParticleProperty> list = new ArrayList<>();
            for (NSString str : o) {
                list.add(SCNParticleProperty.valueOf(str));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<SCNParticleProperty> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSMutableArray<NSString> array = new NSMutableArray<>();
            for (SCNParticleProperty i : l) {
                array.add(i.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    
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
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(SCNParticleProperty.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final SCNParticleProperty Position = new SCNParticleProperty("PositionValue");
    public static final SCNParticleProperty Angle = new SCNParticleProperty("AngleValue");
    public static final SCNParticleProperty RotationAxis = new SCNParticleProperty("RotationAxisValue");
    public static final SCNParticleProperty Velocity = new SCNParticleProperty("VelocityValue");
    public static final SCNParticleProperty AngularVelocity = new SCNParticleProperty("AngularVelocityValue");
    public static final SCNParticleProperty Life = new SCNParticleProperty("LifeValue");
    public static final SCNParticleProperty Color = new SCNParticleProperty("ColorValue");
    public static final SCNParticleProperty Opacity = new SCNParticleProperty("OpacityValue");
    public static final SCNParticleProperty Size = new SCNParticleProperty("SizeValue");
    public static final SCNParticleProperty Frame = new SCNParticleProperty("FrameValue");
    public static final SCNParticleProperty FrameRate = new SCNParticleProperty("FrameRateValue");
    public static final SCNParticleProperty Bounce = new SCNParticleProperty("BounceValue");
    public static final SCNParticleProperty Charge = new SCNParticleProperty("ChargeValue");
    public static final SCNParticleProperty Friction = new SCNParticleProperty("FrictionValue");
    public static final SCNParticleProperty ContactPoint = new SCNParticleProperty("ContactPointValue");
    public static final SCNParticleProperty ContactNormal = new SCNParticleProperty("ContactNormalValue");
    
    private static SCNParticleProperty[] values = new SCNParticleProperty[] {Position, Angle, RotationAxis, Velocity, AngularVelocity, 
        Life, Color, Opacity, Size, Frame, FrameRate, Bounce, Charge, Friction, ContactPoint, ContactNormal};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private SCNParticleProperty(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static SCNParticleProperty valueOf(NSString value) {
        for (SCNParticleProperty v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/SCNParticleProperty/*</name>*/.class.getName());
    }
    /*<methods>*/
    @GlobalValue(symbol="SCNParticlePropertyPosition", optional=true)
    protected static native NSString PositionValue();
    @GlobalValue(symbol="SCNParticlePropertyAngle", optional=true)
    protected static native NSString AngleValue();
    @GlobalValue(symbol="SCNParticlePropertyRotationAxis", optional=true)
    protected static native NSString RotationAxisValue();
    @GlobalValue(symbol="SCNParticlePropertyVelocity", optional=true)
    protected static native NSString VelocityValue();
    @GlobalValue(symbol="SCNParticlePropertyAngularVelocity", optional=true)
    protected static native NSString AngularVelocityValue();
    @GlobalValue(symbol="SCNParticlePropertyLife", optional=true)
    protected static native NSString LifeValue();
    @GlobalValue(symbol="SCNParticlePropertyColor", optional=true)
    protected static native NSString ColorValue();
    @GlobalValue(symbol="SCNParticlePropertyOpacity", optional=true)
    protected static native NSString OpacityValue();
    @GlobalValue(symbol="SCNParticlePropertySize", optional=true)
    protected static native NSString SizeValue();
    @GlobalValue(symbol="SCNParticlePropertyFrame", optional=true)
    protected static native NSString FrameValue();
    @GlobalValue(symbol="SCNParticlePropertyFrameRate", optional=true)
    protected static native NSString FrameRateValue();
    @GlobalValue(symbol="SCNParticlePropertyBounce", optional=true)
    protected static native NSString BounceValue();
    @GlobalValue(symbol="SCNParticlePropertyCharge", optional=true)
    protected static native NSString ChargeValue();
    @GlobalValue(symbol="SCNParticlePropertyFriction", optional=true)
    protected static native NSString FrictionValue();
    @GlobalValue(symbol="SCNParticlePropertyContactPoint", optional=true)
    protected static native NSString ContactPointValue();
    @GlobalValue(symbol="SCNParticlePropertyContactNormal", optional=true)
    protected static native NSString ContactNormalValue();
    /*</methods>*/
}
