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
package org.robovm.apple.coredata;

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
    public NSPropertyDescription(NSCoder aDecoder) { super((SkipInit) null); initObject(init(aDecoder)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "entity")
    public native NSEntityDescription getEntity();
    @Property(selector = "name")
    public native String getName();
    @Property(selector = "setName:")
    public native void setName(String v);
    @Property(selector = "isOptional")
    public native boolean isOptional();
    @Property(selector = "setOptional:")
    public native void setOptional(boolean v);
    @Property(selector = "isTransient")
    public native boolean isTransient();
    @Property(selector = "setTransient:")
    public native void setTransient(boolean v);
    @Property(selector = "validationPredicates")
    public native NSArray<NSPredicate> getValidationPredicates();
    @Property(selector = "validationWarnings")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getValidationWarnings();
    @Property(selector = "userInfo")
    public native NSDictionary<?, ?> getUserInfo();
    @Property(selector = "setUserInfo:")
    public native void setUserInfo(NSDictionary<?, ?> v);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "isIndexed")
    public native boolean isIndexed();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "setIndexed:")
    public native void setIndexed(boolean v);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "versionHash")
    public native NSData getVersionHash();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "versionHashModifier")
    public native String getVersionHashModifier();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "setVersionHashModifier:")
    public native void setVersionHashModifier(String v);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "isIndexedBySpotlight")
    public native boolean isIndexedBySpotlight();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "setIndexedBySpotlight:")
    public native void setIndexedBySpotlight(boolean v);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "isStoredInExternalRecord")
    public native boolean isStoredInExternalRecord();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "setStoredInExternalRecord:")
    public native void setStoredInExternalRecord(boolean v);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "renamingIdentifier")
    public native String getRenamingIdentifier();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "setRenamingIdentifier:")
    public native void setRenamingIdentifier(String v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "setValidationPredicates:withValidationWarnings:")
    public native void setValidationPredicatesAndWarnings(NSArray<NSPredicate> validationPredicates, @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> validationWarnings);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder coder);
    @Method(selector = "initWithCoder:")
    protected native @Pointer long init(NSCoder aDecoder);
    /*</methods>*/
}
