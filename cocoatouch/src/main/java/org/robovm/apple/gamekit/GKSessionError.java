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
package org.robovm.apple.gamekit;

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
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedSIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/GKSessionError/*</name>*/ implements ValuedEnum {
    /*<values>*/
    InvalidParameter(30500L),
    PeerNotFound(30501L),
    Declined(30502L),
    TimedOut(30503L),
    Cancelled(30504L),
    ConnectionFailed(30505L),
    ConnectionClosed(30506L),
    DataTooBig(30507L),
    NotConnected(30508L),
    CannotEnable(30509L),
    InProgress(30510L),
    Connectivity(30201L),
    Transport(30202L),
    Internal(30203L),
    Unknown(30204L),
    System(30205L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/GKSessionError/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/GKSessionError/*</name>*/ valueOf(long n) {
        for (/*<name>*/GKSessionError/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/GKSessionError/*</name>*/.class.getName());
    }
}
