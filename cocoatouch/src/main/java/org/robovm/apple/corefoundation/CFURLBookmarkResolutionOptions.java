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
public final class /*<name>*/CFURLBookmarkResolutionOptions/*</name>*/ extends Bits</*<name>*/CFURLBookmarkResolutionOptions/*</name>*/> {
    /*<values>*/
    public static final CFURLBookmarkResolutionOptions WithoutUIMask = new CFURLBookmarkResolutionOptions(256L);
    public static final CFURLBookmarkResolutionOptions WithoutMountingMask = new CFURLBookmarkResolutionOptions(512L);
    public static final CFURLBookmarkResolutionOptions WithSecurityScope = new CFURLBookmarkResolutionOptions(1024L);
    /*</values>*/

    private static final /*<name>*/CFURLBookmarkResolutionOptions/*</name>*/[] values = _values(/*<name>*/CFURLBookmarkResolutionOptions/*</name>*/.class);

    public /*<name>*/CFURLBookmarkResolutionOptions/*</name>*/(long value) { super(value); }
    private /*<name>*/CFURLBookmarkResolutionOptions/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/CFURLBookmarkResolutionOptions/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/CFURLBookmarkResolutionOptions/*</name>*/(value, mask);
    }
    protected /*<name>*/CFURLBookmarkResolutionOptions/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/CFURLBookmarkResolutionOptions/*</name>*/[] values() {
        return values.clone();
    }
}
