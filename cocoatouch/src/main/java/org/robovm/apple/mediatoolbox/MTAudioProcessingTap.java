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
package org.robovm.apple.mediatoolbox;

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
import org.robovm.apple.coremedia.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.audiotoolbox.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("MediaToolbox")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MTAudioProcessingTap/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MTAudioProcessingTapPtr extends Ptr<MTAudioProcessingTap, MTAudioProcessingTapPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(MTAudioProcessingTap.class); }/*</bind>*/
    /*<constants>*/
    public static final int CallbacksVersion = 0;
    /*</constants>*/
    /*<constructors>*/
    protected MTAudioProcessingTap() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="MTAudioProcessingTapGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="MTAudioProcessingTapCreate", optional=true)
    protected static native int create(CFAllocator allocator, MTAudioProcessingTapCallbacksStruct callbacks, MTAudioProcessingTapCreationFlag flags, MTAudioProcessingTap.MTAudioProcessingTapPtr tapOut);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="MTAudioProcessingTapGetStorage", optional=true)
    public native VoidPtr getStorage();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="MTAudioProcessingTapGetSourceAudio", optional=true)
    protected native int getSourceAudio(@MachineSizedSInt long numberFrames, AudioBufferList bufferListInOut, IntPtr flagsOut, CMTimeRange timeRangeOut, MachineSizedSIntPtr numberFramesOut);
    /*</methods>*/
}
