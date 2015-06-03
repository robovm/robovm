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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AudioFile/*</name>*/ 
    extends /*<extends>*/NativeObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public interface Callbacks {
        int read(long position, int requestCount, ByteBuffer buffer) throws OSStatusException;
        
        int write(long position, int requestCount, ByteBuffer buffer) throws OSStatusException;
        
        long getSize();
        
        void setSize(long size);
    }
    
    /*<ptr>*/public static class AudioFilePtr extends Ptr<AudioFile, AudioFilePtr> {}/*</ptr>*/
    
    private static java.util.concurrent.atomic.AtomicLong callbackId = new java.util.concurrent.atomic.AtomicLong();
    
    private static final LongMap<Callbacks> callbacks = new LongMap<>();
    private static final java.lang.reflect.Method cbRead;
    private static final java.lang.reflect.Method cbWrite;
    private static final java.lang.reflect.Method cbGetSize;
    private static final java.lang.reflect.Method cbSetSize;
    
    static {
        try {
            cbRead = AudioFile.class.getDeclaredMethod("cbRead", Long.TYPE, Long.TYPE, Integer.TYPE, Long.TYPE, IntPtr.class);
            cbWrite = AudioFile.class.getDeclaredMethod("cbWrite", Long.TYPE, Long.TYPE, Integer.TYPE, Long.TYPE, IntPtr.class);
            cbGetSize = AudioFile.class.getDeclaredMethod("cbGetSize", Long.TYPE);
            cbSetSize = AudioFile.class.getDeclaredMethod("cbSetSize", Long.TYPE, Long.TYPE);
        } catch (Throwable e) {
            throw new Error(e);
        }
    }
    
    /*<bind>*/static { Bro.bind(AudioFile.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected AudioFile() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    @Callback
    private static final OSStatus cbRead(@Pointer long clientData, long position, int requestCount, @Pointer long buffer, IntPtr actualCount) {
        synchronized (callbacks) {
            try {
                int actualRead = callbacks.get(clientData).read(position, requestCount, VM.newDirectByteBuffer(buffer, requestCount));
                actualCount.set(actualRead);
            } catch (OSStatusException e) {
                return e.getStatus();
            }
            return OSStatus.NO_ERR;
        }
    }
    @Callback
    private static final OSStatus cbWrite(@Pointer long clientData, long position, int requestCount, @Pointer long buffer, IntPtr actualCount) {
        synchronized (callbacks) {
            try {
                int actualWrite = callbacks.get(clientData).write(position, requestCount, VM.newDirectByteBuffer(buffer, requestCount));
                actualCount.set(actualWrite);
            } catch (OSStatusException e) {
                return e.getStatus();
            }
            return OSStatus.NO_ERR;
        }
    }
    @Callback
    private static final long cbGetSize(@Pointer long clientData) {
        synchronized (callbacks) {
            return callbacks.get(clientData).getSize();
        }
    }
    @Callback
    private static final OSStatus cbSetSize(@Pointer long clientData, long size) {
        synchronized (callbacks) {
            callbacks.get(clientData).setSize(size);
            return OSStatus.NO_ERR;
        }
    }
    
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public static AudioFile create(NSURL fileRef, AudioFileType fileType, AudioStreamBasicDescription format, AudioFileFlags flags) throws OSStatusException {
        AudioFile.AudioFilePtr ptr = new AudioFile.AudioFilePtr();
        OSStatus status = create0(fileRef, fileType, format, flags, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public static AudioFile openURL(NSURL fileRef, byte permissions, AudioFileType fileTypeHint) throws OSStatusException {
        AudioFile.AudioFilePtr ptr = new AudioFile.AudioFilePtr();
        OSStatus status = openURL0(fileRef, permissions, fileTypeHint, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public static AudioFile initialize(Callbacks callback, AudioFileType fileType, AudioStreamBasicDescription format, AudioFileFlags flags) throws OSStatusException {
        if (callback == null) {
            throw new NullPointerException("callbacks");
        }
        AudioFile.AudioFilePtr ptr = new AudioFile.AudioFilePtr();
        long cid = callbackId.getAndIncrement();

        OSStatus status = initialize0(cid, new FunctionPtr(cbRead), new FunctionPtr(cbWrite), new FunctionPtr(cbGetSize), new FunctionPtr(cbSetSize), fileType, format, flags, ptr);
        if (OSStatusException.throwIfNecessary(status)) {
            synchronized (callbacks) {
                callbacks.put(cid, callback);
            }
            return ptr.get();
        }
        return null;
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public static AudioFile open(Callbacks callback, AudioFileType fileTypeHint) throws OSStatusException {
        if (callback == null) {
            throw new NullPointerException("callbacks");
        }
        AudioFile.AudioFilePtr ptr = new AudioFile.AudioFilePtr();
        long cid = callbackId.getAndIncrement();

        OSStatus status = open0(cid, new FunctionPtr(cbRead), new FunctionPtr(cbWrite), new FunctionPtr(cbGetSize), new FunctionPtr(cbSetSize), fileTypeHint, ptr);
        if (OSStatusException.throwIfNecessary(status)) {
            synchronized (callbacks) {
                callbacks.put(cid, callback);
            }
            return ptr.get();
        }
        return null;
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public void closeFile() throws OSStatusException {
        OSStatus status = closeFile0();
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public void optimize() throws OSStatusException {
        OSStatus status = optimize0();
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public byte[] readBytes(boolean useCache, long startingByte, int bytesToRead) throws OSStatusException {
        IntPtr numBytesPtr = new IntPtr();
        BytePtr ptr = new BytePtr();
        OSStatus status = readBytes0(useCache, startingByte, numBytesPtr, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.toByteArray(numBytesPtr.get());
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public int writeBytes(boolean useCache, long startingByte, byte[] buffer) throws OSStatusException {
        IntPtr ptr = new IntPtr(buffer.length);
        OSStatus status = writeBytes0(useCache, startingByte, ptr, VM.getArrayValuesAddress(buffer));
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public int countUserData(int userDataID) throws OSStatusException {
        IntPtr ptr = new IntPtr();
        OSStatus status = countUserData0(userDataID, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public int getUserDataSize(int userDataID, int index) throws OSStatusException {
        IntPtr ptr = new IntPtr();
        OSStatus status = getUserDataSize0(userDataID, index, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public <T extends Struct<T>> T getUserData(int userDataID, int index, Class<T> type) throws OSStatusException {
        T data = Struct.allocate(type);
        IntPtr dataSize = new IntPtr(Struct.sizeOf(data));
        OSStatus status = getUserData0(userDataID, index, dataSize, data.as(VoidPtr.class));
        OSStatusException.throwIfNecessary(status);
        return data;
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public void setUserData(int userDataID, int index, Struct<?> userData) throws OSStatusException {
        OSStatus status = setUserData0(userDataID, index, userData == null ? 0 : Struct.sizeOf(userData), userData == null ? null : userData.as(VoidPtr.class));
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public void removeUserData(int userDataID, int index) throws OSStatusException {
        OSStatus status = removeUserData0(userDataID, index);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public int getPropertySize(AudioFileProperty id) throws OSStatusException {
        IntPtr ptr = new IntPtr();
        OSStatus status = getPropertyInfo0(id, ptr, null);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public boolean isPropertyWritable(AudioFileProperty id) throws OSStatusException {
        IntPtr ptr = new IntPtr();
        OSStatus status = getPropertyInfo0(id, null, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get() != 0;
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public <T extends Struct<T>> T getProperty(AudioFileProperty id, Class<T> type) throws OSStatusException {
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
    public <T extends Struct<T>> void setProperty(AudioFileProperty id, T data) throws OSStatusException {
        OSStatus status = setProperty0(id, data == null ? 0 : Struct.sizeOf(data), data == null ? null : data.as(VoidPtr.class));
        OSStatusException.throwIfNecessary(status);
    }
    public int getPropertyAsInt(AudioFileProperty id) throws OSStatusException {
        IntPtr ptr = getProperty(id, IntPtr.class);
        return ptr.get();
    }
    public long getPropertyAsLong(AudioFileProperty id) throws OSStatusException {
        LongPtr ptr = getProperty(id, LongPtr.class);
        return ptr.get();
    }
    public float getPropertyAsFloat(AudioFileProperty id) throws OSStatusException {
        FloatPtr ptr = getProperty(id, FloatPtr.class);
        return ptr.get();
    }
    public double getPropertyAsDouble(AudioFileProperty id) throws OSStatusException {
        DoublePtr ptr = getProperty(id, DoublePtr.class);
        return ptr.get();
    }
    public void setProperty(AudioFileProperty id, int value) throws OSStatusException {
        setProperty(id, new IntPtr(value));
    }
    public void setProperty(AudioFileProperty id, long value) throws OSStatusException {
        setProperty(id, new LongPtr(value));
    }
    public void setProperty(AudioFileProperty id, float value) throws OSStatusException {
        setProperty(id, new FloatPtr(value));
    }
    public void setProperty(AudioFileProperty id, double value) throws OSStatusException {
        setProperty(id, new DoublePtr(value));
    }
    
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public static int getGlobalInfoSize(AudioFileProperty id, Struct<?> specifier) throws OSStatusException {
        IntPtr ptr = new IntPtr();
        OSStatus status = getGlobalInfoSize0(id, specifier == null ? 0 : Struct.sizeOf(specifier), specifier == null ? null : specifier.as(VoidPtr.class), ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public static <T extends Struct<T>> T getGlobalInfo(AudioFileProperty id, Struct<?> specifier, Class<T> type) throws OSStatusException {
        T data = Struct.allocate(type);
        IntPtr dataSize = new IntPtr(Struct.sizeOf(data));
        OSStatus status = getGlobalInfo0(id, specifier == null ? 0 : Struct.sizeOf(specifier), specifier == null ? null : specifier.as(VoidPtr.class), dataSize, data.as(VoidPtr.class));
        OSStatusException.throwIfNecessary(status);
        return data;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileCreateWithURL", optional=true)
    protected static native OSStatus create0(NSURL inFileRef, AudioFileType inFileType, AudioStreamBasicDescription inFormat, AudioFileFlags inFlags, AudioFile.AudioFilePtr outAudioFile);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileOpenURL", optional=true)
    protected static native OSStatus openURL0(NSURL inFileRef, byte inPermissions, AudioFileType inFileTypeHint, AudioFile.AudioFilePtr outAudioFile);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileInitializeWithCallbacks", optional=true)
    protected static native OSStatus initialize0(@Pointer long inClientData, FunctionPtr inReadFunc, FunctionPtr inWriteFunc, FunctionPtr inGetSizeFunc, FunctionPtr inSetSizeFunc, AudioFileType inFileType, AudioStreamBasicDescription inFormat, AudioFileFlags inFlags, AudioFile.AudioFilePtr outAudioFile);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileOpenWithCallbacks", optional=true)
    protected static native OSStatus open0(@Pointer long inClientData, FunctionPtr inReadFunc, FunctionPtr inWriteFunc, FunctionPtr inGetSizeFunc, FunctionPtr inSetSizeFunc, AudioFileType inFileTypeHint, AudioFile.AudioFilePtr outAudioFile);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileClose", optional=true)
    protected native OSStatus closeFile0();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileOptimize", optional=true)
    protected native OSStatus optimize0();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileReadBytes", optional=true)
    protected native OSStatus readBytes0(boolean inUseCache, long inStartingByte, IntPtr ioNumBytes, BytePtr outBuffer);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileWriteBytes", optional=true)
    protected native OSStatus writeBytes0(boolean inUseCache, long inStartingByte, IntPtr ioNumBytes, @Pointer long inBuffer);
    /**
     * @since Available in iOS 2.2 and later.
     */
    @Bridge(symbol="AudioFileReadPacketData", optional=true)
    protected native OSStatus readPacketData0(boolean inUseCache, IntPtr ioNumBytes, AudioStreamPacketDescription outPacketDescriptions, long inStartingPacket, IntPtr ioNumPackets, VoidPtr outBuffer);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Bridge(symbol="AudioFileReadPackets", optional=true)
    protected native OSStatus readPackets0(boolean inUseCache, IntPtr outNumBytes, AudioStreamPacketDescription outPacketDescriptions, long inStartingPacket, IntPtr ioNumPackets, VoidPtr outBuffer);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileWritePackets", optional=true)
    protected native OSStatus writePackets0(boolean inUseCache, int inNumBytes, AudioStreamPacketDescription inPacketDescriptions, long inStartingPacket, IntPtr ioNumPackets, VoidPtr inBuffer);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileCountUserData", optional=true)
    protected native OSStatus countUserData0(int inUserDataID, IntPtr outNumberItems);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileGetUserDataSize", optional=true)
    protected native OSStatus getUserDataSize0(int inUserDataID, int inIndex, IntPtr outUserDataSize);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileGetUserData", optional=true)
    protected native OSStatus getUserData0(int inUserDataID, int inIndex, IntPtr ioUserDataSize, VoidPtr outUserData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileSetUserData", optional=true)
    protected native OSStatus setUserData0(int inUserDataID, int inIndex, int inUserDataSize, VoidPtr inUserData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileRemoveUserData", optional=true)
    protected native OSStatus removeUserData0(int inUserDataID, int inIndex);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileGetPropertyInfo", optional=true)
    protected native OSStatus getPropertyInfo0(AudioFileProperty inPropertyID, IntPtr outDataSize, IntPtr isWritable);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileGetProperty", optional=true)
    protected native OSStatus getProperty0(AudioFileProperty inPropertyID, IntPtr ioDataSize, VoidPtr outPropertyData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileSetProperty", optional=true)
    protected native OSStatus setProperty0(AudioFileProperty inPropertyID, int inDataSize, VoidPtr inPropertyData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileGetGlobalInfoSize", optional=true)
    protected static native OSStatus getGlobalInfoSize0(AudioFileProperty inPropertyID, int inSpecifierSize, VoidPtr inSpecifier, IntPtr outDataSize);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileGetGlobalInfo", optional=true)
    protected static native OSStatus getGlobalInfo0(AudioFileProperty inPropertyID, int inSpecifierSize, VoidPtr inSpecifier, IntPtr ioDataSize, VoidPtr outPropertyData);
    /*</methods>*/
}
