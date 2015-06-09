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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GKLocalPlayer/*</name>*/ 
    extends /*<extends>*/GKPlayer/*</extends>*/ 
    /*<implements>*/implements GKSavedGameListener/*</implements>*/ {

    public static class Notifications {
        public static NSObject observeAuthenticationDidChange(GKLocalPlayer object, final VoidBlock1<GKLocalPlayer> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(AuthenticationDidChangeNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke((GKLocalPlayer) a.getObject());
                }
            });
        }
    }
    /*<ptr>*/public static class GKLocalPlayerPtr extends Ptr<GKLocalPlayer, GKLocalPlayerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GKLocalPlayer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GKLocalPlayer() {}
    protected GKLocalPlayer(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "isAuthenticated")
    public native boolean isAuthenticated();
    @Property(selector = "isUnderage")
    public native boolean isUnderage();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "authenticateHandler")
    public native @Block VoidBlock2<UIViewController, NSError> getAuthenticateHandler();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setAuthenticateHandler:")
    public native void setAuthenticateHandler(@Block VoidBlock2<UIViewController, NSError> v);
    /**
     * @since Available in iOS 4.1 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Property(selector = "friends")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getFriends();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 4.1 and later.
     */
    @GlobalValue(symbol="GKPlayerAuthenticationDidChangeNotificationName", optional=true)
    public static native NSString AuthenticationDidChangeNotification();
    
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "loadFriendPlayersWithCompletionHandler:")
    public native void loadFriendPlayers(@Block VoidBlock2<NSArray<GKPlayer>, NSError> completionHandler);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "setDefaultLeaderboardIdentifier:completionHandler:")
    public native void setDefaultLeaderboardIdentifier(String leaderboardIdentifier, @Block VoidBlock1<NSError> completionHandler);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "loadDefaultLeaderboardIdentifierWithCompletionHandler:")
    public native void loadDefaultLeaderboardIdentifier(@Block VoidBlock2<String, NSError> completionHandler);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "generateIdentityVerificationSignatureWithCompletionHandler:")
    public native void generateIdentityVerificationSignature(@Block VoidBlock5<NSURL, NSData, NSData, Long, NSError> completionHandler);
    @Method(selector = "localPlayer")
    public static native GKLocalPlayer getLocalPlayer();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "registerListener:")
    public native void registerListener(GKLocalPlayerListener listener);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "unregisterListener:")
    public native void unregisterListener(GKLocalPlayerListener listener);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "unregisterAllListeners")
    public native void unregisterAllListeners();
    /**
     * @since Available in iOS 6.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Method(selector = "setDefaultLeaderboardCategoryID:completionHandler:")
    public native void setDefaultLeaderboardCategoryID(String categoryID, @Block VoidBlock1<NSError> completionHandler);
    /**
     * @since Available in iOS 6.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Method(selector = "loadDefaultLeaderboardCategoryIDWithCompletionHandler:")
    public native void loadDefaultLeaderboardCategoryID(@Block VoidBlock2<String, NSError> completionHandler);
    /**
     * @since Available in iOS 4.1 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Method(selector = "loadFriendsWithCompletionHandler:")
    public native void loadFriends(@Block VoidBlock2<NSArray<NSString>, NSError> completionHandler);
    /**
     * @since Available in iOS 4.1 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Method(selector = "authenticateWithCompletionHandler:")
    public native void authenticate(@Block VoidBlock1<NSError> completionHandler);
    @Method(selector = "fetchSavedGamesWithCompletionHandler:")
    public native void fetchSavedGames(@Block VoidBlock2<NSArray<GKSavedGame>, NSError> handler);
    @Method(selector = "saveGameData:withName:completionHandler:")
    public native void saveGameData(NSData data, String name, @Block VoidBlock2<GKSavedGame, NSError> handler);
    @Method(selector = "deleteSavedGamesWithName:completionHandler:")
    public native void deleteSavedGames(String name, @Block VoidBlock1<NSError> handler);
    @Method(selector = "resolveConflictingSavedGames:withData:completionHandler:")
    public native void resolveConflictingSavedGames(NSArray<GKSavedGame> conflictingSavedGames, NSData data, @Block VoidBlock2<NSArray<GKSavedGame>, NSError> handler);
    @Method(selector = "player:didModifySavedGame:")
    public native void didModifySavedGame(GKPlayer player, GKSavedGame savedGame);
    @Method(selector = "player:hasConflictingSavedGames:")
    public native void hasConflictingSavedGames(GKPlayer player, NSArray<GKSavedGame> savedGames);
    /*</methods>*/
}
