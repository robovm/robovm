/*
 * Copyright (C) 2014 Trillian AB
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
package org.robovm.apple.uikit;

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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIApplication/*</name>*/ 
    extends /*<extends>*/UIResponder/*</extends>*/ 
    /*<implements>*/implements UIActionSheetDelegate/*</implements>*/ {

    /*<ptr>*/public static class UIApplicationPtr extends Ptr<UIApplication, UIApplicationPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIApplication.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIApplication() {}
    protected UIApplication(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "delegate")
    public native UIApplicationDelegate getDelegate();
    @Property(selector = "setDelegate:")
    public native void setDelegate(UIApplicationDelegate v);
    @Property(selector = "isIdleTimerDisabled")
    public native boolean isIdleTimerDisabled();
    @Property(selector = "setIdleTimerDisabled:")
    public native void setIdleTimerDisabled(boolean v);
    @Property(selector = "keyWindow")
    public native UIWindow getKeyWindow();
    @Property(selector = "windows")
    public native NSArray<?> getWindows();
    @Property(selector = "isNetworkActivityIndicatorVisible")
    public native boolean isNetworkActivityIndicatorVisible();
    @Property(selector = "setNetworkActivityIndicatorVisible:")
    public native void setNetworkActivityIndicatorVisible(boolean v);
    @Property(selector = "statusBarStyle")
    public native UIStatusBarStyle getStatusBarStyle();
    @Property(selector = "setStatusBarStyle:")
    public native void setStatusBarStyle(UIStatusBarStyle v);
    @Property(selector = "isStatusBarHidden")
    public native boolean isStatusBarHidden();
    @Property(selector = "setStatusBarHidden:")
    public native void setStatusBarHidden(boolean v);
    @Property(selector = "statusBarOrientation")
    public native UIInterfaceOrientation getStatusBarOrientation();
    @Property(selector = "setStatusBarOrientation:")
    public native void setStatusBarOrientation(UIInterfaceOrientation v);
    @Property(selector = "statusBarOrientationAnimationDuration")
    public native double getStatusBarOrientationAnimationDuration();
    @Property(selector = "statusBarFrame")
    public native @ByVal CGRect getStatusBarFrame();
    @Property(selector = "applicationIconBadgeNumber")
    public native @MachineSizedSInt long getApplicationIconBadgeNumber();
    @Property(selector = "setApplicationIconBadgeNumber:")
    public native void setApplicationIconBadgeNumber(@MachineSizedSInt long v);
    @Property(selector = "applicationSupportsShakeToEdit")
    public native boolean isApplicationSupportsShakeToEdit();
    @Property(selector = "setApplicationSupportsShakeToEdit:")
    public native void setApplicationSupportsShakeToEdit(boolean v);
    @Property(selector = "applicationState")
    public native UIApplicationState getApplicationState();
    @Property(selector = "backgroundTimeRemaining")
    public native double getBackgroundTimeRemaining();
    @Property(selector = "backgroundRefreshStatus")
    public native UIBackgroundRefreshStatus getBackgroundRefreshStatus();
    @Property(selector = "isProtectedDataAvailable")
    public native boolean isProtectedDataAvailable();
    @Property(selector = "userInterfaceLayoutDirection")
    public native UIUserInterfaceLayoutDirection getUserInterfaceLayoutDirection();
    @Property(selector = "preferredContentSizeCategory")
    public native String getPreferredContentSizeCategory();
    @Property(selector = "scheduledLocalNotifications")
    public native NSArray<?> getScheduledLocalNotifications();
    @Property(selector = "setScheduledLocalNotifications:")
    public native void setScheduledLocalNotifications(NSArray<?> v);
    @Property(selector = "isProximitySensingEnabled")
    public native boolean isProximitySensingEnabled();
    @Property(selector = "setProximitySensingEnabled:")
    public native void setProximitySensingEnabled(boolean v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "beginIgnoringInteractionEvents")
    public native void beginIgnoringInteractionEvents();
    @Method(selector = "endIgnoringInteractionEvents")
    public native void endIgnoringInteractionEvents();
    @Method(selector = "isIgnoringInteractionEvents")
    public native boolean isIgnoringInteractionEvents();
    @Method(selector = "openURL:")
    public native boolean openURL$(NSURL url);
    @Method(selector = "canOpenURL:")
    public native boolean canOpenURL$(NSURL url);
    @Method(selector = "sendEvent:")
    public native void sendEvent$(UIEvent event);
    @Method(selector = "sendAction:to:from:forEvent:")
    public native boolean sendAction$to$from$forEvent$(Selector action, NSObject target, NSObject sender, UIEvent event);
    @Method(selector = "setStatusBarStyle:animated:")
    public native void setStatusBarStyle$animated$(UIStatusBarStyle statusBarStyle, boolean animated);
    @Method(selector = "setStatusBarHidden:withAnimation:")
    public native void setStatusBarHidden$withAnimation$(boolean hidden, UIStatusBarAnimation animation);
    @Method(selector = "setStatusBarOrientation:animated:")
    public native void setStatusBarOrientation$animated$(UIInterfaceOrientation interfaceOrientation, boolean animated);
    @Method(selector = "supportedInterfaceOrientationsForWindow:")
    public native @MachineSizedUInt long getSupportedInterfaceOrientations(UIWindow window);
    @Method(selector = "beginBackgroundTaskWithExpirationHandler:")
    public native @MachineSizedUInt long beginBackgroundTask(ObjCBlock handler);
    @Method(selector = "beginBackgroundTaskWithName:expirationHandler:")
    public native @MachineSizedUInt long beginBackgroundTaskWithName$expirationHandler$(String taskName, ObjCBlock handler);
    @Method(selector = "endBackgroundTask:")
    public native void endBackgroundTask$(@MachineSizedUInt long identifier);
    @Method(selector = "setMinimumBackgroundFetchInterval:")
    public native void setMinimumBackgroundFetchInterval$(double minimumBackgroundFetchInterval);
    @Method(selector = "setKeepAliveTimeout:handler:")
    public native boolean setKeepAliveTimeout$handler$(double timeout, ObjCBlock keepAliveHandler);
    @Method(selector = "clearKeepAliveTimeout")
    public native void clearKeepAliveTimeout();
    @Method(selector = "sharedApplication")
    public static native UIApplication getSharedApplication();
    @Method(selector = "registerForRemoteNotificationTypes:")
    public native void registerForRemoteNotificationTypes$(UIRemoteNotificationType types);
    @Method(selector = "unregisterForRemoteNotifications")
    public native void unregisterForRemoteNotifications();
    @Method(selector = "enabledRemoteNotificationTypes")
    public native UIRemoteNotificationType getEnabledRemoteNotificationTypes();
    @Method(selector = "presentLocalNotificationNow:")
    public native void presentLocalNotificationNow$(UILocalNotification notification);
    @Method(selector = "scheduleLocalNotification:")
    public native void scheduleLocalNotification$(UILocalNotification notification);
    @Method(selector = "cancelLocalNotification:")
    public native void cancelLocalNotification$(UILocalNotification notification);
    @Method(selector = "cancelAllLocalNotifications")
    public native void cancelAllLocalNotifications();
    @Method(selector = "beginReceivingRemoteControlEvents")
    public native void beginReceivingRemoteControlEvents();
    @Method(selector = "endReceivingRemoteControlEvents")
    public native void endReceivingRemoteControlEvents();
    @Method(selector = "setNewsstandIconImage:")
    public native void setNewsstandIconImage$(UIImage image);
    @Method(selector = "extendStateRestoration")
    public native void extendStateRestoration();
    @Method(selector = "completeStateRestoration")
    public native void completeStateRestoration();
    @Method(selector = "ignoreSnapshotOnNextApplicationLaunch")
    public native void ignoreSnapshotOnNextApplicationLaunch();
    @Method(selector = "registerObjectForStateRestoration:restorationIdentifier:")
    public static native void registerObjectForStateRestoration$restorationIdentifier$(UIStateRestoring object, String restorationIdentifier);
    @Method(selector = "setStatusBarHidden:animated:")
    public native void setStatusBarHidden$animated$(boolean hidden, boolean animated);
    @Method(selector = "actionSheet:clickedButtonAtIndex:")
    public native void clicked(UIActionSheet actionSheet, @MachineSizedSInt long buttonIndex);
    @Method(selector = "actionSheetCancel:")
    public native void cancel(UIActionSheet actionSheet);
    @Method(selector = "willPresentActionSheet:")
    public native void willPresent(UIActionSheet actionSheet);
    @Method(selector = "didPresentActionSheet:")
    public native void didPresent(UIActionSheet actionSheet);
    @Method(selector = "actionSheet:willDismissWithButtonIndex:")
    public native void willDismiss(UIActionSheet actionSheet, @MachineSizedSInt long buttonIndex);
    @Method(selector = "actionSheet:didDismissWithButtonIndex:")
    public native void didDismiss(UIActionSheet actionSheet, @MachineSizedSInt long buttonIndex);
    /*</methods>*/
}
