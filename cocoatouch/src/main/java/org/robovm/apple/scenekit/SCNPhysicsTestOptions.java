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
@Marshaler(/*<name>*/SCNPhysicsTestOptions/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNPhysicsTestOptions/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static SCNPhysicsTestOptions toObject(Class<SCNPhysicsTestOptions> cls, long handle, long flags) {
            NSDictionary o = (NSDictionary) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new SCNPhysicsTestOptions(o);
        }
        @MarshalsPointer
        public static long toNative(SCNPhysicsTestOptions o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<SCNPhysicsTestOptions> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary> o = (NSArray<NSDictionary>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<SCNPhysicsTestOptions> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new SCNPhysicsTestOptions(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<SCNPhysicsTestOptions> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary> array = new NSMutableArray<>();
            for (SCNPhysicsTestOptions i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    SCNPhysicsTestOptions(NSDictionary data) {
        super(data);
    }
    public SCNPhysicsTestOptions() {}
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
    public SCNPhysicsTestOptions set(NSString key, NSObject value) {
        data.put(key, value);
        return this;
    }
    

    public SCNPhysicsCollisionCategory getCollisionBitMask() {
        if (has(Keys.CollisionBitMask())) {
            NSNumber val = (NSNumber) get(Keys.CollisionBitMask());
            return new SCNPhysicsCollisionCategory(val.longValue());
        }
        return null;
    }
    public SCNPhysicsTestOptions setCollisionBitMask(SCNPhysicsCollisionCategory collisionBitMask) {
        set(Keys.CollisionBitMask(), NSNumber.valueOf(collisionBitMask.value()));
        return this;
    }
    public SCNPhysicsTestSearchMode getSearchMode() {
        if (has(Keys.SearchMode())) {
            NSString val = (NSString) get(Keys.SearchMode());
            return SCNPhysicsTestSearchMode.valueOf(val);
        }
        return null;
    }
    public SCNPhysicsTestOptions setSearchMode(SCNPhysicsTestSearchMode searchMode) {
        set(Keys.SearchMode(), searchMode.value());
        return this;
    }
    public boolean isBackfaceCulling() {
        if (has(Keys.BackfaceCulling())) {
            NSNumber val = (NSNumber) get(Keys.BackfaceCulling());
            return val.booleanValue();
        }
        return false;
    }
    public SCNPhysicsTestOptions setBackfaceCulling(boolean backfaceCulling) {
        set(Keys.BackfaceCulling(), NSNumber.valueOf(backfaceCulling));
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("SceneKit")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        @GlobalValue(symbol="SCNPhysicsTestCollisionBitMaskKey", optional=true)
        public static native NSString CollisionBitMask();
        @GlobalValue(symbol="SCNPhysicsTestSearchModeKey", optional=true)
        public static native NSString SearchMode();
        @GlobalValue(symbol="SCNPhysicsTestBackfaceCullingKey", optional=true)
        public static native NSString BackfaceCulling();
    }
    /*</keys>*/
}
