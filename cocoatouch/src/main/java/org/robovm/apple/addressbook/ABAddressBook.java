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
    
    private static final java.util.concurrent.atomic.AtomicLong changeCallbackId = new java.util.concurrent.atomic.AtomicLong();
    private static final LongMap<ExternalChangeCallback> externalChangeCallbacks = new LongMap<>();
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
            for (LongMap.Entry<ExternalChangeCallback> entry : externalChangeCallbacks.entries()) {
                if (entry.value == callback) {
                    refconId = entry.key;
                    externalChangeCallbacks.remove(refconId);
                    break;
                }
            }
        }
        unregisterExternalChangeCallback(new FunctionPtr(cbExternalChange), refconId);
    }
    
    public static String getLocalizedLabel(ABPropertyLabel label) {
        return getLocalizedLabel(label.value().toString());
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
    public static ABAddressBook create(NSDictionary<NSString, ?> options) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       ABAddressBook result = create(options, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="ABAddressBookCreateWithOptions", optional=true)
    private static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) ABAddressBook create(NSDictionary<NSString, ?> options, NSError.NSErrorPtr error);
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
    public boolean save() throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = save(ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Bridge(symbol="ABAddressBookSave", optional=true)
    private native boolean save(NSError.NSErrorPtr error);
    @Bridge(symbol="ABAddressBookHasUnsavedChanges", optional=true)
    public native boolean hasUnsavedChanges();
    public boolean addRecord(ABRecord record) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = addRecord(record, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Bridge(symbol="ABAddressBookAddRecord", optional=true)
    private native boolean addRecord(ABRecord record, NSError.NSErrorPtr error);
    public boolean removeRecord(ABRecord record) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = removeRecord(record, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Bridge(symbol="ABAddressBookRemoveRecord", optional=true)
    private native boolean removeRecord(ABRecord record, NSError.NSErrorPtr error);
    @Bridge(symbol="ABAddressBookCopyLocalizedLabel", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFString.AsStringNoRetainMarshaler.class) String getLocalizedLabel(String label);
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
    public native @org.robovm.rt.bro.annotation.Marshaler(ABRecord.NoRetainMarshaler.class) ABSource getDefaultSource();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="ABAddressBookGetSourceWithRecordID", optional=true)
    public native ABSource getSource(int sourceID);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="ABAddressBookCopyArrayOfAllSources", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(ABSource.AsListMarshaler.class) List<ABSource> getAllSources();
    @Bridge(symbol="ABAddressBookGetPersonCount", optional=true)
    public native @MachineSizedSInt long getPersonCount();
    @Bridge(symbol="ABAddressBookGetPersonWithRecordID", optional=true)
    public native ABPerson getPerson(int recordID);
    @Bridge(symbol="ABAddressBookCopyArrayOfAllPeople", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(ABPerson.AsListMarshaler.class) List<ABPerson> getAllPeople();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="ABAddressBookCopyArrayOfAllPeopleInSource", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(ABPerson.AsListMarshaler.class) List<ABPerson> getAllPeopleInSource(ABSource source);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="ABAddressBookCopyArrayOfAllPeopleInSourceWithSortOrdering", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(ABPerson.AsListMarshaler.class) List<ABPerson> getAllPeopleInSource(ABSource source, ABPersonSortOrdering sortOrdering);
    @Bridge(symbol="ABAddressBookCopyPeopleWithName", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(ABPerson.AsListMarshaler.class) List<ABPerson> getPeople(String name);
    @Bridge(symbol="ABAddressBookGetGroupWithRecordID", optional=true)
    public native ABGroup getGroup(int recordID);
    @Bridge(symbol="ABAddressBookGetGroupCount", optional=true)
    public native @MachineSizedSInt long getGroupCount();
    @Bridge(symbol="ABAddressBookCopyArrayOfAllGroups", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(ABGroup.AsListMarshaler.class) List<ABGroup> getAllGroups();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="ABAddressBookCopyArrayOfAllGroupsInSource", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(ABGroup.AsListMarshaler.class) List<ABGroup> getAllGroupsInSource(ABSource source);
    /*</methods>*/
}
