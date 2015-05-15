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
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAudioTime/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVAudioTimePtr extends Ptr<AVAudioTime, AVAudioTimePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVAudioTime.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVAudioTime() {}
    protected AVAudioTime(SkipInit skipInit) { super(skipInit); }
    public AVAudioTime(AudioTimeStamp ts, double sampleRate) { super((SkipInit) null); initObject(init(ts, sampleRate)); }
    public AVAudioTime(long hostTime) { super((SkipInit) null); initObject(init(hostTime)); }
    public AVAudioTime(long sampleTime, double sampleRate) { super((SkipInit) null); initObject(init(sampleTime, sampleRate)); }
    public AVAudioTime(long hostTime, long sampleTime, double sampleRate) { super((SkipInit) null); initObject(init(hostTime, sampleTime, sampleRate)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "isHostTimeValid")
    public native boolean isHostTimeValid();
    @Property(selector = "hostTime")
    public native long getHostTime();
    @Property(selector = "isSampleTimeValid")
    public native boolean isSampleTimeValid();
    @Property(selector = "sampleTime")
    public native long getSampleTime();
    @Property(selector = "sampleRate")
    public native double getSampleRate();
    @Property(selector = "audioTimeStamp")
    public native @ByVal AudioTimeStamp getAudioTimeStamp();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithAudioTimeStamp:sampleRate:")
    protected native @Pointer long init(AudioTimeStamp ts, double sampleRate);
    @Method(selector = "initWithHostTime:")
    protected native @Pointer long init(long hostTime);
    @Method(selector = "initWithSampleTime:atRate:")
    protected native @Pointer long init(long sampleTime, double sampleRate);
    @Method(selector = "initWithHostTime:sampleTime:atRate:")
    protected native @Pointer long init(long hostTime, long sampleTime, double sampleRate);
    @Method(selector = "extrapolateTimeFromAnchor:")
    public native AVAudioTime extrapolateTimeFromAnchor(AVAudioTime anchorTime);
    @Method(selector = "timeWithAudioTimeStamp:sampleRate:")
    public static native AVAudioTime create(AudioTimeStamp ts, double sampleRate);
    @Method(selector = "timeWithHostTime:")
    public static native AVAudioTime create(long hostTime);
    @Method(selector = "timeWithSampleTime:atRate:")
    public static native AVAudioTime create(long sampleTime, double sampleRate);
    @Method(selector = "timeWithHostTime:sampleTime:atRate:")
    public static native AVAudioTime create(long hostTime, long sampleTime, double sampleRate);
    @Method(selector = "hostTimeForSeconds:")
    public static native long convertSecondsToHostTime(double seconds);
    @Method(selector = "secondsForHostTime:")
    public static native double convertHostTimeToSeconds(long hostTime);
    /*</methods>*/
}
