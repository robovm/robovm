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
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CloudKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CKModifySubscriptionsOperation/*</name>*/ 
    extends /*<extends>*/CKDatabaseOperation/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CKModifySubscriptionsOperationPtr extends Ptr<CKModifySubscriptionsOperation, CKModifySubscriptionsOperationPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CKModifySubscriptionsOperation.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CKModifySubscriptionsOperation() {}
    protected CKModifySubscriptionsOperation(SkipInit skipInit) { super(skipInit); }
    public CKModifySubscriptionsOperation(NSArray<CKSubscription> subscriptionsToSave, @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> subscriptionIDsToDelete) { super((SkipInit) null); initObject(init(subscriptionsToSave, subscriptionIDsToDelete)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "subscriptionsToSave")
    public native NSArray<CKSubscription> getSubscriptionsToSave();
    @Property(selector = "setSubscriptionsToSave:")
    public native void setSubscriptionsToSave(NSArray<CKSubscription> v);
    @Property(selector = "subscriptionIDsToDelete")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getSubscriptionIDsToDelete();
    @Property(selector = "setSubscriptionIDsToDelete:")
    public native void setSubscriptionIDsToDelete(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> v);
    @Property(selector = "modifySubscriptionsCompletionBlock")
    public native @Block VoidBlock3<NSArray<CKSubscription>, NSArray<NSString>, NSError> getModifySubscriptionsCompletionBlock();
    @Property(selector = "setModifySubscriptionsCompletionBlock:")
    public native void setModifySubscriptionsCompletionBlock(@Block VoidBlock3<NSArray<CKSubscription>, NSArray<NSString>, NSError> v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithSubscriptionsToSave:subscriptionIDsToDelete:")
    protected native @Pointer long init(NSArray<CKSubscription> subscriptionsToSave, @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> subscriptionIDsToDelete);
    /*</methods>*/
}
