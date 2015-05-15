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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GKAchievementDescription/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class GKAchievementDescriptionPtr extends Ptr<GKAchievementDescription, GKAchievementDescriptionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GKAchievementDescription.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GKAchievementDescription() {}
    protected GKAchievementDescription(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "identifier")
    public native String getIdentifier();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "groupIdentifier")
    public native String getGroupIdentifier();
    @Property(selector = "title")
    public native String getTitle();
    @Property(selector = "achievedDescription")
    public native String getAchievedDescription();
    @Property(selector = "unachievedDescription")
    public native String getUnachievedDescription();
    @Property(selector = "maximumPoints")
    public native @MachineSizedSInt long getMaximumPoints();
    @Property(selector = "isHidden")
    public native boolean isHidden();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "isReplayable")
    public native boolean isReplayable();
    /**
     * @since Available in iOS 4.1 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Property(selector = "image")
    public native UIImage getImage();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "loadAchievementDescriptionsWithCompletionHandler:")
    public static native void loadAchievementDescriptions(@Block VoidBlock2<NSArray<GKAchievementDescription>, NSError> completionHandler);
    @Method(selector = "loadImageWithCompletionHandler:")
    public native void loadImage(@Block VoidBlock2<UIImage, NSError> completionHandler);
    @Method(selector = "incompleteAchievementImage")
    public static native UIImage getIncompleteAchievementImage();
    @Method(selector = "placeholderCompletedAchievementImage")
    public static native UIImage getPlaceholderCompletedAchievementImage();
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
