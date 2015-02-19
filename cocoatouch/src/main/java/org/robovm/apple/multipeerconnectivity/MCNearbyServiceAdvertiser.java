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
package org.robovm.apple.multipeerconnectivity;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 7.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("MultipeerConnectivity") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MCNearbyServiceAdvertiser/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MCNearbyServiceAdvertiserPtr extends Ptr<MCNearbyServiceAdvertiser, MCNearbyServiceAdvertiserPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MCNearbyServiceAdvertiser.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MCNearbyServiceAdvertiser() {}
    protected MCNearbyServiceAdvertiser(SkipInit skipInit) { super(skipInit); }
    public MCNearbyServiceAdvertiser(MCPeerID myPeerID, @org.robovm.rt.bro.annotation.Marshaler(NSDictionary.AsStringStringMapMarshaler.class) Map<String, String> info, String serviceType) { super((SkipInit) null); initObject(init(myPeerID, info, serviceType)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "delegate")
    public native MCNearbyServiceAdvertiserDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(MCNearbyServiceAdvertiserDelegate v);
    @Property(selector = "myPeerID")
    public native MCPeerID getMyPeerID();
    @Property(selector = "discoveryInfo")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSDictionary.AsStringStringMapMarshaler.class) Map<String, String> getDiscoveryInfo();
    @Property(selector = "serviceType")
    public native String getServiceType();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithPeer:discoveryInfo:serviceType:")
    protected native @Pointer long init(MCPeerID myPeerID, @org.robovm.rt.bro.annotation.Marshaler(NSDictionary.AsStringStringMapMarshaler.class) Map<String, String> info, String serviceType);
    @Method(selector = "startAdvertisingPeer")
    public native void startAdvertisingPeer();
    @Method(selector = "stopAdvertisingPeer")
    public native void stopAdvertisingPeer();
    /*</methods>*/
}
