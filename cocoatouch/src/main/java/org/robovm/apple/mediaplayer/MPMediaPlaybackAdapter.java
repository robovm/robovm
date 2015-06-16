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
package org.robovm.apple.mediaplayer;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MPMediaPlaybackAdapter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements MPMediaPlayback/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*/
    @NotImplemented("isPreparedToPlay")
    public boolean isPreparedToPlay() { return false; }
    @NotImplemented("currentPlaybackTime")
    public double getCurrentPlaybackTime() { return 0; }
    @NotImplemented("setCurrentPlaybackTime:")
    public void setCurrentPlaybackTime(double v) {}
    @NotImplemented("currentPlaybackRate")
    public float getCurrentPlaybackRate() { return 0; }
    @NotImplemented("setCurrentPlaybackRate:")
    public void setCurrentPlaybackRate(float v) {}
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @NotImplemented("prepareToPlay")
    public void prepareToPlay() {}
    @NotImplemented("play")
    public void play() {}
    @NotImplemented("pause")
    public void pause() {}
    @NotImplemented("stop")
    public void stop() {}
    @NotImplemented("beginSeekingForward")
    public void beginSeekingForward() {}
    @NotImplemented("beginSeekingBackward")
    public void beginSeekingBackward() {}
    @NotImplemented("endSeeking")
    public void endSeeking() {}
    /*</methods>*/
}
