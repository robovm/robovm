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
/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GKVoiceChatClientAdapter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements GKVoiceChatClient/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @NotImplemented("voiceChatService:sendData:toParticipantID:")
    public void sendData(GKVoiceChatService voiceChatService, NSData data, String participantID) {}
    @NotImplemented("participantID")
    public String getParticipantID() { return null; }
    @NotImplemented("voiceChatService:sendRealTimeData:toParticipantID:")
    public void sendRealTimeData(GKVoiceChatService voiceChatService, NSData data, String participantID) {}
    @NotImplemented("voiceChatService:didStartWithParticipantID:")
    public void didStart(GKVoiceChatService voiceChatService, String participantID) {}
    @NotImplemented("voiceChatService:didNotStartWithParticipantID:error:")
    public void didNotStart(GKVoiceChatService voiceChatService, String participantID, NSError error) {}
    @NotImplemented("voiceChatService:didStopWithParticipantID:error:")
    public void didStop(GKVoiceChatService voiceChatService, String participantID, NSError error) {}
    @NotImplemented("voiceChatService:didReceiveInvitationFromParticipantID:callID:")
    public void didReceiveInvitation(GKVoiceChatService voiceChatService, String participantID, @MachineSizedSInt long callID) {}
    /*</methods>*/
}
