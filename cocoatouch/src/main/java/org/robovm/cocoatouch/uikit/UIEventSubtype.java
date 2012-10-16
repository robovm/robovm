/*
 * Copyright (C) 2012 RoboVM
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
package org.robovm.cocoatouch.uikit;

import org.robovm.rt.bro.IntValuedEnum;

public enum UIEventSubtype implements IntValuedEnum {
    None(0),
    MotionShake(1),
    RemoteControlPlay(100),
    RemoteControlPause(101),
    RemoteControlStop(102),
    RemoteControlTogglePlayPause(103),
    RemoteControlNextTrack(104),
    RemoteControlPreviousTrack(105),
    RemoteControlBeginSeekingBackward(106),
    RemoteControlEndSeekingBackward(107),
    RemoteControlBeginSeekingForward(108),
    RemoteControlEndSeekingForward(109);

    private final int n;

    private UIEventSubtype(int n) { this.n = n; }
    public int value() { return n; }
}
