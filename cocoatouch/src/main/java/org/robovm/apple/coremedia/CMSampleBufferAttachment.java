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
@Marshaler(/*<name>*/CMSampleBufferAttachment/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMSampleBufferAttachment/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CMSampleBufferAttachment toObject(Class<CMSampleBufferAttachment> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CMSampleBufferAttachment(o);
        }
        @MarshalsPointer
        public static long toNative(CMSampleBufferAttachment o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CMSampleBufferAttachment> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(CFArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CMSampleBufferAttachment> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CMSampleBufferAttachment(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CMSampleBufferAttachment> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CMSampleBufferAttachment i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    CMSampleBufferAttachment(CFDictionary data) {
        super(data);
    }
    public CMSampleBufferAttachment() {}
    /*</constructors>*/

    /*<methods>*/
    public boolean has(CMSampleBufferAttachmentKey key) {
        return data.containsKey(key.value());
    }
    public <T extends NativeObject> T get(CMSampleBufferAttachmentKey key, Class<T> type) {
        if (has(key)) {
            return data.get(key.value(), type);
        }
        return null;
    }
    public CMSampleBufferAttachment set(CMSampleBufferAttachmentKey key, NativeObject value) {
        data.put(key.value(), value);
        return this;
    }
    

    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean shouldResetDecoderBeforeDecoding() {
        if (has(CMSampleBufferAttachmentKey.ResetDecoderBeforeDecoding)) {
            CFBoolean val = get(CMSampleBufferAttachmentKey.ResetDecoderBeforeDecoding, CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMSampleBufferAttachment setShouldResetDecoderBeforeDecoding(boolean shouldResetDecoderBeforeDecoding) {
        set(CMSampleBufferAttachmentKey.ResetDecoderBeforeDecoding, CFBoolean.valueOf(shouldResetDecoderBeforeDecoding));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean shouldDrainAfterDecoding() {
        if (has(CMSampleBufferAttachmentKey.DrainAfterDecoding)) {
            CFBoolean val = get(CMSampleBufferAttachmentKey.DrainAfterDecoding, CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMSampleBufferAttachment setShouldDrainAfterDecoding(boolean shouldDrainAfterDecoding) {
        set(CMSampleBufferAttachmentKey.DrainAfterDecoding, CFBoolean.valueOf(shouldDrainAfterDecoding));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public Map<String, NSObject> getShouldPostNotificationWhenConsumed() {
        if (has(CMSampleBufferAttachmentKey.PostNotificationWhenConsumed)) {
            CFDictionary val = get(CMSampleBufferAttachmentKey.PostNotificationWhenConsumed, CFDictionary.class);
            NSDictionary dict = val.as(NSDictionary.class);
            return dict.asStringMap();
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMSampleBufferAttachment setShouldPostNotificationWhenConsumed(Map<String, NSObject> shouldPostNotificationWhenConsumed) {
        set(CMSampleBufferAttachmentKey.PostNotificationWhenConsumed, CFDictionary.fromStringMap(shouldPostNotificationWhenConsumed));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean shouldResumeOutput() {
        if (has(CMSampleBufferAttachmentKey.ResumeOutput)) {
            CFBoolean val = get(CMSampleBufferAttachmentKey.ResumeOutput, CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMSampleBufferAttachment setShouldResumeOutput(boolean shouldResumeOutput) {
        set(CMSampleBufferAttachmentKey.ResumeOutput, CFBoolean.valueOf(shouldResumeOutput));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public long getTransitionID() {
        if (has(CMSampleBufferAttachmentKey.TransitionID)) {
            CFNumber val = get(CMSampleBufferAttachmentKey.TransitionID, CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMSampleBufferAttachment setTransitionID(long transitionID) {
        set(CMSampleBufferAttachmentKey.TransitionID, CFNumber.valueOf(transitionID));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTime getTrimDurationAtStart() {
        if (has(CMSampleBufferAttachmentKey.TrimDurationAtStart)) {
            CFDictionary val = get(CMSampleBufferAttachmentKey.TrimDurationAtStart, CFDictionary.class);
            NSDictionary dict = val.as(NSDictionary.class);
            return CMTime.create(dict);
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMSampleBufferAttachment setTrimDurationAtStart(CMTime trimDurationAtStart) {
        set(CMSampleBufferAttachmentKey.TrimDurationAtStart, trimDurationAtStart.asDictionary(null).as(CFDictionary.class));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTime getTrimDurationAtEnd() {
        if (has(CMSampleBufferAttachmentKey.TrimDurationAtEnd)) {
            CFDictionary val = get(CMSampleBufferAttachmentKey.TrimDurationAtEnd, CFDictionary.class);
            NSDictionary dict = val.as(NSDictionary.class);
            return CMTime.create(dict);
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMSampleBufferAttachment setTrimDurationAtEnd(CMTime trimDurationAtEnd) {
        set(CMSampleBufferAttachmentKey.TrimDurationAtEnd, trimDurationAtEnd.asDictionary(null).as(CFDictionary.class));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public double getSpeedMultiplier() {
        if (has(CMSampleBufferAttachmentKey.SpeedMultiplier)) {
            CFNumber val = get(CMSampleBufferAttachmentKey.SpeedMultiplier, CFNumber.class);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMSampleBufferAttachment setSpeedMultiplier(double speedMultiplier) {
        set(CMSampleBufferAttachmentKey.SpeedMultiplier, CFNumber.valueOf(speedMultiplier));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean shouldRevers() {
        if (has(CMSampleBufferAttachmentKey.Reverse)) {
            CFBoolean val = get(CMSampleBufferAttachmentKey.Reverse, CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMSampleBufferAttachment setShouldRevers(boolean shouldRevers) {
        set(CMSampleBufferAttachmentKey.Reverse, CFBoolean.valueOf(shouldRevers));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean fillsDiscontinuitiesWithSilence() {
        if (has(CMSampleBufferAttachmentKey.FillDiscontinuitiesWithSilence)) {
            CFBoolean val = get(CMSampleBufferAttachmentKey.FillDiscontinuitiesWithSilence, CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMSampleBufferAttachment setFillsDiscontinuitiesWithSilence(boolean fillsDiscontinuitiesWithSilence) {
        set(CMSampleBufferAttachmentKey.FillDiscontinuitiesWithSilence, CFBoolean.valueOf(fillsDiscontinuitiesWithSilence));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean isEmptyMedia() {
        if (has(CMSampleBufferAttachmentKey.EmptyMedia)) {
            CFBoolean val = get(CMSampleBufferAttachmentKey.EmptyMedia, CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMSampleBufferAttachment setEmptyMedia(boolean emptyMedia) {
        set(CMSampleBufferAttachmentKey.EmptyMedia, CFBoolean.valueOf(emptyMedia));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean isPermanentEmptyMedia() {
        if (has(CMSampleBufferAttachmentKey.PermanentEmptyMedia)) {
            CFBoolean val = get(CMSampleBufferAttachmentKey.PermanentEmptyMedia, CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMSampleBufferAttachment setPermanentEmptyMedia(boolean permanentEmptyMedia) {
        set(CMSampleBufferAttachmentKey.PermanentEmptyMedia, CFBoolean.valueOf(permanentEmptyMedia));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean displaysEmptyMediaImmediately() {
        if (has(CMSampleBufferAttachmentKey.DisplayEmptyMediaImmediately)) {
            CFBoolean val = get(CMSampleBufferAttachmentKey.DisplayEmptyMediaImmediately, CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMSampleBufferAttachment setDisplaysEmptyMediaImmediately(boolean displaysEmptyMediaImmediately) {
        set(CMSampleBufferAttachmentKey.DisplayEmptyMediaImmediately, CFBoolean.valueOf(displaysEmptyMediaImmediately));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean isEndsPreviousSampleDuration() {
        if (has(CMSampleBufferAttachmentKey.EndsPreviousSampleDuration)) {
            CFBoolean val = get(CMSampleBufferAttachmentKey.EndsPreviousSampleDuration, CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMSampleBufferAttachment setEndsPreviousSampleDuration(boolean endsPreviousSampleDuration) {
        set(CMSampleBufferAttachmentKey.EndsPreviousSampleDuration, CFBoolean.valueOf(endsPreviousSampleDuration));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSURL getSampleReferenceURL() {
        if (has(CMSampleBufferAttachmentKey.SampleReferenceURL)) {
            NSURL val = get(CMSampleBufferAttachmentKey.SampleReferenceURL, NSURL.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMSampleBufferAttachment setSampleReferenceURL(NSURL sampleReferenceURL) {
        set(CMSampleBufferAttachmentKey.SampleReferenceURL, sampleReferenceURL);
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public long getSampleReferenceByteOffset() {
        if (has(CMSampleBufferAttachmentKey.SampleReferenceByteOffset)) {
            CFNumber val = get(CMSampleBufferAttachmentKey.SampleReferenceByteOffset, CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMSampleBufferAttachment setSampleReferenceByteOffset(long sampleReferenceByteOffset) {
        set(CMSampleBufferAttachmentKey.SampleReferenceByteOffset, CFNumber.valueOf(sampleReferenceByteOffset));
        return this;
    }
    /**
     * @since Available in iOS 4.3 and later.
     */
    public long getGradualDecoderRefresh() {
        if (has(CMSampleBufferAttachmentKey.GradualDecoderRefresh)) {
            CFNumber val = get(CMSampleBufferAttachmentKey.GradualDecoderRefresh, CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.3 and later.
     */
    public CMSampleBufferAttachment setGradualDecoderRefresh(long gradualDecoderRefresh) {
        set(CMSampleBufferAttachmentKey.GradualDecoderRefresh, CFNumber.valueOf(gradualDecoderRefresh));
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public CMSampleBufferDroppedFrameReason getDroppedFrameReason() {
        if (has(CMSampleBufferAttachmentKey.DroppedFrameReason)) {
            CFString val = get(CMSampleBufferAttachmentKey.DroppedFrameReason, CFString.class);
            return CMSampleBufferDroppedFrameReason.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public CMSampleBufferAttachment setDroppedFrameReason(CMSampleBufferDroppedFrameReason droppedFrameReason) {
        set(CMSampleBufferAttachmentKey.DroppedFrameReason, droppedFrameReason.value());
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CMSampleBufferDroppedFrameReasonInfo getDroppedFrameReasonInfo() {
        if (has(CMSampleBufferAttachmentKey.DroppedFrameReasonInfo)) {
            CFString val = get(CMSampleBufferAttachmentKey.DroppedFrameReasonInfo, CFString.class);
            return CMSampleBufferDroppedFrameReasonInfo.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CMSampleBufferAttachment setDroppedFrameReasonInfo(CMSampleBufferDroppedFrameReasonInfo droppedFrameReasonInfo) {
        set(CMSampleBufferAttachmentKey.DroppedFrameReasonInfo, droppedFrameReasonInfo.value());
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    /*</keys>*/
}
