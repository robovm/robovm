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
package org.robovm.apple.contactsui;

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
import org.robovm.apple.contacts.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 9.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("ContactsUI") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CNContactViewController/*</name>*/ 
    extends /*<extends>*/UIViewController/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CNContactViewControllerPtr extends Ptr<CNContactViewController, CNContactViewControllerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CNContactViewController.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CNContactViewController() {}
    protected CNContactViewController(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "contact")
    public native CNContact getContact();
    @Property(selector = "displayedPropertyKeys")
    public native @org.robovm.rt.bro.annotation.Marshaler(CNContactPropertyKey.AsListMarshaler.class) List<CNContactPropertyKey> getDisplayedPropertyKeys();
    @Property(selector = "setDisplayedPropertyKeys:")
    public native void setDisplayedPropertyKeys(@org.robovm.rt.bro.annotation.Marshaler(CNContactPropertyKey.AsListMarshaler.class) List<CNContactPropertyKey> v);
    @Property(selector = "delegate")
    public native CNContactViewControllerDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(CNContactViewControllerDelegate v);
    @Property(selector = "contactStore")
    public native CNContactStore getContactStore();
    @Property(selector = "setContactStore:")
    public native void setContactStore(CNContactStore v);
    @Property(selector = "parentGroup")
    public native CNGroup getParentGroup();
    @Property(selector = "setParentGroup:")
    public native void setParentGroup(CNGroup v);
    @Property(selector = "parentContainer")
    public native CNContainer getParentContainer();
    @Property(selector = "setParentContainer:")
    public native void setParentContainer(CNContainer v);
    @Property(selector = "alternateName")
    public native String getAlternateName();
    @Property(selector = "setAlternateName:")
    public native void setAlternateName(String v);
    @Property(selector = "message")
    public native String getMessage();
    @Property(selector = "setMessage:")
    public native void setMessage(String v);
    @Property(selector = "allowsEditing")
    public native boolean allowsEditing();
    @Property(selector = "setAllowsEditing:")
    public native void setAllowsEditing(boolean v);
    @Property(selector = "allowsActions")
    public native boolean allowsActions();
    @Property(selector = "setAllowsActions:")
    public native void setAllowsActions(boolean v);
    @Property(selector = "shouldShowLinkedContacts")
    public native boolean shouldShowLinkedContacts();
    @Property(selector = "setShouldShowLinkedContacts:")
    public native void setShouldShowLinkedContacts(boolean v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "highlightPropertyWithKey:identifier:")
    public native void highlightProperty(CNContactPropertyKey key, String identifier);
    @Method(selector = "descriptorForRequiredKeys")
    public static native String getDescriptorForRequiredKeys();
    @Method(selector = "viewControllerForContact:")
    public static native CNContactViewController createForContact(CNContact contact);
    @Method(selector = "viewControllerForUnknownContact:")
    public static native CNContactViewController createForUnknownContact(CNContact contact);
    @Method(selector = "viewControllerForNewContact:")
    public static native CNContactViewController createForNewContact(CNContact contact);
    /*</methods>*/
}
