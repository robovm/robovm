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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWindow_Class/UIWindowClassReference/UIWindowClassReference.html">UIWindow Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIWindow /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIWindow /*</name>*/.class);
    }

    /*<constructors>*/
    public UIWindow() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWindow_Class/UIWindowClassReference/UIWindowClassReference.html#//apple_ref/occ/instp/UIWindow/keyWindow">@property(nonatomic, readonly, getter=isKeyWindow) BOOL keyWindow</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isKeyWindow") public native @Type("BOOL") boolean isKeyWindow();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWindow_Class/UIWindowClassReference/UIWindowClassReference.html#//apple_ref/occ/instp/UIWindow/rootViewController">@property(nonatomic, retain) UIViewController *rootViewController</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("rootViewController") public native @Type("UIViewController *") UIViewController getRootViewController();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWindow_Class/UIWindowClassReference/UIWindowClassReference.html#//apple_ref/occ/instp/UIWindow/rootViewController">@property(nonatomic, retain) UIViewController *rootViewController</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("setRootViewController:") public native void setRootViewController(@Type("UIViewController *") UIViewController v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWindow_Class/UIWindowClassReference/UIWindowClassReference.html#//apple_ref/occ/instp/UIWindow/screen">@property(nonatomic, retain) UIScreen *screen</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("screen") public native @Type("UIScreen *") UIScreen getScreen();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWindow_Class/UIWindowClassReference/UIWindowClassReference.html#//apple_ref/occ/instp/UIWindow/screen">@property(nonatomic, retain) UIScreen *screen</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setScreen:") public native void setScreen(@Type("UIScreen *") UIScreen v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWindow_Class/UIWindowClassReference/UIWindowClassReference.html#//apple_ref/occ/instp/UIWindow/windowLevel">@property(nonatomic) UIWindowLevel windowLevel</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("windowLevel") public native @Type("UIWindowLevel") float getWindowLevel();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWindow_Class/UIWindowClassReference/UIWindowClassReference.html#//apple_ref/occ/instp/UIWindow/windowLevel">@property(nonatomic) UIWindowLevel windowLevel</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setWindowLevel:") public native void setWindowLevel(@Type("UIWindowLevel") float v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWindow_Class/UIWindowClassReference/UIWindowClassReference.html#//apple_ref/occ/instm/UIWindow/becomeKeyWindow">- (void)becomeKeyWindow</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("becomeKeyWindow") public native @Type("void") void becomeKeyWindow();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWindow_Class/UIWindowClassReference/UIWindowClassReference.html#//apple_ref/occ/instm/UIWindow/convertPoint:fromWindow:">- (CGPoint)convertPoint:(CGPoint)point fromWindow:(UIWindow *)window</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("convertPoint:fromWindow:") public native @Type("CGPoint") CGPoint convertPointFromWindow(@Type("CGPoint") CGPoint point, @Type("UIWindow *") UIWindow window);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWindow_Class/UIWindowClassReference/UIWindowClassReference.html#//apple_ref/occ/instm/UIWindow/convertPoint:toWindow:">- (CGPoint)convertPoint:(CGPoint)point toWindow:(UIWindow *)window</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("convertPoint:toWindow:") public native @Type("CGPoint") CGPoint convertPointToWindow(@Type("CGPoint") CGPoint point, @Type("UIWindow *") UIWindow window);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWindow_Class/UIWindowClassReference/UIWindowClassReference.html#//apple_ref/occ/instm/UIWindow/convertRect:fromWindow:">- (CGRect)convertRect:(CGRect)rect fromWindow:(UIWindow *)window</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("convertRect:fromWindow:") public native @Type("CGRect") CGRect convertRectFromWindow(@Type("CGRect") CGRect rect, @Type("UIWindow *") UIWindow window);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWindow_Class/UIWindowClassReference/UIWindowClassReference.html#//apple_ref/occ/instm/UIWindow/convertRect:toWindow:">- (CGRect)convertRect:(CGRect)rect toWindow:(UIWindow *)window</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("convertRect:toWindow:") public native @Type("CGRect") CGRect convertRectToWindow(@Type("CGRect") CGRect rect, @Type("UIWindow *") UIWindow window);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWindow_Class/UIWindowClassReference/UIWindowClassReference.html#//apple_ref/occ/instm/UIWindow/makeKeyAndVisible">- (void)makeKeyAndVisible</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("makeKeyAndVisible") public native @Type("void") void makeKeyAndVisible();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWindow_Class/UIWindowClassReference/UIWindowClassReference.html#//apple_ref/occ/instm/UIWindow/makeKeyWindow">- (void)makeKeyWindow</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("makeKeyWindow") public native @Type("void") void makeKeyWindow();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWindow_Class/UIWindowClassReference/UIWindowClassReference.html#//apple_ref/occ/instm/UIWindow/resignKeyWindow">- (void)resignKeyWindow</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("resignKeyWindow") public native @Type("void") void resignKeyWindow();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWindow_Class/UIWindowClassReference/UIWindowClassReference.html#//apple_ref/occ/instm/UIWindow/sendEvent:">- (void)sendEvent:(UIEvent *)event</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("sendEvent:") public native @Type("void") void sendEvent(@Type("UIEvent *") UIEvent event);
    /*</methods>*/

}
