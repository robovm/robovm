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
package org.robovm.apple.corefoundation;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreservices.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/CFSocketFlags/*</name>*/ extends Bits</*<name>*/CFSocketFlags/*</name>*/> {
    /*<values>*/
    public static final CFSocketFlags None = new CFSocketFlags(0L);
    public static final CFSocketFlags AutomaticallyReenableReadCallBack = new CFSocketFlags(1L);
    public static final CFSocketFlags AutomaticallyReenableAcceptCallBack = new CFSocketFlags(2L);
    public static final CFSocketFlags AutomaticallyReenableDataCallBack = new CFSocketFlags(3L);
    public static final CFSocketFlags AutomaticallyReenableWriteCallBack = new CFSocketFlags(8L);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFSocketFlags LeaveErrors = new CFSocketFlags(64L);
    public static final CFSocketFlags CloseOnInvalidate = new CFSocketFlags(128L);
    /*</values>*/

    private static final /*<name>*/CFSocketFlags/*</name>*/[] values = _values(/*<name>*/CFSocketFlags/*</name>*/.class);

    public /*<name>*/CFSocketFlags/*</name>*/(long value) { super(value); }
    private /*<name>*/CFSocketFlags/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/CFSocketFlags/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/CFSocketFlags/*</name>*/(value, mask);
    }
    protected /*<name>*/CFSocketFlags/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/CFSocketFlags/*</name>*/[] values() {
        return values.clone();
    }
}
