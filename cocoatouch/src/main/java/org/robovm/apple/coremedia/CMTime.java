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
package org.robovm.apple.coremedia;

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
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMTime/*</name>*/ 
    extends /*<extends>*/Struct<CMTime>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CMTimePtr extends Ptr<CMTime, CMTimePtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CMTime() {}
    public CMTime(long value, int timescale, int flags, long epoch) {
        this.value(value);
        this.timescale(timescale);
        this.flags(flags);
        this.epoch(epoch);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native long value();
    @StructMember(0) public native CMTime value(long value);
    @StructMember(1) public native int timescale();
    @StructMember(1) public native CMTime timescale(int timescale);
    @StructMember(2) public native int flags();
    @StructMember(2) public native CMTime flags(int flags);
    @StructMember(3) public native long epoch();
    @StructMember(3) public native CMTime epoch(long epoch);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
