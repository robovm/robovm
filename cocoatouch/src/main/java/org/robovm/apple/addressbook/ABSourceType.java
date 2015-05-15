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
package org.robovm.apple.addressbook;

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
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
public final class /*<name>*/ABSourceType/*</name>*/ extends Bits</*<name>*/ABSourceType/*</name>*/> {
    /*<values>*/
    public static final ABSourceType Local = new ABSourceType(0L);
    public static final ABSourceType Exchange = new ABSourceType(1L);
    public static final ABSourceType ExchangeGAL = new ABSourceType(16777217L);
    public static final ABSourceType MobileMe = new ABSourceType(2L);
    public static final ABSourceType LDAP = new ABSourceType(16777219L);
    public static final ABSourceType CardDAV = new ABSourceType(4L);
    public static final ABSourceType CardDAVSearch = new ABSourceType(16777220L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/ABSourceType/*</name>*/[] values = _values(/*<name>*/ABSourceType/*</name>*/.class);

    public /*<name>*/ABSourceType/*</name>*/(long value) { super(value); }
    private /*<name>*/ABSourceType/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/ABSourceType/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/ABSourceType/*</name>*/(value, mask);
    }
    protected /*<name>*/ABSourceType/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/ABSourceType/*</name>*/[] values() {
        return values.clone();
    }
}
