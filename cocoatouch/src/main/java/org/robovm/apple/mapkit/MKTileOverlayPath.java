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
package org.robovm.apple.mapkit;

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
import org.robovm.apple.corelocation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MKTileOverlayPath/*</name>*/ 
    extends /*<extends>*/Struct<MKTileOverlayPath>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MKTileOverlayPathPtr extends Ptr<MKTileOverlayPath, MKTileOverlayPathPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MKTileOverlayPath() {}
    public MKTileOverlayPath(@MachineSizedSInt long x, @MachineSizedSInt long y, @MachineSizedSInt long z, @MachineSizedFloat double contentScaleFactor) {
        this.x(x);
        this.y(y);
        this.z(z);
        this.contentScaleFactor(contentScaleFactor);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @MachineSizedSInt long x();
    @StructMember(0) public native MKTileOverlayPath x(@MachineSizedSInt long x);
    @StructMember(1) public native @MachineSizedSInt long y();
    @StructMember(1) public native MKTileOverlayPath y(@MachineSizedSInt long y);
    @StructMember(2) public native @MachineSizedSInt long z();
    @StructMember(2) public native MKTileOverlayPath z(@MachineSizedSInt long z);
    @StructMember(3) public native @MachineSizedFloat double contentScaleFactor();
    @StructMember(3) public native MKTileOverlayPath contentScaleFactor(@MachineSizedFloat double contentScaleFactor);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
