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

/**
 *
 * <div class="javadoc"></div>
 */
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
    @GlobalValue(symbol="kSecClass", optional=true)
    public static native CFType Class();
    @GlobalValue(symbol="kSecClassGenericPassword", optional=true)
    public static native CFType ClassGenericPassword();
    @GlobalValue(symbol="kSecClassInternetPassword", optional=true)
    public static native CFType ClassInternetPassword();
    @GlobalValue(symbol="kSecClassCertificate", optional=true)
    public static native CFType ClassCertificate();
    @GlobalValue(symbol="kSecClassKey", optional=true)
    public static native CFType ClassKey();
    @GlobalValue(symbol="kSecClassIdentity", optional=true)
    public static native CFType ClassIdentity();
    @GlobalValue(symbol="kSecAttrAccessible", optional=true)
    public static native CFType AttrAccessible();
    @GlobalValue(symbol="kSecAttrAccessGroup", optional=true)
    public static native CFType AttrAccessGroup();
    @GlobalValue(symbol="kSecAttrSynchronizable", optional=true)
    public static native CFType AttrSynchronizable();
    @GlobalValue(symbol="kSecAttrSynchronizableAny", optional=true)
    public static native CFType AttrSynchronizableAny();
    @GlobalValue(symbol="kSecAttrCreationDate", optional=true)
    public static native CFType AttrCreationDate();
    @GlobalValue(symbol="kSecAttrModificationDate", optional=true)
    public static native CFType AttrModificationDate();
    @GlobalValue(symbol="kSecAttrDescription", optional=true)
    public static native CFType AttrDescription();
    @GlobalValue(symbol="kSecAttrComment", optional=true)
    public static native CFType AttrComment();
    @GlobalValue(symbol="kSecAttrCreator", optional=true)
    public static native CFType AttrCreator();
    @GlobalValue(symbol="kSecAttrType", optional=true)
    public static native CFType AttrType();
    @GlobalValue(symbol="kSecAttrLabel", optional=true)
    public static native CFType AttrLabel();
    @GlobalValue(symbol="kSecAttrIsInvisible", optional=true)
    public static native CFType AttrIsInvisible();
    @GlobalValue(symbol="kSecAttrIsNegative", optional=true)
    public static native CFType AttrIsNegative();
    @GlobalValue(symbol="kSecAttrAccount", optional=true)
    public static native CFType AttrAccount();
    @GlobalValue(symbol="kSecAttrService", optional=true)
    public static native CFType AttrService();
    @GlobalValue(symbol="kSecAttrGeneric", optional=true)
    public static native CFType AttrGeneric();
    @GlobalValue(symbol="kSecAttrSecurityDomain", optional=true)
    public static native CFType AttrSecurityDomain();
    @GlobalValue(symbol="kSecAttrServer", optional=true)
    public static native CFType AttrServer();
    @GlobalValue(symbol="kSecAttrProtocol", optional=true)
    public static native CFType AttrProtocol();
    @GlobalValue(symbol="kSecAttrAuthenticationType", optional=true)
    public static native CFType AttrAuthenticationType();
    @GlobalValue(symbol="kSecAttrPort", optional=true)
    public static native CFType AttrPort();
    @GlobalValue(symbol="kSecAttrPath", optional=true)
    public static native CFType AttrPath();
    @GlobalValue(symbol="kSecAttrSubject", optional=true)
    public static native CFType AttrSubject();
    @GlobalValue(symbol="kSecAttrIssuer", optional=true)
    public static native CFType AttrIssuer();
    @GlobalValue(symbol="kSecAttrSerialNumber", optional=true)
    public static native CFType AttrSerialNumber();
    @GlobalValue(symbol="kSecAttrSubjectKeyID", optional=true)
    public static native CFType AttrSubjectKeyID();
    @GlobalValue(symbol="kSecAttrPublicKeyHash", optional=true)
    public static native CFType AttrPublicKeyHash();
    @GlobalValue(symbol="kSecAttrCertificateType", optional=true)
    public static native CFType AttrCertificateType();
    @GlobalValue(symbol="kSecAttrCertificateEncoding", optional=true)
    public static native CFType AttrCertificateEncoding();
    @GlobalValue(symbol="kSecAttrKeyClass", optional=true)
    public static native CFType AttrKeyClass();
    @GlobalValue(symbol="kSecAttrApplicationLabel", optional=true)
    public static native CFType AttrApplicationLabel();
    @GlobalValue(symbol="kSecAttrIsPermanent", optional=true)
    public static native CFType AttrIsPermanent();
    @GlobalValue(symbol="kSecAttrApplicationTag", optional=true)
    public static native CFType AttrApplicationTag();
    @GlobalValue(symbol="kSecAttrKeyType", optional=true)
    public static native CFType AttrKeyType();
    @GlobalValue(symbol="kSecAttrKeySizeInBits", optional=true)
    public static native CFType AttrKeySizeInBits();
    @GlobalValue(symbol="kSecAttrEffectiveKeySize", optional=true)
    public static native CFType AttrEffectiveKeySize();
    @GlobalValue(symbol="kSecAttrCanEncrypt", optional=true)
    public static native CFType AttrCanEncrypt();
    @GlobalValue(symbol="kSecAttrCanDecrypt", optional=true)
    public static native CFType AttrCanDecrypt();
    @GlobalValue(symbol="kSecAttrCanDerive", optional=true)
    public static native CFType AttrCanDerive();
    @GlobalValue(symbol="kSecAttrCanSign", optional=true)
    public static native CFType AttrCanSign();
    @GlobalValue(symbol="kSecAttrCanVerify", optional=true)
    public static native CFType AttrCanVerify();
    @GlobalValue(symbol="kSecAttrCanWrap", optional=true)
    public static native CFType AttrCanWrap();
    @GlobalValue(symbol="kSecAttrCanUnwrap", optional=true)
    public static native CFType AttrCanUnwrap();
    @GlobalValue(symbol="kSecAttrAccessibleWhenUnlocked", optional=true)
    public static native CFType AttrAccessibleWhenUnlocked();
    @GlobalValue(symbol="kSecAttrAccessibleAfterFirstUnlock", optional=true)
    public static native CFType AttrAccessibleAfterFirstUnlock();
    @GlobalValue(symbol="kSecAttrAccessibleAlways", optional=true)
    public static native CFType AttrAccessibleAlways();
    @GlobalValue(symbol="kSecAttrAccessibleWhenUnlockedThisDeviceOnly", optional=true)
    public static native CFType AttrAccessibleWhenUnlockedThisDeviceOnly();
    @GlobalValue(symbol="kSecAttrAccessibleAfterFirstUnlockThisDeviceOnly", optional=true)
    public static native CFType AttrAccessibleAfterFirstUnlockThisDeviceOnly();
    @GlobalValue(symbol="kSecAttrAccessibleAlwaysThisDeviceOnly", optional=true)
    public static native CFType AttrAccessibleAlwaysThisDeviceOnly();
    @GlobalValue(symbol="kSecAttrProtocolFTP", optional=true)
    public static native CFType AttrProtocolFTP();
    @GlobalValue(symbol="kSecAttrProtocolFTPAccount", optional=true)
    public static native CFType AttrProtocolFTPAccount();
    @GlobalValue(symbol="kSecAttrProtocolHTTP", optional=true)
    public static native CFType AttrProtocolHTTP();
    @GlobalValue(symbol="kSecAttrProtocolIRC", optional=true)
    public static native CFType AttrProtocolIRC();
    @GlobalValue(symbol="kSecAttrProtocolNNTP", optional=true)
    public static native CFType AttrProtocolNNTP();
    @GlobalValue(symbol="kSecAttrProtocolPOP3", optional=true)
    public static native CFType AttrProtocolPOP3();
    @GlobalValue(symbol="kSecAttrProtocolSMTP", optional=true)
    public static native CFType AttrProtocolSMTP();
    @GlobalValue(symbol="kSecAttrProtocolSOCKS", optional=true)
    public static native CFType AttrProtocolSOCKS();
    @GlobalValue(symbol="kSecAttrProtocolIMAP", optional=true)
    public static native CFType AttrProtocolIMAP();
    @GlobalValue(symbol="kSecAttrProtocolLDAP", optional=true)
    public static native CFType AttrProtocolLDAP();
    @GlobalValue(symbol="kSecAttrProtocolAppleTalk", optional=true)
    public static native CFType AttrProtocolAppleTalk();
    @GlobalValue(symbol="kSecAttrProtocolAFP", optional=true)
    public static native CFType AttrProtocolAFP();
    @GlobalValue(symbol="kSecAttrProtocolTelnet", optional=true)
    public static native CFType AttrProtocolTelnet();
    @GlobalValue(symbol="kSecAttrProtocolSSH", optional=true)
    public static native CFType AttrProtocolSSH();
    @GlobalValue(symbol="kSecAttrProtocolFTPS", optional=true)
    public static native CFType AttrProtocolFTPS();
    @GlobalValue(symbol="kSecAttrProtocolHTTPS", optional=true)
    public static native CFType AttrProtocolHTTPS();
    @GlobalValue(symbol="kSecAttrProtocolHTTPProxy", optional=true)
    public static native CFType AttrProtocolHTTPProxy();
    @GlobalValue(symbol="kSecAttrProtocolHTTPSProxy", optional=true)
    public static native CFType AttrProtocolHTTPSProxy();
    @GlobalValue(symbol="kSecAttrProtocolFTPProxy", optional=true)
    public static native CFType AttrProtocolFTPProxy();
    @GlobalValue(symbol="kSecAttrProtocolSMB", optional=true)
    public static native CFType AttrProtocolSMB();
    @GlobalValue(symbol="kSecAttrProtocolRTSP", optional=true)
    public static native CFType AttrProtocolRTSP();
    @GlobalValue(symbol="kSecAttrProtocolRTSPProxy", optional=true)
    public static native CFType AttrProtocolRTSPProxy();
    @GlobalValue(symbol="kSecAttrProtocolDAAP", optional=true)
    public static native CFType AttrProtocolDAAP();
    @GlobalValue(symbol="kSecAttrProtocolEPPC", optional=true)
    public static native CFType AttrProtocolEPPC();
    @GlobalValue(symbol="kSecAttrProtocolIPP", optional=true)
    public static native CFType AttrProtocolIPP();
    @GlobalValue(symbol="kSecAttrProtocolNNTPS", optional=true)
    public static native CFType AttrProtocolNNTPS();
    @GlobalValue(symbol="kSecAttrProtocolLDAPS", optional=true)
    public static native CFType AttrProtocolLDAPS();
    @GlobalValue(symbol="kSecAttrProtocolTelnetS", optional=true)
    public static native CFType AttrProtocolTelnetS();
    @GlobalValue(symbol="kSecAttrProtocolIMAPS", optional=true)
    public static native CFType AttrProtocolIMAPS();
    @GlobalValue(symbol="kSecAttrProtocolIRCS", optional=true)
    public static native CFType AttrProtocolIRCS();
    @GlobalValue(symbol="kSecAttrProtocolPOP3S", optional=true)
    public static native CFType AttrProtocolPOP3S();
    @GlobalValue(symbol="kSecAttrAuthenticationTypeNTLM", optional=true)
    public static native CFType AttrAuthenticationTypeNTLM();
    @GlobalValue(symbol="kSecAttrAuthenticationTypeMSN", optional=true)
    public static native CFType AttrAuthenticationTypeMSN();
    @GlobalValue(symbol="kSecAttrAuthenticationTypeDPA", optional=true)
    public static native CFType AttrAuthenticationTypeDPA();
    @GlobalValue(symbol="kSecAttrAuthenticationTypeRPA", optional=true)
    public static native CFType AttrAuthenticationTypeRPA();
    @GlobalValue(symbol="kSecAttrAuthenticationTypeHTTPBasic", optional=true)
    public static native CFType AttrAuthenticationTypeHTTPBasic();
    @GlobalValue(symbol="kSecAttrAuthenticationTypeHTTPDigest", optional=true)
    public static native CFType AttrAuthenticationTypeHTTPDigest();
    @GlobalValue(symbol="kSecAttrAuthenticationTypeHTMLForm", optional=true)
    public static native CFType AttrAuthenticationTypeHTMLForm();
    @GlobalValue(symbol="kSecAttrAuthenticationTypeDefault", optional=true)
    public static native CFType AttrAuthenticationTypeDefault();
    @GlobalValue(symbol="kSecAttrKeyClassPublic", optional=true)
    public static native CFType AttrKeyClassPublic();
    @GlobalValue(symbol="kSecAttrKeyClassPrivate", optional=true)
    public static native CFType AttrKeyClassPrivate();
    @GlobalValue(symbol="kSecAttrKeyClassSymmetric", optional=true)
    public static native CFType AttrKeyClassSymmetric();
    @GlobalValue(symbol="kSecAttrKeyTypeRSA", optional=true)
    public static native CFType AttrKeyTypeRSA();
    @GlobalValue(symbol="kSecAttrKeyTypeEC", optional=true)
    public static native CFType AttrKeyTypeEC();
    @GlobalValue(symbol="kSecMatchPolicy", optional=true)
    public static native CFType MatchPolicy();
    @GlobalValue(symbol="kSecMatchItemList", optional=true)
    public static native CFType MatchItemList();
    @GlobalValue(symbol="kSecMatchSearchList", optional=true)
    public static native CFType MatchSearchList();
    @GlobalValue(symbol="kSecMatchIssuers", optional=true)
    public static native CFType MatchIssuers();
    @GlobalValue(symbol="kSecMatchEmailAddressIfPresent", optional=true)
    public static native CFType MatchEmailAddressIfPresent();
    @GlobalValue(symbol="kSecMatchSubjectContains", optional=true)
    public static native CFType MatchSubjectContains();
    @GlobalValue(symbol="kSecMatchCaseInsensitive", optional=true)
    public static native CFType MatchCaseInsensitive();
    @GlobalValue(symbol="kSecMatchTrustedOnly", optional=true)
    public static native CFType MatchTrustedOnly();
    @GlobalValue(symbol="kSecMatchValidOnDate", optional=true)
    public static native CFType MatchValidOnDate();
    @GlobalValue(symbol="kSecMatchLimit", optional=true)
    public static native CFType MatchLimit();
    @GlobalValue(symbol="kSecMatchLimitOne", optional=true)
    public static native CFType MatchLimitOne();
    @GlobalValue(symbol="kSecMatchLimitAll", optional=true)
    public static native CFType MatchLimitAll();
    @GlobalValue(symbol="kSecReturnData", optional=true)
    public static native CFType ReturnData();
    @GlobalValue(symbol="kSecReturnAttributes", optional=true)
    public static native CFType ReturnAttributes();
    @GlobalValue(symbol="kSecReturnRef", optional=true)
    public static native CFType ReturnRef();
    @GlobalValue(symbol="kSecReturnPersistentRef", optional=true)
    public static native CFType ReturnPersistentRef();
    @GlobalValue(symbol="kSecValueData", optional=true)
    public static native CFType ValueData();
    @GlobalValue(symbol="kSecValueRef", optional=true)
    public static native CFType ValueRef();
    @GlobalValue(symbol="kSecValuePersistentRef", optional=true)
    public static native CFType ValuePersistentRef();
    @GlobalValue(symbol="kSecUseItemList", optional=true)
    public static native CFType UseItemList();
    
    @Bridge(symbol="SecItemCopyMatching", optional=true)
    public static native int copyMatching(CFDictionary query, CFType.CFTypePtr result);
    @Bridge(symbol="SecItemAdd", optional=true)
    public static native int add(CFDictionary attributes, CFType.CFTypePtr result);
    @Bridge(symbol="SecItemUpdate", optional=true)
    public static native int update(CFDictionary query, CFDictionary attributesToUpdate);
    @Bridge(symbol="SecItemDelete", optional=true)
    public static native int delete(CFDictionary query);
    /*</methods>*/
}
