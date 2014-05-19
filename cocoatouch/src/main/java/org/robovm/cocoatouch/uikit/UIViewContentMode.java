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

import org.robovm.rt.bro.ValuedEnum;

@Deprecated public enum UIViewContentMode implements ValuedEnum {
    ScaleToFill(0),
    ScaleAspectFit(1),
    ScaleAspectFill(2),
    Redraw(3),
    Center(4),
    Top(5),
    Bottom(6),
    Left(7),
    Right(8),
    TopLeft(9),
    TopRight(10),
    BottomLeft(11),
    BottomRight(12);

    private final long n;

    private UIViewContentMode(long n) { this.n = n; }
    public long value() { return n; }
}
