/*
 * Copyright (C) 2013-2015 RoboVM AB
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
package org.robovm.apple.coredata;

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
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/NSSnapshotEventType/*</name>*/ extends Bits</*<name>*/NSSnapshotEventType/*</name>*/> {
    /*<values>*/
    public static final NSSnapshotEventType None = new NSSnapshotEventType(0L);
    public static final NSSnapshotEventType UndoInsertion = new NSSnapshotEventType(2L);
    public static final NSSnapshotEventType UndoDeletion = new NSSnapshotEventType(4L);
    public static final NSSnapshotEventType UndoUpdate = new NSSnapshotEventType(8L);
    public static final NSSnapshotEventType Rollback = new NSSnapshotEventType(16L);
    public static final NSSnapshotEventType Refresh = new NSSnapshotEventType(32L);
    public static final NSSnapshotEventType MergePolicy = new NSSnapshotEventType(64L);
    /*</values>*/

    private static final /*<name>*/NSSnapshotEventType/*</name>*/[] values = _values(/*<name>*/NSSnapshotEventType/*</name>*/.class);

    public /*<name>*/NSSnapshotEventType/*</name>*/(long value) { super(value); }
    private /*<name>*/NSSnapshotEventType/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/NSSnapshotEventType/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/NSSnapshotEventType/*</name>*/(value, mask);
    }
    protected /*<name>*/NSSnapshotEventType/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/NSSnapshotEventType/*</name>*/[] values() {
        return values.clone();
    }
}
