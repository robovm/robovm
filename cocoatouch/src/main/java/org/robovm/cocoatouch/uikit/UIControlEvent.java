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

import java.util.*;

public enum UIControlEvent implements ValuedEnum {
    TouchDown(1 <<  0),
    TouchDownRepeat(1 <<  1),
    TouchDragInside(1 <<  2),
    TouchDragOutside(1 <<  3),
    TouchDragEnter(1 <<  4),
    TouchDragExit(1 <<  5),
    TouchUpInside(1 <<  6),
    TouchUpOutside(1 <<  7),
    TouchCancel(1 <<  8),
    ValueChanged(1 << 12),
    EditingDidBegin(1 << 16),
    EditingChanged(1 << 17),
    EditingDidEnd(1 << 18),
    EditingDidEndOnExit(1 << 19),
    AllTouchEvents(0x00000FFF),
    AllEditingEvents(0x000F0000),
    ApplicationReserved(0x0F000000),
    SystemReserved(0xF0000000),
    AllEvents(0xFFFFFFFF),
    ApplicationEvent1(1 << 24),
    ApplicationEvent2(1 << 25),
    ApplicationEvent3(1 << 26),
    ApplicationEvent4(1 << 27),
    SystemEvent1(1 << 28),
    SystemEvent2(1 << 29),
    SystemEvent3(1 << 30),
    SystemEvent4(1 << 31);

    private final int n;

    private UIControlEvent(int n) { this.n = n; }
    public int value() { return n; }
    public static UIControlEvent fromValue(int n) {
        for (UIControlEvent v : values()) {
            if (n == v.value()) {
                return v;
            }
        }
        throw new IllegalArgumentException("Unknown UIControlEvent value: " + n);
    }
    public static EnumSet<UIControlEvent> fromBits(int bits) {
        EnumSet<UIControlEvent> set = EnumSet.noneOf(UIControlEvent.class);
        for (UIControlEvent v : values()) {
            int value = v.value();
            if ((bits & value) == value) {
                set.add(v);
            }
        }
        return set;
    }
    public static int toBits(EnumSet<UIControlEvent> set) {
        int bits = 0;
        for (UIControlEvent v : set) {
            bits |= v.value();
        }
        return bits;
    }
}
