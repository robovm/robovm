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
package org.robovm.apple.coreaudio;

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
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
public final class /*<name>*/AudioTimeStampFlags/*</name>*/ extends Bits</*<name>*/AudioTimeStampFlags/*</name>*/> {
    /*<values>*/
    public static final AudioTimeStampFlags None = new AudioTimeStampFlags(0L);
    public static final AudioTimeStampFlags SampleTimeValid = new AudioTimeStampFlags(1L);
    public static final AudioTimeStampFlags HostTimeValid = new AudioTimeStampFlags(2L);
    public static final AudioTimeStampFlags RateScalarValid = new AudioTimeStampFlags(4L);
    public static final AudioTimeStampFlags WordClockTimeValid = new AudioTimeStampFlags(8L);
    public static final AudioTimeStampFlags SMPTETimeValid = new AudioTimeStampFlags(16L);
    public static final AudioTimeStampFlags SampleHostTimeValid = new AudioTimeStampFlags(3L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/AudioTimeStampFlags/*</name>*/[] values = _values(/*<name>*/AudioTimeStampFlags/*</name>*/.class);

    public /*<name>*/AudioTimeStampFlags/*</name>*/(long value) { super(value); }
    private /*<name>*/AudioTimeStampFlags/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/AudioTimeStampFlags/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/AudioTimeStampFlags/*</name>*/(value, mask);
    }
    protected /*<name>*/AudioTimeStampFlags/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/AudioTimeStampFlags/*</name>*/[] values() {
        return values.clone();
    }
}
