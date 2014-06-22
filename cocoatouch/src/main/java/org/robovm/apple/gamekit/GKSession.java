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
package org.robovm.apple.gamekit;

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
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 3.0 and later.
 * @deprecated Deprecated in iOS 7.0.
 */
@Deprecated
/*</javadoc>*/
/*<annotations>*/@Library("GameKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GKSession/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GKSessionPtr extends Ptr<GKSession, GKSessionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GKSession.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GKSession() {}
    protected GKSession(SkipInit skipInit) { super(skipInit); }
    public GKSession(String sessionID, String name, GKSessionMode mode) { super((SkipInit) null); initObject(initWithSessionID$displayName$sessionMode$(sessionID, name, mode)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "delegate")
    public native GKSessionDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(GKSessionDelegate v);
    @Property(selector = "sessionID")
    public native String getSessionID();
    @Property(selector = "displayName")
    public native String getDisplayName();
    @Property(selector = "sessionMode")
    public native GKSessionMode getSessionMode();
    @Property(selector = "peerID")
    public native String getPeerID();
    @Property(selector = "isAvailable")
    public native boolean isAvailable();
    @Property(selector = "setAvailable:")
    public native void setAvailable(boolean v);
    @Property(selector = "disconnectTimeout")
    public native double getDisconnectTimeout();
    @Property(selector = "setDisconnectTimeout:")
    public native void setDisconnectTimeout(double v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithSessionID:displayName:sessionMode:")
    protected native @Pointer long initWithSessionID$displayName$sessionMode$(String sessionID, String name, GKSessionMode mode);
    @Method(selector = "displayNameForPeer:")
    public native String getDisplayName(String peerID);
    @Method(selector = "sendData:toPeers:withDataMode:error:")
    public native boolean sendData(NSData data, NSArray<NSString> peers, GKSendDataMode mode, NSError.NSErrorPtr error);
    @Method(selector = "sendDataToAllPeers:withDataMode:error:")
    public native boolean sendDataToAllPeers(NSData data, GKSendDataMode mode, NSError.NSErrorPtr error);
    @Method(selector = "setDataReceiveHandler:withContext:")
    public native void setDataReceiveHandler(NSObject handler, VoidPtr context);
    @Method(selector = "connectToPeer:withTimeout:")
    public native void connect(String peerID, double timeout);
    @Method(selector = "cancelConnectToPeer:")
    public native void cancelConnect(String peerID);
    @Method(selector = "acceptConnectionFromPeer:error:")
    public native boolean acceptConnection(String peerID, NSError.NSErrorPtr error);
    @Method(selector = "denyConnectionFromPeer:")
    public native void denyConnection(String peerID);
    @Method(selector = "disconnectPeerFromAllPeers:")
    public native void disconnectPeer(String peerID);
    @Method(selector = "disconnectFromAllPeers")
    public native void disconnectFromAllPeers();
    @Method(selector = "peersWithConnectionState:")
    public native NSArray<NSString> getPeers(GKPeerConnectionState state);
    /*</methods>*/
}
