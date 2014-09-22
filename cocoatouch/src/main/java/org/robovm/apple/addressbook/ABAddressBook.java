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
package org.robovm.apple.addressbook;

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
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("AddressBook")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ABAddressBook/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    public interface RequestAccessCompletionHandler {
        void requestAccess(boolean granted, NSError error);
    }
    public interface ExternalChangeCallback {
        void externalChange(ABAddressBook addressBook, NSDictionary<?, ?> info);
    }
    
    private static java.util.concurrent.atomic.AtomicLong changeCallbackId = new java.util.concurrent.atomic.AtomicLong();
    private static Map<Long, ExternalChangeCallback> externalChangeCallbacks = new HashMap<Long, ExternalChangeCallback>();
    private static final java.lang.reflect.Method cbExternalChange;
    
    static {
        try {
            cbExternalChange = ABAddressBook.class.getDeclaredMethod("cbExternalChange", ABAddressBook.class, NSDictionary.class, long.class);
        } catch (Throwable e) {
            throw new Error(e);
        }
    }
    /*<bind>*/static { Bro.bind(ABAddressBook.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    @Callback
    private static void cbExternalChange(ABAddressBook addressBook, NSDictionary<?, ?> info, @Pointer long refcon) {
        ExternalChangeCallback callback = null;
        synchronized (externalChangeCallbacks) {
            callback = externalChangeCallbacks.get(refcon);
        }
        callback.externalChange(addressBook, info);
    }
    
    /**
    * @since Available in iOS 6.0 and later.
    */
    public static ABAddressBook create(NSDictionary<NSString, ?> options) {
        ABAddressBook addressBook = create(options, null);
        return addressBook;
    }
    
    public boolean save() {
        return save(null);
    }
    
    public boolean addRecord(ABRecord record) {
        return addRecord(record, null);
    }
    
    public boolean removeRecord(ABRecord record) {
        return removeRecord(record, null);
    }
    
    public void registerExternalChangeCallback(ExternalChangeCallback callback) {
        long refconId = ABAddressBook.changeCallbackId.getAndIncrement();
        registerExternalChangeCallback(new FunctionPtr(cbExternalChange), refconId);
        synchronized (externalChangeCallbacks) {
            externalChangeCallbacks.put(refconId, callback);
        }
    }
    public void unregisterExternalChangeCallback(ExternalChangeCallback callback) {
        long refconId = 0;
        synchronized (externalChangeCallbacks) {
            for (Map.Entry<Long, ExternalChangeCallback> entry : externalChangeCallbacks.entrySet()) {
                if (entry.getValue() == callback) {
                    refconId = entry.getKey();
                    externalChangeCallbacks.remove(refconId);
                    break;
                }
            }
        }
        unregisterExternalChangeCallback(new FunctionPtr(cbExternalChange), refconId);
    }
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="ABAddressBookGetAuthorizationStatus", optional=true)
    public static native ABAuthorizationStatus getAuthorizationStatus();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="ABAddressBookCreateWithOptions", optional=true)
    protected static native ABAddressBook create(NSDictionary<NSString, ?> options, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Bridge(symbol="ABAddressBookCreate", optional=true)
    public static native ABAddressBook create();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="ABAddressBookRequestAccessWithCompletion", optional=true)
    public native void requestAccess(@Block RequestAccessCompletionHandler completion);
    @Bridge(symbol="ABAddressBookSave", optional=true)
    protected native boolean save(NSError.NSErrorPtr error);
    @Bridge(symbol="ABAddressBookHasUnsavedChanges", optional=true)
    public native boolean hasUnsavedChanges();
    @Bridge(symbol="ABAddressBookAddRecord", optional=true)
    protected native boolean addRecord(ABRecord record, NSError.NSErrorPtr error);
    @Bridge(symbol="ABAddressBookRemoveRecord", optional=true)
    protected native boolean removeRecord(ABRecord record, NSError.NSErrorPtr error);
    @Bridge(symbol="ABAddressBookCopyLocalizedLabel", optional=true)
    public static native String getLocalizedLabel(String label);
    @Bridge(symbol="ABAddressBookRegisterExternalChangeCallback", optional=true)
    protected native void registerExternalChangeCallback(FunctionPtr callback, @Pointer long context);
    @Bridge(symbol="ABAddressBookUnregisterExternalChangeCallback", optional=true)
    protected native void unregisterExternalChangeCallback(FunctionPtr callback, @Pointer long context);
    @Bridge(symbol="ABAddressBookRevert", optional=true)
    public native void revert();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="ABAddressBookCopyDefaultSource", optional=true)
    public native ABSource getDefaultSource();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="ABAddressBookGetSourceWithRecordID", optional=true)
    public native ABSource getSource(int sourceID);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="ABAddressBookCopyArrayOfAllSources", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFArray.AsListMarshaler.class) List<ABSource> getAllSources();
    @Bridge(symbol="ABAddressBookGetPersonCount", optional=true)
    public native @MachineSizedSInt long getPersonCount();
    @Bridge(symbol="ABAddressBookGetPersonWithRecordID", optional=true)
    public native ABPerson getPerson(int recordID);
    @Bridge(symbol="ABAddressBookCopyArrayOfAllPeople", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFArray.AsListMarshaler.class) List<ABPerson> getAllPeople();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="ABAddressBookCopyArrayOfAllPeopleInSource", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFArray.AsListMarshaler.class) List<ABPerson> getAllPeopleInSource(ABSource source);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="ABAddressBookCopyArrayOfAllPeopleInSourceWithSortOrdering", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFArray.AsListMarshaler.class) List<ABPerson> getAllPeopleInSource(ABSource source, ABPersonSortOrdering sortOrdering);
    @Bridge(symbol="ABAddressBookCopyPeopleWithName", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFArray.AsListMarshaler.class) List<ABPerson> getPeople(String name);
    @Bridge(symbol="ABAddressBookGetGroupWithRecordID", optional=true)
    public native ABGroup getGroup(int recordID);
    @Bridge(symbol="ABAddressBookGetGroupCount", optional=true)
    public native @MachineSizedSInt long getGroupCount();
    @Bridge(symbol="ABAddressBookCopyArrayOfAllGroups", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFArray.AsListMarshaler.class) List<ABGroup> getAllGroups();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="ABAddressBookCopyArrayOfAllGroupsInSource", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFArray.AsListMarshaler.class) List<ABGroup> getAllGroupsInSource(ABSource source);
    /*</methods>*/
}
