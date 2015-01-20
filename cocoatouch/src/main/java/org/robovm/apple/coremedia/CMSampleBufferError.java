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
public enum /*<name>*/CMSampleBufferError/*</name>*/ implements ValuedEnum {
    No(0L),
    /*<values>*/
    AllocationFailed(-12730L),
    RequiredParameterMissing(-12731L),
    AlreadyHasDataBuffer(-12732L),
    BufferNotReady(-12733L),
    SampleIndexOutOfRange(-12734L),
    BufferHasNoSampleSizes(-12735L),
    BufferHasNoSampleTimingInfo(-12736L),
    ArrayTooSmall(-12737L),
    InvalidEntryCount(-12738L),
    CannotSubdivide(-12739L),
    SampleTimingInfoInvalid(-12740L),
    InvalidMediaTypeForOperation(-12741L),
    InvalidSampleData(-12742L),
    InvalidMediaFormat(-12743L),
    Invalidated(-12744L),
    DataFailed(-12745L),
    DataCanceled(-12746L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/CMSampleBufferError/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/CMSampleBufferError/*</name>*/ valueOf(long n) {
        for (/*<name>*/CMSampleBufferError/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/CMSampleBufferError/*</name>*/.class.getName());
    }
}
