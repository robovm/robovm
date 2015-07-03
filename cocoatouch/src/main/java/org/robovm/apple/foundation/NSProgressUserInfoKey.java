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
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/NSProgressUserInfoKey/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSProgressUserInfoKey/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/NSProgressUserInfoKey/*</name>*/.class); }

    /*<marshalers>*/
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
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<NSProgressUserInfoKey> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<NSProgressUserInfoKey> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(NSProgressUserInfoKey.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSProgressUserInfoKey> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (NSProgressUserInfoKey o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSProgressUserInfoKey EstimatedTimeRemaining = new NSProgressUserInfoKey("EstimatedTimeRemaining");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSProgressUserInfoKey Throughput = new NSProgressUserInfoKey("Throughput");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSProgressUserInfoKey FileOperationKind = new NSProgressUserInfoKey("FileOperationKind");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSProgressUserInfoKey FileURL = new NSProgressUserInfoKey("FileURL");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSProgressUserInfoKey FileTotalCount = new NSProgressUserInfoKey("FileTotalCount");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSProgressUserInfoKey FileCompletedCount = new NSProgressUserInfoKey("FileCompletedCount");
    /*</constants>*/
    
    private static /*<name>*/NSProgressUserInfoKey/*</name>*/[] values = new /*<name>*/NSProgressUserInfoKey/*</name>*/[] {/*<value_list>*/EstimatedTimeRemaining, Throughput, FileOperationKind, FileURL, FileTotalCount, FileCompletedCount/*</value_list>*/};
    
    /*<name>*/NSProgressUserInfoKey/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/NSProgressUserInfoKey/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/NSProgressUserInfoKey/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSProgressUserInfoKey/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Foundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="NSProgressEstimatedTimeRemainingKey", optional=true)
        public static native NSString EstimatedTimeRemaining();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="NSProgressThroughputKey", optional=true)
        public static native NSString Throughput();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="NSProgressFileOperationKindKey", optional=true)
        public static native NSString FileOperationKind();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="NSProgressFileURLKey", optional=true)
        public static native NSString FileURL();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="NSProgressFileTotalCountKey", optional=true)
        public static native NSString FileTotalCount();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="NSProgressFileCompletedCountKey", optional=true)
        public static native NSString FileCompletedCount();
        /*</values>*/
    }
}
