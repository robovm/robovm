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
package org.robovm.apple.coreservices;

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
/*<annotations>*/@Library("CFNetwork")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFHTTPMessage/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFHTTPMessagePtr extends Ptr<CFHTTPMessage, CFHTTPMessagePtr> {}/*</ptr>*/
    
    private static final int EFFECTIVE_DIRECT_ADDRESS_OFFSET;

    static {
        try {
            java.lang.reflect.Field f1 = Buffer.class.getDeclaredField("effectiveDirectAddress");
            if (f1.getType() != long.class) {
                throw new Error("java.nio.Buffer.effectiveDirectAddress should be a long");
            }
            EFFECTIVE_DIRECT_ADDRESS_OFFSET = VM.getInstanceFieldOffset(VM.getFieldAddress(f1));
        } catch (NoSuchFieldException e) {
            throw new Error(e);
        }
    }
    
    static long getEffectiveAddress(ByteBuffer bytes) {
        if (!bytes.isDirect()) {
            throw new IllegalArgumentException("Direct ByteBuffer expected");
        }
        return VM.getLong(VM.getObjectAddress(bytes) + EFFECTIVE_DIRECT_ADDRESS_OFFSET);
    }
    /*<bind>*/static { Bro.bind(CFHTTPMessage.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFHTTPMessage() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    static void checkOffsetAndCount(int arrayLength, int offset, int count) {
        if ((offset | count) < 0 || offset > arrayLength || arrayLength - offset < count) {
            throw new ArrayIndexOutOfBoundsException("length=" + arrayLength + "; regionStart=" + offset
                    + "; regionLength=" + count);
        }
    }
    
    public CFHTTPMessage append(ByteBuffer bytes) {
        long handle = getEffectiveAddress(bytes) + bytes.position();
        appendBytes(handle, bytes.remaining());
        return this;
    }

    public CFHTTPMessage append(byte[] bytes) {
        return append(bytes, 0, bytes.length);
    }
    
    public CFHTTPMessage append(byte[] bytes, int offset, int length) {
        checkOffsetAndCount(bytes.length, offset, length);
        if (length == 0) {
            return this;
        }
        appendBytes(VM.getArrayValuesAddress(bytes) + offset, length);
        return this;
    }
    
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static CFHTTPMessage createRequest(String requestMethod, NSURL url, CFHTTPVersion httpVersion) {
        return createRequest(null, requestMethod, url, httpVersion);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static CFHTTPMessage createResponse(@MachineSizedSInt long statusCode, String statusDescription, CFHTTPVersion httpVersion) {
        return createResponse(null, statusCode, statusDescription, httpVersion);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static CFHTTPMessage createEmpty(boolean isRequest) {
        return createEmpty(null, isRequest);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static CFHTTPMessage createCopy(CFHTTPMessage message) {
        return createCopy(null, message);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean applyCredentials(CFHTTPAuthentication auth, String username, String password, String accountDomain) {
        CFHTTPAuthenticationCredentials credentials = new CFHTTPAuthenticationCredentials();
        if (username != null) credentials.setUsername(username);
        if (password != null) credentials.setPassword(password);
        if (accountDomain != null) credentials.setAccountDomain(accountDomain);
        return applyCredentials(auth, credentials, null);
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFHTTPMessageGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFHTTPMessageCreateRequest", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFHTTPMessage createRequest(CFAllocator alloc, String requestMethod, NSURL url, CFHTTPVersion httpVersion);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFHTTPMessageCreateResponse", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFHTTPMessage createResponse(CFAllocator alloc, @MachineSizedSInt long statusCode, String statusDescription, CFHTTPVersion httpVersion);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFHTTPMessageCreateEmpty", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFHTTPMessage createEmpty(CFAllocator alloc, boolean isRequest);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFHTTPMessageCreateCopy", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFHTTPMessage createCopy(CFAllocator alloc, CFHTTPMessage message);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFHTTPMessageIsRequest", optional=true)
    public native boolean isRequest();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFHTTPMessageCopyVersion", optional=true)
    public native CFHTTPVersion getVersion();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFHTTPMessageCopyBody", optional=true)
    public native NSData getBody();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFHTTPMessageSetBody", optional=true)
    public native void setBody(NSData bodyData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFHTTPMessageCopyHeaderFieldValue", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFString.AsStringNoRetainMarshaler.class) String getHeaderFieldValue(String headerField);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFHTTPMessageCopyAllHeaderFields", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFDictionary.AsStringStringMapMarshaler.class) Map<String, String> getAllHeaderFields();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFHTTPMessageSetHeaderFieldValue", optional=true)
    public native void setHeaderFieldValue(String headerField, String value);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFHTTPMessageAppendBytes", optional=true)
    private native boolean appendBytes(@Pointer long newBytes, @MachineSizedSInt long numBytes);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFHTTPMessageIsHeaderComplete", optional=true)
    public native boolean isHeaderComplete();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFHTTPMessageCopySerializedMessage", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(NSObject.NoRetainMarshaler.class) NSData getSerializedMessage();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFHTTPMessageCopyRequestURL", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(NSObject.NoRetainMarshaler.class) NSURL getRequestURL();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFHTTPMessageCopyRequestMethod", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFString.AsStringNoRetainMarshaler.class) String getRequestMethod();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFHTTPMessageAddAuthentication", optional=true)
    public native boolean addAuthentication(CFHTTPMessage authenticationFailureResponse, String username, String password, CFHTTPAuthenticationScheme authenticationScheme, boolean forProxy);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFHTTPMessageGetResponseStatusCode", optional=true)
    public native @MachineSizedSInt long getResponseStatusCode();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFHTTPMessageCopyResponseStatusLine", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFString.AsStringNoRetainMarshaler.class) String getResponseStatusLine();
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean applyCredentials(CFHTTPAuthentication auth, String username, String password) throws CFStreamErrorException {
       CFStreamError.CFStreamErrorPtr ptr = new CFStreamError.CFStreamErrorPtr();
       boolean result = applyCredentials(auth, username, password, ptr);
       if (ptr.get() != null) { throw new CFStreamErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFHTTPMessageApplyCredentials", optional=true)
    private native boolean applyCredentials(CFHTTPAuthentication auth, String username, String password, CFStreamError.CFStreamErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean applyCredentials(CFHTTPAuthentication auth, CFHTTPAuthenticationCredentials dict) throws CFStreamErrorException {
       CFStreamError.CFStreamErrorPtr ptr = new CFStreamError.CFStreamErrorPtr();
       boolean result = applyCredentials(auth, dict, ptr);
       if (ptr.get() != null) { throw new CFStreamErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFHTTPMessageApplyCredentialDictionary", optional=true)
    private native boolean applyCredentials(CFHTTPAuthentication auth, CFHTTPAuthenticationCredentials dict, CFStreamError.CFStreamErrorPtr error);
    /*</methods>*/
}
