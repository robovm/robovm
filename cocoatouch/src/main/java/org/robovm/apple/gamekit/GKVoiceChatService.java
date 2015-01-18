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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GKVoiceChatService/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GKVoiceChatServicePtr extends Ptr<GKVoiceChatService, GKVoiceChatServicePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GKVoiceChatService.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GKVoiceChatService() {}
    protected GKVoiceChatService(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "client")
    public native GKVoiceChatClient getClient();
    @Property(selector = "setClient:", strongRef = true)
    public native void setClient(GKVoiceChatClient v);
    @Property(selector = "isMicrophoneMuted")
    public native boolean isMicrophoneMuted();
    @Property(selector = "setMicrophoneMuted:")
    public native void setMicrophoneMuted(boolean v);
    @Property(selector = "remoteParticipantVolume")
    public native float getRemoteParticipantVolume();
    @Property(selector = "setRemoteParticipantVolume:")
    public native void setRemoteParticipantVolume(float v);
    @Property(selector = "isOutputMeteringEnabled")
    public native boolean isOutputMeteringEnabled();
    @Property(selector = "setOutputMeteringEnabled:")
    public native void setOutputMeteringEnabled(boolean v);
    @Property(selector = "isInputMeteringEnabled")
    public native boolean isInputMeteringEnabled();
    @Property(selector = "setInputMeteringEnabled:")
    public native void setInputMeteringEnabled(boolean v);
    @Property(selector = "outputMeterLevel")
    public native float getOutputMeterLevel();
    @Property(selector = "inputMeterLevel")
    public native float getInputMeterLevel();
    /*</properties>*/
    /*<members>*//*</members>*/
    /**
     * 
     * @param participantID
     * @return
     * @throws NSErrorException
     */
    public boolean startVoiceChat(String participantID) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = startVoiceChat(participantID, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @param callID
     * @return
     * @throws NSErrorException
     */
    public boolean acceptCallID(@MachineSizedSInt long callID) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = acceptCallID(callID, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /*<methods>*/
    @Method(selector = "startVoiceChatWithParticipantID:error:")
    protected native boolean startVoiceChat(String participantID, NSError.NSErrorPtr error);
    @Method(selector = "stopVoiceChatWithParticipantID:")
    public native void stopVoiceChat(String participantID);
    @Method(selector = "acceptCallID:error:")
    protected native boolean acceptCallID(@MachineSizedSInt long callID, NSError.NSErrorPtr error);
    @Method(selector = "denyCallID:")
    public native void denyCallID(@MachineSizedSInt long callID);
    @Method(selector = "receivedRealTimeData:fromParticipantID:")
    public native void receivedRealTimeData(NSData audio, String participantID);
    @Method(selector = "receivedData:fromParticipantID:")
    public native void receivedData(NSData arbitraryData, String participantID);
    @Method(selector = "defaultVoiceChatService")
    public static native GKVoiceChatService getDefaultVoiceChatService();
    @Method(selector = "isVoIPAllowed")
    public static native boolean isVoIPAllowed();
    /*</methods>*/
}
