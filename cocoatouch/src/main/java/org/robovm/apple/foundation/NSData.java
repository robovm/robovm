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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSData/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSPropertyList/*</implements>*/ {

    /*<ptr>*/public static class NSDataPtr extends Ptr<NSData, NSDataPtr> {}/*</ptr>*/
    
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
    
    /*<bind>*/static { ObjCRuntime.bind(NSData.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSData() {}
    protected NSData(SkipInit skipInit) { super(skipInit); }
    public NSData(NSData data) { super((SkipInit) null); initObject(init(data)); }
    /*</constructors>*/
    
    public NSData(byte[] bytes) {
        super((SkipInit) null);
        if (bytes == null) {
            throw new NullPointerException("bytes");
        }
        initObject(init(VM.getArrayValuesAddress(bytes), bytes.length));
    }

    public NSData(ByteBuffer bytes) {
        super((SkipInit) null);
        if (bytes == null) {
            throw new NullPointerException("bytes");
        }
        long handle = getEffectiveAddress(bytes) + bytes.position();
        initObject(init(handle, bytes.remaining(), false));
        addStrongRef(bytes);
    }

    public NSData(BytePtr bytes, int length, boolean freeWhenDone) {
        super((SkipInit) null);
        if (bytes == null) {
            throw new NullPointerException("bytes");
        }
        initObject(init(bytes.getHandle(), length, freeWhenDone));
    }
    
    public <T extends Struct<T>> NSData(T structData) {
        super((SkipInit) null);
        if (structData == null) {
            throw new NullPointerException("structData");
        }
        initObject(init(structData.getHandle(), Struct.sizeOf(structData)));
    }
    
    /*<properties>*/
    @Property(selector = "length")
    public native @MachineSizedUInt long getLength();
    @Property(selector = "bytes")
    protected native @Pointer long getBytes0();
    /*</properties>*/
    /*<members>*//*</members>*/
    
    public ByteBuffer asByteBuffer() {
        return VM.newDirectByteBuffer(getBytes0(), getLength());
    }

    public byte[] getBytes() {
        int length = (int) getLength();
        byte[] bytes = new byte[length];
        getBytes(VM.getArrayValuesAddress(bytes), length);
        return bytes;
    }
    
    public <T extends Struct<T>> T getStructData(Class<T> type) {
        int length = (int) getLength();
        T struct = Struct.allocate(type);
        getBytes(struct.getHandle(), length);
        return struct;
    }
    
    public static NSData fromBaseEncodedData(NSData base64Data, NSDataBase64DecodingOptions options) {
        NSData data = new NSData((SkipInit) null);
        long handle = data.init(base64Data, options);
        if (handle == 0) {
            return null;
        }
        data.initObject(handle);
        return data;
    }

    public static NSData fromBaseEncodedString(String base64String, NSDataBase64DecodingOptions options) {
        NSData data = new NSData((SkipInit) null);
        long handle = data.init(base64String, options);
        if (handle == 0) {
            return null;
        }
        data.initObject(handle);
        return data;
    }

    /**
     * 
     * @param file
     * @param readOptionsMask
     * @return
     * @throws NSErrorException
     */
    public static NSData read(java.io.File file, NSDataReadingOptions readOptionsMask) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        NSData result = (NSData) readFile(file.getAbsolutePath(), readOptionsMask, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    public static NSData read(java.io.File file) {
        return (NSData) readFile(file.getAbsolutePath());
    }
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    public static NSData readMapped(java.io.File file) {
        return (NSData) readMappedFile(file.getAbsolutePath());
    }
    /**
     * 
     * @param url
     * @param readOptionsMask
     * @return
     * @throws NSErrorException
     */
    public NSData read(NSURL url, NSDataReadingOptions readOptionsMask) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        NSData result = read(url, readOptionsMask, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    
    public void write(java.io.File file, boolean useAuxiliaryFile) {
        writeFile(file.getAbsolutePath(), useAuxiliaryFile);
    }
    /**
     * 
     * @param file
     * @param writeOptionsMask
     * @throws NSErrorException
     */
    public void write(java.io.File file, NSDataWritingOptions writeOptionsMask) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        writeFile(file.getAbsolutePath(), writeOptionsMask, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
    }
    /**
    *
    *
    * @throws NSErrorException
    */
    public boolean write(NSURL url, NSDataWritingOptions writeOptionsMask) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = write(url, writeOptionsMask, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /*<methods>*/
    @Method(selector = "getBytes:length:")
    protected native void getBytes(@Pointer long buffer, @MachineSizedUInt long length);
    @Method(selector = "subdataWithRange:")
    public native NSData getSubdata(@ByVal NSRange range);
    @Method(selector = "writeToFile:atomically:")
    protected native boolean writeFile(String path, boolean useAuxiliaryFile);
    @Method(selector = "writeToURL:atomically:")
    public native boolean write(NSURL url, boolean atomically);
    @Method(selector = "writeToFile:options:error:")
    protected native boolean writeFile(String path, NSDataWritingOptions writeOptionsMask, NSError.NSErrorPtr errorPtr);
    @Method(selector = "writeToURL:options:error:")
    protected native boolean write(NSURL url, NSDataWritingOptions writeOptionsMask, NSError.NSErrorPtr errorPtr);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "rangeOfData:options:range:")
    public native @ByVal NSRange find(NSData dataToFind, NSDataSearchOptions mask, @ByVal NSRange searchRange);
    @Method(selector = "initWithBytes:length:")
    protected native @Pointer long init(@Pointer long bytes, @MachineSizedUInt long length);
    @Method(selector = "initWithBytesNoCopy:length:freeWhenDone:")
    protected native @Pointer long init(@Pointer long bytes, @MachineSizedUInt long length, boolean b);
    @Method(selector = "initWithData:")
    protected native @Pointer long init(NSData data);
    @Method(selector = "dataWithContentsOfFile:options:error:")
    protected static native NSData readFile(String path, NSDataReadingOptions readOptionsMask, NSError.NSErrorPtr errorPtr);
    @Method(selector = "dataWithContentsOfURL:options:error:")
    protected static native NSData read(NSURL url, NSDataReadingOptions readOptionsMask, NSError.NSErrorPtr errorPtr);
    @Method(selector = "dataWithContentsOfFile:")
    protected static native NSData readFile(String path);
    @Method(selector = "dataWithContentsOfURL:")
    public static native NSData read(NSURL url);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "initWithBase64EncodedString:options:")
    protected native @Pointer long init(String base64String, NSDataBase64DecodingOptions options);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "base64EncodedStringWithOptions:")
    public native String toBase64EncodedString(NSDataBase64EncodingOptions options);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "initWithBase64EncodedData:options:")
    protected native @Pointer long init(NSData base64Data, NSDataBase64DecodingOptions options);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "base64EncodedDataWithOptions:")
    public native NSData toBase64EncodedData(NSDataBase64EncodingOptions options);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Method(selector = "dataWithContentsOfMappedFile:")
    protected static native NSObject readMappedFile(String path);
    /*</methods>*/
}
