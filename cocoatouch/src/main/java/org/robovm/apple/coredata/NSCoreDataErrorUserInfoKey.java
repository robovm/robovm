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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSCoreDataErrorUserInfoKey/*</name>*/ 
    extends /*<extends>*/NSErrorUserInfoKey/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(NSCoreDataErrorUserInfoKey.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSCoreDataErrorUserInfoKey DetailedErrors = new NSCoreDataErrorUserInfoKey("DetailedErrorsKey");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSCoreDataErrorUserInfoKey ValidationObject = new NSCoreDataErrorUserInfoKey("ValidationObjectErrorKey");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSCoreDataErrorUserInfoKey ValidationKey = new NSCoreDataErrorUserInfoKey("ValidationKeyErrorKey");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSCoreDataErrorUserInfoKey ValidationPredicate = new NSCoreDataErrorUserInfoKey("ValidationPredicateErrorKey");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSCoreDataErrorUserInfoKey ValidationValue = new NSCoreDataErrorUserInfoKey("ValidationValueErrorKey");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSCoreDataErrorUserInfoKey AffectedStores = new NSCoreDataErrorUserInfoKey("AffectedStoresErrorKey");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSCoreDataErrorUserInfoKey AffectedObjects = new NSCoreDataErrorUserInfoKey("AffectedObjectsErrorKey");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSCoreDataErrorUserInfoKey SaveConflicts = new NSCoreDataErrorUserInfoKey("PersistentStoreSaveConflictsErrorKey");
    
    private static NSCoreDataErrorUserInfoKey[] values = new NSCoreDataErrorUserInfoKey[] {DetailedErrors, ValidationKey, ValidationObject, ValidationPredicate,
        ValidationValue, AffectedStores, AffectedObjects, SaveConflicts};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private NSCoreDataErrorUserInfoKey(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static NSCoreDataErrorUserInfoKey valueOf(NSString value) {
        for (NSCoreDataErrorUserInfoKey v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSCoreDataErrorUserInfoKey/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSDetailedErrorsKey", optional=true)
    protected static native NSString DetailedErrorsKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSValidationObjectErrorKey", optional=true)
    protected static native NSString ValidationObjectErrorKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSValidationKeyErrorKey", optional=true)
    protected static native NSString ValidationKeyErrorKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSValidationPredicateErrorKey", optional=true)
    protected static native NSString ValidationPredicateErrorKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSValidationValueErrorKey", optional=true)
    protected static native NSString ValidationValueErrorKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSAffectedStoresErrorKey", optional=true)
    protected static native NSString AffectedStoresErrorKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSAffectedObjectsErrorKey", optional=true)
    protected static native NSString AffectedObjectsErrorKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSPersistentStoreSaveConflictsErrorKey", optional=true)
    protected static native NSString PersistentStoreSaveConflictsErrorKey();
    /*</methods>*/
}
