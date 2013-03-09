/*
 * Copyright (C) 2012 Trillian AB
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
package org.robovm.cocoatouch.glkit;

import java.util.*;

import org.robovm.rt.bro.ValuedEnum;

public enum GLKViewDrawableColorFormat implements ValuedEnum {
    RGBA8888(0),
    RGB565(1);

    private final int n;

    private GLKViewDrawableColorFormat(int n) { this.n = n; }
    public int value() { return n; }
    public static GLKViewDrawableColorFormat fromValue(int n) {
        for (GLKViewDrawableColorFormat v : values()) {
            if (n == v.value()) {
                return v;
            }
        }
        throw new IllegalArgumentException("Unknown GLKViewDrawableColorFormat value: " + n);
    }
}
