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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAudioUnitGenerator/*</name>*/ 
    extends /*<extends>*/AVAudioUnit/*</extends>*/ 
    /*<implements>*/implements AVAudioMixing/*</implements>*/ {

    /*<ptr>*/public static class AVAudioUnitGeneratorPtr extends Ptr<AVAudioUnitGenerator, AVAudioUnitGeneratorPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVAudioUnitGenerator.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVAudioUnitGenerator() {}
    protected AVAudioUnitGenerator(SkipInit skipInit) { super(skipInit); }
    public AVAudioUnitGenerator(@ByVal AudioComponentDescription audioComponentDescription) { super((SkipInit) null); initObject(initWithAudioComponentDescription$(audioComponentDescription)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "bypass")
    public native boolean isBypass();
    @Property(selector = "setBypass:")
    public native void setBypass(boolean v);
    @Property(selector = "volume")
    public native float getVolume();
    @Property(selector = "setVolume:")
    public native void setVolume(float v);
    @Property(selector = "pan")
    public native float getPan();
    @Property(selector = "setPan:")
    public native void setPan(float v);
    @Property(selector = "renderingAlgorithm")
    public native AVAudio3DMixingRenderingAlgorithm getRenderingAlgorithm();
    @Property(selector = "setRenderingAlgorithm:")
    public native void setRenderingAlgorithm(AVAudio3DMixingRenderingAlgorithm v);
    @Property(selector = "rate")
    public native float getRate();
    @Property(selector = "setRate:")
    public native void setRate(float v);
    @Property(selector = "reverbBlend")
    public native float getReverbBlend();
    @Property(selector = "setReverbBlend:")
    public native void setReverbBlend(float v);
    @Property(selector = "obstruction")
    public native float getObstruction();
    @Property(selector = "setObstruction:")
    public native void setObstruction(float v);
    @Property(selector = "occlusion")
    public native float getOcclusion();
    @Property(selector = "setOcclusion:")
    public native void setOcclusion(float v);
    @Property(selector = "position")
    public native @ByVal AVAudio3DPoint getPosition();
    @Property(selector = "setPosition:")
    public native void setPosition(@ByVal AVAudio3DPoint v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithAudioComponentDescription:")
    protected native @Pointer long initWithAudioComponentDescription$(@ByVal AudioComponentDescription audioComponentDescription);
    /*</methods>*/
}
