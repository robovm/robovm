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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MIDIDriverInterface/*</name>*/ 
    extends /*<extends>*/Struct<MIDIDriverInterface>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MIDIDriverInterfacePtr extends Ptr<MIDIDriverInterface, MIDIDriverInterfacePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(MIDIDriverInterface.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MIDIDriverInterface() {}
    public MIDIDriverInterface(VoidPtr _reserved, FunctionPtr QueryInterface, FunctionPtr AddRef, FunctionPtr Release, FunctionPtr FindDevices, FunctionPtr Start, FunctionPtr Stop, FunctionPtr Configure, FunctionPtr Send, FunctionPtr EnableSource, FunctionPtr Flush, FunctionPtr Monitor) {
        this.set_reserved(_reserved);
        this.setQueryInterface(QueryInterface);
        this.setAddRef(AddRef);
        this.setRelease(Release);
        this.setFindDevices(FindDevices);
        this.setStart(Start);
        this.setStop(Stop);
        this.setConfigure(Configure);
        this.setSend(Send);
        this.setEnableSource(EnableSource);
        this.setFlush(Flush);
        this.setMonitor(Monitor);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native VoidPtr get_reserved();
    @StructMember(0) public native MIDIDriverInterface set_reserved(VoidPtr _reserved);
    @StructMember(1) public native FunctionPtr getQueryInterface();
    @StructMember(1) public native MIDIDriverInterface setQueryInterface(FunctionPtr QueryInterface);
    @StructMember(2) public native FunctionPtr getAddRef();
    @StructMember(2) public native MIDIDriverInterface setAddRef(FunctionPtr AddRef);
    @StructMember(3) public native FunctionPtr getRelease();
    @StructMember(3) public native MIDIDriverInterface setRelease(FunctionPtr Release);
    @StructMember(4) public native FunctionPtr getFindDevices();
    @StructMember(4) public native MIDIDriverInterface setFindDevices(FunctionPtr FindDevices);
    @StructMember(5) public native FunctionPtr getStart();
    @StructMember(5) public native MIDIDriverInterface setStart(FunctionPtr Start);
    @StructMember(6) public native FunctionPtr getStop();
    @StructMember(6) public native MIDIDriverInterface setStop(FunctionPtr Stop);
    @StructMember(7) public native FunctionPtr getConfigure();
    @StructMember(7) public native MIDIDriverInterface setConfigure(FunctionPtr Configure);
    @StructMember(8) public native FunctionPtr getSend();
    @StructMember(8) public native MIDIDriverInterface setSend(FunctionPtr Send);
    @StructMember(9) public native FunctionPtr getEnableSource();
    @StructMember(9) public native MIDIDriverInterface setEnableSource(FunctionPtr EnableSource);
    @StructMember(10) public native FunctionPtr getFlush();
    @StructMember(10) public native MIDIDriverInterface setFlush(FunctionPtr Flush);
    @StructMember(11) public native FunctionPtr getMonitor();
    @StructMember(11) public native MIDIDriverInterface setMonitor(FunctionPtr Monitor);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
