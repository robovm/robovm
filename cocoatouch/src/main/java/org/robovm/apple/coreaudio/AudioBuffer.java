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
package org.robovm.apple.coreaudio;

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
import org.robovm.rt.bro.BufferMarshalers.BufferMarshaler;

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AudioBuffer/*</name>*/ 
    extends /*<extends>*/Struct<AudioBuffer>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AudioBufferPtr extends Ptr<AudioBuffer, AudioBufferPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    
    public AudioBuffer setData(byte[] data) {
        setArrayData(data, data.length);
        return this;
    }
    public AudioBuffer setData(short[] data) {
        setArrayData(data, data.length);
        return this;
    }
    public AudioBuffer setData(char[] data) {
        setArrayData(data, data.length);
        return this;
    }
    public AudioBuffer setData(int[] data) {
        setArrayData(data, data.length);
        return this;
    }
    public AudioBuffer setData(long[] data) {
        setArrayData(data, data.length);
        return this;
    }
    public AudioBuffer setData(float[] data) {
        setArrayData(data, data.length);
        return this;
    }
    public AudioBuffer setData(double[] data) {
        setArrayData(data, data.length);
        return this;
    }
    public AudioBuffer setData(Buffer data) {
        setDataByteSize(data.capacity());
        setData0(BufferMarshaler.toNative(data, 0));
        return this;
    }
    private AudioBuffer setArrayData(Object array, int length) {
        setDataByteSize(length);
        setData0(VM.getArrayValuesAddress(array));
        return this;
    }
    
    @SuppressWarnings("unchecked")
    public <T extends Buffer> T getDataAsBuffer(Class<T> bufferType) {
        VoidPtr ptr = getDataPointer();
        if (bufferType == ByteBuffer.class) {
            return (T) ptr.as(BytePtr.class).asByteBuffer(getDataByteSize());
        } else if (bufferType == CharBuffer.class) {
            return (T) ptr.as(CharPtr.class).asCharBuffer(getDataByteSize());
        } else if (bufferType == ShortBuffer.class) {
            return (T) ptr.as(ShortPtr.class).asShortBuffer(getDataByteSize());
        } else if (bufferType == IntBuffer.class) {
            return (T) ptr.as(IntPtr.class).asIntBuffer(getDataByteSize());
        } else if (bufferType == LongBuffer.class) {
            return (T) ptr.as(LongPtr.class).asLongBuffer(getDataByteSize());
        } else if (bufferType == FloatBuffer.class) {
            return (T) ptr.as(FloatPtr.class).asFloatBuffer(getDataByteSize());
        } else if (bufferType == DoubleBuffer.class) {
            return (T) ptr.as(DoublePtr.class).asDoubleBuffer(getDataByteSize());
        } else {
            throw new UnsupportedOperationException("Buffer type not supported: " + bufferType);
        }
    }

    public byte[] getDataAsByteArray() {
        BytePtr ptr = getDataPointer().as(BytePtr.class);
        return ptr.toByteArray(getDataByteSize());
    }
    public short[] getDataAsShortArray() {
        ShortPtr ptr = getDataPointer().as(ShortPtr.class);
        return ptr.toShortArray(getDataByteSize());
    }
    public char[] getDataAsCharArray() {
        CharPtr ptr = getDataPointer().as(CharPtr.class);
        return ptr.toCharArray(getDataByteSize());
    }
    public int[] getDataAsIntArray() {
        IntPtr ptr = getDataPointer().as(IntPtr.class);
        return ptr.toIntArray(getDataByteSize());
    }
    public long[] getDataAsLongArray() {
        LongPtr ptr = getDataPointer().as(LongPtr.class);
        return ptr.toLongArray(getDataByteSize());
    }
    public float[] getDataAsFloatArray() {
        FloatPtr ptr = getDataPointer().as(FloatPtr.class);
        return ptr.toFloatArray(getDataByteSize());
    }
    public double[] getDataAsDoubleArray() {
        DoublePtr ptr = getDataPointer().as(DoublePtr.class);
        return ptr.toDoubleArray(getDataByteSize());
    }
    /*<members>*/
    @StructMember(0) public native int getNumberChannels();
    @StructMember(0) public native AudioBuffer setNumberChannels(int numberChannels);
    @StructMember(1) private native int getDataByteSize();
    @StructMember(1) private native AudioBuffer setDataByteSize(int dataByteSize);
    /*</members>*/
    @StructMember(2) public native VoidPtr getDataPointer();
    @StructMember(2) private native AudioBuffer setData0(@Pointer long data);
    /*<methods>*//*</methods>*/
}
