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
package org.robovm.apple.contacts;

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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Contacts") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/CNErrorUserInfoKey/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CNErrorUserInfoKey/*</name>*/ 
    extends /*<extends>*/NSErrorUserInfoKey/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CNErrorUserInfoKey/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CNErrorUserInfoKey toObject(Class<CNErrorUserInfoKey> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CNErrorUserInfoKey.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CNErrorUserInfoKey o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<CNErrorUserInfoKey> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CNErrorUserInfoKey> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CNErrorUserInfoKey.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CNErrorUserInfoKey> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (CNErrorUserInfoKey o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNErrorUserInfoKey AffectedRecords = new CNErrorUserInfoKey("AffectedRecords");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNErrorUserInfoKey AffectedRecordIdentifiers = new CNErrorUserInfoKey("AffectedRecordIdentifiers");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNErrorUserInfoKey ValidationErrors = new CNErrorUserInfoKey("ValidationErrors");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNErrorUserInfoKey KeyPaths = new CNErrorUserInfoKey("KeyPaths");
    /*</constants>*/
    
    private static /*<name>*/CNErrorUserInfoKey/*</name>*/[] values = new /*<name>*/CNErrorUserInfoKey/*</name>*/[] {/*<value_list>*/AffectedRecords, AffectedRecordIdentifiers, ValidationErrors, KeyPaths/*</value_list>*/};
    
    /*<name>*/CNErrorUserInfoKey/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CNErrorUserInfoKey/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/CNErrorUserInfoKey/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CNErrorUserInfoKey/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Contacts") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNErrorUserInfoAffectedRecordsKey", optional=true)
        public static native NSString AffectedRecords();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNErrorUserInfoAffectedRecordIdentifiersKey", optional=true)
        public static native NSString AffectedRecordIdentifiers();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNErrorUserInfoValidationErrorsKey", optional=true)
        public static native NSString ValidationErrors();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNErrorUserInfoKeyPathsKey", optional=true)
        public static native NSString KeyPaths();
        /*</values>*/
    }
}
