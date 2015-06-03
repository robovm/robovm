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
package org.robovm.apple.systemconfiguration;

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
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/SCNetworkReachabilityFlags/*</name>*/ extends Bits</*<name>*/SCNetworkReachabilityFlags/*</name>*/> {
    /*<values>*/
    public static final SCNetworkReachabilityFlags None = new SCNetworkReachabilityFlags(0L);
    public static final SCNetworkReachabilityFlags TransientConnection = new SCNetworkReachabilityFlags(1L);
    public static final SCNetworkReachabilityFlags Reachable = new SCNetworkReachabilityFlags(2L);
    public static final SCNetworkReachabilityFlags ConnectionRequired = new SCNetworkReachabilityFlags(4L);
    public static final SCNetworkReachabilityFlags ConnectionOnTraffic = new SCNetworkReachabilityFlags(8L);
    public static final SCNetworkReachabilityFlags InterventionRequired = new SCNetworkReachabilityFlags(16L);
    public static final SCNetworkReachabilityFlags ConnectionOnDemand = new SCNetworkReachabilityFlags(32L);
    public static final SCNetworkReachabilityFlags IsLocalAddress = new SCNetworkReachabilityFlags(65536L);
    public static final SCNetworkReachabilityFlags IsDirect = new SCNetworkReachabilityFlags(131072L);
    public static final SCNetworkReachabilityFlags IsWWAN = new SCNetworkReachabilityFlags(262144L);
    public static final SCNetworkReachabilityFlags ConnectionAutomatic = new SCNetworkReachabilityFlags(8L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/SCNetworkReachabilityFlags/*</name>*/[] values = _values(/*<name>*/SCNetworkReachabilityFlags/*</name>*/.class);

    public /*<name>*/SCNetworkReachabilityFlags/*</name>*/(long value) { super(value); }
    private /*<name>*/SCNetworkReachabilityFlags/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/SCNetworkReachabilityFlags/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/SCNetworkReachabilityFlags/*</name>*/(value, mask);
    }
    protected /*<name>*/SCNetworkReachabilityFlags/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/SCNetworkReachabilityFlags/*</name>*/[] values() {
        return values.clone();
    }
}
