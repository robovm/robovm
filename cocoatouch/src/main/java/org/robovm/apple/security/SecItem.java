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
    @GlobalValue(symbol="kSecClass")
    public static native CFType Class();
    @GlobalValue(symbol="kSecClassGenericPassword")
    public static native CFType ClassGenericPassword();
    @GlobalValue(symbol="kSecClassInternetPassword")
    public static native CFType ClassInternetPassword();
    @GlobalValue(symbol="kSecClassCertificate")
    public static native CFType ClassCertificate();
    @GlobalValue(symbol="kSecClassKey")
    public static native CFType ClassKey();
    @GlobalValue(symbol="kSecClassIdentity")
    public static native CFType ClassIdentity();
    @GlobalValue(symbol="kSecAttrAccessible")
    public static native CFType AttrAccessible();
    @GlobalValue(symbol="kSecAttrAccessGroup")
    public static native CFType AttrAccessGroup();
    @GlobalValue(symbol="kSecAttrSynchronizable")
    public static native CFType AttrSynchronizable();
    @GlobalValue(symbol="kSecAttrSynchronizableAny")
    public static native CFType AttrSynchronizableAny();
    @GlobalValue(symbol="kSecAttrCreationDate")
    public static native CFType AttrCreationDate();
    @GlobalValue(symbol="kSecAttrModificationDate")
    public static native CFType AttrModificationDate();
    @GlobalValue(symbol="kSecAttrDescription")
    public static native CFType AttrDescription();
    @GlobalValue(symbol="kSecAttrComment")
    public static native CFType AttrComment();
    @GlobalValue(symbol="kSecAttrCreator")
    public static native CFType AttrCreator();
    @GlobalValue(symbol="kSecAttrType")
    public static native CFType AttrType();
    @GlobalValue(symbol="kSecAttrLabel")
    public static native CFType AttrLabel();
    @GlobalValue(symbol="kSecAttrIsInvisible")
    public static native CFType AttrIsInvisible();
    @GlobalValue(symbol="kSecAttrIsNegative")
    public static native CFType AttrIsNegative();
    @GlobalValue(symbol="kSecAttrAccount")
    public static native CFType AttrAccount();
    @GlobalValue(symbol="kSecAttrService")
    public static native CFType AttrService();
    @GlobalValue(symbol="kSecAttrGeneric")
    public static native CFType AttrGeneric();
    @GlobalValue(symbol="kSecAttrSecurityDomain")
    public static native CFType AttrSecurityDomain();
    @GlobalValue(symbol="kSecAttrServer")
    public static native CFType AttrServer();
    @GlobalValue(symbol="kSecAttrProtocol")
    public static native CFType AttrProtocol();
    @GlobalValue(symbol="kSecAttrAuthenticationType")
    public static native CFType AttrAuthenticationType();
    @GlobalValue(symbol="kSecAttrPort")
    public static native CFType AttrPort();
    @GlobalValue(symbol="kSecAttrPath")
    public static native CFType AttrPath();
    @GlobalValue(symbol="kSecAttrSubject")
    public static native CFType AttrSubject();
    @GlobalValue(symbol="kSecAttrIssuer")
    public static native CFType AttrIssuer();
    @GlobalValue(symbol="kSecAttrSerialNumber")
    public static native CFType AttrSerialNumber();
    @GlobalValue(symbol="kSecAttrSubjectKeyID")
    public static native CFType AttrSubjectKeyID();
    @GlobalValue(symbol="kSecAttrPublicKeyHash")
    public static native CFType AttrPublicKeyHash();
    @GlobalValue(symbol="kSecAttrCertificateType")
    public static native CFType AttrCertificateType();
    @GlobalValue(symbol="kSecAttrCertificateEncoding")
    public static native CFType AttrCertificateEncoding();
    @GlobalValue(symbol="kSecAttrKeyClass")
    public static native CFType AttrKeyClass();
    @GlobalValue(symbol="kSecAttrApplicationLabel")
    public static native CFType AttrApplicationLabel();
    @GlobalValue(symbol="kSecAttrIsPermanent")
    public static native CFType AttrIsPermanent();
    @GlobalValue(symbol="kSecAttrApplicationTag")
    public static native CFType AttrApplicationTag();
    @GlobalValue(symbol="kSecAttrKeyType")
    public static native CFType AttrKeyType();
    @GlobalValue(symbol="kSecAttrKeySizeInBits")
    public static native CFType AttrKeySizeInBits();
    @GlobalValue(symbol="kSecAttrEffectiveKeySize")
    public static native CFType AttrEffectiveKeySize();
    @GlobalValue(symbol="kSecAttrCanEncrypt")
    public static native CFType AttrCanEncrypt();
    @GlobalValue(symbol="kSecAttrCanDecrypt")
    public static native CFType AttrCanDecrypt();
    @GlobalValue(symbol="kSecAttrCanDerive")
    public static native CFType AttrCanDerive();
    @GlobalValue(symbol="kSecAttrCanSign")
    public static native CFType AttrCanSign();
    @GlobalValue(symbol="kSecAttrCanVerify")
    public static native CFType AttrCanVerify();
    @GlobalValue(symbol="kSecAttrCanWrap")
    public static native CFType AttrCanWrap();
    @GlobalValue(symbol="kSecAttrCanUnwrap")
    public static native CFType AttrCanUnwrap();
    @GlobalValue(symbol="kSecAttrAccessibleWhenUnlocked")
    public static native CFType AttrAccessibleWhenUnlocked();
    @GlobalValue(symbol="kSecAttrAccessibleAfterFirstUnlock")
    public static native CFType AttrAccessibleAfterFirstUnlock();
    @GlobalValue(symbol="kSecAttrAccessibleAlways")
    public static native CFType AttrAccessibleAlways();
    @GlobalValue(symbol="kSecAttrAccessibleWhenUnlockedThisDeviceOnly")
    public static native CFType AttrAccessibleWhenUnlockedThisDeviceOnly();
    @GlobalValue(symbol="kSecAttrAccessibleAfterFirstUnlockThisDeviceOnly")
    public static native CFType AttrAccessibleAfterFirstUnlockThisDeviceOnly();
    @GlobalValue(symbol="kSecAttrAccessibleAlwaysThisDeviceOnly")
    public static native CFType AttrAccessibleAlwaysThisDeviceOnly();
    @GlobalValue(symbol="kSecAttrProtocolFTP")
    public static native CFType AttrProtocolFTP();
    @GlobalValue(symbol="kSecAttrProtocolFTPAccount")
    public static native CFType AttrProtocolFTPAccount();
    @GlobalValue(symbol="kSecAttrProtocolHTTP")
    public static native CFType AttrProtocolHTTP();
    @GlobalValue(symbol="kSecAttrProtocolIRC")
    public static native CFType AttrProtocolIRC();
    @GlobalValue(symbol="kSecAttrProtocolNNTP")
    public static native CFType AttrProtocolNNTP();
    @GlobalValue(symbol="kSecAttrProtocolPOP3")
    public static native CFType AttrProtocolPOP3();
    @GlobalValue(symbol="kSecAttrProtocolSMTP")
    public static native CFType AttrProtocolSMTP();
    @GlobalValue(symbol="kSecAttrProtocolSOCKS")
    public static native CFType AttrProtocolSOCKS();
    @GlobalValue(symbol="kSecAttrProtocolIMAP")
    public static native CFType AttrProtocolIMAP();
    @GlobalValue(symbol="kSecAttrProtocolLDAP")
    public static native CFType AttrProtocolLDAP();
    @GlobalValue(symbol="kSecAttrProtocolAppleTalk")
    public static native CFType AttrProtocolAppleTalk();
    @GlobalValue(symbol="kSecAttrProtocolAFP")
    public static native CFType AttrProtocolAFP();
    @GlobalValue(symbol="kSecAttrProtocolTelnet")
    public static native CFType AttrProtocolTelnet();
    @GlobalValue(symbol="kSecAttrProtocolSSH")
    public static native CFType AttrProtocolSSH();
    @GlobalValue(symbol="kSecAttrProtocolFTPS")
    public static native CFType AttrProtocolFTPS();
    @GlobalValue(symbol="kSecAttrProtocolHTTPS")
    public static native CFType AttrProtocolHTTPS();
    @GlobalValue(symbol="kSecAttrProtocolHTTPProxy")
    public static native CFType AttrProtocolHTTPProxy();
    @GlobalValue(symbol="kSecAttrProtocolHTTPSProxy")
    public static native CFType AttrProtocolHTTPSProxy();
    @GlobalValue(symbol="kSecAttrProtocolFTPProxy")
    public static native CFType AttrProtocolFTPProxy();
    @GlobalValue(symbol="kSecAttrProtocolSMB")
    public static native CFType AttrProtocolSMB();
    @GlobalValue(symbol="kSecAttrProtocolRTSP")
    public static native CFType AttrProtocolRTSP();
    @GlobalValue(symbol="kSecAttrProtocolRTSPProxy")
    public static native CFType AttrProtocolRTSPProxy();
    @GlobalValue(symbol="kSecAttrProtocolDAAP")
    public static native CFType AttrProtocolDAAP();
    @GlobalValue(symbol="kSecAttrProtocolEPPC")
    public static native CFType AttrProtocolEPPC();
    @GlobalValue(symbol="kSecAttrProtocolIPP")
    public static native CFType AttrProtocolIPP();
    @GlobalValue(symbol="kSecAttrProtocolNNTPS")
    public static native CFType AttrProtocolNNTPS();
    @GlobalValue(symbol="kSecAttrProtocolLDAPS")
    public static native CFType AttrProtocolLDAPS();
    @GlobalValue(symbol="kSecAttrProtocolTelnetS")
    public static native CFType AttrProtocolTelnetS();
    @GlobalValue(symbol="kSecAttrProtocolIMAPS")
    public static native CFType AttrProtocolIMAPS();
    @GlobalValue(symbol="kSecAttrProtocolIRCS")
    public static native CFType AttrProtocolIRCS();
    @GlobalValue(symbol="kSecAttrProtocolPOP3S")
    public static native CFType AttrProtocolPOP3S();
    @GlobalValue(symbol="kSecAttrAuthenticationTypeNTLM")
    public static native CFType AttrAuthenticationTypeNTLM();
    @GlobalValue(symbol="kSecAttrAuthenticationTypeMSN")
    public static native CFType AttrAuthenticationTypeMSN();
    @GlobalValue(symbol="kSecAttrAuthenticationTypeDPA")
    public static native CFType AttrAuthenticationTypeDPA();
    @GlobalValue(symbol="kSecAttrAuthenticationTypeRPA")
    public static native CFType AttrAuthenticationTypeRPA();
    @GlobalValue(symbol="kSecAttrAuthenticationTypeHTTPBasic")
    public static native CFType AttrAuthenticationTypeHTTPBasic();
    @GlobalValue(symbol="kSecAttrAuthenticationTypeHTTPDigest")
    public static native CFType AttrAuthenticationTypeHTTPDigest();
    @GlobalValue(symbol="kSecAttrAuthenticationTypeHTMLForm")
    public static native CFType AttrAuthenticationTypeHTMLForm();
    @GlobalValue(symbol="kSecAttrAuthenticationTypeDefault")
    public static native CFType AttrAuthenticationTypeDefault();
    @GlobalValue(symbol="kSecAttrKeyClassPublic")
    public static native CFType AttrKeyClassPublic();
    @GlobalValue(symbol="kSecAttrKeyClassPrivate")
    public static native CFType AttrKeyClassPrivate();
    @GlobalValue(symbol="kSecAttrKeyClassSymmetric")
    public static native CFType AttrKeyClassSymmetric();
    @GlobalValue(symbol="kSecAttrKeyTypeRSA")
    public static native CFType AttrKeyTypeRSA();
    @GlobalValue(symbol="kSecAttrKeyTypeEC")
    public static native CFType AttrKeyTypeEC();
    @GlobalValue(symbol="kSecMatchPolicy")
    public static native CFType MatchPolicy();
    @GlobalValue(symbol="kSecMatchItemList")
    public static native CFType MatchItemList();
    @GlobalValue(symbol="kSecMatchSearchList")
    public static native CFType MatchSearchList();
    @GlobalValue(symbol="kSecMatchIssuers")
    public static native CFType MatchIssuers();
    @GlobalValue(symbol="kSecMatchEmailAddressIfPresent")
    public static native CFType MatchEmailAddressIfPresent();
    @GlobalValue(symbol="kSecMatchSubjectContains")
    public static native CFType MatchSubjectContains();
    @GlobalValue(symbol="kSecMatchCaseInsensitive")
    public static native CFType MatchCaseInsensitive();
    @GlobalValue(symbol="kSecMatchTrustedOnly")
    public static native CFType MatchTrustedOnly();
    @GlobalValue(symbol="kSecMatchValidOnDate")
    public static native CFType MatchValidOnDate();
    @GlobalValue(symbol="kSecMatchLimit")
    public static native CFType MatchLimit();
    @GlobalValue(symbol="kSecMatchLimitOne")
    public static native CFType MatchLimitOne();
    @GlobalValue(symbol="kSecMatchLimitAll")
    public static native CFType MatchLimitAll();
    @GlobalValue(symbol="kSecReturnData")
    public static native CFType ReturnData();
    @GlobalValue(symbol="kSecReturnAttributes")
    public static native CFType ReturnAttributes();
    @GlobalValue(symbol="kSecReturnRef")
    public static native CFType ReturnRef();
    @GlobalValue(symbol="kSecReturnPersistentRef")
    public static native CFType ReturnPersistentRef();
    @GlobalValue(symbol="kSecValueData")
    public static native CFType ValueData();
    @GlobalValue(symbol="kSecValueRef")
    public static native CFType ValueRef();
    @GlobalValue(symbol="kSecValuePersistentRef")
    public static native CFType ValuePersistentRef();
    @GlobalValue(symbol="kSecUseItemList")
    public static native CFType UseItemList();
    
    @Bridge(symbol="SecItemCopyMatching")
    public static native int copyMatching(CFDictionary query, CFType.CFTypePtr result);
    @Bridge(symbol="SecItemAdd")
    public static native int add(CFDictionary attributes, CFType.CFTypePtr result);
    @Bridge(symbol="SecItemUpdate")
    public static native int update(CFDictionary query, CFDictionary attributesToUpdate);
    @Bridge(symbol="SecItemDelete")
    public static native int delete(CFDictionary query);
    /*</methods>*/
}
