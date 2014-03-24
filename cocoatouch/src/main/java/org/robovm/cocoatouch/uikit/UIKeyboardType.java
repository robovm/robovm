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

public enum UIKeyboardType implements ValuedEnum {
    Default(0),
    ASCIICapable(1),
    NumbersAndPunctuation(2),
    URL(3),
    NumberPad(4),
    PhonePad(5),
    NamePhonePad(6),
    EmailAddress(7),
    DecimalPad(8),
    Twitter(9),
    Alphabet(UIKeyboardType.ASCIICapable.value());

    private final long n;

    private UIKeyboardType(long n) { this.n = n; }
    public long value() { return n; }
}
