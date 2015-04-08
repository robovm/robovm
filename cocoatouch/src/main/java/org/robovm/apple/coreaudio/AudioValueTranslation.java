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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AudioValueTranslation/*</name>*/ 
    extends /*<extends>*/Struct<AudioValueTranslation>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AudioValueTranslationPtr extends Ptr<AudioValueTranslation, AudioValueTranslationPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    public AudioValueTranslation setInputData(byte[] data) {
        setInputArrayData(data, data.length);
        return this;
    }
    public AudioValueTranslation setInputData(short[] data) {
        setInputArrayData(data, data.length);
        return this;
    }
    public AudioValueTranslation setInputData(char[] data) {
        setInputArrayData(data, data.length);
        return this;
    }
    public AudioValueTranslation setInputData(int[] data) {
        setInputArrayData(data, data.length);
        return this;
    }
    public AudioValueTranslation setInputData(long[] data) {
        setInputArrayData(data, data.length);
        return this;
    }
    public AudioValueTranslation setInputData(float[] data) {
        setInputArrayData(data, data.length);
        return this;
    }
    public AudioValueTranslation setInputData(double[] data) {
        setInputArrayData(data, data.length);
        return this;
    }
    public AudioValueTranslation setInputData(Buffer data) {
        setInputDataSize(data.capacity());
        setInputData0(BufferMarshaler.toNative(data, 0));
        return this;
    }
    private AudioValueTranslation setInputArrayData(Object array, int length) {
        setInputDataSize(length);
        setInputData0(VM.getArrayValuesAddress(array));
        return this;
    }
    
    @SuppressWarnings("unchecked")
    public <T extends Buffer> T getOutputDataAsBuffer(Class<T> bufferType) {
        VoidPtr ptr = getOutputDataPointer();
        if (bufferType == ByteBuffer.class) {
            return (T) ptr.as(BytePtr.class).asByteBuffer(getOutputDataSize());
        } else if (bufferType == CharBuffer.class) {
            return (T) ptr.as(CharPtr.class).asCharBuffer(getOutputDataSize());
        } else if (bufferType == ShortBuffer.class) {
            return (T) ptr.as(ShortPtr.class).asShortBuffer(getOutputDataSize());
        } else if (bufferType == IntBuffer.class) {
            return (T) ptr.as(IntPtr.class).asIntBuffer(getOutputDataSize());
        } else if (bufferType == LongBuffer.class) {
            return (T) ptr.as(LongPtr.class).asLongBuffer(getOutputDataSize());
        } else if (bufferType == FloatBuffer.class) {
            return (T) ptr.as(FloatPtr.class).asFloatBuffer(getOutputDataSize());
        } else if (bufferType == DoubleBuffer.class) {
            return (T) ptr.as(DoublePtr.class).asDoubleBuffer(getOutputDataSize());
        } else {
            throw new UnsupportedOperationException("Buffer type not supported: " + bufferType);
        }
    }

    public byte[] getOutputDataAsByteArray() {
        BytePtr ptr = getOutputDataPointer().as(BytePtr.class);
        return ptr.toByteArray(getOutputDataSize());
    }
    public short[] getOutputDataAsShortArray() {
        ShortPtr ptr = getOutputDataPointer().as(ShortPtr.class);
        return ptr.toShortArray(getOutputDataSize());
    }
    public char[] getOutputDataAsCharArray() {
        CharPtr ptr = getOutputDataPointer().as(CharPtr.class);
        return ptr.toCharArray(getOutputDataSize());
    }
    public int[] getOutputDataAsIntArray() {
        IntPtr ptr = getOutputDataPointer().as(IntPtr.class);
        return ptr.toIntArray(getOutputDataSize());
    }
    public long[] getOutputDataAsLongArray() {
        LongPtr ptr = getOutputDataPointer().as(LongPtr.class);
        return ptr.toLongArray(getOutputDataSize());
    }
    public float[] getOutputDataAsFloatArray() {
        FloatPtr ptr = getOutputDataPointer().as(FloatPtr.class);
        return ptr.toFloatArray(getOutputDataSize());
    }
    public double[] getOutputDataAsDoubleArray() {
        DoublePtr ptr = getOutputDataPointer().as(DoublePtr.class);
        return ptr.toDoubleArray(getOutputDataSize());
    }
    /*<members>*/
    @StructMember(1) private native int getInputDataSize();
    @StructMember(1) private native AudioValueTranslation setInputDataSize(int inputDataSize);
    @StructMember(3) private native int getOutputDataSize();
    @StructMember(3) private native AudioValueTranslation setOutputDataSize(int outputDataSize);
    /*</members>*/
    @StructMember(0) public native VoidPtr getInputDataPointer();
    @StructMember(0) private native AudioValueTranslation setInputData0(@Pointer long inputData);
    @StructMember(2) public native VoidPtr getOutputDataPointer();
    @StructMember(2) private native AudioValueTranslation setOutputData(@Pointer long outputData);
    /*<methods>*//*</methods>*/
}
