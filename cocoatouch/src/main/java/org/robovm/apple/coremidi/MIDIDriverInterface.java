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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MIDIDriverInterface/*</name>*/ 
    extends /*<extends>*/Struct<MIDIDriverInterface>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MIDIDriverInterfacePtr extends Ptr<MIDIDriverInterface, MIDIDriverInterfacePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(MIDIDriverInterface.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MIDIDriverInterface() {}
    public MIDIDriverInterface(VoidPtr _reserved, FunctionPtr QueryInterface, FunctionPtr AddRef, FunctionPtr Release, FunctionPtr FindDevices, FunctionPtr Start, FunctionPtr Stop, FunctionPtr Configure, FunctionPtr Send, FunctionPtr EnableSource, FunctionPtr Flush, FunctionPtr Monitor) {
        this._reserved(_reserved);
        this.QueryInterface(QueryInterface);
        this.AddRef(AddRef);
        this.Release(Release);
        this.FindDevices(FindDevices);
        this.Start(Start);
        this.Stop(Stop);
        this.Configure(Configure);
        this.Send(Send);
        this.EnableSource(EnableSource);
        this.Flush(Flush);
        this.Monitor(Monitor);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native VoidPtr _reserved();
    @StructMember(0) public native MIDIDriverInterface _reserved(VoidPtr _reserved);
    @StructMember(1) public native FunctionPtr QueryInterface();
    @StructMember(1) public native MIDIDriverInterface QueryInterface(FunctionPtr QueryInterface);
    @StructMember(2) public native FunctionPtr AddRef();
    @StructMember(2) public native MIDIDriverInterface AddRef(FunctionPtr AddRef);
    @StructMember(3) public native FunctionPtr Release();
    @StructMember(3) public native MIDIDriverInterface Release(FunctionPtr Release);
    @StructMember(4) public native FunctionPtr FindDevices();
    @StructMember(4) public native MIDIDriverInterface FindDevices(FunctionPtr FindDevices);
    @StructMember(5) public native FunctionPtr Start();
    @StructMember(5) public native MIDIDriverInterface Start(FunctionPtr Start);
    @StructMember(6) public native FunctionPtr Stop();
    @StructMember(6) public native MIDIDriverInterface Stop(FunctionPtr Stop);
    @StructMember(7) public native FunctionPtr Configure();
    @StructMember(7) public native MIDIDriverInterface Configure(FunctionPtr Configure);
    @StructMember(8) public native FunctionPtr Send();
    @StructMember(8) public native MIDIDriverInterface Send(FunctionPtr Send);
    @StructMember(9) public native FunctionPtr EnableSource();
    @StructMember(9) public native MIDIDriverInterface EnableSource(FunctionPtr EnableSource);
    @StructMember(10) public native FunctionPtr Flush();
    @StructMember(10) public native MIDIDriverInterface Flush(FunctionPtr Flush);
    @StructMember(11) public native FunctionPtr Monitor();
    @StructMember(11) public native MIDIDriverInterface Monitor(FunctionPtr Monitor);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
