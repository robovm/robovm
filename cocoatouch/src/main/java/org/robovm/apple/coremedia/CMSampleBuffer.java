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
package org.robovm.apple.coremedia;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreMedia")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMSampleBuffer/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/implements CMBuffer {

    public static class Notifications {
        /**
         * @since Available in iOS 4.0 and later.
         */
        public static NSObject observeDataBecameReady(CMSampleBuffer object, final VoidBlock1<CMSampleBuffer> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(DataBecameReadyNotification(), object.as(NSObject.class), NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke(a.getObject().as(CMSampleBuffer.class));
                }
            });
        }
        /**
         * @since Available in iOS 8.0 and later.
         */
        public static NSObject observeDataFailed(CMSampleBuffer object, final VoidBlock2<CMSampleBuffer, OSStatus> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(DataFailedNotification(), object.as(NSObject.class), NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    NSDictionary<NSString, NSObject> data = a.getUserInfo();
                    NSNumber val = (NSNumber) data.get(OSStatusNotificationParameter());
                    OSStatus error = val != null ? OSStatus.valueOf((int)val.longValue()) : null;
                    block.invoke(a.getObject().as(CMSampleBuffer.class), error);
                }
            });
        }
        /**
         * @since Available in iOS 4.0 and later.
         */
        public static NSObject observeInhibitOutputUntil(CMSampleBuffer object, final VoidBlock2<CMSampleBuffer, Long> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(InhibitOutputUntilConduitNotification(), object.as(NSObject.class), NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    NSDictionary<NSString, NSObject> data = a.getUserInfo();
                    NSNumber val = (NSNumber) data.get(ResumeTagConduitNotificationParameter());
                    long tag = val != null ? val.longValue() : 0;
                    block.invoke(a.getObject().as(CMSampleBuffer.class), tag);
                }
            });
        }
        /**
         * @since Available in iOS 4.0 and later.
         */
        public static NSObject observeResetOutput(CMSampleBuffer object, final VoidBlock1<CMSampleBuffer> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(ResetOutputConduitNotification(), object.as(NSObject.class), NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke(a.getObject().as(CMSampleBuffer.class));
                }
            });
        }
        /**
         * @since Available in iOS 4.3 and later.
         */
        public static NSObject observeUpcomingOutputPTSRangeChanged(CMSampleBuffer object, final VoidBlock4<CMSampleBuffer, Boolean, CMTime, CMTime> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(UpcomingOutputPTSRangeChangedConduitNotification(), object.as(NSObject.class), NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    NSDictionary<NSString, NSObject> data = a.getUserInfo();
                    NSNumber val1 = (NSNumber) data.get(UpcomingOutputPTSRangeMayOverlapQueuedOutputPTSRangeConduitNotificationParameter());
                    boolean mayOverlap = val1 != null ? val1.booleanValue() : false;
                    NSDictionary<NSString, NSObject> val2 = (NSDictionary<NSString, NSObject>) data.get(MinUpcomingOutputPTSConduitNotificationParameter());
                    CMTime min = val2 != null ? CMTime.create(val2) : null;
                    NSDictionary<NSString, NSObject> val3 = (NSDictionary<NSString, NSObject>) data.get(MaxUpcomingOutputPTSConduitNotificationParameter());
                    CMTime max = val3 != null ? CMTime.create(val3) : null;
                    block.invoke(a.getObject().as(CMSampleBuffer.class), mayOverlap, min, max);
                }
            });
        }
        /**
         * @since Available in iOS 4.0 and later.
         */
        public static NSObject observeBufferConsumed(CMSampleBuffer object, final VoidBlock2<CMSampleBuffer, NSDictionary<NSString, NSObject>> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(BufferConsumedConsumerNotification(), object.as(NSObject.class), NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke(a.getObject().as(CMSampleBuffer.class), a.getUserInfo());
                }
            });
        }
    }
    
    /*<ptr>*/public static class CMSampleBufferPtr extends Ptr<CMSampleBuffer, CMSampleBufferPtr> {}/*</ptr>*/
    
    public interface InvalidateCallback {
        void invalidate(CMSampleBuffer buffer);
    }
    public interface MakeDataReadyCallback {
        void makeDataReady(CMSampleBuffer buffer) throws OSStatusException;
    }
    public interface ForEachCallback {
        void forEach(CMSampleBuffer buffer, long index) throws OSStatusException;
    }
    
    private static java.util.concurrent.atomic.AtomicLong refconId = new java.util.concurrent.atomic.AtomicLong();
    private long localRefconId;
    private static final LongMap<InvalidateCallback> invalidateCallbacks = new LongMap<>();
    private static final LongMap<MakeDataReadyCallback> makeDataReadyCallbacks = new LongMap<>();
    private static final LongMap<ForEachCallback> forEachCallbacks = new LongMap<>();
    private static final java.lang.reflect.Method cbInvalidate;
    private static final java.lang.reflect.Method cbMakeDataReady;
    private static final java.lang.reflect.Method cbForEach;
    
    static {
        try {
            cbInvalidate = CMSampleBuffer.class.getDeclaredMethod("cbInvalidate", CMSampleBuffer.class, long.class);
            cbMakeDataReady = CMSampleBuffer.class.getDeclaredMethod("cbMakeDataReady", CMSampleBuffer.class, long.class);
            cbForEach = CMSampleBuffer.class.getDeclaredMethod("cbForEach", CMSampleBuffer.class, long.class, long.class);
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
    private static OSStatus cbMakeDataReady(CMSampleBuffer buffer, long refcon) {
        synchronized (makeDataReadyCallbacks) {
            try {
                makeDataReadyCallbacks.get(refcon).makeDataReady(buffer);
            } catch (OSStatusException e) {
                return e.getStatus();
            }
            return OSStatus.NO_ERR;
        }
    }
    @Callback
    private static OSStatus cbForEach(CMSampleBuffer buffer, long index, long refcon) {
        synchronized (forEachCallbacks) {
            try {
                forEachCallbacks.get(refcon).forEach(buffer, index);
            } catch (OSStatusException e) {
                return e.getStatus();
            }
            return OSStatus.NO_ERR;
        }
    }
    
    
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public static CMSampleBuffer create(CMBlockBuffer dataBuffer, boolean dataReady, MakeDataReadyCallback callback, CMFormatDescription formatDescription, @MachineSizedSInt long numSamples, CMSampleTimingInfo[] sampleTimingArray, long[] sampleSizeArray) throws OSStatusException {
        if (sampleTimingArray == null) {
            throw new NullPointerException("sampleTimingArray");
        }
        if (sampleSizeArray == null) {
            throw new NullPointerException("sampleSizeArray");
        }

        long refconId = CMSampleBuffer.refconId.getAndIncrement();
        
        CMSampleTimingInfo sampleTimingPtr = Struct.allocate(CMSampleTimingInfo.class, sampleTimingArray.length);
        sampleTimingPtr.update(sampleTimingArray);
        
        MachineSizedUIntPtr sampleSizePtr = Struct.allocate(MachineSizedUIntPtr.class, sampleSizeArray.length);
        sampleSizePtr.set(sampleSizeArray);
        
        CMSampleBufferPtr ptr = new CMSampleBufferPtr();
        OSStatus status = create0(null, dataBuffer, dataReady, new FunctionPtr(cbMakeDataReady), refconId, formatDescription, numSamples, sampleTimingArray.length, sampleTimingPtr, sampleSizeArray.length, sampleSizePtr, ptr);
        if (OSStatusException.throwIfNecessary(status)) {
            CMSampleBuffer buffer = ptr.get();
            buffer.localRefconId = refconId;
            synchronized (makeDataReadyCallbacks) {
                makeDataReadyCallbacks.put(refconId, callback);
            }
            return buffer;
        }
        return null;
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 8.0 and later.
     */
    public CMSampleBuffer createReady(CMBlockBuffer dataBuffer, CMFormatDescription formatDescription, @MachineSizedSInt long numSamples, CMSampleTimingInfo[] sampleTimingArray, long[] sampleSizeArray) throws OSStatusException {
        if (sampleTimingArray == null) {
            throw new NullPointerException("sampleTimingArray");
        }
        if (sampleSizeArray == null) {
            throw new NullPointerException("sampleSizeArray");
        }

        long refconId = CMSampleBuffer.refconId.getAndIncrement();
        
        CMSampleTimingInfo sampleTimingPtr = Struct.allocate(CMSampleTimingInfo.class, sampleTimingArray.length);
        sampleTimingPtr.update(sampleTimingArray);
        
        MachineSizedUIntPtr sampleSizePtr = Struct.allocate(MachineSizedUIntPtr.class, sampleSizeArray.length);
        sampleSizePtr.set(sampleSizeArray);
        
        CMSampleBufferPtr ptr = new CMSampleBufferPtr();
        OSStatus status = createReady0(null, dataBuffer, formatDescription, numSamples, sampleTimingArray.length, sampleTimingPtr, sampleSizeArray.length, sampleSizePtr, ptr);
        if (OSStatusException.throwIfNecessary(status)) {
            CMSampleBuffer buffer = ptr.get();
            buffer.localRefconId = refconId;
            return buffer;
        }
        return null;
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    @WeaklyLinked
    public static CMSampleBuffer createAudioSampleBuffer(CMBlockBuffer dataBuffer, boolean dataReady, MakeDataReadyCallback callback, CMFormatDescription formatDescription, @MachineSizedSInt long numSamples, @ByVal CMTime sbufPTS, AudioStreamPacketDescription[] packetDescriptions) throws OSStatusException {
        long refconId = CMSampleBuffer.refconId.getAndIncrement();
        
        AudioStreamPacketDescription packetDescriptionPtr = Struct.allocate(AudioStreamPacketDescription.class, (int)numSamples);
        packetDescriptionPtr.update(packetDescriptions);
        
        CMSampleBufferPtr ptr = new CMSampleBufferPtr();
        OSStatus status = createAudioSampleBuffer0(null, dataBuffer, dataReady, new FunctionPtr(cbMakeDataReady), refconId, formatDescription, numSamples, sbufPTS, packetDescriptionPtr, ptr);
        if (OSStatusException.throwIfNecessary(status)) {
            CMSampleBuffer buffer = ptr.get();
            buffer.localRefconId = refconId;
            synchronized (makeDataReadyCallbacks) {
                makeDataReadyCallbacks.put(refconId, callback);
            }
            return buffer;
        }
        return null;
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 8.0 and later.
     */
    @WeaklyLinked
    public static CMSampleBuffer createAudioSampleBuffer(CMBlockBuffer dataBuffer, CMFormatDescription formatDescription, @MachineSizedSInt long numSamples, @ByVal CMTime sbufPTS, AudioStreamPacketDescription[] packetDescriptions) throws OSStatusException {
        long refconId = CMSampleBuffer.refconId.getAndIncrement();
        
        AudioStreamPacketDescription packetDescriptionPtr = Struct.allocate(AudioStreamPacketDescription.class, (int)numSamples);
        packetDescriptionPtr.update(packetDescriptions);
        
        CMSampleBufferPtr ptr = new CMSampleBufferPtr();
        OSStatus status = createAudioSampleBuffer0(null, dataBuffer, formatDescription, numSamples, sbufPTS, packetDescriptionPtr, ptr);
        if (OSStatusException.throwIfNecessary(status)) {
            CMSampleBuffer buffer = ptr.get();
            buffer.localRefconId = refconId;
            return buffer;
        }
        return null;
    }

    /**
     * @throws OSStatusException 
     * @since Available in iOS 8.0 and later.
     */
    @WeaklyLinked
    public static CMSampleBuffer createReady(CVImageBuffer imageBuffer, CMVideoFormatDescription formatDescription, CMSampleTimingInfo sampleTiming) throws OSStatusException {
        long refconId = CMSampleBuffer.refconId.getAndIncrement();
        
        CMSampleBufferPtr ptr = new CMSampleBufferPtr();
        OSStatus status = createReadyWithImageBuffer0(null, imageBuffer, formatDescription, sampleTiming, ptr);
        if (OSStatusException.throwIfNecessary(status)) {
            CMSampleBuffer buffer = ptr.get();
            buffer.localRefconId = refconId;
            return buffer;
        }
        return null;
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    @WeaklyLinked
    public static CMSampleBuffer create(CVImageBuffer imageBuffer, boolean dataReady, MakeDataReadyCallback callback, CMVideoFormatDescription formatDescription, CMSampleTimingInfo sampleTiming) throws OSStatusException {
        long refconId = CMSampleBuffer.refconId.getAndIncrement();
        
        CMSampleBufferPtr ptr = new CMSampleBufferPtr();
        OSStatus status = createForImageBuffer0(null, imageBuffer, dataReady, new FunctionPtr(cbMakeDataReady), refconId, formatDescription, sampleTiming, ptr);
        if (OSStatusException.throwIfNecessary(status)) {
            CMSampleBuffer buffer = ptr.get();
            buffer.localRefconId = refconId;
            synchronized (makeDataReadyCallbacks) {
                makeDataReadyCallbacks.put(refconId, callback);
            }
            return buffer;
        }
        return null;
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public CMSampleBuffer createCopy() throws OSStatusException {
        CMSampleBufferPtr ptr = new CMSampleBufferPtr();
        OSStatus status = createCopy0(null, this, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public CMSampleBuffer createCopy(CMSampleTimingInfo[] sampleTimingArray) throws OSStatusException {
        CMSampleTimingInfo sampleTimingPtr = Struct.allocate(CMSampleTimingInfo.class, sampleTimingArray.length);
        sampleTimingPtr.update(sampleTimingArray);
        
        CMSampleBufferPtr ptr = new CMSampleBufferPtr();
        OSStatus status = createCopyWithNewTiming0(null, this, sampleTimingArray.length, sampleTimingPtr, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public CMSampleBuffer createCopy(@ByVal CFRange sampleRange) throws OSStatusException {
        CMSampleBufferPtr ptr = new CMSampleBufferPtr();
        OSStatus status = createForRange0(null, this, sampleRange, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public void setDataBuffer(CMBlockBuffer dataBuffer) throws OSStatusException {
        OSStatus status = setDataBuffer0(dataBuffer);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    @WeaklyLinked
    public void setAudioBufferList(AudioBufferList bufferList, CMSampleBufferFlags flags) throws OSStatusException {
        OSStatus status = setAudioBufferList0(null, null, flags, bufferList);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    @WeaklyLinked
    public AudioBufferList getAudioBufferList(@MachineSizedUInt long bufferListSize, CMSampleBufferFlags flags, CMBlockBuffer buffer) throws OSStatusException {
        CMBlockBuffer.CMBlockBufferPtr ptr = new CMBlockBuffer.CMBlockBufferPtr();
        ptr.set(buffer);
        AudioBufferList list = new AudioBufferList();
        OSStatus status = getAudioBufferList0(null, list, bufferListSize, null, null, flags, ptr);
        OSStatusException.throwIfNecessary(status);
        return list;
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    @WeaklyLinked
    public AudioStreamPacketDescription[] getAudioStreamPacketDescriptions(@MachineSizedUInt long packetDescriptionsSize) throws OSStatusException {
        AudioStreamPacketDescription description = new AudioStreamPacketDescription();
        OSStatus status = getAudioStreamPacketDescriptions0(packetDescriptionsSize, description, null);
        OSStatusException.throwIfNecessary(status);
        return description.toArray((int)packetDescriptionsSize);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public void copyPCMDataIntoAudioBufferList(int frameOffset, int numFrames, AudioBufferList bufferList) throws OSStatusException {
        OSStatus status = copyPCMDataIntoAudioBufferList0(frameOffset, numFrames, bufferList);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public void setDataReady() throws OSStatusException {
        OSStatus status = setDataReady0();
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 8.0 and later.
     */
    public void setDataFailed(OSStatus status) throws OSStatusException {
        OSStatus s = setDataFailed0(status);
        OSStatusException.throwIfNecessary(s);
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public OSStatus hasDataFailed() {
        IntPtr ptr = new IntPtr();
        boolean failed = hasDataFailed0(ptr);
        if (failed) {
            return OSStatus.valueOf(ptr.get());
        }
        return null;
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public void makeDataReady() throws OSStatusException {
        OSStatus status = makeDataReady0();
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public void trackDataReadiness(CMSampleBuffer sbufToTrack) throws OSStatusException {
        OSStatus status = trackDataReadiness0(sbufToTrack);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public void invalidate() throws OSStatusException {
        OSStatus status = invalidate0();
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public void setInvalidateCallback(InvalidateCallback callback) throws OSStatusException {
        long refconId = localRefconId;
        OSStatus status = setInvalidateCallback0(new FunctionPtr(cbInvalidate), refconId);
        if (OSStatusException.throwIfNecessary(status)) {
            synchronized (invalidateCallbacks) {
                invalidateCallbacks.put(refconId, callback);
            }
        }
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 8.0 and later.
     */
    public void setInvalidateHandler(VoidBlock1<CMSampleBuffer> invalidateHandler) throws OSStatusException {
        OSStatus status = setInvalidateHandler0(invalidateHandler);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public void setOutputPresentationTimeStamp(CMTime outputPresentationTimeStamp) throws OSStatusException {
        OSStatus status = setOutputPresentationTimeStamp0(outputPresentationTimeStamp);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public CMSampleTimingInfo[] getSampleTimingInfoArray(@MachineSizedSInt long timingArrayEntries) throws OSStatusException {
        CMSampleTimingInfo info = new CMSampleTimingInfo();
        OSStatus status = getSampleTimingInfoArray0(timingArrayEntries, info, null);
        OSStatusException.throwIfNecessary(status);
        return info.toArray((int)timingArrayEntries);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public CMSampleTimingInfo[] getOutputSampleTimingInfoArray(@MachineSizedSInt long timingArrayEntries) throws OSStatusException {
        CMSampleTimingInfo info = new CMSampleTimingInfo();
        OSStatus status = getOutputSampleTimingInfoArray0(timingArrayEntries, info, null);
        OSStatusException.throwIfNecessary(status);
        return info.toArray((int)timingArrayEntries);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public CMSampleTimingInfo getSampleTimingInfo(@MachineSizedSInt long sampleIndex) throws OSStatusException {
        CMSampleTimingInfo info = new CMSampleTimingInfo();
        OSStatus status = getSampleTimingInfo0(sampleIndex, info);
        OSStatusException.throwIfNecessary(status);
        return info;
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public long[] getSampleSizeArray(@MachineSizedSInt long sizeArrayEntries) throws OSStatusException {
        MachineSizedUIntPtr ptr = new MachineSizedUIntPtr();
        OSStatus status = getSampleSizeArray0(sizeArrayEntries, ptr, null);
        OSStatusException.throwIfNecessary(status);
        return ptr.toLongArray((int)sizeArrayEntries);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public void callForEachSample(ForEachCallback callback) throws OSStatusException {
        long refconId = localRefconId;
        OSStatus status = callForEachSample0(new FunctionPtr(cbForEach), refconId);
        if (OSStatusException.throwIfNecessary(status)) {
            synchronized (forEachCallbacks) {
                forEachCallbacks.put(refconId, callback);
            }
        }
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 8.0 and later.
     */
    public void callForEachSample(Block2<CMSampleBuffer, Long, OSStatus> handler) throws OSStatusException {
        OSStatus status = callForEachSample0(handler);
        OSStatusException.throwIfNecessary(status);
    }

    /**
     * @since Available in iOS 4.0 and later.
     */
    public void setSampleBufferAttachments(CMSampleBufferAttachment attachment, CMAttachmentMode attachmentMode) {
        CMAttachmentBearer.setAttachments(this, attachment.getDictionary(), attachmentMode);
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMSampleBufferAttachment getSampleBufferAttachments(CMAttachmentMode attachmentMode) {
        CFDictionary dict = CMAttachmentBearer.getAttachments(this, attachmentMode);
        if (dict != null) {
            return new CMSampleBufferAttachment(dict);
        }
        return null;
    }
    
    /**
     * @since Available in iOS 4.0 and later.
     */
    @WeaklyLinked
    @Bridge(symbol="CMSampleBufferGetImageBuffer", optional=true)
    public native CVPixelBuffer getPixelBuffer();
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferNotification_DataBecameReady", optional=true)
    public static native NSString DataBecameReadyNotification();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferNotification_DataFailed", optional=true)
    public static native NSString DataFailedNotification();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferNotificationParameter_OSStatus", optional=true)
    protected static native NSString OSStatusNotificationParameter();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferConduitNotification_InhibitOutputUntil", optional=true)
    public static native NSString InhibitOutputUntilConduitNotification();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferConduitNotificationParameter_ResumeTag", optional=true)
    protected static native NSString ResumeTagConduitNotificationParameter();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferConduitNotification_ResetOutput", optional=true)
    public static native NSString ResetOutputConduitNotification();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferConduitNotification_UpcomingOutputPTSRangeChanged", optional=true)
    public static native NSString UpcomingOutputPTSRangeChangedConduitNotification();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferConduitNotificationParameter_UpcomingOutputPTSRangeMayOverlapQueuedOutputPTSRange", optional=true)
    protected static native NSString UpcomingOutputPTSRangeMayOverlapQueuedOutputPTSRangeConduitNotificationParameter();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferConduitNotificationParameter_MinUpcomingOutputPTS", optional=true)
    protected static native NSString MinUpcomingOutputPTSConduitNotificationParameter();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferConduitNotificationParameter_MaxUpcomingOutputPTS", optional=true)
    protected static native NSString MaxUpcomingOutputPTSConduitNotificationParameter();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferConsumerNotification_BufferConsumed", optional=true)
    public static native NSString BufferConsumedConsumerNotification();
    
    /**
     * @since Available in iOS 4.0 and later.
     */
    @WeaklyLinked
    @Bridge(symbol="CMSampleBufferCreate", optional=true)
    protected static native OSStatus create0(CFAllocator allocator, CMBlockBuffer dataBuffer, boolean dataReady, FunctionPtr makeDataReadyCallback, @Pointer long makeDataReadyRefcon, CMFormatDescription formatDescription, @MachineSizedSInt long numSamples, @MachineSizedSInt long numSampleTimingEntries, CMSampleTimingInfo sampleTimingArray, @MachineSizedSInt long numSampleSizeEntries, MachineSizedUIntPtr sampleSizeArray, CMSampleBuffer.CMSampleBufferPtr sBufOut);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @WeaklyLinked
    @Bridge(symbol="CMSampleBufferCreateReady", optional=true)
    protected static native OSStatus createReady0(CFAllocator allocator, CMBlockBuffer dataBuffer, CMFormatDescription formatDescription, @MachineSizedSInt long numSamples, @MachineSizedSInt long numSampleTimingEntries, CMSampleTimingInfo sampleTimingArray, @MachineSizedSInt long numSampleSizeEntries, MachineSizedUIntPtr sampleSizeArray, CMSampleBuffer.CMSampleBufferPtr sBufOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @WeaklyLinked
    @Bridge(symbol="CMAudioSampleBufferCreateWithPacketDescriptions", optional=true)
    protected static native OSStatus createAudioSampleBuffer0(CFAllocator allocator, CMBlockBuffer dataBuffer, boolean dataReady, FunctionPtr makeDataReadyCallback, @Pointer long makeDataReadyRefcon, CMFormatDescription formatDescription, @MachineSizedSInt long numSamples, @ByVal CMTime sbufPTS, AudioStreamPacketDescription packetDescriptions, CMSampleBuffer.CMSampleBufferPtr sBufOut);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @WeaklyLinked
    @Bridge(symbol="CMAudioSampleBufferCreateReadyWithPacketDescriptions", optional=true)
    protected static native OSStatus createAudioSampleBuffer0(CFAllocator allocator, CMBlockBuffer dataBuffer, CMFormatDescription formatDescription, @MachineSizedSInt long numSamples, @ByVal CMTime sbufPTS, AudioStreamPacketDescription packetDescriptions, CMSampleBuffer.CMSampleBufferPtr sBufOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @WeaklyLinked
    @Bridge(symbol="CMSampleBufferCreateForImageBuffer", optional=true)
    protected static native OSStatus createForImageBuffer0(CFAllocator allocator, CVImageBuffer imageBuffer, boolean dataReady, FunctionPtr makeDataReadyCallback, @Pointer long makeDataReadyRefcon, CMVideoFormatDescription formatDescription, CMSampleTimingInfo sampleTiming, CMSampleBuffer.CMSampleBufferPtr sBufOut);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @WeaklyLinked
    @Bridge(symbol="CMSampleBufferCreateReadyWithImageBuffer", optional=true)
    protected static native OSStatus createReadyWithImageBuffer0(CFAllocator allocator, CVImageBuffer imageBuffer, CMVideoFormatDescription formatDescription, CMSampleTimingInfo sampleTiming, CMSampleBuffer.CMSampleBufferPtr sBufOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @WeaklyLinked
    @Bridge(symbol="CMSampleBufferCreateCopy", optional=true)
    protected static native OSStatus createCopy0(CFAllocator allocator, CMSampleBuffer sbuf, CMSampleBuffer.CMSampleBufferPtr sbufCopyOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @WeaklyLinked
    @Bridge(symbol="CMSampleBufferCreateCopyWithNewTiming", optional=true)
    protected static native OSStatus createCopyWithNewTiming0(CFAllocator allocator, CMSampleBuffer originalSBuf, @MachineSizedSInt long numSampleTimingEntries, CMSampleTimingInfo sampleTimingArray, CMSampleBuffer.CMSampleBufferPtr sBufCopyOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferCopySampleBufferForRange", optional=true)
    protected static native OSStatus createForRange0(CFAllocator allocator, CMSampleBuffer sbuf, @ByVal CFRange sampleRange, CMSampleBuffer.CMSampleBufferPtr sBufOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferSetDataBuffer", optional=true)
    protected native OSStatus setDataBuffer0(CMBlockBuffer dataBuffer);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferGetDataBuffer", optional=true)
    public native CMBlockBuffer getDataBuffer();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @WeaklyLinked
    @Bridge(symbol="CMSampleBufferGetImageBuffer", optional=true)
    public native CVImageBuffer getImageBuffer();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @WeaklyLinked
    @Bridge(symbol="CMSampleBufferSetDataBufferFromAudioBufferList", optional=true)
    protected native OSStatus setAudioBufferList0(CFAllocator bbufStructAllocator, CFAllocator bbufMemoryAllocator, CMSampleBufferFlags flags, AudioBufferList bufferList);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @WeaklyLinked
    @Bridge(symbol="CMSampleBufferGetAudioBufferListWithRetainedBlockBuffer", optional=true)
    protected native OSStatus getAudioBufferList0(MachineSizedUIntPtr bufferListSizeNeededOut, AudioBufferList bufferListOut, @MachineSizedUInt long bufferListSize, CFAllocator bbufStructAllocator, CFAllocator bbufMemoryAllocator, CMSampleBufferFlags flags, CMBlockBuffer.CMBlockBufferPtr blockBufferOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @WeaklyLinked
    @Bridge(symbol="CMSampleBufferGetAudioStreamPacketDescriptions", optional=true)
    protected native OSStatus getAudioStreamPacketDescriptions0(@MachineSizedUInt long packetDescriptionsSize, AudioStreamPacketDescription packetDescriptionsOut, MachineSizedUIntPtr packetDescriptionsSizeNeededOut);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    @Bridge(symbol="CMSampleBufferCopyPCMDataIntoAudioBufferList", optional=true)
    protected native OSStatus copyPCMDataIntoAudioBufferList0(int frameOffset, int numFrames, AudioBufferList bufferList);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferSetDataReady", optional=true)
    protected native OSStatus setDataReady0();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferDataIsReady", optional=true)
    public native boolean isDataReady();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMSampleBufferSetDataFailed", optional=true)
    protected native OSStatus setDataFailed0(OSStatus status);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMSampleBufferHasDataFailed", optional=true)
    protected native boolean hasDataFailed0(IntPtr statusOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferMakeDataReady", optional=true)
    protected native OSStatus makeDataReady0();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferTrackDataReadiness", optional=true)
    protected native OSStatus trackDataReadiness0(CMSampleBuffer sbufToTrack);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferInvalidate", optional=true)
    protected native OSStatus invalidate0();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferSetInvalidateCallback", optional=true)
    protected native OSStatus setInvalidateCallback0(FunctionPtr invalidateCallback, long invalidateRefCon);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMSampleBufferSetInvalidateHandler", optional=true)
    protected native OSStatus setInvalidateHandler0(@Block VoidBlock1<CMSampleBuffer> invalidateHandler);
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
    protected native OSStatus setOutputPresentationTimeStamp0(@ByVal CMTime outputPresentationTimeStamp);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferGetOutputDecodeTimeStamp", optional=true)
    public native @ByVal CMTime getOutputDecodeTimeStamp();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferGetSampleTimingInfoArray", optional=true)
    protected native OSStatus getSampleTimingInfoArray0(@MachineSizedSInt long timingArrayEntries, CMSampleTimingInfo timingArrayOut, MachineSizedSIntPtr timingArrayEntriesNeededOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferGetOutputSampleTimingInfoArray", optional=true)
    protected native OSStatus getOutputSampleTimingInfoArray0(@MachineSizedSInt long timingArrayEntries, CMSampleTimingInfo timingArrayOut, MachineSizedSIntPtr timingArrayEntriesNeededOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferGetSampleTimingInfo", optional=true)
    protected native OSStatus getSampleTimingInfo0(@MachineSizedSInt long sampleIndex, CMSampleTimingInfo timingInfoOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferGetSampleSizeArray", optional=true)
    protected native OSStatus getSampleSizeArray0(@MachineSizedSInt long sizeArrayEntries, MachineSizedUIntPtr sizeArrayOut, MachineSizedSIntPtr sizeArrayEntriesNeededOut);
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
    public native @org.robovm.rt.bro.annotation.Marshaler(CMSampleAttachment.AsListMarshaler.class) List<CMSampleAttachment> getSampleAttachments(boolean createIfNecessary);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMSampleBufferCallForEachSample", optional=true)
    protected native OSStatus callForEachSample0(FunctionPtr callback, @Pointer long refcon);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMSampleBufferCallBlockForEachSample", optional=true)
    protected native OSStatus callForEachSample0(@Block Block2<CMSampleBuffer, Long, OSStatus> handler);
    /*</methods>*/
}
