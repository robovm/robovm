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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GKLeaderboard/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GKLeaderboardPtr extends Ptr<GKLeaderboard, GKLeaderboardPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GKLeaderboard.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GKLeaderboard() {}
    protected GKLeaderboard(SkipInit skipInit) { super(skipInit); }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public GKLeaderboard(NSArray<GKPlayer> players) { super((SkipInit) null); initObject(init(players)); }
    /**
     * @since Available in iOS 4.1 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    public GKLeaderboard(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> playerIDs) { super((SkipInit) null); initObject(init(playerIDs)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "timeScope")
    public native GKLeaderboardTimeScope getTimeScope();
    @Property(selector = "setTimeScope:")
    public native void setTimeScope(GKLeaderboardTimeScope v);
    @Property(selector = "playerScope")
    public native GKLeaderboardPlayerScope getPlayerScope();
    @Property(selector = "setPlayerScope:")
    public native void setPlayerScope(GKLeaderboardPlayerScope v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "identifier")
    public native String getIdentifier();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setIdentifier:")
    public native void setIdentifier(String v);
    @Property(selector = "title")
    public native String getTitle();
    @Property(selector = "range")
    public native @ByVal NSRange getRange();
    @Property(selector = "setRange:")
    public native void setRange(@ByVal NSRange v);
    @Property(selector = "scores")
    public native NSArray<GKScore> getScores();
    @Property(selector = "maxRange")
    public native @MachineSizedUInt long getMaxRange();
    @Property(selector = "localPlayerScore")
    public native GKScore getLocalPlayerScore();
    @Property(selector = "isLoading")
    public native boolean isLoading();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "groupIdentifier")
    public native String getGroupIdentifier();
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
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "initWithPlayers:")
    protected native @Pointer long init(NSArray<GKPlayer> players);
    @Method(selector = "loadScoresWithCompletionHandler:")
    public native void loadScores(@Block VoidBlock2<NSArray<GKScore>, NSError> completionHandler);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "loadLeaderboardsWithCompletionHandler:")
    public static native void loadLeaderboards(@Block VoidBlock2<NSArray<GKLeaderboard>, NSError> completionHandler);
    /**
     * @since Available in iOS 4.1 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Method(selector = "initWithPlayerIDs:")
    protected native @Pointer long init(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> playerIDs);
    /**
     * @since Available in iOS 4.1 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Method(selector = "loadCategoriesWithCompletionHandler:")
    public static native void loadCategories(@Block VoidBlock3<NSArray<NSString>, NSArray<NSString>, NSError> completionHandler);
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Method(selector = "setDefaultLeaderboard:withCompletionHandler:")
    public static native void setDefaultLeaderboard(String leaderboardIdentifier, @Block VoidBlock1<NSError> completionHandler);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "loadImageWithCompletionHandler:")
    public native void loadImage(@Block VoidBlock2<UIImage, NSError> completionHandler);
    /*</methods>*/
}
