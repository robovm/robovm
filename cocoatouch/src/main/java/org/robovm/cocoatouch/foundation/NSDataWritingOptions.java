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

import org.robovm.rt.bro.IntValuedEnum;

public enum NSDataWritingOptions implements IntValuedEnum {
    Atomic(1 << 0),
    FileProtectionNone(0x10000000),
    FileProtectionComplete(0x20000000),
    FileProtectionCompleteUnlessOpen(0x30000000),
    FileProtectionCompleteUntilFirstUserAuthentication(0x40000000),
    FileProtectionMask(0xf0000000);

    private final int n;

    private NSDataWritingOptions(int n) { this.n = n; }
    public int value() { return n; }
}
