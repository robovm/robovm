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

public enum UIDataDetectorType {
    PhoneNumber(1 << 0),
    Link(1 << 1),
    Address(1 << 2),
    CalendarEvent(1 << 3),
    None(0),
    All(0xFFFFFFFF);

    private final int n;

    private UIDataDetectorType(int n) { this.n = n; }
    public int value() { return n; }
    public static UIDataDetectorType fromValue(int n) {
        for (UIDataDetectorType v : values()) {
            if (n == v.value()) {
                return v;
            }
        }
        throw new IllegalArgumentException("Unknown UIDataDetectorType value: " + n);
    }
    public static EnumSet<UIDataDetectorType> fromBits(int bits) {
        EnumSet<UIDataDetectorType> set = EnumSet.noneOf(UIDataDetectorType.class);
        for (UIDataDetectorType v : values()) {
            int value = v.value();
            if ((bits & value) == value) {
                set.add(v);
            }
        }
        return set;
    }
    public static int toBits(EnumSet<UIDataDetectorType> set) {
        int bits = 0;
        for (UIDataDetectorType v : set) {
            bits |= v.value();
        }
        return bits;
    }
}
