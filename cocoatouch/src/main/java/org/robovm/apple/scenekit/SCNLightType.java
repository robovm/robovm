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
@Marshaler(SCNLightType.Marshaler.class)
/*<annotations>*/@Library("SceneKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNLightType/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static SCNLightType toObject(Class<SCNLightType> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return SCNLightType.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(SCNLightType o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(SCNLightType.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final SCNLightType Ambient = new SCNLightType("AmbientValue");
    public static final SCNLightType Omni = new SCNLightType("OmniValue");
    public static final SCNLightType Directional = new SCNLightType("DirectionalValue");
    public static final SCNLightType Spot = new SCNLightType("SpotValue");
    
    private static SCNLightType[] values = new SCNLightType[] {Ambient, Omni, Directional, Spot};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private SCNLightType(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static SCNLightType valueOf(NSString value) {
        for (SCNLightType v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/SCNLightType/*</name>*/.class.getName());
    }
    /*<methods>*/
    @GlobalValue(symbol="SCNLightTypeAmbient", optional=true)
    protected static native NSString AmbientValue();
    @GlobalValue(symbol="SCNLightTypeOmni", optional=true)
    protected static native NSString OmniValue();
    @GlobalValue(symbol="SCNLightTypeDirectional", optional=true)
    protected static native NSString DirectionalValue();
    @GlobalValue(symbol="SCNLightTypeSpot", optional=true)
    protected static native NSString SpotValue();
    /*</methods>*/
}
