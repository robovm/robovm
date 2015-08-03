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
/*<annotations>*/@Library("SceneKit")/*</annotations>*/
@Marshaler(/*<name>*/SCNPhysicsShapeOptions/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNPhysicsShapeOptions/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static SCNPhysicsShapeOptions toObject(Class<SCNPhysicsShapeOptions> cls, long handle, long flags) {
            NSDictionary o = (NSDictionary) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
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
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<SCNPhysicsShapeOptions> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary> o = (NSArray<NSDictionary>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<SCNPhysicsShapeOptions> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new SCNPhysicsShapeOptions(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<SCNPhysicsShapeOptions> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary> array = new NSMutableArray<>();
            for (SCNPhysicsShapeOptions i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    SCNPhysicsShapeOptions(NSDictionary data) {
        super(data);
    }
    public SCNPhysicsShapeOptions() {}
    /*</constructors>*/

    /*<methods>*/
    public boolean has(NSString key) {
        return data.containsKey(key);
    }
    public NSObject get(NSString key) {
        if (has(key)) {
            return data.get(key);
        }
        return null;
    }
    public SCNPhysicsShapeOptions set(NSString key, NSObject value) {
        data.put(key, value);
        return this;
    }
    

    public SCNPhysicsShapeType getType() {
        if (has(Keys.Type())) {
            NSString val = (NSString) get(Keys.Type());
            return SCNPhysicsShapeType.valueOf(val);
        }
        return null;
    }
    public SCNPhysicsShapeOptions setType(SCNPhysicsShapeType type) {
        set(Keys.Type(), type.value());
        return this;
    }
    public boolean keepsAsCompound() {
        if (has(Keys.KeepAsCompound())) {
            NSNumber val = (NSNumber) get(Keys.KeepAsCompound());
            return val.booleanValue();
        }
        return false;
    }
    public SCNPhysicsShapeOptions setKeepsAsCompound(boolean keepsAsCompound) {
        set(Keys.KeepAsCompound(), NSNumber.valueOf(keepsAsCompound));
        return this;
    }
    public SCNVector3 getScale() {
        if (has(Keys.Scale())) {
            NSData val = (NSData) get(Keys.Scale());
            return val.getStructData(SCNVector3.class);
        }
        return null;
    }
    public SCNPhysicsShapeOptions setScale(SCNVector3 scale) {
        set(Keys.Scale(), new NSData(scale));
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("SceneKit")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        @GlobalValue(symbol="SCNPhysicsShapeTypeKey", optional=true)
        public static native NSString Type();
        @GlobalValue(symbol="SCNPhysicsShapeKeepAsCompoundKey", optional=true)
        public static native NSString KeepAsCompound();
        @GlobalValue(symbol="SCNPhysicsShapeScaleKey", optional=true)
        public static native NSString Scale();
    }
    /*</keys>*/
}
