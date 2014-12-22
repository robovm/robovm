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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GKLocalPlayerListenerAdapter/*</name>*/ 
    extends /*<extends>*/GKChallengeListenerAdapter/*</extends>*/ 
    /*<implements>*/implements GKLocalPlayerListener/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @NotImplemented("player:wantsToPlayChallenge:")
    public void wantsToPlayChallenge(GKPlayer player, GKChallenge challenge) { throw new UnsupportedOperationException(); }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @NotImplemented("player:didReceiveChallenge:")
    public void didReceiveChallenge(GKPlayer player, GKChallenge challenge) { throw new UnsupportedOperationException(); }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @NotImplemented("player:didCompleteChallenge:issuedByFriend:")
    public void didCompleteChallenge(GKPlayer player, GKChallenge challenge, GKPlayer friendPlayer) { throw new UnsupportedOperationException(); }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @NotImplemented("player:issuedChallengeWasCompleted:byFriend:")
    public void issuedChallengeWasCompleted(GKPlayer player, GKChallenge challenge, GKPlayer friendPlayer) { throw new UnsupportedOperationException(); }

    /**
     * @since Available in iOS 7.0 and later.
     */
    @NotImplemented("player:didAcceptInvite:")
    public void didAcceptInvite(GKPlayer player, GKInvite invite) { throw new UnsupportedOperationException(); }
    /**
     * @since Available in iOS 8.0 and later.
     */
    @NotImplemented("player:didRequestMatchWithOtherPlayers:")
    public void didRequestMatch(GKPlayer player, NSArray<GKPlayer> playersToInvite) { throw new UnsupportedOperationException(); }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @NotImplemented("player:receivedTurnEventForMatch:didBecomeActive:")
    public void receivedTurnEvent(GKPlayer player, GKTurnBasedMatch match, boolean didBecomeActive) { throw new UnsupportedOperationException(); }
    @NotImplemented("player:matchEnded:")
    public void matchEnded(GKPlayer player, GKTurnBasedMatch match) { throw new UnsupportedOperationException(); }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @NotImplemented("player:receivedExchangeRequest:forMatch:")
    public void receivedExchangeRequest(GKPlayer player, GKTurnBasedExchange exchange, GKTurnBasedMatch match) { throw new UnsupportedOperationException(); }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @NotImplemented("player:receivedExchangeCancellation:forMatch:")
    public void receivedExchangeCancellation(GKPlayer player, GKTurnBasedExchange exchange, GKTurnBasedMatch match) { throw new UnsupportedOperationException(); }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @NotImplemented("player:receivedExchangeReplies:forCompletedExchange:forMatch:")
    public void receivedExchangeReplies(GKPlayer player, NSArray<GKTurnBasedExchange> replies, GKTurnBasedExchange exchange, GKTurnBasedMatch match) { throw new UnsupportedOperationException(); }
    /**
     * @since Available in iOS 7.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @NotImplemented("player:didRequestMatchWithPlayers:")
    public void didRequestMatch(GKPlayer player, @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> playerIDsToInvite) { throw new UnsupportedOperationException(); }

    @NotImplemented("player:didModifySavedGame:")
    public void didModifySavedGame(GKPlayer player, GKSavedGame savedGame) { throw new UnsupportedOperationException(); }
    @NotImplemented("player:hasConflictingSavedGames:")
    public void hasConflictingSavedGames(GKPlayer player, NSArray<GKSavedGame> savedGames) { throw new UnsupportedOperationException(); }

    /*<methods>*/
    
    /*</methods>*/
}
