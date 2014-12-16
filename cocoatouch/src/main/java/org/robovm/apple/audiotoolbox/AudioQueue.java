/*
 * Copyright (C) 2014 Trillian Mobile AB
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
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("AudioToolbox")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AudioQueue/*</name>*/ 
    extends /*<extends>*/NativeObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AudioQueuePtr extends Ptr<AudioQueue, AudioQueuePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(AudioQueue.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected AudioQueue() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioQueueNewOutput", optional=true)
    public static native AudioQueueError newOutput(AudioStreamBasicDescription inFormat, FunctionPtr inCallbackProc, VoidPtr inUserData, CFRunLoop inCallbackRunLoop, CFString inCallbackRunLoopMode, int inFlags, AudioQueue.AudioQueuePtr outAQ);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioQueueNewInput", optional=true)
    public static native AudioQueueError newInput(AudioStreamBasicDescription inFormat, FunctionPtr inCallbackProc, VoidPtr inUserData, CFRunLoop inCallbackRunLoop, CFString inCallbackRunLoopMode, int inFlags, AudioQueue.AudioQueuePtr outAQ);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioQueueAllocateBuffer", optional=true)
    public native AudioQueueError allocateBuffer(int inBufferByteSize, AudioQueueBuffer.AudioQueueBufferPtr outBuffer);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioQueueAllocateBufferWithPacketDescriptions", optional=true)
    public native AudioQueueError allocateBufferWithPacketDescriptions(int inBufferByteSize, int inNumberPacketDescriptions, AudioQueueBuffer.AudioQueueBufferPtr outBuffer);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioQueueFreeBuffer", optional=true)
    public native AudioQueueError freeBuffer(AudioQueueBuffer inBuffer);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioQueueEnqueueBuffer", optional=true)
    public native AudioQueueError enqueueBuffer(AudioQueueBuffer inBuffer, int inNumPacketDescs, AudioStreamPacketDescription inPacketDescs);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioQueueEnqueueBufferWithParameters", optional=true)
    public native AudioQueueError enqueueBufferWithParameters(AudioQueueBuffer inBuffer, int inNumPacketDescs, AudioStreamPacketDescription inPacketDescs, int inTrimFramesAtStart, int inTrimFramesAtEnd, int inNumParamValues, AudioQueueParameterEvent inParamValues, AudioTimeStamp inStartTime, AudioTimeStamp outActualStartTime);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioQueueStart", optional=true)
    public native AudioQueueError start(AudioTimeStamp inStartTime);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioQueuePrime", optional=true)
    public native AudioQueueError prime(int inNumberOfFramesToPrepare, IntPtr outNumberOfFramesPrepared);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioQueueStop", optional=true)
    public native AudioQueueError stop(boolean inImmediate);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioQueuePause", optional=true)
    public native AudioQueueError pause();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioQueueFlush", optional=true)
    public native AudioQueueError flush();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioQueueReset", optional=true)
    public native AudioQueueError reset();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioQueueGetParameter", optional=true)
    public native AudioQueueError getParameter(int inParamID, FloatPtr outValue);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioQueueSetParameter", optional=true)
    public native AudioQueueError setParameter(int inParamID, float inValue);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioQueueGetProperty", optional=true)
    public native AudioQueueError getProperty(int inID, VoidPtr outData, IntPtr ioDataSize);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioQueueSetProperty", optional=true)
    public native AudioQueueError setProperty(int inID, VoidPtr inData, int inDataSize);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioQueueGetPropertySize", optional=true)
    public native AudioQueueError getPropertySize(int inID, IntPtr outDataSize);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioQueueAddPropertyListener", optional=true)
    public native AudioQueueError addPropertyListener(int inID, FunctionPtr inProc, VoidPtr inUserData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioQueueRemovePropertyListener", optional=true)
    public native AudioQueueError removePropertyListener(int inID, FunctionPtr inProc, VoidPtr inUserData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioQueueCreateTimeline", optional=true)
    public native AudioQueueError createTimeline(AudioQueueTimeline.AudioQueueTimelinePtr outTimeline);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioQueueDisposeTimeline", optional=true)
    public native AudioQueueError disposeTimeline(AudioQueueTimeline inTimeline);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioQueueGetCurrentTime", optional=true)
    public native AudioQueueError getCurrentTime(AudioQueueTimeline inTimeline, AudioTimeStamp outTimeStamp, BytePtr outTimelineDiscontinuity);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioQueueDeviceGetCurrentTime", optional=true)
    public native AudioQueueError deviceGetCurrentTime(AudioTimeStamp outTimeStamp);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioQueueDeviceTranslateTime", optional=true)
    public native AudioQueueError deviceTranslateTime(AudioTimeStamp inTime, AudioTimeStamp outTime);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioQueueDeviceGetNearestStartTime", optional=true)
    public native AudioQueueError deviceGetNearestStartTime(AudioTimeStamp ioRequestedStartTime, int inFlags);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioQueueSetOfflineRenderFormat", optional=true)
    public native AudioQueueError setOfflineRenderFormat(AudioStreamBasicDescription inFormat, AudioChannelLayout inLayout);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioQueueOfflineRender", optional=true)
    public native AudioQueueError offlineRender(AudioTimeStamp inTimestamp, AudioQueueBuffer ioBuffer, int inNumberFrames);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="AudioQueueProcessingTapNew", optional=true)
    public native AudioQueueError processingTapNew(FunctionPtr inCallback, VoidPtr inClientData, int inFlags, IntPtr outMaxFrames, AudioStreamBasicDescription outProcessingFormat, AudioQueueProcessingTap.AudioQueueProcessingTapPtr outAQTap);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="AudioQueueProcessingTapDispose", optional=true)
    public static native AudioQueueError processingTapDispose(AudioQueueProcessingTap inAQTap);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="AudioQueueProcessingTapGetSourceAudio", optional=true)
    public static native AudioQueueError processingTapGetSourceAudio(AudioQueueProcessingTap inAQTap, int inNumberFrames, AudioTimeStamp ioTimeStamp, IntPtr outFlags, IntPtr outNumberFrames, AudioBufferList ioData);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="AudioQueueProcessingTapGetQueueTime", optional=true)
    public static native AudioQueueError processingTapGetQueueTime(AudioQueueProcessingTap inAQTap, DoublePtr outQueueSampleTime, IntPtr outQueueFrameCount);
    /*</methods>*/
}
