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
package org.robovm.cocoatouch.foundation;

import org.robovm.rt.bro.Bits;

public final class NSStringEnumerationOptions extends Bits<NSStringEnumerationOptions> {
    public static final NSStringEnumerationOptions ByLines = new NSStringEnumerationOptions(0, 0xff);
    public static final NSStringEnumerationOptions ByParagraphs = new NSStringEnumerationOptions(1, 0xff);
    public static final NSStringEnumerationOptions ByComposedCharacterSequences = new NSStringEnumerationOptions(2, 0xff);
    public static final NSStringEnumerationOptions ByWords = new NSStringEnumerationOptions(3, 0xff);
    public static final NSStringEnumerationOptions BySentences = new NSStringEnumerationOptions(4, 0xff);
    public static final NSStringEnumerationOptions Reverse = new NSStringEnumerationOptions(1 << 8);
    public static final NSStringEnumerationOptions SubstringNotRequired = new NSStringEnumerationOptions(1 << 9);
    public static final NSStringEnumerationOptions Localized = new NSStringEnumerationOptions(1 << 10);

    private static final NSStringEnumerationOptions[] values = _values(NSStringEnumerationOptions.class);
    
    private NSStringEnumerationOptions(long value) { super(value); }
    private NSStringEnumerationOptions(long value, long mask) { super(value, mask); }
    protected NSStringEnumerationOptions wrap(long value, long mask) {
        return new NSStringEnumerationOptions(value, mask);
    }
    protected NSStringEnumerationOptions[] _values() {
        return values;
    }
}
