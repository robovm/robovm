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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html">UIApplication Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIApplication /*</name>*/ 
    extends /*<extends>*/ UIResponder /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIApplication /*</name>*/.class);
    }

    /*<constructors>*/
    public UIApplication() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/applicationIconBadgeNumber">@property(nonatomic) NSInteger applicationIconBadgeNumber</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("applicationIconBadgeNumber") public native @Type("NSInteger") int getApplicationIconBadgeNumber();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/applicationIconBadgeNumber">@property(nonatomic) NSInteger applicationIconBadgeNumber</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setApplicationIconBadgeNumber:") public native void setApplicationIconBadgeNumber(@Type("NSInteger") int v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/applicationState">@property(nonatomic, readonly) UIApplicationState applicationState</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("applicationState") public native @Type("UIApplicationState") UIApplicationState getApplicationState();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/applicationSupportsShakeToEdit">@property(nonatomic) BOOL applicationSupportsShakeToEdit</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("applicationSupportsShakeToEdit") public native @Type("BOOL") boolean isApplicationSupportsShakeToEdit();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/applicationSupportsShakeToEdit">@property(nonatomic) BOOL applicationSupportsShakeToEdit</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setApplicationSupportsShakeToEdit:") public native void setApplicationSupportsShakeToEdit(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/backgroundTimeRemaining">@property(nonatomic, readonly) NSTimeInterval backgroundTimeRemaining</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("backgroundTimeRemaining") public native @Type("NSTimeInterval") double getBackgroundTimeRemaining();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/delegate">@property(nonatomic, assign) id&amp;lt;UIApplicationDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("delegate") public native @Type("id<UIApplicationDelegate>") UIApplicationDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/delegate">@property(nonatomic, assign) id&amp;lt;UIApplicationDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(@Type("id<UIApplicationDelegate>") UIApplicationDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/idleTimerDisabled">@property(nonatomic, getter=isIdleTimerDisabled) BOOL idleTimerDisabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isIdleTimerDisabled") public native @Type("BOOL") boolean isIdleTimerDisabled();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/idleTimerDisabled">@property(nonatomic, getter=isIdleTimerDisabled) BOOL idleTimerDisabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setIdleTimerDisabled:") public native void setIdleTimerDisabled(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/keyWindow">@property(nonatomic, readonly) UIWindow *keyWindow</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("keyWindow") public native @Type("UIWindow *") UIWindow getKeyWindow();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/networkActivityIndicatorVisible">@property(nonatomic, getter=isNetworkActivityIndicatorVisible) BOOL networkActivityIndicatorVisible</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isNetworkActivityIndicatorVisible") public native @Type("BOOL") boolean isNetworkActivityIndicatorVisible();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/networkActivityIndicatorVisible">@property(nonatomic, getter=isNetworkActivityIndicatorVisible) BOOL networkActivityIndicatorVisible</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setNetworkActivityIndicatorVisible:") public native void setNetworkActivityIndicatorVisible(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/protectedDataAvailable">@property(nonatomic, readonly, getter=isProtectedDataAvailable) BOOL protectedDataAvailable</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("isProtectedDataAvailable") public native @Type("BOOL") boolean isProtectedDataAvailable();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/scheduledLocalNotifications">@property(nonatomic, copy) NSArray *scheduledLocalNotifications</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("scheduledLocalNotifications") public native @Type("NSArray *") NSArray getScheduledLocalNotifications();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/scheduledLocalNotifications">@property(nonatomic, copy) NSArray *scheduledLocalNotifications</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("setScheduledLocalNotifications:") public native void setScheduledLocalNotifications(@Type("NSArray *") NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/statusBarFrame">@property(nonatomic, readonly) CGRect statusBarFrame</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("statusBarFrame") public native @Type("CGRect") CGRect getStatusBarFrame();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/statusBarHidden">@property(nonatomic, getter=isStatusBarHidden) BOOL statusBarHidden</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isStatusBarHidden") public native @Type("BOOL") boolean isStatusBarHidden();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/statusBarHidden">@property(nonatomic, getter=isStatusBarHidden) BOOL statusBarHidden</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setStatusBarHidden:") public native void setStatusBarHidden(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/statusBarOrientation">@property(nonatomic) UIInterfaceOrientation statusBarOrientation</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("statusBarOrientation") public native @Type("UIInterfaceOrientation") UIInterfaceOrientation getStatusBarOrientation();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/statusBarOrientation">@property(nonatomic) UIInterfaceOrientation statusBarOrientation</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setStatusBarOrientation:") public native void setStatusBarOrientation(@Type("UIInterfaceOrientation") UIInterfaceOrientation v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/statusBarOrientationAnimationDuration">@property(nonatomic, readonly) NSTimeInterval statusBarOrientationAnimationDuration</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("statusBarOrientationAnimationDuration") public native @Type("NSTimeInterval") double getStatusBarOrientationAnimationDuration();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/statusBarStyle">@property(nonatomic) UIStatusBarStyle statusBarStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("statusBarStyle") public native @Type("UIStatusBarStyle") UIStatusBarStyle getStatusBarStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/statusBarStyle">@property(nonatomic) UIStatusBarStyle statusBarStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setStatusBarStyle:") public native void setStatusBarStyle(@Type("UIStatusBarStyle") UIStatusBarStyle v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/userInterfaceLayoutDirection">@property(nonatomic, readonly) UIUserInterfaceLayoutDirection userInterfaceLayoutDirection</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("userInterfaceLayoutDirection") public native @Type("UIUserInterfaceLayoutDirection") UIUserInterfaceLayoutDirection getUserInterfaceLayoutDirection();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instp/UIApplication/windows">@property(nonatomic, readonly) NSArray *windows</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("windows") public native @Type("NSArray *") NSArray getWindows();
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/clm/UIApplication/sharedApplication">+ (UIApplication *)sharedApplication</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("sharedApplication") public native static @Type("UIApplication *") UIApplication getSharedApplication();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/beginBackgroundTaskWithExpirationHandler:">- (UIBackgroundTaskIdentifier)beginBackgroundTaskWithExpirationHandler:(void (^)(void))handler</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("beginBackgroundTaskWithExpirationHandler:") public native @Type("UIBackgroundTaskIdentifier") int beginBackgroundTask(@Type("void (^)(void)") VoidBlock handler);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/beginIgnoringInteractionEvents">- (void)beginIgnoringInteractionEvents</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("beginIgnoringInteractionEvents") public native @Type("void") void beginIgnoringInteractionEvents();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/beginReceivingRemoteControlEvents">- (void)beginReceivingRemoteControlEvents</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("beginReceivingRemoteControlEvents") public native @Type("void") void beginReceivingRemoteControlEvents();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/canOpenURL:">- (BOOL)canOpenURL:(NSURL *)url</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("canOpenURL:") public native @Type("BOOL") boolean canOpenURL(@Type("NSURL *") NSURL url);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/cancelAllLocalNotifications">- (void)cancelAllLocalNotifications</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("cancelAllLocalNotifications") public native @Type("void") void cancelAllLocalNotifications();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/cancelLocalNotification:">- (void)cancelLocalNotification:(UILocalNotification *)notification</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("cancelLocalNotification:") public native @Type("void") void cancelLocalNotification(@Type("UILocalNotification *") UILocalNotification notification);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/clearKeepAliveTimeout">- (void)clearKeepAliveTimeout</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("clearKeepAliveTimeout") public native @Type("void") void clearKeepAliveTimeout();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/completeStateRestoration">- (void)completeStateRestoration</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("completeStateRestoration") public native @Type("void") void completeStateRestoration();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/endBackgroundTask:">- (void)endBackgroundTask:(UIBackgroundTaskIdentifier)identifier</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("endBackgroundTask:") public native @Type("void") void endBackgroundTask(@Type("UIBackgroundTaskIdentifier") int identifier);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/endIgnoringInteractionEvents">- (void)endIgnoringInteractionEvents</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("endIgnoringInteractionEvents") public native @Type("void") void endIgnoringInteractionEvents();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/endReceivingRemoteControlEvents">- (void)endReceivingRemoteControlEvents</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("endReceivingRemoteControlEvents") public native @Type("void") void endReceivingRemoteControlEvents();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/extendStateRestoration">- (void)extendStateRestoration</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("extendStateRestoration") public native @Type("void") void extendStateRestoration();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/enabledRemoteNotificationTypes">- (UIRemoteNotificationType)enabledRemoteNotificationTypes</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("enabledRemoteNotificationTypes") public native @Type("UIRemoteNotificationType") EnumSet<UIRemoteNotificationType> getEnabledRemoteNotificationTypes();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/supportedInterfaceOrientationsForWindow:">- (NSUInteger)supportedInterfaceOrientationsForWindow:(UIWindow *)window</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("supportedInterfaceOrientationsForWindow:") public native @Type("NSUInteger") int getSupportedInterfaceOrientations(@Type("UIWindow *") UIWindow window);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/isIgnoringInteractionEvents">- (BOOL)isIgnoringInteractionEvents</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isIgnoringInteractionEvents") public native @Type("BOOL") boolean isIgnoringInteractionEvents();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/openURL:">- (BOOL)openURL:(NSURL *)url</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("openURL:") public native @Type("BOOL") boolean openURL(@Type("NSURL *") NSURL url);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/presentLocalNotificationNow:">- (void)presentLocalNotificationNow:(UILocalNotification *)notification</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("presentLocalNotificationNow:") public native @Type("void") void presentLocalNotificationNow(@Type("UILocalNotification *") UILocalNotification notification);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/registerForRemoteNotificationTypes:">- (void)registerForRemoteNotificationTypes:(UIRemoteNotificationType)types</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("registerForRemoteNotificationTypes:") public native @Type("void") void registerForRemoteNotificationTypes(@Type("UIRemoteNotificationType") EnumSet<UIRemoteNotificationType> types);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/scheduleLocalNotification:">- (void)scheduleLocalNotification:(UILocalNotification *)notification</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("scheduleLocalNotification:") public native @Type("void") void scheduleLocalNotification(@Type("UILocalNotification *") UILocalNotification notification);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/sendAction:to:from:forEvent:">- (BOOL)sendAction:(SEL)action to:(id)target from:(id)sender forEvent:(UIEvent *)event</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("sendAction:to:from:forEvent:") public native @Type("BOOL") boolean sendAction(@Type("SEL") Selector action, @Type("id") NSObject target, @Type("id") NSObject sender, @Type("UIEvent *") UIEvent event);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/sendEvent:">- (void)sendEvent:(UIEvent *)event</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("sendEvent:") public native @Type("void") void sendEvent(@Type("UIEvent *") UIEvent event);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/setKeepAliveTimeout:handler:">- (BOOL)setKeepAliveTimeout:(NSTimeInterval)timeout handler:(void (^)(void))keepAliveHandler</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("setKeepAliveTimeout:handler:") public native @Type("BOOL") boolean setKeepAliveTimeout(@Type("NSTimeInterval") double timeout, @Type("void (^)(void)") VoidBlock keepAliveHandler);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/setNewsstandIconImage:">- (void)setNewsstandIconImage:(UIImage *)image</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setNewsstandIconImage:") public native @Type("void") void setNewsstandIconImage(@Type("UIImage *") UIImage image);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/setStatusBarHidden:withAnimation:">- (void)setStatusBarHidden:(BOOL)hidden withAnimation:(UIStatusBarAnimation)animation</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setStatusBarHidden:withAnimation:") public native @Type("void") void setStatusBarHidden(@Type("BOOL") boolean hidden, @Type("UIStatusBarAnimation") UIStatusBarAnimation animation);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/setStatusBarOrientation:animated:">- (void)setStatusBarOrientation:(UIInterfaceOrientation)interfaceOrientation animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setStatusBarOrientation:animated:") public native @Type("void") void setStatusBarOrientation(@Type("UIInterfaceOrientation") UIInterfaceOrientation interfaceOrientation, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/setStatusBarStyle:animated:">- (void)setStatusBarStyle:(UIStatusBarStyle)statusBarStyle animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setStatusBarStyle:animated:") public native @Type("void") void setStatusBarStyle(@Type("UIStatusBarStyle") UIStatusBarStyle statusBarStyle, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIApplication_Class/Reference/Reference.html#//apple_ref/occ/instm/UIApplication/unregisterForRemoteNotifications">- (void)unregisterForRemoteNotifications</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("unregisterForRemoteNotifications") public native @Type("void") void unregisterForRemoteNotifications();
    /*</methods>*/

}
