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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AudioQueueProcessingTap/*</name>*/ 
    extends /*<extends>*/NativeObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public interface ProcessingTapCallback {
        int process(AudioQueueProcessingTap aqTap, int numberFrames, AudioTimeStamp timeStamp, AudioQueueProcessingTapFlags flags, AudioBufferList data);
    }
    
    /*<ptr>*/public static class AudioQueueProcessingTapPtr extends Ptr<AudioQueueProcessingTap, AudioQueueProcessingTapPtr> {}/*</ptr>*/
    
    private static java.util.concurrent.atomic.AtomicLong callbackId = new java.util.concurrent.atomic.AtomicLong();
    
    private static final LongMap<ProcessingTapCallback> callbacks = new LongMap<>();
    private static final java.lang.reflect.Method cbProcess;
    
    static {
        try {
            cbProcess = AudioQueueProcessingTap.class.getDeclaredMethod("cbProcess", Long.TYPE, AudioQueueProcessingTap.class, Integer.class, AudioTimeStamp.class, 
                    AudioQueueProcessingTapFlags.class, IntPtr.class, AudioBufferList.class);
        } catch (Throwable e) {
            throw new Error(e);
        }
    }
    /*<bind>*/static { Bro.bind(AudioQueueProcessingTap.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected AudioQueueProcessingTap() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    @Callback
    private static void cbProcess(@Pointer long clientData, AudioQueueProcessingTap aqTap, int numberFrames, AudioTimeStamp timeStamp, AudioQueueProcessingTapFlags flags, IntPtr outNumberFrames, AudioBufferList data) {
        synchronized (callbacks) {
            int outFrames = callbacks.get(clientData).process(aqTap, numberFrames, timeStamp, flags, data);
            outNumberFrames.set(outFrames);
        }
    }
    
    private int maxProcessingFrames;
    private AudioStreamBasicDescription processingFormat;
    
    /**
     * @throws OSStatusException 
     * @since Available in iOS 6.0 and later.
     */
    protected static AudioQueueProcessingTap create(AudioQueue audioQueue, ProcessingTapCallback callback, AudioQueueProcessingTapFlags flags) throws OSStatusException {
        AudioQueueProcessingTap.AudioQueueProcessingTapPtr ptr = new AudioQueueProcessingTap.AudioQueueProcessingTapPtr();
        IntPtr maxFramesPtr = new IntPtr();
        AudioStreamBasicDescription.AudioStreamBasicDescriptionPtr processingFormatPtr = new AudioStreamBasicDescription.AudioStreamBasicDescriptionPtr();
        
        long cid = callbackId.getAndIncrement();
        
        OSStatus status = create0(audioQueue, new FunctionPtr(cbProcess), cid, flags, maxFramesPtr, processingFormatPtr, ptr);
        if (OSStatusException.throwIfNecessary(status)) {
            synchronized (callbacks) {
                callbacks.put(cid, callback);
            }
            
            AudioQueueProcessingTap result = ptr.get();
            result.maxProcessingFrames = maxFramesPtr.get();
            result.processingFormat = processingFormatPtr.get();
            return result;
        }
        return null;
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 6.0 and later.
     */
    public void dispose() throws OSStatusException {
        OSStatus status = dispose0();
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 6.0 and later.
     */
    public int getSourceAudio(int numberFrames, AudioTimeStamp timeStamp, AudioQueueProcessingTapMutableFlags sourceFlags, AudioBufferList data) throws OSStatusException {
        IntPtr sourceFramesPtr = new IntPtr();
        OSStatus status = getSourceAudio0(numberFrames, timeStamp, sourceFlags, sourceFramesPtr, data);
        OSStatusException.throwIfNecessary(status);
        return sourceFramesPtr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 6.0 and later.
     */
    public void getQueueTime(DoublePtr queueSampleTime, IntPtr queueFrameCount) throws OSStatusException {
        OSStatus status = getQueueTime0(queueSampleTime, queueFrameCount);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 6.0 and later.
     */
    public double getQueueSampleTime() throws OSStatusException {
        DoublePtr ptr = new DoublePtr();
        getQueueTime(ptr, null);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 6.0 and later.
     */
    public int getQueueFrameCount() throws OSStatusException {
        IntPtr ptr = new IntPtr();
        getQueueTime(null, ptr);
        return ptr.get();
    }
    
    public int getMaxProcessingFrames() {
        return maxProcessingFrames;
    }
    public AudioStreamBasicDescription getProcessingFormat() {
        return processingFormat;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="AudioQueueProcessingTapNew", optional=true)
    protected static native OSStatus create0(AudioQueue inAQ, FunctionPtr inCallback, @Pointer long inClientData, AudioQueueProcessingTapFlags inFlags, IntPtr outMaxFrames, AudioStreamBasicDescription.AudioStreamBasicDescriptionPtr outProcessingFormat, AudioQueueProcessingTap.AudioQueueProcessingTapPtr outAQTap);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="AudioQueueProcessingTapDispose", optional=true)
    protected native OSStatus dispose0();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="AudioQueueProcessingTapGetSourceAudio", optional=true)
    protected native OSStatus getSourceAudio0(int inNumberFrames, AudioTimeStamp ioTimeStamp, AudioQueueProcessingTapMutableFlags outFlags, IntPtr outNumberFrames, AudioBufferList ioData);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="AudioQueueProcessingTapGetQueueTime", optional=true)
    protected native OSStatus getQueueTime0(DoublePtr outQueueSampleTime, IntPtr outQueueFrameCount);
    /*</methods>*/
}
