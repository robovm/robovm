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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MIDISysexSendRequest/*</name>*/ 
    extends /*<extends>*/Struct<MIDISysexSendRequest>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MIDISysexSendRequestPtr extends Ptr<MIDISysexSendRequest, MIDISysexSendRequestPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MIDISysexSendRequest() {}
    public MIDISysexSendRequest(MIDIEndpoint destination, BytePtr data, int bytesToSend, boolean complete, ByteBuffer reserved, FunctionPtr completionProc, VoidPtr completionRefCon) {
        this.destination(destination);
        this.data(data);
        this.bytesToSend(bytesToSend);
        this.complete(complete);
        this.reserved(reserved);
        this.completionProc(completionProc);
        this.completionRefCon(completionRefCon);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native MIDIEndpoint destination();
    @StructMember(0) public native MIDISysexSendRequest destination(MIDIEndpoint destination);
    @StructMember(1) public native BytePtr data();
    @StructMember(1) public native MIDISysexSendRequest data(BytePtr data);
    @StructMember(2) public native int bytesToSend();
    @StructMember(2) public native MIDISysexSendRequest bytesToSend(int bytesToSend);
    @StructMember(3) public native boolean complete();
    @StructMember(3) public native MIDISysexSendRequest complete(boolean complete);
    @StructMember(4) public native @Array({3}) ByteBuffer reserved();
    @StructMember(4) public native MIDISysexSendRequest reserved(@Array({3}) ByteBuffer reserved);
    @StructMember(5) public native FunctionPtr completionProc();
    @StructMember(5) public native MIDISysexSendRequest completionProc(FunctionPtr completionProc);
    @StructMember(6) public native VoidPtr completionRefCon();
    @StructMember(6) public native MIDISysexSendRequest completionRefCon(VoidPtr completionRefCon);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
