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
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static SSLContext create(SSLProtocolSide protocolSide, SSLConnectionType connectionType) {
        return create(null, protocolSide, connectionType);
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public SSLSessionState getSessionState() {
        IntPtr ptr = new IntPtr();
        getSessionState(ptr);
        return SSLSessionState.valueOf(ptr.get());
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean getSessionOption(SSLSessionOption option) {
        BooleanPtr ptr = new BooleanPtr();
        getSessionOption(option, ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public SSLProtocol getMinProtocolVersion() {
        IntPtr ptr = new IntPtr();
        getMinProtocolVersion(ptr);
        return SSLProtocol.valueOf(ptr.get());
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public SSLProtocol getMaxProtocolVersion() {
        IntPtr ptr = new IntPtr();
        getMaxProtocolVersion(ptr);
        return SSLProtocol.valueOf(ptr.get());
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public OSStatus setCertificate(SecIdentity identity, SecCertificate...certificates) {
        CFArray arr = CFMutableArray.create();
        arr.add(identity);
        for (SecCertificate certificate : certificates) {
            arr.add(certificate);
        }
        return setCertificate(arr);
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public OSStatus setEncryptionCertificate(SecIdentity identity, SecCertificate...certificates) {
        CFArray arr = CFMutableArray.create();
        arr.add(identity);
        for (SecCertificate certificate : certificates) {
            arr.add(certificate);
        }
        return setEncryptionCertificate(arr);
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public VoidPtr getConnection() {
        VoidPtr.VoidPtrPtr ptr = new VoidPtr.VoidPtrPtr();
        getConnection(ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public OSStatus setPeerDomainName(String peerName) {
        byte[] bytes = peerName.getBytes();
        int length = bytes.length;
        long handle = VM.malloc(length + 1);
        VM.memcpy(handle, VM.getArrayValuesAddress(bytes), length);
        return setPeerDomainName(handle, length);
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public String getPeerDomainName() {
        BytePtr peerNamePtr = new BytePtr();
        MachineSizedUIntPtr peerNameLenPtr = new MachineSizedUIntPtr();
        getPeerDomainName(peerNamePtr, peerNameLenPtr);
        return peerNamePtr.toStringZ();
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public OSStatus setDatagramHelloCookie(byte[] cookie) {
        return setDatagramHelloCookie(VM.getArrayValuesAddress(cookie), cookie.length);
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public long getMaxDatagramRecordSize() {
        MachineSizedUIntPtr ptr = new MachineSizedUIntPtr();
        getMaxDatagramRecordSize(ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public SSLProtocol getNegotiatedProtocolVersion() {
        IntPtr ptr = new IntPtr();
        getNegotiatedProtocolVersion(ptr);
        return SSLProtocol.valueOf(ptr.get());
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public long getNumberSupportedCiphers() {
        MachineSizedUIntPtr ptr = new MachineSizedUIntPtr();
        getNumberSupportedCiphers(ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public short[] getSupportedCiphers() {
        ShortPtr ciphersPtr = new ShortPtr();
        MachineSizedUIntPtr numCiphersPtr = new MachineSizedUIntPtr();
        getSupportedCiphers(ciphersPtr, numCiphersPtr);
        return ciphersPtr.toShortArray((int)numCiphersPtr.get());
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public OSStatus setEnabledCiphers(short[] ciphers) {
        return setEnabledCiphers(VM.getArrayValuesAddress(ciphers), ciphers.length);
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public long getNumberEnabledCiphers() {
        MachineSizedUIntPtr ptr = new MachineSizedUIntPtr();
        getNumberEnabledCiphers(ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public short[] getEnabledCiphers() {
        ShortPtr ciphersPtr = new ShortPtr();
        MachineSizedUIntPtr numCiphersPtr = new MachineSizedUIntPtr();
        getEnabledCiphers(ciphersPtr, numCiphersPtr);
        return ciphersPtr.toShortArray((int)numCiphersPtr.get());
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public SecTrust getPeerTrust() {
        SecTrust.SecTrustPtr ptr = new SecTrust.SecTrustPtr();
        getPeerTrust(ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public OSStatus setPeerID(String peerID) {
        byte[] bytes = peerID.getBytes();
        int length = bytes.length;
        long handle = VM.malloc(length + 1);
        VM.memcpy(handle, VM.getArrayValuesAddress(bytes), length);
        return setPeerID(handle, length);
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public String getPeerID() {
        BytePtr.BytePtrPtr peerIDPtr = new BytePtr.BytePtrPtr();
        MachineSizedUIntPtr peerIDLenPtr = new MachineSizedUIntPtr();
        getPeerID(peerIDPtr, peerIDLenPtr);
        return peerIDPtr.get().toStringZ();
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public short getNegotiatedCipher() {
        ShortPtr ptr = new ShortPtr();
        getNegotiatedCipher(ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public OSStatus addDistinguishedName(NSData derDN) {
        if (derDN == null) {
            throw new NullPointerException("derDN");
        }
        return addDistinguishedName(VM.getArrayValuesAddress(derDN.getBytes()), derDN.getLength());
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public NSArray<NSData> getDistinguishedNames() {
        NSArray.NSArrayPtr<NSData> ptr = new NSArray.NSArrayPtr<NSData>();
        getDistinguishedNames(ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public SSLClientCertificateState getClientCertificateState() {
        IntPtr ptr = new IntPtr();
        getClientCertificateState(ptr);
        return SSLClientCertificateState.valueOf(ptr.get());
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public long write(byte[] data) {
        MachineSizedUIntPtr ptr = new MachineSizedUIntPtr();
        write(VM.getArrayValuesAddress(data), data.length, ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public byte[] read(int dataLength) {
        BytePtr dataPtr = Struct.allocate(BytePtr.class, dataLength);
        MachineSizedUIntPtr processedPtr = new MachineSizedUIntPtr();
        read(dataPtr, dataLength, processedPtr);
        return dataPtr.toByteArray((int)processedPtr.get());
        
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public int getBufferedReadSize() {
        MachineSizedUIntPtr ptr = new MachineSizedUIntPtr();
        getBufferedReadSize(ptr);
        return (int)ptr.get();
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public int getDatagramWriteSize() {
        MachineSizedUIntPtr ptr = new MachineSizedUIntPtr();
        getDatagramWriteSize(ptr);
        return (int)ptr.get();
    } 
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLContextGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLCreateContext", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) SSLContext create(CFAllocator alloc, SSLProtocolSide protocolSide, SSLConnectionType connectionType);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLGetSessionState", optional=true)
    protected native OSStatus getSessionState(IntPtr state);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLSetSessionOption", optional=true)
    public native OSStatus setSessionOption(SSLSessionOption option, boolean value);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLGetSessionOption", optional=true)
    protected native OSStatus getSessionOption(SSLSessionOption option, BooleanPtr value);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLSetIOFuncs", optional=true)
    private native OSStatus setIOFunctions(FunctionPtr readFunc, FunctionPtr writeFunc);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLSetProtocolVersionMin", optional=true)
    public native OSStatus setMinProtocolVersion(SSLProtocol minVersion);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLGetProtocolVersionMin", optional=true)
    protected native OSStatus getMinProtocolVersion(IntPtr minVersion);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLSetProtocolVersionMax", optional=true)
    public native OSStatus setMaxProtocolVersion(SSLProtocol maxVersion);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLGetProtocolVersionMax", optional=true)
    protected native OSStatus getMaxProtocolVersion(IntPtr maxVersion);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLSetCertificate", optional=true)
    protected native OSStatus setCertificate(CFArray certRefs);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLSetConnection", optional=true)
    public native OSStatus setConnection(VoidPtr connection);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLGetConnection", optional=true)
    protected native OSStatus getConnection(VoidPtr.VoidPtrPtr connection);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLSetPeerDomainName", optional=true)
    protected native OSStatus setPeerDomainName(@Pointer long peerName, @MachineSizedUInt long peerNameLen);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLGetPeerDomainNameLength", optional=true)
    protected native OSStatus getPeerDomainNameLength(MachineSizedUIntPtr peerNameLen);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLGetPeerDomainName", optional=true)
    protected native OSStatus getPeerDomainName(BytePtr peerName, MachineSizedUIntPtr peerNameLen);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLSetDatagramHelloCookie", optional=true)
    protected native OSStatus setDatagramHelloCookie(@Pointer long cookie, @MachineSizedUInt long cookieLen);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLSetMaxDatagramRecordSize", optional=true)
    public native OSStatus setMaxDatagramRecordSize(@MachineSizedUInt long maxSize);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLGetMaxDatagramRecordSize", optional=true)
    protected native OSStatus getMaxDatagramRecordSize(MachineSizedUIntPtr maxSize);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLGetNegotiatedProtocolVersion", optional=true)
    protected native OSStatus getNegotiatedProtocolVersion(IntPtr protocol);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLGetNumberSupportedCiphers", optional=true)
    protected native OSStatus getNumberSupportedCiphers(MachineSizedUIntPtr numCiphers);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLGetSupportedCiphers", optional=true)
    protected native OSStatus getSupportedCiphers(ShortPtr ciphers, MachineSizedUIntPtr numCiphers);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLSetEnabledCiphers", optional=true)
    protected native OSStatus setEnabledCiphers(@Pointer long ciphers, @MachineSizedUInt long numCiphers);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLGetNumberEnabledCiphers", optional=true)
    protected native OSStatus getNumberEnabledCiphers(MachineSizedUIntPtr numCiphers);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLGetEnabledCiphers", optional=true)
    protected native OSStatus getEnabledCiphers(ShortPtr ciphers, MachineSizedUIntPtr numCiphers);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLCopyPeerTrust", optional=true)
    protected native OSStatus getPeerTrust(SecTrust.SecTrustPtr trust);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLSetPeerID", optional=true)
    protected native OSStatus setPeerID(@Pointer long peerID, @MachineSizedUInt long peerIDLen);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLGetPeerID", optional=true)
    protected native OSStatus getPeerID(BytePtr.BytePtrPtr peerID, MachineSizedUIntPtr peerIDLen);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLGetNegotiatedCipher", optional=true)
    protected native OSStatus getNegotiatedCipher(ShortPtr cipherSuite);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLSetEncryptionCertificate", optional=true)
    protected native OSStatus setEncryptionCertificate(CFArray certRefs);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLSetClientSideAuthenticate", optional=true)
    public native OSStatus setClientSideAuthenticate(SSLAuthenticate auth);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLAddDistinguishedName", optional=true)
    protected native OSStatus addDistinguishedName(@Pointer long derDN, @MachineSizedUInt long derDNLen);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLCopyDistinguishedNames", optional=true)
    protected native OSStatus getDistinguishedNames(NSArray.NSArrayPtr<NSData> names);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLGetClientCertificateState", optional=true)
    protected native OSStatus getClientCertificateState(IntPtr clientState);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLHandshake", optional=true)
    public native OSStatus handshake();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLWrite", optional=true)
    protected native OSStatus write(@Pointer long data, @MachineSizedUInt long dataLength, MachineSizedUIntPtr processed);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLRead", optional=true)
    protected native OSStatus read(BytePtr data, @MachineSizedUInt long dataLength, MachineSizedUIntPtr processed);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLGetBufferedReadSize", optional=true)
    protected native OSStatus getBufferedReadSize(MachineSizedUIntPtr bufSize);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLGetDatagramWriteSize", optional=true)
    protected native OSStatus getDatagramWriteSize(MachineSizedUIntPtr bufSize);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="SSLClose", optional=true)
    public native OSStatus closeContext();
    /*</methods>*/
}
