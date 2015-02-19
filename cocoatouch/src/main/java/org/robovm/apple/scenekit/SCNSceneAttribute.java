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
@Marshaler(SCNSceneAttribute.Marshaler.class)
/*<annotations>*/@Library("SceneKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNSceneAttribute/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static SCNSceneAttribute toObject(Class<SCNSceneAttribute> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return SCNSceneAttribute.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(SCNSceneAttribute o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(SCNSceneAttribute.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final SCNSceneAttribute StartTime = new SCNSceneAttribute("StartTimeValue");
    public static final SCNSceneAttribute EndTime = new SCNSceneAttribute("EndTimeValue");
    public static final SCNSceneAttribute FrameRate = new SCNSceneAttribute("FrameRateValue");
    public static final SCNSceneAttribute UpAxis = new SCNSceneAttribute("UpAxisValue");
    
    private static SCNSceneAttribute[] values = new SCNSceneAttribute[] {StartTime, EndTime, FrameRate, UpAxis};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private SCNSceneAttribute(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static SCNSceneAttribute valueOf(NSString value) {
        for (SCNSceneAttribute v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/SCNSceneAttribute/*</name>*/.class.getName());
    }
    /*<methods>*/
    @GlobalValue(symbol="SCNSceneStartTimeAttributeKey", optional=true)
    protected static native NSString StartTimeValue();
    @GlobalValue(symbol="SCNSceneEndTimeAttributeKey", optional=true)
    protected static native NSString EndTimeValue();
    @GlobalValue(symbol="SCNSceneFrameRateAttributeKey", optional=true)
    protected static native NSString FrameRateValue();
    @GlobalValue(symbol="SCNSceneUpAxisAttributeKey", optional=true)
    protected static native NSString UpAxisValue();
    /*</methods>*/
}
