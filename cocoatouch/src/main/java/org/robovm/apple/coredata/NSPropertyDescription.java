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
/**
 * @since Available in iOS 3.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CoreData") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSPropertyDescription/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class NSPropertyDescriptionPtr extends Ptr<NSPropertyDescription, NSPropertyDescriptionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSPropertyDescription.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSPropertyDescription() {}
    protected NSPropertyDescription(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "entity")
    public native NSEntityDescription entity();
    @Method(selector = "name")
    public native String name();
    @Method(selector = "setName:")
    public native void setName(String name);
    @Method(selector = "isOptional")
    public native boolean isOptional();
    @Method(selector = "setOptional:")
    public native void setOptional(boolean flag);
    @Method(selector = "isTransient")
    public native boolean isTransient();
    @Method(selector = "setTransient:")
    public native void setTransient(boolean flag);
    @Method(selector = "validationPredicates")
    public native NSArray<?> validationPredicates();
    @Method(selector = "validationWarnings")
    public native NSArray<?> validationWarnings();
    @Method(selector = "setValidationPredicates:withValidationWarnings:")
    public native void setValidationPredicates$withValidationWarnings$(NSArray<?> validationPredicates, NSArray<?> validationWarnings);
    @Method(selector = "userInfo")
    public native NSDictionary<?, ?> userInfo();
    @Method(selector = "setUserInfo:")
    public native void setUserInfo(NSDictionary<?, ?> dictionary);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "isIndexed")
    public native boolean isIndexed();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "setIndexed:")
    public native void setIndexed(boolean flag);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "versionHash")
    public native NSData versionHash();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "versionHashModifier")
    public native String versionHashModifier();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "setVersionHashModifier:")
    public native void setVersionHashModifier(String modifierString);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "isIndexedBySpotlight")
    public native boolean isIndexedBySpotlight();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "setIndexedBySpotlight:")
    public native void setIndexedBySpotlight(boolean flag);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "isStoredInExternalRecord")
    public native boolean isStoredInExternalRecord();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "setStoredInExternalRecord:")
    public native void setStoredInExternalRecord(boolean flag);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "renamingIdentifier")
    public native String renamingIdentifier();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "setRenamingIdentifier:")
    public native void setRenamingIdentifier(String value);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
