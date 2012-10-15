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

public enum UIRemoteNotificationType implements ValuedEnum {
    None(0),
    Badge(1 << 0),
    Sound(1 << 1),
    Alert(1 << 2),
    NewsstandContentAvailability(1 << 3);

    private final int n;

    private UIRemoteNotificationType(int n) { this.n = n; }
    public int value() { return n; }
    public static UIRemoteNotificationType fromValue(int n) {
        for (UIRemoteNotificationType v : values()) {
            if (n == v.value()) {
                return v;
            }
        }
        throw new IllegalArgumentException("Unknown UIRemoteNotificationType value: " + n);
    }
    public static EnumSet<UIRemoteNotificationType> fromBits(int bits) {
        EnumSet<UIRemoteNotificationType> set = EnumSet.noneOf(UIRemoteNotificationType.class);
        for (UIRemoteNotificationType v : values()) {
            int value = v.value();
            if ((bits & value) == value) {
                set.add(v);
            }
        }
        return set;
    }
    public static int toBits(EnumSet<UIRemoteNotificationType> set) {
        int bits = 0;
        for (UIRemoteNotificationType v : set) {
            bits |= v.value();
        }
        return bits;
    }
}
