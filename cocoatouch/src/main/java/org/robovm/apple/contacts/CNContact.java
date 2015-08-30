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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CNContact/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CNContactPtr extends Ptr<CNContact, CNContactPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CNContact.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CNContact() {}
    protected CNContact(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "identifier")
    public native String getIdentifier();
    @Property(selector = "contactType")
    public native CNContactType getContactType();
    @Property(selector = "namePrefix")
    public native String getNamePrefix();
    @Property(selector = "givenName")
    public native String getGivenName();
    @Property(selector = "middleName")
    public native String getMiddleName();
    @Property(selector = "familyName")
    public native String getFamilyName();
    @Property(selector = "previousFamilyName")
    public native String getPreviousFamilyName();
    @Property(selector = "nameSuffix")
    public native String getNameSuffix();
    @Property(selector = "nickname")
    public native String getNickname();
    @Property(selector = "phoneticGivenName")
    public native String getPhoneticGivenName();
    @Property(selector = "phoneticMiddleName")
    public native String getPhoneticMiddleName();
    @Property(selector = "phoneticFamilyName")
    public native String getPhoneticFamilyName();
    @Property(selector = "organizationName")
    public native String getOrganizationName();
    @Property(selector = "departmentName")
    public native String getDepartmentName();
    @Property(selector = "jobTitle")
    public native String getJobTitle();
    @Property(selector = "note")
    public native String getNote();
    @Property(selector = "imageData")
    public native NSData getImageData();
    @Property(selector = "thumbnailImageData")
    public native NSData getThumbnailImageData();
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "imageDataAvailable")
    public native boolean isImageDataAvailable();
    @Property(selector = "phoneNumbers")
    public native NSArray<CNLabeledValue<CNPhoneNumber>> getPhoneNumbers();
    @Property(selector = "emailAddresses")
    public native NSArray<CNLabeledValue<String>> getEmailAddresses();
    @Property(selector = "postalAddresses")
    public native NSArray<CNLabeledValue<CNPostalAddress>> getPostalAddresses();
    @Property(selector = "urlAddresses")
    public native NSArray<CNLabeledValue<String>> getUrlAddresses();
    @Property(selector = "contactRelations")
    public native NSArray<CNLabeledValue<CNContactRelation>> getContactRelations();
    @Property(selector = "socialProfiles")
    public native NSArray<CNLabeledValue<CNSocialProfile>> getSocialProfiles();
    @Property(selector = "instantMessageAddresses")
    public native NSArray<CNLabeledValue<CNInstantMessageAddress>> getInstantMessageAddresses();
    @Property(selector = "birthday")
    public native NSDateComponents getBirthday();
    @Property(selector = "nonGregorianBirthday")
    public native NSDateComponents getNonGregorianBirthday();
    @Property(selector = "dates")
    public native NSArray<CNLabeledValue<NSDateComponents>> getDates();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "isKeyAvailable:")
    public native boolean isKeyAvailable(CNContactPropertyKey key);
    @Method(selector = "areKeysAvailable:")
    public native boolean areKeysAvailable(@org.robovm.rt.bro.annotation.Marshaler(CNContactPropertyKey.AsListMarshaler.class) List<CNContactPropertyKey> keys);
    @Method(selector = "isUnifiedWithContactWithIdentifier:")
    public native boolean isUnifiedWithContact(String contactIdentifier);
    @Method(selector = "localizedStringForKey:")
    public static native String getLocalizedProperty(CNContactPropertyKey key);
    @Method(selector = "comparatorForNameSortOrder:")
    public static native @Block Block2<String, String, NSComparisonResult> getNameComparator(CNContactSortOrder sortOrder);
    @Method(selector = "descriptorForAllComparatorKeys")
    public static native String getDescriptorForAllComparatorKeys();
    @Method(selector = "predicateForContactsMatchingName:")
    public static native NSPredicate getPredicateForContacts(String name);
    @Method(selector = "predicateForContactsWithIdentifiers:")
    public static native NSPredicate getPredicateForContacts(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> identifiers);
    @Method(selector = "predicateForContactsInGroupWithIdentifier:")
    public static native NSPredicate getPredicateForContactsInGroup(String groupIdentifier);
    @Method(selector = "predicateForContactsInContainerWithIdentifier:")
    public static native NSPredicate getPredicateForContactsInContainer(String containerIdentifier);
    /*</methods>*/
}
