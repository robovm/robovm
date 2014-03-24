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

import org.robovm.rt.bro.Bits;

public final class UITableViewCellStateMask extends Bits<UITableViewCellStateMask> {
    public static final UITableViewCellStateMask DefaultMask = new UITableViewCellStateMask(0);
    public static final UITableViewCellStateMask ShowingEditControlMask = new UITableViewCellStateMask(1 << 0);
    public static final UITableViewCellStateMask ShowingDeleteConfirmationMask = new UITableViewCellStateMask(1 << 1);

    private static final UITableViewCellStateMask[] values = _values(UITableViewCellStateMask.class);
    
    private UITableViewCellStateMask(long value) { super(value); }
    private UITableViewCellStateMask(long value, long mask) { super(value, mask); }
    protected UITableViewCellStateMask wrap(long value, long mask) {
        return new UITableViewCellStateMask(value, mask);
    }
    protected UITableViewCellStateMask[] _values() {
        return values;
    }
}
