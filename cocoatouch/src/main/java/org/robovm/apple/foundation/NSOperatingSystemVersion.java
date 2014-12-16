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
        this.setMajorversion(majorVersion);
        this.setMinorversion(minorVersion);
        this.setPatchversion(patchVersion);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @MachineSizedSInt long getMajorversion();
    @StructMember(0) public native NSOperatingSystemVersion setMajorversion(@MachineSizedSInt long majorVersion);
    
    @Deprecated
    @StructMember(0) public native @MachineSizedSInt long majorVersion();
    @Deprecated
    @StructMember(0) public native NSOperatingSystemVersion majorVersion(@MachineSizedSInt long majorVersion);
    
    @StructMember(1) public native @MachineSizedSInt long getMinorversion();
    @StructMember(1) public native NSOperatingSystemVersion setMinorversion(@MachineSizedSInt long minorVersion);
    
    @Deprecated
    @StructMember(1) public native @MachineSizedSInt long minorVersion();
    @Deprecated
    @StructMember(1) public native NSOperatingSystemVersion minorVersion(@MachineSizedSInt long minorVersion);
    
    @StructMember(2) public native @MachineSizedSInt long getPatchversion();
    @StructMember(2) public native NSOperatingSystemVersion setPatchversion(@MachineSizedSInt long patchVersion);
    
    @Deprecated
    @StructMember(2) public native @MachineSizedSInt long patchVersion();
    @Deprecated
    @StructMember(2) public native NSOperatingSystemVersion patchVersion(@MachineSizedSInt long patchVersion);
    
    /*</members>*/
    /*<methods>*//*</methods>*/
}
