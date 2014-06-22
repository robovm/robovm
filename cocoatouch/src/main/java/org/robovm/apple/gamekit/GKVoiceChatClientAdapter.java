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
    public void sendData(GKVoiceChatService voiceChatService, NSData data, String participantID) { throw new UnsupportedOperationException(); }
    @NotImplemented("participantID")
    public String getParticipantID() { throw new UnsupportedOperationException(); }
    @NotImplemented("voiceChatService:sendRealTimeData:toParticipantID:")
    public void sendRealTimeData(GKVoiceChatService voiceChatService, NSData data, String participantID) { throw new UnsupportedOperationException(); }
    @NotImplemented("voiceChatService:didStartWithParticipantID:")
    public void didStart(GKVoiceChatService voiceChatService, String participantID) { throw new UnsupportedOperationException(); }
    @NotImplemented("voiceChatService:didNotStartWithParticipantID:error:")
    public void didNotStart(GKVoiceChatService voiceChatService, String participantID, NSError error) { throw new UnsupportedOperationException(); }
    @NotImplemented("voiceChatService:didStopWithParticipantID:error:")
    public void didStop(GKVoiceChatService voiceChatService, String participantID, NSError error) { throw new UnsupportedOperationException(); }
    @NotImplemented("voiceChatService:didReceiveInvitationFromParticipantID:callID:")
    public void didReceiveInvitation(GKVoiceChatService voiceChatService, String participantID, @MachineSizedSInt long callID) { throw new UnsupportedOperationException(); }
    /*</methods>*/
}
