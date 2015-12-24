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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CNMutableContact/*</name>*/ 
    extends /*<extends>*/CNContact/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CNMutableContactPtr extends Ptr<CNMutableContact, CNMutableContactPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CNMutableContact.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CNMutableContact() {}
    protected CNMutableContact(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "contactType")
    public native CNContactType getContactType();
    @Property(selector = "setContactType:")
    public native void setContactType(CNContactType v);
    @Property(selector = "namePrefix")
    public native String getNamePrefix();
    @Property(selector = "setNamePrefix:")
    public native void setNamePrefix(String v);
    @Property(selector = "givenName")
    public native String getGivenName();
    @Property(selector = "setGivenName:")
    public native void setGivenName(String v);
    @Property(selector = "middleName")
    public native String getMiddleName();
    @Property(selector = "setMiddleName:")
    public native void setMiddleName(String v);
    @Property(selector = "familyName")
    public native String getFamilyName();
    @Property(selector = "setFamilyName:")
    public native void setFamilyName(String v);
    @Property(selector = "previousFamilyName")
    public native String getPreviousFamilyName();
    @Property(selector = "setPreviousFamilyName:")
    public native void setPreviousFamilyName(String v);
    @Property(selector = "nameSuffix")
    public native String getNameSuffix();
    @Property(selector = "setNameSuffix:")
    public native void setNameSuffix(String v);
    @Property(selector = "nickname")
    public native String getNickname();
    @Property(selector = "setNickname:")
    public native void setNickname(String v);
    @Property(selector = "phoneticGivenName")
    public native String getPhoneticGivenName();
    @Property(selector = "setPhoneticGivenName:")
    public native void setPhoneticGivenName(String v);
    @Property(selector = "phoneticMiddleName")
    public native String getPhoneticMiddleName();
    @Property(selector = "setPhoneticMiddleName:")
    public native void setPhoneticMiddleName(String v);
    @Property(selector = "phoneticFamilyName")
    public native String getPhoneticFamilyName();
    @Property(selector = "setPhoneticFamilyName:")
    public native void setPhoneticFamilyName(String v);
    @Property(selector = "organizationName")
    public native String getOrganizationName();
    @Property(selector = "setOrganizationName:")
    public native void setOrganizationName(String v);
    @Property(selector = "departmentName")
    public native String getDepartmentName();
    @Property(selector = "setDepartmentName:")
    public native void setDepartmentName(String v);
    @Property(selector = "jobTitle")
    public native String getJobTitle();
    @Property(selector = "setJobTitle:")
    public native void setJobTitle(String v);
    @Property(selector = "note")
    public native String getNote();
    @Property(selector = "setNote:")
    public native void setNote(String v);
    @Property(selector = "imageData")
    public native NSData getImageData();
    @Property(selector = "setImageData:")
    public native void setImageData(NSData v);
    @Property(selector = "phoneNumbers")
    public native NSArray<CNLabeledValue<CNPhoneNumber>> getPhoneNumbers();
    @Property(selector = "setPhoneNumbers:")
    public native void setPhoneNumbers(NSArray<CNLabeledValue<CNPhoneNumber>> v);
    @Property(selector = "emailAddresses")
    public native NSArray<CNLabeledValue<String>> getEmailAddresses();
    @Property(selector = "setEmailAddresses:")
    public native void setEmailAddresses(NSArray<CNLabeledValue<String>> v);
    @Property(selector = "postalAddresses")
    public native NSArray<CNLabeledValue<CNPostalAddress>> getPostalAddresses();
    @Property(selector = "setPostalAddresses:")
    public native void setPostalAddresses(NSArray<CNLabeledValue<CNPostalAddress>> v);
    @Property(selector = "urlAddresses")
    public native NSArray<CNLabeledValue<String>> getUrlAddresses();
    @Property(selector = "setUrlAddresses:")
    public native void setUrlAddresses(NSArray<CNLabeledValue<String>> v);
    @Property(selector = "contactRelations")
    public native NSArray<CNLabeledValue<CNContactRelation>> getContactRelations();
    @Property(selector = "setContactRelations:")
    public native void setContactRelations(NSArray<CNLabeledValue<CNContactRelation>> v);
    @Property(selector = "socialProfiles")
    public native NSArray<CNLabeledValue<CNSocialProfile>> getSocialProfiles();
    @Property(selector = "setSocialProfiles:")
    public native void setSocialProfiles(NSArray<CNLabeledValue<CNSocialProfile>> v);
    @Property(selector = "instantMessageAddresses")
    public native NSArray<CNLabeledValue<CNInstantMessageAddress>> getInstantMessageAddresses();
    @Property(selector = "setInstantMessageAddresses:")
    public native void setInstantMessageAddresses(NSArray<CNLabeledValue<CNInstantMessageAddress>> v);
    @Property(selector = "birthday")
    public native NSDateComponents getBirthday();
    @Property(selector = "setBirthday:")
    public native void setBirthday(NSDateComponents v);
    @Property(selector = "nonGregorianBirthday")
    public native NSDateComponents getNonGregorianBirthday();
    @Property(selector = "setNonGregorianBirthday:")
    public native void setNonGregorianBirthday(NSDateComponents v);
    @Property(selector = "dates")
    public native NSArray<CNLabeledValue<NSDateComponents>> getDates();
    @Property(selector = "setDates:")
    public native void setDates(NSArray<CNLabeledValue<NSDateComponents>> v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
