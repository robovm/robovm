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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.audiounit.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.coremidi.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("AudioToolbox")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ExtAudioFile/*</name>*/ 
    extends /*<extends>*/NativeObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class ExtAudioFilePtr extends Ptr<ExtAudioFile, ExtAudioFilePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(ExtAudioFile.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected ExtAudioFile() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.1 and later.
     */
    public static ExtAudioFile openURL(NSURL url) throws OSStatusException {
        ExtAudioFile.ExtAudioFilePtr ptr = new ExtAudioFile.ExtAudioFilePtr();
        OSStatus status = openURL0(url, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.1 and later.
     */
    public static ExtAudioFile wrapAudioFile(AudioFile audioFile, boolean forWriting) throws OSStatusException {
        ExtAudioFile.ExtAudioFilePtr ptr = new ExtAudioFile.ExtAudioFilePtr();
        OSStatus status = wrapAudioFile0(audioFile, forWriting, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.1 and later.
     */
    public static ExtAudioFile create(NSURL url, AudioFileType fileType, AudioStreamBasicDescription streamDesc, AudioChannelLayout channelLayout, AudioFileFlags flags) throws OSStatusException {
        ExtAudioFile.ExtAudioFilePtr ptr = new ExtAudioFile.ExtAudioFilePtr();
        OSStatus status = create0(url, fileType, streamDesc, channelLayout, flags, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.1 and later.
     */
    public void dispose() throws OSStatusException {
        OSStatus status = dispose0();
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.1 and later.
     */
    public int read(int numberFrames, AudioBufferList data) throws OSStatusException {
        IntPtr ptr = new IntPtr(numberFrames);
        OSStatus status = read0(ptr, data);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.1 and later.
     */
    public void write(int numberFrames, AudioBufferList data) throws OSStatusException {
        OSStatus status = write0(numberFrames, data);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.1 and later.
     */
    public void writeAsync(int numberFrames, AudioBufferList data) throws OSStatusException {
        OSStatus status = writeAsync0(numberFrames, data);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.1 and later.
     */
    public void seek(long frameOffset) throws OSStatusException {
        OSStatus status = seek0(frameOffset);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.1 and later.
     */
    public long tell() throws OSStatusException {
        LongPtr ptr = new LongPtr();
        OSStatus status = tell0(ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.1 and later.
     */
    public int getPropertySize(ExtAudioFileProperty id) throws OSStatusException {
        IntPtr ptr = new IntPtr();
        OSStatus status = getPropertyInfo0(id, ptr, null);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.1 and later.
     */
    public boolean isPropertyWritable(ExtAudioFileProperty id) throws OSStatusException {
        BooleanPtr ptr = new BooleanPtr();
        OSStatus status = getPropertyInfo0(id, null, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.1 and later.
     */
    public <T extends Struct<T>> T getProperty(ExtAudioFileProperty id, Class<T> type) throws OSStatusException {
        T data = Struct.allocate(type);
        IntPtr dataSize = new IntPtr(Struct.sizeOf(data));
        OSStatus status = getProperty0(id, dataSize, data.as(VoidPtr.class));
        OSStatusException.throwIfNecessary(status);
        return data;
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.1 and later.
     */
    public <T extends Struct<T>> void setProperty(ExtAudioFileProperty id, T data) throws OSStatusException {
        OSStatus status = setProperty0(id, data == null ? 0 : Struct.sizeOf(data), data == null ? null : data.as(VoidPtr.class));
        OSStatusException.throwIfNecessary(status);
    }
    public int getPropertyAsInt(ExtAudioFileProperty id) throws OSStatusException {
        IntPtr ptr = getProperty(id, IntPtr.class);
        return ptr.get();
    }
    public long getPropertyAsLong(ExtAudioFileProperty id) throws OSStatusException {
        LongPtr ptr = getProperty(id, LongPtr.class);
        return ptr.get();
    }
    public float getPropertyAsFloat(ExtAudioFileProperty id) throws OSStatusException {
        FloatPtr ptr = getProperty(id, FloatPtr.class);
        return ptr.get();
    }
    public double getPropertyAsDouble(ExtAudioFileProperty id) throws OSStatusException {
        DoublePtr ptr = getProperty(id, DoublePtr.class);
        return ptr.get();
    }
    public void setProperty(ExtAudioFileProperty id, int value) throws OSStatusException {
        setProperty(id, new IntPtr(value));
    }
    public void setProperty(ExtAudioFileProperty id, long value) throws OSStatusException {
        setProperty(id, new LongPtr(value));
    }
    public void setProperty(ExtAudioFileProperty id, float value) throws OSStatusException {
        setProperty(id, new FloatPtr(value));
    }
    public void setProperty(ExtAudioFileProperty id, double value) throws OSStatusException {
        setProperty(id, new DoublePtr(value));
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.1 and later.
     */
    @Bridge(symbol="ExtAudioFileOpenURL", optional=true)
    protected static native OSStatus openURL0(NSURL inURL, ExtAudioFile.ExtAudioFilePtr outExtAudioFile);
    /**
     * @since Available in iOS 2.1 and later.
     */
    @Bridge(symbol="ExtAudioFileWrapAudioFileID", optional=true)
    protected static native OSStatus wrapAudioFile0(AudioFile inFileID, boolean inForWriting, ExtAudioFile.ExtAudioFilePtr outExtAudioFile);
    /**
     * @since Available in iOS 2.1 and later.
     */
    @Bridge(symbol="ExtAudioFileCreateWithURL", optional=true)
    protected static native OSStatus create0(NSURL inURL, AudioFileType inFileType, AudioStreamBasicDescription inStreamDesc, AudioChannelLayout inChannelLayout, AudioFileFlags inFlags, ExtAudioFile.ExtAudioFilePtr outExtAudioFile);
    /**
     * @since Available in iOS 2.1 and later.
     */
    @Bridge(symbol="ExtAudioFileDispose", optional=true)
    protected native OSStatus dispose0();
    /**
     * @since Available in iOS 2.1 and later.
     */
    @Bridge(symbol="ExtAudioFileRead", optional=true)
    protected native OSStatus read0(IntPtr ioNumberFrames, AudioBufferList ioData);
    /**
     * @since Available in iOS 2.1 and later.
     */
    @Bridge(symbol="ExtAudioFileWrite", optional=true)
    protected native OSStatus write0(int inNumberFrames, AudioBufferList ioData);
    /**
     * @since Available in iOS 2.1 and later.
     */
    @Bridge(symbol="ExtAudioFileWriteAsync", optional=true)
    protected native OSStatus writeAsync0(int inNumberFrames, AudioBufferList ioData);
    /**
     * @since Available in iOS 2.1 and later.
     */
    @Bridge(symbol="ExtAudioFileSeek", optional=true)
    protected native OSStatus seek0(long inFrameOffset);
    /**
     * @since Available in iOS 2.1 and later.
     */
    @Bridge(symbol="ExtAudioFileTell", optional=true)
    protected native OSStatus tell0(LongPtr outFrameOffset);
    /**
     * @since Available in iOS 2.1 and later.
     */
    @Bridge(symbol="ExtAudioFileGetPropertyInfo", optional=true)
    protected native OSStatus getPropertyInfo0(ExtAudioFileProperty inPropertyID, IntPtr outSize, BooleanPtr outWritable);
    /**
     * @since Available in iOS 2.1 and later.
     */
    @Bridge(symbol="ExtAudioFileGetProperty", optional=true)
    protected native OSStatus getProperty0(ExtAudioFileProperty inPropertyID, IntPtr ioPropertyDataSize, VoidPtr outPropertyData);
    /**
     * @since Available in iOS 2.1 and later.
     */
    @Bridge(symbol="ExtAudioFileSetProperty", optional=true)
    protected native OSStatus setProperty0(ExtAudioFileProperty inPropertyID, int inPropertyDataSize, VoidPtr inPropertyData);
    /*</methods>*/
}
