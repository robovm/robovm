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
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CloudKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CKContainer/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Notifications {
        /**
         * @since Available in iOS 9.0 and later.
         */
        public static NSObject observeAccountChanged(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(AccountChangedNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
    }
    
    /*<ptr>*/public static class CKContainerPtr extends Ptr<CKContainer, CKContainerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CKContainer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CKContainer() {}
    protected CKContainer(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "containerIdentifier")
    public native String getContainerIdentifier();
    @Property(selector = "privateCloudDatabase")
    public native CKDatabase getPrivateCloudDatabase();
    @Property(selector = "publicCloudDatabase")
    public native CKDatabase getPublicCloudDatabase();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="CKOwnerDefaultName", optional=true)
    public static native String getDefaultOwnerName();
    /**
     * @since Available in iOS 9.0 and later.
     */
    @GlobalValue(symbol="CKAccountChangedNotification", optional=true)
    public static native NSString AccountChangedNotification();
    
    @Method(selector = "addOperation:")
    public native void addOperation(CKOperation operation);
    @Method(selector = "defaultContainer")
    public static native CKContainer getDefaultContainer();
    @Method(selector = "containerWithIdentifier:")
    public static native CKContainer create(String containerIdentifier);
    @Method(selector = "accountStatusWithCompletionHandler:")
    public native void getAccountStatus(@Block VoidBlock2<CKAccountStatus, NSError> completionHandler);
    @Method(selector = "statusForApplicationPermission:completionHandler:")
    public native void getStatusForApplicationPermission(CKApplicationPermissions applicationPermission, @Block VoidBlock2<CKApplicationPermissionStatus, NSError> completionHandler);
    @Method(selector = "requestApplicationPermission:completionHandler:")
    public native void requestApplicationPermission(CKApplicationPermissions applicationPermission, @Block VoidBlock2<CKApplicationPermissionStatus, NSError> completionHandler);
    @Method(selector = "fetchUserRecordIDWithCompletionHandler:")
    public native void fetchUserRecordID(@Block VoidBlock2<CKRecordID, NSError> completionHandler);
    @Method(selector = "discoverAllContactUserInfosWithCompletionHandler:")
    public native void discoverAllContactUserInfos(@Block VoidBlock2<NSArray<CKDiscoveredUserInfo>, NSError> completionHandler);
    @Method(selector = "discoverUserInfoWithEmailAddress:completionHandler:")
    public native void discoverUserInfo(String email, @Block VoidBlock2<CKDiscoveredUserInfo, NSError> completionHandler);
    @Method(selector = "discoverUserInfoWithUserRecordID:completionHandler:")
    public native void discoverUserInfo(CKRecordID userRecordID, @Block VoidBlock2<CKDiscoveredUserInfo, NSError> completionHandler);
    /*</methods>*/
}
