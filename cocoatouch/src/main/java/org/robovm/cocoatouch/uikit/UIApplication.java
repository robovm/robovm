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
    @Bind("applicationIconBadgeNumber") public native @Type("NSInteger") int getApplicationIconBadgeNumber();
    @Bind("setApplicationIconBadgeNumber:") public native void setApplicationIconBadgeNumber(@Type("NSInteger") int v);
    @Bind("applicationState") public native @Type("UIApplicationState") UIApplicationState getApplicationState();
    @Bind("applicationSupportsShakeToEdit") public native @Type("BOOL") boolean isApplicationSupportsShakeToEdit();
    @Bind("setApplicationSupportsShakeToEdit:") public native void setApplicationSupportsShakeToEdit(@Type("BOOL") boolean v);
    @Bind("backgroundTimeRemaining") public native @Type("NSTimeInterval") double getBackgroundTimeRemaining();
    @Bind("delegate") public native @Type("id<UIApplicationDelegate>") UIApplicationDelegate getDelegate();
    @Bind("setDelegate:") public native void setDelegate(@Type("id<UIApplicationDelegate>") UIApplicationDelegate v);
    @Bind("isIdleTimerDisabled") public native @Type("BOOL") boolean isIdleTimerDisabled();
    @Bind("setIdleTimerDisabled:") public native void setIdleTimerDisabled(@Type("BOOL") boolean v);
    @Bind("keyWindow") public native @Type("UIWindow *") UIWindow getKeyWindow();
    @Bind("isNetworkActivityIndicatorVisible") public native @Type("BOOL") boolean isNetworkActivityIndicatorVisible();
    @Bind("setNetworkActivityIndicatorVisible:") public native void setNetworkActivityIndicatorVisible(@Type("BOOL") boolean v);
    @Bind("isProtectedDataAvailable") public native @Type("BOOL") boolean isProtectedDataAvailable();
    @Bind("scheduledLocalNotifications") public native @Type("NSArray *") NSArray getScheduledLocalNotifications();
    @Bind("setScheduledLocalNotifications:") public native void setScheduledLocalNotifications(@Type("NSArray *") NSArray v);
    @Bind("statusBarFrame") public native @Type("CGRect") CGRect getStatusBarFrame();
    @Bind("isStatusBarHidden") public native @Type("BOOL") boolean isStatusBarHidden();
    @Bind("setStatusBarHidden:") public native void setStatusBarHidden(@Type("BOOL") boolean v);
    @Bind("statusBarOrientation") public native @Type("UIInterfaceOrientation") UIInterfaceOrientation getStatusBarOrientation();
    @Bind("setStatusBarOrientation:") public native void setStatusBarOrientation(@Type("UIInterfaceOrientation") UIInterfaceOrientation v);
    @Bind("statusBarOrientationAnimationDuration") public native @Type("NSTimeInterval") double getStatusBarOrientationAnimationDuration();
    @Bind("statusBarStyle") public native @Type("UIStatusBarStyle") UIStatusBarStyle getStatusBarStyle();
    @Bind("setStatusBarStyle:") public native void setStatusBarStyle(@Type("UIStatusBarStyle") UIStatusBarStyle v);
    @Bind("userInterfaceLayoutDirection") public native @Type("UIUserInterfaceLayoutDirection") UIUserInterfaceLayoutDirection getUserInterfaceLayoutDirection();
    @Bind("windows") public native @Type("NSArray *") NSArray getWindows();
    /*</properties>*/
    /*<methods>*/
    @Bind("sharedApplication") public native static @Type("UIApplication *") UIApplication getSharedApplication();
    @Bind("beginBackgroundTaskWithExpirationHandler:") public native @Type("UIBackgroundTaskIdentifier") int beginBackgroundTask(@Type("void (^)(void)") VoidBlock handler);
    @Bind("beginIgnoringInteractionEvents") public native @Type("void") void beginIgnoringInteractionEvents();
    @Bind("beginReceivingRemoteControlEvents") public native @Type("void") void beginReceivingRemoteControlEvents();
    @Bind("canOpenURL:") public native @Type("BOOL") boolean canOpenURL(@Type("NSURL *") NSURL url);
    @Bind("cancelAllLocalNotifications") public native @Type("void") void cancelAllLocalNotifications();
    @Bind("cancelLocalNotification:") public native @Type("void") void cancelLocalNotification(@Type("UILocalNotification *") UILocalNotification notification);
    @Bind("clearKeepAliveTimeout") public native @Type("void") void clearKeepAliveTimeout();
    @Bind("completeStateRestoration") public native @Type("void") void completeStateRestoration();
    @Bind("endBackgroundTask:") public native @Type("void") void endBackgroundTask(@Type("UIBackgroundTaskIdentifier") int identifier);
    @Bind("endIgnoringInteractionEvents") public native @Type("void") void endIgnoringInteractionEvents();
    @Bind("endReceivingRemoteControlEvents") public native @Type("void") void endReceivingRemoteControlEvents();
    @Bind("extendStateRestoration") public native @Type("void") void extendStateRestoration();
    @Bind("enabledRemoteNotificationTypes") public native @Type("UIRemoteNotificationType") EnumSet<UIRemoteNotificationType> getEnabledRemoteNotificationTypes();
    @Bind("isIgnoringInteractionEvents") public native @Type("BOOL") boolean isIgnoringInteractionEvents();
    @Bind("openURL:") public native @Type("BOOL") boolean openURL(@Type("NSURL *") NSURL url);
    @Bind("presentLocalNotificationNow:") public native @Type("void") void presentLocalNotificationNow(@Type("UILocalNotification *") UILocalNotification notification);
    @Bind("registerForRemoteNotificationTypes:") public native @Type("void") void registerForRemoteNotificationTypes(@Type("UIRemoteNotificationType") EnumSet<UIRemoteNotificationType> types);
    @Bind("scheduleLocalNotification:") public native @Type("void") void scheduleLocalNotification(@Type("UILocalNotification *") UILocalNotification notification);
    @Bind("sendAction:to:from:forEvent:") public native @Type("BOOL") boolean sendAction(@Type("SEL") Selector action, @Type("id") NSObject target, @Type("id") NSObject sender, @Type("UIEvent *") UIEvent event);
    @Bind("sendEvent:") public native @Type("void") void sendEvent(@Type("UIEvent *") UIEvent event);
    @Bind("setKeepAliveTimeout:handler:") public native @Type("BOOL") boolean setKeepAliveTimeout(@Type("NSTimeInterval") double timeout, @Type("void (^)(void)") VoidBlock keepAliveHandler);
    @Bind("setNewsstandIconImage:") public native @Type("void") void setNewsstandIconImage(@Type("UIImage *") UIImage image);
    @Bind("setStatusBarHidden:withAnimation:") public native @Type("void") void setStatusBarHidden(@Type("BOOL") boolean hidden, @Type("UIStatusBarAnimation") UIStatusBarAnimation animation);
    @Bind("setStatusBarOrientation:animated:") public native @Type("void") void setStatusBarOrientation(@Type("UIInterfaceOrientation") UIInterfaceOrientation interfaceOrientation, @Type("BOOL") boolean animated);
    @Bind("setStatusBarStyle:animated:") public native @Type("void") void setStatusBarStyle(@Type("UIStatusBarStyle") UIStatusBarStyle statusBarStyle, @Type("BOOL") boolean animated);
    @Bind("supportedInterfaceOrientationsForWindow:") public native @Type("NSUInteger") int supportedInterfaceOrientationsForWindow(@Type("UIWindow *") UIWindow window);
    @Bind("unregisterForRemoteNotifications") public native @Type("void") void unregisterForRemoteNotifications();
    /*</methods>*/

}
