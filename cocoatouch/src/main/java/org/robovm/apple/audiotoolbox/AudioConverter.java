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
package org.robovm.apple.audiotoolbox;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.audiounit.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremidi.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("AudioToolbox")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AudioConverter/*</name>*/ 
    extends /*<extends>*/NativeObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AudioConverterPtr extends Ptr<AudioConverter, AudioConverterPtr> {}/*</ptr>*/

    /*<bind>*/static { Bro.bind(AudioConverter.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected AudioConverter() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public static AudioConverter create(AudioStreamBasicDescription sourceFormat, AudioStreamBasicDescription destinationFormat) throws OSStatusException {
        AudioConverter.AudioConverterPtr ptr = new AudioConverter.AudioConverterPtr();
        OSStatus status = create0(sourceFormat, destinationFormat, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public static AudioConverter create(AudioStreamBasicDescription sourceFormat, AudioStreamBasicDescription destinationFormat, AudioClassDescription[] classDescriptions) throws OSStatusException {
        AudioConverter.AudioConverterPtr ptr = new AudioConverter.AudioConverterPtr();
        AudioClassDescription classes = new AudioClassDescription();
        classes.update(classDescriptions);
        OSStatus status = create0(sourceFormat, destinationFormat, classDescriptions.length, classes, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public void dispose() throws OSStatusException {
        OSStatus status = dispose0();
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public void reset() throws OSStatusException {
        OSStatus status = reset0();
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public int getPropertySize(AudioConverterProperty id) throws OSStatusException {
        IntPtr ptr = new IntPtr();
        OSStatus status = getPropertyInfo0(id, ptr, null);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public boolean isPropertyWritable(AudioConverterProperty id) throws OSStatusException {
        BooleanPtr ptr = new BooleanPtr();
        OSStatus status = getPropertyInfo0(id, null, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public <T extends Struct<T>> T getProperty(AudioConverterProperty id, Class<T> type) throws OSStatusException {
        T data = Struct.allocate(type);
        IntPtr dataSize = new IntPtr(Struct.sizeOf(data));
        OSStatus status = getProperty0(id, dataSize, data.as(VoidPtr.class));
        OSStatusException.throwIfNecessary(status);
        return data;
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public <T extends Struct<T>> void setProperty(AudioConverterProperty id, T data) throws OSStatusException {
        OSStatus status = setProperty0(id, data == null ? 0 : Struct.sizeOf(data), data == null ? null : data.as(VoidPtr.class));
        OSStatusException.throwIfNecessary(status);
    }
    public int getPropertyAsInt(AudioConverterProperty id) throws OSStatusException {
        IntPtr ptr = getProperty(id, IntPtr.class);
        return ptr.get();
    }
    public long getPropertyAsLong(AudioConverterProperty id) throws OSStatusException {
        LongPtr ptr = getProperty(id, LongPtr.class);
        return ptr.get();
    }
    public float getPropertyAsFloat(AudioConverterProperty id) throws OSStatusException {
        FloatPtr ptr = getProperty(id, FloatPtr.class);
        return ptr.get();
    }
    public double getPropertyAsDouble(AudioConverterProperty id) throws OSStatusException {
        DoublePtr ptr = getProperty(id, DoublePtr.class);
        return ptr.get();
    }
    public void setProperty(AudioConverterProperty id, int value) throws OSStatusException {
        setProperty(id, new IntPtr(value));
    }
    public void setProperty(AudioConverterProperty id, long value) throws OSStatusException {
        setProperty(id, new LongPtr(value));
    }
    public void setProperty(AudioConverterProperty id, float value) throws OSStatusException {
        setProperty(id, new FloatPtr(value));
    }
    public void setProperty(AudioConverterProperty id, double value) throws OSStatusException {
        setProperty(id, new DoublePtr(value));
    }
    
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public byte[] convertBuffer(byte[] buffer) throws OSStatusException {
        BytePtr bytePtr = new BytePtr();
        IntPtr sizePtr = new IntPtr(buffer.length);
        OSStatus status = convertBuffer0(buffer.length, VM.getArrayValuesAddress(buffer), sizePtr, bytePtr);
        OSStatusException.throwIfNecessary(status);
        return bytePtr.toByteArray(sizePtr.get());
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void convertComplexBuffer(int numberPCMFrames, AudioBufferList inputData, AudioBufferList outputData) throws OSStatusException {
        OSStatus status = convertComplexBuffer0(numberPCMFrames, inputData, outputData);
        OSStatusException.throwIfNecessary(status);
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioConverterNew", optional=true)
    protected static native OSStatus create0(AudioStreamBasicDescription inSourceFormat, AudioStreamBasicDescription inDestinationFormat, AudioConverter.AudioConverterPtr outAudioConverter);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioConverterNewSpecific", optional=true)
    protected static native OSStatus create0(AudioStreamBasicDescription inSourceFormat, AudioStreamBasicDescription inDestinationFormat, int inNumberClassDescriptions, AudioClassDescription inClassDescriptions, AudioConverter.AudioConverterPtr outAudioConverter);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioConverterDispose", optional=true)
    protected native OSStatus dispose0();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioConverterReset", optional=true)
    protected native OSStatus reset0();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioConverterGetPropertyInfo", optional=true)
    protected native OSStatus getPropertyInfo0(AudioConverterProperty inPropertyID, IntPtr outSize, BooleanPtr outWritable);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioConverterGetProperty", optional=true)
    protected native OSStatus getProperty0(AudioConverterProperty inPropertyID, IntPtr ioPropertyDataSize, VoidPtr outPropertyData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioConverterSetProperty", optional=true)
    protected native OSStatus setProperty0(AudioConverterProperty inPropertyID, int inPropertyDataSize, VoidPtr inPropertyData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioConverterConvertBuffer", optional=true)
    protected native OSStatus convertBuffer0(int inInputDataSize, @Pointer long inInputData, IntPtr ioOutputDataSize, BytePtr outOutputData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioConverterFillComplexBuffer", optional=true)
    protected native OSStatus fillComplexBuffer0(FunctionPtr inInputDataProc, @Pointer long inInputDataProcUserData, IntPtr ioOutputDataPacketSize, AudioBufferList outOutputData, AudioStreamPacketDescription.AudioStreamPacketDescriptionPtr outPacketDescription);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="AudioConverterConvertComplexBuffer", optional=true)
    protected native OSStatus convertComplexBuffer0(int inNumberPCMFrames, AudioBufferList inInputData, AudioBufferList outOutputData);
    /*</methods>*/
}
