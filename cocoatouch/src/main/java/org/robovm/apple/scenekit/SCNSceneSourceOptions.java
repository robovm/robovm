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
@Marshaler(SCNSceneSourceOptions.Marshaler.class)
/*<annotations>*/@Library("SceneKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNSceneSourceOptions/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static SCNSceneSourceOptions toObject(Class<SCNSceneSourceOptions> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new SCNSceneSourceOptions(o);
        }
        @MarshalsPointer
        public static long toNative(SCNSceneSourceOptions o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected SCNSceneSourceOptions(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public SCNSceneSourceOptions() {
        data = new NSMutableDictionary<>();
    }
    /*<bind>*/static { Bro.bind(SCNSceneSourceOptions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    public boolean isCreatingNormalsIfAbsent() {
        if (data.containsKey(CreateNormalsIfAbsentKey())) {
            NSNumber val = (NSNumber) data.get(CreateNormalsIfAbsentKey());
            return val.booleanValue();
        }
        return false;
    }
    public SCNSceneSourceOptions setCreateNormalsIfAbsent(boolean createNormals) {
        data.put(CreateNormalsIfAbsentKey(), NSNumber.valueOf(createNormals));
        return this;
    }
    public boolean isCheckingConsistency() {
        if (data.containsKey(CheckConsistencyKey())) {
            NSNumber val = (NSNumber) data.get(CheckConsistencyKey());
            return val.booleanValue();
        }
        return false;
    }
    public SCNSceneSourceOptions setCheckConsistency(boolean check) {
        data.put(CheckConsistencyKey(), NSNumber.valueOf(check));
        return this;
    }
    public boolean isFlatteningScene() {
        if (data.containsKey(FlattenSceneKey())) {
            NSNumber val = (NSNumber) data.get(FlattenSceneKey());
            return val.booleanValue();
        }
        return false;
    }
    public SCNSceneSourceOptions setFlattenScene(boolean flattenScene) {
        data.put(FlattenSceneKey(), NSNumber.valueOf(flattenScene));
        return this;
    }
    public boolean isUsingSafeMode() {
        if (data.containsKey(UseSafeModeKey())) {
            NSNumber val = (NSNumber) data.get(UseSafeModeKey());
            return val.booleanValue();
        }
        return false;
    }
    public SCNSceneSourceOptions setUseSafeMode(boolean safeMode) {
        data.put(UseSafeModeKey(), NSNumber.valueOf(safeMode));
        return this;
    }
    @SuppressWarnings("unchecked")
    public NSArray<NSURL> getAssetDirectoryURLs() {
        if (data.containsKey(AssetDirectoryURLsKey())) {
            NSArray<NSURL> val = (NSArray<NSURL>) data.get(AssetDirectoryURLsKey());
            return val;
        }
        return null;
    }
    public SCNSceneSourceOptions setAssetDirectoryURLs(NSArray<NSURL> urls) {
        data.put(AssetDirectoryURLsKey(), urls);
        return this;
    }
    public boolean isOverridingAssetURLs() {
        if (data.containsKey(OverrideAssetURLsKey())) {
            NSNumber val = (NSNumber) data.get(OverrideAssetURLsKey());
            return val.booleanValue();
        }
        return false;
    }
    public SCNSceneSourceOptions setOverrideAssetURLs(boolean override) {
        data.put(OverrideAssetURLsKey(), NSNumber.valueOf(override));
        return this;
    }
    public boolean isStrictConformance() {
        if (data.containsKey(StrictConformanceKey())) {
            NSNumber val = (NSNumber) data.get(StrictConformanceKey());
            return val.booleanValue();
        }
        return false;
    }
    public SCNSceneSourceOptions setStrictConformance(boolean strict) {
        data.put(StrictConformanceKey(), NSNumber.valueOf(strict));
        return this;
    }
    public SCNSceneSourceAnimationImportPolicy getAnimationImportPolicy() {
        if (data.containsKey(AnimationImportPolicyKey())) {
            NSString val = (NSString) data.get(AnimationImportPolicyKey());
            return SCNSceneSourceAnimationImportPolicy.valueOf(val);
        }
        return null;
    }
    public SCNSceneSourceOptions setAnimationImportPolicy(SCNSceneSourceAnimationImportPolicy policy) {
        data.put(AnimationImportPolicyKey(), policy.value());
        return this;
    }
    /*<methods>*/
    @GlobalValue(symbol="SCNSceneSourceCreateNormalsIfAbsentKey", optional=true)
    protected static native NSString CreateNormalsIfAbsentKey();
    @GlobalValue(symbol="SCNSceneSourceCheckConsistencyKey", optional=true)
    protected static native NSString CheckConsistencyKey();
    @GlobalValue(symbol="SCNSceneSourceFlattenSceneKey", optional=true)
    protected static native NSString FlattenSceneKey();
    @GlobalValue(symbol="SCNSceneSourceUseSafeModeKey", optional=true)
    protected static native NSString UseSafeModeKey();
    @GlobalValue(symbol="SCNSceneSourceAssetDirectoryURLsKey", optional=true)
    protected static native NSString AssetDirectoryURLsKey();
    @GlobalValue(symbol="SCNSceneSourceOverrideAssetURLsKey", optional=true)
    protected static native NSString OverrideAssetURLsKey();
    @GlobalValue(symbol="SCNSceneSourceStrictConformanceKey", optional=true)
    protected static native NSString StrictConformanceKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="SCNSceneSourceAnimationImportPolicyKey", optional=true)
    protected static native NSString AnimationImportPolicyKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
