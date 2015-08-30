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
/**
 * @since Available in iOS 9.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Contacts") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CNContactStore/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Notifications {
        /**
         * @since Available in iOS 9.0 and later.
         */
        public static NSObject observeDidChange(CNContactStore object, final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(DidChangeNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
    }

    /*<ptr>*/public static class CNContactStorePtr extends Ptr<CNContactStore, CNContactStorePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CNContactStore.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CNContactStore() {}
    protected CNContactStore(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    public boolean enumerateContacts(CNContactFetchRequest fetchRequest, VoidBlock2<CNContact, BooleanPtr> block) throws NSErrorException {
        NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
        boolean result = enumerateContacts(fetchRequest, ptr, block);
        if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
        return result;
    }
    
    /*<methods>*/
    /**
     * @since Available in iOS 9.0 and later.
     */
    @GlobalValue(symbol="CNContactStoreDidChangeNotification", optional=true)
    public static native NSString DidChangeNotification();
    
    @Method(selector = "requestAccessForEntityType:completionHandler:")
    public native void requestAccessForEntityType(CNEntityType entityType, @Block VoidBlock2<Boolean, NSError> completionHandler);
    public NSArray<CNContact> getUnifiedContactsMatchingPredicate(NSPredicate predicate, List<CNContactPropertyKey> keys) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       NSArray<CNContact> result = getUnifiedContactsMatchingPredicate(predicate, keys, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "unifiedContactsMatchingPredicate:keysToFetch:error:")
    private native NSArray<CNContact> getUnifiedContactsMatchingPredicate(NSPredicate predicate, @org.robovm.rt.bro.annotation.Marshaler(CNContactPropertyKey.AsListMarshaler.class) List<CNContactPropertyKey> keys, NSError.NSErrorPtr error);
    public CNContact getUnifiedContact(String identifier, List<CNContactPropertyKey> keys) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       CNContact result = getUnifiedContact(identifier, keys, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "unifiedContactWithIdentifier:keysToFetch:error:")
    private native CNContact getUnifiedContact(String identifier, @org.robovm.rt.bro.annotation.Marshaler(CNContactPropertyKey.AsListMarshaler.class) List<CNContactPropertyKey> keys, NSError.NSErrorPtr error);
    @Method(selector = "enumerateContactsWithFetchRequest:error:usingBlock:")
    private native boolean enumerateContacts(CNContactFetchRequest fetchRequest, NSError.NSErrorPtr error, @Block VoidBlock2<CNContact, BooleanPtr> block);
    public NSArray<CNGroup> getGroupsMatchingPredicate(NSPredicate predicate) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       NSArray<CNGroup> result = getGroupsMatchingPredicate(predicate, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "groupsMatchingPredicate:error:")
    private native NSArray<CNGroup> getGroupsMatchingPredicate(NSPredicate predicate, NSError.NSErrorPtr error);
    public NSArray<CNContainer> getContainersMatchingPredicate(NSPredicate predicate) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       NSArray<CNContainer> result = getContainersMatchingPredicate(predicate, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "containersMatchingPredicate:error:")
    private native NSArray<CNContainer> getContainersMatchingPredicate(NSPredicate predicate, NSError.NSErrorPtr error);
    public boolean executeSaveRequest(CNSaveRequest saveRequest) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = executeSaveRequest(saveRequest, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "executeSaveRequest:error:")
    private native boolean executeSaveRequest(CNSaveRequest saveRequest, NSError.NSErrorPtr error);
    @Method(selector = "defaultContainerIdentifier")
    public native String getDefaultContainerIdentifier();
    @Method(selector = "authorizationStatusForEntityType:")
    public static native CNAuthorizationStatus getAuthorizationStatusForEntityType(CNEntityType entityType);
    /*</methods>*/
}
