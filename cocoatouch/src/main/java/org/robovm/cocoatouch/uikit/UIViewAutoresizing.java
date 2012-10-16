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

import org.robovm.rt.bro.IntValuedEnum;

public enum UIViewAutoresizing implements IntValuedEnum {
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
}
