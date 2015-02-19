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
 * @since Available in iOS 6.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("GameKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GKGameCenterViewController/*</name>*/ 
    extends /*<extends>*/UINavigationController/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GKGameCenterViewControllerPtr extends Ptr<GKGameCenterViewController, GKGameCenterViewControllerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GKGameCenterViewController.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GKGameCenterViewController() {}
    protected GKGameCenterViewController(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "gameCenterDelegate")
    public native GKGameCenterControllerDelegate getGameCenterDelegate();
    @Property(selector = "setGameCenterDelegate:", strongRef = true)
    public native void setGameCenterDelegate(GKGameCenterControllerDelegate v);
    @Property(selector = "viewState")
    public native GKGameCenterViewControllerState getViewState();
    @Property(selector = "setViewState:")
    public native void setViewState(GKGameCenterViewControllerState v);
    /**
     * @since Available in iOS 4.1 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Property(selector = "leaderboardTimeScope")
    public native GKLeaderboardTimeScope getLeaderboardTimeScope();
    /**
     * @since Available in iOS 4.1 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Property(selector = "setLeaderboardTimeScope:")
    public native void setLeaderboardTimeScope(GKLeaderboardTimeScope v);
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
     * @since Available in iOS 4.1 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Property(selector = "leaderboardCategory")
    public native String getLeaderboardCategory();
    /**
     * @since Available in iOS 4.1 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Property(selector = "setLeaderboardCategory:")
    public native void setLeaderboardCategory(String v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
