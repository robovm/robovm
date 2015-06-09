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
import org.robovm.rt.annotation.*;
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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GKMatchmaker/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GKMatchmakerPtr extends Ptr<GKMatchmaker, GKMatchmakerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GKMatchmaker.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GKMatchmaker() {}
    protected GKMatchmaker(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @since Available in iOS 4.1 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Property(selector = "inviteHandler")
    public native @Block VoidBlock2<GKInvite, NSArray<NSString>> getInviteHandler();
    /**
     * @since Available in iOS 4.1 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Property(selector = "setInviteHandler:")
    public native void setInviteHandler(@Block VoidBlock2<GKInvite, NSArray<NSString>> v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "matchForInvite:completionHandler:")
    public native void match(GKInvite invite, @Block VoidBlock2<GKMatch, NSError> completionHandler);
    @Method(selector = "findMatchForRequest:withCompletionHandler:")
    public native void findMatch(GKMatchRequest request, @Block VoidBlock2<GKMatch, NSError> completionHandler);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "findPlayersForHostedRequest:withCompletionHandler:")
    public native void findPlayersForHostedRequest(GKMatchRequest request, @Block VoidBlock2<NSArray<GKPlayer>, NSError> completionHandler);
    @Method(selector = "addPlayersToMatch:matchRequest:completionHandler:")
    public native void addPlayersToMatch(GKMatch match, GKMatchRequest matchRequest, @Block VoidBlock1<NSError> completionHandler);
    @Method(selector = "cancel")
    public native void cancel();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "cancelPendingInviteToPlayer:")
    public native void cancelPendingInvite(GKPlayer player);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "finishMatchmakingForMatch:")
    public native void finishMatchmaking(GKMatch match);
    @Method(selector = "queryPlayerGroupActivity:withCompletionHandler:")
    public native void queryPlayerGroupActivity(@MachineSizedUInt long playerGroup, @Block("(@MachineSizedSInt,)") VoidBlock2<Long, NSError> completionHandler);
    @Method(selector = "queryActivityWithCompletionHandler:")
    public native void queryActivity(@Block("(@MachineSizedSInt,)") VoidBlock2<Long, NSError> completionHandler);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "startBrowsingForNearbyPlayersWithHandler:")
    public native void startNearbyPlayersBrowsing(@Block VoidBlock2<GKPlayer, Boolean> reachableHandler);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "stopBrowsingForNearbyPlayers")
    public native void stopBrowsingForNearbyPlayers();
    @Method(selector = "sharedMatchmaker")
    public static native GKMatchmaker getSharedMatchmaker();
    /**
     * @since Available in iOS 6.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Method(selector = "startBrowsingForNearbyPlayersWithReachableHandler:")
    public native void startBrowsingForNearbyPlayers(@Block VoidBlock2<String, Boolean> reachableHandler);
    /**
     * @since Available in iOS 6.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Method(selector = "cancelInviteToPlayer:")
    public native void cancelInvite(String playerID);
    /**
     * @since Available in iOS 4.1 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Method(selector = "findPlayersForHostedMatchRequest:withCompletionHandler:")
    public native void findPlayers(GKMatchRequest request, @Block VoidBlock2<NSArray<NSString>, NSError> completionHandler);
    /*</methods>*/
}
