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
 * @since Available in iOS 7.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVSpeechUtterance/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVSpeechUtterancePtr extends Ptr<AVSpeechUtterance, AVSpeechUtterancePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVSpeechUtterance.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVSpeechUtterance() {}
    protected AVSpeechUtterance(SkipInit skipInit) { super(skipInit); }
    public AVSpeechUtterance(String string) { super((SkipInit) null); initObject(init(string)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "voice")
    public native AVSpeechSynthesisVoice getVoice();
    @Property(selector = "setVoice:")
    public native void setVoice(AVSpeechSynthesisVoice v);
    @Property(selector = "speechString")
    public native String getSpeechString();
    @Property(selector = "rate")
    public native float getRate();
    @Property(selector = "setRate:")
    public native void setRate(float v);
    @Property(selector = "pitchMultiplier")
    public native float getPitchMultiplier();
    @Property(selector = "setPitchMultiplier:")
    public native void setPitchMultiplier(float v);
    @Property(selector = "volume")
    public native float getVolume();
    @Property(selector = "setVolume:")
    public native void setVolume(float v);
    @Property(selector = "preUtteranceDelay")
    public native double getPreUtteranceDelay();
    @Property(selector = "setPreUtteranceDelay:")
    public native void setPreUtteranceDelay(double v);
    @Property(selector = "postUtteranceDelay")
    public native double getPostUtteranceDelay();
    @Property(selector = "setPostUtteranceDelay:")
    public native void setPostUtteranceDelay(double v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="AVSpeechUtteranceMinimumSpeechRate", optional=true)
    public static native float getMinimumSpeechRate();
    @GlobalValue(symbol="AVSpeechUtteranceMaximumSpeechRate", optional=true)
    public static native float getMaximumSpeechRate();
    @GlobalValue(symbol="AVSpeechUtteranceDefaultSpeechRate", optional=true)
    public static native float getDefaultSpeechRate();
    
    @Method(selector = "initWithString:")
    protected native @Pointer long init(String string);
    @Method(selector = "speechUtteranceWithString:")
    public static native AVSpeechUtterance create(String string);
    /*</methods>*/
}
