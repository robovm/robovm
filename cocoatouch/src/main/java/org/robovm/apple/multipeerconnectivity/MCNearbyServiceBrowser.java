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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MCNearbyServiceBrowser/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MCNearbyServiceBrowserPtr extends Ptr<MCNearbyServiceBrowser, MCNearbyServiceBrowserPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MCNearbyServiceBrowser.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MCNearbyServiceBrowser() {}
    protected MCNearbyServiceBrowser(SkipInit skipInit) { super(skipInit); }
    public MCNearbyServiceBrowser(MCPeerID myPeerID, String serviceType) { super((SkipInit) null); initObject(init(myPeerID, serviceType)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "delegate")
    public native MCNearbyServiceBrowserDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(MCNearbyServiceBrowserDelegate v);
    @Property(selector = "myPeerID")
    public native MCPeerID getMyPeerID();
    @Property(selector = "serviceType")
    public native String getServiceType();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithPeer:serviceType:")
    protected native @Pointer long init(MCPeerID myPeerID, String serviceType);
    @Method(selector = "startBrowsingForPeers")
    public native void startBrowsing();
    @Method(selector = "stopBrowsingForPeers")
    public native void stopBrowsing();
    @Method(selector = "invitePeer:toSession:withContext:timeout:")
    public native void invitePeer(MCPeerID peerID, MCSession session, NSData context, double timeout);
    /*</methods>*/
}
