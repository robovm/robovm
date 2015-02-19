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
package org.robovm.apple.coremidi;

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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MIDIThruConnectionEndpoint/*</name>*/ 
    extends /*<extends>*/Struct<MIDIThruConnectionEndpoint>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MIDIThruConnectionEndpointPtr extends Ptr<MIDIThruConnectionEndpoint, MIDIThruConnectionEndpointPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MIDIThruConnectionEndpoint() {}
    public MIDIThruConnectionEndpoint(MIDIEndpoint endpointRef, int uniqueID) {
        this.setEndpointRef(endpointRef);
        this.setUniqueID(uniqueID);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native MIDIEndpoint getEndpointRef();
    @StructMember(0) public native MIDIThruConnectionEndpoint setEndpointRef(MIDIEndpoint endpointRef);
    @StructMember(1) public native int getUniqueID();
    @StructMember(1) public native MIDIThruConnectionEndpoint setUniqueID(int uniqueID);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
