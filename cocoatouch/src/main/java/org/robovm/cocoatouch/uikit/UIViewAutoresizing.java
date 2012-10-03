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

public enum UIViewAutoresizing {
    None(0),
    FlexibleLeftMargin(1 << 0),
    FlexibleWidth(1 << 1),
    FlexibleRightMargin(1 << 2),
    FlexibleTopMargin(1 << 3),
    FlexibleHeight(1 << 4),
    FlexibleBottomMargin(1 << 5);

    private final int n;

    private UIViewAutoresizing(int n) { this.n = n; }
    public int value() { return n; }
    public static UIViewAutoresizing fromValue(int n) {
        for (UIViewAutoresizing v : values()) {
            if (n == v.value()) {
                return v;
            }
        }
        throw new IllegalArgumentException("Unknown UIViewAutoresizing value: " + n);
    }
    public static EnumSet<UIViewAutoresizing> fromBits(int bits) {
        EnumSet<UIViewAutoresizing> set = EnumSet.noneOf(UIViewAutoresizing.class);
        for (UIViewAutoresizing v : values()) {
            int value = v.value();
            if ((bits & value) == value) {
                set.add(v);
            }
        }
        return set;
    }
    public static int toBits(EnumSet<UIViewAutoresizing> set) {
        int bits = 0;
        for (UIViewAutoresizing v : set) {
            bits |= v.value();
        }
        return bits;
    }
}
