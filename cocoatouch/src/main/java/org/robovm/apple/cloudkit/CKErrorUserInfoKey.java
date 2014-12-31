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
package org.robovm.apple.cloudkit;

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
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(CKErrorUserInfoKey.Marshaler.class)
/*<annotations>*/@Library("CloudKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CKErrorUserInfoKey/*</name>*/ 
    extends /*<extends>*/NSErrorUserInfoKey/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
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

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CKErrorUserInfoKey.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CKErrorUserInfoKey PartialErrorsByItemID = new CKErrorUserInfoKey("PartialErrorsByItemIDValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CKErrorUserInfoKey AncestorRecord = new CKErrorUserInfoKey("AncestorRecordValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CKErrorUserInfoKey ServerRecord = new CKErrorUserInfoKey("ServerRecordValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CKErrorUserInfoKey ClientRecord = new CKErrorUserInfoKey("ClientRecordValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CKErrorUserInfoKey RetryAfter = new CKErrorUserInfoKey("RetryAfterValue");
    
    private static CKErrorUserInfoKey[] values = new CKErrorUserInfoKey[] {PartialErrorsByItemID, AncestorRecord, ServerRecord, 
        ClientRecord, RetryAfter};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private CKErrorUserInfoKey(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static CKErrorUserInfoKey valueOf(NSString value) {
        for (CKErrorUserInfoKey v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CKErrorUserInfoKey/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="CKPartialErrorsByItemIDKey", optional=true)
    protected static native String PartialErrorsByItemIDValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="CKRecordChangedErrorAncestorRecordKey", optional=true)
    protected static native String AncestorRecordValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="CKRecordChangedErrorServerRecordKey", optional=true)
    protected static native String ServerRecordValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="CKRecordChangedErrorClientRecordKey", optional=true)
    protected static native String ClientRecordValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="CKErrorRetryAfterKey", optional=true)
    protected static native String RetryAfterValue();
    /*</methods>*/
}
