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
/*<annotations>*/@Library("CoreMedia") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/CMSampleBufferAttachmentKey/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMSampleBufferAttachmentKey/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CMSampleBufferAttachmentKey toObject(Class<CMSampleBufferAttachmentKey> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CMSampleBufferAttachmentKey.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CMSampleBufferAttachmentKey o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CMSampleBufferAttachmentKey> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(CFArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CMSampleBufferAttachmentKey> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CMSampleBufferAttachmentKey.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CMSampleBufferAttachmentKey> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CMSampleBufferAttachmentKey o : l) {
                array.add(o.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CMSampleBufferAttachmentKey ResetDecoderBeforeDecoding = new CMSampleBufferAttachmentKey("ResetDecoderBeforeDecoding");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CMSampleBufferAttachmentKey DrainAfterDecoding = new CMSampleBufferAttachmentKey("DrainAfterDecoding");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CMSampleBufferAttachmentKey PostNotificationWhenConsumed = new CMSampleBufferAttachmentKey("PostNotificationWhenConsumed");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CMSampleBufferAttachmentKey ResumeOutput = new CMSampleBufferAttachmentKey("ResumeOutput");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CMSampleBufferAttachmentKey TransitionID = new CMSampleBufferAttachmentKey("TransitionID");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CMSampleBufferAttachmentKey TrimDurationAtStart = new CMSampleBufferAttachmentKey("TrimDurationAtStart");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CMSampleBufferAttachmentKey TrimDurationAtEnd = new CMSampleBufferAttachmentKey("TrimDurationAtEnd");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CMSampleBufferAttachmentKey SpeedMultiplier = new CMSampleBufferAttachmentKey("SpeedMultiplier");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CMSampleBufferAttachmentKey Reverse = new CMSampleBufferAttachmentKey("Reverse");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CMSampleBufferAttachmentKey FillDiscontinuitiesWithSilence = new CMSampleBufferAttachmentKey("FillDiscontinuitiesWithSilence");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CMSampleBufferAttachmentKey EmptyMedia = new CMSampleBufferAttachmentKey("EmptyMedia");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CMSampleBufferAttachmentKey PermanentEmptyMedia = new CMSampleBufferAttachmentKey("PermanentEmptyMedia");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CMSampleBufferAttachmentKey DisplayEmptyMediaImmediately = new CMSampleBufferAttachmentKey("DisplayEmptyMediaImmediately");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CMSampleBufferAttachmentKey EndsPreviousSampleDuration = new CMSampleBufferAttachmentKey("EndsPreviousSampleDuration");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CMSampleBufferAttachmentKey SampleReferenceURL = new CMSampleBufferAttachmentKey("SampleReferenceURL");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CMSampleBufferAttachmentKey SampleReferenceByteOffset = new CMSampleBufferAttachmentKey("SampleReferenceByteOffset");
    /**
     * @since Available in iOS 4.3 and later.
     */
    public static final CMSampleBufferAttachmentKey GradualDecoderRefresh = new CMSampleBufferAttachmentKey("GradualDecoderRefresh");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final CMSampleBufferAttachmentKey DroppedFrameReason = new CMSampleBufferAttachmentKey("DroppedFrameReason");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CMSampleBufferAttachmentKey DroppedFrameReasonInfo = new CMSampleBufferAttachmentKey("DroppedFrameReasonInfo");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMSampleBufferAttachmentKey ForceKeyFrame = new CMSampleBufferAttachmentKey("ForceKeyFrame");
    /*</constants>*/
    
    private static /*<name>*/CMSampleBufferAttachmentKey/*</name>*/[] values = new /*<name>*/CMSampleBufferAttachmentKey/*</name>*/[] {/*<value_list>*/ResetDecoderBeforeDecoding, DrainAfterDecoding, PostNotificationWhenConsumed, ResumeOutput, TransitionID, TrimDurationAtStart, TrimDurationAtEnd, SpeedMultiplier, Reverse, FillDiscontinuitiesWithSilence, EmptyMedia, PermanentEmptyMedia, DisplayEmptyMediaImmediately, EndsPreviousSampleDuration, SampleReferenceURL, SampleReferenceByteOffset, GradualDecoderRefresh, DroppedFrameReason, DroppedFrameReasonInfo, ForceKeyFrame/*</value_list>*/};
    
    /*<name>*/CMSampleBufferAttachmentKey/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CMSampleBufferAttachmentKey/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CMSampleBufferAttachmentKey/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CMSampleBufferAttachmentKey/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreMedia") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMSampleBufferAttachmentKey_ResetDecoderBeforeDecoding", optional=true)
        public static native CFString ResetDecoderBeforeDecoding();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMSampleBufferAttachmentKey_DrainAfterDecoding", optional=true)
        public static native CFString DrainAfterDecoding();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMSampleBufferAttachmentKey_PostNotificationWhenConsumed", optional=true)
        public static native CFString PostNotificationWhenConsumed();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMSampleBufferAttachmentKey_ResumeOutput", optional=true)
        public static native CFString ResumeOutput();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMSampleBufferAttachmentKey_TransitionID", optional=true)
        public static native CFString TransitionID();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMSampleBufferAttachmentKey_TrimDurationAtStart", optional=true)
        public static native CFString TrimDurationAtStart();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMSampleBufferAttachmentKey_TrimDurationAtEnd", optional=true)
        public static native CFString TrimDurationAtEnd();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMSampleBufferAttachmentKey_SpeedMultiplier", optional=true)
        public static native CFString SpeedMultiplier();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMSampleBufferAttachmentKey_Reverse", optional=true)
        public static native CFString Reverse();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMSampleBufferAttachmentKey_FillDiscontinuitiesWithSilence", optional=true)
        public static native CFString FillDiscontinuitiesWithSilence();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMSampleBufferAttachmentKey_EmptyMedia", optional=true)
        public static native CFString EmptyMedia();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMSampleBufferAttachmentKey_PermanentEmptyMedia", optional=true)
        public static native CFString PermanentEmptyMedia();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMSampleBufferAttachmentKey_DisplayEmptyMediaImmediately", optional=true)
        public static native CFString DisplayEmptyMediaImmediately();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMSampleBufferAttachmentKey_EndsPreviousSampleDuration", optional=true)
        public static native CFString EndsPreviousSampleDuration();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMSampleBufferAttachmentKey_SampleReferenceURL", optional=true)
        public static native CFString SampleReferenceURL();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMSampleBufferAttachmentKey_SampleReferenceByteOffset", optional=true)
        public static native CFString SampleReferenceByteOffset();
        /**
         * @since Available in iOS 4.3 and later.
         */
        @GlobalValue(symbol="kCMSampleBufferAttachmentKey_GradualDecoderRefresh", optional=true)
        public static native CFString GradualDecoderRefresh();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="kCMSampleBufferAttachmentKey_DroppedFrameReason", optional=true)
        public static native CFString DroppedFrameReason();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCMSampleBufferAttachmentKey_DroppedFrameReasonInfo", optional=true)
        public static native CFString DroppedFrameReasonInfo();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMSampleBufferAttachmentKey_ForceKeyFrame", optional=true)
        public static native CFString ForceKeyFrame();
        /*</values>*/
    }
}
