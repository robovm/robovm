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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CNContactPickerViewController/*</name>*/ 
    extends /*<extends>*/UIViewController/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CNContactPickerViewControllerPtr extends Ptr<CNContactPickerViewController, CNContactPickerViewControllerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CNContactPickerViewController.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CNContactPickerViewController() {}
    protected CNContactPickerViewController(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "displayedPropertyKeys")
    public native @org.robovm.rt.bro.annotation.Marshaler(CNContactPropertyKey.AsListMarshaler.class) List<CNContactPropertyKey> getDisplayedPropertyKeys();
    @Property(selector = "setDisplayedPropertyKeys:")
    public native void setDisplayedPropertyKeys(@org.robovm.rt.bro.annotation.Marshaler(CNContactPropertyKey.AsListMarshaler.class) List<CNContactPropertyKey> v);
    @Property(selector = "delegate")
    public native CNContactPickerDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(CNContactPickerDelegate v);
    @Property(selector = "predicateForEnablingContact")
    public native NSPredicate getPredicateForEnablingContact();
    @Property(selector = "setPredicateForEnablingContact:")
    public native void setPredicateForEnablingContact(NSPredicate v);
    @Property(selector = "predicateForSelectionOfContact")
    public native NSPredicate getPredicateForSelectionOfContact();
    @Property(selector = "setPredicateForSelectionOfContact:")
    public native void setPredicateForSelectionOfContact(NSPredicate v);
    @Property(selector = "predicateForSelectionOfProperty")
    public native NSPredicate getPredicateForSelectionOfProperty();
    @Property(selector = "setPredicateForSelectionOfProperty:")
    public native void setPredicateForSelectionOfProperty(NSPredicate v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
