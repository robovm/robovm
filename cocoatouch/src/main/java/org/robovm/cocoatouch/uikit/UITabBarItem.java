/*
 * Copyright (C) 2012 RoboVM
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
package org.robovm.cocoatouch.uikit;

/*<imports>*/
import org.robovm.cocoatouch.coreanimation.*;
import org.robovm.cocoatouch.coredata.*;
import org.robovm.cocoatouch.coregraphics.*;
import org.robovm.cocoatouch.coreimage.*;
import org.robovm.cocoatouch.foundation.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.bind.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarItem_Class/Reference/Reference.html">UITabBarItem Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UITabBarItem /*</name>*/ 
    extends /*<extends>*/ UIBarItem /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UITabBarItem /*</name>*/.class);
    }

    /*<constructors>*/
    public UITabBarItem() {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UITabBarItem/initWithTabBarSystemItem:tag:">- (id)initWithTabBarSystemItem:(UITabBarSystemItem)systemItem tag:(NSInteger)tag</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("initWithTabBarSystemItem:tag:") public UITabBarItem(@Type("UITabBarSystemItem") UITabBarSystemItem systemItem, @Type("NSInteger") int tag) {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UITabBarItem/initWithTitle:image:tag:">- (id)initWithTitle:(NSString *)title image:(UIImage *)image tag:(NSInteger)tag</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("initWithTitle:image:tag:") public UITabBarItem(@Type("NSString *") String title, @Type("UIImage *") UIImage image, @Type("NSInteger") int tag) {}
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBarItem/badgeValue">@property(nonatomic, copy) NSString *badgeValue</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("badgeValue") public native @Type("NSString *") String getBadgeValue();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBarItem/badgeValue">@property(nonatomic, copy) NSString *badgeValue</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setBadgeValue:") public native void setBadgeValue(@Type("NSString *") String v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UITabBarItem/finishedSelectedImage">- (UIImage *)finishedSelectedImage</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("finishedSelectedImage") public native @Type("UIImage *") UIImage getFinishedSelectedImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UITabBarItem/finishedUnselectedImage">- (UIImage *)finishedUnselectedImage</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("finishedUnselectedImage") public native @Type("UIImage *") UIImage getFinishedUnselectedImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UITabBarItem/titlePositionAdjustment">- (UIOffset)titlePositionAdjustment</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("titlePositionAdjustment") public native @Type("UIOffset") UIOffset getTitlePositionAdjustment();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UITabBarItem/setFinishedSelectedImage:withFinishedUnselectedImage:">- (void)setFinishedSelectedImage:(UIImage *)selectedImage withFinishedUnselectedImage:(UIImage *)unselectedImage</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setFinishedSelectedImage:withFinishedUnselectedImage:") public native @Type("void") void setFinishedImages(@Type("UIImage *") UIImage selectedImage, @Type("UIImage *") UIImage unselectedImage);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UITabBarItem/setTitlePositionAdjustment:">- (void)setTitlePositionAdjustment:(UIOffset)adjustment</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setTitlePositionAdjustment:") public native @Type("void") void setTitlePositionAdjustment(@Type("UIOffset") UIOffset adjustment);
    /*</methods>*/

}
