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
package org.robovm.apple.coregraphics;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
public enum /*<name>*/CGTextDrawingMode/*</name>*/ implements ValuedEnum {
    /*<values>*/
    Fill(0L),
    Stroke(1L),
    FillStroke(2L),
    Invisible(3L),
    FillClip(4L),
    StrokeClip(5L),
    FillStrokeClip(6L),
    Clip(7L);
    /*</values>*/

    private final long n;

    private /*<name>*/CGTextDrawingMode/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/CGTextDrawingMode/*</name>*/ valueOf(long n) {
        for (/*<name>*/CGTextDrawingMode/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/CGTextDrawingMode/*</name>*/.class.getName());
    }
}
