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
package org.robovm.cocoatouch.uikit;

import org.robovm.rt.bro.Bits;

public final class UIDocumentState extends Bits<UIDocumentState> {
    public static final UIDocumentState Normal = new UIDocumentState(0);
    public static final UIDocumentState Closed = new UIDocumentState(1 << 0);
    public static final UIDocumentState InConflict = new UIDocumentState(1 << 1);
    public static final UIDocumentState SavingError = new UIDocumentState(1 << 2);
    public static final UIDocumentState EditingDisabled = new UIDocumentState(1 << 3);

    private static final UIDocumentState[] values = _values(UIDocumentState.class);
    
    private UIDocumentState(long value) { super(value); }
    private UIDocumentState(long value, long mask) { super(value, mask); }
    protected UIDocumentState wrap(long value, long mask) {
        return new UIDocumentState(value, mask);
    }
    protected UIDocumentState[] values() {
        return values;
    }
}
