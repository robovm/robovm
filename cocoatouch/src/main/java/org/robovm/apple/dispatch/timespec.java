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
package org.robovm.apple.dispatch;

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
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*//*</visibility>*/ class /*<name>*/timespec/*</name>*/ 
    extends /*<extends>*/Struct<timespec>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class timespecPtr extends Ptr<timespec, timespecPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public timespec() {}
    public timespec(@MachineSizedSInt long tv_sec, @MachineSizedSInt long tv_nsec) {
        this.tv_sec(tv_sec);
        this.tv_nsec(tv_nsec);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @MachineSizedSInt long tv_sec();
    @StructMember(0) public native timespec tv_sec(@MachineSizedSInt long tv_sec);
    @StructMember(1) public native @MachineSizedSInt long tv_nsec();
    @StructMember(1) public native timespec tv_nsec(@MachineSizedSInt long tv_nsec);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
