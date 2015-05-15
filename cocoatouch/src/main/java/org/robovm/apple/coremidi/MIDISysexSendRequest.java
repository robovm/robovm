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
        this.setDestination(destination);
        this.setData(data);
        this.setBytesToSend(bytesToSend);
        this.setComplete(complete);
        this.setReserved(reserved);
        this.setCompletionProc(completionProc);
        this.setCompletionRefCon(completionRefCon);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native MIDIEndpoint getDestination();
    @StructMember(0) public native MIDISysexSendRequest setDestination(MIDIEndpoint destination);
    @StructMember(1) public native BytePtr getData();
    @StructMember(1) public native MIDISysexSendRequest setData(BytePtr data);
    @StructMember(2) public native int getBytesToSend();
    @StructMember(2) public native MIDISysexSendRequest setBytesToSend(int bytesToSend);
    @StructMember(3) public native boolean isComplete();
    @StructMember(3) public native MIDISysexSendRequest setComplete(boolean complete);
    @StructMember(4) public native @Array({3}) ByteBuffer getReserved();
    @StructMember(4) public native MIDISysexSendRequest setReserved(@Array({3}) ByteBuffer reserved);
    @StructMember(5) public native FunctionPtr getCompletionProc();
    @StructMember(5) public native MIDISysexSendRequest setCompletionProc(FunctionPtr completionProc);
    @StructMember(6) public native VoidPtr getCompletionRefCon();
    @StructMember(6) public native MIDISysexSendRequest setCompletionRefCon(VoidPtr completionRefCon);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
