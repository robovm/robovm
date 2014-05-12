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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CoreData/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CoreData.class); }/*</bind>*/
    /*<constants>*/
    public static final double VersionNumber10_4 = 46.0;
    public static final double VersionNumber10_4_3 = 77.0;
    public static final double VersionNumber10_5 = 185.0;
    public static final double VersionNumber10_5_3 = 186.0;
    public static final double VersionNumber10_6 = 246.0;
    public static final double VersionNumber10_6_2 = 250.0;
    public static final double VersionNumber10_6_3 = 251.0;
    public static final double VersionNumber10_7 = 358.4;
    public static final double VersionNumber10_7_2 = 358.12;
    public static final double VersionNumber10_7_3 = 358.13;
    public static final double VersionNumber10_7_4 = 358.14;
    public static final double VersionNumber10_8 = 407.5;
    public static final double VersionNumber10_8_2 = 407.7;
    public static final double VersionNumber_iPhoneOS_3_0 = 241.0;
    public static final double VersionNumber_iPhoneOS_3_1 = 248.0;
    public static final double VersionNumber_iPhoneOS_3_2 = 310.2;
    public static final double VersionNumber_iPhoneOS_4_0 = 320.5;
    public static final double VersionNumber_iPhoneOS_4_1 = 320.11;
    public static final double VersionNumber_iPhoneOS_4_2 = 320.15;
    public static final double VersionNumber_iPhoneOS_4_3 = 320.17;
    public static final double VersionNumber_iPhoneOS_5_0 = 386.1;
    public static final double VersionNumber_iPhoneOS_5_1 = 386.5;
    public static final double VersionNumber_iPhoneOS_6_0 = 419.0;
    /*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="NSCoreDataVersionNumber", optional=true)
    public static native double VersionNumber();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSDetailedErrorsKey", optional=true)
    public static native NSString ErrorKeyDetailedErrors();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSValidationObjectErrorKey", optional=true)
    public static native NSString ErrorKeyValidationObject();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSValidationKeyErrorKey", optional=true)
    public static native NSString ErrorKeyValidationKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSValidationPredicateErrorKey", optional=true)
    public static native NSString ErrorKeyValidationPredicate();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSValidationValueErrorKey", optional=true)
    public static native NSString ErrorKeyValidationValue();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSAffectedStoresErrorKey", optional=true)
    public static native NSString ErrorKeyAffectedStores();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSAffectedObjectsErrorKey", optional=true)
    public static native NSString ErrorKeyAffectedObjects();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSSQLiteErrorDomain", optional=true)
    public static native String ErrorDomainSQLite();
    /*</methods>*/
}
