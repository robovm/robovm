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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/NSURLBookmarkCreationOptions/*</name>*/ extends Bits</*<name>*/NSURLBookmarkCreationOptions/*</name>*/> {
    /*<values>*/
    /**
     * @since Available in iOS 4.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static final NSURLBookmarkCreationOptions PreferFileIDResolution = new NSURLBookmarkCreationOptions(256L);
    public static final NSURLBookmarkCreationOptions MinimalBookmark = new NSURLBookmarkCreationOptions(512L);
    public static final NSURLBookmarkCreationOptions SuitableForBookmarkFile = new NSURLBookmarkCreationOptions(1024L);
    public static final NSURLBookmarkCreationOptions WithSecurityScope = new NSURLBookmarkCreationOptions(2048L);
    public static final NSURLBookmarkCreationOptions SecurityScopeAllowOnlyReadAccess = new NSURLBookmarkCreationOptions(4096L);
    /*</values>*/

    private static final /*<name>*/NSURLBookmarkCreationOptions/*</name>*/[] values = _values(/*<name>*/NSURLBookmarkCreationOptions/*</name>*/.class);

    public /*<name>*/NSURLBookmarkCreationOptions/*</name>*/(long value) { super(value); }
    private /*<name>*/NSURLBookmarkCreationOptions/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/NSURLBookmarkCreationOptions/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/NSURLBookmarkCreationOptions/*</name>*/(value, mask);
    }
    protected /*<name>*/NSURLBookmarkCreationOptions/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/NSURLBookmarkCreationOptions/*</name>*/[] values() {
        return values.clone();
    }
}
