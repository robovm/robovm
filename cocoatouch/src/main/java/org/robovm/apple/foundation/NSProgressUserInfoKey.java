/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(NSProgressUserInfoKey.Marshaler.class)
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSProgressUserInfoKey/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static NSProgressUserInfoKey toObject(Class<NSProgressUserInfoKey> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSProgressUserInfoKey.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(NSProgressUserInfoKey o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(NSProgressUserInfoKey.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSProgressUserInfoKey EstimatedTimeRemaining = new NSProgressUserInfoKey("EstimatedTimeRemainingValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSProgressUserInfoKey Throughput = new NSProgressUserInfoKey("ThroughputValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSProgressUserInfoKey FileOperationKind = new NSProgressUserInfoKey("FileOperationKindValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSProgressUserInfoKey FileURL = new NSProgressUserInfoKey("FileURLValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSProgressUserInfoKey FileTotalCount = new NSProgressUserInfoKey("FileTotalCountValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSProgressUserInfoKey FileCompletedCount = new NSProgressUserInfoKey("FileCompletedCountValue");
    
    private static NSProgressUserInfoKey[] values = new NSProgressUserInfoKey[] {EstimatedTimeRemaining, Throughput, FileOperationKind, FileURL, FileTotalCount, FileCompletedCount};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private NSProgressUserInfoKey(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static NSProgressUserInfoKey valueOf(NSString value) {
        for (NSProgressUserInfoKey v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSProgressUserInfoKey/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSProgressEstimatedTimeRemainingKey", optional=true)
    protected static native NSString EstimatedTimeRemainingValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSProgressThroughputKey", optional=true)
    protected static native NSString ThroughputValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSProgressFileOperationKindKey", optional=true)
    protected static native NSString FileOperationKindValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSProgressFileURLKey", optional=true)
    protected static native NSString FileURLValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSProgressFileTotalCountKey", optional=true)
    protected static native NSString FileTotalCountValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSProgressFileCompletedCountKey", optional=true)
    protected static native NSString FileCompletedCountValue();
    /*</methods>*/
}
