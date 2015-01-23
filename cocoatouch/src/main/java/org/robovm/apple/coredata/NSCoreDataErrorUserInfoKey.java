/*
 * Copyright (C) 2015 Trillian Mobile AB
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
package org.robovm.apple.coredata;

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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreData")/*</annotations>*/
@Marshaler(/*<name>*/NSCoreDataErrorUserInfoKey/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSCoreDataErrorUserInfoKey/*</name>*/ 
    extends /*<extends>*/NSErrorUserInfoKey/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/NSCoreDataErrorUserInfoKey/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static NSCoreDataErrorUserInfoKey toObject(Class<NSCoreDataErrorUserInfoKey> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSCoreDataErrorUserInfoKey.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(NSCoreDataErrorUserInfoKey o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<NSCoreDataErrorUserInfoKey> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<NSCoreDataErrorUserInfoKey> list = new ArrayList<>();
            for (int i = 0, n = o.size(); i < n; i++) {
                list.add(NSCoreDataErrorUserInfoKey.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSCoreDataErrorUserInfoKey> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (NSCoreDataErrorUserInfoKey i : l) {
                array.add(i.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSCoreDataErrorUserInfoKey DetailedErrorsKey = new NSCoreDataErrorUserInfoKey("DetailedErrorsKey");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSCoreDataErrorUserInfoKey ValidationObjectErrorKey = new NSCoreDataErrorUserInfoKey("ValidationObjectErrorKey");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSCoreDataErrorUserInfoKey ValidationKeyErrorKey = new NSCoreDataErrorUserInfoKey("ValidationKeyErrorKey");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSCoreDataErrorUserInfoKey ValidationPredicateErrorKey = new NSCoreDataErrorUserInfoKey("ValidationPredicateErrorKey");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSCoreDataErrorUserInfoKey ValidationValueErrorKey = new NSCoreDataErrorUserInfoKey("ValidationValueErrorKey");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSCoreDataErrorUserInfoKey AffectedStoresErrorKey = new NSCoreDataErrorUserInfoKey("AffectedStoresErrorKey");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSCoreDataErrorUserInfoKey AffectedObjectsErrorKey = new NSCoreDataErrorUserInfoKey("AffectedObjectsErrorKey");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSCoreDataErrorUserInfoKey PersistentStoreSaveConflictsErrorKey = new NSCoreDataErrorUserInfoKey("PersistentStoreSaveConflictsErrorKey");
    /*</constants>*/
    
    private static /*<name>*/NSCoreDataErrorUserInfoKey/*</name>*/[] values = new /*<name>*/NSCoreDataErrorUserInfoKey/*</name>*/[] {/*<value_list>*/DetailedErrorsKey, ValidationObjectErrorKey, ValidationKeyErrorKey, ValidationPredicateErrorKey, ValidationValueErrorKey, AffectedStoresErrorKey, AffectedObjectsErrorKey, PersistentStoreSaveConflictsErrorKey/*</value_list>*/};
    
    /*<name>*/NSCoreDataErrorUserInfoKey/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/NSCoreDataErrorUserInfoKey/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/NSCoreDataErrorUserInfoKey/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSCoreDataErrorUserInfoKey/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreData")/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="NSDetailedErrorsKey", optional=true)
        public static native NSString DetailedErrorsKey();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="NSValidationObjectErrorKey", optional=true)
        public static native NSString ValidationObjectErrorKey();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="NSValidationKeyErrorKey", optional=true)
        public static native NSString ValidationKeyErrorKey();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="NSValidationPredicateErrorKey", optional=true)
        public static native NSString ValidationPredicateErrorKey();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="NSValidationValueErrorKey", optional=true)
        public static native NSString ValidationValueErrorKey();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="NSAffectedStoresErrorKey", optional=true)
        public static native NSString AffectedStoresErrorKey();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="NSAffectedObjectsErrorKey", optional=true)
        public static native NSString AffectedObjectsErrorKey();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSPersistentStoreSaveConflictsErrorKey", optional=true)
        public static native NSString PersistentStoreSaveConflictsErrorKey();
        /*</values>*/
    }
}
