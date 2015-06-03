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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSOperatingSystemVersion/*</name>*/ 
    extends /*<extends>*/Struct<NSOperatingSystemVersion>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSOperatingSystemVersionPtr extends Ptr<NSOperatingSystemVersion, NSOperatingSystemVersionPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSOperatingSystemVersion() {}
    public NSOperatingSystemVersion(@MachineSizedSInt long majorVersion, @MachineSizedSInt long minorVersion, @MachineSizedSInt long patchVersion) {
        this.setMajorVersion(majorVersion);
        this.setMinorVersion(minorVersion);
        this.setPatchVersion(patchVersion);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @MachineSizedSInt long getMajorVersion();
    @StructMember(0) public native NSOperatingSystemVersion setMajorVersion(@MachineSizedSInt long majorVersion);
    @StructMember(1) public native @MachineSizedSInt long getMinorVersion();
    @StructMember(1) public native NSOperatingSystemVersion setMinorVersion(@MachineSizedSInt long minorVersion);
    @StructMember(2) public native @MachineSizedSInt long getPatchVersion();
    @StructMember(2) public native NSOperatingSystemVersion setPatchVersion(@MachineSizedSInt long patchVersion);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
