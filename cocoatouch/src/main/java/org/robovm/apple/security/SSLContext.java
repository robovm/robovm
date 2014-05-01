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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SSLContext/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SSLContextPtr extends Ptr<SSLContext, SSLContextPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(SSLContext.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected SSLContext() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Bridge(symbol="SSLContextGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="SSLCreateContext", optional=true)
    public static native SSLContext createContext(CFAllocator alloc, SSLProtocolSide protocolSide, SSLConnectionType connectionType);
    @Bridge(symbol="SSLGetSessionState", optional=true)
    public native int getSessionState(IntPtr state);
    @Bridge(symbol="SSLSetSessionOption", optional=true)
    public native int setSessionOption(SSLSessionOption option, boolean value);
    @Bridge(symbol="SSLGetSessionOption", optional=true)
    public native int getSessionOption(SSLSessionOption option, BytePtr value);
    @Bridge(symbol="SSLSetIOFuncs", optional=true)
    public native int setIOFuncs(FunctionPtr readFunc, FunctionPtr writeFunc);
    @Bridge(symbol="SSLSetProtocolVersionMin", optional=true)
    public native int setProtocolVersionMin(SSLProtocol minVersion);
    @Bridge(symbol="SSLGetProtocolVersionMin", optional=true)
    public native int getProtocolVersionMin(IntPtr minVersion);
    @Bridge(symbol="SSLSetProtocolVersionMax", optional=true)
    public native int setProtocolVersionMax(SSLProtocol maxVersion);
    @Bridge(symbol="SSLGetProtocolVersionMax", optional=true)
    public native int getProtocolVersionMax(IntPtr maxVersion);
    @Bridge(symbol="SSLSetCertificate", optional=true)
    public native int setCertificate(CFArray certRefs);
    @Bridge(symbol="SSLSetConnection", optional=true)
    public native int setConnection(VoidPtr connection);
    @Bridge(symbol="SSLGetConnection", optional=true)
    public native int getConnection(VoidPtr.VoidPtrPtr connection);
    @Bridge(symbol="SSLSetPeerDomainName", optional=true)
    public native int setPeerDomainName(BytePtr peerName, @MachineSizedUInt long peerNameLen);
    @Bridge(symbol="SSLGetPeerDomainNameLength", optional=true)
    public native int getPeerDomainNameLength(MachineSizedUIntPtr peerNameLen);
    @Bridge(symbol="SSLGetPeerDomainName", optional=true)
    public native int getPeerDomainName(BytePtr peerName, MachineSizedUIntPtr peerNameLen);
    @Bridge(symbol="SSLSetDatagramHelloCookie", optional=true)
    public native int setDatagramHelloCookie(VoidPtr cookie, @MachineSizedUInt long cookieLen);
    @Bridge(symbol="SSLSetMaxDatagramRecordSize", optional=true)
    public native int setMaxDatagramRecordSize(@MachineSizedUInt long maxSize);
    @Bridge(symbol="SSLGetMaxDatagramRecordSize", optional=true)
    public native int getMaxDatagramRecordSize(MachineSizedUIntPtr maxSize);
    @Bridge(symbol="SSLGetNegotiatedProtocolVersion", optional=true)
    public native int getNegotiatedProtocolVersion(IntPtr protocol);
    @Bridge(symbol="SSLGetNumberSupportedCiphers", optional=true)
    public native int getNumberSupportedCiphers(MachineSizedUIntPtr numCiphers);
    @Bridge(symbol="SSLGetSupportedCiphers", optional=true)
    public native int getSupportedCiphers(ShortPtr ciphers, MachineSizedUIntPtr numCiphers);
    @Bridge(symbol="SSLSetEnabledCiphers", optional=true)
    public native int setEnabledCiphers(ShortPtr ciphers, @MachineSizedUInt long numCiphers);
    @Bridge(symbol="SSLGetNumberEnabledCiphers", optional=true)
    public native int getNumberEnabledCiphers(MachineSizedUIntPtr numCiphers);
    @Bridge(symbol="SSLGetEnabledCiphers", optional=true)
    public native int getEnabledCiphers(ShortPtr ciphers, MachineSizedUIntPtr numCiphers);
    @Bridge(symbol="SSLCopyPeerTrust", optional=true)
    public native int copyPeerTrust(SecTrust.SecTrustPtr trust);
    @Bridge(symbol="SSLSetPeerID", optional=true)
    public native int setPeerID(VoidPtr peerID, @MachineSizedUInt long peerIDLen);
    @Bridge(symbol="SSLGetPeerID", optional=true)
    public native int getPeerID(VoidPtr.VoidPtrPtr peerID, MachineSizedUIntPtr peerIDLen);
    @Bridge(symbol="SSLGetNegotiatedCipher", optional=true)
    public native int getNegotiatedCipher(ShortPtr cipherSuite);
    @Bridge(symbol="SSLSetEncryptionCertificate", optional=true)
    public native int setEncryptionCertificate(CFArray certRefs);
    @Bridge(symbol="SSLSetClientSideAuthenticate", optional=true)
    public native int setClientSideAuthenticate(SSLAuthenticate auth);
    @Bridge(symbol="SSLAddDistinguishedName", optional=true)
    public native int addDistinguishedName(VoidPtr derDN, @MachineSizedUInt long derDNLen);
    @Bridge(symbol="SSLCopyDistinguishedNames", optional=true)
    public native int copyDistinguishedNames(CFArray.CFArrayPtr names);
    @Bridge(symbol="SSLGetClientCertificateState", optional=true)
    public native int getClientCertificateState(IntPtr clientState);
    @Bridge(symbol="SSLHandshake", optional=true)
    public native int handshake();
    @Bridge(symbol="SSLWrite", optional=true)
    public native int write(VoidPtr data, @MachineSizedUInt long dataLength, MachineSizedUIntPtr processed);
    @Bridge(symbol="SSLRead", optional=true)
    public native int read(VoidPtr data, @MachineSizedUInt long dataLength, MachineSizedUIntPtr processed);
    @Bridge(symbol="SSLGetBufferedReadSize", optional=true)
    public native int getBufferedReadSize(MachineSizedUIntPtr bufSize);
    @Bridge(symbol="SSLGetDatagramWriteSize", optional=true)
    public native int getDatagramWriteSize(MachineSizedUIntPtr bufSize);
    @Bridge(symbol="SSLClose", optional=true)
    public native int closeContext();
    /*</methods>*/
}
