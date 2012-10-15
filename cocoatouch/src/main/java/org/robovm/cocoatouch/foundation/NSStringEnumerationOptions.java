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
package org.robovm.cocoatouch.foundation;

import org.robovm.rt.bro.ValuedEnum;

public enum NSStringEnumerationOptions implements ValuedEnum {
    ByLines(0),
    ByParagraphs(1),
    ByComposedCharacterSequences(2),
    ByWords(3),
    BySentences(4),
    Reverse(1 << 8),
    SubstringNotRequired(1 << 9),
    Localized(1 << 10);

    private final int n;

    private NSStringEnumerationOptions(int n) { this.n = n; }
    public int value() { return n; }
}
