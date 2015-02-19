/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
package org.robovm.apple.assetslibrary;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.imageio.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
@ForceLinkClass(ALAssetsLibraryError.class)
/*<annotations>*//*</annotations>*/
public enum /*<name>*/ALAssetsLibraryErrorCode/*</name>*/ implements NSErrorCode {
    /*<values>*/
    Unknown(-1L),
    WriteFailed(-3300L),
    WriteBusy(-3301L),
    WriteInvalidData(-3302L),
    WriteIncompatibleData(-3303L),
    WriteDataEncoding(-3304L),
    WriteDiskSpace(-3305L),
    DataUnavailable(-3310L),
    AccessUserDenied(-3311L),
    AccessGloballyDenied(-3312L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/ALAssetsLibraryErrorCode/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/ALAssetsLibraryErrorCode/*</name>*/ valueOf(long n) {
        for (/*<name>*/ALAssetsLibraryErrorCode/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/ALAssetsLibraryErrorCode/*</name>*/.class.getName());
    }
}
