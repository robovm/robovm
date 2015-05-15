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
/**
 * @since Available in iOS 4.1 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("GameKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GKMatchRequest/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GKMatchRequestPtr extends Ptr<GKMatchRequest, GKMatchRequestPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GKMatchRequest.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GKMatchRequest() {}
    protected GKMatchRequest(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "minPlayers")
    public native @MachineSizedUInt long getMinPlayers();
    @Property(selector = "setMinPlayers:")
    public native void setMinPlayers(@MachineSizedUInt long v);
    @Property(selector = "maxPlayers")
    public native @MachineSizedUInt long getMaxPlayers();
    @Property(selector = "setMaxPlayers:")
    public native void setMaxPlayers(@MachineSizedUInt long v);
    @Property(selector = "playerGroup")
    public native @MachineSizedUInt long getPlayerGroup();
    @Property(selector = "setPlayerGroup:")
    public native void setPlayerGroup(@MachineSizedUInt long v);
    @Property(selector = "playerAttributes")
    public native int getPlayerAttributes();
    @Property(selector = "setPlayerAttributes:")
    public native void setPlayerAttributes(int v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "recipients")
    public native NSArray<GKPlayer> getRecipients();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setRecipients:")
    public native void setRecipients(NSArray<GKPlayer> v);
    /**
     * @since Available in iOS 4.1 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Property(selector = "playersToInvite")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getPlayersToInvite();
    /**
     * @since Available in iOS 4.1 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Property(selector = "setPlayersToInvite:")
    public native void setPlayersToInvite(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "inviteMessage")
    public native String getInviteMessage();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setInviteMessage:")
    public native void setInviteMessage(String v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "defaultNumberOfPlayers")
    public native @MachineSizedUInt long getDefaultNumberOfPlayers();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setDefaultNumberOfPlayers:")
    public native void setDefaultNumberOfPlayers(@MachineSizedUInt long v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "recipientResponseHandler")
    public native @Block VoidBlock2<GKPlayer, GKInviteRecipientResponse> getRecipientResponseHandler();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setRecipientResponseHandler:")
    public native void setRecipientResponseHandler(@Block VoidBlock2<GKPlayer, GKInviteRecipientResponse> v);
    /**
     * @since Available in iOS 6.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Property(selector = "inviteeResponseHandler")
    public native @Block VoidBlock2<String, GKInviteeResponse> getInviteeResponseHandler();
    /**
     * @since Available in iOS 6.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Property(selector = "setInviteeResponseHandler:")
    public native void setInviteeResponseHandler(@Block VoidBlock2<String, GKInviteeResponse> v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "maxPlayersAllowedForMatchOfType:")
    public static native @MachineSizedUInt long getMaxPlayersAllowedForMatchType(GKMatchType matchType);
    /*</methods>*/
}
