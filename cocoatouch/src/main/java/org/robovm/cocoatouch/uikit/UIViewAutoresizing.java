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

public final class UIViewAutoresizing extends Bits<UIViewAutoresizing> {
    public static final UIViewAutoresizing None = new UIViewAutoresizing(0);
    public static final UIViewAutoresizing FlexibleLeftMargin = new UIViewAutoresizing(1 << 0);
    public static final UIViewAutoresizing FlexibleWidth = new UIViewAutoresizing(1 << 1);
    public static final UIViewAutoresizing FlexibleRightMargin = new UIViewAutoresizing(1 << 2);
    public static final UIViewAutoresizing FlexibleTopMargin = new UIViewAutoresizing(1 << 3);
    public static final UIViewAutoresizing FlexibleHeight = new UIViewAutoresizing(1 << 4);
    public static final UIViewAutoresizing FlexibleBottomMargin = new UIViewAutoresizing(1 << 5);

    private static final UIViewAutoresizing[] values = _values(UIViewAutoresizing.class);
    
    private UIViewAutoresizing(long value) { super(value); }
    private UIViewAutoresizing(long value, long mask) { super(value, mask); }
    protected UIViewAutoresizing wrap(long value, long mask) {
        return new UIViewAutoresizing(value, mask);
    }
    protected UIViewAutoresizing[] values() {
        return values;
    }
}
