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

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/MPMediaPlayback/*</name>*/ 
    /*<implements>*/extends NSObjectProtocol/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    @Property(selector = "isPreparedToPlay")
    boolean isIsPreparedToPlay();
    @Property(selector = "currentPlaybackTime")
    double getCurrentPlaybackTime();
    @Property(selector = "setCurrentPlaybackTime:")
    void setCurrentPlaybackTime(double v);
    @Property(selector = "currentPlaybackRate")
    float getCurrentPlaybackRate();
    @Property(selector = "setCurrentPlaybackRate:")
    void setCurrentPlaybackRate(float v);
    /*</properties>*/
    /*<methods>*/
    @Method(selector = "prepareToPlay")
    void prepareToPlay();
    @Method(selector = "play")
    void play();
    @Method(selector = "pause")
    void pause();
    @Method(selector = "stop")
    void stop();
    @Method(selector = "beginSeekingForward")
    void beginSeekingForward();
    @Method(selector = "beginSeekingBackward")
    void beginSeekingBackward();
    @Method(selector = "endSeeking")
    void endSeeking();
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
