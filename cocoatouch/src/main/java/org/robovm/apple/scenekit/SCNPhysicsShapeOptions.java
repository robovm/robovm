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
@Marshaler(SCNPhysicsShapeOptions.Marshaler.class)
/*<annotations>*/@Library("SceneKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNPhysicsShapeOptions/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static SCNPhysicsShapeOptions toObject(Class<SCNPhysicsShapeOptions> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new SCNPhysicsShapeOptions(o);
        }
        @MarshalsPointer
        public static long toNative(SCNPhysicsShapeOptions o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected SCNPhysicsShapeOptions(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public SCNPhysicsShapeOptions() {
        data = new NSMutableDictionary<>();
    }
    /*<bind>*/static { Bro.bind(SCNPhysicsShapeOptions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    public SCNPhysicsShapeType getType() {
        if (data.containsKey(TypeKey())) {
            NSString val = (NSString) data.get(TypeKey());
            return SCNPhysicsShapeType.valueOf(val);
        }
        return null;
    }
    public SCNPhysicsShapeOptions setType(SCNPhysicsShapeType type) {
        data.put(TypeKey(), type.value());
        return this;
    }
    public boolean isKeepingAsCompound() {
        if (data.containsKey(KeepAsCompoundKey())) {
            NSNumber val = (NSNumber) data.get(KeepAsCompoundKey());
            return val.booleanValue();
        }
        return true;
    }
    public SCNPhysicsShapeOptions setKeepAsCompound(boolean keepAsCompound) {
        data.put(KeepAsCompoundKey(), NSNumber.valueOf(keepAsCompound));
        return this;
    }
    public SCNVector3 getScale() {
        if (data.containsKey(ScaleKey())) {
            NSValue val = (NSValue) data.get(ScaleKey());
            return val.SCNVector3Value();
        }
        return null;
    }
    public SCNPhysicsShapeOptions setScale(SCNVector3 scale) {
        data.put(ScaleKey(), NSValue.valueOf(scale));
        return this;
    }
    /*<methods>*/
    @GlobalValue(symbol="SCNPhysicsShapeTypeKey", optional=true)
    protected static native NSString TypeKey();
    @GlobalValue(symbol="SCNPhysicsShapeKeepAsCompoundKey", optional=true)
    protected static native NSString KeepAsCompoundKey();
    @GlobalValue(symbol="SCNPhysicsShapeScaleKey", optional=true)
    protected static native NSString ScaleKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
