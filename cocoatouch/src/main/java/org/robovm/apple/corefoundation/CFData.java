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
package org.robovm.apple.corefoundation;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreservices.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coretext.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFData/*</name>*/ 
    extends /*<extends>*/CFPropertyList/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFDataPtr extends Ptr<CFData, CFDataPtr> {}/*</ptr>*/
    
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
    /*<bind>*/static { Bro.bind(CFData.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFData() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public static CFData create(byte[] bytes) {
        if (bytes == null) {
            throw new NullPointerException("bytes");
        }
        return create((CFAllocator)null, VM.getArrayValuesAddress(bytes), bytes.length);
    }
    public static CFData create(ByteBuffer bytes) {
        if (bytes == null) {
            throw new NullPointerException("bytes");
        }
        long handle = getEffectiveAddress(bytes) + bytes.position();
        CFData result = create(null, handle, bytes.remaining(), null);
        return result;
    }
    public static CFData createCopy(CFData theData) {
        return createCopy(null, theData);
    }
    public ByteBuffer asByteBuffer() {
        return VM.newDirectByteBuffer(getBytePtr(), getLength());
    }

    public byte[] getBytes() {
        int length = (int) getLength();
        byte[] bytes = new byte[length];
        getBytes(new CFRange(0, length), VM.getArrayValuesAddress(bytes));
        return bytes;
    }
    /*<methods>*/
    @Bridge(symbol="CFDataGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFDataCreate", optional=true)
    private static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFData create(CFAllocator allocator, @Pointer long bytes, @MachineSizedSInt long length);
    @Bridge(symbol="CFDataCreateWithBytesNoCopy", optional=true)
    private static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFData create(CFAllocator allocator, @Pointer long bytes, @MachineSizedSInt long length, CFAllocator bytesDeallocator);
    @Bridge(symbol="CFDataCreateCopy", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFData createCopy(CFAllocator allocator, CFData theData);
    @Bridge(symbol="CFDataGetLength", optional=true)
    public native @MachineSizedSInt long getLength();
    @Bridge(symbol="CFDataGetBytePtr", optional=true)
    protected native @Pointer long getBytePtr();
    @Bridge(symbol="CFDataGetBytes", optional=true)
    protected native void getBytes(@ByVal CFRange range, @Pointer long buffer);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFDataFind", optional=true)
    public native @ByVal CFRange find(CFData dataToFind, @ByVal CFRange searchRange, CFDataSearchFlags compareOptions);
    /*</methods>*/
}
