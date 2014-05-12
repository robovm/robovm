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
package org.robovm.apple.coregraphics;

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
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
/*<annotations>*//*</annotations>*/
public final class /*<name>*/CGBitmapInfo/*</name>*/ extends Bits</*<name>*/CGBitmapInfo/*</name>*/> {
    /*<values>*/
    public static final CGBitmapInfo AlphaInfoMask = new CGBitmapInfo(31L);
    public static final CGBitmapInfo FloatComponents = new CGBitmapInfo(256L);
    public static final CGBitmapInfo ByteOrderMask = new CGBitmapInfo(28672L);
    public static final CGBitmapInfo ByteOrderDefault = new CGBitmapInfo(0L);
    public static final CGBitmapInfo ByteOrder16Little = new CGBitmapInfo(4096L);
    public static final CGBitmapInfo ByteOrder32Little = new CGBitmapInfo(8192L);
    public static final CGBitmapInfo ByteOrder16Big = new CGBitmapInfo(12288L);
    public static final CGBitmapInfo ByteOrder32Big = new CGBitmapInfo(16384L);
    /*</values>*/

    private static final /*<name>*/CGBitmapInfo/*</name>*/[] values = _values(/*<name>*/CGBitmapInfo/*</name>*/.class);

    public /*<name>*/CGBitmapInfo/*</name>*/(long value) { super(value); }
    private /*<name>*/CGBitmapInfo/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/CGBitmapInfo/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/CGBitmapInfo/*</name>*/(value, mask);
    }
    protected /*<name>*/CGBitmapInfo/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/CGBitmapInfo/*</name>*/[] values() {
        return values.clone();
    }
}
