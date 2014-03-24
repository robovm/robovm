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

public final class UIPopoverArrowDirection extends Bits<UIPopoverArrowDirection> {
    public static final UIPopoverArrowDirection Up = new UIPopoverArrowDirection(1 << 0);
    public static final UIPopoverArrowDirection Down = new UIPopoverArrowDirection(1 << 1);
    public static final UIPopoverArrowDirection Left = new UIPopoverArrowDirection(1 << 2);
    public static final UIPopoverArrowDirection Right = new UIPopoverArrowDirection(1 << 3);
    public static final UIPopoverArrowDirection Any = new UIPopoverArrowDirection(Up.value() | Down.value() | Left.value() | Right.value());
    public static final UIPopoverArrowDirection Unknown = new UIPopoverArrowDirection(0xFFFFFFFF);

    private static final UIPopoverArrowDirection[] values = _values(UIPopoverArrowDirection.class);
    
    private UIPopoverArrowDirection(long value) { super(value); }
    private UIPopoverArrowDirection(long value, long mask) { super(value, mask); }
    protected UIPopoverArrowDirection wrap(long value, long mask) {
        return new UIPopoverArrowDirection(value, mask);
    }
    protected UIPopoverArrowDirection[] _values() {
        return values;
    }
}
