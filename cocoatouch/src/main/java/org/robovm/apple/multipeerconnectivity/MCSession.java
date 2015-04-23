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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MCSession/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MCSessionPtr extends Ptr<MCSession, MCSessionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MCSession.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MCSession() {}
    protected MCSession(SkipInit skipInit) { super(skipInit); }
    public MCSession(MCPeerID myPeerID) { super((SkipInit) null); initObject(init(myPeerID)); }
    public MCSession(MCPeerID myPeerID, @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsListMarshaler.class) List<SecIdentity> identity, MCEncryptionPreference encryptionPreference) { super((SkipInit) null); initObject(init(myPeerID, identity, encryptionPreference)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "delegate")
    public native MCSessionDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(MCSessionDelegate v);
    @Property(selector = "myPeerID")
    public native MCPeerID getMyPeerID();
    @Property(selector = "securityIdentity")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsListMarshaler.class) List<SecIdentity> getSecurityIdentity();
    @Property(selector = "encryptionPreference")
    public native MCEncryptionPreference getEncryptionPreference();
    @Property(selector = "connectedPeers")
    public native NSArray<MCPeerID> getConnectedPeers();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kMCSessionMinimumNumberOfPeers", optional=true)
    public static native @MachineSizedUInt long getMinimumNumberOfPeers();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kMCSessionMaximumNumberOfPeers", optional=true)
    public static native @MachineSizedUInt long getMaximumNumberOfPeers();
    
    @Method(selector = "initWithPeer:")
    protected native @Pointer long init(MCPeerID myPeerID);
    @Method(selector = "initWithPeer:securityIdentity:encryptionPreference:")
    protected native @Pointer long init(MCPeerID myPeerID, @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsListMarshaler.class) List<SecIdentity> identity, MCEncryptionPreference encryptionPreference);
    public boolean sendData(NSData data, NSArray<MCPeerID> peerIDs, MCSessionSendDataMode mode) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = sendData(data, peerIDs, mode, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "sendData:toPeers:withMode:error:")
    private native boolean sendData(NSData data, NSArray<MCPeerID> peerIDs, MCSessionSendDataMode mode, NSError.NSErrorPtr error);
    @Method(selector = "disconnect")
    public native void disconnect();
    @Method(selector = "sendResourceAtURL:withName:toPeer:withCompletionHandler:")
    public native NSProgress sendResource(NSURL resourceURL, String resourceName, MCPeerID peerID, @Block VoidBlock1<NSError> completionHandler);
    public NSOutputStream startStream(String streamName, MCPeerID peerID) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       NSOutputStream result = startStream(streamName, peerID, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "startStreamWithName:toPeer:error:")
    private native NSOutputStream startStream(String streamName, MCPeerID peerID, NSError.NSErrorPtr error);
    @Method(selector = "nearbyConnectionDataForPeer:withCompletionHandler:")
    public native void requestNearbyConnectionData(MCPeerID peerID, @Block VoidBlock2<NSData, NSError> completionHandler);
    @Method(selector = "connectPeer:withNearbyConnectionData:")
    public native void connectPeer(MCPeerID peerID, NSData data);
    @Method(selector = "cancelConnectPeer:")
    public native void cancelConnectPeer(MCPeerID peerID);
    /*</methods>*/
}
