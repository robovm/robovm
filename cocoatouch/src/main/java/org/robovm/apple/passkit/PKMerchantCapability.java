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
package org.robovm.apple.passkit;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.addressbook.*;
import org.robovm.apple.contacts.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/PKMerchantCapability/*</name>*/ extends Bits</*<name>*/PKMerchantCapability/*</name>*/> {
    /*<values>*/
    public static final PKMerchantCapability None = new PKMerchantCapability(0L);
    public static final PKMerchantCapability _3DS = new PKMerchantCapability(1L);
    public static final PKMerchantCapability EMV = new PKMerchantCapability(2L);
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final PKMerchantCapability Credit = new PKMerchantCapability(4L);
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final PKMerchantCapability Debit = new PKMerchantCapability(8L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/PKMerchantCapability/*</name>*/[] values = _values(/*<name>*/PKMerchantCapability/*</name>*/.class);

    public /*<name>*/PKMerchantCapability/*</name>*/(long value) { super(value); }
    private /*<name>*/PKMerchantCapability/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/PKMerchantCapability/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/PKMerchantCapability/*</name>*/(value, mask);
    }
    protected /*<name>*/PKMerchantCapability/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/PKMerchantCapability/*</name>*/[] values() {
        return values.clone();
    }
}
