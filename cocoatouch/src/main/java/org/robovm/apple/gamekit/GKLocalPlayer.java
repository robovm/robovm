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
 * @since Available in iOS 4.1 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("GameKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GKLocalPlayer/*</name>*/ 
    extends /*<extends>*/GKPlayer/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Notifications {
        public static NSObject observeAuthenticationDidChange(GKLocalPlayer object, final VoidBlock1<GKLocalPlayer> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(AuthenticationDidChangeNotificationName(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
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
    @Property(selector = "friends")
    public native NSArray<NSString> getFriends();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 4.1 and later.
     */
    @GlobalValue(symbol="GKPlayerAuthenticationDidChangeNotificationName", optional=true)
    public static native NSString AuthenticationDidChangeNotificationName();
    
    @Method(selector = "loadFriendsWithCompletionHandler:")
    public native void loadFriends(@Block VoidBlock2<NSArray<NSString>, NSError> completionHandler);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "setDefaultLeaderboardIdentifier:completionHandler:")
    public native void setDefaultLeaderboardIdentifier(String leaderboardIdentifier, @Block VoidBlock1<NSError> completionHandler);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "loadDefaultLeaderboardIdentifierWithCompletionHandler:")
    public native void loadDefaultLeaderboardIdentifier(@Block VoidBlock2<NSString, NSError> completionHandler);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "generateIdentityVerificationSignatureWithCompletionHandler:")
    public native void generateIdentityVerificationSignature(@Block VoidBlock5<NSURL, NSData, NSData, Long, NSError> completionHandler);
    /**
     * @since Available in iOS 4.1 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Method(selector = "authenticateWithCompletionHandler:")
    public native void authenticate(@Block VoidBlock1<NSError> completionHandler);
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
    /*</methods>*/
}
