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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAudioEngine/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVAudioEnginePtr extends Ptr<AVAudioEngine, AVAudioEnginePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVAudioEngine.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVAudioEngine() {}
    protected AVAudioEngine(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "musicSequence")
    public native MusicSequence getMusicSequence();
    @Property(selector = "setMusicSequence:")
    public native void setMusicSequence(MusicSequence v);
    @Property(selector = "outputNode")
    public native AVAudioOutputNode getOutputNode();
    @Property(selector = "inputNode")
    public native AVAudioInputNode getInputNode();
    @Property(selector = "mainMixerNode")
    public native AVAudioMixerNode getMainMixerNode();
    @Property(selector = "isRunning")
    public native boolean isRunning();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "attachNode:")
    public native void attachNode$(AVAudioNode node);
    @Method(selector = "detachNode:")
    public native void detachNode$(AVAudioNode node);
    @Method(selector = "connect:to:fromBus:toBus:format:")
    public native void connect$to$fromBus$toBus$format$(AVAudioNode node1, AVAudioNode node2, @MachineSizedUInt long bus1, @MachineSizedUInt long bus2, AVAudioFormat format);
    @Method(selector = "connect:to:format:")
    public native void connect$to$format$(AVAudioNode node1, AVAudioNode node2, AVAudioFormat format);
    @Method(selector = "disconnectNodeInput:bus:")
    public native void disconnectNodeInput$bus$(AVAudioNode node, @MachineSizedUInt long bus);
    @Method(selector = "disconnectNodeInput:")
    public native void disconnectNodeInput$(AVAudioNode node);
    @Method(selector = "disconnectNodeOutput:bus:")
    public native void disconnectNodeOutput$bus$(AVAudioNode node, @MachineSizedUInt long bus);
    @Method(selector = "disconnectNodeOutput:")
    public native void disconnectNodeOutput$(AVAudioNode node);
    @Method(selector = "prepare")
    public native void prepare();
    @Method(selector = "startAndReturnError:")
    public native boolean startAndReturnError$(NSError.NSErrorPtr outError);
    @Method(selector = "pause")
    public native void pause();
    @Method(selector = "reset")
    public native void reset();
    @Method(selector = "stop")
    public native void stop();
    /*</methods>*/
}
