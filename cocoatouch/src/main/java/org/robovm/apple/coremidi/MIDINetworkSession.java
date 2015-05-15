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
/**
 * @since Available in iOS 4.2 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CoreMIDI") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MIDINetworkSession/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MIDINetworkSessionPtr extends Ptr<MIDINetworkSession, MIDINetworkSessionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MIDINetworkSession.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MIDINetworkSession() {}
    protected MIDINetworkSession(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "isEnabled")
    public native boolean isEnabled();
    @Property(selector = "setEnabled:")
    public native void setEnabled(boolean v);
    @Property(selector = "networkPort")
    public native @MachineSizedUInt long getNetworkPort();
    @Property(selector = "networkName")
    public native String getNetworkName();
    @Property(selector = "localName")
    public native String getLocalName();
    @Property(selector = "connectionPolicy")
    public native MIDINetworkConnectionPolicy getConnectionPolicy();
    @Property(selector = "setConnectionPolicy:")
    public native void setConnectionPolicy(MIDINetworkConnectionPolicy v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "contacts")
    public native NSSet<MIDINetworkHost> getContacts();
    @Method(selector = "addContact:")
    public native boolean addContact(MIDINetworkHost contact);
    @Method(selector = "removeContact:")
    public native boolean removeContact(MIDINetworkHost contact);
    @Method(selector = "connections")
    public native NSSet<MIDINetworkConnection> getConnections();
    @Method(selector = "addConnection:")
    public native boolean addConnection(MIDINetworkConnection connection);
    @Method(selector = "removeConnection:")
    public native boolean removeConnection(MIDINetworkConnection connection);
    @Method(selector = "sourceEndpoint")
    public native MIDIEndpoint getSourceEndpoint();
    @Method(selector = "destinationEndpoint")
    public native MIDIEndpoint getDestinationEndpoint();
    @Method(selector = "defaultSession")
    public static native MIDINetworkSession defaultSession();
    /*</methods>*/
}
