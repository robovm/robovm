/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
package org.robovm.apple.security;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Security")/*</annotations>*/
@Marshaler(/*<name>*/SecAttributes/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SecAttributes/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static SecAttributes toObject(Class<SecAttributes> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new SecAttributes(o);
        }
        @MarshalsPointer
        public static long toNative(SecAttributes o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<SecAttributes> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<SecAttributes> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new SecAttributes(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<SecAttributes> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (SecAttributes i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    SecAttributes(CFDictionary data) {
        super(data);
    }
    public SecAttributes() {}
    /*</constructors>*/

    /*<methods>*/
    public boolean has(CFType key) {
        return data.containsKey(key);
    }
    public <T extends NativeObject> T get(CFType key, Class<T> type) {
        if (has(key)) {
            return data.get(key, type);
        }
        return null;
    }
    public SecAttributes set(CFType key, NativeObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 4.0 and later.
     */
    public SecAttrAccessible getAccessible() {
        if (has(Keys.Accessible())) {
            CFType val = get(Keys.Accessible(), CFType.class);
            return SecAttrAccessible.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public SecAttributes setAccessible(SecAttrAccessible accessible) {
        set(Keys.Accessible(), accessible.value());
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public SecAccessControl getAccessControl() {
        if (has(Keys.AccessControl())) {
            SecAccessControl val = get(Keys.AccessControl(), SecAccessControl.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public SecAttributes setAccessControl(SecAccessControl accessControl) {
        set(Keys.AccessControl(), accessControl);
        return this;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public String getAccessGroup() {
        if (has(Keys.AccessGroup())) {
            CFString val = get(Keys.AccessGroup(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public SecAttributes setAccessGroup(String accessGroup) {
        set(Keys.AccessGroup(), new CFString(accessGroup));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean isSynchronizable() {
        if (has(Keys.Synchronizable())) {
            CFBoolean val = get(Keys.Synchronizable(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public SecAttributes setSynchronizable(boolean synchronizable) {
        set(Keys.Synchronizable(), CFBoolean.valueOf(synchronizable));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSDate getCreationDate() {
        if (has(Keys.CreationDate())) {
            NSDate val = get(Keys.CreationDate(), NSDate.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setCreationDate(NSDate creationDate) {
        set(Keys.CreationDate(), creationDate);
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSDate getModificationDate() {
        if (has(Keys.ModificationDate())) {
            NSDate val = get(Keys.ModificationDate(), NSDate.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setModificationDate(NSDate modificationDate) {
        set(Keys.ModificationDate(), modificationDate);
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getDescription() {
        if (has(Keys.Description())) {
            CFString val = get(Keys.Description(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setDescription(String description) {
        set(Keys.Description(), new CFString(description));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getComment() {
        if (has(Keys.Comment())) {
            CFString val = get(Keys.Comment(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setComment(String comment) {
        set(Keys.Comment(), new CFString(comment));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public long getCreator() {
        if (has(Keys.Creator())) {
            CFNumber val = get(Keys.Creator(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setCreator(long creator) {
        set(Keys.Creator(), CFNumber.valueOf(creator));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public long getType() {
        if (has(Keys.Type())) {
            CFNumber val = get(Keys.Type(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setType(long type) {
        set(Keys.Type(), CFNumber.valueOf(type));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getLabel() {
        if (has(Keys.Label())) {
            CFString val = get(Keys.Label(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setLabel(String label) {
        set(Keys.Label(), new CFString(label));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean isInvisible() {
        if (has(Keys.IsInvisible())) {
            CFBoolean val = get(Keys.IsInvisible(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setIsInvisible(boolean isInvisible) {
        set(Keys.IsInvisible(), CFBoolean.valueOf(isInvisible));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean isNegative() {
        if (has(Keys.IsNegative())) {
            CFBoolean val = get(Keys.IsNegative(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setIsNegative(boolean isNegative) {
        set(Keys.IsNegative(), CFBoolean.valueOf(isNegative));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getAccount() {
        if (has(Keys.Account())) {
            CFString val = get(Keys.Account(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setAccount(String account) {
        set(Keys.Account(), new CFString(account));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getService() {
        if (has(Keys.Service())) {
            CFString val = get(Keys.Service(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setService(String service) {
        set(Keys.Service(), new CFString(service));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSData getGeneric() {
        if (has(Keys.Generic())) {
            NSData val = get(Keys.Generic(), NSData.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setGeneric(NSData generic) {
        set(Keys.Generic(), generic);
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getSecurityDomain() {
        if (has(Keys.SecurityDomain())) {
            CFString val = get(Keys.SecurityDomain(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setSecurityDomain(String securityDomain) {
        set(Keys.SecurityDomain(), new CFString(securityDomain));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getServer() {
        if (has(Keys.Server())) {
            CFString val = get(Keys.Server(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setServer(String server) {
        set(Keys.Server(), new CFString(server));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttrProtocol getProtocol() {
        if (has(Keys.Protocol())) {
            CFType val = get(Keys.Protocol(), CFType.class);
            return SecAttrProtocol.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setProtocol(SecAttrProtocol protocol) {
        set(Keys.Protocol(), protocol.value());
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttrAuthenticationType getAuthenticationType() {
        if (has(Keys.AuthenticationType())) {
            CFType val = get(Keys.AuthenticationType(), CFType.class);
            return SecAttrAuthenticationType.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setAuthenticationType(SecAttrAuthenticationType authenticationType) {
        set(Keys.AuthenticationType(), authenticationType.value());
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public int getPort() {
        if (has(Keys.Port())) {
            CFNumber val = get(Keys.Port(), CFNumber.class);
            return val.intValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setPort(int port) {
        set(Keys.Port(), CFNumber.valueOf(port));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getPath() {
        if (has(Keys.Path())) {
            CFString val = get(Keys.Path(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setPath(String path) {
        set(Keys.Path(), new CFString(path));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSData getSubject() {
        if (has(Keys.Subject())) {
            NSData val = get(Keys.Subject(), NSData.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setSubject(NSData subject) {
        set(Keys.Subject(), subject);
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSData getIssuer() {
        if (has(Keys.Issuer())) {
            NSData val = get(Keys.Issuer(), NSData.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setIssuer(NSData issuer) {
        set(Keys.Issuer(), issuer);
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSData getSerialNumber() {
        if (has(Keys.SerialNumber())) {
            NSData val = get(Keys.SerialNumber(), NSData.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setSerialNumber(NSData serialNumber) {
        set(Keys.SerialNumber(), serialNumber);
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSData getSubjectKeyID() {
        if (has(Keys.SubjectKeyID())) {
            NSData val = get(Keys.SubjectKeyID(), NSData.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setSubjectKeyID(NSData subjectKeyID) {
        set(Keys.SubjectKeyID(), subjectKeyID);
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSData getPublicKeyHash() {
        if (has(Keys.PublicKeyHash())) {
            NSData val = get(Keys.PublicKeyHash(), NSData.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setPublicKeyHash(NSData publicKeyHash) {
        set(Keys.PublicKeyHash(), publicKeyHash);
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public long getCertificateType() {
        if (has(Keys.CertificateType())) {
            CFNumber val = get(Keys.CertificateType(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setCertificateType(long certificateType) {
        set(Keys.CertificateType(), CFNumber.valueOf(certificateType));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public long getCertificateEncoding() {
        if (has(Keys.CertificateEncoding())) {
            CFNumber val = get(Keys.CertificateEncoding(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setCertificateEncoding(long certificateEncoding) {
        set(Keys.CertificateEncoding(), CFNumber.valueOf(certificateEncoding));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttrKeyClass getKeyClass() {
        if (has(Keys.KeyClass())) {
            CFType val = get(Keys.KeyClass(), CFType.class);
            return SecAttrKeyClass.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setKeyClass(SecAttrKeyClass keyClass) {
        set(Keys.KeyClass(), keyClass.value());
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getApplicationLabel() {
        if (has(Keys.ApplicationLabel())) {
            CFString val = get(Keys.ApplicationLabel(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setApplicationLabel(String applicationLabel) {
        set(Keys.ApplicationLabel(), new CFString(applicationLabel));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean isPermanent() {
        if (has(Keys.IsPermanent())) {
            CFBoolean val = get(Keys.IsPermanent(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setIsPermanent(boolean isPermanent) {
        set(Keys.IsPermanent(), CFBoolean.valueOf(isPermanent));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSData getApplicationTag() {
        if (has(Keys.ApplicationTag())) {
            NSData val = get(Keys.ApplicationTag(), NSData.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setApplicationTag(NSData applicationTag) {
        set(Keys.ApplicationTag(), applicationTag);
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttrKeyType getKeyType() {
        if (has(Keys.KeyType())) {
            CFType val = get(Keys.KeyType(), CFType.class);
            return SecAttrKeyType.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setKeyType(SecAttrKeyType keyType) {
        set(Keys.KeyType(), keyType.value());
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public long getKeySizeInBits() {
        if (has(Keys.KeySizeInBits())) {
            CFNumber val = get(Keys.KeySizeInBits(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setKeySizeInBits(long keySizeInBits) {
        set(Keys.KeySizeInBits(), CFNumber.valueOf(keySizeInBits));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public int getEffectiveKeySize() {
        if (has(Keys.EffectiveKeySize())) {
            CFNumber val = get(Keys.EffectiveKeySize(), CFNumber.class);
            return val.intValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setEffectiveKeySize(int effectiveKeySize) {
        set(Keys.EffectiveKeySize(), CFNumber.valueOf(effectiveKeySize));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean canEncrypt() {
        if (has(Keys.CanEncrypt())) {
            CFBoolean val = get(Keys.CanEncrypt(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setCanEncrypt(boolean canEncrypt) {
        set(Keys.CanEncrypt(), CFBoolean.valueOf(canEncrypt));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean canDecrypt() {
        if (has(Keys.CanDecrypt())) {
            CFBoolean val = get(Keys.CanDecrypt(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setCanDecrypt(boolean canDecrypt) {
        set(Keys.CanDecrypt(), CFBoolean.valueOf(canDecrypt));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean canDerive() {
        if (has(Keys.CanDerive())) {
            CFBoolean val = get(Keys.CanDerive(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setCanDerive(boolean canDerive) {
        set(Keys.CanDerive(), CFBoolean.valueOf(canDerive));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean canSign() {
        if (has(Keys.CanSign())) {
            CFBoolean val = get(Keys.CanSign(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setCanSign(boolean canSign) {
        set(Keys.CanSign(), CFBoolean.valueOf(canSign));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean canVerify() {
        if (has(Keys.CanVerify())) {
            CFBoolean val = get(Keys.CanVerify(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setCanVerify(boolean canVerify) {
        set(Keys.CanVerify(), CFBoolean.valueOf(canVerify));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean canWrap() {
        if (has(Keys.CanWrap())) {
            CFBoolean val = get(Keys.CanWrap(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setCanWrap(boolean canWrap) {
        set(Keys.CanWrap(), CFBoolean.valueOf(canWrap));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean canUnwrap() {
        if (has(Keys.CanUnwrap())) {
            CFBoolean val = get(Keys.CanUnwrap(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setCanUnwrap(boolean canUnwrap) {
        set(Keys.CanUnwrap(), CFBoolean.valueOf(canUnwrap));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public List<CFType> getItemList() {
        if (has(Keys.UseItemList())) {
            CFArray val = get(Keys.UseItemList(), CFArray.class);
            return val.toList(CFType.class);
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecAttributes setItemList(List<CFType> itemList) {
        set(Keys.UseItemList(), CFArray.create(itemList));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public String getOperationPrompt() {
        if (has(Keys.UseOperationPrompt())) {
            CFString val = get(Keys.UseOperationPrompt(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public SecAttributes setOperationPrompt(String operationPrompt) {
        set(Keys.UseOperationPrompt(), new CFString(operationPrompt));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public boolean usesNoAuthenticationUI() {
        if (has(Keys.UseNoAuthenticationUI())) {
            CFBoolean val = get(Keys.UseNoAuthenticationUI(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public SecAttributes setUsesNoAuthenticationUI(boolean usesNoAuthenticationUI) {
        set(Keys.UseNoAuthenticationUI(), CFBoolean.valueOf(usesNoAuthenticationUI));
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("Security")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kSecAttrAccessible", optional=true)
        public static native CFType Accessible();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kSecAttrAccessControl", optional=true)
        public static native CFType AccessControl();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kSecAttrAccessGroup", optional=true)
        public static native CFType AccessGroup();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kSecAttrSynchronizable", optional=true)
        public static native CFType Synchronizable();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrCreationDate", optional=true)
        public static native CFType CreationDate();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrModificationDate", optional=true)
        public static native CFType ModificationDate();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrDescription", optional=true)
        public static native CFType Description();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrComment", optional=true)
        public static native CFType Comment();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrCreator", optional=true)
        public static native CFType Creator();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrType", optional=true)
        public static native CFType Type();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrLabel", optional=true)
        public static native CFType Label();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrIsInvisible", optional=true)
        public static native CFType IsInvisible();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrIsNegative", optional=true)
        public static native CFType IsNegative();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrAccount", optional=true)
        public static native CFType Account();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrService", optional=true)
        public static native CFType Service();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrGeneric", optional=true)
        public static native CFType Generic();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrSecurityDomain", optional=true)
        public static native CFType SecurityDomain();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrServer", optional=true)
        public static native CFType Server();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrProtocol", optional=true)
        public static native CFType Protocol();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrAuthenticationType", optional=true)
        public static native CFType AuthenticationType();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrPort", optional=true)
        public static native CFType Port();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrPath", optional=true)
        public static native CFType Path();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrSubject", optional=true)
        public static native CFType Subject();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrIssuer", optional=true)
        public static native CFType Issuer();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrSerialNumber", optional=true)
        public static native CFType SerialNumber();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrSubjectKeyID", optional=true)
        public static native CFType SubjectKeyID();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrPublicKeyHash", optional=true)
        public static native CFType PublicKeyHash();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrCertificateType", optional=true)
        public static native CFType CertificateType();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrCertificateEncoding", optional=true)
        public static native CFType CertificateEncoding();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrKeyClass", optional=true)
        public static native CFType KeyClass();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrApplicationLabel", optional=true)
        public static native CFType ApplicationLabel();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrIsPermanent", optional=true)
        public static native CFType IsPermanent();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrApplicationTag", optional=true)
        public static native CFType ApplicationTag();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrKeyType", optional=true)
        public static native CFType KeyType();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrKeySizeInBits", optional=true)
        public static native CFType KeySizeInBits();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrEffectiveKeySize", optional=true)
        public static native CFType EffectiveKeySize();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrCanEncrypt", optional=true)
        public static native CFType CanEncrypt();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrCanDecrypt", optional=true)
        public static native CFType CanDecrypt();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrCanDerive", optional=true)
        public static native CFType CanDerive();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrCanSign", optional=true)
        public static native CFType CanSign();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrCanVerify", optional=true)
        public static native CFType CanVerify();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrCanWrap", optional=true)
        public static native CFType CanWrap();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrCanUnwrap", optional=true)
        public static native CFType CanUnwrap();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecUseItemList", optional=true)
        public static native CFType UseItemList();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kSecUseOperationPrompt", optional=true)
        public static native CFType UseOperationPrompt();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kSecUseNoAuthenticationUI", optional=true)
        public static native CFType UseNoAuthenticationUI();
    }
    /*</keys>*/
}
