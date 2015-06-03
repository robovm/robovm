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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFMutableData/*</name>*/ 
    extends /*<extends>*/CFData/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFMutableDataPtr extends Ptr<CFMutableData, CFMutableDataPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFMutableData.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    protected CFMutableData() {}
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    static void checkOffsetAndCount(int arrayLength, int offset, int count) {
        if ((offset | count) < 0 || offset > arrayLength || arrayLength - offset < count) {
            throw new ArrayIndexOutOfBoundsException("length=" + arrayLength + "; regionStart=" + offset
                    + "; regionLength=" + count);
        }
    }
    
    public static CFMutableData create(long capacity) {
        return create((CFAllocator)null, capacity);
    }
    public static CFMutableData createCopy(long capacity, CFData theData) {
        return createCopy(null, capacity, theData);
    }

    public ByteBuffer asByteBuffer() {
        return VM.newDirectByteBuffer(getMutableBytePtr(), getLength());
    }
    
    public CFMutableData append(ByteBuffer bytes) {
        long handle = getEffectiveAddress(bytes) + bytes.position();
        append(handle, bytes.remaining());
        return this;
    }

    public CFMutableData append(byte[] bytes) {
        return append(bytes, 0, bytes.length);
    }
    
    public CFMutableData append(byte[] bytes, int offset, int length) {
        checkOffsetAndCount(bytes.length, offset, length);
        if (length == 0) {
            return this;
        }
        append(VM.getArrayValuesAddress(bytes) + offset, length);
        return this;
    }

    public CFMutableData insert(long location, ByteBuffer bytes) {
        return replace(new CFRange(location, 0), bytes);
    }

    public CFMutableData insert(long location, byte[] bytes) {
        return replace(new CFRange(location, 0), bytes, 0, bytes.length);
    }
    
    public CFMutableData insert(long location, byte[] bytes, int offset, int length) {
        return replace(new CFRange(location, 0), bytes, offset, length);
    }

    public CFMutableData replace(CFRange range, ByteBuffer bytes) {
        long handle = getEffectiveAddress(bytes) + bytes.position();
        replace(range, handle, bytes.remaining());
        return this;
    }

    public CFMutableData replace(CFRange range, byte[] bytes) {
        return replace(range, bytes, 0, bytes.length);
    }
    
    public CFMutableData replace(CFRange range, byte[] bytes, int offset, int length) {
        checkOffsetAndCount(bytes.length, offset, length);
        replace(range, VM.getArrayValuesAddress(bytes) + offset, length);
        return this;
    }
    public CFMutableData delete(CFRange range) {
        deleteBytes(range);
        return this;
    }
    /*<methods>*/
    @Bridge(symbol="CFDataCreateMutable", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFMutableData create(CFAllocator allocator, @MachineSizedSInt long capacity);
    @Bridge(symbol="CFDataCreateMutableCopy", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFMutableData createCopy(CFAllocator allocator, @MachineSizedSInt long capacity, CFData theData);
    @Bridge(symbol="CFDataGetMutableBytePtr", optional=true)
    protected native @Pointer long getMutableBytePtr();
    @Bridge(symbol="CFDataSetLength", optional=true)
    public native void setLength(@MachineSizedSInt long length);
    @Bridge(symbol="CFDataIncreaseLength", optional=true)
    public native void increaseLength(@MachineSizedSInt long extraLength);
    @Bridge(symbol="CFDataAppendBytes", optional=true)
    protected native void append(@Pointer long bytes, @MachineSizedSInt long length);
    @Bridge(symbol="CFDataReplaceBytes", optional=true)
    protected native void replace(@ByVal CFRange range, @Pointer long newBytes, @MachineSizedSInt long newLength);
    @Bridge(symbol="CFDataDeleteBytes", optional=true)
    protected native void deleteBytes(@ByVal CFRange range);
    /*</methods>*/
}
