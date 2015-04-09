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
package org.robovm.apple.avfoundation;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
import org.robovm.apple.mediatoolbox.*;
import org.robovm.apple.audiounit.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVCompositionTrackSegment/*</name>*/ 
    extends /*<extends>*/AVAssetTrackSegment/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVCompositionTrackSegmentPtr extends Ptr<AVCompositionTrackSegment, AVCompositionTrackSegmentPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVCompositionTrackSegment.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVCompositionTrackSegment() {}
    protected AVCompositionTrackSegment(SkipInit skipInit) { super(skipInit); }
    public AVCompositionTrackSegment(NSURL URL, int trackID, @ByVal CMTimeRange sourceTimeRange, @ByVal CMTimeRange targetTimeRange) { super((SkipInit) null); initObject(init(URL, trackID, sourceTimeRange, targetTimeRange)); }
    public AVCompositionTrackSegment(@ByVal CMTimeRange timeRange) { super((SkipInit) null); initObject(init(timeRange)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "isEmpty")
    public native boolean isEmpty();
    @Property(selector = "sourceURL")
    public native NSURL getSourceURL();
    @Property(selector = "sourceTrackID")
    public native int getSourceTrackID();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithURL:trackID:sourceTimeRange:targetTimeRange:")
    protected native @Pointer long init(NSURL URL, int trackID, @ByVal CMTimeRange sourceTimeRange, @ByVal CMTimeRange targetTimeRange);
    @Method(selector = "initWithTimeRange:")
    protected native @Pointer long init(@ByVal CMTimeRange timeRange);
    @Method(selector = "compositionTrackSegmentWithURL:trackID:sourceTimeRange:targetTimeRange:")
    public static native AVCompositionTrackSegment create(NSURL URL, int trackID, @ByVal CMTimeRange sourceTimeRange, @ByVal CMTimeRange targetTimeRange);
    @Method(selector = "compositionTrackSegmentWithTimeRange:")
    public static native AVCompositionTrackSegment create(@ByVal CMTimeRange timeRange);
    /*</methods>*/
}
