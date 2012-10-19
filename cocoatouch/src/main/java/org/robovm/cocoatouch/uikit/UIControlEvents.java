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

public final class UIControlEvents extends Bits<UIControlEvents> {
    public static final UIControlEvents TouchDown = new UIControlEvents(1 <<  0);
    public static final UIControlEvents TouchDownRepeat = new UIControlEvents(1 <<  1);
    public static final UIControlEvents TouchDragInside = new UIControlEvents(1 <<  2);
    public static final UIControlEvents TouchDragOutside = new UIControlEvents(1 <<  3);
    public static final UIControlEvents TouchDragEnter = new UIControlEvents(1 <<  4);
    public static final UIControlEvents TouchDragExit = new UIControlEvents(1 <<  5);
    public static final UIControlEvents TouchUpInside = new UIControlEvents(1 <<  6);
    public static final UIControlEvents TouchUpOutside = new UIControlEvents(1 <<  7);
    public static final UIControlEvents TouchCancel = new UIControlEvents(1 <<  8);
    public static final UIControlEvents ValueChanged = new UIControlEvents(1 << 12);
    public static final UIControlEvents EditingDidBegin = new UIControlEvents(1 << 16);
    public static final UIControlEvents EditingChanged = new UIControlEvents(1 << 17);
    public static final UIControlEvents EditingDidEnd = new UIControlEvents(1 << 18);
    public static final UIControlEvents EditingDidEndOnExit = new UIControlEvents(1 << 19);
    public static final UIControlEvents AllTouchEvents = new UIControlEvents(0x00000FFF);
    public static final UIControlEvents AllEditingEvents = new UIControlEvents(0x000F0000);
    public static final UIControlEvents ApplicationReserved = new UIControlEvents(0x0F000000);
    public static final UIControlEvents SystemReserved = new UIControlEvents(0xF0000000);
    public static final UIControlEvents AllEvents = new UIControlEvents(0xFFFFFFFF);
    public static final UIControlEvents ApplicationEvent1 = new UIControlEvents(1 << 24);
    public static final UIControlEvents ApplicationEvent2 = new UIControlEvents(1 << 25);
    public static final UIControlEvents ApplicationEvent3 = new UIControlEvents(1 << 26);
    public static final UIControlEvents ApplicationEvent4 = new UIControlEvents(1 << 27);
    public static final UIControlEvents SystemEvent1 = new UIControlEvents(1 << 28);
    public static final UIControlEvents SystemEvent2 = new UIControlEvents(1 << 29);
    public static final UIControlEvents SystemEvent3 = new UIControlEvents(1 << 30);
    public static final UIControlEvents SystemEvent4 = new UIControlEvents(1 << 31);

    private static final UIControlEvents[] values = _values(UIControlEvents.class);
    
    private UIControlEvents(long value) { super(value); }
    private UIControlEvents(long value, long mask) { super(value, mask); }
    protected UIControlEvents wrap(long value, long mask) {
        return new UIControlEvents(value, mask);
    }
    protected UIControlEvents[] values() {
        return values;
    }
}
