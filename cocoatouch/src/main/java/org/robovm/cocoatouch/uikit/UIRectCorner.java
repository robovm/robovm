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

public final class UIRectCorner extends Bits<UIRectCorner> {
    public static final UIRectCorner TopLeft = new UIRectCorner(1 << 0);
    public static final UIRectCorner TopRight = new UIRectCorner(1 << 1);
    public static final UIRectCorner BottomLeft = new UIRectCorner(1 << 2);
    public static final UIRectCorner BottomRight = new UIRectCorner(1 << 3);
    public static final UIRectCorner AllCorners = new UIRectCorner(0xffffffff);

    private static final UIRectCorner[] values = _values(UIRectCorner.class);
    
    private UIRectCorner(long value) { super(value); }
    private UIRectCorner(long value, long mask) { super(value, mask); }
    protected UIRectCorner wrap(long value, long mask) {
        return new UIRectCorner(value, mask);
    }
    protected UIRectCorner[] _values() {
        return values;
    }
}
