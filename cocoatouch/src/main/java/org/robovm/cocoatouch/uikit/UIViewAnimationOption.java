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

public enum UIViewAnimationOption implements IntValuedEnum {
    LayoutSubviews(1 <<  0),
    AllowUserInteraction(1 <<  1),
    BeginFromCurrentState(1 <<  2),
    Repeat(1 <<  3),
    Autoreverse(1 <<  4),
    OverrideInheritedDuration(1 <<  5),
    OverrideInheritedCurve(1 <<  6),
    AllowAnimatedContent(1 <<  7),
    ShowHideTransitionViews(1 <<  8),
    CurveEaseInOut(0 << 16),
    CurveEaseIn(1 << 16),
    CurveEaseOut(2 << 16),
    CurveLinear(3 << 16),
    TransitionNone(0 << 20),
    TransitionFlipFromLeft(1 << 20),
    TransitionFlipFromRight(2 << 20),
    TransitionCurlUp(3 << 20),
    TransitionCurlDown(4 << 20),
    TransitionCrossDissolve(5 << 20),
    TransitionFlipFromTop(6 << 20),
    TransitionFlipFromBottom(7 << 20);

    private final int n;

    private UIViewAnimationOption(int n) { this.n = n; }
    public int value() { return n; }
}
