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

public final class NSDataWritingOptions extends Bits<NSDataWritingOptions> {
	public static final NSDataWritingOptions Atomic = new NSDataWritingOptions(1 << 0);
	public static final NSDataWritingOptions FileProtectionNone = new NSDataWritingOptions(0x10000000, 0xf0000000);
	public static final NSDataWritingOptions FileProtectionComplete = new NSDataWritingOptions(0x20000000, 0xf0000000);
	public static final NSDataWritingOptions FileProtectionCompleteUnlessOpen = new NSDataWritingOptions(0x30000000, 0xf0000000);
	public static final NSDataWritingOptions FileProtectionCompleteUntilFirstUserAuthentication = new NSDataWritingOptions(0x40000000, 0xf0000000);
//	public static final NSDataWritingOptions FileProtectionMask = new NSDataWritingOptions(0xf0000000);

    private static final NSDataWritingOptions[] values = _values(NSDataWritingOptions.class);
    
    private NSDataWritingOptions(long value) { super(value); }
    private NSDataWritingOptions(long value, long mask) { super(value, mask); }
    protected NSDataWritingOptions wrap(long value, long mask) {
        return new NSDataWritingOptions(value, mask);
    }
    protected NSDataWritingOptions[] _values() {
        return values;
    }
}
