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
/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAudioMixingAdapter/*</name>*/ 
    extends /*<extends>*/AVAudioStereoMixingAdapter/*</extends>*/ 
    /*<implements>*/implements AVAudioMixing/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*/
    @NotImplemented("volume")
    public float getVolume() { return 0; }
    @NotImplemented("setVolume:")
    public void setVolume(float v) {}
    /*</properties>*/
    /*<members>*//*</members>*/
    @NotImplemented("renderingAlgorithm")
    public AVAudio3DMixingRenderingAlgorithm getRenderingAlgorithm() { throw new UnsupportedOperationException(); }
    @NotImplemented("setRenderingAlgorithm:")
    public void setRenderingAlgorithm(AVAudio3DMixingRenderingAlgorithm v) { throw new UnsupportedOperationException(); }
    @NotImplemented("rate")
    public float getRate() { throw new UnsupportedOperationException(); }
    @NotImplemented("setRate:")
    public void setRate(float v) { throw new UnsupportedOperationException(); }
    @NotImplemented("reverbBlend")
    public float getReverbBlend() { throw new UnsupportedOperationException(); }
    @NotImplemented("setReverbBlend:")
    public void setReverbBlend(float v) { throw new UnsupportedOperationException(); }
    @NotImplemented("obstruction")
    public float getObstruction() { throw new UnsupportedOperationException(); }
    @NotImplemented("setObstruction:")
    public void setObstruction(float v) { throw new UnsupportedOperationException(); }
    @NotImplemented("occlusion")
    public float getOcclusion() { throw new UnsupportedOperationException(); }
    @NotImplemented("setOcclusion:")
    public void setOcclusion(float v) { throw new UnsupportedOperationException(); }
    @NotImplemented("position")
    public @ByVal AVAudio3DPoint getPosition() { throw new UnsupportedOperationException(); }
    @NotImplemented("setPosition:")
    public void setPosition(@ByVal AVAudio3DPoint v) { throw new UnsupportedOperationException(); }    
    /*<methods>*/
    
    /*</methods>*/
}
