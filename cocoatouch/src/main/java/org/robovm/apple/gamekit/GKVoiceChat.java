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
 * @since Available in iOS 4.1 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("GameKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GKVoiceChat/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GKVoiceChatPtr extends Ptr<GKVoiceChat, GKVoiceChatPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GKVoiceChat.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GKVoiceChat() {}
    protected GKVoiceChat(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "playerVoiceChatStateDidChangeHandler")
    public native @Block VoidBlock2<GKPlayer, GKInviteRecipientResponse> getPlayerVoiceChatStateDidChangeHandler();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setPlayerVoiceChatStateDidChangeHandler:")
    public native void setPlayerVoiceChatStateDidChangeHandler(@Block VoidBlock2<GKPlayer, GKInviteRecipientResponse> v);
    @Property(selector = "name")
    public native String getName();
    @Property(selector = "isActive")
    public native boolean isActive();
    @Property(selector = "setActive:")
    public native void setActive(boolean v);
    @Property(selector = "volume")
    public native float getVolume();
    @Property(selector = "setVolume:")
    public native void setVolume(float v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "players")
    public native NSArray<GKPlayer> getPlayers();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Property(selector = "playerIDs")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getPlayerIDs();
    /**
     * @since Available in iOS 4.1 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Property(selector = "playerStateUpdateHandler")
    public native @Block VoidBlock2<String, GKVoiceChatPlayerState> getPlayerStateUpdateHandler();
    /**
     * @since Available in iOS 4.1 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Property(selector = "setPlayerStateUpdateHandler:")
    public native void setPlayerStateUpdateHandler(@Block VoidBlock2<String, GKVoiceChatPlayerState> v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "start")
    public native void start();
    @Method(selector = "stop")
    public native void stop();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "setPlayer:muted:")
    public native void setPlayerMuted(GKPlayer player, boolean isMuted);
    @Method(selector = "isVoIPAllowed")
    public static native boolean isVoIPAllowed();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Method(selector = "setMute:forPlayer:")
    public native void setMuteForPlayer(boolean isMuted, String playerID);
    /*</methods>*/
}
