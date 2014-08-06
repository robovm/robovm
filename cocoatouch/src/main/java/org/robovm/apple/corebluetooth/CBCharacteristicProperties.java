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
package org.robovm.apple.corebluetooth;

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
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/CBCharacteristicProperties/*</name>*/ extends Bits</*<name>*/CBCharacteristicProperties/*</name>*/> {
    /*<values>*/
    public static final CBCharacteristicProperties Broadcast = new CBCharacteristicProperties(1L);
    public static final CBCharacteristicProperties Read = new CBCharacteristicProperties(2L);
    public static final CBCharacteristicProperties WriteWithoutResponse = new CBCharacteristicProperties(4L);
    public static final CBCharacteristicProperties Write = new CBCharacteristicProperties(8L);
    public static final CBCharacteristicProperties Notify = new CBCharacteristicProperties(16L);
    public static final CBCharacteristicProperties Indicate = new CBCharacteristicProperties(32L);
    public static final CBCharacteristicProperties AuthenticatedSignedWrites = new CBCharacteristicProperties(64L);
    public static final CBCharacteristicProperties ExtendedProperties = new CBCharacteristicProperties(128L);
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final CBCharacteristicProperties NotifyEncryptionRequired = new CBCharacteristicProperties(256L);
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final CBCharacteristicProperties IndicateEncryptionRequired = new CBCharacteristicProperties(512L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/CBCharacteristicProperties/*</name>*/[] values = _values(/*<name>*/CBCharacteristicProperties/*</name>*/.class);

    public /*<name>*/CBCharacteristicProperties/*</name>*/(long value) { super(value); }
    private /*<name>*/CBCharacteristicProperties/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/CBCharacteristicProperties/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/CBCharacteristicProperties/*</name>*/(value, mask);
    }
    protected /*<name>*/CBCharacteristicProperties/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/CBCharacteristicProperties/*</name>*/[] values() {
        return values.clone();
    }
}
