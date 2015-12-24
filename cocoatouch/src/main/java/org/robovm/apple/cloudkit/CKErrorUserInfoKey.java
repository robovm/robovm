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
package org.robovm.apple.cloudkit;

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
import org.robovm.apple.corelocation.*;
import org.robovm.apple.contacts.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CloudKit") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/CKErrorUserInfoKey/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CKErrorUserInfoKey/*</name>*/ 
    extends /*<extends>*/NSErrorUserInfoKey/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CKErrorUserInfoKey/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CKErrorUserInfoKey toObject(Class<CKErrorUserInfoKey> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CKErrorUserInfoKey.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CKErrorUserInfoKey o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<CKErrorUserInfoKey> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CKErrorUserInfoKey> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CKErrorUserInfoKey.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CKErrorUserInfoKey> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (CKErrorUserInfoKey o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CKErrorUserInfoKey PartialErrorsByItemID = new CKErrorUserInfoKey("PartialErrorsByItemID");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CKErrorUserInfoKey AncestorRecord = new CKErrorUserInfoKey("AncestorRecord");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CKErrorUserInfoKey ServerRecord = new CKErrorUserInfoKey("ServerRecord");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CKErrorUserInfoKey ClientRecord = new CKErrorUserInfoKey("ClientRecord");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CKErrorUserInfoKey RetryAfter = new CKErrorUserInfoKey("RetryAfter");
    /*</constants>*/
    
    private static /*<name>*/CKErrorUserInfoKey/*</name>*/[] values = new /*<name>*/CKErrorUserInfoKey/*</name>*/[] {/*<value_list>*/PartialErrorsByItemID, AncestorRecord, ServerRecord, ClientRecord, RetryAfter/*</value_list>*/};
    
    /*<name>*/CKErrorUserInfoKey/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CKErrorUserInfoKey/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/CKErrorUserInfoKey/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CKErrorUserInfoKey/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CloudKit") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="CKPartialErrorsByItemIDKey", optional=true)
        public static native NSString PartialErrorsByItemID();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="CKRecordChangedErrorAncestorRecordKey", optional=true)
        public static native NSString AncestorRecord();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="CKRecordChangedErrorServerRecordKey", optional=true)
        public static native NSString ServerRecord();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="CKRecordChangedErrorClientRecordKey", optional=true)
        public static native NSString ClientRecord();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="CKErrorRetryAfterKey", optional=true)
        public static native NSString RetryAfter();
        /*</values>*/
    }
}
