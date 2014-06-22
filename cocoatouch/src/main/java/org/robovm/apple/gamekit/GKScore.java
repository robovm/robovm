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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GKScore/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class GKScorePtr extends Ptr<GKScore, GKScorePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GKScore.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GKScore() {}
    protected GKScore(SkipInit skipInit) { super(skipInit); }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public GKScore(String identifier) { super((SkipInit) null); initObject(initWithLeaderboardIdentifier$(identifier)); }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public GKScore(String identifier, String playerID) { super((SkipInit) null); initObject(initWithLeaderboardIdentifier$forPlayer$(identifier, playerID)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "value")
    public native long getValue();
    @Property(selector = "setValue:")
    public native void setValue(long v);
    @Property(selector = "formattedValue")
    public native String getFormattedValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "leaderboardIdentifier")
    public native String getLeaderboardIdentifier();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setLeaderboardIdentifier:")
    public native void setLeaderboardIdentifier(String v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "context")
    public native long getContext();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setContext:")
    public native void setContext(long v);
    @Property(selector = "date")
    public native NSDate getDate();
    @Property(selector = "playerID")
    public native String getPlayerID();
    @Property(selector = "rank")
    public native @MachineSizedSInt long getRank();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "shouldSetDefaultLeaderboard")
    public native boolean isShouldSetDefaultLeaderboard();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setShouldSetDefaultLeaderboard:")
    public native void setShouldSetDefaultLeaderboard(boolean v);
    /**
     * @since Available in iOS 4.1 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Property(selector = "category")
    public native String getCategory();
    /**
     * @since Available in iOS 4.1 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Property(selector = "setCategory:")
    public native void setCategory(String v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "initWithLeaderboardIdentifier:")
    protected native @Pointer long initWithLeaderboardIdentifier$(String identifier);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "initWithLeaderboardIdentifier:forPlayer:")
    protected native @Pointer long initWithLeaderboardIdentifier$forPlayer$(String identifier, String playerID);
    /**
     * @since Available in iOS 4.1 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Method(selector = "reportScoreWithCompletionHandler:")
    public native void reportScore(@Block VoidBlock1<NSError> completionHandler);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "reportScores:withCompletionHandler:")
    public static native void reportScores(NSArray<GKScore> scores, @Block VoidBlock1<NSError> completionHandler);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "challengeComposeControllerWithPlayers:message:completionHandler:")
    public native UIViewController getChallengeComposeController(NSArray<NSString> playerIDs, String message, @Block VoidBlock3<UIViewController, Boolean, NSArray<NSString>> completionHandler);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "reportScores:withEligibleChallenges:withCompletionHandler:")
    public static native void reportScores(NSArray<GKScore> scores, NSArray<GKChallenge> challenges, @Block VoidBlock1<NSError> completionHandler);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
