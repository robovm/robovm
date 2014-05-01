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
package org.robovm.apple.corefoundation;

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
import org.robovm.apple.dispatch.*;
/*</imports>*/

/**
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/CFRunLoopActivity/*</name>*/ extends Bits</*<name>*/CFRunLoopActivity/*</name>*/> {
    /*<values>*/
    public static final CFRunLoopActivity Entry = new CFRunLoopActivity(1L);
    public static final CFRunLoopActivity BeforeTimers = new CFRunLoopActivity(2L);
    public static final CFRunLoopActivity BeforeSources = new CFRunLoopActivity(4L);
    public static final CFRunLoopActivity BeforeWaiting = new CFRunLoopActivity(32L);
    public static final CFRunLoopActivity AfterWaiting = new CFRunLoopActivity(64L);
    public static final CFRunLoopActivity Exit = new CFRunLoopActivity(128L);
    public static final CFRunLoopActivity AllActivities = new CFRunLoopActivity(268435455L);
    /*</values>*/

    private static final /*<name>*/CFRunLoopActivity/*</name>*/[] values = _values(/*<name>*/CFRunLoopActivity/*</name>*/.class);

    public /*<name>*/CFRunLoopActivity/*</name>*/(long value) { super(value); }
    private /*<name>*/CFRunLoopActivity/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/CFRunLoopActivity/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/CFRunLoopActivity/*</name>*/(value, mask);
    }
    protected /*<name>*/CFRunLoopActivity/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/CFRunLoopActivity/*</name>*/[] values() {
        return values.clone();
    }
}
