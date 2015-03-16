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
package org.robovm.apple.foundation;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;

import org.apache.xpath.functions.FuncNormalizeSpace;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(NSProgressUserInfo.Marshaler.class)
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSProgressUserInfo/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static NSProgressUserInfo toObject(Class<NSProgressUserInfo> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new NSProgressUserInfo(o);
        }
        @MarshalsPointer
        public static long toNative(NSProgressUserInfo o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected NSProgressUserInfo(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public NSProgressUserInfo() {
        this.data = new NSMutableDictionary<>();
    }
    /*<bind>*/static { Bro.bind(NSProgressUserInfo.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSProgressUserInfo set(String key, NSObject value) {
        data.put(new NSString(key), value);
        return this;
    }
    public NSProgressUserInfo set(NSProgressUserInfoKey key, NSObject value) {
        data.put(key.value(), value);
        return this;
    }
    public NSObject get(String key) {
        return data.get(new NSString(key));
    }
    public NSObject get(NSProgressUserInfoKey key) {
        return data.get(key.value());
    }
    public boolean contains(String key) {
        return data.containsKey(new NSString(key));
    }
    public boolean contains(NSProgressUserInfoKey key) {
        return data.containsKey(key.value());
    }
    
    /**
     * @since Available in iOS 7.0 and later.
     */
    public double getEstimatedTimeRemaining() {
        if (contains(NSProgressUserInfoKey.EstimatedTimeRemaining)) {
            NSNumber val = (NSNumber)get(NSProgressUserInfoKey.EstimatedTimeRemaining);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSProgressUserInfo setEstimatedTimeRemaining(double timeRemaining) {
        set(NSProgressUserInfoKey.EstimatedTimeRemaining, NSNumber.valueOf(timeRemaining));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public long getThroughput() {
        if (contains(NSProgressUserInfoKey.Throughput)) {
            NSNumber val = (NSNumber)get(NSProgressUserInfoKey.Throughput);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSProgressUserInfo setThroughput(long throughput) {
        set(NSProgressUserInfoKey.Throughput, NSNumber.valueOf(throughput));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSProgressFileOperationKind getFileOperationKind() {
        if (contains(NSProgressUserInfoKey.FileOperationKind)) {
            NSString val = (NSString)get(NSProgressUserInfoKey.FileOperationKind);
            return NSProgressFileOperationKind.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSProgressUserInfo setFileOperationKind(NSProgressFileOperationKind kind) {
        set(NSProgressUserInfoKey.FileOperationKind, kind.value());
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSURL getFileURL() {
        if (contains(NSProgressUserInfoKey.FileURL)) {
            NSURL val = (NSURL)get(NSProgressUserInfoKey.FileURL);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSProgressUserInfo setFileURL(NSURL url) {
        set(NSProgressUserInfoKey.FileURL, url);
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public long getTotalFileCount() {
        if (contains(NSProgressUserInfoKey.FileTotalCount)) {
            NSNumber val = (NSNumber)get(NSProgressUserInfoKey.FileTotalCount);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSProgressUserInfo setTotalFileCount(long fileCount) {
        set(NSProgressUserInfoKey.FileTotalCount, NSNumber.valueOf(fileCount));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public long getCompletedFileCount() {
        if (contains(NSProgressUserInfoKey.FileCompletedCount)) {
            NSNumber val = (NSNumber)get(NSProgressUserInfoKey.FileCompletedCount);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSProgressUserInfo setCompletedFileCount(long fileCount) {
        set(NSProgressUserInfoKey.FileCompletedCount, NSNumber.valueOf(fileCount));
        return this;
    }
    /*<methods>*/
    /*</methods>*/
}
