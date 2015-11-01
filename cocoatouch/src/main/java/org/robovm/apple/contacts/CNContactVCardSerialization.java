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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CNContactVCardSerialization/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CNContactVCardSerializationPtr extends Ptr<CNContactVCardSerialization, CNContactVCardSerializationPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CNContactVCardSerialization.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CNContactVCardSerialization() {}
    protected CNContactVCardSerialization(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "descriptorForRequiredKeys")
    public static native String getDescriptorForRequiredKeys();
    public static NSData convertContactsToData(NSArray<CNContact> contacts) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       NSData result = convertContactsToData(contacts, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "dataWithContacts:error:")
    private static native NSData convertContactsToData(NSArray<CNContact> contacts, NSError.NSErrorPtr error);
    public static NSArray<CNContact> convertsDataToContacts(NSData data) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       NSArray<CNContact> result = convertsDataToContacts(data, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "contactsWithData:error:")
    private static native NSArray<CNContact> convertsDataToContacts(NSData data, NSError.NSErrorPtr error);
    /*</methods>*/
}
