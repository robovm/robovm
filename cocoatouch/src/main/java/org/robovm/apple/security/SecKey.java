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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SecKey/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SecKeyPtr extends Ptr<SecKey, SecKeyPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(SecKey.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected SecKey() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static OSStatus generatePair(SecKeyParameters parameters, VoidBlock2<SecKey, SecKey> result) {
        SecKey.SecKeyPtr publicPtr = new SecKey.SecKeyPtr();
        SecKey.SecKeyPtr privatePtr = new SecKey.SecKeyPtr();
        OSStatus r = generatePair(parameters, publicPtr, privatePtr);
        result.invoke(publicPtr.get(), privatePtr.get());
        return r;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public byte[] rawSign(SecPadding padding, byte[] dataToSign) {
        if (dataToSign == null) {
            throw new NullPointerException("dataToSign");
        }
        BytePtr sigPtr = new BytePtr();
        MachineSizedUIntPtr sigLenPtr = new MachineSizedUIntPtr();
        rawSign(padding, VM.getArrayValuesAddress(dataToSign), dataToSign.length, sigPtr, sigLenPtr);
        return sigPtr.toByteArray((int)sigLenPtr.get());
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public OSStatus rawVerify(SecPadding padding, byte[] signedData, byte[] sig) {
        if (signedData == null) {
            throw new NullPointerException("signedData");
        }
        if (sig == null) {
            throw new NullPointerException("sig");
        }
        return rawVerify(padding, VM.getArrayValuesAddress(signedData), signedData.length, VM.getArrayValuesAddress(sig), sig.length);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public byte[] encrypt(SecPadding padding, byte[] plainText) {
        if (plainText == null) {
            throw new NullPointerException("plainText");
        }
        BytePtr cipherTextPtr = new BytePtr();
        MachineSizedUIntPtr cipherTextLenPtr = new MachineSizedUIntPtr();
        encrypt(padding, VM.getArrayValuesAddress(plainText), plainText.length, cipherTextPtr, cipherTextLenPtr);
        return cipherTextPtr.toByteArray((int)cipherTextLenPtr.get());
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public byte[] decrypt(SecPadding padding, byte[] cipherText) {
        if (cipherText == null) {
            throw new NullPointerException("cipherText");
        }
        BytePtr plainTextPtr = new BytePtr();
        MachineSizedUIntPtr plainTextLenPtr = new MachineSizedUIntPtr();
        decrypt(padding, VM.getArrayValuesAddress(cipherText), cipherText.length, plainTextPtr, plainTextLenPtr);
        return plainTextPtr.toByteArray((int)plainTextLenPtr.get());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecKeyGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecKeyGeneratePair", optional=true)
    public static native OSStatus generatePair(SecKeyParameters parameters, SecKey.SecKeyPtr publicKey, SecKey.SecKeyPtr privateKey);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecKeyRawSign", optional=true)
    protected native OSStatus rawSign(SecPadding padding, @Pointer long dataToSign, @MachineSizedUInt long dataToSignLen, BytePtr sig, MachineSizedUIntPtr sigLen);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecKeyRawVerify", optional=true)
    protected native OSStatus rawVerify(SecPadding padding, @Pointer long signedData, @MachineSizedUInt long signedDataLen, @Pointer long sig, @MachineSizedUInt long sigLen);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecKeyEncrypt", optional=true)
    protected native OSStatus encrypt(SecPadding padding, @Pointer long plainText, @MachineSizedUInt long plainTextLen, BytePtr cipherText, MachineSizedUIntPtr cipherTextLen);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecKeyDecrypt", optional=true)
    protected native OSStatus decrypt(SecPadding padding, @Pointer long cipherText, @MachineSizedUInt long cipherTextLen, BytePtr plainText, MachineSizedUIntPtr plainTextLen);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecKeyGetBlockSize", optional=true)
    public native @MachineSizedUInt long getBlockSize();
    /*</methods>*/
}
