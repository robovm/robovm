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
package org.robovm.apple.passkit;

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
import org.robovm.apple.addressbook.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("PassKit")/*</annotations>*/
@Marshaler(/*<name>*/PKPassLibraryNotification/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/PKPassLibraryNotification/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static PKPassLibraryNotification toObject(Class<PKPassLibraryNotification> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new PKPassLibraryNotification(o);
        }
        @MarshalsPointer
        public static long toNative(PKPassLibraryNotification o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<PKPassLibraryNotification> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary<NSString, NSObject>> o = (NSArray<NSDictionary<NSString, NSObject>>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<PKPassLibraryNotification> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new PKPassLibraryNotification(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<PKPassLibraryNotification> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary<NSString, NSObject>> array = new NSMutableArray<>();
            for (PKPassLibraryNotification i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    PKPassLibraryNotification(NSDictionary<NSString, NSObject> data) {
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
    public NSArray<PKPass> getAddedPasses() {
        if (has(Keys.AddedPasses())) {
            NSArray<PKPass> val = (NSArray<PKPass>) get(Keys.AddedPasses());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSArray<PKPass> getReplacementPasses() {
        if (has(Keys.ReplacementPasses())) {
            NSArray<PKPass> val = (NSArray<PKPass>) get(Keys.ReplacementPasses());
            return val;
        }
        return null;
    }
    /*</methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @SuppressWarnings("unchecked")
    public List<PKRemovedPassInfo> getRemovedPassInfos() {
        if (has(Keys.RemovedPassInfos())) {
            NSArray<NSDictionary<NSString, NSObject>> val = (NSArray<NSDictionary<NSString, NSObject>>) get(Keys.RemovedPassInfos());
            List<PKRemovedPassInfo> list = new ArrayList<>();
            for (NSDictionary<NSString, NSObject> v : val) {
                list.add(new PKRemovedPassInfo(v));
            }
            return list;
        }
        return null;
    }
    
    /*<keys>*/
    @Library("PassKit")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="PKPassLibraryAddedPassesUserInfoKey", optional=true)
        public static native NSString AddedPasses();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="PKPassLibraryReplacementPassesUserInfoKey", optional=true)
        public static native NSString ReplacementPasses();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="PKPassLibraryRemovedPassInfosUserInfoKey", optional=true)
        public static native NSString RemovedPassInfos();
    }
    /*</keys>*/
}
