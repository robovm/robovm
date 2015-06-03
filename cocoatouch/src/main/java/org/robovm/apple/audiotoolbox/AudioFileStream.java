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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AudioFileStream/*</name>*/ 
    extends /*<extends>*/NativeObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AudioFileStreamPtr extends Ptr<AudioFileStream, AudioFileStreamPtr> {}/*</ptr>*/
    
    public interface ParseListener {
        void onPropertyParsed(AudioFileStream audioFileStream, AudioFileStreamProperty property, AudioFileStreamMutablePropertyFlags flags);

        void onPacketsParsed(int numberBytes, long inputData, AudioStreamPacketDescription[] packetDescriptions);
    }
    
    private static java.util.concurrent.atomic.AtomicLong callbackId = new java.util.concurrent.atomic.AtomicLong();
    
    private static LongMap<ParseListener> parseListeners = new LongMap<>();
    private static final java.lang.reflect.Method cbParseProperty;
    private static final java.lang.reflect.Method cbParsePackets;
    
    static {
        try {
            cbParseProperty = AudioFileStream.class.getDeclaredMethod("cbParseProperty", Long.TYPE, AudioFileStream.class, AudioFileStreamProperty.class, AudioFileStreamMutablePropertyFlags.class);
            cbParsePackets = AudioFileStream.class.getDeclaredMethod("cbParsePackets", Long.TYPE, Integer.TYPE, Integer.TYPE, Long.TYPE, AudioStreamPacketDescription.class);
        } catch (Throwable e) {
            throw new Error(e);
        }
    }
    /*<bind>*/static { Bro.bind(AudioFileStream.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected AudioFileStream() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    private long cid;
    
    @Callback
    private static void cbParseProperty(@Pointer long clientData, AudioFileStream audioFileStream, AudioFileStreamProperty property, AudioFileStreamMutablePropertyFlags flags) {
        synchronized (parseListeners) {
            parseListeners.get(clientData).onPropertyParsed(audioFileStream, property, flags);
        }
    }
    @Callback
    private static void cbParsePackets(@Pointer long clientData, int numberBytes, int numberPackets, @Pointer long inputData, AudioStreamPacketDescription packetDescriptions) {
        synchronized (parseListeners) {
            parseListeners.get(clientData).onPacketsParsed(numberBytes, inputData, packetDescriptions.toArray(numberPackets));
        }
    }
    
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public static AudioFileStream open(ParseListener parseListener, AudioFileType fileTypeHint) throws OSStatusException {
        if (parseListener == null) {
            throw new NullPointerException("parseListener");
        }
        long cid = callbackId.getAndIncrement();
        
        AudioFileStream.AudioFileStreamPtr ptr = new AudioFileStream.AudioFileStreamPtr();
        OSStatus status = open0(cid, new FunctionPtr(cbParseProperty), new FunctionPtr(cbParsePackets), fileTypeHint, ptr);
        if (OSStatusException.throwIfNecessary(status)) {
            synchronized (parseListeners) {
                parseListeners.put(cid, parseListener);
            }
        }
        AudioFileStream result = ptr.get();
        result.cid = cid;
        return result;
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public void parseBytes(byte[] bytes, AudioFileStreamParseFlags flags) throws OSStatusException {
        OSStatus status = parseBytes0(bytes.length, VM.getArrayValuesAddress(bytes), flags);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public long seek(long packetOffset, AudioFileStreamMutableSeekFlags flags) throws OSStatusException {
        LongPtr ptr = new LongPtr();
        OSStatus status = seek0(packetOffset, ptr, flags);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public int getPropertySize(AudioFileStreamProperty id) throws OSStatusException {
        IntPtr ptr = new IntPtr();
        OSStatus status = getPropertyInfo0(id, ptr, null);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public boolean isPropertyWritable(AudioFileStreamProperty id) throws OSStatusException {
        BooleanPtr ptr = new BooleanPtr();
        OSStatus status = getPropertyInfo0(id, null, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public <T extends Struct<T>> T getProperty(AudioFileStreamProperty id, Class<T> type) throws OSStatusException {
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
    public <T extends Struct<T>> void setProperty(AudioFileStreamProperty id, T data) throws OSStatusException {
        OSStatus status = setProperty0(id, data == null ? 0 : Struct.sizeOf(data), data == null ? null : data.as(VoidPtr.class));
        OSStatusException.throwIfNecessary(status);
    }
    public int getPropertyAsInt(AudioFileStreamProperty id) throws OSStatusException {
        IntPtr ptr = getProperty(id, IntPtr.class);
        return ptr.get();
    }
    public long getPropertyAsLong(AudioFileStreamProperty id) throws OSStatusException {
        LongPtr ptr = getProperty(id, LongPtr.class);
        return ptr.get();
    }
    public float getPropertyAsFloat(AudioFileStreamProperty id) throws OSStatusException {
        FloatPtr ptr = getProperty(id, FloatPtr.class);
        return ptr.get();
    }
    public double getPropertyAsDouble(AudioFileStreamProperty id) throws OSStatusException {
        DoublePtr ptr = getProperty(id, DoublePtr.class);
        return ptr.get();
    }
    public void setProperty(AudioFileStreamProperty id, int value) throws OSStatusException {
        setProperty(id, new IntPtr(value));
    }
    public void setProperty(AudioFileStreamProperty id, long value) throws OSStatusException {
        setProperty(id, new LongPtr(value));
    }
    public void setProperty(AudioFileStreamProperty id, float value) throws OSStatusException {
        setProperty(id, new FloatPtr(value));
    }
    public void setProperty(AudioFileStreamProperty id, double value) throws OSStatusException {
        setProperty(id, new DoublePtr(value));
    }
    
    /* Convenience methods for getting/setting properties */
    public AudioStreamBasicDescription getDataFormat() throws OSStatusException {
        return getProperty(AudioFileStreamProperty.DataFormat, AudioStreamBasicDescription.class);
    }
    /* End: Convenience methods for getting/setting properties */
    
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public void close() throws OSStatusException {
        OSStatus status = close0();
        OSStatusException.throwIfNecessary(status);
        
        // Remove parse listener.
        synchronized (parseListeners) {
            parseListeners.remove(this.cid);
        }
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileStreamOpen", optional=true)
    protected static native OSStatus open0(@Pointer long inClientData, FunctionPtr inPropertyListenerProc, FunctionPtr inPacketsProc, AudioFileType inFileTypeHint, AudioFileStream.AudioFileStreamPtr outAudioFileStream);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileStreamParseBytes", optional=true)
    protected native OSStatus parseBytes0(int inDataByteSize, @Pointer long inData, AudioFileStreamParseFlags inFlags);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileStreamSeek", optional=true)
    protected native OSStatus seek0(long inPacketOffset, LongPtr outDataByteOffset, AudioFileStreamMutableSeekFlags ioFlags);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileStreamGetPropertyInfo", optional=true)
    protected native OSStatus getPropertyInfo0(AudioFileStreamProperty inPropertyID, IntPtr outPropertyDataSize, BooleanPtr outWritable);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileStreamGetProperty", optional=true)
    protected native OSStatus getProperty0(AudioFileStreamProperty inPropertyID, IntPtr ioPropertyDataSize, VoidPtr outPropertyData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileStreamSetProperty", optional=true)
    protected native OSStatus setProperty0(AudioFileStreamProperty inPropertyID, int inPropertyDataSize, VoidPtr inPropertyData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileStreamClose", optional=true)
    protected native OSStatus close0();
    /*</methods>*/
}
