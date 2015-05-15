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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CloudKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CKMarkNotificationsReadOperation/*</name>*/ 
    extends /*<extends>*/CKOperation/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CKMarkNotificationsReadOperationPtr extends Ptr<CKMarkNotificationsReadOperation, CKMarkNotificationsReadOperationPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CKMarkNotificationsReadOperation.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CKMarkNotificationsReadOperation() {}
    protected CKMarkNotificationsReadOperation(SkipInit skipInit) { super(skipInit); }
    public CKMarkNotificationsReadOperation(NSArray<CKNotificationID> notificationIDs) { super((SkipInit) null); initObject(init(notificationIDs)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "notificationIDs")
    public native NSArray<CKNotificationID> getNotificationIDs();
    @Property(selector = "setNotificationIDs:")
    public native void setNotificationIDs(NSArray<CKNotificationID> v);
    @Property(selector = "markNotificationsReadCompletionBlock")
    public native @Block VoidBlock2<NSArray<CKNotificationID>, NSError> getMarkNotificationsReadCompletionBlock();
    @Property(selector = "setMarkNotificationsReadCompletionBlock:")
    public native void setMarkNotificationsReadCompletionBlock(@Block VoidBlock2<NSArray<CKNotificationID>, NSError> v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithNotificationIDsToMarkRead:")
    protected native @Pointer long init(NSArray<CKNotificationID> notificationIDs);
    /*</methods>*/
}
