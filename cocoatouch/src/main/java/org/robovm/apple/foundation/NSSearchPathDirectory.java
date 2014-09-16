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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
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
    /**
     * @since Available in iOS 4.0 and later.
     */
    AutosavedInformationDirectory(11L),
    DesktopDirectory(12L),
    CachesDirectory(13L),
    ApplicationSupportDirectory(14L),
    /**
     * @since Available in iOS 2.0 and later.
     */
    DownloadsDirectory(15L),
    /**
     * @since Available in iOS 4.0 and later.
     */
    InputMethodsDirectory(16L),
    /**
     * @since Available in iOS 4.0 and later.
     */
    MoviesDirectory(17L),
    /**
     * @since Available in iOS 4.0 and later.
     */
    MusicDirectory(18L),
    /**
     * @since Available in iOS 4.0 and later.
     */
    PicturesDirectory(19L),
    /**
     * @since Available in iOS 4.0 and later.
     */
    PrinterDescriptionDirectory(20L),
    /**
     * @since Available in iOS 4.0 and later.
     */
    SharedPublicDirectory(21L),
    /**
     * @since Available in iOS 4.0 and later.
     */
    PreferencePanesDirectory(22L),
    ApplicationScriptsDirectory(23L),
    /**
     * @since Available in iOS 4.0 and later.
     */
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
