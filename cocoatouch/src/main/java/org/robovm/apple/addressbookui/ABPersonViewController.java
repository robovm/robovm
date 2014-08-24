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
package org.robovm.apple.addressbookui;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.addressbook.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AddressBookUI") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ABPersonViewController/*</name>*/ 
    extends /*<extends>*/UIViewController/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class ABPersonViewControllerPtr extends Ptr<ABPersonViewController, ABPersonViewControllerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(ABPersonViewController.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public ABPersonViewController() {}
    protected ABPersonViewController(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    public List<ABPersonProperty> getDisplayedProperties() {
        NSArray<NSNumber> p = getDisplayedProperties0();
        List<ABPersonProperty> properties = new ArrayList<>();
        if (p == null) return properties;
        for (NSNumber property : p) {
            properties.add(ABPersonProperty.valueOf(property.intValue()));
        }
        return properties;
    }
    public void setDisplayedProperties(ABPersonProperty...properties) {
        setDisplayedProperties(Arrays.asList(properties));
    }
    public void setDisplayedProperties(List<ABPersonProperty> properties) {
        NSMutableArray<NSNumber> p = new NSMutableArray<>();
        for (ABPersonProperty property : properties) {
            p.add(NSNumber.valueOf(property.value()));
        }
        setDisplayedProperties0(p);
    }
    /*<properties>*/
    @Property(selector = "personViewDelegate")
    public native ABPersonViewControllerDelegate getPersonViewDelegate();
    @Property(selector = "setPersonViewDelegate:", strongRef = true)
    public native void setPersonViewDelegate(ABPersonViewControllerDelegate v);
    @Property(selector = "addressBook")
    public native ABAddressBook getAddressBook();
    @Property(selector = "setAddressBook:")
    public native void setAddressBook(ABAddressBook v);
    @Property(selector = "displayedPerson")
    public native ABPerson getDisplayedPerson();
    @Property(selector = "setDisplayedPerson:")
    public native void setDisplayedPerson(ABPerson v);
    @Property(selector = "displayedProperties")
    protected native NSArray<NSNumber> getDisplayedProperties0();
    @Property(selector = "setDisplayedProperties:")
    protected native void setDisplayedProperties0(NSArray<NSNumber> v);
    @Property(selector = "allowsEditing")
    public native boolean isAllowsEditing();
    @Property(selector = "setAllowsEditing:")
    public native void setAllowsEditing(boolean v);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "allowsActions")
    public native boolean isAllowsActions();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "setAllowsActions:")
    public native void setAllowsActions(boolean v);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "shouldShowLinkedPeople")
    public native boolean isShouldShowLinkedPeople();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "setShouldShowLinkedPeople:")
    public native void setShouldShowLinkedPeople(boolean v);
    /*</properties>*/
    /*<members>*//*</members>*/
    public void setHighlightedItem(ABPersonProperty property, int identifier) {
        setHighlightedItem(property.value(), identifier);
    }
    /*<methods>*/
    @Method(selector = "setHighlightedItemForProperty:withIdentifier:")
    protected native void setHighlightedItem(int property, int identifier);
    /*</methods>*/
}
