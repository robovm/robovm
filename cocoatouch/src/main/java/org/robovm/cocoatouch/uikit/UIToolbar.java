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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html">UIToolbar Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIToolbar /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIToolbar /*</name>*/.class);
    }

    /*<constructors>*/
    public UIToolbar() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instp/UIToolbar/barStyle">@property(nonatomic) UIBarStyle barStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("barStyle") public native @Type("UIBarStyle") UIBarStyle getBarStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instp/UIToolbar/barStyle">@property(nonatomic) UIBarStyle barStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setBarStyle:") public native void setBarStyle(@Type("UIBarStyle") UIBarStyle v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instp/UIToolbar/items">@property(nonatomic, copy) NSArray *items</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("items") public native @Type("NSArray *") NSArray getItems();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instp/UIToolbar/items">@property(nonatomic, copy) NSArray *items</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setItems:") public native void setItems(@Type("NSArray *") NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instp/UIToolbar/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tintColor") public native @Type("UIColor *") UIColor getTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instp/UIToolbar/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTintColor:") public native void setTintColor(@Type("UIColor *") UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instp/UIToolbar/translucent">@property(nonatomic, assign, getter=isTranslucent) BOOL translucent</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("isTranslucent") public native @Type("BOOL") boolean isTranslucent();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instp/UIToolbar/translucent">@property(nonatomic, assign, getter=isTranslucent) BOOL translucent</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setTranslucent:") public native void setTranslucent(@Type("BOOL") boolean v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instm/UIToolbar/backgroundImageForToolbarPosition:barMetrics:">- (UIImage *)backgroundImageForToolbarPosition:(UIToolbarPosition)topOrBottom barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("backgroundImageForToolbarPosition:barMetrics:") public native @Type("UIImage *") UIImage getBackgroundImage(@Type("UIToolbarPosition") UIToolbarPosition topOrBottom, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instm/UIToolbar/shadowImageForToolbarPosition:">- (UIImage *)shadowImageForToolbarPosition:(UIToolbarPosition)topOrBottom</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("shadowImageForToolbarPosition:") public native @Type("UIImage *") UIImage getShadowImage(@Type("UIToolbarPosition") UIToolbarPosition topOrBottom);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instm/UIToolbar/setBackgroundImage:forToolbarPosition:barMetrics:">- (void)setBackgroundImage:(UIImage *)backgroundImage forToolbarPosition:(UIToolbarPosition)topOrBottom barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setBackgroundImage:forToolbarPosition:barMetrics:") public native @Type("void") void setBackgroundImage(@Type("UIImage *") UIImage backgroundImage, @Type("UIToolbarPosition") UIToolbarPosition topOrBottom, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instm/UIToolbar/setItems:animated:">- (void)setItems:(NSArray *)items animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setItems:animated:") public native @Type("void") void setItems(@Type("NSArray *") NSArray items, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instm/UIToolbar/setShadowImage:forToolbarPosition:">- (void)setShadowImage:(UIImage *)shadowImage forToolbarPosition:(UIToolbarPosition)topOrBottom</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setShadowImage:forToolbarPosition:") public native @Type("void") void setShadowImage(@Type("UIImage *") UIImage shadowImage, @Type("UIToolbarPosition") UIToolbarPosition topOrBottom);
    /*</methods>*/

}
