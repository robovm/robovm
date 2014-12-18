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
@Marshaler(SCNSceneSourceAnimationImportPolicy.Marshaler.class)
/*<annotations>*/@Library("SceneKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNSceneSourceAnimationImportPolicy/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static SCNSceneSourceAnimationImportPolicy toObject(Class<SCNSceneSourceAnimationImportPolicy> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return SCNSceneSourceAnimationImportPolicy.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(SCNSceneSourceAnimationImportPolicy o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(SCNSceneSourceAnimationImportPolicy.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final SCNSceneSourceAnimationImportPolicy Play = new SCNSceneSourceAnimationImportPolicy("PlayValue");
    public static final SCNSceneSourceAnimationImportPolicy PlayRepeatedly = new SCNSceneSourceAnimationImportPolicy("PlayRepeatedlyValue");
    public static final SCNSceneSourceAnimationImportPolicy DoNotPlay = new SCNSceneSourceAnimationImportPolicy("DoNotPlayValue");
    public static final SCNSceneSourceAnimationImportPolicy PlayUsingSceneTimeBase = new SCNSceneSourceAnimationImportPolicy("PlayUsingSceneTimeBaseValue");
    
    private static SCNSceneSourceAnimationImportPolicy[] values = new SCNSceneSourceAnimationImportPolicy[] {Play, PlayRepeatedly, DoNotPlay, 
        PlayUsingSceneTimeBase};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private SCNSceneSourceAnimationImportPolicy(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static SCNSceneSourceAnimationImportPolicy valueOf(NSString value) {
        for (SCNSceneSourceAnimationImportPolicy v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/SCNSceneSourceAnimationImportPolicy/*</name>*/.class.getName());
    }
    /*<methods>*/
    @GlobalValue(symbol="SCNSceneSourceAnimationImportPolicyPlay", optional=true)
    protected static native NSString PlayValue();
    @GlobalValue(symbol="SCNSceneSourceAnimationImportPolicyPlayRepeatedly", optional=true)
    protected static native NSString PlayRepeatedlyValue();
    @GlobalValue(symbol="SCNSceneSourceAnimationImportPolicyDoNotPlay", optional=true)
    protected static native NSString DoNotPlayValue();
    @GlobalValue(symbol="SCNSceneSourceAnimationImportPolicyPlayUsingSceneTimeBase", optional=true)
    protected static native NSString PlayUsingSceneTimeBaseValue();
    /*</methods>*/
}
