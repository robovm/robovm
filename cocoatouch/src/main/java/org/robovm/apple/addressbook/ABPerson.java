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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ABPerson/*</name>*/ 
    extends /*<extends>*/ABRecord/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<ABPerson> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<ABPerson> list = new ArrayList<>();
            for (long i = 0, n = o.size(); i < n; i++) {
                ABRecord record = o.get(i, ABRecord.class);
                list.add((ABPerson)NativeObject.Marshaler.toObject(ABPerson.class, record.getHandle(), flags));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<ABPerson> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (ABPerson i : l) {
                array.add(i);
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(ABPerson.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public String getFirstName() {
        CFString val = (CFString)getValue(ABPersonProperty.FirstName);
        if (val != null) return val.toString();
        return null;
    }
    public ABPerson setFirstName(String firstName) throws NSErrorException {
        setValue(ABPersonProperty.FirstName, new CFString(firstName));
        return this;
    }
    public String getLastName() {
        CFString val = (CFString)getValue(ABPersonProperty.LastName);
        if (val != null) return val.toString();
        return null;
    }
    public ABPerson setLastName(String lastName) throws NSErrorException {
        setValue(ABPersonProperty.LastName, new CFString(lastName));
        return this;
    }
    public String getMiddleName() {
        CFString val = (CFString)getValue(ABPersonProperty.MiddleName);
        if (val != null) return val.toString();
        return null;
    }
    public ABPerson setMiddleName(String middleName) throws NSErrorException {
        setValue(ABPersonProperty.MiddleName, new CFString(middleName));
        return this;
    }
    public String getPrefix() {
        CFString val = (CFString)getValue(ABPersonProperty.Prefix);
        if (val != null) return val.toString();
        return null;
    }
    public ABPerson setPrefix(String prefix) throws NSErrorException {
        setValue(ABPersonProperty.Prefix, new CFString(prefix));
        return this;
    }
    public String getSuffix() {
        CFString val = (CFString)getValue(ABPersonProperty.Suffix);
        if (val != null) return val.toString();
        return null;
    }
    public ABPerson setSuffix(String suffix) throws NSErrorException {
        setValue(ABPersonProperty.Suffix, new CFString(suffix));
        return this;
    }
    public String getNickname() {
        CFString val = (CFString)getValue(ABPersonProperty.Nickname);
        if (val != null) return val.toString();
        return null;
    }
    public ABPerson setNickname(String nickname) throws NSErrorException {
        setValue(ABPersonProperty.Nickname, new CFString(nickname));
        return this;
    }
    public String getFirstNamePhonetic() {
        CFString val = (CFString)getValue(ABPersonProperty.FirstNamePhonetic);
        if (val != null) return val.toString();
        return null;
    }
    public ABPerson setFirstNamePhonetic(String firstNamePhonetic) throws NSErrorException {
        setValue(ABPersonProperty.FirstNamePhonetic, new CFString(firstNamePhonetic));
        return this;
    }
    public String getLastNamePhonetic() {
        CFString val = (CFString)getValue(ABPersonProperty.LastNamePhonetic);
        if (val != null) return val.toString();
        return null;
    }
    public ABPerson setLastNamePhonetic(String lastNamePhonetic) throws NSErrorException {
        setValue(ABPersonProperty.LastNamePhonetic, new CFString(lastNamePhonetic));
        return this;
    }
    public String getMiddleNamePhonetic() {
        CFString val = (CFString)getValue(ABPersonProperty.MiddleNamePhonetic);
        if (val != null) return val.toString();
        return null;
    }
    public ABPerson setMiddleNamePhonetic(String middleNamePhonetic) throws NSErrorException {
        setValue(ABPersonProperty.MiddleNamePhonetic, new CFString(middleNamePhonetic));
        return this;
    }
    public String getOrganization() {
        CFString val = (CFString)getValue(ABPersonProperty.Organization);
        if (val != null) return val.toString();
        return null;
    }
    public ABPerson setOrganization(String organization) throws NSErrorException {
        setValue(ABPersonProperty.Organization, new CFString(organization));
        return this;
    }
    public String getJobTitle() {
        CFString val = (CFString)getValue(ABPersonProperty.JobTitle);
        if (val != null) return val.toString();
        return null;
    }
    public ABPerson setJobTitle(String jobTitle) throws NSErrorException {
        setValue(ABPersonProperty.JobTitle, new CFString(jobTitle));
        return this;
    }
    public String getDepartment() {
        CFString val = (CFString)getValue(ABPersonProperty.Department);
        if (val != null) return val.toString();
        return null;
    }
    public ABPerson setDepartment(String department) throws NSErrorException {
        setValue(ABPersonProperty.Department, new CFString(department));
        return this;
    }
    public List<ABPersonEmailAddress> getEmailAddresses() {
        ABMultiValue val = (ABMultiValue)getValue(ABPersonProperty.Email);
        List<ABPersonEmailAddress> list = new ArrayList<>();
        if (val == null) return list;
        long size = val.getCount();
        for (int i = 0; i < size; i++) {
            CFString address = (CFString)val.getValue(i);
            CFString label = (CFString)val.getLabel(i);
            list.add(new ABPersonEmailAddress(address, label));
        }
        return list;
    }
    public ABPerson setEmailAddresses(List<ABPersonEmailAddress> emailAddresses) throws NSErrorException {
        ABMutableMultiValue val = ABMutableMultiValue.create(ABPropertyType.MultiString);
        for (ABPersonEmailAddress address : emailAddresses) {
            val.addValueAndLabel(address.getAddress0(), address.getLabel0(), null);
        }
        setValue(ABPersonProperty.Email, val);
        return this;
    }
    public int addEmailAddress(ABPersonEmailAddress emailAddress) {
        ABMutableMultiValue val = (ABMutableMultiValue)getValue(ABPersonProperty.Email);
        if (val != null) return val.addValueAndLabel(emailAddress.getAddress0(), emailAddress.getLabel());
        return -1;
    }
    public void removeEmailAddress(int id) {
        ABMutableMultiValue val = (ABMutableMultiValue)getValue(ABPersonProperty.Email);
        if (val != null) val.removeValueAndLabel(val.indexOf(id));
    }
    public NSDate getBirthday() {
        NSDate val = (NSDate)getNSValue(ABPersonProperty.Birthday);
        return val;
    }
    public ABPerson setBirthday(NSDate birthday) throws NSErrorException {
        setNSValue(ABPersonProperty.Birthday, birthday);
        return this;
    }
    public String getNote() {
        CFString val = (CFString)getValue(ABPersonProperty.Note);
        if (val != null) return val.toString();
        return null;
    }
    public ABPerson setNote(String note) throws NSErrorException {
        setValue(ABPersonProperty.Note, new CFString(note));
        return this;
    }
    public NSDate getCreationDate() {
        NSDate val = (NSDate)getNSValue(ABPersonProperty.CreationDate);
        return val;
    }
    public ABPerson setCreationDate(NSDate creationDate) throws NSErrorException {
        setNSValue(ABPersonProperty.CreationDate, creationDate);
        return this;
    }
    public NSDate getModificationDate() {
        NSDate val = (NSDate)getNSValue(ABPersonProperty.ModificationDate);
        return val;
    }
    public ABPerson setModificationDate(NSDate modificationDate) throws NSErrorException {
        setNSValue(ABPersonProperty.ModificationDate, modificationDate);
        return this;
    }
    public List<ABPersonAddress> getAddresses() {
        ABMultiValue val = (ABMultiValue)getValue(ABPersonProperty.Address);
        List<ABPersonAddress> list = new ArrayList<>();
        if (val == null) return list;
        long size = val.getCount();
        for (int i = 0; i < size; i++) {
            CFDictionary address = (CFDictionary)val.getValue(i);
            CFString label = val.getLabel(i);
            list.add(new ABPersonAddress(address, label));
        }
        return list;
    }
    public ABPerson setAddresses(List<ABPersonAddress> addresses) throws NSErrorException {
        ABMutableMultiValue val = ABMutableMultiValue.create(ABPropertyType.MultiDictionary);
        for (ABPersonAddress address : addresses) {
            val.addValueAndLabel(address.getDictionary(), address.getLabel0(), null);
        }
        setValue(ABPersonProperty.Address, val);
        return this;
    }
    public int addAddress(ABPersonAddress address) {
        ABMutableMultiValue val = (ABMutableMultiValue)getValue(ABPersonProperty.Address);
        if (val != null) return val.addValueAndLabel(address.getDictionary(), address.getLabel0());
        return -1;
    }
    public void removeAddress(int id) {
        ABMutableMultiValue val = (ABMutableMultiValue)getValue(ABPersonProperty.Address);
        if (val != null) val.removeValueAndLabel(val.indexOf(id));
    }
    public List<ABPersonDate> getDates() {
        ABMultiValue val = (ABMultiValue)getValue(ABPersonProperty.Date);
        List<ABPersonDate> list = new ArrayList<>();
        if (val == null) return list;
        long size = val.getCount();
        for (int i = 0; i < size; i++) {
            CFDate date = (CFDate)val.getValue(i);
            CFString label = val.getLabel(i);
            list.add(new ABPersonDate(date, label));
        }
        return list;
    } 
    public ABPerson setDates(List<ABPersonDate> dates) throws NSErrorException {
        ABMutableMultiValue val = ABMutableMultiValue.create(ABPropertyType.MultiDateTime);
        for (ABPersonDate date : dates) {
            val.addValueAndLabel(date.getDate0(), date.getLabel0(), null);
        }
        setValue(ABPersonProperty.Date, val);
        return this;
    }
    public ABPersonKind getKind() {
        CFNumber val = (CFNumber)getValue(ABPersonProperty.Kind);
        return ABPersonKind.valueOf(val);
    }
    public ABPerson setKind(ABPersonKind kind) throws NSErrorException {
        setValue(ABPersonProperty.Kind, kind.value());
        return this;
    }
    public List<ABPersonPhoneNumber> getPhoneNumbers() {
        ABMultiValue val = (ABMultiValue)getValue(ABPersonProperty.Phone);
        List<ABPersonPhoneNumber> list = new ArrayList<>();
        if (val == null) return list;
        long size = val.getCount();
        for (int i = 0; i < size; i++) {
            CFString number = (CFString)val.getValue(i);
            CFString label = (CFString)val.getLabel(i);
            list.add(new ABPersonPhoneNumber(number, label));
        }
        return list;
    }
    public ABPerson setPhoneNumbers(List<ABPersonPhoneNumber> phoneNumbers) throws NSErrorException {
        ABMutableMultiValue val = ABMutableMultiValue.create(ABPropertyType.MultiString);
        for (ABPersonPhoneNumber number : phoneNumbers) {
            val.addValueAndLabel(number.getNumber0(), number.getLabel0(), null);
        }
        setValue(ABPersonProperty.Phone, val);
        return this;
    }
    public int addPhoneNumber(ABPersonPhoneNumber phoneNumber) {
        ABMutableMultiValue val = (ABMutableMultiValue)getValue(ABPersonProperty.Phone);
        if (val != null) return val.addValueAndLabel(phoneNumber.getNumber0(), phoneNumber.getLabel());
        return -1;
    }
    public void removePhoneNumber(int id) {
        ABMutableMultiValue val = (ABMutableMultiValue)getValue(ABPersonProperty.Phone);
        if (val != null) val.removeValueAndLabel(val.indexOf(id));
    }
    public List<ABPersonInstantMessageAccount> getInstantMessageAccounts() {
        ABMultiValue val = (ABMultiValue)getValue(ABPersonProperty.InstantMessage);
        List<ABPersonInstantMessageAccount> list = new ArrayList<>();
        if (val == null) return list;
        long size = val.getCount();
        for (int i = 0; i < size; i++) {
            CFDictionary dict = (CFDictionary)val.getValue(i);
            CFString label = (CFString)val.getLabel(i);
            list.add(new ABPersonInstantMessageAccount(dict, label));
        }
        return list;
    }
    public ABPerson setInstantMessageAccounts(List<ABPersonInstantMessageAccount> instantMessageAccounts) throws NSErrorException {
        ABMutableMultiValue val = ABMutableMultiValue.create(ABPropertyType.MultiDictionary);
        for (ABPersonInstantMessageAccount account : instantMessageAccounts) {
            val.addValueAndLabel(account.getDictionary(), account.getLabel0(), null);
        }
        setValue(ABPersonProperty.InstantMessage, val);
        return this;
    }
    public int addInstantMessageAccount(ABPersonInstantMessageAccount instantMessageAccount) {
        ABMutableMultiValue val = (ABMutableMultiValue)getValue(ABPersonProperty.InstantMessage);
        if (val != null) return val.addValueAndLabel(instantMessageAccount.getDictionary(), instantMessageAccount.getLabel0());
        return -1;
    }
    public void removeInstantMessageAccount(int id) {
        ABMutableMultiValue val = (ABMutableMultiValue)getValue(ABPersonProperty.InstantMessage);
        if (val != null) val.removeValueAndLabel(val.indexOf(id));
    }
    public List<ABPersonSocialProfile> getSocialProfiles() {
        ABMultiValue val = (ABMultiValue)getValue(ABPersonProperty.SocialProfile);
        List<ABPersonSocialProfile> list = new ArrayList<>();
        if (val == null) return list;
        long size = val.getCount();
        for (int i = 0; i < size; i++) {
            CFDictionary dict = (CFDictionary)val.getValue(i);
            CFString label = (CFString)val.getLabel(i);
            list.add(new ABPersonSocialProfile(dict, label));
        }
        return list;
    }
    public ABPerson setSocialProfiles(List<ABPersonSocialProfile> socialProfiles) throws NSErrorException {
        ABMutableMultiValue val = ABMutableMultiValue.create(ABPropertyType.MultiDictionary);
        for (ABPersonSocialProfile account : socialProfiles) {
            val.addValueAndLabel(account.getDictionary(), account.getLabel0(), null);
        }
        setValue(ABPersonProperty.SocialProfile, val);
        return this;
    }
    public int addSocialProfile(ABPersonSocialProfile socialProfile) {
        ABMutableMultiValue val = (ABMutableMultiValue)getValue(ABPersonProperty.SocialProfile);
        if (val != null) return val.addValueAndLabel(socialProfile.getDictionary(), socialProfile.getLabel0());
        return -1;
    }
    public void removeSocialProfile(int id) {
        ABMutableMultiValue val = (ABMutableMultiValue)getValue(ABPersonProperty.SocialProfile);
        if (val != null) val.removeValueAndLabel(val.indexOf(id));
    }
    public List<ABPersonURL> getURLs() {
        ABMultiValue val = (ABMultiValue)getValue(ABPersonProperty.URL);
        List<ABPersonURL> list = new ArrayList<>();
        if (val == null) return list;
        long size = val.getCount();
        for (int i = 0; i < size; i++) {
            CFString url = (CFString)val.getValue(i);
            CFString label = (CFString)val.getLabel(i);
            list.add(new ABPersonURL(url, label));
        }
        return list;
    }
    public ABPerson setURLs(List<ABPersonURL> urls) throws NSErrorException {
        ABMutableMultiValue val = ABMutableMultiValue.create(ABPropertyType.MultiString);
        for (ABPersonURL url : urls) {
            val.addValueAndLabel(url.getURL0(), url.getLabel0(), null);
        }
        setValue(ABPersonProperty.URL, val);
        return this;
    }
    public int addURL(ABPersonURL url) {
        ABMutableMultiValue val = (ABMutableMultiValue)getValue(ABPersonProperty.URL);
        if (val != null) return val.addValueAndLabel(url.getURL0(), url.getLabel());
        return -1;
    }
    public void removeURL(int id) {
        ABMutableMultiValue val = (ABMutableMultiValue)getValue(ABPersonProperty.URL);
        if (val != null) val.removeValueAndLabel(val.indexOf(id));
    }
    public List<ABPersonRelatedName> getRelatedNames() {
        ABMultiValue val = (ABMultiValue)getValue(ABPersonProperty.RelatedNames);
        List<ABPersonRelatedName> list = new ArrayList<>();
        if (val == null) return list;
        long size = val.getCount();
        for (int i = 0; i < size; i++) {
            CFString name = (CFString)val.getValue(i);
            CFString label = (CFString)val.getLabel(i);
            list.add(new ABPersonRelatedName(name, label));
        }
        return list;
    }
    public ABPerson setRelatedNames(List<ABPersonRelatedName> relatedNames) throws NSErrorException {
        ABMutableMultiValue val = ABMutableMultiValue.create(ABPropertyType.MultiString);
        for (ABPersonRelatedName relatedName : relatedNames) {
            val.addValueAndLabel(relatedName.getName0(), relatedName.getLabel0(), null);
        }
        setValue(ABPersonProperty.RelatedNames, val);
        return this;
    }
    public int addRelatedName(ABPersonRelatedName relatedName) {
        ABMutableMultiValue val = (ABMutableMultiValue)getValue(ABPersonProperty.RelatedNames);
        if (val != null) return val.addValueAndLabel(relatedName.getName0(), relatedName.getLabel());
        return -1;
    }
    public void removeRelatedName(int id) {
        ABMutableMultiValue val = (ABMutableMultiValue)getValue(ABPersonProperty.RelatedNames);
        if (val != null) val.removeValueAndLabel(val.indexOf(id));
    }
    public ABPersonAlternateBirthday getAlternateBirthday() {
        CFDictionary val = (CFDictionary) getValue(ABPersonProperty.AlternateBirthday);
        if (val != null) return new ABPersonAlternateBirthday(val);
        return null;
    }
    public ABPerson setAlternateBirthday(ABPersonAlternateBirthday alternateBirthday) throws NSErrorException {
        setValue(ABPersonProperty.AlternateBirthday, alternateBirthday.getDictionary());
        return this;
    }
    
    /**
     * 
     * @param imageData
     * @return
     * @throws NSErrorException
     */
    public boolean setImageData(NSData imageData) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = setImageData(imageData, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @return
     * @throws NSErrorException
     */
    public boolean removeImageData() throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = removeImageData(err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /*<methods>*/
    @Bridge(symbol="ABPersonCreate", optional=true)
    public static native ABPerson create();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="ABPersonCreateInSource", optional=true)
    public static native ABPerson create(ABSource source);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="ABPersonCopySource", optional=true)
    public native ABSource getSource();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="ABPersonCopyArrayOfAllLinkedPeople", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(ABPerson.AsListMarshaler.class) List<ABPerson> getAllLinkedPeople();
    @Bridge(symbol="ABPersonGetSortOrdering", optional=true)
    public static native ABPersonSortOrdering getSortOrdering();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Bridge(symbol="ABPersonGetCompositeNameFormat", optional=true)
    public static native ABPersonCompositeNameFormat getConstantCompositeNameFormat();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="ABPersonGetCompositeNameFormatForRecord", optional=true)
    public native ABPersonCompositeNameFormat getCompositeNameFormat();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="ABPersonCopyCompositeNameDelimiterForRecord", optional=true)
    public native String getCompositeNameDelimiter();
    @Bridge(symbol="ABPersonSetImageData", optional=true)
    protected native boolean setImageData(NSData imageData, NSError.NSErrorPtr error);
    @Bridge(symbol="ABPersonCopyImageData", optional=true)
    public native NSData getImageData();
    /**
     * @since Available in iOS 4.1 and later.
     */
    @Bridge(symbol="ABPersonCopyImageDataWithFormat", optional=true)
    public native NSData getImageData(ABPersonImageFormat format);
    @Bridge(symbol="ABPersonHasImageData", optional=true)
    public native boolean hasImageData();
    @Bridge(symbol="ABPersonRemoveImageData", optional=true)
    protected native boolean removeImageData(NSError.NSErrorPtr error);
    @Bridge(symbol="ABPersonComparePeopleByName", optional=true)
    public native CFComparisonResult compareTo(ABRecord person2, ABPersonSortOrdering ordering);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="ABPersonCreatePeopleInSourceWithVCardRepresentation", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(ABPerson.AsListMarshaler.class) List<ABPerson> createPeopleInSource(ABSource source, NSData vCardData);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="ABPersonCreateVCardRepresentationWithPeople", optional=true)
    public static native NSData createVCardRepresentation(@org.robovm.rt.bro.annotation.Marshaler(ABPerson.AsListMarshaler.class) List<ABPerson> people);
    /*</methods>*/
}
