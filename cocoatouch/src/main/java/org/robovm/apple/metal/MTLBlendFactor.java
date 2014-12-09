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
package org.robovm.apple.metal;

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
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedUIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/MTLBlendFactor/*</name>*/ implements ValuedEnum {
    /*<values>*/
    Zero(0L),
    One(1L),
    SourceColor(2L),
    OneMinusSourceColor(3L),
    SourceAlpha(4L),
    OneMinusSourceAlpha(5L),
    DestinationColor(6L),
    OneMinusDestinationColor(7L),
    DestinationAlpha(8L),
    OneMinusDestinationAlpha(9L),
    SourceAlphaSaturated(10L),
    BlendColor(11L),
    OneMinusBlendColor(12L),
    BlendAlpha(13L),
    OneMinusBlendAlpha(14L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/MTLBlendFactor/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/MTLBlendFactor/*</name>*/ valueOf(long n) {
        for (/*<name>*/MTLBlendFactor/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/MTLBlendFactor/*</name>*/.class.getName());
    }
}
