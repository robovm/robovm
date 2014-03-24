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
package org.robovm.apple.foundation;

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
import org.robovm.apple.security.*;
/*</imports>*/

/**
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedUIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/NSSearchPathDirectory/*</name>*/ implements ValuedEnum {
    /*<values>*/
    ApplicationDirectory(1L),
    DemoApplicationDirectory(2L),
    DeveloperApplicationDirectory(3L),
    AdminApplicationDirectory(4L),
    LibraryDirectory(5L),
    DeveloperDirectory(6L),
    UserDirectory(7L),
    DocumentationDirectory(8L),
    DocumentDirectory(9L),
    CoreServiceDirectory(10L),
    AutosavedInformationDirectory(11L),
    DesktopDirectory(12L),
    CachesDirectory(13L),
    ApplicationSupportDirectory(14L),
    DownloadsDirectory(15L),
    InputMethodsDirectory(16L),
    MoviesDirectory(17L),
    MusicDirectory(18L),
    PicturesDirectory(19L),
    PrinterDescriptionDirectory(20L),
    SharedPublicDirectory(21L),
    PreferencePanesDirectory(22L),
    ApplicationScriptsDirectory(23L),
    ItemReplacementDirectory(99L),
    AllApplicationsDirectory(100L),
    AllLibrariesDirectory(101L),
    TrashDirectory(102L);
    /*</values>*/

    private final long n;

    private /*<name>*/NSSearchPathDirectory/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/NSSearchPathDirectory/*</name>*/ valueOf(long n) {
        for (/*<name>*/NSSearchPathDirectory/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/NSSearchPathDirectory/*</name>*/.class.getName());
    }
}
