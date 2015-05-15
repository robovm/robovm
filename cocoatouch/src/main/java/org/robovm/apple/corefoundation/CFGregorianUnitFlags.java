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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/CFGregorianUnitFlags/*</name>*/ extends Bits</*<name>*/CFGregorianUnitFlags/*</name>*/> {
    /*<values>*/
    public static final CFGregorianUnitFlags None = new CFGregorianUnitFlags(0L);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    public static final CFGregorianUnitFlags UnitsYears = new CFGregorianUnitFlags(1L);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    public static final CFGregorianUnitFlags UnitsMonths = new CFGregorianUnitFlags(2L);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    public static final CFGregorianUnitFlags UnitsDays = new CFGregorianUnitFlags(4L);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    public static final CFGregorianUnitFlags UnitsHours = new CFGregorianUnitFlags(8L);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    public static final CFGregorianUnitFlags UnitsMinutes = new CFGregorianUnitFlags(16L);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    public static final CFGregorianUnitFlags UnitsSeconds = new CFGregorianUnitFlags(32L);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    public static final CFGregorianUnitFlags AllUnits = new CFGregorianUnitFlags(16777215L);
    /*</values>*/

    private static final /*<name>*/CFGregorianUnitFlags/*</name>*/[] values = _values(/*<name>*/CFGregorianUnitFlags/*</name>*/.class);

    public /*<name>*/CFGregorianUnitFlags/*</name>*/(long value) { super(value); }
    private /*<name>*/CFGregorianUnitFlags/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/CFGregorianUnitFlags/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/CFGregorianUnitFlags/*</name>*/(value, mask);
    }
    protected /*<name>*/CFGregorianUnitFlags/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/CFGregorianUnitFlags/*</name>*/[] values() {
        return values.clone();
    }
}
