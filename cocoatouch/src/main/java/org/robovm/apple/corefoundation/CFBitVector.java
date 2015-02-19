/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFBitVector/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFBitVectorPtr extends Ptr<CFBitVector, CFBitVectorPtr> {}/*</ptr>*/
    
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
    /*<bind>*/static { Bro.bind(CFBitVector.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFBitVector() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public static CFBitVector create(byte[] bytes) {
        return create(null, bytes);
    }
    public static CFBitVector create(CFAllocator allocator, byte[] bytes) {
        if (bytes == null) {
            throw new NullPointerException("bytes");
        }
        return create(allocator, VM.getArrayValuesAddress(bytes), bytes.length);
    }
    public static CFBitVector create(ByteBuffer bytes) {
        return create(null, bytes);
    }
    public static CFBitVector create(CFAllocator allocator, ByteBuffer bytes) {
        if (bytes == null) {
            throw new NullPointerException("bytes");
        }
        long handle = getEffectiveAddress(bytes) + bytes.position();
        CFBitVector result = create(allocator, handle, bytes.remaining());
        return result;
    }
    public static CFBitVector createCopy(CFBitVector bv) {
        return createCopy(null, bv);
    }

    public byte[] getBytes() {
        int length = (int) getCount();
        byte[] bytes = new byte[length];
        getBits(new CFRange(0, length), VM.getArrayValuesAddress(bytes));
        return bytes;
    }
    /*<methods>*/
    @Bridge(symbol="CFBitVectorGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFBitVectorCreate", optional=true)
    private static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFBitVector create(CFAllocator allocator, @Pointer long bytes, @MachineSizedSInt long numBits);
    @Bridge(symbol="CFBitVectorCreateCopy", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFBitVector createCopy(CFAllocator allocator, CFBitVector bv);
    @Bridge(symbol="CFBitVectorGetCount", optional=true)
    public native @MachineSizedSInt long getCount();
    @Bridge(symbol="CFBitVectorGetCountOfBit", optional=true)
    public native @MachineSizedSInt long getCountOfBit(@ByVal CFRange range, int value);
    @Bridge(symbol="CFBitVectorContainsBit", optional=true)
    public native boolean contains(@ByVal CFRange range, int value);
    @Bridge(symbol="CFBitVectorGetBitAtIndex", optional=true)
    public native int get(@MachineSizedSInt long idx);
    @Bridge(symbol="CFBitVectorGetBits", optional=true)
    private native void getBits(@ByVal CFRange range, @Pointer long bytes);
    @Bridge(symbol="CFBitVectorGetFirstIndexOfBit", optional=true)
    public native @MachineSizedSInt long indexOf(@ByVal CFRange range, int value);
    @Bridge(symbol="CFBitVectorGetLastIndexOfBit", optional=true)
    public native @MachineSizedSInt long lastIndexOf(@ByVal CFRange range, int value);
    /*</methods>*/
}
