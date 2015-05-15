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

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/MCSessionDelegate/*</name>*/ 
    /*<implements>*/extends NSObjectProtocol/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    @Method(selector = "session:peer:didChangeState:")
    void didChangeState(MCSession session, MCPeerID peerID, MCSessionState state);
    @Method(selector = "session:didReceiveData:fromPeer:")
    void didReceiveData(MCSession session, NSData data, MCPeerID peerID);
    @Method(selector = "session:didReceiveStream:withName:fromPeer:")
    void didReceiveStream(MCSession session, NSInputStream stream, String streamName, MCPeerID peerID);
    @Method(selector = "session:didStartReceivingResourceWithName:fromPeer:withProgress:")
    void didStartReceivingResource(MCSession session, String resourceName, MCPeerID peerID, NSProgress progress);
    @Method(selector = "session:didFinishReceivingResourceWithName:fromPeer:atURL:withError:")
    void didFinishReceivingResource(MCSession session, String resourceName, MCPeerID peerID, NSURL localURL, NSError error);
    @Method(selector = "session:didReceiveCertificate:fromPeer:certificateHandler:")
    void didReceiveCertificate(MCSession session, @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsListMarshaler.class) List<SecIdentity> certificate, MCPeerID peerID, @Block VoidBooleanBlock certificateHandler);
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
