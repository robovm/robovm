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
/*<annotations>*//*</annotations>*/
public enum /*<name>*/SCStatusCode/*</name>*/ implements ValuedEnum {
    /*<values>*/
    OK(0L),
    Failed(1001L),
    InvalidArgument(1002L),
    AccessError(1003L),
    NoKey(1004L),
    KeyExists(1005L),
    Locked(1006L),
    NeedLock(1007L),
    NoStoreSession(2001L),
    NoStoreServer(2002L),
    NotifierActive(2003L),
    NoPrefsSession(3001L),
    PrefsBusy(3002L),
    NoConfigFile(3003L),
    NoLink(3004L),
    Stale(3005L),
    MaxLink(3006L),
    ReachabilityUnknown(4001L),
    ConnectionNoService(5001L),
    ConnectionIgnore(5002L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/SCStatusCode/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/SCStatusCode/*</name>*/ valueOf(long n) {
        for (/*<name>*/SCStatusCode/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/SCStatusCode/*</name>*/.class.getName());
    }
}
