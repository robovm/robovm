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
import org.robovm.rt.annotation.*;
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
            CFArray o = (CFArray) CFType.Marshaler.toObject(CFArray.class, handle, flags);
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
        CFString val = getValue(ABPersonProperty.FirstName, CFString.class);
        if (val != null) return val.toString();
        return null;
    }
    public ABPerson setFirstName(String firstName) throws NSErrorException {
        if (firstName == null) {
            setValue(ABPersonProperty.FirstName, null);
        } else {
            setValue(ABPersonProperty.FirstName, new CFString(firstName));
        }
        return this;
    }
    public String getLastName() {
        CFString val = getValue(ABPersonProperty.LastName, CFString.class);
        if (val != null) return val.toString();
        return null;
    }
    public ABPerson setLastName(String lastName) throws NSErrorException {
        if (lastName == null) {
            setValue(ABPersonProperty.LastName, null);
        } else {
            setValue(ABPersonProperty.LastName, new CFString(lastName));
        }
        return this;
    }
    public String getMiddleName() {
        CFString val = getValue(ABPersonProperty.MiddleName, CFString.class);
        if (val != null) return val.toString();
        return null;
    }
    public ABPerson setMiddleName(String middleName) throws NSErrorException {
        if (middleName == null) {
            setValue(ABPersonProperty.MiddleName, null);
        } else {
            setValue(ABPersonProperty.MiddleName, new CFString(middleName));
        }
        return this;
    }
    public String getPrefix() {
        CFString val = getValue(ABPersonProperty.Prefix, CFString.class);
        if (val != null) return val.toString();
        return null;
    }
    public ABPerson setPrefix(String prefix) throws NSErrorException {
        if (prefix == null) {
            setValue(ABPersonProperty.Prefix, null);
        } else {
            setValue(ABPersonProperty.Prefix, new CFString(prefix));
        }
        return this;
    }
    public String getSuffix() {
        CFString val = getValue(ABPersonProperty.Suffix, CFString.class);
        if (val != null) return val.toString();
        return null;
    }
    public ABPerson setSuffix(String suffix) throws NSErrorException {
        if (suffix == null) {
            setValue(ABPersonProperty.Suffix, null);
        } else {
            setValue(ABPersonProperty.Suffix, new CFString(suffix));
        }
        return this;
    }
    public String getNickname() {
        CFString val = getValue(ABPersonProperty.Nickname, CFString.class);
        if (val != null) return val.toString();
        return null;
    }
    public ABPerson setNickname(String nickname) throws NSErrorException {
        if (nickname == null) {
            setValue(ABPersonProperty.Nickname, null);
        } else {
            setValue(ABPersonProperty.Nickname, new CFString(nickname));
        }
        return this;
    }
    public String getFirstNamePhonetic() {
        CFString val = getValue(ABPersonProperty.FirstNamePhonetic, CFString.class);
        if (val != null) return val.toString();
        return null;
    }
    public ABPerson setFirstNamePhonetic(String firstNamePhonetic) throws NSErrorException {
        if (firstNamePhonetic == null) {
            setValue(ABPersonProperty.FirstNamePhonetic, null);
        } else {
            setValue(ABPersonProperty.FirstNamePhonetic, new CFString(firstNamePhonetic));
        }
        return this;
    }
    public String getLastNamePhonetic() {
        CFString val = getValue(ABPersonProperty.LastNamePhonetic, CFString.class);
        if (val != null) return val.toString();
        return null;
    }
    public ABPerson setLastNamePhonetic(String lastNamePhonetic) throws NSErrorException {
        if (lastNamePhonetic == null) {
            setValue(ABPersonProperty.LastNamePhonetic, null);
        } else {
            setValue(ABPersonProperty.LastNamePhonetic, new CFString(lastNamePhonetic));
        }
        return this;
    }
    public String getMiddleNamePhonetic() {
        CFString val = getValue(ABPersonProperty.MiddleNamePhonetic, CFString.class);
        if (val != null) return val.toString();
        return null;
    }
    public ABPerson setMiddleNamePhonetic(String middleNamePhonetic) throws NSErrorException {
        if (middleNamePhonetic == null) {
            setValue(ABPersonProperty.MiddleNamePhonetic, null);
        } else {
            setValue(ABPersonProperty.MiddleNamePhonetic, new CFString(middleNamePhonetic));
        }
        return this;
    }
    public String getOrganization() {
        CFString val = getValue(ABPersonProperty.Organization, CFString.class);
        if (val != null) return val.toString();
        return null;
    }
    public ABPerson setOrganization(String organization) throws NSErrorException {
        if (organization == null) {
            setValue(ABPersonProperty.Organization, null);
        } else {
            setValue(ABPersonProperty.Organization, new CFString(organization));
        }
        return this;
    }
    public String getJobTitle() {
        CFString val = getValue(ABPersonProperty.JobTitle, CFString.class);
        if (val != null) return val.toString();
        return null;
    }
    public ABPerson setJobTitle(String jobTitle) throws NSErrorException {
        if (jobTitle == null) {
            setValue(ABPersonProperty.JobTitle, null);
        } else {
            setValue(ABPersonProperty.JobTitle, new CFString(jobTitle));
        }
        return this;
    }
    public String getDepartment() {
        CFString val = getValue(ABPersonProperty.Department, CFString.class);
        if (val != null) return val.toString();
        return null;
    }
    public ABPerson setDepartment(String department) throws NSErrorException {
        if (department == null) {
            setValue(ABPersonProperty.Department, null);
        } else {
            setValue(ABPersonProperty.Department, new CFString(department));
        }
        return this;
    }
    public List<ABPersonEmailAddress> getEmailAddresses() {
        ABMultiValue val = getValue(ABPersonProperty.Email, ABMultiValue.class);
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
        if (emailAddresses == null) {
            setValue(ABPersonProperty.Email, null);
        } else {
            ABMutableMultiValue val = ABMutableMultiValue.create(ABPropertyType.MultiString);
            for (ABPersonEmailAddress address : emailAddresses) {
                val.addValueAndLabel(address.getAddress0(), address.getLabel0(), null);
            }
            setValue(ABPersonProperty.Email, val);
        }
        return this;
    }
    public int addEmailAddress(ABPersonEmailAddress emailAddress) throws NSErrorException {
        if (emailAddress == null) throw new NullPointerException("emailAddress");
        ABMultiValue val = getValue(ABPersonProperty.Email, ABMultiValue.class);
        ABMutableMultiValue mutable;
        if (val != null) {
            mutable = ABMutableMultiValue.create(val);
        } else {
            mutable = ABMutableMultiValue.create(ABPropertyType.MultiString);
        }
        int result = mutable.addValueAndLabel(emailAddress.getAddress0(), emailAddress.getLabel0());
        setValue(ABPersonProperty.Email, mutable);
        return result;
    }
    public boolean removeEmailAddress(int id) throws NSErrorException {
        ABMultiValue val = getValue(ABPersonProperty.Email, ABMultiValue.class);
        if (val != null) {
            ABMutableMultiValue mutable = ABMutableMultiValue.create(val);
            boolean result = mutable.removeValueAndLabel(mutable.indexOf(id));
            setValue(ABPersonProperty.Email, mutable);
            return result;
        }
        return false;
    }
    public NSDate getBirthday() {
        NSDate val = getValue(ABPersonProperty.Birthday, NSDate.class);
        return val;
    }
    public ABPerson setBirthday(NSDate birthday) throws NSErrorException {
        if (birthday == null) {
            setValue(ABPersonProperty.Birthday, null);
        } else {
            setValue(ABPersonProperty.Birthday, birthday.as(CFDate.class));
        }
        return this;
    }
    public String getNote() {
        CFString val = getValue(ABPersonProperty.Note, CFString.class);
        if (val != null) return val.toString();
        return null;
    }
    public ABPerson setNote(String note) throws NSErrorException {
        if (note == null) {
            setValue(ABPersonProperty.Note, null);
        } else {
            setValue(ABPersonProperty.Note, new CFString(note));
        }
        return this;
    }
    public NSDate getCreationDate() {
        NSDate val = getValue(ABPersonProperty.CreationDate, NSDate.class);
        return val;
    }
    public ABPerson setCreationDate(NSDate creationDate) throws NSErrorException {
        if (creationDate == null) {
            setValue(ABPersonProperty.CreationDate, null);
        } else {
            setValue(ABPersonProperty.CreationDate, creationDate.as(CFDate.class));
        }
        return this;
    }
    public NSDate getModificationDate() {
        NSDate val = getValue(ABPersonProperty.ModificationDate, NSDate.class);
        return val;
    }
    public ABPerson setModificationDate(NSDate modificationDate) throws NSErrorException {
        if (modificationDate == null) {
            setValue(ABPersonProperty.ModificationDate, null);
        } else {
            setValue(ABPersonProperty.ModificationDate, modificationDate.as(CFDate.class));
        }
        return this;
    }
    public List<ABPersonAddress> getAddresses() {
        ABMultiValue val = getValue(ABPersonProperty.Address, ABMultiValue.class);
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
        if (addresses == null) {
            setValue(ABPersonProperty.Address, null);
        } else {
            ABMutableMultiValue val = ABMutableMultiValue.create(ABPropertyType.MultiDictionary);
            for (ABPersonAddress address : addresses) {
                val.addValueAndLabel(address.getDictionary(), address.getLabel0(), null);
            }
            setValue(ABPersonProperty.Address, val);
        }
        return this;
    }
    public int addAddress(ABPersonAddress address) throws NSErrorException {
        if (address == null) throw new NullPointerException("address");
        ABMultiValue val = getValue(ABPersonProperty.Address, ABMultiValue.class);
        ABMutableMultiValue mutable;
        if (val != null) {
            mutable = ABMutableMultiValue.create(val);
        } else {
            mutable = ABMutableMultiValue.create(ABPropertyType.MultiDictionary);
        }
        int result = mutable.addValueAndLabel(address.getDictionary(), address.getLabel0());
        setValue(ABPersonProperty.Address, mutable);
        return result;
    }
    public boolean removeAddress(int id) throws NSErrorException {
        ABMultiValue val = getValue(ABPersonProperty.Address, ABMultiValue.class);
        if (val != null) {
            ABMutableMultiValue mutable = ABMutableMultiValue.create(val);
            boolean result = mutable.removeValueAndLabel(mutable.indexOf(id));
            setValue(ABPersonProperty.Address, mutable);
            return result;
        }
        return false;
    }
    public List<ABPersonDate> getDates() {
        ABMultiValue val = getValue(ABPersonProperty.Date, ABMultiValue.class);
        List<ABPersonDate> list = new ArrayList<>();
        if (val == null) return list;
        long size = val.getCount();
        for (int i = 0; i < size; i++) {
            NSDate date = val.getValue(i).as(NSDate.class);
            CFString label = val.getLabel(i);
            list.add(new ABPersonDate(date, label));
        }
        return list;
    } 
    public ABPerson setDates(List<ABPersonDate> dates) throws NSErrorException {
        if (dates == null) {
            setValue(ABPersonProperty.Date, null);
        } else {
            ABMutableMultiValue val = ABMutableMultiValue.create(ABPropertyType.MultiDateTime);
            for (ABPersonDate date : dates) {
                val.addValueAndLabel(date.getDate().as(CFDate.class), date.getLabel0(), null);
            }
            setValue(ABPersonProperty.Date, val);
        }
        return this;
    }
    public ABPersonKind getKind() {
        CFNumber val = getValue(ABPersonProperty.Kind, CFNumber.class);
        return ABPersonKind.valueOf(val);
    }
    public ABPerson setKind(ABPersonKind kind) throws NSErrorException {
        if (kind == null) {
            setValue(ABPersonProperty.Kind, null);
        } else {
            setValue(ABPersonProperty.Kind, kind.value());
        }
        return this;
    }
    public List<ABPersonPhoneNumber> getPhoneNumbers() {
        ABMultiValue val = getValue(ABPersonProperty.Phone, ABMultiValue.class);
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
        if (phoneNumbers == null) {
            setValue(ABPersonProperty.Phone, null);
        } else {
            ABMutableMultiValue val = ABMutableMultiValue.create(ABPropertyType.MultiString);
            for (ABPersonPhoneNumber number : phoneNumbers) {
                val.addValueAndLabel(number.getNumber0(), number.getLabel0(), null);
            }
            setValue(ABPersonProperty.Phone, val);
        }
        return this;
    }
    public int addPhoneNumber(ABPersonPhoneNumber phoneNumber) throws NSErrorException {
        if (phoneNumber == null) throw new NullPointerException("phoneNumber");
        ABMultiValue val = getValue(ABPersonProperty.Phone, ABMultiValue.class);
        ABMutableMultiValue mutable;
        if (val != null) {
            mutable = ABMutableMultiValue.create(val);
        } else {
            mutable = ABMutableMultiValue.create(ABPropertyType.MultiString);
        }
        int result = mutable.addValueAndLabel(phoneNumber.getNumber0(), phoneNumber.getLabel0());
        setValue(ABPersonProperty.Phone, mutable);
        return result;
    }
    public boolean removePhoneNumber(int id) throws NSErrorException {
        ABMultiValue val = getValue(ABPersonProperty.Phone, ABMultiValue.class);
        if (val != null) {
            ABMutableMultiValue mutable = ABMutableMultiValue.create(val);
            boolean result = mutable.removeValueAndLabel(mutable.indexOf(id));
            setValue(ABPersonProperty.Phone, mutable);
            return result;
        }
        return false;
    }
    public List<ABPersonInstantMessageAccount> getInstantMessageAccounts() {
        ABMultiValue val = getValue(ABPersonProperty.InstantMessage, ABMultiValue.class);
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
        if (instantMessageAccounts == null) {
            setValue(ABPersonProperty.InstantMessage, null);
        } else {
            ABMutableMultiValue val = ABMutableMultiValue.create(ABPropertyType.MultiDictionary);
            for (ABPersonInstantMessageAccount account : instantMessageAccounts) {
                val.addValueAndLabel(account.getDictionary(), account.getLabel0(), null);
            }
            setValue(ABPersonProperty.InstantMessage, val);
        }
        return this;
    }
    public int addInstantMessageAccount(ABPersonInstantMessageAccount instantMessageAccount) throws NSErrorException {
        if (instantMessageAccount == null) throw new NullPointerException("instantMessageAccount");
        ABMultiValue val = getValue(ABPersonProperty.InstantMessage, ABMultiValue.class);
        ABMutableMultiValue mutable;
        if (val != null) {
            mutable = ABMutableMultiValue.create(val);
        } else {
            mutable = ABMutableMultiValue.create(ABPropertyType.MultiDictionary);
        }
        int result = mutable.addValueAndLabel(instantMessageAccount.getDictionary(), instantMessageAccount.getLabel0());
        setValue(ABPersonProperty.InstantMessage, mutable);
        return result;
    }
    public boolean removeInstantMessageAccount(int id) throws NSErrorException {
        ABMultiValue val = getValue(ABPersonProperty.InstantMessage, ABMultiValue.class);
        if (val != null) {
            ABMutableMultiValue mutable = ABMutableMultiValue.create(val);
            boolean result = mutable.removeValueAndLabel(mutable.indexOf(id));
            setValue(ABPersonProperty.InstantMessage, mutable);
            return result;
        }
        return false;
    }
    public List<ABPersonSocialProfile> getSocialProfiles() {
        ABMultiValue val = getValue(ABPersonProperty.SocialProfile, ABMultiValue.class);
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
        if (socialProfiles == null) {
            setValue(ABPersonProperty.SocialProfile, null);
        } else {
            ABMutableMultiValue val = ABMutableMultiValue.create(ABPropertyType.MultiDictionary);
            for (ABPersonSocialProfile account : socialProfiles) {
                val.addValueAndLabel(account.getDictionary(), account.getLabel0(), null);
            }
            setValue(ABPersonProperty.SocialProfile, val);
        }
        return this;
    }
    public int addSocialProfile(ABPersonSocialProfile socialProfile) throws NSErrorException {
        if (socialProfile == null) throw new NullPointerException("socialProfile");
        ABMultiValue val = getValue(ABPersonProperty.SocialProfile, ABMultiValue.class);
        ABMutableMultiValue mutable;
        if (val != null) {
            mutable = ABMutableMultiValue.create(val);
        } else {
            mutable = ABMutableMultiValue.create(ABPropertyType.MultiDictionary);
        }
        int result = mutable.addValueAndLabel(socialProfile.getDictionary(), socialProfile.getLabel0());
        setValue(ABPersonProperty.SocialProfile, mutable);
        return result;
    }
    public boolean removeSocialProfile(int id) throws NSErrorException {
        ABMultiValue val = getValue(ABPersonProperty.SocialProfile, ABMultiValue.class);
        if (val != null) {
            ABMutableMultiValue mutable = ABMutableMultiValue.create(val);
            boolean result = mutable.removeValueAndLabel(mutable.indexOf(id));
            setValue(ABPersonProperty.SocialProfile, mutable);
            return result;
        }
        return false;
    }
    public List<ABPersonURL> getURLs() {
        ABMultiValue val = getValue(ABPersonProperty.URL, ABMultiValue.class);
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
        if (urls == null) {
            setValue(ABPersonProperty.URL, null);
        } else {
            ABMutableMultiValue val = ABMutableMultiValue.create(ABPropertyType.MultiString);
            for (ABPersonURL url : urls) {
                val.addValueAndLabel(url.getURL0(), url.getLabel0(), null);
            }
            setValue(ABPersonProperty.URL, val);
        }
        return this;
    }
    public int addURL(ABPersonURL url) throws NSErrorException {
        if (url == null) throw new NullPointerException("url");
        ABMultiValue val = getValue(ABPersonProperty.URL, ABMultiValue.class);
        ABMutableMultiValue mutable;
        if (val != null) {
            mutable = ABMutableMultiValue.create(val);
        } else {
            mutable = ABMutableMultiValue.create(ABPropertyType.MultiString);
        }
        int result = mutable.addValueAndLabel(url.getURL0(), url.getLabel0());
        setValue(ABPersonProperty.URL, mutable);
        return result;
    }
    public boolean removeURL(int id) throws NSErrorException {
        ABMultiValue val = getValue(ABPersonProperty.URL, ABMultiValue.class);
        if (val != null) {
            ABMutableMultiValue mutable = ABMutableMultiValue.create(val);
            boolean result = mutable.removeValueAndLabel(mutable.indexOf(id));
            setValue(ABPersonProperty.URL, mutable);
            return result;
        }
        return false;
    }
    public List<ABPersonRelatedName> getRelatedNames() {
        ABMultiValue val = getValue(ABPersonProperty.RelatedNames, ABMultiValue.class);
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
        if (relatedNames == null) {
            setValue(ABPersonProperty.RelatedNames, null);
        } else {
            ABMutableMultiValue val = ABMutableMultiValue.create(ABPropertyType.MultiString);
            for (ABPersonRelatedName relatedName : relatedNames) {
                val.addValueAndLabel(relatedName.getName0(), relatedName.getLabel0(), null);
            }
            setValue(ABPersonProperty.RelatedNames, val);
        }
        return this;
    }
    public int addRelatedName(ABPersonRelatedName relatedName) throws NSErrorException {
        if (relatedName == null) throw new NullPointerException("relatedName");
        ABMultiValue val = getValue(ABPersonProperty.RelatedNames, ABMultiValue.class);
        ABMutableMultiValue mutable;
        if (val != null) {
            mutable = ABMutableMultiValue.create(val);
        } else {
            mutable = ABMutableMultiValue.create(ABPropertyType.MultiString);
        }
        int result = mutable.addValueAndLabel(relatedName.getName0(), relatedName.getLabel0());
        setValue(ABPersonProperty.RelatedNames, mutable);
        return result;
    }
    public boolean removeRelatedName(int id) throws NSErrorException {
        ABMultiValue val = getValue(ABPersonProperty.RelatedNames, ABMultiValue.class);
        if (val != null) {
            ABMutableMultiValue mutable = ABMutableMultiValue.create(val);
            boolean result = mutable.removeValueAndLabel(mutable.indexOf(id));
            setValue(ABPersonProperty.RelatedNames, mutable);
            return result;
        }
        return false;
    }
    public ABPersonAlternateBirthday getAlternateBirthday() {
        CFDictionary val = getValue(ABPersonProperty.AlternateBirthday, CFDictionary.class);
        if (val != null) return new ABPersonAlternateBirthday(val);
        return null;
    }
    public ABPerson setAlternateBirthday(ABPersonAlternateBirthday alternateBirthday) throws NSErrorException {
        if (alternateBirthday == null) {
            setValue(ABPersonProperty.AlternateBirthday, null);
        } else {
            setValue(ABPersonProperty.AlternateBirthday, alternateBirthday.getDictionary());
        }
        return this;
    }
    /*<methods>*/
    @Bridge(symbol="ABPersonCreate", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(ABRecord.NoRetainMarshaler.class) ABPerson create();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="ABPersonCreateInSource", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(ABRecord.NoRetainMarshaler.class) ABPerson create(ABSource source);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="ABPersonCopySource", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(ABRecord.NoRetainMarshaler.class) ABSource getSource();
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
    public native @org.robovm.rt.bro.annotation.Marshaler(CFString.AsStringNoRetainMarshaler.class) String getCompositeNameDelimiter();
    public boolean setImageData(NSData imageData) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = setImageData(imageData, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Bridge(symbol="ABPersonSetImageData", optional=true)
    private native boolean setImageData(NSData imageData, NSError.NSErrorPtr error);
    @Bridge(symbol="ABPersonCopyImageData", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(NSObject.NoRetainMarshaler.class) NSData getImageData();
    /**
     * @since Available in iOS 4.1 and later.
     */
    @Bridge(symbol="ABPersonCopyImageDataWithFormat", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(NSObject.NoRetainMarshaler.class) NSData getImageData(ABPersonImageFormat format);
    @Bridge(symbol="ABPersonHasImageData", optional=true)
    public native boolean hasImageData();
    public boolean removeImageData() throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = removeImageData(ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Bridge(symbol="ABPersonRemoveImageData", optional=true)
    private native boolean removeImageData(NSError.NSErrorPtr error);
    @Bridge(symbol="ABPersonComparePeopleByName", optional=true)
    public native CFComparisonResult compareTo(ABRecord person2, ABPersonSortOrdering ordering);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="ABPersonCreatePeopleInSourceWithVCardRepresentation", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(ABPerson.AsListMarshaler.class) List<ABPerson> createPeopleWithVCardRepresentation(ABSource source, NSData vCardData);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="ABPersonCreateVCardRepresentationWithPeople", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(NSObject.NoRetainMarshaler.class) NSData createVCardRepresentationWithPeople(@org.robovm.rt.bro.annotation.Marshaler(ABPerson.AsListMarshaler.class) List<ABPerson> people);
    /*</methods>*/
}
