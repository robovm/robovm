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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ABPersonRelatedNameLabel/*</name>*/ 
    extends /*<extends>*/ABPropertyLabel/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(ABPersonRelatedNameLabel.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final ABPersonRelatedNameLabel Father = new ABPersonRelatedNameLabel("FatherLabel");
    public static final ABPersonRelatedNameLabel Mother = new ABPersonRelatedNameLabel("MotherLabel");
    public static final ABPersonRelatedNameLabel Parent = new ABPersonRelatedNameLabel("ParentLabel");
    public static final ABPersonRelatedNameLabel Brother = new ABPersonRelatedNameLabel("BrotherLabel");
    public static final ABPersonRelatedNameLabel Sister = new ABPersonRelatedNameLabel("SisterLabel");
    public static final ABPersonRelatedNameLabel Child = new ABPersonRelatedNameLabel("ChildLabel");
    public static final ABPersonRelatedNameLabel Friend = new ABPersonRelatedNameLabel("FriendLabel");
    public static final ABPersonRelatedNameLabel Spouse = new ABPersonRelatedNameLabel("SpouseLabel");
    public static final ABPersonRelatedNameLabel Partner = new ABPersonRelatedNameLabel("PartnerLabel");
    public static final ABPersonRelatedNameLabel Assistant = new ABPersonRelatedNameLabel("AssistantLabel");
    public static final ABPersonRelatedNameLabel Manager = new ABPersonRelatedNameLabel("ManagerLabel");
    private static ABPropertyLabel[] values = new ABPropertyLabel[] {Work, Home, Other, Father, Mother, Parent, Brother, Sister, Child, Friend, Spouse, Partner, Assistant, Manager};
    
    private ABPersonRelatedNameLabel(String getterName) {
        super(getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public static ABPropertyLabel valueOf(CFString value) {
        for (ABPropertyLabel v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/ABPersonRelatedNameLabel/*</name>*/.class.getName());
    }
    /*<methods>*/
    @GlobalValue(symbol="kABPersonFatherLabel", optional=true)
    protected static native CFString FatherLabel();
    @GlobalValue(symbol="kABPersonMotherLabel", optional=true)
    protected static native CFString MotherLabel();
    @GlobalValue(symbol="kABPersonParentLabel", optional=true)
    protected static native CFString ParentLabel();
    @GlobalValue(symbol="kABPersonBrotherLabel", optional=true)
    protected static native CFString BrotherLabel();
    @GlobalValue(symbol="kABPersonSisterLabel", optional=true)
    protected static native CFString SisterLabel();
    @GlobalValue(symbol="kABPersonChildLabel", optional=true)
    protected static native CFString ChildLabel();
    @GlobalValue(symbol="kABPersonFriendLabel", optional=true)
    protected static native CFString FriendLabel();
    @GlobalValue(symbol="kABPersonSpouseLabel", optional=true)
    protected static native CFString SpouseLabel();
    @GlobalValue(symbol="kABPersonPartnerLabel", optional=true)
    protected static native CFString PartnerLabel();
    @GlobalValue(symbol="kABPersonAssistantLabel", optional=true)
    protected static native CFString AssistantLabel();
    @GlobalValue(symbol="kABPersonManagerLabel", optional=true)
    protected static native CFString ManagerLabel();
    /*</methods>*/
}
