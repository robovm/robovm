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

public enum UIPopoverArrowDirection implements ValuedEnum {
    Up(1 << 0),
    Down(1 << 1),
    Left(1 << 2),
    Right(1 << 3),
    Any(Up.value() | Down.value() | Left.value() | Right.value()),
    Unknown(0xFFFFFFFF);

    private final int n;

    private UIPopoverArrowDirection(int n) { this.n = n; }
    public int value() { return n; }
    public static UIPopoverArrowDirection fromValue(int n) {
        for (UIPopoverArrowDirection v : values()) {
            if (n == v.value()) {
                return v;
            }
        }
        throw new IllegalArgumentException("Unknown UIPopoverArrowDirection value: " + n);
    }
    public static EnumSet<UIPopoverArrowDirection> fromBits(int bits) {
        EnumSet<UIPopoverArrowDirection> set = EnumSet.noneOf(UIPopoverArrowDirection.class);
        for (UIPopoverArrowDirection v : values()) {
            int value = v.value();
            if ((bits & value) == value) {
                set.add(v);
            }
        }
        return set;
    }
    public static int toBits(EnumSet<UIPopoverArrowDirection> set) {
        int bits = 0;
        for (UIPopoverArrowDirection v : set) {
            bits |= v.value();
        }
        return bits;
    }
}
