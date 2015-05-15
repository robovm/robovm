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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/PKPassLibraryNotificationArgs/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(PKPassLibraryNotificationArgs.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected PKPassLibraryNotificationArgs (NSNotification notification) {
        this.data = notification.getUserInfo();
    }
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
    public NSArray<PKPass> getAddedPasses() {
        if (data.containsKey(AddedPassesKey())) {
            NSArray<PKPass> val = (NSArray<PKPass>)data.get(AddedPassesKey());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    @SuppressWarnings("unchecked")
    public NSArray<PKPass> getReplacementPasses() {
        if (data.containsKey(ReplacementPassesKey())) {
            NSArray<PKPass> val = (NSArray<PKPass>)data.get(ReplacementPassesKey());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    @SuppressWarnings("unchecked")
    public List<PKRemovedPassInfo> getRemovedPassInfos() {
        if (data.containsKey(RemovedPassInfosKey())) {
            NSArray<NSDictionary<NSString, NSObject>> val = (NSArray<NSDictionary<NSString, NSObject>>)data.get(RemovedPassInfosKey());
            List<PKRemovedPassInfo> list = new ArrayList<>();
            for (NSDictionary<NSString, NSObject> v : val) {
                list.add(new PKRemovedPassInfo(v));
            }
            return list;
        }
        return null;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="PKPassLibraryAddedPassesUserInfoKey", optional=true)
    protected static native NSString AddedPassesKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="PKPassLibraryReplacementPassesUserInfoKey", optional=true)
    protected static native NSString ReplacementPassesKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="PKPassLibraryRemovedPassInfosUserInfoKey", optional=true)
    protected static native NSString RemovedPassInfosKey();
    /*</methods>*/
    
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
