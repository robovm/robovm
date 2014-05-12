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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSData/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSDataPtr extends Ptr<NSData, NSDataPtr> {}/*</ptr>*/
    
    private static final int EFFECTIVE_DIRECT_ADDRESS_OFFSET;

    static {
        try {
            java.lang.reflect.Field f1 = Buffer.class.getDeclaredField("effectiveDirectAddress");
            if (f1.getType() != int.class) {
                // Make sure we don't mess up here when we start using a 64-bit capable Android class lib.
                throw new Error("java.nio.Buffer.effectiveDirectAddress should be an int");
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
        return VM.getInt(VM.getObjectAddress(bytes) + EFFECTIVE_DIRECT_ADDRESS_OFFSET);
    }
    
    /*<bind>*/static { ObjCRuntime.bind(NSData.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSData() {}
    protected NSData(SkipInit skipInit) { super(skipInit); }
    public NSData(NSData data) { super((SkipInit) null); initObject(initWithData$(data)); }
    /*</constructors>*/
    
    public NSData(byte[] bytes) {
        super((SkipInit) null);
        if (bytes == null) {
            throw new NullPointerException("bytes");
        }
        initObject(initWithBytes$length$(VM.getArrayValuesAddress(bytes), bytes.length));
    }

    public NSData(ByteBuffer bytes) {
        super((SkipInit) null);
        if (bytes == null) {
            throw new NullPointerException("bytes");
        }
        long handle = getEffectiveAddress(bytes) + bytes.position();
        initObject(initWithBytesNoCopy$length$freeWhenDone$(handle, bytes.remaining(), false));
        addStrongRef(bytes);
    }

    public NSData(BytePtr bytes, int length, boolean freeWhenDone) {
        super((SkipInit) null);
        if (bytes == null) {
            throw new NullPointerException("bytes");
        }
        initObject(initWithBytesNoCopy$length$freeWhenDone$(bytes.getHandle(), length, freeWhenDone));
    }
    
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    
    public ByteBuffer asByteBuffer() {
        return VM.newDirectByteBuffer(bytes(), getLength());
    }

    public byte[] getBytes() {
        int length = (int) getLength();
        byte[] bytes = new byte[length];
        getBytes$length$(VM.getArrayValuesAddress(bytes), length);
        return bytes;
    }
    
    public static NSData fromBaseEncodedData(NSData base64Data, NSDataBase64DecodingOptions options) {
        NSData data = new NSData((SkipInit) null);
        long handle = data.initWithBase64EncodedData$options$(base64Data, options);
        if (handle == 0) {
            return null;
        }
        data.initObject(handle);
        return data;
    }

    public static NSData fromBaseEncodedString(String base64String, NSDataBase64DecodingOptions options) {
        NSData data = new NSData((SkipInit) null);
        long handle = data.initWithBase64EncodedString$options$(base64String, options);
        if (handle == 0) {
            return null;
        }
        data.initObject(handle);
        return data;
    }

    public static NSData read(java.io.File file, NSDataReadingOptions readOptionsMask, NSError.NSErrorPtr errorPtr) {
        return (NSData) dataWithContentsOfFile$options$error$(file.getAbsolutePath(), readOptionsMask, errorPtr);
    }
    public static NSData read(java.io.File file) {
        return (NSData) dataWithContentsOfFile$(file.getAbsolutePath());
    }
    public static NSData readMapped(java.io.File file) {
        return (NSData) dataWithContentsOfMappedFile$(file.getAbsolutePath());
    }
    
    public void write(java.io.File file, boolean useAuxiliaryFile) {
        writeToFile$atomically$(file.getAbsolutePath(), useAuxiliaryFile);
    }

    public void write(java.io.File file, NSDataWritingOptions writeOptionsMask, NSError.NSErrorPtr errorPtr) {
        writeToFile$options$error$(file.getAbsolutePath(), writeOptionsMask, errorPtr);
    }

    /*<methods>*/
    @Method(selector = "length")
    public native @MachineSizedUInt long getLength();
    @Method(selector = "bytes")
    protected native @Pointer long bytes();
    @Method(selector = "getBytes:length:")
    protected native void getBytes$length$(@Pointer long buffer, @MachineSizedUInt long length);
    @Method(selector = "subdataWithRange:")
    public native NSData getSubdata(@ByVal NSRange range);
    @Method(selector = "writeToFile:atomically:")
    protected native boolean writeToFile$atomically$(String path, boolean useAuxiliaryFile);
    @Method(selector = "writeToURL:atomically:")
    public native boolean write(NSURL url, boolean atomically);
    @Method(selector = "writeToFile:options:error:")
    protected native boolean writeToFile$options$error$(String path, NSDataWritingOptions writeOptionsMask, NSError.NSErrorPtr errorPtr);
    @Method(selector = "writeToURL:options:error:")
    public native boolean write(NSURL url, NSDataWritingOptions writeOptionsMask, NSError.NSErrorPtr errorPtr);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "rangeOfData:options:range:")
    public native @ByVal NSRange find(NSData dataToFind, NSDataSearchOptions mask, @ByVal NSRange searchRange);
    @Method(selector = "initWithBytes:length:")
    protected native @Pointer long initWithBytes$length$(@Pointer long bytes, @MachineSizedUInt long length);
    @Method(selector = "initWithBytesNoCopy:length:freeWhenDone:")
    protected native @Pointer long initWithBytesNoCopy$length$freeWhenDone$(@Pointer long bytes, @MachineSizedUInt long length, boolean b);
    @Method(selector = "initWithData:")
    protected native @Pointer long initWithData$(NSData data);
    @Method(selector = "dataWithContentsOfFile:options:error:")
    protected static native NSObject dataWithContentsOfFile$options$error$(String path, NSDataReadingOptions readOptionsMask, NSError.NSErrorPtr errorPtr);
    @Method(selector = "dataWithContentsOfURL:options:error:")
    public static native NSObject read(NSURL url, NSDataReadingOptions readOptionsMask, NSError.NSErrorPtr errorPtr);
    @Method(selector = "dataWithContentsOfFile:")
    protected static native NSObject dataWithContentsOfFile$(String path);
    @Method(selector = "dataWithContentsOfURL:")
    public static native NSObject read(NSURL url);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "initWithBase64EncodedString:options:")
    protected native @Pointer long initWithBase64EncodedString$options$(String base64String, NSDataBase64DecodingOptions options);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "base64EncodedStringWithOptions:")
    public native String toBase64EncodedString(NSDataBase64EncodingOptions options);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "initWithBase64EncodedData:options:")
    protected native @Pointer long initWithBase64EncodedData$options$(NSData base64Data, NSDataBase64DecodingOptions options);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "base64EncodedDataWithOptions:")
    public native NSData toBase64EncodedData(NSDataBase64EncodingOptions options);
    @Method(selector = "dataWithContentsOfMappedFile:")
    protected static native NSObject dataWithContentsOfMappedFile$(String path);
    /*</methods>*/
}
