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
package org.robovm.apple.mediaplayer;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 4.3 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("MediaPlayer") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MPMovieAccessLogEvent/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MPMovieAccessLogEventPtr extends Ptr<MPMovieAccessLogEvent, MPMovieAccessLogEventPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MPMovieAccessLogEvent.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MPMovieAccessLogEvent() {}
    protected MPMovieAccessLogEvent(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "numberOfSegmentsDownloaded")
    public native @MachineSizedUInt long getNumberOfSegmentsDownloaded();
    @Property(selector = "playbackStartDate")
    public native NSDate getPlaybackStartDate();
    @Property(selector = "URI")
    public native String getURI();
    @Property(selector = "serverAddress")
    public native String getServerAddress();
    @Property(selector = "numberOfServerAddressChanges")
    public native @MachineSizedUInt long getNumberOfServerAddressChanges();
    @Property(selector = "playbackSessionID")
    public native String getPlaybackSessionID();
    @Property(selector = "playbackStartOffset")
    public native double getPlaybackStartOffset();
    @Property(selector = "segmentsDownloadedDuration")
    public native double getSegmentsDownloadedDuration();
    @Property(selector = "durationWatched")
    public native double getDurationWatched();
    @Property(selector = "numberOfStalls")
    public native @MachineSizedSInt long getNumberOfStalls();
    @Property(selector = "numberOfBytesTransferred")
    public native long getNumberOfBytesTransferred();
    @Property(selector = "observedBitrate")
    public native double getObservedBitrate();
    @Property(selector = "indicatedBitrate")
    public native double getIndicatedBitrate();
    @Property(selector = "numberOfDroppedVideoFrames")
    public native @MachineSizedSInt long getNumberOfDroppedVideoFrames();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
