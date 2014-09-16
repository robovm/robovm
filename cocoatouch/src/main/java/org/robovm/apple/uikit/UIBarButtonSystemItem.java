/*
 * Copyright (C) 2014 Trillian Mobile AB
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
package org.robovm.apple.uikit;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedSIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/UIBarButtonSystemItem/*</name>*/ implements ValuedEnum {
    /*<values>*/
    Done(0L),
    Cancel(1L),
    Edit(2L),
    Save(3L),
    Add(4L),
    FlexibleSpace(5L),
    FixedSpace(6L),
    Compose(7L),
    Reply(8L),
    Action(9L),
    Organize(10L),
    Bookmarks(11L),
    Search(12L),
    Refresh(13L),
    Stop(14L),
    Camera(15L),
    Trash(16L),
    Play(17L),
    Pause(18L),
    Rewind(19L),
    FastForward(20L),
    Undo(21L),
    Redo(22L),
    PageCurl(23L);
    /*</values>*/

    private final long n;

    private /*<name>*/UIBarButtonSystemItem/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/UIBarButtonSystemItem/*</name>*/ valueOf(long n) {
        for (/*<name>*/UIBarButtonSystemItem/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/UIBarButtonSystemItem/*</name>*/.class.getName());
    }
}
