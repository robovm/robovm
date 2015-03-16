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
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAudioUnitMIDIInstrument/*</name>*/ 
    extends /*<extends>*/AVAudioUnit/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVAudioUnitMIDIInstrumentPtr extends Ptr<AVAudioUnitMIDIInstrument, AVAudioUnitMIDIInstrumentPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVAudioUnitMIDIInstrument.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVAudioUnitMIDIInstrument() {}
    protected AVAudioUnitMIDIInstrument(SkipInit skipInit) { super(skipInit); }
    public AVAudioUnitMIDIInstrument(@ByVal AudioComponentDescription description) { super((SkipInit) null); initObject(init(description)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithAudioComponentDescription:")
    protected native @Pointer long init(@ByVal AudioComponentDescription description);
    @Method(selector = "startNote:withVelocity:onChannel:")
    public native void startNote(byte note, byte velocity, byte channel);
    @Method(selector = "stopNote:onChannel:")
    public native void stopNote(byte note, byte channel);
    @Method(selector = "sendController:withValue:onChannel:")
    public native void sendController(byte controller, byte value, byte channel);
    @Method(selector = "sendPitchBend:onChannel:")
    public native void sendPitchBend(short pitchbend, byte channel);
    @Method(selector = "sendPressure:onChannel:")
    public native void sendPressure(byte pressure, byte channel);
    @Method(selector = "sendPressureForKey:withValue:onChannel:")
    public native void sendPressure(byte key, byte value, byte channel);
    @Method(selector = "sendProgramChange:onChannel:")
    public native void sendProgramChange(byte program, byte channel);
    @Method(selector = "sendProgramChange:bankMSB:bankLSB:onChannel:")
    public native void sendProgramChange(byte program, byte bankMSB, byte bankLSB, byte channel);
    @Method(selector = "sendMIDIEvent:data1:data2:")
    public native void sendMIDIEvent(byte midiStatus, byte data1, byte data2);
    @Method(selector = "sendMIDIEvent:data1:")
    public native void sendMIDIEvent(byte midiStatus, byte data1);
    @Method(selector = "sendMIDISysExEvent:")
    public native void sendMIDISysExEvent(NSData midiData);
    /*</methods>*/
}
