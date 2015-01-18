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
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Security")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SecItem/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(SecItem.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecClass", optional=true)
    public static native CFType Class();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecClassGenericPassword", optional=true)
    public static native CFType ClassGenericPassword();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecClassInternetPassword", optional=true)
    public static native CFType ClassInternetPassword();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecClassCertificate", optional=true)
    public static native CFType ClassCertificate();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecClassKey", optional=true)
    public static native CFType ClassKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecClassIdentity", optional=true)
    public static native CFType ClassIdentity();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kSecAttrAccessible", optional=true)
    public static native CFType AttrAccessible();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kSecAttrAccessControl", optional=true)
    public static native CFType AttrAccessControl();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kSecAttrAccessGroup", optional=true)
    public static native CFType AttrAccessGroup();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kSecAttrSynchronizable", optional=true)
    public static native CFType AttrSynchronizable();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kSecAttrSynchronizableAny", optional=true)
    public static native CFType AttrSynchronizableAny();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrCreationDate", optional=true)
    public static native CFType AttrCreationDate();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrModificationDate", optional=true)
    public static native CFType AttrModificationDate();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrDescription", optional=true)
    public static native CFType AttrDescription();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrComment", optional=true)
    public static native CFType AttrComment();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrCreator", optional=true)
    public static native CFType AttrCreator();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrType", optional=true)
    public static native CFType AttrType();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrLabel", optional=true)
    public static native CFType AttrLabel();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrIsInvisible", optional=true)
    public static native CFType AttrIsInvisible();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrIsNegative", optional=true)
    public static native CFType AttrIsNegative();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrAccount", optional=true)
    public static native CFType AttrAccount();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrService", optional=true)
    public static native CFType AttrService();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrGeneric", optional=true)
    public static native CFType AttrGeneric();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrSecurityDomain", optional=true)
    public static native CFType AttrSecurityDomain();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrServer", optional=true)
    public static native CFType AttrServer();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrProtocol", optional=true)
    public static native CFType AttrProtocol();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrAuthenticationType", optional=true)
    public static native CFType AttrAuthenticationType();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrPort", optional=true)
    public static native CFType AttrPort();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrPath", optional=true)
    public static native CFType AttrPath();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrSubject", optional=true)
    public static native CFType AttrSubject();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrIssuer", optional=true)
    public static native CFType AttrIssuer();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrSerialNumber", optional=true)
    public static native CFType AttrSerialNumber();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrSubjectKeyID", optional=true)
    public static native CFType AttrSubjectKeyID();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrPublicKeyHash", optional=true)
    public static native CFType AttrPublicKeyHash();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrCertificateType", optional=true)
    public static native CFType AttrCertificateType();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrCertificateEncoding", optional=true)
    public static native CFType AttrCertificateEncoding();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrKeyClass", optional=true)
    public static native CFType AttrKeyClass();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrApplicationLabel", optional=true)
    public static native CFType AttrApplicationLabel();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrIsPermanent", optional=true)
    public static native CFType AttrIsPermanent();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrApplicationTag", optional=true)
    public static native CFType AttrApplicationTag();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrKeyType", optional=true)
    public static native CFType AttrKeyType();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrKeySizeInBits", optional=true)
    public static native CFType AttrKeySizeInBits();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrEffectiveKeySize", optional=true)
    public static native CFType AttrEffectiveKeySize();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrCanEncrypt", optional=true)
    public static native CFType AttrCanEncrypt();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrCanDecrypt", optional=true)
    public static native CFType AttrCanDecrypt();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrCanDerive", optional=true)
    public static native CFType AttrCanDerive();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrCanSign", optional=true)
    public static native CFType AttrCanSign();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrCanVerify", optional=true)
    public static native CFType AttrCanVerify();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrCanWrap", optional=true)
    public static native CFType AttrCanWrap();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrCanUnwrap", optional=true)
    public static native CFType AttrCanUnwrap();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kSecAttrAccessibleWhenUnlocked", optional=true)
    public static native CFType AttrAccessibleWhenUnlocked();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kSecAttrAccessibleAfterFirstUnlock", optional=true)
    public static native CFType AttrAccessibleAfterFirstUnlock();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kSecAttrAccessibleAlways", optional=true)
    public static native CFType AttrAccessibleAlways();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kSecAttrAccessibleWhenPasscodeSetThisDeviceOnly", optional=true)
    public static native CFType AttrAccessibleWhenPasscodeSetThisDeviceOnly();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kSecAttrAccessibleWhenUnlockedThisDeviceOnly", optional=true)
    public static native CFType AttrAccessibleWhenUnlockedThisDeviceOnly();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kSecAttrAccessibleAfterFirstUnlockThisDeviceOnly", optional=true)
    public static native CFType AttrAccessibleAfterFirstUnlockThisDeviceOnly();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kSecAttrAccessibleAlwaysThisDeviceOnly", optional=true)
    public static native CFType AttrAccessibleAlwaysThisDeviceOnly();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrProtocolFTP", optional=true)
    public static native CFType AttrProtocolFTP();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrProtocolFTPAccount", optional=true)
    public static native CFType AttrProtocolFTPAccount();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrProtocolHTTP", optional=true)
    public static native CFType AttrProtocolHTTP();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrProtocolIRC", optional=true)
    public static native CFType AttrProtocolIRC();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrProtocolNNTP", optional=true)
    public static native CFType AttrProtocolNNTP();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrProtocolPOP3", optional=true)
    public static native CFType AttrProtocolPOP3();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrProtocolSMTP", optional=true)
    public static native CFType AttrProtocolSMTP();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrProtocolSOCKS", optional=true)
    public static native CFType AttrProtocolSOCKS();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrProtocolIMAP", optional=true)
    public static native CFType AttrProtocolIMAP();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrProtocolLDAP", optional=true)
    public static native CFType AttrProtocolLDAP();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrProtocolAppleTalk", optional=true)
    public static native CFType AttrProtocolAppleTalk();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrProtocolAFP", optional=true)
    public static native CFType AttrProtocolAFP();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrProtocolTelnet", optional=true)
    public static native CFType AttrProtocolTelnet();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrProtocolSSH", optional=true)
    public static native CFType AttrProtocolSSH();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrProtocolFTPS", optional=true)
    public static native CFType AttrProtocolFTPS();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrProtocolHTTPS", optional=true)
    public static native CFType AttrProtocolHTTPS();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrProtocolHTTPProxy", optional=true)
    public static native CFType AttrProtocolHTTPProxy();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrProtocolHTTPSProxy", optional=true)
    public static native CFType AttrProtocolHTTPSProxy();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrProtocolFTPProxy", optional=true)
    public static native CFType AttrProtocolFTPProxy();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrProtocolSMB", optional=true)
    public static native CFType AttrProtocolSMB();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrProtocolRTSP", optional=true)
    public static native CFType AttrProtocolRTSP();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrProtocolRTSPProxy", optional=true)
    public static native CFType AttrProtocolRTSPProxy();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrProtocolDAAP", optional=true)
    public static native CFType AttrProtocolDAAP();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrProtocolEPPC", optional=true)
    public static native CFType AttrProtocolEPPC();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrProtocolIPP", optional=true)
    public static native CFType AttrProtocolIPP();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrProtocolNNTPS", optional=true)
    public static native CFType AttrProtocolNNTPS();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrProtocolLDAPS", optional=true)
    public static native CFType AttrProtocolLDAPS();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrProtocolTelnetS", optional=true)
    public static native CFType AttrProtocolTelnetS();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrProtocolIMAPS", optional=true)
    public static native CFType AttrProtocolIMAPS();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrProtocolIRCS", optional=true)
    public static native CFType AttrProtocolIRCS();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrProtocolPOP3S", optional=true)
    public static native CFType AttrProtocolPOP3S();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrAuthenticationTypeNTLM", optional=true)
    public static native CFType AttrAuthenticationTypeNTLM();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrAuthenticationTypeMSN", optional=true)
    public static native CFType AttrAuthenticationTypeMSN();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrAuthenticationTypeDPA", optional=true)
    public static native CFType AttrAuthenticationTypeDPA();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrAuthenticationTypeRPA", optional=true)
    public static native CFType AttrAuthenticationTypeRPA();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrAuthenticationTypeHTTPBasic", optional=true)
    public static native CFType AttrAuthenticationTypeHTTPBasic();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrAuthenticationTypeHTTPDigest", optional=true)
    public static native CFType AttrAuthenticationTypeHTTPDigest();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrAuthenticationTypeHTMLForm", optional=true)
    public static native CFType AttrAuthenticationTypeHTMLForm();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrAuthenticationTypeDefault", optional=true)
    public static native CFType AttrAuthenticationTypeDefault();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrKeyClassPublic", optional=true)
    public static native CFType AttrKeyClassPublic();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrKeyClassPrivate", optional=true)
    public static native CFType AttrKeyClassPrivate();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrKeyClassSymmetric", optional=true)
    public static native CFType AttrKeyClassSymmetric();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecAttrKeyTypeRSA", optional=true)
    public static native CFType AttrKeyTypeRSA();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kSecAttrKeyTypeEC", optional=true)
    public static native CFType AttrKeyTypeEC();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecMatchPolicy", optional=true)
    public static native CFType MatchPolicy();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecMatchItemList", optional=true)
    public static native CFType MatchItemList();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecMatchSearchList", optional=true)
    public static native CFType MatchSearchList();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecMatchIssuers", optional=true)
    public static native CFType MatchIssuers();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecMatchEmailAddressIfPresent", optional=true)
    public static native CFType MatchEmailAddressIfPresent();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecMatchSubjectContains", optional=true)
    public static native CFType MatchSubjectContains();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecMatchCaseInsensitive", optional=true)
    public static native CFType MatchCaseInsensitive();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecMatchTrustedOnly", optional=true)
    public static native CFType MatchTrustedOnly();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecMatchValidOnDate", optional=true)
    public static native CFType MatchValidOnDate();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecMatchLimit", optional=true)
    public static native CFType MatchLimit();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecMatchLimitOne", optional=true)
    public static native CFType MatchLimitOne();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecMatchLimitAll", optional=true)
    public static native CFType MatchLimitAll();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecReturnData", optional=true)
    public static native CFType ReturnData();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecReturnAttributes", optional=true)
    public static native CFType ReturnAttributes();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecReturnRef", optional=true)
    public static native CFType ReturnRef();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecReturnPersistentRef", optional=true)
    public static native CFType ReturnPersistentRef();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecValueData", optional=true)
    public static native CFType ValueData();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecValueRef", optional=true)
    public static native CFType ValueRef();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecValuePersistentRef", optional=true)
    public static native CFType ValuePersistentRef();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecUseItemList", optional=true)
    public static native CFType UseItemList();
    
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecItemCopyMatching", optional=true)
    public static native int copyMatching(CFDictionary query, CFType.CFTypePtr result);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecItemAdd", optional=true)
    public static native int add(CFDictionary attributes, CFType.CFTypePtr result);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecItemUpdate", optional=true)
    public static native int update(CFDictionary query, CFDictionary attributesToUpdate);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecItemDelete", optional=true)
    public static native int delete(CFDictionary query);
    /*</methods>*/
}
