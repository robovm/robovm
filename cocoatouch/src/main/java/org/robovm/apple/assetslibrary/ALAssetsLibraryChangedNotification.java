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
package org.robovm.apple.assetslibrary;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.imageio.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("AssetsLibrary")/*</annotations>*/
@Marshaler(/*<name>*/ALAssetsLibraryChangedNotification/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ALAssetsLibraryChangedNotification/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static ALAssetsLibraryChangedNotification toObject(Class<ALAssetsLibraryChangedNotification> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new ALAssetsLibraryChangedNotification(o);
        }
        @MarshalsPointer
        public static long toNative(ALAssetsLibraryChangedNotification o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<ALAssetsLibraryChangedNotification> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary<NSString, NSObject>> o = (NSArray<NSDictionary<NSString, NSObject>>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<ALAssetsLibraryChangedNotification> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new ALAssetsLibraryChangedNotification(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<ALAssetsLibraryChangedNotification> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary<NSString, NSObject>> array = new NSMutableArray<>();
            for (ALAssetsLibraryChangedNotification i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    ALAssetsLibraryChangedNotification(NSDictionary<NSString, NSObject> data) {
        super(data);
    }
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
    

    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSSet<NSURL> getUpdatedAssets() {
        if (has(Keys.UpdatedAssets())) {
            NSSet<NSURL> val = (NSSet<NSURL>) get(Keys.UpdatedAssets());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSSet<NSURL> getInsertedAssetGroups() {
        if (has(Keys.InsertedAssetGroups())) {
            NSSet<NSURL> val = (NSSet<NSURL>) get(Keys.InsertedAssetGroups());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSSet<NSURL> getUpdatedAssetGroups() {
        if (has(Keys.UpdatedAssetGroups())) {
            NSSet<NSURL> val = (NSSet<NSURL>) get(Keys.UpdatedAssetGroups());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSSet<NSURL> getDeletedAssetGroups() {
        if (has(Keys.DeletedAssetGroups())) {
            NSSet<NSURL> val = (NSSet<NSURL>) get(Keys.DeletedAssetGroups());
            return val;
        }
        return null;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("AssetsLibrary")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="ALAssetLibraryUpdatedAssetsKey", optional=true)
        public static native NSString UpdatedAssets();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="ALAssetLibraryInsertedAssetGroupsKey", optional=true)
        public static native NSString InsertedAssetGroups();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="ALAssetLibraryUpdatedAssetGroupsKey", optional=true)
        public static native NSString UpdatedAssetGroups();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="ALAssetLibraryDeletedAssetGroupsKey", optional=true)
        public static native NSString DeletedAssetGroups();
    }
    /*</keys>*/
}
