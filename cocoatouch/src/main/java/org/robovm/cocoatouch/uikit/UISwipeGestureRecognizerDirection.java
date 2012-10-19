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

import org.robovm.rt.bro.Bits;

public final class UISwipeGestureRecognizerDirection extends Bits<UISwipeGestureRecognizerDirection> {
    public static final UISwipeGestureRecognizerDirection Right = new UISwipeGestureRecognizerDirection(1 << 0);
    public static final UISwipeGestureRecognizerDirection Left = new UISwipeGestureRecognizerDirection(1 << 1);
    public static final UISwipeGestureRecognizerDirection Up = new UISwipeGestureRecognizerDirection(1 << 2);
    public static final UISwipeGestureRecognizerDirection Down = new UISwipeGestureRecognizerDirection(1 << 3);

    private static final UISwipeGestureRecognizerDirection[] values = _values(UISwipeGestureRecognizerDirection.class);
    
    private UISwipeGestureRecognizerDirection(long value) { super(value); }
    private UISwipeGestureRecognizerDirection(long value, long mask) { super(value, mask); }
    protected UISwipeGestureRecognizerDirection wrap(long value, long mask) {
        return new UISwipeGestureRecognizerDirection(value, mask);
    }
    protected UISwipeGestureRecognizerDirection[] values() {
        return values;
    }
}
