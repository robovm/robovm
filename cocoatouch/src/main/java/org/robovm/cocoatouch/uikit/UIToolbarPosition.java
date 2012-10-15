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

public enum UIToolbarPosition implements ValuedEnum {
    Any(0),
    Bottom(1),
    Top(2);

    private final int n;

    private UIToolbarPosition(int n) { this.n = n; }
    public int value() { return n; }
    public static UIToolbarPosition fromValue(int n) {
        for (UIToolbarPosition v : values()) {
            if (n == v.value()) {
                return v;
            }
        }
        throw new IllegalArgumentException("Unknown UIToolbarPosition value: " + n);
    }
}
