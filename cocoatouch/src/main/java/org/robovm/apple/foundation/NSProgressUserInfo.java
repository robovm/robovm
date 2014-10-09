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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
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
    /**
     * @since Available in iOS 7.0 and later.
     */
    public double getEstimatedTimeRemaining() {
        if (data.containsKey(EstimatedTimeRemaining())) {
            NSNumber val = (NSNumber)data.get(EstimatedTimeRemaining());
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSProgressUserInfo setEstimatedTimeRemaining(double timeRemaining) {
        data.put(EstimatedTimeRemaining(), NSNumber.valueOf(timeRemaining));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public long getThroughput() {
        if (data.containsKey(Throughput())) {
            NSNumber val = (NSNumber)data.get(Throughput());
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSProgressUserInfo setThroughput(long throughput) {
        data.put(Throughput(), NSNumber.valueOf(throughput));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSProgressFileOperationKind getFileOperationKind() {
        if (data.containsKey(FileOperationKind())) {
            NSString val = (NSString)data.get(FileOperationKind());
            return NSProgressFileOperationKind.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSProgressUserInfo setFileOperationKind(NSProgressFileOperationKind kind) {
        data.put(FileOperationKind(), kind.value());
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSURL getFileURL() {
        if (data.containsKey(FileURL())) {
            NSURL val = (NSURL)data.get(FileURL());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSProgressUserInfo setFileURL(NSURL url) {
        data.put(FileURL(), url);
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public long getTotalFileCount() {
        if (data.containsKey(FileTotalCount())) {
            NSNumber val = (NSNumber)data.get(FileTotalCount());
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSProgressUserInfo setTotalFileCount(long fileCount) {
        data.put(FileTotalCount(), NSNumber.valueOf(fileCount));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public long getCompletedFileCount() {
        if (data.containsKey(FileCompletedCount())) {
            NSNumber val = (NSNumber)data.get(FileCompletedCount());
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSProgressUserInfo setCompletedFileCount(long fileCount) {
        data.put(FileCompletedCount(), NSNumber.valueOf(fileCount));
        return this;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSProgressEstimatedTimeRemainingKey", optional=true)
    protected static native NSString EstimatedTimeRemaining();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSProgressThroughputKey", optional=true)
    protected static native NSString Throughput();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSProgressFileOperationKindKey", optional=true)
    protected static native NSString FileOperationKind();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSProgressFileURLKey", optional=true)
    protected static native NSString FileURL();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSProgressFileTotalCountKey", optional=true)
    protected static native NSString FileTotalCount();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSProgressFileCompletedCountKey", optional=true)
    protected static native NSString FileCompletedCount();
    /*</methods>*/
}
