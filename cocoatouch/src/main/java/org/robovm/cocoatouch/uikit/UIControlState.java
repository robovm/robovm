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

@Deprecated public final class UIControlState extends Bits<UIControlState> {
    public static final UIControlState Normal = new UIControlState(0);
    public static final UIControlState Highlighted = new UIControlState(1 << 0);
    public static final UIControlState Disabled = new UIControlState(1 << 1);
    public static final UIControlState Selected = new UIControlState(1 << 2);
    public static final UIControlState Application = new UIControlState(0x00FF0000);
    public static final UIControlState Reserved = new UIControlState(0xFF000000);
    public static final UIControlState ApplicationState1 = new UIControlState(1 << 16);
    public static final UIControlState ApplicationState2 = new UIControlState(1 << 17);
    public static final UIControlState ApplicationState3 = new UIControlState(1 << 18);
    public static final UIControlState ApplicationState4 = new UIControlState(1 << 19);
    public static final UIControlState ApplicationState5 = new UIControlState(1 << 20);
    public static final UIControlState ApplicationState6 = new UIControlState(1 << 21);
    public static final UIControlState ApplicationState7 = new UIControlState(1 << 22);
    public static final UIControlState ApplicationState8 = new UIControlState(1 << 23);

    private static final UIControlState[] values = _values(UIControlState.class);
    
    private UIControlState(long value) { super(value); }
    private UIControlState(long value, long mask) { super(value, mask); }
    protected UIControlState wrap(long value, long mask) {
        return new UIControlState(value, mask);
    }
    protected UIControlState[] _values() {
        return values;
    }
}
