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
package org.robovm.apple.coremedia;

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
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
public enum /*<name>*/CMBufferQueueTriggerCondition/*</name>*/ implements ValuedEnum {
    /*<values>*/
    WhenDurationBecomesLessThan(1L),
    WhenDurationBecomesLessThanOrEqualTo(2L),
    WhenDurationBecomesGreaterThan(3L),
    WhenDurationBecomesGreaterThanOrEqualTo(4L),
    WhenMinPresentationTimeStampChanges(5L),
    WhenMaxPresentationTimeStampChanges(6L),
    WhenDataBecomesReady(7L),
    WhenEndOfDataReached(8L),
    WhenReset(9L),
    WhenBufferCountBecomesLessThan(10L),
    WhenBufferCountBecomesGreaterThan(11L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/CMBufferQueueTriggerCondition/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/CMBufferQueueTriggerCondition/*</name>*/ valueOf(long n) {
        for (/*<name>*/CMBufferQueueTriggerCondition/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/CMBufferQueueTriggerCondition/*</name>*/.class.getName());
    }
}
