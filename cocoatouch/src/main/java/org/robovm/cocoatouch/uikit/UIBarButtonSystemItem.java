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

import org.robovm.rt.bro.ValuedEnum;

public enum UIBarButtonSystemItem implements ValuedEnum {
    Done(0),
    Cancel(1),
    Edit(2),
    Save(3),
    Add(4),
    FlexibleSpace(5),
    FixedSpace(6),
    Compose(7),
    Reply(8),
    Action(9),
    Organize(10),
    Bookmarks(11),
    Search(12),
    Refresh(13),
    Stop(14),
    Camera(15),
    Trash(16),
    Play(17),
    Pause(18),
    Rewind(19),
    FastForward(20),
    Undo(21),
    Redo(22),
    PageCurl(23);

    private final int n;

    private UIBarButtonSystemItem(int n) { this.n = n; }
    public int value() { return n; }
}
