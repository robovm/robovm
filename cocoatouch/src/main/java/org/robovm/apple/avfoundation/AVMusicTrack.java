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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.mediatoolbox.*;
import org.robovm.apple.audiotoolbox.*;
import org.robovm.apple.audiounit.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 9.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVMusicTrack/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVMusicTrackPtr extends Ptr<AVMusicTrack, AVMusicTrackPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVMusicTrack.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVMusicTrack() {}
    protected AVMusicTrack(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "destinationAudioUnit")
    public native AVAudioUnit getDestinationAudioUnit();
    @Property(selector = "setDestinationAudioUnit:")
    public native void setDestinationAudioUnit(AVAudioUnit v);
    @Property(selector = "destinationMIDIEndpoint")
    public native int getDestinationMIDIEndpoint();
    @Property(selector = "setDestinationMIDIEndpoint:")
    public native void setDestinationMIDIEndpoint(int v);
    @Property(selector = "loopRange")
    public native @ByVal AVBeatRange getLoopRange();
    @Property(selector = "setLoopRange:")
    public native void setLoopRange(@ByVal AVBeatRange v);
    @Property(selector = "isLoopingEnabled")
    public native boolean isLoopingEnabled();
    @Property(selector = "setLoopingEnabled:")
    public native void setLoopingEnabled(boolean v);
    @Property(selector = "numberOfLoops")
    public native @MachineSizedSInt long getNumberOfLoops();
    @Property(selector = "setNumberOfLoops:")
    public native void setNumberOfLoops(@MachineSizedSInt long v);
    @Property(selector = "offsetTime")
    public native double getOffsetTime();
    @Property(selector = "setOffsetTime:")
    public native void setOffsetTime(double v);
    @Property(selector = "isMuted")
    public native boolean isMuted();
    @Property(selector = "setMuted:")
    public native void setMuted(boolean v);
    @Property(selector = "isSoloed")
    public native boolean isSoloed();
    @Property(selector = "setSoloed:")
    public native void setSoloed(boolean v);
    @Property(selector = "lengthInBeats")
    public native double getLengthInBeats();
    @Property(selector = "setLengthInBeats:")
    public native void setLengthInBeats(double v);
    @Property(selector = "lengthInSeconds")
    public native double getLengthInSeconds();
    @Property(selector = "setLengthInSeconds:")
    public native void setLengthInSeconds(double v);
    @Property(selector = "timeResolution")
    public native @MachineSizedUInt long getTimeResolution();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
