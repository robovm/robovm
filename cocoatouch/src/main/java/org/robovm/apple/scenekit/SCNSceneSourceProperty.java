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
@Marshaler(SCNSceneSourceProperty.Marshaler.class)
/*<annotations>*/@Library("SceneKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNSceneSourceProperty/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static SCNSceneSourceProperty toObject(Class<SCNSceneSourceProperty> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return SCNSceneSourceProperty.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(SCNSceneSourceProperty o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(SCNSceneSourceProperty.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final SCNSceneSourceProperty Contributors = new SCNSceneSourceProperty("ContributorsValue");
    public static final SCNSceneSourceProperty CreatedDate = new SCNSceneSourceProperty("CreatedDateValue");
    public static final SCNSceneSourceProperty ModifiedDate = new SCNSceneSourceProperty("ModifiedDateValue");
    public static final SCNSceneSourceProperty UpAxis = new SCNSceneSourceProperty("UpAxisValue");
    public static final SCNSceneSourceProperty Unit = new SCNSceneSourceProperty("UnitValue");
    
    private static SCNSceneSourceProperty[] values = new SCNSceneSourceProperty[] {Contributors, CreatedDate, ModifiedDate, 
        UpAxis, Unit};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private SCNSceneSourceProperty(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static SCNSceneSourceProperty valueOf(NSString value) {
        for (SCNSceneSourceProperty v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/SCNSceneSourceProperty/*</name>*/.class.getName());
    }
    /*<methods>*/
    @GlobalValue(symbol="SCNSceneSourceAssetContributorsKey", optional=true)
    protected static native NSString ContributorsValue();
    @GlobalValue(symbol="SCNSceneSourceAssetCreatedDateKey", optional=true)
    protected static native NSString CreatedDateValue();
    @GlobalValue(symbol="SCNSceneSourceAssetModifiedDateKey", optional=true)
    protected static native NSString ModifiedDateValue();
    @GlobalValue(symbol="SCNSceneSourceAssetUpAxisKey", optional=true)
    protected static native NSString UpAxisValue();
    @GlobalValue(symbol="SCNSceneSourceAssetUnitKey", optional=true)
    protected static native NSString UnitValue();
    /*</methods>*/
}
