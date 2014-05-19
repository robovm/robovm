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

@Deprecated public enum NSLayoutAttribute implements ValuedEnum {
    Left(1),
    Right(2),
    Top(3),
    Bottom(4),
    Leading(5),
    Trailing(6),
    Width(7),
    Height(8),
    CenterX(9),
    CenterY(10),
    Baseline(11),
    NotAnAttribute(0);

    private final long n;

    private NSLayoutAttribute(long n) { this.n = n; }
    public long value() { return n; }
}
