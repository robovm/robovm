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
package org.robovm.apple.coremedia;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corevideo.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreMedia")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMSampleBuffer/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/implements CMBuffer {

    /*<ptr>*/public static class CMSampleBufferPtr extends Ptr<CMSampleBuffer, CMSampleBufferPtr> {}/*</ptr>*/
    
    public interface InvalidateCallback {
        void invalidate(CMSampleBuffer buffer);
    }
    public interface MakeDataReadyCallback {
        CMSampleBufferError makeDataReady(CMSampleBuffer buffer);
    }
    
    private static java.util.concurrent.atomic.AtomicLong refconId = new java.util.concurrent.atomic.AtomicLong();
    private long localRefconId;
    private static Map<Long, InvalidateCallback> invalidateCallbacks = new HashMap<Long, InvalidateCallback>();
    private static Map<Long, MakeDataReadyCallback> makeDataReadyCallbacks = new HashMap<Long, MakeDataReadyCallback>();
    private static final java.lang.reflect.Method cbInvalidate;
    private static final java.lang.reflect.Method cbMakeDataReady;
    
    static {
        try {
            cbInvalidate = CMBufferQueue.class.getDeclaredMethod("cbInvalidate", CMSampleBuffer.class, long.class);
            cbMakeDataReady = CMBufferQueue.class.getDeclaredMethod("cbMakeDataReady", CMSampleBuffer.class, long.class);
        } catch (Throwable e) {
            throw new Error(e);
        }
    }
    /*<bind>*/static { Bro.bind(CMSampleBuffer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CMSampleBuffer() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    @Callback
    private static void cbInvalidate(CMSampleBuffer buffer, long refcon) {
        InvalidateCallback callback = null;
        synchronized (invalidateCallbacks) {
            callback = invalidateCallbacks.get(refcon);
        }
        callback.invalidate(buffer);
    }
    @Callback
    private static void cbMakeDataReady(CMSampleBuffer buffer, long refcon) {
        MakeDataReadyCallback callback = null;
        synchronized (makeDataReadyCallbacks) {
            callback = makeDataReadyCallbacks.get(refcon);
        }
        callback.makeDataReady(buffer);
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferCreate", optional=true)
    protected static native int create(CFAllocator allocator, CMBlockBuffer dataBuffer, boolean dataReady, FunctionPtr makeDataReadyCallback, VoidPtr makeDataReadyRefcon, CMFormatDescription formatDescription, @MachineSizedSInt long numSamples, @MachineSizedSInt long numSampleTimingEntries, CMSampleTimingInfo sampleTimingArray, @MachineSizedSInt long numSampleSizeEntries, MachineSizedUIntPtr sampleSizeArray, CMSampleBuffer.CMSampleBufferPtr sBufOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMAudioSampleBufferCreateWithPacketDescriptions", optional=true)
    protected static native int createAudioSampleBuffer(CFAllocator allocator, CMBlockBuffer dataBuffer, boolean dataReady, FunctionPtr makeDataReadyCallback, VoidPtr makeDataReadyRefcon, CMFormatDescription formatDescription, @MachineSizedSInt long numSamples, @ByVal CMTime sbufPTS, AudioStreamPacketDescription packetDescriptions, CMSampleBuffer.CMSampleBufferPtr sBufOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferCreateForImageBuffer", optional=true)
    protected static native int createForImageBuffer(CFAllocator allocator, CVImageBuffer imageBuffer, boolean dataReady, FunctionPtr makeDataReadyCallback, VoidPtr makeDataReadyRefcon, CMVideoFormatDescription formatDescription, CMSampleTimingInfo sampleTiming, CMSampleBuffer.CMSampleBufferPtr sBufOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferCreateCopy", optional=true)
    protected static native int createCopy(CFAllocator allocator, CMSampleBuffer sbuf, CMSampleBuffer.CMSampleBufferPtr sbufCopyOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferCreateCopyWithNewTiming", optional=true)
    protected static native int createCopyWithNewTiming(CFAllocator allocator, CMSampleBuffer originalSBuf, @MachineSizedSInt long numSampleTimingEntries, CMSampleTimingInfo sampleTimingArray, CMSampleBuffer.CMSampleBufferPtr sBufCopyOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferCopySampleBufferForRange", optional=true)
    protected static native int copySampleBuffer(CFAllocator allocator, CMSampleBuffer sbuf, @ByVal CFRange sampleRange, CMSampleBuffer.CMSampleBufferPtr sBufOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferSetDataBuffer", optional=true)
    public native int setDataBuffer(CMBlockBuffer dataBuffer);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferGetDataBuffer", optional=true)
    public native CMBlockBuffer getDataBuffer();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferGetImageBuffer", optional=true)
    public native CVImageBuffer getImageBuffer();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferSetDataBufferFromAudioBufferList", optional=true)
    protected native int setDataBuffer(CFAllocator bbufStructAllocator, CFAllocator bbufMemoryAllocator, int flags, AudioBufferList bufferList);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferGetAudioBufferListWithRetainedBlockBuffer", optional=true)
    protected native int getAudioBufferList(MachineSizedUIntPtr bufferListSizeNeededOut, AudioBufferList bufferListOut, @MachineSizedUInt long bufferListSize, CFAllocator bbufStructAllocator, CFAllocator bbufMemoryAllocator, int flags, CMBlockBuffer.CMBlockBufferPtr blockBufferOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferGetAudioStreamPacketDescriptions", optional=true)
    protected native int getAudioStreamPacketDescriptions(@MachineSizedUInt long packetDescriptionsSize, AudioStreamPacketDescription packetDescriptionsOut, MachineSizedUIntPtr packetDescriptionsSizeNeededOut);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CMSampleBufferCopyPCMDataIntoAudioBufferList", optional=true)
    public native CMSampleBufferError copyPCMDataIntoAudioBufferList(int frameOffset, int numFrames, AudioBufferList bufferList);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferSetDataReady", optional=true)
    public native CMSampleBufferError setDataReady();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferDataIsReady", optional=true)
    public native boolean isDataReady();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferMakeDataReady", optional=true)
    public native CMSampleBufferError makeDataReady();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferTrackDataReadiness", optional=true)
    public native CMSampleBufferError trackDataReadiness(CMSampleBuffer sbufToTrack);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferInvalidate", optional=true)
    public native CMSampleBufferError invalidate();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferSetInvalidateCallback", optional=true)
    protected native CMSampleBufferError setInvalidateCallback(FunctionPtr invalidateCallback, long invalidateRefCon);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferIsValid", optional=true)
    public native boolean isValid();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferGetNumSamples", optional=true)
    public native @MachineSizedSInt long getNumSamples();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferGetDuration", optional=true)
    public native @ByVal CMTime getDuration();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferGetPresentationTimeStamp", optional=true)
    public native @ByVal CMTime getPresentationTimeStamp();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferGetDecodeTimeStamp", optional=true)
    public native @ByVal CMTime getDecodeTimeStamp();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferGetOutputDuration", optional=true)
    public native @ByVal CMTime getOutputDuration();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferGetOutputPresentationTimeStamp", optional=true)
    public native @ByVal CMTime getOutputPresentationTimeStamp();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferSetOutputPresentationTimeStamp", optional=true)
    public native int setOutputPresentationTimeStamp(@ByVal CMTime outputPresentationTimeStamp);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferGetOutputDecodeTimeStamp", optional=true)
    public native @ByVal CMTime getOutputDecodeTimeStamp();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferGetSampleTimingInfoArray", optional=true)
    protected native CMSampleBufferError getSampleTimingInfoArray(@MachineSizedSInt long timingArrayEntries, CMSampleTimingInfo timingArrayOut, MachineSizedSIntPtr timingArrayEntriesNeededOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferGetOutputSampleTimingInfoArray", optional=true)
    protected native CMSampleBufferError getOutputSampleTimingInfoArray(@MachineSizedSInt long timingArrayEntries, CMSampleTimingInfo timingArrayOut, MachineSizedSIntPtr timingArrayEntriesNeededOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferGetSampleTimingInfo", optional=true)
    protected native CMSampleBufferError getSampleTimingInfo(@MachineSizedSInt long sampleIndex, CMSampleTimingInfo timingInfoOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferGetSampleSizeArray", optional=true)
    protected native CMSampleBufferError getSampleSizeArray(@MachineSizedSInt long sizeArrayEntries, MachineSizedUIntPtr sizeArrayOut, MachineSizedSIntPtr sizeArrayEntriesNeededOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferGetSampleSize", optional=true)
    public native @MachineSizedUInt long getSampleSize(@MachineSizedSInt long sampleIndex);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferGetTotalSampleSize", optional=true)
    public native @MachineSizedUInt long getTotalSampleSize();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferGetFormatDescription", optional=true)
    public native CMFormatDescription getFormatDescription();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferGetSampleAttachmentsArray", optional=true)
    public native NSArray<?> getSampleAttachmentsArray(boolean createIfNecessary);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferCallForEachSample", optional=true)
    public native CMSampleBufferError callForEachSample(FunctionPtr callback, VoidPtr refcon);
    /*</methods>*/
}
