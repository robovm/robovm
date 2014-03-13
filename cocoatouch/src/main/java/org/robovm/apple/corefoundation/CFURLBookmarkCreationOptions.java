/*
 * Copyright (C) 2014 Trillian AB
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
public final class /*<name>*/CFURLBookmarkCreationOptions/*</name>*/ extends Bits</*<name>*/CFURLBookmarkCreationOptions/*</name>*/> {
    /*<values>*/
    public static final CFURLBookmarkCreationOptions PreferFileIDResolutionMask = new CFURLBookmarkCreationOptions(256L);
    public static final CFURLBookmarkCreationOptions MinimalBookmarkMask = new CFURLBookmarkCreationOptions(512L);
    public static final CFURLBookmarkCreationOptions SuitableForBookmarkFile = new CFURLBookmarkCreationOptions(1024L);
    public static final CFURLBookmarkCreationOptions WithSecurityScope = new CFURLBookmarkCreationOptions(2048L);
    public static final CFURLBookmarkCreationOptions SecurityScopeAllowOnlyReadAccess = new CFURLBookmarkCreationOptions(4096L);
    /*</values>*/

    private static final /*<name>*/CFURLBookmarkCreationOptions/*</name>*/[] values = _values(/*<name>*/CFURLBookmarkCreationOptions/*</name>*/.class);

    public /*<name>*/CFURLBookmarkCreationOptions/*</name>*/(long value) { super(value); }
    private /*<name>*/CFURLBookmarkCreationOptions/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/CFURLBookmarkCreationOptions/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/CFURLBookmarkCreationOptions/*</name>*/(value, mask);
    }
    protected /*<name>*/CFURLBookmarkCreationOptions/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/CFURLBookmarkCreationOptions/*</name>*/[] values() {
        return values.clone();
    }
}
