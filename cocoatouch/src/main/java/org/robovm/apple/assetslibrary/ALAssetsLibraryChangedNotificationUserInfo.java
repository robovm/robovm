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
package org.robovm.apple.assetslibrary;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.imageio.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(ALAssetsLibraryChangedNotificationUserInfo.Marshaler.class)
/*<annotations>*/@Library("AssetsLibrary")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ALAssetsLibraryChangedNotificationUserInfo/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static ALAssetsLibraryChangedNotificationUserInfo toObject(Class<ALAssetsLibraryChangedNotificationUserInfo> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new ALAssetsLibraryChangedNotificationUserInfo(o);
        }
        @MarshalsPointer
        public static long toNative(ALAssetsLibraryChangedNotificationUserInfo o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }

    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected ALAssetsLibraryChangedNotificationUserInfo(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    /*<bind>*/static { Bro.bind(ALAssetsLibraryChangedNotificationUserInfo.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    /**
     * @since Available in iOS 6.0 and later.
     */
    @SuppressWarnings("unchecked")
    public NSSet<NSURL> getUpdatedAssets() {
        if (data.containsKey(UpdatedAssetsKey())) {
            NSSet<NSURL> val = (NSSet<NSURL>) data.get(UpdatedAssetsKey());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    @SuppressWarnings("unchecked")
    public NSSet<NSURL> getInsertedAssetGroups() {
        if (data.containsKey(InsertedAssetGroupsKey())) {
            NSSet<NSURL> val = (NSSet<NSURL>) data.get(InsertedAssetGroupsKey());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    @SuppressWarnings("unchecked")
    public NSSet<NSURL> getUpdatedAssetGroups() {
        if (data.containsKey(UpdatedAssetGroupsKey())) {
            NSSet<NSURL> val = (NSSet<NSURL>) data.get(UpdatedAssetGroupsKey());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    @SuppressWarnings("unchecked")
    public NSSet<NSURL> getDeletedAssetGroups() {
        if (data.containsKey(DeletedAssetGroupsKey())) {
            NSSet<NSURL> val = (NSSet<NSURL>) data.get(DeletedAssetGroupsKey());
            return val;
        }
        return null;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="ALAssetLibraryUpdatedAssetsKey", optional=true)
    protected static native NSString UpdatedAssetsKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="ALAssetLibraryInsertedAssetGroupsKey", optional=true)
    protected static native NSString InsertedAssetGroupsKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="ALAssetLibraryUpdatedAssetGroupsKey", optional=true)
    protected static native NSString UpdatedAssetGroupsKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="ALAssetLibraryDeletedAssetGroupsKey", optional=true)
    protected static native NSString DeletedAssetGroupsKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
