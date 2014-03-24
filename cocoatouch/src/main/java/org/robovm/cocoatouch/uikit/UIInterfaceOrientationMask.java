/*
 * Copyright (C) 2012 Trillian Mobile AB
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

public final class UIInterfaceOrientationMask extends Bits<UIInterfaceOrientationMask> {
    public static final UIInterfaceOrientationMask Portrait = new UIInterfaceOrientationMask(1 << UIInterfaceOrientation.Portrait.value());
    public static final UIInterfaceOrientationMask LandscapeLeft = new UIInterfaceOrientationMask(1 << UIInterfaceOrientation.LandscapeLeft.value());
    public static final UIInterfaceOrientationMask LandscapeRight = new UIInterfaceOrientationMask(1 << UIInterfaceOrientation.LandscapeRight.value());
    public static final UIInterfaceOrientationMask PortraitUpsideDown = new UIInterfaceOrientationMask(1 << UIInterfaceOrientation.PortraitUpsideDown.value());
    public static final UIInterfaceOrientationMask Landscape = new UIInterfaceOrientationMask(LandscapeLeft.value() | LandscapeRight.value());
    public static final UIInterfaceOrientationMask All = new UIInterfaceOrientationMask(Portrait.value() | LandscapeLeft.value() | LandscapeRight.value() | PortraitUpsideDown.value());
    public static final UIInterfaceOrientationMask AllButUpsideDown = new UIInterfaceOrientationMask(Portrait.value() | LandscapeLeft.value() | LandscapeRight.value());

    private static final UIInterfaceOrientationMask[] values = _values(UIInterfaceOrientationMask.class);
    
    private UIInterfaceOrientationMask(long value) { super(value); }
    private UIInterfaceOrientationMask(long value, long mask) { super(value, mask); }
    protected UIInterfaceOrientationMask wrap(long value, long mask) {
        return new UIInterfaceOrientationMask(value, mask);
    }
    protected UIInterfaceOrientationMask[] _values() {
        return values;
    }
}
