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

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MTLClearColor/*</name>*/ 
    extends /*<extends>*/Struct<MTLClearColor>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MTLClearColorPtr extends Ptr<MTLClearColor, MTLClearColorPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MTLClearColor() {}
    public MTLClearColor(double red, double green, double blue, double alpha) {
        this.red(red);
        this.green(green);
        this.blue(blue);
        this.alpha(alpha);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native double red();
    @StructMember(0) public native MTLClearColor red(double red);
    @StructMember(1) public native double green();
    @StructMember(1) public native MTLClearColor green(double green);
    @StructMember(2) public native double blue();
    @StructMember(2) public native MTLClearColor blue(double blue);
    @StructMember(3) public native double alpha();
    @StructMember(3) public native MTLClearColor alpha(double alpha);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
