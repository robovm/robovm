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
@Marshaler(SCNPhysicsTestOptions.Marshaler.class)
/*<annotations>*/@Library("SceneKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNPhysicsTestOptions/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static SCNPhysicsTestOptions toObject(Class<SCNPhysicsTestOptions> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
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
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected SCNPhysicsTestOptions(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public SCNPhysicsTestOptions() {
        data = new NSMutableDictionary<>();
    }
    /*<bind>*/static { Bro.bind(SCNPhysicsTestOptions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }

    public SCNPhysicsCollisionCategory getCollisionBitMask() {
        if (data.containsKey(CollisionBitMaskKey())) {
            NSNumber val = (NSNumber) data.get(CollisionBitMaskKey());
            return new SCNPhysicsCollisionCategory(val.longValue());
        }
        return null;
    }
    public SCNPhysicsTestOptions setCollisionBitMask(SCNPhysicsCollisionCategory mask) {
        data.put(CollisionBitMaskKey(), NSNumber.valueOf(mask.value()));
        return this;
    }
    public SCNPhysicsTestSearchMode getSearchMode() {
        if (data.containsKey(SearchModeKey())) {
            NSString val = (NSString) data.get(SearchModeKey());
            return SCNPhysicsTestSearchMode.valueOf(val);
        }
        return null;
    }
    public SCNPhysicsTestOptions setSearchMode(SCNPhysicsTestSearchMode searchMode) {
        data.put(SearchModeKey(), searchMode.value());
        return this;
    }
    public boolean getBackfaceCulling() {
        if (data.containsKey(BackfaceCullingKey())) {
            NSNumber val = (NSNumber) data.get(BackfaceCullingKey());
            return val.booleanValue();
        }
        return true;
    }
    public SCNPhysicsTestOptions setBackfaceCulling(boolean backfaceCulling) {
        data.put(BackfaceCullingKey(), NSNumber.valueOf(backfaceCulling));
        return this;
    }
    /*<methods>*/
    @GlobalValue(symbol="SCNPhysicsTestCollisionBitMaskKey", optional=true)
    protected static native NSString CollisionBitMaskKey();
    @GlobalValue(symbol="SCNPhysicsTestSearchModeKey", optional=true)
    protected static native NSString SearchModeKey();
    @GlobalValue(symbol="SCNPhysicsTestBackfaceCullingKey", optional=true)
    protected static native NSString BackfaceCullingKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
