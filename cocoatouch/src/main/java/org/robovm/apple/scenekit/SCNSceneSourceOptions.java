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
@Marshaler(/*<name>*/SCNSceneSourceOptions/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNSceneSourceOptions/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
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
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<SCNSceneSourceOptions> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary<NSString, NSObject>> o = (NSArray<NSDictionary<NSString, NSObject>>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<SCNSceneSourceOptions> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new SCNSceneSourceOptions(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<SCNSceneSourceOptions> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary<NSString, NSObject>> array = new NSMutableArray<>();
            for (SCNSceneSourceOptions i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    SCNSceneSourceOptions(NSDictionary<NSString, NSObject> data) {
        super(data);
    }
    public SCNSceneSourceOptions() {}
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
    public SCNSceneSourceOptions set(NSString key, NSObject value) {
        data.put(key, value);
        return this;
    }
    

    public boolean createsNormalsIfAbsent() {
        if (has(Keys.CreateNormalsIfAbsent())) {
            NSNumber val = (NSNumber) get(Keys.CreateNormalsIfAbsent());
            return val.booleanValue();
        }
        return false;
    }
    public SCNSceneSourceOptions setCreatesNormalsIfAbsent(boolean createsNormalsIfAbsent) {
        set(Keys.CreateNormalsIfAbsent(), NSNumber.valueOf(createsNormalsIfAbsent));
        return this;
    }
    public boolean checksConsistency() {
        if (has(Keys.CheckConsistency())) {
            NSNumber val = (NSNumber) get(Keys.CheckConsistency());
            return val.booleanValue();
        }
        return false;
    }
    public SCNSceneSourceOptions setChecksConsistency(boolean checksConsistency) {
        set(Keys.CheckConsistency(), NSNumber.valueOf(checksConsistency));
        return this;
    }
    public boolean flattensScene() {
        if (has(Keys.FlattenScene())) {
            NSNumber val = (NSNumber) get(Keys.FlattenScene());
            return val.booleanValue();
        }
        return false;
    }
    public SCNSceneSourceOptions setFlattensScene(boolean flattensScene) {
        set(Keys.FlattenScene(), NSNumber.valueOf(flattensScene));
        return this;
    }
    public boolean usesSafeMode() {
        if (has(Keys.UseSafeMode())) {
            NSNumber val = (NSNumber) get(Keys.UseSafeMode());
            return val.booleanValue();
        }
        return false;
    }
    public SCNSceneSourceOptions setUsesSafeMode(boolean usesSafeMode) {
        set(Keys.UseSafeMode(), NSNumber.valueOf(usesSafeMode));
        return this;
    }
    public NSArray<NSURL> getAssetDirectoryURLs() {
        if (has(Keys.AssetDirectoryURLs())) {
            NSArray<NSURL> val = (NSArray<NSURL>) get(Keys.AssetDirectoryURLs());
            return val;
        }
        return null;
    }
    public SCNSceneSourceOptions setAssetDirectoryURLs(NSArray<NSURL> assetDirectoryURLs) {
        set(Keys.AssetDirectoryURLs(), assetDirectoryURLs);
        return this;
    }
    public boolean overridesAssetURLs() {
        if (has(Keys.OverrideAssetURLs())) {
            NSNumber val = (NSNumber) get(Keys.OverrideAssetURLs());
            return val.booleanValue();
        }
        return false;
    }
    public SCNSceneSourceOptions setOverridesAssetURLs(boolean overridesAssetURLs) {
        set(Keys.OverrideAssetURLs(), NSNumber.valueOf(overridesAssetURLs));
        return this;
    }
    public boolean isStrictConformance() {
        if (has(Keys.StrictConformance())) {
            NSNumber val = (NSNumber) get(Keys.StrictConformance());
            return val.booleanValue();
        }
        return false;
    }
    public SCNSceneSourceOptions setStrictConformance(boolean strictConformance) {
        set(Keys.StrictConformance(), NSNumber.valueOf(strictConformance));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public SCNSceneSourceAnimationImportPolicy getAnimationImportPolicy() {
        if (has(Keys.AnimationImportPolicy())) {
            NSString val = (NSString) get(Keys.AnimationImportPolicy());
            return SCNSceneSourceAnimationImportPolicy.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public SCNSceneSourceOptions setAnimationImportPolicy(SCNSceneSourceAnimationImportPolicy animationImportPolicy) {
        set(Keys.AnimationImportPolicy(), animationImportPolicy.value());
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("SceneKit")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        @GlobalValue(symbol="SCNSceneSourceCreateNormalsIfAbsentKey", optional=true)
        public static native NSString CreateNormalsIfAbsent();
        @GlobalValue(symbol="SCNSceneSourceCheckConsistencyKey", optional=true)
        public static native NSString CheckConsistency();
        @GlobalValue(symbol="SCNSceneSourceFlattenSceneKey", optional=true)
        public static native NSString FlattenScene();
        @GlobalValue(symbol="SCNSceneSourceUseSafeModeKey", optional=true)
        public static native NSString UseSafeMode();
        @GlobalValue(symbol="SCNSceneSourceAssetDirectoryURLsKey", optional=true)
        public static native NSString AssetDirectoryURLs();
        @GlobalValue(symbol="SCNSceneSourceOverrideAssetURLsKey", optional=true)
        public static native NSString OverrideAssetURLs();
        @GlobalValue(symbol="SCNSceneSourceStrictConformanceKey", optional=true)
        public static native NSString StrictConformance();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="SCNSceneSourceAnimationImportPolicyKey", optional=true)
        public static native NSString AnimationImportPolicy();
    }
    /*</keys>*/
}
