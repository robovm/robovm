/*
 * Copyright (C) 2012 Trillian AB
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

import org.robovm.rt.bro.Bits;

public final class UIViewAnimationOptions extends Bits<UIViewAnimationOptions> {
    public static final UIViewAnimationOptions LayoutSubviews = new UIViewAnimationOptions(1 <<  0);
    public static final UIViewAnimationOptions AllowUserInteraction = new UIViewAnimationOptions(1 <<  1);
    public static final UIViewAnimationOptions BeginFromCurrentState = new UIViewAnimationOptions(1 <<  2);
    public static final UIViewAnimationOptions Repeat = new UIViewAnimationOptions(1 <<  3);
    public static final UIViewAnimationOptions Autoreverse = new UIViewAnimationOptions(1 <<  4);
    public static final UIViewAnimationOptions OverrideInheritedDuration = new UIViewAnimationOptions(1 <<  5);
    public static final UIViewAnimationOptions OverrideInheritedCurve = new UIViewAnimationOptions(1 <<  6);
    public static final UIViewAnimationOptions AllowAnimatedContent = new UIViewAnimationOptions(1 <<  7);
    public static final UIViewAnimationOptions ShowHideTransitionViews = new UIViewAnimationOptions(1 <<  8);
    public static final UIViewAnimationOptions CurveEaseInOut = new UIViewAnimationOptions(0 << 16, 3 << 16);
    public static final UIViewAnimationOptions CurveEaseIn = new UIViewAnimationOptions(1 << 16, 3 << 16);
    public static final UIViewAnimationOptions CurveEaseOut = new UIViewAnimationOptions(2 << 16, 3 << 16);
    public static final UIViewAnimationOptions CurveLinear = new UIViewAnimationOptions(3 << 16, 3 << 16);
    public static final UIViewAnimationOptions TransitionNone = new UIViewAnimationOptions(0 << 20, 7 << 20);
    public static final UIViewAnimationOptions TransitionFlipFromLeft = new UIViewAnimationOptions(1 << 20, 7 << 20);
    public static final UIViewAnimationOptions TransitionFlipFromRight = new UIViewAnimationOptions(2 << 20, 7 << 20);
    public static final UIViewAnimationOptions TransitionCurlUp = new UIViewAnimationOptions(3 << 20, 7 << 20);
    public static final UIViewAnimationOptions TransitionCurlDown = new UIViewAnimationOptions(4 << 20, 7 << 20);
    public static final UIViewAnimationOptions TransitionCrossDissolve = new UIViewAnimationOptions(5 << 20, 7 << 20);
    public static final UIViewAnimationOptions TransitionFlipFromTop = new UIViewAnimationOptions(6 << 20, 7 << 20);
    public static final UIViewAnimationOptions TransitionFlipFromBottom = new UIViewAnimationOptions(7 << 20, 7 << 20);

    private static final UIViewAnimationOptions[] values = _values(UIViewAnimationOptions.class);
    
    private UIViewAnimationOptions(long value) { super(value); }
    private UIViewAnimationOptions(long value, long mask) { super(value, mask); }
    protected UIViewAnimationOptions wrap(long value, long mask) {
        return new UIViewAnimationOptions(value, mask);
    }
    protected UIViewAnimationOptions[] _values() {
        return values;
    }
}
