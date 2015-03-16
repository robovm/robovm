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
package org.robovm.apple.audiotoolbox;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
public enum /*<name>*/AudioFileError/*</name>*/ implements ValuedEnum {
	No(0L),
    /*<values>*/
    Unspecified(2003334207L),
    UnsupportedFileType(1954115647L),
    UnsupportedDataFormat(1718449215L),
    UnsupportedProperty(1886681407L),
    BadPropertySize(561211770L),
    Permissions(1886547263L),
    NotOptimized(1869640813L),
    InvalidChunk(1667787583L),
    DoesNotAllow64BitDataSize(1868981823L),
    InvalidPacketOffset(1885563711L),
    InvalidFile(1685348671L),
    OperationNotSupported(1869627199L),
    NotOpen(-38L),
    EndOfFile(-39L),
    Position(-40L),
    FileNotFound(-43L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/AudioFileError/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/AudioFileError/*</name>*/ valueOf(long n) {
        for (/*<name>*/AudioFileError/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/AudioFileError/*</name>*/.class.getName());
    }
}
